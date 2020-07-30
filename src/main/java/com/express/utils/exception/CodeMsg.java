package com.express.utils.exception;

/**
 * @author fyzn12
 * @version 1.0
 * @date 2020/3/19 19:02
 */
public class CodeMsg {
    private int code;
    private String msg;
    //通用的错误码
    public static CodeMsg SUCCESS = new CodeMsg(0, "success");
    public static CodeMsg PHONE_ERROR = new CodeMsg(400, "手机号或者验证码格式不正确");
    public static CodeMsg EMAIL_ERROR = new CodeMsg(400, "邮箱格式不正确");
    //登录模块 5002XX
    public static CodeMsg SERVER_ERROR = new CodeMsg(400, "数据格式不正确");
    public static CodeMsg PASSWORD_ERROR = new CodeMsg(400, "密码不符合条件");
    public static CodeMsg BIND_ERROR = new CodeMsg(500212, "绑定信息错误");
    public static CodeMsg STUDENTID_ERROR = new CodeMsg(500213, "学号格式错误");
    public static CodeMsg STUDENTIDE_NOT_EXIST = new CodeMsg(500214, "学号不存在");
    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }
    private CodeMsg(int code,String msg) {
        this.code = code;
        this.msg = msg;
    }
    @Override
    public String toString() {
        return "CodeMsg{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
