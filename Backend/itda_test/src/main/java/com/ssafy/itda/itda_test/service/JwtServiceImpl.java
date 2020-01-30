package com.ssafy.itda.itda_test.service;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ssafy.itda.itda_test.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtServiceImpl {
	public static final Logger log = LoggerFactory.getLogger(JwtServiceImpl.class);

	@Value("${jwt.salt}")
	private String salt;

	@Value("${jwt.expmin}")
	private long expireMin;

	public String create(final User user) {
		log.trace("time: {}", expireMin);
		final JwtBuilder builder = Jwts.builder();
		builder.setHeaderParam("typ", "JWT");
		builder.setSubject("로그인토큰").setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * expireMin))
				.claim("User", user).claim("uid", user.getUid()).claim("uname", user.getUname())
				.claim("email", user.getEmail()).claim("auth", user.getAuth());
		builder.signWith(SignatureAlgorithm.HS256, salt.getBytes());

		final String jwt = builder.compact();
		log.debug("토큰 발행 : {}", jwt);
		return jwt;
	}

	public void checkValid(final String jwt) {
		log.trace("토큰 점검 : {}", jwt);
		Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
	}

	public Map<String, Object> get(final String jwt) {
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
		} catch (final Exception e) {
			throw new RuntimeException();
		}

		log.trace("claims : {}", claims);
		return claims.getBody();
	}

}