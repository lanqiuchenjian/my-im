package com.myim.server.robot;

import com.google.gson.Gson;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class SendToRobot {
    @Test
    public void setToRobotTest() throws IOException {
        String ROBOT_URL = "http://47.110.41.97:5000/chat";
        String COOKIE = "ajs_anonymous_id=%22a9d83cda-d314-4672-93e4-9d6a4685b883%22; _ga=GA1.1.803276138.1603346358; jenkins-timestamper-offset=-28800000; _xsrf=2|a45acb71|934a964a62f3a11d44450dd6a08967bf|1617279399; validation=\"2|1:0|10:1617279414|10:validation|44:ZjRiZGUyYTM0MmM3Yzc1YWEyNzZmNzhiMjZjZmJkOGE=|ef5ca07e997076d32f457e8e47022332e3f64d1deb7ad2e4a39529df6294c06b\"";
        String CONTENT_TYPE = "application/x-www-form-urlencoded";
        Header header1 = new BasicHeader("cookie", COOKIE);
        Header header2 = new BasicHeader("charset", "UTF-8");
        Header header3 = new BasicHeader("Content-type", CONTENT_TYPE);

        ArrayList<BasicNameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("type", "text"));
        list.add(new BasicNameValuePair("query", "上海天气"));
        list.add(new BasicNameValuePair("validate", "2|1:0|10:1617279414|10:validation|44:ZjRiZGUyYTM0MmM3Yzc1YWEyNzZmNzhiMjZjZmJkOGE=|ef5ca07e997076d32f457e8e47022332e3f64d1deb7ad2e4a39529df6294c06b"));
        list.add(new BasicNameValuePair("uuid", "12321"));

        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "UTF-8");
        String s =Request.Post(ROBOT_URL).addHeader(header1).addHeader(header2).addHeader(header3).body(urlEncodedFormEntity).execute().returnContent().asString();
        System.out.println(s);
    }
}
