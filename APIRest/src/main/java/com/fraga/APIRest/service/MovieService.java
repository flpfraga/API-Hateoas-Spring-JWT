package com.fraga.APIRest.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fraga.APIRest.controller.MovieController;
import com.fraga.APIRest.converter.DozerConverter;
import com.fraga.APIRest.data.model.Actor;
import com.fraga.APIRest.data.model.Movie;
import com.fraga.APIRest.data.vo.ActorVO;
import com.fraga.APIRest.data.vo.MovieVO;
import com.fraga.APIRest.exception.ResourceNotFoundException;
import com.fraga.APIRest.repository.ActorRepository;
import com.fraga.APIRest.repository.MovieRepository;

@Service
public class MovieService {
	@Autowired
	private MovieRepository repository;
	@Autowired
	private ActorRepository actorRepository;

	public Page<MovieVO> findAll(Pageable pageable) {
		var moviePage = repository.findAll(pageable);
		var movieVosPage = moviePage.map(p -> DozerConverter.parseObject(p, MovieVO.class));
		movieVosPage.map(p -> p.add(linkTo(methodOn(MovieController.class).findById(p.getKey())).withSelfRel()));
		return movieVosPage;
	}

	public MovieVO findById(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records matches for this ID!"));

		MovieVO vo = DozerConverter.parseObject(entity, MovieVO.class);
		vo.add(linkTo(methodOn(MovieController.class).findById(id)).withSelfRel());
		return vo;
	}

	public MovieVO create(MovieVO movie) {
		List<Actor> actors = DozerConverter.parseList(movie.getActors(), Actor.class);
		var entity = repository.save(DozerConverter.parseObject(movie, Movie.class));
		actors.forEach(a -> a.setMovie(entity));
		actors.forEach(actorRepository::save);
		var vo = DozerConverter.parseObject(entity, MovieVO.class);
		vo.setActors(DozerConverter.parseList(movie.getActors(), ActorVO.class));
		vo.add(linkTo(methodOn(MovieController.class).findById(movie.getKey())).withSelfRel());
		return vo;
	}

//	public void delete(Long id) {
//		var vo = findById(id);
//		repository.delete(DozerConverter.parseObject(vo, Movie.class));
//	}

	public MovieVO update(Long id, MovieVO movie) {
		var vo = findById(id);
		List<Actor> actors = DozerConverter.parseList(movie.getActors(), Actor.class);
		
		vo.setTitle(movie.getTitle());
		vo.setDirector(movie.getDirector());
		vo.setGenre(movie.getGenre());
		vo.setVoteCount(movie.getVoteCount());
		vo.setVoteAverage(movie.getVoteAverage());
		var entity = repository.save(DozerConverter.parseObject(vo, Movie.class));
		actors.forEach(a -> a.setMovie(entity));
		actors.forEach(actorRepository::save);
		vo = DozerConverter.parseObject(entity, MovieVO.class);
		vo.add(linkTo(methodOn(MovieController.class).findById(movie.getKey())).withSelfRel());
		return vo;
	}

	public void movieVoted(Long id, Long vote) {
		var vo = findById(id);
		Long voteCount = vo.getVoteCount();
		Double voteAverage = vo.getVoteAverage();
		voteAverage = (voteCount + vote) / ((voteCount / voteAverage) + 1);
		voteCount = voteCount + vote;
		vo.setVoteAverage(voteAverage);
		vo.setVoteCount(voteCount);
		repository.save(DozerConverter.parseObject(vo, Movie.class));
	}

	public Page<MovieVO> findAllByDirectorDirector(String query, Pageable pageable) {
		Movie movie = new Movie();
		movie.setDirector(query);
		var moviePage = repository.findAll(
				Example.of(movie, ExampleMatcher.matchingAny().withStringMatcher(StringMatcher.CONTAINING)), pageable);
		var movieVosPage = moviePage.map(p -> DozerConverter.parseObject(p, MovieVO.class));
		movieVosPage.map(p -> p.add(linkTo(methodOn(MovieController.class).findById(p.getKey())).withSelfRel()));
		return movieVosPage;
	}

	public Page<MovieVO> findAllByTitle(String query, Pageable pageable) {
		Movie movie = new Movie();
		movie.setTitle(query);
		var moviePage = repository.findAll(
				Example.of(movie, ExampleMatcher.matchingAny().withStringMatcher(StringMatcher.CONTAINING)), pageable);
		var movieVosPage = moviePage.map(p -> DozerConverter.parseObject(p, MovieVO.class));
		movieVosPage.map(p -> p.add(linkTo(methodOn(MovieController.class).findById(p.getKey())).withSelfRel()));
		return movieVosPage;
	}

	public Page<MovieVO> findAllByGenre(String query, Pageable pageable) {
		Movie movie = new Movie();
		movie.setGenre(query);
		var moviePage = repository.findAll(
				Example.of(movie, ExampleMatcher.matchingAny().withStringMatcher(StringMatcher.CONTAINING)), pageable);
		var movieVosPage = moviePage.map(p -> DozerConverter.parseObject(p, MovieVO.class));
		movieVosPage.map(p -> p.add(linkTo(methodOn(MovieController.class).findById(p.getKey())).withSelfRel()));
		return movieVosPage;
	}

	public List<MovieVO> findAllByActor(String query) {
		return DozerConverter.parseList(repository.findByActor("%" + query + "%"), MovieVO.class);
	}

}
