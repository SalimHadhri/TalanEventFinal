package tn.talan.academyApp.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class UserEventParticipation implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private UserEventParticipationkey id;


	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name = "user_id")

	private User user;



	@ManyToOne
	@MapsId("eventId")
	@JoinColumn(name = "event_id")

	private Event event;
	
	private boolean validatedCriterias ;
	
	private boolean notified = Boolean.FALSE;






	public UserEventParticipationkey getId() {
		return id;
	}

	public void setId(UserEventParticipationkey id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}


	

	public UserEventParticipation(UserEventParticipationkey id, User user, Event event) {
		this.id = id;
		this.user = user;
		this.event = event;
	}

	public UserEventParticipation(UserEventParticipationkey id, User user, Event event, boolean validated) {
		this.id = id;
		this.user = user;
		this.event = event;
		this.validatedCriterias = validated;
	}

	public UserEventParticipation() {
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
