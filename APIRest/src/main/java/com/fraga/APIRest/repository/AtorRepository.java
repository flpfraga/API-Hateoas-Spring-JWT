package com.fraga.APIRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fraga.APIRest.data.model.Ator;

@Repository
public interface AtorRepository extends JpaRepository<Ator, Long>{

}
