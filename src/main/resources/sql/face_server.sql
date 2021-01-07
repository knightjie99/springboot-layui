/*
Navicat MySQL Data Transfer

Source Server         : 47.100.166.186
Source Server Version : 50505
Source Host           : 47.100.166.186:3306
Source Database       : face_server

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2021-01-07 20:13:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bus_attend
-- ----------------------------
DROP TABLE IF EXISTS `bus_attend`;
CREATE TABLE `bus_attend` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '考勤管理表',
  `staff_id` int(11) DEFAULT NULL COMMENT '员工ID',
  `staff_name` varchar(20) DEFAULT NULL COMMENT '员工名称',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门ID',
  `attend_date` datetime DEFAULT NULL COMMENT '考勤日期(天)',
  `status` int(2) DEFAULT NULL COMMENT '考勤状态',
  `attend_hour` double(3,1) DEFAULT NULL COMMENT '当天工时',
  `on_time` datetime DEFAULT NULL COMMENT '签到时间',
  `on_gate` int(11) DEFAULT NULL COMMENT '签到闸机ID',
  `on_photo` varchar(50) DEFAULT NULL COMMENT '签到图片',
  `off_time` datetime DEFAULT NULL COMMENT '签退时间',
  `off_gate` int(11) DEFAULT NULL COMMENT '签退闸机ID',
  `off_photo` varchar(50) DEFAULT NULL COMMENT '签退照片',
  `belong_id` int(11) DEFAULT NULL COMMENT '所属用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=853 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bus_attend
-- ----------------------------

-- ----------------------------
-- Table structure for bus_attend_1
-- ----------------------------
DROP TABLE IF EXISTS `bus_attend_1`;
CREATE TABLE `bus_attend_1` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '考勤管理表',
  `staff_id` int(11) DEFAULT NULL COMMENT '员工ID',
  `staff_name` varchar(20) DEFAULT NULL COMMENT '员工名称',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门ID',
  `attend_date` datetime DEFAULT NULL COMMENT '考勤日期(天)',
  `attend_hour` double(3,1) DEFAULT NULL COMMENT '当天工时',
  `on_time` datetime DEFAULT NULL COMMENT '签到时间',
  `on_gate` int(11) DEFAULT NULL COMMENT '签到闸机ID',
  `on_photo` varchar(50) DEFAULT NULL COMMENT '签到图片',
  `off_time` datetime DEFAULT NULL COMMENT '签退时间',
  `off_gate` int(11) DEFAULT NULL COMMENT '签退闸机ID',
  `off_photo` varchar(50) DEFAULT NULL COMMENT '签退照片',
  `belong_id` int(11) DEFAULT NULL COMMENT '所属用户ID',
  `on_status` int(2) DEFAULT NULL COMMENT '上班考勤状态',
  `off_status` int(2) DEFAULT NULL COMMENT '下班考勤状态',
  `overtime_hours` double(3,1) NOT NULL DEFAULT 0.0,
  `valid_attend` int(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bus_attend_1
-- ----------------------------
INSERT INTO `bus_attend_1` VALUES ('1', '56', 'aaa', '16', '2020-12-29 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('2', '57', 'bbb', '16', '2020-12-29 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('3', '64', '黄超强', '16', '2020-12-29 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('4', '82', '李经留', '16', '2020-12-29 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('5', '83', '谢谢2', '16', '2020-12-29 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('6', '84', '汪志杰', '16', '2020-12-29 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('7', '85', '樊刚', '16', '2020-12-29 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('8', '86', '尹克强', '16', '2020-12-29 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('9', '56', 'aaa', '16', '2020-12-30 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('10', '57', 'bbb', '16', '2020-12-30 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('11', '64', '黄超强', '16', '2020-12-31 09:51:47', '6.4', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('12', '82', '李经留', '16', '2020-12-30 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('13', '83', '谢谢2', '16', '2020-12-30 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('14', '84', '汪志杰', '16', '2020-12-30 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('15', '85', '樊刚', '16', '2020-12-30 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('16', '86', '尹克强', '16', '2020-12-30 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('17', '56', 'aaa', '16', '2020-12-31 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('18', '57', 'bbb', '16', '2020-12-31 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('19', '64', '黄超强', '16', '2020-12-31 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('20', '82', '李经留', '16', '2020-12-31 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('21', '83', '谢谢2', '16', '2020-12-31 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('22', '84', '汪志杰', '16', '2020-12-31 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('23', '85', '樊刚', '16', '2020-12-31 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('24', '86', '尹克强', '16', '2020-12-31 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('25', '56', 'aaa', '16', '2021-01-01 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('26', '57', 'bbb', '16', '2021-01-01 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('27', '64', '黄超强', '16', '2021-01-01 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('28', '82', '李经留', '16', '2021-01-01 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('29', '83', '谢谢2', '16', '2021-01-01 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('30', '84', '汪志杰', '16', '2021-01-01 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('31', '85', '樊刚', '16', '2021-01-01 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('32', '86', '尹克强', '16', '2021-01-01 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('33', '56', 'aaa', '16', '2021-01-02 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('34', '57', 'bbb', '16', '2021-01-02 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('35', '64', '黄超强', '16', '2021-01-02 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('36', '82', '李经留', '16', '2021-01-02 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('37', '83', '谢谢2', '16', '2021-01-02 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('38', '84', '汪志杰', '16', '2021-01-02 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('39', '85', '樊刚', '16', '2021-01-02 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('40', '86', '尹克强', '16', '2021-01-02 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('41', '56', 'aaa', '16', '2021-01-03 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('42', '57', 'bbb', '16', '2021-01-03 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('43', '64', '黄超强', '16', '2021-01-04 09:51:49', '7.9', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('44', '82', '李经留', '16', '2021-01-03 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('45', '83', '谢谢2', '16', '2021-01-03 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('46', '84', '汪志杰', '16', '2021-01-03 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('47', '85', '樊刚', '16', '2021-01-03 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('48', '86', '尹克强', '16', '2021-01-03 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('49', '56', 'aaa', '16', '2021-01-04 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('50', '57', 'bbb', '16', '2021-01-05 03:18:27', '0.1', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('51', '64', '黄超强', '16', '2021-01-05 09:26:00', '6.7', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('52', '82', '李经留', '16', '2021-01-04 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('53', '83', '谢谢2', '16', '2021-01-04 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('54', '84', '汪志杰', '16', '2021-01-04 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('55', '85', '樊刚', '16', '2021-01-04 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('56', '86', '尹克强', '16', '2021-01-04 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('57', '56', 'aaa', '16', '2021-01-05 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('58', '57', 'bbb', '16', '2021-01-06 10:10:36', '0.5', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('59', '64', '黄超强', '16', '2021-01-06 10:12:11', '3.7', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('60', '82', '李经留', '16', '2021-01-05 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('61', '83', '谢谢2', '16', '2021-01-05 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('62', '84', '汪志杰', '16', '2021-01-05 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('63', '85', '樊刚', '16', '2021-01-05 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_1` VALUES ('64', '86', '尹克强', '16', '2021-01-05 16:00:00', '0.0', null, null, null, null, null, null, '1', '4', '5', '0.0', '0');

-- ----------------------------
-- Table structure for bus_attend_22
-- ----------------------------
DROP TABLE IF EXISTS `bus_attend_22`;
CREATE TABLE `bus_attend_22` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '考勤管理表',
  `staff_id` int(11) DEFAULT NULL COMMENT '员工ID',
  `staff_name` varchar(20) DEFAULT NULL COMMENT '员工名称',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门ID',
  `attend_date` datetime DEFAULT NULL COMMENT '考勤日期(天)',
  `attend_hour` double(3,1) DEFAULT NULL COMMENT '当天工时',
  `on_time` datetime DEFAULT NULL COMMENT '签到时间',
  `on_gate` int(11) DEFAULT NULL COMMENT '签到闸机ID',
  `on_photo` varchar(50) DEFAULT NULL COMMENT '签到图片',
  `off_time` datetime DEFAULT NULL COMMENT '签退时间',
  `off_gate` int(11) DEFAULT NULL COMMENT '签退闸机ID',
  `off_photo` varchar(50) DEFAULT NULL COMMENT '签退照片',
  `belong_id` int(11) DEFAULT NULL COMMENT '所属用户ID',
  `on_status` int(2) DEFAULT NULL COMMENT '上班考勤状态',
  `off_status` int(2) DEFAULT NULL COMMENT '下班考勤状态',
  `overtime_hours` double(3,1) NOT NULL DEFAULT 0.0,
  `valid_attend` int(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bus_attend_22
-- ----------------------------
INSERT INTO `bus_attend_22` VALUES ('1', '204', '章少林', '57', '2020-12-29 16:00:00', '0.0', null, null, null, null, null, null, '22', '6', '6', '0.0', '0');
INSERT INTO `bus_attend_22` VALUES ('2', '207', '黎国雄', '57', '2020-12-29 16:00:00', '0.0', null, null, null, null, null, null, '22', '6', '6', '0.0', '0');


-- ----------------------------
-- Table structure for bus_attend_32
-- ----------------------------
DROP TABLE IF EXISTS `bus_attend_32`;
CREATE TABLE `bus_attend_32` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '考勤管理表',
  `staff_id` int(11) DEFAULT NULL COMMENT '员工ID',
  `staff_name` varchar(20) DEFAULT NULL COMMENT '员工名称',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门ID',
  `attend_date` datetime DEFAULT NULL COMMENT '考勤日期(天)',
  `attend_hour` double(3,1) DEFAULT NULL COMMENT '当天工时',
  `on_time` datetime DEFAULT NULL COMMENT '签到时间',
  `on_gate` int(11) DEFAULT NULL COMMENT '签到闸机ID',
  `on_photo` varchar(50) DEFAULT NULL COMMENT '签到图片',
  `off_time` datetime DEFAULT NULL COMMENT '签退时间',
  `off_gate` int(11) DEFAULT NULL COMMENT '签退闸机ID',
  `off_photo` varchar(50) DEFAULT NULL COMMENT '签退照片',
  `belong_id` int(11) DEFAULT NULL COMMENT '所属用户ID',
  `on_status` int(2) DEFAULT NULL COMMENT '上班考勤状态',
  `off_status` int(2) DEFAULT NULL COMMENT '下班考勤状态',
  `overtime_hours` double(3,1) NOT NULL DEFAULT 0.0,
  `valid_attend` int(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=353 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bus_attend_32
-- ----------------------------

INSERT INTO `bus_attend_32` VALUES ('349', '202', '姚正武', '46', '2021-01-05 16:00:00', '0.0', null, null, null, null, null, null, '32', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_32` VALUES ('350', '203', '王炳兴', '40', '2021-01-05 16:00:00', '0.0', null, null, null, null, null, null, '32', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_32` VALUES ('351', '205', '景耀', '41', '2021-01-05 16:00:00', '0.0', null, null, null, null, null, null, '32', '4', '5', '0.0', '0');
INSERT INTO `bus_attend_32` VALUES ('352', '206', '杨爱勇', '48', '2021-01-05 16:00:00', '0.0', null, null, null, null, null, null, '32', '4', '5', '0.0', '0');

-- ----------------------------
-- Table structure for bus_attend_35
-- ----------------------------
DROP TABLE IF EXISTS `bus_attend_35`;
CREATE TABLE `bus_attend_35` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '考勤管理表',
  `staff_id` int(11) DEFAULT NULL COMMENT '员工ID',
  `staff_name` varchar(20) DEFAULT NULL COMMENT '员工名称',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门ID',
  `attend_date` datetime DEFAULT NULL COMMENT '考勤日期(天)',
  `attend_hour` double(3,1) DEFAULT NULL COMMENT '当天工时',
  `on_time` datetime DEFAULT NULL COMMENT '签到时间',
  `on_gate` int(11) DEFAULT NULL COMMENT '签到闸机ID',
  `on_photo` varchar(50) DEFAULT NULL COMMENT '签到图片',
  `off_time` datetime DEFAULT NULL COMMENT '签退时间',
  `off_gate` int(11) DEFAULT NULL COMMENT '签退闸机ID',
  `off_photo` varchar(50) DEFAULT NULL COMMENT '签退照片',
  `belong_id` int(11) DEFAULT NULL COMMENT '所属用户ID',
  `on_status` int(2) DEFAULT NULL COMMENT '上班考勤状态',
  `off_status` int(2) DEFAULT NULL COMMENT '下班考勤状态',
  `overtime_hours` double(3,1) NOT NULL DEFAULT 0.0,
  `valid_attend` int(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bus_attend_35
-- ----------------------------

-- ----------------------------
-- Table structure for bus_attend_4
-- ----------------------------
DROP TABLE IF EXISTS `bus_attend_4`;
CREATE TABLE `bus_attend_4` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '考勤管理表',
  `staff_id` int(11) DEFAULT NULL COMMENT '员工ID',
  `staff_name` varchar(20) DEFAULT NULL COMMENT '员工名称',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门ID',
  `attend_date` datetime DEFAULT NULL COMMENT '考勤日期(天)',
  `attend_hour` double(3,1) DEFAULT NULL COMMENT '当天工时',
  `on_time` datetime DEFAULT NULL COMMENT '签到时间',
  `on_gate` int(11) DEFAULT NULL COMMENT '签到闸机ID',
  `on_photo` varchar(50) DEFAULT NULL COMMENT '签到图片',
  `off_time` datetime DEFAULT NULL COMMENT '签退时间',
  `off_gate` int(11) DEFAULT NULL COMMENT '签退闸机ID',
  `off_photo` varchar(50) DEFAULT NULL COMMENT '签退照片',
  `belong_id` int(11) DEFAULT NULL COMMENT '所属用户ID',
  `on_status` int(2) DEFAULT NULL COMMENT '上班考勤状态',
  `off_status` int(2) DEFAULT NULL COMMENT '下班考勤状态',
  `overtime_hours` double(3,1) NOT NULL DEFAULT 0.0,
  `valid_attend` int(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bus_attend_4
-- ----------------------------
INSERT INTO `bus_attend_4` VALUES ('1', '80', '章少林', '9', '2020-12-29 16:00:00', '0.0', null, null, null, null, null, null, '4', '6', '6', '0.0', '0');
INSERT INTO `bus_attend_4` VALUES ('2', '91', '黎国雄', '9', '2020-12-29 16:00:00', '0.0', null, null, null, null, null, null, '4', '6', '6', '0.0', '0');


-- ----------------------------
-- Table structure for bus_attend_rule
-- ----------------------------
DROP TABLE IF EXISTS `bus_attend_rule`;
CREATE TABLE `bus_attend_rule` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '考勤排班表',
  `rule_name` varchar(20) DEFAULT NULL COMMENT '班次名称',
  `sign_time` varchar(20) DEFAULT NULL COMMENT '上班时间',
  `leave_time` varchar(20) DEFAULT NULL COMMENT '下班时间',
  `start_sign` varchar(20) DEFAULT NULL COMMENT '开始签到时间',
  `end_sign` varchar(20) DEFAULT NULL COMMENT '结束签到时间',
  `start_leave` varchar(20) DEFAULT NULL COMMENT '开始签退时间',
  `end_leave` varchar(20) DEFAULT NULL COMMENT '结束签退时间',
  `belong_id` int(11) DEFAULT NULL COMMENT '所属用户ID',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bus_attend_rule
-- ----------------------------
INSERT INTO `bus_attend_rule` VALUES ('1', '普通班', '09:00:00', '18:00:00', '08:30:00', '09:30:00', '17:30:00', '18:30:08', '1', '2020-10-31 07:37:45', '2020-11-18 09:13:02');


-- ----------------------------
-- Table structure for bus_attend_staff
-- ----------------------------
DROP TABLE IF EXISTS `bus_attend_staff`;
CREATE TABLE `bus_attend_staff` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '人员排班表',
  `rule_id` int(11) DEFAULT NULL COMMENT '考勤班次ID',
  `staff_list` varchar(255) DEFAULT NULL COMMENT '员工ID集合',
  `cycle_date` varchar(30) DEFAULT NULL COMMENT '循环日期',
  `sign_time` varchar(20) DEFAULT NULL COMMENT '上班时间',
  `leave_time` varchar(20) DEFAULT NULL COMMENT '下班时间',
  `belong_id` int(11) DEFAULT NULL COMMENT '所属用户ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bus_attend_staff
-- ----------------------------
INSERT INTO `bus_attend_staff` VALUES ('2', '1', '45,42,43,44,41', '1,2,3,4,5,', null, null, '1', '2020-10-31 07:48:56', '2020-11-19 02:31:24');
INSERT INTO `bus_attend_staff` VALUES ('3', '2', '', '1,2,3,4,5,', null, null, '1', '2020-11-11 08:01:14', '2020-11-11 08:01:14');
INSERT INTO `bus_attend_staff` VALUES ('4', '4', '46', '1,2,3,4,5,', null, null, '1', '2020-11-11 09:46:45', '2020-11-19 02:31:20');
INSERT INTO `bus_attend_staff` VALUES ('5', '5', '56,57,64,82,83,84,85,86', '1,2,3,4,5,6,7,', null, null, '22', '2020-11-26 09:05:39', '2020-12-30 09:29:42');
INSERT INTO `bus_attend_staff` VALUES ('7', '8', '', '1,2,3,4,5,', null, null, '22', '2020-11-27 07:54:00', '2020-11-27 07:54:00');
INSERT INTO `bus_attend_staff` VALUES ('8', '7', '78', '2,3,4,5,', null, null, '22', '2020-12-03 07:46:00', '2020-12-03 07:46:00');
INSERT INTO `bus_attend_staff` VALUES ('9', '10', '103', '1,2,3,4,5,', null, null, '4', '2020-12-08 09:47:58', '2020-12-08 09:47:58');
INSERT INTO `bus_attend_staff` VALUES ('10', '11', '162,185,183,184,190,176,192,163,175,178,180,197,188,193,164,170,186,189,191,194,198,202,165,173,181,203,205,174,179,195,166,167,168,169,171,172,177,182,187,196,201,206,199,200', '1,2,3,4,5,', null, null, '32', '2020-12-10 08:27:20', '2020-12-21 09:07:41');

-- ----------------------------
-- Table structure for bus_backup
-- ----------------------------
DROP TABLE IF EXISTS `bus_backup`;
CREATE TABLE `bus_backup` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '数据备份表',
  `backup_time` datetime DEFAULT NULL COMMENT '备份时间',
  `backup_way` int(2) DEFAULT NULL COMMENT '1-手动 2-自动',
  `frequency` int(2) DEFAULT NULL COMMENT '备份频率 1-周 2-月',
  `backup_content` varchar(20) DEFAULT NULL COMMENT '备份内容',
  `status` int(2) DEFAULT NULL COMMENT '状态 1-开启 2-关闭',
  `belong_id` int(11) DEFAULT NULL COMMENT '所属用户ID',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `cron` varchar(20) DEFAULT NULL,
  `file_path` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bus_backup
-- ----------------------------
INSERT INTO `bus_backup` VALUES ('5', '2020-11-16 10:43:03', '1', null, '1,2,3', '1', '1', '2020-11-16 10:43:03', '2020-11-16 10:43:03', null, 'backup/1/1605523383130');
INSERT INTO `bus_backup` VALUES ('7', '2020-11-16 10:46:24', '1', null, '2', '1', '1', '2020-11-16 10:46:24', '2020-11-16 10:46:24', null, 'backup/1/1605523584038');
INSERT INTO `bus_backup` VALUES ('8', '2020-11-16 10:48:07', '1', null, '3', '1', '1', '2020-11-16 10:48:07', '2020-11-16 10:48:07', null, 'backup/1/1605523687824');

-- ----------------------------
-- Table structure for bus_black_visitor
-- ----------------------------
DROP TABLE IF EXISTS `bus_black_visitor`;
CREATE TABLE `bus_black_visitor` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '黑名单表',
  `name` varchar(20) DEFAULT NULL COMMENT '访客姓名',
  `belong_id` int(11) DEFAULT NULL COMMENT '所属用户的ID(关联sys_user表)',
  `phone` varchar(20) DEFAULT '',
  `person_group` varchar(50) DEFAULT '',
  `connected_phone` varchar(20) DEFAULT '',
  `visitor_photo` varchar(255) DEFAULT '' COMMENT '访客照片',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `creator_name` varchar(20) DEFAULT '' COMMENT '创建者名',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `id_num` varchar(20) DEFAULT '' COMMENT '身份证号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bus_black_visitor
-- ----------------------------
INSERT INTO `bus_black_visitor` VALUES ('8', '12312', '1', '31', '313', '313', '2020-11-28/DE630A619FFA42D7A8909ADBDDF2C4A4.jpg', '2020-11-28 03:09:30', '超级管理员', '13', '2020-11-28 03:07:22', '113');
INSERT INTO `bus_black_visitor` VALUES ('12', '123123', '24', '2131213', '', '', '2020-12-01/5FEC7C0DD3CF446BB80A74DD4AE22DCA.jpg', '2020-12-01 01:14:16', '123123', '', '2020-12-01 01:14:16', '12312312');


-- ----------------------------
-- Table structure for bus_gate
-- ----------------------------
DROP TABLE IF EXISTS `bus_gate`;
CREATE TABLE `bus_gate` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '设备管理表',
  `device_sn` varchar(20) DEFAULT NULL COMMENT '设备SN号',
  `gate_name` varchar(20) DEFAULT NULL COMMENT '闸机名称',
  `online_status` int(2) DEFAULT NULL COMMENT '在线状态(1-在线 2-离线)',
  `install_location` varchar(20) DEFAULT NULL COMMENT '安装位置',
  `active_time` datetime DEFAULT NULL COMMENT '激活时间',
  `gate_status` int(2) DEFAULT NULL COMMENT '闸机状态(1-已激活 2-未激活)',
  `current_ver` varchar(30) DEFAULT NULL COMMENT '当前版本',
  `newest_ver` varchar(30) DEFAULT NULL COMMENT '最新版本',
  `face_set` varchar(5) DEFAULT NULL COMMENT '人脸设置(on-开 off-关)',
  `infrared_set` varchar(5) DEFAULT NULL COMMENT '红外线设置(on-开 off-关)',
  `temperature_set` varchar(5) DEFAULT NULL COMMENT '测温设置(on-开 off-关)',
  `available` int(2) DEFAULT 1 COMMENT '是否可用(0-已删除 1-可用)',
  `belong_id` int(11) DEFAULT NULL COMMENT '所属用户的ID(关联sys_user表)',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `ver_type` int(2) DEFAULT NULL COMMENT '版本类型(1-阿里云 2-云飞励天)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bus_gate
-- ----------------------------
INSERT INTO `bus_gate` VALUES ('5', '123', '123', '2', '123', null, '1', null, '20120709', 'off', 'off', 'off', '1', '4', '', '2020-11-03 10:34:21', '2020-11-03 10:34:21', null);
INSERT INTO `bus_gate` VALUES ('6', 'QHAP8YNK8Q', 'hcq', '2', 'hcq\'location', '2020-11-07 03:31:52', '1', '20121109', '20122409', 'off', 'on', 'on', '1', '1', 'huangchaoqiang', '2020-11-07 03:31:40', '2020-12-11 01:50:28', '1');
INSERT INTO `bus_gate` VALUES ('38', 'S8821202011100939', 'hhhhhhhh', '2', '深圳', '2020-12-03 02:22:04', '1', '21010517', '20122409', 'on', 'off', 'on', '1', '22', '', '2020-12-03 02:21:53', '2021-01-07 15:11:38', '1');
INSERT INTO `bus_gate` VALUES ('39', '8821202011301027', '阿里云', '2', '', '2020-12-03 06:35:59', '1', '20120809', '20122409', 'off', 'off', 'off', '0', '4', '', '2020-12-03 06:35:54', '2020-12-09 09:17:31', '1');
INSERT INTO `bus_gate` VALUES ('40', '8821202011301027', '阿里云_01', '2', '深圳', '2020-12-09 09:26:44', '1', '20122515', '20122409', 'off', 'off', 'on', '1', '32', '', '2020-12-09 09:25:59', '2021-01-04 08:52:25', '1');
INSERT INTO `bus_gate` VALUES ('41', '8888888888888888', 'hcq', '2', 'hcq\'location', '2020-12-11 06:19:47', '1', '21010714', '20122409', 'off', 'off', 'on', '1', '1', '', '2020-12-11 06:19:40', '2021-01-07 17:22:17', '1');
INSERT INTO `bus_gate` VALUES ('45', '111', '111', '2', '', '2020-12-21 09:25:29', '1', '21010601', '', 'off', 'off', 'on', '1', '1', '', '2020-12-21 09:24:43', '2021-01-07 01:36:34', '2');
INSERT INTO `bus_gate` VALUES ('46', 'S8821202012261128', '测试', '2', '', '2021-01-07 13:55:04', '1', '21010714', '', 'off', 'off', 'off', '1', '22', '', '2021-01-07 03:36:02', '2021-01-07 17:12:21', '1');

-- ----------------------------
-- Table structure for bus_gate_author
-- ----------------------------
DROP TABLE IF EXISTS `bus_gate_author`;
CREATE TABLE `bus_gate_author` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '闸机授权表',
  `gate_id` int(11) DEFAULT NULL COMMENT '闸机ID',
  `staff_list` varchar(255) DEFAULT NULL COMMENT '被授权人',
  `author_time` varchar(50) DEFAULT NULL COMMENT '通行时间',
  `belong_id` int(11) DEFAULT NULL COMMENT '所属用户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bus_gate_author
-- ----------------------------
INSERT INTO `bus_gate_author` VALUES ('72', '38', '204,207,210', '00:00:00 - 24:00:00', '22', '2020-12-03 08:09:40', '2021-01-06 01:25:19');
INSERT INTO `bus_gate_author` VALUES ('74', '40', '162,185,183,184,190,176,192,163,175,178,180,197,188,193,164,170,186,189,191,194,198,202,165,173,181,203,174,179,195,205,166,167,168,169,171,172,177,182,187,196,201,199,200,206', '00:00:00 - 24:00:00', '32', '2020-12-09 10:08:40', '2020-12-15 08:14:20');
INSERT INTO `bus_gate_author` VALUES ('76', '41', '56,57,64,82,83,84,85,86', '00:00:00 - 24:00:00', '1', '2020-12-12 03:43:35', '2020-12-28 06:35:03');
INSERT INTO `bus_gate_author` VALUES ('77', '45', '56,57', '00:00:00 - 24:00:00', '1', '2020-12-21 09:25:54', '2020-12-22 02:26:53');
INSERT INTO `bus_gate_author` VALUES ('78', '46', '211,210', '00:00:00 - 24:00:00', '22', '2021-01-07 13:55:22', '2021-01-07 14:04:39');

-- ----------------------------
-- Table structure for bus_pass_record
-- ----------------------------
DROP TABLE IF EXISTS `bus_pass_record`;
CREATE TABLE `bus_pass_record` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '通行记录表',
  `pass_time` datetime DEFAULT NULL COMMENT '通过时间',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `sex` int(2) DEFAULT NULL COMMENT '性别(1-男 2-女)',
  `id_num` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `card_num` varchar(20) DEFAULT NULL COMMENT '卡号',
  `face_photo` varchar(255) DEFAULT NULL COMMENT '人脸信息访问地址',
  `gate_id` int(11) DEFAULT NULL COMMENT '闸机ID',
  `pass_way` int(2) DEFAULT NULL COMMENT '通行方式(1-人脸识别 2-刷卡)',
  `identity_type` int(2) DEFAULT NULL COMMENT '身份(1-员工 2-访客)',
  `temperature` varchar(10) DEFAULT NULL COMMENT '温度',
  `pass_direction` int(2) DEFAULT NULL COMMENT '通行方向(1-进入 2-离开)',
  `belong_id` int(11) DEFAULT NULL COMMENT '所属用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6192 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bus_pass_record
-- ----------------------------

-- ----------------------------
-- Table structure for bus_pass_record_1
-- ----------------------------
DROP TABLE IF EXISTS `bus_pass_record_1`;
CREATE TABLE `bus_pass_record_1` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '通行记录表',
  `pass_time` datetime DEFAULT NULL COMMENT '通过时间',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `sex` int(2) DEFAULT NULL COMMENT '性别(1-男 2-女)',
  `id_num` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `card_num` varchar(20) DEFAULT NULL COMMENT '卡号',
  `face_photo` varchar(255) DEFAULT NULL COMMENT '人脸信息访问地址',
  `gate_id` int(11) DEFAULT NULL COMMENT '闸机ID',
  `pass_way` int(2) DEFAULT NULL COMMENT '通行方式(1-人脸识别 2-刷卡)',
  `identity_type` int(2) DEFAULT NULL COMMENT '身份(1-员工 2-访客)',
  `temperature` varchar(10) DEFAULT NULL COMMENT '温度',
  `pass_direction` int(2) DEFAULT NULL COMMENT '通行方向(1-进入 2-离开)',
  `belong_id` int(11) DEFAULT NULL COMMENT '所属用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bus_pass_record_1
-- ----------------------------
INSERT INTO `bus_pass_record_1` VALUES ('1', '2021-01-07 16:10:38', '黄', '1', '110101199003072551', '17704025126', '6666666', '2020-11-24/6992AE804D5B4CD3A8B6FDCFBC22F60F.jpg', '41', '1', '1', '36.57', '1', '1');


-- ----------------------------
-- Table structure for bus_pass_record_22
-- ----------------------------
DROP TABLE IF EXISTS `bus_pass_record_22`;
CREATE TABLE `bus_pass_record_22` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '通行记录表',
  `pass_time` datetime DEFAULT NULL COMMENT '通过时间',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `sex` int(2) DEFAULT NULL COMMENT '性别(1-男 2-女)',
  `id_num` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `card_num` varchar(20) DEFAULT NULL COMMENT '卡号',
  `face_photo` varchar(255) DEFAULT NULL COMMENT '人脸信息访问地址',
  `gate_id` int(11) DEFAULT NULL COMMENT '闸机ID',
  `pass_way` int(2) DEFAULT NULL COMMENT '通行方式(1-人脸识别 2-刷卡)',
  `identity_type` int(2) DEFAULT NULL COMMENT '身份(1-员工 2-访客)',
  `temperature` varchar(10) DEFAULT NULL COMMENT '温度',
  `pass_direction` int(2) DEFAULT NULL COMMENT '通行方向(1-进入 2-离开)',
  `belong_id` int(11) DEFAULT NULL COMMENT '所属用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bus_pass_record_22
-- ----------------------------
INSERT INTO `bus_pass_record_22` VALUES ('1', '2021-01-07 13:51:21', '刘娇', '2', '', '17688835203', '', '2021-01-05/1C016C885FA94A85827DA1D515C9A330.jpg', '38', '1', '1', '37.14', '1', '22');


-- ----------------------------
-- Table structure for bus_pass_record_32
-- ----------------------------
DROP TABLE IF EXISTS `bus_pass_record_32`;
CREATE TABLE `bus_pass_record_32` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '通行记录表',
  `pass_time` datetime DEFAULT NULL COMMENT '通过时间',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `sex` int(2) DEFAULT NULL COMMENT '性别(1-男 2-女)',
  `id_num` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `card_num` varchar(20) DEFAULT NULL COMMENT '卡号',
  `face_photo` varchar(255) DEFAULT NULL COMMENT '人脸信息访问地址',
  `gate_id` int(11) DEFAULT NULL COMMENT '闸机ID',
  `pass_way` int(2) DEFAULT NULL COMMENT '通行方式(1-人脸识别 2-刷卡)',
  `identity_type` int(2) DEFAULT NULL COMMENT '身份(1-员工 2-访客)',
  `temperature` varchar(10) DEFAULT NULL COMMENT '温度',
  `pass_direction` int(2) DEFAULT NULL COMMENT '通行方向(1-进入 2-离开)',
  `belong_id` int(11) DEFAULT NULL COMMENT '所属用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bus_pass_record_32
-- ----------------------------

-- ----------------------------
-- Table structure for bus_pass_record_35
-- ----------------------------
DROP TABLE IF EXISTS `bus_pass_record_35`;
CREATE TABLE `bus_pass_record_35` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '通行记录表',
  `pass_time` datetime DEFAULT NULL COMMENT '通过时间',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `sex` int(2) DEFAULT NULL COMMENT '性别(1-男 2-女)',
  `id_num` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `card_num` varchar(20) DEFAULT NULL COMMENT '卡号',
  `face_photo` varchar(255) DEFAULT NULL COMMENT '人脸信息访问地址',
  `gate_id` int(11) DEFAULT NULL COMMENT '闸机ID',
  `pass_way` int(2) DEFAULT NULL COMMENT '通行方式(1-人脸识别 2-刷卡)',
  `identity_type` int(2) DEFAULT NULL COMMENT '身份(1-员工 2-访客)',
  `temperature` varchar(10) DEFAULT NULL COMMENT '温度',
  `pass_direction` int(2) DEFAULT NULL COMMENT '通行方向(1-进入 2-离开)',
  `belong_id` int(11) DEFAULT NULL COMMENT '所属用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bus_pass_record_35
-- ----------------------------

-- ----------------------------
-- Table structure for bus_pass_record_36
-- ----------------------------
DROP TABLE IF EXISTS `bus_pass_record_36`;
CREATE TABLE `bus_pass_record_36` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '通行记录表',
  `pass_time` datetime DEFAULT NULL COMMENT '通过时间',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `sex` int(2) DEFAULT NULL COMMENT '性别(1-男 2-女)',
  `id_num` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `card_num` varchar(20) DEFAULT NULL COMMENT '卡号',
  `face_photo` varchar(255) DEFAULT NULL COMMENT '人脸信息访问地址',
  `gate_id` int(11) DEFAULT NULL COMMENT '闸机ID',
  `pass_way` int(2) DEFAULT NULL COMMENT '通行方式(1-人脸识别 2-刷卡)',
  `identity_type` int(2) DEFAULT NULL COMMENT '身份(1-员工 2-访客)',
  `temperature` varchar(10) DEFAULT NULL COMMENT '温度',
  `pass_direction` int(2) DEFAULT NULL COMMENT '通行方向(1-进入 2-离开)',
  `belong_id` int(11) DEFAULT NULL COMMENT '所属用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bus_pass_record_36
-- ----------------------------

-- ----------------------------
-- Table structure for bus_pass_record_4
-- ----------------------------
DROP TABLE IF EXISTS `bus_pass_record_4`;
CREATE TABLE `bus_pass_record_4` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '通行记录表',
  `pass_time` datetime DEFAULT NULL COMMENT '通过时间',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `sex` int(2) DEFAULT NULL COMMENT '性别(1-男 2-女)',
  `id_num` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `card_num` varchar(20) DEFAULT NULL COMMENT '卡号',
  `face_photo` varchar(255) DEFAULT NULL COMMENT '人脸信息访问地址',
  `gate_id` int(11) DEFAULT NULL COMMENT '闸机ID',
  `pass_way` int(2) DEFAULT NULL COMMENT '通行方式(1-人脸识别 2-刷卡)',
  `identity_type` int(2) DEFAULT NULL COMMENT '身份(1-员工 2-访客)',
  `temperature` varchar(10) DEFAULT NULL COMMENT '温度',
  `pass_direction` int(2) DEFAULT NULL COMMENT '通行方向(1-进入 2-离开)',
  `belong_id` int(11) DEFAULT NULL COMMENT '所属用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bus_pass_record_4
-- ----------------------------

-- ----------------------------
-- Table structure for bus_person_error
-- ----------------------------
DROP TABLE IF EXISTS `bus_person_error`;
CREATE TABLE `bus_person_error` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '人员信息异常记录表',
  `person_id` int(11) DEFAULT NULL COMMENT '人员ID',
  `identity_type` int(2) DEFAULT NULL COMMENT '人员身份类型(1-员工 2-访客)',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `id_num` varchar(30) DEFAULT NULL COMMENT '身份证号',
  `phone` varchar(20) DEFAULT NULL,
  `person_photo` varchar(255) DEFAULT NULL COMMENT '照片路径',
  `error_msg` varchar(255) DEFAULT NULL COMMENT '错误信息',
  `create_time` datetime DEFAULT NULL,
  `belong_id` int(11) DEFAULT NULL COMMENT '所属用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=690 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bus_person_error
-- ----------------------------
INSERT INTO `bus_person_error` VALUES ('305', '49', '1', '张三', '430921199710270016', '17373729068', '2020-11-20/C5DE56CA3B6443B1B4BD976BDCF50831.jpg', '人脸数据角度不符合{\"posePitch\":26.757236,\"poseRoll\":11.203232,\"poseYaw\":24.402962}', '2020-11-20 02:49:01', '4');


-- ----------------------------
-- Table structure for bus_staff
-- ----------------------------
DROP TABLE IF EXISTS `bus_staff`;
CREATE TABLE `bus_staff` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '员工信息表',
  `staff_name` varchar(20) DEFAULT NULL COMMENT '员工姓名',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门ID',
  `sex` int(2) DEFAULT NULL COMMENT '性别',
  `id_num` varchar(30) DEFAULT NULL COMMENT '身份证号',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `card_num` varchar(20) DEFAULT NULL COMMENT '卡号',
  `staff_photo` varchar(255) DEFAULT NULL COMMENT '头像访问地址',
  `available` int(11) DEFAULT 1 COMMENT '是否可用(0-已删除 1-可用)',
  `status` int(2) DEFAULT NULL COMMENT '员工状态(1-在职 2-离职)',
  `belong_id` int(11) DEFAULT NULL COMMENT '所属用户的ID(关联sys_user表)',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `staff_num` varchar(20) DEFAULT '',
  `staff_job` varchar(20) DEFAULT '',
  `entry_date` varchar(30) DEFAULT '',
  `email` varchar(30) DEFAULT '',
  `recog_status` int(2) DEFAULT NULL COMMENT '1-通过 2-不通过 0-待更新',
  `prompt` varchar(100) DEFAULT '' COMMENT '提示语',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=212 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bus_staff
-- ----------------------------
INSERT INTO `bus_staff` VALUES ('56', 'aaa', '16', '2', '', '18888888888', '555456121235', '2020-11-20/0BFF5EDDBCBE4280B3D750AE8E20FD6E.jpg', '1', '1', '1', '', '2020-11-20 08:54:10', '2020-12-07 03:02:38', '4234', '', '', '', '2', '');

-- ----------------------------
-- Table structure for bus_staff_card
-- ----------------------------
DROP TABLE IF EXISTS `bus_staff_card`;
CREATE TABLE `bus_staff_card` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '发卡管理表',
  `card_num` varchar(20) DEFAULT NULL,
  `card_binder` int(11) DEFAULT NULL COMMENT '卡片绑定者ID',
  `card_author` int(11) DEFAULT NULL COMMENT '发卡者ID',
  `belong_id` int(11) DEFAULT NULL COMMENT '所属用户',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bus_staff_card
-- ----------------------------
INSERT INTO `bus_staff_card` VALUES ('9', '321312', '86', '22', '22', null, '2020-12-03 10:10:35', '2020-12-03 10:10:35');
INSERT INTO `bus_staff_card` VALUES ('11', '21231', '75', '22', '22', null, '2020-12-04 03:43:23', '2020-12-04 03:43:23');
INSERT INTO `bus_staff_card` VALUES ('12', '3214214', '78', '22', '22', null, '2020-12-04 03:43:36', '2020-12-04 03:43:36');

-- ----------------------------
-- Table structure for bus_staff_error
-- ----------------------------
DROP TABLE IF EXISTS `bus_staff_error`;
CREATE TABLE `bus_staff_error` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '设备异常表',
  `name` varchar(100) DEFAULT '',
  `sex` varchar(10) DEFAULT '',
  `remark` varchar(255) DEFAULT '',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `belong_id` int(11) DEFAULT NULL,
  `id_num` varchar(30) DEFAULT '' COMMENT '身份证号',
  `phone` varchar(20) DEFAULT '' COMMENT '手机号码',
  `card_num` varchar(20) DEFAULT '' COMMENT '卡号',
  `dept_name` varchar(30) DEFAULT '' COMMENT '身份证号',
  `face_photo` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=298 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bus_staff_error
-- ----------------------------
INSERT INTO `bus_staff_error` VALUES ('65', '黄佩', '女', '员工编号已被使用', '2020-11-28 02:22:55', '22', '', '15686677653', '5432890766', '工程部', 'huangpei.jpg');
INSERT INTO `bus_staff_error` VALUES ('66', '李经留', '男', '员工编号已被使用', '2020-11-28 02:22:55', '22', '', '15686677655', '', '工程部', 'lijingliu.jpg');

-- ----------------------------
-- Table structure for bus_temp_visitor
-- ----------------------------
DROP TABLE IF EXISTS `bus_temp_visitor`;
CREATE TABLE `bus_temp_visitor` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '访客表',
  `visitor_name` varchar(20) DEFAULT NULL COMMENT '访客姓名',
  `sex` int(2) DEFAULT NULL COMMENT '性别(1-男 2-女)',
  `visit_time` datetime DEFAULT NULL COMMENT '来访时间',
  `leave_time` datetime DEFAULT NULL COMMENT '离开时间',
  `id_num` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `phone` varchar(20) DEFAULT NULL,
  `gate_ids` varchar(60) DEFAULT '' COMMENT '闸机ID',
  `visitor_photo` varchar(255) DEFAULT NULL COMMENT '访客照片',
  `status` int(2) DEFAULT NULL COMMENT '状态(1-已生效 2-已失效)',
  `belong_id` int(11) DEFAULT NULL COMMENT '所属用户的ID(关联sys_user表)',
  `creator_name` varchar(20) DEFAULT NULL COMMENT '创建者名',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `recog_status` int(2) DEFAULT NULL COMMENT '1-通过 2-不通过 0-待更新',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bus_temp_visitor
-- ----------------------------
INSERT INTO `bus_temp_visitor` VALUES ('6', '反反复复', '1', '2020-11-23 16:00:00', '2020-11-24 02:53:00', '110101199003071479', '13333333333', '6', '2020-11-24/E9E9BAD2726D4D709C9F5548A6A9B0DD.jpg', '2', '1', '超级管理员', '是是是', '2020-11-24 02:51:02', '2020-11-24 02:51:02', '1');
INSERT INTO `bus_temp_visitor` VALUES ('22', '1', '1', '2020-12-23 16:00:00', '2020-12-23 16:00:00', '430921199710270016', '17373729061', '40', '2020-12-24/D9AA6CF42D4F42BA972E43DAB09333F2.jpg', '2', '32', 'FISE_ZN', '', '2020-12-24 09:45:38', '2020-12-28 03:31:32', null);

-- ----------------------------
-- Table structure for bus_vip_visitor
-- ----------------------------
DROP TABLE IF EXISTS `bus_vip_visitor`;
CREATE TABLE `bus_vip_visitor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `visitor_name` varchar(20) DEFAULT '' COMMENT '访客姓名',
  `phone` varchar(20) DEFAULT '',
  `visit_time` varchar(30) DEFAULT '' COMMENT '来访时间',
  `leave_time` varchar(30) DEFAULT '' COMMENT '离开时间',
  `gate_ids` varchar(11) DEFAULT '' COMMENT '闸机ID',
  `person_group` varchar(50) DEFAULT '',
  `connected_phone` varchar(20) DEFAULT '',
  `cycle_date` varchar(20) DEFAULT NULL COMMENT 'week',
  `visitor_photo` varchar(255) DEFAULT '' COMMENT '访客照片',
  `belong_id` int(11) DEFAULT NULL COMMENT '所属用户的ID(关联sys_user表)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `creator_name` varchar(20) DEFAULT '' COMMENT '创建者名',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `id_num` varchar(20) DEFAULT '' COMMENT '身份证号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bus_vip_visitor
-- ----------------------------
INSERT INTO `bus_vip_visitor` VALUES ('41', 'zs', '17373729061', '00:00', '00:00', '40,41', '', '', '2', '2020-12-21/ED65DD4867A34F9DBB6CA7401B26F8DD.jpg', '1', '2020-12-21 05:53:35', '2020-12-21 05:53:35', '超级管理员', '', '430921199710270016');
INSERT INTO `bus_vip_visitor` VALUES ('42', 'zy', '17373729068', '00:00', '00:00', '40', '', '', '4,5,6', '2020-12-21/C728B8AD206546D2BD9F876377998BE2.jpg', '32', '2020-12-21 11:16:06', '2020-12-25 01:01:57', 'FISE_ZN', '', '430921199710270011');
INSERT INTO `bus_vip_visitor` VALUES ('54', '刘娇', '17688835203', '10:09', '11:55', '38', '', '18507716104', '5', '2020-12-25/13C3BC6A5A6545BB84F146898A9B701F.jpg', '22', '2020-12-25 01:07:30', '2020-12-25 03:49:53', 'YTLF', '', '110101199003070214');
INSERT INTO `bus_vip_visitor` VALUES ('55', '黄', '', '', '', '', '', '', null, '2020-12-25/0DE9B8EDA9EE4B479EC66A689EEC4656.jpg', '22', '2020-12-25 01:07:30', '2020-12-25 01:07:30', 'YTLF', '', '110101199003072770');

-- ----------------------------
-- Table structure for sys_app
-- ----------------------------
DROP TABLE IF EXISTS `sys_app`;
CREATE TABLE `sys_app` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '应用管理表',
  `app_ver` varchar(20) DEFAULT NULL COMMENT '版本',
  `app_name` varchar(30) DEFAULT NULL COMMENT '升级包名称',
  `app_size` double(12,2) DEFAULT NULL COMMENT '升级包大小',
  `update_log` varchar(200) DEFAULT NULL COMMENT '更新日志',
  `status` int(2) DEFAULT NULL COMMENT '状态',
  `app_url` varchar(255) DEFAULT NULL COMMENT '应用访问地址',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `ver_type` int(2) DEFAULT NULL COMMENT '版本类型(1-阿里云 2-云飞励天)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_app
-- ----------------------------
INSERT INTO `sys_app` VALUES ('3', '20112111', '20112111_V2020-11-21.apk', '55415.08', 'Logger.i(\"收到更新推送\")', '2', '2020-11-21/A3E0AF36A85D464F806BB84FAC725AD7.apk', '2020-11-21 02:44:12', '2020-11-21 02:46:56', null);
INSERT INTO `sys_app` VALUES ('4', '20120416', '20120416_V2020-12-04.apk', '66282.22', '', '1', '2020-12-04/6F4FB81ADCAA475AAF4DAB5D1433CF95.apk', '2020-12-04 09:30:00', '2020-12-04 09:32:05', null);
INSERT INTO `sys_app` VALUES ('7', '20120709', '20120709_V-2020.12.07.09.apk', '66282.22', 'test', '2', '2020-12-07/B503D51795F24E9C93461D1B061475A7.apk', '2020-12-07 01:09:48', '2020-12-07 01:09:52', null);
INSERT INTO `sys_app` VALUES ('16', '20121018', '20121018_V-2020.12.10.18_1.apk', '66286.39', '', '2', '2020-12-10/21A426CAF8FB449BA58311DD947FFBFD.apk', '2020-12-10 10:48:54', '2020-12-10 10:49:16', '1');
INSERT INTO `sys_app` VALUES ('17', '20121109', '20121109_V-2020.12.11.09_1.apk', '66286.39', '紧急修复不显示识别结果的问题', '2', '2020-12-11/FB6881BF310B4226982236258E4A945D.apk', '2020-12-11 01:45:57', '2020-12-11 01:46:01', '1');
INSERT INTO `sys_app` VALUES ('20', '20181120', '20181120_V1.2.3_1.apk', null, '', '1', null, '2020-12-22 03:36:18', '2020-12-22 03:36:18', null);
INSERT INTO `sys_app` VALUES ('21', '20122309', '20122309_V-2020.12.23.09_1.apk', '67123.17', '', '2', '2020-12-23/CAA75E20D09A4963B956CCC063A58830.apk', '2020-12-23 06:34:17', '2020-12-23 06:34:23', '1');
INSERT INTO `sys_app` VALUES ('23', '20122409', '20122409_V-2020.12.24.09_1.apk', '67123.17', '', '2', '2020-12-24/3201492AD26449039FFCBC3EF0736A5A.apk', '2020-12-24 02:48:06', '2020-12-24 02:48:42', '1');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `open` int(11) DEFAULT NULL COMMENT '是否展开，0不展开，1展开',
  `remark` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `available` int(11) DEFAULT NULL COMMENT '是否可用，0不可用，1可用',
  `ordernum` int(11) DEFAULT NULL COMMENT '排序码',
  `createtime` datetime DEFAULT NULL,
  `belong_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '0', '总经办', '1', '大BOSS', '深圳', '1', '1', '2020-10-30 14:06:32', null);
INSERT INTO `sys_dept` VALUES ('9', '0', '研发部', '1', '', '', '1', '1', '2020-11-20 09:46:38', '4');
INSERT INTO `sys_dept` VALUES ('10', '9', '软件部', '1', '', '', '1', '2', '2020-11-20 09:46:51', '4');
INSERT INTO `sys_dept` VALUES ('11', '0', '测试部', '1', '', '', '1', '3', '2020-11-25 02:49:05', '4');
INSERT INTO `sys_dept` VALUES ('12', '11', '01', '1', '', '', '1', '4', '2020-11-25 02:49:20', '4');
INSERT INTO `sys_dept` VALUES ('68', '0', '其它', '1', null, null, '1', '21', '2020-12-14 09:42:11', '22');

-- ----------------------------
-- Table structure for sys_device
-- ----------------------------
DROP TABLE IF EXISTS `sys_device`;
CREATE TABLE `sys_device` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '设备管理表',
  `device_sn` varchar(30) DEFAULT NULL COMMENT '设备SN号',
  `cpu_id` varchar(50) DEFAULT '' COMMENT 'CPU唯一标识',
  `belong_id` int(11) DEFAULT NULL COMMENT '所属用户ID',
  `status` int(2) DEFAULT NULL COMMENT '状态(1-在线 2-离线)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_device
-- ----------------------------
INSERT INTO `sys_device` VALUES ('2', 'zytest', '', '3', '2', '2020-10-30 09:51:17', '2020-10-30 10:09:21');
INSERT INTO `sys_device` VALUES ('4', '666', '', '1', '2', '2020-10-30 10:11:11', '2020-10-31 08:36:19');


-- ----------------------------
-- Table structure for sys_device_error
-- ----------------------------
DROP TABLE IF EXISTS `sys_device_error`;
CREATE TABLE `sys_device_error` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '设备异常表',
  `device_sn` varchar(30) DEFAULT NULL COMMENT '设备SN号',
  `remark` varchar(30) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_device_error
-- ----------------------------
INSERT INTO `sys_device_error` VALUES ('1', 'S8829202012251827', 'SN号已被使用', '2020-12-01 06:15:04', '2020-12-01 06:15:04');


-- ----------------------------
-- Table structure for sys_loginfo
-- ----------------------------
DROP TABLE IF EXISTS `sys_loginfo`;
CREATE TABLE `sys_loginfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `loginip` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `logintime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1804 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_loginfo
-- ----------------------------
INSERT INTO `sys_loginfo` VALUES ('1', '超级管理员-system', '172.17.0.2', '2020-10-30 09:45:52');
INSERT INTO `sys_loginfo` VALUES ('1803', '超级管理员-system', '127.0.0.1', '2021-01-07 19:22:57');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `content` text COLLATE utf8_unicode_ci DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `opername` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES ('1', '关于系统V1.3更新公告', '2', '2018-08-07 00:00:00', '管理员');
INSERT INTO `sys_notice` VALUES ('15', '111111', null, '2019-11-25 15:30:06', '落亦-');
INSERT INTO `sys_notice` VALUES ('16', '试一下', null, '2019-11-25 15:32:44', '落亦-');
INSERT INTO `sys_notice` VALUES ('17', '忘记写name属性了', '这回应该可以了', '2019-11-25 15:34:51', '落亦-');
INSERT INTO `sys_notice` VALUES ('18', '数据表格的bug', '删除一页数据表格的所有数据，显示无数据', '2019-11-26 09:24:09', '落亦-');
INSERT INTO `sys_notice` VALUES ('19', '数据表格', '当返回的数据为0时，只需将当前页减一即可', '2019-11-26 09:24:59', '落亦-');
INSERT INTO `sys_notice` VALUES ('20', '还差一条', '在来一条', '2019-11-26 09:25:14', '落亦-');
INSERT INTO `sys_notice` VALUES ('21', '再来一条', '来一条', '2019-11-26 09:25:30', '落亦-');
INSERT INTO `sys_notice` VALUES ('22', '还有18天四级', '18天', '2019-11-26 09:25:51', '落亦-');
INSERT INTO `sys_notice` VALUES ('23', 'bug搞定', 'OK了', '2019-11-26 09:26:19', '落亦-');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `percode` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '权限编码[只有type=permission才有 user:view]',
  `icon` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `href` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `target` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `open` int(11) DEFAULT NULL,
  `ordernum` int(11) DEFAULT NULL,
  `available` int(11) DEFAULT NULL COMMENT '是否可用[0不可用，1可用]',
  `en_title` varchar(255) COLLATE utf8_unicode_ci DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '0', 'menu', '人脸识别系统', null, '&#xe68e;', '', '', '1', '1', '1', '');
INSERT INTO `sys_permission` VALUES ('2', '1', 'menu', '数据统计', null, '&#xe68e', '/sys/toDeskManager', '', '0', '2', '1', 'Statistics');
INSERT INTO `sys_permission` VALUES ('3', '1', 'menu', '人员管理', null, '&#xe857;', '', '', '1', '3', '1', 'Person Management');
INSERT INTO `sys_permission` VALUES ('4', '1', 'menu', '闸机管理', null, '&#xe645;', '', null, '0', '4', '1', 'Gate Management');
INSERT INTO `sys_permission` VALUES ('5', '1', 'menu', '应用管理', null, '&#xe653;', '', '', '0', '5', '1', 'APP Management');
INSERT INTO `sys_permission` VALUES ('6', '1', 'menu', '设备管理', null, '&#xe631;', '', '', '0', '6', '1', 'Device Management');
INSERT INTO `sys_permission` VALUES ('7', '1', 'menu', '考勤管理', null, '&#xe672;', '', '', '0', '7', '1', 'Attendance Management');
INSERT INTO `sys_permission` VALUES ('8', '1', 'menu', '企业管理', null, '&#xe613;', '', '', '0', '8', '1', 'Enterprice Management');
INSERT INTO `sys_permission` VALUES ('9', '1', 'menu', '系统管理', null, '&#xe614;', '', '', '0', '9', '1', 'System Management');
INSERT INTO `sys_permission` VALUES ('10', '1', 'menu', '其它管理', null, '&#xe628;', '', '', '0', '10', '1', 'Others');
INSERT INTO `sys_permission` VALUES ('11', '3', 'menu', '员工信息', null, '&#xe612;', '/bus/toStaffManager', '', '0', '11', '1', 'Staff');
INSERT INTO `sys_permission` VALUES ('12', '3', 'menu', '临时访客', null, '&#xe658;', '/bus/toVisitorManager', '', '0', '12', '1', 'Temp Visitor');
INSERT INTO `sys_permission` VALUES ('13', '4', 'menu', '闸机列表', null, '&#xe756;', '/bus/toGateManager', '', '0', '13', '1', 'Gate');
INSERT INTO `sys_permission` VALUES ('14', '4', 'menu', '通行记录', null, '&#xe65a;', '/bus/toPassRecordManager', '', '0', '14', '1', 'Pass Record');
INSERT INTO `sys_permission` VALUES ('15', '4', 'menu', '授权管理', null, '&#xe615;', '/bus/toGateAuthorManager', '', '0', '15', '1', 'Gate Auth');
INSERT INTO `sys_permission` VALUES ('16', '5', 'menu', '应用列表', null, '&#xe62a;', '/sys/toAppManager', '', '0', '16', '1', 'App List');
INSERT INTO `sys_permission` VALUES ('17', '6', 'menu', '设备列表', null, '&#xe62d;', '/sys/toDeviceManager', '', '0', '17', '1', 'Device');
INSERT INTO `sys_permission` VALUES ('18', '6', 'menu', '异常记录', null, '&#xe6b2;', '/sys/toDeviceErrorManager', '', '0', '18', '1', 'Abnormal');
INSERT INTO `sys_permission` VALUES ('19', '7', 'menu', '考勤列表', null, '&#xe60a;', '/bus/toAttendManager', '', '0', '19', '1', 'Attend List');
INSERT INTO `sys_permission` VALUES ('20', '7', 'menu', '考勤规则', null, '&#xe66c;', '/bus/toAttendRuleManager', '', '0', '20', '1', 'Attend Rule');
INSERT INTO `sys_permission` VALUES ('21', '7', 'menu', '人员排班', null, '&#xe637;', '/bus/toAttendStaffManager', '', '0', '21', '1', 'Attend Staff');
INSERT INTO `sys_permission` VALUES ('22', '3', 'menu', '发卡管理', null, '&#xe63b;', '/bus/toStaffCardManager', '', '0', '22', '1', 'StaffCard');
INSERT INTO `sys_permission` VALUES ('23', '8', 'menu', '数据备份', '', 'layui-icon-tree', '/bus/toBackupManager', '', '0', '23', '1', 'Data Backup');
INSERT INTO `sys_permission` VALUES ('24', '3', 'menu', '部门管理', null, '&#xe770;', '/sys/toDeptManager', '', '0', '24', '1', 'Dept');
INSERT INTO `sys_permission` VALUES ('25', '9', 'menu', '菜单管理', null, '&#xe857;', '/sys/toMenuManager', '', '0', '25', '1', 'Menu');
INSERT INTO `sys_permission` VALUES ('26', '9', 'menu', '权限管理', '', '&#xe857;', '/sys/toPermissionManager', '', '0', '26', '1', 'Auth');
INSERT INTO `sys_permission` VALUES ('27', '9', 'menu', '角色管理', '', '&#xe650;', '/sys/toRoleManager', '', '0', '27', '1', 'Charactor');
INSERT INTO `sys_permission` VALUES ('28', '9', 'menu', '用户管理', '', '&#xe612;', '/sys/toUserManager', '', '0', '28', '1', 'User');
INSERT INTO `sys_permission` VALUES ('29', '9', 'menu', '注册管理', '', '&#xe650;', '/sys/toRegisterManager', '', '0', '29', '1', 'Login Management');
INSERT INTO `sys_permission` VALUES ('30', '10', 'menu', '登陆日志', null, '&#xe675;', '/sys/toLoginfoManager', '', '0', '30', '1', 'Loginfo');
INSERT INTO `sys_permission` VALUES ('31', '10', 'menu', '系统公告', null, '&#xe756;', '/sys/toNoticeManager', null, '0', '31', '1', 'Notice');
INSERT INTO `sys_permission` VALUES ('32', '10', 'menu', '图标管理', null, '&#xe670;', '../resources/page/icon.html', null, '0', '32', '1', 'Icon');
INSERT INTO `sys_permission` VALUES ('33', '24', 'permission', '查询部门', 'dept:view', '', null, null, '0', '33', '1', '');
INSERT INTO `sys_permission` VALUES ('34', '24', 'permission', '添加部门', 'dept:create', '', null, null, '0', '34', '1', '');
INSERT INTO `sys_permission` VALUES ('35', '24', 'permission', '修改部门', 'dept:update', '', null, null, '0', '35', '1', '');
INSERT INTO `sys_permission` VALUES ('36', '24', 'permission', '删除部门', 'dept:delete', '', null, null, '0', '36', '1', '');
INSERT INTO `sys_permission` VALUES ('37', '25', 'permission', '查询菜单', 'menu:view', '', null, null, '0', '37', '1', '');
INSERT INTO `sys_permission` VALUES ('38', '25', 'permission', '添加菜单', 'menu:create', '', '', '', '0', '38', '1', '');
INSERT INTO `sys_permission` VALUES ('39', '25', 'permission', '修改菜单', 'menu:update', '', null, null, '0', '39', '1', '');
INSERT INTO `sys_permission` VALUES ('40', '25', 'permission', '删除菜单', 'menu:delete', '', null, null, '0', '40', '1', '');
INSERT INTO `sys_permission` VALUES ('41', '26', 'permission', '查询权限', 'permission:view', '', null, null, '0', '41', '1', '');
INSERT INTO `sys_permission` VALUES ('42', '26', 'permission', '添加权限', 'permission:create', '', null, null, '0', '42', '1', '');
INSERT INTO `sys_permission` VALUES ('43', '26', 'permission', '修改权限', 'permission:update', '', null, null, '0', '43', '1', '');
INSERT INTO `sys_permission` VALUES ('44', '26', 'permission', '删除权限', 'permission:delete', '', null, null, '0', '44', '1', '');
INSERT INTO `sys_permission` VALUES ('45', '27', 'permission', '查询角色', 'role:view', '', null, null, '0', '45', '1', '');
INSERT INTO `sys_permission` VALUES ('46', '27', 'permission', '添加角色', 'role:create', '', null, null, '0', '46', '1', '');
INSERT INTO `sys_permission` VALUES ('47', '27', 'permission', '修改角色', 'role:update', '', null, null, '0', '47', '1', '');
INSERT INTO `sys_permission` VALUES ('48', '27', 'permission', '删除角色', 'role:delete', '', null, null, '0', '48', '1', '');
INSERT INTO `sys_permission` VALUES ('49', '27', 'permission', '分配权限', 'role:selectPermission', '', null, null, '0', '49', '1', '');
INSERT INTO `sys_permission` VALUES ('50', '28', 'permission', '查询角色', 'user:view', '', null, null, '0', '50', '1', '');
INSERT INTO `sys_permission` VALUES ('51', '28', 'permission', '添加用户', 'user:create', '', null, null, '0', '51', '1', '');
INSERT INTO `sys_permission` VALUES ('52', '28', 'permission', '修改用户', 'user:update', '', null, null, '0', '52', '1', '');
INSERT INTO `sys_permission` VALUES ('53', '28', 'permission', '删除用户', 'user:delete', '', null, null, '0', '53', '1', '');
INSERT INTO `sys_permission` VALUES ('54', '28', 'permission', '用户分配角色', 'user:selectRole', '', null, null, '0', '54', '1', '');
INSERT INTO `sys_permission` VALUES ('55', '28', 'permission', '重置密码', 'user:resetPwd', null, null, null, '0', '55', '1', '');
INSERT INTO `sys_permission` VALUES ('56', '29', 'permission', '查询注册', 'register:view', '', null, null, '0', '56', '1', '');
INSERT INTO `sys_permission` VALUES ('57', '29', 'permission', '添加注册', 'register:create', '', null, null, '0', '57', '1', '');
INSERT INTO `sys_permission` VALUES ('58', '29', 'permission', '修改注册', 'register:update', '', null, null, '0', '58', '1', '');
INSERT INTO `sys_permission` VALUES ('59', '29', 'permission', '删除注册', 'register:delete', '', null, null, '0', '59', '1', '');
INSERT INTO `sys_permission` VALUES ('60', '30', 'permission', '日志查询', 'info:view', null, null, null, null, '60', '1', '');
INSERT INTO `sys_permission` VALUES ('61', '30', 'permission', '日志删除', 'info:delete', null, null, null, null, '61', '1', '');
INSERT INTO `sys_permission` VALUES ('62', '30', 'permission', '日志批量删除', 'info:batchdelete', null, null, null, null, '62', '1', '');
INSERT INTO `sys_permission` VALUES ('63', '31', 'permission', '公告查询', 'notice:view', null, null, null, null, '63', '1', '');
INSERT INTO `sys_permission` VALUES ('64', '31', 'permission', '公告添加', 'notice:create', null, null, null, null, '64', '1', '');
INSERT INTO `sys_permission` VALUES ('65', '31', 'permission', '公告修改', 'notice:update', null, null, null, null, '65', '1', '');
INSERT INTO `sys_permission` VALUES ('66', '31', 'permission', '公告删除', 'notice:delete', null, null, null, null, '66', '1', '');
INSERT INTO `sys_permission` VALUES ('67', '11', 'permission', '员工查询', 'staff:view', null, null, null, null, '67', '1', '');
INSERT INTO `sys_permission` VALUES ('68', '11', 'permission', '员工添加', 'staff:create', null, null, null, null, '68', '1', '');
INSERT INTO `sys_permission` VALUES ('69', '11', 'permission', '员工修改', 'staff:update', null, null, null, null, '69', '1', '');
INSERT INTO `sys_permission` VALUES ('70', '11', 'permission', '员工删除', 'staff:delete', null, null, null, null, '70', '1', '');
INSERT INTO `sys_permission` VALUES ('71', '12', 'permission', '访客查询', 'visitor:view', null, null, null, null, '71', '1', '');
INSERT INTO `sys_permission` VALUES ('72', '12', 'permission', '访客添加', 'visitor:create', null, null, null, null, '72', '1', '');
INSERT INTO `sys_permission` VALUES ('73', '12', 'permission', '访客修改', 'visitor:update', null, null, null, null, '73', '1', '');
INSERT INTO `sys_permission` VALUES ('74', '12', 'permission', '访客删除', 'visitor:delete', null, null, null, null, '74', '1', '');
INSERT INTO `sys_permission` VALUES ('75', '13', 'permission', '闸机查询', 'gate:view', null, null, null, null, '75', '1', '');
INSERT INTO `sys_permission` VALUES ('76', '13', 'permission', '闸机添加', 'gate:create', null, null, null, null, '76', '1', '');
INSERT INTO `sys_permission` VALUES ('77', '13', 'permission', '闸机修改', 'gate:update', null, null, null, null, '77', '1', '');
INSERT INTO `sys_permission` VALUES ('78', '13', 'permission', '闸机删除', 'gate:delete', null, null, null, null, '78', '1', '');
INSERT INTO `sys_permission` VALUES ('79', '14', 'permission', '通行查询', 'passRecord:view', null, null, null, null, '79', '1', '');
INSERT INTO `sys_permission` VALUES ('80', '14', 'permission', '通行添加', 'passRecord:create', null, null, null, null, '80', '1', '');
INSERT INTO `sys_permission` VALUES ('81', '14', 'permission', '通行修改', 'passRecord:update', null, null, null, null, '81', '1', '');
INSERT INTO `sys_permission` VALUES ('82', '14', 'permission', '通行删除', 'passRecord:delete', null, null, null, null, '82', '1', '');
INSERT INTO `sys_permission` VALUES ('83', '15', 'permission', '授权查询', 'gateAuthor:view', null, null, null, null, '83', '1', '');
INSERT INTO `sys_permission` VALUES ('84', '15', 'permission', '授权添加', 'gateAuthor:create', null, null, null, null, '84', '1', '');
INSERT INTO `sys_permission` VALUES ('85', '15', 'permission', '授权修改', 'gateAuthor:update', null, null, null, null, '85', '1', '');
INSERT INTO `sys_permission` VALUES ('86', '15', 'permission', '授权删除', 'gateAuthor:delete', null, null, null, null, '86', '1', '');
INSERT INTO `sys_permission` VALUES ('87', '16', 'permission', '应用查询', 'app:view', null, null, null, null, '87', '1', '');
INSERT INTO `sys_permission` VALUES ('88', '16', 'permission', '应用添加', 'app:create', null, null, null, null, '88', '1', '');
INSERT INTO `sys_permission` VALUES ('89', '16', 'permission', '应用修改', 'app:update', null, null, null, null, '89', '1', '');
INSERT INTO `sys_permission` VALUES ('90', '16', 'permission', '应用删除', 'app:delete', null, null, null, null, '90', '1', '');
INSERT INTO `sys_permission` VALUES ('91', '17', 'permission', '设备查询', 'device:view', null, null, null, null, '91', '1', '');
INSERT INTO `sys_permission` VALUES ('92', '17', 'permission', '设备添加', 'device:create', null, null, null, null, '92', '1', '');
INSERT INTO `sys_permission` VALUES ('93', '17', 'permission', '设备修改', 'device:update', null, null, null, null, '93', '1', '');
INSERT INTO `sys_permission` VALUES ('94', '17', 'permission', '设备删除', 'device:delete', null, null, null, null, '94', '1', '');
INSERT INTO `sys_permission` VALUES ('95', '18', 'permission', '异常查询', 'deviceError:view', null, null, null, null, '95', '1', '');
INSERT INTO `sys_permission` VALUES ('96', '18', 'permission', '异常添加', 'deviceError:create', null, null, null, null, '96', '1', '');
INSERT INTO `sys_permission` VALUES ('97', '18', 'permission', '异常修改', 'deviceError:update', null, null, null, null, '97', '1', '');
INSERT INTO `sys_permission` VALUES ('98', '18', 'permission', '异常删除', 'deviceError:delete', null, null, null, null, '98', '1', '');
INSERT INTO `sys_permission` VALUES ('99', '19', 'permission', '考勤查询', 'attend:view', null, null, null, null, '99', '1', '');
INSERT INTO `sys_permission` VALUES ('100', '19', 'permission', '考勤添加', 'attend:create', null, null, null, null, '100', '1', '');
INSERT INTO `sys_permission` VALUES ('101', '19', 'permission', '考勤修改', 'attend:update', null, null, null, null, '101', '1', '');
INSERT INTO `sys_permission` VALUES ('102', '19', 'permission', '考勤删除', 'attend:delete', null, null, null, null, '102', '1', '');
INSERT INTO `sys_permission` VALUES ('103', '20', 'permission', '规则查询', 'attendRule:view', null, null, null, null, '103', '1', '');
INSERT INTO `sys_permission` VALUES ('104', '20', 'permission', '规则添加', 'attendRule:create', null, null, null, null, '104', '1', '');
INSERT INTO `sys_permission` VALUES ('105', '20', 'permission', '规则修改', 'attendRule:update', null, null, null, null, '105', '1', '');
INSERT INTO `sys_permission` VALUES ('106', '20', 'permission', '规则删除', 'attendRule:delete', null, null, null, null, '106', '1', '');
INSERT INTO `sys_permission` VALUES ('107', '21', 'permission', '排班查询', 'attendStaff:view', null, null, null, null, '107', '1', '');
INSERT INTO `sys_permission` VALUES ('108', '21', 'permission', '排班添加', 'attendStaff:create', null, null, null, null, '108', '1', '');
INSERT INTO `sys_permission` VALUES ('109', '21', 'permission', '排班修改', 'attendStaff:update', null, null, null, null, '109', '1', '');
INSERT INTO `sys_permission` VALUES ('110', '21', 'permission', '排班删除', 'attendStaff:delete', null, null, null, null, '110', '1', '');
INSERT INTO `sys_permission` VALUES ('111', '22', 'permission', '发卡查询', 'staffCard:view', null, null, null, null, '111', '1', '');
INSERT INTO `sys_permission` VALUES ('112', '22', 'permission', '发卡添加', 'staffCard:create', null, null, null, null, '112', '1', '');
INSERT INTO `sys_permission` VALUES ('113', '22', 'permission', '发卡修改', 'staffCard:update', null, null, null, null, '113', '1', '');
INSERT INTO `sys_permission` VALUES ('114', '22', 'permission', '发卡删除', 'staffCard:delete', null, null, null, null, '114', '1', '');
INSERT INTO `sys_permission` VALUES ('115', '3', 'menu', '同步数据异常', null, '&#xe6b2;', '/bus/toPersonErrorManager', '', '0', '115', '1', 'Face Error');
INSERT INTO `sys_permission` VALUES ('116', '3', 'menu', '导入信息异常', null, '&#xe6b2;', '/bus/toStaffError', '', '1', '116', '1', 'Input Error');
INSERT INTO `sys_permission` VALUES ('117', '3', 'menu', 'vip访客', null, '&#xe658;', '/bus/toVipVisitorManager', '', '1', '117', '1', 'Vip visitor');
INSERT INTO `sys_permission` VALUES ('118', '3', 'menu', '访客黑名单', '', '&#xe612;', '/bus/toBlacklistManager', '', '1', '118', '1', 'BlackList');

-- ----------------------------
-- Table structure for sys_register
-- ----------------------------
DROP TABLE IF EXISTS `sys_register`;
CREATE TABLE `sys_register` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '注册管理表',
  `company_name` varchar(20) DEFAULT NULL COMMENT '公司名称',
  `login_name` varchar(20) DEFAULT NULL COMMENT '账号',
  `password` varchar(30) DEFAULT NULL COMMENT '密码',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `status` int(2) DEFAULT NULL COMMENT '状态(1-待审核 2-已通过 3-已拒绝)',
  `remark` varchar(30) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_register
-- ----------------------------
INSERT INTO `sys_register` VALUES ('2', 'adad', 'test', '123456', '17373729061', '3', '123', '2020-10-31 08:21:50', '2020-10-31 08:21:50');
INSERT INTO `sys_register` VALUES ('3', '深圳沸石', 'feishi', '123456789', '18926575592', '2', '24241221', '2020-11-02 07:52:07', '2020-11-02 07:52:07');
INSERT INTO `sys_register` VALUES ('4', '深圳沸石智能技术有限公司', 'Test', 'Fise1234', '17688835203', '3', '2131212', '2020-11-02 08:59:26', '2020-11-02 08:59:26');
INSERT INTO `sys_register` VALUES ('5', 'sz', 'test1', '123456', '18682403907', '1', null, '2020-11-05 09:30:40', '2020-11-05 09:30:40');
INSERT INTO `sys_register` VALUES ('6', '沸石科技', '1399346552', '1399346552', '18565044556', '1', null, '2020-11-23 06:18:50', '2020-11-23 06:18:50');
INSERT INTO `sys_register` VALUES ('7', '沸石科技', '1399346552', '1399346552', '18565044556', '1', null, '2020-11-23 06:21:25', '2020-11-23 06:21:25');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `belong_user` int(11) DEFAULT NULL COMMENT '所属用户',
  `remark` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `available` int(11) DEFAULT NULL COMMENT '0不可用，1可用',
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', '1', '拥有所有菜单权限', '1', '2019-04-10 14:06:32');
INSERT INTO `sys_role` VALUES ('4', '系统管理员', '1', '系统管理员', '1', '2019-04-10 14:06:32');
INSERT INTO `sys_role` VALUES ('5', 'PC端管理员', '1', 'PC端管理员', '1', '2019-04-10 14:06:32');
INSERT INTO `sys_role` VALUES ('9', '门禁系统', '1', '', '1', '2020-11-25 02:58:51');
INSERT INTO `sys_role` VALUES ('10', '闸机系统', '1', '', '1', '2020-11-25 03:23:30');
INSERT INTO `sys_role` VALUES ('11', '考勤系统', '1', '', '1', '2020-11-25 03:27:48');
INSERT INTO `sys_role` VALUES ('12', 'Yun', '22', '', '1', '2020-12-03 03:12:45');
INSERT INTO `sys_role` VALUES ('14', 'FISE领导', '1', '通用权限', '1', '2020-12-04 10:28:30');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `rid` int(11) DEFAULT NULL,
  `pid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('4', '1');
INSERT INTO `sys_role_permission` VALUES ('4', '4');
INSERT INTO `sys_role_permission` VALUES ('4', '15');
INSERT INTO `sys_role_permission` VALUES ('4', '87');
INSERT INTO `sys_role_permission` VALUES ('4', '88');
INSERT INTO `sys_role_permission` VALUES ('4', '89');
INSERT INTO `sys_role_permission` VALUES ('4', '90');
INSERT INTO `sys_role_permission` VALUES ('4', '5');
INSERT INTO `sys_role_permission` VALUES ('4', '16');
INSERT INTO `sys_role_permission` VALUES ('4', '91');
INSERT INTO `sys_role_permission` VALUES ('4', '92');
INSERT INTO `sys_role_permission` VALUES ('4', '93');
INSERT INTO `sys_role_permission` VALUES ('4', '94');
INSERT INTO `sys_role_permission` VALUES ('4', '17');
INSERT INTO `sys_role_permission` VALUES ('4', '95');
INSERT INTO `sys_role_permission` VALUES ('4', '96');
INSERT INTO `sys_role_permission` VALUES ('4', '97');
INSERT INTO `sys_role_permission` VALUES ('4', '98');
INSERT INTO `sys_role_permission` VALUES ('4', '8');
INSERT INTO `sys_role_permission` VALUES ('4', '27');
INSERT INTO `sys_role_permission` VALUES ('4', '45');
INSERT INTO `sys_role_permission` VALUES ('4', '46');
INSERT INTO `sys_role_permission` VALUES ('4', '47');
INSERT INTO `sys_role_permission` VALUES ('4', '48');
INSERT INTO `sys_role_permission` VALUES ('4', '49');
INSERT INTO `sys_role_permission` VALUES ('4', '28');
INSERT INTO `sys_role_permission` VALUES ('4', '50');
INSERT INTO `sys_role_permission` VALUES ('4', '51');
INSERT INTO `sys_role_permission` VALUES ('4', '52');
INSERT INTO `sys_role_permission` VALUES ('4', '53');
INSERT INTO `sys_role_permission` VALUES ('4', '54');
INSERT INTO `sys_role_permission` VALUES ('4', '55');
INSERT INTO `sys_role_permission` VALUES ('4', '29');
INSERT INTO `sys_role_permission` VALUES ('4', '56');
INSERT INTO `sys_role_permission` VALUES ('4', '57');
INSERT INTO `sys_role_permission` VALUES ('4', '58');
INSERT INTO `sys_role_permission` VALUES ('4', '59');
INSERT INTO `sys_role_permission` VALUES ('5', '1');
INSERT INTO `sys_role_permission` VALUES ('5', '2');
INSERT INTO `sys_role_permission` VALUES ('5', '10');
INSERT INTO `sys_role_permission` VALUES ('5', '67');
INSERT INTO `sys_role_permission` VALUES ('5', '68');
INSERT INTO `sys_role_permission` VALUES ('5', '69');
INSERT INTO `sys_role_permission` VALUES ('5', '70');
INSERT INTO `sys_role_permission` VALUES ('5', '11');
INSERT INTO `sys_role_permission` VALUES ('5', '71');
INSERT INTO `sys_role_permission` VALUES ('5', '72');
INSERT INTO `sys_role_permission` VALUES ('5', '73');
INSERT INTO `sys_role_permission` VALUES ('5', '74');
INSERT INTO `sys_role_permission` VALUES ('5', '115');
INSERT INTO `sys_role_permission` VALUES ('5', '116');
INSERT INTO `sys_role_permission` VALUES ('5', '3');
INSERT INTO `sys_role_permission` VALUES ('5', '12');
INSERT INTO `sys_role_permission` VALUES ('5', '75');
INSERT INTO `sys_role_permission` VALUES ('5', '76');
INSERT INTO `sys_role_permission` VALUES ('5', '77');
INSERT INTO `sys_role_permission` VALUES ('5', '78');
INSERT INTO `sys_role_permission` VALUES ('5', '13');
INSERT INTO `sys_role_permission` VALUES ('5', '79');
INSERT INTO `sys_role_permission` VALUES ('5', '80');
INSERT INTO `sys_role_permission` VALUES ('5', '81');
INSERT INTO `sys_role_permission` VALUES ('5', '82');
INSERT INTO `sys_role_permission` VALUES ('5', '14');
INSERT INTO `sys_role_permission` VALUES ('5', '83');
INSERT INTO `sys_role_permission` VALUES ('5', '84');
INSERT INTO `sys_role_permission` VALUES ('5', '85');
INSERT INTO `sys_role_permission` VALUES ('5', '86');
INSERT INTO `sys_role_permission` VALUES ('5', '6');
INSERT INTO `sys_role_permission` VALUES ('5', '18');
INSERT INTO `sys_role_permission` VALUES ('5', '99');
INSERT INTO `sys_role_permission` VALUES ('5', '100');
INSERT INTO `sys_role_permission` VALUES ('5', '101');
INSERT INTO `sys_role_permission` VALUES ('5', '102');
INSERT INTO `sys_role_permission` VALUES ('5', '19');
INSERT INTO `sys_role_permission` VALUES ('5', '103');
INSERT INTO `sys_role_permission` VALUES ('5', '104');
INSERT INTO `sys_role_permission` VALUES ('5', '105');
INSERT INTO `sys_role_permission` VALUES ('5', '106');
INSERT INTO `sys_role_permission` VALUES ('5', '20');
INSERT INTO `sys_role_permission` VALUES ('5', '107');
INSERT INTO `sys_role_permission` VALUES ('5', '108');
INSERT INTO `sys_role_permission` VALUES ('5', '109');
INSERT INTO `sys_role_permission` VALUES ('5', '110');
INSERT INTO `sys_role_permission` VALUES ('5', '21');
INSERT INTO `sys_role_permission` VALUES ('5', '111');
INSERT INTO `sys_role_permission` VALUES ('5', '112');
INSERT INTO `sys_role_permission` VALUES ('5', '113');
INSERT INTO `sys_role_permission` VALUES ('5', '114');
INSERT INTO `sys_role_permission` VALUES ('5', '8');
INSERT INTO `sys_role_permission` VALUES ('5', '24');
INSERT INTO `sys_role_permission` VALUES ('5', '33');
INSERT INTO `sys_role_permission` VALUES ('5', '34');
INSERT INTO `sys_role_permission` VALUES ('5', '35');
INSERT INTO `sys_role_permission` VALUES ('5', '36');
INSERT INTO `sys_role_permission` VALUES ('5', '67');
INSERT INTO `sys_role_permission` VALUES ('5', '68');
INSERT INTO `sys_role_permission` VALUES ('5', '69');
INSERT INTO `sys_role_permission` VALUES ('5', '70');
INSERT INTO `sys_role_permission` VALUES ('5', '71');
INSERT INTO `sys_role_permission` VALUES ('5', '72');
INSERT INTO `sys_role_permission` VALUES ('5', '73');
INSERT INTO `sys_role_permission` VALUES ('5', '74');
INSERT INTO `sys_role_permission` VALUES ('5', '75');
INSERT INTO `sys_role_permission` VALUES ('5', '76');
INSERT INTO `sys_role_permission` VALUES ('5', '77');
INSERT INTO `sys_role_permission` VALUES ('5', '78');
INSERT INTO `sys_role_permission` VALUES ('5', '79');
INSERT INTO `sys_role_permission` VALUES ('5', '80');
INSERT INTO `sys_role_permission` VALUES ('5', '81');
INSERT INTO `sys_role_permission` VALUES ('5', '82');
INSERT INTO `sys_role_permission` VALUES ('5', '83');
INSERT INTO `sys_role_permission` VALUES ('5', '84');
INSERT INTO `sys_role_permission` VALUES ('5', '85');
INSERT INTO `sys_role_permission` VALUES ('5', '86');
INSERT INTO `sys_role_permission` VALUES ('5', '99');
INSERT INTO `sys_role_permission` VALUES ('5', '100');
INSERT INTO `sys_role_permission` VALUES ('5', '101');
INSERT INTO `sys_role_permission` VALUES ('5', '102');
INSERT INTO `sys_role_permission` VALUES ('5', '103');
INSERT INTO `sys_role_permission` VALUES ('5', '104');
INSERT INTO `sys_role_permission` VALUES ('5', '105');
INSERT INTO `sys_role_permission` VALUES ('5', '106');
INSERT INTO `sys_role_permission` VALUES ('5', '107');
INSERT INTO `sys_role_permission` VALUES ('5', '108');
INSERT INTO `sys_role_permission` VALUES ('5', '109');
INSERT INTO `sys_role_permission` VALUES ('5', '110');
INSERT INTO `sys_role_permission` VALUES ('5', '111');
INSERT INTO `sys_role_permission` VALUES ('5', '112');
INSERT INTO `sys_role_permission` VALUES ('5', '113');
INSERT INTO `sys_role_permission` VALUES ('5', '114');
INSERT INTO `sys_role_permission` VALUES ('5', '33');
INSERT INTO `sys_role_permission` VALUES ('5', '34');
INSERT INTO `sys_role_permission` VALUES ('5', '35');
INSERT INTO `sys_role_permission` VALUES ('5', '36');
INSERT INTO `sys_role_permission` VALUES ('1', '1');
INSERT INTO `sys_role_permission` VALUES ('1', '2');
INSERT INTO `sys_role_permission` VALUES ('1', '10');
INSERT INTO `sys_role_permission` VALUES ('1', '68');
INSERT INTO `sys_role_permission` VALUES ('1', '69');
INSERT INTO `sys_role_permission` VALUES ('1', '70');
INSERT INTO `sys_role_permission` VALUES ('1', '11');
INSERT INTO `sys_role_permission` VALUES ('1', '71');
INSERT INTO `sys_role_permission` VALUES ('1', '73');
INSERT INTO `sys_role_permission` VALUES ('1', '74');
INSERT INTO `sys_role_permission` VALUES ('1', '24');
INSERT INTO `sys_role_permission` VALUES ('1', '33');
INSERT INTO `sys_role_permission` VALUES ('1', '34');
INSERT INTO `sys_role_permission` VALUES ('1', '35');
INSERT INTO `sys_role_permission` VALUES ('1', '36');
INSERT INTO `sys_role_permission` VALUES ('1', '115');
INSERT INTO `sys_role_permission` VALUES ('1', '116');
INSERT INTO `sys_role_permission` VALUES ('1', '3');
INSERT INTO `sys_role_permission` VALUES ('1', '12');
INSERT INTO `sys_role_permission` VALUES ('1', '75');
INSERT INTO `sys_role_permission` VALUES ('1', '76');
INSERT INTO `sys_role_permission` VALUES ('1', '77');
INSERT INTO `sys_role_permission` VALUES ('1', '78');
INSERT INTO `sys_role_permission` VALUES ('1', '13');
INSERT INTO `sys_role_permission` VALUES ('1', '79');
INSERT INTO `sys_role_permission` VALUES ('1', '81');
INSERT INTO `sys_role_permission` VALUES ('1', '82');
INSERT INTO `sys_role_permission` VALUES ('1', '14');
INSERT INTO `sys_role_permission` VALUES ('1', '83');
INSERT INTO `sys_role_permission` VALUES ('1', '84');
INSERT INTO `sys_role_permission` VALUES ('1', '86');
INSERT INTO `sys_role_permission` VALUES ('1', '4');
INSERT INTO `sys_role_permission` VALUES ('1', '15');
INSERT INTO `sys_role_permission` VALUES ('1', '5');
INSERT INTO `sys_role_permission` VALUES ('1', '16');
INSERT INTO `sys_role_permission` VALUES ('1', '91');
INSERT INTO `sys_role_permission` VALUES ('1', '92');
INSERT INTO `sys_role_permission` VALUES ('1', '17');
INSERT INTO `sys_role_permission` VALUES ('1', '6');
INSERT INTO `sys_role_permission` VALUES ('1', '18');
INSERT INTO `sys_role_permission` VALUES ('1', '21');
INSERT INTO `sys_role_permission` VALUES ('1', '7');
INSERT INTO `sys_role_permission` VALUES ('1', '22');
INSERT INTO `sys_role_permission` VALUES ('1', '8');
INSERT INTO `sys_role_permission` VALUES ('1', '38');
INSERT INTO `sys_role_permission` VALUES ('1', '39');
INSERT INTO `sys_role_permission` VALUES ('1', '40');
INSERT INTO `sys_role_permission` VALUES ('1', '42');
INSERT INTO `sys_role_permission` VALUES ('1', '43');
INSERT INTO `sys_role_permission` VALUES ('1', '44');
INSERT INTO `sys_role_permission` VALUES ('1', '46');
INSERT INTO `sys_role_permission` VALUES ('1', '47');
INSERT INTO `sys_role_permission` VALUES ('1', '48');
INSERT INTO `sys_role_permission` VALUES ('1', '49');
INSERT INTO `sys_role_permission` VALUES ('1', '51');
INSERT INTO `sys_role_permission` VALUES ('1', '52');
INSERT INTO `sys_role_permission` VALUES ('1', '53');
INSERT INTO `sys_role_permission` VALUES ('1', '54');
INSERT INTO `sys_role_permission` VALUES ('1', '55');
INSERT INTO `sys_role_permission` VALUES ('1', '56');
INSERT INTO `sys_role_permission` VALUES ('1', '57');
INSERT INTO `sys_role_permission` VALUES ('1', '9');
INSERT INTO `sys_role_permission` VALUES ('1', '30');
INSERT INTO `sys_role_permission` VALUES ('1', '31');
INSERT INTO `sys_role_permission` VALUES ('1', '32');
INSERT INTO `sys_role_permission` VALUES ('1', '67');
INSERT INTO `sys_role_permission` VALUES ('1', '68');
INSERT INTO `sys_role_permission` VALUES ('1', '69');
INSERT INTO `sys_role_permission` VALUES ('1', '70');
INSERT INTO `sys_role_permission` VALUES ('1', '71');
INSERT INTO `sys_role_permission` VALUES ('1', '72');
INSERT INTO `sys_role_permission` VALUES ('1', '73');
INSERT INTO `sys_role_permission` VALUES ('1', '74');
INSERT INTO `sys_role_permission` VALUES ('1', '33');
INSERT INTO `sys_role_permission` VALUES ('1', '34');
INSERT INTO `sys_role_permission` VALUES ('1', '35');
INSERT INTO `sys_role_permission` VALUES ('1', '36');
INSERT INTO `sys_role_permission` VALUES ('1', '75');
INSERT INTO `sys_role_permission` VALUES ('1', '76');
INSERT INTO `sys_role_permission` VALUES ('1', '77');
INSERT INTO `sys_role_permission` VALUES ('1', '78');
INSERT INTO `sys_role_permission` VALUES ('1', '79');
INSERT INTO `sys_role_permission` VALUES ('1', '80');
INSERT INTO `sys_role_permission` VALUES ('1', '81');
INSERT INTO `sys_role_permission` VALUES ('1', '82');
INSERT INTO `sys_role_permission` VALUES ('1', '83');
INSERT INTO `sys_role_permission` VALUES ('1', '84');
INSERT INTO `sys_role_permission` VALUES ('1', '85');
INSERT INTO `sys_role_permission` VALUES ('1', '86');
INSERT INTO `sys_role_permission` VALUES ('1', '87');
INSERT INTO `sys_role_permission` VALUES ('1', '88');
INSERT INTO `sys_role_permission` VALUES ('1', '89');
INSERT INTO `sys_role_permission` VALUES ('1', '90');
INSERT INTO `sys_role_permission` VALUES ('1', '91');
INSERT INTO `sys_role_permission` VALUES ('1', '92');
INSERT INTO `sys_role_permission` VALUES ('1', '93');
INSERT INTO `sys_role_permission` VALUES ('1', '94');
INSERT INTO `sys_role_permission` VALUES ('1', '95');
INSERT INTO `sys_role_permission` VALUES ('1', '96');
INSERT INTO `sys_role_permission` VALUES ('1', '97');
INSERT INTO `sys_role_permission` VALUES ('1', '98');
INSERT INTO `sys_role_permission` VALUES ('1', '99');
INSERT INTO `sys_role_permission` VALUES ('1', '100');
INSERT INTO `sys_role_permission` VALUES ('1', '101');
INSERT INTO `sys_role_permission` VALUES ('1', '102');
INSERT INTO `sys_role_permission` VALUES ('1', '111');
INSERT INTO `sys_role_permission` VALUES ('1', '112');
INSERT INTO `sys_role_permission` VALUES ('1', '113');
INSERT INTO `sys_role_permission` VALUES ('1', '114');
INSERT INTO `sys_role_permission` VALUES ('1', '60');
INSERT INTO `sys_role_permission` VALUES ('1', '61');
INSERT INTO `sys_role_permission` VALUES ('1', '62');
INSERT INTO `sys_role_permission` VALUES ('1', '63');
INSERT INTO `sys_role_permission` VALUES ('1', '64');
INSERT INTO `sys_role_permission` VALUES ('1', '65');
INSERT INTO `sys_role_permission` VALUES ('1', '66');
INSERT INTO `sys_role_permission` VALUES ('12', '1');
INSERT INTO `sys_role_permission` VALUES ('12', '2');
INSERT INTO `sys_role_permission` VALUES ('12', '3');
INSERT INTO `sys_role_permission` VALUES ('12', '7');
INSERT INTO `sys_role_permission` VALUES ('12', '8');
INSERT INTO `sys_role_permission` VALUES ('12', '10');
INSERT INTO `sys_role_permission` VALUES ('12', '12');
INSERT INTO `sys_role_permission` VALUES ('12', '13');
INSERT INTO `sys_role_permission` VALUES ('12', '14');
INSERT INTO `sys_role_permission` VALUES ('12', '22');
INSERT INTO `sys_role_permission` VALUES ('12', '24');
INSERT INTO `sys_role_permission` VALUES ('12', '26');
INSERT INTO `sys_role_permission` VALUES ('12', '27');
INSERT INTO `sys_role_permission` VALUES ('12', '28');
INSERT INTO `sys_role_permission` VALUES ('12', '29');
INSERT INTO `sys_role_permission` VALUES ('12', '33');
INSERT INTO `sys_role_permission` VALUES ('12', '34');
INSERT INTO `sys_role_permission` VALUES ('12', '35');
INSERT INTO `sys_role_permission` VALUES ('12', '36');
INSERT INTO `sys_role_permission` VALUES ('12', '41');
INSERT INTO `sys_role_permission` VALUES ('12', '42');
INSERT INTO `sys_role_permission` VALUES ('12', '43');
INSERT INTO `sys_role_permission` VALUES ('12', '44');
INSERT INTO `sys_role_permission` VALUES ('12', '45');
INSERT INTO `sys_role_permission` VALUES ('12', '46');
INSERT INTO `sys_role_permission` VALUES ('12', '47');
INSERT INTO `sys_role_permission` VALUES ('12', '48');
INSERT INTO `sys_role_permission` VALUES ('12', '49');
INSERT INTO `sys_role_permission` VALUES ('12', '50');
INSERT INTO `sys_role_permission` VALUES ('12', '51');
INSERT INTO `sys_role_permission` VALUES ('12', '52');
INSERT INTO `sys_role_permission` VALUES ('12', '53');
INSERT INTO `sys_role_permission` VALUES ('12', '54');
INSERT INTO `sys_role_permission` VALUES ('12', '55');
INSERT INTO `sys_role_permission` VALUES ('12', '56');
INSERT INTO `sys_role_permission` VALUES ('12', '57');
INSERT INTO `sys_role_permission` VALUES ('12', '58');
INSERT INTO `sys_role_permission` VALUES ('12', '59');
INSERT INTO `sys_role_permission` VALUES ('12', '67');
INSERT INTO `sys_role_permission` VALUES ('12', '68');
INSERT INTO `sys_role_permission` VALUES ('12', '69');
INSERT INTO `sys_role_permission` VALUES ('12', '70');
INSERT INTO `sys_role_permission` VALUES ('12', '75');
INSERT INTO `sys_role_permission` VALUES ('12', '76');
INSERT INTO `sys_role_permission` VALUES ('12', '77');
INSERT INTO `sys_role_permission` VALUES ('12', '78');
INSERT INTO `sys_role_permission` VALUES ('12', '79');
INSERT INTO `sys_role_permission` VALUES ('12', '80');
INSERT INTO `sys_role_permission` VALUES ('12', '81');
INSERT INTO `sys_role_permission` VALUES ('12', '82');
INSERT INTO `sys_role_permission` VALUES ('12', '83');
INSERT INTO `sys_role_permission` VALUES ('12', '84');
INSERT INTO `sys_role_permission` VALUES ('12', '85');
INSERT INTO `sys_role_permission` VALUES ('12', '86');
INSERT INTO `sys_role_permission` VALUES ('12', '115');
INSERT INTO `sys_role_permission` VALUES ('12', '116');
INSERT INTO `sys_role_permission` VALUES ('12', '1');
INSERT INTO `sys_role_permission` VALUES ('12', '2');
INSERT INTO `sys_role_permission` VALUES ('12', '3');
INSERT INTO `sys_role_permission` VALUES ('12', '6');
INSERT INTO `sys_role_permission` VALUES ('12', '7');
INSERT INTO `sys_role_permission` VALUES ('12', '8');
INSERT INTO `sys_role_permission` VALUES ('12', '10');
INSERT INTO `sys_role_permission` VALUES ('12', '11');
INSERT INTO `sys_role_permission` VALUES ('12', '12');
INSERT INTO `sys_role_permission` VALUES ('12', '13');
INSERT INTO `sys_role_permission` VALUES ('12', '14');
INSERT INTO `sys_role_permission` VALUES ('12', '21');
INSERT INTO `sys_role_permission` VALUES ('12', '22');
INSERT INTO `sys_role_permission` VALUES ('12', '24');
INSERT INTO `sys_role_permission` VALUES ('12', '26');
INSERT INTO `sys_role_permission` VALUES ('12', '27');
INSERT INTO `sys_role_permission` VALUES ('12', '28');
INSERT INTO `sys_role_permission` VALUES ('12', '29');
INSERT INTO `sys_role_permission` VALUES ('12', '33');
INSERT INTO `sys_role_permission` VALUES ('12', '34');
INSERT INTO `sys_role_permission` VALUES ('12', '35');
INSERT INTO `sys_role_permission` VALUES ('12', '36');
INSERT INTO `sys_role_permission` VALUES ('12', '41');
INSERT INTO `sys_role_permission` VALUES ('12', '42');
INSERT INTO `sys_role_permission` VALUES ('12', '43');
INSERT INTO `sys_role_permission` VALUES ('12', '44');
INSERT INTO `sys_role_permission` VALUES ('12', '45');
INSERT INTO `sys_role_permission` VALUES ('12', '46');
INSERT INTO `sys_role_permission` VALUES ('12', '47');
INSERT INTO `sys_role_permission` VALUES ('12', '48');
INSERT INTO `sys_role_permission` VALUES ('12', '49');
INSERT INTO `sys_role_permission` VALUES ('12', '50');
INSERT INTO `sys_role_permission` VALUES ('12', '51');
INSERT INTO `sys_role_permission` VALUES ('12', '52');
INSERT INTO `sys_role_permission` VALUES ('12', '53');
INSERT INTO `sys_role_permission` VALUES ('12', '54');
INSERT INTO `sys_role_permission` VALUES ('12', '55');
INSERT INTO `sys_role_permission` VALUES ('12', '56');
INSERT INTO `sys_role_permission` VALUES ('12', '57');
INSERT INTO `sys_role_permission` VALUES ('12', '58');
INSERT INTO `sys_role_permission` VALUES ('12', '59');
INSERT INTO `sys_role_permission` VALUES ('12', '67');
INSERT INTO `sys_role_permission` VALUES ('12', '68');
INSERT INTO `sys_role_permission` VALUES ('12', '69');
INSERT INTO `sys_role_permission` VALUES ('12', '70');
INSERT INTO `sys_role_permission` VALUES ('12', '71');
INSERT INTO `sys_role_permission` VALUES ('12', '72');
INSERT INTO `sys_role_permission` VALUES ('12', '73');
INSERT INTO `sys_role_permission` VALUES ('12', '74');
INSERT INTO `sys_role_permission` VALUES ('12', '75');
INSERT INTO `sys_role_permission` VALUES ('12', '76');
INSERT INTO `sys_role_permission` VALUES ('12', '77');
INSERT INTO `sys_role_permission` VALUES ('12', '78');
INSERT INTO `sys_role_permission` VALUES ('12', '79');
INSERT INTO `sys_role_permission` VALUES ('12', '80');
INSERT INTO `sys_role_permission` VALUES ('12', '81');
INSERT INTO `sys_role_permission` VALUES ('12', '82');
INSERT INTO `sys_role_permission` VALUES ('12', '83');
INSERT INTO `sys_role_permission` VALUES ('12', '84');
INSERT INTO `sys_role_permission` VALUES ('12', '85');
INSERT INTO `sys_role_permission` VALUES ('12', '86');
INSERT INTO `sys_role_permission` VALUES ('12', '111');
INSERT INTO `sys_role_permission` VALUES ('12', '112');
INSERT INTO `sys_role_permission` VALUES ('12', '113');
INSERT INTO `sys_role_permission` VALUES ('12', '114');
INSERT INTO `sys_role_permission` VALUES ('12', '115');
INSERT INTO `sys_role_permission` VALUES ('12', '116');
INSERT INTO `sys_role_permission` VALUES ('12', '117');
INSERT INTO `sys_role_permission` VALUES ('12', '118');
INSERT INTO `sys_role_permission` VALUES ('12', '1');
INSERT INTO `sys_role_permission` VALUES ('12', '2');
INSERT INTO `sys_role_permission` VALUES ('12', '3');
INSERT INTO `sys_role_permission` VALUES ('12', '6');
INSERT INTO `sys_role_permission` VALUES ('12', '7');
INSERT INTO `sys_role_permission` VALUES ('12', '8');
INSERT INTO `sys_role_permission` VALUES ('12', '10');
INSERT INTO `sys_role_permission` VALUES ('12', '11');
INSERT INTO `sys_role_permission` VALUES ('12', '12');
INSERT INTO `sys_role_permission` VALUES ('12', '13');
INSERT INTO `sys_role_permission` VALUES ('12', '18');
INSERT INTO `sys_role_permission` VALUES ('12', '19');
INSERT INTO `sys_role_permission` VALUES ('12', '20');
INSERT INTO `sys_role_permission` VALUES ('12', '21');
INSERT INTO `sys_role_permission` VALUES ('12', '22');
INSERT INTO `sys_role_permission` VALUES ('12', '24');
INSERT INTO `sys_role_permission` VALUES ('12', '27');
INSERT INTO `sys_role_permission` VALUES ('12', '28');
INSERT INTO `sys_role_permission` VALUES ('12', '29');
INSERT INTO `sys_role_permission` VALUES ('12', '33');
INSERT INTO `sys_role_permission` VALUES ('12', '34');
INSERT INTO `sys_role_permission` VALUES ('12', '35');
INSERT INTO `sys_role_permission` VALUES ('12', '36');
INSERT INTO `sys_role_permission` VALUES ('12', '45');
INSERT INTO `sys_role_permission` VALUES ('12', '46');
INSERT INTO `sys_role_permission` VALUES ('12', '47');
INSERT INTO `sys_role_permission` VALUES ('12', '48');
INSERT INTO `sys_role_permission` VALUES ('12', '49');
INSERT INTO `sys_role_permission` VALUES ('12', '50');
INSERT INTO `sys_role_permission` VALUES ('12', '51');
INSERT INTO `sys_role_permission` VALUES ('12', '52');
INSERT INTO `sys_role_permission` VALUES ('12', '53');
INSERT INTO `sys_role_permission` VALUES ('12', '54');
INSERT INTO `sys_role_permission` VALUES ('12', '55');
INSERT INTO `sys_role_permission` VALUES ('12', '56');
INSERT INTO `sys_role_permission` VALUES ('12', '57');
INSERT INTO `sys_role_permission` VALUES ('12', '58');
INSERT INTO `sys_role_permission` VALUES ('12', '59');
INSERT INTO `sys_role_permission` VALUES ('12', '67');
INSERT INTO `sys_role_permission` VALUES ('12', '68');
INSERT INTO `sys_role_permission` VALUES ('12', '69');
INSERT INTO `sys_role_permission` VALUES ('12', '70');
INSERT INTO `sys_role_permission` VALUES ('12', '71');
INSERT INTO `sys_role_permission` VALUES ('12', '72');
INSERT INTO `sys_role_permission` VALUES ('12', '73');
INSERT INTO `sys_role_permission` VALUES ('12', '74');
INSERT INTO `sys_role_permission` VALUES ('12', '75');
INSERT INTO `sys_role_permission` VALUES ('12', '76');
INSERT INTO `sys_role_permission` VALUES ('12', '77');
INSERT INTO `sys_role_permission` VALUES ('12', '78');
INSERT INTO `sys_role_permission` VALUES ('12', '79');
INSERT INTO `sys_role_permission` VALUES ('12', '80');
INSERT INTO `sys_role_permission` VALUES ('12', '81');
INSERT INTO `sys_role_permission` VALUES ('12', '82');
INSERT INTO `sys_role_permission` VALUES ('12', '99');
INSERT INTO `sys_role_permission` VALUES ('12', '100');
INSERT INTO `sys_role_permission` VALUES ('12', '101');
INSERT INTO `sys_role_permission` VALUES ('12', '102');
INSERT INTO `sys_role_permission` VALUES ('12', '103');
INSERT INTO `sys_role_permission` VALUES ('12', '104');
INSERT INTO `sys_role_permission` VALUES ('12', '105');
INSERT INTO `sys_role_permission` VALUES ('12', '106');
INSERT INTO `sys_role_permission` VALUES ('12', '107');
INSERT INTO `sys_role_permission` VALUES ('12', '108');
INSERT INTO `sys_role_permission` VALUES ('12', '109');
INSERT INTO `sys_role_permission` VALUES ('12', '110');
INSERT INTO `sys_role_permission` VALUES ('12', '111');
INSERT INTO `sys_role_permission` VALUES ('12', '112');
INSERT INTO `sys_role_permission` VALUES ('12', '113');
INSERT INTO `sys_role_permission` VALUES ('12', '114');
INSERT INTO `sys_role_permission` VALUES ('12', '115');
INSERT INTO `sys_role_permission` VALUES ('12', '116');
INSERT INTO `sys_role_permission` VALUES ('12', '117');
INSERT INTO `sys_role_permission` VALUES ('12', '118');
INSERT INTO `sys_role_permission` VALUES ('14', '1');
INSERT INTO `sys_role_permission` VALUES ('14', '2');
INSERT INTO `sys_role_permission` VALUES ('14', '3');
INSERT INTO `sys_role_permission` VALUES ('14', '4');
INSERT INTO `sys_role_permission` VALUES ('14', '5');
INSERT INTO `sys_role_permission` VALUES ('14', '6');
INSERT INTO `sys_role_permission` VALUES ('14', '7');
INSERT INTO `sys_role_permission` VALUES ('14', '8');
INSERT INTO `sys_role_permission` VALUES ('14', '9');
INSERT INTO `sys_role_permission` VALUES ('14', '10');
INSERT INTO `sys_role_permission` VALUES ('14', '11');
INSERT INTO `sys_role_permission` VALUES ('14', '12');
INSERT INTO `sys_role_permission` VALUES ('14', '13');
INSERT INTO `sys_role_permission` VALUES ('14', '14');
INSERT INTO `sys_role_permission` VALUES ('14', '15');
INSERT INTO `sys_role_permission` VALUES ('14', '16');
INSERT INTO `sys_role_permission` VALUES ('14', '17');
INSERT INTO `sys_role_permission` VALUES ('14', '18');
INSERT INTO `sys_role_permission` VALUES ('14', '21');
INSERT INTO `sys_role_permission` VALUES ('14', '22');
INSERT INTO `sys_role_permission` VALUES ('14', '24');
INSERT INTO `sys_role_permission` VALUES ('14', '30');
INSERT INTO `sys_role_permission` VALUES ('14', '31');
INSERT INTO `sys_role_permission` VALUES ('14', '32');
INSERT INTO `sys_role_permission` VALUES ('14', '33');
INSERT INTO `sys_role_permission` VALUES ('14', '34');
INSERT INTO `sys_role_permission` VALUES ('14', '35');
INSERT INTO `sys_role_permission` VALUES ('14', '36');
INSERT INTO `sys_role_permission` VALUES ('14', '38');
INSERT INTO `sys_role_permission` VALUES ('14', '39');
INSERT INTO `sys_role_permission` VALUES ('14', '40');
INSERT INTO `sys_role_permission` VALUES ('14', '42');
INSERT INTO `sys_role_permission` VALUES ('14', '43');
INSERT INTO `sys_role_permission` VALUES ('14', '44');
INSERT INTO `sys_role_permission` VALUES ('14', '46');
INSERT INTO `sys_role_permission` VALUES ('14', '47');
INSERT INTO `sys_role_permission` VALUES ('14', '48');
INSERT INTO `sys_role_permission` VALUES ('14', '49');
INSERT INTO `sys_role_permission` VALUES ('14', '51');
INSERT INTO `sys_role_permission` VALUES ('14', '52');
INSERT INTO `sys_role_permission` VALUES ('14', '53');
INSERT INTO `sys_role_permission` VALUES ('14', '54');
INSERT INTO `sys_role_permission` VALUES ('14', '55');
INSERT INTO `sys_role_permission` VALUES ('14', '56');
INSERT INTO `sys_role_permission` VALUES ('14', '57');
INSERT INTO `sys_role_permission` VALUES ('14', '60');
INSERT INTO `sys_role_permission` VALUES ('14', '61');
INSERT INTO `sys_role_permission` VALUES ('14', '62');
INSERT INTO `sys_role_permission` VALUES ('14', '63');
INSERT INTO `sys_role_permission` VALUES ('14', '64');
INSERT INTO `sys_role_permission` VALUES ('14', '65');
INSERT INTO `sys_role_permission` VALUES ('14', '66');
INSERT INTO `sys_role_permission` VALUES ('14', '67');
INSERT INTO `sys_role_permission` VALUES ('14', '68');
INSERT INTO `sys_role_permission` VALUES ('14', '69');
INSERT INTO `sys_role_permission` VALUES ('14', '70');
INSERT INTO `sys_role_permission` VALUES ('14', '71');
INSERT INTO `sys_role_permission` VALUES ('14', '72');
INSERT INTO `sys_role_permission` VALUES ('14', '73');
INSERT INTO `sys_role_permission` VALUES ('14', '74');
INSERT INTO `sys_role_permission` VALUES ('14', '75');
INSERT INTO `sys_role_permission` VALUES ('14', '76');
INSERT INTO `sys_role_permission` VALUES ('14', '77');
INSERT INTO `sys_role_permission` VALUES ('14', '78');
INSERT INTO `sys_role_permission` VALUES ('14', '79');
INSERT INTO `sys_role_permission` VALUES ('14', '80');
INSERT INTO `sys_role_permission` VALUES ('14', '81');
INSERT INTO `sys_role_permission` VALUES ('14', '82');
INSERT INTO `sys_role_permission` VALUES ('14', '83');
INSERT INTO `sys_role_permission` VALUES ('14', '84');
INSERT INTO `sys_role_permission` VALUES ('14', '85');
INSERT INTO `sys_role_permission` VALUES ('14', '86');
INSERT INTO `sys_role_permission` VALUES ('14', '87');
INSERT INTO `sys_role_permission` VALUES ('14', '88');
INSERT INTO `sys_role_permission` VALUES ('14', '89');
INSERT INTO `sys_role_permission` VALUES ('14', '90');
INSERT INTO `sys_role_permission` VALUES ('14', '91');
INSERT INTO `sys_role_permission` VALUES ('14', '92');
INSERT INTO `sys_role_permission` VALUES ('14', '93');
INSERT INTO `sys_role_permission` VALUES ('14', '94');
INSERT INTO `sys_role_permission` VALUES ('14', '95');
INSERT INTO `sys_role_permission` VALUES ('14', '96');
INSERT INTO `sys_role_permission` VALUES ('14', '97');
INSERT INTO `sys_role_permission` VALUES ('14', '98');
INSERT INTO `sys_role_permission` VALUES ('14', '99');
INSERT INTO `sys_role_permission` VALUES ('14', '100');
INSERT INTO `sys_role_permission` VALUES ('14', '101');
INSERT INTO `sys_role_permission` VALUES ('14', '102');
INSERT INTO `sys_role_permission` VALUES ('14', '111');
INSERT INTO `sys_role_permission` VALUES ('14', '112');
INSERT INTO `sys_role_permission` VALUES ('14', '113');
INSERT INTO `sys_role_permission` VALUES ('14', '114');
INSERT INTO `sys_role_permission` VALUES ('14', '115');
INSERT INTO `sys_role_permission` VALUES ('14', '116');
INSERT INTO `sys_role_permission` VALUES ('11', '1');
INSERT INTO `sys_role_permission` VALUES ('11', '2');
INSERT INTO `sys_role_permission` VALUES ('11', '3');
INSERT INTO `sys_role_permission` VALUES ('11', '11');
INSERT INTO `sys_role_permission` VALUES ('11', '67');
INSERT INTO `sys_role_permission` VALUES ('11', '68');
INSERT INTO `sys_role_permission` VALUES ('11', '69');
INSERT INTO `sys_role_permission` VALUES ('11', '70');
INSERT INTO `sys_role_permission` VALUES ('11', '12');
INSERT INTO `sys_role_permission` VALUES ('11', '71');
INSERT INTO `sys_role_permission` VALUES ('11', '72');
INSERT INTO `sys_role_permission` VALUES ('11', '73');
INSERT INTO `sys_role_permission` VALUES ('11', '74');
INSERT INTO `sys_role_permission` VALUES ('11', '22');
INSERT INTO `sys_role_permission` VALUES ('11', '111');
INSERT INTO `sys_role_permission` VALUES ('11', '112');
INSERT INTO `sys_role_permission` VALUES ('11', '113');
INSERT INTO `sys_role_permission` VALUES ('11', '114');
INSERT INTO `sys_role_permission` VALUES ('11', '24');
INSERT INTO `sys_role_permission` VALUES ('11', '33');
INSERT INTO `sys_role_permission` VALUES ('11', '34');
INSERT INTO `sys_role_permission` VALUES ('11', '35');
INSERT INTO `sys_role_permission` VALUES ('11', '36');
INSERT INTO `sys_role_permission` VALUES ('11', '115');
INSERT INTO `sys_role_permission` VALUES ('11', '116');
INSERT INTO `sys_role_permission` VALUES ('11', '117');
INSERT INTO `sys_role_permission` VALUES ('11', '118');
INSERT INTO `sys_role_permission` VALUES ('11', '4');
INSERT INTO `sys_role_permission` VALUES ('11', '13');
INSERT INTO `sys_role_permission` VALUES ('11', '75');
INSERT INTO `sys_role_permission` VALUES ('11', '76');
INSERT INTO `sys_role_permission` VALUES ('11', '77');
INSERT INTO `sys_role_permission` VALUES ('11', '78');
INSERT INTO `sys_role_permission` VALUES ('11', '14');
INSERT INTO `sys_role_permission` VALUES ('11', '79');
INSERT INTO `sys_role_permission` VALUES ('11', '80');
INSERT INTO `sys_role_permission` VALUES ('11', '81');
INSERT INTO `sys_role_permission` VALUES ('11', '82');
INSERT INTO `sys_role_permission` VALUES ('11', '15');
INSERT INTO `sys_role_permission` VALUES ('11', '83');
INSERT INTO `sys_role_permission` VALUES ('11', '84');
INSERT INTO `sys_role_permission` VALUES ('11', '85');
INSERT INTO `sys_role_permission` VALUES ('11', '86');
INSERT INTO `sys_role_permission` VALUES ('11', '7');
INSERT INTO `sys_role_permission` VALUES ('11', '19');
INSERT INTO `sys_role_permission` VALUES ('11', '99');
INSERT INTO `sys_role_permission` VALUES ('11', '100');
INSERT INTO `sys_role_permission` VALUES ('11', '101');
INSERT INTO `sys_role_permission` VALUES ('11', '102');
INSERT INTO `sys_role_permission` VALUES ('11', '20');
INSERT INTO `sys_role_permission` VALUES ('11', '103');
INSERT INTO `sys_role_permission` VALUES ('11', '104');
INSERT INTO `sys_role_permission` VALUES ('11', '105');
INSERT INTO `sys_role_permission` VALUES ('11', '106');
INSERT INTO `sys_role_permission` VALUES ('11', '21');
INSERT INTO `sys_role_permission` VALUES ('11', '107');
INSERT INTO `sys_role_permission` VALUES ('11', '108');
INSERT INTO `sys_role_permission` VALUES ('11', '109');
INSERT INTO `sys_role_permission` VALUES ('11', '110');
INSERT INTO `sys_role_permission` VALUES ('11', '8');
INSERT INTO `sys_role_permission` VALUES ('11', '23');
INSERT INTO `sys_role_permission` VALUES ('11', '67');
INSERT INTO `sys_role_permission` VALUES ('11', '68');
INSERT INTO `sys_role_permission` VALUES ('11', '69');
INSERT INTO `sys_role_permission` VALUES ('11', '70');
INSERT INTO `sys_role_permission` VALUES ('11', '71');
INSERT INTO `sys_role_permission` VALUES ('11', '72');
INSERT INTO `sys_role_permission` VALUES ('11', '73');
INSERT INTO `sys_role_permission` VALUES ('11', '74');
INSERT INTO `sys_role_permission` VALUES ('11', '111');
INSERT INTO `sys_role_permission` VALUES ('11', '112');
INSERT INTO `sys_role_permission` VALUES ('11', '113');
INSERT INTO `sys_role_permission` VALUES ('11', '114');
INSERT INTO `sys_role_permission` VALUES ('11', '33');
INSERT INTO `sys_role_permission` VALUES ('11', '34');
INSERT INTO `sys_role_permission` VALUES ('11', '35');
INSERT INTO `sys_role_permission` VALUES ('11', '36');
INSERT INTO `sys_role_permission` VALUES ('11', '75');
INSERT INTO `sys_role_permission` VALUES ('11', '76');
INSERT INTO `sys_role_permission` VALUES ('11', '77');
INSERT INTO `sys_role_permission` VALUES ('11', '78');
INSERT INTO `sys_role_permission` VALUES ('11', '79');
INSERT INTO `sys_role_permission` VALUES ('11', '80');
INSERT INTO `sys_role_permission` VALUES ('11', '81');
INSERT INTO `sys_role_permission` VALUES ('11', '82');
INSERT INTO `sys_role_permission` VALUES ('11', '83');
INSERT INTO `sys_role_permission` VALUES ('11', '84');
INSERT INTO `sys_role_permission` VALUES ('11', '85');
INSERT INTO `sys_role_permission` VALUES ('11', '86');
INSERT INTO `sys_role_permission` VALUES ('11', '99');
INSERT INTO `sys_role_permission` VALUES ('11', '100');
INSERT INTO `sys_role_permission` VALUES ('11', '101');
INSERT INTO `sys_role_permission` VALUES ('11', '102');
INSERT INTO `sys_role_permission` VALUES ('11', '103');
INSERT INTO `sys_role_permission` VALUES ('11', '104');
INSERT INTO `sys_role_permission` VALUES ('11', '105');
INSERT INTO `sys_role_permission` VALUES ('11', '106');
INSERT INTO `sys_role_permission` VALUES ('11', '107');
INSERT INTO `sys_role_permission` VALUES ('11', '108');
INSERT INTO `sys_role_permission` VALUES ('11', '109');
INSERT INTO `sys_role_permission` VALUES ('11', '110');
INSERT INTO `sys_role_permission` VALUES ('10', '1');
INSERT INTO `sys_role_permission` VALUES ('10', '2');
INSERT INTO `sys_role_permission` VALUES ('10', '3');
INSERT INTO `sys_role_permission` VALUES ('10', '11');
INSERT INTO `sys_role_permission` VALUES ('10', '67');
INSERT INTO `sys_role_permission` VALUES ('10', '68');
INSERT INTO `sys_role_permission` VALUES ('10', '69');
INSERT INTO `sys_role_permission` VALUES ('10', '70');
INSERT INTO `sys_role_permission` VALUES ('10', '12');
INSERT INTO `sys_role_permission` VALUES ('10', '71');
INSERT INTO `sys_role_permission` VALUES ('10', '72');
INSERT INTO `sys_role_permission` VALUES ('10', '73');
INSERT INTO `sys_role_permission` VALUES ('10', '74');
INSERT INTO `sys_role_permission` VALUES ('10', '22');
INSERT INTO `sys_role_permission` VALUES ('10', '111');
INSERT INTO `sys_role_permission` VALUES ('10', '112');
INSERT INTO `sys_role_permission` VALUES ('10', '113');
INSERT INTO `sys_role_permission` VALUES ('10', '114');
INSERT INTO `sys_role_permission` VALUES ('10', '24');
INSERT INTO `sys_role_permission` VALUES ('10', '33');
INSERT INTO `sys_role_permission` VALUES ('10', '34');
INSERT INTO `sys_role_permission` VALUES ('10', '35');
INSERT INTO `sys_role_permission` VALUES ('10', '36');
INSERT INTO `sys_role_permission` VALUES ('10', '115');
INSERT INTO `sys_role_permission` VALUES ('10', '116');
INSERT INTO `sys_role_permission` VALUES ('10', '117');
INSERT INTO `sys_role_permission` VALUES ('10', '118');
INSERT INTO `sys_role_permission` VALUES ('10', '4');
INSERT INTO `sys_role_permission` VALUES ('10', '13');
INSERT INTO `sys_role_permission` VALUES ('10', '75');
INSERT INTO `sys_role_permission` VALUES ('10', '76');
INSERT INTO `sys_role_permission` VALUES ('10', '77');
INSERT INTO `sys_role_permission` VALUES ('10', '78');
INSERT INTO `sys_role_permission` VALUES ('10', '14');
INSERT INTO `sys_role_permission` VALUES ('10', '79');
INSERT INTO `sys_role_permission` VALUES ('10', '80');
INSERT INTO `sys_role_permission` VALUES ('10', '81');
INSERT INTO `sys_role_permission` VALUES ('10', '82');
INSERT INTO `sys_role_permission` VALUES ('10', '15');
INSERT INTO `sys_role_permission` VALUES ('10', '83');
INSERT INTO `sys_role_permission` VALUES ('10', '84');
INSERT INTO `sys_role_permission` VALUES ('10', '85');
INSERT INTO `sys_role_permission` VALUES ('10', '86');
INSERT INTO `sys_role_permission` VALUES ('10', '8');
INSERT INTO `sys_role_permission` VALUES ('10', '23');
INSERT INTO `sys_role_permission` VALUES ('10', '67');
INSERT INTO `sys_role_permission` VALUES ('10', '68');
INSERT INTO `sys_role_permission` VALUES ('10', '69');
INSERT INTO `sys_role_permission` VALUES ('10', '70');
INSERT INTO `sys_role_permission` VALUES ('10', '71');
INSERT INTO `sys_role_permission` VALUES ('10', '72');
INSERT INTO `sys_role_permission` VALUES ('10', '73');
INSERT INTO `sys_role_permission` VALUES ('10', '74');
INSERT INTO `sys_role_permission` VALUES ('10', '111');
INSERT INTO `sys_role_permission` VALUES ('10', '112');
INSERT INTO `sys_role_permission` VALUES ('10', '113');
INSERT INTO `sys_role_permission` VALUES ('10', '114');
INSERT INTO `sys_role_permission` VALUES ('10', '33');
INSERT INTO `sys_role_permission` VALUES ('10', '34');
INSERT INTO `sys_role_permission` VALUES ('10', '35');
INSERT INTO `sys_role_permission` VALUES ('10', '36');
INSERT INTO `sys_role_permission` VALUES ('10', '75');
INSERT INTO `sys_role_permission` VALUES ('10', '76');
INSERT INTO `sys_role_permission` VALUES ('10', '77');
INSERT INTO `sys_role_permission` VALUES ('10', '78');
INSERT INTO `sys_role_permission` VALUES ('10', '79');
INSERT INTO `sys_role_permission` VALUES ('10', '80');
INSERT INTO `sys_role_permission` VALUES ('10', '81');
INSERT INTO `sys_role_permission` VALUES ('10', '82');
INSERT INTO `sys_role_permission` VALUES ('10', '83');
INSERT INTO `sys_role_permission` VALUES ('10', '84');
INSERT INTO `sys_role_permission` VALUES ('10', '85');
INSERT INTO `sys_role_permission` VALUES ('10', '86');
INSERT INTO `sys_role_permission` VALUES ('9', '1');
INSERT INTO `sys_role_permission` VALUES ('9', '2');
INSERT INTO `sys_role_permission` VALUES ('9', '3');
INSERT INTO `sys_role_permission` VALUES ('9', '11');
INSERT INTO `sys_role_permission` VALUES ('9', '67');
INSERT INTO `sys_role_permission` VALUES ('9', '68');
INSERT INTO `sys_role_permission` VALUES ('9', '69');
INSERT INTO `sys_role_permission` VALUES ('9', '70');
INSERT INTO `sys_role_permission` VALUES ('9', '12');
INSERT INTO `sys_role_permission` VALUES ('9', '71');
INSERT INTO `sys_role_permission` VALUES ('9', '72');
INSERT INTO `sys_role_permission` VALUES ('9', '73');
INSERT INTO `sys_role_permission` VALUES ('9', '74');
INSERT INTO `sys_role_permission` VALUES ('9', '22');
INSERT INTO `sys_role_permission` VALUES ('9', '111');
INSERT INTO `sys_role_permission` VALUES ('9', '112');
INSERT INTO `sys_role_permission` VALUES ('9', '113');
INSERT INTO `sys_role_permission` VALUES ('9', '114');
INSERT INTO `sys_role_permission` VALUES ('9', '24');
INSERT INTO `sys_role_permission` VALUES ('9', '33');
INSERT INTO `sys_role_permission` VALUES ('9', '34');
INSERT INTO `sys_role_permission` VALUES ('9', '35');
INSERT INTO `sys_role_permission` VALUES ('9', '36');
INSERT INTO `sys_role_permission` VALUES ('9', '115');
INSERT INTO `sys_role_permission` VALUES ('9', '116');
INSERT INTO `sys_role_permission` VALUES ('9', '4');
INSERT INTO `sys_role_permission` VALUES ('9', '13');
INSERT INTO `sys_role_permission` VALUES ('9', '75');
INSERT INTO `sys_role_permission` VALUES ('9', '76');
INSERT INTO `sys_role_permission` VALUES ('9', '77');
INSERT INTO `sys_role_permission` VALUES ('9', '78');
INSERT INTO `sys_role_permission` VALUES ('9', '14');
INSERT INTO `sys_role_permission` VALUES ('9', '79');
INSERT INTO `sys_role_permission` VALUES ('9', '80');
INSERT INTO `sys_role_permission` VALUES ('9', '81');
INSERT INTO `sys_role_permission` VALUES ('9', '82');
INSERT INTO `sys_role_permission` VALUES ('9', '15');
INSERT INTO `sys_role_permission` VALUES ('9', '83');
INSERT INTO `sys_role_permission` VALUES ('9', '84');
INSERT INTO `sys_role_permission` VALUES ('9', '85');
INSERT INTO `sys_role_permission` VALUES ('9', '86');
INSERT INTO `sys_role_permission` VALUES ('9', '8');
INSERT INTO `sys_role_permission` VALUES ('9', '23');
INSERT INTO `sys_role_permission` VALUES ('9', '67');
INSERT INTO `sys_role_permission` VALUES ('9', '68');
INSERT INTO `sys_role_permission` VALUES ('9', '69');
INSERT INTO `sys_role_permission` VALUES ('9', '70');
INSERT INTO `sys_role_permission` VALUES ('9', '71');
INSERT INTO `sys_role_permission` VALUES ('9', '72');
INSERT INTO `sys_role_permission` VALUES ('9', '73');
INSERT INTO `sys_role_permission` VALUES ('9', '74');
INSERT INTO `sys_role_permission` VALUES ('9', '111');
INSERT INTO `sys_role_permission` VALUES ('9', '112');
INSERT INTO `sys_role_permission` VALUES ('9', '113');
INSERT INTO `sys_role_permission` VALUES ('9', '114');
INSERT INTO `sys_role_permission` VALUES ('9', '33');
INSERT INTO `sys_role_permission` VALUES ('9', '34');
INSERT INTO `sys_role_permission` VALUES ('9', '35');
INSERT INTO `sys_role_permission` VALUES ('9', '36');
INSERT INTO `sys_role_permission` VALUES ('9', '75');
INSERT INTO `sys_role_permission` VALUES ('9', '76');
INSERT INTO `sys_role_permission` VALUES ('9', '77');
INSERT INTO `sys_role_permission` VALUES ('9', '78');
INSERT INTO `sys_role_permission` VALUES ('9', '79');
INSERT INTO `sys_role_permission` VALUES ('9', '80');
INSERT INTO `sys_role_permission` VALUES ('9', '81');
INSERT INTO `sys_role_permission` VALUES ('9', '82');
INSERT INTO `sys_role_permission` VALUES ('9', '83');
INSERT INTO `sys_role_permission` VALUES ('9', '84');
INSERT INTO `sys_role_permission` VALUES ('9', '85');
INSERT INTO `sys_role_permission` VALUES ('9', '86');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `login_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pwd` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `available` int(11) DEFAULT 1 COMMENT '是否可用，0不可用，1可用',
  `order_num` int(11) DEFAULT NULL COMMENT '排序码',
  `type` int(255) DEFAULT NULL COMMENT '用户类型[0超级管理员，1管理员，2普通用户]',
  `salt` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '盐',
  `user_photo` varchar(255) CHARACTER SET utf8 DEFAULT '' COMMENT '头像访问地址',
  `belong_user` int(11) DEFAULT NULL COMMENT '所属用户',
  `remark` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 DEFAULT '' COMMENT '手机号',
  `login_times` int(11) DEFAULT 0 COMMENT '登录次数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT COMMENT='InnoDB free: 9216 kB; (`deptid`) REFER `warehouse/sys_dept`(`id`) ON UPDATE CASC';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '超级管理员', 'system', '532ac00e86893901af5f0be6b704dbc7', '1', '1', '0', '04A93C74C8294AA09A8B974FD1F4ECBB', 'face.jpg', '1', '超级管理员12131', '', '1254', null, '2020-12-08 10:15:10', '1111222');
INSERT INTO `sys_user` VALUES ('2', '落亦-', 'luoyi', '532ac00e86893901af5f0be6b704dbc7', '1', '2', '0', '04A93C74C8294AA09A8B974FD1F4ECBB', '2020-10-30/751AADD7A9D54201B0F45E08F0C0D850.jpg_temp', '1', '超级管理员', '', '10', null, '2020-10-30 10:06:02', '');
INSERT INTO `sys_user` VALUES ('3', '系统管理员', 'adminSys', '532ac00e86893901af5f0be6b704dbc7', '1', '3', '1', '04A93C74C8294AA09A8B974FD1F4ECBB', '2020-10-30/3C4398373370405F97ADCE4BA37757DE.jpg_temp', '1', '系统管理员', '', '16', null, '2020-11-02 01:59:20', '');
INSERT INTO `sys_user` VALUES ('4', '深圳沸石智能技术有限公司', 'adminPC', 'd85a470abbe315b8c428ce476917991f', '1', '4', '1', 'F8B029B2E6754049842032674A942DA5', '2020-12-05/1AC8AFCF51354E3E91873B3BCAF805C3.PNG', '1', 'PC端管理员', '', '207', null, '2020-12-07 01:59:16', '深圳市');
INSERT INTO `sys_user` VALUES ('18', 'test01', '1111', '384bd193ecb1f6b286f58aa92e47eec9', '1', '1', '1', 'FAF7157A183640998C73754264A0E2C8', '', '1', '', '17373729061', '3', null, '2020-12-24 09:05:44', '');
INSERT INTO `sys_user` VALUES ('21', '深圳沸石', 'feishi', 'f1280ba91e17ee28708eb2518623d6b6', '1', null, '1', 'EC2AE114DE4C463C9D44C61B3884A4C5', '', '3', null, '18926575592', '0', '2020-11-03 10:39:19', '2020-11-03 10:39:19', '');
INSERT INTO `sys_user` VALUES ('22', 'YTLF', 'YTLF', '819f10142000c23d6bdf9441b8117900', '1', '5', '1', 'D5518822BAEE44C3B283ABF8DBD075FE', 'face.jpg', null, '', '', '261', null, '2020-12-01 07:39:46', '');
INSERT INTO `sys_user` VALUES ('25', '234234', '333', '6b814049168ce09f8c4a86ff668d2221', '1', '52', '1', 'A4BBFA88F3AC45D495F8A732E7A83D43', '', null, '', '1737456454', '0', null, null, '');
INSERT INTO `sys_user` VALUES ('26', '1', '111', 'da4a2db0f2c4beb23070e8efd66c16ba', '1', '111', '1', 'F3334C019A4448FF9095D2701C2A7BEC', '', null, '', '111', '0', null, null, '');
INSERT INTO `sys_user` VALUES ('27', '111111111111', '11111111', '28bdd1b18ef05f4794230aeb33a79693', '1', '11111', '1', '064975EA3FB5474A88ED05EB76AA7C67', '', null, '', '13526365689', '0', null, null, '');
INSERT INTO `sys_user` VALUES ('28', '111111', '11', '43b99f1fbc187705c9c164518d2a5311', '1', '111', '1', 'AC1EDEEB4E594B9995B3A700EF4B9C06', '', null, '', '11111', '0', null, null, '');
INSERT INTO `sys_user` VALUES ('29', '6666', '6666', 'a6bca22bc27ba5ed8d1cb8d0c8a32feb', '1', '16', '1', 'E55149DDFC214AE2AA81A64023733565', '', '22', '', '18565684220', '0', null, null, '');
INSERT INTO `sys_user` VALUES ('31', 'Fise', 'Fise', '8fbba35039b51d814fee01dcbc16637f', '1', '17', '1', 'ABCDE1C1D8054A3C8EA727D100039F65', '500.jpg', '22', '', '17688835203', '12', '2020-12-03 12:04:41', '2020-12-04 08:34:08', '');
INSERT INTO `sys_user` VALUES ('32', 'FISE_ZN', 'fise_zn', '9cce23faacf788e529570653f7d3046d', '1', '5', '1', '36682E7508CF46989E646BD539DB23CE', 'face.jpg', '1', '沸石智能考勤专用', '13424312276', '232', '2020-12-04 10:48:48', '2020-12-10 08:49:20', '');
INSERT INTO `sys_user` VALUES ('34', 'fiseTest', 'fiseTest', 'c8a3846be5c6b1109010dc15a48cbc22', '1', '6', '1', 'A400AC03DFC04D209855D20F043DE761', 'face.jpg', '1', '', '17373729066', '4', '2020-12-11 07:22:54', '2020-12-11 07:22:54', '');
INSERT INTO `sys_user` VALUES ('35', 'FISE_ZN_BJ', 'fise_zn_bj', '94708a1012ce36a31d0d2ee019463770', '1', '7', '1', 'E00597B99D3B49AF9584E6ED23CC7376', 'face.jpg', '1', '沸石智能北京专用', '13632549550', '3', '2020-12-21 06:03:46', '2020-12-21 06:15:21', '');
INSERT INTO `sys_user` VALUES ('36', '123123', '123123', '17ce55a419b9a35bbb182f686dba40df', '1', null, '1', '5DD8926F2E7C4D0BA14F6CA712B2585F', '', null, null, '17373729068', '0', '2020-12-24 09:09:17', '2020-12-24 09:09:17', '');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `uid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('3', '4');
INSERT INTO `sys_user_role` VALUES ('18', '5');
INSERT INTO `sys_user_role` VALUES ('19', '5');
INSERT INTO `sys_user_role` VALUES ('4', '9');
INSERT INTO `sys_user_role` VALUES ('4', '10');
INSERT INTO `sys_user_role` VALUES ('4', '11');
INSERT INTO `sys_user_role` VALUES ('22', '9');
INSERT INTO `sys_user_role` VALUES ('22', '10');
INSERT INTO `sys_user_role` VALUES ('22', '11');
INSERT INTO `sys_user_role` VALUES ('25', '9');
INSERT INTO `sys_user_role` VALUES ('25', '10');
INSERT INTO `sys_user_role` VALUES ('25', '11');
INSERT INTO `sys_user_role` VALUES ('26', '9');
INSERT INTO `sys_user_role` VALUES ('26', '10');
INSERT INTO `sys_user_role` VALUES ('26', '11');
INSERT INTO `sys_user_role` VALUES ('27', '9');
INSERT INTO `sys_user_role` VALUES ('27', '10');
INSERT INTO `sys_user_role` VALUES ('27', '11');
INSERT INTO `sys_user_role` VALUES ('28', '9');
INSERT INTO `sys_user_role` VALUES ('28', '10');
INSERT INTO `sys_user_role` VALUES ('28', '11');
INSERT INTO `sys_user_role` VALUES ('29', '9');
INSERT INTO `sys_user_role` VALUES ('29', '10');
INSERT INTO `sys_user_role` VALUES ('29', '11');
INSERT INTO `sys_user_role` VALUES ('31', '10');
INSERT INTO `sys_user_role` VALUES ('32', '9');
INSERT INTO `sys_user_role` VALUES ('32', '10');
INSERT INTO `sys_user_role` VALUES ('32', '11');
INSERT INTO `sys_user_role` VALUES ('34', '9');
INSERT INTO `sys_user_role` VALUES ('34', '10');
INSERT INTO `sys_user_role` VALUES ('34', '11');
INSERT INTO `sys_user_role` VALUES ('35', '9');
INSERT INTO `sys_user_role` VALUES ('35', '10');
INSERT INTO `sys_user_role` VALUES ('35', '11');
INSERT INTO `sys_user_role` VALUES ('36', '9');
INSERT INTO `sys_user_role` VALUES ('36', '10');
INSERT INTO `sys_user_role` VALUES ('36', '11');
