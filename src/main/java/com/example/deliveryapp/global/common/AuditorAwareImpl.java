package com.example.deliveryapp.global.common;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        //TODO 인증된 사용자 정보 가져오기
        return Optional.of("mihye");
    }
}