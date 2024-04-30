package com.fraga.APIRest.service;

import com.fraga.APIRest.data.enums.EPermissaoUsuario;
import com.fraga.APIRest.data.model.Permission;
import com.fraga.APIRest.data.model.Usuario;
import com.fraga.APIRest.dto.UsuarioResponseDTO;
import com.fraga.APIRest.exception.BadCredentialsException;
import com.fraga.APIRest.exception.InvalidJwtAuthenticationException;
import com.fraga.APIRest.exception.InvalidParams;
import com.fraga.APIRest.mocks.FilmeMock;
import com.fraga.APIRest.mocks.UsuarioMock;
import com.fraga.APIRest.repository.UsuarioRepository;
import com.fraga.APIRest.service.impl.UsuarioServiceImpl;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class UsuarioServiceImplTest {
    @InjectMocks
    private UsuarioServiceImpl usuarioService;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private ModelMapper mapper;
    @Spy
    private UsuarioMock usuarioMock;

    @Before
    public void setUp() {
        this.usuarioMock = new UsuarioMock();
    }

    @Test
    public void testarBuscarTodosUsuariosComSucesso() {
        Page<Usuario> pageUsuarios = new PageImpl<>(usuarioMock.getUsuarios(), Pageable.ofSize(1), 10);
        when(usuarioRepository.findAll((Pageable) any())).thenReturn(pageUsuarios);

        Page<UsuarioResponseDTO> pageRetorno = usuarioService.buscarTodosUsuarios(1, 1);
        assertEquals(10, pageRetorno.getTotalElements());
        assertEquals(2, pageRetorno.getContent().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testarBuscarTodosUsuariosExcessaoPaginable() {
        Page<Usuario> pageUsuarios = new PageImpl<>(usuarioMock.getUsuarios(), Pageable.ofSize(1), 10);
        when(usuarioRepository.findAll((Pageable) any())).thenReturn(pageUsuarios);

        usuarioService.buscarTodosUsuarios(-1, 1);
    }

    @Test
    public void testarBuscarPorIdComSucesso() {
        Usuario usuario = usuarioMock.getUsuario();
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));

        Usuario retorno = usuarioService.buscarUsuarioPorId(1L);
        assertEquals(usuario, retorno);
    }

    @Test(expected = InvalidParams.class)
    public void testarBuscarPorIdExcessao() {
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());
        usuarioService.buscarUsuarioPorId(1L);
    }

    @Test
    public void testarCriarNovoUsuarioComSucesso() {
        Usuario usuario = usuarioMock.getUsuario();
        when(usuarioRepository.buscarPorNomeUsuario(anyString())).thenReturn(Optional.empty());

        when(mapper.map(any(), eq(Usuario.class))).thenReturn(usuario);

        UsuarioResponseDTO usuarioResponseDTO = usuarioMock.getUsuarioResponseDTO();
        when(mapper.map(any(), eq(UsuarioResponseDTO.class))).thenReturn(usuarioResponseDTO);

        UsuarioResponseDTO retorno = usuarioService.criarNovoUsuario(usuarioMock.getUsuarioRequestDTO());

        assertEquals(usuarioResponseDTO, retorno);
    }

    @Test(expected = InvalidParams.class)
    public void testarCriarNovoUsuarioExcessaoUsuarioJaCadastrado() {
        when(usuarioRepository.buscarPorNomeUsuario(anyString())).thenReturn(Optional.of(usuarioMock.getUsuario()));

        usuarioService.criarNovoUsuario(usuarioMock.getUsuarioRequestDTO());
    }

    @Test
    public void testarBuscarPorNomeComSucesso(){
        Usuario usuario = usuarioMock.getUsuario();
        when(usuarioRepository.buscarPorNomeUsuario(anyString())).thenReturn(Optional.of(usuario));

        UsuarioResponseDTO usuarioResponseDTO = usuarioMock.getUsuarioResponseDTO();
        when(mapper.map(any(), eq(UsuarioResponseDTO.class))).thenReturn(usuarioResponseDTO);

        UsuarioResponseDTO retorno = usuarioService.buscarUsuarioPorNome("nome");

        assertEquals(usuarioResponseDTO, retorno);
    }

    @Test(expected = InvalidParams.class)
    public void testarBuscarPorNomeExcessaoParametroNomeNulo(){
        UsuarioResponseDTO retorno = usuarioService.buscarUsuarioPorNome(null);
    }

    @Test(expected = BadCredentialsException.class)
    public void testarBuscarPorNomeExcessaoUsuarioNaoEncontrado(){
        when(usuarioRepository.buscarPorNomeUsuario(anyString())).thenReturn(Optional.empty());

        usuarioService.buscarUsuarioPorNome("nome");
    }

    @Test
    public void testarAtualizarUsuarioComSucesso() {
        Usuario usuario = usuarioMock.getUsuario();
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));

        UsuarioResponseDTO usuarioResponseDTO = usuarioMock.getUsuarioResponseDTO();
        when(mapper.map(any(), eq(UsuarioResponseDTO.class))).thenReturn(usuarioResponseDTO);

        UsuarioResponseDTO retorno = usuarioService.atualizarUsuario(1L, usuarioMock.getUsuarioAtualizarDTO());

        assertEquals(usuarioResponseDTO, retorno);
    }

    @Test
    public void testarDesativarUusario(){
        Usuario usuario = usuarioMock.getUsuario();
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));
        usuario.setActive(false);

        usuarioService.desativarUsuario(1L);
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    public void testarBuscarTodosUsuariosAtivos(){
        Page<Usuario> pageUsuarios = new PageImpl<>(usuarioMock.getUsuarios(), Pageable.ofSize(1), 10);
        when(usuarioRepository.buscarUsuariosAtivos(any())).thenReturn(pageUsuarios);

        Page<UsuarioResponseDTO> pageRetorno = usuarioService.buscarTodosUsuariosAtivos(1, 1);
        assertEquals(10, pageRetorno.getTotalElements());
        assertEquals(2, pageRetorno.getContent().size());
    }

    @Test
    public void testarLoadByUserNameComSucesso(){
        Usuario usuario = usuarioMock.getUsuario();
        when(usuarioRepository.loadUserByUsername(anyString())).thenReturn(usuario);

        UserDetails retorno = usuarioService.loadUserByUsername("usuario");

        assertEquals(usuario.getNomeUsuario(), retorno.getUsername());
        assertEquals(usuario.getSenha(), retorno.getPassword());
    }

    @Test(expected = InvalidJwtAuthenticationException.class)
    public void testarLoadByUserNameExceptionUsernameNaoEncontrado(){
        when(usuarioRepository.loadUserByUsername(anyString())).thenReturn(null);

        usuarioService.loadUserByUsername("usuario");
    }

    @Test
    public void testarAtualizarUsuarioAdmin(){
        Usuario usuario = usuarioMock.getUsuario();
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));

        UsuarioResponseDTO usuarioResponseDTO = usuarioMock.getUsuarioResponseDTO();
        when(mapper.map(any(), eq(UsuarioResponseDTO.class))).thenReturn(usuarioResponseDTO);

        usuario.getPermissions().add(new Permission(
                EPermissaoUsuario.ADMIN.getId(), EPermissaoUsuario.ADMIN.getPermissao()));
        usuarioService.atualizarUsuarioAdmin(1L);

        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    public void testarAtualizarUsuarioCommon(){
        Usuario usuario = usuarioMock.getUsuario2();
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));

        UsuarioResponseDTO usuarioResponseDTO = usuarioMock.getUsuarioResponseDTO();
        when(mapper.map(any(), eq(UsuarioResponseDTO.class))).thenReturn(usuarioResponseDTO);

        usuario.setPermissions(List.of(new Permission(
                EPermissaoUsuario.COMMON_USER.getId(), EPermissaoUsuario.COMMON_USER.getPermissao())));
        usuarioService.atualizarUsuarioCommon(1L);

        verify(usuarioRepository, times(1)).save(usuario);
    }


}
