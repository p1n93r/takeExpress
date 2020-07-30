package com.express.domain;

import java.util.ArrayList;
import java.util.List;

public class LogOperationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LogOperationExample() {
        oredCriteria = new ArrayList<>();
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
            criteria = new ArrayList<>();
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

        public Criteria andLogOpidIsNull() {
            addCriterion("log_opid is null");
            return (Criteria) this;
        }

        public Criteria andLogOpidIsNotNull() {
            addCriterion("log_opid is not null");
            return (Criteria) this;
        }

        public Criteria andLogOpidEqualTo(Integer value) {
            addCriterion("log_opid =", value, "logOpid");
            return (Criteria) this;
        }

        public Criteria andLogOpidNotEqualTo(Integer value) {
            addCriterion("log_opid <>", value, "logOpid");
            return (Criteria) this;
        }

        public Criteria andLogOpidGreaterThan(Integer value) {
            addCriterion("log_opid >", value, "logOpid");
            return (Criteria) this;
        }

        public Criteria andLogOpidGreaterThanOrEqualTo(Integer value) {
            addCriterion("log_opid >=", value, "logOpid");
            return (Criteria) this;
        }

        public Criteria andLogOpidLessThan(Integer value) {
            addCriterion("log_opid <", value, "logOpid");
            return (Criteria) this;
        }

        public Criteria andLogOpidLessThanOrEqualTo(Integer value) {
            addCriterion("log_opid <=", value, "logOpid");
            return (Criteria) this;
        }

        public Criteria andLogOpidIn(List<Integer> values) {
            addCriterion("log_opid in", values, "logOpid");
            return (Criteria) this;
        }

        public Criteria andLogOpidNotIn(List<Integer> values) {
            addCriterion("log_opid not in", values, "logOpid");
            return (Criteria) this;
        }

        public Criteria andLogOpidBetween(Integer value1, Integer value2) {
            addCriterion("log_opid between", value1, value2, "logOpid");
            return (Criteria) this;
        }

        public Criteria andLogOpidNotBetween(Integer value1, Integer value2) {
            addCriterion("log_opid not between", value1, value2, "logOpid");
            return (Criteria) this;
        }

        public Criteria andLogOptypeIsNull() {
            addCriterion("log_optype is null");
            return (Criteria) this;
        }

        public Criteria andLogOptypeIsNotNull() {
            addCriterion("log_optype is not null");
            return (Criteria) this;
        }

        public Criteria andLogOptypeEqualTo(String value) {
            addCriterion("log_optype =", value, "logOptype");
            return (Criteria) this;
        }

        public Criteria andLogOptypeNotEqualTo(String value) {
            addCriterion("log_optype <>", value, "logOptype");
            return (Criteria) this;
        }

        public Criteria andLogOptypeGreaterThan(String value) {
            addCriterion("log_optype >", value, "logOptype");
            return (Criteria) this;
        }

        public Criteria andLogOptypeGreaterThanOrEqualTo(String value) {
            addCriterion("log_optype >=", value, "logOptype");
            return (Criteria) this;
        }

        public Criteria andLogOptypeLessThan(String value) {
            addCriterion("log_optype <", value, "logOptype");
            return (Criteria) this;
        }

        public Criteria andLogOptypeLessThanOrEqualTo(String value) {
            addCriterion("log_optype <=", value, "logOptype");
            return (Criteria) this;
        }

        public Criteria andLogOptypeLike(String value) {
            addCriterion("log_optype like", value, "logOptype");
            return (Criteria) this;
        }

        public Criteria andLogOptypeNotLike(String value) {
            addCriterion("log_optype not like", value, "logOptype");
            return (Criteria) this;
        }

        public Criteria andLogOptypeIn(List<String> values) {
            addCriterion("log_optype in", values, "logOptype");
            return (Criteria) this;
        }

        public Criteria andLogOptypeNotIn(List<String> values) {
            addCriterion("log_optype not in", values, "logOptype");
            return (Criteria) this;
        }

        public Criteria andLogOptypeBetween(String value1, String value2) {
            addCriterion("log_optype between", value1, value2, "logOptype");
            return (Criteria) this;
        }

        public Criteria andLogOptypeNotBetween(String value1, String value2) {
            addCriterion("log_optype not between", value1, value2, "logOptype");
            return (Criteria) this;
        }

        public Criteria andLogOpcontentIsNull() {
            addCriterion("log_opcontent is null");
            return (Criteria) this;
        }

