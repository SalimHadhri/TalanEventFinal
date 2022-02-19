package tn.talan.academyApp.dtos;

import java.io.Serializable;

import tn.talan.academyApp.entities.UserEventParticipationkey;

public class UserEventParticipationDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private UserEventParticipationkey id;
	private UserDto user;

	
	private EventDto event;
	private Boolean notified = Boolean.FALSE;

	private boolean validatedCriterias;


	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public EventDto getEvent() {
		return event;
	}

	public void setEvent(EventDto event) {
		this.event = event;
	}

	public UserEventParticipationkey getId() {
		return id;
	}

	public void setId(UserEventParticipationkey id) {
		this.id = id;
	}

	public boolean isValidatedCriterias() {
		return validatedCriterias;
	}

	public void setValidatedCriterias(boolean validatedCriterias) {
		this.validatedCriterias = validatedCriterias;
	}

	public Boolean getNotified() {
		return notified;
	}

	public void setNotified(Boolean notified) {
		this.notified = notified;
	}


	
	
}
