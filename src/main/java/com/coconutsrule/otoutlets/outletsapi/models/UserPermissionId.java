package com.coconutsrule.otoutlets.outletsapi.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.coconutsrule.otoutlets.outletsapi.security.Permission;
import lombok.Data;

@Embeddable
@Data
public class UserPermissionId implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    private ApiUser user;

    private Permission permission;
}
