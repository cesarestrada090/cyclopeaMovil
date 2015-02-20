-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.6.11


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema restfullcyclopea
--

CREATE DATABASE IF NOT EXISTS restfullcyclopea;
USE restfullcyclopea;

--
-- Definition of table `books`
--

DROP TABLE IF EXISTS `books`;
CREATE TABLE `books` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `author` varchar(45) NOT NULL,
  `title` varchar(45) NOT NULL,
  `isbn` varchar(45) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `books`
--

/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` (`id`,`author`,`title`,`isbn`,`created_at`) VALUES 
 (1,'Cesar','Estrada','12221','2010-10-10 00:00:00');
/*!40000 ALTER TABLE `books` ENABLE KEYS */;


--
-- Definition of table `certificado`
--

DROP TABLE IF EXISTS `certificado`;
CREATE TABLE `certificado` (
  `STR_TPDOCTRANSP` varchar(1) CHARACTER SET utf8 DEFAULT NULL,
  `STR_NUMDOCTRANSP` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `STR_RZTRANSP` varchar(120) DEFAULT NULL,
  `STR_TPDOCEVAL` int(10) unsigned DEFAULT NULL,
  `STR_NUMDOCEVAL` varchar(120) DEFAULT NULL,
  `STR_CLASAUTOR` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `INT_RESULTADO` int(10) unsigned DEFAULT NULL,
  `STR_VIGENCIA` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `DTE_FECINSPECCION` datetime DEFAULT NULL,
  `DTE_FECVENCIMIENTO` datetime DEFAULT NULL,
  `STR_CDENTIDADCERT` varchar(11) CHARACTER SET utf8 DEFAULT NULL,
  `STR_CDLOCAL` varchar(2) CHARACTER SET utf8 DEFAULT NULL,
  `STR_UBIGEO` varchar(6) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `certificado`
--

/*!40000 ALTER TABLE `certificado` DISABLE KEYS */;
INSERT INTO `certificado` (`STR_TPDOCTRANSP`,`STR_NUMDOCTRANSP`,`STR_RZTRANSP`,`STR_TPDOCEVAL`,`STR_NUMDOCEVAL`,`STR_CLASAUTOR`,`INT_RESULTADO`,`STR_VIGENCIA`,`DTE_FECINSPECCION`,`DTE_FECVENCIMIENTO`,`STR_CDENTIDADCERT`,`STR_CDLOCAL`,`STR_UBIGEO`) VALUES 
 (NULL,'46541974','Empresa Orion',1,'A651154','M1',1,'12','2015-02-18 19:24:37','2015-02-18 19:24:37','00','01','130100'),
 (NULL,'46541974','Empresa Orion',1,'A651154','M1',1,'12','2015-02-18 19:24:49','2015-02-18 19:24:49','00','01','130100'),
 (NULL,'46541974','Empresa Orion',1,'A651154','M1',1,'12','2015-02-18 19:50:27','2015-02-18 19:50:27','00','01','130100'),
 ('1','46541974','Empresa Orion',1,'A651154','M1',1,'12','2015-02-18 19:53:51','2015-02-18 19:53:51','00','01','130100');
/*!40000 ALTER TABLE `certificado` ENABLE KEYS */;


--
-- Definition of table `fotografia`
--

DROP TABLE IF EXISTS `fotografia`;
CREATE TABLE `fotografia` (
  `STR_TPDOCEVAL` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `STR_NUMDOCEVAL` varchar(50) NOT NULL,
  `BIN_FOTOGRAFIA` blob NOT NULL,
  `BIN_FOTOGRAFIA2` blob NOT NULL,
  `BIN_FOTOGRAFIA3` blob NOT NULL,
  PRIMARY KEY (`STR_TPDOCEVAL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `fotografia`
--

/*!40000 ALTER TABLE `fotografia` DISABLE KEYS */;
/*!40000 ALTER TABLE `fotografia` ENABLE KEYS */;


--
-- Definition of table `observaciones`
--

