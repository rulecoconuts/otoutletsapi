package com.coconutsrule.otoutlets.outletsapi.repo;

import com.coconutsrule.otoutlets.outletsapi.models.Outlet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface OutletRepo extends JpaRepository<Outlet, Integer> {
    
}
