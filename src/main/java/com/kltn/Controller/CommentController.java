package com.kltn.Controller;

import com.kltn.Configuration.JwtProvider;
import com.kltn.DTO.CommentDTO;
import com.kltn.Model.Comment;
import com.kltn.Service.ICommentService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private ICommentService commentService;

    @PostMapping("comment")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin
    public ResponseEntity<?> addComment(@RequestHeader(value = "Authorization")String token, @RequestBody CommentDTO comment){
        try {
            Claims claims = jwtProvider.decodeToken(token.substring(7));
            Pair<Comment, String> result = commentService.addComment(comment.getCommentContent(), claims.getSubject(), comment.getFilm());
            if (result.getFirst() == null) {
                return new ResponseEntity<String>("{\"result\":\" " + result.getSecond() + " \"}", HttpStatus.OK);
            }
            return new ResponseEntity<Comment>( result.getFirst(), HttpStatus.OK);
        } catch(Exception e) {
            //e.printStackTrace();
            return new ResponseEntity<String>("{\"result\":\" Unrecognized error \"}", HttpStatus.OK);
        }
    }

    @GetMapping("getcomment")
    @CrossOrigin
    public ResponseEntity<?> getComment(@RequestParam int filmId, @RequestParam int pagesize) {
        try {
            return new ResponseEntity<List<CommentDTO>>(commentService.getComment(filmId, pagesize), HttpStatus.OK);
        }catch(Exception ex) {
            ex.getMessage();
            return new ResponseEntity<List<CommentDTO>>(new ArrayList<>(), HttpStatus.OK);
        }
    }
}
