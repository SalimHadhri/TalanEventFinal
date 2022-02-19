package tn.talan.academyApp.servicesImpl;

import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.mail.MessagingException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.talan.academyApp.dtos.EventDto;
import tn.talan.academyApp.dtos.UserDto;
import tn.talan.academyApp.dtos.UserEventParticipationDto;
import tn.talan.academyApp.entities.Event;
import tn.talan.academyApp.entities.User;
import tn.talan.academyApp.entities.UserEventParticipation;
import tn.talan.academyApp.entities.UserEventParticipationkey;
import tn.talan.academyApp.repositories.UserEventParticipationRepository;
import tn.talan.academyApp.services.EventService;
import tn.talan.academyApp.services.UserEventParticipationService;
import tn.talan.academyApp.services.UserService;

@Service
@Transactional
public class UserEventParticipationServiceImpl implements UserEventParticipationService {

	@Autowired
	UserEventParticipationRepository eventParticipationRepository;

	@Autowired
	public UserService userService;

	@Autowired
	public EventService eventService;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<EventDto> findEventParticipated(Long idUser) {

		List<UserEventParticipation> eventParticipations = eventParticipationRepository.findAll();
		List<UserEventParticipation> eventParticipationsById = new ArrayList<>();

			for (UserEventParticipation eventParticipation : eventParticipations) {

				if (eventParticipation.getUser().getUserId().equals(idUser)
						&& eventParticipation.isValidatedCriterias()) {

					eventParticipationsById.add(eventParticipation);
				}
			}
			List<Event> events = new ArrayList<>();

			for (UserEventParticipation eventParticipation : eventParticipationsById) {

				events.add(eventParticipation.getEvent());
			}

			List<EventDto> eventDtos = new ArrayList<>();

		for (Event event : events) {
			if (!event.getAnnulée()) {
			eventDtos.add((modelMapper.map(event, EventDto.class)));
			}
		}

			return eventDtos;

	}

	@Override
	public List<UserDto> findUserParticipated(Long idEvent) {

		List<UserEventParticipation> userEventParticipations = eventParticipationRepository.findAll();
		List<UserEventParticipation> userParticipationsById = new ArrayList<>();
		for (UserEventParticipation eventParticipation : userEventParticipations) {

			if (eventParticipation.getEvent().getEventId().equals(idEvent) && eventParticipation.isValidatedCriterias()) {

					userParticipationsById.add(eventParticipation);
				}
			}
			List<User> users = new ArrayList<>();

			for (UserEventParticipation eventParticipation : userParticipationsById) {

				users.add(eventParticipation.getUser());
			}

			List<UserDto> usersDtos = new ArrayList<>();

			for (User user : users) {
				usersDtos.add((modelMapper.map(user, UserDto.class)));

			}

			return usersDtos;

	}

	@Override
	public EventDto subscribeEvent(Long idUser, Long idEvent, boolean validatet) {

		if (validatet) {

			EventDto eventDtoToSubscribe = eventService.findEventById(idEvent);
			Event eventToSubscribe = modelMapper.map(eventDtoToSubscribe, Event.class);

			UserDto userDtoSubscription = userService.findUserByIdUser(idUser);
			User userSubscription = modelMapper.map(userDtoSubscription, User.class);

			UserEventParticipationkey eventParticipationkeySubscription = new UserEventParticipationkey(idUser,
					idEvent);

			UserEventParticipation eventParticipation = new UserEventParticipation(eventParticipationkeySubscription,
					userSubscription, eventToSubscribe, validatet);
			UserEventParticipation eventParticipationSubscribed = eventParticipationRepository.save(eventParticipation);

			Event eventSubcribed = eventParticipationSubscribed.getEvent();
			return modelMapper.map(eventSubcribed, EventDto.class);
		} else
			return null;

	}

	@Override
	public EventDto annulerUserEventParticipation(Long idUser, Long idEvent) {

		UserEventParticipationkey eventParticipationkeyDelete = new UserEventParticipationkey(idUser, idEvent);
		UserEventParticipation userEventParticipation = new UserEventParticipation(eventParticipationkeyDelete,
				modelMapper.map(userService.findUserByIdUser(idUser), User.class),
				modelMapper.map(eventService.findEventById(idEvent), Event.class), true);

		eventParticipationRepository.delete(userEventParticipation);

		return eventService.findEventById(idEvent);

	}

	@Override
	public UserDto topParticipation(List<UserDto> allUsers) {

		List<Long> nbrParticipationParUtilisateur = new ArrayList<>();

		List<List<EventDto>> listDeParticipationsParUtilisateur = new ArrayList<List<EventDto>>();

		for (UserDto userDto : allUsers) {

			listDeParticipationsParUtilisateur.add(findEventParticipated(userDto.getUserId()));

		}

		for (List<EventDto> eventDtos : listDeParticipationsParUtilisateur) {
			nbrParticipationParUtilisateur.add((long) eventDtos.size());
		}

		int maxIndiceParticipation = maxIndiceList(nbrParticipationParUtilisateur);

		UserDto userMaxParticipation1 = allUsers.get(maxIndiceParticipation);

		return userMaxParticipation1;

	}

