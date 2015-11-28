package com.aohuan.bean;

import java.io.Serializable;
import java.util.List;

public class GuangGaoLunboBean implements Serializable{

	private String code;
	private String message;
	public List<GuangGaoLunbo> list;


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


	public List<GuangGaoLunbo> getList() {
		return list;
	}


	public void setList(List<GuangGaoLunbo> list) {
		this.list = list;
	}


	public class GuangGaoLunbo implements Serializable{
		private String team_id;
		private String images;
		public String getTeam_id() {
			return team_id;
		}
		public void setTeam_id(String team_id) {
			this.team_id = team_id;
		}
		public String getImages() {
			return images;
		}
		public void setImages(String images) {
			this.images = images;
		}

	}

}
