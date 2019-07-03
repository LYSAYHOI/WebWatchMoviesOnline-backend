package com.kltn.DTO;

import com.kltn.Model.Film;

import java.util.List;

public class FilmAndLength {
    private List<Film> film;
    private long length;

    public List<Film> getFilm() {
        return film;
    }

    public void setFilm(List<Film> film) {
        this.film = film;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }
}
