package com.coconutsrule.otoutlets.outletsapi.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import org.geolatte.geom.C3D;
import org.geolatte.geom.Point;
import lombok.Data;

/**
 * An item is an important thing on the map whose shape we don't need to know.
 */
@Data
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEM_ID_SEQ")
    @SequenceGenerator(name = "ITEM_ID_SEQ", sequenceName = "ITEM_ID_SEQ", allocationSize = 10)
    Integer id;

    @Column(nullable = false)
    Point<C3D> location;
}
