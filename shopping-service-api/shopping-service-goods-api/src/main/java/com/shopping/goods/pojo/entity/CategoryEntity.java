package com.shopping.goods.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品类目
 *
 * @author dhl
 * @email dinghl@sqqmall.com
 * @date 2020-11-23 15:45:56
 */
@Data
@TableName("tb_category")
public class CategoryEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 分类ID
     */
    @TableId
    private Integer id;
    /**
     * 分类名称
     */
    private String name;
    /**
     * 商品数量
     */
    private Integer goodsNum;
    /**
     * 是否显示
     */
    private String isShow;
    /**
     * 是否导航
     */
    private String isMenu;
    /**
     * 排序
     */
    private Integer seq;
    /**
     * 上级ID
     */
    private Integer parentId;
    /**
     * 模板ID
     */
    private Integer templateId;

}
