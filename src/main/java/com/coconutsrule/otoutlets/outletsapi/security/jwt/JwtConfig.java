package com.coconutsrule.otoutlets.outletsapi.security.jwt;

import java.util.Date;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "jwtconfig")
public class JwtConfig {
    private String header = "Authorization";
    private String tokenPrefix;
    private Integer expirationValue;
    private String expirationUnit;
    private String secret;

    /**
     * Get expiration time of JWT in milliseconds
     * 
     * @return
     */
    public int getExpirationTimeInMilliseconds() {
        int milliseconds = expirationValue;
        String unit = expirationUnit.toLowerCase();

        // Convert expiration value to milliseconds based on unit
        switch (unit) {
            case "y":
                milliseconds *= 12;
            case "m":
                milliseconds *= 4;
            case "w":
                milliseconds *= 7;
            case "d":
                milliseconds *= 24;
            case "h":
                milliseconds *= 60;
            case "min":
                milliseconds *= 60;
            case "s":
                milliseconds *= 1000;
            default:
                break;
        }
        System.out.println("");

        return milliseconds;
    }

    Date getNewExpirationDate(){
        return new Date(System.currentTimeMillis()+getExpirationTimeInMilliseconds());
    }
}
