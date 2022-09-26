package com.fraga.APIRest.converter.movie;

import java.util.ArrayList;
import java.util.List;

import com.fraga.APIRest.data.model.Movie;
import com.fraga.APIRest.data.vo.MovieVO;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class CustomMovieDozerConverter {

	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

//	public static List<MovieVO> customParseMovieToVoList(List<Movie> origin) {
//		List<MovieVO> movies = new ArrayList<>();
//		for (Movie m : origin) {
//
//			MovieVO vo = mapper.map(m, MovieVO.class);
//			vo.setActors(m.getActors());
//			movies.add(vo);
//
//		}
//		return movies;
//
//	}
//
//	public static MovieVO customParseMovieToVoObject(Movie origin) {
//		MovieVO vo = mapper.map(origin, MovieVO.class);
//		vo.setActors(origin.getActors());
//		return vo;
//	}
//	public static List<Movie> customParseVoToMovieList(List<MovieVO> origin) {
//		List<Movie> movies = new ArrayList<>();
//		for (MovieVO m : origin) {
//			
//			Movie vo = mapper.map(m, Movie.class);
//			vo.setActors(m.getActors());
//			movies.add(vo);
//			
//		}
//		return movies;
//		
//	}
//	
//	public static Movie customParseVoToMovieObject(MovieVO origin) {
//		Movie vo = mapper.map(origin, Movie.class);
//		vo.setActors(origin.getActors());
//		return vo;
//	}
}
