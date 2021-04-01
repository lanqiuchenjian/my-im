package com.myim.server.robot;

import com.google.gson.Gson;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import java.io.IOException;
import java.net.URLEncoder;
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

        NameValuePair n1 = new BasicNameValuePair("type", "text");
//        NameValuePair n2 = new BasicNameValuePair("query", URLEncoder.encode("上海天气", "UTF-8"));
        NameValuePair n2 = new BasicNameValuePair("query", "上海天气");
        NameValuePair n3 = new BasicNameValuePair("validate", "2|1:0|10:1617279414|10:validation|44:ZjRiZGUyYTM0MmM3Yzc1YWEyNzZmNzhiMjZjZmJkOGE=|ef5ca07e997076d32f457e8e47022332e3f64d1deb7ad2e4a39529df6294c06b");
        NameValuePair n4 = new BasicNameValuePair("uuid", UUID.randomUUID().toString());


        String s = Request.Post(ROBOT_URL).addHeader(header1).addHeader(header2).addHeader(header3)
                .bodyString("type=text", ContentType.APPLICATION_FORM_URLENCODED)
                .bodyString("query=上海天气", ContentType.APPLICATION_FORM_URLENCODED)
                .bodyString("validate=2|1:0|10:1617279414|10:validation|44:ZjRiZGUyYTM0MmM3Yzc1YWEyNzZmNzhiMjZjZmJkOGE=|ef5ca07e997076d32f457e8e47022332e3f64d1deb7ad2e4a39529df6294c06b", ContentType.APPLICATION_FORM_URLENCODED)
                .bodyString("uuid=123321", ContentType.APPLICATION_FORM_URLENCODED)
                .execute().returnContent().asString();
        HashMap m = new HashMap();
        new Gson();
        System.out.println(s);
    }
}
