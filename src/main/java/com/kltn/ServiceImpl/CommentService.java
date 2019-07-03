package com.kltn.ServiceImpl;

import com.kltn.DTO.CommentDTO;
import com.kltn.DTO.FilmDTO;
import com.kltn.DTO.UserDTO;
import com.kltn.Model.Comment;
import com.kltn.Model.Film;
import com.kltn.Model.User;
import com.kltn.Repository.ICommentRepository;
import com.kltn.Service.ICommentService;
import com.kltn.Service.IUserService;
import com.kltn.Utilities.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService implements ICommentService {

    @Autowired
    private IUserService userService;

    @Autowired
    private ICommentRepository commentRepository;

    @Transactional
    public Pair<Comment, String> addComment(String commentContent, String username, FilmDTO film) {
        try {
            User CommentUser = userService.getUserFromUsername(username);
            if(CommentUser == null) throw new CustomException("user not found");
            if(commentContent.equals("")) throw new CustomException("comment no content");
            Comment newComment = commentRepository.addComment(commentContent, CommentUser.getUserId(), film.getFilmId());
            if(newComment == null) throw new CustomException("comment can not be created");
            return Pair.of(newComment, "success" );
        }
        catch(CustomException e){
            return Pair.of(null, e.getMessage() );
        }
        catch(Exception e){
            e.printStackTrace();
            return Pair.of(null, "unrecognized error" );
        }
    }

    @Override
    public List<CommentDTO> getComment(int filmId, int pagesize) {
        try {
            List<Comment> listComment = commentRepository.getComment(filmId, pagesize);
            List<CommentDTO> listReturnComment = new ArrayList<>();
            for (int i = 0; i < listComment.size(); i++) {
                CommentDTO com = new CommentDTO();
                UserDTO use = new UserDTO();
                FilmDTO fil = new FilmDTO();
                com.setCommentContent(listComment.get(i).getCommentContent());
                com.setCommentId(listComment.get(i).getCommentId());
                com.setCommentTime(listComment.get(i).getCommentTime());
                use.setUserId(listComment.get(i).getUser().getUserId());
                use.setUserName(listComment.get(i).getUser().getUsername());
                fil.setFilmId(listComment.get(i).getFilm().getFilmId());
                fil.setFilmName(listComment.get(i).getFilm().getFilmName());
                com.setFilm(fil);
                com.setUser(use);
                listReturnComment.add(com);
            }
            return listReturnComment;
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}

