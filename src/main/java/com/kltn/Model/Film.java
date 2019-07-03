package com.kltn.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table
public class Film {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int filmId;
	
	@Column
	private String filmName;
	
	@Column(columnDefinition = "TEXT")
	private String filmDescription;

	@Column
	private LocalDate addDate = LocalDate.now();

	@Column
	private int viewed = 0;

	@Column
	private int liked = 0;

	@Column
	private String image;

	@ManyToMany(mappedBy = "likedFilm")
	@JsonIgnore
	private Set<User> likedUser;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "genre_film",
		joinColumns = @JoinColumn(name = "film_id"),
		inverseJoinColumns = @JoinColumn(name = "genre_id"))
	//@JsonIgnore
	private Set<Genre> genre;

	public Film() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getFilmId() {
		return filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public String getFilmName() {
		return filmName;
	}

	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}

	public String getFilmDescription() {
		return filmDescription;
	}

	public void setFilmDescription(String filmDescription) {
		this.filmDescription = filmDescription;
	}

	public int getViewed() {
		return viewed;
	}

	public void setViewed(int viewed) {
		this.viewed = viewed;
	}

	public Set<User> getLikedUser() {
		return likedUser;
	}

	public void setLikedUser(Set<User> likedUser) {
		this.likedUser = likedUser;
	}

	public Set<Genre> getGenre() {
		return genre;
	}

	public void setGenre(Set<Genre> genre) {
		this.genre = genre;
	}

	public LocalDate getAddDate() {
		return addDate;
	}

	public void setAddDate(LocalDate addDate) {
		this.addDate = addDate;
	}

	public int getLiked() {
		return liked;
	}

	public void setLiked(int liked) {
		this.liked = liked;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
