package com.coconutsrule.otoutlets.outletsapi;

import com.coconutsrule.otoutlets.outletsapi.models.Building;
import com.coconutsrule.otoutlets.outletsapi.models.BuildingFloor;
import com.coconutsrule.otoutlets.outletsapi.models.Item;
import com.coconutsrule.otoutlets.outletsapi.models.Outlet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class RestConfig {
    @Bean
    RepositoryRestConfigurer repositoryRestConfigurer(){
        return RepositoryRestConfigurer.withConfig(config->{
            config.exposeIdsFor(Outlet.class, Building.class, BuildingFloor.class);
        });
    }
}
