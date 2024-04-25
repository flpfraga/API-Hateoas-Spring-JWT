package com.fraga.APIRest.service.impl;

import com.fraga.APIRest.data.enums.EEventObserver;
import com.fraga.APIRest.data.model.Filme;
import com.fraga.APIRest.data.model.NotaUsuarioFilme;
import com.fraga.APIRest.data.model.Usuario;
import com.fraga.APIRest.dto.FilmeObserverDTO;
import com.fraga.APIRest.exception.InvalidParams;
import com.fraga.APIRest.repository.NotaUsuarioFilmeRepository;
import com.fraga.APIRest.service.FilmeService;
import com.fraga.APIRest.service.NotaUsuarioFilmeService;
import com.fraga.APIRest.service.UsuarioService;
import com.fraga.APIRest.service.observer.impl.NotaUsuarioFilmeObservableImpl;

import java.util.List;

@org.springframework.stereotype.Service
public class NotaUsuarioFilmeServiceImpl implements NotaUsuarioFilmeService {

    private final NotaUsuarioFilmeRepository notaUsuarioFilmeRepository;
    private final UsuarioService usuarioService;
    private final FilmeService filmeService;
    private final NotaUsuarioFilmeObservableImpl notaUsuarioFilmeObservable;

    public NotaUsuarioFilmeServiceImpl(NotaUsuarioFilmeRepository notaUsuarioFilmeRepository,
                                       UsuarioService usuarioService,
                                       FilmeService filmeService, NotaUsuarioFilmeObservableImpl notaUsuarioFilmeObservable) {
        this.notaUsuarioFilmeRepository = notaUsuarioFilmeRepository;
        this.usuarioService = usuarioService;
        this.filmeService = filmeService;
        this.notaUsuarioFilmeObservable = notaUsuarioFilmeObservable;
    }

    @Override
    public void atualizarNotaFilme(Long idUsuario, Long idFilme, Integer nota) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(idUsuario);
        Filme filme = filmeService.buscarFilmePorId(idFilme);
        NotaUsuarioFilme notaUsuarioFilme = notaUsuarioFilmeRepository.save(new NotaUsuarioFilme(usuario, filme, nota));

        notificar(notaUsuarioFilme.getUsuario(), filme);
    }

    private void notificar(Usuario usuario, Filme filme) {
        List<NotaUsuarioFilme> notasUsuariosFilme = notaUsuarioFilmeRepository.findAll();
        Double mediaVotos = notasUsuariosFilme.stream()
                .filter(notaUsuarioFilme -> filme.getId().equals(notaUsuarioFilme.getFilme().getId()))
                .map(NotaUsuarioFilme::getNota)
                .mapToDouble(Integer::doubleValue).average().orElseThrow(
                        () -> new InvalidParams("Não foi possível calcular a média de votos para o filme.")
                );
        notaUsuarioFilmeObservable.notifyObservers(EEventObserver.VOTO.name(),
                new FilmeObserverDTO(filme.getId(), mediaVotos, notasUsuariosFilme.stream().anyMatch(notaUsuarioFilme ->
                (usuario.getId().equals(notaUsuarioFilme.getUsuario().getId()) &&
                        filme.getId().equals(notaUsuarioFilme.getFilme().getId())))));

    }

    @Override
    public List<Integer> buscarTodasNotasPorFilme(Filme filme) {
        return notaUsuarioFilmeRepository.buscarTodasNotasPorFilme(filme);
    }

    @Override
    public List<NotaUsuarioFilme> buscarTodosNotasTodosFilmes() {
        return notaUsuarioFilmeRepository.findAll();
    }

    @Override
    public List<Filme> buscarFilmesVotadosPorUsuario(Usuario usuario) {
        return notaUsuarioFilmeRepository.buscarFilmesVotadosPorUsuario(usuario);
    }

}
