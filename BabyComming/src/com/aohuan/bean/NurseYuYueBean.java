package com.aohuan.bean;

import java.io.Serializable;

public class NurseYuYueBean implements Serializable{
	private String code;
	private String message;
	private String pay_id;
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
	public String getPay_id() {
		return pay_id;
	}
	public void setPay_id(String pay_id) {
		this.pay_id = pay_id;
	}
	@Override
	public String toString() {
		return "NurseYuYueBean [code=" + code + ", message=" + message
				+ ", pay_id=" + pay_id + "]";
	}
	

}
