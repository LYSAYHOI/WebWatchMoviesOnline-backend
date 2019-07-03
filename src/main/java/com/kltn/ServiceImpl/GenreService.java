package com.kltn.ServiceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kltn.Model.Genre;
import com.kltn.Repository.IGenreRepository;
import com.kltn.Service.IGenreService;

@Service
public class GenreService implements IGenreService{

	@Autowired
	private IGenreRepository genreRepository;
	
	@Override
	@Transactional
	public List<Genre> listGenre() {
		return genreRepository.listGenre();
	}

	@Override
	@Transactional
	public Genre getGenre(int genreId) {
		return genreRepository.getGenre(genreId);
	}

	@Override
	@Transactional
	public boolean addNewGenre(Genre genre) {
		if(genreRepository.getGenre(genre.getGenreId()) == null) {
			return genreRepository.addNewGenre(genre);
		}
		return false;
	}

	@Override
	@Transactional
	public boolean editGenre(Genre genre) {
		return genreRepository.editGenre(genre);
	}
	
}
