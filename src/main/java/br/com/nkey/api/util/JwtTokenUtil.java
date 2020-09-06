package br.com.nkey.api.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil {
	@Value("${jwt.secret}")
    private String JWT_PRIVATE_KEY;

	public String generateToken(String jwtSubject) {
		return Jwts.builder().setSubject(jwtSubject).signWith(SignatureAlgorithm.HS512, TextCodec.BASE64.encode(JWT_PRIVATE_KEY)).compact();
	}

	public Claims parseToken(String jwtToken) {
		return Jwts.parser().setSigningKey(TextCodec.BASE64.encode(JWT_PRIVATE_KEY)).parseClaimsJws(jwtToken).getBody();
	}
}