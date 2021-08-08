package com.example.design.mode.demo.factory.actual.demo;

/**
 * 1、定义创建分享模版
 * @author yig
 */
public interface Share {
    /**
     * 获取分享类型
     * @return
     */
    String getShareFunctionType();

    /**
     * 处理方法
     * @param shareName
     * @return
     */
    String mainProcess(String shareName);

}
