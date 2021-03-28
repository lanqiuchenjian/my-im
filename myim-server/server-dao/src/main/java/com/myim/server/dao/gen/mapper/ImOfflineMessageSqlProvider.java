package com.myim.server.dao.gen.mapper;

import com.myim.server.dao.gen.domain.ImOfflineMessage;
import com.myim.server.dao.gen.domain.ImOfflineMessageExample.Criteria;
import com.myim.server.dao.gen.domain.ImOfflineMessageExample.Criterion;
import com.myim.server.dao.gen.domain.ImOfflineMessageExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class ImOfflineMessageSqlProvider {

    public String deleteByExample(ImOfflineMessageExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("im_offline_message");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(ImOfflineMessage record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("im_offline_message");
        
        if (record.getToImUserId() != null) {
            sql.VALUES("to_im_user_id", "#{toImUserId,jdbcType=BIGINT}");
        }
        
        if (record.getToImUserLoginName() != null) {
            sql.VALUES("to_im_user_login_name", "#{toImUserLoginName,jdbcType=CHAR}");
        }
        
        if (record.getFromImUserLoginName() != null) {
            sql.VALUES("from_im_user_login_name", "#{fromImUserLoginName,jdbcType=CHAR}");
        }
        
        if (record.getOfflineMesCount() != null) {
            sql.VALUES("offline_mes_count", "#{offlineMesCount,jdbcType=BIGINT}");
        }
        
        if (record.getIsOffline() != null) {
            sql.VALUES("is_offline", "#{isOffline,jdbcType=BIT}");
        }
        
        if (record.getExtra() != null) {
            sql.VALUES("extra", "#{extra,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getImMessageId() != null) {
            sql.VALUES("im_message_id", "#{imMessageId,jdbcType=BIGINT}");
        }
        
        if (record.getFromImUserId() != null) {
            sql.VALUES("from_im_user_id", "#{fromImUserId,jdbcType=BIGINT}");
        }
        
        return sql.toString();
    }

    public String selectByExample(ImOfflineMessageExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("to_im_user_id");
        sql.SELECT("to_im_user_login_name");
        sql.SELECT("from_im_user_login_name");
        sql.SELECT("offline_mes_count");
        sql.SELECT("is_offline");
        sql.SELECT("extra");
        sql.SELECT("update_time");
        sql.SELECT("create_time");
        sql.SELECT("im_message_id");
        sql.SELECT("from_im_user_id");
        sql.FROM("im_offline_message");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        ImOfflineMessage record = (ImOfflineMessage) parameter.get("record");
        ImOfflineMessageExample example = (ImOfflineMessageExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("im_offline_message");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=BIGINT}");
        }
        
        if (record.getToImUserId() != null) {
            sql.SET("to_im_user_id = #{record.toImUserId,jdbcType=BIGINT}");
        }
        
        if (record.getToImUserLoginName() != null) {
            sql.SET("to_im_user_login_name = #{record.toImUserLoginName,jdbcType=CHAR}");
        }
        
        if (record.getFromImUserLoginName() != null) {
            sql.SET("from_im_user_login_name = #{record.fromImUserLoginName,jdbcType=CHAR}");
        }
        
        if (record.getOfflineMesCount() != null) {
            sql.SET("offline_mes_count = #{record.offlineMesCount,jdbcType=BIGINT}");
        }
        
        if (record.getIsOffline() != null) {
            sql.SET("is_offline = #{record.isOffline,jdbcType=BIT}");
        }
        
        if (record.getExtra() != null) {
            sql.SET("extra = #{record.extra,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getImMessageId() != null) {
            sql.SET("im_message_id = #{record.imMessageId,jdbcType=BIGINT}");
        }
        
        if (record.getFromImUserId() != null) {
            sql.SET("from_im_user_id = #{record.fromImUserId,jdbcType=BIGINT}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("im_offline_message");
        
        sql.SET("id = #{record.id,jdbcType=BIGINT}");
        sql.SET("to_im_user_id = #{record.toImUserId,jdbcType=BIGINT}");
        sql.SET("to_im_user_login_name = #{record.toImUserLoginName,jdbcType=CHAR}");
        sql.SET("from_im_user_login_name = #{record.fromImUserLoginName,jdbcType=CHAR}");
        sql.SET("offline_mes_count = #{record.offlineMesCount,jdbcType=BIGINT}");
        sql.SET("is_offline = #{record.isOffline,jdbcType=BIT}");
        sql.SET("extra = #{record.extra,jdbcType=VARCHAR}");
        sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("im_message_id = #{record.imMessageId,jdbcType=BIGINT}");
        sql.SET("from_im_user_id = #{record.fromImUserId,jdbcType=BIGINT}");
        
        ImOfflineMessageExample example = (ImOfflineMessageExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(ImOfflineMessage record) {
        SQL sql = new SQL();
        sql.UPDATE("im_offline_message");
        
        if (record.getToImUserId() != null) {
            sql.SET("to_im_user_id = #{toImUserId,jdbcType=BIGINT}");
        }
        
        if (record.getToImUserLoginName() != null) {
            sql.SET("to_im_user_login_name = #{toImUserLoginName,jdbcType=CHAR}");
        }
        
        if (record.getFromImUserLoginName() != null) {
            sql.SET("from_im_user_login_name = #{fromImUserLoginName,jdbcType=CHAR}");
        }
        
        if (record.getOfflineMesCount() != null) {
            sql.SET("offline_mes_count = #{offlineMesCount,jdbcType=BIGINT}");
        }
        
        if (record.getIsOffline() != null) {
            sql.SET("is_offline = #{isOffline,jdbcType=BIT}");
        }
        
        if (record.getExtra() != null) {
            sql.SET("extra = #{extra,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getImMessageId() != null) {
            sql.SET("im_message_id = #{imMessageId,jdbcType=BIGINT}");
        }
        
        if (record.getFromImUserId() != null) {
            sql.SET("from_im_user_id = #{fromImUserId,jdbcType=BIGINT}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, ImOfflineMessageExample example, boolean includeExamplePhrase) {
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