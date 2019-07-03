package com.kltn.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table
public class Genre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int genreId;

	@Column(unique = true)
	private String genreName;

	@Column
	private String genreDescription;
	
	@ManyToMany(mappedBy = "genre")
	@JsonIgnore
	private Set<Film> film;

	public int getGenreId() {
		return genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	public String getGenreDescription() {
		return genreDescription;
	}

	public void setGenreDescription(String genreDescription) {
		this.genreDescription = genreDescription;
	}

	public Set<Film> getFilm() {
		return film;
	}

	public void setFilm(Set<Film> film) {
		this.film = film;
	}

	public Genre() {
		super();
	}
	
	
}
