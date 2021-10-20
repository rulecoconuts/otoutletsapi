package com.coconutsrule.otoutlets.outletsapi.models;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Building extends PlaceMark{
    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<BuildingFloor> floors;
}
