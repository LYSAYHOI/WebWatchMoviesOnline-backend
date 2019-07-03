package com.kltn.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import com.kltn.Model.Episode;
import com.kltn.Model.Film;

public interface IEpisodeService {
	
	public List<Episode> listEpisode(Film film);
	public Pair<Episode,String> addEpisode(Episode newEp);
	public Pair<Episode,String> editEpisode(Episode newEp);
	public Pair<Episode,String> checkIfHasEpisode(Film film, int epNumber);
	public int addView(int episodeId);
}
