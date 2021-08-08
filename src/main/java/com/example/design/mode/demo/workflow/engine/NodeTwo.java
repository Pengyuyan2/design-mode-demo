package com.example.design.mode.demo.workflow.engine;

import org.springframework.stereotype.Service;

/**
 * 7、创建测试node节点二
 * @author yig
 */
@Service
public class NodeTwo implements FlowNodeInterface{
    @Override
    public Object invokeNode(FlowEngine.RunData nodeData, Context context) {
        System.out.println("执行方法" + nodeData.getParamTwo());
        return nodeData.getParamTwo();
    }

    @Override
    public void afterInvoke(FlowEngine.RunData nodeData, Context context) {

    }

    @Override
    public String resultKey() {
        return "NodeTwo";
    }
}
