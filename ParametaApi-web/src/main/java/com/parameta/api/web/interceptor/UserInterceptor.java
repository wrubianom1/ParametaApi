package com.parameta.api.web.interceptor;

import com.parameta.api.web.dto.UserSessionDTO;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInterceptor extends HandlerInterceptorAdapter {

    private UserSessionDTO userSessionDTO;

    public UserInterceptor(UserSessionDTO headerDTO) {
        this.userSessionDTO = headerDTO;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) {

        userSessionDTO.setUserID(request.getHeader(JWTValidateInterceptor.PROPERTY_ID));
        userSessionDTO.setUserName(request.getHeader(JWTValidateInterceptor.PROPERTY_USERNAME));
        return true;
    }

}