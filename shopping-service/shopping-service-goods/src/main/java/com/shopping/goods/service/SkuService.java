package com.shopping.goods.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.shopping.goods.pojo.entity.SkuEntity;
import utils.PageUtils;

import java.util.Map;

/**
 * 商品表
 *
 * @author dhl
 * @email dinghl@sqqmall.com
 * @date 2020-11-23 15:45:56
 */
public interface SkuService extends IService<SkuEntity> {

    PageUtils queryPage(Map<String, Object> params);

}

