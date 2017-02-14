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
import com.aearwood.coordinator.service.TeamService;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers=TeamRestController.class, secure = false)
public class TeamRestControllerTest {

	@Autowired
	 private MockMvc mockMvc;
	
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
		    public void readAllTeamsSuccess() throws Exception {
			Set<TeamDTO> teamDTOset = new HashSet<>();
		        TeamDTO teamDTO = new TeamDTO();
		        teamDTO.setMaximumAge(20);
		        teamDTO.setMinimumAge(18);
		        teamDTO.setType(SportType.SOCCER);
		        teamDTO.setName("Gators");
		        teamDTOset.add(teamDTO);
		        
		        teamDTO = new TeamDTO();
		        teamDTO.setMaximumAge(20);
		        teamDTO.setMinimumAge(18);
		        teamDTO.setType(SportType.SOCCER);
		        teamDTO.setName("Pirates");
		        teamDTOset.add(teamDTO);
		        
		        teamDTOset.add(teamDTO);
		        
		        when(teamServiceMock.findAllByType(SportType.SOCCER)).thenReturn(teamDTOset);
		        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		        mappingJackson2HttpMessageConverter.write(teamDTOset, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		        
		        mockMvc.perform(get("/api/v1/sports/SOCCER/teams")  
		                .contentType(MediaType.APPLICATION_JSON)
		                .accept(MediaType.APPLICATION_JSON))
		                .andExpect(status().isOk())
		                .andExpect(jsonPath("$.length()", is(2)))
		                .andExpect(content().json(mockHttpOutputMessage.getBodyAsString()));
		    }
	  @Test
	    public void createTeamSuccess() throws Exception {
		String email = "test@gmail.com";
	 
		 TeamDTO teamDTO = new TeamDTO();
	        teamDTO.setMaximumAge(20);
	        teamDTO.setMinimumAge(18);
	        teamDTO.setType(SportType.SOCCER);
	        teamDTO.setName("Gators");
	        
	        when(teamServiceMock.createTeam(isA(TeamDTO.class))).thenReturn(teamDTO);
	       MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
	       mappingJackson2HttpMessageConverter.write(teamDTO, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
	        
	        mockMvc.perform(post("/api/v1/sports/SOCCER/teams")  
	        		.accept(MediaType.APPLICATION_JSON_UTF8)
	        		.contentType(MediaType.APPLICATION_JSON_UTF8)
	                .content(mockHttpOutputMessage.getBodyAsBytes()))
	                .andExpect(status().isCreated())
	                .andExpect(jsonPath("$.minimumAge", is(teamDTO.getMinimumAge())))
	                .andExpect(jsonPath("$.maximumAge", is(teamDTO.getMaximumAge())))
	                .andExpect(jsonPath("$.name", is(teamDTO.getName())));
	        
	        		verify(teamServiceMock, times(1)).createTeam(isA(TeamDTO.class));
	    }
	  @Test
	    public void readTeamsSuccess() throws Exception {
		String email = "test@gmail.com";
		Integer teamId = 0;
	        
	        TeamDTO teamDTO = new TeamDTO();
	        teamDTO.setMaximumAge(10);
	        teamDTO.setMinimumAge(8);
	        teamDTO.setName("Pirates");
	        teamDTO.setType(SportType.SOCCER);
	        
	        Set<TeamDTO> teamDTOset = new HashSet<>();
	        teamDTOset.add(teamDTO);
	        
	        when(teamServiceMock.findOne(eq(teamId))).thenReturn(teamDTO);
	       MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
	       mappingJackson2HttpMessageConverter.write(teamDTO, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
	        
	        mockMvc.perform(get("/api/v1/sports/SOCCER/teams/" + teamId)  
	        		.accept(MediaType.APPLICATION_JSON_UTF8))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.maximumAge", is(teamDTO.getMaximumAge())))
	                .andExpect(jsonPath("$.minimumAge", is(teamDTO.getMinimumAge())))
	                .andExpect(jsonPath("$.name", is(teamDTO.getName())))
	                .andExpect(jsonPath("$.type", is(SportType.SOCCER.name())));
	        
	        		verify(teamServiceMock, times(1)).findOne(eq(teamId));
	    }
 
}	