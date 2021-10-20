package com.coconutsrule.otoutlets.outletsapi.models;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKTReader;

import javax.persistence.*;

@Data
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class PlaceMark {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLACEMARK_ID_SEQ")
    @SequenceGenerator(name = "PLACEMARK_ID_SEQ", sequenceName = "PLACEMARK_ID_SEQ", allocationSize = 1)
    Integer id;

    @Column(nullable = false)
    Geometry shape;

    @Setter(AccessLevel.NONE)
    @Column(nullable = false)
    protected String type;
}
