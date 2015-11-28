package com.aohuan.bean;

import java.util.List;

/**
 * 套餐详情
 * 
 * @author Administrator
 * 
 */
public class TaoCanDetailBean {

	private int code;
	private String message;
	
	List<String> image;
	private String id;
	private String price;
	private String shoucang;
	private String farefree;
	private String count;
	private String summary;
	private String presentation;
	private String team_price;
	private String market_price;
	private String title;

	private List<KuaiDi> kuaidi;
	private Partner partner;

	
	public List<String> getImage() {
		return image;
	}

	public void setImage(List<String> image) {
		this.image = image;
	}

	public int getCode() {
		return code;
	}

	public String getTeam_price() {
		return team_price;
	}

	public void setTeam_price(String team_price) {
		this.team_price = team_price;
	}

	public String getMarket_price() {
		return market_price;
	}

	public void setMarket_price(String market_price) {
		this.market_price = market_price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getShoucang() {
		return shoucang;
	}

	public void setShoucang(String shoucang) {
		this.shoucang = shoucang;
	}

	public String getFarefree() {
		return farefree;
	}

	public void setFarefree(String farefree) {
		this.farefree = farefree;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getPresentation() {
		return presentation;
	}

	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}

	public List<KuaiDi> getKuaidi() {
		return kuaidi;
	}

	public void setKuaidi(List<KuaiDi> kuaidi) {
		this.kuaidi = kuaidi;
	}

	public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public class Partner {
		private String id;
		private String title;
		private String pingfen;
		private String address;
		private String pinglun_number;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
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

	}

}
