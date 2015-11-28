package com.aohuan.bean;

import java.io.Serializable;
import java.util.List;

public class MyOrderAllBean implements Serializable{
	private String code;
	private String message;
	public List<MyOrderAll> list;
	
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

	public List<MyOrderAll> getList() {
		return list;
	}

	public void setList(List<MyOrderAll> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "MyOrderAllBean [code=" + code + ", message=" + message
				+ ", list=" + list + "]";
	}

	public class MyOrderAll implements Serializable{
		private String id;
		private String pay_id;
		private String image;
		private String title;
		private String quantity;
		private String origin;
		private String type;
		private String allowrefund;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getPay_id() {
			return pay_id;
		}
		public void setPay_id(String pay_id) {
			this.pay_id = pay_id;
		}
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getQuantity() {
			return quantity;
		}
		public void setQuantity(String quantity) {
			this.quantity = quantity;
		}
		public String getOrigin() {
			return origin;
		}
		public void setOrigin(String origin) {
			this.origin = origin;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getAllowrefund() {
			return allowrefund;
		}
		public void setAllowrefund(String allowrefund) {
			this.allowrefund = allowrefund;
		}
		@Override
		public String toString() {
			return "MyOrderAll [id=" + id + ", pay_id=" + pay_id + ", image="
					+ image + ", title=" + title + ", quantity=" + quantity
					+ ", origin=" + origin + ", type=" + type
					+ ", allowrefund=" + allowrefund + "]";
		}
		
	}

}
