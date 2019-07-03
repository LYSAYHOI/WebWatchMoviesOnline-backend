package com.kltn.DTO;

import java.util.List;

import com.kltn.Model.Genre;

public class FilmDTO {
	
	private int filmId;
	private String filmName;

	public int getFilmId() {
		return filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public String getFilmName() {
		return filmName;
	}

	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}

	public FilmDTO() {
	}
}
