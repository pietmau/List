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
-- Table structure for table `tags_to_items`
--

DROP TABLE IF EXISTS `tags_to_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tags_to_items` (
  `tag_id` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL,
  `tag_title` varchar(45) NOT NULL,
  `item_title` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `tags_idx` (`tag_id`,`tag_title`),
  KEY `items_idx` (`item_id`,`item_title`),
  KEY `ddd_idx` (`tag_title`),
  CONSTRAINT `ddd` FOREIGN KEY (`tag_title`) REFERENCES `tags` (`title`),
  CONSTRAINT `fffff` FOREIGN KEY (`item_id`) REFERENCES `checklist_item` (`id`),
  CONSTRAINT `hhhh` FOREIGN KEY (`tag_title`) REFERENCES `tags` (`title`),
  CONSTRAINT `tags_key` FOREIGN KEY (`tag_id`) REFERENCES `tags` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tags_to_items`
--

LOCK TABLES `tags_to_items` WRITE;
/*!40000 ALTER TABLE `tags_to_items` DISABLE KEYS */;
INSERT INTO `tags_to_items` VALUES (2,2,297,'Fermale','Bras'),(20,4,184,'All','Luggage tags'),(20,18,183,'All','Backpack/Suitcase'),(20,19,259,'All','Carry­on bag (small back­pack)'),(21,20,1,'Abroad','Renew/check passport'),(21,21,4,'Abroad','Reconfirm/check­in online with airline'),(21,22,5,'Abroad','Check flight info for airline & times'),(21,23,6,'Abroad','Check baggage weights'),(20,24,7,'All','Personalize luggage'),(20,25,8,'All','Insurance: Trip cancellation/medical'),(21,27,9,'Abroad','Change money'),(20,31,340,'All','Check local destination weather/news'),(18,32,45,'Long','Pay bills/rent/utilities'),(18,33,46,'Long','Forward or hold mail/newspapers/magazines'),(20,34,47,'All','Wash clothing'),(20,35,48,'All','Empty fridge of perishables'),(20,36,49,'All','Empty trash'),(20,37,50,'All','Store valuables in a safe place'),(20,38,51,'All','Turn off heater/air conditioner'),(20,39,52,'All','Lock all doors and windows'),(21,40,81,'Abroad','Passport'),(21,41,82,'Abroad','Visas'),(20,42,83,'All','Personal identification'),(20,43,84,'All','Drivers license'),(7,46,85,'Hotel','Hotel/hostel contact info'),(8,47,85,'Hostel/guesthouse','Hotel/hostel contact info'),(21,48,86,'Abroad','Vaccination certificate'),(20,49,87,'All','Health insurance documents'),(20,50,88,'All','Reservations & itineraries'),(20,51,89,'All','Copies of tickets/passport/etc.'),(20,52,90,'All','Student ID/FHA/Int\'l discount cards'),(21,53,111,'Abroad','Cash (Foreigh & local currency)'),(20,55,112,'All','Emergency money'),(20,56,113,'All','Credit card/debit card'),(20,58,114,'All','Wallet'),(22,59,341,'Airplane','Airline tickets'),(7,60,342,'Hotel','Book hotel/hostel'),(8,61,342,'Hostel/guesthouse','Book hotel/hostel'),(22,63,184,'Airplane','Luggage tags'),(4,65,185,'Kids, Toddlers','Kid\'s backpack/suitcase'),(20,66,298,'All','Underwear'),(20,68,299,'All','Socks (thin cotton, thermal, wool)'),(20,69,300,'All','Pajamas, sleepwear'),(6,70,301,'Cold/rainy','Long/thermal underwear');
/*!40000 ALTER TABLE `tags_to_items` ENABLE KEYS */;
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

-- Dump completed on 2019-03-28 17:15:52
