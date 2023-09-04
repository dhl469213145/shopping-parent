package com.shopping.goods.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import utils.PageUtils;

import java.util.Map;

@FeignClient(value = "goods")
@RequestMapping("goods/sku")
public interface SkuFeign {

    @PostMapping("/list")
    PageUtils list(@ApiIgnore @RequestBody(required = false) Map<String, Object> params);

}
