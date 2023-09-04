package com.shopping.user.util;

import com.shopping.user.config.properties.JwtProperties;
import error.RRException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

/**
 * 描述
 *
 * @author www.itheima.com
 * @version 1.0
 * @package pojo.entity *
 * @since 1.0
 */
@Component
public class JwtUtil {
    //有效期为
//    public static final Long JWT_TTL = 3600000L;// 60 * 60 *1000  一个小时

    //Jwt令牌信息
//    public static final String JWT_KEY = "itcast";

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 生成令牌
     *
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     */
    public String createJWT(String id, String subject, Long ttlMillis) {
        //指定算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        //当前系统时间
        long nowMillis = System.currentTimeMillis();
        //令牌签发时间
        Date now = new Date(nowMillis);

        //如果令牌有效期为null，则默认设置有效期1小时
        if (ttlMillis == null) {
            ttlMillis = jwtProperties.getExpiration();
        }

        //令牌过期时间设置
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);

        //生成秘钥
        SecretKey secretKey = generalKey();

        //封装Jwt令牌信息
        JwtBuilder builder = Jwts.builder()
                .setId(id)                    //唯一的ID
                .setSubject(subject)          // 主题  可以是JSON数据
                .setIssuer("admin")          // 签发者
                .setIssuedAt(now)             // 签发时间
                .signWith(signatureAlgorithm, secretKey) // 签名算法以及密匙
                .setExpiration(expDate);      // 设置过期时间
        return builder.compact();
    }

    /**
     * 生成加密 secretKey
     *
     * @return
     */
    public SecretKey generalKey() {
        byte[] encodedKey = Base64.getEncoder().encode(jwtProperties.getSecret().getBytes());
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }


    /**
     * 解析令牌数据
     *
     * @param jwtToken
     * @return
     * @throws RRException
     */
    public Claims parseJWT(String jwtToken) throws RRException {
        Claims claims = null;
        try {
            claims=Jwts.parser()
                    .setSigningKey(generalKey())
                    .parseClaimsJws(jwtToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new RRException("JWT验证失败:token已经过期");
        } catch (UnsupportedJwtException e) {
            throw new RRException("JWT验证失败:token格式不支持");
        } catch (MalformedJwtException e) {
            throw new RRException("JWT验证失败:无效的token");
        } catch (SignatureException e) {
            throw new RRException("JWT验证失败:无效的token");
        } catch (IllegalArgumentException e) {
            throw new RRException("JWT验证失败:无效的token");
        }
        return claims;
    }

    public static void main(String[] args) {
        JwtUtil jwtUtil = new JwtUtil();
        String jwt = jwtUtil.createJWT("weiyibiaoshi", "aaaaaa", null);
        System.out.println(jwt);
        try {
            Claims claims = jwtUtil.parseJWT(jwt);
            System.out.println(claims);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
