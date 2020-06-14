package com.day1.controller;


import com.day1.common.lang.Result;
import com.day1.entity.User;
import com.day1.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author day1
 * @since 2020-06-14
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户相关接口")
public class UserController {

    @Autowired
    UserService userService;

    @RequiresAuthentication
    @GetMapping("/index")
    @ApiOperation("测试查找用户的接口")
    public Object index(){

        return Result.succ(userService.getById(1L));
    }



    @PostMapping("/save")
    @ApiOperation("测试保存用户的接口")
    public Object save(@Validated @RequestBody User user){

        return Result.succ(user);
    }
}
