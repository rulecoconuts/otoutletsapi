package com.coconutsrule.otoutlets.outletsapi.security;

import com.coconutsrule.otoutlets.outletsapi.models.ApiUser;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ApiUserConverter implements Converter<SpringSecurityUser, ApiUser> {

    @Override
    public ApiUser convert(SpringSecurityUser user) {
        ApiUser apiUser = new ApiUser();
        apiUser.setId(user.getId());
        apiUser.setUsername(user.getUsername());
        apiUser.setPassword(user.getPassword());
        apiUser.setAccountNonExpired(user.isAccountNonExpired());
        apiUser.setAccountNonLocked(user.isAccountNonLocked());
        apiUser.setCredentialsNonExpired(user.isCredentialsNonExpired());
        apiUser.setEnabled(user.isEnabled());
        return apiUser;
    }
    
}
