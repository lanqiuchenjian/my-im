package com.myim.server.message.annotation;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ImPropertyResolver {

    public Object[] resolver(JsonObject jsonObject, Parameter[] parameters) {
        Object[] data = null;
        if (null != parameters) {
            int length = parameters.length;
            data = new Object[length];
            for (int i = 0; i < length; i++) {
                Object value = null;
                Parameter p = parameters[i];
                Type type = p.getParameterizedType();
                Define pd = p.getAnnotation(Define.class);
                if (null != pd) {
                    String name = pd.value();
                    value = getParameterValue(name, jsonObject, type);
                } else if (type instanceof ParameterizedType) {

                } else if (type instanceof GenericArrayType) {

                } else if (type instanceof Class) {
                    Class<?> genericClass = (Class<?>) type;

                    if (Body.class.isAssignableFrom(genericClass)) {
                        value = gson.fromJson(jsonObject, type);
                    } else if (JsonElement.class.isAssignableFrom(genericClass)) {

                    } else if (JsonObject.class.isAssignableFrom(genericClass)) {
                        value = jsonObject;
                    } else if (OnlyPropertyUtil.isString(genericClass)) {

                    } else if (OnlyPropertyUtil.isPrimitive(genericClass)) {

                    } else {
                        value = beanBox.getBean(genericClass);
                    }
                    if (null == value) {
                        if (OnlyClassUtil.isCanInstance(genericClass)) {
                            try {
                                value = genericClass.newInstance();
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (null == value) {
                        value =OnlyPropertyUtil.getDefaultValue(genericClass);
                    }
                }
                data[i] = value;
            }
        }
        return data;
    }

    private Object getParameterValue(String name, JsonObject bodyObject, Type type) {
        Object o = null;
        if (null != bodyObject) {
            JsonElement je = bodyObject.get(name);
            if (null != je) {
                o = gson.fromJson(je, type);
            }
        }
        return o;
    }
}
