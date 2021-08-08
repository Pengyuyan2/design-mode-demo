package com.example.design.mode.demo.factory.actual.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 2、创建一个分享工厂根据我们的类型获取我们预先加载在Spring容器中的bean实列
 * @author yig
 */
@Component
public class ShareFactory {
    @Autowired
    private List<Share> shareFunctionList;

    /**
     * 根据分享类型获取对应的分享处理方式
     * @param type
     * @return
     */
    public Share getShareFunction(String type){
        for (Share share : shareFunctionList) {
            if (share.getShareFunctionType().equals(type)){
                return share;
            }
        }
        return null;
    }

    public enum EnumShareType{
        SUCCESS_ORDER("successOrder");

        private String name;

        EnumShareType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

    }

}
