package com.shopping.goods.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.shopping.goods.pojo.entity.PrefEntity;
import utils.PageUtils;

import java.util.Map;

/**
 * @author dhl
 * @email dinghl@sqqmall.com
 * @date 2020-11-23 15:45:56
 */
public interface PrefService extends IService<PrefEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

