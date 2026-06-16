package com.kh.rupp_dev.boukryuniversity.service.impl;

import com.kh.rupp_dev.boukryuniversity.dto.response.RefreshTokenResponse;
import com.kh.rupp_dev.boukryuniversity.entity.RefreshToken;
import com.kh.rupp_dev.boukryuniversity.jwt.JwtService;
import com.kh.rupp_dev.boukryuniversity.repository.RefreshTokenRepository;
import com.kh.rupp_dev.boukryuniversity.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;

    @Value("${jwt.expiration-refresh}")
    private Long expiration;

    @Override
    public RefreshToken create() {
        RefreshToken refresh = new RefreshToken();
        String randomRefreshToken = UUID.randomUUID().toString();
        refresh.setToken(randomRefreshToken);
        refresh.setExpiryAt(Instant.now().plusMillis(expiration));
        log.info("Created refresh token [{}]", refresh);
        return refresh;
    }

    @Override
    public Optional<RefreshToken> findByToken(String refresh) {
        return refreshTokenRepository.findByToken(refresh);
    }

    @Override
    public RefreshTokenResponse verify(String token) {
        RefreshToken refreshToken = this.findByToken(token)
                .orElseThrow(() -> new ResourceAccessException("Refresh token not found."));

        if(refreshToken.getExpiryAt().isBefore(Instant.now())) {
            String accessToken = jwtService.generateToken(refreshToken.getUser().getEmail());
            return RefreshTokenResponse
                    .builder()
                    .accessToken(accessToken)
                    .build();

        }

        throw new IllegalArgumentException("Refresh token expired.");
    }
}
