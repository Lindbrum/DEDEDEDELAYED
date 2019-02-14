-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ambiental_monitoring
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ambiental_monitoring
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ambiental_monitoring` DEFAULT CHARACTER SET utf8 ;
USE `ambiental_monitoring` ;

-- -----------------------------------------------------
-- Table `ambiental_monitoring`.`Utente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ambiental_monitoring`.`Utente` (
  `idUtente` INT NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `idZona` VARCHAR(45) NULL,
  `admin` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`idUtente`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ambiental_monitoring`.`Zona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ambiental_monitoring`.`Zona` (
  `idZona` INT NOT NULL,
  `tipologia` VARCHAR(45) NOT NULL,
  `nome` VARCHAR(45) NULL,
  `alert` SMALLINT(2) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idZona`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ambiental_monitoring`.`Sensore`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ambiental_monitoring`.`Sensore` (
  `idSensore` INT NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  `variabileAmbientale` DOUBLE NULL,
  `massimale` DOUBLE NOT NULL,
  `frequenzaInvio` INT NOT NULL,
  `ultimoInvio` DATETIME NULL,
  `zona` INT NOT NULL,
  `installatore` INT NOT NULL,
  PRIMARY KEY (`idSensore`),
  INDEX `fk_Sensore_Zona1_idx` (`zona` ASC) VISIBLE,
  INDEX `fk_Sensore_Utente1_idx` (`installatore` ASC) VISIBLE,
  CONSTRAINT `fk_Sensore_Zona1`
    FOREIGN KEY (`zona`)
    REFERENCES `ambiental_monitoring`.`Zona` (`idZona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Sensore_Utente1`
    FOREIGN KEY (`installatore`)
    REFERENCES `ambiental_monitoring`.`Utente` (`idUtente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ambiental_monitoring`.`Segnale`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ambiental_monitoring`.`Segnale` (
  `idSegnale` INT NOT NULL AUTO_INCREMENT,
  `sensore` INT NOT NULL,
  `timestamp` DATETIME NOT NULL,
  `variabileAmbientale` VARCHAR(45) NOT NULL,
  `alert` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idSegnale`),
  INDEX `fk_Segnale_Sensore_idx` (`sensore` ASC) VISIBLE,
  CONSTRAINT `fk_Segnale_Sensore`
    FOREIGN KEY (`sensore`)
    REFERENCES `ambiental_monitoring`.`Sensore` (`idSensore`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ambiental_monitoring`.`Gestione`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ambiental_monitoring`.`Gestione` (
  `utente` INT NOT NULL,
  `zona` INT NOT NULL,
  PRIMARY KEY (`utente`, `zona`),
  INDEX `fk_Utente_has_Zona_Zona1_idx` (`zona` ASC) VISIBLE,
  INDEX `fk_Utente_has_Zona_Utente1_idx` (`utente` ASC) VISIBLE,
  CONSTRAINT `fk_Utente_has_Zona_Utente1`
    FOREIGN KEY (`utente`)
    REFERENCES `ambiental_monitoring`.`Utente` (`idUtente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Utente_has_Zona_Zona1`
    FOREIGN KEY (`zona`)
    REFERENCES `ambiental_monitoring`.`Zona` (`idZona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE USER 'root' IDENTIFIED BY 'root';

GRANT ALL ON `ambiental_monitoring`.* TO 'root';
CREATE USER 'admin' IDENTIFIED BY 'admin';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE `ambiental_monitoring`.* TO 'admin';
GRANT EXECUTE ON ROUTINE `ambiental_monitoring`.* TO 'admin';
CREATE USER 'client' IDENTIFIED BY 'client';

GRANT SELECT ON TABLE `ambiental_monitoring`.* TO 'client';
CREATE USER 'server' IDENTIFIED BY 'server';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE `ambiental_monitoring`.* TO 'server';
GRANT EXECUTE ON ROUTINE `ambiental_monitoring`.* TO 'server';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
