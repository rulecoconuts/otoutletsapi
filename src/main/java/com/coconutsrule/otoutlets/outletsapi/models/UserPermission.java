package com.coconutsrule.otoutlets.outletsapi.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class UserPermission {
    @EmbeddedId
    UserPermissionId permissionId;
}
