/*
SQLyog Community v11.01 (64 bit)
MySQL - 5.5.24-log : Database - minecraft
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`minecraft` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `minecraft`;

/*Table structure for table `actions` */

DROP TABLE IF EXISTS `actions`;

CREATE TABLE `actions` (
  `actionid` int(255) NOT NULL AUTO_INCREMENT,
  `action` text NOT NULL,
  `repeat` tinyint(1) NOT NULL DEFAULT '0',
  `repeattimes` int(100) NOT NULL DEFAULT '0',
  `delay` int(25) DEFAULT NULL,
  `last_run` datetime DEFAULT NULL,
  PRIMARY KEY (`actionid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Table structure for table `identikey_users` */

DROP TABLE IF EXISTS `identikey_users`;

CREATE TABLE `identikey_users` (
  `identikey` varchar(8) NOT NULL COMMENT 'CU Identikey',
  `username` varchar(150) DEFAULT NULL COMMENT 'Username if different from Identikey',
  PRIMARY KEY (`identikey`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `logs` */

DROP TABLE IF EXISTS `logs`;

CREATE TABLE `logs` (
  `logid` int(255) NOT NULL AUTO_INCREMENT,
  `ipaddress` varchar(16) DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  `log_message` text NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `date_logged` datetime NOT NULL,
  PRIMARY KEY (`logid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `spec_items` */

DROP TABLE IF EXISTS `spec_items`;

CREATE TABLE `spec_items` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `username` varchar(25) NOT NULL,
  `itemid` int(255) NOT NULL,
  `quantity` int(255) NOT NULL,
  `itemstate` enum('granted','taken','removed') NOT NULL DEFAULT 'granted',
  `date_granted` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

/*Table structure for table `team_roster` */

DROP TABLE IF EXISTS `team_roster`;

CREATE TABLE `team_roster` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `username` varchar(25) NOT NULL,
  `teamname` varchar(255) NOT NULL,
  `hostname` varchar(255) NOT NULL,
  `points` int(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `email` varchar(255) NOT NULL,
  `permission` int(10) NOT NULL DEFAULT '0',
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `attempts` tinyint(1) NOT NULL DEFAULT '0',
  `member` varchar(255) DEFAULT NULL,
  `online` tinyint(1) NOT NULL DEFAULT '0',
  `points` int(255) NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `preferences` text,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `users_log` */

DROP TABLE IF EXISTS `users_log`;

CREATE TABLE `users_log` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `date_logged` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=234 DEFAULT CHARSET=latin1;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
