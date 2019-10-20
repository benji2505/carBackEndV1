package com.daermann.cardatabase.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static java.util.Collections.emptyList;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationService {

	static final long EXPIRATIONSTIME = 86400000;	//defines expiration time in ms
	static final String SIGNINKEY= "SecretKey";		//algorithm specific signing key to digitally sign the key
	static final String PREFIX="Bearer";			//defines prefix of the token
	
	//add token to authorization header
	public static void addToken(HttpServletResponse response, String username) {
		String JwtToken = Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONSTIME))
				.signWith(SignatureAlgorithm.HS512, SIGNINKEY)
				.compact();
		response.addHeader("Authorization", PREFIX + " " +JwtToken);
		response.addHeader("Access-Control-Expose-Headers", "Authorization");
	}
	
	//get token from authorization header
	public static Authentication getAuthentication(HttpServletRequest request) {
	
		String token = request.getHeader("Authorization");
		if(token!=null) {
			String user= Jwts.parser()
					.setSigningKey(SIGNINKEY)
					.parseClaimsJws(token.replace(PREFIX, ""))
					.getBody()
					.getSubject();
		
			if(user!=null) {
				return new UsernamePasswordAuthenticationToken(user, null, emptyList());
			}
		}
		return null;
		
	}
	
}
