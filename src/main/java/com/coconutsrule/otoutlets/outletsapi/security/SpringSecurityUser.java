package com.coconutsrule.otoutlets.outletsapi.security;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SpringSecurityUser implements UserDetails {
    final Integer id;
    final String username;
    final String password;
    final Collection<? extends GrantedAuthority> authorities;
    boolean isAccountNonExpired = true;
    boolean isAccountNonLocked = true;
    boolean isCredentialsNonExpired = true;
    boolean isEnabled = true;
}
