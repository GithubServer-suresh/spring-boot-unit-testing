package com.unittest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unittest.model.Ticket;
import com.unittest.service.TicketBookingService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TicketBookingController.class )
public class TicketBookingControllerTest {

	@Autowired
	private MockMvc mockMvc;

	//controller makes call to service layer
	//we are testing only controller classes
	@MockBean
	private TicketBookingService ticketBookingService;

	@Test
	public void testCreateTicket() throws Exception {
		Ticket ticket = new Ticket();
		ticket.setTicketId(1);
		ticket.setPassengerName("alok");
		ticket.setSourceStation("CGR");
		ticket.setDestStation("DLH");
		ticket.setBookingBy("Alok");
		ticket.setEmail("alok@gmail.com");
		String inputInJson = this.mapToJson(ticket);
		String URI = "/api/tickets/create";
		Mockito.when(ticketBookingService.createTicket(Mockito.any(Ticket.class))).thenReturn(ticket);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(URI)
				.accept(MediaType.APPLICATION_JSON)
				.content(inputInJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();

		assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void testgetTicketById() throws Exception {
		Ticket ticket = new Ticket();
		ticket.setTicketId(1);
		ticket.setPassengerName("alok");
		ticket.setSourceStation("CGR");
		ticket.setDestStation("DLH");
		ticket.setBookingBy("Alok");
		ticket.setEmail("alok@gmail.com");
		
		Mockito.when(ticketBookingService.getTicketById(Mockito.anyInt())).thenReturn(ticket);
		
		String URI = "/api/tickets/ticketId/1";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(ticket);
		String outputJson = result.getResponse().getContentAsString();
		assertThat(outputJson).isEqualTo(expectedJson);
	}
	
	
	@Test
	public void testGetAllBookedTickets() throws Exception {
		
		Ticket ticket1 = new Ticket();
		ticket1.setTicketId(1);
		ticket1.setPassengerName("alok");
		ticket1.setSourceStation("CGR");
		ticket1.setDestStation("DLH");
		ticket1.setBookingBy("Alok");
		ticket1.setEmail("alok@gmail.com");
		
		Ticket ticket2 = new Ticket();
		ticket2.setTicketId(1);
		ticket2.setPassengerName("alok");
		ticket2.setSourceStation("CGR");
		ticket2.setDestStation("DLH");
		ticket2.setBookingBy("Alok");
		ticket2.setEmail("alok@gmail.com");
		
		List<Ticket> ticketList = new ArrayList<>();
		ticketList.add(ticket1);ticketList.add(ticket2);
		
		Mockito.when(ticketBookingService.getAllBookedTickets()).thenReturn(ticketList);
		
		String uri = "/api/tickets/alltickets";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
			.get(uri)
			.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		String expectedJson = this.mapToJson(ticketList);
		String outputJson = result.getResponse().getContentAsString();
		assertThat(outputJson).isEqualTo(expectedJson);
	}
	
	
	@Test
	public void testGetTicketByEmail() throws Exception  {
		Ticket ticket1 = new Ticket();
		ticket1.setTicketId(1);
		ticket1.setPassengerName("alok");
		ticket1.setSourceStation("CGR");
		ticket1.setDestStation("DLH");
		ticket1.setBookingBy("Alok");
		ticket1.setEmail("alok@gmail.com");
		
		String expectedJson = this.mapToJson(ticket1);
		
		Mockito.when(ticketBookingService.getTicketByEmail(Mockito.anyString())).thenReturn(ticket1);
		
		String uri = "/api/tickets/email/alok@gmail.com";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
			.get(uri)
			.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String outputJson = result.getResponse().getContentAsString();
		assertThat(outputJson).isEqualTo(expectedJson);
	}
	
	/**
	 * maps an Object into a JSON string. uses a Jackson ObjectMapper
	 * @throws JsonProcessingException 
	 */
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}



}
