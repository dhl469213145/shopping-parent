package com.shopping.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shopping.goods.mapper.TemplateDao;
import com.shopping.goods.service.TemplateService;
import org.springframework.stereotype.Service;
import com.shopping.goods.pojo.entity.TemplateEntity;
import utils.PageUtils;
import utils.Query;

import java.util.Map;


@Service("templateService")
public class TemplateServiceImpl extends ServiceImpl<TemplateDao, TemplateEntity> implements TemplateService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TemplateEntity> page = this.page(
                new Query<TemplateEntity>().getPage(params),
                new QueryWrapper<TemplateEntity>()
        );

        return new PageUtils(page);
    }

}
