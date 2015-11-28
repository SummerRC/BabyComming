package com.aohuan.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.aohuan.bean.GuangGaoLunboBean.GuangGaoLunbo;

public class NurseDetaisBean implements Serializable{
	private String code;
	private String message;
	public List<NurseDetais> list;
	public List<Pingjia> pinglun;
	public List<Pingjia> getPinglun() {
		return pinglun;
	}

	public void setPinglun(List<Pingjia> pinglun) {
		this.pinglun = pinglun;
	}

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

	public List<NurseDetais> getList() {
		return list;
	}

	public void setList(List<NurseDetais> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "NurseDetaisBean [code=" + code + ", message=" + message
				+ ", list=" + list + "]";
	}
	public class Pingjia implements Serializable{
		private String details;
		private String create_time;
		private String username;
		public String getDetails() {
			return details;
		}
		public void setDetails(String details) {
			this.details = details;
		}
		public String getCreate_time() {
			return create_time;
		}
		public void setCreate_time(String create_time) {
			this.create_time = create_time;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		@Override
		public String toString() {
			return "Pingjia [details=" + details + ", create_time="
					+ create_time + ", username=" + username + "]";
		}
		
	}

	public class NurseDetais implements Serializable{
		private String id;
		private String name;
		private String price;
		private String ding_price;
		private String age;
		private String shoucang;
		private String location_city;
		private String education;
		private String particulars;
		private String image;
		private String grade;
		private String partner_id;
		private String partner_title;
		private String pinglun_tiao;
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
		public String getDing_price() {
			return ding_price;
		}
		public void setDing_price(String ding_price) {
			this.ding_price = ding_price;
		}
		public String getAge() {
			return age;
		}
		public void setAge(String age) {
			this.age = age;
		}
		public String getShoucang() {
			return shoucang;
		}
		public void setShoucang(String shoucang) {
			this.shoucang = shoucang;
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
		public String getParticulars() {
			return particulars;
		}
		public void setParticulars(String particulars) {
			this.particulars = particulars;
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
		public String getPartner_id() {
			return partner_id;
		}
		public void setPartner_id(String partner_id) {
			this.partner_id = partner_id;
		}
		public String getPartner_title() {
			return partner_title;
		}
		public void setPartner_title(String partner_title) {
			this.partner_title = partner_title;
		}
		public String getPinglun_tiao() {
			return pinglun_tiao;
		}
		public void setPinglun_tiao(String pinglun_tiao) {
			this.pinglun_tiao = pinglun_tiao;
		}
		public String getPingfen() {
			return pingfen;
		}
		public void setPingfen(String pingfen) {
			this.pingfen = pingfen;
		}
		@Override
		public String toString() {
			return "NurseDetais [id=" + id + ", name=" + name + ", price="
					+ price + ", ding_price=" + ding_price + ", age=" + age
					+ ", shoucang=" + shoucang + ", location_city="
					+ location_city + ", education=" + education
					+ ", particulars=" + particulars + ", image=" + image
					+ ", grade=" + grade + ", partner_id=" + partner_id
					+ ", partner_title=" + partner_title + ", pinglun_tiao="
					+ pinglun_tiao + ", pingfen=" + pingfen + "]";
		}
		
		
	}

}
