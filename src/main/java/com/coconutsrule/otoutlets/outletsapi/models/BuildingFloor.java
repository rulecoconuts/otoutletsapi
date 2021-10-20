package com.coconutsrule.otoutlets.outletsapi.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class BuildingFloor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BUILDING_FLOOR_ID_SEQ")
    @SequenceGenerator(name = "BUILDING_FLOOR_ID_SEQ", sequenceName = "BUILDING_FLOOR_ID_SEQ", allocationSize = 1)
    Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "building_id", nullable = false)
    Building building;

    int floorNumber;
}
