package com.fraga.APIRest.service;

import com.fraga.APIRest.data.model.Filme;
import com.fraga.APIRest.dto.FilmeRequestDTO;
import com.fraga.APIRest.dto.FilmeResponseDTO;
import com.fraga.APIRest.service.observer.FilmeObserver;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FilmeService extends FilmeObserver {


    /**
     * Buscar um filme pelo id informado
     *
     * @param id com valor do id do filme a ser buscado
     * @return Filme
     */
    Filme buscarFilmePorId(Long id);


    /**
     * Buscar todos os filmes ordenados pela média de votos paginados
     *
     * @param pagina com valor da página a retornar
     * @param tamanho com valor do tamanho da página
     * @param direcao com valor de ordenacao crescente ou descrescente
     * @return Page<FilmeResponseDTO>
     */
    Page<FilmeResponseDTO> buscarFilmesOrdenadosPorMediaVotos(Integer pagina, Integer tamanho, String direcao);


    /**
     * Buscar todos os filmes paginados e ordenados de forma alfabética por um dos parâmetros
     *
     * @param pagina com valor da página a retornar
     * @param tamanho com valor do tamanho da página
     * @param ordenarPor com nome do parâmetro base para ordenação
     * @return Page<FilmeResponseDTO>
     */
    Page<FilmeResponseDTO> buscarFilmesOrdenadosPorParametro(Integer pagina,
                                                             Integer tamanho,
                                                             String ordenarPor);

    /**
     * Adicionar um filme a coleção
     *
     * @param filmeRequestDTO com novo filme a ser adicionado
     * @return FilmeResponseDTO
     */
    FilmeResponseDTO adicionarFilmes(FilmeRequestDTO filmeRequestDTO);

    /**
     * Adicionar um filme a coleção
     *
     * @param id com o valor do identificador do filme a ser deletado
     */
    void deletarFilmesPorId(Long id);

    /**
     * Adicionar um filme a coleção
     *
     * @param id              com o valor do identificador do filme a ser atualizado
     * @param filmeRequestDTO com os valores do filme a ser atualizado
     * @return FilmeResponseDTO
     */
    FilmeResponseDTO atualizarFilmes(Long id, FilmeRequestDTO filmeRequestDTO);


    /**
     * Atualiza lista de atores de um filme
     *
     * @param id              com o valor do identificador do filme a ser atualizado
     * @param idsAtores com ids dos atores
     */
    void atualizarAtoresFilme(Long id, List<Long> idsAtores);
}
