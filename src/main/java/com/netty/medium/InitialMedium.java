package com.netty.medium;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Method;
import java.util.Map;

@Component
public class InitialMedium implements BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean.getClass().isAnnotationPresent(Controller.class)) {
            Method[] methods = bean.getClass().getDeclaredMethods();
            for(Method method : methods){
                String methodName = bean.getClass().getName()+"."+method.getName();
                Map<String,BeanMethod> beanMethodMap = Media.beanMap;
                BeanMethod beanMethod = new BeanMethod();
                beanMethod.setBean(bean);
                beanMethod.setMethod(method);
                beanMethodMap.put(methodName,beanMethod);
            }
        }
        return bean;
    }
}
