package com.shopping.order.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author dhl
 * @email ${email}
 * @date 2022-04-24 16:23:13
 */
@Data
@TableName("tb_order")
public class OrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 订单id
	 */
	@TableId(type = IdType.ASSIGN_ID)
	private String id;
	/**
	 * 数量合计
	 */
	private Integer totalNum;
	/**
	 * 金额合计
	 */
	private Integer totalMoney;
	/**
	 * 优惠金额
	 */
	private Integer preMoney;
	/**
	 * 邮费
	 */
	private Integer postFee;
	/**
	 * 实付金额
	 */
	private Integer payMoney;
	/**
	 * 支付类型，1、在线支付、0 货到付款
	 */
	private String payType;
	/**
	 * 订单创建时间
	 */
	private Date createTime;
	/**
	 * 订单更新时间
	 */
	private Date updateTime;
	/**
	 * 付款时间
	 */
	private Date payTime;
	/**
	 * 发货时间
	 */
	private Date consignTime;
	/**
	 * 交易完成时间
	 */
	private Date endTime;
	/**
	 * 交易关闭时间
	 */
	private Date closeTime;
	/**
	 * 物流名称
	 */
	private String shippingName;
	/**
	 * 物流单号
	 */
	private String shippingCode;
	/**
	 * 用户名称
	 */
	private String username;
	/**
	 * 买家留言
	 */
	private String buyerMessage;
	/**
	 * 是否评价
	 */
	private String buyerRate;
	/**
	 * 收货人
	 */
	private String receiverContact;
	/**
	 * 收货人手机
	 */
	private String receiverMobile;
	/**
	 * 收货人地址
	 */
	private String receiverAddress;
	/**
	 * 订单来源：1:web，2：app，3：微信公众号，4：微信小程序  5 H5手机页面
	 */
	private String sourceType;
	/**
	 * 交易流水号
	 */
	private String transactionId;
	/**
	 * 订单状态,0:未完成,1:已完成，2：已退货
	 */
	private String orderStatus;
	/**
	 * 支付状态,0:未支付，1：已支付，2：支付失败
	 */
	private String payStatus;
	/**
	 * 发货状态,0:未发货，1：已发货，2：已收货
	 */
	private String consignStatus;
	/**
	 * 是否删除
	 */
	private String isDelete;

}
