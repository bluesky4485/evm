-- MySQL dump 10.13  Distrib 5.7.12, for Win32 (AMD64)
--
-- Host: 10.44.30.97    Database: evm
-- ------------------------------------------------------
-- Server version	5.0.22-community-nt

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
-- Not dumping tablespaces as no INFORMATION_SCHEMA.FILES table on this server
--

--
-- Table structure for table `qr_cfg_func_service`
--

DROP TABLE IF EXISTS `qr_cfg_func_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_cfg_func_service` (
  `SYSTEM_ID` bigint(64) NOT NULL COMMENT '系统ID',
  `SERVICE_ID` bigint(64) NOT NULL COMMENT '服务ID',
  `FUNCTION_ID` bigint(64) NOT NULL COMMENT '权限ID',
  UNIQUE KEY `CFG_PK` (`SYSTEM_ID`,`SERVICE_ID`,`FUNCTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='App权限-服务配置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_cfg_func_service`
--

LOCK TABLES `qr_cfg_func_service` WRITE;
/*!40000 ALTER TABLE `qr_cfg_func_service` DISABLE KEYS */;
INSERT INTO `qr_cfg_func_service` VALUES (100,100100,30003),(100,300001,30001),(100,300002,30002),(200,100100,30003),(200,100200,30003),(200,100201,40003),(200,100202,40003),(200,100203,40003),(200,100204,40003),(200,100205,40003),(200,100206,40003),(200,300001,40001),(200,300002,30002),(200,310205,30004),(200,310305,30005),(200,320206,40002),(200,320207,40002),(200,320208,40002),(200,320209,40002),(200,320210,40002),(200,320212,40002),(200,320213,40002),(200,320214,40002),(300,100100,30003),(300,100300,50003),(300,100302,50003),(300,300001,50001),(300,300002,30002),(300,310305,50002),(300,330306,50002),(300,330307,50002),(300,330308,50002),(300,330309,50002);
/*!40000 ALTER TABLE `qr_cfg_func_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_custom`
--

DROP TABLE IF EXISTS `qr_custom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_custom` (
  `CUS_ID` bigint(64) NOT NULL auto_increment COMMENT '客户编号',
  `CUS_NAME` varchar(50) NOT NULL COMMENT '客户姓名',
  `CUS_CARID` varchar(50) default NULL COMMENT '客户身份证号',
  `CUS_TEL1` varchar(50) NOT NULL COMMENT '客户电话1',
  `CUS_TEL2` varchar(50) NOT NULL COMMENT '客户电话2',
  `CUS_SEX` varchar(10) default NULL COMMENT '客户性别',
  `CUS_ADDRESS` varchar(200) default NULL COMMENT '客户地址',
  `CUS_SUBSTATION` varchar(200) default NULL COMMENT '所属分局',
  `CUS_POLICESTATION` varchar(200) default NULL COMMENT '所属派出所 ',
  `CUS_TYPE` varchar(10) default NULL COMMENT '用户类别',
  `CUS_REMARK` varchar(200) default NULL COMMENT '客户备注',
  `UPD_DATE` datetime default NULL,
  `INS_DATE` datetime NOT NULL,
  `INS_USER` bigint(64) NOT NULL,
  `UPD_USER` bigint(64) NOT NULL,
  `CUS_STATUS` varchar(10) NOT NULL default '0',
  `LAT` decimal(16,6) default NULL,
  `LNG` decimal(16,6) default NULL,
  `USER_ID` bigint(64) default NULL COMMENT '用户ID',
  PRIMARY KEY  (`CUS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_custom`
--

LOCK TABLES `qr_custom` WRITE;
/*!40000 ALTER TABLE `qr_custom` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_custom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_device_property`
--

DROP TABLE IF EXISTS `qr_device_property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_device_property` (
  `device_id` bigint(64) default NULL COMMENT '设备ID',
  `property_id` bigint(64) NOT NULL auto_increment,
  `propert_name` varchar(100) default NULL COMMENT '属性名',
  `UPD_DATE` datetime default NULL COMMENT '更新时间',
  `INS_DATE` datetime default NULL COMMENT '创建时间',
  `INS_USER` bigint(64) default NULL COMMENT '创建用户ID',
  `UPD_USER` bigint(64) default NULL COMMENT '更新用户ID',
  PRIMARY KEY  (`property_id`),
  UNIQUE KEY `PKEY` (`device_id`,`propert_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备属性表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_device_property`
--

LOCK TABLES `qr_device_property` WRITE;
/*!40000 ALTER TABLE `qr_device_property` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_device_property` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_device_type`
--

DROP TABLE IF EXISTS `qr_device_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_device_type` (
  `id` bigint(64) NOT NULL auto_increment COMMENT '设备ID',
  `text` varchar(100) default NULL COMMENT '设备名称',
  `class_type` varchar(10) default NULL COMMENT '大分类类型（设备，线材，辅助设备）',
  `UPD_DATE` datetime default NULL COMMENT '更新时间',
  `INS_DATE` datetime default NULL COMMENT '创建时间',
  `INS_USER` bigint(64) default NULL COMMENT '创建用户ID',
  `UPD_USER` bigint(64) default NULL COMMENT '更新用户ID',
  `DEV_STATUS` varchar(10) default '0' COMMENT '删除标示 ，1已删',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `text_UNIQUE` (`text`,`DEV_STATUS`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备类型表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_device_type`
--

LOCK TABLES `qr_device_type` WRITE;
/*!40000 ALTER TABLE `qr_device_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_device_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_deviceitem`
--

DROP TABLE IF EXISTS `qr_deviceitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_deviceitem` (
  `order_id` bigint(64) default NULL COMMENT '订单ID',
  `deviceitem_id` bigint(64) NOT NULL auto_increment COMMENT '设备ID',
  `device_type_id` bigint(64) NOT NULL COMMENT '设备类型ID',
  `deviceitem_Name` varchar(100) default NULL COMMENT '设备名称',
  `deviceitem_status` varchar(10) default '0' COMMENT '删除标识',
  `deviceitem_uid` varchar(100) default NULL COMMENT '设备唯一标示，可以为空',
  `UPD_DATE` datetime default NULL,
  `INS_DATE` datetime default NULL,
  `INS_USER` bigint(64) default NULL,
  `UPD_USER` bigint(64) default NULL,
  `deviceitem_addr` varchar(100) default NULL COMMENT '地址',
  PRIMARY KEY  (`deviceitem_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_deviceitem`
--

LOCK TABLES `qr_deviceitem` WRITE;
/*!40000 ALTER TABLE `qr_deviceitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_deviceitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_deviceitem_property`
--

DROP TABLE IF EXISTS `qr_deviceitem_property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_deviceitem_property` (
  `deviceitem_property_id` bigint(64) NOT NULL auto_increment,
  `devicetype_property_id` bigint(64) default NULL COMMENT '设备属性ID',
  `deviceitem_property_value` varchar(145) default NULL COMMENT '设备属性值',
  `deviceItem_id` bigint(64) default NULL COMMENT '设备ID',
  `UPD_DATE` datetime default NULL COMMENT '更新时间',
  `INS_DATE` datetime default NULL COMMENT '创建时间',
  `INS_USER` bigint(64) default NULL COMMENT '创建用户ID',
  `UPD_USER` bigint(64) default NULL COMMENT '更新用户ID',
  PRIMARY KEY  (`deviceitem_property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备属性';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_deviceitem_property`
--

LOCK TABLES `qr_deviceitem_property` WRITE;
/*!40000 ALTER TABLE `qr_deviceitem_property` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_deviceitem_property` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_dict_acceptstatus`
--

DROP TABLE IF EXISTS `qr_dict_acceptstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_dict_acceptstatus` (
  `id` int(11) NOT NULL,
  `text` varchar(45) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='验收状态';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_dict_acceptstatus`
--

LOCK TABLES `qr_dict_acceptstatus` WRITE;
/*!40000 ALTER TABLE `qr_dict_acceptstatus` DISABLE KEYS */;
INSERT INTO `qr_dict_acceptstatus` VALUES (0,'未回访'),(1,'已修好'),(2,'未修好');
/*!40000 ALTER TABLE `qr_dict_acceptstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_dict_buildtype`
--

DROP TABLE IF EXISTS `qr_dict_buildtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_dict_buildtype` (
  `id` int(11) NOT NULL,
  `text` varchar(45) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_dict_buildtype`
--

LOCK TABLES `qr_dict_buildtype` WRITE;
/*!40000 ALTER TABLE `qr_dict_buildtype` DISABLE KEYS */;
INSERT INTO `qr_dict_buildtype` VALUES (0,'自建'),(1,'外包');
/*!40000 ALTER TABLE `qr_dict_buildtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_dict_custom_type`
--

DROP TABLE IF EXISTS `qr_dict_custom_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_dict_custom_type` (
  `id` int(11) NOT NULL,
  `text` varchar(45) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户类型字典表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_dict_custom_type`
--

LOCK TABLES `qr_dict_custom_type` WRITE;
/*!40000 ALTER TABLE `qr_dict_custom_type` DISABLE KEYS */;
INSERT INTO `qr_dict_custom_type` VALUES (0,'报警用户'),(1,'天网用户');
/*!40000 ALTER TABLE `qr_dict_custom_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_dict_device_type`
--

DROP TABLE IF EXISTS `qr_dict_device_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_dict_device_type` (
  `id` int(11) NOT NULL,
  `text` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备大类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_dict_device_type`
--

LOCK TABLES `qr_dict_device_type` WRITE;
/*!40000 ALTER TABLE `qr_dict_device_type` DISABLE KEYS */;
INSERT INTO `qr_dict_device_type` VALUES (1,'设备'),(2,'线材'),(3,'辅助材料');
/*!40000 ALTER TABLE `qr_dict_device_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_dict_devicetype`
--

DROP TABLE IF EXISTS `qr_dict_devicetype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_dict_devicetype` (
  `id` int(11) NOT NULL,
  `text` varchar(45) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备类型';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_dict_devicetype`
--

LOCK TABLES `qr_dict_devicetype` WRITE;
/*!40000 ALTER TABLE `qr_dict_devicetype` DISABLE KEYS */;
INSERT INTO `qr_dict_devicetype` VALUES (1,'设备'),(2,'线材'),(3,'辅助材料');
/*!40000 ALTER TABLE `qr_dict_devicetype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_dict_maintainstatus`
--

DROP TABLE IF EXISTS `qr_dict_maintainstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_dict_maintainstatus` (
  `id` int(11) NOT NULL,
  `text` varchar(45) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='维修状态';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_dict_maintainstatus`
--

LOCK TABLES `qr_dict_maintainstatus` WRITE;
/*!40000 ALTER TABLE `qr_dict_maintainstatus` DISABLE KEYS */;
INSERT INTO `qr_dict_maintainstatus` VALUES (0,'创建'),(1,'预约'),(2,'维修中'),(3,'维修完成'),(4,'回访完成');
/*!40000 ALTER TABLE `qr_dict_maintainstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_dict_projecttype`
--

DROP TABLE IF EXISTS `qr_dict_projecttype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_dict_projecttype` (
  `id` int(11) NOT NULL,
  `text` varchar(45) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目类型';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_dict_projecttype`
--

LOCK TABLES `qr_dict_projecttype` WRITE;
/*!40000 ALTER TABLE `qr_dict_projecttype` DISABLE KEYS */;
INSERT INTO `qr_dict_projecttype` VALUES (0,'报警'),(1,'天网'),(2,'系统集成');
/*!40000 ALTER TABLE `qr_dict_projecttype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_dict_workcheckresult`
--

DROP TABLE IF EXISTS `qr_dict_workcheckresult`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_dict_workcheckresult` (
  `id` int(11) NOT NULL,
  `text` varchar(45) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='施工内检结果';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_dict_workcheckresult`
--

LOCK TABLES `qr_dict_workcheckresult` WRITE;
/*!40000 ALTER TABLE `qr_dict_workcheckresult` DISABLE KEYS */;
INSERT INTO `qr_dict_workcheckresult` VALUES (0,'未内检'),(1,'内检通过'),(2,'内检不通过');
/*!40000 ALTER TABLE `qr_dict_workcheckresult` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_dict_workstatus`
--

DROP TABLE IF EXISTS `qr_dict_workstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_dict_workstatus` (
  `id` int(11) NOT NULL,
  `text` varchar(45) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='/施工状态';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_dict_workstatus`
--

LOCK TABLES `qr_dict_workstatus` WRITE;
/*!40000 ALTER TABLE `qr_dict_workstatus` DISABLE KEYS */;
INSERT INTO `qr_dict_workstatus` VALUES (0,'创建'),(1,'预约'),(2,'施工中'),(3,'施工完成'),(4,'验收完成');
/*!40000 ALTER TABLE `qr_dict_workstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_dict_worktype`
--

DROP TABLE IF EXISTS `qr_dict_worktype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_dict_worktype` (
  `id` int(11) NOT NULL,
  `text` varchar(45) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='施工类型';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_dict_worktype`
--

LOCK TABLES `qr_dict_worktype` WRITE;
/*!40000 ALTER TABLE `qr_dict_worktype` DISABLE KEYS */;
INSERT INTO `qr_dict_worktype` VALUES (0,'报警项目'),(1,'天网项目');
/*!40000 ALTER TABLE `qr_dict_worktype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_faulttype`
--

DROP TABLE IF EXISTS `qr_faulttype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_faulttype` (
  `FAULTTYPE_ID` bigint(64) NOT NULL auto_increment,
  `FAULTTYPE_NO` varchar(50) default NULL,
  `FAULTTYPE_NAME` varchar(50) NOT NULL,
  `UPD_DATE` datetime NOT NULL COMMENT '更新时间',
  `INS_DATE` datetime NOT NULL COMMENT '创建时间',
  `INS_USER` bigint(64) NOT NULL COMMENT '创建用户ID',
  `UPD_USER` bigint(64) NOT NULL COMMENT '更新用户ID',
  PRIMARY KEY  (`FAULTTYPE_ID`),
  UNIQUE KEY `FAULTTYPE_NAME_UNIQUE` (`FAULTTYPE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_faulttype`
--

LOCK TABLES `qr_faulttype` WRITE;
/*!40000 ALTER TABLE `qr_faulttype` DISABLE KEYS */;
INSERT INTO `qr_faulttype` VALUES (1,'1','施工质量','2016-10-25 21:37:06','2016-10-10 00:00:00',1,1),(2,'3','装修','2016-10-25 21:36:19','2016-10-25 14:29:53',20,1),(17,'e','操作失误','2016-10-25 21:16:13','2016-10-25 20:18:56',1,1),(18,'er','市电故障','2016-10-25 21:15:06','2016-10-25 20:19:39',1,1),(20,'jh','其它故障','2016-10-25 21:14:14','2016-10-25 20:23:15',1,1),(22,'','光缆故障','2016-10-25 21:14:01','2016-10-25 21:06:37',1,1),(23,'','光电故障','2016-10-25 21:07:30','2016-10-25 21:07:30',1,1),(24,'','人为破坏','2016-10-25 21:37:19','2016-10-25 21:07:42',1,1),(25,'','市政故障','2016-10-25 21:07:51','2016-10-25 21:07:51',1,1),(26,NULL,'人为原因','2016-10-25 21:15:59','2016-10-25 21:13:37',1,1);
/*!40000 ALTER TABLE `qr_faulttype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_function`
--

DROP TABLE IF EXISTS `qr_function`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_function` (
  `function_id` bigint(64) NOT NULL auto_increment COMMENT '功能ID',
  `function_name` varchar(50) default NULL COMMENT '功能名称',
  `function_desc` varchar(200) default NULL,
  `UPD_DATE` datetime default NULL,
  `INS_DATE` datetime default NULL,
  `INS_USER` bigint(64) default NULL,
  `UPD_USER` bigint(64) default NULL,
  PRIMARY KEY  (`function_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_function`
--

LOCK TABLES `qr_function` WRITE;
/*!40000 ALTER TABLE `qr_function` DISABLE KEYS */;
INSERT INTO `qr_function` VALUES (10001,'WEB业务端-登录','WEB业务端-登录','2016-11-14 10:58:20','2016-11-14 10:58:20',1,1),(10002,'WEB业务端-查询(所有查询)',NULL,'2016-11-14 10:58:20','2016-11-14 10:58:20',1,1),(10003,'WEB业务端-增、删、改(一般)',NULL,'2016-11-14 10:58:20','2016-11-14 10:58:20',1,1),(10004,'WEB业务端-指定施工负责人(项目经理)',NULL,'2016-11-14 10:58:20','2016-11-14 10:58:20',1,1),(10005,'WEB业务端-施工内检结果(项目经理)',NULL,'2016-11-14 10:58:20','2016-11-14 10:58:20',1,1),(20001,'MIS端-登录',NULL,'2016-11-14 10:58:20','2016-11-14 10:58:20',1,1),(20002,'MIS端-查询(所有查询)',NULL,'2016-11-14 10:58:20','2016-11-14 10:58:20',1,1),(20003,'MIS端-增、删、改',NULL,'2016-11-14 10:58:20','2016-11-14 10:58:20',1,1),(30001,'用户APP端-登录',NULL,'2016-11-14 10:58:20','2016-11-14 10:58:20',1,1),(30002,'用户APP端-修改密码',NULL,'2016-11-14 10:58:20','2016-11-14 10:58:20',1,1),(30003,'用户APP端-查询（所有查询）',NULL,'2016-11-14 10:58:20','2016-11-14 10:58:20',1,1),(30004,'用户APP端-修改某一工程订单的评价信息',NULL,'2016-11-14 10:58:20','2016-11-14 10:58:20',1,1),(30005,'用户APP端-修改某一工程订单的维修评价信息',NULL,'2016-11-14 10:58:20','2016-11-14 10:58:20',1,1),(40001,'工程APP端-登录',NULL,'2016-11-14 10:58:20','2016-11-14 10:58:20',1,1),(40002,'工程APP端-订单修改(包括对设备的修改)',NULL,'2016-11-14 10:58:20','2016-11-14 10:58:20',1,1),(40003,'用户APP端-查询（所有查询）',NULL,'2016-11-14 10:58:20','2016-11-14 10:58:20',1,1),(50001,'维修APP端-登录',NULL,'2016-11-14 10:58:20','2016-11-14 10:58:20',1,1),(50002,'维修APP端-维修订单修改',NULL,'2016-11-14 10:58:20','2016-11-14 10:58:20',1,1),(50003,'用户APP端-维修订单查询（所有查询）',NULL,'2016-11-14 10:58:20','2016-11-14 10:58:20',1,1);
/*!40000 ALTER TABLE `qr_function` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_log`
--

DROP TABLE IF EXISTS `qr_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_log` (
  `log_id` bigint(64) NOT NULL auto_increment COMMENT '日志ID',
  `log_content` varchar(500) default NULL COMMENT '日志内容',
  `log_para` varchar(600) default NULL COMMENT '日志参数',
  `INS_DATE` datetime default NULL,
  `INS_USER` bigint(64) default NULL,
  `log_biz_type` varchar(10) default '0' COMMENT '日志业务类型1客户表，2项目表，3订单表，4维修订单',
  `log_biz_ID` bigint(64) default '0' COMMENT '业务ID',
  PRIMARY KEY  (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_log`
--

LOCK TABLES `qr_log` WRITE;
/*!40000 ALTER TABLE `qr_log` DISABLE KEYS */;
INSERT INTO `qr_log` VALUES (1,'用户登录','登录名:admin','2017-02-17 08:49:15',1,'4',10001),(2,'查询客户信息','','2017-02-17 08:50:03',1,'4',10002),(3,'查询项目信息','','2017-02-17 08:50:07',1,'4',10002),(4,'查询订单信息','','2017-02-17 08:50:11',1,'4',10002),(5,'查询维修订单信息','','2017-02-17 08:50:14',1,'4',10002),(6,'查询故障类型信息','','2017-02-17 08:50:23',1,'4',10002),(7,'查询故障类型信息','','2017-02-17 08:50:27',1,'4',10002),(8,'查询设备类型信息','deviceId=0;deviceName=null;classType=null;updUser=1;updDate=null;insUser=null;insDate=null;','2017-02-17 08:50:30',1,'4',10002),(9,'查询订单信息','','2017-02-17 08:50:35',1,'4',10002),(10,'查询故障类型信息','','2017-02-17 08:50:52',1,'4',10002),(11,'删除故障类型信息','故障类型ID:33;','2017-02-17 08:51:07',1,'2',10003),(12,'查询故障类型信息','','2017-02-17 08:51:07',1,'4',10002),(13,'查询客户信息','','2017-02-17 08:51:27',1,'4',10002),(14,'查询客户信息','','2017-02-17 08:52:02',1,'4',10002),(15,'创建客户信息','客户编号:1;客户姓名:a;客户电话1:13912341234;客户电话2:13912341234;性别:null;用户类型:null;','2017-02-17 08:52:18',1,'1',10003),(16,'查询客户信息','','2017-02-17 08:52:20',1,'4',10002),(17,'查询项目信息','','2017-02-17 08:52:22',1,'4',10002),(18,'查询客户信息','','2017-02-17 08:52:34',1,'4',10002),(19,'创建项目信息','项目ID:1;项目编号:a;项目名称:v;客户ID:1;项目分类:null;合同编号:a;合同金额:0.00;缴费类型:;年服务费:0.00;建设方式:null;备注:;','2017-02-17 08:52:44',1,'1',10003),(20,'查询项目信息','','2017-02-17 08:52:46',1,'4',10002),(21,'查询订单信息','','2017-02-17 08:52:53',1,'4',10002),(22,'用户登录','登录名:admin','2017-02-17 08:55:13',1,'4',10001),(23,'用户登录','登录名:admin','2017-02-17 09:02:00',1,'4',10001),(24,'查询客户信息','','2017-02-17 09:02:35',1,'4',10002),(25,'查询客户信息','','2017-02-17 09:02:59',1,'4',10002),(26,'查询项目信息','','2017-02-17 09:03:01',1,'4',10002),(27,'查询客户信息','','2017-02-17 09:03:24',1,'4',10002),(28,'查询项目信息','','2017-02-17 09:03:25',1,'4',10002),(29,'查询订单信息','','2017-02-17 09:03:28',1,'4',10002),(30,'查询维修订单信息','','2017-02-17 09:03:31',1,'4',10002),(31,'查询故障类型信息','','2017-02-17 09:03:40',1,'4',10002),(32,'查询故障类型信息','','2017-02-17 09:03:42',1,'4',10002);
/*!40000 ALTER TABLE `qr_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_morder`
--

DROP TABLE IF EXISTS `qr_morder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_morder` (
  `ORDER_ID` bigint(64) NOT NULL COMMENT '订单编号',
  `MORDER_ID` bigint(64) NOT NULL auto_increment COMMENT '运维订单编号',
  `MORDER_PM` bigint(64) NOT NULL COMMENT '运维经理ID',
  `MAINTAIN_DATE` datetime default NULL COMMENT '派修时间',
  `FAULT_DESC` varchar(200) default NULL COMMENT '故障描述',
  `APPOINTMENT_DATE` datetime default NULL COMMENT '预约时间',
  `PLAN_END_DATE` datetime default NULL COMMENT '计划完成时间',
  `MWORKER_CNT` bigint(64) default NULL COMMENT '维修人数',
  `MAINTAIN_BEGIN_DATE` datetime default NULL COMMENT '维修开始日期',
  `MAINTAIN_STATUS` varchar(10) default NULL COMMENT '维修状态  创建:0 预约1, 维修中2 维修完成3 回访完成4',
  `MAINTAIN_SPEED` varchar(20) default NULL COMMENT '维修进度',
  `FAULT_TYPE` varchar(10) default NULL COMMENT '故障类别',
  `MAINTAIN_PM` bigint(64) default NULL COMMENT '维修负责人ID',
  `ACCEPT_STATUS` varchar(10) default NULL COMMENT '验收状态',
  `ACCEPTECALL_MAN` varchar(20) default NULL COMMENT '验收回访人',
  `CALLBACK_DATE` datetime default NULL COMMENT '验收回访时间',
  `SOLUTION` varchar(200) default NULL COMMENT '解决方案',
  `MAINTAIN_RESULTDESC` varchar(200) default NULL COMMENT '维修结果描述',
  `MAINTAIN_REMARK` varchar(200) default NULL COMMENT '备注',
  `USER_SCORE` varchar(20) default NULL COMMENT '用户评分',
  `USER_PROPOSAL` varchar(200) default NULL COMMENT '用户评价',
  `UPD_DATE` datetime NOT NULL COMMENT '更新时间',
  `INS_DATE` datetime NOT NULL COMMENT '创建时间',
  `INS_USER` bigint(64) NOT NULL COMMENT '创建用户ID',
  `UPD_USER` bigint(64) NOT NULL COMMENT '订单编号',
  `MORDER_STATUS` varchar(10) default '0' COMMENT '是否删除1',
  `MORDER_NO` varchar(45) default NULL,
  `LAST_UPDDATE` datetime default NULL COMMENT '最后一次进度更新时间',
  PRIMARY KEY  (`MORDER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='维修订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_morder`
--

LOCK TABLES `qr_morder` WRITE;
/*!40000 ALTER TABLE `qr_morder` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_morder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_order`
--

DROP TABLE IF EXISTS `qr_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_order` (
  `order_id` bigint(64) NOT NULL auto_increment COMMENT '订单ID',
  `ORDER_NO` varchar(100) default NULL COMMENT '订单编号',
  `PROJECT_ID` bigint(64) default NULL COMMENT '项目ID',
  `WORK_PM_ID` bigint(64) default NULL COMMENT '施工负责人ID',
  `WORK_CNT` bigint(64) default NULL COMMENT '施工人数',
  `WORK_ADDRESS` varchar(100) default NULL,
  `WORK_COMPANY` varchar(100) default NULL COMMENT '施工单位',
  `WORK_COMPANY_QUALIFIED` varchar(100) default NULL COMMENT '单位资质情况 ',
  `WORK_TYPE` varchar(10) default '0' COMMENT '施工类型',
  `WORK_DAYS` bigint(64) default '0' COMMENT '施工天数',
  `PLAN_BEGIN_DATE` datetime default NULL COMMENT '计划施工时间',
  `PLAN_END_DATE` datetime default NULL COMMENT '计划完工时间',
  `STORE_PM_ID` bigint(64) default NULL COMMENT '出库联系人',
  `PLAN_OUTSTORE_DATE` datetime default NULL COMMENT '计划出库时间',
  `WORK_STATUS` varchar(10) default '0' COMMENT '施工状态:创建0预约1施工中2施工完成3验收完成4',
  `WORK_BEGIN_DATE` datetime default NULL COMMENT '实际施工时间',
  `WORK_END_DATE` datetime default NULL COMMENT '实际施工完成时间',
  `WORK_PROGRESS` varchar(100) default '0' COMMENT '施工进度',
  `LAST_WORKUPD_DATE` datetime default NULL COMMENT '最后一次施工更新时间',
  `CHECKER_ID` bigint(64) default NULL COMMENT '内检人员',
  `CHECK_RESULT` varchar(100) default NULL COMMENT '施工内检结果',
  `CHECK_DATE` datetime default NULL COMMENT '内检时间',
  `USER_RATING` varchar(100) default '0' COMMENT '用户评分',
  `USER_APPRAISE` varchar(200) default NULL COMMENT '用户评价',
  `WORK_REMARK` varchar(200) default NULL COMMENT '备注',
  `UPD_DATE` datetime default NULL COMMENT '更新时间',
  `INS_DATE` datetime default NULL COMMENT '创建时间',
  `INS_USER` bigint(64) default NULL COMMENT '创建用户ID',
  `UPD_USER` bigint(64) default NULL COMMENT '更新用户ID',
  `order_status` varchar(10) default '0' COMMENT '1时删除',
  `lat` decimal(16,6) default NULL,
  `lng` decimal(16,6) default NULL,
  PRIMARY KEY  (`order_id`),
  UNIQUE KEY `order_no` (`ORDER_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_order`
--

LOCK TABLES `qr_order` WRITE;
/*!40000 ALTER TABLE `qr_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_project`
--

DROP TABLE IF EXISTS `qr_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_project` (
  `PROJECT_ID` bigint(64) NOT NULL auto_increment COMMENT '项目ID',
  `PROJECT_NO` varchar(100) default NULL,
  `PROJECT_NAME` varchar(50) NOT NULL COMMENT '项目名称',
  `CUS_ID` bigint(64) NOT NULL COMMENT '客户ID-外键关联客户表',
  `CM_ID` bigint(64) default NULL COMMENT '客户经理ID-外键关联用户表',
  `PM_ID` bigint(64) default NULL COMMENT '项目经理ID-外键关联用户表',
  `PROJECT_TYPE` varchar(10) default NULL COMMENT '项目分类 ',
  `CONTRACT_ID` varchar(100) NOT NULL COMMENT '合同编号',
  `CONTRACT_SUM` decimal(16,2) default NULL COMMENT '合同金额',
  `SIGN_DATE` datetime default NULL COMMENT '签订日期',
  `PAY_TYPE` varchar(10) default NULL COMMENT '缴费类型 ',
  `WORK_END_DATE` datetime default NULL COMMENT '工期截止日期',
  `WORK_START_DATE` datetime default NULL COMMENT '开工日期 ',
  `YEAR_PAY` decimal(16,2) default NULL COMMENT '年服务费',
  `SERVICE_END_DATE` datetime default NULL COMMENT '服务截止日期 ',
  `PAY_DATE` datetime default NULL COMMENT '缴费日期',
  `BUILD_TYPE` varchar(10) default '0' COMMENT '建设方式',
  `JOIN_DATE` datetime default NULL COMMENT '入网日期 ',
  `STOP_DATE` datetime default NULL COMMENT '停机日期 ',
  `PROJECT_REMARK` varchar(200) default NULL COMMENT '备注',
  `UPD_DATE` datetime NOT NULL COMMENT '更新时间',
  `INS_DATE` datetime NOT NULL COMMENT '创建时间',
  `INS_USER` bigint(64) NOT NULL,
  `UPD_USER` bigint(64) NOT NULL,
  `PRO_STATUS` varchar(10) default '0' COMMENT '删除标示：1 删除',
  PRIMARY KEY  (`PROJECT_ID`),
  UNIQUE KEY `Project_no_UNIQUE` (`PROJECT_NO`,`PRO_STATUS`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_project`
--

LOCK TABLES `qr_project` WRITE;
/*!40000 ALTER TABLE `qr_project` DISABLE KEYS */;
INSERT INTO `qr_project` VALUES (1,'10000001','标准测试项目',0,0,0,'0','10000001',NULL,NULL,'',NULL,NULL,0.00,NULL,NULL,'',NULL,NULL,'','2017-02-17 08:52:44','2017-02-17 08:52:44',1,1,'0');
/*!40000 ALTER TABLE `qr_project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_rel_order_device`
--

DROP TABLE IF EXISTS `qr_rel_order_device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_rel_order_device` (
  `order_id` bigint(64) default '0' COMMENT '订单ID',
  `device_id` bigint(64) default '0' COMMENT '设备类型ID',
  `store_cnt` bigint(64) default '0' COMMENT '库存数量',
  `plan_cnt` bigint(64) default '0' COMMENT '可领数量',
  `work_cnt` bigint(64) default '0' COMMENT '总数量',
  `INS_DATE` datetime NOT NULL,
  `INS_USER` bigint(64) NOT NULL,
  `UPD_DATE` datetime NOT NULL COMMENT '更新时间',
  `UPD_USER` bigint(64) NOT NULL,
  UNIQUE KEY `order_device_ID` (`order_id`,`device_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单设备关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_rel_order_device`
--

LOCK TABLES `qr_rel_order_device` WRITE;
/*!40000 ALTER TABLE `qr_rel_order_device` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_rel_order_device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_rel_order_file`
--

DROP TABLE IF EXISTS `qr_rel_order_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_rel_order_file` (
  `file_id` bigint(64) NOT NULL auto_increment,
  `order_id` bigint(64) NOT NULL,
  `file_name` varchar(150) default NULL,
  `file_type` varchar(10) default NULL,
  `file_path` varchar(150) default NULL,
  `file_size` bigint(64) default NULL,
  `biz_type` varchar(10) default NULL,
  `INS_DATE` datetime NOT NULL,
  `INS_USER` bigint(64) NOT NULL,
  `UPD_DATE` datetime NOT NULL COMMENT '更新时间',
  `UPD_USER` bigint(64) NOT NULL,
  PRIMARY KEY  (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_rel_order_file`
--

LOCK TABLES `qr_rel_order_file` WRITE;
/*!40000 ALTER TABLE `qr_rel_order_file` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_rel_order_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_rel_project_device`
--

DROP TABLE IF EXISTS `qr_rel_project_device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_rel_project_device` (
  `PD_ID` bigint(64) NOT NULL auto_increment COMMENT '唯一ID',
  `project_id` bigint(64) default NULL COMMENT '项目ID',
  `Device_TYPE_id` bigint(64) default NULL COMMENT '设备ID',
  `Device_Cnt` bigint(64) default NULL COMMENT '消耗设备数量',
  `UPD_DATE` datetime default NULL COMMENT '更新时间',
  `INS_DATE` datetime default NULL COMMENT '创建时间',
  `INS_USER` bigint(64) default NULL COMMENT '创建用户ID',
  `UPD_USER` bigint(64) default NULL COMMENT '更新用户ID',
  UNIQUE KEY `PD_ID_UNIQUE` (`PD_ID`),
  UNIQUE KEY `Project_device_id` (`project_id`,`Device_TYPE_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目设备关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_rel_project_device`
--

LOCK TABLES `qr_rel_project_device` WRITE;
/*!40000 ALTER TABLE `qr_rel_project_device` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_rel_project_device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_rel_project_user`
--

DROP TABLE IF EXISTS `qr_rel_project_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_rel_project_user` (
  `project_id` bigint(64) NOT NULL COMMENT '项目Id',
  `user_id` bigint(64) NOT NULL COMMENT '用户ID',
  `upd_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目用户关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_rel_project_user`
--

LOCK TABLES `qr_rel_project_user` WRITE;
/*!40000 ALTER TABLE `qr_rel_project_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_rel_project_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_rel_role_func`
--

DROP TABLE IF EXISTS `qr_rel_role_func`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_rel_role_func` (
  `role_id` bigint(64) default NULL COMMENT '角色ID',
  `function_id` bigint(64) default NULL COMMENT '权限ID',
  `UPD_DATE` datetime default NULL,
  `INS_DATE` datetime default NULL,
  `INS_USER` bigint(64) default NULL,
  `UPD_USER` bigint(64) default NULL,
  UNIQUE KEY `PKEY` (`role_id`,`function_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_rel_role_func`
--

LOCK TABLES `qr_rel_role_func` WRITE;
/*!40000 ALTER TABLE `qr_rel_role_func` DISABLE KEYS */;
INSERT INTO `qr_rel_role_func` VALUES (1,10001,'2017-01-23 14:37:42','2017-01-23 14:37:42',NULL,NULL),(1,10002,'2017-01-23 14:37:42','2017-01-23 14:37:42',NULL,NULL),(1,10003,'2017-01-23 14:37:42','2017-01-23 14:37:42',NULL,NULL),(1,10004,'2017-01-23 14:37:42','2017-01-23 14:37:42',NULL,NULL),(1,10005,'2017-01-23 14:37:42','2017-01-23 14:37:42',NULL,NULL),(1,20001,'2017-01-23 14:37:42','2017-01-23 14:37:42',NULL,NULL),(1,20002,'2017-01-23 14:37:42','2017-01-23 14:37:42',NULL,NULL),(1,20003,'2017-01-23 14:37:42','2017-01-23 14:37:42',NULL,NULL),(1,30001,'2017-01-23 14:37:42','2017-01-23 14:37:42',NULL,NULL),(1,30002,'2017-01-23 14:37:42','2017-01-23 14:37:42',NULL,NULL),(1,30003,'2017-01-23 14:37:42','2017-01-23 14:37:42',NULL,NULL),(1,30004,'2017-01-23 14:37:42','2017-01-23 14:37:42',NULL,NULL),(1,30005,'2017-01-23 14:37:42','2017-01-23 14:37:42',NULL,NULL),(1,40001,'2017-01-23 14:37:42','2017-01-23 14:37:42',NULL,NULL),(1,40002,'2017-01-23 14:37:42','2017-01-23 14:37:42',NULL,NULL),(1,40003,'2017-01-23 14:37:42','2017-01-23 14:37:42',NULL,NULL),(1,50001,'2017-01-23 14:37:42','2017-01-23 14:37:42',NULL,NULL),(1,50002,'2017-01-23 14:37:42','2017-01-23 14:37:42',NULL,NULL),(1,50003,'2017-01-23 14:37:42','2017-01-23 14:37:42',NULL,NULL);
/*!40000 ALTER TABLE `qr_rel_role_func` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_rel_user_role`
--

DROP TABLE IF EXISTS `qr_rel_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_rel_user_role` (
  `user_id` bigint(64) NOT NULL,
  `role_id` bigint(64) NOT NULL,
  `UPD_DATE` datetime default NULL,
  `INS_DATE` datetime default NULL,
  `INS_USER` bigint(64) default NULL,
  `UPD_USER` bigint(64) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_rel_user_role`
--

LOCK TABLES `qr_rel_user_role` WRITE;
/*!40000 ALTER TABLE `qr_rel_user_role` DISABLE KEYS */;
INSERT INTO `qr_rel_user_role` VALUES (1,1,'2016-11-22 11:03:38','2016-11-22 11:03:38',1,1),(1,2,'2016-11-22 11:03:38','2016-11-22 11:03:38',1,1);
/*!40000 ALTER TABLE `qr_rel_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_role`
--

DROP TABLE IF EXISTS `qr_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_role` (
  `ROLE_ID` bigint(64) NOT NULL auto_increment COMMENT '角色ID',
  `ROLE_NAME` varchar(50) NOT NULL COMMENT '角色名称',
  `ROLE_REMARK` varchar(200) default NULL COMMENT '角色描述',
  `UPD_DATE` datetime default NULL,
  `INS_DATE` datetime default NULL,
  `INS_USER` bigint(64) default NULL,
  `UPD_USER` bigint(64) default NULL,
  `ROLE_STATUS` varchar(10) default '0',
  PRIMARY KEY  (`ROLE_ID`),
  UNIQUE KEY `PKEY` (`ROLE_NAME`,`ROLE_STATUS`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_role`
--

LOCK TABLES `qr_role` WRITE;
/*!40000 ALTER TABLE `qr_role` DISABLE KEYS */;
INSERT INTO `qr_role` VALUES (1,'管理员','管理员','2017-01-23 14:37:42','2016-10-10 00:00:00',1,1,'0'),(2,'APP操作员','APP操作员','2017-01-23 14:37:51','2016-10-10 00:00:00',1,1,'0'),(3,'项目经理','项目经理','2016-11-17 21:11:07','2016-11-17 10:56:02',1,1,'0'),(4,'维修经理','维修经理','2016-11-17 11:08:57','2016-11-17 11:00:19',1,1,'0'),(5,'库管员','库管员','2016-11-17 11:01:46','2016-11-17 11:01:46',1,1,'0'),(19,'APP用户端','给客户使用的','2016-12-26 10:08:12','2016-12-26 10:08:12',1,1,'0');
/*!40000 ALTER TABLE `qr_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_user`
--

DROP TABLE IF EXISTS `qr_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_user` (
  `USER_ID` bigint(64) NOT NULL auto_increment COMMENT '用户ID',
  `USER_NAME` varchar(50) NOT NULL COMMENT '用户名称',
  `USER_FULLNAME` varchar(50) NOT NULL COMMENT '中文用户名',
  `USER_PWD` varchar(50) NOT NULL COMMENT '密码',
  `USER_SEX` varchar(10) default NULL COMMENT '性别',
  `USER_TEL1` varchar(20) default NULL COMMENT '电话1',
  `USER_TEL2` varchar(20) default NULL COMMENT '电话2',
  `USER_IDCARD` varchar(20) default NULL COMMENT '身份证号',
  `USER_ADDRESS` varchar(50) default NULL COMMENT '用户地址',
  `USER_REMARK` varchar(200) default NULL COMMENT '备注',
  `USER_STATUS` varchar(10) default '0' COMMENT '用户状态：0:启用(default)；1：停用',
  `UPD_DATE` datetime NOT NULL COMMENT '更新时间',
  `INS_DATE` datetime NOT NULL COMMENT '创建时间',
  `INS_USER` bigint(64) NOT NULL COMMENT '创建用户ID',
  `UPD_USER` bigint(64) NOT NULL COMMENT '更新用户ID',
  `LAT` decimal(16,6) default NULL,
  `LNG` decimal(16,6) default NULL,
  `PWD_CHANGE` varchar(1) NOT NULL default '0' COMMENT '密码是否变更过0；未变，1发送变更',
  PRIMARY KEY  (`USER_ID`),
  UNIQUE KEY `PKEY` (`USER_NAME`,`USER_STATUS`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_user`
--

LOCK TABLES `qr_user` WRITE;
/*!40000 ALTER TABLE `qr_user` DISABLE KEYS */;
INSERT INTO `qr_user` VALUES (1,'admin','管理员','sa1234','1','15948794508','15948794508','220701198312090909','吉林省长春市二道区长青街道二道区老百姓综合门诊','','0','2016-01-01 00:00:00','2016-01-01 00:00:00',1,1,43.911085,125.409644,'0');
/*!40000 ALTER TABLE `qr_user` ENABLE KEYS */;
UNLOCK TABLES;
