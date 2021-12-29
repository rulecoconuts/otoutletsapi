package com.coconutsrule.otoutlets.outletsapi.security;

import org.springframework.stereotype.Component;
import lombok.Data;

@Data
public class PasswordStorage {
    String password;
    
    void clear(){
        setPassword(null);
    }
}
