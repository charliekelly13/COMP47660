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
  `id` BIGINT NOT NULL,
  `module_code` VARCHAR(45) NOT NULL,
  `module_name` VARCHAR(45) NOT NULL,
  `module_year` VARCHAR(45) NOT NULL,
  `module_description` VARCHAR(45) NOT NULL,
  `maximum_students` INT NOT NULL,
  `coordinator_id` VARCHAR(45) NOT NULL,
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
  
  CREATE TABLE IF NOT EXISTS `university`.`coordinates`(
  `coordinator_id` VARCHAR(45) NOT NULL,
  `module_id` BIGINT NOT NULL,
  UNIQUE (`module_id`),
  PRIMARY KEY (`coordinator_id`, `module_id`),
  CONSTRAINT `Coordinates-Modules`
    FOREIGN KEY (`module_id`)
    REFERENCES `university`.`modules` (`id`),
  CONSTRAINT `Coordinates-Staff`
    FOREIGN KEY (`coordinator_id`)
    REFERENCES `university`.`staff` (`id`));

