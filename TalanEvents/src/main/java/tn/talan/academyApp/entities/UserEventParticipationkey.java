package tn.talan.academyApp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserEventParticipationkey implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "event_id")
	private Long eventId;
	
	
	
	

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public UserEventParticipationkey(Long userId, Long eventId) {
		this.userId = userId;
		this.eventId = eventId;
	}

	public UserEventParticipationkey() {
	}

	


	
	
	
	

}
