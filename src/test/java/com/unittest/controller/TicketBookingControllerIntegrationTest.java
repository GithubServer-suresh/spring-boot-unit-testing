package com.unittest.controller;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unittest.SpringBootUnitTestingApplication;
import com.unittest.model.Ticket;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootUnitTestingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
public class TicketBookingControllerIntegrationTest {
	
	//telling spring container assign some random port to this variable
	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	private HttpHeaders headers = new HttpHeaders();
	
	@Test
	public void testCreateTicket() throws Exception {
		Ticket ticket = new Ticket();
		ticket.setTicketId(1);
		ticket.setPassengerName("alok");
		ticket.setSourceStation("CGR");
		ticket.setDestStation("DLH");
		ticket.setBookingBy("Alok");
		ticket.setEmail("alok@gmail.com");
		
		String URIToCreateTicket = "/api/tickets/create";
		
		String inputJson = this.mapToJson(ticket);
		
		HttpEntity<Ticket> entity = new HttpEntity<Ticket>(ticket, headers);
		
		//In case of security, pass username and password in headers section
		
		ResponseEntity<String> response = testRestTemplate
				.exchange(formFullURLWithPort(URIToCreateTicket),
						HttpMethod.POST,entity, String.class);
		String responseInJson = response.getBody();
		assertThat(responseInJson).isEqualTo(inputJson);
		
	}
	
	
	@Test
	public void testGetTicketById() throws Exception{
		Ticket ticket = new Ticket();
		ticket.setTicketId(1);
		ticket.setPassengerName("alok");
		ticket.setSourceStation("CGR");
		ticket.setDestStation("DLH");
		ticket.setBookingBy("Alok");
		ticket.setEmail("alok@gmail.com");
		
		
		String URIToCreateTicket = "/api/tickets/create";
		String inputInJson = this.mapToJson(ticket);
		
		HttpEntity<Ticket> entity = new HttpEntity<Ticket>(ticket, headers);
		
		//In case of security, pass username and password in headers section
		
		ResponseEntity<String> response = testRestTemplate
				.exchange(formFullURLWithPort(URIToCreateTicket),
						HttpMethod.POST,entity, String.class);
		
		String URI = "/api/tickets/ticketId/1";
		String bodyJsonResponse = testRestTemplate.getForObject(formFullURLWithPort(URI), String.class);
		assertThat(bodyJsonResponse).isEqualTo(inputInJson);
	}
	
	@Test
	public void testGetTicketByEmail() throws Exception {
		Ticket ticket = new Ticket();
		ticket.setTicketId(1);
		ticket.setPassengerName("alok");
		ticket.setSourceStation("CGR");
		ticket.setDestStation("DLH");
		ticket.setBookingBy("Alok");
		ticket.setEmail("alok@gmail.com");
		
		
		String URIToCreateTicket = "/api/tickets/create";
		String inputInJson = this.mapToJson(ticket);
		
		HttpEntity<Ticket> entity = new HttpEntity<Ticket>(ticket, headers);
		
		//In case of security, pass username and password in headers section
		
		ResponseEntity<String> response = testRestTemplate
				.exchange(formFullURLWithPort(URIToCreateTicket),
						HttpMethod.POST,entity, String.class);
		
		String URIToGetTicket = "/api/tickets/email/alok@gmail.com";
		String bodyJsonResponse = testRestTemplate.getForObject(formFullURLWithPort(URIToGetTicket), String.class);
		assertThat(bodyJsonResponse).isEqualTo(inputInJson);
	
	}
	
	/*
	 * This utility method maps an object into a JSON string. Uses a Jackson ObjectMapper.
	 */
	private String mapToJson(Object object) throws JsonProcessingException{
		ObjectMapper objectMapper = new ObjectMapper(); 
		return objectMapper.writeValueAsString(object);
	}
	
	/*
	 * This utility method to create the url for given uri.
	 * It appends the RANDOM_PORT generated port 
	 */
	private String formFullURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}  
	
	
	
}
