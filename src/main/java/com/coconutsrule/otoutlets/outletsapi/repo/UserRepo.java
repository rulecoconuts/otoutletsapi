package com.coconutsrule.otoutlets.outletsapi.repo;

import com.coconutsrule.otoutlets.outletsapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
