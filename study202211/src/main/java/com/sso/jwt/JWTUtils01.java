package com.sso.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author admin
 *  原文链接：https://blog.csdn.net/Top_L398/article/details/109361680
 */
public class JWTUtils01 {

    private static String SIGNATURE = "token!@#$%^7890";

    /**
     * 生成token
     * @param map //传入payload
     * @return 返回token
     */
    public static String getToken(Map<String,String> map){
        JWTCreator.Builder builder = JWT.create();
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND,7);
        builder.withExpiresAt(instance.getTime());
        return builder.sign(Algorithm.HMAC256(SIGNATURE)).toString();
    }

    /**
     * 验证token
     * @param token
     */
    public static void verify(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGNATURE)).build().verify(token);
    }

    /**
     * 获取token中payload
     * @param token
     * @return
     */
    public static DecodedJWT getToken(String token){
        return JWT.require(Algorithm.HMAC256(SIGNATURE)).build().verify(token);
    }


    private static void extracted() {
        Map<String,String> user = new HashMap<>();
        user.put("userId","u001");
        user.put("username","zhangsan");
        String token = JWTUtils01.getToken(user);
        System.out.println("token==="+token);

        DecodedJWT jwt = JWTUtils01.getToken(token);
        System.out.println("jwt.getPayload:" + jwt.getPayload());
        String userId = jwt.getClaim("userId").asString();
        System.out.println("userId==" + userId);
        System.out.println("===================");
        String token1 = token + "aaa";
        DecodedJWT jwt1 = JWTUtils01.getToken(token1);
        System.out.println("jwt1.getPayload:" + jwt1.getPayload());
    }
    
    public static void m1(){
        HashMap<String,Object> map = new HashMap<>();
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND,20);
        Date time = instance.getTime();
        String token = JWT.create()
                .withHeader(map) //可以不设定，就是使用默认的
                .withClaim("userId",20)//payload  //自定义用户名
                .withClaim("username","zhangsan")
                .withExpiresAt(time) //指定令牌过期时间
                .sign(Algorithm.HMAC256(SIGNATURE));//签名
        System.out.println("token==" + token);
        System.out.println("=====================");
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SIGNATURE)).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        Integer userId = decodedJWT.getClaim("userId").asInt();//获取负载里面对应的内容
        String username = decodedJWT.getClaim("username").asString();
        Date expiresAt = decodedJWT.getExpiresAt();//获取过期时间
        System.out.println("userId=" + userId );
        System.out.println("username=" + username );
        System.out.println("expiresAt=" + expiresAt );

    }


    public static void main(String[] args) {
        extracted();
//        m1();
    }

}

