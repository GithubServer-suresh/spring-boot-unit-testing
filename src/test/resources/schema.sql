CREATE TABLE ticket ( 
	ticket_id INT(11) NOT NULL AUTO_INCREMENT,
    booking_by varchar(255) NOT NULL,
    source_station varchar(255) NOT NULL,
    dest_station varchar(255) NOT NULL,
    email VARCHAR(250) default NULL,
    passenger_name VARCHAR(250) NOT NULL, 
    PRIMARY KEY (ticket_id)
    );