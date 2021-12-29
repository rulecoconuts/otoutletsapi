package com.coconutsrule.otoutlets.outletsapi.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@DiscriminatorValue("buildingfloor")
public class BuildingFloor extends Place {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLACE_ID_SEQ")
    Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "building_id", nullable = false)
    Building building;

    int floorNumber;
}
