package com.unittest.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unittest.dao.TicketBookingDao;
import com.unittest.model.Ticket;


@Service
public class TicketBookingService {

	@Autowired
	public TicketBookingDao ticketBookingDao;
	
	
	public Ticket createTicket(Ticket ticket) {
		return ticketBookingDao.save(ticket);
	}
	
	public Ticket getTicketById(Integer ticketId) {
		Optional<Ticket> findById = ticketBookingDao.findById(ticketId);
		if(findById.isPresent()) {
			Ticket ticket = findById.get();
			return ticket;
		}
		return null;
	}
	
	public Iterable<Ticket> getAllBookedTickets(){
		return ticketBookingDao.findAll();
	}
	
	public void deleteTicket(Integer ticketId) {
		Ticket ticket  = new Ticket(ticketId);
		ticketBookingDao.delete(ticket);
	}
	
	public Ticket updateTicket(Integer ticketId, String newEmail) {
		Ticket ticketFromdb = ticketBookingDao.findById(ticketId).get();
		ticketFromdb.setEmail(newEmail);
		ticketBookingDao.save(ticketFromdb);
		return ticketFromdb;
	}
	
	public Ticket getTicketByEmail(String email) {
		return ticketBookingDao.findByEmail(email);
	}
	
}
