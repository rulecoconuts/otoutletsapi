package com.coconutsrule.otoutlets.outletsapi.security.jwt;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.coconutsrule.otoutlets.outletsapi.dao.UserDao;
import com.coconutsrule.otoutlets.outletsapi.models.ApiUser;
import com.coconutsrule.otoutlets.outletsapi.security.CustomUserDetailsService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import lombok.Data;

@Data
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private UserDao userDao;
    private JwtConfig jwtConfig;
    private UserDetailsService userDetailsService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserDao userDao,
            JwtConfig jwtConfig, UserDetailsService userDetailsService) {
        this(authenticationManager);
        this.userDao = userDao;
        this.jwtConfig = jwtConfig;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Authorize JWT token on request
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(jwtConfig.getHeader());
        if (header == null || !header.startsWith(jwtConfig.getTokenPrefix())) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }

    /**
     * Get authentication token from request
     * 
     * @param request
     * @return
     */
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(jwtConfig.getHeader()).replace("Bearer ", "");
        if (token != null) {
            String userIdString = JWT.require(Algorithm.HMAC512(jwtConfig.getSecret().getBytes()))
                    .build().verify(token).getSubject();

            if (userIdString != null) {
                ApiUser user = userDao.findById(Integer.parseInt(userIdString));
                
                return new UsernamePasswordAuthenticationToken(user, null,
                        ((CustomUserDetailsService) userDetailsService).buildAuthorities(user));
            }
        }

        return null;
    }

}
