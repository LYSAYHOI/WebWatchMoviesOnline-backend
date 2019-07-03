package com.kltn.Controller;

import com.kltn.Configuration.JwtProvider;
import com.kltn.Model.Episode;
import com.kltn.Model.Film;
import com.kltn.Model.User;
import com.kltn.Service.ICollectionService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CollectionController {

    @Autowired
    private ICollectionService collectionService;

    @Autowired
    private JwtProvider jwtProvider;

    @GetMapping("newepisode")
    @CrossOrigin
    public ResponseEntity<?> newEpisode(){
        List<Episode> newEp = collectionService.newEpisode();
        return new ResponseEntity<List>(newEp, HttpStatus.OK);
    }

    @GetMapping("newfilm")
    @CrossOrigin
    public ResponseEntity<?> newFilm(){
        List<Film> newFilm = collectionService.newFilm();
        return new ResponseEntity<List>(newFilm, HttpStatus.OK);
    }

    @GetMapping("likedFilm")
    @CrossOrigin
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> likedFilm(@RequestHeader(value = "Authorization")String token, @RequestParam int pagesize){
        Claims claims = jwtProvider.decodeToken(token.substring(7));
        List<User> newFilm = collectionService.likedFilm(claims.getSubject(), pagesize);
        return new ResponseEntity<List>(newFilm, HttpStatus.OK);
    }

    @GetMapping("ranking")
    @CrossOrigin
    public ResponseEntity<?> mostViewFilm(@RequestParam int pagesize){
        List<Film> newEp = collectionService.mostViewFilm(pagesize);
        return new ResponseEntity<List>(newEp, HttpStatus.OK);
    }
}
