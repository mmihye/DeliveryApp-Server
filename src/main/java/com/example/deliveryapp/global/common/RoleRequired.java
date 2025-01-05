package com.example.deliveryapp.global.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.example.deliveryapp.domain.user.enumerate.UserRole;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RoleRequired {
    UserRole[] value();
}
