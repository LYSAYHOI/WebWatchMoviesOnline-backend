package com.kltn.RepositoryImpl;

import com.kltn.Model.Comment;
import com.kltn.Model.Episode;
import com.kltn.Model.Film;
import com.kltn.Model.User;
import com.kltn.Repository.ICommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CommentRepository implements ICommentRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Comment addComment(String commentContent, long userId, int filmId) {
        try {
            Comment newComment = new Comment();
            newComment.setCommentContent(commentContent);
            Film film = new Film();
            film.setFilmId(filmId);
            User user = new User();
            user.setUserId(userId);
            newComment.setFilm(film);
            newComment.setUser(user);
            entityManager.persist(newComment);
            entityManager.flush();
            return newComment;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Comment> getComment(int filmId, int pagesize) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Comment> query = cb.createQuery(Comment.class);
            Root root = query.from(Comment.class);
            query.where(cb.equal(root.get("film").get("filmId"), filmId));
            query.orderBy(cb.desc(root.get("commentTime")));
            return entityManager.createQuery(query)
                    .setFirstResult(0)
                    .setMaxResults(pagesize)
                    .getResultList();
        }catch (Exception ex){
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }
}
