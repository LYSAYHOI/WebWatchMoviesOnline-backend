package com.kltn.Repository;

import com.kltn.Model.Comment;
import com.kltn.Model.Film;
import com.kltn.Model.User;

import java.util.List;

public interface ICommentRepository {
    public Comment addComment(String commentContent, long userId, int filmId);
    public List<Comment> getComment(int filmId, int pagesize);
}
