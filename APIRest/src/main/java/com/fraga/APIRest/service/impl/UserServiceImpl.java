package com.fraga.APIRest.service.impl;

import com.fraga.APIRest.data.model.Permission;
import com.fraga.APIRest.data.model.Usuario;
import com.fraga.APIRest.data.vo.UserVO;
import com.fraga.APIRest.exception.BadCredentialsException;
import com.fraga.APIRest.repository.FilmeRepository;
import com.fraga.APIRest.repository.UsuarioRepository;
import com.fraga.APIRest.service.UserService;
import com.fraga.APIRest.util.queryManager.QueryParams;
import com.fraga.APIRest.util.validation.SecurityValidations;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UsuarioRepository usuarioRepository;
    private final FilmeRepository filmeRepository;
    private final SecurityValidations<Usuario> validations;

    public UserServiceImpl(UsuarioRepository repository, FilmeRepository filmeRepository, SecurityValidations<Usuario> validations) {
        this.usuarioRepository = repository;
        this.filmeRepository = filmeRepository;
        this.validations = validations;
    }

//    public Page<UserVO> readAll(QueryParams<Usuario> queryParams) {
//        var userPage = usuarioRepository.findAll(queryParams.pagination());
//        var userVOPage = userPage.map(p -> DozerConverter.parseObject(p, UserVO.class));
//        userVOPage.map(p -> p.add(linkTo(methodOn(UserController.class).readById(p.getKey())).withSelfRel()));
//        return userVOPage;
//    }


//    public UserVO readById(Long id) {
//
//        var entity = usuarioRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("No records matches for this ID!"));
//        UserVO vo = DozerConverter.parseObject(entity, UserVO.class);
//        vo.add(linkTo(methodOn(UserController.class).readById(id)).withSelfRel());
//        return vo;
//    }


//    public UserVO create(UserVO userVO) {
//
//        var entity = DozerConverter.parseObject(userVO, Usuario.class);
//
//        if (!validations.validEntity(entity)) {
//            throw new InvalidParams("Invalid values for create entity User!");
//        }
//
//        entity.setActive(true);
//        entity = addCommomPermission(entity);
//
//        // Cripting password
//        String criptingPassword = PasswordEncripitingBCrypt.encript(entity.getPassword());
//        entity.setPassword(criptingPassword);
//        entity.setAccountNonExpired(true);
//        entity.setAccountNonLocked(true);
//        entity.setCredentialsNonExpired(true);
//        entity.setEnabled(true);
//        // Does not allow creat a user with equals username.
//        try {
//            UserVO vo = DozerConverter.parseObject(usuarioRepository.save(entity), UserVO.class);
//            vo.add(linkTo(methodOn(UserController.class).readById(userVO.getKey())).withSelfRel());
//            return vo;
//        } catch (Exception e) {
//            throw new ResourceConflitException("Username alread in use!");
//        }
//    }

    /**
     * Create a new admin user. Access just for admin users.
     * 
     * @Param UserVO
     * @return UserVO
     */
//    public UserVO createAdminUser(UserVO userVO) {
//
//        var entity = DozerConverter.parseObject(userVO, Usuario.class);
//
//        // Valid not nullable filds
//        if (!validations.validEntity(entity)) {
//            throw new InvalidParams("Invalid values for create entity User!");
//        }
//
//        entity.setActive(true);
//        entity = addAdminPermission(entity);
//
//        // Cripting password
//        String criptingPassword = PasswordEncripitingBCrypt.encript(entity.getPassword());
//        entity.setPassword(criptingPassword);
//
//        // Does not allow creat a user with equals username.
//        try {
//            userVO = DozerConverter.parseObject(usuarioRepository.save(entity), UserVO.class);
//            userVO.add(linkTo(methodOn(UserController.class).readById(userVO.getKey())).withSelfRel());
//            return userVO;
//        } catch (Exception e) {
//            throw new ResourceConflitException("Username alread in use!");
//        }
//    }

    @Override
    public Page<UserVO> readAll(QueryParams<Usuario> queryParams) {
        return null;
    }

    @Override
    public UserVO readById(Long id) {
        return null;
    }

    @Override
    public UserVO create(UserVO userVO) {
        return null;
    }

    @Override
    public UserVO createAdminUser(UserVO userVO) {
        return null;
    }

    @Override
    public UserVO update(Long id, UserVO userVO) {
        return null;
    }

    @Override
    public Page<UserVO> findAllWithFilterAndPagination(QueryParams<Usuario> queryParams) {
        return null;
    }

    @Override
    public List<UserVO> findAllWithFilter(QueryParams<Usuario> queryParams) {
        return null;
    }

    /**
     * Update a save user.
     * 
     * @Param Long id, UserVO
     * @return UserVO
     */
