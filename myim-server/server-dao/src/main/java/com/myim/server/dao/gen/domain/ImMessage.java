package com.myim.server.dao.gen.domain;

import java.io.Serializable;
import java.util.Date;

public class ImMessage implements Serializable {
    private Long id;

    private Long imUserSingleRelationId;

    private String mKey;

    private String mRepeat;

    private Boolean mCheck;

    private Integer mStatus;

    private Integer msgId;

    private String mAction;

    private String title;

    private String content;

    private String mFormat;

    private String sender;

    private String receiver;

    private String extra;

    private Date mTimestamp;

    private Date updateTime;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getImUserSingleRelationId() {
        return imUserSingleRelationId;
    }

    public void setImUserSingleRelationId(Long imUserSingleRelationId) {
        this.imUserSingleRelationId = imUserSingleRelationId;
    }

    public String getmKey() {
        return mKey;
    }

    public void setmKey(String mKey) {
        this.mKey = mKey;
    }

    public String getmRepeat() {
        return mRepeat;
    }

    public void setmRepeat(String mRepeat) {
        this.mRepeat = mRepeat;
    }

    public Boolean getmCheck() {
        return mCheck;
    }

    public void setmCheck(Boolean mCheck) {
        this.mCheck = mCheck;
    }

    public Integer getmStatus() {
        return mStatus;
    }

    public void setmStatus(Integer mStatus) {
        this.mStatus = mStatus;
    }

    public Integer getMsgId() {
        return msgId;
    }

    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    public String getmAction() {
        return mAction;
    }

    public void setmAction(String mAction) {
        this.mAction = mAction;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getmFormat() {
        return mFormat;
    }

    public void setmFormat(String mFormat) {
        this.mFormat = mFormat;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public Date getmTimestamp() {
        return mTimestamp;
    }

    public void setmTimestamp(Date mTimestamp) {
        this.mTimestamp = mTimestamp;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", imUserSingleRelationId=").append(imUserSingleRelationId);
        sb.append(", mKey=").append(mKey);
        sb.append(", mRepeat=").append(mRepeat);
        sb.append(", mCheck=").append(mCheck);
        sb.append(", mStatus=").append(mStatus);
        sb.append(", msgId=").append(msgId);
        sb.append(", mAction=").append(mAction);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", mFormat=").append(mFormat);
        sb.append(", sender=").append(sender);
        sb.append(", receiver=").append(receiver);
        sb.append(", extra=").append(extra);
        sb.append(", mTimestamp=").append(mTimestamp);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}