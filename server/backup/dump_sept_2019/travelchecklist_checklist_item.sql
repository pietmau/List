CREATE DATABASE  IF NOT EXISTS `travelchecklist` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `travelchecklist`;
-- MySQL dump 10.13  Distrib 8.0.16, for macos10.14 (x86_64)
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
  `priority` int(11) DEFAULT '0',
  `description` varchar(45) DEFAULT NULL,
  `category_id` int(11) NOT NULL,
  `optional` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `title_UNIQUE` (`title`),
  KEY `category_key_idx` (`category_id`),
  CONSTRAINT `category_key` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=550 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checklist_item`
--

LOCK TABLES `checklist_item` WRITE;
/*!40000 ALTER TABLE `checklist_item` DISABLE KEYS */;
INSERT INTO `checklist_item` VALUES (1,'Renew/check passport',0,NULL,1,0),(4,'Reconfirm/check­in online with airline',0,NULL,1,0),(5,'Check flight info for airline & times',0,NULL,1,0),(6,'Check baggage weights',0,NULL,1,0),(7,'Personalize luggage',0,NULL,1,0),(8,'Insurance: Trip cancellation/medical',0,NULL,1,0),(9,'Change money',0,NULL,1,0),(45,'Pay bills/rent/utilities',0,NULL,9,0),(46,'Forward or hold mail/newspapers/magazines',0,NULL,9,0),(47,'Wash clothing',0,NULL,9,0),(48,'Empty fridge of perishables',0,NULL,9,0),(49,'Empty trash',0,NULL,9,0),(50,'Store valuables in a safe place',0,NULL,9,0),(51,'Turn off heater/air conditioner',0,NULL,9,0),(52,'Lock all doors and windows',0,NULL,9,0),(61,'Hepatitis A',0,NULL,11,0),(62,'Yellow fever',0,NULL,11,0),(63,'Polio',0,NULL,11,0),(64,'Typhoid',0,NULL,11,0),(81,'Passport',0,NULL,12,0),(82,'Visas',0,NULL,12,0),(83,'Personal identification',0,NULL,12,0),(84,'Drivers license',0,NULL,12,0),(85,'Hotel/hostel contact info',0,NULL,12,0),(86,'Vaccination certificate',0,NULL,12,0),(87,'Health insurance documents',0,NULL,12,0),(88,'Reservations & itineraries',0,NULL,12,0),(89,'Copies of tickets/passport/etc.',0,NULL,12,0),(90,'Student ID/FHA/Int\'l discount cards',0,NULL,12,0),(111,'Cash (Foreigh & local currency)',0,NULL,13,0),(112,'Emergency money',0,NULL,13,0),(113,'Credit card/debit card',0,NULL,13,0),(114,'Wallet',0,NULL,13,0),(183,'Backpack/Suitcase',0,NULL,15,0),(184,'Luggage tags',0,NULL,15,0),(185,'Kid\'s backpack/suitcase',0,NULL,15,0),(259,'Carry-on bag (small back­pack)',0,NULL,15,0),(297,'Bras',0,NULL,19,0),(298,'Underwear',0,NULL,19,0),(299,'Socks (thin cotton, thermal, wool)',0,NULL,19,0),(300,'Pajamas, sleepwear',0,NULL,19,0),(301,'Long/thermal underwear',0,NULL,19,0),(340,'Check local destination weather/news',0,NULL,1,0),(341,'Airline tickets',0,NULL,14,0),(342,'Book hotel/hostel',0,NULL,14,0),(343,'Polos / T­Shirts / Casual shirts',0,NULL,100,0),(344,'Jeans / Casual pants',0,NULL,100,0),(345,'Tank tops',0,NULL,100,0),(347,'Short sleeve shirts',0,NULL,100,0),(350,'Raincoat',0,NULL,21,0),(351,'Scarf/neckwarmer',0,NULL,21,0),(352,'Gloves/mittens',0,NULL,21,0),(354,'Cap/Sun hat',0,NULL,21,0),(355,'Warm hat',0,NULL,21,0),(356,'Sweaters',0,NULL,22,0),(357,'Long sleeve dress shirts',0,NULL,22,0),(358,'Dress pants',0,NULL,22,0),(359,'Dresses',0,NULL,22,0),(360,'Walking shoes/Sneakers',0,NULL,23,0),(361,'Dress shoes',0,NULL,23,0),(362,'Hiking boots',0,NULL,23,0),(363,'Dress sandals',0,NULL,23,0),(364,'Heels',0,NULL,23,0),(365,'Flip­flops/slippers',0,NULL,23,0),(366,'Belt',0,NULL,24,0),(367,'Sunglasses/case/strap',0,NULL,24,0),(368,'Watch',0,NULL,24,0),(369,'Compact/micro umbrella',0,NULL,24,0),(371,'Baby formula, baby bottles',0,NULL,25,0),(372,'Diaper bag, Diapers',0,NULL,25,0),(373,'Diaper cream',0,NULL,25,0),(374,'Pacifiers',0,NULL,25,0),(375,'Child carrier',0,NULL,25,0),(376,'Onesie',0,NULL,25,0),(378,'Baby shoes/sandals',0,NULL,25,0),(379,'Toys, books',0,NULL,25,0),(380,'Baby blanket',0,NULL,25,0),(381,'Bib',0,NULL,25,0),(382,'Toothbrush / Toothpaste',0,NULL,26,0),(383,'Deodorant',0,NULL,26,0),(384,'Shampoo/conditioner',0,NULL,26,0),(385,'Soap/cleansers',0,NULL,26,0),(386,'Towel/washcloth',0,NULL,26,0),(387,'Tissues/toilet paper',0,NULL,26,0),(388,'Feminine hygiene, tampons/pads',0,NULL,26,0),(389,'Toiletry bag',0,NULL,26,0),(390,'Hair brush/comb',0,NULL,26,0),(391,'Sunblock',0,NULL,26,0),(392,'Baby wipes (Towelettes)',0,NULL,26,0),(393,'Shaving gel/foam',0,NULL,26,0),(394,'Razor/electric shaver',0,NULL,26,0),(396,'Cotton swabs (Q­Tips)',0,NULL,26,0),(398,'Nail clippers',0,NULL,26,0),(399,'Dental floss',0,NULL,26,0),(400,'Perfume/Aftershave',0,NULL,26,0),(401,'Make­up',0,NULL,26,0),(402,'Makeup remover',0,NULL,26,0),(403,'Mirror',0,NULL,26,0),(404,'Lip balm',0,NULL,26,0),(405,'Pain/aspirin medication',0,NULL,27,0),(406,'Prescription medicine',0,NULL,27,0),(407,'Insect repellent',0,NULL,27,0),(408,'Hand sanitizer',0,NULL,27,0),(409,'First aid kit',0,NULL,27,0),(410,'Band­aids',0,NULL,27,0),(411,'Diarrhea medicine',0,NULL,27,0),(412,'Sunburn relief',0,NULL,27,0),(413,'Iodine',0,NULL,27,0),(415,'Laundry bag',0,NULL,28,0),(417,'Laundry detergent',0,NULL,28,0),(418,'Laundry brush',0,NULL,28,0),(421,'Electric Converters',0,NULL,30,0),(422,'Cell phone (+charger)',0,NULL,30,0),(423,'Digital camera (+charger)',0,NULL,31,0),(424,'Swim trunks / briefs',0,NULL,32,0),(425,'Swimsuit / bikini',0,NULL,32,0),(426,'Kids\' swimwear',0,NULL,32,0),(427,'Water safety devices for kids',0,NULL,32,0),(428,'Swimsuit coverup or sarong',0,NULL,32,0),(429,'Beach towel',0,NULL,32,0),(430,'Diving certificate',0,NULL,33,0),(431,'Diving log',0,NULL,33,0),(432,'Fishing License',0,NULL,34,0),(433,'Fishing rod/reel',0,NULL,34,0),(434,'Hooks',0,NULL,34,0),(436,'Camp stove without fuel',0,NULL,35,0),(437,'Tent',0,NULL,35,0),(438,'Water proof matches / fire starters',0,NULL,35,0),(439,'Eating utensils (cutlery)',0,NULL,35,0),(440,'Drinking cup',0,NULL,35,0),(441,'Plates / bowls',0,NULL,35,0),(442,'Pot / frying pan / cook set',0,NULL,35,0),(443,'Dish soap',0,NULL,35,0),(444,'Charcoal/firewood/buddy burner',0,NULL,35,0),(445,'Seasonings/sugar/condiments',0,NULL,35,0),(446,'Paper towels',0,NULL,35,0),(447,'Trash bags',0,NULL,35,0),(448,'Waterproof ski jacket',0,NULL,36,0),(449,'Waterproof ski pants',0,NULL,36,0),(450,'Waterproof socks',0,NULL,36,0),(451,'Ski gloves',0,NULL,36,0),(452,'Ski helmet, ski hat, or beanie',0,NULL,36,0),(453,'Ski boots',0,NULL,36,0),(460,'Carry­on bag (small back­pack)',0,NULL,15,0),(503,'Panty hose',0,NULL,19,0);
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

-- Dump completed on 2019-09-15 18:40:15
