package com.kltn.ServiceImpl;

import com.kltn.Model.Episode;
import com.kltn.Model.Film;
import com.kltn.Model.User;
import com.kltn.Repository.ICollectionRepository;
import com.kltn.Service.ICollectionService;
import com.kltn.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollectionService implements ICollectionService {

    @Autowired
    private IUserService userService;

    @Autowired
    private ICollectionRepository collectionRepository;

    @Override
    public List<Episode> newEpisode() {
        return collectionRepository.newEpisode();
    }

    @Override
    public List<Film> newFilm() {
        return collectionRepository.newFilm();
    }

    @Override
    public List<User> likedFilm(String username, int pagesize) {
        User user = userService.getUserFromUsername(username);
        if(user == null) return new ArrayList<>();
        return collectionRepository.likedFilm(user, pagesize);
    }

    @Override
    public List<Film> mostViewFilm(int pagesize) {
        return collectionRepository.mostViewFilm(pagesize);
    }
}
