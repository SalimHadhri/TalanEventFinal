package tn.talan.academyApp.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "`user`")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long userId;

	private String firstName;
	private String lastName;
	private String password;
	private String email;


	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))

	private Set<Role> roles = new HashSet<>();

	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private Set<UserEventParticipation> userEventParticipation;

	@OneToMany(mappedBy = "userCreator", fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Event> evenementsCreated;

	public User() {
	}

	public User(Long userId) {
		this.userId = userId;
	}

	public User(String email, String firstName, String lastName, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
	}

	public User(String email, String password) {
		this.password = password;
		this.email = email;
	}

	public User(Long userId, String firstName, String lastName, String password, String email, Set<Role> roles) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.roles = roles;
	}
	

	public User(Long userId, String firstName, String lastName, String password, String email, Set<Role> roles,
			Set<UserEventParticipation> userEventParticipation, List<Event> evenementsCreated) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.roles = roles;
		this.userEventParticipation = userEventParticipation;
		this.evenementsCreated = evenementsCreated;
	}

	public User(String firstName, String lastName, String password, String email, Set<Role> roles,
			Set<UserEventParticipation> userEventParticipation, List<Event> evenementsCreated) {
		super();
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
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

	public Set<UserEventParticipation> getUserEventParticipation() {
		return userEventParticipation;
	}

	public void setUserEventParticipation(Set<UserEventParticipation> userEventParticipation) {
		this.userEventParticipation = userEventParticipation;
	}

	public List<Event> getEvenementsCreated() {
		return evenementsCreated;
	}

	public void setEvenementsCreated(List<Event> evenementsCreated) {
		this.evenementsCreated = evenementsCreated;
	}

}
