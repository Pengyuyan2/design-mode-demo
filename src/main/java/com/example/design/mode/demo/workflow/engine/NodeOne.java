package com.example.design.mode.demo.workflow.engine;

import org.springframework.stereotype.Service;

/**
 * 7、创建测试node节点一
 * @author yig
 */
@Service
public class NodeOne implements FlowNodeInterface{

    @Override
    public Object invokeNode(FlowEngine.RunData nodeData, Context context) {
        System.out.println("执行方法" + nodeData.getParamOne());
        return nodeData.getParamOne();
    }

    @Override
    public void afterInvoke(FlowEngine.RunData nodeData, Context context) {

    }

    @Override
    public String resultKey() {
        return "NodeOne";
    }
}
