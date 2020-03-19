package com.huyong.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import java.security.Key;
import java.util.Date;
import java.util.Set;

/**
 * 描述: jwt工具类
 *
 * @author huyong
 * @date 2020-03-12 4:00 下午
 */
public class JwtHelper {

    private static final String  BASE_64_SECURITY= "MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY=";
    /**
     * 一周有效时间
     */
    private static final long EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;

    /**
     * 解析jwt
     */
    public static Claims parseJWT(String jsonWebToken, String base64Security){
        try {
            return Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken).getBody();
        } catch(Exception ex) {
            return null;
        }
    }

    public static String createJWT(Long id, String name, String email, int role) {
        return createJWT(id, name, email, role, EXPIRE_TIME, BASE_64_SECURITY);
    }

    /**
     * 构建jwt
     */
    private static String createJWT(Long id, String name, String email, int role, long TTLMillis, String base64Security) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .claim("role", role)
                .claim("userName", name)
                .claim("email", email)
                .claim("id", id)
                .signWith(signatureAlgorithm, signingKey);
        //添加Token过期时间
        if (TTLMillis >= 0) {
            long expMillis = nowMillis + TTLMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }
        //生成JWT
        return builder.compact();
    }
}

