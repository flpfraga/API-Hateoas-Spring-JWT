package com.fraga.APIRest.controller;

import com.fraga.APIRest.data.model.Usuario;
import com.fraga.APIRest.dto.DefaultResponseDTO;
import com.fraga.APIRest.dto.UsuarioAtualizarDTO;
import com.fraga.APIRest.dto.UsuarioRequestDTO;
import com.fraga.APIRest.dto.UsuarioResponseDTO;
import com.fraga.APIRest.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private UsuarioController usuarioController;

    private Usuario usuario;
    private UsuarioResponseDTO usuarioResponseDTO;
    private UsuarioRequestDTO usuarioRequestDTO;
    private UsuarioAtualizarDTO usuarioAtualizarDTO;
    private Page<UsuarioResponseDTO> usuarioPage;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNomeUsuario("testuser");
        usuario.setNomeCompleto("Test User");

        usuarioResponseDTO = new UsuarioResponseDTO();
        usuarioResponseDTO.setNomeUsuario("testuser");
        usuarioResponseDTO.setNomeCompleto("Test User");

        usuarioRequestDTO = new UsuarioRequestDTO();
        usuarioRequestDTO.setNomeUsuario("newuser");
        usuarioRequestDTO.setNomeCompleto("New User");
        usuarioRequestDTO.setSenha("password");

        usuarioAtualizarDTO = new UsuarioAtualizarDTO();
        usuarioAtualizarDTO.setNomeCompleto("Updated User");

        List<UsuarioResponseDTO> usuarios = Arrays.asList(usuarioResponseDTO);
        usuarioPage = new PageImpl<>(usuarios);
    }

    @Test
    void whenBuscarTodosUsuarios_thenReturnPage() {
        // Arrange
        when(usuarioService.buscarTodosUsuarios(0, 10)).thenReturn(usuarioPage);

        // Act
        ResponseEntity<DefaultResponseDTO<Page<UsuarioResponseDTO>>> response = 
                usuarioController.buscarTodosUsuarios(0, 10);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getResposta());
        assertEquals(1, response.getBody().getResposta().getTotalElements());
        
        verify(usuarioService, times(1)).buscarTodosUsuarios(0, 10);
    }

    @Test
    void whenBuscarUsuarioPorId_thenReturnUsuario() {
        // Arrange
        when(usuarioService.buscarUsuarioPorId(1L)).thenReturn(usuario);
        when(mapper.map(usuario, UsuarioResponseDTO.class)).thenReturn(usuarioResponseDTO);

        // Act
        ResponseEntity<DefaultResponseDTO<UsuarioResponseDTO>> response = 
                usuarioController.buscarUsuarioPorId(1L);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(usuarioResponseDTO.getNomeUsuario(), response.getBody().getResposta().getNomeUsuario());
        assertEquals(usuarioResponseDTO.getNomeCompleto(), response.getBody().getResposta().getNomeCompleto());
        
        verify(usuarioService, times(1)).buscarUsuarioPorId(1L);
        verify(mapper, times(1)).map(usuario, UsuarioResponseDTO.class);
    }

    @Test
    void whenBuscarUsuarioPorNome_thenReturnUsuario() {
        // Arrange
        when(usuarioService.buscarUsuarioPorNome("testuser")).thenReturn(usuarioResponseDTO);

        // Act
        ResponseEntity<DefaultResponseDTO<UsuarioResponseDTO>> response = 
                usuarioController.buscarUsuarioPorNome("testuser");

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(usuarioResponseDTO.getNomeUsuario(), response.getBody().getResposta().getNomeUsuario());
        assertEquals(usuarioResponseDTO.getNomeCompleto(), response.getBody().getResposta().getNomeCompleto());
        
        verify(usuarioService, times(1)).buscarUsuarioPorNome("testuser");
    }

    @Test
    void whenCriarNovoUsuario_thenReturnUsuario() {
        // Arrange
        when(usuarioService.criarNovoUsuario(usuarioRequestDTO)).thenReturn(usuarioResponseDTO);

        // Act
        ResponseEntity<DefaultResponseDTO<UsuarioResponseDTO>> response = 
                usuarioController.criarNovoUsuario(usuarioRequestDTO);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(usuarioResponseDTO.getNomeUsuario(), response.getBody().getResposta().getNomeUsuario());
        assertEquals(usuarioResponseDTO.getNomeCompleto(), response.getBody().getResposta().getNomeCompleto());
        
        verify(usuarioService, times(1)).criarNovoUsuario(usuarioRequestDTO);
    }

    @Test
    void whenAtualizarUsuario_thenReturnUsuario() {
        // Arrange
        when(usuarioService.atualizarUsuario(1L, usuarioAtualizarDTO)).thenReturn(usuarioResponseDTO);

        // Act
        ResponseEntity<DefaultResponseDTO<UsuarioResponseDTO>> response = 
                usuarioController.atualizarUsuario(1L, usuarioAtualizarDTO);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(usuarioResponseDTO.getNomeUsuario(), response.getBody().getResposta().getNomeUsuario());
        assertEquals(usuarioResponseDTO.getNomeCompleto(), response.getBody().getResposta().getNomeCompleto());
        
        verify(usuarioService, times(1)).atualizarUsuario(1L, usuarioAtualizarDTO);
    }

    @Test
    void whenDesativarUsuario_thenReturnSuccessMessage() {
        // Arrange
        doNothing().when(usuarioService).desativarUsuario(1L);

        // Act
        ResponseEntity<DefaultResponseDTO<String>> response = 
                usuarioController.desativarUsuario(1L);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("Usuário desatiado com sucesso.", response.getBody().getResposta());
        
        verify(usuarioService, times(1)).desativarUsuario(1L);
    }
} 