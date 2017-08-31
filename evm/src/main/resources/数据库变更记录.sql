--增加字段：是否变更密码
ALTER TABLE `evm`.`qr_user` 
ADD COLUMN `PWD_CHANGE` VARCHAR(1) NOT NULL DEFAULT '0' COMMENT '密码是否变更过0；未变，1发送变更' AFTER `LNG`;
--创建App权限-服务配置表
CREATE TABLE `evm`.`qr_cfg_func_service` (
  `SYSTEM_ID` BIGINT(64) NOT NULL COMMENT '系统ID',
  `SERVICE_ID` BIGINT(64) NOT NULL COMMENT '服务ID',
  `FUNCTION_ID` BIGINT(64) NOT NULL COMMENT '权限ID')
COMMENT = 'App权限-服务配置表';
ALTER TABLE `evm`.`qr_cfg_func_service` 
ADD UNIQUE INDEX `CFG_PK` (`SYSTEM_ID` ASC, `SERVICE_ID` ASC, `FUNCTION_ID` ASC);
--设备增加地址列
ALTER TABLE `evm`.`qr_deviceitem` 
ADD COLUMN `deviceitem_addr` VARCHAR(100) NULL COMMENT '地址' AFTER `UPD_USER`;
ALTER TABLE `evm`.`qr_rel_order_device` 
CHANGE COLUMN `device_id` `device_id` BIGINT(64) NULL DEFAULT '0' COMMENT '设备类型ID' ;
ALTER TABLE `evm`.`qr_rel_order_device` 
CHANGE COLUMN `work_cnt` `work_cnt` BIGINT(64) NULL DEFAULT NULL COMMENT '总数量' ;
ALTER TABLE `evm`.`qr_rel_order_device` 
CHANGE COLUMN `work_cnt` `work_cnt` BIGINT(64) NULL DEFAULT 0 COMMENT '总数量' ;
ALTER TABLE `evm`.`qr_deviceitem` 
CHANGE COLUMN `deviceitem_Name` `deviceitem_Name` VARCHAR(100) NULL COMMENT '设备名称' ;
--2017/02/20
--项目状态
ALTER TABLE `evm`.`qr_project` 
ADD COLUMN `PRO_STAT` VARCHAR(10) NULL COMMENT '项目状态：（维护期内，维护期外，终止）' AFTER `PRO_STATUS`;
--修改
UPDATE `evm`.`qr_dict_device_type` SET `text`='前端设备' WHERE `id`='1';
UPDATE `evm`.`qr_dict_device_type` SET `text`='后端设备' WHERE `id`='2';
ALTER TABLE `evm`.`qr_project` 
CHANGE COLUMN `PRO_STAT` `PRO_STAT` VARCHAR(10) NULL DEFAULT '1' COMMENT '项目状态：（维护期内，维护期外，终止）' ;
ALTER TABLE `evm`.`qr_deviceitem` 
ADD COLUMN `lat` DECIMAL(16,6) NULL DEFAULT NULL AFTER `deviceitem_addr`,
ADD COLUMN `lng` DECIMAL(16,6) NULL DEFAULT NULL AFTER `lat`;
--2017/02/21
INSERT INTO `evm`.`qr_cfg_func_service` (`SYSTEM_ID`, `SERVICE_ID`, `FUNCTION_ID`) VALUES ('300', '100312', '50003');

--2017/03/08  去索引
ALTER TABLE `evm`.`qr_project`  DROP INDEX `Project_no_UNIQUE` ;
--2017/03/10 加注释
ALTER TABLE `evm`.`qr_rel_order_file` CHANGE COLUMN `biz_type` `biz_type` VARCHAR(10) NULL DEFAULT NULL COMMENT '业务类型（1施工图片1，2竣工图片2）' ;
INSERT INTO `evm`.`qr_cfg_func_service` (`SYSTEM_ID`, `SERVICE_ID`, `FUNCTION_ID`) VALUES ('200', '320215', '40002');
INSERT INTO `evm`.`qr_cfg_func_service` (`SYSTEM_ID`, `SERVICE_ID`, `FUNCTION_ID`) VALUES ('300', '330310', '50002');
INSERT INTO `evm`.`qr_cfg_func_service` (`SYSTEM_ID`, `SERVICE_ID`, `FUNCTION_ID`) VALUES ('300', '330311', '50002');
ALTER TABLE `evm`.`qr_rel_order_file`  CHANGE COLUMN `file_path` `file_path` VARCHAR(1000) NULL DEFAULT NULL ;

