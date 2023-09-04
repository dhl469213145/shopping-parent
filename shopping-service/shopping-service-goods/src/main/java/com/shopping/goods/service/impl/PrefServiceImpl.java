package com.shopping.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shopping.goods.mapper.PrefDao;
import com.shopping.goods.service.PrefService;
import org.springframework.stereotype.Service;
import com.shopping.goods.pojo.entity.PrefEntity;
import utils.PageUtils;
import utils.Query;

import java.util.Map;


@Service("prefService")
public class PrefServiceImpl extends ServiceImpl<PrefDao, PrefEntity> implements PrefService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PrefEntity> page = this.page(
                new Query<PrefEntity>().getPage(params),
                new QueryWrapper<PrefEntity>()
        );

        return new PageUtils(page);
    }

}
