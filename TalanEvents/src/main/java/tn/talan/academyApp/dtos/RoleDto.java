package tn.talan.academyApp.dtos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class RoleDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long roleId;
	private String name;
	@JsonIgnore
	private Set<UserDto> users = new HashSet<>();

	public RoleDto(Long roleId, String name, Set<UserDto> users) {
		this.roleId = roleId;
		this.name = name;
		this.users = users;
	}
	
	public RoleDto(Long roleId, String name) {
		this.roleId = roleId;
		this.name = name;
	}
	

	public RoleDto() {
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<UserDto> getUsers() {
		return users;
	}

	public void setUsers(Set<UserDto> users) {
		this.users = users;
	}



}
