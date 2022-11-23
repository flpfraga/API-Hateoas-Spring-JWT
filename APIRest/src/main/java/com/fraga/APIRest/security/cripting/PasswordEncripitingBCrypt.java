package com.fraga.APIRest.security.cripting;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncripitingBCrypt {
    
    /**
     * Encripty a password before save or update the data base
     * @param String
     * @return String
     */
    public static String encript(String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(16);
        return passwordEncoder.encode(password);
    }

}
