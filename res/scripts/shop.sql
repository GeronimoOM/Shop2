-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: sabaik6fx8he7pua.chr7pe7iynqr.eu-west-1.rds.amazonaws.com    Database: vqhw9t4apel93gdk
-- ------------------------------------------------------
-- Server version	5.7.11-log

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
-- Table structure for table `sh_departments`
--

DROP TABLE IF EXISTS `sh_departments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sh_departments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sh_employees`
--

DROP TABLE IF EXISTS `sh_employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sh_employees` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `salary` int(11) NOT NULL,
  `department_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `department_id` (`department_id`),
  CONSTRAINT `fk_employees_department_id` FOREIGN KEY (`department_id`) REFERENCES `sh_departments` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sh_items`
--

DROP TABLE IF EXISTS `sh_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sh_items` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `price` decimal(12,2) NOT NULL,
  `amount` int(11) NOT NULL,
  `min_amount` int(11) NOT NULL,
  `department_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `department_id` (`department_id`),
  CONSTRAINT `fk_items_department_id` FOREIGN KEY (`department_id`) REFERENCES `sh_departments` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sh_orders`
--

DROP TABLE IF EXISTS `sh_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sh_orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `supplier_id` bigint(20) NOT NULL,
  `item_id` bigint(20) NOT NULL,
  `amount` int(11) NOT NULL,
  `employee_id` bigint(20) NOT NULL,
  `supply_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `supplier_id` (`supplier_id`,`employee_id`,`supply_id`),
  KEY `fk_orders_employee_id` (`employee_id`),
  KEY `fk_orders_supply_id` (`supply_id`),
  KEY `fk_orders_item_id_idx` (`item_id`),
  CONSTRAINT `fk_orders_employee_id` FOREIGN KEY (`employee_id`) REFERENCES `sh_employees` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_orders_supplier_id` FOREIGN KEY (`supplier_id`) REFERENCES `sh_suppliers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_orders_supply_id` FOREIGN KEY (`supply_id`) REFERENCES `sh_supplies` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sh_purchases`
--

DROP TABLE IF EXISTS `sh_purchases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sh_purchases` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `employee_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `employee_id` (`employee_id`),
  CONSTRAINT `fk_purchases_employee_id` FOREIGN KEY (`employee_id`) REFERENCES `sh_employees` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sh_purchases_items`
--

DROP TABLE IF EXISTS `sh_purchases_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sh_purchases_items` (
  `purchase_id` bigint(20) NOT NULL,
  `item_id` bigint(20) NOT NULL,
  `amount` int(11) NOT NULL,
  PRIMARY KEY (`purchase_id`,`item_id`),
  KEY `fk_purchases_items_item_id` (`item_id`),
  CONSTRAINT `fk_purchases_items_item_id` FOREIGN KEY (`item_id`) REFERENCES `sh_items` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_purchases_items_purchase_id` FOREIGN KEY (`purchase_id`) REFERENCES `sh_purchases` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sh_suppliers`
--

DROP TABLE IF EXISTS `sh_suppliers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sh_suppliers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sh_suppliers_items`
--

DROP TABLE IF EXISTS `sh_suppliers_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sh_suppliers_items` (
  `supplier_id` bigint(20) NOT NULL,
  `item_id` bigint(20) NOT NULL,
  PRIMARY KEY (`supplier_id`,`item_id`),
  KEY `fk_suppliers_items_item_id` (`item_id`),
  CONSTRAINT `fk_suppliers_items_item_id` FOREIGN KEY (`item_id`) REFERENCES `sh_items` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_suppliers_items_supplier_id` FOREIGN KEY (`supplier_id`) REFERENCES `sh_suppliers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sh_supplies`
--

DROP TABLE IF EXISTS `sh_supplies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sh_supplies` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `supplier_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `supplier_id` (`supplier_id`),
  CONSTRAINT `fk_supplies_supplier_id` FOREIGN KEY (`supplier_id`) REFERENCES `sh_suppliers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sh_supplies_items`
--

DROP TABLE IF EXISTS `sh_supplies_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sh_supplies_items` (
  `supply_id` bigint(20) NOT NULL,
  `item_id` bigint(20) NOT NULL,
  `amount` int(11) NOT NULL,
  `price` decimal(12,2) NOT NULL,
  PRIMARY KEY (`supply_id`,`item_id`),
  KEY `fk_supplies_items_item_id` (`item_id`),
  CONSTRAINT `fk_supplies_items_item_id` FOREIGN KEY (`item_id`) REFERENCES `sh_items` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_supplies_items_supply_id` FOREIGN KEY (`supply_id`) REFERENCES `sh_supplies` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-03 20:01:27
