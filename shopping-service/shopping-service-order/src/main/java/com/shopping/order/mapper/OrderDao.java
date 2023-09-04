package com.shopping.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shopping.order.pojo.entity.OrderEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
}
