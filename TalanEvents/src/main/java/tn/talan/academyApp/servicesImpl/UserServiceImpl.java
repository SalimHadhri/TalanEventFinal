package tn.talan.academyApp.servicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.talan.academyApp.dtos.UserDto;
import tn.talan.academyApp.entities.User;
import tn.talan.academyApp.repositories.UserRepository;
import tn.talan.academyApp.services.EventService;
import tn.talan.academyApp.services.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	private ModelMapper modelMapper = new ModelMapper();

	@Autowired
	public EventService eventService;

	@Override
	public UserDto findByEmail(String email) {
		Optional<User> userRendred = userRepository.findByEmail(email);
		if (userRendred.isPresent()) {
			return modelMapper.map(userRendred.get(), UserDto.class);
		}
		return null;
	}

	@Override
	public Boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public UserDto addUser(UserDto userDto) {
		return modelMapper.map(userRepository.save(modelMapper.map(userDto, User.class)), UserDto.class);
	}

	@Override
	public UserDto findUserByIdUser(Long id) {
		return modelMapper.map(userRepository.findById(id).get(), UserDto.class);
	}

	@Override
	public List<UserDto> findAllUsers() {

		List<User> allUsers = userRepository.findAll();
		List<UserDto> allUsersdto = new ArrayList<>();

		for (User user : allUsers) {

			UserDto userDtoToAdd = modelMapper.map(user, UserDto.class);
			allUsersdto.add(userDtoToAdd);
		}

		return allUsersdto;
	}

	



}
