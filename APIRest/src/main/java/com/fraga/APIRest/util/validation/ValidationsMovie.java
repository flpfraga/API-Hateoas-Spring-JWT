package com.fraga.APIRest.util.validation;

import org.springframework.stereotype.Component;

import com.fraga.APIRest.data.model.Filme;

@Component
public class ValidationsMovie implements SimpleValidations<Filme> {
    
   
    @Override
    public boolean validEntity(Filme entity) {
        
//        if (entity.getTitle() == null || entity.getTitle().equals("")) return false;
        
        return true;
    }
    
}
