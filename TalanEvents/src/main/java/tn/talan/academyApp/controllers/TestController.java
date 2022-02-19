package tn.talan.academyApp.controllers;

import org.springframework.security.access.prepost.PreAuthorize; 
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

	

	
	@GetMapping("/Collab")
	@PreAuthorize("hasRole('Collaborateur')")
	public String userAccess() {
		return "Collab Content.";
	}


	@GetMapping("/admin")
	@PreAuthorize("hasRole('Administrateur')")
	public String adminAccess() {
		return "Admin Board.";
	}
}
