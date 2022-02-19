package tn.talan.academyApp.services;

import java.util.List;

import tn.talan.academyApp.dtos.UserDto;

public interface UserService {
	public UserDto findByEmail(String email);

	public Boolean existsByEmail(String email);

	public UserDto addUser(UserDto userDto);

	public UserDto findUserByIdUser(Long id);
	
	public List<UserDto> findAllUsers() ;
	

}
