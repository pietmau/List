-- MySQL dump 10.13  Distrib 8.0.15, for macos10.14 (x86_64)
--
-- Host: checklistappdatabase.cqe6ft379bmn.eu-west-1.rds.amazonaws.com    Database: travelchecklist
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `checklist_item`
--

DROP TABLE IF EXISTS `checklist_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `checklist_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `checked` tinyint(4) DEFAULT '0',
  `priority` int(11) DEFAULT '0',
  `description` varchar(45) DEFAULT NULL,
  `category_id` int(11) NOT NULL,
  `optional` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `title_UNIQUE` (`title`),
  KEY `category_key_idx` (`category_id`),
  CONSTRAINT `category_key` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=346 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checklist_item`
--

LOCK TABLES `checklist_item` WRITE;
/*!40000 ALTER TABLE `checklist_item` DISABLE KEYS */;
INSERT INTO `checklist_item` VALUES (1,'Renew/check passport',0,0,NULL,1,0),(4,'Reconfirm/check­in online with airline',0,0,NULL,1,0),(5,'Check flight info for airline & times',0,0,NULL,1,0),(6,'Check baggage weights',0,0,NULL,1,0),(7,'Personalize luggage',0,0,NULL,1,0),(8,'Insurance: Trip cancellation/medical',0,0,NULL,1,0),(9,'Change money',0,0,NULL,1,0),(45,'Pay bills/rent/utilities',0,0,NULL,9,0),(46,'Forward or hold mail/newspapers/magazines',0,0,NULL,9,0),(47,'Wash clothing',0,0,NULL,9,0),(48,'Empty fridge of perishables',0,0,NULL,9,0),(49,'Empty trash',0,0,NULL,9,0),(50,'Store valuables in a safe place',0,0,NULL,9,0),(51,'Turn off heater/air conditioner',0,0,NULL,9,0),(52,'Lock all doors and windows',0,0,NULL,9,0),(61,'Hepatitis A',0,0,NULL,11,0),(62,'Yellow fever',0,0,NULL,11,0),(63,'Polio',0,0,NULL,11,0),(64,'Typhoid',0,0,NULL,11,0),(81,'Passport',0,0,NULL,12,0),(82,'Visas',0,0,NULL,12,0),(83,'Personal identification',0,0,NULL,12,0),(84,'Drivers license',0,0,NULL,12,0),(85,'Hotel/hostel contact info',0,0,NULL,12,0),(86,'Vaccination certificate',0,0,NULL,12,0),(87,'Health insurance documents',0,0,NULL,12,0),(88,'Reservations & itineraries',0,0,NULL,12,0),(89,'Copies of tickets/passport/etc.',0,0,NULL,12,0),(90,'Student ID/FHA/Int\'l discount cards',0,0,NULL,12,0),(111,'Cash (Foreigh & local currency)',0,0,NULL,13,0),(112,'Emergency money',0,0,NULL,13,0),(113,'Credit card/debit card',0,0,NULL,13,0),(114,'Wallet',0,0,NULL,13,0),(183,'Backpack/Suitcase',0,0,NULL,15,0),(184,'Luggage tags',0,0,NULL,15,0),(185,'Kid\'s backpack/suitcase',0,0,NULL,15,0),(259,'Carry-on bag (small back­pack)',0,0,NULL,15,0),(297,'Bras',0,0,NULL,19,0),(298,'Underwear',0,0,NULL,19,0),(299,'Socks (thin cotton, thermal, wool)',0,0,NULL,19,0),(300,'Pajamas, sleepwear',0,0,NULL,19,0),(301,'Long/thermal underwear',0,0,NULL,19,0),(340,'Check local destination weather/news',0,0,NULL,1,0),(341,'Airline tickets',0,0,NULL,14,0),(342,'Book hotel/hostel',0,0,NULL,14,0),(343,'Polos / T­Shirts / Casual shirts',0,0,NULL,100,0),(344,'Jeans / Casual pants',0,0,NULL,100,0),(345,'Tank tops',0,0,NULL,100,0);
/*!40000 ALTER TABLE `checklist_item` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-28 16:06:33