DROP TABLE IF EXISTS `observaciones`;
CREATE TABLE `observaciones` (
  `STR_TPDOCEVAL` int(10) unsigned NOT NULL,
  `STR_NUMDOCEVAL` varchar(50) NOT NULL,
  `STR_CDOBSDETEC` varchar(10) NOT NULL,
  `STR_DSOBSDETEC` varchar(1000) NOT NULL,
  PRIMARY KEY (`STR_TPDOCEVAL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `observaciones`
--

/*!40000 ALTER TABLE `observaciones` DISABLE KEYS */;
INSERT INTO `observaciones` (`STR_TPDOCEVAL`,`STR_NUMDOCEVAL`,`STR_CDOBSDETEC`,`STR_DSOBSDETEC`) VALUES 
 (1,'46541974','23,25','Choque en la parte frontal');
/*!40000 ALTER TABLE `observaciones` ENABLE KEYS */;


--
-- Definition of table `resultados`
--

DROP TABLE IF EXISTS `resultados`;
CREATE TABLE `resultados` (
  `INT_PRUEALI` int(10) unsigned NOT NULL,
  `INT_PROFNEUMA` int(10) unsigned NOT NULL,
  `INT_PRUEBLUCES` int(10) unsigned NOT NULL,
  `INT_SUSPENSION` int(10) unsigned NOT NULL,
  `INT_EMIGASES` int(10) unsigned NOT NULL,
  `INT_FRESERV` int(10) unsigned NOT NULL,
  `INT_FREESTAC` int(10) unsigned NOT NULL,
  `INT_FREEMER` int(10) unsigned NOT NULL,
  `NUM_DISEJES` float NOT NULL,
  `NUM_PISOS` int(10) unsigned NOT NULL,
  `STR_OBSERVACIONES` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `resultados`
--

/*!40000 ALTER TABLE `resultados` DISABLE KEYS */;
INSERT INTO `resultados` (`INT_PRUEALI`,`INT_PROFNEUMA`,`INT_PRUEBLUCES`,`INT_SUSPENSION`,`INT_EMIGASES`,`INT_FRESERV`,`INT_FREESTAC`,`INT_FREEMER`,`NUM_DISEJES`,`NUM_PISOS`,`STR_OBSERVACIONES`) VALUES 
 (1,1,1,1,1,1,1,1,12.33,1,'Todo Perfecto');
/*!40000 ALTER TABLE `resultados` ENABLE KEYS */;


--
-- Definition of table `supervisores`
--

DROP TABLE IF EXISTS `supervisores`;
CREATE TABLE `supervisores` (
  `STR_DNI_SUP` varchar(20) NOT NULL,
  PRIMARY KEY (`STR_DNI_SUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `supervisores`
--

/*!40000 ALTER TABLE `supervisores` DISABLE KEYS */;
INSERT INTO `supervisores` (`STR_DNI_SUP`) VALUES 
 ('17844604');
/*!40000 ALTER TABLE `supervisores` ENABLE KEYS */;


--
-- Definition of table `vehiculo`
--

DROP TABLE IF EXISTS `vehiculo`;
CREATE TABLE `vehiculo` (
  `STR_PLACA` varchar(30) NOT NULL DEFAULT '',
  `STR_CATEGORIA` varchar(30) DEFAULT NULL,
  `STR_MOTOR` varchar(30) DEFAULT NULL,
  `STR_SERIE` varchar(30) DEFAULT NULL,
  `NUM_ANIO` int(10) unsigned DEFAULT NULL,
  `NUM_EJES` int(10) unsigned DEFAULT NULL,
  `NUM_RUEDAS` int(10) unsigned DEFAULT NULL,
  `NUM_ASIENTOS` int(10) unsigned DEFAULT NULL,
  `NUM_PSJR` int(10) unsigned DEFAULT NULL,
  `NUM_LARGO` float DEFAULT NULL,
  `NUM_ANCHO` float DEFAULT NULL,
  `NUM_ALTO` float DEFAULT NULL,
  `NUM_PNETO` float DEFAULT NULL,
  `NUM_CUTIL` float DEFAULT NULL,
  `NUM_PBRUTO` float DEFAULT NULL,
  `NUM_PUERTAS` int(10) unsigned DEFAULT NULL,
  `NUM_SALIDAS` int(10) unsigned DEFAULT NULL,
  `NUM_CILINDROS` int(10) unsigned DEFAULT NULL,
  `STR_COLOR` varchar(100) DEFAULT NULL,
  `STR_ID_COMBUSTIBLE` varchar(1) DEFAULT NULL,
  `STR_ID_MARCA` varchar(100) DEFAULT NULL,
  `STR_ID_MODELO` varchar(100) DEFAULT NULL,
  `STR_ID_CARROCERIA` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`STR_PLACA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `vehiculo`
--

/*!40000 ALTER TABLE `vehiculo` DISABLE KEYS */;
INSERT INTO `vehiculo` (`STR_PLACA`,`STR_CATEGORIA`,`STR_MOTOR`,`STR_SERIE`,`NUM_ANIO`,`NUM_EJES`,`NUM_RUEDAS`,`NUM_ASIENTOS`,`NUM_PSJR`,`NUM_LARGO`,`NUM_ANCHO`,`NUM_ALTO`,`NUM_PNETO`,`NUM_CUTIL`,`NUM_PBRUTO`,`NUM_PUERTAS`,`NUM_SALIDAS`,`NUM_CILINDROS`,`STR_COLOR`,`STR_ID_COMBUSTIBLE`,`STR_ID_MARCA`,`STR_ID_MODELO`,`STR_ID_CARROCERIA`) VALUES 
 ('A1-3122','M1','2.2L','1222',2005,2,4,3,4,2.2,1.3,2.32,800,300.2,1000,5,2,4,'Rojo',NULL,NULL,NULL,'1');
/*!40000 ALTER TABLE `vehiculo` ENABLE KEYS */;


--
-- Definition of function `maybe_utf8_decode`
--

DROP FUNCTION IF EXISTS `maybe_utf8_decode`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ $$
CREATE DEFINER=`root`@`localhost` FUNCTION `maybe_utf8_decode`(str text charset utf8) RETURNS text CHARSET utf8
    DETERMINISTIC
BEGIN
declare str_converted text charset utf8;
declare max_error_count int default @@max_error_count;
set @@max_error_count = 0;
set str_converted = convert(binary convert(str using latin1) using utf8);
set @@max_error_count = max_error_count;
if @@warning_count > 0 then
    return str;
else
    return str_converted;
end if;
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
