package com.fraga.APIRest.repository;

import com.fraga.APIRest.data.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fraga.APIRest.data.model.Ator;

import java.util.Optional;

@Repository
public interface AtorRepository extends JpaRepository<Ator, Long>{

}
