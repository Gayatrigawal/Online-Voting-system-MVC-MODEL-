CREATE DATABASE  IF NOT EXISTS `onlinevotesys` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `onlinevotesys`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: onlinevotesys
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `candidate`
--

DROP TABLE IF EXISTS `candidate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `candidate` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `login` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `mobileNo` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `partyid` bigint DEFAULT NULL,
  `partyName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidate`
--

LOCK TABLES `candidate` WRITE;
/*!40000 ALTER TABLE `candidate` DISABLE KEYS */;
INSERT INTO `candidate` VALUES (2,'Joshua','Johnson','kyhu@mailinator.com','Pa$$w0rd!','1999-07-15','8585855858','Male',3,'Congress'),(3,'Naida','Beasley','xoqahejyq@mailinator.com','Pa$$w0rd!','1999-02-11','8585855858','Male',3,'Congress'),(4,'Ullaaaaa','Hammond','lisawefa@mailinator.com','Pa$$w0rd!','1999-07-15','8585855858','Female',4,'XYz'),(5,'Aaron','Mcbride','modurajix@mailinator.com','Pa$$w0rd!','1997-07-15','8989899797','Male',1,'BJP');
/*!40000 ALTER TABLE `candidate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `election`
--

DROP TABLE IF EXISTS `election`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `election` (
  `id` bigint NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `election`
--

LOCK TABLES `election` WRITE;
/*!40000 ALTER TABLE `election` DISABLE KEYS */;
INSERT INTO `election` VALUES (1,'Panchayat Chunav','2023-04-12'),(2,'Janpat','2023-04-19');
/*!40000 ALTER TABLE `election` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `v_parties`
--

DROP TABLE IF EXISTS `v_parties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `v_parties` (
  `ID` bigint NOT NULL,
  `name` varchar(225) DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `image` longblob,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `v_parties`
--

LOCK TABLES `v_parties` WRITE;
/*!40000 ALTER TABLE `v_parties` DISABLE KEYS */;
/*!40000 ALTER TABLE `v_parties` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `v_user`
--

DROP TABLE IF EXISTS `v_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `v_user` (
  `ID` bigint NOT NULL,
  `firstName` varchar(225) DEFAULT NULL,
  `lastName` varchar(225) DEFAULT NULL,
  `login` varchar(225) DEFAULT NULL,
  `password` varchar(225) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `mobileNo` varchar(225) DEFAULT NULL,
  `roleId` bigint DEFAULT NULL,
  `gender` varchar(225) DEFAULT NULL,
  `roleName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `v_user`
--

LOCK TABLES `v_user` WRITE;
/*!40000 ALTER TABLE `v_user` DISABLE KEYS */;
INSERT INTO `v_user` VALUES (1,'Hari','om','Admin123@gmail.com','Pa$$w0rd!','1997-10-06','9165415598',1,'Male','ADMIN'),(2,'Keelie','Wilkins','zyzede@mailinator.com','Pa$$w0rd!','1997-07-15','Martina',2,'Male','EOfficer'),(3,'Debra','Hart','fobebalo@mailinator.com','Pa$$w0rd!','1998-06-22','Damian',3,'Male','Voter'),(4,'Lee','Cabrera','cilipereke@mailinator.com','Pa$$w0rd!','1998-07-15','8987989879',3,'Female','Voter'),(5,'Zephr','Rivas','ruji@mailinator.com','Pa$$w0rd!','1999-07-15','8989899797',3,'Male','Voter');
/*!40000 ALTER TABLE `v_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `v_vote`
--

DROP TABLE IF EXISTS `v_vote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `v_vote` (
  `ID` bigint NOT NULL,
  `partyId` bigint DEFAULT NULL,
  `partyName` varchar(225) DEFAULT NULL,
  `voterId` varchar(225) DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `v_vote`
--

LOCK TABLES `v_vote` WRITE;
/*!40000 ALTER TABLE `v_vote` DISABLE KEYS */;
INSERT INTO `v_vote` VALUES (1,1,'BJP','5522552','Shubham@gmail.com','Shubham@gmail.com','2023-07-19 10:23:14','2023-07-19 10:23:14'),(2,3,'Congress','787878787','Murphy@123gmail.com','Murphy@123gmail.com','2023-07-19 10:23:44','2023-07-19 10:23:44'),(3,1,'BJP','9977676','Brenda@23gmail.com','Brenda@23gmail.com','2023-07-19 10:25:21','2023-07-19 10:25:21');
/*!40000 ALTER TABLE `v_vote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vote`
--

DROP TABLE IF EXISTS `vote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vote` (
  `id` bigint NOT NULL,
  `voteid` bigint DEFAULT NULL,
  `voterName` varchar(45) DEFAULT NULL,
  `electionName` varchar(45) DEFAULT NULL,
  `userid` bigint DEFAULT NULL,
  `candidateName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vote`
--

LOCK TABLES `vote` WRITE;
/*!40000 ALTER TABLE `vote` DISABLE KEYS */;
INSERT INTO `vote` VALUES (1,10201,'Lee',NULL,4,'Joshua'),(2,10202,'Debra',NULL,3,'Ullaaaaa'),(3,10203,'Zephr',NULL,5,'Joshua');
/*!40000 ALTER TABLE `vote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voterapplication`
--

DROP TABLE IF EXISTS `voterapplication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `voterapplication` (
  `id` bigint NOT NULL,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `login` varchar(45) DEFAULT NULL,
  `mobileNo` varchar(45) DEFAULT NULL,
  `userid` bigint DEFAULT NULL,
  `idProof` longblob,
  `voterid` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voterapplication`
--

LOCK TABLES `voterapplication` WRITE;
/*!40000 ALTER TABLE `voterapplication` DISABLE KEYS */;
/*!40000 ALTER TABLE `voterapplication` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-04 13:40:20
