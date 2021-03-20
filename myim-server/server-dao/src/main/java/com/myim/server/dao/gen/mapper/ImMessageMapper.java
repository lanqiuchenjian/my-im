package com.myim.server.dao.gen.mapper;

import com.myim.server.dao.gen.domain.ImMessage;
import com.myim.server.dao.gen.domain.ImMessageExample;
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
        "insert into im_message (im_user_single_relation_id, m_key, ",
        "m_repeat, m_check, m_status, ",
        "msg_id, m_action, ",
        "title, content, ",
        "m_format, sender, ",
        "receiver, extra, ",
        "m_timestamp, update_time, ",
        "create_time)",
        "values (#{imUserSingleRelationId,jdbcType=BIGINT}, #{mKey,jdbcType=VARCHAR}, ",
        "#{mRepeat,jdbcType=VARCHAR}, #{mCheck,jdbcType=BIT}, #{mStatus,jdbcType=INTEGER}, ",
        "#{msgId,jdbcType=INTEGER}, #{mAction,jdbcType=VARCHAR}, ",
        "#{title,jdbcType=VARCHAR}, #{content,jdbcType=VARCHAR}, ",
        "#{mFormat,jdbcType=VARCHAR}, #{sender,jdbcType=VARCHAR}, ",
        "#{receiver,jdbcType=VARCHAR}, #{extra,jdbcType=VARCHAR}, ",
        "#{mTimestamp,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{createTime,jdbcType=TIMESTAMP})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(ImMessage record);

    @InsertProvider(type=ImMessageSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(ImMessage record);

    @SelectProvider(type=ImMessageSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
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
    List<ImMessage> selectByExample(ImMessageExample example);

    @Select({
        "select",
        "id, im_user_single_relation_id, m_key, m_repeat, m_check, m_status, msg_id, ",
        "m_action, title, content, m_format, sender, receiver, extra, m_timestamp, update_time, ",
        "create_time",
        "from im_message",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
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
    ImMessage selectByPrimaryKey(Long id);

    @UpdateProvider(type=ImMessageSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ImMessage record, @Param("example") ImMessageExample example);

    @UpdateProvider(type=ImMessageSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ImMessage record, @Param("example") ImMessageExample example);

    @UpdateProvider(type=ImMessageSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ImMessage record);

    @Update({
        "update im_message",
        "set im_user_single_relation_id = #{imUserSingleRelationId,jdbcType=BIGINT},",
          "m_key = #{mKey,jdbcType=VARCHAR},",
          "m_repeat = #{mRepeat,jdbcType=VARCHAR},",
          "m_check = #{mCheck,jdbcType=BIT},",
          "m_status = #{mStatus,jdbcType=INTEGER},",
          "msg_id = #{msgId,jdbcType=INTEGER},",
          "m_action = #{mAction,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "content = #{content,jdbcType=VARCHAR},",
          "m_format = #{mFormat,jdbcType=VARCHAR},",
          "sender = #{sender,jdbcType=VARCHAR},",
          "receiver = #{receiver,jdbcType=VARCHAR},",
          "extra = #{extra,jdbcType=VARCHAR},",
          "m_timestamp = #{mTimestamp,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(ImMessage record);
}