package tn.talan.academyApp.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tn.talan.academyApp.dtos.CritereDto;
import tn.talan.academyApp.dtos.EventDto;
import tn.talan.academyApp.services.CritereService;
import tn.talan.academyApp.dtos.UserDto;
import tn.talan.academyApp.security.WebSecurityConfig;
import tn.talan.academyApp.services.CritereService;
import tn.talan.academyApp.services.EventService;
import tn.talan.academyApp.services.UserEventParticipationService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/event")
public class EventController {

	@Autowired
	public CritereService critereService;

	@Autowired
	public EventService eventService;

	@Autowired
	private WebSecurityConfig securityTokenConfig;

	@Autowired
	private UserEventParticipationService eventParticipationService;

	@PutMapping(value = "/annulerEventCretated/{idUser}/{idEvent}")
	public EventDto annulerEvenementCretated(@PathVariable Long idUser, @PathVariable Long idEvent)
			throws GeneralSecurityException, MessagingException {
		return eventService.annulerEventCretated(idUser, idEvent);
	}

	@PutMapping(value = "/annulerEventParticipated/{idUser}/{idEvent}")
	public EventDto annulerEventParticipated(@PathVariable Long idUser, @PathVariable Long idEvent)
			throws GeneralSecurityException, MessagingException {
		return eventParticipationService.annulerUserEventParticipation(idUser, idEvent);
	}

	@GetMapping(value = "/eventsParticipated/{id}")
	public List<EventDto> findEventsParticipatedByUserId(@PathVariable Long id) {
		return eventParticipationService.findEventParticipated(id);

	}

	@GetMapping(value = "/usersParticipated/{id}")
	public List<UserDto> findUsersParticipatedByEventId(@PathVariable Long id) {
		return eventParticipationService.findUserParticipated(id);

	}

	@PostMapping(value = "/subscribeEvent/{idUser}/{idEvent}/{validated}")
	public EventDto subscribeEvent(@PathVariable Long idUser, @PathVariable Long idEvent,
			@PathVariable boolean validated) {

		return eventParticipationService.subscribeEvent(idUser, idEvent, validated);
	}

	@PostMapping(value = "/CreateEvent/{idUser}")
	public EventDto CreateEvent(@PathVariable Long idUser, @RequestBody EventDto eventDto)
			throws GeneralSecurityException, MessagingException {

		return eventService.createEvent(eventDto, idUser);
	}

	@GetMapping(value = "/eventsCreated/{id}")
	public List<EventDto> findEventsCreated(@PathVariable Long id) {
		return eventService.findEventsCreated(id);
	}

	@GetMapping(value = "/allEvents")
	public List<EventDto> allEvents() {
		return eventService.findAllEvents();
	}

	@GetMapping(value = "/allEventsValide")
	public List<EventDto> allEventsValidée() {
		return eventService.findEventByStatus("validée");
	}

	@GetMapping(value = "/allEventsEnAttente")
	@PreAuthorize("hasRole('ROLE_Administrateur')")
	public List<EventDto> allEventsenAttente() {
		return eventService.findEventByStatus("En attente de validation");
	}

	@GetMapping(value = "/allEventsNonAnnulée")
	@PreAuthorize("hasRole('ROLE_Administrateur')")
	public List<EventDto> allEventsNonAnnulée() {
		return eventService.findEventByAnnulée(false);
	}

	@GetMapping(value = "/SearchEventsByName/{name}")
	public List<EventDto> findEventsByName(@PathVariable String name) {
		return eventService.searchEventsByName(name);
	}

	@GetMapping(value = "/topThreeUserParticipation")
	public List<UserDto> topThreeUserParticipation() {
		return eventParticipationService.topThreeDeParticipation();
	}

	@GetMapping(value = "/topThreeUserCreation")
	public List<UserDto> topThreeUserCreation() {
		return eventParticipationService.topThreeUserCreators();
	}

	@GetMapping(value = "/tauxPresenceEvent/{id}")
	public Long tauxPresenceEvent(@PathVariable Long id) {
		return eventParticipationService.tauxDePresenceParEvenement(id);
	}

	@GetMapping(value = "/topThreeEventParticipation")
	public List<EventDto> topThreeEventParticipation() {
		return eventParticipationService.topThreePresenceParEvenement();
	}

	@GetMapping(value = "/findEventsById/{id}")
	public EventDto findEventById(@PathVariable Long id) {
		return eventService.findEventById(id);
	}

	@PutMapping(value = "/validateCriteriasUser/{idUser}/{idEvent}")
	public EventDto validateCriteriasUser(@PathVariable Long idUser, @PathVariable Long idEvent) {

		return eventParticipationService.validateCriteriasUser(idUser, idEvent);
	}

	@PostMapping("/upload")

	public Map<String, String> saveEventFile(@RequestParam("image") MultipartFile multipartFile) throws IOException {
		File file = new File("C:/uploaded_img/" + multipartFile.getOriginalFilename());

		try (OutputStream os = new FileOutputStream(file)) {
			os.write(multipartFile.getBytes());
		}
		Map<String, String> result = new HashMap<>();
		result.put("url", "C:/uploaded_img/" + multipartFile.getOriginalFilename());
		return result;
	}

	@PostMapping(value = "/download")
	public byte[] getImg(@RequestBody Map<String, String> path) throws IOException {
		File file2Upload = new File(path.get("path"));
		return Files.readAllBytes(file2Upload.toPath());

	}

	@PostMapping(value = "/addCritere")
	public CritereDto CreateCritere(@RequestBody CritereDto critereDto) {

		return critereService.addCritere(critereDto);
	}

	@GetMapping(value = "/eventsSuccessRate")
	public Long eventsSuccessRate() {
		return eventParticipationService.successRateEvents();
	}

	@GetMapping(value = "/monthsSuccessRate")
	public List<Long> monthSuccessRate() {
		return securityTokenConfig.getRateEventsParticipationsperMonth();

	}

	@GetMapping(value = "/findNombreParticipationById/{idUser}")
	public int findNombreParticipationById(@PathVariable Long idUser) {
		return eventParticipationService.nombreParticipationUserParEvenement(idUser);
	}

	@GetMapping(value = "/findNombreCreationById/{idUser}")
	public int findNombreCreationById(@PathVariable Long idUser) {
		return eventService.nombreCreationUserParEvenement(idUser);
	}
	@PutMapping(value = "/annulerEventAdmin/{idEvent}")
	@PreAuthorize("hasRole('ROLE_Administrateur')")
	public EventDto annulerEventAdmin( @PathVariable Long idEvent)
		throws GeneralSecurityException, MessagingException {
	return eventService.annulerEventAdmin( idEvent);
	}

	@PutMapping(value = "/validerEventAdmin/{idEvent}")
	@PreAuthorize("hasRole('ROLE_Administrateur')")
	public EventDto validerEventAdmin(@PathVariable Long idEvent)
		throws GeneralSecurityException, MessagingException {
	return eventService.validerEventAdmin( idEvent);
	}


}
