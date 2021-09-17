package com.parameta.api.web.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.parameta.api.web.interceptor.JWTValidateInterceptor;

public class TokenUtils {

    public static String generateToken(String client, String clientId) {
        Algorithm algorithm = Algorithm.HMAC256(JWTValidateInterceptor.KEY_JWT);
        String token = JWT.create().withClaim(JWTValidateInterceptor.PROPERTY_ID, client)
                .withClaim(JWTValidateInterceptor.PROPERTY_USERNAME, JWTValidateInterceptor.PROPERTY_USERNAME + client)
                .withClaim(JWTValidateInterceptor.PROPERTY_ID, clientId)
                .withIssuer(JWTValidateInterceptor.ALGORITHM_CURRENT)
                .sign(algorithm);
        return token;
    }
}
