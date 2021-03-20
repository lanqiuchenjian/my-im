package com.myim.server.dao.gen.mapper;

import com.myim.server.dao.gen.domain.ImUser;
import com.myim.server.dao.gen.domain.ImUserExample;
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

public interface ImUserMapper {
    @DeleteProvider(type=ImUserSqlProvider.class, method="deleteByExample")
    int deleteByExample(ImUserExample example);

    @Delete({
        "delete from im_user",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into im_user (user_name, nick_name, ",
        "phone, photo, login_name, ",
        "login_password, user_status, ",
        "service_alias_name, ext_char1, ",
        "update_time, create_time)",
        "values (#{userName,jdbcType=VARCHAR}, #{nickName,jdbcType=VARCHAR}, ",
        "#{phone,jdbcType=INTEGER}, #{photo,jdbcType=VARCHAR}, #{loginName,jdbcType=VARCHAR}, ",
        "#{loginPassword,jdbcType=VARCHAR}, #{userStatus,jdbcType=INTEGER}, ",
        "#{serviceAliasName,jdbcType=VARCHAR}, #{extChar1,jdbcType=VARCHAR}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{createTime,jdbcType=TIMESTAMP})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(ImUser record);

    @InsertProvider(type=ImUserSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(ImUser record);

    @SelectProvider(type=ImUserSqlProvider.class, method="selectByExample")
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
    List<ImUser> selectByExample(ImUserExample example);

    @Select({
        "select",
        "id, user_name, nick_name, phone, photo, login_name, login_password, user_status, ",
        "service_alias_name, ext_char1, update_time, create_time",
        "from im_user",
        "where id = #{id,jdbcType=BIGINT}"
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
    ImUser selectByPrimaryKey(Long id);

    @UpdateProvider(type=ImUserSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ImUser record, @Param("example") ImUserExample example);

    @UpdateProvider(type=ImUserSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ImUser record, @Param("example") ImUserExample example);

    @UpdateProvider(type=ImUserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ImUser record);

    @Update({
        "update im_user",
        "set user_name = #{userName,jdbcType=VARCHAR},",
          "nick_name = #{nickName,jdbcType=VARCHAR},",
          "phone = #{phone,jdbcType=INTEGER},",
          "photo = #{photo,jdbcType=VARCHAR},",
          "login_name = #{loginName,jdbcType=VARCHAR},",
          "login_password = #{loginPassword,jdbcType=VARCHAR},",
          "user_status = #{userStatus,jdbcType=INTEGER},",
          "service_alias_name = #{serviceAliasName,jdbcType=VARCHAR},",
          "ext_char1 = #{extChar1,jdbcType=VARCHAR},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(ImUser record);
}