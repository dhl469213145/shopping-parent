package com.shopping.order.feign;

import api.R;
import com.shopping.order.pojo.dto.SkuSaveDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import utils.PageUtils;

import java.util.Map;

@FeignClient(value = "goods")
@RequestMapping("goods/sku")
public interface SkuFeign {

    @GetMapping("/info/{id}")
    R info(@PathVariable("id") Long id);

    @PostMapping("/save")
    R saveOrder(@RequestBody SkuSaveDTO skuSaveDTO);

    @PostMapping("/list")
    R list(@ApiIgnore @RequestBody(required = false) Map<String, Object> params);
}
