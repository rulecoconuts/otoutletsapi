package com.coconutsrule.otoutlets.outletsapi.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.coconutsrule.otoutlets.outletsapi.dao.UserDao;
import com.coconutsrule.otoutlets.outletsapi.models.ApiUser;
import com.coconutsrule.otoutlets.outletsapi.models.UserPermission;
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
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApiUser user = userDao.findByUsername(username);

        if(user == null) throw new UsernameNotFoundException("Username not found");

        UserDetails details = new SpringSecurityUser(user.getId(),
                user.getUsername(), user.getPassword(), buildAuthorities(user));
        return details;
    }

    public Collection<? extends GrantedAuthority> buildAuthorities(ApiUser user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(user.getRole() == null){
            user.setRole(Role.USER);
        }
        authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
        for (UserPermission permission : user.getPermissions())
            authorities.add(new SimpleGrantedAuthority(
                    permission.getPermissionId().getPermission().toString()));
        return authorities;
    }
}
