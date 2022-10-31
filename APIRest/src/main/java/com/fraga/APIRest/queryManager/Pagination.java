package com.fraga.APIRest.queryManager;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class Pagination {
    
    
    public static Pageable paginationManager(int page, int size) {
      return PageRequest.of(page, size);
    }
    public static Pageable paginationManager(int page, int size, String orderBy, String properties) {
      var sortDirection = "desc".equalsIgnoreCase(orderBy) ? Direction.DESC : Direction.ASC;
      return PageRequest.of(page, size, Sort.by(sortDirection, properties));
    }
    
}
