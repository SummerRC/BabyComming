package com.aohuan.bean;

import java.util.List;


/**
 * 商家介绍
 * @author Administrator
 *
 */
public class StoreIntroDetailBean {

	 private int code;
	 private String message;
	 private String id;
	 private String image;
	 private String title;
	 private String count;
	 private String phone;
	 private String pingfen;
	 private String address;
	 private String pinglun_number;
	 private String other;
	 private List<GoodsList2> team;
	 
	 
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPingfen() {
		return pingfen;
	}
	public void setPingfen(String pingfen) {
		this.pingfen = pingfen;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPinglun_number() {
		return pinglun_number;
	}
	public void setPinglun_number(String pinglun_number) {
		this.pinglun_number = pinglun_number;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public List<GoodsList2> getTeam() {
		return team;
	}
	public void setTeam(List<GoodsList2> team) {
		this.team = team;
	} 
	 
	public class GoodsList2 {
		private String id;
		private String image;
		private String title;
		private String product;
		private String team_price;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
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

		public String getProduct() {
			return product;
		}

		public void setProduct(String product) {
			this.product = product;
		}

		public String getTeam_price() {
			return team_price;
		}

		public void setTeam_price(String team_price) {
			this.team_price = team_price;
		}

	}

}
