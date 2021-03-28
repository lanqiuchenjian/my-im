package com.myim.server.dao.gen.domain;

import java.io.Serializable;
import java.util.Date;

public class ImOfflineMessage implements Serializable {
    private Long id;

    private Long toImUserId;

    private String toImUserLoginName;

    private String fromImUserLoginName;

    private Long offlineMesCount;

    private Boolean isOffline;

    private String extra;

    private Date updateTime;

    private Date createTime;

    private Long imMessageId;

    private Long fromImUserId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getToImUserId() {
        return toImUserId;
    }

    public void setToImUserId(Long toImUserId) {
        this.toImUserId = toImUserId;
    }

    public String getToImUserLoginName() {
        return toImUserLoginName;
    }

    public void setToImUserLoginName(String toImUserLoginName) {
        this.toImUserLoginName = toImUserLoginName;
    }

    public String getFromImUserLoginName() {
        return fromImUserLoginName;
    }

    public void setFromImUserLoginName(String fromImUserLoginName) {
        this.fromImUserLoginName = fromImUserLoginName;
    }

    public Long getOfflineMesCount() {
        return offlineMesCount;
    }

    public void setOfflineMesCount(Long offlineMesCount) {
        this.offlineMesCount = offlineMesCount;
    }

    public Boolean getIsOffline() {
        return isOffline;
    }

    public void setIsOffline(Boolean isOffline) {
        this.isOffline = isOffline;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
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

    public Long getImMessageId() {
        return imMessageId;
    }

    public void setImMessageId(Long imMessageId) {
        this.imMessageId = imMessageId;
    }

    public Long getFromImUserId() {
        return fromImUserId;
    }

    public void setFromImUserId(Long fromImUserId) {
        this.fromImUserId = fromImUserId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", toImUserId=").append(toImUserId);
        sb.append(", toImUserLoginName=").append(toImUserLoginName);
        sb.append(", fromImUserLoginName=").append(fromImUserLoginName);
        sb.append(", offlineMesCount=").append(offlineMesCount);
        sb.append(", isOffline=").append(isOffline);
        sb.append(", extra=").append(extra);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", imMessageId=").append(imMessageId);
        sb.append(", fromImUserId=").append(fromImUserId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}