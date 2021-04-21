package com.test.demo.response;

import com.test.demo.constant.ResponseCodeConst;

public class Response<T> {

	protected int code;

	protected String msg;

	protected Boolean success;

	protected T data;

	public Response() {
	}

	public Response(ResponseCodeConst responseCodeConst, String msg) {
		this.code = responseCodeConst.getCode();
		this.msg = msg;
		this.success = responseCodeConst.isSuccess();
	}

	public Response(ResponseCodeConst responseCodeConst, T data) {
		super();
		this.code = responseCodeConst.getCode();
		this.msg = responseCodeConst.getMsg();
		this.data = data;
		this.success = responseCodeConst.isSuccess();
	}

	public Response(ResponseCodeConst responseCodeConst, T data, String msg) {
		super();
		this.code = responseCodeConst.getCode();
		this.msg = msg;
		this.data = data;
		this.success = responseCodeConst.isSuccess();
	}

	private Response(ResponseCodeConst responseCodeConst) {
		this.code = responseCodeConst.getCode();
		this.msg = responseCodeConst.getMsg();
		this.success = responseCodeConst.isSuccess();
	}

	public Response(Response<T> response) {
		this.code = response.getCode();
		this.msg = response.getMsg();
		this.success = response.isSuccess();
	}

	public static <T> Response<T> succ() {
		return new Response<T>(ResponseCodeConst.SUCCESS);
	}

	public static <T> Response<T> succData(T data) {
		return new Response<T>(ResponseCodeConst.SUCCESS, data);
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

	public Boolean isSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static <T> Response<T> wrap(ResponseCodeConst codeConst) {
		return new Response<T>(codeConst);
	}
}
