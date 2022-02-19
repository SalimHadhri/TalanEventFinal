package tn.talan.academyApp.services;

import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.List;

import javax.mail.MessagingException;

import tn.talan.academyApp.dtos.EventDto;
import tn.talan.academyApp.dtos.UserDto;
import tn.talan.academyApp.dtos.UserEventParticipationDto;

public interface UserEventParticipationService {

	public List<EventDto> findEventParticipated(Long idUser);

	
	// if validated criteria is true
	public EventDto subscribeEvent(Long idUser, Long idEvent,boolean validatet);

	// only validated criterias is on 
	public List<UserDto> findUserParticipated(Long idEvent);

	public EventDto annulerUserEventParticipation(Long idUser, Long idEvent);

	public UserDto topParticipation(List<UserDto> allUsers);

	public int maxIndiceList(List<Long> list);

	public List<UserDto> topThreeDeParticipation();

	public UserDto topUserCreations(List<UserDto> allUsers);

	public List<UserDto> topThreeUserCreators();

	// pourcentage de remplissage de l'evenement
	public Long tauxDePresenceParEvenement(Long idEvent);

	public List<EventDto> topThreePresenceParEvenement();

	public EventDto topEventTauxParicipation(List<EventDto> allEvents);

	public void notifyEventParticipators() throws GeneralSecurityException, MessagingException, ParseException;

	public EventDto validateCriteriasUser(Long idUser, Long idEvent) ;
	
	public Long successRateEvents() ;
	
	public int nombreParticipationUserParEvenement(Long idUser) ;
	
	public List<UserEventParticipationDto> findAllUserEventParticipations()  ;
	
	public List<UserEventParticipationDto> findUserEventByUser(UserDto userDto) ;
	
	
	

}
