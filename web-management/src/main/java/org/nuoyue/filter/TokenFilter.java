package org.nuoyue.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.nuoyue.utils.CurrentHolder;
import org.nuoyue.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*") // Apply this filter to all URLs
public class TokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // Get the request URI
        String requestURI = request.getRequestURI();

        // Check if the request URI starts with /login
        if (requestURI.startsWith("/login")) {
            // If it's a login request, allow it to pass through
            log.info("Login request detected, allowing it to pass through: {}", requestURI);
            filterChain.doFilter(request, response);
            return;
        }

        // For other requests, check for the presence of the token in the header
        String token = request.getHeader("token");
        if (token == null || token.isEmpty()) {
            // If the token is missing, return a 401 Unauthorized response
            log.info("Unauthorized request detected, token is missing: {}", requestURI);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: Token is missing");
            return;
        }

        // If the token is present
        try{

            // Validate the token and extract claims
            Claims claims = JwtUtils.validateToken(token);
            Integer empId = Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(empId);

        } catch (Exception e) {
            // If the token is invalid, return a 401 Unauthorized response
            log.info("Unauthorized request detected, token is invalid: {}", requestURI);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: Token is invalid");
        }
        log.info("Token is valid, allowing request to proceed: {}", requestURI);
        filterChain.doFilter(request, response);

        // Clean up the ThreadLocal variable after the request is processed
        CurrentHolder.remove();
    }
}
