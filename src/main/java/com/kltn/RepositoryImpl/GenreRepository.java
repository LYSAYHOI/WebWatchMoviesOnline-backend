package com.kltn.RepositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kltn.Model.Genre;
import com.kltn.Repository.IGenreRepository;

@Repository
public class GenreRepository implements IGenreRepository{

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Genre> listGenre() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Genre> query = builder.createQuery(Genre.class);
		Root<Genre> root = query.from(Genre.class);
		query.select(root);
 		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public Genre getGenre(int genreId) {
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Genre> query = cb.createQuery(Genre.class);
			Root<Genre> root = query.from(Genre.class);
			query.select(root);
			query.where(cb.equal(root.get("genreId"), genreId));
			return entityManager.createQuery(query).getSingleResult();
		}catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	@Override
	public boolean addNewGenre(Genre genre) {
		try {
			entityManager.persist(genre);
			return true;
		}catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	@Override
	public boolean editGenre(Genre genre) {
		try {
//			Genre editingGenre = getGenre(genre.getGenreId());
//			editingGenre.setGenreName(genre.getGenreName());
//			editingGenre.setGenreDescription(genre.getGenreDescription());
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaUpdate<Genre> query = cb.createCriteriaUpdate(Genre.class);
			Root<Genre> root = query.from(Genre.class);
			query.set("genreDescription", genre.getGenreDescription());
			query.where(cb.equal(root.get("genreId"), genre.getGenreId()));
			int result = entityManager.createQuery(query).executeUpdate();
			if(result > 0)
				return true;
			return false;
		}catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

}
