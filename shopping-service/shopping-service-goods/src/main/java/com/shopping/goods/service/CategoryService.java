package com.shopping.goods.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.shopping.goods.pojo.entity.CategoryEntity;
import utils.PageUtils;

import java.util.Map;

/**
 * 商品类目
 *
 * @author dhl
 * @email dinghl@sqqmall.com
 * @date 2020-11-23 15:45:56
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

