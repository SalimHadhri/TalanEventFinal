package tn.talan.academyApp.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "Critere")
public class Critere implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long critereId;
	
	
	private String descriptionCritere ;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id")
	@JsonIgnore
	private Event event;



	public Critere() {
	}


	public Critere(String descriptionCritere, Event event) {

		this.descriptionCritere = descriptionCritere;
		this.event = event;
	}


	public Critere(Long critereId,  String descriptionCritere) {
		this.critereId = critereId;
		this.descriptionCritere = descriptionCritere;
	}


	public Critere(Long critereId,  String descriptionCritere, Event event) {
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


	public Event getEvent() {
		return event;
	}


	public void setEvent(Event event) {
		this.event = event;
	}


	
		

}
