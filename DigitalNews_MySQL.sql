CREATE DATABASE  IF NOT EXISTS `digitalnews` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `digitalnews`;
-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: digitalnews
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `role` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'admin','123',1),(2,'admin2','123',1);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `news` (
  `id` int NOT NULL AUTO_INCREMENT,
  `newsTitle` longtext,
  `newsContent` longtext,
  `newsType` int DEFAULT NULL,
  `author` int DEFAULT NULL,
  `createDate` date DEFAULT NULL,
  `image` longtext,
  PRIMARY KEY (`id`),
  KEY `FK_News_Account` (`author`),
  KEY `FK_News_NewsType` (`newsType`),
  CONSTRAINT `FK_News_Account` FOREIGN KEY (`author`) REFERENCES `account` (`id`),
  CONSTRAINT `FK_News_NewsType` FOREIGN KEY (`newsType`) REFERENCES `newstype` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
INSERT INTO `news` VALUES (1,'Test1','<h2>content</h2>',3,1,'2023-10-21','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRjLptXP23bZAee0uji_hR_E6S8dADHJc11wgfWaBjRcTZJzXFXMzsUP1Lcs2gz1DPqmks&usqp=CAU'),(2,'hih','<p>conetn</p>',2,1,'2023-10-21','https://www.adobe.com/express/create/thumbnail/media_184a3a28ded5926b56142bf7f41b1c6972df38f0c.png?width=750&format=png&optimize=medium'),(4,'test','<p>dsfdsfsgs</p>',5,1,'2023-10-21','https://marketplace.canva.com/EAFf5rfnPgA/1/0/800w/canva-blue-modern-eye-catching-vlog-youtube-thumbnail-XTJTyike0CE.jpg'),(5,'Hihihihii','<p>abc</p>',1,1,'2023-10-21','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ2cm5lsbYJRN45qmUotUJ1NGIajXtksGrCozhvM3w5P5gi5DMhefrtXRhpsRj9iT5jQTQ&usqp=CAU'),(6,'123','<p>czdc</p>',6,1,'2023-10-21','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRbFCURuJZr0pVpmhA6OPAAfFbolJdPVRix2EZbhWraF4rCfzWwiiWYpcnXsJTd-AimoII&usqp=CAU'),(7,'456','<p>dasds</p>',6,1,'2023-10-21','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRGizoTxbJGbAXNVLD5coLE6hd71TzuG6k2aGhmfitUWpacBlKAWwCMG_fdjr2gXbsGPeg&usqp=CAU'),(8,'789','<p>dsdfs</p>',4,1,'2023-10-21','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQwYTMHnfNebq3JIdkTlgjyA3UjhA3nWhJK4Q&usqp=CAU');
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `newstype`
--

DROP TABLE IF EXISTS `newstype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `newstype` (
  `id` int NOT NULL AUTO_INCREMENT,
  `newsType` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `newstype`
--

LOCK TABLES `newstype` WRITE;
/*!40000 ALTER TABLE `newstype` DISABLE KEYS */;
INSERT INTO `newstype` VALUES (1,'Sport'),(2,'Music'),(3,'Life'),(4,'Social'),(5,'Beauty & Fashion'),(6,'Technology');
/*!40000 ALTER TABLE `newstype` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-21 18:32:29
