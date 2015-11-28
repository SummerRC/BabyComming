package com.aohuan.bean;

import java.io.Serializable;

public class CodeMessageBean implements Serializable{
	private String code;
	private String message;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "CodeMessageBean [code=" + code + ", message=" + message + "]";
	}
	

}
