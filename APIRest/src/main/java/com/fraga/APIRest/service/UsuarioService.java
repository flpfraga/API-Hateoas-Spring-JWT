package com.fraga.APIRest.service;

import com.fraga.APIRest.data.model.Usuario;
import com.fraga.APIRest.data.vo.UserVO;
import com.fraga.APIRest.dto.UsuarioRequestDTO;
import com.fraga.APIRest.util.queryManager.QueryParams;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

 public interface UsuarioService extends UserDetailsService {
     Page<UserVO> readAll(QueryParams<Usuario> queryParams) ;
     UserVO readById(Long id) ;
     void criarNovoUsuario(UsuarioRequestDTO usuarioRequestDTO) ;
     UserVO createAdminUser(UserVO userVO) ;
     UserVO update(Long id, UserVO userVO) ;
     Page<UserVO> findAllWithFilterAndPagination(QueryParams<Usuario> queryParams) ;
     List<UserVO> findAllWithFilter(QueryParams<Usuario> queryParams) ;
     void desactiveCommomUser(Long id) ;
     ResponseEntity<?> voteForMovie(Long user_id, Long movie_id, Long vote) ;
     boolean movieWasVotedByThisUser(Usuario user, Long movie_id) ;
     UserDetails loadUserByUsername(String username) throws UsernameNotFoundException ;
     Usuario addCommomPermission(Usuario user) ;
     Usuario addAdminPermission(Usuario user) ;
}
