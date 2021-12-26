package com.coconutsrule.otoutlets.outletsapi.models;

import java.time.ZonedDateTime;
import javax.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class DatedEntity<U> {
    @CreatedDate
    ZonedDateTime creationDate;

    @LastModifiedDate
    ZonedDateTime lastModifiedDate;

    @CreatedBy
    U createdBy;

    @LastModifiedBy
    U lastModifiedBy;
}
