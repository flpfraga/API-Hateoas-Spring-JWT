package com.fraga.APIRest.controller;

import com.fraga.APIRest.data.model.Filme;
import com.fraga.APIRest.dto.DefaultResponseDTO;
import com.fraga.APIRest.dto.FilmeResponseDTO;
import com.fraga.APIRest.service.FilmeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FilmesControllerTest {

    @Mock
    private FilmeService filmeService;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private FilmesController filmesController;

    private Filme filme;
    private FilmeResponseDTO filmeResponseDTO;
    private Page<Filme> filmePage;
    private Page<FilmeResponseDTO> filmeResponseDTOPage;

    @BeforeEach
    void setUp() {
        filme = new Filme();
        filme.setId(1L);
        filme.setTitulo("Test Movie");
        filme.setDiretor("Test Director");

        filmeResponseDTO = new FilmeResponseDTO();
        filmeResponseDTO.setId(1L);
        filmeResponseDTO.setTitulo("Test Movie");
        filmeResponseDTO.setDiretor("Test Director");

        List<Filme> filmes = Arrays.asList(filme);
        filmePage = new PageImpl<>(filmes);

        List<FilmeResponseDTO> filmesDTO = Arrays.asList(filmeResponseDTO);
        filmeResponseDTOPage = new PageImpl<>(filmesDTO);
    }

    @Test
    void whenBuscarFilmePorId_thenReturnFilme() {
        // Arrange
        when(filmeService.buscarFilmePorId(1L)).thenReturn(filme);
        when(mapper.map(filme, FilmeResponseDTO.class)).thenReturn(filmeResponseDTO);

        // Act
        ResponseEntity<DefaultResponseDTO<FilmeResponseDTO>> response = filmesController.buscarFilmePorId(1L);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(filmeResponseDTO.getId(), response.getBody().getResposta().getId());
        assertEquals(filmeResponseDTO.getTitulo(), response.getBody().getResposta().getTitulo());
        
        verify(filmeService, times(1)).buscarFilmePorId(1L);
        verify(mapper, times(1)).map(filme, FilmeResponseDTO.class);
    }

    @Test
    void whenBuscarFilmesOrdenadosPorParametro_thenReturnPage() {
        // Arrange
        when(filmeService.buscarFilmesOrdenadosPorParametro(0, 10, "titulo"))
                .thenReturn(filmeResponseDTOPage);

        // Act
        ResponseEntity<DefaultResponseDTO<Page<FilmeResponseDTO>>> response = 
                filmesController.buscarFilmesOrdenadosPorParametro(0, 10, "titulo");

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getResposta());
        assertEquals(1, response.getBody().getResposta().getTotalElements());
        
        verify(filmeService, times(1)).buscarFilmesOrdenadosPorParametro(0, 10, "titulo");
    }

    @Test
    void whenBuscarOrdenadosMediaVotos_thenReturnPage() {
        // Arrange
        when(filmeService.buscarFilmesOrdenadosPorMediaVotos(0, 10, "Desc"))
                .thenReturn(filmeResponseDTOPage);

        // Act
        ResponseEntity<DefaultResponseDTO<Page<FilmeResponseDTO>>> response = 
                filmesController.buscarOrdenadosMediaVotos(0, 10, "Desc");

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getResposta());
        assertEquals(1, response.getBody().getResposta().getTotalElements());
        
        verify(filmeService, times(1)).buscarFilmesOrdenadosPorMediaVotos(0, 10, "Desc");
    }

    @Test
    void whenBuscarFilmesOrdenadosPorParametroWithNullParams_thenReturnPage() {
        // Arrange
        when(filmeService.buscarFilmesOrdenadosPorParametro(null, null, null))
                .thenReturn(filmeResponseDTOPage);

        // Act
        ResponseEntity<DefaultResponseDTO<Page<FilmeResponseDTO>>> response = 
                filmesController.buscarFilmesOrdenadosPorParametro(null, null, null);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getResposta());
        assertEquals(1, response.getBody().getResposta().getTotalElements());
        
        verify(filmeService, times(1)).buscarFilmesOrdenadosPorParametro(null, null, null);
    }
} 