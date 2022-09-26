package com.fraga.APIRest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fraga.APIRest.data.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{

	@Query("SELECT m FROM Movie m WHERE m.director LIKE:director")
	List<Movie> findByDirector(@Param ("director") String director);
	
	@Query("SELECT m FROM Movie m WHERE m.title LIKE:title")
	List<Movie> findByTitle(@Param ("title")String title);
	
	@Query("SELECT m FROM Movie m WHERE m.genre LIKE:genre")
	List<Movie> findByGenre(@Param ("genre")String genre);
	
	@Query("SELECT m FROM Movie m INNER JOIN Actor a ON m.id = a WHERE a.name LIKE:actor")
	List<Movie> findByActor(@Param ("actor")String actor);
	
}
