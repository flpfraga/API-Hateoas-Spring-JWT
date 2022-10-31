package com.fraga.APIRest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public abstract class AbstractService<T> {
    
   
    public abstract Page<T> readAll(Pageable pageable);
    
    public abstract T readById(Long id);
    
    public abstract T create(T object);
    
    public abstract void delete(Long id);
    
    public abstract T update(Long id, T object);
       
    
}
