package com.coconutsrule.otoutlets.outletsapi.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
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
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.Data;

@Data
@Entity
@JsonDeserialize(using = ApiUserDeserializer.class)
public class ApiUser {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ID_SEQ")
    @SequenceGenerator(name = "USER_ID_SEQ", sequenceName = "USER_ID_SEQ")
    @Id
    Integer id;

    @Column(unique = true)
    String username;

    // Password hash
    String password;
    String salt;

    boolean isAccountNonExpired = true;
    boolean isAccountNonLocked = true;
    boolean isCredentialsNonExpired = true;
    boolean isEnabled = true;

    Role role;

    @OneToMany(mappedBy = "permissionId.user", fetch = FetchType.EAGER)
	List<UserPermission> permissions = new ArrayList<>();
}
