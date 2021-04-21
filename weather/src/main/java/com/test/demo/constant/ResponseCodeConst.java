package com.test.demo.constant;

public class ResponseCodeConst {
	public static final ResponseCodeConst SUCCESS = new ResponseCodeConst(1, "成功!", true);

    public static final ResponseCodeConst ERROR_PARAM = new ResponseCodeConst(2, "%s参数异常！");

    public static final ResponseCodeConst SYSTEM_ERROR = new ResponseCodeConst(3, "系统异常！");

    public static final ResponseCodeConst NOT_EXISTS = new ResponseCodeConst(4, "数据不存在!");

    public static final ResponseCodeConst REQUEST_METHOD_ERROR = new ResponseCodeConst(5, "请求方式错误");

    public static final ResponseCodeConst JSON_FORMAT_ERROR = new ResponseCodeConst(6, "JSON格式错误");
    
    public static final ResponseCodeConst PROVINCE_ERROR = new ResponseCodeConst(7, "The province code is error!");
    
    public static final ResponseCodeConst CITY_ERROR = new ResponseCodeConst(8, "The city code is error!");
    
    public static final ResponseCodeConst COUNTY_ERROR = new ResponseCodeConst(9, "The county code is error!");

    protected int code;

    protected String msg;

    protected boolean success;

    public ResponseCodeConst() {
    }

    protected ResponseCodeConst(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    protected ResponseCodeConst(int code, String msg, boolean success) {
        super();
        this.code = code;
        this.msg = msg;
        this.success = success;
    }

    protected ResponseCodeConst(int code) {
        super();
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
