package com.fraga.APIRest.service.impl;

import com.fraga.APIRest.data.model.Ator;
import com.fraga.APIRest.repository.AtorRepository;
import com.fraga.APIRest.service.AtorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AtorServiceImpl implements AtorService {

    private final AtorRepository atorRepository;

    public AtorServiceImpl(AtorRepository atorRepository) {
        this.atorRepository = atorRepository;
    }

    @Override
    public List<Ator> buscarAtoresPorId(List<Long> idAtores) {
        if (Objects.isNull(idAtores) || idAtores.isEmpty()) {
            return null;
        }
        return atorRepository.findAllById(idAtores);
    }

    @Override
    public List<Ator> salvarListaAtores(List<String> atores) {
        Map<String,Ator> mapAtoresSalvos = atorRepository.findAll().stream()
                .filter(ator -> atores.contains(ator.getNome()))
                .collect(Collectors.toMap(Ator::getNome, ator -> ator));

        List<Ator> atoresNaoSalvos = atores.stream().filter(ator -> !mapAtoresSalvos.containsKey(ator))
                .map(Ator::new).toList();
        atoresNaoSalvos = atorRepository.saveAll(atoresNaoSalvos);

        atoresNaoSalvos.addAll(mapAtoresSalvos.values());
        return atoresNaoSalvos;
    }


}
