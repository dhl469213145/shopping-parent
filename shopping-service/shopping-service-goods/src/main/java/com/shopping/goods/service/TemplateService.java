package com.shopping.goods.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.shopping.goods.pojo.entity.TemplateEntity;
import utils.PageUtils;

import java.util.Map;

/**
 * @author dhl
 * @email dinghl@sqqmall.com
 * @date 2020-11-23 15:45:56
 */
public interface TemplateService extends IService<TemplateEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

