package com.coconutsrule.otoutlets.outletsapi.security.jwt;

import com.coconutsrule.otoutlets.outletsapi.dao.UserDao;
import com.coconutsrule.otoutlets.outletsapi.security.CustomBasicAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Order(2)
@Configuration
public class JwtAuthorizationConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDao userDao;

    @Autowired
    JwtConfig jwtConfig;

    @Autowired
    @Qualifier("CustomUserDetailsService")
    UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**").authorizeRequests().anyRequest().authenticated().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .csrf().disable().cors().disable()
                .addFilter(jwtAuthorizationFilter());
    }

    JwtAuthorizationFilter jwtAuthorizationFilter() throws Exception {
        return new JwtAuthorizationFilter(authenticationManager(), userDao, jwtConfig, userDetailsService);
    }

    BasicAuthenticationEntryPoint customBasicAuthenticationEntryPoint() {
        return new CustomBasicAuthenticationEntryPoint();
    }
}
