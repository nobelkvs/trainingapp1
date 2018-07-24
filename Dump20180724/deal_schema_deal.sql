-- MySQL dump 10.13  Distrib 5.7.22, for Linux (x86_64)
--
-- Host: localhost    Database: deal_schema
-- ------------------------------------------------------
-- Server version	5.7.22-0ubuntu18.04.1

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
-- Table structure for table `deal`
--

DROP TABLE IF EXISTS `deal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `deal` (
  `deal_id` int(11) NOT NULL AUTO_INCREMENT,
  `deal_name` varchar(45) NOT NULL,
  `owner_name` varchar(45) DEFAULT NULL,
  `deal_value` varchar(20) DEFAULT NULL,
  `probability` int(11) DEFAULT NULL,
  `customer_name` varchar(45) DEFAULT NULL,
  `customer_contact` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`deal_id`)
) ENGINE=InnoDB AUTO_INCREMENT=172 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deal`
--

LOCK TABLES `deal` WRITE;
/*!40000 ALTER TABLE `deal` DISABLE KEYS */;
INSERT INTO `deal` VALUES (1,'aparna','abhinav','99L',99,'uma','5426'),(123,'nidhi','cghn','67',67,'xfg','45645'),(130,'karuna','sashmitha','1C',40,'naveen choudhary','7584236984'),(131,'tom','sowmya','65',50,'kathy','1475963258'),(132,'karuna','sowmya','55',55,'kathy','1475963258'),(133,'tom','sowmya','65',30,'john','1247859647'),(136,'sharon','sam','46',10,'kathy','1247859647'),(144,'Aradhya','sowmya','65',50,'niketha','1247859647'),(145,'sharon','sashmitha','10',50,'kathy','1247859647'),(146,'Sree','gv','65',30,'kathy','1247859647'),(147,'srinidhi','sam','10',10,'john','1247859647'),(148,'tom','tim','65',62,'naveen choudhary','7584236984'),(150,'nidhi','tara','45L',20,'druthi','1578359648'),(153,'nidhi','tara','45L',20,'druthi','1578359648'),(154,'tom','sashmitha','70L',30,'kathy','1247859647'),(157,'sharon','tim','65',30,'niketha','1247859647'),(158,'tom','tim','10',50,'niketha','7584236984'),(159,'nidhi','tara','45L',20,'druthi','1578359648'),(160,'sharon','tim','70',50,'kathy','7584236984'),(161,'sharon','sam','10',40,'kathy','7584236984'),(162,'tom','tim','50',30,'niketha','1247859647'),(163,'nidhi','tara','45L',20,'druthi','1578359648'),(164,'nidhi','tara','45L',20,'druthi','1578359648'),(165,'nidhi','tara','45L',20,'druthi','1578359648'),(166,'nidhi','tara','45L',20,'druthi','1578359648'),(169,'karuna','sashmitha','70',30,'niketha','7584236984'),(170,'nidhi','tara','45L',20,'druthi','1578359648'),(171,'nidhi','tara','45L',20,'druthi','1578359648');
/*!40000 ALTER TABLE `deal` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-24 17:39:41
