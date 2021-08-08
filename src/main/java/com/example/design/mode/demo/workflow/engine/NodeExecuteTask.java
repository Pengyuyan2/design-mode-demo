package com.example.design.mode.demo.workflow.engine;

import java.util.concurrent.Callable;

/**
 * 4、执行Call方法，也就是执行我们的node节点
 * @author yig
 */
public class NodeExecuteTask implements Callable {
    private FlowNodeInterface flowNodeInterface;
    private FlowEngine.RunData nodeData;
    private Context context;

    public NodeExecuteTask(FlowNodeInterface flowNodeInterface, FlowEngine.RunData nodeData, Context context) {
        this.flowNodeInterface = flowNodeInterface;
        this.nodeData = nodeData;
        this.context = context;
    }

    public Object execute(){
        try{
            Object o = flowNodeInterface.invokeNode(nodeData, context);
            flowNodeInterface.afterInvoke(nodeData, context);
            return o;
        }catch (Throwable e){
            throw e;
        }
    }

    @Override
    public Object call() throws Exception {
        return execute();
    }
}
