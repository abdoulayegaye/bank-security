package com.bank.security.banksecurity.config;

import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;

import com.bank.security.banksecurity.domain.User;
import com.bank.security.banksecurity.mapping.UserMapper;
import com.bank.security.banksecurity.repository.RolesRepository;
import com.bank.security.banksecurity.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;

import static com.bank.security.banksecurity.config.JWTConstant.*;

@Component
public class JwtTokenUtil implements Serializable {
    final UserRepository userRepository;
    final RolesRepository rolesRepository;
    final UserDetailsService userDetailsService;
    private UserMapper userMapper;
    JwtTokenUtil(RolesRepository role, UserRepository user, UserDetailsService userDetailsService){
        this.rolesRepository = role;
        this.userRepository = user;
        this.userDetailsService = userDetailsService;
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public User getUserAuthenticate(HttpServletRequest req) {
        String token = getToken(req);
        String username = getUsernameFromToken(token);
        return userMapper.toUser(userRepository.findByUsername(username));
    }
    public String getToken(HttpServletRequest req) {
        String token = req.getHeader(HEADER_STRING) ;
        return token.replace(TOKEN_PREFIX,"").trim();
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(User user) {
        return doGenerateToken(user.getUsername());
    }

    private String doGenerateToken(String subject) {

        Claims claims = Jwts.claims().setSubject(subject);
        UserDetails userDetails = userDetailsService.loadUserByUsername(subject);
        claims.put("scopes", userDetails.getAuthorities());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("http://localhost:8083")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS*1000))
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}