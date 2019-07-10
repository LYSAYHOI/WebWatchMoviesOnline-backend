package com.kltn.Service;

import com.kltn.Model.Episode;
import com.kltn.Model.Film;
import com.kltn.Model.User;

import java.util.List;

public interface ICollectionService {

    public List<Episode> newEpisode();
    public List<Film> newFilm();
    public List<User> likedFilm(String username, int pagesize);
    public List<Film> mostViewFilm(int pagesize);
    public List<Film> search(String keyword);

}
