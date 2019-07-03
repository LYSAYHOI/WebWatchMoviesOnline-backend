package com.kltn.Repository;

import java.util.List;

import com.kltn.Model.Episode;
import com.kltn.Model.Film;
import org.springframework.data.util.Pair;

public interface IEpisodeRepository {
	public List<Episode> listEpisode(Film film);
	public Pair<Episode,String> addEpisode(Episode newEp);
	public Pair<Episode,String> checkIfHasEpisode(Film film, int epNumber);
	public Pair<Episode,String> editEpisode(Episode newEp);
	public Pair<Episode,String> getAnEpisodeById(int episodeId);
	public Pair<Integer,Integer> addView(int episodeId);
}
