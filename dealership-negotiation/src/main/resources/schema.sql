DROP TABLE IF EXISTS dealership_automaker;
DROP TABLE IF EXISTS automaker;
DROP TABLE IF EXISTS car;
DROP TABLE IF EXISTS dealership;

CREATE TABLE dealership (
	dealership_id int NOT NULL AUTO_INCREMENT,
	dealership_name varchar(256) NOT NULL,
	street_address varchar(128) NOT NULL,
	city varchar(60),
	state varchar(40),
	zip varchar(20),
	phone varchar(30),
	PRIMARY KEY (dealership_id)
);

CREATE TABLE car (
	car_id int NOT NULL AUTO_INCREMENT,
	dealership_id int NULL,
	make varchar(60) NOT NULL,
	model varchar(60) NOT NULL,
	car_year int,
	vin varchar(60),
	mileage int NOT NULL,
	PRIMARY KEY (car_id),
	FOREIGN KEY (dealership_id) REFERENCES dealership (dealership_id) ON DELETE CASCADE
);

CREATE TABLE automaker (
	automaker_id int NOT NULL AUTO_INCREMENT,
	name varchar(128),
	city varchar(60),
	state varchar(40),
	PRIMARY KEY (automaker_id)
);

CREATE TABLE dealership_automaker (
	dealership_id int NOT NULL,
	automaker_id int NOT NULL,
	FOREIGN KEY (dealership_id) REFERENCES dealership (dealership_id) ON DELETE CASCADE,
	FOREIGN KEY (automaker_id) REFERENCES automaker (automaker_id) ON DELETE CASCADE
);