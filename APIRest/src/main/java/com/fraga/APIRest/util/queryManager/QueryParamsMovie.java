package com.fraga.APIRest.util.queryManager;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.fraga.APIRest.data.model.Filme;

@Component
public class QueryParamsMovie implements QueryParams<Filme>  {
    
    private Integer page;
    
    private Integer size;
    
    private Filme entity;
    
    private String orderParam = "";
    
    private boolean exampled;
    
    private boolean ordened;
    
    private boolean paginate;
    
    private Direction sortDirection;


    @Override
    public Integer getPage() {
        return page;
    }

    @Override
    public void setPage(Integer page) {
        this.page = page;
    }

    @Override
    public Integer getSize() {
        return size;
    }

    @Override
    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public Filme getEntity() {
        return entity;
    }

    @Override
    public void setEntity(Filme entity) {

       this.entity = entity;
    }

    @Override
    public String getOrderParam() {
        return orderParam;
    }

    @Override
    public void setOrderParam(String orderParam) {
        this.orderParam = orderParam;
    }
    
    @Override
    public Sort inOrder() {
        return Sort.by(sortDirection, orderParam);
    }

    @Override
    public Pageable pagination() {
        return PageRequest.of(page, size);
    }
    
    @Override
    public Pageable paginationWithSort() {
        return PageRequest.of(page, size, Sort.by(sortDirection, orderParam));
    }
    
    @Override
    public Example<Filme> createExample() {
        ExampleMatcher matcher = ExampleMatcher.matchingAny().withStringMatcher(StringMatcher.CONTAINING);
        Example<Filme> example = Example.of(entity, matcher);
        return example;
    }


    @Override
    public boolean isExampled() {
        return exampled;
    }

    @Override
    public void setExampled(boolean exampled) {
        this.exampled = exampled;
    }

    @Override
    public boolean isOrdened() {
        return ordened;
    }

    @Override
    public void setOrdened(boolean ordened) {
        this.ordened = ordened;
    }

    @Override
    public boolean isPaginate() {
        return paginate;
    }

    @Override
    public void setPaginate(boolean paginete) {
        this.paginate = paginete;
    }

    @Override
    public boolean pageIsValid() {
        if (page <=-1) return false;
        if (size <=0) return false;
        return true;
    }
    
    @Override
    public Direction getSortDirection() {
        return this.sortDirection;
    }

    //Set diction for sort. True return direction sort. False return inverse direction sort
    @Override
    public void setSortDirection(boolean direction) {
        if(direction) {
            this.sortDirection = Direction.ASC;
        }
        this.sortDirection = Direction.DESC;
    }

    
}