-- ----------------------------
-- Table structure for qr_rel_morder_file
-- ----------------------------
DROP TABLE IF EXISTS `qr_rel_morder_file`;
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
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;
--2016/03/15
INSERT INTO `evm`.`qr_cfg_func_service` (`SYSTEM_ID`, `SERVICE_ID`, `FUNCTION_ID`) VALUES ('200', '320216', '40002');
--2016/03/16
INSERT INTO `evm`.`qr_cfg_func_service` (`SYSTEM_ID`, `SERVICE_ID`, `FUNCTION_ID`) VALUES ('300', '330312', '50002');

--2017/03/28
INSERT INTO `evm`.`qr_function` (`function_id`, `function_name`, `UPD_DATE`, `INS_DATE`, `INS_USER`, `UPD_USER`) VALUES ('60001', 'APP端-订单设备维护', '2016-11-14 10:58:20', '2016-11-14 10:58:20', '1', '1');
UPDATE `evm`.`qr_cfg_func_service` SET `SYSTEM_ID`='100', `FUNCTION_ID`='60001' WHERE `SYSTEM_ID`='200' and`SERVICE_ID`='320212' and`FUNCTION_ID`='40002';
UPDATE `evm`.`qr_cfg_func_service` SET `SYSTEM_ID`='100', `FUNCTION_ID`='60001' WHERE `SYSTEM_ID`='200' and`SERVICE_ID`='320213' and`FUNCTION_ID`='40002';
UPDATE `evm`.`qr_cfg_func_service` SET `SYSTEM_ID`='100', `FUNCTION_ID`='60001' WHERE `SYSTEM_ID`='200' and`SERVICE_ID`='320214' and`FUNCTION_ID`='40002';
INSERT INTO `evm`.`qr_cfg_func_service` (`SYSTEM_ID`, `SERVICE_ID`, `FUNCTION_ID`) VALUES ('300', '100206', '50003');

INSERT INTO `evm`.`qr_cfg_func_service` (`SYSTEM_ID`, `SERVICE_ID`, `FUNCTION_ID`) VALUES ('300', '320212', '60001');
INSERT INTO `evm`.`qr_cfg_func_service` (`SYSTEM_ID`, `SERVICE_ID`, `FUNCTION_ID`) VALUES ('200', '320212', '60001');
INSERT INTO `evm`.`qr_cfg_func_service` (`SYSTEM_ID`, `SERVICE_ID`, `FUNCTION_ID`) VALUES ('300', '320213', '60001');
INSERT INTO `evm`.`qr_cfg_func_service` (`SYSTEM_ID`, `SERVICE_ID`, `FUNCTION_ID`) VALUES ('200', '320213', '60001');
INSERT INTO `evm`.`qr_cfg_func_service` (`SYSTEM_ID`, `SERVICE_ID`, `FUNCTION_ID`) VALUES ('200', '320214', '60001');
INSERT INTO `evm`.`qr_cfg_func_service` (`SYSTEM_ID`, `SERVICE_ID`, `FUNCTION_ID`) VALUES ('300', '320214', '60001');
INSERT INTO `evm`.`qr_cfg_func_service` (`SYSTEM_ID`, `SERVICE_ID`, `FUNCTION_ID`) VALUES ('100', '310205', '30004');
INSERT INTO `evm`.`qr_cfg_func_service` (`SYSTEM_ID`, `SERVICE_ID`, `FUNCTION_ID`) VALUES ('300', '310205', '30004');
UPDATE `evm`.`qr_cfg_func_service` SET `FUNCTION_ID`='30005' WHERE `SYSTEM_ID`='300' and`SERVICE_ID`='310305' and`FUNCTION_ID`='50002';
INSERT INTO `evm`.`qr_cfg_func_service` (`SYSTEM_ID`, `SERVICE_ID`, `FUNCTION_ID`) VALUES ('100', '310305', '30005');

