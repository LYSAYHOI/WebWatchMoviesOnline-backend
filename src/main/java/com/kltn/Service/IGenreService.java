package com.kltn.Service;

import java.util.List;

import com.kltn.Model.Genre;

public interface IGenreService {

	public List<Genre> listGenre();
	public Genre getGenre(int genreId);
	public boolean addNewGenre(Genre genre);
	public boolean editGenre(Genre genre);
}
