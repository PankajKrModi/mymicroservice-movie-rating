package io.mymicroservice.moviecatalogservice.models;

import java.io.Serializable;

public class Rating implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String movieId;
	private int rating;
	public Rating(String movieId, int rating) {
		super();
		this.movieId = movieId;
		this.rating = rating;
	}
	
	public Rating() {
	}

	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
}
