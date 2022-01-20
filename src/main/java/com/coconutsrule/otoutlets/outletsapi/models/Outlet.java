package com.coconutsrule.otoutlets.outletsapi.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("outlet")
public class Outlet extends Item {
    // @Id
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEM_ID_SEQ")
    // private Integer id;
}
