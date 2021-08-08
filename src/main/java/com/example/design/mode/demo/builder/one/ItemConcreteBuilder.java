package com.example.design.mode.demo.builder.one;

import com.alibaba.fastjson.JSON;

/**
 * @Author wmx
 * @Date 2021/5/15 15:11
 * @Description 具体建造者类
 */
public class ItemConcreteBuilder extends ItemBuilder {
    @Override
    public void buildNormal() {
        item.setItemName("普通商品");
        item.setType(1);
    }

    @Override
    public void buildCard() {
        item.setItemName("卡券商品");
        item.setCode("123456");
        item.setType(2);
    }

    @Override
    public void buildVideo() {
        item.setItemName("视频商品");
        item.setType(3);
        item.setUrl("http://www.baidu.com");
    }

    public static void main(String[] args) {
        ItemBuilder itemBuilder = new ItemConcreteBuilder();
        ItemDirector director = new ItemDirector(itemBuilder);
        Item normalItem = director.normalConstruct();
        System.out.println(JSON.toJSONString(normalItem));
        //剔除导演类
        ItemBuilder itemBuilder1 = new ItemConcreteBuilder();
        itemBuilder1.buildVideo();
        Item videoItem = itemBuilder1.getItem();
        System.out.println(JSON.toJSONString(videoItem));
    }
}
