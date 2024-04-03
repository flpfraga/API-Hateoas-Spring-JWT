package com.fraga.APIRest.util.validation;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.fraga.APIRest.data.model.Usuario;
import com.fraga.APIRest.exception.InvalidParams;

@Component
public class ValidationsUser implements SecurityValidations<Usuario> {


    /**
     * Get the userName of the user was authenticate.
     * 
     * @return String
     */
    @Override
    public String nameInUse() {
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
    @Override
    public boolean validUpdateActions(String userName) {
        
        System.out.println("valid update actions " + userName);
        
        if (!userName.equals(nameInUse())) {

            return false;
        }
        return true;
    }

    @Override
    public boolean validEntity(Usuario entity) {
        
//
//        if (entity.getUserName().equals(null) || entity.getUserName().equals(""))
//            return false;
//        if (entity.getPassword().equals(null) || entity.getPassword().equals(""))
//            throw new InvalidParams("Password can`t be null!");
        return true;
    }
   

    @Override
    public boolean isAdminUser(Usuario entity) {
        
        for (GrantedAuthority g : entity.getPermissions()) {
            if (g.equals("ADMIN"))
                return true;
        }
        return false;
    }

}