INSERT INTO `evm`.`qr_cfg_func_service` (`SYSTEM_ID`, `SERVICE_ID`, `FUNCTION_ID`) VALUES ('100', '100300', '30003');
INSERT INTO `evm`.`qr_cfg_func_service` (`SYSTEM_ID`, `SERVICE_ID`, `FUNCTION_ID`) VALUES ('100', '100301', '30003');
INSERT INTO `evm`.`qr_cfg_func_service` (`SYSTEM_ID`, `SERVICE_ID`, `FUNCTION_ID`) VALUES ('100', '100302', '30003');
INSERT INTO `evm`.`qr_cfg_func_service` (`SYSTEM_ID`, `SERVICE_ID`, `FUNCTION_ID`) VALUES ('100', '100312', '30003');
INSERT INTO `evm`.`qr_cfg_func_service` (`SYSTEM_ID`, `SERVICE_ID`, `FUNCTION_ID`) VALUES ('100', '100303', '30003');

ALTER TABLE `evm`.`qr_cfg_func_service` 

COMMENT = 'App权限-服务配置表100	用户APP\n200	工程APP\n300	维修APP\n400	工程、维修/维护管理系统\n500	MIS系统' ;

--2017/03/31
truncate  table qr_cfg_func_service;
INSERT INTO `qr_cfg_func_service` VALUES (100,100100,30003),(100,100200,30003),(100,100201,30003),(100,100202,30003),(100,100203,30003),(100,100205,30003),(100,100206,30003),(100,100300,30003),(100,100301,30003),(100,100302,30003),(100,100303,30003),(100,100312,30003),(100,300001,30001),(100,300002,30002),(100,310205,30004),(100,320212,60001),(100,320213,60001),(100,320214,60001),(200,100100,40003),(200,100200,40003),(200,100201,40003),(200,100202,40003),(200,100203,40003),(200,100204,40003),(200,100205,40003),(200,100206,40003),(200,300001,40001),(200,310205,40002),(200,320206,40002),(200,320207,40002),(200,320208,40002),(200,320209,40002),(200,320210,40002),(200,320215,40002),(200,320216,40002),(300,100100,50003),(300,100203,50003),(300,100205,50003),(300,100206,50003),(300,100300,50003),(300,100302,50003),(300,100312,50003),(300,300001,50001),(300,310205,50002),(300,310305,30005),(300,330306,50002),(300,330307,50002),(300,330308,50002),(300,330309,50002),(300,330310,50002),(300,330311,50002),(300,330312,50002);

CREATE TABLE `qr_dict_appservice` (
  `id` bigint(64) NOT NULL,
  `text` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
INSERT INTO `qr_dict_appservice` VALUES (100100,'查询全部订单统计'),(100200,'查询全部工程订单信息'),(100201,'模糊查询某一订单的工程信息。'),(100202,'查询某一个工程订单的详细信息。'),(100203,'查询某一个工程订单的设备信息。'),(100205,'查询某一个工程订单中的设备的属性信息。'),(100206,'查询某一工程预先设置的设备种类信息'),(100300,'查询全部维修/维护订单信息。'),(100301,'模糊查询某一订单的维修/维护信息。'),(100302,'查询某一个维修/维护订单的详细信息。'),(100303,'查询某一个订单的全部维修记录信息。'),(100312,'查询某一个维修/维护订单的详细信息。'),(300001,'用户登录'),(300002,'用户密码修改'),(310205,'修改某一工程订单的评价信息。'),(310305,'修改某一维修/维护订单的评价信息。'),(320206,'修改工程开工时间。'),(320207,'修改工程开工进度。'),(320208,'增加工程中某一订单的设备种类。'),(320209,'删除工程中某一订单的设备种类'),(320210,'修改工程中某一订单的设备的数量信息'),(320212,'新增设备'),(320213,'删除设备'),(320214,'更新设备'),(320215,'上传工程施工图片。'),(320216,'删除工程图片'),(330306,'修改维修开工时间。'),(330307,'修改维修进度。'),(330308,'修改故障类型'),(330309,'修改维修结果描述'),(330310,'上传维修图片。'),(330311,'删除维修图片'),(330312,'修改维修人数');
