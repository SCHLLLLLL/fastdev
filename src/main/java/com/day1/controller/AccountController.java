package com.day1.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.day1.common.dto.LoginDto;
import com.day1.common.dto.RegisterDto;
import com.day1.common.lang.Result;
import com.day1.entity.User;
import com.day1.service.UserService;
import com.day1.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: Day1
 * @Date: 2020/6/14 16:59
 * @Description:
 */
@RestController
@Api(tags = "用户登录注册接口")
public class AccountController {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtil jwtUtil;

    @ApiOperation("用户登录接口")
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response){
        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
        Assert.notNull(user,"用户不存在");

        if (!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))){
            return Result.fail("密码不正确");
        }

        String jwt = jwtUtil.generateToken(user.getId());

        response.setHeader("Authorization",jwt);
        response.setHeader("Access-control-Expose-Headers","Authorization");
        return Result.succ(MapUtil.builder()
                .put("id",user.getId())
                .put("username",user.getUsername())
                .put("email",user.getEmail())
                .put("avatar",user.getAvatar())
                .map()
        );
    }

    @ApiOperation("用户注销接口")
    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout(){
        SecurityUtils.getSubject().logout();
        return Result.succ(null);
    }

    @ApiOperation("用户注册接口")
    @PostMapping("/register")
    public Result register(@Validated @RequestBody RegisterDto registerDto){
        User user = userService.getOne(new QueryWrapper<User>().eq("username", registerDto.getUsername()));
        Assert.isNull(user,"用户名已存在");

        User u = new User();
        BeanUtils.copyProperties(registerDto,u);
        u.setPassword(SecureUtil.md5(registerDto.getPassword()));
        u.setStatus(0);
        userService.save(u);

        return Result.succ("注册成功");
    }
}
