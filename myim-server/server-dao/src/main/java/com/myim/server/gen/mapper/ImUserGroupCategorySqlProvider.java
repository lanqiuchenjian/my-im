package com.myim.server.gen.mapper;

import com.myim.server.gen.domain.ImUserGroupCategory;
import com.myim.server.gen.domain.ImUserGroupCategoryExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class ImUserGroupCategorySqlProvider {

    public String deleteByExample(ImUserGroupCategoryExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("im_user_group_category");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(ImUserGroupCategory record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("im_user_group_category");
        
        if (record.getImUserId() != null) {
            sql.VALUES("im_user_id", "#{imUserId,jdbcType=BIGINT}");
        }
        
        if (record.getGroupName() != null) {
            sql.VALUES("group_name", "#{groupName,jdbcType=VARCHAR}");
        }
        
        if (record.getGroupDesc() != null) {
            sql.VALUES("group_desc", "#{groupDesc,jdbcType=VARCHAR}");
        }
        
        if (record.getCount() != null) {
            sql.VALUES("count", "#{count,jdbcType=BIGINT}");
        }
        
        if (record.getExtChar1() != null) {
            sql.VALUES("ext_char1", "#{extChar1,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String selectByExample(ImUserGroupCategoryExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("im_user_id");
        sql.SELECT("group_name");
        sql.SELECT("group_desc");
        sql.SELECT("count");
        sql.SELECT("ext_char1");
        sql.SELECT("update_time");
        sql.SELECT("create_time");
        sql.FROM("im_user_group_category");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        ImUserGroupCategory record = (ImUserGroupCategory) parameter.get("record");
        ImUserGroupCategoryExample example = (ImUserGroupCategoryExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("im_user_group_category");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=BIGINT}");
        }
        
        if (record.getImUserId() != null) {
            sql.SET("im_user_id = #{record.imUserId,jdbcType=BIGINT}");
        }
        
        if (record.getGroupName() != null) {
            sql.SET("group_name = #{record.groupName,jdbcType=VARCHAR}");
        }
        
        if (record.getGroupDesc() != null) {
            sql.SET("group_desc = #{record.groupDesc,jdbcType=VARCHAR}");
        }
        
        if (record.getCount() != null) {
            sql.SET("count = #{record.count,jdbcType=BIGINT}");
        }
        
        if (record.getExtChar1() != null) {
            sql.SET("ext_char1 = #{record.extChar1,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("im_user_group_category");
        
        sql.SET("id = #{record.id,jdbcType=BIGINT}");
        sql.SET("im_user_id = #{record.imUserId,jdbcType=BIGINT}");
        sql.SET("group_name = #{record.groupName,jdbcType=VARCHAR}");
        sql.SET("group_desc = #{record.groupDesc,jdbcType=VARCHAR}");
        sql.SET("count = #{record.count,jdbcType=BIGINT}");
        sql.SET("ext_char1 = #{record.extChar1,jdbcType=VARCHAR}");
        sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        
        ImUserGroupCategoryExample example = (ImUserGroupCategoryExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(ImUserGroupCategory record) {
        SQL sql = new SQL();
        sql.UPDATE("im_user_group_category");
        
        if (record.getImUserId() != null) {
            sql.SET("im_user_id = #{imUserId,jdbcType=BIGINT}");
        }
        
        if (record.getGroupName() != null) {
            sql.SET("group_name = #{groupName,jdbcType=VARCHAR}");
        }
        
        if (record.getGroupDesc() != null) {
            sql.SET("group_desc = #{groupDesc,jdbcType=VARCHAR}");
        }
        
        if (record.getCount() != null) {
            sql.SET("count = #{count,jdbcType=BIGINT}");
        }
        
        if (record.getExtChar1() != null) {
            sql.SET("ext_char1 = #{extChar1,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, ImUserGroupCategoryExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<ImUserGroupCategoryExample.Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            ImUserGroupCategoryExample.Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<ImUserGroupCategoryExample.Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    ImUserGroupCategoryExample.Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}