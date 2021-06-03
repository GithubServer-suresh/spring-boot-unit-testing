package com.unittest.dao;

import org.springframework.data.repository.CrudRepository;

import com.unittest.model.Ticket;

public interface TicketBookingDao extends CrudRepository<Ticket, Integer>{

	//added 1 method explicitly
	Ticket findByEmail(String email);
}
