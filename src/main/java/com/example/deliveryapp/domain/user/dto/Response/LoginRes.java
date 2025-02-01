package com.example.deliveryapp.domain.user.dto.Response;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record LoginRes(
        Long memberId,
        String email,
        String name,
        String nickname,
        String phoneNumber,
        String accessToken,
        String refreshToken
){

}