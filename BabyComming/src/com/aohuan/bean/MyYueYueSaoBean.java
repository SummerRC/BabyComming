package com.aohuan.bean;

import java.util.List;

public class MyYueYueSaoBean extends BaseBean{

	private List<YueSao> list;
	
	 public List<YueSao> getList() {
		return list;
	}

	public void setList(List<YueSao> list) {
		this.list = list;
	}

	public class YueSao{
		 
		 private String id;
		 private String pay_id;
		 private String name;
		 private String price;
		 private String age;
		 private String state;
		 private String location_city;
		 private String education;
		 private String image;
		 private String grade;
		 private String pingfen;
		 
		public String getPay_id() {
			return pay_id;
		}
		public void setPay_id(String pay_id) {
			this.pay_id = pay_id;
		}
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
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
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
