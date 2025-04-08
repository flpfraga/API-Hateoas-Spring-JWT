package com.fraga.APIRest.service;

import com.fraga.APIRest.data.model.Filme;
import com.fraga.APIRest.data.model.NotaUsuarioFilme;
import com.fraga.APIRest.data.model.Usuario;
import com.fraga.APIRest.exception.InvalidParams;
import com.fraga.APIRest.mocks.FilmeMock;
import com.fraga.APIRest.mocks.UsuarioMock;
import com.fraga.APIRest.repository.NotaUsuarioFilmeRepository;
import com.fraga.APIRest.service.impl.NotaUsuarioFilmeServiceImpl;
import com.fraga.APIRest.service.observer.impl.NotaUsuarioFilmeObservableImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

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
        this.filmeMock = new FilmeMock();
        this.usuarioMock = new UsuarioMock();
    }

    @Test
    public void testarAtualizarNotaFilmeComSucesso(){
        Usuario usuario = usuarioMock.getUsuario();
        when(usuarioService.buscarUsuarioPorId(anyLong())).thenReturn(usuario);

        Filme filme = filmeMock.getFilme();
        when(filmeService.buscarFilmePorId(anyLong())).thenReturn(filme);

        NotaUsuarioFilme notaUsuarioFilme = new NotaUsuarioFilme();
        notaUsuarioFilme.setFilme(filme);
        notaUsuarioFilme.setUsuario(usuario);
        notaUsuarioFilme.setNota(4);

        when(notaUsuarioFilmeRepository.save(any())).thenReturn(notaUsuarioFilme);

        when(notaUsuarioFilmeRepository.findAll()).thenReturn(Collections.singletonList(notaUsuarioFilme));

        notaUsuarioFilmeService.atualizarNotaFilme(1L, 1L, 4);

        verify(notaUsuarioFilmeObservable, times(1))
                .notifyObservers(anyString(), any());
    }

    @Test(expected = InvalidParams.class)
    public void testarAtualizarNotaFilmeExcessaoCalcularMediaNotas(){
        Usuario usuario = usuarioMock.getUsuario();
        when(usuarioService.buscarUsuarioPorId(anyLong())).thenReturn(usuario);

        Filme filme = filmeMock.getFilme();
        when(filmeService.buscarFilmePorId(anyLong())).thenReturn(filme);

        NotaUsuarioFilme notaUsuarioFilme = new NotaUsuarioFilme();
        notaUsuarioFilme.setFilme(filme);
        notaUsuarioFilme.setUsuario(usuario);

        when(notaUsuarioFilmeRepository.save(any())).thenReturn(notaUsuarioFilme);

        when(notaUsuarioFilmeRepository.findAll()).thenReturn(Collections.emptyList());

        notaUsuarioFilmeService.atualizarNotaFilme(1L, 1L, 4);
    }

    @Test
    public void testarBuscarTodasNotasPorFilme(){
        when(notaUsuarioFilmeRepository.buscarTodasNotasPorFilme(any())).thenReturn(Arrays.asList(1,2,3));

        List<Integer> listaRetorno = notaUsuarioFilmeService.buscarTodasNotasPorFilme(any());

        assertEquals(3, listaRetorno.size());
    }

    @Test
    public void testarBuscarFilmeVotadosPorUsuario(){
        Usuario usuario = new Usuario();

        when(notaUsuarioFilmeRepository.buscarFilmesVotadosPorUsuario(usuario)).thenReturn(Collections.singletonList(filmeMock.getFilme()));

        List<Filme> listaRetorno = notaUsuarioFilmeService.buscarFilmesVotadosPorUsuario(usuario);

        assertEquals(1, listaRetorno.size());
    }
}
