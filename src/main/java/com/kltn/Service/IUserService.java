package com.kltn.Service;

import com.kltn.DTO.SignUpForm;
import com.kltn.Model.User;

import java.util.List;

public interface IUserService {
	public boolean signUp(SignUpForm signUpInfo);
	public User getUserFromUsername(String username);
	public void addAdmin(User user);
	public long checkIfExistAdmin();
	public boolean likeFilm(String username, int filmId);
	public boolean blockuser(String username);
	public List<User> getAllUser(int pageindex, int pagesize);
	public boolean changePassword(String username, String newPassword);
	public boolean changeInfo(String username, SignUpForm editedUser);
}
