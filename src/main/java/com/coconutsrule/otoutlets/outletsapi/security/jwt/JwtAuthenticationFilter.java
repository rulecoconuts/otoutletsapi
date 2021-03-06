package com.coconutsrule.otoutlets.outletsapi.security.jwt;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.swing.Spring;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.coconutsrule.otoutlets.outletsapi.dao.UserDao;
import com.coconutsrule.otoutlets.outletsapi.models.ApiUser;
import com.coconutsrule.otoutlets.outletsapi.security.PasswordStorage;
import com.coconutsrule.otoutlets.outletsapi.security.SpringSecurityUser;
import com.coconutsrule.otoutlets.outletsapi.utils.MultipleReadHttpServletRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import lombok.RequiredArgsConstructor;

/**
 * Authentication filter to handle JWT authentication
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;
    private final UserDao userDao;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtConfig jwtConfig,
            UserDao userDao) {
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
        this.userDao = userDao;

        this.setFilterProcessesUrl("/login");
    }

    /**
     * Attempt to authenticate the username and password by retreiving the username and creds and
     * converting it to an AuthenticationToken
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException {
        try {
            ApiUser user = new ObjectMapper().readValue(request.getInputStream(), ApiUser.class);

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getUsername(), user.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * On successful authentication, respond with a JWT
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
            HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        SpringSecurityUser user = ((SpringSecurityUser) authResult.getPrincipal());
        String token = JWT.create().withSubject(user.getId().toString()).withExpiresAt(jwtConfig.getNewExpirationDate())
                .sign(Algorithm.HMAC512(jwtConfig.getSecret()));

        response.addHeader(jwtConfig.getHeader(), jwtConfig.getTokenPrefix() + " " + token);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        MultipleReadHttpServletRequest multiReadRequest =
                new MultipleReadHttpServletRequest((HttpServletRequest) request);
        super.doFilter(multiReadRequest, response, chain);
    }

}
