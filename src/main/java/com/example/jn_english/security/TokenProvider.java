package com.example.jn_english.security;

import com.example.jn_english.entity.admin.Admin;
import com.example.jn_english.entity.admin.AdminRepository;
import com.example.jn_english.entity.details.Details;
import com.example.jn_english.entity.details.DetailsService;
import com.example.jn_english.entity.refresh.Refresh;
import com.example.jn_english.entity.refresh.RefreshRepository;
import com.example.jn_english.exception.custom.AccountIdNotFoundException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class TokenProvider {

    private final DetailsService detailsService;

    private final AdminRepository adminRepository;

    private final RefreshRepository refreshRepository;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expired.access}")
    private Long access;

    @Value("${jwt.expired.refresh}")
    private Long refresh;

    private byte[] secretKey() {
        return Base64.getEncoder().encodeToString(secret.getBytes(StandardCharsets.UTF_8)).getBytes();
    }

    public Claims parseToken(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey()).build().parseClaimsJws(token).getBody();
    }

    public boolean isRefresh(String token) {
        Claims claims = parseToken(token);
        return claims.get("type").equals("refresh");
    }

    public Authentication getAuthentication(String accountId) {
        Details details = (Details) detailsService.loadUserByUsername(accountId);
        return new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
    }

    public String accessToken(String accountId) {
        return generateToken(accountId, "access");
    }

    public String refreshToken(String accountId) {
        String token = generateToken(accountId, "refresh");
        Admin admin = adminRepository.findByAccountId(accountId)
                .orElseThrow(()-> AccountIdNotFoundException.EXCEPTION);
        if (refreshRepository.findByAdmin(admin).isEmpty()) {
            refreshRepository.save(Refresh.builder()
                            .refresh(token)
                            .admin(admin)
                    .build());
        } else {
            Refresh refreshToken = refreshRepository.findByAdmin(admin).get();
            refreshRepository.save(refreshToken.updateRefresh(token));
        }
        return token;
    }

    private String generateToken(String accountId, String type) {
        return Jwts.builder()
                .setSubject(accountId)
                .claim("type", type)
                .signWith(SignatureAlgorithm.HS256, secretKey())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ( type.equals("access") ? access : refresh ) * 1000))
                .compact();
    }
}
