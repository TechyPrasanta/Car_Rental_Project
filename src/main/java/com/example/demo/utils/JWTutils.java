/*
 * package com.example.demo.utils;
 * 
 * import java.util.Date; import java.util.HashMap; import java.util.Map; import
 * java.util.function.Function;
 * 
 * import org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.stereotype.Component;
 * 
 * import io.jsonwebtoken.Claims; import io.jsonwebtoken.Jwts; import
 * io.jsonwebtoken.SignatureAlgorithm;
 * 
 * @Component public class JWTutils {
 * 
 * public String extractUserName(String token) { return
 * extractClaim(token,Claims::getSubject); }
 * 
 * public String generateToken(UserDetails userDetails) { return
 * generateToken(new HashMap<>(),userDetails); }
 * 
 * public boolean isTokenValid(String token,UserDetails userDetails) { final
 * String UserName = extractUserName(token); return
 * (UserName.equals(UserDetails.getUsername()))&& !isTokenExpired(token); }
 * private<T> T extractClaim(String token, Function<Claims,T>claimsResolvers) {
 * final Claims claims = extractAllClaims(token); return
 * claimsResolvers.apply(claims); } public String
 * generateToken(Map<String,Object>extraClaims, UserDetails userDetails) {
 * return
 * Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
 * .setIssuedAt(new Date(System.currentTimeMillis())) .setExpiration(new
 * Date(System.currentTimeMillis()+1000*60*24))
 * .signWith(getSigningKey(),SignatureAlgorithm.HS256).compact(); }
 * 
 * }
 */