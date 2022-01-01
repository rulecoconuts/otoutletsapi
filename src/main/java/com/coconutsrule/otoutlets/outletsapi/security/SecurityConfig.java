package com.coconutsrule.otoutlets.outletsapi.security;

import java.time.Instant;
import java.util.Optional;
import javax.sql.DataSource;
import com.coconutsrule.otoutlets.outletsapi.dao.UserDao;
import com.coconutsrule.otoutlets.outletsapi.security.jwt.DebugFilter;
import com.coconutsrule.otoutlets.outletsapi.security.jwt.JwtAuthenticationFilter;
import com.coconutsrule.otoutlets.outletsapi.security.jwt.JwtAuthorizationFilter;
import com.coconutsrule.otoutlets.outletsapi.security.jwt.JwtConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.annotation.Order;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.RequestScope;

@EnableWebSecurity
@Order(1)
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
        // httpSecurity.addFilter(jwtAuthenticationFilter()).authorizeRequests()
        // .antMatchers("/login**").permitAll().and().addFilter(jwtAuthorizationFilter())
        // .authorizeRequests().anyRequest().authenticated().and().sessionManagement()
        // .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.antMatcher("/login**")
                .httpBasic()
                .authenticationEntryPoint(customBasicAuthenticationEntryPoint())
                .and().csrf().disable().cors().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilterBefore(new DebugFilter(), JwtAuthenticationFilter.class)
                .addFilter(jwtAuthenticationFilter());
    }

    JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return new JwtAuthenticationFilter(authenticationManager(), jwtConfig, userDao);
    }

    JwtAuthorizationFilter jwtAuthorizationFilter() throws Exception {
        return new JwtAuthorizationFilter(authenticationManager(), userDao, jwtConfig);
    }

    BasicAuthenticationEntryPoint customBasicAuthenticationEntryPoint() {
        return new CustomBasicAuthenticationEntryPoint();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean("auditorAwareRef")
    public AuditorAware<com.coconutsrule.otoutlets.outletsapi.models.ApiUser> auditorAware() {
        return new CustomAuditorAware();
    }

    @Bean("dateTimeProviderRef")
    public DateTimeProvider auditingDateTimeProvider() {
        return () -> Optional.of(Instant.now());
    }

    @Bean
    public PasswordStorage passwordStorage() {
        return new PasswordStorage();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/register**");
    }
}
