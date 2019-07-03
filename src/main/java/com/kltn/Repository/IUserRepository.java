package com.kltn.Repository;

import com.kltn.Model.User;

public interface IUserRepository {
	
	public User getUser(String username);
	public boolean saveNewUser(User user);
	public User getUserById(long userId);
	public Long checkIfExistAdmin();
	public boolean likeFilm(String username, int filmId);
}
