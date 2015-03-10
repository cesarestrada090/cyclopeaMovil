-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.28-rc-community


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
-- Definition of table `certificado`
--

DROP TABLE IF EXISTS `certificado`;
CREATE TABLE `certificado` (
  `STR_TPDOCTRANSP` varchar(1) DEFAULT NULL,
  `STR_NUMDOCTRANSP` varchar(15) DEFAULT NULL,
  `STR_RZTRANSP` varchar(120) DEFAULT NULL,
  `STR_TPDOCEVAL` int(10) unsigned DEFAULT NULL,
  `STR_NUMDOCEVAL` varchar(120) DEFAULT NULL,
  `STR_CLASAUTOR` varchar(100) DEFAULT NULL,
  `INT_RESULTADO` int(10) unsigned DEFAULT NULL,
  `STR_VIGENCIA` varchar(20) DEFAULT NULL,
  `DTE_FECINSPECCION` datetime DEFAULT NULL,
  `DTE_FECVENCIMIENTO` datetime DEFAULT NULL,
  `STR_CDENTIDADCERT` varchar(11) DEFAULT NULL,
  `STR_CDLOCAL` varchar(2) DEFAULT NULL,
  `STR_UBIGEO` varchar(6) DEFAULT NULL,
  `INT_IDCERTIFICADO` int(10) unsigned DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `certificado`
--

/*!40000 ALTER TABLE `certificado` DISABLE KEYS */;
INSERT INTO `certificado` (`STR_TPDOCTRANSP`,`STR_NUMDOCTRANSP`,`STR_RZTRANSP`,`STR_TPDOCEVAL`,`STR_NUMDOCEVAL`,`STR_CLASAUTOR`,`INT_RESULTADO`,`STR_VIGENCIA`,`DTE_FECINSPECCION`,`DTE_FECVENCIMIENTO`,`STR_CDENTIDADCERT`,`STR_CDLOCAL`,`STR_UBIGEO`,`INT_IDCERTIFICADO`) VALUES 
 ('-',NULL,NULL,NULL,'-','-',0,'12',NULL,NULL,'-','-','-',1),
 ('-',NULL,NULL,NULL,'-','-',0,'12',NULL,NULL,'-','-','-',2);
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
  `STR_TPDOCEVAL` int(10) unsigned DEFAULT NULL,
  `STR_NUMDOCEVAL` varchar(50) DEFAULT NULL,
  `STR_CDOBSDETEC` varchar(10) DEFAULT NULL,
  `STR_DSOBSDETEC` varchar(1000) DEFAULT NULL,
  `INT_IDCERTIFICADO` int(10) unsigned DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `observaciones`
--

/*!40000 ALTER TABLE `observaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `observaciones` ENABLE KEYS */;


--
-- Definition of table `resultados`
--

DROP TABLE IF EXISTS `resultados`;
CREATE TABLE `resultados` (
  `IDCERTIFICADO` int(10) unsigned NOT NULL,
  `PRUEALI` int(10) unsigned DEFAULT NULL,
  `PROFNEUMA` int(10) unsigned DEFAULT NULL,
  `PRUEBLUCES` int(10) unsigned DEFAULT NULL,
  `SUSPENSION` int(10) unsigned DEFAULT NULL,
  `EMIGASES` int(10) unsigned DEFAULT NULL,
  `FRESERV` int(10) unsigned DEFAULT NULL,
  `FREESTAC` int(10) unsigned DEFAULT NULL,
  `FREEMER` int(10) unsigned DEFAULT NULL,
  `DISEJES` float DEFAULT NULL,
  `PISOS` int(10) unsigned DEFAULT NULL,
  `OBSERVACIONES` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `resultados`
--

/*!40000 ALTER TABLE `resultados` DISABLE KEYS */;
INSERT INTO `resultados` (`IDCERTIFICADO`,`PRUEALI`,`PROFNEUMA`,`PRUEBLUCES`,`SUSPENSION`,`EMIGASES`,`FRESERV`,`FREESTAC`,`FREEMER`,`DISEJES`,`PISOS`,`OBSERVACIONES`) VALUES 
 (1,0,0,0,0,0,0,0,0,0,0,NULL),
 (2,0,0,0,0,0,0,0,0,0,0,NULL),
 (1,0,0,0,0,0,0,0,0,0,0,NULL),
 (2,0,0,0,0,0,0,0,0,0,0,NULL),
 (1,0,0,0,0,0,0,0,0,0,0,NULL),
 (2,0,0,0,0,0,0,0,0,0,0,NULL),
 (1,0,0,0,0,0,0,0,0,0,0,NULL),
 (2,0,0,0,0,0,0,0,0,0,0,NULL),
 (1,0,0,0,0,0,0,0,0,0,0,NULL),
 (2,0,0,0,0,0,0,0,0,0,0,NULL);
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
  `STR_ID_COMBUSTIBLE` varchar(100) DEFAULT NULL,
  `STR_ID_MARCA` varchar(100) DEFAULT NULL,
  `STR_ID_MODELO` varchar(100) DEFAULT NULL,
  `STR_ID_CARROCERIA` varchar(100) DEFAULT NULL,
  `INT_IDCERTIFICADO` int(10) unsigned NOT NULL,
  PRIMARY KEY (`STR_PLACA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `vehiculo`
--

/*!40000 ALTER TABLE `vehiculo` DISABLE KEYS */;
INSERT INTO `vehiculo` (`STR_PLACA`,`STR_CATEGORIA`,`STR_MOTOR`,`STR_SERIE`,`NUM_ANIO`,`NUM_EJES`,`NUM_RUEDAS`,`NUM_ASIENTOS`,`NUM_PSJR`,`NUM_LARGO`,`NUM_ANCHO`,`NUM_ALTO`,`NUM_PNETO`,`NUM_CUTIL`,`NUM_PBRUTO`,`NUM_PUERTAS`,`NUM_SALIDAS`,`NUM_CILINDROS`,`STR_COLOR`,`STR_ID_COMBUSTIBLE`,`STR_ID_MARCA`,`STR_ID_MODELO`,`STR_ID_CARROCERIA`,`INT_IDCERTIFICADO`) VALUES 
 ('dsok2',NULL,'3126','22266',2015,6,0,6,0,26,662,63,32,6,6,NULL,NULL,0,'6',' NINGUNO',' SIN MARCA','0','***',2),
 ('kmasdmka',NULL,'9','8',2015,8,0,8,0,8,8,8,0,9,9,NULL,NULL,0,'9','Item 1','0','0','Item 1',1);
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

--
-- Definition of procedure `listarAllCertificados`
--

DROP PROCEDURE IF EXISTS `listarAllCertificados`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `listarAllCertificados`()
BEGIN
select
       /*CERTIFICADO*/
       IFNULL(cer.idcertificado,'-') AS idcertificado,
       IFNULL(cer.tipoDocTransp,'-') AS tipoDocTransp,
       IFNULL(cer.numDocEvaluar,'-') AS numDocEvaluar,
       IFNULL(cer.claseAutorizacion,'-') AS claseAutorizacion,
       IFNULL(cer.resultado,'-') AS resultado,
       IFNULL(cer.vigencia,'-') AS vigencia,
       IFNULL(cer.fecInspeccion,'-') AS fecInspeccion,
       IFNULL(cer.fecVencimiento,'-') AS fecVencimiento,
       IFNULL(cer.cIdentidadCert,'-') AS cIdentidadCert,
       IFNULL(cer.codLocal,'-') AS codLocal,
       IFNULL(cer.ubigeo,'-') AS ubigeo,
       IFNULL(cer.idTarjeta,'-') AS idTarjeta,
       /*TARJETA DE PROPIEDAD*/
       IFNULL(tp.placa,'-') as placa,
       IFNULL(tp.ntarjeta,'-') as ntarjeta,
       IFNULL(tp.razon1,'-') as razon1,
       IFNULL(tp.domicilio,'-') as domicilio,
       IFNULL(tp.idclase,'-') as idclase,
       IFNULL(tp.idmarca,'-') as idmarca,
       IFNULL(tp.fabricacion,'-') as fabricacion,
       IFNULL(tp.idmodelo,'-') as idmodelo,
       IFNULL(tp.version,'-') as version,
       IFNULL(tp.idcombustible,'-') as idcombustible,
       IFNULL(tp.idcarroceria,'-') as idcarroceria,
       IFNULL(tp.ejes,'-') as ejes,
       IFNULL(tp.colores,'-') as colores,
       IFNULL(tp.nmotor,'-') as nmotor,
       IFNULL(tp.cilindros,'-') as cilindros,
       IFNULL(tp.nserie,'-') as nserie,
       IFNULL(tp.vin,'-') as vin,
       IFNULL(tp.ruedas,'-') as ruedas,
       IFNULL(tp.pasajeros,'-') as pasajeros,
       IFNULL(tp.asientos,'-') as asientos,
       IFNULL(tp.peso_seco,'-') as peso_seco,
       IFNULL(tp.peso_bruto,'-') as peso_bruto,
       IFNULL(tp.longitud,'-') as longitud,
       IFNULL(tp.altura,'-') as altura,
       IFNULL(tp.ancho,'-') as ancho,
       IFNULL(tp.carga_util,'-') as carga_util,
       IFNULL(tp.estado,'-') as estado,
       IFNULL(tp.fecha,'-') as fecha,
       IFNULL(tp.nruedas,'-') as nruedas,
       IFNULL(tp.kilometraje,'-') as kilometraje,
       /*RESULTADOS*/
       IFNULL(r.PRUEALI,0)  as PRUEALI,
       IFNULL(r.PROFNEUMA,0)  as PROFNEUMA,
       IFNULL(r.PRUEBLUCES,0)  as PRUEBLUCES,
       IFNULL(r.SUSPENSION,0)  as SUSPENSION,
       IFNULL(r.EMIGASES,0)  as EMIGASES,
       IFNULL(r.FRESERV,0)  as FRESERV,
       IFNULL(r.FREESTAC,0) as  FREESTAC,
       IFNULL(r.FREEMER,0) as  FREEMER,
       IFNULL(CAST(r.DISEJES AS CHAR),0) as DISEJES,
       IFNULL(CAST(r.PISOS AS CHAR),0) as PISOS
from certificado cer
left join tarjetapropiedad tp on tp.id_tarjeta=cer.idtarjeta
left join resultados r on r.IDCERTIFICADO=cer.idCertificado;

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
