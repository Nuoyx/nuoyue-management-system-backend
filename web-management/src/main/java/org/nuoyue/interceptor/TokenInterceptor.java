package org.nuoyue.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.nuoyue.utils.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Get the request URI
        String requestURI = request.getRequestURI();

        // Check if the request URI starts with /login
        if (requestURI.startsWith("/login")) {
            // If it's a login request, allow it to pass through
            log.info("Login request detected, allowing it to pass through: {}", requestURI);
            return true;
        }

        // For other requests, check for the presence of the token in the header
        String token = request.getHeader("token");
        if (token == null || token.isEmpty()) {
            // If the token is missing, return a 401 Unauthorized response
            log.info("Unauthorized request detected, token is missing: {}", requestURI);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: Token is missing");
            return false;
        }

        // If the token is present
        try{
            JwtUtils.validateToken(token); // Validate the token (this will throw an exception if the token is invalid)

        } catch (Exception e) {
            // If the token is invalid, return a 401 Unauthorized response
            log.info("Unauthorized request detected, token is invalid: {}", requestURI);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: Token is invalid");
            return false;
        }
        log.info("Token is valid, allowing request to proceed: {}", requestURI);
        return true;
    }
}
