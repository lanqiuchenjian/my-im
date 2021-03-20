package com.myim.server.dao.gen.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImMessageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ImMessageExample() {
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

        public Criteria andImUserSingleRelationIdIsNull() {
            addCriterion("im_user_single_relation_id is null");
            return (Criteria) this;
        }

        public Criteria andImUserSingleRelationIdIsNotNull() {
            addCriterion("im_user_single_relation_id is not null");
            return (Criteria) this;
        }

        public Criteria andImUserSingleRelationIdEqualTo(Long value) {
            addCriterion("im_user_single_relation_id =", value, "imUserSingleRelationId");
            return (Criteria) this;
        }

        public Criteria andImUserSingleRelationIdNotEqualTo(Long value) {
            addCriterion("im_user_single_relation_id <>", value, "imUserSingleRelationId");
            return (Criteria) this;
        }

        public Criteria andImUserSingleRelationIdGreaterThan(Long value) {
            addCriterion("im_user_single_relation_id >", value, "imUserSingleRelationId");
            return (Criteria) this;
        }

        public Criteria andImUserSingleRelationIdGreaterThanOrEqualTo(Long value) {
            addCriterion("im_user_single_relation_id >=", value, "imUserSingleRelationId");
            return (Criteria) this;
        }

        public Criteria andImUserSingleRelationIdLessThan(Long value) {
            addCriterion("im_user_single_relation_id <", value, "imUserSingleRelationId");
            return (Criteria) this;
        }

        public Criteria andImUserSingleRelationIdLessThanOrEqualTo(Long value) {
            addCriterion("im_user_single_relation_id <=", value, "imUserSingleRelationId");
            return (Criteria) this;
        }

        public Criteria andImUserSingleRelationIdIn(List<Long> values) {
            addCriterion("im_user_single_relation_id in", values, "imUserSingleRelationId");
            return (Criteria) this;
        }

        public Criteria andImUserSingleRelationIdNotIn(List<Long> values) {
            addCriterion("im_user_single_relation_id not in", values, "imUserSingleRelationId");
            return (Criteria) this;
        }

        public Criteria andImUserSingleRelationIdBetween(Long value1, Long value2) {
            addCriterion("im_user_single_relation_id between", value1, value2, "imUserSingleRelationId");
            return (Criteria) this;
        }

        public Criteria andImUserSingleRelationIdNotBetween(Long value1, Long value2) {
            addCriterion("im_user_single_relation_id not between", value1, value2, "imUserSingleRelationId");
            return (Criteria) this;
        }

        public Criteria andMKeyIsNull() {
            addCriterion("m_key is null");
            return (Criteria) this;
        }

        public Criteria andMKeyIsNotNull() {
            addCriterion("m_key is not null");
            return (Criteria) this;
        }

        public Criteria andMKeyEqualTo(String value) {
            addCriterion("m_key =", value, "mKey");
            return (Criteria) this;
        }

        public Criteria andMKeyNotEqualTo(String value) {
            addCriterion("m_key <>", value, "mKey");
            return (Criteria) this;
        }

        public Criteria andMKeyGreaterThan(String value) {
            addCriterion("m_key >", value, "mKey");
            return (Criteria) this;
        }

        public Criteria andMKeyGreaterThanOrEqualTo(String value) {
            addCriterion("m_key >=", value, "mKey");
            return (Criteria) this;
        }

        public Criteria andMKeyLessThan(String value) {
            addCriterion("m_key <", value, "mKey");
            return (Criteria) this;
        }

        public Criteria andMKeyLessThanOrEqualTo(String value) {
            addCriterion("m_key <=", value, "mKey");
            return (Criteria) this;
        }

        public Criteria andMKeyLike(String value) {
            addCriterion("m_key like", value, "mKey");
            return (Criteria) this;
        }

        public Criteria andMKeyNotLike(String value) {
            addCriterion("m_key not like", value, "mKey");
            return (Criteria) this;
        }

        public Criteria andMKeyIn(List<String> values) {
            addCriterion("m_key in", values, "mKey");
            return (Criteria) this;
        }

        public Criteria andMKeyNotIn(List<String> values) {
            addCriterion("m_key not in", values, "mKey");
            return (Criteria) this;
        }

        public Criteria andMKeyBetween(String value1, String value2) {
            addCriterion("m_key between", value1, value2, "mKey");
            return (Criteria) this;
        }

        public Criteria andMKeyNotBetween(String value1, String value2) {
            addCriterion("m_key not between", value1, value2, "mKey");
            return (Criteria) this;
        }

        public Criteria andMRepeatIsNull() {
            addCriterion("m_repeat is null");
            return (Criteria) this;
        }

        public Criteria andMRepeatIsNotNull() {
            addCriterion("m_repeat is not null");
            return (Criteria) this;
        }

        public Criteria andMRepeatEqualTo(String value) {
            addCriterion("m_repeat =", value, "mRepeat");
            return (Criteria) this;
        }

        public Criteria andMRepeatNotEqualTo(String value) {
            addCriterion("m_repeat <>", value, "mRepeat");
            return (Criteria) this;
        }

        public Criteria andMRepeatGreaterThan(String value) {
            addCriterion("m_repeat >", value, "mRepeat");
            return (Criteria) this;
        }

        public Criteria andMRepeatGreaterThanOrEqualTo(String value) {
            addCriterion("m_repeat >=", value, "mRepeat");
            return (Criteria) this;
        }

        public Criteria andMRepeatLessThan(String value) {
            addCriterion("m_repeat <", value, "mRepeat");
            return (Criteria) this;
        }

        public Criteria andMRepeatLessThanOrEqualTo(String value) {
            addCriterion("m_repeat <=", value, "mRepeat");
            return (Criteria) this;
        }

        public Criteria andMRepeatLike(String value) {
            addCriterion("m_repeat like", value, "mRepeat");
            return (Criteria) this;
        }

        public Criteria andMRepeatNotLike(String value) {
            addCriterion("m_repeat not like", value, "mRepeat");
            return (Criteria) this;
        }

        public Criteria andMRepeatIn(List<String> values) {
            addCriterion("m_repeat in", values, "mRepeat");
            return (Criteria) this;
        }

        public Criteria andMRepeatNotIn(List<String> values) {
            addCriterion("m_repeat not in", values, "mRepeat");
            return (Criteria) this;
        }

        public Criteria andMRepeatBetween(String value1, String value2) {
            addCriterion("m_repeat between", value1, value2, "mRepeat");
            return (Criteria) this;
        }

        public Criteria andMRepeatNotBetween(String value1, String value2) {
            addCriterion("m_repeat not between", value1, value2, "mRepeat");
            return (Criteria) this;
        }

        public Criteria andMCheckIsNull() {
            addCriterion("m_check is null");
            return (Criteria) this;
        }

        public Criteria andMCheckIsNotNull() {
            addCriterion("m_check is not null");
            return (Criteria) this;
        }

        public Criteria andMCheckEqualTo(Boolean value) {
            addCriterion("m_check =", value, "mCheck");
            return (Criteria) this;
        }

        public Criteria andMCheckNotEqualTo(Boolean value) {
            addCriterion("m_check <>", value, "mCheck");
            return (Criteria) this;
        }

        public Criteria andMCheckGreaterThan(Boolean value) {
            addCriterion("m_check >", value, "mCheck");
            return (Criteria) this;
        }

        public Criteria andMCheckGreaterThanOrEqualTo(Boolean value) {
            addCriterion("m_check >=", value, "mCheck");
            return (Criteria) this;
        }

        public Criteria andMCheckLessThan(Boolean value) {
            addCriterion("m_check <", value, "mCheck");
            return (Criteria) this;
        }

        public Criteria andMCheckLessThanOrEqualTo(Boolean value) {
            addCriterion("m_check <=", value, "mCheck");
            return (Criteria) this;
        }

        public Criteria andMCheckIn(List<Boolean> values) {
            addCriterion("m_check in", values, "mCheck");
            return (Criteria) this;
        }

        public Criteria andMCheckNotIn(List<Boolean> values) {
            addCriterion("m_check not in", values, "mCheck");
            return (Criteria) this;
        }

        public Criteria andMCheckBetween(Boolean value1, Boolean value2) {
            addCriterion("m_check between", value1, value2, "mCheck");
            return (Criteria) this;
        }

        public Criteria andMCheckNotBetween(Boolean value1, Boolean value2) {
            addCriterion("m_check not between", value1, value2, "mCheck");
            return (Criteria) this;
        }

        public Criteria andMStatusIsNull() {
            addCriterion("m_status is null");
            return (Criteria) this;
        }

        public Criteria andMStatusIsNotNull() {
            addCriterion("m_status is not null");
            return (Criteria) this;
        }

        public Criteria andMStatusEqualTo(Integer value) {
            addCriterion("m_status =", value, "mStatus");
            return (Criteria) this;
        }

        public Criteria andMStatusNotEqualTo(Integer value) {
            addCriterion("m_status <>", value, "mStatus");
            return (Criteria) this;
        }

        public Criteria andMStatusGreaterThan(Integer value) {
            addCriterion("m_status >", value, "mStatus");
            return (Criteria) this;
        }

        public Criteria andMStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("m_status >=", value, "mStatus");
            return (Criteria) this;
        }

        public Criteria andMStatusLessThan(Integer value) {
            addCriterion("m_status <", value, "mStatus");
            return (Criteria) this;
        }

        public Criteria andMStatusLessThanOrEqualTo(Integer value) {
            addCriterion("m_status <=", value, "mStatus");
            return (Criteria) this;
        }

        public Criteria andMStatusIn(List<Integer> values) {
            addCriterion("m_status in", values, "mStatus");
            return (Criteria) this;
        }

        public Criteria andMStatusNotIn(List<Integer> values) {
            addCriterion("m_status not in", values, "mStatus");
            return (Criteria) this;
        }

        public Criteria andMStatusBetween(Integer value1, Integer value2) {
            addCriterion("m_status between", value1, value2, "mStatus");
            return (Criteria) this;
        }

        public Criteria andMStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("m_status not between", value1, value2, "mStatus");
            return (Criteria) this;
        }

        public Criteria andMsgIdIsNull() {
            addCriterion("msg_id is null");
            return (Criteria) this;
        }

        public Criteria andMsgIdIsNotNull() {
            addCriterion("msg_id is not null");
            return (Criteria) this;
        }

        public Criteria andMsgIdEqualTo(Integer value) {
            addCriterion("msg_id =", value, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdNotEqualTo(Integer value) {
            addCriterion("msg_id <>", value, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdGreaterThan(Integer value) {
            addCriterion("msg_id >", value, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("msg_id >=", value, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdLessThan(Integer value) {
            addCriterion("msg_id <", value, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdLessThanOrEqualTo(Integer value) {
            addCriterion("msg_id <=", value, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdIn(List<Integer> values) {
            addCriterion("msg_id in", values, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdNotIn(List<Integer> values) {
            addCriterion("msg_id not in", values, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdBetween(Integer value1, Integer value2) {
            addCriterion("msg_id between", value1, value2, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdNotBetween(Integer value1, Integer value2) {
            addCriterion("msg_id not between", value1, value2, "msgId");
            return (Criteria) this;
        }

        public Criteria andMActionIsNull() {
            addCriterion("m_action is null");
            return (Criteria) this;
        }

        public Criteria andMActionIsNotNull() {
            addCriterion("m_action is not null");
            return (Criteria) this;
        }

        public Criteria andMActionEqualTo(String value) {
            addCriterion("m_action =", value, "mAction");
            return (Criteria) this;
        }

        public Criteria andMActionNotEqualTo(String value) {
            addCriterion("m_action <>", value, "mAction");
            return (Criteria) this;
        }

        public Criteria andMActionGreaterThan(String value) {
            addCriterion("m_action >", value, "mAction");
            return (Criteria) this;
        }

        public Criteria andMActionGreaterThanOrEqualTo(String value) {
            addCriterion("m_action >=", value, "mAction");
            return (Criteria) this;
        }

        public Criteria andMActionLessThan(String value) {
            addCriterion("m_action <", value, "mAction");
            return (Criteria) this;
        }

        public Criteria andMActionLessThanOrEqualTo(String value) {
            addCriterion("m_action <=", value, "mAction");
            return (Criteria) this;
        }

        public Criteria andMActionLike(String value) {
            addCriterion("m_action like", value, "mAction");
            return (Criteria) this;
        }

        public Criteria andMActionNotLike(String value) {
            addCriterion("m_action not like", value, "mAction");
            return (Criteria) this;
        }

        public Criteria andMActionIn(List<String> values) {
            addCriterion("m_action in", values, "mAction");
            return (Criteria) this;
        }

        public Criteria andMActionNotIn(List<String> values) {
            addCriterion("m_action not in", values, "mAction");
            return (Criteria) this;
        }

        public Criteria andMActionBetween(String value1, String value2) {
            addCriterion("m_action between", value1, value2, "mAction");
            return (Criteria) this;
        }

        public Criteria andMActionNotBetween(String value1, String value2) {
            addCriterion("m_action not between", value1, value2, "mAction");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andMFormatIsNull() {
            addCriterion("m_format is null");
            return (Criteria) this;
        }

        public Criteria andMFormatIsNotNull() {
            addCriterion("m_format is not null");
            return (Criteria) this;
        }

        public Criteria andMFormatEqualTo(String value) {
            addCriterion("m_format =", value, "mFormat");
            return (Criteria) this;
        }

        public Criteria andMFormatNotEqualTo(String value) {
            addCriterion("m_format <>", value, "mFormat");
            return (Criteria) this;
        }

        public Criteria andMFormatGreaterThan(String value) {
            addCriterion("m_format >", value, "mFormat");
            return (Criteria) this;
        }

        public Criteria andMFormatGreaterThanOrEqualTo(String value) {
            addCriterion("m_format >=", value, "mFormat");
            return (Criteria) this;
        }

        public Criteria andMFormatLessThan(String value) {
            addCriterion("m_format <", value, "mFormat");
            return (Criteria) this;
        }

        public Criteria andMFormatLessThanOrEqualTo(String value) {
            addCriterion("m_format <=", value, "mFormat");
            return (Criteria) this;
        }

        public Criteria andMFormatLike(String value) {
            addCriterion("m_format like", value, "mFormat");
            return (Criteria) this;
        }

        public Criteria andMFormatNotLike(String value) {
            addCriterion("m_format not like", value, "mFormat");
            return (Criteria) this;
        }

        public Criteria andMFormatIn(List<String> values) {
            addCriterion("m_format in", values, "mFormat");
            return (Criteria) this;
        }

        public Criteria andMFormatNotIn(List<String> values) {
            addCriterion("m_format not in", values, "mFormat");
            return (Criteria) this;
        }

        public Criteria andMFormatBetween(String value1, String value2) {
            addCriterion("m_format between", value1, value2, "mFormat");
            return (Criteria) this;
        }

        public Criteria andMFormatNotBetween(String value1, String value2) {
            addCriterion("m_format not between", value1, value2, "mFormat");
            return (Criteria) this;
        }

        public Criteria andSenderIsNull() {
            addCriterion("sender is null");
            return (Criteria) this;
        }

        public Criteria andSenderIsNotNull() {
            addCriterion("sender is not null");
            return (Criteria) this;
        }

        public Criteria andSenderEqualTo(String value) {
            addCriterion("sender =", value, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderNotEqualTo(String value) {
            addCriterion("sender <>", value, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderGreaterThan(String value) {
            addCriterion("sender >", value, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderGreaterThanOrEqualTo(String value) {
            addCriterion("sender >=", value, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderLessThan(String value) {
            addCriterion("sender <", value, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderLessThanOrEqualTo(String value) {
            addCriterion("sender <=", value, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderLike(String value) {
            addCriterion("sender like", value, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderNotLike(String value) {
            addCriterion("sender not like", value, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderIn(List<String> values) {
            addCriterion("sender in", values, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderNotIn(List<String> values) {
            addCriterion("sender not in", values, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderBetween(String value1, String value2) {
            addCriterion("sender between", value1, value2, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderNotBetween(String value1, String value2) {
            addCriterion("sender not between", value1, value2, "sender");
            return (Criteria) this;
        }

        public Criteria andReceiverIsNull() {
            addCriterion("receiver is null");
            return (Criteria) this;
        }

        public Criteria andReceiverIsNotNull() {
            addCriterion("receiver is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverEqualTo(String value) {
            addCriterion("receiver =", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotEqualTo(String value) {
            addCriterion("receiver <>", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverGreaterThan(String value) {
            addCriterion("receiver >", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverGreaterThanOrEqualTo(String value) {
            addCriterion("receiver >=", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLessThan(String value) {
            addCriterion("receiver <", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLessThanOrEqualTo(String value) {
            addCriterion("receiver <=", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLike(String value) {
            addCriterion("receiver like", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotLike(String value) {
            addCriterion("receiver not like", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverIn(List<String> values) {
            addCriterion("receiver in", values, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotIn(List<String> values) {
            addCriterion("receiver not in", values, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverBetween(String value1, String value2) {
            addCriterion("receiver between", value1, value2, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotBetween(String value1, String value2) {
            addCriterion("receiver not between", value1, value2, "receiver");
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

        public Criteria andMTimestampIsNull() {
            addCriterion("m_timestamp is null");
            return (Criteria) this;
        }

        public Criteria andMTimestampIsNotNull() {
            addCriterion("m_timestamp is not null");
            return (Criteria) this;
        }

        public Criteria andMTimestampEqualTo(Date value) {
            addCriterion("m_timestamp =", value, "mTimestamp");
            return (Criteria) this;
        }

        public Criteria andMTimestampNotEqualTo(Date value) {
            addCriterion("m_timestamp <>", value, "mTimestamp");
            return (Criteria) this;
        }

        public Criteria andMTimestampGreaterThan(Date value) {
            addCriterion("m_timestamp >", value, "mTimestamp");
            return (Criteria) this;
        }

        public Criteria andMTimestampGreaterThanOrEqualTo(Date value) {
            addCriterion("m_timestamp >=", value, "mTimestamp");
            return (Criteria) this;
        }

        public Criteria andMTimestampLessThan(Date value) {
            addCriterion("m_timestamp <", value, "mTimestamp");
            return (Criteria) this;
        }

        public Criteria andMTimestampLessThanOrEqualTo(Date value) {
            addCriterion("m_timestamp <=", value, "mTimestamp");
            return (Criteria) this;
        }

        public Criteria andMTimestampIn(List<Date> values) {
            addCriterion("m_timestamp in", values, "mTimestamp");
            return (Criteria) this;
        }

        public Criteria andMTimestampNotIn(List<Date> values) {
            addCriterion("m_timestamp not in", values, "mTimestamp");
            return (Criteria) this;
        }

        public Criteria andMTimestampBetween(Date value1, Date value2) {
            addCriterion("m_timestamp between", value1, value2, "mTimestamp");
            return (Criteria) this;
        }

        public Criteria andMTimestampNotBetween(Date value1, Date value2) {
            addCriterion("m_timestamp not between", value1, value2, "mTimestamp");
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