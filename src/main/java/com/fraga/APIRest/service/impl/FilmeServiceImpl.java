package com.fraga.APIRest.service.impl;

import com.fraga.APIRest.data.enums.EFilmeParametro;
import com.fraga.APIRest.data.model.Ator;
import com.fraga.APIRest.data.model.Filme;
import com.fraga.APIRest.dto.FilmeObserverDTO;
import com.fraga.APIRest.dto.FilmeRequestDTO;
import com.fraga.APIRest.dto.FilmeResponseDTO;
import com.fraga.APIRest.exception.InvalidParams;
import com.fraga.APIRest.repository.FilmeRepository;
import com.fraga.APIRest.service.AtorService;
import com.fraga.APIRest.service.FilmeService;
import com.fraga.APIRest.util.PaginacaoUtils;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@org.springframework.stereotype.Service
public class FilmeServiceImpl implements FilmeService {

    private final FilmeRepository filmeRepository;
    private final AtorService atorService;
    private final ModelMapper mapper;

    public FilmeServiceImpl(FilmeRepository filmeRepository, AtorService atorService, ModelMapper mapper) {
        this.filmeRepository = filmeRepository;
        this.atorService = atorService;
        this.mapper = mapper;
    }


    @Override
    public Filme buscarFilmePorId(Long id) {
        return filmeRepository.findById(id).orElseThrow(() -> new InvalidParams("Filme não cadastrado."));
    }

    @Override
    public Page<FilmeResponseDTO> buscarFilmesOrdenadosPorMediaVotos(Integer pagina, Integer tamanho, String direcao) {
        Page<Filme> filmes = filmeRepository.findAll(PaginacaoUtils.gerarPaginacaoOrdenada(
                pagina, tamanho, EFilmeParametro.MEDIA.getDescricao(), direcao));
        return filmes.map(FilmeResponseDTO::new);
    }

    @Override
    public Page<FilmeResponseDTO> buscarFilmesOrdenadosPorParametro(Integer pagina,
                                                                    Integer tamanho,
                                                                    String ordenarPor) {
        if (!validarParametroOrdenacao(ordenarPor)) {
            throw new InvalidParams("Parametro de ordenação informado inválido");
        }
        Page<Filme> filmes = filmeRepository.findAll(PaginacaoUtils.gerarPaginacaoOrdenada(pagina, tamanho, ordenarPor));
        return filmes.map(FilmeResponseDTO::new);
    }

    private Boolean validarParametroOrdenacao(String ordenarPor) {
        return Arrays.stream(EFilmeParametro.values()).anyMatch(param -> param.getDescricao().equals(ordenarPor));
    }

    @Override
    public void update(String evento, FilmeObserverDTO dado) {
        Filme filme = buscarFilmePorId(dado.getIdFilme());

        if (Boolean.FALSE.equals(dado.getVotado())) {
            filme.setContagemVotos(filme.getContagemVotos() + 1);
        }
        filme.setMediaVotos(dado.getMediaVotos());
        filmeRepository.save(filme);
    }

    @Override
    public FilmeResponseDTO adicionarFilmes(FilmeRequestDTO filmeRequestDTO) {
        if (Objects.nonNull(buscarFilmePorTitulo(filmeRequestDTO.getTitulo(), false))) {
            throw new InvalidParams("Filme com título " + filmeRequestDTO.getTitulo() + " já cadastrado.");
        }
        List<Ator> atores = atorService.salvarListaAtores(filmeRequestDTO.getAtores());
        Filme filme = mapper.map(filmeRequestDTO, Filme.class);
        filme.setAtores(atores);
        return mapper.map(filmeRepository.save(filme), FilmeResponseDTO.class);
    }

    private Filme buscarFilmePorTitulo(String titulo, Boolean thowable) {
        return filmeRepository.buscarPorTitulo(titulo).orElseGet(() -> {
            if (thowable) {
                throw new InvalidParams("Filme com título " + titulo + " não cadastrado.");
            }
            return null;
        });
    }


    @Override
    public void deletarFilmesPorId(Long id) {
        Filme filme = buscarFilmePorId(id);
        filmeRepository.delete(filme);
    }

    @Override
    public FilmeResponseDTO atualizarFilmes(Long id, FilmeRequestDTO filmeRequestDTO) {
        Filme filme = buscarFilmePorId(id);
        List<Ator> atores = atorService.salvarListaAtores(filmeRequestDTO.getAtores());
        filme.setTitulo(filmeRequestDTO.getTitulo());
        filme.setDiretor(filmeRequestDTO.getDiretor());
        filme.setGenero(filmeRequestDTO.getGenero());
        filme.setDetalhes(filmeRequestDTO.getDetalhes());
        filme.setAtores(atores);
        return mapper.map(filmeRepository.save(filme), FilmeResponseDTO.class);
    }

    @Override
    public void atualizarAtoresFilme(Long id, List<Long> idsAtores) {
        Filme filme = buscarFilmePorId(id);
        List<Ator> atores = atorService.buscarAtoresPorId(idsAtores);
        filme.setAtores(atores);
        filmeRepository.save(filme);
    }

}
