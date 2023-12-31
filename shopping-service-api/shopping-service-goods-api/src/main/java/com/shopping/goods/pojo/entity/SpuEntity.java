package com.shopping.goods.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author dhl
 * @email dinghl@sqqmall.com
 * @date 2020-11-23 15:45:56
 */
@Data
@TableName("tb_spu")
public class SpuEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 货号
     */
    private String sn;
    /**
     * SPU名
     */
    private String name;
    /**
     * 副标题
     */
    private String caption;
    /**
     * 品牌ID
     */
    private Integer brandId;
    /**
     * 一级分类
     */
    private Integer category1Id;
    /**
     * 二级分类
     */
    private Integer category2Id;
    /**
     * 三级分类
     */
    private Integer category3Id;
    /**
     * 模板ID
     */
    private Integer templateId;
    /**
     * 运费模板id
     */
    private Integer freightId;
    /**
     * 图片
     */
    private String image;
    /**
     * 图片列表
     */
    private String images;
    /**
     * 售后服务
     */
    private String saleService;
    /**
     * 介绍
     */
    private String introduction;
    /**
     * 规格列表
     */
    private String specItems;
    /**
     * 参数列表
     */
    private String paraItems;
    /**
     * 销量
     */
    private Integer saleNum;
    /**
     * 评论数
     */
    private Integer commentNum;
    /**
     * 是否上架,0已下架，1已上架
     */
    private String isMarketable;
    /**
     * 是否启用规格
     */
    private String isEnableSpec;
    /**
     * 是否删除,0:未删除，1：已删除
     */
    private String isDelete;
    /**
     * 审核状态，0：未审核，1：已审核，2：审核不通过
     */
    private String status;

}
