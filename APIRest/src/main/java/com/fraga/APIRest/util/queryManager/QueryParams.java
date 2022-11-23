package com.fraga.APIRest.util.queryManager;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public interface QueryParams<D> {

    Integer getPage();

    void setPage(Integer page);

    Integer getSize();

    void setSize(Integer size);

    D getEntity();

    void setEntity(D entity);

    String getOrderParam();

    void setOrderParam(String orderParam);
    
    boolean isPaginate();
    
    void setPaginate(boolean paginate);

    boolean isOrdened();
    
    void setOrdened(boolean ordened);
    
    boolean isExampled();
    
    void setExampled(boolean exampled);
    
    Sort inOrder();
    
    Pageable pagination();
    
    Pageable paginationWithSort();

    Example<D> createExample();
    
    boolean pageIsValid();
    
    Direction getSortDirection();
    
    void setSortDirection(boolean direction);

}