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
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE (`id`),
  UNIQUE (`username`));

CREATE TABLE IF NOT EXISTS `university`.`modules` (
  `id` VARCHAR(45) NOT NULL,
  `module_name` VARCHAR(45) NOT NULL,
  `module_description` VARCHAR(45) NOT NULL,
  `maximum_students` INT NOT NULL,
  `enrolled_students` INT NOT NULL,
  `coordinator_id` VARCHAR(45) NOT NULL,
  `coordinator_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE (`id`));
    
    CREATE TABLE IF NOT EXISTS `university`.`grades` (
  `student_id` VARCHAR(45) NOT NULL,
  `grade` VARCHAR(2) NOT NULL,
  `module_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`student_id`, `module_id`),
  CONSTRAINT `Grade-Module`
    FOREIGN KEY (`module_id`)
    REFERENCES `university`.`modules` (`id`),
  CONSTRAINT `Grade-Student`
    FOREIGN KEY (`student_id`)
    REFERENCES `university`.`students` (`id`));

CREATE TABLE IF NOT EXISTS `university`.`enrollment` (
  `student_id` VARCHAR(45) NOT NULL,
  `module_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_student`, `module_id`),
  CONSTRAINT `Enrollment-Modules`
    FOREIGN KEY (`module_id`)
    REFERENCES `university`.`modules` (`id`),
  CONSTRAINT `Enrollment-Student`
    FOREIGN KEY (`student_id`)
    REFERENCES `university`.`students` (`id`));

CREATE TABLE IF NOT EXISTS `university`.`staff` (
  `staff_id` VARCHAR(45) NOT NULL,
  `staff_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`staff_id`),
  UNIQUE (`staff_id`));
  
  CREATE TABLE IF NOT EXISTS `university`.`coordinates`(
  `coordinator_id` VARCHAR(45) NOT NULL,
  `module_id` VARCHAR(45) NOT NULL,
  UNIQUE (`module_id`),
  PRIMARY KEY (`coordinator_id`, `module_id`),
  CONSTRAINT `Coordinates-Modules`
    FOREIGN KEY (`module_id`)
    REFERENCES `university`.`modules` (`module_id`),
  CONSTRAINT `Coordinates-Staff`
    FOREIGN KEY (`coordinator_id`)
    REFERENCES `university`.`staff` (`staff_id`));