//    public UserVO update(Long id, UserVO userVO) {
//        var entity = DozerConverter.parseObject(userVO, Usuario.class);
//
//        // Valid not nullable filds
//        if (!validations.validEntity(entity)) {
//            throw new InvalidParams("Invalid values for create entity User!");
//        }
//
//        entity = usuarioRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("No records matches for this ID!"));
//
//        // Check if the update request and a soliciting user is equal.
//        if (validations.validUpdateActions(userVO.getuserName())) {
//            throw new BadCredentialsException("Request to update user other than authenticated!");
//        }
//
//        entity.setFullName(userVO.getFullName());
//        String criptingPassword = PasswordEncripitingBCrypt.encript(entity.getPassword());
//        entity.setPassword(criptingPassword);
//
//        // Does not allow changes is the username.
//        try {
//            userVO = DozerConverter.parseObject(usuarioRepository.save(entity), UserVO.class);
//            userVO.add(linkTo(methodOn(UserController.class).readById(userVO.getKey())).withSelfRel());
//            return userVO;
//        } catch (Exception e) {
//            throw new ResourceConflitException("Username alread in use!");
//        }
//
//    }

//    public Page<UserVO> findAllWithFilterAndPagination(QueryParams<Usuario> queryParams) {
//
//        var userPage = usuarioRepository.findAll(queryParams.createExample(), queryParams.paginationWithSort());
//        var userVosPage = userPage.map(p -> DozerConverter.parseObject(p, UserVO.class));
//        userVosPage.map(p -> p.add(linkTo(methodOn(UserController.class).readById(p.getKey())).withSelfRel()));
//        return userVosPage;
//    }

//    public List<UserVO> findAllWithFilter(QueryParams<Usuario> queryParams) {
//
//        List<UserVO> users = DozerConverter
//                .parseList(usuarioRepository.findAll(queryParams.createExample(), queryParams.inOrder()), UserVO.class);
//        users.stream()
//                .forEach(p -> p.add(
//                        linkTo(methodOn(FilmesController.class).readById(p.getKey())).withSelfRel()));
//        return users;
//    }

    @Transactional
    public void desactiveCommomUser(Long id) {

        var entity = readById(id);

        // Check if the update request and a soliciting user is equal.

        if (!validations.validUpdateActions(entity.getuserName())) {
            throw new BadCredentialsException("Request to update user other than authenticated!");
        }
        usuarioRepository.desactiveCommomUser(id);
    }

    @Override
    public ResponseEntity<?> voteForMovie(Long user_id, Long movie_id, Long vote) {
        return null;
    }

    @Override
    public boolean movieWasVotedByThisUser(Usuario user, Long movie_id) {
        return false;
    }

//    @Transactional
//    public ResponseEntity<?> voteForMovie(Long user_id, Long movie_id, Long vote) {
//        //Verify if vote was bettwen 0 and
//        if (vote < 0 && vote > 4) {
//            throw new ResourceConflitException("Vote value invalid!");
//        }
//
//        // Get user from data base by id
//        Usuario user = usuarioRepository.findById(user_id)
//                .orElseThrow(() -> new ResourceNotFoundException("No records users matches for this ID!"));
//
//        // Check if user is just a common user.
//        if (validations.isAdminUser(user)) {
//            throw new BadCredentialsException("This solicitation can be just a commom user!");
//        }
//
//        // Check if the update request and a soliciting user is equal.
//        if (!validations.validUpdateActions(user.getUserName())) {
//            throw new BadCredentialsException("Request to update user other than authenticated!");
//        }
//
//        // Get a user from the data base by id.
//        var movie = filmeRepository.findById(movie_id)
//                .orElseThrow(() -> new ResourceNotFoundException("No records movies matches for this ID!"));
//
//        // Check if this user ha already given a vote for him
//        if (movieWasVotedByThisUser(user, movie_id)) {
//            return new ResponseEntity<>("Movie was voted by this user!", HttpStatus.CONFLICT);
//        }
//
//        Long voteCount = movie.getVoteCount();
//        Double voteAverage = movie.getVoteAverage();
//        voteAverage = (voteCount + vote) / ((voteCount / voteAverage) + 1);
//        voteCount = voteCount + vote;
//        movie.setVoteAverage(voteAverage);
//        movie.setVoteCount(voteCount);
//        user.getVotedMovies().add(movie);
//
//        // Update a voted movies list for a user
//        usuarioRepository.save(user);
//
//        // Update vote average and vote count.
//        filmeRepository.updateVote(movie_id, voteAverage, voteCount);
//        return ResponseEntity.ok().build();
//    }
//
//    public boolean movieWasVotedByThisUser(Usuario user, Long movie_id) {
//        List<Filme> movies = user.getVotedMovies();
//        for (Filme m : movies) {
//            if (Objects.equals(m.getId(), movie_id)) {
//                return true;
//            }
//        }
//        return false;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.loadUserByUsername(username);
    }

    public Usuario addCommomPermission(Usuario user) {
        List<Permission> permissions = new ArrayList<>() {
            {
                add(new Permission(2L, "COMMON_USER"));
            }
        };
        user.setPermissions(permissions);
        return user;
    }

    public Usuario addAdminPermission(Usuario user) {
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
