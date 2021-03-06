package com.aohuan.bean;

import java.io.Serializable;
import java.util.List;

public class YuesaoListBean implements Serializable{

	private String code;
	private String message;
	public List<YuesaoList> list;


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


	public List<YuesaoList> getList() {
		return list;
	}


	public void setList(List<YuesaoList> list) {
		this.list = list;
	}



	public class YuesaoList implements Serializable{
		private String id;
		private String name;
		private String price;
		private String age;
		private String location_city;
		private String education;
		private String image;
		private String grade;
		private String pingfen;
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
		public String getPrice() {
			return price;
		}
		public void setPrice(String price) {
			this.price = price;
		}
		public String getAge() {
			return age;
		}
		public void setAge(String age) {
			this.age = age;
		}
		public String getLocation_city() {
			return location_city;
		}
		public void setLocation_city(String location_city) {
			this.location_city = location_city;
		}
		public String getEducation() {
			return education;
		}
		public void setEducation(String education) {
			this.education = education;
		}
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}
		public String getGrade() {
			return grade;
		}
		public void setGrade(String grade) {
			this.grade = grade;
		}
		public String getPingfen() {
			return pingfen;
		}
		public void setPingfen(String pingfen) {
			this.pingfen = pingfen;
		}
		
	}

}
