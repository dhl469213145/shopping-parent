package com.shopping.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shopping.goods.mapper.SkuDao;
import com.shopping.goods.pojo.entity.SkuEntity;
import com.shopping.goods.service.SkuService;
import org.springframework.stereotype.Service;
import utils.PageUtils;
import utils.Query;

import java.util.Map;


@Service("skuService")
public class SkuServiceImpl extends ServiceImpl<SkuDao, SkuEntity> implements SkuService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuEntity> page = this.page(
                new Query<SkuEntity>().getPage(params),
                new QueryWrapper<SkuEntity>()
        );

        return new PageUtils(page);
    }


}
