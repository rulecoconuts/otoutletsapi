package com.coconutsrule.otoutlets.outletsapi.geomconfig;

import java.time.format.DateTimeFormatter;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.geolatte.geom.json.GeolatteGeomModule;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class GeomConfig {
    @Bean
    Jackson2ObjectMapperBuilderCustomizer jsonMapperBuilderuCustomizer(){
        return new GeomJackson2ObjectMapperBuilderCustomizer();
    }
}
