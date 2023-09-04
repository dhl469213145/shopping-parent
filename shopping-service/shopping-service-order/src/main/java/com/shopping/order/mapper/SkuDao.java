package com.shopping.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shopping.order.pojo.entity.SkuEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品表
 *
 * @author dhl
 * @email dinghl@sqqmall.com
 * @date 2020-11-23 15:45:56
 */
@Mapper
public interface SkuDao extends BaseMapper<SkuEntity> {

}
