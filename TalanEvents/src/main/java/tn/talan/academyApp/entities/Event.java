package tn.talan.academyApp.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Event")
public class Event implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long eventId;

	private String nameEvent;
	private String descriptionEvent;
	private String lieuEvent;
	private Date dateDebutEvent;
	private Date dateFinEvent;
	private int nombreParticipantsEvent;
	private Boolean annulée = Boolean.FALSE;
	private String pathImage;
	private String status ;

	@OneToMany(fetch = FetchType.LAZY,mappedBy = "event")
 
	private Set<Critere> criteresEvent = new HashSet<>();



	public Event(Long eventId, String nameEvent, String descriptionEvent, String lieuEvent, Date dateDebutEvent,
			Date dateFinEvent, int nombreParticipantsEvent, Boolean annulée, String pathImage, String status,
			Set<Critere> criteresEvent, Set<UserEventParticipation> userEventParticipation, User userCreator) {
		this.eventId = eventId;
		this.nameEvent = nameEvent;
		this.descriptionEvent = descriptionEvent;
		this.lieuEvent = lieuEvent;
		this.dateDebutEvent = dateDebutEvent;
		this.dateFinEvent = dateFinEvent;
		this.nombreParticipantsEvent = nombreParticipantsEvent;
		this.annulée = annulée;
		this.pathImage = pathImage;
		this.status = status;
		this.criteresEvent = criteresEvent;
		this.userEventParticipation = userEventParticipation;
		this.userCreator = userCreator;
	}

	@OneToMany(mappedBy = "event")
	@JsonIgnore
	private Set<UserEventParticipation> userEventParticipation;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	
	private User userCreator;
	
	

	public Event() {
	}



	public Event(String nameEvent, String descriptionEvent, String lieuEvent, Date dateDebutEvent, Date dateFinEvent,
			int nombreParticipantsEvent, boolean annulée, String pathImage, String status, Set<Critere> criteresEvent,
			Set<UserEventParticipation> userEventParticipation, User userCreator) {
		this.nameEvent = nameEvent;
		this.descriptionEvent = descriptionEvent;
		this.lieuEvent = lieuEvent;
		this.dateDebutEvent = dateDebutEvent;
		this.dateFinEvent = dateFinEvent;
		this.nombreParticipantsEvent = nombreParticipantsEvent;
		this.annulée = annulée;
		this.pathImage = pathImage;
		this.status = status;
		this.criteresEvent = criteresEvent;
		this.userEventParticipation = userEventParticipation;
		this.userCreator = userCreator;
	}



	public Event(Long eventId, String nameEvent, String descriptionEvent, String lieuEvent, Date dateDebutEvent,
			Date dateFinEvent, int nombreParticipantsEvent, Set<Critere> criteresEvent, String status,
			User userCreator) {
		this.eventId = eventId;
		this.nameEvent = nameEvent;
		this.descriptionEvent = descriptionEvent;
		this.lieuEvent = lieuEvent;
		this.dateDebutEvent = dateDebutEvent;
		this.dateFinEvent = dateFinEvent;
		this.nombreParticipantsEvent = nombreParticipantsEvent;
		this.criteresEvent = criteresEvent;
		this.status = status;
		this.userCreator = userCreator;
	}

	public Event(String nameEvent, String descriptionEvent, String lieuEvent, Date dateDebutEvent, Date dateFinEvent,
			int nombreParticipantsEvent, boolean annulée, String pathImage) {
		this.nameEvent = nameEvent;
		this.descriptionEvent = descriptionEvent;
		this.lieuEvent = lieuEvent;
		this.dateDebutEvent = dateDebutEvent;
		this.dateFinEvent = dateFinEvent;
		this.nombreParticipantsEvent = nombreParticipantsEvent;
		this.annulée = annulée;
		this.pathImage = pathImage;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public String getNameEvent() {
		return nameEvent;
	}

	public void setNameEvent(String nameEvent) {
		this.nameEvent = nameEvent;
	}

	public String getDescriptionEvent() {
		return descriptionEvent;
	}

	public void setDescriptionEvent(String descriptionEvent) {
		this.descriptionEvent = descriptionEvent;
	}

	

	public Date getDateDebutEvent() {
		return dateDebutEvent;
	}

	public void setDateDebutEvent(Date dateDebutEvent) {
		this.dateDebutEvent = dateDebutEvent;
	}

	public Date getDateFinEvent() {
		return dateFinEvent;
	}

	public void setDateFinEvent(Date dateFinEvent) {
		this.dateFinEvent = dateFinEvent;
	}

	public int getNombreParticipantsEvent() {
		return nombreParticipantsEvent;
	}

	public void setNombreParticipantsEvent(int nombreParticipantsEvent) {
		this.nombreParticipantsEvent = nombreParticipantsEvent;
	}

	public Set<Critere> getCriteresEvent() {
		return criteresEvent;
	}

	public void setCriteresEvent(Set<Critere> criteresEvent) {
		this.criteresEvent = criteresEvent;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUserCreator() {
		return userCreator;
	}

	public void setUserCreator(User userCreator) {
		this.userCreator = userCreator;
	}


	public boolean isAnnulée() {
		return annulée;
	}

	public void setAnnulée(boolean annulée) {
		this.annulée = annulée;
	}

	public String getPathImage() {
		return pathImage;
	}

	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}


	

	public Set<UserEventParticipation> getUserEventParticipation() {
		return userEventParticipation;
	}

	public void setUserEventParticipation(Set<UserEventParticipation> userEventParticipation) {
		this.userEventParticipation = userEventParticipation;
	}

	public String getLieuEvent() {
		return lieuEvent;
	}

	public void setLieuEvent(String lieuEvent) {
		this.lieuEvent = lieuEvent;
	}



	public Boolean getAnnulée() {
		return annulée;
	}



	public void setAnnulée(Boolean annulée) {
		this.annulée = annulée;
	}
	
	

}
