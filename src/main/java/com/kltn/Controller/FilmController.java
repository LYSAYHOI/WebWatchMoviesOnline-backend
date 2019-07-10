package com.kltn.Controller;

import com.kltn.DTO.FilmAndLength;
import com.kltn.Model.Film;
import com.kltn.Service.IFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("film")
public class FilmController {

    @Autowired
    private IFilmService filmService;

    @GetMapping("get")
    @CrossOrigin
    public ResponseEntity<?> getFilm(@RequestParam int filmId){
        Film film = filmService.getFilm(filmId);
        if(film != null){
            return new ResponseEntity<Film>(film, HttpStatus.OK);
        }
        return new ResponseEntity<String>("{\"result\":\"no film found\"}", HttpStatus.OK);
    }

    @PostMapping("add")
    @CrossOrigin
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> addFilm(@RequestBody Film film){
        Film filmAdded = filmService.addFilm(film);
        if(filmAdded != null){
            return new ResponseEntity<Film>(filmAdded, HttpStatus.OK);
        }
        return new ResponseEntity<String>("{\"result\":\"add film fail\"}", HttpStatus.OK);
    }

    @GetMapping("getall")
    @CrossOrigin
    public ResponseEntity<?> getAllFilm(@RequestParam int pageNumber, @RequestParam int pageSize) {
        FilmAndLength filmAndLength = filmService.getAllFilm(pageNumber, pageSize);
        if(filmAndLength.getFilm().size() != 0){
            return new ResponseEntity<FilmAndLength>(filmAndLength, HttpStatus.OK);
        }
        return new ResponseEntity<String>("{\"result\":\"no film found\"}", HttpStatus.OK);
    }

    @PostMapping("edit")
    @CrossOrigin
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> editFilm(@RequestBody Film film) {
        boolean result = filmService.editFilm(film);
        if(result){
            return new ResponseEntity<String>("{\"result\":\"edit film success\"}", HttpStatus.OK);
        }
        return new ResponseEntity<String>("{\"result\":\"edit film fail\"}", HttpStatus.OK);
    }

    @GetMapping("byGenre")
    @CrossOrigin
    public ResponseEntity<?> getFilmByGenre(@RequestParam int genreId, @RequestParam int pagesize) {
        try {
            List<Film> listFilm = filmService.getFilmByGenre(genreId, pagesize);
            return new ResponseEntity<List<Film>>(listFilm, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("{\"result\":\"" +e.getMessage()+ "\"}", HttpStatus.OK);
        }
    }

}
