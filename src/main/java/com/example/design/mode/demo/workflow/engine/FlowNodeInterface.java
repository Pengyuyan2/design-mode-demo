package com.example.design.mode.demo.workflow.engine;

/**
 * 5、创建节点接口，这里面定义了一个ResultKey，这个Key也就是跟我流程中的这个节点所绑定，在获取数据的时候也就是通过这个key来标识
 * @author yig
 */
public interface FlowNodeInterface<T> {
    /**
     * Node的执行方法
     * @param nodeData
     * @param context
     * @return
     */
    T invokeNode(FlowEngine.RunData nodeData, Context context);

    /**
     * node执行完后执行的方法
     * @param nodeData
     * @param context
     */
    void afterInvoke(FlowEngine.RunData nodeData, Context context);

    /**
     * 从context中获取此node结果的key
     * @return
     */
    String resultKey();
}
