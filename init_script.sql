CREATE SCHEMA IF NOT EXISTS `university`;
USE `university` ;

CREATE TABLE IF NOT EXISTS `university`.`students` (
  `id` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `fees_paid` TINYINT NOT NULL,
  `nationality` VARCHAR(45) NOT NULL,
  `gender` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(45) NOT NULL,
  `email_address` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE (`id`),
  UNIQUE (`username`));

CREATE TABLE IF NOT EXISTS `university`.`modules` (
  `id` BIGINT NOT NULL,
  `module_code` VARCHAR(45) NOT NULL,
  `module_name` VARCHAR(45) NOT NULL,
  `module_year` VARCHAR(45) NOT NULL,
  `module_description` VARCHAR(45) NOT NULL,
  `coordinator_id` VARCHAR(45) NOT NULL,
  `maximum_students` INT NOT NULL,
  `terminated` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE (`id`));
    
    CREATE TABLE IF NOT EXISTS `university`.`grades` (
  `student_id` VARCHAR(45) NOT NULL,
  `grade` INT NOT NULL,
  `module_id` BIGINT NOT NULL,
  PRIMARY KEY (`student_id`, `module_id`),
  CONSTRAINT `Grade-Module`
    FOREIGN KEY (`module_id`)
    REFERENCES `university`.`modules` (`id`),
  CONSTRAINT `Grade-Student`
    FOREIGN KEY (`student_id`)
    REFERENCES `university`.`students` (`id`));

CREATE TABLE IF NOT EXISTS `university`.`enrollment` (
  `student_id` VARCHAR(45) NOT NULL,
  `module_id` BIGINT NOT NULL,
  PRIMARY KEY (`student_id`, `module_id`),
  CONSTRAINT `Enrollment-Modules`
    FOREIGN KEY (`module_id`)
    REFERENCES `university`.`modules` (`id`),
  CONSTRAINT `Enrollment-Student`
    FOREIGN KEY (`student_id`)
    REFERENCES `university`.`students` (`id`));

CREATE TABLE IF NOT EXISTS `university`.`staff` (
  `id` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `nationality` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(45) NOT NULL,
  `email_address` VARCHAR(45) NOT NULL, 
  `gender` VARCHAR(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE (`id`),
  UNIQUE(`USERNAME`));
  
    
INSERT INTO `students`
VALUES ("1", "DARA", "CLARKE", 1, "IRISH", "MALE", "0867543241", "DARA@GMAIL.COM","old road", "DARA6", "PAS$W0R__D");

INSERT INTO `students`
VALUES ("2", "Oisin", "Laker", 0, "CANADIAN", "MALE", "0845443241", "oISIN@GMAIL.COM","NEW road", "oISIN4", "$W0R__D");

INSERT INTO `students`
VALUES ("3", "Charles", "Rcif", 1, "SPANISH", "MALE", "123156", "CHARLIEEE@GMAIL.COM","MEDIUM road", "C3", "PAS$");

INSERT INTO `students`
VALUES ("4", "Teliia", "Foghorn", 0, "FRENCH", "FEMALE", "845775", "THEGOAT@GMAIL.COM","road", "C4", "PASSWORD");

INSERT INTO `modules`
VALUES (1, "COMP30970", "SECURE SOFTWARE ENGINEERING", "2019", "GOOD", "1","500", 1);

INSERT INTO `modules`
VALUES (2, "COMP30432", "CONTEMPORARY SOFTWARE ENGINEERING", "2019", "GOOD", "1","500", 1);

INSERT INTO `modules`
VALUES (3,"ENG10970", "ELECTRICAL ENGINEERING", "2020", "ENGINEERING", "2","100", 0);

INSERT INTO `grades`
VALUES ("1", 100, 1);

INSERT INTO `grades`
VALUES ("3", 0, 1);

INSERT INTO `grades`
VALUES ("4", 10, 2);

INSERT INTO `enrollment`
VALUES ("1",1);

INSERT INTO `enrollment`
VALUES ("1",2);

INSERT INTO `enrollment`
VALUES ("2",2);

INSERT INTO `enrollment`
VALUES ("3",1);

INSERT INTO `enrollment`
VALUES ("4",2);

INSERT INTO `staff`
VALUES ("1", "Lucy", "Barnet", "l1", "Pswword", "Italian", "addle road", "34568", "l@ucd.ie", "Female");

INSERT INTO `staff`
VALUES ("2", "Andrew", "Louis", "A1", "LEXAGRAPH", "Russian", "bakers avenue", "123456789", "a@ucd.ie", "Male");
