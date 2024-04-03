package com.fraga.APIRest.service.impl;

import com.fraga.APIRest.dto.FilmeRequestDTO;
import com.fraga.APIRest.repository.AtorRepository;
import com.fraga.APIRest.repository.FilmeRepository;
import com.fraga.APIRest.service.FilmesAdminService;
import org.springframework.stereotype.Service;

@Service
public class FilmesAdminServiceImpl implements FilmesAdminService {

    private final FilmeRepository filmeRepository;
    private final AtorRepository atorRepository;

    public FilmesAdminServiceImpl(FilmeRepository filmeRepository, AtorRepository atorRepository) {
        this.filmeRepository = filmeRepository;
        this.atorRepository = atorRepository;
    }


    @Override
    public void adicionarFilmes(FilmeRequestDTO filmeRequestDTO) {


    }
    @Override
    public void deletarFilmesPorId(Long id) {

    }
    @Override
    public void atualizarFilmes(Long id, FilmeRequestDTO filmeRequestDTO) {

    }
}
