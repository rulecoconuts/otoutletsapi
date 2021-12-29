package com.coconutsrule.otoutlets.outletsapi.models;

import javax.persistence.*;
import lombok.Data;
import java.util.List;
import java.util.Set;

@Data
@Entity
@DiscriminatorValue("building")
public class Building extends Place{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLACE_ID_SEQ")
    Integer id;
    
    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<BuildingFloor> floors;
}
