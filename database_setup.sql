#########################################################################################################################################
# setting up an newe user:
#########################################################################################################################################

DROP USER IF EXISTS 'spring-hibernate'@'localhost';
CREATE USER 'spring-hibernate'@'localhost' IDENTIFIED BY 'spring-hibernate';
GRANT ALL PRIVILEGES ON * . * TO 'spring-hibernate'@'localhost';
ALTER USER 'spring-hibernate'@'localhost' IDENTIFIED WITH mysql_native_password BY 'spring-hibernate';


#########################################################################################################################################
# setting up a new database:
#########################################################################################################################################

DROP DATABASE IF EXISTS `spring-hibernate`;
CREATE DATABASE `spring-hibernate`;
USE `spring-hibernate`;


#########################################################################################################################################
# creating new tables for the database:
#########################################################################################################################################

DROP TABLE IF EXISTS `spring-hibernate`.`meeting`;
CREATE TABLE `spring-hibernate`.`meeting` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `date_time` datetime NOT NULL,
  `display` tinyint(1) DEFAULT '1',
  `created` datetime DEFAULT now(),
  `last_updated` datetime DEFAULT now(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
);

#########################################################################################################################################

DROP TABLE IF EXISTS `spring-hibernate`.`user`;
CREATE TABLE `spring-hibernate`.`user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL UNIQUE,
  `password` varchar(50) NOT NULL,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `company` varchar(100) NOT NULL,
  `is_admin` tinyint(1) DEFAULT '0',
  `created` datetime DEFAULT now(),
  `last_updated` datetime DEFAULT now(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
);

#########################################################################################################################################

DROP TABLE IF EXISTS `spring-hibernate`.`meeting_user`;
CREATE TABLE `spring-hibernate`.`meeting_user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_meeting` INT NOT NULL,
  `id_user` INT NOT NULL,
  `created` datetime DEFAULT now(),
  `last_updated` datetime DEFAULT now(),
  PRIMARY KEY (`id`));


#########################################################################################################################################
# filling up the new tables with sample data:
#########################################################################################################################################

INSERT INTO `spring-hibernate`.`meeting` (
    `name`,
    `date_time`) 
VALUES
	('Thementag - Wie leite ich ein Thema richtig ein?', '2020-10-01 10:00:00'),
	('Ernährungsberatung', '2020-10-01 12:00:00'),
	('Workshop Datenverwaltung', '2020-10-01 14:00:00'),
	('Vortrag Algorithmen', '2020-10-20 10:30:00'),
	('Vortrag Datenstrukturen', '2020-10-20 12:30:00'),
	('SQL Einführung', '2020-10-06 08:30:00'),
	('Einführung in komplexe Systeme', '2020-10-06 15:00:00');

#########################################################################################################################################

INSERT INTO `spring-hibernate`.`user` (
    `username`,
    `password`,
    `firstname`,
    `lastname`,
    `email`,
    `company`,
    `is_admin`) 
VALUES
	('admin', 'admin', 'admin', 'admin', 'admin', 'admin', 1),
	('jdun', '12345', 'Jax', 'Dunlop', 'J.Dunlop@dunlop-gmbh.com', 'Dunlop', 0),
	('mmus', '12345', 'Max', 'Mustermann', 'M.Mustermann@beispielfirma.com', 'Beispielfirma', 0),
	('gdin', '12345', 'Gerda', 'Dinkel', 'G.Dinkel@email.com', 'Post', 0),
	('sfle', '12345', 'Sammy', 'Fleischbällchen', 'S.Fleisch@hotmail.com', 'Burgerking', 0),
	('smue', '12345', 'Sabine', 'Müller', 'S.Müller@gmail.com', 'DHL', 0);
    
#########################################################################################################################################

INSERT INTO `spring-hibernate`.`meeting_user` (
    `id_meeting`,
    `id_user`) 
VALUES
	('1', '2'),
	('2', '2'),
	('3', '2'),
	('1', '3'),
	('3', '3'),
	('5', '3'),
	('2', '4'),
	('3', '4'),
	('4', '4'),
	('3', '5'),
	('4', '5'),
	('6', '5'),
	('4', '6'),
	('6', '6'),
	('7', '6');