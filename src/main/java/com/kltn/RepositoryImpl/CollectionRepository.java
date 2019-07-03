package com.kltn.RepositoryImpl;

import com.kltn.Model.Episode;
import com.kltn.Model.Film;
import com.kltn.Model.User;
import com.kltn.Repository.ICollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CollectionRepository implements ICollectionRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Episode> newEpisode() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Episode> query = cb.createQuery(Episode.class);
        Root root = query.from(Episode.class);
        query.orderBy(cb.desc(root.get("date")));
        return entityManager.createQuery(query)
                .setFirstResult(0)
                .setMaxResults(8)
                .getResultList();
    }

    @Override
    public List<Film> newFilm() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Film> query = cb.createQuery(Film.class);
        Root root = query.from(Film.class);
        query.orderBy(cb.desc(root.get("addDate")));
        return entityManager.createQuery(query)
                .setFirstResult(0)
                .setMaxResults(8)
                .getResultList();
    }

    @Override
    public List<User> likedFilm(User user, int pagesize) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root root = query.from(User.class);
        query.select(root.get("likedFilm"));
        query.where(cb.equal(root.get("userId"), user.getUserId()));
        //query.orderBy(cb.desc(root.get("likedFilm").get("addDate")));
        return entityManager.createQuery(query)
                .setFirstResult(0)
                .setMaxResults(pagesize)
                .getResultList();
    }

    @Override
    public List<Film> mostViewFilm(int pagesize) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Film> query = cb.createQuery(Film.class);
        Root root = query.from(Film.class);
        query.orderBy(cb.desc(root.get("viewed")));
        return entityManager.createQuery(query)
                .setFirstResult(0)
                .setMaxResults(pagesize)
                .getResultList();
    }

}
