package com.myim.server.message.bo.resp.chat;

import java.util.List;

public class OfflineMessageBo{
    private Long count;
    private String toImUserLoginName;
    private List<MessageBo> list;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getToImUserLoginName() {
        return toImUserLoginName;
    }

    public void setToImUserLoginName(String toImUserLoginName) {
        this.toImUserLoginName = toImUserLoginName;
    }

    public List<MessageBo> getList() {
        return list;
    }

    public void setList(List<MessageBo> list) {
        this.list = list;
    }
}
