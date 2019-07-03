package com.kltn.Repository;

import com.kltn.Model.Film;

import java.util.List;

public interface IFilmRepository {
	public Film getFilm(int idFilm);
	public Film addFilm(Film film);
	public List<Film> getAllFilm(int pageNumber, int pageSize);
	public boolean editFilm(Film film);
	public Long getFilmLength();
	public int updateViewProperties(int filmId);
	public int addView(int filmId);
	public List<Film> getFilmByGenre(int genreId, int pagesize);
}
