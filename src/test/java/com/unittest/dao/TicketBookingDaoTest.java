package com.unittest.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import com.unittest.model.Ticket;

@SpringBootTest
@DataJpaTest
public class TicketBookingDaoTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private TicketBookingDao ticketBookingDao;

	
	//write Junit test cases.write method 
	//method always public and return type always void
	//every method name should prefix with test
	
	@Test
	public void testSaveTicket() {
		Ticket ticket = getTicket();
		Ticket savedInDb = entityManager.persist(ticket);
		
		
	} 
	
	
	private Ticket getTicket() {
		Ticket ticket = new Ticket();
		//ticket.setTicketId(1);
		ticket.setPassengerName("suri");
		ticket.setSourceStation("clx");
		ticket.setDestStation("kjm");
		ticket.setEmail("abc@gmail.com");
		ticket.setBookingBy("alok");
		return ticket;
	}
	
	
	
}
