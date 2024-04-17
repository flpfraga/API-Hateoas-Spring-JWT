package com.fraga.APIRest.service.impl;

import com.fraga.APIRest.data.enums.EFilmeParametro;
import com.fraga.APIRest.data.model.Filme;
import com.fraga.APIRest.dto.FilmeResponseDTO;
import com.fraga.APIRest.exception.InvalidParams;
import com.fraga.APIRest.exception.ResourceNotFoundException;
import com.fraga.APIRest.repository.FilmeRepository;
import com.fraga.APIRest.service.FilmeService;
import com.fraga.APIRest.util.PaginacaoUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class FilmeServiceImpl implements FilmeService {

    private final FilmeRepository filmeRepository;

    public FilmeServiceImpl(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }


    @Override
    public Filme buscarFilmePorId(Long id) {
        Optional<Filme> filme = filmeRepository.findById(id);
        if (filme.isPresent()) {
            return filme.get();
        }
        throw new ResourceNotFoundException("Não foi encontrado filmes com o id informado.");
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
    public Long buscarNumeroDeVotosPorFilme(Long id){
        return buscarFilmePorId(id).getContagemVotos();
    }

    @Override
    public void salvar(Filme filme){
        filmeRepository.save(filme);
    }


    @Override
    public void atualizaNotaFilme(Long idFilme, Double mediaVotos, Boolean isVotado) {
        Filme filme = buscarFilmePorId(idFilme);

        if(Boolean.TRUE.equals(isVotado)){
            filme.setContagemVotos(filme.getContagemVotos() + 1);
        }
        filme.setMediaVotos(mediaVotos);
        filmeRepository.save(filme);
    }

}
