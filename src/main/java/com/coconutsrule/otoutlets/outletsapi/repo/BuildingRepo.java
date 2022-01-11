package com.coconutsrule.otoutlets.outletsapi.repo;

import com.coconutsrule.otoutlets.outletsapi.models.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BuildingRepo extends JpaRepository<Building, Integer> {
    
}
