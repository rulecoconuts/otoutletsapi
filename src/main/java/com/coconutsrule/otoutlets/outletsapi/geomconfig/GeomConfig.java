package com.coconutsrule.otoutlets.outletsapi.geomconfig;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeomConfig {
    Jackson2ObjectMapperBuilderCustomizer jsonMapperBuilderCustomizer(){
        return new GeomJackson2ObjectMapperBuilderCustomizer();
    }
}
