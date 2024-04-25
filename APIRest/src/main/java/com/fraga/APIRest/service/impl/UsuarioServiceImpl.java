package com.fraga.APIRest.service.impl;

import com.fraga.APIRest.data.enums.EPermissaoUsuario;
import com.fraga.APIRest.data.model.Permission;
import com.fraga.APIRest.data.model.Usuario;
import com.fraga.APIRest.dto.UsuarioAtualizarDTO;
import com.fraga.APIRest.dto.UsuarioRequestDTO;
import com.fraga.APIRest.dto.UsuarioResponseDTO;
import com.fraga.APIRest.exception.BadCredentialsException;
import com.fraga.APIRest.exception.InvalidJwtAuthenticationException;
import com.fraga.APIRest.exception.InvalidParams;
import com.fraga.APIRest.repository.UsuarioRepository;
import com.fraga.APIRest.security.cripting.PasswordEncripitingBCrypt;
import com.fraga.APIRest.service.UsuarioService;
import org.apache.logging.log4j.util.Strings;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static com.fraga.APIRest.util.PaginacaoUtils.gerarPaginacao;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ModelMapper mapper;

    public UsuarioServiceImpl(UsuarioRepository repository, ModelMapper mapper) {
        this.usuarioRepository = repository;
        this.mapper = mapper;
    }

    @Override
    public Page<UsuarioResponseDTO> buscarTodosUsuarios(Integer pagina, Integer tamanho) {
        return usuarioRepository.findAll(gerarPaginacao(pagina, tamanho)).map(UsuarioResponseDTO::new);
    }

    @Override
    public Usuario buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(
                () -> new InvalidParams("Usuário de id " + id + " não encontrado.")
        );
    }

    @Override
    public UsuarioResponseDTO criarNovoUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        if (Objects.isNull(buscarPorNome(usuarioRequestDTO.getNomeUsuario(), false))) {
            Usuario usuario = mapper.map(usuarioRequestDTO, Usuario.class);
            usuario.setAtualizadoEm(LocalDate.now());
            adicionarPermissaoUsuarioComum(usuario);
            usuario.setSenha(encriptografarSenha(usuario.getSenha()));
            return mapper.map(usuarioRepository.save(usuario), UsuarioResponseDTO.class);
        } else {
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
        if (Strings.isBlank(nomeUsuario)) {
            throw new InvalidParams("O nome do usuário não pode ser vazio!");
        }
        return mapper.map(buscarPorNome(nomeUsuario, true), UsuarioResponseDTO.class);
    }

    private void adicionarPermissaoUsuarioComum(Usuario usuario) {
        usuario.setPermissions(List.of(new Permission(
                EPermissaoUsuario.COMMON_USER.getId(), EPermissaoUsuario.COMMON_USER.getPermissao())));
    }

    private void adicionarPermissaoUsuarioAdmin(Usuario usuario) {
        usuario.getPermissions().add(new Permission(
                EPermissaoUsuario.ADMIN.getId(), EPermissaoUsuario.ADMIN.getPermissao()));
    }

    private String encriptografarSenha(String senha) {
        return PasswordEncripitingBCrypt.encript(senha);
    }

    @Override
    public UsuarioResponseDTO atualizarUsuario(Long id, UsuarioAtualizarDTO usuarioAtualizarDTO) {
        Usuario usuario = buscarUsuarioPorId(id);
        usuario.setNomeCompleto(usuarioAtualizarDTO.getNomeCompleto());

        return mapper.map(usuarioRepository.save(usuario), UsuarioResponseDTO.class);
    }

    @Override
    public void desativarUsuario(Long id) {
        Usuario usuario = buscarUsuarioPorId(id);
        usuario.setActive(false);
        usuarioRepository.save(usuario);
    }

    @Override
    public Page<UsuarioResponseDTO> buscarTodosUsuariosAtivos(Integer pagina, Integer tamanho) {
        return usuarioRepository.buscarUsuariosAtivos(gerarPaginacao(pagina, tamanho)).map(UsuarioResponseDTO::new);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.loadUserByUsername(username);
        if(Objects.isNull(usuario)){
            throw new InvalidJwtAuthenticationException("Token inválido");
        }
        return usuario;
    }

    @Override
    public void atualizarUsuarioAdmin (Long id){
        Usuario usuario = buscarUsuarioPorId(id);
        adicionarPermissaoUsuarioAdmin(usuario);
        usuarioRepository.save(usuario);
    }

    @Override
    public void atualizarUsuarioCommon (Long id){
        Usuario usuario = buscarUsuarioPorId(id);
        usuario.setPermissions(usuario.getPermissions().stream()
                .filter(permission -> EPermissaoUsuario.COMMON_USER.getPermissao().equals(permission.getDescription()))
                .toList());
        adicionarPermissaoUsuarioComum(usuario);
        usuarioRepository.save(usuario);
    }

}
