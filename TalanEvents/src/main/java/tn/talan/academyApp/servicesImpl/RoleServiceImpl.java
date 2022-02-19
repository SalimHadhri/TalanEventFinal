package tn.talan.academyApp.servicesImpl;

import java.util.Optional; 
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.talan.academyApp.dtos.RoleDto;
import tn.talan.academyApp.entities.Role;
import tn.talan.academyApp.repositories.RoleRepository;
import tn.talan.academyApp.services.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleRepository roleRepository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public RoleDto findRoleById(Long roleId) {
		Optional<Role> roleRendred = roleRepository.findById(roleId);
		if (roleRendred.isPresent()) {
			return modelMapper.map(roleRendred.get(), RoleDto.class);
		}
		return null;
	}

	@Override
	public RoleDto findRoleByName(String name) {
		Optional<Role> roleRendred = roleRepository.findByName(name);
		if (roleRendred.isPresent()) {
			return modelMapper.map(roleRendred.get(), RoleDto.class);
		}
		return null;
	}
}
