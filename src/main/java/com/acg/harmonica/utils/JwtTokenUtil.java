package com.acg.harmonica.utils;


import com.acg.harmonica.entity.CentreUser;
import io.jsonwebtoken.*;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lweijian
 */
public class JwtTokenUtil {
    private static final String SECRET = "$2a$10$eD.oly3neLqy/hrn51j/6eXc8bspuKk8yRoOjaNN4pIlC/c91bVmm";

    private static final int VALID_DATE = 1;

    /**
     * 默认有效时间是一天
     *
     * @param map
     * @return token
     */
    private static String generateToken(Map<String, Object> map) {

        Date expirationDate = DateUtils.addDayOfDate(new Date(), VALID_DATE);
        return Jwts.builder()
                .setClaims(map)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    /**
     * 自定义有效天数
     *
     * @param map
     * @param day
     * @return token
     */
    private static String generateToken(Map<String, Object> map, int day) {
        Date expirationDate = DateUtils.addDayOfDate(new Date(), day);
        return Jwts.builder()
                .setClaims(map)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }


    /**
     * 默认有效时间是一天
     *
     * @param centreUser
     * @return token
     */
    public static String generateToken(CentreUser centreUser) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", centreUser.getRoleName());
        map.put(Claims.ISSUED_AT, new Date());
        return generateToken(map);
    }

    /**
     * 自定义有效天数
     *
     * @param centreUser
     * @param day
     * @return token
     */
    public static String generateToken(CentreUser centreUser, int day) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", centreUser.getRoleName());
        map.put(Claims.ISSUED_AT, new Date());
        return generateToken(map, day);
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    public static Claims getClaimsFromToken(String token) throws Exception {
        Claims claims = null;
        claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        return claims;
    }



    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    public static String getUsernameFormToken(String token) throws Exception {

        return (String) getClaimsFromToken(token).get("username");
    }

    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    public static Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return true;
        }

    }

    /**
     * 验证令牌
     * 判断是否是同一个用户  判断令牌是否过期
     *
     * @param token      令牌
     * @param centreUser 角色
     * @return 是否有效
     */
    public static ReturnObj validateToken(String token, CentreUser centreUser) {
        ReturnObj ret = new ReturnObj();
        try {
            String tokenUserName = (String) getClaimsFromToken(token).get("username");
            if (tokenUserName.equals(centreUser.getUsername()) && !isTokenExpired(token)) {
                ret.setMsg(ReturnCode.OK);
            } else {
                ret.setMsg(ReturnCode.ERROR_TOKEN);
            }

        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            ret.setMsg(ReturnCode.OUT_TIME_TOKEN);
        } catch (Exception e) {
            e.printStackTrace();
            ret.setMsg(ReturnCode.ERROR_TOKEN);
        }
        return ret;
    }

    /**
     * 刷新令牌
     *
     * @param refreshToken 刷新令牌
     * @return 新令牌
     */
    public static Map refreshToken(String refreshToken) {
        String accessToken;
        try {
            Claims claims = getClaimsFromToken(refreshToken);
            claims.put(Claims.ISSUED_AT, new Date());
            accessToken = generateToken(claims);
            refreshToken = generateToken(claims,7);
        } catch (Exception e) {
            accessToken = null;
            refreshToken=null;
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<>(2);
        map.put("accessToken",accessToken);
        map.put("refreshToken",refreshToken);
        return map;
    }
}
