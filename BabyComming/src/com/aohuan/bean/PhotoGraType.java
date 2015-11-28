package com.aohuan.bean;

import java.util.List;

public class PhotoGraType {

	private int code;
	private String message;

	private List<PhotoType> list;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<PhotoType> getList() {
		return list;
	}

	public void setList(List<PhotoType> list) {
		this.list = list;
	}

	public class PhotoType {
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

	}

}
