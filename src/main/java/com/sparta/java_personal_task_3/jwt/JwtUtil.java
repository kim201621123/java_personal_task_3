package com.sparta.java_personal_task_3.jwt;

import com.sparta.java_personal_task_3.entity.UserRoleEnum;
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
@Slf4j
public class JwtUtil {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    // Token 식별자
    public static final String BEARER_PREFIX = "Bearer ";

    public static final String ACCESS_TOKEN_HEADER = "ACCESS_TOKEN_HEADER";
    public static final String REFRESH_TOKEN_HEADER = "REFRESH_TOKEN_HEADER";

    // Access Token 만료시간 설정
    public final long ACCESS_TOKEN_EXPIRATION =  30 * 60 * 1000L; // 30 * 60 * 1000L; // 30분

    // Refresh Token 만료기간 설정
    public final long REFRESH_TOKEN_EXPIRATION = 24 * 60 * 60 * 1000L; // 24시간

    // 로그 설정
    public static final Logger logger = LoggerFactory.getLogger("JWT 관련 로그");

    @Value("${JWT_SECRET_KEY}")
    private String secret;

    private Key key;

    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(secret);
        key = Keys.hmacShaKeyFor(bytes);
    }

    // 토큰 생성 Access, Refresh Token
    public String createAccessToken(String username, UserRoleEnum role) {

        // 생성시간
        Date date = new Date();

        return BEARER_PREFIX +
                Jwts.builder().setSubject(username)
                        .claim(ACCESS_TOKEN_HEADER, "access")
                        .claim("UserRole", role)
                        .setExpiration(new Date(date.getTime() + ACCESS_TOKEN_EXPIRATION))
                        .setIssuedAt(date)
                        .signWith(signatureAlgorithm, secret)
                        .compact();
    }

    // 토큰 생성 Refresh Token
    public String createRefreshToken() {

        // 생성시간
        Date date = new Date();

        return BEARER_PREFIX +
                Jwts.builder()
                        .claim(REFRESH_TOKEN_HEADER, "refresh")
                        .setExpiration(new Date(date.getTime() + REFRESH_TOKEN_EXPIRATION))
                        .setIssuedAt(date)
                        .signWith(signatureAlgorithm, secret)
                        .compact();
    }

    // JWT 토큰 substring
    public String substringToken(String tokenValue) {
        // 토큰인지 확인
        if(StringUtils.hasText(tokenValue) && tokenValue.startsWith(BEARER_PREFIX)) {
            return tokenValue.substring(7);
        }
        logger.error("Not Found Token");
        throw new NullPointerException("Not Found Token");
    }

    // 헤더에 토큰 전달
    public void addJwtToHeader(HttpServletResponse response, String headerName, String token) {
        response.setHeader(headerName, token);
    }

    // 토큰에서 사용자 정보 가져오기
    public Claims getUserInfoFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    // 사용자에게서 Access 토큰 가져오기
    public String getAccessTokenFromRequest(HttpServletRequest request) {
        return getTokenFromRequest(request,ACCESS_TOKEN_HEADER);
    }

    // 사용자에게서 Refresh 토큰 가져오기
    public String getRefreshTokenFromRequest(HttpServletRequest request) {
        return getTokenFromRequest(request,REFRESH_TOKEN_HEADER);
    }

    // HttpServletRequest 에서 토큰있는지 확인
    public String getTokenFromRequest(HttpServletRequest request, String headerName) {
        String token = request.getHeader(headerName);
        if(token != null && !token.isEmpty()) {
            return token;
        }
        return null;
    }

    // 토큰 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        } catch (SecurityException | MalformedJwtException | SignatureException e) {
            logger.error("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.");
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token, 만료된 JWT token 입니다.");
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.");
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims is empty, 잘못된 JWT 토큰 입니다.");
        }
        return true;
    }
}