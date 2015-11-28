package com.aohuan.bean;

import java.io.Serializable;
import java.util.List;

public class OrderDetailsBean implements Serializable{
	/**
	 * "id": "2",
    "snid": "2",
    "create_time": "1970-01-01",
    "pay_id": "1",
    "title": "阿萨德发送到发发",
    "image": "",
    "quantity": "1"
    "origin": "1000.00",
    "pinglun": {
        "score": "",
        "details": ""
    },
    "list": [],
    "code": 0,
    "message": "成功"
	 */
	private String id;
	private String snid;
	private String create_time;
	private String pay_id;
	private String title;
	private String image;
	private String quantity;
	private String origin;
	public Pinglun pinglun;
	public List<OrderDetails> list;
	private String code;
	private String message;
	
	@Override
	public String toString() {
		return "OrderDetailsBean [id=" + id + ", snid=" + snid
				+ ", create_time=" + create_time + ", pay_id=" + pay_id
				+ ", title=" + title + ", image=" + image + ", quantity="
				+ quantity + ", origin=" + origin + ", pinglun=" + pinglun
				+ ", list=" + list + ", code=" + code + ", message=" + message
				+ "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSnid() {
		return snid;
	}
	public void setSnid(String snid) {
		this.snid = snid;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getPay_id() {
		return pay_id;
	}
	public void setPay_id(String pay_id) {
		this.pay_id = pay_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
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
	public Pinglun getPinglun() {
		return pinglun;
	}
	public void setPinglun(Pinglun pinglun) {
		this.pinglun = pinglun;
	}
	public List<OrderDetails> getList() {
		return list;
	}
	public void setList(List<OrderDetails> list) {
		this.list = list;
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
	public class Pinglun implements Serializable{
		private String score;
		private String details;
		public String getScore() {
			return score;
		}
		public void setScore(String score) {
			this.score = score;
		}
		public String getDetails() {
			return details;
		}
		public void setDetails(String details) {
			this.details = details;
		}
		@Override
		public String toString() {
			return "Pinglun [score=" + score + ", details=" + details + "]";
		}
		
	}
	public class OrderDetails implements Serializable{
		
		private String id;
		private String secret;
		private String consume;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getSecret() {
			return secret;
		}
		public void setSecret(String secret) {
			this.secret = secret;
		}
		public String getConsume() {
			return consume;
		}
		public void setConsume(String consume) {
			this.consume = consume;
		}
		@Override
		public String toString() {
			return "OrderDetails [id=" + id + ", secret=" + secret
					+ ", consume=" + consume + "]";
		}
		
		
	}
	

}
