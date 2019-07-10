package com.kltn.ServiceImpl;

import javax.transaction.Transactional;

import com.kltn.Model.Role;
import com.kltn.Model.RoleName;
import com.kltn.Service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kltn.DTO.SignUpForm;
import com.kltn.Model.User;
import com.kltn.Repository.IUserRepository;
import com.kltn.Service.IUserService;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements IUserService{
	
	@Autowired
	private PasswordEncoder pe;
	
	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IRoleService roleService;
	
	@Transactional
	public boolean signUp(SignUpForm signUpInfo) {
		User newUser = new User();
		newUser.setBirthday(signUpInfo.getBirthday());
		newUser.setEmail(signUpInfo.getEmail());
		newUser.setMale(signUpInfo.isMale());
		newUser.setName(signUpInfo.getName());
		newUser.setUsername(signUpInfo.getUsername());
		newUser.setPassword(pe.encode(signUpInfo.getPassword()));
		Set<Role> roles = new HashSet<>();
		roles.add(roleService.getRole(RoleName.MEMBER));
		newUser.setRoles(roles);
		if(userRepository.getUser(newUser.getUsername()) == null && userRepository.saveNewUser(newUser) == true) {
			return true;
		}
		return false;
	}

	public User getUserFromUsername(String username) {
		return userRepository.getUser(username);
	}

	@Transactional
	public void addAdmin(User user){
		Set<Role> setrole = user.getRoles();
		Set<Role> rolesToAdd = new HashSet<>();
		for (Iterator<Role> it = setrole.iterator(); it.hasNext(); ) {
			Role f = it.next();
			rolesToAdd.add(roleService.getRole(f.getRoleName()));
		}
		user.setRoles(rolesToAdd);
		userRepository.saveNewUser(user);
	}

	@Transactional
	public long checkIfExistAdmin(){
		return userRepository.checkIfExistAdmin();
	}

	@Transactional
	public boolean likeFilm(String username, int filmId) {
		return userRepository.likeFilm(username, filmId);
	}

	@Transactional
	public boolean blockuser(String username){
		return userRepository.blockuser(username);
	}

	@Transactional
	public List<User> getAllUser(int pageindex, int pagesize) {
		return userRepository.getAllUser(pageindex, pagesize);
	}

	@Transactional
	public boolean changePassword(String username, String newPassword) {
		return userRepository.changePassword(username, pe.encode(newPassword));
	}

	@Transactional
	public boolean changeInfo(String username, SignUpForm editedUser) {
		if(editedUser.getName().equals("") || editedUser.getName() == null ||
				editedUser.getEmail().equals("") || editedUser.getEmail() == null ||
				editedUser.getBirthday() == null)
			return false;
		User user = new User();
		user.setBirthday(editedUser.getBirthday());
		user.setEmail(editedUser.getEmail());
		user.setMale(editedUser.isMale());
		user.setName(editedUser.getName());
		return userRepository.changeInfo(username, user);
	}
}