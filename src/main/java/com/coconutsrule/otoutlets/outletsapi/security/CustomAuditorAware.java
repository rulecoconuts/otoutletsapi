package com.coconutsrule.otoutlets.outletsapi.security;

import java.util.Optional;
import com.coconutsrule.otoutlets.outletsapi.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Component;

public class CustomAuditorAware implements AuditorAware<User> {
    SecurityContext context;

    public CustomAuditorAware(SecurityContext context) {
        this.context = context;
    }

    @Override
    public Optional<User> getCurrentAuditor() {
        return Optional.of((User)context.getAuthentication().getPrincipal());
    }
    
}
