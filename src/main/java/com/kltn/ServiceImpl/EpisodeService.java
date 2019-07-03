package com.kltn.ServiceImpl;

import java.util.List;

import com.kltn.Repository.IFilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import com.kltn.Model.Episode;
import com.kltn.Model.Film;
import com.kltn.Repository.IEpisodeRepository;
import com.kltn.Service.IEpisodeService;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

@Service
public class EpisodeService implements IEpisodeService{
 
	@Autowired
	private IEpisodeRepository episodeRepository;

	@Autowired
	private IFilmRepository filmRepository;
	
	@Override
	public List<Episode> listEpisode(Film film) {
		return episodeRepository.listEpisode(film);
	}

	@Override
	@Transactional
	public Pair<Episode,String> addEpisode(Episode newEp) {
		try {
			Pair<Episode,String> pair = checkIfHasEpisode(newEp.getFilm(), newEp.getEpisodeNumber());
			if(pair.getSecond().toLowerCase().equals("episode not found"))
				return episodeRepository.addEpisode(newEp);
			else if(pair.getSecond().toLowerCase().equals("success")) {
				return Pair.of(new Episode(), "Episode has already added");
			}
			return Pair.of(new Episode(), pair.getSecond());
		}catch (Exception ex){
			return Pair.of(null, ex.toString());
		}
	}

	@Override
	public Pair<Episode,String> checkIfHasEpisode(Film film, int epNumber) {
		return episodeRepository.checkIfHasEpisode(film, epNumber);
	}

	@Override
	@Transactional
	public Pair<Episode,String> editEpisode(Episode newEp){
		return episodeRepository.editEpisode(newEp);
	}

	@Override
	@Transactional
	public int addView(int episodeId) {
		Pair<Integer,Integer> pair = episodeRepository.addView(episodeId);
		filmRepository.addView(pair.getSecond());
		return pair.getFirst();
	}
}
