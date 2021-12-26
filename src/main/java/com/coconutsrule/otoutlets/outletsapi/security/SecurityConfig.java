package com.coconutsrule.otoutlets.outletsapi.security;

import java.time.Instant;
import java.util.Optional;
import javax.sql.DataSource;
import com.coconutsrule.otoutlets.outletsapi.dao.UserDao;
import com.coconutsrule.otoutlets.outletsapi.security.jwt.JwtAuthenticationFilter;
import com.coconutsrule.otoutlets.outletsapi.security.jwt.JwtAuthorizationFilter;
import com.coconutsrule.otoutlets.outletsapi.security.jwt.JwtConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("CustomUserDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    UserDao userDao;

    @Autowired
    JwtConfig jwtConfig;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsManager users(DataSource dataSource) {
        JdbcUserDetailsManager usersManager = new JdbcUserDetailsManager(dataSource);
        return usersManager;
    }

    @Autowired
    public void configureAuthenticationManager(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic().authenticationEntryPoint(customBasicAuthenticationEntryPoint())
        .and()
        .authorizeRequests()
        .regexMatchers("/register").permitAll()
        .anyRequest().authenticated()
        .and()
        .addFilter(new JwtAuthenticationFilter(authenticationManager))
        .addFilter(new JwtAuthorizationFilter(authenticationManager));
    }

    BasicAuthenticationEntryPoint customBasicAuthenticationEntryPoint() {
        return new CustomBasicAuthenticationEntryPoint();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean("auditorAwareRef")
    public AuditorAware<com.coconutsrule.otoutlets.outletsapi.models.User> auditorAware(SecurityContext context){
        return new CustomAuditorAware(context);   
    }

    @Bean("dateTimeProviderRef")
    public DateTimeProvider auditingDateTimeProvider(){
        return ()->Optional.of(Instant.now());
    }
}
