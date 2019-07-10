package com.kltn.Controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.kltn.Model.Episode;
import com.kltn.Model.Film;
import com.kltn.Model.Genre;
import com.kltn.Service.IEpisodeService;

@RestController
public class EpisodeController {

	@Autowired
	private IEpisodeService episodeService;
	
	@PostMapping("episode")
//	@PreAuthorize("isAuthenticated()")
	@CrossOrigin
	public ResponseEntity<List<Episode>> getListEpisodeOfFilm(@RequestBody Film film){
		List<Episode> listEp = episodeService.listEpisode(film);
		if(listEp.size() > 0) {
			for (Iterator<Genre> iterator2 = listEp.get(0).getFilm().getGenre().iterator(); iterator2.hasNext(); ) {
				Genre genre = (Genre) iterator2.next();
				genre.setFilm(null);
			}
		}
		return new ResponseEntity<List<Episode>>(listEp, HttpStatus.OK);
	}

	@PostMapping("add-episode")
	@PreAuthorize("hasAuthority('ADMIN')")
	@CrossOrigin
	public ResponseEntity<?> addEpisode(@RequestBody Episode episode){
		try {
			Pair<Episode,String> pair = episodeService.addEpisode(episode);
			if(pair.getSecond().toLowerCase().equals("success")) {
				return new ResponseEntity<Episode>(pair.getFirst(), HttpStatus.OK);
			}
			return new ResponseEntity<String>("{\"result\":\" "+pair.getSecond()+" \"}", HttpStatus.OK);
		}catch (Exception ex){
			ex.printStackTrace();
			return new ResponseEntity<String>("{\"result\":\" Unrecognized error \"}", HttpStatus.OK);
		}
	}

	@PostMapping("edit-episode")
	@PreAuthorize("hasAuthority('ADMIN')")
	@CrossOrigin
	public ResponseEntity<?> editEpisode(@RequestBody Episode episode){
		try {
			Pair<Episode,String> pair = episodeService.editEpisode(episode);
			if(pair.getSecond().toLowerCase().equals("success")) {
				return new ResponseEntity<Episode>(pair.getFirst(), HttpStatus.OK);
			}
			return new ResponseEntity<String>("{\"result\":\" "+pair.getSecond()+" \"}", HttpStatus.OK);
		}catch (Exception ex){
			//ex.printStackTrace();
			return new ResponseEntity<String>("{\"result\":\" "+ex.getMessage()+" \"}", HttpStatus.OK);
		}
	}

	@GetMapping("addEpisodeView")
	@CrossOrigin
	public ResponseEntity<?> addView(@RequestParam int episodeId){
		try {
			return new ResponseEntity<String>("{\"viewcount\":\" "+episodeService.addView(episodeId)+" \"}", HttpStatus.OK);
		}catch (Exception ex){
			return new ResponseEntity<String>("{\"result\":\" "+ex.getMessage()+" \"}", HttpStatus.OK);
		}
	}


}
