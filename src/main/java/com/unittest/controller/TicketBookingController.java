package com.unittest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unittest.model.Ticket;
import com.unittest.service.TicketBookingService;

@RestController
@RequestMapping(value="/api/tickets")
public class TicketBookingController {

	//making call to service layer
	@Autowired
	private TicketBookingService ticketBookingService;
	
	@CrossOrigin
	@PostMapping(value="/create", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	public Ticket createTicket(@RequestBody Ticket ticket) {
		return ticketBookingService.createTicket(ticket);
	}
	
	@CrossOrigin
	@GetMapping(value="/ticketId/{ticketId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Ticket getTicketById(@PathVariable("ticketId") Integer ticketId) {
		return ticketBookingService.getTicketById(ticketId);
	}
	
	@CrossOrigin
	@GetMapping(value="/alltickets", produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Ticket> getAllBookedTickets(){
		return ticketBookingService.getAllBookedTickets();
	}
	
	@CrossOrigin
	@PutMapping(value="/ticketId/{ticketId}/email/{newEmail}")
	public Ticket updateTicket(@PathVariable("ticketId") int ticketId, @PathVariable("newEmail") String newEmail) {
		return ticketBookingService.updateTicket(ticketId, newEmail);
	}
	
	@DeleteMapping(value="/ticketId/{ticketId}")
	public void deleteTicket(@PathVariable("ticketId") Integer ticketId) {
		ticketBookingService.deleteTicket(ticketId);
	}
	
	
	@GetMapping(value="/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Ticket getTicketByEmail(@PathVariable("email")String email) {
		return ticketBookingService.getTicketByEmail(email);
	}
	
	
}
