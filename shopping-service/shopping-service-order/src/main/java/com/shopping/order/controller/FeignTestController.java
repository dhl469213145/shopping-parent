package com.shopping.order.controller;

import api.R;
import com.shopping.order.feign.SkuFeign;
import com.shopping.order.pojo.dto.SkuSaveDTO;
import com.shopping.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("order/feignTest")
public class FeignTestController extends BaseController {
    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private OrderService orderService;

    @GetMapping("login")
    public R login() {
        getHttpSession().setAttribute("member", "dd");
        return R.success("success");
    }

    @GetMapping("infoSku/{id}")
    public R infoSku(@PathVariable("id") Long id) {

        return R.data(skuFeign.info(id));
    }

    @PostMapping ("listSku")
    public R listSku(@ApiIgnore @RequestBody(required = false) Map<String, Object> params) {
        return skuFeign.list(params);
    }

    @PostMapping ("saveOrderFeign")
    public R saveOrderFeign(@Valid @RequestBody SkuSaveDTO skuSaveDTO) {
        orderService.saveOrderFeign();
        return R.success("ok");
    }

    @PostMapping ("saveProductFeign")
    public R saveProductFeign() {
        orderService.saveProductFeign();
        return R.success("ok");
    }

    @PostMapping ("distributeTx")
    public R distributeTx() {
        orderService.distributeTx();
        return R.success("ok");
    }
}
