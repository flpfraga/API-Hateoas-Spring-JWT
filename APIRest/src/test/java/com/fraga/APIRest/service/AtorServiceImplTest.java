package com.fraga.APIRest.service;

import com.fraga.APIRest.data.model.Ator;
import com.fraga.APIRest.mocks.AtorMock;
import com.fraga.APIRest.repository.AtorRepository;
import com.fraga.APIRest.service.impl.AtorServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AtorServiceImplTest {

    @InjectMocks
    private AtorServiceImpl atorService;
    @Mock
    private AtorRepository atorRepository;

    @Spy
    private AtorMock atorMock;

    @Before
    public void setUp(){
        atorMock = new AtorMock();
    }

    @Test
    public void testarBuscarPorIdComSucesso(){
        List<Ator> atores = atorMock.getAtores();
        when(atorRepository.findAllById(anyList())).thenReturn(atores);

        List<Ator> retorno = atorService.buscarAtoresPorId(Collections.singletonList(1L));

        assertEquals(atores.size(),retorno.size());
    }

    @Test
    public void testarBuscarPorIdListaIdsVazia(){
        List<Ator> retorno = atorService.buscarAtoresPorId(Collections.emptyList());
        assertNull(retorno);
    }

    @Test
    public void testarSalvarListaComSucesso(){
        List<Ator> atores = atorMock.getAtores();
        when(atorRepository.findAll()).thenReturn(atores);

        when(atorRepository.saveAll(anyList()))
                .thenReturn(new ArrayList<>(){{
                    add(new Ator(3L,"teste3"));
                }});

        List<Ator> listaRetorno = atorService.salvarListaAtores(Arrays.asList("Ator 1","teste3"));

        assertEquals(3,listaRetorno.size());
    }
}
