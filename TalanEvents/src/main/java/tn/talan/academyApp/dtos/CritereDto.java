package tn.talan.academyApp.dtos;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CritereDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long critereId;
	private String descriptionCritere;
	@JsonIgnore
	private EventDto event;


	public CritereDto() {
	}

	


	public CritereDto(String descriptionCritere, EventDto event) {
		this.descriptionCritere = descriptionCritere;
		this.event = event;
	}


	public CritereDto(Long critereId,  String descriptionCritere,EventDto event) {
		this.critereId = critereId;	
		this.descriptionCritere = descriptionCritere;
		this.event = event;
	}


	public Long getCritereId() {
		return critereId;
	}

	public void setCritereId(Long critereId) {
		this.critereId = critereId;
	}

	

	public String getDescriptionCritere() {
		return descriptionCritere;
	}

	public void setDescriptionCritere(String descriptionCritere) {
		this.descriptionCritere = descriptionCritere;
	}

	public EventDto getEvent() {
		return event;
	}

	public void setEvent(EventDto event) {
		this.event = event;
	}



	

	

}
