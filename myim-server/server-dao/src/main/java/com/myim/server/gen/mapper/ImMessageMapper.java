package com.myim.server.gen.mapper;

import com.myim.server.gen.domain.ImMessage;
import com.myim.server.gen.domain.ImMessageExample;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface ImMessageMapper {
    @DeleteProvider(type=ImMessageSqlProvider.class, method="deleteByExample")
    int deleteByExample(ImMessageExample example);

    @Delete({
        "delete from im_message",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into im_message (imUserSingleRelationId, key, ",
        "repeat, check, status, ",
        "msg_id, action, title, ",
        "content, format, ",
        "sender, receiver, ",
        "extra, timestamp, ",
        "update_time, create_time)",
        "values (#{imusersinglerelationid,jdbcType=BIGINT}, #{key,jdbcType=VARCHAR}, ",
        "#{repeat,jdbcType=VARCHAR}, #{check,jdbcType=BIT}, #{status,jdbcType=INTEGER}, ",
        "#{msgId,jdbcType=INTEGER}, #{action,jdbcType=VARCHAR}, #{title,jdbcType=VARCHAR}, ",
        "#{content,jdbcType=VARCHAR}, #{format,jdbcType=VARCHAR}, ",
        "#{sender,jdbcType=VARCHAR}, #{receiver,jdbcType=VARCHAR}, ",
        "#{extra,jdbcType=VARCHAR}, #{timestamp,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{createTime,jdbcType=TIMESTAMP})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(ImMessage record);

    @InsertProvider(type=ImMessageSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(ImMessage record);

    @SelectProvider(type=ImMessageSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="imUserSingleRelationId", property="imusersinglerelationid", jdbcType=JdbcType.BIGINT),
        @Result(column="key", property="key", jdbcType=JdbcType.VARCHAR),
        @Result(column="repeat", property="repeat", jdbcType=JdbcType.VARCHAR),
        @Result(column="check", property="check", jdbcType=JdbcType.BIT),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="msg_id", property="msgId", jdbcType=JdbcType.INTEGER),
        @Result(column="action", property="action", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="format", property="format", jdbcType=JdbcType.VARCHAR),
        @Result(column="sender", property="sender", jdbcType=JdbcType.VARCHAR),
        @Result(column="receiver", property="receiver", jdbcType=JdbcType.VARCHAR),
        @Result(column="extra", property="extra", jdbcType=JdbcType.VARCHAR),
        @Result(column="timestamp", property="timestamp", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ImMessage> selectByExample(ImMessageExample example);

    @Select({
        "select",
        "id, imUserSingleRelationId, key, repeat, check, status, msg_id, action, title, ",
        "content, format, sender, receiver, extra, timestamp, update_time, create_time",
        "from im_message",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="imUserSingleRelationId", property="imusersinglerelationid", jdbcType=JdbcType.BIGINT),
        @Result(column="key", property="key", jdbcType=JdbcType.VARCHAR),
        @Result(column="repeat", property="repeat", jdbcType=JdbcType.VARCHAR),
        @Result(column="check", property="check", jdbcType=JdbcType.BIT),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="msg_id", property="msgId", jdbcType=JdbcType.INTEGER),
        @Result(column="action", property="action", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="format", property="format", jdbcType=JdbcType.VARCHAR),
        @Result(column="sender", property="sender", jdbcType=JdbcType.VARCHAR),
        @Result(column="receiver", property="receiver", jdbcType=JdbcType.VARCHAR),
        @Result(column="extra", property="extra", jdbcType=JdbcType.VARCHAR),
        @Result(column="timestamp", property="timestamp", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    ImMessage selectByPrimaryKey(Long id);

    @UpdateProvider(type=ImMessageSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ImMessage record, @Param("example") ImMessageExample example);

    @UpdateProvider(type=ImMessageSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ImMessage record, @Param("example") ImMessageExample example);

    @UpdateProvider(type=ImMessageSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ImMessage record);

    @Update({
        "update im_message",
        "set imUserSingleRelationId = #{imusersinglerelationid,jdbcType=BIGINT},",
          "key = #{key,jdbcType=VARCHAR},",
          "repeat = #{repeat,jdbcType=VARCHAR},",
          "check = #{check,jdbcType=BIT},",
          "status = #{status,jdbcType=INTEGER},",
          "msg_id = #{msgId,jdbcType=INTEGER},",
          "action = #{action,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "content = #{content,jdbcType=VARCHAR},",
          "format = #{format,jdbcType=VARCHAR},",
          "sender = #{sender,jdbcType=VARCHAR},",
          "receiver = #{receiver,jdbcType=VARCHAR},",
          "extra = #{extra,jdbcType=VARCHAR},",
          "timestamp = #{timestamp,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(ImMessage record);
}