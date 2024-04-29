package com.fraga.APIRest.service;

import com.fraga.APIRest.data.model.Ator;
import com.fraga.APIRest.data.model.Filme;
import com.fraga.APIRest.dto.FilmeObserverDTO;
import com.fraga.APIRest.dto.FilmeRequestDTO;
import com.fraga.APIRest.dto.FilmeResponseDTO;
import com.fraga.APIRest.exception.InvalidParams;
import com.fraga.APIRest.mocks.AtorMock;
import com.fraga.APIRest.mocks.FilmeMock;
import com.fraga.APIRest.mocks.FilmeObserverMock;
import com.fraga.APIRest.repository.FilmeRepository;
import com.fraga.APIRest.service.impl.FilmeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class FilmeServiceImplTest {

    @InjectMocks
    private FilmeServiceImpl filmeService;
    @Mock
    private FilmeRepository filmeRepository;
    @Mock
    private ModelMapper mapper;
    @Mock
    private AtorService atorService;
    @Spy
    private AtorMock atorMock;
    @Spy
    private FilmeMock filmeMock;
    @Spy
    private FilmeObserverMock filmeObserverMock;

    @Before
    public void setUp() {
        atorMock = new AtorMock();
        filmeMock = new FilmeMock();
    }

    @Test
    public void testarBuscarPorIdListaComSucesso() {
        Filme filme = filmeMock.getFilme();
        when(filmeRepository.findById(anyLong())).thenReturn(Optional.of(filme));

        Filme retorno = filmeService.buscarFilmePorId(1L);
        assertEquals(filme.getTitulo(), retorno.getTitulo());
        assertEquals(filme.getDetalhes(), retorno.getDetalhes());
        assertEquals(filme.getGenero(), retorno.getGenero());
        assertEquals(filme.getDiretor(), retorno.getDiretor());
    }

    @Test(expected = InvalidParams.class)
    public void testarBuscarPorIdListaIdsVaziaExcessao() {
        when(filmeRepository.findById(anyLong())).thenReturn(Optional.empty());

        filmeService.buscarFilmePorId(1L);
    }

    @Test
    public void testarBuscarFilmesOrdenadosPelaMediaVotosAscendenteComSucesso() {
        Filme filme = filmeMock.getFilme();
        filme.setAtores(atorMock.getAtores());

        Page<Filme> paginaFilme = new PageImpl<>(Collections.singletonList(filme), Pageable.ofSize(1), 1L);

        when(filmeRepository.findAll((Pageable) any())).thenReturn(paginaFilme);

        Page<FilmeResponseDTO> listaRetorno = filmeService
                .buscarFilmesOrdenadosPorMediaVotos(0, 1, "Asc");

        assertEquals(listaRetorno.getTotalElements(), 1);
    }

    @Test
    public void testarBuscarFilmesOrdenadosPelaMediaVotosDescendenteComSucesso() {
        Filme filme = filmeMock.getFilme();
        filme.setAtores(atorMock.getAtores());

        Page<Filme> paginaFilme = new PageImpl<>(Collections.singletonList(filme), Pageable.ofSize(1), 1L);

        when(filmeRepository.findAll((Pageable) any())).thenReturn(paginaFilme);

        Page<FilmeResponseDTO> paginaRetorno = filmeService
                .buscarFilmesOrdenadosPorMediaVotos(0, 1, "Desc");

        assertEquals(paginaRetorno.getTotalElements(), 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testarBuscarFilmesOrdenadosPelaMediaVotosExcessaoUtilsPageable() {
        Filme filme = filmeMock.getFilme();
        filme.setAtores(atorMock.getAtores());

        Page<Filme> paginaFilme = new PageImpl<>(Collections.singletonList(filme), Pageable.ofSize(1), 1L);
        when(filmeRepository.findAll((Pageable) any())).thenReturn(paginaFilme);

        filmeService.buscarFilmesOrdenadosPorMediaVotos(-1, 1, anyString());
    }

    @Test
    public void testarBuscarFilmesOrdenadosPorParametroComSucesso() {
        Filme filme = filmeMock.getFilme();
        filme.setAtores(atorMock.getAtores());

        Page<Filme> paginaFilme = new PageImpl<>(Collections.singletonList(filme), Pageable.ofSize(1), 1L);
        when(filmeRepository.findAll((Pageable) any())).thenReturn(paginaFilme);

        Page<FilmeResponseDTO> paginaRetorno = filmeService
                .buscarFilmesOrdenadosPorParametro(1, 1, "Diretor");

        assertEquals(paginaRetorno.getTotalElements(), 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testarBuscarFilmesOrdenadosPorParametroExcessaaoUtilsPageable() {
        Filme filme = filmeMock.getFilme();
        filme.setAtores(atorMock.getAtores());

        Page<Filme> paginaFilme = new PageImpl<>(Collections.singletonList(filme), Pageable.ofSize(1), 1L);
        when(filmeRepository.findAll((Pageable) any())).thenReturn(paginaFilme);

        filmeService.buscarFilmesOrdenadosPorParametro(-1, 1, "Diretor");
    }

    @Test(expected = InvalidParams.class)
    public void testarBuscarFilmesOrdenadosPorParametroInvalidoExcessao() {
        filmeService.buscarFilmesOrdenadosPorParametro(1, 1, "Parametro Invalido");
    }

    @Test
    public void testarFazerUpdateFilmeVotadoComSucesso(){
        FilmeObserverDTO filmeObserverDTO = filmeObserverMock.getFilmeObserverDTO();

        Filme filme = filmeMock.getFilme();
        when(filmeRepository.findById(anyLong())).thenReturn(Optional.of(filme));

        when(filmeRepository.save(any())).thenReturn(filme);

        filmeService.update("evento", filmeObserverDTO);

        verify(filmeRepository, times(1)).save(filme);
    }

    @Test
    public void testarFazerUpdateFilmeNaoVotadoComSucesso(){
        FilmeObserverDTO filmeObserverDTO = filmeObserverMock.getFilmeObserverDTO();
        filmeObserverDTO.setVotado(false);

        Filme filme = filmeMock.getFilme();
        when(filmeRepository.findById(anyLong())).thenReturn(Optional.of(filme));
        when(filmeRepository.save(any())).thenReturn(filme);

        filme.setContagemVotos(filme.getContagemVotos()+1);

        filmeService.update("evento", filmeObserverDTO);

        verify(filmeRepository, times(1)).save(filme);
    }

    @Test
    public void testarAdicionarFilmeComSucesso(){
        Filme filme = filmeMock.getFilme();
        when(filmeRepository.buscarPorTitulo(anyString())).thenReturn(Optional.empty());

        List<Ator> atores = atorMock.getAtores();
        when(atorService.salvarListaAtores(anyList())).thenReturn(atores);
        when(mapper.map(any(), eq(Filme.class))).thenReturn(filme);

        FilmeResponseDTO filmeResponseDTO = filmeMock.getFilmeResponseDTO();
        when(mapper.map(any(), eq(FilmeResponseDTO.class))).thenReturn(filmeResponseDTO);

        FilmeResponseDTO retorno = filmeService.adicionarFilmes(filmeMock.getFilmeRequestDTO());

        assertEquals(filmeResponseDTO.getDiretor(), retorno.getDiretor());
        assertEquals(filmeResponseDTO.getTitulo(), retorno.getTitulo());
        assertEquals(filmeResponseDTO.getGenero(), retorno.getGenero());
    }

    @Test(expected = InvalidParams.class)
    public void testarAdicionarFilmeExcessaoFilmeJaCadastrado(){
        Filme filme = filmeMock.getFilme();
        when(filmeRepository.buscarPorTitulo(anyString())).thenReturn(Optional.of(filme));

       filmeService.adicionarFilmes(filmeMock.getFilmeRequestDTO());
    }

    @Test
    public void testarDeletarFilmeComSucesso(){
        Filme filme = filmeMock.getFilme();
        when(filmeRepository.findById(anyLong())).thenReturn(Optional.of(filme));
        doNothing().when(filmeRepository).delete(any());

        filmeService.deletarFilmesPorId(1L);

        verify(filmeRepository, times(1)).delete(filme);
    }

}
