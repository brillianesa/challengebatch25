-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema db_batch25
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema db_batch25
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db_batch25` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `db_batch25` ;

-- -----------------------------------------------------
-- Table `db_batch25`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_batch25`.`product` (
  `productid` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NULL DEFAULT NULL,
  `description` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`productid`))
ENGINE = InnoDB
AUTO_INCREMENT = 23
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_batch25`.`user_input`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_batch25`.`user_input` (
  `userinputid` INT NOT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`userinputid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_batch25`.`trx_user_input`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_batch25`.`trx_user_input` (
  `trscid` INT NOT NULL AUTO_INCREMENT,
  `label` VARCHAR(255) NULL DEFAULT NULL,
  `productid` INT NULL DEFAULT NULL,
  `userinputid` INT NULL DEFAULT NULL,
  PRIMARY KEY (`trscid`),
  INDEX `trx_user_input_ibfk_2` (`userinputid` ASC) VISIBLE,
  INDEX `productid` (`productid` ASC) VISIBLE,
  CONSTRAINT `trx_user_input_ibfk_2`
    FOREIGN KEY (`userinputid`)
    REFERENCES `db_batch25`.`user_input` (`userinputid`)
    ON UPDATE RESTRICT,
  CONSTRAINT `trx_user_input_ibfk_3`
    FOREIGN KEY (`productid`)
    REFERENCES `db_batch25`.`product` (`productid`),
  CONSTRAINT `trx_user_input_ibfk_4`
    FOREIGN KEY (`productid`)
    REFERENCES `db_batch25`.`product` (`productid`),
  CONSTRAINT `trx_user_input_ibfk_5`
    FOREIGN KEY (`productid`)
    REFERENCES `db_batch25`.`product` (`productid`),
  CONSTRAINT `trx_user_input_ibfk_6`
    FOREIGN KEY (`productid`)
    REFERENCES `db_batch25`.`product` (`productid`))
ENGINE = InnoDB
AUTO_INCREMENT = 32
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
