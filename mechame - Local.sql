CREATE DATABASE  IF NOT EXISTS `mechame` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `mechame`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: mechame
-- ------------------------------------------------------
-- Server version	5.1.30-community

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
-- Table structure for table `lembrete`
--

DROP TABLE IF EXISTS `lembrete`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lembrete` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `telefone` bigint(20) NOT NULL,
  `titulo` varchar(150) NOT NULL,
  `raio` int(11) NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `ativo` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `FK_LembreteUsuario_idx` (`telefone`),
  CONSTRAINT `FK_LembreteUsuario` FOREIGN KEY (`telefone`) REFERENCES `usuario` (`telefone`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lembrete`
--

LOCK TABLES `lembrete` WRITE;
/*!40000 ALTER TABLE `lembrete` DISABLE KEYS */;
INSERT INTO `lembrete` VALUES (1,5511987654321,'Teste lembrete',10,20,31,1),(8,5511987654321,'Lembrete XPTO 2',20,20.1524,-15.5255,1),(9,5512121212121,'Lembrete Teste',30,10.1524,-19.5255,1),(11,5532323232323,'Lembrete do lero lero',80,49.4,-49.55,1),(15,5511987412123,'Lembrete Academia',20,20.15,-15.55,1),(16,5511987412123,'Lembrete Academia',20,20.15,-15.55,1);
/*!40000 ALTER TABLE `lembrete` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_acesso`
--

DROP TABLE IF EXISTS `log_acesso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_acesso` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_log` datetime NOT NULL,
  `telefone` bigint(20) NOT NULL,
  `descricao` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_acesso`
--

LOCK TABLES `log_acesso` WRITE;
/*!40000 ALTER TABLE `log_acesso` DISABLE KEYS */;
/*!40000 ALTER TABLE `log_acesso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `telefone` bigint(20) NOT NULL,
  `nome` varchar(150) NOT NULL,
  `email` varchar(150) NOT NULL,
  `senha` varchar(30) NOT NULL,
  PRIMARY KEY (`telefone`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (5511987412123,'Nome 22','email12@email.com.br','senha123321'),(5511987412157,'Nome 2','email@email.com.br','senha321'),(5511987654234,'Nome124','email21@email.com','senha112323'),(5511987654321,'Nome','email@email.com','senha123'),(5512121212121,'Nome Teste asdf','email12dealmeitda@email.com.br','asdfseasdfnha123321'),(5532323232323,'Lero lero com','email21xpto@email.com','senhasdfaasdf112323');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-06-06  1:23:58
