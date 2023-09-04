package com.shopping.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shopping.goods.mapper.SpecDao;
import com.shopping.goods.service.SpecService;
import org.springframework.stereotype.Service;
import com.shopping.goods.pojo.entity.SpecEntity;
import utils.PageUtils;
import utils.Query;

import java.util.Map;


@Service("specService")
public class SpecServiceImpl extends ServiceImpl<SpecDao, SpecEntity> implements SpecService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpecEntity> page = this.page(
                new Query<SpecEntity>().getPage(params),
                new QueryWrapper<SpecEntity>()
        );

        return new PageUtils(page);
    }

}
