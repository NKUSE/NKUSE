/*
Navicat MySQL Data Transfer

Source Server         : NKUSE
Source Server Version : 50738
Source Host           : localhost:3306
Source Database       : cet6

Target Server Type    : MYSQL
Target Server Version : 50738
File Encoding         : 65001

Date: 2023-05-19 16:59:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `answer_obj`
-- ----------------------------
DROP TABLE IF EXISTS `answer_obj`;
CREATE TABLE `answer_obj` (
  `stu_id` varchar(20) NOT NULL,
  `examination_id` varchar(20) NOT NULL,
  `ob_id` varchar(20) NOT NULL,
  `stu_ans` varchar(255) NOT NULL,
  `stu_score` int(11) NOT NULL,
  PRIMARY KEY (`stu_id`,`examination_id`,`ob_id`) USING BTREE,
  KEY `examination_id` (`examination_id`) USING BTREE,
  KEY `ob_id` (`ob_id`) USING BTREE,
  CONSTRAINT `answer_obj_ibfk_1` FOREIGN KEY (`stu_id`) REFERENCES `user_info` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `answer_obj_ibfk_2` FOREIGN KEY (`examination_id`) REFERENCES `exam_info` (`examination_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `answer_obj_ibfk_3` FOREIGN KEY (`ob_id`) REFERENCES `ques_obj` (`question_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of answer_obj
-- ----------------------------

-- ----------------------------
-- Table structure for `answer_sub`
-- ----------------------------
DROP TABLE IF EXISTS `answer_sub`;
CREATE TABLE `answer_sub` (
  `stu_id` varchar(20) NOT NULL,
  `examination_id` varchar(20) NOT NULL,
  `sub_id` varchar(20) NOT NULL,
  `stu_ans` varchar(255) NOT NULL,
  `stu_score` int(11) NOT NULL,
  PRIMARY KEY (`stu_id`,`examination_id`,`sub_id`) USING BTREE,
  KEY `examination_id` (`examination_id`) USING BTREE,
  KEY `sub_id` (`sub_id`) USING BTREE,
  CONSTRAINT `answer_sub_ibfk_1` FOREIGN KEY (`stu_id`) REFERENCES `user_info` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `answer_sub_ibfk_2` FOREIGN KEY (`examination_id`) REFERENCES `exam_info` (`examination_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `answer_sub_ibfk_3` FOREIGN KEY (`sub_id`) REFERENCES `ques_sub` (`question_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of answer_sub
-- ----------------------------

-- ----------------------------
-- Table structure for `exam_info`
-- ----------------------------
DROP TABLE IF EXISTS `exam_info`;
CREATE TABLE `exam_info` (
  `examination_id` varchar(20) NOT NULL,
  `time` datetime NOT NULL,
  `fee` int(11) NOT NULL,
  PRIMARY KEY (`examination_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of exam_info
-- ----------------------------

-- ----------------------------
-- Table structure for `ques_obj`
-- ----------------------------
DROP TABLE IF EXISTS `ques_obj`;
CREATE TABLE `ques_obj` (
  `question_id` varchar(20) NOT NULL,
  `question` varchar(255) NOT NULL,
  `A` varchar(255) NOT NULL,
  `D` varchar(255) NOT NULL,
  `C` varchar(255) NOT NULL,
  `B` varchar(255) NOT NULL,
  `score` int(11) NOT NULL,
  `answer` varchar(255) NOT NULL,
  PRIMARY KEY (`question_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ques_obj
-- ----------------------------

-- ----------------------------
-- Table structure for `ques_sub`
-- ----------------------------
DROP TABLE IF EXISTS `ques_sub`;
CREATE TABLE `ques_sub` (
  `question_id` varchar(20) NOT NULL,
  `question` varchar(255) NOT NULL,
  `score` int(11) NOT NULL,
  PRIMARY KEY (`question_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ques_sub
-- ----------------------------

-- ----------------------------
-- Table structure for `regist_info`
-- ----------------------------
DROP TABLE IF EXISTS `regist_info`;
CREATE TABLE `regist_info` (
  `user_id` varchar(20) NOT NULL,
  `exam_id` varchar(20) NOT NULL,
  `examination_id` varchar(20) NOT NULL,
  `seat_num` int(11) NOT NULL,
  `score` int(11) NOT NULL,
  `feed` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`exam_id`,`examination_id`) USING BTREE,
  KEY `exam_id` (`exam_id`) USING BTREE,
  KEY `examination_id` (`examination_id`) USING BTREE,
  CONSTRAINT `regist_info_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `regist_info_ibfk_2` FOREIGN KEY (`exam_id`) REFERENCES `room_info` (`exam_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `regist_info_ibfk_3` FOREIGN KEY (`examination_id`) REFERENCES `exam_info` (`examination_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of regist_info
-- ----------------------------

-- ----------------------------
-- Table structure for `room_info`
-- ----------------------------
DROP TABLE IF EXISTS `room_info`;
CREATE TABLE `room_info` (
  `exam_id` varchar(20) NOT NULL,
  `place` varchar(255) NOT NULL,
  `room` varchar(255) NOT NULL,
  `capacity` int(11) NOT NULL,
  PRIMARY KEY (`exam_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of room_info
-- ----------------------------

-- ----------------------------
-- Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `user_id` varchar(20) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `id_num` varchar(30) NOT NULL,
  `password` varchar(255) NOT NULL,
  `user_type` int(11) NOT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  KEY `stu_id` (`user_id`,`id_num`) USING BTREE,
  KEY `id_num` (`id_num`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('000001', '张三', '11111111111', '11111@qq.com', '232323232', '12345678', '1');
