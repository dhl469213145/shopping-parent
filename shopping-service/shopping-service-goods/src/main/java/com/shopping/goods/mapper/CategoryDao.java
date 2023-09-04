package com.shopping.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.shopping.goods.pojo.entity.CategoryEntity;

/**
 * 商品类目
 *
 * @author dhl
 * @email dinghl@sqqmall.com
 * @date 2020-11-23 15:45:56
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {

}
