package com.fraga.APIRest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fraga.APIRest.data.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	@Query("SELECT u FROM Usuario u WHERE u.active = true ORDER BY u.active")
	Page<Usuario> buscarUsuariosAtivos(Pageable pageable);
	
	@Query("SELECT u FROM Usuario u WHERE u.nomeUsuario =:userName")
	Usuario loadUserByUsername(@Param("userName") String userName);

	@Query("SELECT u FROM Usuario u WHERE u.nomeUsuario =:userName")
	Optional<Usuario> buscarPorNomeUsuario(@Param("userName") String userName);

}
