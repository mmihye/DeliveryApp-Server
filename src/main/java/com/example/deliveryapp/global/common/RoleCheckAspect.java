package com.example.deliveryapp.global.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.example.deliveryapp.domain.user.enumerate.UserRole;
import com.example.deliveryapp.global.exception.ApplicationException;
import com.example.deliveryapp.global.exception.ErrorCode;

import jakarta.servlet.http.HttpServletRequest;

@Component
@Aspect
public class RoleCheckAspect {

    private final HttpServletRequest request;

    public RoleCheckAspect(HttpServletRequest request) {
        this.request = request;
    }

    @Before("@annotation(roleRequired)")
    public void checkRole(RoleRequired roleRequired) {
        String currentRole = (String) request.getAttribute("role");
        UserRole userRole = UserRole.fromString(currentRole);

        if (currentRole == null) {
            throw new ApplicationException(ErrorCode.FORBIDDEN_EXCEPTION);
        }

        UserRole[] requiredRoles = roleRequired.value();
        boolean hasRole = false;
        for (UserRole role : requiredRoles) {
            if (role.equals(userRole)) {
                hasRole = true;
                break;
            }
        }

        if (!hasRole) {
            throw new ApplicationException(ErrorCode.FORBIDDEN_EXCEPTION);
        }
    }
}
