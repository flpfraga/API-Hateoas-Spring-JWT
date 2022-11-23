package com.fraga.APIRest.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.fraga.APIRest.controller.MovieController;
import com.fraga.APIRest.converter.DozerConverter;
import com.fraga.APIRest.data.model.Actor;
import com.fraga.APIRest.data.model.Movie;
import com.fraga.APIRest.data.vo.ActorVO;
import com.fraga.APIRest.data.vo.MovieVO;
import com.fraga.APIRest.exception.InvalidParams;
import com.fraga.APIRest.exception.ResourceNotFoundException;
import com.fraga.APIRest.repository.ActorRepository;
import com.fraga.APIRest.repository.MovieRepository;
import com.fraga.APIRest.util.queryManager.QueryParams;
import com.fraga.APIRest.util.validation.SimpleValidations;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    @Autowired
    private ActorRepository actorRepository;
    
    @Autowired
    private SimpleValidations<Movie> validations;
    
    
    /**
     * Return all movies sorted by name or vote Average.
     * 
     * @param Sort
     * @return List<MovieVO>
     */

    public List<MovieVO> readAllInOrder(QueryParams<Movie> queryParams) {
        
        List<MovieVO> movies = DozerConverter.parseList(repository.findAll(queryParams.inOrder()), MovieVO.class);
        movies.stream()
        .forEach(p -> p.add(
                linkTo(methodOn(MovieController.class).readById(p.getKey())).withSelfRel()));
        return movies;
    }
    /**
     * Return all movies sorted by name or vote Average. Return pagined.
     * 
     * @param Sort
     * @return List<MovieVO>
     */
    
    public Page<MovieVO> readAllInOrderPagined(QueryParams<Movie> queryParams) {
        
        var moviePage = repository.findAll(queryParams.paginationWithSort());
        var movieVOPage = moviePage.map(p -> DozerConverter.parseObject(p, MovieVO.class));
        movieVOPage.map(p -> p.add(linkTo(methodOn(MovieController.class).readById(p.getKey())).withSelfRel()));
        return movieVOPage;
    }

    /**
     * Return a movie by id
     * 
     * @param Long
     * @return MovieVO
     */
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
    public MovieVO create(MovieVO movieVO) {
        
        var entity = DozerConverter.parseObject(movieVO, Movie.class);
        
        //Valid not nullable filds
        if (!validations.validEntity(entity)) {
            throw new InvalidParams("Invalid values for create entity Movie!");
        }
        
        //Persist new movie
        entity = repository.save(entity);
        
        //Save actors list for this movie
        createActors(entity);
        
        var vo = DozerConverter.parseObject(entity, MovieVO.class);
        vo.setActors(DozerConverter.parseList(movieVO.getActors(), ActorVO.class));
        vo.add(linkTo(methodOn(MovieController.class).readById(movieVO.getKey())).withSelfRel());
        return vo;
    }
    
    public void createActors( Movie movie) {
        
        List<Actor> actors = DozerConverter.parseList(movie.getActors(), Actor.class);
        actors.forEach(a -> a.setMovie(movie));
        actors.forEach(actorRepository::save);
        
    }

    /**
     * Delete a movie by id
     * 
     * @param Long
     */
    public void delete(Long id) {
        readById(id);
        repository.deleteById(id);
    }

    /**
     * Update a movie
     * 
     * @param MovieVO
     *                return MovieVO
     */
    public MovieVO update(Long id, MovieVO movieVO) {
        
        var entity = DozerConverter.parseObject(movieVO, Movie.class);
        
      //Valid not nullable filds
        if (!validations.validEntity(entity)) {
            throw new InvalidParams("Invalid values for create entity Movie!");
        }
        
        var vo = readById(id);

        vo.setTitle(movieVO.getTitle());
        vo.setDirector(movieVO.getDirector());
        vo.setGenre(movieVO.getGenre());
        vo.setVoteCount(movieVO.getVoteCount());
        vo.setVoteAverage(movieVO.getVoteAverage());
        entity = repository.save(DozerConverter.parseObject(vo, Movie.class));
        
        vo = DozerConverter.parseObject(entity, MovieVO.class);
        vo.add(linkTo(methodOn(MovieController.class).readById(movieVO.getKey())).withSelfRel());
        return vo;
    }

    /**
     * Return a page movie filter by params
     * 
     * @param movieVO
     * @return
     */
    public Page<MovieVO> findAllWithFilterAndPagination(QueryParams<Movie> queryParams) {

        var moviePage = repository.findAll(queryParams.createExample(), queryParams.pagination());

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
    public List<MovieVO> findAllWithFilter(QueryParams<Movie> queryParams) {

        List<MovieVO> movies = DozerConverter.parseList(repository.findAll(queryParams.createExample()), MovieVO.class);
        movies.stream()
                .forEach(p -> p.add(
                        linkTo(methodOn(MovieController.class).readById(p.getKey())).withSelfRel()));
        return movies;
    }

    public List<MovieVO> findAllByActor(String query) {
        return DozerConverter.parseList(repository.findByActor("%" + query + "%"), MovieVO.class);
    }

}
