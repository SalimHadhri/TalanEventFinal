package tn.talan.academyApp.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import tn.talan.academyApp.AcademyTestApplication;
import tn.talan.academyApp.dtos.EventDto;
import tn.talan.academyApp.dtos.RoleDto;
import tn.talan.academyApp.dtos.UserDto;
import tn.talan.academyApp.entities.User;
import tn.talan.academyApp.services.EventService;
import tn.talan.academyApp.services.UserEventParticipationService;
import tn.talan.academyApp.services.UserService;
import tn.talan.academyApp.servicesImpl.EventServiceImpl;
import tn.talan.academyApp.servicesImpl.UserEventParticipationServiceImpl;
import tn.talan.academyApp.servicesImpl.UserServiceImpl;
import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestContext.class, AcademyTestApplication.class })
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = AcademyTestApplication.class)
@WebAppConfiguration
@AutoConfigureMockMvc
class EventControllerTests {

	private static final ObjectMapper MAPPER = new ObjectMapper()
			.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).registerModule(new JavaTimeModule());

	public static String requestBody(Object request) {
		try {
			return MAPPER.writeValueAsString(request);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> T parseResponse(MvcResult result, Class<T> responseClass) {
		try {
			String contentAsString = result.getResponse().getContentAsString();
			return MAPPER.readValue(contentAsString, responseClass);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private String obtainAccessToken(String username, String password) throws Exception {

		User user = new User();
		user.setEmail(username);
		user.setPassword(password);

		MvcResult requestResult = mockMvc
				.perform(post("/auth/signin").contentType(MediaType.APPLICATION_JSON).content(requestBody(user)))
				.andExpect(status().isOk()).andReturn();

		String resultString = requestResult.getResponse().getContentAsString();

		JacksonJsonParser jsonParser = new JacksonJsonParser();
		return jsonParser.parseMap(resultString).get("token").toString();
	

	}
		
	

	
	@Autowired
	private MockMvc mockMvc;
	

	
	
	@Mock
	UserEventParticipationService userEventParticipationService;

	@Mock
	private EventService eventService;

	@Mock
	private UserService userService;
	
	
	 


	@Test
	public void findEventByIdTest()
	{
		
		when(eventService.findEventById(1L)).thenReturn(new EventDto(1L, "foot", "foot++", "nasr")) ;
		
		EventDto event =  eventService.findEventById(1L) ;
		
		assertEquals("foot", event.getNameEvent());
		assertEquals("foot++", event.getDescriptionEvent());
		assertEquals("nasr", event.getLieuEvent());

		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
	
	
	/*
	@Before
	public void init() {
		
	} 
	
		private RoleDto roleCollab;
	private RoleDto roleAdmin;
	private UserDto salimH;
	private UserDto salimG;
	private UserDto salimT;
	private UserDto salimE;
	private EventDto eventDto1;
	private EventDto eventDto2;
	private EventDto eventDto3;
		
		
		

		roleCollab = MockUtils.getRole(1L, "ROLE_Collaborateur");
		roleAdmin = MockUtils.getRole(2L, "ROLE_Administrateur");

		salimH = MockUtils.getUser(3L, "salimH", "salimH", "salimH", "selimhadri@hotmail.fr", (Set<RoleDto>) roleCollab,
				null, null);
		salimG = MockUtils.getUser(4L, "salimG", "salimG", "salimG", "hadhrisalim@gmail.com", (Set<RoleDto>) roleCollab,
				null, null);
		salimT = MockUtils.getUser(5L, "salimT", "salimT", "salimT", "salim.hadhri@talan.com",
				(Set<RoleDto>) roleCollab, null, null);
		salimE = MockUtils.getUser(6L, "salimE", "salimE", "salimE", "salim.hadhri@esprit.tn",
				(Set<RoleDto>) roleCollab, null, null);

		// private* static Event event = MockUtils.getEvent()

		eventDto1 = MockUtils.getEvent(7L, "lala", "gh", "bgjh", null, null, 14, false, "bfdsiuf", "ssasa", null, null,
				salimH);
		eventDto2 = MockUtils.getEvent(8L, "gcgfc", "gh", "bgjh", null, null, 14, false, "bfdsiuf", "ssasa", null, null,
				salimG);
		eventDto3 = MockUtils.getEvent(9L, "fdhgfdg", "gh", "bgjh", null, null, 14, false, "bfdsiuf", "ssasa", null,
				null, salimE);

	}

	*/



/*
	@Test
	public void topThreeUserParticipationAPI() throws Exception {

		String accessToken = obtainAccessToken("salim.hadhri@talan.com", "salimT");

		when(userEventParticipationService.topThreeDeParticipation()).thenReturn(Arrays.asList(salimT, salimG, salimE));
		mockMvc.perform(get("/event/topThreeUserParticipation").header("Authorization", "Bearer " + accessToken))
				.andExpect(status().isOk());

		verify(userEventParticipationService, times(1)).topThreeDeParticipation();
		verifyNoMoreInteractions(userEventParticipationService);
	}

	@Test
	public void createEventAPI() throws Exception {

		String accessToken = obtainAccessToken("salim.hadhri@talan.com", "salimT");

		when(userService.findUserByIdUser(3L)).thenReturn(salimH);
		when(eventService.createEvent(eventDto1, 3L)).thenReturn(eventDto1);

		
		mockMvc.perform(post("/CreateEvent/{idUser}", 3L)
				.header("Authorization", "Bearer " + accessToken)
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody(eventDto1)))
				.andExpect(status().isOk());
				
				
		verify(userService, times(1)).findUserByIdUser(3L);
		verify(eventService, times(1)).createEvent(eventDto1, 3L);
		verifyNoMoreInteractions(userService,eventService);
		

	}
*/

