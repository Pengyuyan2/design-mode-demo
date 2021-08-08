package com.example.design.mode.demo.factory;

/**
 * @author yig
 */
public class FactoryMethodProductFactory extends FactoryMethod{
    @Override
    protected Product createProduct(String activityId) {
        if (EnumProductType.ActivityOne.getName().equals(activityId)){
            //业务逻辑处理代码...

            return new OneProduct();
        }else if (EnumProductType.ActivityTwo.getName().equals(activityId)){
            return new TwoProduct();
        }

        return null;
    }

    public static class OneProduct extends Product{}
    public static class TwoProduct extends Product{}

    public static void main(String[] args) {
        FactoryMethod factoryMethod = new FactoryMethodProductFactory();
        Product product = factoryMethod.product("one", "one");
        System.out.println(product.getProductName());
    }
}
