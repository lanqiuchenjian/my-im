package com.myim.server.gen.domain;

import java.io.Serializable;
import java.util.Date;

public class ImUserGroupRelation implements Serializable {
    private Long id;

    private Long imUserGroupCategoryId;

    private Long imUserId;

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

    public Long getImUserGroupCategoryId() {
        return imUserGroupCategoryId;
    }

    public void setImUserGroupCategoryId(Long imUserGroupCategoryId) {
        this.imUserGroupCategoryId = imUserGroupCategoryId;
    }

    public Long getImUserId() {
        return imUserId;
    }

    public void setImUserId(Long imUserId) {
        this.imUserId = imUserId;
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
        sb.append(", imUserGroupCategoryId=").append(imUserGroupCategoryId);
        sb.append(", imUserId=").append(imUserId);
        sb.append(", extChar1=").append(extChar1);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}