package tn.talan.academyApp.security.services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import tn.talan.academyApp.entities.User;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long userId;
	private String firstName;
	private String lastName;
	private String email;

	@JsonIgnore
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl() {
		super();
	}

	public UserDetailsImpl(Long userId, String firstName, String lastName, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;

		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(User user) {

		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

		return new UserDetailsImpl(user.getUserId(), user.getFirstName(),

				user.getLastName(), user.getEmail(), user.getPassword(), authorities);

	}

	public Long getUserId() {
		return userId;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
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

}
