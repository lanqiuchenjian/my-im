package com.myim.server.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class BeanCommon {
    public static void copyFromTo(Object from, Object to, Boolean copyNull) {
        BeanCopier beanCopier = BeanCopier.create(from.getClass(), to.getClass(), !copyNull);
        Converter converter = (fromBean, toClass, methodName) -> {
            try {
                if(fromBean == null){
                    try {
                        return to.getClass().getMethod("get" + methodName.toString().substring(3)).invoke(to);
                    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        e.printStackTrace();
                        return fromBean;
                    }
                }

                if (fromBean.getClass().equals(toClass)) {
                    return fromBean;
                }

                if(toClass.isAssignableFrom(Integer.class)){
                    return Integer.valueOf(StringUtils.isEmpty(fromBean.toString()) ? "0" : fromBean.toString());
                }else if(toClass.isAssignableFrom(Long.class)){
                    return Long.valueOf(StringUtils.isEmpty(fromBean.toString()) ? "0" : fromBean.toString());
                }else if(toClass.isAssignableFrom(Date.class)){
                    String timeStr = fromBean.toString();
                    DateTimeFormatter timeDtf = null;
                    if (timeStr.contains("."))
                        timeDtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
                    else
                        timeDtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime localDateTime = LocalDateTime.parse(timeStr, timeDtf);
                    ZoneId zone = ZoneId.systemDefault();
                    Instant instant = localDateTime.atZone(zone).toInstant();
                    return Date.from(instant);
                }else if(toClass.isAssignableFrom(byte[].class)){
                    return fromBean.toString().getBytes();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return fromBean;
        };

        if(copyNull)
            beanCopier.copy(from, to, null);
        else
            beanCopier .copy(from, to, converter);
    }


}
