package tn.talan.academyApp.services;

import tn.talan.academyApp.dtos.RoleDto;

public interface RoleService {

	public RoleDto findRoleById(Long roleId);

	public RoleDto findRoleByName(String name);

}
