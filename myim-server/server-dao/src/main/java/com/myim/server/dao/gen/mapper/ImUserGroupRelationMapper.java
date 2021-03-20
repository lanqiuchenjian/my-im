package com.myim.server.dao.gen.mapper;

import com.myim.server.dao.gen.domain.ImUserGroupRelation;
import com.myim.server.dao.gen.domain.ImUserGroupRelationExample;
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

public interface ImUserGroupRelationMapper {
    @DeleteProvider(type=ImUserGroupRelationSqlProvider.class, method="deleteByExample")
    int deleteByExample(ImUserGroupRelationExample example);

    @Delete({
        "delete from im_user_group_relation",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into im_user_group_relation (im_user_group_category_id, im_user_id, ",
        "ext_char1, update_time, ",
        "create_time)",
        "values (#{imUserGroupCategoryId,jdbcType=BIGINT}, #{imUserId,jdbcType=BIGINT}, ",
        "#{extChar1,jdbcType=VARCHAR}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{createTime,jdbcType=TIMESTAMP})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(ImUserGroupRelation record);

    @InsertProvider(type=ImUserGroupRelationSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(ImUserGroupRelation record);

    @SelectProvider(type=ImUserGroupRelationSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="im_user_group_category_id", property="imUserGroupCategoryId", jdbcType=JdbcType.BIGINT),
        @Result(column="im_user_id", property="imUserId", jdbcType=JdbcType.BIGINT),
        @Result(column="ext_char1", property="extChar1", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ImUserGroupRelation> selectByExample(ImUserGroupRelationExample example);

    @Select({
        "select",
        "id, im_user_group_category_id, im_user_id, ext_char1, update_time, create_time",
        "from im_user_group_relation",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="im_user_group_category_id", property="imUserGroupCategoryId", jdbcType=JdbcType.BIGINT),
        @Result(column="im_user_id", property="imUserId", jdbcType=JdbcType.BIGINT),
        @Result(column="ext_char1", property="extChar1", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    ImUserGroupRelation selectByPrimaryKey(Long id);

    @UpdateProvider(type=ImUserGroupRelationSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ImUserGroupRelation record, @Param("example") ImUserGroupRelationExample example);

    @UpdateProvider(type=ImUserGroupRelationSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ImUserGroupRelation record, @Param("example") ImUserGroupRelationExample example);

    @UpdateProvider(type=ImUserGroupRelationSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ImUserGroupRelation record);

    @Update({
        "update im_user_group_relation",
        "set im_user_group_category_id = #{imUserGroupCategoryId,jdbcType=BIGINT},",
          "im_user_id = #{imUserId,jdbcType=BIGINT},",
          "ext_char1 = #{extChar1,jdbcType=VARCHAR},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(ImUserGroupRelation record);
}