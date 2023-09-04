package com.shopping.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import pojo.entity.UserEntity;
import utils.PageUtils;

import java.util.Map;

/**
 * 用户表
 *
 * @author dhl
 * @email dinghl@sqqmall.com
 * @date 2020-12-01 12:02:07
 */
public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

