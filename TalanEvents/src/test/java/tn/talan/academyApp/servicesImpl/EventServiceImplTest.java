/*package tn.talan.academyApp.servicesImpl;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import tn.talan.academyApp.AcademyTestApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = AcademyTestApplication.class)
@AutoConfigureMockMvc
public class EventServiceImplTest {
	
	@Autowired
	private MockMvc mockMvc;
/*	
	@Test
	public void getEventsCreated() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/event/eventsCreated/{id}", 1)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.[*]").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.[*].userCreator.userId").value(1))
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}}*/
