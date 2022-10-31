package com.fraga.APIRest.util;

import com.fraga.APIRest.data.vo.MovieVO;
import com.fraga.APIRest.data.vo.UserVO;
import com.fraga.APIRest.exception.InvalidParams;

public class ValidDataParams {
    
    public static void validReadAll(Integer page, Integer size) {
        if (page <=-1) throw new InvalidParams("Page number must be a positive number!");
        if (size <=0) throw new InvalidParams("Size number must be greater then 0!");
    }

    
    public static void validCreateUser(UserVO user) {
        if (user.getuserName().equals(null) ||user.getuserName().equals("")) throw new InvalidParams("User name can`t be null!");
        if (user.getPassword().equals(null) ||user.getPassword().equals("")) throw new InvalidParams("Password can`t be null!");
    }
    
    public static void validCreateMovie(MovieVO movie) {
        if (movie.getTitle().equals(null) ||movie.getTitle().equals("")) throw new InvalidParams("Title can`t be null!");
    }

    public static void validVoteNumber(Long vote) {
        if (vote < 0 || vote > 4) throw new InvalidParams("The vote value need be between 0 and 4!");
    }
    
}
