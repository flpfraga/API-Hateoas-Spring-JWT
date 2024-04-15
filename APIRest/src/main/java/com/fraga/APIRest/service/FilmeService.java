package com.fraga.APIRest.service;

import com.fraga.APIRest.data.model.Filme;
import com.fraga.APIRest.dto.FilmeParametrosDTO;
import com.fraga.APIRest.dto.FilmeResponseDTO;
import com.fraga.APIRest.service.observer.FilmeObserver;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

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
     * Busca a contagem de votos que um filme recebeu
     *
     * @param id com valor do filme para buscar a contagem de votos
     */
    Long buscarNumeroDeVotosPorFilme(Long id);


    /**
     * Salva um filme na base de dados
     *
     * @param filme com objeto a ser salvo
     */
    void salvar(Filme filme);
}