        public Criteria andLogOpcontentIsNotNull() {
            addCriterion("log_opcontent is not null");
            return (Criteria) this;
        }

        public Criteria andLogOpcontentEqualTo(String value) {
            addCriterion("log_opcontent =", value, "logOpcontent");
            return (Criteria) this;
        }

        public Criteria andLogOpcontentNotEqualTo(String value) {
            addCriterion("log_opcontent <>", value, "logOpcontent");
            return (Criteria) this;
        }

        public Criteria andLogOpcontentGreaterThan(String value) {
            addCriterion("log_opcontent >", value, "logOpcontent");
            return (Criteria) this;
        }

        public Criteria andLogOpcontentGreaterThanOrEqualTo(String value) {
            addCriterion("log_opcontent >=", value, "logOpcontent");
            return (Criteria) this;
        }

        public Criteria andLogOpcontentLessThan(String value) {
            addCriterion("log_opcontent <", value, "logOpcontent");
            return (Criteria) this;
        }

        public Criteria andLogOpcontentLessThanOrEqualTo(String value) {
            addCriterion("log_opcontent <=", value, "logOpcontent");
            return (Criteria) this;
        }

        public Criteria andLogOpcontentLike(String value) {
            addCriterion("log_opcontent like", value, "logOpcontent");
            return (Criteria) this;
        }

        public Criteria andLogOpcontentNotLike(String value) {
            addCriterion("log_opcontent not like", value, "logOpcontent");
            return (Criteria) this;
        }

        public Criteria andLogOpcontentIn(List<String> values) {
            addCriterion("log_opcontent in", values, "logOpcontent");
            return (Criteria) this;
        }

        public Criteria andLogOpcontentNotIn(List<String> values) {
            addCriterion("log_opcontent not in", values, "logOpcontent");
            return (Criteria) this;
        }

        public Criteria andLogOpcontentBetween(String value1, String value2) {
            addCriterion("log_opcontent between", value1, value2, "logOpcontent");
            return (Criteria) this;
        }

        public Criteria andLogOpcontentNotBetween(String value1, String value2) {
            addCriterion("log_opcontent not between", value1, value2, "logOpcontent");
            return (Criteria) this;
        }

        public Criteria andLogOptimeIsNull() {
            addCriterion("log_optime is null");
            return (Criteria) this;
        }

        public Criteria andLogOptimeIsNotNull() {
            addCriterion("log_optime is not null");
            return (Criteria) this;
        }

        public Criteria andLogOptimeEqualTo(String value) {
            addCriterion("log_optime =", value, "logOptime");
            return (Criteria) this;
        }

        public Criteria andLogOptimeNotEqualTo(String value) {
            addCriterion("log_optime <>", value, "logOptime");
            return (Criteria) this;
        }

        public Criteria andLogOptimeGreaterThan(String value) {
            addCriterion("log_optime >", value, "logOptime");
            return (Criteria) this;
        }

        public Criteria andLogOptimeGreaterThanOrEqualTo(String value) {
            addCriterion("log_optime >=", value, "logOptime");
            return (Criteria) this;
        }

        public Criteria andLogOptimeLessThan(String value) {
            addCriterion("log_optime <", value, "logOptime");
            return (Criteria) this;
        }

        public Criteria andLogOptimeLessThanOrEqualTo(String value) {
            addCriterion("log_optime <=", value, "logOptime");
            return (Criteria) this;
        }

        public Criteria andLogOptimeLike(String value) {
            addCriterion("log_optime like", value, "logOptime");
            return (Criteria) this;
        }

        public Criteria andLogOptimeNotLike(String value) {
            addCriterion("log_optime not like", value, "logOptime");
            return (Criteria) this;
        }

        public Criteria andLogOptimeIn(List<String> values) {
            addCriterion("log_optime in", values, "logOptime");
            return (Criteria) this;
        }

        public Criteria andLogOptimeNotIn(List<String> values) {
            addCriterion("log_optime not in", values, "logOptime");
            return (Criteria) this;
        }

        public Criteria andLogOptimeBetween(String value1, String value2) {
            addCriterion("log_optime between", value1, value2, "logOptime");
            return (Criteria) this;
        }

        public Criteria andLogOptimeNotBetween(String value1, String value2) {
            addCriterion("log_optime not between", value1, value2, "logOptime");
            return (Criteria) this;
        }
    }

    /**
     */
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