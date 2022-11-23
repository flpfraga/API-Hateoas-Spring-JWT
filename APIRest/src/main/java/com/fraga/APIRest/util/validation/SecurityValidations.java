package com.fraga.APIRest.util.validation;

public interface SecurityValidations<D> extends SimpleValidations<D>{

    String nameInUse();
    
    boolean validUpdateActions(String userName);
    
    boolean isAdminUser(D entity);
    
    
}