package com.example.design.mode.demo.workflow.engine;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 6、因为我们在节点里面存的Class类，所以我们这里通过实现ApplicationContextAware类来获取Spring容器中的bean实例
 * @author yig
 */
public class BeanService implements ApplicationContextAware {
    /**
     * spring bean上下文
     */
    protected static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanService.applicationContext = applicationContext;
    }

    public static Object getBeanByName(String name) throws BeansException{
        return applicationContext.getBean(name);
    }

    public static <T> T getSingleBeanByType(Class<T> clazz) throws Exception{
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanName : beanDefinitionNames) {
            Object beanByName = getBeanByName(beanName);
            if (beanByName != null){
                /**
                 * 防止被代理导致拿不到bean
                 */
                Object target = AppTargetUtil.getTarget(beanByName);
                if (clazz.getName().equals(target.getClass().getName())){
                    return (T) beanByName;
                }
            }
        }
        throw new RuntimeException();
    }

}
