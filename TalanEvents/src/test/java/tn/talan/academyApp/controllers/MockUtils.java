package tn.talan.academyApp.controllers;

import java.util.Date;
import java.util.List;
import java.util.Set;

import tn.talan.academyApp.dtos.CritereDto;
import tn.talan.academyApp.dtos.EventDto;
import tn.talan.academyApp.dtos.RoleDto;
import tn.talan.academyApp.dtos.UserDto;
import tn.talan.academyApp.dtos.UserEventParticipationDto;

public class MockUtils {

	private MockUtils() {
	}

	public static EventDto getEvent(Long eventId, String nameEvent, String descriptionEvent, String lieuEvent,
			Date dateDebutEvent, Date dateFinEvent, int nombreParticipantsEvent, boolean annulée, String pathImage,
			String status, Set<CritereDto> criteresEvent, Set<UserEventParticipationDto> userEventParticipation,
			UserDto userCreator) {

		return new EventDto(eventId, nameEvent, descriptionEvent, lieuEvent, dateDebutEvent, dateFinEvent,
				nombreParticipantsEvent, annulée, pathImage, status, criteresEvent, userEventParticipation,
				userCreator);
	}

	public static UserDto getUser(Long userId, String firstName, String lastName, String password, String email,
			Set<RoleDto> roles, Set<UserEventParticipationDto> userEventParticipation,
			List<EventDto> evenementsCreated) {

		return new UserDto(userId, firstName, lastName, password, email, roles, userEventParticipation,
				evenementsCreated);
	}

	public static CritereDto getCritere(Long critereId, String descriptionCritere, EventDto event) {

		return new CritereDto(critereId, descriptionCritere, event);
	}

	public static RoleDto getRole(Long roleId, String name) {

		return new RoleDto(roleId, name);
	}
	
	
}
