package com.example.design.mode.demo.builder.one;

/**
 * @Author wmx
 * @Date 2021/5/15 15:17
 * @Description 导演类。指导如何创建对象
 */
public class ItemDirector {
    private ItemBuilder itemBuilder;

    public ItemDirector(ItemBuilder itemBuilder){
        this.itemBuilder = itemBuilder;
    }

    public Item normalConstruct(){
        itemBuilder.buildNormal();
        return itemBuilder.getItem();
    }

    public Item cardConstruct(){
        itemBuilder.buildCard();
        return itemBuilder.getItem();
    }

    public Item videoConstruct(){
        itemBuilder.buildVideo();
        return itemBuilder.getItem();
    }

}
