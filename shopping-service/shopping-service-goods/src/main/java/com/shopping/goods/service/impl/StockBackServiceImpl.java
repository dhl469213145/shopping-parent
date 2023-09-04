package com.shopping.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shopping.goods.mapper.StockBackDao;
import com.shopping.goods.service.StockBackService;
import org.springframework.stereotype.Service;
import com.shopping.goods.pojo.entity.StockBackEntity;
import utils.PageUtils;
import utils.Query;

import java.util.Map;


@Service("stockBackService")
public class StockBackServiceImpl extends ServiceImpl<StockBackDao, StockBackEntity> implements StockBackService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<StockBackEntity> page = this.page(
                new Query<StockBackEntity>().getPage(params),
                new QueryWrapper<StockBackEntity>()
        );

        return new PageUtils(page);
    }

}
