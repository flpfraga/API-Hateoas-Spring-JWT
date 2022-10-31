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
import org.springframework.data.domain.Sort;
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
public class MovieService extends AbstractService<MovieVO> {

    @Autowired
    private MovieRepository repository;

    @Autowired
    private ActorRepository actorRepository;

    /**
     * Return a movie`s page sorted by name or vote Average.
     * 
     * @param Pageable
     * @return Page<MovieVO>
     */
    @Override
    public Page<MovieVO> readAll(Pageable pageable) {
        var moviePage = repository.findAll(pageable);
        var movieVOPage = moviePage.map(p -> DozerConverter.parseObject(p, MovieVO.class));
        movieVOPage.map(p -> p.add(linkTo(methodOn(MovieController.class).readById(p.getKey())).withSelfRel()));
        return movieVOPage;
    }

    /**
     * Return all movies sorted by name or vote Average.
     * 
     * @param Sort
     * @return List<MovieVO>
     */
    public List<MovieVO> readAll(Sort sort) {
        List<MovieVO> movies = DozerConverter.parseList(repository.findAll(sort), MovieVO.class);
        movies.stream()
                .forEach(p -> p.add(
                        linkTo(methodOn(MovieController.class).readById(p.getKey())).withSelfRel()));
        return movies;
    }

    /**
     * Return a movie by id
     * 
     * @param Long
     * @return MovieVO
     */
    @Override
    public MovieVO readById(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records matches for this ID!"));
        MovieVO vo = DozerConverter.parseObject(entity, MovieVO.class);
        vo.add(linkTo(methodOn(MovieController.class).readById(id)).withSelfRel());
        return vo;
    }

    /**
     * Create a new movie
     * 
     * @param MovieVO
     *                return MovieVO
     */
    @Override
    public MovieVO create(MovieVO movie) {
        List<Actor> actors = DozerConverter.parseList(movie.getActors(), Actor.class);
        var entity = repository.save(DozerConverter.parseObject(movie, Movie.class));
        actors.forEach(a -> a.setMovie(entity));
        actors.forEach(actorRepository::save);
        var vo = DozerConverter.parseObject(entity, MovieVO.class);
        vo.setActors(DozerConverter.parseList(movie.getActors(), ActorVO.class));
        vo.add(linkTo(methodOn(MovieController.class).readById(movie.getKey())).withSelfRel());
        return vo;
    }

    /**
     * Delete a movie by id
     * 
     * @param Long
     */
    @Override
    public void delete(Long id) {
        var entity = readById(id);
        repository.deleteById(id);
    }

    /**
     * Update a movie
     * 
     * @param MovieVO
     *                return MovieVO
     */
    @Override
    public MovieVO update(Long id, MovieVO movie) {
        var vo = readById(id);
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
        vo.add(linkTo(methodOn(MovieController.class).readById(movie.getKey())).withSelfRel());
        return vo;
    }

    /**
     * Return a page movie filter by params
     * 
     * @param movieVO
     * @return
     */
    public Page<MovieVO> findAllWithFilter(MovieVO movieVO, Pageable pageable) {
        var entity = DozerConverter.parseObject(movieVO, Movie.class);

        // Create a example matcher for search in data base
        ExampleMatcher matcher = ExampleMatcher.matchingAny().withStringMatcher(StringMatcher.CONTAINING);
        Example<Movie> example = Example.of(entity, matcher);

        var moviePage = repository.findAll(example, pageable);
        var movieVosPage = moviePage.map(p -> DozerConverter.parseObject(p, MovieVO.class));
        movieVosPage.map(p -> p.add(linkTo(methodOn(MovieController.class).readById(p.getKey())).withSelfRel()));
        return movieVosPage;
    }

    /**
     * Return a list movie filter by params
     * 
     * @param movieVO
     * @return
     */
    public List<MovieVO> findAllWithFilter(MovieVO movieVO) {
        var entity = DozerConverter.parseObject(movieVO, Movie.class);

        // Create a example matcher for search in data base
        ExampleMatcher matcher = ExampleMatcher.matchingAny().withStringMatcher(StringMatcher.CONTAINING);
        Example<Movie> example = Example.of(entity, matcher);
        List<MovieVO> movies = DozerConverter.parseList(repository.findAll(example), MovieVO.class);
        movies.stream()
                .forEach(p -> p.add(
                        linkTo(methodOn(MovieController.class).readById(p.getKey())).withSelfRel()));
        return movies;
    }

    public List<MovieVO> findAllByActor(String query) {
        return DozerConverter.parseList(repository.findByActor("%" + query + "%"), MovieVO.class);
    }

}
