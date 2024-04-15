package com.fraga.APIRest.config;

import com.fraga.APIRest.data.model.Filme;
import com.fraga.APIRest.data.model.Usuario;
import com.fraga.APIRest.dto.FilmeResponseDTO;
import com.fraga.APIRest.dto.UsuarioResponseDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper mapper() {
        ModelMapper mapper = new ModelMapper();
            mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            mapper.typeMap(Filme.class, FilmeResponseDTO.class).
                    addMappings(map -> map.map(Filme::getAtoresNome, FilmeResponseDTO::setAtores));
        return mapper;
    }
}
