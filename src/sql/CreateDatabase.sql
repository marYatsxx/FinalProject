CREATE SCHEMA IF NOT EXISTS `pharmacy` DEFAULT CHARACTER SET utf8 ;
USE `pharmacy` ;

-- -----------------------------------------------------
-- Table `pharmacy`.`medicine`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pharmacy`.`medicine` (
     `medicine_id` INT(11) NOT NULL AUTO_INCREMENT,
     `name` VARCHAR(45) NOT NULL,
     `dosage` INT(11) NOT NULL,
     `price` DOUBLE NOT NULL,
     `needs_prescription` TINYINT(4) NOT NULL,
     PRIMARY KEY (`medicine_id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `pharmacy`.`user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pharmacy`.`user_role` (
    `roleid` INT(11) NOT NULL,
    `role` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`roleid`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `pharmacy`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pharmacy`.`user` (
   `user_id` INT NOT NULL AUTO_INCREMENT,
   `login` VARCHAR(50) CHARACTER SET 'utf8' NOT NULL,
   `password` VARCHAR(45) NOT NULL,
   `name` VARCHAR(45) NOT NULL,
   `surname` VARCHAR(45) NOT NULL,
   `user_role_id` INT(11) NOT NULL,
   INDEX `fk_user_user_role_idx` (`user_role_id` ASC) VISIBLE,
   UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
   PRIMARY KEY (`user_id`),
   CONSTRAINT `fk_user_user_role`
     FOREIGN KEY (`user_role_id`)
       REFERENCES `pharmacy`.`user_role` (`roleid`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `pharmacy`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pharmacy`.`order` (
    `order_id` INT(11) NOT NULL AUTO_INCREMENT,
    `client_login` VARCHAR(50) CHARACTER SET 'utf8' NOT NULL,
    `price` DOUBLE NOT NULL,
    `validity` DATETIME NOT NULL,
    PRIMARY KEY (`order_id`),
    INDEX `fk_order_user1_idx` (`client_login` ASC) VISIBLE,
    CONSTRAINT `fk_order_user1`
    FOREIGN KEY (`client_login`)
    REFERENCES `pharmacy`.`user` (`login`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `pharmacy`.`order_has_medicine`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pharmacy`.`order_has_medicine` (
    `order_id` INT(11) NOT NULL,
    `medicine_id` INT(11) NOT NULL,
    PRIMARY KEY (`order_id`, `medicine_id`),
    INDEX `fk_order_has_medicine_medicine1_idx` (`medicine_id` ASC) VISIBLE,
    INDEX `fk_order_has_medicine_order1_idx` (`order_id` ASC) VISIBLE,
    CONSTRAINT `fk_order_has_medicine_medicine1`
    FOREIGN KEY (`medicine_id`)
    REFERENCES `pharmacy`.`medicine` (`medicine_id`),
    CONSTRAINT `fk_order_has_medicine_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `pharmacy`.`order` (`order_id`))
ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `pharmacy`.`prescription`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pharmacy`.`prescription` (
   `prescription_id` INT(11) NOT NULL AUTO_INCREMENT,
   `client_login` VARCHAR(50) CHARACTER SET 'utf8' NOT NULL,
   `doctor_login` VARCHAR(50) CHARACTER SET 'utf8' NOT NULL,
   `validity` DATETIME NULL DEFAULT NULL,
    `medicine_id` INT(11) NOT NULL,
    PRIMARY KEY (`prescription_id`),
    INDEX `fk_prescription_medicine1_idx` (`medicine_id` ASC) VISIBLE,
    INDEX `fk_prescription_user1_idx` (`doctor_login` ASC) VISIBLE,
    INDEX `fk_prescription_user2_idx` (`client_login` ASC) VISIBLE,
    CONSTRAINT `fk_prescription_medicine1`
    FOREIGN KEY (`medicine_id`)
    REFERENCES `pharmacy`.`medicine` (`medicine_id`),
    CONSTRAINT `fk_prescription_user1`
    FOREIGN KEY (`doctor_login`)
    REFERENCES `pharmacy`.`user` (`login`),
    CONSTRAINT `fk_prescription_user2`
    FOREIGN KEY (`client_login`)
    REFERENCES `pharmacy`.`user` (`login`))
ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '		';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
