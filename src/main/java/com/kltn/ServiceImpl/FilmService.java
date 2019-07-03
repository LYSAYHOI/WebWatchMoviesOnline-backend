package com.kltn.ServiceImpl;

import com.kltn.DTO.FilmAndLength;
import com.kltn.Model.Film;
import com.kltn.Repository.IFilmRepository;
import com.kltn.Service.IFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FilmService implements IFilmService {

    @Autowired
    private IFilmRepository filmRepository;

    @Transactional
    public Film getFilm(int idFilm){
        return filmRepository.getFilm(idFilm);
    }

    @Transactional
    public Film addFilm(Film film){
        return filmRepository.addFilm(film);
    }

    @Transactional
    public FilmAndLength getAllFilm(int pageNumber, int pageSize){
        FilmAndLength filmAndLength = new FilmAndLength();
        filmAndLength.setFilm(filmRepository.getAllFilm(pageNumber, pageSize));
        filmAndLength.setLength(filmRepository.getFilmLength());
        return filmAndLength;
    }

    @Transactional
    public boolean editFilm(Film film){
        return filmRepository.editFilm(film);
    }

    @Transactional
    public int updateViewProperties(int filmId) {
        return filmRepository.updateViewProperties(filmId);
    }
}
