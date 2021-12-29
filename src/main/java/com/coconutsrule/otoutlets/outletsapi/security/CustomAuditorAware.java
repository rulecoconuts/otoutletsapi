package com.coconutsrule.otoutlets.outletsapi.security;

import java.util.Optional;
import com.coconutsrule.otoutlets.outletsapi.models.ApiUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

public class CustomAuditorAware implements AuditorAware<ApiUser> {
    @Override
    public Optional<ApiUser> getCurrentAuditor() {
        SecurityContext context = SecurityContextHolder.getContext();
        return Optional.of((ApiUser)context.getAuthentication().getPrincipal());
    }
}
