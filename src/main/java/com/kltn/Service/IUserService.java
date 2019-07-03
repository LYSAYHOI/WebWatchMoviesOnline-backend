package com.kltn.Service;

import com.kltn.DTO.SignUpForm;
import com.kltn.Model.User;

public interface IUserService {
	public boolean signUp(SignUpForm signUpInfo);
	public User getUserFromUsername(String username);
	public void addAdmin(User user);
	public long checkIfExistAdmin();
	public boolean likeFilm(String username, int filmId);
}
