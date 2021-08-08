package com.example.design.mode.demo.factory.actual.demo;

import org.springframework.stereotype.Component;

/**
 * 定义不同的类型来实现分享图片
 * @author yig
 */
@Component
public class SuccessOrderShare implements Share{
    @Override
    public String getShareFunctionType() {
        return ShareFactory.EnumShareType.SUCCESS_ORDER.getName();
    }

    @Override
    public String mainProcess(String shareName) {
        return shareName;
    }

}
