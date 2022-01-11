package com.coconutsrule.otoutlets.outletsapi.repo;

import com.coconutsrule.otoutlets.outletsapi.models.BuildingFloor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BuildingFloorRepo extends JpaRepository<BuildingFloor, Integer> {
    
}
