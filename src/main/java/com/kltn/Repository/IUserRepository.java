package com.kltn.Repository;

import com.kltn.Model.User;

import java.util.List;

public interface IUserRepository {
	
	public User getUser(String username);
	public boolean saveNewUser(User user);
	public User getUserById(long userId);
	public Long checkIfExistAdmin();
	public boolean likeFilm(String username, int filmId);
	public boolean blockuser(String username);
	public List<User> getAllUser(int pageindex, int pagesize);
	public boolean changePassword(String username, String newPassword);
	public boolean changeInfo(String username, User editedUser);
}
