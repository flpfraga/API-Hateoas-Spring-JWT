package com.fraga.APIRest.security.cripting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncripiting {
    
    
    /**
     * Encripty a password before save or update the data base
     * @param password
     * @return String
     */
    public static String encript(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(16);
        return bCryptPasswordEncoder.encode(password);
    }

}
