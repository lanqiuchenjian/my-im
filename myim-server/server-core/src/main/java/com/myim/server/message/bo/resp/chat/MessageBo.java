package com.myim.server.message.bo.resp.chat;

import lombok.Data;

@Data
public class MessageBo {
    private String key;

    private String repeat;

    private long id;

    private String action;

    private String title;

    private String content;

    private String sender;

    private String receiver;

    private String format;

    private String extra;

    private long timestamp;
}
