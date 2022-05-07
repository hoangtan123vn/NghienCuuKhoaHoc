package com.onion.respone;

import lombok.Data;

@Data
public class JwtAuthenticationRespone {
    private String token;

    public JwtAuthenticationRespone(String token){
        this.token = token;
    }
}
