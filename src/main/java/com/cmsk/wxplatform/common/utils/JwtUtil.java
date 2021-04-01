package com.cmsk.wxplatform.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhanglgstart
 * @create 2021-03-08 10:40
 */
public class JwtUtil {
    private JwtUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean verify(String token, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public static String getStringFromTokenByKey(String token, String key) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim(key).asString();
    }

    public static Long getLongFromTokenByKey(String token, String key) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim(key).as(Long.class);
    }

    /**
     * 生成Token
     * @return
     * @throws Exception
     */
    public static String createJwtToken(Map<String,Object> paramMap,String secret) throws Exception {
        /*Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.SECOND, EXPIRE);
        Date expireDate = nowTime.getTime();*/
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        String token = JWT.create()
                .withHeader(map)//头
                .withClaim(Constant.ISS, (String) paramMap.get("iss"))
                .withClaim(Constant.EXP, (Long) paramMap.get("exp"))
                /*.withIssuedAt(new Date())//签名时间
                .withExpiresAt(expireDate)//过期时间*/
                .sign(Algorithm.HMAC256(secret));//签名
        return Constant.BEARER + Constant.BLACK + token;
    }
}
