package com.kltn.Service;

import javax.transaction.Transactional;

import com.kltn.DTO.FilmAndLength;
import org.springframework.stereotype.Service;

import com.kltn.Model.Film;

import java.util.List;

@Service
public interface IFilmService {

	@Transactional
	public Film getFilm(int idFilm);
	public Film addFilm(Film film);
	public FilmAndLength getAllFilm(int pageNumber, int pageSize);
	public boolean editFilm(Film film);
	public int updateViewProperties(int filmId);

}
