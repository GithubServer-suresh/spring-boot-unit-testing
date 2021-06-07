package com.unittest.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.unittest.dao.TicketBookingDao;
import com.unittest.exception.TicketNotFoundException;
import com.unittest.model.Ticket;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketBookingServiceTest {

	//This is Junit test case for service layer 
	//Here we have autowired our Service layer.But Dao layer don't want to call 
	//THis is JUnit test case.
	//In integration test,u r going to call actual database or end to end . 
	//Here instead of calling the actual Dao layer, we just returning mockito object
	//another way to run JUnit test cases, mvn clean package under project directory
	//If u want to skip JUnit test cases, then use  @Ignore at class level. sothat all test cases will be skipped
	//If u want to skip 1 particular JUnit test case, then before JUnit test case, annotate with @Ignore
	//when to use Ignore? if u not completed JUnit test cases then if u want to run other test cases then use ignore
	//while packaging executable jar,if u try to skip JUnit test cases then use mvn clean package -DskipTests. All Junit test cases will not run.
	
	@Autowired
	private TicketBookingService ticketBookingService;
	
	@MockBean
	private TicketBookingDao ticketBookingDao;
	
//	@Test
	public void testCreateTicket() {
		
		Ticket ticket = new Ticket();
		ticket.setPassengerName("alok");
		ticket.setSourceStation("CGR");
		ticket.setDestStation("DLH");
		ticket.setBookingBy("Alok");
		ticket.setEmail("alok@gmail.com");
//		ticket = null;
		//In service class, we are calling dao method
		//I am telling Mockito if within this method if ticketBookingDao.save(ticket) makes a call
		//call, return ticket. 
		//when is a factory method 
		//what Mockito.when tells is: 
		//if ticketBookingDao.save(ticket) gets called then return ticket.
		//Mockito.when(ticketBookingDao.save(ticket)).thenReturn(null);
		Mockito.when(ticketBookingDao.save(ticket)).thenReturn(ticket);
		assertThat(ticketBookingService.createTicket(ticket)).isEqualTo(ticket);
		
	}
	
//	@Test
	public void testgetTicketById() {
		Ticket ticket = new Ticket();
		ticket.setTicketId(1);
		ticket.setPassengerName("alok");
		ticket.setSourceStation("CGR");
		ticket.setDestStation("DLH");
		ticket.setBookingBy("Alok");
		ticket.setEmail("alok@gmail.com");
		
		Mockito.when(ticketBookingDao.findById(1)).thenReturn(Optional.of(ticket));
		assertEquals(ticketBookingService.getTicketById(1), ticket);
	}
	
	
	//@Test
	public void testGetAllBookedTickets() {
		Ticket ticket1 = new Ticket();
		ticket1.setPassengerName("alok");
		ticket1.setSourceStation("CGR");
		ticket1.setDestStation("DLH");
		ticket1.setBookingBy("Alok");
		ticket1.setEmail("alok@gmail.com");
		
		Ticket ticket2 = new Ticket();
		ticket2.setPassengerName("alok");
		ticket2.setSourceStation("CGR");
		ticket2.setDestStation("DLH");
		ticket2.setBookingBy("Alok");
		ticket2.setEmail("alok@gmail.com");
		
		List<Ticket> ticketList = new ArrayList<>();
		ticketList.add(ticket1);
		ticketList.add(ticket2);
		
		Mockito.when(ticketBookingDao.findAll()).thenReturn(ticketList);
		assertThat(ticketBookingService.getAllBookedTickets()).isEqualTo(ticketList);
	}
	
	//@Test
	public void testDeleteTicket() {
		Ticket ticket2 = new Ticket();
		ticket2.setTicketId(1);
		ticket2.setPassengerName("alok");
		ticket2.setSourceStation("CGR");
		ticket2.setDestStation("DLH");
		ticket2.setBookingBy("Alok");
		ticket2.setEmail("alok@gmail.com");
//		assertNotNull(ticket2);
//		ticket2=null;
//		assertNull(ticket2, "Ticket is not found");
		Mockito.when(ticketBookingDao.save(null)).thenThrow(new TicketNotFoundException("Not Found"));
		Mockito.when(ticketBookingDao.findById(1)).thenReturn(Optional.of(ticket2));
		Mockito.when(ticketBookingDao.existsById(ticket2.getTicketId())).thenReturn(false);
		assertFalse(ticketBookingDao.existsById(ticket2.getTicketId()));
	}
	
	
	//@Test
	public void testUpdateTicket() {
		Ticket ticket2 = new Ticket();
		ticket2.setTicketId(1);
		ticket2.setPassengerName("alok");
		ticket2.setSourceStation("CGR");
		ticket2.setDestStation("DLH");
		ticket2.setBookingBy("Alok");
		ticket2.setEmail("alok@gmail.com");
		
		Mockito.when(ticketBookingDao.findById(1)).thenReturn(Optional.of(ticket2));
		
		ticket2.setEmail("update@gmail.com");
		Mockito.when(ticketBookingDao.save(ticket2)).thenReturn(ticket2);
		assertThat(ticketBookingService.updateTicket(1, "update@gmail.com")).isEqualTo(ticket2);
	}
	
	//Test case will fail because method return type always void
	//@Test
	/*
	 * public String testgetTicketByEmail() { assertEquals(1, 1); return "testing";
	 * }
	 */
	
	@Test
	public void testgetTicketByEmail() {
		Ticket ticket2 = new Ticket();
		ticket2.setTicketId(1);
		ticket2.setPassengerName("alok");
		ticket2.setSourceStation("CGR");
		ticket2.setDestStation("DLH");
		ticket2.setBookingBy("Alok");
		ticket2.setEmail("alok@gmail.com");
		
		Mockito.when(ticketBookingDao.findByEmail("alok@gmail.com")).thenReturn(ticket2);
		assertThat(ticketBookingService.getTicketByEmail("alok@gmail.com")).isEqualTo(ticket2);
	} 	
}
