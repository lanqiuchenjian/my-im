package com.myim.server.dao.gen.domain;

import java.io.Serializable;
import java.util.Date;

public class ImUserGroupCategory implements Serializable {
    private Long id;

    private Long imUserId;

    private String groupName;

    private String groupDesc;

    private Long count;

    private String extChar1;

    private Date updateTime;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getImUserId() {
        return imUserId;
    }

    public void setImUserId(Long imUserId) {
        this.imUserId = imUserId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getExtChar1() {
        return extChar1;
    }

    public void setExtChar1(String extChar1) {
        this.extChar1 = extChar1;
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
        sb.append(", imUserId=").append(imUserId);
        sb.append(", groupName=").append(groupName);
        sb.append(", groupDesc=").append(groupDesc);
        sb.append(", count=").append(count);
        sb.append(", extChar1=").append(extChar1);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}