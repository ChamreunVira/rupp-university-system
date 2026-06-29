package com.kh.rupp_dev.boukryuniversity.mapper;

import com.kh.rupp_dev.boukryuniversity.dto.request.UserRequest;
import com.kh.rupp_dev.boukryuniversity.dto.response.UserResponse;
import com.kh.rupp_dev.boukryuniversity.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRequest request) {
        if (request == null) {
            return null;
        }

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setBio(request.getBio());
        return user;
    }

    public UserResponse toResponse(User user) {
        if (user == null) {
            return null;
        }

        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .bio(user.getBio())
                .verified(user.isVerified())
                .verificationToken(user.getVerificationToken())
                .refreshToken(user.getRefreshToken().getToken())
                .status(user.isStatus())
                .role(user.getRole().getName())
                .attempt(user.getAttempt())
                .lockTime(user.getLockTime())
                .build();
    }
}
