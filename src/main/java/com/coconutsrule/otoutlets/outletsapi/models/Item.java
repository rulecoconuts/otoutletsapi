package com.coconutsrule.otoutlets.outletsapi.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.SequenceGenerator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.geolatte.geom.C3D;
import org.geolatte.geom.Point;
import org.geolatte.geom.codec.Wkt;
import org.geolatte.geom.json.GeometryDeserializer;
import org.geolatte.geom.json.GeometrySerializer;
import lombok.Data;

/**
 * An item is an important thing on the map whose shape we don't need to know.
 */
@Data
@Inheritance
@Entity
public abstract class Item extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEM_ID_SEQ")
    @SequenceGenerator(name = "ITEM_ID_SEQ", sequenceName = "ITEM_ID_SEQ", allocationSize = 10)
    private Integer id;

    String name;

    @Column(nullable = false)
    Point<C3D> location = (Point)Wkt.fromWkt("POINT Z(0 0 0)");
}
