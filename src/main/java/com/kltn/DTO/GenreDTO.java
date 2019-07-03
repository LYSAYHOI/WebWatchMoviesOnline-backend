package com.kltn.DTO;

import java.util.List;

public class GenreDTO {
	
	private int genreId;
	private String genreName;
	private String genreDescription;
	private List<FilmDTO> filmDTO;
	public int getGenreId() {
		return genreId;
	}
	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}
	public String getGenreName() {
		return genreName;
	}
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	public String getGenreDescription() {
		return genreDescription;
	}
	public void setGenreDescription(String genreDescription) {
		this.genreDescription = genreDescription;
	}
	public List<FilmDTO> getFilmDTO() {
		return filmDTO;
	}
	public void setFilmDTO(List<FilmDTO> filmDTO) {
		this.filmDTO = filmDTO;
	}
	public GenreDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
