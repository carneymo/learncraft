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

/*Table structure for table `identikey_users` */

DROP TABLE IF EXISTS `identikey_users`;

CREATE TABLE `identikey_users` (
  `identikey` varchar(8) NOT NULL COMMENT 'CU Identikey',
  `username` varchar(150) DEFAULT NULL COMMENT 'Username if different from Identikey',
  PRIMARY KEY (`identikey`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `spec_items` */

DROP TABLE IF EXISTS `spec_items`;

CREATE TABLE `spec_items` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `username` varchar(25) NOT NULL,
  `itemid` int(255) NOT NULL,
  `quantity` int(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Table structure for table `users_log` */

DROP TABLE IF EXISTS `users_log`;

CREATE TABLE `users_log` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `date_logged` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=latin1;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
