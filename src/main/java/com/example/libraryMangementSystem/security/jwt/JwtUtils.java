package com.example.libraryMangementSystem.security.jwt;

import com.example.libraryMangementSystem.security.UserAppDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtils {

    @Value("${auth.token.jwtkey}")
    private String jwtSecret;
    @Value("${auth.token.expiration}")
    private int expirationTime;


    public String generateToken(Authentication authentication){
        UserAppDetails userPrincipal = (UserAppDetails) authentication.getPrincipal();

        List<String> roles = userPrincipal.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority).toList();


        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .claim("id",userPrincipal.getId())
                .claim("roles",roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() +expirationTime))
                .signWith(key(), SignatureAlgorithm.HS256).compact();
    }

    public boolean isValid(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            // Invalid JWT signature
            throw new JwtException("invalid signature");
        } catch (MalformedJwtException e) {
            // Invalid JWT token
            throw new JwtException("invalid token");
        } catch (ExpiredJwtException e) {
            // Expired JWT token
            throw new JwtException("expired token");
        } catch (UnsupportedJwtException e) {
            // Unsupported JWT token
        } catch (IllegalArgumentException e) {
            // JWT claims string is empty
        }
        return false;
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }


    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }



}
