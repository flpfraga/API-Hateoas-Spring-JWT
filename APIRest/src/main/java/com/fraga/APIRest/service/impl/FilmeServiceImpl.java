package com.fraga.APIRest.service.impl;

import com.fraga.APIRest.data.enums.EFilmeParametro;
import com.fraga.APIRest.data.model.Filme;
import com.fraga.APIRest.dto.FilmeParametrosDTO;
import com.fraga.APIRest.dto.FilmeResponseDTO;
import com.fraga.APIRest.exception.InvalidParams;
import com.fraga.APIRest.exception.ResourceNotFoundException;
import com.fraga.APIRest.repository.AtorRepository;
import com.fraga.APIRest.repository.FilmeRepository;
import com.fraga.APIRest.service.NotaUsuarioFilmeService;
import com.fraga.APIRest.service.UsuarioService;
import com.fraga.APIRest.util.PaginacaoUtils;
import com.fraga.APIRest.util.validation.SimpleValidations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class FilmeServiceImpl implements com.fraga.APIRest.service.FilmeService {

    private final FilmeRepository filmeRepository;

    private final NotaUsuarioFilmeService notaUsuarioFilmeService;

    public FilmeServiceImpl(FilmeRepository filmeRepository,
                            NotaUsuarioFilmeService notaUsuarioFilmeService) {
        this.filmeRepository = filmeRepository;
        this.notaUsuarioFilmeService = notaUsuarioFilmeService;
    }


    public void atualizarNotaFilme(Filme filme, Long nota) {

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
    public void atualizarMediaVotosPorFilme(Long idFilme) {
        Filme filme = buscarFilmePorId(idFilme);
        Double mediaVotos = notaUsuarioFilmeService.buscarTodasNotasPorFilme(filme).stream()
                .mapToDouble(Integer::doubleValue).average().orElseThrow(
                        () -> new ResourceNotFoundException("Não foi possível calcular a média de votos para o filme.")
                );
        filme.setMediaVotos(mediaVotos);
        filmeRepository.save(filme);
    }


}
