package com.cloud.erp.utils;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * jst token 工具类 用于登陆生成token及解释token
 * 
 * @author LUWEIMIAO1
 * 
 */
public class JWTTokenUtil {

    /** 密钥 */
    private static final String SECURITY_KEY = "895@!$fmty#$%";

    /** 默认token有效时间240分钟 */
    private static final long TIME_LIMIT = 240 * 60 * 1000;
    /** token key */
    private static final String USER_TOKEN_KEY = "userId";
    private static final String USER_TOKEN_Name = "userName";

    private static final String USER_TOKEN_KEY_CURRENT_TIME = "currentSystemMilliTime";

    private static final String USER_TOKEN_KEY_HOSTNAME = "hostName";

    /**
     * 解释jwt token
     * 
     * @param jsonToken
     *            jwt token
     * @return jst Claims对象
     */
    public static Claims parseJWT(String jsonToken) {
        try {
            return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECURITY_KEY))
                .parseClaimsJws(jsonToken).getBody();
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 从 token获取用户id
     * 
     * @param jsonToken
     *            jwt token
     * @return 用户id
     */
    public static Integer getUseridByJWTToken(String jsonToken) {
        Claims claims = parseJWT(jsonToken);
        if (claims == null) {
            return null;
        }
        Integer userid = null;
        Object obj = claims.get(USER_TOKEN_KEY);
        if (obj != null) {
            userid = Integer.valueOf(obj.toString());
        }

        return userid;
    }

    public static String getClaimValueByKey(String jsonToken, String key) {
        Claims claims = parseJWT(jsonToken);
        if (claims == null) {
            return null;
        }
        String value = null;
        Object obj = claims.get(key);
        if (obj != null) {
            value = obj.toString();
        }
        return value;
    }

    /**
     * 生成jwt token
     * 
     * @param userId
     *            用户id或帐号
     * @return
     */
    public static String createJWTToken(String userId) {
        return createJWTToken(userId, TIME_LIMIT);
    }

    /**
     * 生成jwt token
     * 
     * @param userId
     *            用户id或帐号
     * @param timeLimitMillis
     *            有效时间秒
     * @return
     */
    public static String createJWTToken(String userId, long timeLimitMillis) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECURITY_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        // 添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().claim(USER_TOKEN_KEY, userId)// 设置用户id
            .signWith(signatureAlgorithm, signingKey);

        // 生成JWT
        return builder.compact();
    }

    public static String generateToken(Integer userId, String userName) {
        Map<String, Object> map = new HashMap<>(2);
        map.put(USER_TOKEN_KEY, userId);
        map.put(USER_TOKEN_Name, userName);
        map.put(USER_TOKEN_KEY_CURRENT_TIME, System.currentTimeMillis());
        map.put(USER_TOKEN_KEY_HOSTNAME, System.getProperty("HOSTNAME"));
        return createMultiKeysToken(map, TIME_LIMIT);
    }

    private static String createMultiKeysToken(Map<String, Object> map, long timeLimit) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        // 生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECURITY_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder();
        if (map != null) {
            map.forEach((key, value) -> {
                if (value != null) {
                    builder.claim(key, value);
                }
            });
        }
        builder.signWith(signatureAlgorithm, signingKey);
        return builder.compact();
    }

}
