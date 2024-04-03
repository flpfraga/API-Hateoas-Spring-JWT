package com.fraga.APIRest.service.impl;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

import com.fraga.APIRest.dto.FilmeParametrosDTO;
import com.fraga.APIRest.dto.FilmeResponseDTO;
import com.fraga.APIRest.service.FilmeService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.fraga.APIRest.controller.FilmesController;
import com.fraga.APIRest.data.model.Ator;
import com.fraga.APIRest.data.model.Filme;
import com.fraga.APIRest.data.vo.ActorVO;
import com.fraga.APIRest.data.vo.MovieVO;
import com.fraga.APIRest.exception.InvalidParams;
import com.fraga.APIRest.exception.ResourceNotFoundException;
import com.fraga.APIRest.repository.AtorRepository;
import com.fraga.APIRest.repository.FilmeRepository;
import com.fraga.APIRest.util.queryManager.QueryParams;
import com.fraga.APIRest.util.validation.SimpleValidations;

@Service
public class FilmeServiceImpl implements FilmeService {

    private final FilmeRepository filmeRepository;
    private final AtorRepository atorRepository;
    private final SimpleValidations<Filme> validations;
    private final ModelMapper mapper;

    public FilmeServiceImpl(FilmeRepository filmeRepository,
                            AtorRepository atorRepository,
                            SimpleValidations<Filme> validations,
                            ModelMapper mapper) {
        this.filmeRepository = filmeRepository;
        this.atorRepository = atorRepository;
        this.validations = validations;
        this.mapper = mapper;
    }

//    public List<MovieVO> readAllInOrder(QueryParams<Filme> queryParams) {
//
//        List<MovieVO> movies = DozerConverter.parseList(filmeRepository.findAll(queryParams.inOrder()), MovieVO.class);
//        movies.stream()
//        .forEach(p -> p.add(
//                linkTo(methodOn(FilmesController.class).readById(p.getKey())).withSelfRel()));
//        return movies;
//    }
//
//
//    public Page<MovieVO> readAllInOrderPagined(QueryParams<Filme> queryParams) {
//
//        var moviePage = filmeRepository.findAll(queryParams.paginationWithSort());
//        var movieVOPage = moviePage.map(p -> DozerConverter.parseObject(p, MovieVO.class));
//        movieVOPage.map(p -> p.add(linkTo(methodOn(FilmesController.class).readById(p.getKey())).withSelfRel()));
//        return movieVOPage;
//    }
//
//
//    public MovieVO readById(Long id) {
//        var entity = filmeRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("No records matches for this ID!"));
//        MovieVO vo = DozerConverter.parseObject(entity, MovieVO.class);
//        vo.add(linkTo(methodOn(FilmesController.class).readById(id)).withSelfRel());
//        return vo;
//    }
//
//
//    public MovieVO create(MovieVO movieVO) {
//
//        var entity = DozerConverter.parseObject(movieVO, Filme.class);
//
//        //Valid not nullable filds
//        if (!validations.validEntity(entity)) {
//            throw new InvalidParams("Invalid values for create entity Movie!");
//        }
//
//        //Persist new movie
//        entity = filmeRepository.save(entity);
//
//        //Save actors list for this movie
//        createActors(entity);
//
//        var vo = DozerConverter.parseObject(entity, MovieVO.class);
//        vo.setActors(DozerConverter.parseList(movieVO.getActors(), ActorVO.class));
//        vo.add(linkTo(methodOn(FilmesController.class).readById(movieVO.getKey())).withSelfRel());
//        return vo;
//    }
//
//    public void createActors( Filme movie) {
//
//        List<Ator> actors = DozerConverter.parseList(movie.getActors(), Ator.class);
//        actors.forEach(a -> a.setMovie(movie));
//        atorRepository.saveAll(actors);
//
//    }
//
//
//    public void delete(Long id) {
//        readById(id);
//        filmeRepository.deleteById(id);
//    }
//
//
//    public MovieVO update(Long id, MovieVO movieVO) {
//
//        var entity = DozerConverter.parseObject(movieVO, Filme.class);
//
//      //Valid not nullable filds
//        if (!validations.validEntity(entity)) {
//            throw new InvalidParams("Invalid values for create entity Movie!");
//        }
//
//        var vo = readById(id);
//
//        vo.setTitle(movieVO.getTitle());
//        vo.setDirector(movieVO.getDirector());
//        vo.setGenre(movieVO.getGenre());
//        vo.setVoteCount(movieVO.getVoteCount());
//        vo.setVoteAverage(movieVO.getVoteAverage());
//        entity = filmeRepository.save(DozerConverter.parseObject(vo, Filme.class));
//
//        vo = DozerConverter.parseObject(entity, MovieVO.class);
//        vo.add(linkTo(methodOn(FilmesController.class).readById(movieVO.getKey())).withSelfRel());
//        return vo;
//    }
//
//
//    public Page<MovieVO> findAllWithFilterAndPagination(QueryParams<Filme> queryParams) {
//
//        var moviePage = filmeRepository.findAll(queryParams.createExample(), queryParams.pagination());
//
//        var movieVosPage = moviePage.map(p -> DozerConverter.parseObject(p, MovieVO.class));
//
//        movieVosPage.map(p -> p.add(linkTo(methodOn(FilmesController.class).readById(p.getKey())).withSelfRel()));
//
//        return movieVosPage;
//    }
//
//
//    public List<MovieVO> findAllWithFilter(QueryParams<Filme> queryParams) {
//
//        List<MovieVO> movies = DozerConverter.parseList(filmeRepository.findAll(queryParams.createExample()), MovieVO.class);
//        movies.forEach(p -> p.add(
//                        linkTo(methodOn(FilmesController.class).readById(p.getKey())).withSelfRel()));
//        return movies;
//    }
//


    @Override
    public FilmeResponseDTO buscarFilmePorId(Long id) {
        Optional<Filme> filme = filmeRepository.findById(id);

        if(filme.isPresent()){
            return mapper.map(filme.get(), FilmeResponseDTO.class);
        }
        throw new ResourceNotFoundException("Não foi encontrado filmes com o id informado.");
    }

    @Override
    public Page<FilmeResponseDTO> buscarFilmeOrdemInsercaoPaginado(Integer page, Integer size) {
        return null;
    }

    @Override
    public List<FilmeResponseDTO> buscarFilmesOrdenadosPorMediaVotos() {
        return null;
    }

    @Override
    public Page<FilmeResponseDTO> buscarFilmesPorParametros(FilmeParametrosDTO filmeParametrosDTO) {
        return null;
    }
}
