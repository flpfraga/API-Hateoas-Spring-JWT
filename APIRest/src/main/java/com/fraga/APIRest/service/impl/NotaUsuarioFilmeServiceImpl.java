package com.fraga.APIRest.service.impl;

import com.fraga.APIRest.data.model.Filme;
import com.fraga.APIRest.data.model.NotaUsuarioFilme;
import com.fraga.APIRest.data.model.Usuario;
import com.fraga.APIRest.repository.NotaUsuarioFilmeRepository;
import com.fraga.APIRest.service.FilmeService;
import com.fraga.APIRest.service.NotaUsuarioFilmeService;
import com.fraga.APIRest.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotaUsuarioFilmeServiceImpl implements NotaUsuarioFilmeService {

    private final NotaUsuarioFilmeRepository notaUsuarioFilmeRepository;
    private final UsuarioService usuarioService;
    private final FilmeService filmeService;

    public NotaUsuarioFilmeServiceImpl(NotaUsuarioFilmeRepository notaUsuarioFilmeRepository,
                                       UsuarioService usuarioService,
                                       FilmeService filmeService) {
        this.notaUsuarioFilmeRepository = notaUsuarioFilmeRepository;
        this.usuarioService = usuarioService;
        this.filmeService = filmeService;
    }

    @Override
    public void atualizarNotaFilme(Long idUsuario, Long idFilme, Integer nota){
        Usuario usuario = usuarioService.buscarUsuarioPorId(idUsuario);
        Filme filme = filmeService.buscarFilmePorId(idFilme);

        notaUsuarioFilmeRepository.save(new NotaUsuarioFilme(usuario, filme, nota));
    }

    @Override
    public List<Integer> buscarTodasNotasPorFilme(Filme filme){
        return notaUsuarioFilmeRepository.buscarNotasPorFilme(filme);
    }

}
