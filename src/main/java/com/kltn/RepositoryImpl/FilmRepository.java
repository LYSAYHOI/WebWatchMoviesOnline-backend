package com.kltn.RepositoryImpl;

import com.kltn.Model.Film;
import com.kltn.Model.Genre;
import com.kltn.Repository.IFilmRepository;
import com.kltn.Utilities.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class FilmRepository implements IFilmRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Film getFilm(int idFilm) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Film> query = cb.createQuery(Film.class);
            Root root = query.from(Film.class);
            query.where(cb.equal(root.get("filmId"), idFilm));
            return entityManager.createQuery(query).getSingleResult();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public Film addFilm(Film film) {
        boolean complete = false;
        try {
            entityManager.persist(film);
            entityManager.flush();
            complete = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        if (complete) {
            return film;
        }
        return null;
    }

    @Override
    public List<Film> getAllFilm(int pageNumber, int pageSize) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Film> query = cb.createQuery(Film.class);
            Root root = query.from(Film.class);
            query.select(root);
            return entityManager.createQuery(query)
                    .setFirstResult((pageNumber)*pageSize)
                    .setMaxResults(pageSize)
                    .getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public boolean editFilm(Film film) {
        try {
            Film filmEdited = getFilm(film.getFilmId());
            filmEdited.setFilmDescription(film.getFilmDescription());
            filmEdited.setFilmName(film.getFilmName());
            filmEdited.setGenre(film.getGenre());
            if( film.getImage() != null || !film.getImage().equals("")) {
                filmEdited.setImage(film.getImage());
            }
            Film editedFilm = entityManager.merge(film);
            System.out.println("result: " + editedFilm.getFilmName());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Long getFilmLength(){
        try{
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> query = cb.createQuery(Long.class);
            Root root = query.from(Film.class);
            query.select(cb.count(root));
            return entityManager.createQuery(query).getSingleResult();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public int updateViewProperties(int filmId) {
        Film film = getFilm(filmId);
        film.setLiked(film.getLikedUser().size());
        entityManager.merge(film);
        return film.getLikedUser().size();
    }

    @Override
    public int addView(int filmId) {
        Film film = getFilm(filmId);
        if(film != null){
            int view = film.getViewed();
            film.setViewed(view+1);
            entityManager.merge(film);
            return view + 1;
        } throw new CustomException("Film not found");
    }

    @Override
    public List<Film> getFilmByGenre(int genreId, int pagesize) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Film> query = cb.createQuery(Film.class);
            Root root = query.from(Film.class);
            query.where(cb.equal(root.get("genre").get("genreId"), genreId));
            return entityManager.createQuery(query)
                    .setFirstResult(0)
                    .setMaxResults(pagesize)
                    .getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }
}
