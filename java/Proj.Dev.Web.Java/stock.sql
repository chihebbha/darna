-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.27-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema ds
--

CREATE DATABASE IF NOT EXISTS ds;
USE ds;

--
-- Definition of table `depot`
--

DROP TABLE IF EXISTS `depot`;
CREATE TABLE `depot` (
  `id_depot` int(10) unsigned NOT NULL auto_increment,
  `adresse_depot` varchar(45) NOT NULL,
  PRIMARY KEY  (`id_depot`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `depot`
--

/*!40000 ALTER TABLE `depot` DISABLE KEYS */;
INSERT INTO `depot` (`id_depot`,`adresse_depot`) VALUES 
 (1,'gazella'),
 (2,'esprit'),
 (3,'ariana'),
 (4,'sousse');
/*!40000 ALTER TABLE `depot` ENABLE KEYS */;


--
-- Definition of table `stock`
--

DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock` (
  `numero_stock` int(10) unsigned NOT NULL auto_increment,
  `type_vet_stock` varchar(45) NOT NULL,
  `nombre_articles` int(10) unsigned NOT NULL,
  `id_depot` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`numero_stock`),
  KEY `id_depot` (`id_depot`),
  CONSTRAINT `id_depot` FOREIGN KEY (`id_depot`) REFERENCES `depot` (`id_depot`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stock`
--

/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` (`numero_stock`,`type_vet_stock`,`nombre_articles`,`id_depot`) VALUES 
 (1,'Femme',1,2),
 (2,'Femme',1,2),
 (3,'Femme',7,3),
 (4,'Femme',10,1);
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
