SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `enotsdb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `enotsdb` ;

-- -----------------------------------------------------
-- Table `enotsdb`.`applications`
-- -----------------------------------type_name------------------
DROP TABLE IF EXISTS `enotsdb`.`applications` ;

CREATE  TABLE IF NOT EXISTS `enotsdb`.`applications` (
  `name` VARCHAR(45) NOT NULL ,
  `developer` VARCHAR(255) NULL ,
  PRIMARY KEY (`name`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `enotsdb`.`types`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enotsdb`.`types` ;

CREATE  TABLE IF NOT EXISTS `enotsdb`.`types` (
  `name` VARCHAR(45) NOT NULL ,
  `description` VARCHAR(255) NULL ,
  PRIMARY KEY (`name`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `enotsdb`.`applications_types`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enotsdb`.`applications_types` ;

CREATE  TABLE IF NOT EXISTS `enotsdb`.`applications_types` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `application_name` VARCHAR(45) NULL ,
  `type_name` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_applications_types_app` (`application_name` ASC) ,
  INDEX `fk_applications_types_type` (`type_name` ASC) ,
  UNIQUE INDEX `app_type` (`application_name` ASC, `type_name` ASC) ,
  CONSTRAINT `fk_applications_types_app`
    FOREIGN KEY (`application_name` )
    REFERENCES `enotsdb`.`applications` (`name` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_applications_types_type`
    FOREIGN KEY (`type_name` )
    REFERENCES `enotsdb`.`types` (`name` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

GRANT ALL PRIVILEGES  ON `enotsdb`.*
TO 'enotsname'@'%' IDENTIFIED BY 'enotspass'
WITH GRANT OPTION;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
