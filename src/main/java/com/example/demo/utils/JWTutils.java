
  package com.example.demo.utils;
  
  import io.jsonwebtoken.Claims;
  import io.jsonwebtoken.Jwts;
  import io.jsonwebtoken.security.Keys;
  import org.springframework.stereotype.Component;
  import org.springframework.security.core.userdetails.UserDetails;

  import java.security.Key;
  import java.util.Date;
  import java.util.HashMap;
  import java.util.Map;
  import java.util.function.Function;
  
  @Component
  public class JWTutils {

      private static final long JWT_TOKEN_VALIDITY = 1000 * 60 * 60 * 24; // 1 day in milliseconds

      // Generate JWT token
      public String generateToken(UserDetails userDetails) {
          Map<String, Object> claims = new HashMap<>();
          return Jwts.builder()
                  .setClaims(claims)
                  .setSubject(userDetails.getUsername())
                  .setIssuedAt(new Date(System.currentTimeMillis()))
                  .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                  .signWith(getSigningKey())
                  .compact();
      }

      // Validate JWT token
      public boolean isTokenValid(String token, UserDetails userDetails) {
          final String username = extractUsername(token);
          return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
      }

      public String extractUsername(String token) {
          return extractClaim(token, Claims::getSubject);
      }

	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
          final Claims claims = Jwts.parser().setSigningKey(getSigningKey()).parseClaimsJws(token).getBody();
          return claimsResolver.apply(claims);
      }

      private boolean isTokenExpired(String token) {
          final Date expiration = extractExpiration(token);
          return expiration.before(new Date());
      }

      private Date extractExpiration(String token) {
          return extractClaim(token, Claims::getExpiration);
      }

      private Key getSigningKey() {
          // Generate a secure key guaranteed to be secure enough for HMAC-SHA algorithm
          return Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
      }
  }