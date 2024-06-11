package com.flab.lemonticket.service;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtTokenService {
    private Set<String> invaliddatedTokens = new HashSet<>();

    public void invalidateToken(String token) {
        invaliddatedTokens.add(token);
    }

    public boolean isTokenInvalidated(String token){
        return invaliddatedTokens.contains(token);
    }
}
