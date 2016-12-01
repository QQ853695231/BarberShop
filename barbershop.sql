/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : barbershop

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2016-03-10 22:10:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_consumercost
-- ----------------------------
DROP TABLE IF EXISTS `t_consumercost`;
CREATE TABLE `t_consumercost` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `way` varchar(20) DEFAULT NULL,
  `cost` double(10,2) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `time` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_consumercost
-- ----------------------------
INSERT INTO `t_consumercost` VALUES ('2', '18330116421', '李娜', 'card', '35.00', '在在在', 'Mon Feb 29 17:47:53 CST 2016');
INSERT INTO `t_consumercost` VALUES ('3', '18330116421', '李娜', 'card', '23.00', '', 'Mon Feb 29 18:02:00 CST 2016');
INSERT INTO `t_consumercost` VALUES ('5', null, null, 'money', '23.00', '海关海关海关海关', 'Mon Feb 29 18:08:13 CST 2016');
INSERT INTO `t_consumercost` VALUES ('6', '15531178347', '张甲龙', 'card', '20.00', '烫头\n拉直', 'Fri Mar 04 18:08:26 CST 2016');
INSERT INTO `t_consumercost` VALUES ('15', '18330116421', '李娜', 'card', '233.00', ' 在在在左工工 ', '2016年03月08日');
INSERT INTO `t_consumercost` VALUES ('16', '18330116421', '李娜', 'card', '199.00', '', '2016年03月08日');

-- ----------------------------
-- Table structure for t_creditexchange
-- ----------------------------
DROP TABLE IF EXISTS `t_creditexchange`;
CREATE TABLE `t_creditexchange` (
  `id` int(25) NOT NULL AUTO_INCREMENT,
  `exchangenumber` varchar(25) DEFAULT NULL,
  `exchangename` varchar(25) DEFAULT NULL,
  `exchangegoods` varchar(255) DEFAULT NULL,
  `costcredit` int(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_creditexchange
-- ----------------------------
INSERT INTO `t_creditexchange` VALUES ('1', '18330116421', '李娜', '洋娃娃', '20');
INSERT INTO `t_creditexchange` VALUES ('2', '18330116421', '李娜', '洋娃娃', '20');
INSERT INTO `t_creditexchange` VALUES ('3', '18330116421', '李娜', '洋娃娃', '20');
INSERT INTO `t_creditexchange` VALUES ('4', '18330116421', '李娜', '洋娃娃', '20');
INSERT INTO `t_creditexchange` VALUES ('5', '18330116421', '李娜', '在在在在', '20');

-- ----------------------------
-- Table structure for t_salary
-- ----------------------------
DROP TABLE IF EXISTS `t_salary`;
CREATE TABLE `t_salary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employeenum` varchar(255) DEFAULT NULL,
  `employeename` varchar(25) DEFAULT NULL,
  `shouldbepay` double(25,0) DEFAULT NULL,
  `actuallypay` double(25,0) DEFAULT NULL,
  `paytime` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_salary
-- ----------------------------
INSERT INTO `t_salary` VALUES ('2', '18330116421', '李娜', '2000', '333', '2016年03月06日', 'ss');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `Username` varchar(50) DEFAULT NULL,
  `Password` varchar(50) DEFAULT NULL,
  `Power` varchar(50) DEFAULT NULL,
  `Realname` varchar(50) DEFAULT NULL,
  `Tel` varchar(50) NOT NULL,
  PRIMARY KEY (`Tel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1111111', '33333', '收银员', 'aaaa', '11111111111');
INSERT INTO `t_user` VALUES ('张', '1122', '1', '张甲龙', '15531178347');
INSERT INTO `t_user` VALUES ('lina', '1223', '0', '李娜', '18330116421');

-- ----------------------------
-- Table structure for t_vip
-- ----------------------------
DROP TABLE IF EXISTS `t_vip`;
CREATE TABLE `t_vip` (
  `vip_number` varchar(20) NOT NULL,
  `vip_name` varchar(20) NOT NULL,
  `vip_phone` varchar(20) NOT NULL,
  `card_level` varchar(20) NOT NULL,
  `card_balance` double(20,0) DEFAULT NULL,
  `card_credit` int(10) DEFAULT '0',
  `card_time` text,
  PRIMARY KEY (`vip_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_vip
-- ----------------------------
INSERT INTO `t_vip` VALUES ('111111', '张甲龙', '15531178347', '金卡', '77', '224', '2012-01-01');
INSERT INTO `t_vip` VALUES ('212132', '李娜', '18330116421', '银卡', '1', '42', 'Fri Mar 04 18:25:31 CST 2016');
