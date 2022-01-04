package com.coconutsrule.otoutlets.outletsapi.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.coconutsrule.otoutlets.outletsapi.models.ApiUser;
import com.coconutsrule.otoutlets.outletsapi.models.UserPermission;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class SpringSecurityUserConverter implements Converter<ApiUser, SpringSecurityUser> {

    @Override
    public SpringSecurityUser convert(ApiUser user) {
        return new SpringSecurityUser(user.getId(),
                user.getUsername(), user.getPassword(), buildAuthorities(user));
    }

    private Collection<? extends GrantedAuthority> buildAuthorities(ApiUser user) {
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
