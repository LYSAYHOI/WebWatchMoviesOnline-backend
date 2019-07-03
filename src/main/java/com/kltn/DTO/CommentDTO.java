package com.kltn.DTO;

import com.kltn.Model.Film;
import com.kltn.Model.User;

import java.time.LocalDateTime;


public class CommentDTO {

	private String commentContent;
	private int commentId;
	private LocalDateTime commentTime;
	private FilmDTO film;
	private UserDTO user;

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public FilmDTO getFilm() {
		return film;
	}

	public void setFilm(FilmDTO film) {
		this.film = film;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public LocalDateTime getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(LocalDateTime commentTime) {
		this.commentTime = commentTime;
	}
}
