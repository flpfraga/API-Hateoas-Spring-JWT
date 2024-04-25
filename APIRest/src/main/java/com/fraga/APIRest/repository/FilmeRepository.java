package com.fraga.APIRest.repository;

import java.util.List;
import java.util.Optional;

import com.fraga.APIRest.data.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fraga.APIRest.data.model.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long>{

	@Query("SELECT f FROM Filme f WHERE f.titulo =:titulo")
	Optional<Filme> buscarPorTitulo(@Param("titulo") String titulo);

}
