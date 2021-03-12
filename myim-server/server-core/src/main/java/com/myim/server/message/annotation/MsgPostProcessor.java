package com.myim.server.message.annotation;

import com.myim.server.message.MsgServiceBeanFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Component
public class MsgPostProcessor implements BeanPostProcessor {
    @Nullable
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        ImService imService = bean.getClass().getAnnotation(ImService.class);

        if (imService != null) {
            Method[] declaredMethods = bean.getClass().getDeclaredMethods();
            Arrays.stream(declaredMethods).forEach(method -> {
                ImMethod imMethod = method.getAnnotation(ImMethod.class);
                if (imMethod != null) {
                    String className = imService.value();
                    String methodName = imMethod.value();

                    MsgServiceBeanFactory.setMsgServiceBean(className, bean);
                    MsgServiceBeanFactory.setMsgMethodBean(methodName, method);
                }
            });
        }
        return bean;
    }
}
