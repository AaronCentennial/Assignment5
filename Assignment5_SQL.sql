-- MySQL dump Distrib MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: assignment5	User: Aaron 	Password: aaaaaa
-- -------------------------------------------------------------------------------
-- Server version	MariaDB-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Game`
--

DROP TABLE IF EXISTS `Game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Game` (
  `game_id` int(11) NOT NULL AUTO_INCREMENT,
  `game_title` varchar(115) DEFAULT NULL,
  PRIMARY KEY (`game_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Game`
--

LOCK TABLES `Game` WRITE;
/*!40000 ALTER TABLE `Game` DISABLE KEYS */;
INSERT INTO `Game` VALUES (1,'John Lee\'s COMP 305 Assignment 3'),(2,'Franco\'s  COMP 305 Assignment 3'),(3,'Seth\'s COMP 305 Assignment 3'),(4,'Jason Assignment 3'),(5,'Counter Strike'),(6,'Mario Kart');
/*!40000 ALTER TABLE `Game` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Player`
--

DROP TABLE IF EXISTS `Player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Player` (
  `player_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `postal_code` varchar(8) DEFAULT NULL,
  `province` varchar(45) DEFAULT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`player_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Player`
--

LOCK TABLES `Player` WRITE;
/*!40000 ALTER TABLE `Player` DISABLE KEYS */;
INSERT INTO `Player` VALUES (1,'Aaron3','None','123 Fake St','M1E 3F4','ON','666-444-3343'),(2,'Kevin','Killgrave','25 Jones St','T4D 9EW','BC','945-332-2343'),(3,'Matt','Murdock','616 3rd St','E3Q 8E3','PEI','987-666-6767'),(4,'Clark','Kent','405 Queen St','E5Q 8Y6','ON','616-443-6655'),(6,'Dat','Ass','q','q','q','455-887-5584'),(7,'Test2','ASfsdf','55 Hotdog St','J3Q 3Q2','BC','747-885-6654'),(8,'Jessica','Jones','58 jewel St ','M1R 3Q3','Hell\'s Kitchen','444-555-8585');
/*!40000 ALTER TABLE `Player` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PlayerAndGame`
--

DROP TABLE IF EXISTS `PlayerAndGame`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PlayerAndGame` (
  `player_game_id` int(11) NOT NULL AUTO_INCREMENT,
  `game_id` int(11) DEFAULT NULL,
  `player_id` int(11) NOT NULL,
  `playing_date` date DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  PRIMARY KEY (`player_game_id`),
  KEY `game_id` (`game_id`),
  KEY `player_id` (`player_id`),
  CONSTRAINT `PlayerAndGame_ibfk_1` FOREIGN KEY (`game_id`) REFERENCES `Game` (`game_id`),
  CONSTRAINT `PlayerAndGame_ibfk_2` FOREIGN KEY (`player_id`) REFERENCES `Player` (`player_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PlayerAndGame`
--

LOCK TABLES `PlayerAndGame` WRITE;
/*!40000 ALTER TABLE `PlayerAndGame` DISABLE KEYS */;
INSERT INTO `PlayerAndGame` VALUES (1,2,4,'2015-12-05',50),(2,5,1,'2015-09-28',32),(3,2,2,'2015-11-09',82),(4,4,3,'2015-12-01',50),(5,3,1,'2015-11-16',50),(6,4,1,'2015-11-25',22),(7,2,1,'2015-11-24',52),(8,1,1,'2015-11-25',50),(9,4,2,'2015-11-30',0),(10,3,2,'2015-12-01',0),(11,4,2,'2015-12-17',0),(12,4,6,'2015-12-01',800),(13,2,6,'2015-12-01',45),(14,4,6,'2015-12-15',65),(15,5,6,'2015-12-17',75),(16,3,7,'2015-12-14',74),(17,2,4,'2015-12-15',0),(18,1,4,'2015-12-10',0),(19,1,3,'2015-11-30',0),(20,4,7,'2015-11-09',455),(21,1,7,'2015-12-07',985);
/*!40000 ALTER TABLE `PlayerAndGame` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
