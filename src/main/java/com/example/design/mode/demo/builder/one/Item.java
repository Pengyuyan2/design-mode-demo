package com.example.design.mode.demo.builder.one;

import lombok.Data;

/**
 * @Author wmx
 * @Date 2021/5/15 15:07
 * @Description 基础商品类
 */
@Data
public class Item {
    private String itemName;
    private Integer type;
    private String code;
    private String url;
}
