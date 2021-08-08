package com.example.design.mode.demo.builder.two;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author wmx
 * @Date 2021/5/15 15:32
 * @Description 这个例子子大家也可以用于写配置文件(比如我们的链接池，里面很多必填或者不必填参数，
 *              同时也可以避免在因为属性值过多而写构造方法时产生不好维护，不雅观的现象)等
 */
@Data
public class CopyItem {
    private String name;
    private Long stock;

    public CopyItem(CopyItemBuilder copyItemBuilder){
        this.name = copyItemBuilder.name;
        this.stock = copyItemBuilder.stock;
    }

    public static void main(String[] args) {
        CopyItemBuilder builder = new CopyItemBuilder();
        try {
            CopyItem copyItem = builder.setName("copyItem").setStock(9999L).build();
            System.out.println(JSON.toJSONString(copyItem));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class CopyItemBuilder{
        private static final Long DEFAULT_STOCK = 0L;
        private String name;
        private Long stock = DEFAULT_STOCK;

        public CopyItem build(){
            //可以再处理一些额外的逻辑

            return new CopyItem(this);
        }

        public CopyItemBuilder setName(String name) throws Exception {
            if (StringUtils.isBlank(name)){
                throw new Exception("name could not be blank.");
            }
            this.name = name;
            return this;
        }

        public CopyItemBuilder setStock(Long stock) throws Exception {
            if (stock > 999999999L){
                throw new Exception("stock error");
            }
            this.stock = stock;
            return this;
        }

    }

}
