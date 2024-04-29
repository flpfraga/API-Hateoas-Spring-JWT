package com.fraga.APIRest.service;

import com.fraga.APIRest.data.model.Ator;
import com.fraga.APIRest.data.model.Filme;
import com.fraga.APIRest.data.model.NotaUsuarioFilme;
import com.fraga.APIRest.data.model.Usuario;
import com.fraga.APIRest.mocks.AtorMock;
import com.fraga.APIRest.mocks.FilmeMock;
import com.fraga.APIRest.mocks.UsuarioMock;
import com.fraga.APIRest.repository.AtorRepository;
import com.fraga.APIRest.repository.NotaUsuarioFilmeRepository;
import com.fraga.APIRest.service.impl.AtorServiceImpl;
import com.fraga.APIRest.service.impl.NotaUsuarioFilmeServiceImpl;
import com.fraga.APIRest.service.observer.impl.NotaUsuarioFilmeObservableImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class NotaUsuarioServiceImplTest {

    @InjectMocks
    private NotaUsuarioFilmeServiceImpl notaUsuarioFilmeService;
    @Mock
    private UsuarioService usuarioService;
    @Mock
    private FilmeService filmeService;
    @Mock
    private NotaUsuarioFilmeObservableImpl notaUsuarioFilmeObservable;
    @Mock
    private NotaUsuarioFilmeRepository notaUsuarioFilmeRepository;
    @Spy
    private UsuarioMock usuarioMock;
    @Spy
    private FilmeMock filmeMock;

    @Before
    public void setUp(){
        this.usuarioMock = new UsuarioMock();
    }

    @Test
    public void testarAtualizarNotaFilme(){
        Usuario usuario = usuarioMock.getUsuario();
        when(usuarioService.buscarUsuarioPorId(anyLong())).thenReturn(usuario);

        Filme filme = filmeMock.getFilme();
        when(filmeService.buscarFilmePorId(anyLong())).thenReturn(filme);

        NotaUsuarioFilme notaUsuarioFilme = new NotaUsuarioFilme();
        notaUsuarioFilme.setFilme(filme);
        notaUsuarioFilme.setUsuario(usuario);

        when(notaUsuarioFilmeRepository.save(any())).thenReturn(notaUsuarioFilme);

        when(notaUsuarioFilmeRepository.findAll()).thenReturn(Collections.singletonList(notaUsuarioFilme));

    }

}
