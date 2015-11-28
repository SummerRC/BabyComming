package com.aohuan.bean;

import java.io.Serializable;
import java.util.List;

public class YuesaoDengjiBean implements Serializable{

	private String code;
	private String message;
	public List<YuesaoDengji> list;


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


	public List<YuesaoDengji> getList() {
		return list;
	}


	public void setList(List<YuesaoDengji> list) {
		this.list = list;
	}


	@Override
	public String toString() {
		return "YuesaoDengjiBean [code=" + code + ", message=" + message
				+ ", list=" + list + "]";
	}


	public class YuesaoDengji implements Serializable{
		private String id;
		private String name;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		@Override
		public String toString() {
			return "YuesaoDengji [id=" + id + ", name=" + name + "]";
		}
	

	}

}
