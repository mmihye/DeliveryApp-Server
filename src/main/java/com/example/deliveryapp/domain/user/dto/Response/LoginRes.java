package com.example.deliveryapp.domain.user.dto.Response;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record LoginRes(
        @NotNull Long memberId,
        @NotNull String email,
        @NotNull String name,
        @NotNull String nickname,
        @NotNull String phoneNumber,
        @NotNull String accessToken,
        @NotNull String refreshToken
){

}