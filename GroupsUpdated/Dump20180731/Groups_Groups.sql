-- MySQL dump 10.13  Distrib 5.7.22, for Linux (x86_64)
--
-- Host: localhost    Database: Groups
-- ------------------------------------------------------
-- Server version	5.7.22-0ubuntu0.16.04.1

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
-- Table structure for table `Groups`
--

DROP TABLE IF EXISTS `Groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Groups` (
  `gid` int(11) NOT NULL AUTO_INCREMENT,
  `GroupName` varchar(45) NOT NULL,
  `Owner` varchar(45) DEFAULT NULL,
  `SendAs` varchar(45) DEFAULT NULL,
  `Feedback` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`gid`),
  UNIQUE KEY `Owner_UNIQUE` (`Owner`),
  UNIQUE KEY `SendAs_UNIQUE` (`SendAs`)
) ENGINE=InnoDB AUTO_INCREMENT=159 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Groups`
--

LOCK TABLES `Groups` WRITE;
/*!40000 ALTER TABLE `Groups` DISABLE KEYS */;
INSERT INTO `Groups` VALUES (46,'Management','Ramesh','ramesh@gmail.com','YES'),(72,'Sales','Jai','jai@gmail.com','YES'),(97,'Engg','Choudary','sonu@gmail.com','YES'),(98,'Sales','Rajas','naga1@gmail.com','YES'),(99,'Management','Naresh','nareshh@gmail.com','YES'),(102,'Marketing','Nagesh','nagesh@gmail.com','YES'),(104,'Sales','Rajaededd','srajawe@gmail.com','NO'),(105,'Engg','Nagaa','nagaa@gmail.com','YES'),(107,'Enggneering','Rajesh','rajesh@gmail.com','YES'),(108,'Support','Aakash','aakash@gmail.com','YES'),(110,'Enggineering','Rajuu','rajua@gmail.com','YES'),(112,'Management','Divyaa','div@gmail.com','YES'),(116,'Engg','Shekhar','shekar@gmail.com','YES'),(124,'Sales','Nagarajus','nagas@gmail.com','YES'),(147,'Engg','Rajass','srajssa@gmail.com','YES'),(151,'Sales','Rameshh','rameshh@gmail.com','YES'),(155,'Engg','Raja','sraja@gmail.com',NULL),(158,'Engg','Madhusudan','madhusudan@gmail.com','YES');
/*!40000 ALTER TABLE `Groups` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-31 18:45:01
