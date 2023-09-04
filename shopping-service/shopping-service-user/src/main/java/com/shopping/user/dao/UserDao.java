package com.shopping.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pojo.entity.UserEntity;

/**
 * 用户表
 *
 * @author dhl
 * @email dinghl@sqqmall.com
 * @date 2020-12-01 12:02:07
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

}
