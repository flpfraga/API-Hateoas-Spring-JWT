package com.fraga.APIRest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fraga.APIRest.data.model.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long>{

	@Query("SELECT m FROM Filme m WHERE m.diretor LIKE:diretor")
	List<Filme> findByDirector(@Param ("diretor") String diretor);
	
	@Query("SELECT m FROM Filme m WHERE m.titulo LIKE:titulo")
	List<Filme> findByTitle(@Param ("titulo")String titulo);
	
	@Query("SELECT m FROM Filme m WHERE m.genero LIKE:genero")
	List<Filme> findByGenre(@Param ("genero")String genero);
	
//	@Query("SELECT m FROM Filme m INNER JOIN Ator a ON m.id = a WHERE a.name LIKE:actor")
//	List<Filme> findByActor(@Param ("ator")String actor);
//
//	@Modifying
//    @Query("UPDATE Filme m Set m.voteAverage = :voteAverage, m.voteCount = :voteCount WHERE m.id =:id")
//    void updateVote(
//            @Param("id") Long id,
//            @Param("voteAverage") Double voteAverage,
//            @Param("voteCount") Long voteCount);
	
	
}
