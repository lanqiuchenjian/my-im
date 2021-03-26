package com.myim.server.dao.ext.mapper;

import com.myim.server.dao.gen.domain.ImMessage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface ImMessageExtMapper {
//    @Select({
//            "<script>",
//            "select * from im_message",
//            "where im_user_single_relation_id = ${imUserSingleRelationId}",
//            "and id > ${id}",
//            "limit ${size}",
//            "</script>"
//    })
//    List<ImMessage> getHisMesByPageWithId(@Param("id") Long messageId, @Param("size") Long size, @Param("imUserSingleRelationId") Long imUserSingleRelationId);
//
//    @Select({
//            "<script>",
//            "select * from im_message",
//            "where im_user_single_relation_id = ${imUserSingleRelationId}",
//            "and id > ${id}",
//            "limit ${size}",
//            "</script>"
//    })
//    List<ImMessage> getHisMesByPageWithOffset(@Param("id") Long messageId, @Param("size") Long size, @Param("imUserSingleRelationId") Long imUserSingleRelationId);

    @Select({
            "<script>",
            "select * from im_message where im_user_single_relation_id = ${imUserSingleRelationId} and `id` &lt; ${id} order by `id` desc limit ${size}",
            "</script>"
    })
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="im_user_single_relation_id", property="imUserSingleRelationId", jdbcType=JdbcType.BIGINT),
            @Result(column="m_key", property="mKey", jdbcType=JdbcType.VARCHAR),
            @Result(column="m_repeat", property="mRepeat", jdbcType=JdbcType.VARCHAR),
            @Result(column="m_check", property="mCheck", jdbcType=JdbcType.BIT),
            @Result(column="m_status", property="mStatus", jdbcType=JdbcType.INTEGER),
            @Result(column="msg_id", property="msgId", jdbcType=JdbcType.INTEGER),
            @Result(column="m_action", property="mAction", jdbcType=JdbcType.VARCHAR),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
            @Result(column="m_format", property="mFormat", jdbcType=JdbcType.VARCHAR),
            @Result(column="sender", property="sender", jdbcType=JdbcType.VARCHAR),
            @Result(column="receiver", property="receiver", jdbcType=JdbcType.VARCHAR),
            @Result(column="extra", property="extra", jdbcType=JdbcType.VARCHAR),
            @Result(column="m_timestamp", property="mTimestamp", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ImMessage> getHisMesByPageWithIdDesc(@Param("id") Long messageId, @Param("size") Long size, @Param("imUserSingleRelationId") Long imUserSingleRelationId);
    @Select({
            "<script>",
            "select count(*) from im_message",
            "where im_user_single_relation_id = ${imUserSingleRelationId}",
            "</script>"
    })
    int getCount(@Param("imUserSingleRelationId") Long imUserSingleRelationId);
}