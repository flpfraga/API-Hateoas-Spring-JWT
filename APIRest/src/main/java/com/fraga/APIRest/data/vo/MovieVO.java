package com.fraga.APIRest.data.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;


public class MovieVO extends RepresentationModel <MovieVO> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long key;

	private String title;

	private String director;

	private String genre;

	private Double voteAverage;
	
	private Long voteCount;
	
	private String details;
	
	private List<ActorVO>actors;
	
	
	public List<ActorVO> getActors() {
		return actors;
	}

	public void setActors(List<ActorVO> actors) {
		this.actors = actors;
	}

	public MovieVO() {
		// TODO Auto-generated constructor stub
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}


	public Double getVoteAverage() {
		return voteAverage;
	}

	public void setVoteAverage(Double voteAverage) {
		this.voteAverage = voteAverage;
	}

	public Long getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(Long voteCount) {
		this.voteCount = voteCount;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(actors, details, director, genre, key, title, voteAverage, voteCount);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovieVO other = (MovieVO) obj;
		return Objects.equals(actors, other.actors) && Objects.equals(details, other.details)
				&& Objects.equals(director, other.director) && Objects.equals(genre, other.genre)
				&& Objects.equals(key, other.key) && Objects.equals(title, other.title)
				&& Objects.equals(voteAverage, other.voteAverage) && Objects.equals(voteCount, other.voteCount);
	}

	
	
	@Override
	public String toString() {
		return "MovieVO [key=" + key + ", title=" + title + ", director=" + director + ", genre=" + genre
				+ ", voteAverage=" + voteAverage + ", voteCount=" + voteCount + ", details=" + details + ", actors="
				+ actors + "]";
	}
	
}
