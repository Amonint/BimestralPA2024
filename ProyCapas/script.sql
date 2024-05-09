-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema paquetes
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema paquetes
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `paquetes` DEFAULT CHARACTER SET utf8 ;
USE `paquetes` ;

-- -----------------------------------------------------
-- Table `paquetes`.`persona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paquetes`.`persona` (
  `id_persona` INT NOT NULL AUTO_INCREMENT,
  `cedula` VARCHAR(10) NULL,
  `apellidos` VARCHAR(55) NULL,
  `nombres` VARCHAR(55) NULL,
  `mail` VARCHAR(45) NULL,
  PRIMARY KEY (`id_persona`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paquetes`.`empleado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paquetes`.`empleado` (
  `id_empleado` INT NOT NULL AUTO_INCREMENT,
  `ciudad` VARCHAR(150) NULL,
  PRIMARY KEY (`id_empleado`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paquetes`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paquetes`.`cliente` (
  `id_clente` INT NOT NULL AUTO_INCREMENT,
  `celular` VARCHAR(45) NULL,
  PRIMARY KEY (`id_clente`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paquetes`.`repartidor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paquetes`.`repartidor` (
  `id_repartidor` INT NOT NULL AUTO_INCREMENT,
  `zona` INT NULL,
  PRIMARY KEY (`id_repartidor`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paquetes`.`bodegero`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paquetes`.`bodegero` (
  `id_bodegero` INT NOT NULL AUTO_INCREMENT,
  `local` VARCHAR(70) NULL,
  PRIMARY KEY (`id_bodegero`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paquetes`.`entrega`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paquetes`.`entrega` (
  `id_entrega` INT NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(45) NULL,
  `fecha` DATE NULL,
  `observacion` VARCHAR(150) NULL,
  `pk_empleado` INT NOT NULL,
  PRIMARY KEY (`id_entrega`),
  INDEX `fk_entrega_empleado_idx` (`pk_empleado` ASC) VISIBLE,
  CONSTRAINT `fk_entrega_empleado`
    FOREIGN KEY (`pk_empleado`)
    REFERENCES `paquetes`.`empleado` (`id_empleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paquetes`.`direccion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paquetes`.`direccion` (
  `id_direccion` INT NOT NULL AUTO_INCREMENT,
  `calle1` VARCHAR(45) NULL,
  `calle2` VARCHAR(45) NULL,
  `referencia` VARCHAR(45) NULL,
  `actual` INT NULL,
  `pk_cliente` INT NOT NULL,
  PRIMARY KEY (`id_direccion`),
  INDEX `fk_direccion_cliente1_idx` (`pk_cliente` ASC) VISIBLE,
  CONSTRAINT `fk_direccion_cliente1`
    FOREIGN KEY (`pk_cliente`)
    REFERENCES `paquetes`.`cliente` (`id_clente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paquetes`.`paquete`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paquetes`.`paquete` (
  `id_paquete` INT NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(20) NULL,
  `descripcion` VARCHAR(100) NULL,
  `peso` INT NULL,
  `alto` INT NULL,
  `pk_entrega` INT NOT NULL,
  PRIMARY KEY (`id_paquete`),
  INDEX `fk_paquete_entrega1_idx` (`pk_entrega` ASC) VISIBLE,
  CONSTRAINT `fk_paquete_entrega1`
    FOREIGN KEY (`pk_entrega`)
    REFERENCES `paquetes`.`entrega` (`id_entrega`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paquetes`.`estado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paquetes`.`estado` (
  `id_estado` INT NOT NULL AUTO_INCREMENT,
  `tipo` VARCHAR(45) NULL,
  `estado` VARCHAR(45) NULL,
  `fecha` DATE NULL,
  `observacion` VARCHAR(150) NULL,
  `pk_paquete` INT NOT NULL,
  INDEX `fk_estado_paquete1_idx` (`pk_paquete` ASC) VISIBLE,
  PRIMARY KEY (`id_estado`),
  CONSTRAINT `fk_estado_paquete1`
    FOREIGN KEY (`pk_paquete`)
    REFERENCES `paquetes`.`paquete` (`id_paquete`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