	// indice de la plus grande valeur de la liste
	@Override
	public int maxIndiceList(List<Long> list) {

		Long max = list.get(0);
		int position = 0;

		for (int i = 1; i < list.size(); i++) {

			if (list.get(i) > max) {
				max = list.get(i);
				position = i;
			}
		}

		return position;

	}

	@Override
	public List<UserDto> topThreeDeParticipation() {

		List<UserDto> topThree = new ArrayList<>();

		List<UserDto> ListUsersAParcourir1 = new ArrayList<>();
		List<UserDto> ListUsersAParcourir2 = new ArrayList<>();
		List<UserDto> ListUsersAParcourir3 = new ArrayList<>();

		ListUsersAParcourir1 = userService.findAllUsers();

		if (ListUsersAParcourir1 != null && !ListUsersAParcourir1.isEmpty()) {

			UserDto Top1User = new UserDto();
			UserDto Top2User = new UserDto();
			UserDto Top3User = new UserDto();

			Top1User = topParticipation(ListUsersAParcourir1);

			for (UserDto userDto : ListUsersAParcourir1) {
				if (!userDto.equals(Top1User)) {
					ListUsersAParcourir2.add(userDto);
				}
			}
			Top2User = topParticipation(ListUsersAParcourir2);

			for (UserDto userDto : ListUsersAParcourir2) {
				if (!userDto.equals(Top2User)) {
					ListUsersAParcourir3.add(userDto);
				}
			}
			Top3User = topParticipation(ListUsersAParcourir3);

			topThree.add(Top1User);
			topThree.add(Top2User);
			topThree.add(Top3User);

			return topThree;
		} else
			return null;

	}

	@Override
	public UserDto topUserCreations(List<UserDto> allUsers) {

		List<Long> usersParticipationSize = new ArrayList<>();
		for (UserDto userDto : allUsers) {
			usersParticipationSize.add((long) userDto.getEvenementsCreated().size());
		}

		int positionMoreCreations = maxIndiceList(usersParticipationSize);

		return allUsers.get(positionMoreCreations);

	}

	@Override
	public List<UserDto> topThreeUserCreators() {
		List<UserDto> topThree = new ArrayList<>();

		List<UserDto> ListUsersAParcourir1 = new ArrayList<>();
		List<UserDto> ListUsersAParcourir2 = new ArrayList<>();
		List<UserDto> ListUsersAParcourir3 = new ArrayList<>();

		ListUsersAParcourir1 = userService.findAllUsers();

		if (ListUsersAParcourir1 != null && !ListUsersAParcourir1.isEmpty()) {

			UserDto Top1User = new UserDto();
			UserDto Top2User = new UserDto();
			UserDto Top3User = new UserDto();

			Top1User = topUserCreations(ListUsersAParcourir1);

			for (UserDto userDto : ListUsersAParcourir1) {
				if (!userDto.equals(Top1User)) {
					ListUsersAParcourir2.add(userDto);
				}
			}
			Top2User = topUserCreations(ListUsersAParcourir2);

			for (UserDto userDto : ListUsersAParcourir2) {
				if (!userDto.equals(Top2User)) {
					ListUsersAParcourir3.add(userDto);
				}
			}
			Top3User = topUserCreations(ListUsersAParcourir3);

			topThree.add(Top1User);
			topThree.add(Top2User);
			topThree.add(Top3User);

			return topThree;
		} else
			return null;

	}

	@Override
	public Long tauxDePresenceParEvenement(Long idEvent) {

		List<UserDto> userEventParticipators = findUserParticipated(idEvent);

			EventDto eventActive = eventService.findEventById(idEvent);

			Long PourcentagePresencielle = (long) ((userEventParticipators.size() * 100)
					/ eventActive.getNombreParticipantsEvent());
			
			return PourcentagePresencielle;}

	@Override
	public EventDto topEventTauxParicipation(List<EventDto> allEvents) {

		if (allEvents != null && !allEvents.isEmpty()) {

			Long PourcentagePresencielleInitiale = (long) ((allEvents.get(0).getUserEventParticipation().size() * 100)
					/ allEvents.get(0).getNombreParticipantsEvent());

			EventDto greaterEventTauxPresence = allEvents.get(0);

			for (int i = 1; i < allEvents.size(); i++) {

				if ((allEvents.get(i).getUserEventParticipation().size() * 100)
						/ allEvents.get(i).getNombreParticipantsEvent() > PourcentagePresencielleInitiale) {
					PourcentagePresencielleInitiale = (long) (allEvents.get(i).getUserEventParticipation().size() * 100)
							/ allEvents.get(i).getNombreParticipantsEvent();
					greaterEventTauxPresence = allEvents.get(i);

				}
			}
			return greaterEventTauxPresence;
		} else
			return null;

	}

