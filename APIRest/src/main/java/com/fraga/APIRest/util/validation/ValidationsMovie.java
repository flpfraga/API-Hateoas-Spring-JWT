package com.fraga.APIRest.util.validation;

import org.springframework.stereotype.Component;

import com.fraga.APIRest.data.model.Movie;

@Component
public class ValidationsMovie implements SimpleValidations<Movie> {
    
   
    @Override
    public boolean validEntity(Movie entity) {
        
        if (entity.getTitle() == null || entity.getTitle().equals("")) return false;
        
        return true;
    }
    
}
