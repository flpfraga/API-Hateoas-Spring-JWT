package com.fraga.APIRest.service;

import com.fraga.APIRest.dto.UsuarioAtualizarDTO;
import com.fraga.APIRest.dto.UsuarioRequestDTO;
import com.fraga.APIRest.dto.UsuarioResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

 public interface UsuarioService extends UserDetailsService {

     /**
      * Buscar todos usuários paginados
      *
      * @param page com número da página a ser buscada
      * @param size com número de objetos em cada página
      * @return Page<UsuarioResponseDTO>
      */
     Page<UsuarioResponseDTO> buscarTodosUsuarios(Integer page, Integer size) ;

     /**
      * Buscar um usuário pelo número do ID
      *
      * @param id com identificador de um usuário que se deseja buscar
      * @return UsuarioResponseDTO
      */
     UsuarioResponseDTO buscarUsuarioPorId(Long id);

     /**
      * Buscar um usuário pelo nome do usuário
      *
      * @param nomeUsuario com nome do usuário a ser buscado
      * @return UsuarioResponseDTO
      */
     UsuarioResponseDTO buscarUsuarioPorNome(String nomeUsuario);

     /**
      * Criar um novo usuário através dos dados informados.
      *
      * @Param usuarioRequestDTO com os dados de nome de usuário, nome completo e senha.
      * @return UsuarioResponseDTO
      */
     UsuarioResponseDTO criarNovoUsuario(UsuarioRequestDTO usuarioRequestDTO) ;

     /**
      * Atualizar informações de um usuário.
      *
      * @param id com identificador do usuário a ser modificado
      * @param usuarioAtualizarDTO com dados a serem atualizados do usuário
      * @return UserVO
      */
     UsuarioResponseDTO atualizarUsuario(Long id, UsuarioAtualizarDTO usuarioAtualizarDTO) ;

}
