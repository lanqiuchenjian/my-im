package com.myim.server.dao.gen.mapper;

import com.myim.server.dao.gen.domain.ImOfflineMessage;
import com.myim.server.dao.gen.domain.ImOfflineMessageExample;
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

public interface ImOfflineMessageMapper {
    @DeleteProvider(type=ImOfflineMessageSqlProvider.class, method="deleteByExample")
    int deleteByExample(ImOfflineMessageExample example);

    @Delete({
        "delete from im_offline_message",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into im_offline_message (to_im_user_id, from_im_user_id, ",
        "im_message_id, offline_mes_count, ",
        "is_offline, extra, update_time, ",
        "create_time)",
        "values (#{toImUserId,jdbcType=BIGINT}, #{fromImUserId,jdbcType=BIGINT}, ",
        "#{imMessageId,jdbcType=BIGINT}, #{offlineMesCount,jdbcType=BIGINT}, ",
        "#{isOffline,jdbcType=BIT}, #{extra,jdbcType=VARCHAR}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{createTime,jdbcType=TIMESTAMP})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(ImOfflineMessage record);

    @InsertProvider(type=ImOfflineMessageSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(ImOfflineMessage record);

    @SelectProvider(type=ImOfflineMessageSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="to_im_user_id", property="toImUserId", jdbcType=JdbcType.BIGINT),
        @Result(column="from_im_user_id", property="fromImUserId", jdbcType=JdbcType.BIGINT),
        @Result(column="im_message_id", property="imMessageId", jdbcType=JdbcType.BIGINT),
        @Result(column="offline_mes_count", property="offlineMesCount", jdbcType=JdbcType.BIGINT),
        @Result(column="is_offline", property="isOffline", jdbcType=JdbcType.BIT),
        @Result(column="extra", property="extra", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ImOfflineMessage> selectByExample(ImOfflineMessageExample example);

    @Select({
        "select",
        "id, to_im_user_id, from_im_user_id, im_message_id, offline_mes_count, is_offline, ",
        "extra, update_time, create_time",
        "from im_offline_message",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="to_im_user_id", property="toImUserId", jdbcType=JdbcType.BIGINT),
        @Result(column="from_im_user_id", property="fromImUserId", jdbcType=JdbcType.BIGINT),
        @Result(column="im_message_id", property="imMessageId", jdbcType=JdbcType.BIGINT),
        @Result(column="offline_mes_count", property="offlineMesCount", jdbcType=JdbcType.BIGINT),
        @Result(column="is_offline", property="isOffline", jdbcType=JdbcType.BIT),
        @Result(column="extra", property="extra", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    ImOfflineMessage selectByPrimaryKey(Long id);

    @UpdateProvider(type=ImOfflineMessageSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ImOfflineMessage record, @Param("example") ImOfflineMessageExample example);

    @UpdateProvider(type=ImOfflineMessageSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ImOfflineMessage record, @Param("example") ImOfflineMessageExample example);

    @UpdateProvider(type=ImOfflineMessageSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ImOfflineMessage record);

    @Update({
        "update im_offline_message",
        "set to_im_user_id = #{toImUserId,jdbcType=BIGINT},",
          "from_im_user_id = #{fromImUserId,jdbcType=BIGINT},",
          "im_message_id = #{imMessageId,jdbcType=BIGINT},",
          "offline_mes_count = #{offlineMesCount,jdbcType=BIGINT},",
          "is_offline = #{isOffline,jdbcType=BIT},",
          "extra = #{extra,jdbcType=VARCHAR},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(ImOfflineMessage record);
}