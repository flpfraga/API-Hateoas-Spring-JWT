package com.fraga.APIRest.repository;

import java.util.List;

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
	List<Usuario> loadUserActives(Pageable pageable);
	
	@Query("SELECT u FROM Usuario u WHERE u.nome_usuario =:userName")
	Usuario loadUserByUsername(@Param("userName") String userName);
	
	@Modifying
	@Query("UPDATE Usuario u Set u.active = false WHERE u.id =:id")
	void desactiveCommomUser(@Param("id") Long id);
	
	

}
