package com.myim.server.push;

import com.myim.server.message.handler.ClientPushHandler;
import com.myim.server.model.SentBody;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author cj
 */
public class PushTest {

    @Test
    public void setFieldTest() throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        SentBody sentBody = new SentBody();

        sentBody.put("name", "123");
        sentBody.put("age", "31");

        Method setField = ClientPushHandler.class.getDeclaredMethod("setField", SentBody.class, Class.class, Object.class);
        setField.setAccessible(true);
        Object object = setField.invoke(ClientPushHandler.class.newInstance(), sentBody, Stu.class, Stu.class.newInstance());
        System.out.println(object);
    }
}
