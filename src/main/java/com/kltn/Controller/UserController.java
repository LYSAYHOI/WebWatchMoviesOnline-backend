package com.kltn.Controller;

import javax.validation.Valid;

import com.kltn.DTO.ChangePasswordForm;
import com.kltn.Model.User;
import com.kltn.Service.IFilmService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.kltn.Configuration.JwtProvider;
import com.kltn.DTO.SignUpForm;
import com.kltn.DTO.jwtResponse;
import com.kltn.DTO.loginForm;
import com.kltn.Service.IUserService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

	@Autowired
	private IUserService userService;

	@Autowired
	private IFilmService filmService;
	
	@Autowired
	private PasswordEncoder pe;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtProvider jwtProvider;


	@PostMapping("login")
	@CrossOrigin
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody loginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                		loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);
        jwtResponse tokenResponse = new jwtResponse(jwt);
        return ResponseEntity.ok(tokenResponse);
    }

	@GetMapping("unset")
	@PreAuthorize("hasAuthority('ADMIN')")
	@CrossOrigin
	public ResponseEntity<String> unset(){
		return new ResponseEntity<String>("{\"result\":\"access success\"}", HttpStatus.OK);
	}
	
	@PostMapping("signup")
	@CrossOrigin
	public ResponseEntity<?> signUp(@RequestBody SignUpForm user) {
		if(userService.signUp(user)) {
			return new ResponseEntity<String>("{\"result\":\"Sign up successfully\"}", HttpStatus.OK);
		}
		return new ResponseEntity<String>("{\"result\":\"Sign up failed\"}", HttpStatus.OK);
	}

	@GetMapping("like")
	@CrossOrigin
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> likeFilm(@RequestHeader(value = "Authorization")String token, int filmId) {
		try {
			Claims claims = jwtProvider.decodeToken(token.substring(7));
			if(userService.likeFilm(claims.getSubject(), filmId)){
				return new ResponseEntity<String>("{\"viewcount\":\""+filmService.updateViewProperties(filmId)+"\"}", HttpStatus.OK);
			} return new ResponseEntity<String>("{\"result\":\"like film failure\"}", HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("{\"result\":\"like film failure\"}", HttpStatus.OK);
		}
	}

	@GetMapping("blockuser")
	@CrossOrigin
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> blockUser(@RequestParam String username) {
		if(userService.blockuser(username)) {
			return new ResponseEntity<String>("{\"result\":\"block user successfully\"}", HttpStatus.OK);
		}
		return new ResponseEntity<String>("{\"result\":\"block user failed\"}", HttpStatus.OK);
	}

	@GetMapping("getalluser")
	@CrossOrigin
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> getAllUser(@RequestParam int pageindex, @RequestParam int pagesize) {
		try {
			List<User> listUser = userService.getAllUser(pageindex, pagesize);
			for(int i=0; i<listUser.size(); i++) {
				listUser.get(i).setPassword(null);
				listUser.get(i).setRoles(null);
				listUser.get(i).setLikedFilm(null);
			}
			return new ResponseEntity<>(listUser, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
		}
	}

	@PostMapping("changepassword")
	@CrossOrigin
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> changePassword(@RequestHeader(value = "Authorization") String token, @RequestBody ChangePasswordForm changePasswordForm) {
		Claims claims = jwtProvider.decodeToken(token.substring(7));
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						claims.getSubject(),
						changePasswordForm.getOldPassword()
				)
		);
		if(userService.changePassword(claims.getSubject(), changePasswordForm.getNewPassword())){
			return new ResponseEntity<>("{\"result\":\"success\"}", HttpStatus.OK);
		} return new ResponseEntity<>("{\"result\":\"fail\"}", HttpStatus.OK);
	}

	@PostMapping("changeinfo")
	@CrossOrigin
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> changeInfo(@RequestHeader(value = "Authorization") String token, @RequestBody SignUpForm user) {
		System.out.println("--------------------------------------------------------------"+user.getBirthday());
		System.out.println("--------------------------------------------------------------"+user.isMale());
		Claims claims = jwtProvider.decodeToken(token.substring(7));
		if(userService.changeInfo(claims.getSubject(), user)){
			return new ResponseEntity<>("{\"result\":\"success\"}", HttpStatus.OK);
		} return new ResponseEntity<>("{\"result\":\"fail\"}", HttpStatus.OK);
	}
}