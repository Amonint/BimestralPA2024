CREATE DATABASE  IF NOT EXISTS `jpa_paquetes` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `jpa_paquetes`;
-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: jpa_paquetes
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bodeguero`
--

DROP TABLE IF EXISTS `bodeguero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bodeguero` (
  `CEDULA` varchar(255) NOT NULL,
  `LOCAL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CEDULA`),
  CONSTRAINT `FK_BODEGUERO_CEDULA` FOREIGN KEY (`CEDULA`) REFERENCES `persona` (`CEDULA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bodeguero`
--

LOCK TABLES `bodeguero` WRITE;
/*!40000 ALTER TABLE `bodeguero` DISABLE KEYS */;
INSERT INTO `bodeguero` VALUES ('11111','Centro');
/*!40000 ALTER TABLE `bodeguero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `CEDULA` varchar(255) NOT NULL,
  `CELULAR` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CEDULA`),
  CONSTRAINT `FK_CLIENTE_CEDULA` FOREIGN KEY (`CEDULA`) REFERENCES `persona` (`CEDULA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES ('22222','098541'),('33333','0586685');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `direccion`
--

DROP TABLE IF EXISTS `direccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `direccion` (
  `CODIGO` varchar(255) NOT NULL,
  `ACTUAL` tinyint(1) DEFAULT '0',
  `CALLE1` varchar(255) DEFAULT NULL,
  `CALLE2` varchar(255) DEFAULT NULL,
  `REFERENCIA` varchar(255) DEFAULT NULL,
  `CLIENTE_CEDULA` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CODIGO`),
  KEY `FK_DIRECCION_CLIENTE_CEDULA` (`CLIENTE_CEDULA`),
  CONSTRAINT `FK_DIRECCION_CLIENTE_CEDULA` FOREIGN KEY (`CLIENTE_CEDULA`) REFERENCES `persona` (`CEDULA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direccion`
--

LOCK TABLES `direccion` WRITE;
/*!40000 ALTER TABLE `direccion` DISABLE KEYS */;
/*!40000 ALTER TABLE `direccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleado` (
  `CEDULA` varchar(255) NOT NULL,
  `CIUDAD` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CEDULA`),
  CONSTRAINT `FK_EMPLEADO_CEDULA` FOREIGN KEY (`CEDULA`) REFERENCES `persona` (`CEDULA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` VALUES ('11111','Loja');
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entrega`
--

DROP TABLE IF EXISTS `entrega`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entrega` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `CODIGO` varchar(255) DEFAULT NULL,
  `FECHA` date DEFAULT NULL,
  `OBSERVACION` varchar(255) DEFAULT NULL,
  `CLIENTE_CEDULA` varchar(255) DEFAULT NULL,
  `REPARTIDOR_CEDULA` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_ENTREGA_CLIENTE_CEDULA` (`CLIENTE_CEDULA`),
  KEY `FK_ENTREGA_REPARTIDOR_CEDULA` (`REPARTIDOR_CEDULA`),
  CONSTRAINT `FK_ENTREGA_CLIENTE_CEDULA` FOREIGN KEY (`CLIENTE_CEDULA`) REFERENCES `persona` (`CEDULA`),
  CONSTRAINT `FK_ENTREGA_REPARTIDOR_CEDULA` FOREIGN KEY (`REPARTIDOR_CEDULA`) REFERENCES `persona` (`CEDULA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrega`
--

LOCK TABLES `entrega` WRITE;
/*!40000 ALTER TABLE `entrega` DISABLE KEYS */;
/*!40000 ALTER TABLE `entrega` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado`
--

DROP TABLE IF EXISTS `estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estado` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `ESTADO` varchar(255) DEFAULT NULL,
  `FECHA` date DEFAULT NULL,
  `OBSERVACION` varchar(255) DEFAULT NULL,
  `TIPO` int DEFAULT NULL,
  `PAQUETE_CODIGO` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_ESTADO_PAQUETE_CODIGO` (`PAQUETE_CODIGO`),
  CONSTRAINT `FK_ESTADO_PAQUETE_CODIGO` FOREIGN KEY (`PAQUETE_CODIGO`) REFERENCES `paquete` (`CODIGO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado`
--

LOCK TABLES `estado` WRITE;
/*!40000 ALTER TABLE `estado` DISABLE KEYS */;
/*!40000 ALTER TABLE `estado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paquete`
--

DROP TABLE IF EXISTS `paquete`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paquete` (
  `CODIGO` varchar(255) NOT NULL,
  `ALTO` int DEFAULT NULL,
  `DESCRIPCION` varchar(255) DEFAULT NULL,
  `PESO` int DEFAULT NULL,
  `CLIENTE_CEDULA` varchar(255) DEFAULT NULL,
  `ENTREGA_ID` bigint DEFAULT NULL,
  PRIMARY KEY (`CODIGO`),
  KEY `FK_PAQUETE_ENTREGA_ID` (`ENTREGA_ID`),
  KEY `FK_PAQUETE_CLIENTE_CEDULA` (`CLIENTE_CEDULA`),
  CONSTRAINT `FK_PAQUETE_CLIENTE_CEDULA` FOREIGN KEY (`CLIENTE_CEDULA`) REFERENCES `persona` (`CEDULA`),
  CONSTRAINT `FK_PAQUETE_ENTREGA_ID` FOREIGN KEY (`ENTREGA_ID`) REFERENCES `entrega` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paquete`
--

LOCK TABLES `paquete` WRITE;
/*!40000 ALTER TABLE `paquete` DISABLE KEYS */;
INSERT INTO `paquete` VALUES ('001',3,'ropa',6,NULL,NULL);
/*!40000 ALTER TABLE `paquete` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persona` (
  `CEDULA` varchar(255) NOT NULL,
  `DTYPE` varchar(31) DEFAULT NULL,
  `APELLIDOS` varchar(255) DEFAULT NULL,
  `MAIL` varchar(255) DEFAULT NULL,
  `NOMBRES` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CEDULA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES ('11111','Bodeguero','Saritama','srmjs@gmail.com','Jose'),('22222','Cliente','Celi','dsaa@gmail.com','Cesar'),('33333','Cliente','','ffss@gmail.com','Franco');
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repartidor`
--

DROP TABLE IF EXISTS `repartidor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `repartidor` (
  `CEDULA` varchar(255) NOT NULL,
  `ZONA` int DEFAULT NULL,
  PRIMARY KEY (`CEDULA`),
  CONSTRAINT `FK_REPARTIDOR_CEDULA` FOREIGN KEY (`CEDULA`) REFERENCES `persona` (`CEDULA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repartidor`
--

LOCK TABLES `repartidor` WRITE;
/*!40000 ALTER TABLE `repartidor` DISABLE KEYS */;
/*!40000 ALTER TABLE `repartidor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-26 21:36:45
