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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='App权限-服务配置表100	用户APP\n200	工程APP\n300	维修APP\n400	工程、维修/维护管理系统\n500	MIS系统';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `qr_custom`
--

DROP TABLE IF EXISTS `qr_custom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_custom` (
  `CUS_ID` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '客户编号',
  `CUS_NAME` varchar(50) NOT NULL COMMENT '客户姓名',
  `CUS_CARID` varchar(50) DEFAULT NULL COMMENT '客户身份证号',
  `CUS_TEL1` varchar(50) NOT NULL COMMENT '客户电话1',
  `CUS_TEL2` varchar(50) NOT NULL COMMENT '客户电话2',
  `CUS_SEX` varchar(10) DEFAULT NULL COMMENT '客户性别',
  `CUS_ADDRESS` varchar(200) DEFAULT NULL COMMENT '客户地址',
  `CUS_SUBSTATION` varchar(200) DEFAULT NULL COMMENT '所属分局',
  `CUS_POLICESTATION` varchar(200) DEFAULT NULL COMMENT '所属派出所 ',
  `CUS_TYPE` varchar(10) DEFAULT NULL COMMENT '用户类别',
  `CUS_REMARK` varchar(200) DEFAULT NULL COMMENT '客户备注',
  `UPD_DATE` datetime DEFAULT NULL,
  `INS_DATE` datetime NOT NULL,
  `INS_USER` bigint(64) NOT NULL,
  `UPD_USER` bigint(64) NOT NULL,
  `CUS_STATUS` varchar(10) NOT NULL DEFAULT '0',
  `LAT` decimal(16,6) DEFAULT NULL,
  `LNG` decimal(16,6) DEFAULT NULL,
  `USER_ID` bigint(64) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`CUS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='客户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `qr_device_property`
--

DROP TABLE IF EXISTS `qr_device_property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_device_property` (
  `device_id` bigint(64) DEFAULT NULL COMMENT '设备ID',
  `property_id` bigint(64) NOT NULL AUTO_INCREMENT,
  `propert_name` varchar(100) DEFAULT NULL COMMENT '属性名',
  `UPD_DATE` datetime DEFAULT NULL COMMENT '更新时间',
  `INS_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `INS_USER` bigint(64) DEFAULT NULL COMMENT '创建用户ID',
  `UPD_USER` bigint(64) DEFAULT NULL COMMENT '更新用户ID',
  PRIMARY KEY (`property_id`),
  UNIQUE KEY `PKEY` (`device_id`,`propert_name`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='设备属性表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `qr_device_type`
--

DROP TABLE IF EXISTS `qr_device_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_device_type` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '设备ID',
  `text` varchar(100) DEFAULT NULL COMMENT '设备名称',
  `class_type` varchar(10) DEFAULT NULL COMMENT '大分类类型（设备，线材，辅助设备）',
  `UPD_DATE` datetime DEFAULT NULL COMMENT '更新时间',
  `INS_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `INS_USER` bigint(64) DEFAULT NULL COMMENT '创建用户ID',
  `UPD_USER` bigint(64) DEFAULT NULL COMMENT '更新用户ID',
  `DEV_STATUS` varchar(10) DEFAULT '0' COMMENT '删除标示 ，1已删',
  PRIMARY KEY (`id`),
  UNIQUE KEY `text_UNIQUE` (`text`,`DEV_STATUS`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='设备类型表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `qr_deviceitem`
--

DROP TABLE IF EXISTS `qr_deviceitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_deviceitem` (
  `order_id` bigint(64) DEFAULT NULL COMMENT '订单ID',
  `deviceitem_id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '设备ID',
  `device_type_id` bigint(64) NOT NULL COMMENT '设备类型ID',
  `deviceitem_Name` varchar(100) DEFAULT NULL COMMENT '设备名称',
  `deviceitem_status` varchar(10) DEFAULT '0' COMMENT '删除标识',
  `deviceitem_uid` varchar(100) DEFAULT NULL COMMENT '设备唯一标示，可以为空',
  `UPD_DATE` datetime DEFAULT NULL,
  `INS_DATE` datetime DEFAULT NULL,
  `INS_USER` bigint(64) DEFAULT NULL,
  `UPD_USER` bigint(64) DEFAULT NULL,
  `deviceitem_addr` varchar(100) DEFAULT NULL COMMENT '地址',
  `lat` decimal(16,6) DEFAULT NULL,
  `lng` decimal(16,6) DEFAULT NULL,
  PRIMARY KEY (`deviceitem_id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8 COMMENT='设备';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `qr_deviceitem_property`
--

DROP TABLE IF EXISTS `qr_deviceitem_property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_deviceitem_property` (
  `deviceitem_property_id` bigint(64) NOT NULL AUTO_INCREMENT,
  `devicetype_property_id` bigint(64) DEFAULT NULL COMMENT '设备属性ID',
  `deviceitem_property_value` varchar(145) DEFAULT NULL COMMENT '设备属性值',
  `deviceItem_id` bigint(64) DEFAULT NULL COMMENT '设备ID',
  `UPD_DATE` datetime DEFAULT NULL COMMENT '更新时间',
  `INS_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `INS_USER` bigint(64) DEFAULT NULL COMMENT '创建用户ID',
  `UPD_USER` bigint(64) DEFAULT NULL COMMENT '更新用户ID',
  PRIMARY KEY (`deviceitem_property_id`)
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8 COMMENT='设备属性';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `qr_dict_acceptstatus`
--

DROP TABLE IF EXISTS `qr_dict_acceptstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_dict_acceptstatus` (
  `id` int(11) NOT NULL,
  `text` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='验收状态';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `qr_dict_appservice`
--

DROP TABLE IF EXISTS `qr_dict_appservice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_dict_appservice` (
  `id` bigint(64) NOT NULL,
  `text` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `qr_dict_buildtype`
--

DROP TABLE IF EXISTS `qr_dict_buildtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_dict_buildtype` (
  `id` int(11) NOT NULL,
  `text` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `qr_dict_custom_type`
--

DROP TABLE IF EXISTS `qr_dict_custom_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_dict_custom_type` (
  `id` int(11) NOT NULL,
  `text` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户类型字典表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `qr_dict_device_type`
--

DROP TABLE IF EXISTS `qr_dict_device_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_dict_device_type` (
  `id` int(11) NOT NULL,
  `text` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备大类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `qr_dict_maintainstatus`
--

DROP TABLE IF EXISTS `qr_dict_maintainstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_dict_maintainstatus` (
  `id` int(11) NOT NULL,
  `text` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='维修状态';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `qr_dict_projecttype`
--

DROP TABLE IF EXISTS `qr_dict_projecttype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_dict_projecttype` (
  `id` int(11) NOT NULL,
  `text` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目类型';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `qr_dict_workcheckresult`
--

DROP TABLE IF EXISTS `qr_dict_workcheckresult`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_dict_workcheckresult` (
  `id` int(11) NOT NULL,
  `text` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='施工内检结果';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `qr_dict_workstatus`
--

DROP TABLE IF EXISTS `qr_dict_workstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_dict_workstatus` (
  `id` int(11) NOT NULL,
  `text` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='/施工状态';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `qr_dict_worktype`
--

DROP TABLE IF EXISTS `qr_dict_worktype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_dict_worktype` (
  `id` int(11) NOT NULL,
  `text` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='施工类型';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `qr_faulttype`
--

DROP TABLE IF EXISTS `qr_faulttype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_faulttype` (
  `FAULTTYPE_ID` bigint(64) NOT NULL AUTO_INCREMENT,
  `FAULTTYPE_NO` varchar(50) DEFAULT NULL,
  `FAULTTYPE_NAME` varchar(50) NOT NULL,
  `UPD_DATE` datetime NOT NULL COMMENT '更新时间',
  `INS_DATE` datetime NOT NULL COMMENT '创建时间',
  `INS_USER` bigint(64) NOT NULL COMMENT '创建用户ID',
  `UPD_USER` bigint(64) NOT NULL COMMENT '更新用户ID',
  PRIMARY KEY (`FAULTTYPE_ID`),
  UNIQUE KEY `FAULTTYPE_NAME_UNIQUE` (`FAULTTYPE_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `qr_function`
--

DROP TABLE IF EXISTS `qr_function`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_function` (
  `function_id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '功能ID',
  `function_name` varchar(50) DEFAULT NULL COMMENT '功能名称',
  `function_desc` varchar(200) DEFAULT NULL,
  `UPD_DATE` datetime DEFAULT NULL,
  `INS_DATE` datetime DEFAULT NULL,
  `INS_USER` bigint(64) DEFAULT NULL,
  `UPD_USER` bigint(64) DEFAULT NULL,
  PRIMARY KEY (`function_id`)
) ENGINE=InnoDB AUTO_INCREMENT=60002 DEFAULT CHARSET=utf8 COMMENT='权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `qr_log`
--

DROP TABLE IF EXISTS `qr_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_log` (
  `log_id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `log_content` varchar(500) DEFAULT NULL COMMENT '日志内容',
  `log_para` varchar(600) DEFAULT NULL COMMENT '日志参数',
  `INS_DATE` datetime DEFAULT NULL,
  `INS_USER` bigint(64) DEFAULT NULL,
  `log_biz_type` varchar(10) DEFAULT '0' COMMENT '日志业务类型1客户表，2项目表，3订单表，4维修订单',
  `log_biz_ID` bigint(64) DEFAULT '0' COMMENT '业务ID',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=323 DEFAULT CHARSET=utf8 COMMENT='日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `qr_morder`
--

DROP TABLE IF EXISTS `qr_morder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_morder` (
  `ORDER_ID` bigint(64) NOT NULL COMMENT '订单编号',
  `MORDER_ID` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '运维订单编号',
  `MORDER_PM` bigint(64) NOT NULL COMMENT '运维经理ID',
  `MAINTAIN_DATE` datetime DEFAULT NULL COMMENT '派修时间',
  `FAULT_DESC` varchar(200) DEFAULT NULL COMMENT '故障描述',
  `APPOINTMENT_DATE` datetime DEFAULT NULL COMMENT '预约时间',
  `PLAN_END_DATE` datetime DEFAULT NULL COMMENT '计划完成时间',
  `MWORKER_CNT` bigint(64) DEFAULT NULL COMMENT '维修人数',
  `MAINTAIN_BEGIN_DATE` datetime DEFAULT NULL COMMENT '维修开始日期',
  `MAINTAIN_STATUS` varchar(10) DEFAULT NULL COMMENT '维修状态  创建:0 预约1, 维修中2 维修完成3 回访完成4',
  `MAINTAIN_SPEED` varchar(20) DEFAULT NULL COMMENT '维修进度',
  `FAULT_TYPE` varchar(10) DEFAULT NULL COMMENT '故障类别',
  `MAINTAIN_PM` bigint(64) DEFAULT NULL COMMENT '维修负责人ID',
  `ACCEPT_STATUS` varchar(10) DEFAULT NULL COMMENT '验收状态',
  `ACCEPTECALL_MAN` varchar(20) DEFAULT NULL COMMENT '验收回访人',
  `CALLBACK_DATE` datetime DEFAULT NULL COMMENT '验收回访时间',
  `SOLUTION` varchar(200) DEFAULT NULL COMMENT '解决方案',
  `MAINTAIN_RESULTDESC` varchar(200) DEFAULT NULL COMMENT '维修结果描述',
  `MAINTAIN_REMARK` varchar(200) DEFAULT NULL COMMENT '备注',
  `USER_SCORE` varchar(20) DEFAULT NULL COMMENT '用户评分',
  `USER_PROPOSAL` varchar(200) DEFAULT NULL COMMENT '用户评价',
  `UPD_DATE` datetime NOT NULL COMMENT '更新时间',
  `INS_DATE` datetime NOT NULL COMMENT '创建时间',
  `INS_USER` bigint(64) NOT NULL COMMENT '创建用户ID',
  `UPD_USER` bigint(64) NOT NULL COMMENT '订单编号',
  `MORDER_STATUS` varchar(10) DEFAULT '0' COMMENT '是否删除1',
  `MORDER_NO` varchar(45) DEFAULT NULL,
  `LAST_UPDDATE` datetime DEFAULT NULL COMMENT '最后一次进度更新时间',
  PRIMARY KEY (`MORDER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='维修订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `qr_order`
--

DROP TABLE IF EXISTS `qr_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_order` (
  `order_id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `ORDER_NO` varchar(100) DEFAULT NULL COMMENT '订单编号',
  `PROJECT_ID` bigint(64) DEFAULT NULL COMMENT '项目ID',
  `WORK_PM_ID` bigint(64) DEFAULT NULL COMMENT '施工负责人ID',
  `WORK_CNT` bigint(64) DEFAULT NULL COMMENT '施工人数',
  `WORK_ADDRESS` varchar(100) DEFAULT NULL,
  `WORK_COMPANY` varchar(100) DEFAULT NULL COMMENT '施工单位',
  `WORK_COMPANY_QUALIFIED` varchar(100) DEFAULT NULL COMMENT '单位资质情况 ',
  `WORK_TYPE` varchar(10) DEFAULT '0' COMMENT '施工类型',
  `WORK_DAYS` bigint(64) DEFAULT '0' COMMENT '施工天数',
  `PLAN_BEGIN_DATE` datetime DEFAULT NULL COMMENT '计划施工时间',
  `PLAN_END_DATE` datetime DEFAULT NULL COMMENT '计划完工时间',
  `STORE_PM_ID` bigint(64) DEFAULT NULL COMMENT '出库联系人',
  `PLAN_OUTSTORE_DATE` datetime DEFAULT NULL COMMENT '计划出库时间',
  `WORK_STATUS` varchar(10) DEFAULT '0' COMMENT '施工状态:创建0预约1施工中2施工完成3验收完成4',
  `WORK_BEGIN_DATE` datetime DEFAULT NULL COMMENT '实际施工时间',
  `WORK_END_DATE` datetime DEFAULT NULL COMMENT '实际施工完成时间',
  `WORK_PROGRESS` varchar(100) DEFAULT '0' COMMENT '施工进度',
  `LAST_WORKUPD_DATE` datetime DEFAULT NULL COMMENT '最后一次施工更新时间',
  `CHECKER_ID` bigint(64) DEFAULT NULL COMMENT '内检人员',
  `CHECK_RESULT` varchar(100) DEFAULT NULL COMMENT '施工内检结果',
  `CHECK_DATE` datetime DEFAULT NULL COMMENT '内检时间',
  `USER_RATING` varchar(100) DEFAULT '0' COMMENT '用户评分',
  `USER_APPRAISE` varchar(200) DEFAULT NULL COMMENT '用户评价',
  `WORK_REMARK` varchar(200) DEFAULT NULL COMMENT '备注',
  `UPD_DATE` datetime DEFAULT NULL COMMENT '更新时间',
  `INS_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `INS_USER` bigint(64) DEFAULT NULL COMMENT '创建用户ID',
  `UPD_USER` bigint(64) DEFAULT NULL COMMENT '更新用户ID',
  `order_status` varchar(10) DEFAULT '0' COMMENT '1时删除',
  `lat` decimal(16,6) DEFAULT NULL,
  `lng` decimal(16,6) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  UNIQUE KEY `order_no` (`ORDER_NO`)
) ENGINE=InnoDB AUTO_INCREMENT=157 DEFAULT CHARSET=utf8 COMMENT='订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `qr_project`
--

DROP TABLE IF EXISTS `qr_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_project` (
  `PROJECT_ID` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '项目ID',
  `PROJECT_NO` varchar(100) DEFAULT NULL,
  `PROJECT_NAME` varchar(50) NOT NULL COMMENT '项目名称',
  `CUS_ID` bigint(64) NOT NULL COMMENT '客户ID-外键关联客户表',
  `CM_ID` bigint(64) DEFAULT NULL COMMENT '客户经理ID-外键关联用户表',
  `PM_ID` bigint(64) DEFAULT NULL COMMENT '项目经理ID-外键关联用户表',
  `PROJECT_TYPE` varchar(10) DEFAULT NULL COMMENT '项目分类 ',
  `CONTRACT_ID` varchar(100) NOT NULL COMMENT '合同编号',
  `CONTRACT_SUM` decimal(16,2) DEFAULT NULL COMMENT '合同金额',
  `SIGN_DATE` datetime DEFAULT NULL COMMENT '签订日期',
  `PAY_TYPE` varchar(10) DEFAULT NULL COMMENT '缴费类型 ',
  `WORK_END_DATE` datetime DEFAULT NULL COMMENT '工期截止日期',
  `WORK_START_DATE` datetime DEFAULT NULL COMMENT '开工日期 ',
  `YEAR_PAY` decimal(16,2) DEFAULT NULL COMMENT '年服务费',
  `SERVICE_END_DATE` datetime DEFAULT NULL COMMENT '服务截止日期 ',
  `PAY_DATE` datetime DEFAULT NULL COMMENT '缴费日期',
  `BUILD_TYPE` varchar(10) DEFAULT '0' COMMENT '建设方式',
  `JOIN_DATE` datetime DEFAULT NULL COMMENT '入网日期 ',
  `STOP_DATE` datetime DEFAULT NULL COMMENT '停机日期 ',
  `PROJECT_REMARK` varchar(200) DEFAULT NULL COMMENT '备注',
  `UPD_DATE` datetime NOT NULL COMMENT '更新时间',
  `INS_DATE` datetime NOT NULL COMMENT '创建时间',
  `INS_USER` bigint(64) NOT NULL,
  `UPD_USER` bigint(64) NOT NULL,
  `PRO_STATUS` varchar(10) DEFAULT '0' COMMENT '删除标示：1 删除',
  `PRO_STAT` varchar(10) DEFAULT '1' COMMENT '项目状态：（维护期内，维护期外，终止）',
  PRIMARY KEY (`PROJECT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8 COMMENT='项目表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `qr_rel_morder_file`
--

DROP TABLE IF EXISTS `qr_rel_morder_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_rel_morder_file` (
  `file_id` bigint(64) NOT NULL AUTO_INCREMENT,
  `morder_id` bigint(64) NOT NULL,
  `file_name` varchar(150) DEFAULT NULL,
  `file_type` varchar(10) DEFAULT NULL,
  `file_path` varchar(1000) DEFAULT NULL,
  `file_size` bigint(64) DEFAULT NULL,
  `biz_type` varchar(10) DEFAULT NULL COMMENT '业务类型（1施工图片1，2竣工图片2）',
  `INS_DATE` datetime NOT NULL,
  `INS_USER` bigint(64) NOT NULL,
  `UPD_DATE` datetime NOT NULL COMMENT '更新时间',
  `UPD_USER` bigint(64) NOT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `qr_rel_order_device`
--

DROP TABLE IF EXISTS `qr_rel_order_device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_rel_order_device` (
  `order_id` bigint(64) DEFAULT '0' COMMENT '订单ID',
  `device_id` bigint(64) DEFAULT '0' COMMENT '设备类型ID',
  `store_cnt` bigint(64) DEFAULT '0' COMMENT '库存数量',
  `plan_cnt` bigint(64) DEFAULT '0' COMMENT '可领数量',
  `work_cnt` bigint(64) DEFAULT '0' COMMENT '总数量',
  `INS_DATE` datetime NOT NULL,
  `INS_USER` bigint(64) NOT NULL,
  `UPD_DATE` datetime NOT NULL COMMENT '更新时间',
  `UPD_USER` bigint(64) NOT NULL,
  UNIQUE KEY `order_device_ID` (`order_id`,`device_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单设备关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `qr_rel_order_file`
--

DROP TABLE IF EXISTS `qr_rel_order_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_rel_order_file` (
  `file_id` bigint(64) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(64) NOT NULL,
  `file_name` varchar(150) DEFAULT NULL,
  `file_type` varchar(10) DEFAULT NULL,
  `file_path` varchar(1000) DEFAULT NULL,
  `file_size` bigint(64) DEFAULT NULL,
  `biz_type` varchar(10) DEFAULT NULL COMMENT '业务类型（1施工图片1，2竣工图片2）',
  `INS_DATE` datetime NOT NULL,
  `INS_USER` bigint(64) NOT NULL,
  `UPD_DATE` datetime NOT NULL COMMENT '更新时间',
  `UPD_USER` bigint(64) NOT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `qr_rel_project_device`
--

DROP TABLE IF EXISTS `qr_rel_project_device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_rel_project_device` (
  `PD_ID` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '唯一ID',
  `project_id` bigint(64) DEFAULT NULL COMMENT '项目ID',
  `Device_TYPE_id` bigint(64) DEFAULT NULL COMMENT '设备ID',
  `Device_Cnt` bigint(64) DEFAULT NULL COMMENT '消耗设备数量',
  `UPD_DATE` datetime DEFAULT NULL COMMENT '更新时间',
  `INS_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `INS_USER` bigint(64) DEFAULT NULL COMMENT '创建用户ID',
  `UPD_USER` bigint(64) DEFAULT NULL COMMENT '更新用户ID',
  UNIQUE KEY `PD_ID_UNIQUE` (`PD_ID`),
  UNIQUE KEY `Project_device_id` (`project_id`,`Device_TYPE_id`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8 COMMENT='项目设备关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

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
-- Table structure for table `qr_rel_role_func`
--

DROP TABLE IF EXISTS `qr_rel_role_func`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_rel_role_func` (
  `role_id` bigint(64) DEFAULT NULL COMMENT '角色ID',
  `function_id` bigint(64) DEFAULT NULL COMMENT '权限ID',
  `UPD_DATE` datetime DEFAULT NULL,
  `INS_DATE` datetime DEFAULT NULL,
  `INS_USER` bigint(64) DEFAULT NULL,
  `UPD_USER` bigint(64) DEFAULT NULL,
  UNIQUE KEY `PKEY` (`role_id`,`function_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `qr_rel_user_role`
--

DROP TABLE IF EXISTS `qr_rel_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_rel_user_role` (
  `user_id` bigint(64) NOT NULL,
  `role_id` bigint(64) NOT NULL,
  `UPD_DATE` datetime DEFAULT NULL,
  `INS_DATE` datetime DEFAULT NULL,
  `INS_USER` bigint(64) DEFAULT NULL,
  `UPD_USER` bigint(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `qr_role`
--

DROP TABLE IF EXISTS `qr_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_role` (
  `ROLE_ID` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `ROLE_NAME` varchar(50) NOT NULL COMMENT '角色名称',
  `ROLE_REMARK` varchar(200) DEFAULT NULL COMMENT '角色描述',
  `UPD_DATE` datetime DEFAULT NULL,
  `INS_DATE` datetime DEFAULT NULL,
  `INS_USER` bigint(64) DEFAULT NULL,
  `UPD_USER` bigint(64) DEFAULT NULL,
  `ROLE_STATUS` varchar(10) DEFAULT '0',
  PRIMARY KEY (`ROLE_ID`),
  UNIQUE KEY `PKEY` (`ROLE_NAME`,`ROLE_STATUS`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `qr_user`
--

DROP TABLE IF EXISTS `qr_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_user` (
  `USER_ID` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `USER_NAME` varchar(50) NOT NULL COMMENT '用户名称',
  `USER_FULLNAME` varchar(50) NOT NULL COMMENT '中文用户名',
  `USER_PWD` varchar(50) NOT NULL COMMENT '密码',
  `USER_SEX` varchar(10) DEFAULT NULL COMMENT '性别',
  `USER_TEL1` varchar(20) DEFAULT NULL COMMENT '电话1',
  `USER_TEL2` varchar(20) DEFAULT NULL COMMENT '电话2',
  `USER_IDCARD` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `USER_ADDRESS` varchar(50) DEFAULT NULL COMMENT '用户地址',
  `USER_REMARK` varchar(200) DEFAULT NULL COMMENT '备注',
  `USER_STATUS` varchar(10) DEFAULT '0' COMMENT '用户状态：0:启用(default)；1：停用',
  `UPD_DATE` datetime NOT NULL COMMENT '更新时间',
  `INS_DATE` datetime NOT NULL COMMENT '创建时间',
  `INS_USER` bigint(64) NOT NULL COMMENT '创建用户ID',
  `UPD_USER` bigint(64) NOT NULL COMMENT '更新用户ID',
  `LAT` decimal(16,6) DEFAULT NULL,
  `LNG` decimal(16,6) DEFAULT NULL,
  `PWD_CHANGE` varchar(1) NOT NULL DEFAULT '0' COMMENT '密码是否变更过0；未变，1发送变更',
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `PKEY` (`USER_NAME`,`USER_STATUS`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-06 10:47:10
