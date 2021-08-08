package com.example.design.mode.demo.workflow.engine;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 3、引擎类，也是核心类。通过我们添加的node节点判断我们哪些流程是需要串行的那些是需要并行的，通过线程池创建线程放入Feature中，来达到同步执行的效果。
 * @author yig
 */
@Service
public class FlowEngine {
    /**
     * 引擎执行入口
     * @param flowNode
     * @param runData
     * @param context
     * @throws Exception
     */
    public void execute(FlowNode flowNode, RunData runData, Context context) throws Exception{
        Map<String, List<String>> nodeGroup = groupByGroupName(flowNode);
        Map<String, FlowNode.NodeConf> nodeMap = flowNode.getNodeMap();
        for (String groupName : nodeGroup.keySet()) {
            boolean needThrowExp = false;
            List<String> nodeNameList = nodeGroup.get(groupName);
            //只有一个Node的节点，串行执行
            if (nodeNameList.size() == 1){
                String nodeName = nodeNameList.get(0);
                FlowNodeInterface detailNode = (FlowNodeInterface) BeanService.getSingleBeanByType(Class.forName(nodeName));
                NodeExecuteTask nodeExecuteTask = new NodeExecuteTask(detailNode, runData, context);
                try{
                    Object result = nodeExecuteTask.execute();
                    context.getResultMap().put(detailNode.resultKey(), result);
                }catch (Exception e){
                    needThrowExp = true;
                }
            } else {
                //多个Node的节点，并行执行
                List<Future> resultList = new ArrayList<>();
                List<String> executedNodeNameList = new ArrayList<>();
                List<NodeExecuteTask> executeTaskList = new ArrayList<>();
                for (String nodeName : nodeNameList) {
                    FlowNodeInterface detailNode = (FlowNodeInterface) BeanService.getSingleBeanByType(Class.forName(nodeName));
                    NodeExecuteTask nodeExecuteTask = new NodeExecuteTask(detailNode, runData, context);
                    executeTaskList.add(nodeExecuteTask);
                    executedNodeNameList.add(nodeName);
                    resultList.add(threadPool.submit(nodeExecuteTask));
                }

                for (int i = 0, size = resultList.size(); i < size; i++) {
                    String nodeName = executedNodeNameList.get(i);
                    String nodeKey = groupName + "_" + nodeName;
                    FlowNodeInterface detailNode = (FlowNodeInterface) BeanService.getSingleBeanByType(Class.forName(nodeName));
                    FlowNode.NodeConf nodeConf = nodeMap.get(nodeKey);
                    int timeout = nodeConf.getTimeout();
                    Future future = resultList.get(i);
                    try {
                        Object o = future.get(timeout, TimeUnit.MILLISECONDS);
                        context.getResultMap().put(detailNode.resultKey(), o);
                    }catch (ExecutionException e){
                        needThrowExp = true;
                    }catch (TimeoutException e){
                        needThrowExp = true;
                    }catch (Exception e){
                        needThrowExp = true;
                    }
                }
            }
            if (needThrowExp){
                throw new RuntimeException();
            }
        }
    }

    private Map<String, List<String>> groupByGroupName(FlowNode flowNode) {
        Map<String, List<String>> nodeGroup = new LinkedHashMap<>();
        for (String nodeKey : flowNode.getNodeList()) {
            String groupName = getGroupName(nodeKey);
            String nodeName = getNodeName(nodeKey);
            if (StringUtils.isBlank(groupName)){
                List<String> nodeNameList = new ArrayList<>();
                nodeNameList.add(nodeName);
                nodeGroup.put(nodeName, nodeNameList);
            }else{
                List<String> nodeNameList = nodeGroup.get(groupName);
                if (nodeNameList == null){
                    nodeNameList = new ArrayList<>();
                }
                nodeNameList.add(nodeName);
                nodeGroup.put(groupName, nodeNameList);
            }
        }
        return nodeGroup;
    }

    private String getNodeName(String nodeKey) {
        String[] arr = nodeKey.split("_");
        return arr.length == 2 ? arr[0] : null;
    }

    private String getGroupName(String nodeKey) {
        String[] arr = nodeKey.split("_");
        return  arr.length == 2 ? arr[1] : arr[0];
    }

    /**
     * 在使用线程池的时候我们需要考虑不要设置的参数过大，开启另外的线程也是会占用机器内脆的，一个线程按1兆来算，你开启几百上千个，
     * 也会占用很大的一部分内存。尽可能的去采用池化思想，这里就按大家实际场景去做测试。
     */
    public static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 60L,
            TimeUnit.MINUTES, new LinkedBlockingDeque<>(500), new NamedThreadFactory("engine processor"),
            new ThreadPoolExecutor.AbortPolicy(){
                private AtomicInteger num = new AtomicInteger();

                @Override
                public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                    num.incrementAndGet();
                    throw new RejectedExecutionException("Task " + r.toString() + " was rejected from " + e.toString());
                }
            });

    public static class RunData{
        private String paramOne;
        private String paramTwo;

        public String getParamOne() {
            return paramOne;
        }

        public void setParamOne(String paramOne) {
            this.paramOne = paramOne;
        }

        public String getParamTwo() {
            return paramTwo;
        }

        public void setParamTwo(String paramTwo) {
            this.paramTwo = paramTwo;
        }
    }
}
