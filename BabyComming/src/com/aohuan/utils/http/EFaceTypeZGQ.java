package com.aohuan.utils.http;


/**
 * 
 */
public enum EFaceTypeZGQ {
	/** 广告 */
	URL_GUANGGAO(UrlConstants.URL+"App/webapp.php?action=ad"),
	/** 客服电话 */
	URL_KEFU_PHONE(UrlConstants.URL+"App/webapp.php?action=phone"),
	/** 版本更新 */
	URL_VERSION(UrlConstants.URL+"App/webapp.php?action=android"),
	/** 月嫂等级列表 */
	URL_GRADE_LIST(UrlConstants.URL+"App/webapp.php?action=grade"),
	/** 区域列表 */
	URL_QUYU_LIST(UrlConstants.URL+"App/webapp.php?action=city"),
	/** 月嫂列表 */
	URL_YUESAO_LIST(UrlConstants.URL+"App/webapp.php?action=yuesao"),
	/** 月嫂详情页 */
	URL_YUESAO_DETAILS(UrlConstants.URL+"App/webapp.php?action=yuesao_show"),
	/** 月嫂收藏 */
	URL_YUESAO_COLLECT(UrlConstants.URL+"App/webapp.php?action=yuesao_shoucang"),
	/** 月嫂评价 */
	URL_YUESAO_PINGJIA(UrlConstants.URL+"App/webapp.php?action=yuesao_pl"),
	/** 预约月嫂 */
	URL_YUESAO_ORDER(UrlConstants.URL+"App/webapp.php?action=yuesao_buy"),
	/** 月嫂公司 */
	URL_YUESAO_PARENTS(UrlConstants.URL+"App/webapp.php?action=yuesao_partner_show"),
	/** 商品列表 */
	URL_SELCT_GOODS(UrlConstants.URL+"App/webapp.php?action=goods_team"),
	/** 商品详情 */
	URL_SELCT_GOODS_DETAILS(UrlConstants.URL+"App/webapp.php?action=team_show"),
	/** 商品详情 */
	URL_SELCT_GOODS_COLLECT(UrlConstants.URL+"App/webapp.php?action=team_shoucang"),
	/** 我的订单---全部 */
	URL_MY_ORDER_ALL(UrlConstants.URL+"App/webapp.php?action=my_order"),
	/** 我的订单---待付款 */
	URL_MY_ORDER_NO_FUKUAN(UrlConstants.URL+"App/webapp.php?action=my_order_unpay"),
	/** 我的订单---未消费 */
	URL_MY_ORDER_NO_XIAOFEI(UrlConstants.URL+"App/webapp.php?action=my_order_pay"),
	/** 我的订单---待评价 */
	URL_MY_ORDER_NO_PINGJIA(UrlConstants.URL+"App/webapp.php?action=my_order_pl"),
	/** 订单详情 */
	URL_MY_ORDER_DETAILS(UrlConstants.URL+"App/webapp.php?action=my_order_detail"),
	/** 订单评价 */
	URL_MY_ORDER_PINGJIA(UrlConstants.URL+"App/webapp.php?action=order_pl"),
	/** 待付款订单删除 */
	URL_DELETE_MY_ORDER(UrlConstants.URL+"App/webapp.php?action=d_order"),
	/** 修改用户图片 */
	URL_UPDATE_IMAGE(UrlConstants.URL+"App/webapp.php?action=user_image"),
	/** 刷新用户信息 */
	URL_REFRESH_USERINFO(UrlConstants.URL+"App/webapp.php?action=user_sx"),
	;
	
	
	
	
	private String urlAll;

	private EFaceTypeZGQ(String url) {
		this.urlAll = url;
	}

	public String getUrlAll() {
		return urlAll;
	}
}

