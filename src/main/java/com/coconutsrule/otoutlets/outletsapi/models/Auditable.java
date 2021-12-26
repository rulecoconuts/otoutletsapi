package com.coconutsrule.otoutlets.outletsapi.models;

import java.time.Instant;
import java.time.ZonedDateTime;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.Data;

@EntityListeners(AuditingEntityListener.class)
@Data
@MappedSuperclass
public abstract class Auditable<U> {
    @CreatedDate
    Instant creationDate;

    @LastModifiedDate
    Instant lastModifiedDate;

    @CreatedBy
    U createdBy;

    @LastModifiedBy
    U lastModifiedBy;
}
