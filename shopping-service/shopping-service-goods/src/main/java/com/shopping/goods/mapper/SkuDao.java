package com.shopping.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.shopping.goods.pojo.entity.SkuEntity;

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
