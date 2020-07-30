package com.express.common;

public class FaceResult {
    //根据百度API
    private String group_id;
    private String user_id;
    //用户信息，这里在绑定人脸识别的时候我将loginid存入该字段
    private String user_info;
    private float score;
    public FaceResult(){}
    public String getGroup_id() {
        return group_id;
    }

    @Override
    public String toString() {
        return "FaceResult{" +
                "group_id='" + group_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", user_info='" + user_info + '\'' +
                ", score=" + score +
                '}';
    }

    public FaceResult(String group_id, String user_id, String user_info, float score) {
        this.group_id = group_id;
        this.user_id = user_id;
        this.user_info = user_info;
        this.score = score;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_info() {
        return user_info;
    }

    public void setUser_info(String user_info) {
        this.user_info = user_info;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
