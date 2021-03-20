package com.myim.server.dao.gen.mapper;

import com.myim.server.dao.gen.domain.ImUserSingleCategory;
import com.myim.server.dao.gen.domain.ImUserSingleCategoryExample;

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

public interface ImUserSingleCategoryMapper {
    @DeleteProvider(type=ImUserSingleCategorySqlProvider.class, method="deleteByExample")
    int deleteByExample(ImUserSingleCategoryExample example);

    @Delete({
        "delete from im_user_single_category",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into im_user_single_category (im_user_id, category_name, ",
        "count, ext_char1, ",
        "update_time, create_time)",
        "values (#{imUserId,jdbcType=BIGINT}, #{categoryName,jdbcType=VARCHAR}, ",
        "#{count,jdbcType=BIGINT}, #{extChar1,jdbcType=VARCHAR}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{createTime,jdbcType=TIMESTAMP})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(ImUserSingleCategory record);

    @InsertProvider(type=ImUserSingleCategorySqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(ImUserSingleCategory record);

    @SelectProvider(type=ImUserSingleCategorySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="im_user_id", property="imUserId", jdbcType=JdbcType.BIGINT),
        @Result(column="category_name", property="categoryName", jdbcType=JdbcType.VARCHAR),
        @Result(column="count", property="count", jdbcType=JdbcType.BIGINT),
        @Result(column="ext_char1", property="extChar1", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ImUserSingleCategory> selectByExample(ImUserSingleCategoryExample example);

    @Select({
        "select",
        "id, im_user_id, category_name, count, ext_char1, update_time, create_time",
        "from im_user_single_category",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="im_user_id", property="imUserId", jdbcType=JdbcType.BIGINT),
        @Result(column="category_name", property="categoryName", jdbcType=JdbcType.VARCHAR),
        @Result(column="count", property="count", jdbcType=JdbcType.BIGINT),
        @Result(column="ext_char1", property="extChar1", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    ImUserSingleCategory selectByPrimaryKey(Long id);

    @UpdateProvider(type=ImUserSingleCategorySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ImUserSingleCategory record, @Param("example") ImUserSingleCategoryExample example);

    @UpdateProvider(type=ImUserSingleCategorySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ImUserSingleCategory record, @Param("example") ImUserSingleCategoryExample example);

    @UpdateProvider(type=ImUserSingleCategorySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ImUserSingleCategory record);

    @Update({
        "update im_user_single_category",
        "set im_user_id = #{imUserId,jdbcType=BIGINT},",
          "category_name = #{categoryName,jdbcType=VARCHAR},",
          "count = #{count,jdbcType=BIGINT},",
          "ext_char1 = #{extChar1,jdbcType=VARCHAR},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(ImUserSingleCategory record);
}