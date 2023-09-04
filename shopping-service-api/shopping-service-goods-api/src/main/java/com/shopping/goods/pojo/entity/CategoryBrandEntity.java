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
@TableName("tb_category_brand")
public class CategoryBrandEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 分类ID
     */
    @TableId
    private Integer categoryId;
    /**
     * 品牌ID
     */
    private Integer brandId;

}
