package com.shopping.order.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 商品表
 *
 * @author dhl
 * @email dinghl@sqqmall.com
 * @date 2020-11-23 15:45:56
 */
@Data
@ApiModel
public class SkuSaveDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private String id;

    /**
     * 商品条码
     */
    @NotBlank(message = "商品条码不能为空")
    @ApiModelProperty(value = "商品条码", required = true)
    private String sn;
    /**
     * SKU名称
     */
    @NotBlank(message = "SKU名称不能为空")
    @ApiModelProperty(value = "SKU名称", required = true)
    private String name;
    /**
     * 价格（分）
     */
    @NotNull(message = "价格不能为空")
    @ApiModelProperty(value = "价格（分）", required = true)
    private Integer price;
    /**
     * 库存数量
     */
    @ApiModelProperty(
            value = "库存数量",
            required = true
    )
    private Integer num;
    /**
     * 库存预警数量
     */
    @ApiModelProperty(
            value = "库存预警数量",
            required = false
    )
    private Integer alertNum;
    /**
     * 商品图片
     */
    @ApiModelProperty(
            value = "商品图片",
            required = false
    )
    private String image;
    /**
     * 商品图片列表
     */
    @ApiModelProperty(
            value = "商品图片列表",
            required = false
    )
    private String images;
    /**
     * 重量（克）
     */
    @ApiModelProperty(
            value = "重量（克）",
            required = false
    )
    private Integer weight;
    /**
     * SPUID
     */
    @ApiModelProperty(
            value = "SPUID",
            required = false
    )
    private Long spuId;
    /**
     * 类目ID
     */
    @ApiModelProperty(
            value = "类目ID",
            required = false
    )
    private Integer categoryId;
    /**
     * 类目名称
     */
    @ApiModelProperty(
            value = "类目名称",
            required = false
    )
    private String categoryName;
    /**
     * 品牌名称
     */
    @ApiModelProperty(
            value = "品牌名称",
            required = false
    )
    private String brandName;
    /**
     * 规格
     */
    @ApiModelProperty(
            value = "规格",
            required = false
    )
    private String spec;
    /**
     * 销量
     */
    @ApiModelProperty(
            value = "销量",
            required = false
    )
    private Integer saleNum;
    /**
     * 评论数
     */
    @ApiModelProperty(
            value = "评论数",
            required = false
    )
    private Integer commentNum;
    /**
     * 商品状态 1-正常，2-下架，3-删除
     */
    @ApiModelProperty(
            value = "商品状态 1-正常，2-下架，3-删除",
            required = false
    )
    private String status;


//    @NotBlank(message = "电话号码不允许为空")
//    @Length(max = 11,min = 11,message = "电话必须是11字符")
//    @Pattern(regexp = "^1[3|4|5|8][0-9]\\d{8}$",message = "电话号码格式不正确")
//    private String phone;
//
//    @NotBlank(message = "动态校验码不允许为空")
//    @Length(max = 6,min = 6,message = "校验码必须是6字符")
//    private String otpCode;
//
//    @NotBlank(message = "用户名不允许为空")
//    @Length(max = 20,min = 4,message = "用户名长度必须在4-20字符之间")
//    private String username;
//
//    @NotBlank(message = "密码不允许为空")
//    @Length(max = 20,min = 8,message = "密码长度必须在8-20字符之间")
//    private String password;
}
