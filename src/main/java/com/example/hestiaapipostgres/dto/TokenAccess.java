package com.example.hestiaapipostgres.dto;



public record TokenAccess(
        String token
) {

    public static TokenAccess fromToken(String token) {
        return new TokenAccess(
                token == null ? null : "Bearer " + token
        );
    }
}
