package com.shopping.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shopping.goods.mapper.ParaDao;
import com.shopping.goods.service.ParaService;
import org.springframework.stereotype.Service;
import com.shopping.goods.pojo.entity.ParaEntity;
import utils.PageUtils;
import utils.Query;

import java.util.Map;


@Service("paraService")
public class ParaServiceImpl extends ServiceImpl<ParaDao, ParaEntity> implements ParaService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ParaEntity> page = this.page(
                new Query<ParaEntity>().getPage(params),
                new QueryWrapper<ParaEntity>()
        );

        return new PageUtils(page);
    }

}
