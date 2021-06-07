package com.unittest.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.unittest.model.Ticket;

@RunWith(SpringRunner.class)
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
		System.out.println("In testSaveTicket method");
		Ticket getFromDb = ticketBookingDao.findById(savedInDb.getTicketId()).get();
		
		assertThat(getFromDb).isEqualTo(savedInDb);
	} 
	
	private Ticket getTicket() {
		System.out.println("In getTicket method");
		Ticket ticket = new Ticket();
		//ticket.setTicketId(1);
		ticket.setPassengerName("suri");
		ticket.setSourceStation("clx");
		ticket.setDestStation("kjm");
		ticket.setEmail("abc@gmail.com");
		ticket.setBookingBy("alok");
		return ticket;
	}
	
	@Test
	public void testGetTicketById() {
		System.out.println("In testGetTicketById method");
		Ticket ticket = new Ticket();
		ticket.setPassengerName("alok");
		ticket.setSourceStation("CGR");
		ticket.setDestStation("DLH");
		ticket.setBookingBy("Alok");
		ticket.setEmail("alok@gmail.com");
		//save ticket in DB
		Ticket ticketSavedInDb = entityManager.persist(ticket);
		
		//get ticket from DB
		Ticket ticketFromDb = ticketBookingDao.findById(ticketSavedInDb.getTicketId()).get();
		assertThat(ticketSavedInDb).isEqualTo(ticketFromDb);
	}
	
	
	@Test
	public void testAllBookedTickets() {
		System.out.println("In testAllBookedTickets method");
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
		
		entityManager.persist(ticket1);
		entityManager.persist(ticket2);
		
		Iterable<Ticket> findAll = ticketBookingDao.findAll();
		List<Ticket> ticketList = new ArrayList<>();
		
		for(Ticket ticket : findAll) {
			ticketList.add(ticket);
		}
		assertThat(ticketList.size()).isEqualTo(2);
	}
	
	
	@Test
	public void testFindByEmail() {
		System.out.println("In testFindByEmail method");
		Ticket ticket1 = new Ticket();
		ticket1.setPassengerName("alok");
		ticket1.setSourceStation("CGR");
		ticket1.setDestStation("DLH");
		ticket1.setBookingBy("Alok");
		ticket1.setEmail("alok@gmail.com");
		//Ticket in DB
		entityManager.persist(ticket1);
		//Get ticket info from DB for specified email
		Ticket findByEmail = ticketBookingDao.findByEmail("alok@gmail.com");
		assertThat(findByEmail.getPassengerName()).isEqualTo("alok");
	}
	
	@Test
	public void testDeleteTicketById() {
		System.out.println("In testDeleteTicketById method");
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
		
		//save both tickets in DB 
		Ticket persistTicket1 = entityManager.persist(ticket1);
		Ticket persistTicket2 = entityManager.persist(ticket2);
		
		//delete 1 ticket DB
		entityManager.remove(persistTicket1);
		
		Iterable<Ticket> findAllTickets = ticketBookingDao.findAll();
		List<Ticket> ticketList = new ArrayList<>();
		for(Ticket ticket : ticketList) {
			ticketList.add(ticket);
		}
		assertThat(ticketList.size()).isEqualTo(0);
	}
	
	@Test
	public void testUpdateTicket() {
		System.out.println("In testUpdateTicket method");
		Ticket ticket1 = new Ticket();
		ticket1.setPassengerName("alok");
		ticket1.setSourceStation("CGR");
		ticket1.setDestStation("DLH");
		ticket1.setBookingBy("Alok");
		ticket1.setEmail("alok@gmail.com");
		
		//save Ticket info in DB
		entityManager.persist(ticket1);
		
		Ticket getFromDb = ticketBookingDao.findByEmail("alok@gmail.com");
		
		//update email address
		getFromDb.setEmail("alok1@gmail.com");
		entityManager.persist(getFromDb);
		
		assertThat(getFromDb.getEmail()).isEqualTo("alok1@gmail.com");
	}	
}
