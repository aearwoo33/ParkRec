package com.aearwood.coordinator.api;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.aearwood.coordinator.domain.SportType;
import com.aearwood.coordinator.dto.AthleteDTO;
import com.aearwood.coordinator.dto.TeamDTO;
import com.aearwood.coordinator.service.AthleteService;
import com.aearwood.coordinator.service.TeamService;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers=AthleteRestController.class, secure = false)
public class AthleteRestControllerTest {

	@Autowired
	 private MockMvc mockMvc;
	
	@MockBean
	 private AthleteService athleteServiceMock;
	
	@MockBean
	 private TeamService teamServiceMock;
	
	private HttpMessageConverter mappingJackson2HttpMessageConverter;
	
	 @Autowired
	    void setConverters(HttpMessageConverter<?>[] converters) {

	        this.mappingJackson2HttpMessageConverter =  Arrays.asList(converters).stream()
	            .filter(converter -> converter instanceof MappingJackson2HttpMessageConverter)
	            .findAny()
	            .orElse(null);

	        assertNotNull("JSON message converter must not be null",
	                this.mappingJackson2HttpMessageConverter);
	    }
		@Test
		@WithMockUser(username="'test@gmail.com",roles= {"PARENT"})
		    public void readAthletesSuccess() throws Exception {
			Set<AthleteDTO> athleteDTOset = new HashSet<>();
		        AthleteDTO athleteDTO = new AthleteDTO();
		        athleteDTO.setAge(40);
		        athleteDTO.setFirstName("firstName");
		        athleteDTO.setLastName("lastName");
		        athleteDTOset.add(athleteDTO);
		        
		        athleteDTO = new AthleteDTO();
		        athleteDTO.setAge(41);
		        athleteDTO.setFirstName("firstName2");
		        athleteDTO.setLastName("lastName2");
		        athleteDTOset.add(athleteDTO);
		        
		        when(athleteServiceMock.findAll()).thenReturn(athleteDTOset);
		        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		        mappingJackson2HttpMessageConverter.write(athleteDTOset, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		        
		        mockMvc.perform(get("/api/v1/sports/athletes")  
		                .contentType(MediaType.APPLICATION_JSON)
		                .accept(MediaType.APPLICATION_JSON))
		                .andExpect(status().isOk())
		                .andExpect(jsonPath("$.length()", is(2)))
		                .andExpect(content().json(mockHttpOutputMessage.getBodyAsString()));
		    }
		@Test
		@WithMockUser(username="'test@gmail.com",roles= {"PARENT"})
		    public void readAthletesEmpty() throws Exception {
		        
		        mockMvc.perform(get("/api/v1/sports/athletes")  
		                .contentType(MediaType.APPLICATION_JSON)
		                .accept(MediaType.APPLICATION_JSON))
		                .andExpect(status().isOk())
		                .andExpect(jsonPath("$.length()", is(0)));
		    }
	  @Test
	    public void createAthleteSuccess() throws Exception {
		String email = "test@gmail.com";
	 
	        AthleteDTO athleteDTO = new AthleteDTO();
	        athleteDTO.setAge(40);
	        athleteDTO.setFirstName("firstName");
	        athleteDTO.setLastName("lastName");
	        
	        when(athleteServiceMock.createAthlete(isA(AthleteDTO.class), eq(email))).thenReturn(athleteDTO);
	       MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
	       mappingJackson2HttpMessageConverter.write(athleteDTO, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
	        
	        mockMvc.perform(post("/api/v1/sports/athletes")  
	        		.accept(MediaType.APPLICATION_JSON_UTF8)
	        		.contentType(MediaType.APPLICATION_JSON_UTF8)
	                .content(mockHttpOutputMessage.getBodyAsBytes()))
	                .andExpect(status().isCreated())
	                .andExpect(jsonPath("$.age", is(athleteDTO.getAge())))
	                .andExpect(jsonPath("$.firstName", is(athleteDTO.getFirstName())))
	                .andExpect(jsonPath("$.lastName", is(athleteDTO.getLastName())));
	        
	        		verify(athleteServiceMock, times(1)).createAthlete(isA(AthleteDTO.class), eq(email));
	    }
	  @Test
	    public void readTeamsSuccess() throws Exception {
		String email = "test@gmail.com";
		Integer athleteId = 0;
		   AthleteDTO athleteDTO = mock (AthleteDTO.class);
	        athleteDTO.setAge(40);
	        athleteDTO.setFirstName("firstName");
	        athleteDTO.setLastName("lastName");
	        
	        TeamDTO teamDTO = new TeamDTO();
	        teamDTO.setMaximumAge(10);
	        teamDTO.setMinimumAge(8);
	        teamDTO.setName("Pirates");
	        teamDTO.setType(SportType.SOCCER);
	        
	        Set<TeamDTO> teamDTOset = new HashSet<>();
	        teamDTOset.add(teamDTO);
	        
	        when(athleteDTO.getTeams()).thenReturn(teamDTOset);
	        when(athleteServiceMock.findOne(eq(athleteId))).thenReturn(athleteDTO);
	       MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
	       mappingJackson2HttpMessageConverter.write(teamDTO, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
	        
	        mockMvc.perform(get("/api/v1/sports/athletes/" + athleteId + "/teams")  
	        		.accept(MediaType.APPLICATION_JSON_UTF8))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$[0].maximumAge", is(teamDTO.getMaximumAge())))
	                .andExpect(jsonPath("$[0].minimumAge", is(teamDTO.getMinimumAge())))
	                .andExpect(jsonPath("$[0].name", is(teamDTO.getName())))
	                .andExpect(jsonPath("$[0].type", is(SportType.SOCCER.name())));
	        
	        		verify(athleteServiceMock, times(1)).findOne(eq(athleteId));
	    }
 
}	