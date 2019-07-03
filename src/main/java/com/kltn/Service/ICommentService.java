package com.kltn.Service;

import com.kltn.DTO.CommentDTO;
import com.kltn.DTO.FilmDTO;
import com.kltn.Model.Comment;
import org.springframework.data.util.Pair;

import java.util.List;

public interface ICommentService {
    public Pair<Comment, String> addComment(String commentContent, String username, FilmDTO film);
    public List<CommentDTO> getComment(int filmId, int pagesize);
}
