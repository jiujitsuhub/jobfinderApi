package com.jiujitsuhub.jobfinder.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public class JwtUtils {
    private JwtUtils() {
    }

    public static HttpServletRequest getCurrentRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
    }

    public static String getJwt(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getHeader("Authorization").split(" ")[1];

    }
}
