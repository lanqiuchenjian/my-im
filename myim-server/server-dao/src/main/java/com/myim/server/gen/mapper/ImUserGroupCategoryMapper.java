package com.myim.server.gen.mapper;

import com.myim.server.gen.domain.ImUserGroupCategory;
import com.myim.server.gen.domain.ImUserGroupCategoryExample;
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

public interface ImUserGroupCategoryMapper {
    @DeleteProvider(type=ImUserGroupCategorySqlProvider.class, method="deleteByExample")
    int deleteByExample(ImUserGroupCategoryExample example);

    @Delete({
        "delete from im_user_group_category",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into im_user_group_category (im_user_id, group_name, ",
        "group_desc, count, ",
        "ext_char1, update_time, ",
        "create_time)",
        "values (#{imUserId,jdbcType=BIGINT}, #{groupName,jdbcType=VARCHAR}, ",
        "#{groupDesc,jdbcType=VARCHAR}, #{count,jdbcType=BIGINT}, ",
        "#{extChar1,jdbcType=VARCHAR}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{createTime,jdbcType=TIMESTAMP})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(ImUserGroupCategory record);

    @InsertProvider(type=ImUserGroupCategorySqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(ImUserGroupCategory record);

    @SelectProvider(type=ImUserGroupCategorySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="im_user_id", property="imUserId", jdbcType=JdbcType.BIGINT),
        @Result(column="group_name", property="groupName", jdbcType=JdbcType.VARCHAR),
        @Result(column="group_desc", property="groupDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="count", property="count", jdbcType=JdbcType.BIGINT),
        @Result(column="ext_char1", property="extChar1", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ImUserGroupCategory> selectByExample(ImUserGroupCategoryExample example);

    @Select({
        "select",
        "id, im_user_id, group_name, group_desc, count, ext_char1, update_time, create_time",
        "from im_user_group_category",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="im_user_id", property="imUserId", jdbcType=JdbcType.BIGINT),
        @Result(column="group_name", property="groupName", jdbcType=JdbcType.VARCHAR),
        @Result(column="group_desc", property="groupDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="count", property="count", jdbcType=JdbcType.BIGINT),
        @Result(column="ext_char1", property="extChar1", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    ImUserGroupCategory selectByPrimaryKey(Long id);

    @UpdateProvider(type=ImUserGroupCategorySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ImUserGroupCategory record, @Param("example") ImUserGroupCategoryExample example);

    @UpdateProvider(type=ImUserGroupCategorySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ImUserGroupCategory record, @Param("example") ImUserGroupCategoryExample example);

    @UpdateProvider(type=ImUserGroupCategorySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ImUserGroupCategory record);

    @Update({
        "update im_user_group_category",
        "set im_user_id = #{imUserId,jdbcType=BIGINT},",
          "group_name = #{groupName,jdbcType=VARCHAR},",
          "group_desc = #{groupDesc,jdbcType=VARCHAR},",
          "count = #{count,jdbcType=BIGINT},",
          "ext_char1 = #{extChar1,jdbcType=VARCHAR},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(ImUserGroupCategory record);
}