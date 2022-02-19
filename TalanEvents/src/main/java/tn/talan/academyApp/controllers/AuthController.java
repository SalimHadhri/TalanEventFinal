package tn.talan.academyApp.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.talan.academyApp.dtos.RoleDto;
import tn.talan.academyApp.dtos.UserDto;
import tn.talan.academyApp.payload.request.LoginRequest;
import tn.talan.academyApp.payload.request.SignUpRequest;
import tn.talan.academyApp.payload.response.JwtResponse;
import tn.talan.academyApp.payload.response.MessageResponse;
import tn.talan.academyApp.security.jwt.JwtUtils;
import tn.talan.academyApp.security.services.UserDetailsImpl;
import tn.talan.academyApp.services.RoleService;
import tn.talan.academyApp.services.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUserId(), userDetails.getUsername(),

				roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

		if (userService.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		UserDto userDto = new UserDto(signUpRequest.getEmail(), signUpRequest.getFirstName(),
				signUpRequest.getLastName(), encoder.encode(signUpRequest.getPassword()));

		Set<RoleDto> roles = new HashSet<>();
		roles.add(roleService.findRoleByName("ROLE_Collaborateur"));
		userDto.setRoles(roles);
		userService.addUser(userDto);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

}
