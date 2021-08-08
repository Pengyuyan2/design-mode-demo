package com.example.design.mode.demo.builder.one;

/**
 * @Author wmx
 * @Date 2021/5/15 15:06
 * @Description 抽象建造者
 */
public abstract class ItemBuilder {
    protected Item item = new Item();
    public abstract void buildNormal();
    public abstract void buildCard();
    public abstract void buildVideo();

    public Item getItem(){
        return item;
    }
}
