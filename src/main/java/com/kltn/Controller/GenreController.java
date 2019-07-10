package com.kltn.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kltn.Model.Genre;
import com.kltn.Service.IGenreService;

@RestController
@RequestMapping("genre")
public class GenreController {

	@Autowired
	private IGenreService genreService;
	
	@GetMapping("all")
	@CrossOrigin
	public ResponseEntity<List<Genre>> getGenre(){
		return new ResponseEntity<>(genreService.listGenre(), HttpStatus.OK);
	}
	
	@CrossOrigin
	@PostMapping("add")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> addNewGenre(@RequestBody Genre genre){
		try {
			if(genreService.addNewGenre(genre)) {
				return new ResponseEntity<String>("{\"result\":\"add genre successfully\"}", HttpStatus.OK);
			}
			else {
				return new ResponseEntity<String>("{\"result\":\"add genre fail\"}", HttpStatus.OK);
			}
		}catch (Exception e) {
			return new ResponseEntity<String>("{\"result\":\"un-recognize error\"}", HttpStatus.OK);
		}
	}
	
	@CrossOrigin
	@PostMapping("edit")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> editGenre(@RequestBody Genre genre){
		try {
			if(genreService.editGenre(genre)) {
				return new ResponseEntity<String>("{\"result\":\"eidt genre successfully\"}", HttpStatus.OK);
			}
			else {
				return new ResponseEntity<String>("{\"result\":\"edit genre fail\"}", HttpStatus.OK);
			}
		}catch (Exception e) {
			return new ResponseEntity<String>("{\"result\":\"un-recognize error\"}", HttpStatus.OK);
		}
	}
}
