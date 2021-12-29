package com.coconutsrule.otoutlets.outletsapi.models;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import javax.persistence.*;
import org.geolatte.geom.C3D;
import org.geolatte.geom.Geometry;

/**
 * A place is really what it sounds like, an area on the map.
 * It is a parent table so that all place locations can be queried regardless of their specific type.
 */
@Data
@Inheritance
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("Place")
public abstract class Place extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLACE_ID_SEQ")
    @SequenceGenerator(name = "PLACE_ID_SEQ", sequenceName = "PLACE_ID_SEQ", allocationSize = 10)
    Integer id;

    @Column(nullable = false)
    Geometry<C3D> shape;

    @Column(nullable = false)
    String name;

    @Setter(AccessLevel.NONE)
    @Column(nullable = false)
    protected String type;
}
