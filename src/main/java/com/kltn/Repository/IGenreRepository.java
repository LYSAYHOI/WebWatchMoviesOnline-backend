package com.kltn.Repository;

import java.util.List;

import com.kltn.Model.Genre;

public interface IGenreRepository {
	public List<Genre> listGenre();
	public Genre getGenre(int genreId);
	public boolean addNewGenre(Genre genre);
	public boolean editGenre(Genre genre);
}
