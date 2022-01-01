package com.coconutsrule.otoutlets.outletsapi.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import com.coconutsrule.otoutlets.outletsapi.security.Permission;
import com.coconutsrule.otoutlets.outletsapi.security.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.Data;

@Data
@Entity
public class ApiUser implements Serializable {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ID_SEQ")
    @SequenceGenerator(name = "USER_ID_SEQ", sequenceName = "USER_ID_SEQ")
    @Id
    Integer id;

    String username;

    // Password hash
    String password;
    String salt;

    boolean isAccountNonExpired = true;
    boolean isAccountNonLocked = true;
    boolean isCredentialsNonExpired = true;
    boolean isEnabled = true;

    Role role;

    @OneToMany(mappedBy = "permissionId.user", fetch = FetchType.LAZY)
	List<UserPermission> permissions;
}
