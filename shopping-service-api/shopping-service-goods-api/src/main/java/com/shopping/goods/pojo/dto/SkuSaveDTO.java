package com.shopping.goods.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

}
