package tn.talan.academyApp.dtos;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long userId;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	@JsonIgnore
	private Set<RoleDto> roles;
	@JsonIgnore
	private Set<UserEventParticipationDto> userEventParticipation;
	@JsonIgnore
	private List<EventDto> evenementsCreated;
	
	

	public UserDto() {
	}

	
	public UserDto(Long userId, String firstName, String lastName, String password, String email, Set<RoleDto> roles,
			Set<UserEventParticipationDto> userEventParticipation, List<EventDto> evenementsCreated) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.roles = roles;
		this.userEventParticipation = userEventParticipation;
		this.evenementsCreated = evenementsCreated;
	}


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDto> roles) {
		this.roles = roles;
	}

	public List<EventDto> getEvenementsCreated() {
		return evenementsCreated;
	}

	public void setEvenementsCreated(List<EventDto> evenementsCreated) {
		this.evenementsCreated = evenementsCreated;
	}

	

	public UserDto(Long userId, String firstName, String lastName, String password, String email, Set<RoleDto> roles,
			List<EventDto> evenementsCreated) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.roles = roles;
		this.evenementsCreated = evenementsCreated;
	}

	public UserDto(String firstName, String lastName, String password, String email, Set<RoleDto> roles,
			List<EventDto> evenementsCreated) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.roles = roles;
		this.evenementsCreated = evenementsCreated;
	}

	public UserDto(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public UserDto(String email, String firstName, String lastName, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
	}

	
	public Set<UserEventParticipationDto> getUserEventParticipation() {
		return userEventParticipation;
	}

	public void setUserEventParticipation(Set<UserEventParticipationDto> userEventParticipation) {
		this.userEventParticipation = userEventParticipation;
	}

}
