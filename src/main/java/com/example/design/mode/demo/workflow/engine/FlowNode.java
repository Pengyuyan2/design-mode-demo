package com.example.design.mode.demo.workflow.engine;

import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * 2、创建流程节点，这相当于就是保存我们整个流程中需要执行下游服务的节点，
 * 以Map作为保存数据，NodeConf 节点设置参数，自定义请求服务超时时间（因为并行我们是用的线程池或者通过get设置时间，get返回值结果）
 * @author yig
 */
public class FlowNode {
    private Map<String, NodeConf> nodeMap = new LinkedHashMap<>();

    public void add(String groupName, Class nodeName, NodeConf nodeConf){
        String key;
        if (StringUtils.isNotBlank(groupName)){
            key = groupName + "_" + nodeName.getName();
        } else {
            key = nodeName.getName();
        }

        if (nodeMap.containsKey(key)){
            return;
        }
        nodeMap.put(key, nodeConf);
    }

    public void add(Class nodeName, NodeConf nodeConf){
        add(nodeName.getName(), nodeName, nodeConf);
    }

    public void replace(String groupName, Class nodeName, NodeConf nodeConf){
        String key;
        if (StringUtils.isNotBlank(groupName)){
            key = groupName + "_" + nodeName.getName();
        } else {
            key = nodeName.getName();
        }
        nodeMap.put(key, nodeConf);
    }

    public void replace(Class nodeName, NodeConf nodeConf){
        replace(nodeName.getName(), nodeName, nodeConf);
    }

    public void remove(Class nodeName){
        remove(nodeName.getName(), nodeName);
    }

    public void remove(String groupName, Class nodeName){
        String key;
        if (StringUtils.isNotBlank(groupName)){
            key = groupName + "_" + nodeName.getName();
        } else {
            key = nodeName.getName();
        }
        nodeMap.remove(key);
    }

    public Set<String> getNodeList(){
        return nodeMap.keySet();
    }

    public Map<String, NodeConf> getNodeMap(){
        return nodeMap;
    }

    public void setNodeMap(Map<String, NodeConf> nodeMap) {
        this.nodeMap = nodeMap;
    }

    public static class NodeConf {
        private int timeout = 100;
        public NodeConf(){}

        public NodeConf(int timeout) {
            this.timeout = timeout;
        }

        public int getTimeout() {
            return timeout;
        }

        public void setTimeout(int timeout) {
            this.timeout = timeout;
        }
    }

}
