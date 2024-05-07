package com.itheima.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {

    private static String signKey = "secretbucket911";
    private static Long expire = 43200000L;

    /**
     * 生成jwt令牌
     * @param claims
     * @return
     */
    public static String generateJwt(Map<String,Object> claims){
        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, signKey)//签名算法
                .addClaims(claims)//有效载荷
                .setExpiration(new Date(System.currentTimeMillis() + expire))//有效时间
                .compact();
        return jwt;
    }

    /**
     * 解析jwt令牌
     * @param jwt
     * @return
     */
    public static Claims parseJWT(String jwt){
        Claims claims = Jwts.parser().setSigningKey(signKey).parseClaimsJws(jwt).getBody();
        return claims;
    }
}
