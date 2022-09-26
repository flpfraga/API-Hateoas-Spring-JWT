package com.fraga.APIRest.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.fraga.APIRest.controller.UserController;
import com.fraga.APIRest.converter.DozerConverter;
import com.fraga.APIRest.data.model.Movie;
import com.fraga.APIRest.data.model.User;
import com.fraga.APIRest.data.vo.UserVO;
import com.fraga.APIRest.exception.IntegrityConstraintViolationException;
import com.fraga.APIRest.exception.InvalidCallForUserException;
import com.fraga.APIRest.exception.ResourceNotFoundException;
import com.fraga.APIRest.repository.MovieRepository;
import com.fraga.APIRest.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	@Autowired
	private MovieRepository movieRepository;

	public List<UserVO> findAll() {
		List<UserVO> users = DozerConverter.parseList(repository.findAll(), UserVO.class);
		users.stream().forEach(p -> p.add(linkTo(methodOn(UserController.class).findById(p.getKey())).withSelfRel()));
		return users;

	}

	public Page<UserVO> findAllActives(Pageable pageable) {
		User user = new User();
		user.setActive(true);
		var userPage = repository.findAll(Example.of(user), pageable);
		var userVosPage = userPage.map(p -> DozerConverter.parseObject(p, UserVO.class));
		userVosPage.map(p -> p.add(linkTo(methodOn(UserController.class).findById(p.getKey())).withSelfRel()));
		return userVosPage;

	}

	public UserVO findById(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records matches for this ID!"));

		UserVO vo = DozerConverter.parseObject(entity, UserVO.class);
		vo.add(linkTo(methodOn(UserController.class).findById(id)).withSelfRel());
		return vo;
	}

	public UserVO create(UserVO user) {
		var entity = DozerConverter.parseObject(user, User.class);
		entity.setActive(true);
		try {
		UserVO vo = DozerConverter.parseObject(repository.save(entity), UserVO.class);
		vo.add(linkTo(methodOn(UserController.class).findById(user.getKey())).withSelfRel());
		return vo;
		} catch (Exception e) {
			throw new IntegrityConstraintViolationException("Username alread in use!");
		}
	}

	public UserVO update(Long id, UserVO user) {
		var vo = findById(id);
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName;    
		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		if (userName.equals(vo.getuserName())) {
			vo.setFullName(user.getFullName());
			var entity = DozerConverter.parseObject(vo, User.class);
			try {
				vo = DozerConverter.parseObject(repository.save(entity), UserVO.class);
				vo.add(linkTo(methodOn(UserController.class).findById(user.getKey())).withSelfRel());
				return vo;
			} catch (Exception e) {
				throw new IntegrityConstraintViolationException("Username alread in use!");
			}
		}
		else {
			throw new InvalidCallForUserException("Request to change user other than authenticated!");
		}
		
		
	}

	public boolean desactive(Long id) {
		var vo = findById(id);
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName;    
		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		if (userName.equals(vo.getuserName())) {
			vo.setActive(false);
			var entity = DozerConverter.parseObject(vo, User.class);
			repository.save(entity);
			return true;
		}
		else {
			throw new InvalidCallForUserException("Request to change user other than authenticated!");
		}
		
		
	}

	public ResponseEntity<?> movieVoted(Long user_id, Long movie_id, Long vote) {
		var entity = repository.findById(user_id)
				.orElseThrow(() -> new ResourceNotFoundException("No records users matches for this ID!"));
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName;    
		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		if (userName.equals(entity.getUserName())) {
			List<Movie> movies = entity.getVotedMovies();
			for (Movie m : movies) {
				if (m.getId() == movie_id) {
					return new ResponseEntity<>("Movie was voted by user!", HttpStatus.OK);
				}
			}
			var movie = movieRepository.findById(movie_id)
					.orElseThrow(() -> new ResourceNotFoundException("No records movies matches for this ID!"));
			Long voteCount = movie.getVoteCount();
			Double voteAverage = movie.getVoteAverage();
			voteAverage = (voteCount + vote) / ((voteCount / voteAverage) + 1);
			voteCount = voteCount + vote;
			movie.setVoteAverage(voteAverage);
			movie.setVoteCount(voteCount);
			entity.getVotedMovies().add(movie);
			repository.save(entity);
			return ResponseEntity.ok().build();
		}
		else {
			throw new InvalidCallForUserException("Request to change user other than authenticated!");
		}
	
	}
}
