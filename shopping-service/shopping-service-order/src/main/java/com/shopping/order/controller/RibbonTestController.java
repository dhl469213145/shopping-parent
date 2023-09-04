package com.shopping.order.controller;/*
package com.changgou.order.controller;

import api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("order/ribbonTest")
public class RibbonTestController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("infoSku/{id}")
    public R infoSku(@PathVariable("id") Long id) {
        return R.data(restTemplate.getForEntity("http://goods/changgou/sku/info/" + id, String.class));
    }
}
*/
