package com.fraga.APIRest.service.impl;

import com.fraga.APIRest.data.model.Permission;
import com.fraga.APIRest.data.model.Usuario;
import com.fraga.APIRest.dto.UsuarioAtualizarDTO;
import com.fraga.APIRest.dto.UsuarioRequestDTO;
import com.fraga.APIRest.dto.UsuarioResponseDTO;
import com.fraga.APIRest.exception.BadCredentialsException;
import com.fraga.APIRest.exception.InvalidParams;
import com.fraga.APIRest.repository.FilmeRepository;
import com.fraga.APIRest.repository.UsuarioRepository;
import com.fraga.APIRest.security.cripting.PasswordEncripitingBCrypt;
import com.fraga.APIRest.service.UsuarioService;
import com.fraga.APIRest.util.validation.SecurityValidations;
import org.apache.logging.log4j.util.Strings;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final FilmeRepository filmeRepository;
    private final SecurityValidations<Usuario> validations;
    private final ModelMapper mapper;

    public UsuarioServiceImpl(UsuarioRepository repository,
                              FilmeRepository filmeRepository,
                              SecurityValidations<Usuario> validations,
                              ModelMapper mapper) {
        this.usuarioRepository = repository;
        this.filmeRepository = filmeRepository;
        this.validations = validations;
        this.mapper = mapper;
    }

    @Override
    public Page<UsuarioResponseDTO> buscarTodosUsuarios(Integer pagina, Integer tamanho) {
        return usuarioRepository.findAll(PageRequest.of(pagina, tamanho)).map(UsuarioResponseDTO::new);
    }

    @Override
    public UsuarioResponseDTO buscarUsuarioPorId(Long id) {
        Usuario usuario = buscarPorId(id);
        return mapper.map(usuario, UsuarioResponseDTO.class);
    }

    private Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(
                () ->  new BadCredentialsException("Usuário de id " + id + " não encontrado.")
        );
    }

    @Override
    public UsuarioResponseDTO criarNovoUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        if (Objects.isNull(buscarPorNome(usuarioRequestDTO.getNomeUsuario(), false))){
            Usuario usuario = mapper.map(usuarioRequestDTO, Usuario.class);
            adicionarPermissaoUsuarioComum(usuario);
            usuario.setSenha(encriptografarSenha(usuario.getSenha()));
            return mapper.map(usuarioRepository.save(usuario), UsuarioResponseDTO.class);
        }
        else{
            throw new InvalidParams("Nome do usuário informado já cadastrado.");
        }
    }

    private Usuario buscarPorNome(String nomeUsuario, Boolean isTrowable) {
        return usuarioRepository.buscarPorNomeUsuario(nomeUsuario).orElseGet(
                () -> {
                    if (Boolean.TRUE.equals(isTrowable)) {
                        throw new BadCredentialsException("Usuário não encontrado");
                    }
                    return null;
                }
        );
    }

    @Override
    public UsuarioResponseDTO buscarUsuarioPorNome(String nomeUsuario) {
        if(Strings.isBlank(nomeUsuario)){
            throw new BadCredentialsException("O nome do usuário não pode ser vazio!");
        }
        return mapper.map(buscarPorNome(nomeUsuario, true), UsuarioResponseDTO.class);
    }

    private void adicionarPermissaoUsuarioComum(Usuario usuario){
        usuario.setPermissions(Arrays.asList(new Permission(2, "COMMON_USER")));
    }

    private String encriptografarSenha(String senha){
        return PasswordEncripitingBCrypt.encript(senha);
    }

    @Override
    public UsuarioResponseDTO atualizarUsuario(Long id, UsuarioAtualizarDTO usuarioAtualizarDTO) {
        Usuario usuario = buscarPorId(id);
        usuario.setNomeCompleto(usuarioAtualizarDTO.getNomeCompleto());

        return mapper.map(usuarioRepository.save(usuario), UsuarioResponseDTO.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.loadUserByUsername(username);
    }
}
