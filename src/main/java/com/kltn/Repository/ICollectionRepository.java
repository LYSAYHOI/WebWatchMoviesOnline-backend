package com.kltn.Repository;

import com.kltn.Model.Episode;
import com.kltn.Model.Film;
import com.kltn.Model.User;

import java.util.List;

public interface ICollectionRepository {

    public List<Episode> newEpisode();
    public List<Film> newFilm();
    public List<User> likedFilm(User user, int pagesize);
    public List<Film> mostViewFilm(int pagesize);
}
