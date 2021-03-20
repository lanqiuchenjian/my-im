package com.myim.server.dao.gen.mapper;

import com.myim.server.dao.gen.domain.ImMessage;
import com.myim.server.dao.gen.domain.ImMessageExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class ImMessageSqlProvider {

    public String deleteByExample(ImMessageExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("im_message");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(ImMessage record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("im_message");
        
        if (record.getImusersinglerelationid() != null) {
            sql.VALUES("imUserSingleRelationId", "#{imusersinglerelationid,jdbcType=BIGINT}");
        }
        
        if (record.getKey() != null) {
            sql.VALUES("key", "#{key,jdbcType=VARCHAR}");
        }
        
        if (record.getRepeat() != null) {
            sql.VALUES("repeat", "#{repeat,jdbcType=VARCHAR}");
        }
        
        if (record.getCheck() != null) {
            sql.VALUES("check", "#{check,jdbcType=BIT}");
        }
        
        if (record.getStatus() != null) {
            sql.VALUES("status", "#{status,jdbcType=INTEGER}");
        }
        
        if (record.getMsgId() != null) {
            sql.VALUES("msg_id", "#{msgId,jdbcType=INTEGER}");
        }
        
        if (record.getAction() != null) {
            sql.VALUES("action", "#{action,jdbcType=VARCHAR}");
        }
        
        if (record.getTitle() != null) {
            sql.VALUES("title", "#{title,jdbcType=VARCHAR}");
        }
        
        if (record.getContent() != null) {
            sql.VALUES("content", "#{content,jdbcType=VARCHAR}");
        }
        
        if (record.getFormat() != null) {
            sql.VALUES("format", "#{format,jdbcType=VARCHAR}");
        }
        
        if (record.getSender() != null) {
            sql.VALUES("sender", "#{sender,jdbcType=VARCHAR}");
        }
        
        if (record.getReceiver() != null) {
            sql.VALUES("receiver", "#{receiver,jdbcType=VARCHAR}");
        }
        
        if (record.getExtra() != null) {
            sql.VALUES("extra", "#{extra,jdbcType=VARCHAR}");
        }
        
        if (record.getTimestamp() != null) {
            sql.VALUES("timestamp", "#{timestamp,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String selectByExample(ImMessageExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("imUserSingleRelationId");
        sql.SELECT("key");
        sql.SELECT("repeat");
        sql.SELECT("check");
        sql.SELECT("status");
        sql.SELECT("msg_id");
        sql.SELECT("action");
        sql.SELECT("title");
        sql.SELECT("content");
        sql.SELECT("format");
        sql.SELECT("sender");
        sql.SELECT("receiver");
        sql.SELECT("extra");
        sql.SELECT("timestamp");
        sql.SELECT("update_time");
        sql.SELECT("create_time");
        sql.FROM("im_message");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        ImMessage record = (ImMessage) parameter.get("record");
        ImMessageExample example = (ImMessageExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("im_message");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=BIGINT}");
        }
        
        if (record.getImusersinglerelationid() != null) {
            sql.SET("imUserSingleRelationId = #{record.imusersinglerelationid,jdbcType=BIGINT}");
        }
        
        if (record.getKey() != null) {
            sql.SET("key = #{record.key,jdbcType=VARCHAR}");
        }
        
        if (record.getRepeat() != null) {
            sql.SET("repeat = #{record.repeat,jdbcType=VARCHAR}");
        }
        
        if (record.getCheck() != null) {
            sql.SET("check = #{record.check,jdbcType=BIT}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{record.status,jdbcType=INTEGER}");
        }
        
        if (record.getMsgId() != null) {
            sql.SET("msg_id = #{record.msgId,jdbcType=INTEGER}");
        }
        
        if (record.getAction() != null) {
            sql.SET("action = #{record.action,jdbcType=VARCHAR}");
        }
        
        if (record.getTitle() != null) {
            sql.SET("title = #{record.title,jdbcType=VARCHAR}");
        }
        
        if (record.getContent() != null) {
            sql.SET("content = #{record.content,jdbcType=VARCHAR}");
        }
        
        if (record.getFormat() != null) {
            sql.SET("format = #{record.format,jdbcType=VARCHAR}");
        }
        
        if (record.getSender() != null) {
            sql.SET("sender = #{record.sender,jdbcType=VARCHAR}");
        }
        
        if (record.getReceiver() != null) {
            sql.SET("receiver = #{record.receiver,jdbcType=VARCHAR}");
        }
        
        if (record.getExtra() != null) {
            sql.SET("extra = #{record.extra,jdbcType=VARCHAR}");
        }
        
        if (record.getTimestamp() != null) {
            sql.SET("timestamp = #{record.timestamp,jdbcType=TIMESTAMP}");
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
        sql.UPDATE("im_message");
        
        sql.SET("id = #{record.id,jdbcType=BIGINT}");
        sql.SET("imUserSingleRelationId = #{record.imusersinglerelationid,jdbcType=BIGINT}");
        sql.SET("key = #{record.key,jdbcType=VARCHAR}");
        sql.SET("repeat = #{record.repeat,jdbcType=VARCHAR}");
        sql.SET("check = #{record.check,jdbcType=BIT}");
        sql.SET("status = #{record.status,jdbcType=INTEGER}");
        sql.SET("msg_id = #{record.msgId,jdbcType=INTEGER}");
        sql.SET("action = #{record.action,jdbcType=VARCHAR}");
        sql.SET("title = #{record.title,jdbcType=VARCHAR}");
        sql.SET("content = #{record.content,jdbcType=VARCHAR}");
        sql.SET("format = #{record.format,jdbcType=VARCHAR}");
        sql.SET("sender = #{record.sender,jdbcType=VARCHAR}");
        sql.SET("receiver = #{record.receiver,jdbcType=VARCHAR}");
        sql.SET("extra = #{record.extra,jdbcType=VARCHAR}");
        sql.SET("timestamp = #{record.timestamp,jdbcType=TIMESTAMP}");
        sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        
        ImMessageExample example = (ImMessageExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(ImMessage record) {
        SQL sql = new SQL();
        sql.UPDATE("im_message");
        
        if (record.getImusersinglerelationid() != null) {
            sql.SET("imUserSingleRelationId = #{imusersinglerelationid,jdbcType=BIGINT}");
        }
        
        if (record.getKey() != null) {
            sql.SET("key = #{key,jdbcType=VARCHAR}");
        }
        
        if (record.getRepeat() != null) {
            sql.SET("repeat = #{repeat,jdbcType=VARCHAR}");
        }
        
        if (record.getCheck() != null) {
            sql.SET("check = #{check,jdbcType=BIT}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{status,jdbcType=INTEGER}");
        }
        
        if (record.getMsgId() != null) {
            sql.SET("msg_id = #{msgId,jdbcType=INTEGER}");
        }
        
        if (record.getAction() != null) {
            sql.SET("action = #{action,jdbcType=VARCHAR}");
        }
        
        if (record.getTitle() != null) {
            sql.SET("title = #{title,jdbcType=VARCHAR}");
        }
        
        if (record.getContent() != null) {
            sql.SET("content = #{content,jdbcType=VARCHAR}");
        }
        
        if (record.getFormat() != null) {
            sql.SET("format = #{format,jdbcType=VARCHAR}");
        }
        
        if (record.getSender() != null) {
            sql.SET("sender = #{sender,jdbcType=VARCHAR}");
        }
        
        if (record.getReceiver() != null) {
            sql.SET("receiver = #{receiver,jdbcType=VARCHAR}");
        }
        
        if (record.getExtra() != null) {
            sql.SET("extra = #{extra,jdbcType=VARCHAR}");
        }
        
        if (record.getTimestamp() != null) {
            sql.SET("timestamp = #{timestamp,jdbcType=TIMESTAMP}");
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

    protected void applyWhere(SQL sql, ImMessageExample example, boolean includeExamplePhrase) {
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
        List<ImMessageExample.Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            ImMessageExample.Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<ImMessageExample.Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    ImMessageExample.Criterion criterion = criterions.get(j);
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