package com.shopping.goods.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dhl
 * @email dinghl@sqqmall.com
 * @date 2020-11-23 15:45:56
 */
@Data
@TableName("tb_stock_back")
public class StockBackEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @TableId
    private String orderId;
    /**
     * SKU的id
     */
    private String skuId;
    /**
     * 回滚数量
     */
    private Integer num;
    /**
     * 回滚状态
     */
    private String status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 回滚时间
     */
    private Date backTime;

}
