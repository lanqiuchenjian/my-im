package com.myim.server.message.bo;

import lombok.Data;

@Data
public class BaseRequestBo {
    //消息唯一值，非消息为空
    private String key;
}
