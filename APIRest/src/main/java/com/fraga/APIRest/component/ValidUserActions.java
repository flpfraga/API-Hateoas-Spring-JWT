package com.fraga.APIRest.component;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.fraga.APIRest.data.model.User;

@Component
public class ValidUserActions {

    /**
     * Get the userName of the user was authenticate.
     * 
     * @return String
     */
    public String getUserNameInUse() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    /**
     * Receive the userName of the objetc to be updated. If this userName was
     * different of the user authenticate, throw a exception.
     * 
     * @param String userName
     */
    public boolean validUpdateAction(String userName) {
        System.out.println("valid username " + userName + " getUserNameInUse " + getUserNameInUse());
        if (!userName.equals(getUserNameInUse())) {
            
            return false;
        }
        return true;

    }
    
    public boolean isAdminUser(User user) {
        for (GrantedAuthority g : user.getPermissions()) {
            if (g.equals("ADMIN"))
                return true;
        }
        return false;
    }
}
