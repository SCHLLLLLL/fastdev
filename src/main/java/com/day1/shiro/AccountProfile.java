package com.day1.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Day1
 * @Date: 2020/6/14 16:19
 * @Description:
 */
@Data
public class AccountProfile implements Serializable {

    private Long id;
    private String username;
    private String avatar;
    private String email;
}
