package com.parameta.api.web.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.parameta.api.web.dto.UserSessionDTO;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JWTValidateInterceptor extends HandlerInterceptorAdapter {

    private final UserSessionDTO userSessionDTO;

    public static final String Authorization = "authorization";
    public static final String KEY_JWT = "auth.parameta.API";
    public static final String KEY_BEARER = "Bearer";
    public static final String KEY_SPLIT = " ";
    public static final String PROPERTY_ID = "ID";
    public static final String PROPERTY_USERNAME = "USERNAME";
    public static final String ALGORITHM_CURRENT = "auth0";

    public JWTValidateInterceptor(UserSessionDTO userSessionDTO) {
        this.userSessionDTO = userSessionDTO;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) {
        String token = request.getHeader(Authorization);
        JSONObject json = new JSONObject();
        boolean tokenIsValid = false;
        if (token == null || token.isEmpty()) {
            responseObject("Debe hacer uso de un token para acceder al recurso", HttpServletResponse.SC_UNAUTHORIZED, false, json,
                    response, request);
            return tokenIsValid;
        }
        if (!token.startsWith(KEY_BEARER)) {
            responseObject("Debe hacer uso de un token para acceder al recurso", HttpServletResponse.SC_UNAUTHORIZED, false, json,
                    response, request);
            return tokenIsValid;
        }
        try {
            String tokenTotal = token.split(KEY_SPLIT)[1];
            Algorithm algorithm = Algorithm.HMAC256(KEY_JWT);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(ALGORITHM_CURRENT).build();
            DecodedJWT jwt = verifier.verify(tokenTotal);
            userSessionDTO.setUserID(jwt.getClaim(JWTValidateInterceptor.PROPERTY_ID).asString());
            userSessionDTO.setUserName(jwt.getClaim(JWTValidateInterceptor.PROPERTY_USERNAME).asString());
            tokenIsValid = true;
        } catch (JWTVerificationException exception) {
            responseObject("Debe hacer uso de un token para acceder al recurso", HttpServletResponse.SC_UNAUTHORIZED, false, json,
                    response, request);
        }
        return tokenIsValid;
    }

    boolean responseObject(String message, Integer status, boolean error, JSONObject json,
                           HttpServletResponse httpResponse, HttpServletRequest httpRequest) {
        try {
            json.put("message", message);
            json.put("status", status);
            json.put("isSuccess", error);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        httpResponse.setStatus(status);
        return setHttpRequestResponse(httpRequest, httpResponse, json);
    }


    boolean setHttpRequestResponse(HttpServletRequest httpRequest, HttpServletResponse httpResponse, JSONObject json) {
        httpResponse.addHeader("Content-Type", "application/json");
        try {
            httpResponse.getWriter().write(json.toString());
            httpResponse.getWriter().flush();
            httpResponse.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}