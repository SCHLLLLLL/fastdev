package com.day1.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author: Day1
 * @Date: 2020/6/14 15:51
 * @Description:
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String jwt){
        this.token = jwt;
    }
    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
