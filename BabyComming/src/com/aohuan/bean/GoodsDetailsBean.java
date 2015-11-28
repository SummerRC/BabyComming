package com.aohuan.bean;

import java.io.Serializable;
import java.util.Arrays;

public class GoodsDetailsBean implements Serializable{
	private String code;
	private String message;
	private String id;
	private String[] image;
	private String title;
	private String team_price;
	private String market_price;
	private String shoucang;
	private String presentation;
	private String summary;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String[] getImage() {
		return image;
	}
	public void setImage(String[] image) {
		this.image = image;
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
	public String getShoucang() {
		return shoucang;
	}
	public void setShoucang(String shoucang) {
		this.shoucang = shoucang;
	}
	public String getPresentation() {
		return presentation;
	}
	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	@Override
	public String toString() {
		return "GoodsDetailsBean [code=" + code + ", message=" + message
				+ ", id=" + id + ", image=" + Arrays.toString(image)
				+ ", title=" + title + ", team_price=" + team_price
				+ ", market_price=" + market_price + ", shoucang=" + shoucang
				+ ", presentation=" + presentation + ", summary=" + summary
				+ "]";
	}
	
	
	

}
