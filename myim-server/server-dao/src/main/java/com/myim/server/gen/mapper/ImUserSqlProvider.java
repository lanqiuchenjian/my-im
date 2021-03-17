package com.myim.server.gen.mapper;

import com.myim.server.gen.domain.ImUser;
import com.myim.server.gen.domain.ImUserExample.Criteria;
import com.myim.server.gen.domain.ImUserExample.Criterion;
import com.myim.server.gen.domain.ImUserExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class ImUserSqlProvider {

    public String deleteByExample(ImUserExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("im_user");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(ImUser record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("im_user");
        
        if (record.getUserName() != null) {
            sql.VALUES("user_name", "#{userName,jdbcType=VARCHAR}");
        }
        
        if (record.getNickName() != null) {
            sql.VALUES("nick_name", "#{nickName,jdbcType=VARCHAR}");
        }
        
        if (record.getPhone() != null) {
            sql.VALUES("phone", "#{phone,jdbcType=INTEGER}");
        }
        
        if (record.getPhoto() != null) {
            sql.VALUES("photo", "#{photo,jdbcType=VARCHAR}");
        }
        
        if (record.getLoginName() != null) {
            sql.VALUES("login_name", "#{loginName,jdbcType=VARCHAR}");
        }
        
        if (record.getLoginPassword() != null) {
            sql.VALUES("login_password", "#{loginPassword,jdbcType=VARCHAR}");
        }
        
        if (record.getUserStatus() != null) {
            sql.VALUES("user_status", "#{userStatus,jdbcType=INTEGER}");
        }
        
        if (record.getServiceAliasName() != null) {
            sql.VALUES("service_alias_name", "#{serviceAliasName,jdbcType=VARCHAR}");
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

    public String selectByExample(ImUserExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("user_name");
        sql.SELECT("nick_name");
        sql.SELECT("phone");
        sql.SELECT("photo");
        sql.SELECT("login_name");
        sql.SELECT("login_password");
        sql.SELECT("user_status");
        sql.SELECT("service_alias_name");
        sql.SELECT("ext_char1");
        sql.SELECT("update_time");
        sql.SELECT("create_time");
        sql.FROM("im_user");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        ImUser record = (ImUser) parameter.get("record");
        ImUserExample example = (ImUserExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("im_user");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=BIGINT}");
        }
        
        if (record.getUserName() != null) {
            sql.SET("user_name = #{record.userName,jdbcType=VARCHAR}");
        }
        
        if (record.getNickName() != null) {
            sql.SET("nick_name = #{record.nickName,jdbcType=VARCHAR}");
        }
        
        if (record.getPhone() != null) {
            sql.SET("phone = #{record.phone,jdbcType=INTEGER}");
        }
        
        if (record.getPhoto() != null) {
            sql.SET("photo = #{record.photo,jdbcType=VARCHAR}");
        }
        
        if (record.getLoginName() != null) {
            sql.SET("login_name = #{record.loginName,jdbcType=VARCHAR}");
        }
        
        if (record.getLoginPassword() != null) {
            sql.SET("login_password = #{record.loginPassword,jdbcType=VARCHAR}");
        }
        
        if (record.getUserStatus() != null) {
            sql.SET("user_status = #{record.userStatus,jdbcType=INTEGER}");
        }
        
        if (record.getServiceAliasName() != null) {
            sql.SET("service_alias_name = #{record.serviceAliasName,jdbcType=VARCHAR}");
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
        sql.UPDATE("im_user");
        
        sql.SET("id = #{record.id,jdbcType=BIGINT}");
        sql.SET("user_name = #{record.userName,jdbcType=VARCHAR}");
        sql.SET("nick_name = #{record.nickName,jdbcType=VARCHAR}");
        sql.SET("phone = #{record.phone,jdbcType=INTEGER}");
        sql.SET("photo = #{record.photo,jdbcType=VARCHAR}");
        sql.SET("login_name = #{record.loginName,jdbcType=VARCHAR}");
        sql.SET("login_password = #{record.loginPassword,jdbcType=VARCHAR}");
        sql.SET("user_status = #{record.userStatus,jdbcType=INTEGER}");
        sql.SET("service_alias_name = #{record.serviceAliasName,jdbcType=VARCHAR}");
        sql.SET("ext_char1 = #{record.extChar1,jdbcType=VARCHAR}");
        sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        
        ImUserExample example = (ImUserExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(ImUser record) {
        SQL sql = new SQL();
        sql.UPDATE("im_user");
        
        if (record.getUserName() != null) {
            sql.SET("user_name = #{userName,jdbcType=VARCHAR}");
        }
        
        if (record.getNickName() != null) {
            sql.SET("nick_name = #{nickName,jdbcType=VARCHAR}");
        }
        
        if (record.getPhone() != null) {
            sql.SET("phone = #{phone,jdbcType=INTEGER}");
        }
        
        if (record.getPhoto() != null) {
            sql.SET("photo = #{photo,jdbcType=VARCHAR}");
        }
        
        if (record.getLoginName() != null) {
            sql.SET("login_name = #{loginName,jdbcType=VARCHAR}");
        }
        
        if (record.getLoginPassword() != null) {
            sql.SET("login_password = #{loginPassword,jdbcType=VARCHAR}");
        }
        
        if (record.getUserStatus() != null) {
            sql.SET("user_status = #{userStatus,jdbcType=INTEGER}");
        }
        
        if (record.getServiceAliasName() != null) {
            sql.SET("service_alias_name = #{serviceAliasName,jdbcType=VARCHAR}");
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

    protected void applyWhere(SQL sql, ImUserExample example, boolean includeExamplePhrase) {
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
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
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