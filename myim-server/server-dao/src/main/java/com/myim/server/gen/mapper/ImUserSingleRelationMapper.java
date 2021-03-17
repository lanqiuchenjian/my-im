package com.myim.server.gen.mapper;

import com.myim.server.gen.domain.ImUserSingleRelation;
import com.myim.server.gen.domain.ImUserSingleRelationExample;
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

public interface ImUserSingleRelationMapper {
    @DeleteProvider(type=ImUserSingleRelationSqlProvider.class, method="deleteByExample")
    int deleteByExample(ImUserSingleRelationExample example);

    @Delete({
        "delete from im_user_single_relation",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into im_user_single_relation (im_user_single_category_id, im_user_id, ",
        "ext_char1, update_time, ",
        "create_time)",
        "values (#{imUserSingleCategoryId,jdbcType=BIGINT}, #{imUserId,jdbcType=BIGINT}, ",
        "#{extChar1,jdbcType=VARCHAR}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{createTime,jdbcType=TIMESTAMP})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(ImUserSingleRelation record);

    @InsertProvider(type=ImUserSingleRelationSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(ImUserSingleRelation record);

    @SelectProvider(type=ImUserSingleRelationSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="im_user_single_category_id", property="imUserSingleCategoryId", jdbcType=JdbcType.BIGINT),
        @Result(column="im_user_id", property="imUserId", jdbcType=JdbcType.BIGINT),
        @Result(column="ext_char1", property="extChar1", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ImUserSingleRelation> selectByExample(ImUserSingleRelationExample example);

    @Select({
        "select",
        "id, im_user_single_category_id, im_user_id, ext_char1, update_time, create_time",
        "from im_user_single_relation",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="im_user_single_category_id", property="imUserSingleCategoryId", jdbcType=JdbcType.BIGINT),
        @Result(column="im_user_id", property="imUserId", jdbcType=JdbcType.BIGINT),
        @Result(column="ext_char1", property="extChar1", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    ImUserSingleRelation selectByPrimaryKey(Long id);

    @UpdateProvider(type=ImUserSingleRelationSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ImUserSingleRelation record, @Param("example") ImUserSingleRelationExample example);

    @UpdateProvider(type=ImUserSingleRelationSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ImUserSingleRelation record, @Param("example") ImUserSingleRelationExample example);

    @UpdateProvider(type=ImUserSingleRelationSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ImUserSingleRelation record);

    @Update({
        "update im_user_single_relation",
        "set im_user_single_category_id = #{imUserSingleCategoryId,jdbcType=BIGINT},",
          "im_user_id = #{imUserId,jdbcType=BIGINT},",
          "ext_char1 = #{extChar1,jdbcType=VARCHAR},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(ImUserSingleRelation record);
}