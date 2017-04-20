CREATE DATABASE  IF NOT EXISTS `evm` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `evm`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 10.44.26.19    Database: evm
-- ------------------------------------------------------
-- Server version	5.7.9

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
-- Dumping data for table `qr_cfg_func_service`
--

LOCK TABLES `qr_cfg_func_service` WRITE;
/*!40000 ALTER TABLE `qr_cfg_func_service` DISABLE KEYS */;
INSERT INTO `qr_cfg_func_service` VALUES (100,100100,30003),(100,100200,30003),(100,100201,30003),(100,100202,30003),(100,100203,30003),(100,100205,30003),(100,100206,30003),(100,100300,30003),(100,100301,30003),(100,100302,30003),(100,100303,30003),(100,100312,30003),(100,300001,30001),(100,300002,30002),(100,310205,30004),(100,320212,60001),(100,320213,60001),(100,320214,60001),(200,100100,40003),(200,100200,40003),(200,100201,40003),(200,100202,40003),(200,100203,40003),(200,100204,40003),(200,100205,40003),(200,100206,40003),(200,300001,40001),(200,310205,40002),(200,320206,40002),(200,320207,40002),(200,320208,40002),(200,320209,40002),(200,320210,40002),(200,320215,40002),(200,320216,40002),(300,100100,50003),(300,100203,50003),(300,100205,50003),(300,100206,50003),(300,100300,50003),(300,100301,50003),(300,100302,50003),(300,100303,50003),(300,100312,50003),(300,300001,50001),(300,310205,50002),(300,310305,30005),(300,330306,50002),(300,330307,50002),(300,330308,50002),(300,330309,50002),(300,330310,50002),(300,330311,50002),(300,330312,50002);
/*!40000 ALTER TABLE `qr_cfg_func_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `qr_dict_acceptstatus`
--

LOCK TABLES `qr_dict_acceptstatus` WRITE;
/*!40000 ALTER TABLE `qr_dict_acceptstatus` DISABLE KEYS */;
INSERT INTO `qr_dict_acceptstatus` VALUES (0,'未回访'),(1,'已修好'),(2,'未修好');
/*!40000 ALTER TABLE `qr_dict_acceptstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `qr_dict_appservice`
--

LOCK TABLES `qr_dict_appservice` WRITE;
/*!40000 ALTER TABLE `qr_dict_appservice` DISABLE KEYS */;
INSERT INTO `qr_dict_appservice` VALUES (100100,'查询全部订单统计'),(100200,'查询全部工程订单信息'),(100201,'模糊查询某一订单的工程信息。'),(100202,'查询某一个工程订单的详细信息。'),(100203,'查询某一个工程订单的设备信息。'),(100205,'查询某一个工程订单中的设备的属性信息。'),(100206,'查询某一工程预先设置的设备种类信息'),(100300,'查询全部维修/维护订单信息。'),(100301,'模糊查询某一订单的维修/维护信息。'),(100302,'查询某一个维修/维护订单的详细信息。'),(100303,'查询某一个订单的全部维修记录信息。'),(100312,'查询最近一个维修/维护订单的详细信息。'),(300001,'用户登录'),(300002,'用户密码修改'),(310205,'修改某一工程订单的评价信息。'),(310305,'修改某一维修/维护订单的评价信息。'),(320206,'修改工程开工时间。'),(320207,'修改工程开工进度。'),(320208,'增加工程中某一订单的设备种类。'),(320209,'删除工程中某一订单的设备种类'),(320210,'修改工程中某一订单的设备的数量信息'),(320212,'新增设备'),(320213,'删除设备'),(320214,'更新设备'),(320215,'上传工程施工图片。'),(320216,'删除工程图片'),(330306,'修改维修开工时间。'),(330307,'修改维修进度。'),(330308,'修改故障类型'),(330309,'修改维修结果描述'),(330310,'上传维修图片。'),(330311,'删除维修图片'),(330312,'修改维修人数');
/*!40000 ALTER TABLE `qr_dict_appservice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `qr_dict_buildtype`
--

LOCK TABLES `qr_dict_buildtype` WRITE;
/*!40000 ALTER TABLE `qr_dict_buildtype` DISABLE KEYS */;
INSERT INTO `qr_dict_buildtype` VALUES (0,'自建'),(1,'外包');
/*!40000 ALTER TABLE `qr_dict_buildtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `qr_dict_custom_type`
--

LOCK TABLES `qr_dict_custom_type` WRITE;
/*!40000 ALTER TABLE `qr_dict_custom_type` DISABLE KEYS */;
INSERT INTO `qr_dict_custom_type` VALUES (0,'报警用户'),(1,'天网用户');
/*!40000 ALTER TABLE `qr_dict_custom_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `qr_dict_device_type`
--

LOCK TABLES `qr_dict_device_type` WRITE;
/*!40000 ALTER TABLE `qr_dict_device_type` DISABLE KEYS */;
INSERT INTO `qr_dict_device_type` VALUES (1,'前端设备'),(2,'后端设备'),(3,'辅助材料');
/*!40000 ALTER TABLE `qr_dict_device_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `qr_dict_maintainstatus`
--

LOCK TABLES `qr_dict_maintainstatus` WRITE;
/*!40000 ALTER TABLE `qr_dict_maintainstatus` DISABLE KEYS */;
INSERT INTO `qr_dict_maintainstatus` VALUES (0,'创建'),(1,'预约'),(2,'维修中'),(3,'维修完成'),(4,'回访完成');
/*!40000 ALTER TABLE `qr_dict_maintainstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `qr_dict_projecttype`
--

LOCK TABLES `qr_dict_projecttype` WRITE;
/*!40000 ALTER TABLE `qr_dict_projecttype` DISABLE KEYS */;
INSERT INTO `qr_dict_projecttype` VALUES (0,'报警'),(1,'天网'),(2,'系统集成');
/*!40000 ALTER TABLE `qr_dict_projecttype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `qr_dict_workcheckresult`
--

LOCK TABLES `qr_dict_workcheckresult` WRITE;
/*!40000 ALTER TABLE `qr_dict_workcheckresult` DISABLE KEYS */;
INSERT INTO `qr_dict_workcheckresult` VALUES (0,'未内检'),(1,'内检通过'),(2,'内检不通过');
/*!40000 ALTER TABLE `qr_dict_workcheckresult` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `qr_dict_workstatus`
--

LOCK TABLES `qr_dict_workstatus` WRITE;
/*!40000 ALTER TABLE `qr_dict_workstatus` DISABLE KEYS */;
INSERT INTO `qr_dict_workstatus` VALUES (0,'创建'),(1,'预约'),(2,'施工中'),(3,'施工完成'),(4,'验收完成');
/*!40000 ALTER TABLE `qr_dict_workstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `qr_dict_worktype`
--

LOCK TABLES `qr_dict_worktype` WRITE;
/*!40000 ALTER TABLE `qr_dict_worktype` DISABLE KEYS */;
INSERT INTO `qr_dict_worktype` VALUES (0,'报警项目'),(1,'天网项目');
/*!40000 ALTER TABLE `qr_dict_worktype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `qr_function`
--

LOCK TABLES `qr_function` WRITE;
/*!40000 ALTER TABLE `qr_function` DISABLE KEYS */;
INSERT INTO `qr_function` VALUES (10001,'WEB业务端-登录','WEB业务端-登录','2016-11-14 10:58:20','2016-11-14 10:58:20',1,1),(10002,'WEB业务端-查询(所有查询)',NULL,'2016-11-14 10:58:20','2016-11-14 10:58:20',1,1),(10003,'WEB业务端-增、删、改(一般)',NULL,'2016-11-14 10:58:20','2016-11-14 10:58:20',1,1),(10004,'WEB业务端-指定施工负责人(项目经理)',NULL,'2016-11-14 10:58:20','2016-11-14 10:58:20',1,1),(10005,'WEB业务端-施工内检结果(项目经理)',NULL,'2016-11-14 10:58:20','2016-11-14 10:58:20',1,1),(20001,'MIS端-登录',NULL,'2016-11-14 10:58:20','2016-11-14 10:58:20',1,1),(20002,'MIS端-查询(所有查询)',NULL,'2016-11-14 10:58:20','2016-11-14 10:58:20',1,1),(20003,'MIS端-增、删、改',NULL,'2016-11-14 10:58:20','2016-11-14 10:58:20',1,1),(30001,'用户APP端-登录',NULL,'2016-11-14 10:58:20','2016-11-14 10:58:20',1,1),(30002,'用户APP端-修改密码',NULL,'2016-11-14 10:58:20','2016-11-14 10:58:20',1,1),(30003,'用户APP端-查询（所有查询）',NULL,'2016-11-14 10:58:20','2016-11-14 10:58:20',1,1),(30004,'用户APP端-修改某一工程订单的评价信息',NULL,'2016-11-14 10:58:20','2016-11-14 10:58:20',1,1),(30005,'用户APP端-修改某一工程订单的维修评价信息',NULL,'2016-11-14 10:58:20','2016-11-14 10:58:20',1,1),(40001,'工程APP端-登录',NULL,'2016-11-14 10:58:20','2016-11-14 10:58:20',1,1),(40002,'工程APP端-订单修改(包括对设备的修改)',NULL,'2016-11-14 10:58:20','2016-11-14 10:58:20',1,1),(40003,'工程APP端-查询（所有查询）',NULL,'2016-11-14 10:58:20','2016-11-14 10:58:20',1,1),(50001,'维修APP端-登录',NULL,'2016-11-14 10:58:20','2016-11-14 10:58:20',1,1),(50002,'维修APP端-维修订单修改',NULL,'2016-11-14 10:58:20','2016-11-14 10:58:20',1,1),(50003,'维修APP端-维修订单查询（所有查询）',NULL,'2016-11-14 10:58:20','2016-11-14 10:58:20',1,1),(60001,'APP端-订单设备维护',NULL,'2016-11-14 10:58:20','2016-11-14 10:58:20',1,1);
/*!40000 ALTER TABLE `qr_function` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-06 10:46:46
