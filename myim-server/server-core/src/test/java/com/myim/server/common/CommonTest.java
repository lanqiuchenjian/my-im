package com.myim.server.common;

import com.google.gson.Gson;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommonTest {

    private class Stu {
        private String a;
        public String b;
        protected String c;
        String d;
    }

    @Test
    public void stringTest() {
        Map<Stu, Stu> stuStuMap = new HashMap<>();
        Stu stu = new Stu();
        stu.a = "11";
        stuStuMap.put(stu, stu);
        String s = new Gson().toJson(stuStuMap);
        System.out.println(s);
        System.out.println("xx");
        System.out.println("pp");
    }

    @Test
    public void reflectTest() {
        Field[] fields = Stu.class.getFields();
        Arrays.stream(fields).forEach(f -> System.out.println(f.getName()));

        System.out.println("***********************************");

        fields = Stu.class.getDeclaredFields();
        Arrays.stream(fields).forEach(f -> System.out.println(f.getName()));
    }

    @Test
    public void superClassTest() {
        Class<? super String> superclass = String.class.getSuperclass();
        Type genericSuperclass = String.class.getGenericSuperclass();
        System.out.println("");
    }
}
