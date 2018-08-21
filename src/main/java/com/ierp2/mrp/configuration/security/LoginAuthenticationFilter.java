package com.ierp2.mrp.configuration.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        Map<String, String> requestMap;
        try {
            String accept = request.getContentType();
            if (accept == null) {
                accept = request.getHeader("accept");
            }
            if (accept.contains("application/json")) {
                requestMap = objectMapper.readValue(request.getInputStream(), Map.class);
            } else {
                requestMap = new HashMap<>();
                requestMap.put(getUsernameParameter(), obtainUsername(request));
                requestMap.put(getPasswordParameter(), obtainPassword(request));
            }
        } catch (IOException e) {
            throw new UsernameNotFoundException("请求出错");
        }

        String username = requestMap.get(getUsernameParameter());
        String password = requestMap.get(getPasswordParameter());

        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        username = username.trim();

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                username, password);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

}
