package com.example.optika.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN;

    @org.jetbrains.annotations.NotNull
    @org.jetbrains.annotations.Contract(pure = true)
    @Override
    public String getAuthority() {
        return name();
    }
}