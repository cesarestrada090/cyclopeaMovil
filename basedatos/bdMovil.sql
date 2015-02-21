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
-- Create schema bdnuevamovil
--

CREATE DATABASE IF NOT EXISTS bdnuevamovil;
USE bdnuevamovil;

--
-- Definition of table `alineador`
--

DROP TABLE IF EXISTS `alineador`;
CREATE TABLE `alineador` (
  `idCertificado` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nEje` varchar(45) NOT NULL,
  `desviacionAlineamiento` float NOT NULL,
  `resultadoAlineamiento` varchar(10) NOT NULL,
  `medidaObtenidaNeumatico` float NOT NULL,
  `resultadoNeumatico` varchar(45) NOT NULL,
  PRIMARY KEY (`idCertificado`,`nEje`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `alineador`
--

/*!40000 ALTER TABLE `alineador` DISABLE KEYS */;
/*!40000 ALTER TABLE `alineador` ENABLE KEYS */;


--
-- Definition of table `certificado`
--

DROP TABLE IF EXISTS `certificado`;
CREATE TABLE `certificado` (
  `idCertificado` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `tipoDocTransp` varchar(20) NOT NULL,
  `numDocTransp` varchar(45) NOT NULL,
  `tipoDocEvaluar` varchar(45) NOT NULL,
  `numDocEvaluar` varchar(45) NOT NULL,
  `claseAutorizacion` varchar(45) NOT NULL,
  `resultado` int(10) unsigned NOT NULL,
  `vigencia` varchar(45) NOT NULL,
  `fecInspeccion` date NOT NULL,
  `fecVencimiento` date NOT NULL,
  `cIdentidadCert` varchar(45) NOT NULL,
  `codLocal` varchar(45) NOT NULL,
  `ubigeo` varchar(45) NOT NULL,
  PRIMARY KEY (`idCertificado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `certificado`
--

/*!40000 ALTER TABLE `certificado` DISABLE KEYS */;
/*!40000 ALTER TABLE `certificado` ENABLE KEYS */;


--
-- Definition of table `certificadoequipo`
--

DROP TABLE IF EXISTS `certificadoequipo`;
CREATE TABLE `certificadoequipo` (
  `idCertificado` int(10) unsigned NOT NULL,
  `frenometroNumero` int(10) unsigned NOT NULL,
  `alineadorNumero` int(10) unsigned NOT NULL,
  `analizadorNumero` int(10) unsigned NOT NULL,
  `luxometroNumero` int(10) unsigned NOT NULL,
  `bancoSuspension` int(10) unsigned NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `certificadoequipo`
--

/*!40000 ALTER TABLE `certificadoequipo` DISABLE KEYS */;
/*!40000 ALTER TABLE `certificadoequipo` ENABLE KEYS */;


--
-- Definition of table `equipo`
--

DROP TABLE IF EXISTS `equipo`;
CREATE TABLE `equipo` (
  `idEquipo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(120) DEFAULT NULL,
  `marca` varchar(180) DEFAULT NULL,
  `modelo` varchar(180) DEFAULT NULL,
  `anio` int(10) unsigned DEFAULT NULL,
  `estado` int(10) unsigned DEFAULT NULL,
  `bitacora` varchar(45) DEFAULT NULL,
  `idUser` varchar(45) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`idEquipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `equipo`
--

/*!40000 ALTER TABLE `equipo` DISABLE KEYS */;
/*!40000 ALTER TABLE `equipo` ENABLE KEYS */;


--
-- Definition of table `frenometro`
--

DROP TABLE IF EXISTS `frenometro`;
CREATE TABLE `frenometro` (
  `idCertificado` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idEje` int(10) unsigned NOT NULL,
  `pesoServicio` float NOT NULL,
  `fuerzaDerServicio` float NOT NULL,
  `fuertaIzqServicio` float NOT NULL,
  `desequilibrioServicio` float NOT NULL,
  `resultadoEjeServicio` varchar(20) NOT NULL,
  `eficienciaServicio` double NOT NULL,
  `resultadoServicio` varchar(45) NOT NULL,
  `fuerzaDerEstacionamiento` float NOT NULL,
  `fuerzaIzqEstacionamiento` float NOT NULL,
  `desequilibrioEstacionamiento` float NOT NULL,
  `resultadoEjeEstacionamiento` varchar(20) NOT NULL,
  `eficienciaEstacionamiento` float NOT NULL,
  `resultadoEstacionamiento` varchar(38) NOT NULL,
  `fuerzaDerEmergencia` float NOT NULL,
  `fuerzaIzqEmergencia` float NOT NULL,
  `desequilibrioEmergencia` float NOT NULL,
  `resultadoEjeEmergencia` varchar(20) NOT NULL,
  `eficienciaEmergencia` float NOT NULL,
  `resultadoEmergencia` varchar(39) NOT NULL,
  PRIMARY KEY (`idCertificado`,`idEje`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `frenometro`
--

/*!40000 ALTER TABLE `frenometro` DISABLE KEYS */;
/*!40000 ALTER TABLE `frenometro` ENABLE KEYS */;


--
-- Definition of table `luxometro`
--

DROP TABLE IF EXISTS `luxometro`;
CREATE TABLE `luxometro` (
  `idCertificado` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `tipoLuz` int(10) unsigned NOT NULL,
  `medidaDerLuz` float NOT NULL,
  `medidaIzqLuz` float NOT NULL,
  `alineamiento` varchar(80) NOT NULL,
  `resultadoTipoLuz` varchar(15) NOT NULL,
  PRIMARY KEY (`idCertificado`,`tipoLuz`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `luxometro`
--

/*!40000 ALTER TABLE `luxometro` DISABLE KEYS */;
/*!40000 ALTER TABLE `luxometro` ENABLE KEYS */;


--
-- Definition of table `suspension`
--

DROP TABLE IF EXISTS `suspension`;
CREATE TABLE `suspension` (
  `idCertificado` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `delanteraIzq` float NOT NULL,
  `delanteraDer` float NOT NULL,
  `delanteraDesv` float NOT NULL,
  `resultadoDelantera` varchar(10) NOT NULL,
  `resultadoFinalDelantera` varchar(45) NOT NULL,
  `posteriorIzq` float NOT NULL,
  `posteriorDer` float NOT NULL,
  `posteriorDesv` float NOT NULL,
  `resultadoPosterior` varchar(10) NOT NULL,
  `resultadoFinalPosterior` varchar(45) NOT NULL,
  PRIMARY KEY (`idCertificado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `suspension`
--

/*!40000 ALTER TABLE `suspension` DISABLE KEYS */;
/*!40000 ALTER TABLE `suspension` ENABLE KEYS */;


--
-- Definition of table `tarjetapropiedad`
--

DROP TABLE IF EXISTS `tarjetapropiedad`;
CREATE TABLE `tarjetapropiedad` (
  `id_tarjeta` int(11) NOT NULL AUTO_INCREMENT,
  `placa` varchar(20) NOT NULL,
  `ntarjeta` varchar(12) NOT NULL,
  `razon1` varchar(120) NOT NULL,
  `domicilio` varchar(120) NOT NULL,
  `idclase` varchar(15) NOT NULL,
  `idmarca` varchar(15) NOT NULL,
  `fabricacion` smallint(6) NOT NULL,
  `idmodelo` varchar(15) NOT NULL,
  `version` varchar(35) DEFAULT NULL,
  `idcombustible` varchar(15) NOT NULL,
  `idcarroceria` varchar(15) NOT NULL,
  `ejes` smallint(6) NOT NULL,
  `colores` varchar(60) NOT NULL,
  `nmotor` varchar(60) NOT NULL,
  `cilindros` smallint(6) NOT NULL,
  `nserie` varchar(25) NOT NULL,
  `vin` varchar(25) DEFAULT 'numero vim',
  `ruedas` smallint(6) NOT NULL,
  `pasajeros` smallint(6) NOT NULL,
  `asientos` smallint(6) NOT NULL,
  `peso_seco` decimal(10,3) NOT NULL,
  `peso_bruto` decimal(10,3) NOT NULL,
  `longitud` decimal(10,3) NOT NULL,
  `altura` decimal(10,3) NOT NULL,
  `ancho` decimal(10,3) NOT NULL,
  `carga_util` decimal(10,3) NOT NULL,
  `estado` char(1) NOT NULL DEFAULT '1',
  `fecha` datetime NOT NULL,
  PRIMARY KEY (`id_tarjeta`),
  KEY `ix_TarjetaPropiedad_autoinc` (`id_tarjeta`)
) ENGINE=InnoDB AUTO_INCREMENT=87158 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tarjetapropiedad`
--

/*!40000 ALTER TABLE `tarjetapropiedad` DISABLE KEYS */;
/*!40000 ALTER TABLE `tarjetapropiedad` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
