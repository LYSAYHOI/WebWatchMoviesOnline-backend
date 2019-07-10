package com.kltn.RepositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.kltn.Model.Film;
import com.kltn.Model.Role;
import com.kltn.Service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kltn.Model.User;
import com.kltn.Repository.IUserRepository;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Repository
public class UserRepository implements IUserRepository{

	@Autowired
	private EntityManager entityManager;


	@Override
	public User getUser(String username) {
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<User> query = builder.createQuery(User.class);
			Root<User> root = query.from(User.class);
			query.where(builder.equal(root.get("username"), username));
			return entityManager.createQuery(query).getSingleResult();
		}catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public User getUserById(long userId) {
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<User> query = cb.createQuery(User.class);
			Root<User> root = query.from(User.class);
			query.where(cb.equal(root.get("userId"), userId));
			return entityManager.createQuery(query).getSingleResult();
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public boolean saveNewUser(User user) {
		try {
			entityManager.persist(user);
			return true;
		}catch (Exception ex) {
			System.out.println(ex);
			return false;
		}		
	}

	@Override
	public Long checkIfExistAdmin() {
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Long> query = cb.createQuery(Long.class);
			Root<User> root = query.from(User.class);
			query.select(cb.count(root));
			//query.where(cb.equal(root.get("roles").get("RoleName"), "ADMIN"));
			return entityManager.createQuery(query).getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean likeFilm(String username, int filmId) {
		try {
			User user = getUser(username);
			Film film = new Film();
			film.setFilmId(filmId);
			user.getLikedFilm().add(film);
			entityManager.merge(user);
			entityManager.flush();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean blockuser(String username){
		try {
			User user = getUser(username);
			user.setBlocked(true);
			entityManager.merge(user);
			return true;
		}catch (Exception e){
			return false;
		}
	}

	@Override
	public List<User> getAllUser(int pageindex, int pagesize) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> query = cb.createQuery(User.class);
		Root<User> root = query.from(User.class);
		List<User> listUser = entityManager.createQuery(query)
				.setMaxResults(pagesize)
				.setFirstResult((pageindex)*pagesize)
				.getResultList();
		return listUser;
	}

	@Override
	public boolean changePassword(String username, String newPassword) {
		try {
			User user = getUser(username);
			user.setPassword(newPassword);
			entityManager.merge(user);
			return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean changeInfo(String username, User editedUser) {
		try {
			User user = getUser(username);
			user.setName(editedUser.getName());
			user.setEmail(editedUser.getEmail());
			user.setMale(editedUser.isMale());
			user.setBirthday(editedUser.getBirthday());
			entityManager.merge(user);
			return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
}