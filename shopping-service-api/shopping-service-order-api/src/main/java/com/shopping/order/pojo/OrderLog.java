package com.shopping.order.pojo;

import java.io.Serializable;
import java.util.Date;

public class OrderLog implements Serializable{

	private String id;//ID

	private String operater;//操作员

	private Date operateTime;//操作时间

	private String orderId;//订单ID

	private String orderStatus;//订单状态,0未完成，1已完成，2，已退货

	private String payStatus;//付款状态

	private String consignStatus;//发货状态

	private String remarks;//备注

	private Integer money;//支付金额

	private String username;//



}
