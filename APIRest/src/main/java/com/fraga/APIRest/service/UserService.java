package com.fraga.APIRest.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fraga.APIRest.controller.MovieController;
import com.fraga.APIRest.controller.UserController;
import com.fraga.APIRest.converter.DozerConverter;
import com.fraga.APIRest.data.model.Movie;
import com.fraga.APIRest.data.model.Permission;
import com.fraga.APIRest.data.model.User;
import com.fraga.APIRest.data.vo.UserVO;
import com.fraga.APIRest.exception.BadCredentialsException;
import com.fraga.APIRest.exception.InvalidParams;
import com.fraga.APIRest.exception.ResourceConflitException;
import com.fraga.APIRest.exception.ResourceNotFoundException;
import com.fraga.APIRest.repository.MovieRepository;
import com.fraga.APIRest.repository.UserRepository;
import com.fraga.APIRest.security.cripting.PasswordEncripitingBCrypt;
import com.fraga.APIRest.util.queryManager.QueryParams;
import com.fraga.APIRest.util.validation.SecurityValidations;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private SecurityValidations<User> validations;

    /**
     * Get a list users. Access just for admin users.
     * 
     * @param Pageable
     * @return Page<UserVO>
     */
    public Page<UserVO> readAll(QueryParams<User> queryParams) {
        var userPage = repository.findAll(queryParams.pagination());
        var userVOPage = userPage.map(p -> DozerConverter.parseObject(p, UserVO.class));
        userVOPage.map(p -> p.add(linkTo(methodOn(UserController.class).readById(p.getKey())).withSelfRel()));
        return userVOPage;
    }

    /**
     * Get a specific user by id number.
     * 
     * @param Long
     * @return UserVO
     */
    public UserVO readById(Long id) {

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records matches for this ID!"));
        UserVO vo = DozerConverter.parseObject(entity, UserVO.class);
        vo.add(linkTo(methodOn(UserController.class).readById(id)).withSelfRel());
        return vo;
    }

    /**
     * Create a new commom user.
     * 
     * @Param UserVO
     * @return UserVO
     */
    public UserVO create(UserVO userVO) {

        var entity = DozerConverter.parseObject(userVO, User.class);

        // Valid not nullable filds
        if (!validations.validEntity(entity)) {
            throw new InvalidParams("Invalid values for create entity User!");
        }

        entity.setActive(true);
        entity = addCommomPermission(entity);

        // Cripting password
        String criptingPassword = PasswordEncripitingBCrypt.encript(entity.getPassword());
        entity.setPassword(criptingPassword);

        // Does not allow creat a user with equals username.
        try {
            UserVO vo = DozerConverter.parseObject(repository.save(entity), UserVO.class);
            vo.add(linkTo(methodOn(UserController.class).readById(userVO.getKey())).withSelfRel());
            return vo;
        } catch (Exception e) {
            throw new ResourceConflitException("Username alread in use!");
        }
    }

    /**
     * Create a new admin user. Access just for admin users.
     * 
     * @Param UserVO
     * @return UserVO
     */
    public UserVO createAdminUser(UserVO userVO) {

        var entity = DozerConverter.parseObject(userVO, User.class);

        // Valid not nullable filds
        if (!validations.validEntity(entity)) {
            throw new InvalidParams("Invalid values for create entity User!");
        }

        entity.setActive(true);
        entity = addAdminPermission(entity);

        // Cripting password
        String criptingPassword = PasswordEncripitingBCrypt.encript(entity.getPassword());
        entity.setPassword(criptingPassword);

        // Does not allow creat a user with equals username.
        try {
            userVO = DozerConverter.parseObject(repository.save(entity), UserVO.class);
            userVO.add(linkTo(methodOn(UserController.class).readById(userVO.getKey())).withSelfRel());
            return userVO;
        } catch (Exception e) {
            throw new ResourceConflitException("Username alread in use!");
        }
    }

    /**
     * Update a save user.
     * 
     * @Param Long id, UserVO
     * @return UserVO
     */
    public UserVO update(Long id, UserVO userVO) {
        var entity = DozerConverter.parseObject(userVO, User.class);

        // Valid not nullable filds
        if (!validations.validEntity(entity)) {
            throw new InvalidParams("Invalid values for create entity User!");
        }
        
        entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records matches for this ID!"));

        // Check if the update request and a soliciting user is equal.
        if (validations.validUpdateActions(userVO.getuserName())) {
            throw new BadCredentialsException("Request to update user other than authenticated!");
        }

        entity.setFullName(userVO.getFullName());
        String criptingPassword = PasswordEncripitingBCrypt.encript(entity.getPassword());
        entity.setPassword(criptingPassword);

        // Does not allow changes is the username.
        try {
            userVO = DozerConverter.parseObject(repository.save(entity), UserVO.class);
            userVO.add(linkTo(methodOn(UserController.class).readById(userVO.getKey())).withSelfRel());
            return userVO;
        } catch (Exception e) {
            throw new ResourceConflitException("Username alread in use!");
        }

    }

    /**
     * Return user actives page in asc order, sort by username.
     * 
     * @param QueryParams<User>
     * @return Page<UserVO>
     */
    public Page<UserVO> findAllWithFilterAndPagination(QueryParams<User> queryParams) {

        var userPage = repository.findAll(queryParams.createExample(), queryParams.paginationWithSort());
        var userVosPage = userPage.map(p -> DozerConverter.parseObject(p, UserVO.class));
        userVosPage.map(p -> p.add(linkTo(methodOn(UserController.class).readById(p.getKey())).withSelfRel()));
        return userVosPage;
    }

    /**
     * Return user actives in asc order, sort by username.
     * 
     * @param QueryParams<User>
     * @return List<UserVO>
     */
    public List<UserVO> findAllWithFilter(QueryParams<User> queryParams) {

        List<UserVO> users = DozerConverter
                .parseList(repository.findAll(queryParams.createExample(), queryParams.inOrder()), UserVO.class);
        users.stream()
                .forEach(p -> p.add(
                        linkTo(methodOn(MovieController.class).readById(p.getKey())).withSelfRel()));
        return users;
    }

    /**
     * Update a user enable field for false
     * 
     * @param Long
     */
    @Transactional
    public void desactiveCommomUser(Long id) {

        var entity = readById(id);

        // Check if the update request and a soliciting user is equal.

        if (!validations.validUpdateActions(entity.getuserName())) {
            throw new BadCredentialsException("Request to update user other than authenticated!");
        }
        repository.desactiveCommomUser(id);
    }

    /**
     * Add a vote for a movie, if this wasn`t voted by user
     * 
     * @param user_id
     * @param movie_id
     * @param vote
     * @return
     */
    @Transactional
    public ResponseEntity<?> voteForMovie(Long user_id, Long movie_id, Long vote) {
        //Verify if vote was bettwen 0 and 
        if (vote < 0 && vote > 4) {
            throw new ResourceConflitException("Vote value invalid!");
        }

        // Get user from data base by id
        User user = repository.findById(user_id)
                .orElseThrow(() -> new ResourceNotFoundException("No records users matches for this ID!"));

        // Check if user is just a common user.
        if (validations.isAdminUser(user)) {
            throw new BadCredentialsException("This solicitation can be just a commom user!");
        }

        // Check if the update request and a soliciting user is equal.
        if (!validations.validUpdateActions(user.getUserName())) {
            throw new BadCredentialsException("Request to update user other than authenticated!");
        }

        // Get a user from the data base by id.
        var movie = movieRepository.findById(movie_id)
                .orElseThrow(() -> new ResourceNotFoundException("No records movies matches for this ID!"));

        // Check if this user ha already given a vote for him
        if (movieWasVotedByThisUser(user, movie_id)) {
            return new ResponseEntity<>("Movie was voted by this user!", HttpStatus.CONFLICT);
        }

        Long voteCount = movie.getVoteCount();
        Double voteAverage = movie.getVoteAverage();
        voteAverage = (voteCount + vote) / ((voteCount / voteAverage) + 1);
        voteCount = voteCount + vote;
        movie.setVoteAverage(voteAverage);
        movie.setVoteCount(voteCount);
        user.getVotedMovies().add(movie);

        // Update a voted movies list for a user
        repository.save(user);

        // Update vote average and vote count.
        movieRepository.updateVote(movie_id, voteAverage, voteCount);
        return ResponseEntity.ok().build();
    }

    /**
     * Verify if a movie was voted by a user
     * 
     * @param User
     * @param Long
     * @return Boolean
     */
    public boolean movieWasVotedByThisUser(User user, Long movie_id) {
        List<Movie> movies = user.getVotedMovies();
        for (Movie m : movies) {
            if (m.getId() == movie_id) {
                return true;
            }
        }
        return false;
    }

    /**
     * Do a consult in data base e return a user by userName
     * 
     * @param String
     * @return UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.loadUserByUsername(username);
    }

    /**
     * Add a permission 'COMMOM_USER' for a user
     * 
     * @param User
     * @return User
     */
    public User addCommomPermission(User user) {
        List<Permission> permissions = new ArrayList<>() {
            {
                add(new Permission(2L, "COMMON_USER"));
            }
        };
        user.setPermissions(permissions);
        return user;
    }

    /**
     * Add permissions 'ADMIN' and 'COMMOM_USER' for a user
     * 
     * @param User
     * @return User
     */
    public User addAdminPermission(User user) {
        List<Permission> permissions = new ArrayList<>() {
            {
                add(new Permission(1L, "ADMIN"));
                add(new Permission(2L, "COMMON_USER"));
            }
        };
        user.setPermissions(permissions);
        return user;
    }

}
