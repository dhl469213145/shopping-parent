/*
package com.shopping.order.controller;

import api.R;
import com.shopping.order.service.SkuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import utils.DBConstant;
import utils.PageUtils;
import utils.ValidatorUtils;

import java.util.Arrays;
import java.util.Map;


*/
/**
 * 商品表
 *
 * @author dhl
 * @email dinghl@sqqmall.com
 * @date 2020-11-23 15:45:56
 *//*

@RestController
@CrossOrigin
@RequestMapping("goods/sku")
@Api(value = "", description = "sku业务相关接口", tags = {"sku业务相关接口"})
public class SkuController {
    @Autowired
    private SkuService skuService;

    */
/**
     * 列表
     *//*

    @PostMapping("/list")
    @ApiOperation(value = "sku列表查询", notes = "sku列表查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = DBConstant.PAGE, value = "当前页", required = true, dataType = "int"),
            @ApiImplicitParam(name = DBConstant.LIMIT, value = "显示条目", required = true, dataType = "int"),
    })
    public R list(@ApiIgnore @RequestBody(required = false) Map<String, Object> params) {
        PageUtils page = skuService.queryPage(params);
        return R.data(page);
    }




}
*/
