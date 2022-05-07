package com.onion.respone;

import lombok.Data;

@Data
public class JwtAuthenticationRespone {
    private String token;

    private String refreshtoken;

    public JwtAuthenticationRespone(String token){
        this.token = token;
    }
}
