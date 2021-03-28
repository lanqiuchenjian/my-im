package com.myim.server.dao.gen.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImOfflineMessageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ImOfflineMessageExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andToImUserIdIsNull() {
            addCriterion("to_im_user_id is null");
            return (Criteria) this;
        }

        public Criteria andToImUserIdIsNotNull() {
            addCriterion("to_im_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andToImUserIdEqualTo(Long value) {
            addCriterion("to_im_user_id =", value, "toImUserId");
            return (Criteria) this;
        }

        public Criteria andToImUserIdNotEqualTo(Long value) {
            addCriterion("to_im_user_id <>", value, "toImUserId");
            return (Criteria) this;
        }

        public Criteria andToImUserIdGreaterThan(Long value) {
            addCriterion("to_im_user_id >", value, "toImUserId");
            return (Criteria) this;
        }

        public Criteria andToImUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("to_im_user_id >=", value, "toImUserId");
            return (Criteria) this;
        }

        public Criteria andToImUserIdLessThan(Long value) {
            addCriterion("to_im_user_id <", value, "toImUserId");
            return (Criteria) this;
        }

        public Criteria andToImUserIdLessThanOrEqualTo(Long value) {
            addCriterion("to_im_user_id <=", value, "toImUserId");
            return (Criteria) this;
        }

        public Criteria andToImUserIdIn(List<Long> values) {
            addCriterion("to_im_user_id in", values, "toImUserId");
            return (Criteria) this;
        }

        public Criteria andToImUserIdNotIn(List<Long> values) {
            addCriterion("to_im_user_id not in", values, "toImUserId");
            return (Criteria) this;
        }

        public Criteria andToImUserIdBetween(Long value1, Long value2) {
            addCriterion("to_im_user_id between", value1, value2, "toImUserId");
            return (Criteria) this;
        }

        public Criteria andToImUserIdNotBetween(Long value1, Long value2) {
            addCriterion("to_im_user_id not between", value1, value2, "toImUserId");
            return (Criteria) this;
        }

        public Criteria andToImUserLoginNameIsNull() {
            addCriterion("to_im_user_login_name is null");
            return (Criteria) this;
        }

        public Criteria andToImUserLoginNameIsNotNull() {
            addCriterion("to_im_user_login_name is not null");
            return (Criteria) this;
        }

        public Criteria andToImUserLoginNameEqualTo(String value) {
            addCriterion("to_im_user_login_name =", value, "toImUserLoginName");
            return (Criteria) this;
        }

        public Criteria andToImUserLoginNameNotEqualTo(String value) {
            addCriterion("to_im_user_login_name <>", value, "toImUserLoginName");
            return (Criteria) this;
        }

        public Criteria andToImUserLoginNameGreaterThan(String value) {
            addCriterion("to_im_user_login_name >", value, "toImUserLoginName");
            return (Criteria) this;
        }

        public Criteria andToImUserLoginNameGreaterThanOrEqualTo(String value) {
            addCriterion("to_im_user_login_name >=", value, "toImUserLoginName");
            return (Criteria) this;
        }

        public Criteria andToImUserLoginNameLessThan(String value) {
            addCriterion("to_im_user_login_name <", value, "toImUserLoginName");
            return (Criteria) this;
        }

        public Criteria andToImUserLoginNameLessThanOrEqualTo(String value) {
            addCriterion("to_im_user_login_name <=", value, "toImUserLoginName");
            return (Criteria) this;
        }

        public Criteria andToImUserLoginNameLike(String value) {
            addCriterion("to_im_user_login_name like", value, "toImUserLoginName");
            return (Criteria) this;
        }

        public Criteria andToImUserLoginNameNotLike(String value) {
            addCriterion("to_im_user_login_name not like", value, "toImUserLoginName");
            return (Criteria) this;
        }

        public Criteria andToImUserLoginNameIn(List<String> values) {
            addCriterion("to_im_user_login_name in", values, "toImUserLoginName");
            return (Criteria) this;
        }

        public Criteria andToImUserLoginNameNotIn(List<String> values) {
            addCriterion("to_im_user_login_name not in", values, "toImUserLoginName");
            return (Criteria) this;
        }

        public Criteria andToImUserLoginNameBetween(String value1, String value2) {
            addCriterion("to_im_user_login_name between", value1, value2, "toImUserLoginName");
            return (Criteria) this;
        }

        public Criteria andToImUserLoginNameNotBetween(String value1, String value2) {
            addCriterion("to_im_user_login_name not between", value1, value2, "toImUserLoginName");
            return (Criteria) this;
        }

        public Criteria andFromImUserLoginNameIsNull() {
            addCriterion("from_im_user_login_name is null");
            return (Criteria) this;
        }

        public Criteria andFromImUserLoginNameIsNotNull() {
            addCriterion("from_im_user_login_name is not null");
            return (Criteria) this;
        }

        public Criteria andFromImUserLoginNameEqualTo(String value) {
            addCriterion("from_im_user_login_name =", value, "fromImUserLoginName");
            return (Criteria) this;
        }

        public Criteria andFromImUserLoginNameNotEqualTo(String value) {
            addCriterion("from_im_user_login_name <>", value, "fromImUserLoginName");
            return (Criteria) this;
        }

        public Criteria andFromImUserLoginNameGreaterThan(String value) {
            addCriterion("from_im_user_login_name >", value, "fromImUserLoginName");
            return (Criteria) this;
        }

        public Criteria andFromImUserLoginNameGreaterThanOrEqualTo(String value) {
            addCriterion("from_im_user_login_name >=", value, "fromImUserLoginName");
            return (Criteria) this;
        }

        public Criteria andFromImUserLoginNameLessThan(String value) {
            addCriterion("from_im_user_login_name <", value, "fromImUserLoginName");
            return (Criteria) this;
        }

        public Criteria andFromImUserLoginNameLessThanOrEqualTo(String value) {
            addCriterion("from_im_user_login_name <=", value, "fromImUserLoginName");
            return (Criteria) this;
        }

        public Criteria andFromImUserLoginNameLike(String value) {
            addCriterion("from_im_user_login_name like", value, "fromImUserLoginName");
            return (Criteria) this;
        }

        public Criteria andFromImUserLoginNameNotLike(String value) {
            addCriterion("from_im_user_login_name not like", value, "fromImUserLoginName");
            return (Criteria) this;
        }

        public Criteria andFromImUserLoginNameIn(List<String> values) {
            addCriterion("from_im_user_login_name in", values, "fromImUserLoginName");
            return (Criteria) this;
        }

        public Criteria andFromImUserLoginNameNotIn(List<String> values) {
            addCriterion("from_im_user_login_name not in", values, "fromImUserLoginName");
            return (Criteria) this;
        }

        public Criteria andFromImUserLoginNameBetween(String value1, String value2) {
            addCriterion("from_im_user_login_name between", value1, value2, "fromImUserLoginName");
            return (Criteria) this;
        }

        public Criteria andFromImUserLoginNameNotBetween(String value1, String value2) {
            addCriterion("from_im_user_login_name not between", value1, value2, "fromImUserLoginName");
            return (Criteria) this;
        }

        public Criteria andOfflineMesCountIsNull() {
            addCriterion("offline_mes_count is null");
            return (Criteria) this;
        }

        public Criteria andOfflineMesCountIsNotNull() {
            addCriterion("offline_mes_count is not null");
            return (Criteria) this;
        }

        public Criteria andOfflineMesCountEqualTo(Long value) {
            addCriterion("offline_mes_count =", value, "offlineMesCount");
            return (Criteria) this;
        }

        public Criteria andOfflineMesCountNotEqualTo(Long value) {
            addCriterion("offline_mes_count <>", value, "offlineMesCount");
            return (Criteria) this;
        }

        public Criteria andOfflineMesCountGreaterThan(Long value) {
            addCriterion("offline_mes_count >", value, "offlineMesCount");
            return (Criteria) this;
        }

        public Criteria andOfflineMesCountGreaterThanOrEqualTo(Long value) {
            addCriterion("offline_mes_count >=", value, "offlineMesCount");
            return (Criteria) this;
        }

        public Criteria andOfflineMesCountLessThan(Long value) {
            addCriterion("offline_mes_count <", value, "offlineMesCount");
            return (Criteria) this;
        }

        public Criteria andOfflineMesCountLessThanOrEqualTo(Long value) {
            addCriterion("offline_mes_count <=", value, "offlineMesCount");
            return (Criteria) this;
        }

        public Criteria andOfflineMesCountIn(List<Long> values) {
            addCriterion("offline_mes_count in", values, "offlineMesCount");
            return (Criteria) this;
        }

        public Criteria andOfflineMesCountNotIn(List<Long> values) {
            addCriterion("offline_mes_count not in", values, "offlineMesCount");
            return (Criteria) this;
        }

        public Criteria andOfflineMesCountBetween(Long value1, Long value2) {
            addCriterion("offline_mes_count between", value1, value2, "offlineMesCount");
            return (Criteria) this;
        }

        public Criteria andOfflineMesCountNotBetween(Long value1, Long value2) {
            addCriterion("offline_mes_count not between", value1, value2, "offlineMesCount");
            return (Criteria) this;
        }

        public Criteria andIsOfflineIsNull() {
            addCriterion("is_offline is null");
            return (Criteria) this;
        }

        public Criteria andIsOfflineIsNotNull() {
            addCriterion("is_offline is not null");
            return (Criteria) this;
        }

        public Criteria andIsOfflineEqualTo(Boolean value) {
            addCriterion("is_offline =", value, "isOffline");
            return (Criteria) this;
        }

        public Criteria andIsOfflineNotEqualTo(Boolean value) {
            addCriterion("is_offline <>", value, "isOffline");
            return (Criteria) this;
        }

        public Criteria andIsOfflineGreaterThan(Boolean value) {
            addCriterion("is_offline >", value, "isOffline");
            return (Criteria) this;
        }

        public Criteria andIsOfflineGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_offline >=", value, "isOffline");
            return (Criteria) this;
        }

        public Criteria andIsOfflineLessThan(Boolean value) {
            addCriterion("is_offline <", value, "isOffline");
            return (Criteria) this;
        }

        public Criteria andIsOfflineLessThanOrEqualTo(Boolean value) {
            addCriterion("is_offline <=", value, "isOffline");
            return (Criteria) this;
        }

        public Criteria andIsOfflineIn(List<Boolean> values) {
            addCriterion("is_offline in", values, "isOffline");
            return (Criteria) this;
        }

        public Criteria andIsOfflineNotIn(List<Boolean> values) {
            addCriterion("is_offline not in", values, "isOffline");
            return (Criteria) this;
        }

        public Criteria andIsOfflineBetween(Boolean value1, Boolean value2) {
            addCriterion("is_offline between", value1, value2, "isOffline");
            return (Criteria) this;
        }

        public Criteria andIsOfflineNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_offline not between", value1, value2, "isOffline");
            return (Criteria) this;
        }

        public Criteria andExtraIsNull() {
            addCriterion("extra is null");
            return (Criteria) this;
        }

        public Criteria andExtraIsNotNull() {
            addCriterion("extra is not null");
            return (Criteria) this;
        }

        public Criteria andExtraEqualTo(String value) {
            addCriterion("extra =", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraNotEqualTo(String value) {
            addCriterion("extra <>", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraGreaterThan(String value) {
            addCriterion("extra >", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraGreaterThanOrEqualTo(String value) {
            addCriterion("extra >=", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraLessThan(String value) {
            addCriterion("extra <", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraLessThanOrEqualTo(String value) {
            addCriterion("extra <=", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraLike(String value) {
            addCriterion("extra like", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraNotLike(String value) {
            addCriterion("extra not like", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraIn(List<String> values) {
            addCriterion("extra in", values, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraNotIn(List<String> values) {
            addCriterion("extra not in", values, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraBetween(String value1, String value2) {
            addCriterion("extra between", value1, value2, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraNotBetween(String value1, String value2) {
            addCriterion("extra not between", value1, value2, "extra");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andImMessageIdIsNull() {
            addCriterion("im_message_id is null");
            return (Criteria) this;
        }

        public Criteria andImMessageIdIsNotNull() {
            addCriterion("im_message_id is not null");
            return (Criteria) this;
        }

        public Criteria andImMessageIdEqualTo(Long value) {
            addCriterion("im_message_id =", value, "imMessageId");
            return (Criteria) this;
        }

        public Criteria andImMessageIdNotEqualTo(Long value) {
            addCriterion("im_message_id <>", value, "imMessageId");
            return (Criteria) this;
        }

        public Criteria andImMessageIdGreaterThan(Long value) {
            addCriterion("im_message_id >", value, "imMessageId");
            return (Criteria) this;
        }

        public Criteria andImMessageIdGreaterThanOrEqualTo(Long value) {
            addCriterion("im_message_id >=", value, "imMessageId");
            return (Criteria) this;
        }

        public Criteria andImMessageIdLessThan(Long value) {
            addCriterion("im_message_id <", value, "imMessageId");
            return (Criteria) this;
        }

        public Criteria andImMessageIdLessThanOrEqualTo(Long value) {
            addCriterion("im_message_id <=", value, "imMessageId");
            return (Criteria) this;
        }

        public Criteria andImMessageIdIn(List<Long> values) {
            addCriterion("im_message_id in", values, "imMessageId");
            return (Criteria) this;
        }

        public Criteria andImMessageIdNotIn(List<Long> values) {
            addCriterion("im_message_id not in", values, "imMessageId");
            return (Criteria) this;
        }

        public Criteria andImMessageIdBetween(Long value1, Long value2) {
            addCriterion("im_message_id between", value1, value2, "imMessageId");
            return (Criteria) this;
        }

        public Criteria andImMessageIdNotBetween(Long value1, Long value2) {
            addCriterion("im_message_id not between", value1, value2, "imMessageId");
            return (Criteria) this;
        }

        public Criteria andFromImUserIdIsNull() {
            addCriterion("from_im_user_id is null");
            return (Criteria) this;
        }

        public Criteria andFromImUserIdIsNotNull() {
            addCriterion("from_im_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andFromImUserIdEqualTo(Long value) {
            addCriterion("from_im_user_id =", value, "fromImUserId");
            return (Criteria) this;
        }

        public Criteria andFromImUserIdNotEqualTo(Long value) {
            addCriterion("from_im_user_id <>", value, "fromImUserId");
            return (Criteria) this;
        }

        public Criteria andFromImUserIdGreaterThan(Long value) {
            addCriterion("from_im_user_id >", value, "fromImUserId");
            return (Criteria) this;
        }

        public Criteria andFromImUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("from_im_user_id >=", value, "fromImUserId");
            return (Criteria) this;
        }

        public Criteria andFromImUserIdLessThan(Long value) {
            addCriterion("from_im_user_id <", value, "fromImUserId");
            return (Criteria) this;
        }

        public Criteria andFromImUserIdLessThanOrEqualTo(Long value) {
            addCriterion("from_im_user_id <=", value, "fromImUserId");
            return (Criteria) this;
        }

        public Criteria andFromImUserIdIn(List<Long> values) {
            addCriterion("from_im_user_id in", values, "fromImUserId");
            return (Criteria) this;
        }

        public Criteria andFromImUserIdNotIn(List<Long> values) {
            addCriterion("from_im_user_id not in", values, "fromImUserId");
            return (Criteria) this;
        }

        public Criteria andFromImUserIdBetween(Long value1, Long value2) {
            addCriterion("from_im_user_id between", value1, value2, "fromImUserId");
            return (Criteria) this;
        }

        public Criteria andFromImUserIdNotBetween(Long value1, Long value2) {
            addCriterion("from_im_user_id not between", value1, value2, "fromImUserId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}