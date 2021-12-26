package com.coconutsrule.otoutlets.outletsapi.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.coconutsrule.otoutlets.outletsapi.models.User;
import com.coconutsrule.otoutlets.outletsapi.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("CustomUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    private Collection<? extends GrantedAuthority> buildAuthorities(
            Collection<? extends UserRole> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserRole role : roles)
            authorities.add(new SimpleGrantedAuthority(role.toString()));
        return authorities;
    }
}
