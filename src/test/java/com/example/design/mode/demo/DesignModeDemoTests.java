package com.example.design.mode.demo;

import com.example.design.mode.demo.factory.actual.demo.Share;
import com.example.design.mode.demo.factory.actual.demo.ShareFactory;
import com.example.design.mode.demo.workflow.engine.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Map;

@SpringBootTest
class DesignModeDemoTests {
    @Autowired
    ShareFactory shareFactory;

    @Autowired
    FlowEngine flowEngine;

    /**
     * 工厂模式测试方法
     */
    @Test
    void contextLoads() {
        Share share = shareFactory.getShareFunction(ShareFactory.EnumShareType.SUCCESS_ORDER.getName());
        String result = share.mainProcess("Success order.");
        System.out.println(result);
    }

    /**
     * 流程引擎测试方法
     */
    @Test
    public void flowEngine() throws Exception {
        FlowNode testFlow =Flow.getTestFlow();
        FlowEngine.RunData nodeData = new FlowEngine.RunData();
        nodeData.setParamOne("one");
        nodeData.setParamTwo("two");
        Context context = new Context();
        flowEngine.execute(testFlow, nodeData, context);
        Map<String, Object> resultMap = context.getResultMap();
        System.out.println(resultMap.get("NodeOne"));
        System.out.println(resultMap.get("NodeTwo"));
    }

    public static class Flow{
        private static FlowNode testFlow = new FlowNode();
        static {
            testFlow.add(NodeOne.class, new FlowNode.NodeConf());
            testFlow.add(NodeTwo.class, new FlowNode.NodeConf());
            testFlow.add("three", NodeOne.class, new FlowNode.NodeConf());
            testFlow.add("three", NodeTwo.class, new FlowNode.NodeConf());
        }

        public static FlowNode getTestFlow() {
            return testFlow;
        }
    }

}
