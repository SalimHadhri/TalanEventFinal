package tn.talan.academyApp.servicesImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.talan.academyApp.dtos.CritereDto;
import tn.talan.academyApp.entities.Critere;
import tn.talan.academyApp.repositories.CritereRepository;
import tn.talan.academyApp.services.CritereService;
import tn.talan.academyApp.services.EventService;

@Service
@Transactional
public class CritereServiceeImpl implements CritereService {

	@Autowired
	CritereRepository critereRepository;
	@Autowired
	EventService eventService;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public CritereDto addCritere(CritereDto critereDto) {

		return modelMapper.map(critereRepository.save(modelMapper.map(critereDto, Critere.class)), CritereDto.class);
	}






}
