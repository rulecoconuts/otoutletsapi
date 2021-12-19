package com.coconutsrule.otoutlets.outletsapi.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class BuildingFloor extends Place {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "building_id", nullable = false)
    Building building;

    int floorNumber;
}
