package com.shopping.user.controller;

import api.R;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shopping.user.config.properties.JwtProperties;
import com.shopping.user.service.UserService;
import com.shopping.user.util.JwtUtil;
import error.RRException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import pojo.entity.UserEntity;
import utils.BCrypt;
import utils.PageUtils;
import utils.ValidatorUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * 用户表
 *
 * @author dhl
 * @email dinghl@sqqmall.com
 * @date 2020-12-01 12:02:07
 */
@Slf4j
@RestController
@RequestMapping("rechargeable/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = userService.queryPage(params);

        return R.data(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{username}")
    public R info(@PathVariable("username") String username) {
        UserEntity user = userService.getById(username);

        return R.data(user);
    }

    @ApiOperation("正常登录-普通，spring session")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "username", name = "账号", required = true, dataType = "string"),
            @ApiImplicitParam(value = "password", name = "密码", required = true, dataType = "string")
    })
    @GetMapping("/normorLogin")
    public R normorLogin(@RequestParam String username, @RequestParam String password) {
        // 查找该用户
        UserEntity userEntity = userService.getOne(new QueryWrapper<UserEntity>().eq("username", username));
        if (ObjectUtils.isEmpty(userEntity)) {
            log.error("登录失败，未找到用户信息！[username]={};[password]={}", username);
            throw new RRException("登录失败，用户名或密码错误！");
        }

        // 密码校验
        if (!BCrypt.checkpw(password, userEntity.getPassword())) {
            log.warn("登录失败，用户名或密码错误！");
            return R.fail("登录失败，用户名或密码错误！");
        }

        getHttpSession().setAttribute("member", JSON.toJSONString(userEntity));
        return R.data(userEntity);
    }

    @ApiOperation("登录-jwt")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "username", name = "账号", required = true, dataType = "string"),
            @ApiImplicitParam(value = "password", name = "密码", required = true, dataType = "string")
    })
    @GetMapping("/jwtLogin")
    public R jwtLogin(@RequestParam String username, @RequestParam String password) {
        // 查找该用户
        UserEntity userEntity = userService.getOne(new QueryWrapper<UserEntity>().eq("username", username));
        if (ObjectUtils.isEmpty(userEntity)) {
            log.error("登录失败，未找到用户信息！[username]={};[password]={}", username);
            throw new RRException("登录失败，用户名或密码错误！");
        }

        // 密码校验
        if (!BCrypt.checkpw(password, userEntity.getPassword())) {
            log.warn("登录失败，用户名或密码错误！");
            return R.fail("登录失败，用户名或密码错误！");
        }

        Map<String,String> map = new HashMap<>();
        map.put("token", jwtUtil.createJWT(username, JSON.toJSONString(userEntity), jwtProperties.getExpiration()));
        map.put("tokenHead", jwtProperties.getTokenHead());

        return R.data(map);
    }

    @ApiOperation("登录")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "username", name = "账号", required = true, dataType = "string"),
            @ApiImplicitParam(value = "password", name = "密码", required = true, dataType = "string")
    })
    @GetMapping("/login")
    public R login(HttpServletResponse response, String username, String password) {
        // 查找该用户
        UserEntity userEntity = userService.getOne(new QueryWrapper<UserEntity>().eq("username", username));
        if (ObjectUtils.isEmpty(userEntity)) {
            log.error("登录失败，未找到用户信息！[username]={};[password]={}", username);
            throw new RRException("登录失败，用户名或密码错误！");
        }

        // 密码校验
        if (!BCrypt.checkpw(password, userEntity.getPassword())) {
            log.warn("登录失败，用户名或密码错误！");
            return R.fail("登录失败，用户名或密码错误！");
        }

        // jwt创建token
        Map<String, Object> infoMap = new HashMap<>(2);
        infoMap.put("username", username);
        infoMap.put("role", "USER");
        String token = jwtUtil.createJWT(UUID.randomUUID().toString(), JSON.toJSONString(infoMap), null);

        Cookie cookie = new Cookie("Authorization", token);
        response.addCookie(cookie);

        response.setHeader("Authorization", token);

        return R.data(token);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody UserEntity user) {
        userService.save(user);

        return R.success("ok");
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody UserEntity user) {
        ValidatorUtils.validateEntity(user);
        userService.updateById(user);

        return R.success("ok");
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] usernames) {
        userService.removeByIds(Arrays.asList(usernames));

        return R.success("ok");
    }

}
