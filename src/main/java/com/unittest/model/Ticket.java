package com.unittest.model;




import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="ticket")
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="ticket_Id")
	private Integer ticketId;
	
	@Column(name="passenger_name")
	private String passengerName;
	
	
	@Column(name="booking_by")
	private String bookingBy;
	
	@Column(name="source_station")
	private String sourceStation;
	
	@Column(name="dest_station")
	private String destStation;
	
	@Column(name="email")
	private String email;
	
	
	public Ticket() {
		super();
	}

	public Ticket(int ticketId) {
		this.ticketId = ticketId;
	}

	public Ticket(int ticketId, String passengerName, String bookingBy, String sourceStation, String destStation,
			String email) {
		super();
		this.ticketId = ticketId;
		this.passengerName = passengerName;
		this.bookingBy = bookingBy;
		this.sourceStation = sourceStation;
		this.destStation = destStation;
		this.email = email;
	}

	

	public Integer getTicketId() {
		return ticketId;
	}

	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getBookingBy() {
		return bookingBy;
	}

	public void setBookingBy(String bookingBy) {
		this.bookingBy = bookingBy;
	}

	public String getSourceStation() {
		return sourceStation;
	}

	public void setSourceStation(String sourceStation) {
		this.sourceStation = sourceStation;
	}

	public String getDestStation() {
		return destStation;
	}

	public void setDestStation(String destStation) {
		this.destStation = destStation;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", passengerName=" + passengerName + ", bookingBy=" + bookingBy
				+ ", sourceStation=" + sourceStation + ", destStation=" + destStation + ", email=" + email + "]";
	}

	
	
}
