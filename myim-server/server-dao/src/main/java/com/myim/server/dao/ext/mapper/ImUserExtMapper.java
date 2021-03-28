package com.myim.server.dao.ext.mapper;

import com.myim.server.dao.gen.domain.ImMessage;
import com.myim.server.dao.gen.domain.ImUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface ImUserExtMapper {
    @Select({
            "<script>",
            "select * from im_user where `id` &gt; ${id} order by id asc limit ${size}",
            "</script>"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
            @Result(column="nick_name", property="nickName", jdbcType=JdbcType.VARCHAR),
            @Result(column="phone", property="phone", jdbcType=JdbcType.INTEGER),
            @Result(column="photo", property="photo", jdbcType=JdbcType.VARCHAR),
            @Result(column="login_name", property="loginName", jdbcType=JdbcType.VARCHAR),
            @Result(column="login_password", property="loginPassword", jdbcType=JdbcType.VARCHAR),
            @Result(column="user_status", property="userStatus", jdbcType=JdbcType.INTEGER),
            @Result(column="service_alias_name", property="serviceAliasName", jdbcType=JdbcType.VARCHAR),
            @Result(column="ext_char1", property="extChar1", jdbcType=JdbcType.VARCHAR),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ImUser> getUserLists(@Param("id") Long userId, @Param("size") Long size);
}