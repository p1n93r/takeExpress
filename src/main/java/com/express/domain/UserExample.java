package com.express.domain;

import java.util.ArrayList;
import java.util.List;

public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserExample() {
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("`name` is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("`name` is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("`name` =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("`name` <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("`name` >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("`name` >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("`name` <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("`name` <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("`name` like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("`name` not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("`name` in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("`name` not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("`name` between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("`name` not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andSexIsNull() {
            addCriterion("sex is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(String value) {
            addCriterion("sex =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(String value) {
            addCriterion("sex <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(String value) {
            addCriterion("sex >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(String value) {
            addCriterion("sex >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(String value) {
            addCriterion("sex <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(String value) {
            addCriterion("sex <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLike(String value) {
            addCriterion("sex like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotLike(String value) {
            addCriterion("sex not like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<String> values) {
            addCriterion("sex in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<String> values) {
            addCriterion("sex not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(String value1, String value2) {
            addCriterion("sex between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(String value1, String value2) {
            addCriterion("sex not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPicIsNull() {
            addCriterion("pic is null");
            return (Criteria) this;
        }

        public Criteria andPicIsNotNull() {
            addCriterion("pic is not null");
            return (Criteria) this;
        }

        public Criteria andPicEqualTo(String value) {
            addCriterion("pic =", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotEqualTo(String value) {
            addCriterion("pic <>", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicGreaterThan(String value) {
            addCriterion("pic >", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicGreaterThanOrEqualTo(String value) {
            addCriterion("pic >=", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLessThan(String value) {
            addCriterion("pic <", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLessThanOrEqualTo(String value) {
            addCriterion("pic <=", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLike(String value) {
            addCriterion("pic like", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotLike(String value) {
            addCriterion("pic not like", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicIn(List<String> values) {
            addCriterion("pic in", values, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotIn(List<String> values) {
            addCriterion("pic not in", values, "pic");
            return (Criteria) this;
        }

        public Criteria andPicBetween(String value1, String value2) {
            addCriterion("pic between", value1, value2, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotBetween(String value1, String value2) {
            addCriterion("pic not between", value1, value2, "pic");
            return (Criteria) this;
        }

        public Criteria andStuPicIsNull() {
            addCriterion("stu_pic is null");
            return (Criteria) this;
        }

        public Criteria andStuPicIsNotNull() {
            addCriterion("stu_pic is not null");
            return (Criteria) this;
        }

        public Criteria andStuPicEqualTo(String value) {
            addCriterion("stu_pic =", value, "stuPic");
            return (Criteria) this;
        }

        public Criteria andStuPicNotEqualTo(String value) {
            addCriterion("stu_pic <>", value, "stuPic");
            return (Criteria) this;
        }

        public Criteria andStuPicGreaterThan(String value) {
            addCriterion("stu_pic >", value, "stuPic");
            return (Criteria) this;
        }

        public Criteria andStuPicGreaterThanOrEqualTo(String value) {
            addCriterion("stu_pic >=", value, "stuPic");
            return (Criteria) this;
        }

        public Criteria andStuPicLessThan(String value) {
            addCriterion("stu_pic <", value, "stuPic");
            return (Criteria) this;
        }

        public Criteria andStuPicLessThanOrEqualTo(String value) {
            addCriterion("stu_pic <=", value, "stuPic");
            return (Criteria) this;
        }

        public Criteria andStuPicLike(String value) {
            addCriterion("stu_pic like", value, "stuPic");
            return (Criteria) this;
        }

        public Criteria andStuPicNotLike(String value) {
            addCriterion("stu_pic not like", value, "stuPic");
            return (Criteria) this;
        }

        public Criteria andStuPicIn(List<String> values) {
            addCriterion("stu_pic in", values, "stuPic");
            return (Criteria) this;
        }

        public Criteria andStuPicNotIn(List<String> values) {
            addCriterion("stu_pic not in", values, "stuPic");
            return (Criteria) this;
        }

        public Criteria andStuPicBetween(String value1, String value2) {
            addCriterion("stu_pic between", value1, value2, "stuPic");
            return (Criteria) this;
        }

        public Criteria andStuPicNotBetween(String value1, String value2) {
            addCriterion("stu_pic not between", value1, value2, "stuPic");
            return (Criteria) this;
        }

        public Criteria andHeadPicIsNull() {
            addCriterion("head_pic is null");
            return (Criteria) this;
        }

        public Criteria andHeadPicIsNotNull() {
            addCriterion("head_pic is not null");
            return (Criteria) this;
        }

        public Criteria andHeadPicEqualTo(String value) {
            addCriterion("head_pic =", value, "headPic");
            return (Criteria) this;
        }

        public Criteria andHeadPicNotEqualTo(String value) {
            addCriterion("head_pic <>", value, "headPic");
            return (Criteria) this;
        }

        public Criteria andHeadPicGreaterThan(String value) {
            addCriterion("head_pic >", value, "headPic");
            return (Criteria) this;
        }

        public Criteria andHeadPicGreaterThanOrEqualTo(String value) {
            addCriterion("head_pic >=", value, "headPic");
            return (Criteria) this;
        }

        public Criteria andHeadPicLessThan(String value) {
            addCriterion("head_pic <", value, "headPic");
            return (Criteria) this;
        }

        public Criteria andHeadPicLessThanOrEqualTo(String value) {
            addCriterion("head_pic <=", value, "headPic");
            return (Criteria) this;
        }

        public Criteria andHeadPicLike(String value) {
            addCriterion("head_pic like", value, "headPic");
            return (Criteria) this;
        }

        public Criteria andHeadPicNotLike(String value) {
            addCriterion("head_pic not like", value, "headPic");
            return (Criteria) this;
        }

        public Criteria andHeadPicIn(List<String> values) {
            addCriterion("head_pic in", values, "headPic");
            return (Criteria) this;
        }

        public Criteria andHeadPicNotIn(List<String> values) {
            addCriterion("head_pic not in", values, "headPic");
            return (Criteria) this;
        }

        public Criteria andHeadPicBetween(String value1, String value2) {
            addCriterion("head_pic between", value1, value2, "headPic");
            return (Criteria) this;
        }

        public Criteria andHeadPicNotBetween(String value1, String value2) {
            addCriterion("head_pic not between", value1, value2, "headPic");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNull() {
            addCriterion("nickname is null");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNotNull() {
            addCriterion("nickname is not null");
            return (Criteria) this;
        }

        public Criteria andNicknameEqualTo(String value) {
            addCriterion("nickname =", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotEqualTo(String value) {
            addCriterion("nickname <>", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThan(String value) {
            addCriterion("nickname >", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("nickname >=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThan(String value) {
            addCriterion("nickname <", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThanOrEqualTo(String value) {
            addCriterion("nickname <=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLike(String value) {
            addCriterion("nickname like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotLike(String value) {
            addCriterion("nickname not like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameIn(List<String> values) {
            addCriterion("nickname in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotIn(List<String> values) {
            addCriterion("nickname not in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameBetween(String value1, String value2) {
            addCriterion("nickname between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotBetween(String value1, String value2) {
            addCriterion("nickname not between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andAlipayIsNull() {
            addCriterion("alipay is null");
            return (Criteria) this;
        }

        public Criteria andAlipayIsNotNull() {
            addCriterion("alipay is not null");
            return (Criteria) this;
        }

        public Criteria andAlipayEqualTo(String value) {
            addCriterion("alipay =", value, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayNotEqualTo(String value) {
            addCriterion("alipay <>", value, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayGreaterThan(String value) {
            addCriterion("alipay >", value, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayGreaterThanOrEqualTo(String value) {
            addCriterion("alipay >=", value, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayLessThan(String value) {
            addCriterion("alipay <", value, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayLessThanOrEqualTo(String value) {
            addCriterion("alipay <=", value, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayLike(String value) {
            addCriterion("alipay like", value, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayNotLike(String value) {
            addCriterion("alipay not like", value, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayIn(List<String> values) {
            addCriterion("alipay in", values, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayNotIn(List<String> values) {
            addCriterion("alipay not in", values, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayBetween(String value1, String value2) {
            addCriterion("alipay between", value1, value2, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayNotBetween(String value1, String value2) {
            addCriterion("alipay not between", value1, value2, "alipay");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("`status` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`status` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("`status` like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("`status` not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("`status` not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andDemandScoreIsNull() {
            addCriterion("demand_score is null");
            return (Criteria) this;
        }

        public Criteria andDemandScoreIsNotNull() {
            addCriterion("demand_score is not null");
            return (Criteria) this;
        }

        public Criteria andDemandScoreEqualTo(Integer value) {
            addCriterion("demand_score =", value, "demandScore");
            return (Criteria) this;
        }

        public Criteria andDemandScoreNotEqualTo(Integer value) {
            addCriterion("demand_score <>", value, "demandScore");
            return (Criteria) this;
        }

        public Criteria andDemandScoreGreaterThan(Integer value) {
            addCriterion("demand_score >", value, "demandScore");
            return (Criteria) this;
        }

        public Criteria andDemandScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("demand_score >=", value, "demandScore");
            return (Criteria) this;
        }

        public Criteria andDemandScoreLessThan(Integer value) {
            addCriterion("demand_score <", value, "demandScore");
            return (Criteria) this;
        }

        public Criteria andDemandScoreLessThanOrEqualTo(Integer value) {
            addCriterion("demand_score <=", value, "demandScore");
            return (Criteria) this;
        }

        public Criteria andDemandScoreIn(List<Integer> values) {
            addCriterion("demand_score in", values, "demandScore");
            return (Criteria) this;
        }

        public Criteria andDemandScoreNotIn(List<Integer> values) {
            addCriterion("demand_score not in", values, "demandScore");
            return (Criteria) this;
        }

        public Criteria andDemandScoreBetween(Integer value1, Integer value2) {
            addCriterion("demand_score between", value1, value2, "demandScore");
            return (Criteria) this;
        }

        public Criteria andDemandScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("demand_score not between", value1, value2, "demandScore");
            return (Criteria) this;
        }

        public Criteria andFetchScoreIsNull() {
            addCriterion("fetch_score is null");
            return (Criteria) this;
        }

        public Criteria andFetchScoreIsNotNull() {
            addCriterion("fetch_score is not null");
            return (Criteria) this;
        }

        public Criteria andFetchScoreEqualTo(Integer value) {
            addCriterion("fetch_score =", value, "fetchScore");
            return (Criteria) this;
        }

        public Criteria andFetchScoreNotEqualTo(Integer value) {
            addCriterion("fetch_score <>", value, "fetchScore");
            return (Criteria) this;
        }

        public Criteria andFetchScoreGreaterThan(Integer value) {
            addCriterion("fetch_score >", value, "fetchScore");
            return (Criteria) this;
        }

        public Criteria andFetchScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("fetch_score >=", value, "fetchScore");
            return (Criteria) this;
        }

        public Criteria andFetchScoreLessThan(Integer value) {
            addCriterion("fetch_score <", value, "fetchScore");
            return (Criteria) this;
        }

        public Criteria andFetchScoreLessThanOrEqualTo(Integer value) {
            addCriterion("fetch_score <=", value, "fetchScore");
            return (Criteria) this;
        }

        public Criteria andFetchScoreIn(List<Integer> values) {
            addCriterion("fetch_score in", values, "fetchScore");
            return (Criteria) this;
        }

        public Criteria andFetchScoreNotIn(List<Integer> values) {
            addCriterion("fetch_score not in", values, "fetchScore");
            return (Criteria) this;
        }

        public Criteria andFetchScoreBetween(Integer value1, Integer value2) {
            addCriterion("fetch_score between", value1, value2, "fetchScore");
            return (Criteria) this;
        }

        public Criteria andFetchScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("fetch_score not between", value1, value2, "fetchScore");
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