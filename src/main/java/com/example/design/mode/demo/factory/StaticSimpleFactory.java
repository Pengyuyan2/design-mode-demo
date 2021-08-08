package com.example.design.mode.demo.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * 静态简单工厂模式
 * @author yig
 */
public class StaticSimpleFactory {
    private static final Map<EnumProductType, Product> activityIdMap = new HashMap<>();
    public static <T extends Product> void addProductKey(EnumProductType enumProductType, T product){
        activityIdMap.put(enumProductType, product);
    }

    public static ActivityOne product(EnumProductType type) throws IllegalAccessException, InstantiationException{
        Product product = activityIdMap.get(type);
        return (ActivityOne) product;
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        addProductKey(EnumProductType.ActivityOne, new ActivityOne("mac"));
        ActivityOne product = product(EnumProductType.ActivityOne);
        System.out.println(product.toString());
    }

    public static class Product{
        private String name;
        public String getName(){
            return name;
        }
        public void setName(String name){
            this.name = name;
        }
    }

    public static class ActivityOne extends Product{
        private String stock;

        public ActivityOne(String stock) {
            this.stock = stock;
        }

        @Override
        public String toString() {
            return "ActivityOne{" +
                    "stock=" + stock +
                    '}';
        }
    }

    public class ActivityTwo extends Product{
        private String stock;
    }

    public enum EnumProductType{
        ActivityOne, ActivityTwo;
    }
}
