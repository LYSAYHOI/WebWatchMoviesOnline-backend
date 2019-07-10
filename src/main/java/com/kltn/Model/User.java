package com.kltn.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	
	@Column
	private String name;
	
	@Column(unique = true)
	private String username;
	
	@Column
	private String email;
	
	@Column
	private boolean isMale;
	
	@Column
	private LocalDate birthday;

	@Column
	private boolean isBlocked = false;

	@ManyToMany
	@JoinTable(
			name = "film_likeduser",
			joinColumns = @JoinColumn(name = "userId"),
			inverseJoinColumns = @JoinColumn(name = "filmId"))
	private Set<Film> likedFilm;
	
	@Column
	@NotBlank
    @Size(min=6, max = 100)
	private String password;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", 
    	joinColumns = @JoinColumn(name = "user_id"), 
    	inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
	
	public User() {
		super();
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isMale() {
		return isMale;
	}

	public void setMale(boolean isMale) {
		this.isMale = isMale;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public Set<Film> getLikedFilm() {
		return likedFilm;
	}

	public void setLikedFilm(Set<Film> likedFilm) {
		this.likedFilm = likedFilm;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean blocked) {
		isBlocked = blocked;
	}
}
