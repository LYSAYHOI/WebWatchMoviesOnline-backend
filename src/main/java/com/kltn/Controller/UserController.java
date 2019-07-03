package com.kltn.Controller;

import javax.validation.Valid;

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


	@PostMapping("/login")
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
		return new ResponseEntity<String>("{\"result\":\"Sign up failed\"}", HttpStatus.PRECONDITION_FAILED);
	}

	@GetMapping("like")
	@CrossOrigin
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> likeFilm(@RequestHeader(value = "Authorization")String token, int filmId) {
		try {
			Claims claims = jwtProvider.decodeToken(token.substring(7));
			if(userService.likeFilm(claims.getSubject(), filmId)){
				return new ResponseEntity<String>("{\"viewcount\":\""+filmService.updateViewProperties(filmId)+"\"}", HttpStatus.OK);
			} return new ResponseEntity<String>("{\"result\":\"like film failure\"}", HttpStatus.INTERNAL_SERVER_ERROR);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("{\"result\":\"like film failure\"}", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}