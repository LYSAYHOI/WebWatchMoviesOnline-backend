package com.kltn.RepositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.kltn.Utilities.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Repository;

import com.kltn.Model.Episode;
import com.kltn.Model.Film;
import com.kltn.Repository.IEpisodeRepository;

@Repository
public class EpisodeRepository implements IEpisodeRepository{

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private FilmRepository filmRepository;
	
	@Override
	public List<Episode> listEpisode(Film film) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Episode> query = builder.createQuery(Episode.class);
		Root<Episode> root = query.from(Episode.class);
		query.select(root);
		query.where(builder.equal(root.get("film").get("filmId"), film.getFilmId()));
		query.orderBy(builder.asc(root.get("episodeNumber")));
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public Pair<Episode,String> addEpisode(Episode newEp) {
		try {
			entityManager.persist(newEp);
			entityManager.flush();
			return Pair.of(newEp, "Success");
		}catch (Exception e){
			e.printStackTrace();
			return Pair.of(null, e.toString());
		}
	}

	@Override
	public Pair<Episode,String> checkIfHasEpisode(Film film, int epNumber) {
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Episode> query = builder.createQuery(Episode.class);
			Root<Episode> root = query.from(Episode.class);
			if(filmRepository.getFilm(film.getFilmId()) != null){
				query.where(builder.and(builder.equal(root.get("film").get("filmId"), film.getFilmId()),
						builder.equal(root.get("episodeNumber"), epNumber)));
				return  Pair.of(entityManager.createQuery(query).getSingleResult(),"Success");
			}
			return  Pair.of(new Episode(),"Film not found");
		}catch (NoResultException ex) {
			return Pair.of(new Episode(), "Episode not found");
		}
		catch (Exception e){
			return Pair.of(null, e.toString());
		}
	}

	@Override
	public Pair<Episode,String> editEpisode(Episode newEp){
		try {

			Pair<Episode, String> getedepisode = getAnEpisodeById(newEp.getFilm().getFilmId());
			if(getedepisode.getSecond().toLowerCase().equals("success")){
				Episode editEpisode = getedepisode.getFirst();
				editEpisode.setEpisodeName(newEp.getEpisodeName());
				//editEpisode.setEpisodeNumber(newEp.getEpisodeNumber());
				editEpisode.setLink(newEp.getLink());
				return Pair.of(entityManager.merge(editEpisode), "success");
			}
			return Pair.of(new Episode(), "Episode information provided is not true");
		}catch (Exception e){
			return Pair.of(new Episode(), e.getMessage());
		}
	}

	@Override
	public Pair<Episode,String> getAnEpisodeById(int episodeId){
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Episode> query = builder.createQuery(Episode.class);
			Root<Episode> root = query.from(Episode.class);
			query.where(builder.equal(root.get("episodeId"), episodeId));
			return Pair.of(entityManager.createQuery(query).getSingleResult(), "Success");
		}catch (Exception e){
			return Pair.of(new Episode(), e.getMessage());
		}
	}

	@Override
	public Pair<Integer,Integer> addView(int episodeId) {
		Pair<Episode, String> pair = getAnEpisodeById(episodeId);
		if (pair.getSecond().toLowerCase().equals("success")) {
			int view = pair.getFirst().getView();
			pair.getFirst().setView(view+1);
			entityManager.merge(pair.getFirst());
			return Pair.of(view + 1, pair.getFirst().getFilm().getFilmId());
		}
		throw new CustomException("Episode not found");
	}
}
