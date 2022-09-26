package com.fraga.APIRest.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fraga.APIRest.data.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query("SELECT u FROM User u WHERE u.active = true ORDER BY u.active")
	List<User> loadUserActives(Pageable pageable);
	
	@Query("SELECT u FROM User u WHERE u.userName =:userName")
	User loadUserByUsername(@Param("userName") String userName);

}