	@Override
	public List<EventDto> topThreePresenceParEvenement() {

		List<EventDto> topThree = new ArrayList<>();

		List<EventDto> ListEventAParcourir1 = new ArrayList<>();
		List<EventDto> ListEventAParcouri2 = new ArrayList<>();
		List<EventDto> ListEventAParcouri3 = new ArrayList<>();

		ListEventAParcourir1 = eventService.findAllEvents();

		if (ListEventAParcourir1 != null && !ListEventAParcourir1.isEmpty()) {

			EventDto top1Event = new EventDto();
			EventDto top2Event = new EventDto();
			EventDto top3Event = new EventDto();

			top1Event = topEventTauxParicipation(ListEventAParcourir1);

			for (EventDto eventDto : ListEventAParcourir1) {
				if (!eventDto.equals(top1Event)) {
					ListEventAParcouri2.add(eventDto);
				}
			}
			top2Event = topEventTauxParicipation(ListEventAParcouri2);

			for (EventDto eventDto : ListEventAParcouri2) {
				if (!eventDto.equals(top2Event)) {
					ListEventAParcouri3.add(eventDto);
				}
			}
			top3Event = topEventTauxParicipation(ListEventAParcouri3);

			topThree.add(top1Event);
			topThree.add(top2Event);
			topThree.add(top3Event);

			return topThree;
		} else
			return null;

	}


	@Override
	public Long successRateEvents() {

		List<EventDto> allEvents = eventService.findAllEvents();

	if(allEvents!=null && !allEvents.isEmpty()) {



			Long sumEvenetsrate = 0L;

			for (EventDto event : allEvents) {

				sumEvenetsrate = sumEvenetsrate + tauxDePresenceParEvenement(event.getEventId());

			}

			return sumEvenetsrate / allEvents.size();
	}
	else return null ;
		}



	@Override
	public int nombreParticipationUserParEvenement(Long idUser) {

		return findEventParticipated(idUser).size();
	}

	@Override
	public List<UserEventParticipationDto> findAllUserEventParticipations() {
		List<UserEventParticipation> eventParticipationsAll = eventParticipationRepository.findAll();

		if (eventParticipationsAll != null && !eventParticipationsAll.isEmpty()) {

			List<UserEventParticipationDto> eventParticipationsDtoAll = new ArrayList<>();

			for (UserEventParticipation eventParticipation : eventParticipationsAll) {

				eventParticipationsDtoAll.add(modelMapper.map(eventParticipation, UserEventParticipationDto.class));
			}
			return eventParticipationsDtoAll;
		} else
			return null;
	}

	@Override
	public void notifyEventParticipators() throws GeneralSecurityException, MessagingException, ParseException {

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, 25);
		Date date = cal.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("EEEE,MMMM d,yyyy", Locale.ENGLISH);
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		String formattedDate = sdf.format(date);

		List<UserEventParticipationDto> allEventsParticipatedDto = findAllUserEventParticipations();
		if (allEventsParticipatedDto != null && !allEventsParticipatedDto.isEmpty()) {

			for (UserEventParticipationDto eventParticipationDto : allEventsParticipatedDto) {

				String formattedDateEvent = sdf.format(eventParticipationDto.getEvent().getDateDebutEvent());

				if (eventParticipationDto.getNotified().equals(false) && formattedDate.equals(formattedDateEvent)) {


						eventService.sendMail(eventParticipationDto.getUser().getEmail(),
								"J-1 event : " + eventParticipationDto.getEvent().getNameEvent(),
								"Pack your bags and let's gooo!");
				eventParticipationDto.setNotified(true);
				eventParticipationRepository.save(modelMapper.map(eventParticipationDto, UserEventParticipation.class));
				}
			}
		}
	}

	@Override
	public EventDto validateCriteriasUser(Long idUser, Long idEvent) {


		UserDto userDtoValidated = userService.findUserByIdUser(idUser) ;

		if(userDtoValidated!=null) {
			return subscribeEvent(idUser,idEvent,true) ;

		}
		else {
			return null ;

		}

	}

	@Override
	public List<UserEventParticipationDto> findUserEventByUser(UserDto userDto) {

		List<UserEventParticipationDto> participationDtos = findAllUserEventParticipations();
		List<UserEventParticipationDto> eventParticipationDtosFound = new ArrayList<UserEventParticipationDto>();
		for (UserEventParticipationDto eventParticipationDto : participationDtos) {

			if (eventParticipationDto.getUser().equals(userDto)) {

				eventParticipationDtosFound.add(eventParticipationDto);
			}
		}

		return eventParticipationDtosFound;

	}

}
