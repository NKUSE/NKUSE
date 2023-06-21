/*
Navicat MySQL Data Transfer

Source Server         : NKUSE
Source Server Version : 50738
Source Host           : localhost:3306
Source Database       : nkuse-cet6

Target Server Type    : MYSQL
Target Server Version : 50738
File Encoding         : 65001

Date: 2023-06-21 21:25:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `cet6_answersheet`
-- ----------------------------
DROP TABLE IF EXISTS `cet6_answersheet`;
CREATE TABLE `cet6_answersheet` (
  `answerSheet_id` varchar(255) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `exam_id` varchar(255) DEFAULT NULL,
  `answer_trans` varchar(255) DEFAULT NULL,
  `answer_writing` varchar(255) DEFAULT NULL,
  `answer_obj_1` int(11) DEFAULT NULL,
  `answer_obj_2` int(11) DEFAULT NULL,
  `answer_obj_3` int(11) DEFAULT NULL,
  `answer_obj_4` int(11) DEFAULT NULL,
  `answer_obj_5` int(11) DEFAULT NULL,
  `answer_obj_6` int(11) DEFAULT NULL,
  `answer_obj_7` int(11) DEFAULT NULL,
  `answer_obj_8` int(11) DEFAULT NULL,
  `answer_obj_9` int(11) DEFAULT NULL,
  `answer_obj_10` int(11) DEFAULT NULL,
  `answer_obj_11` int(11) DEFAULT NULL,
  `answer_obj_12` int(11) DEFAULT NULL,
  `answer_obj_13` int(11) DEFAULT NULL,
  `answer_obj_14` int(11) DEFAULT NULL,
  `answer_obj_15` int(11) DEFAULT NULL,
  `answer_obj_16` int(11) DEFAULT NULL,
  `answer_obj_17` int(11) DEFAULT NULL,
  `answer_obj_18` int(11) DEFAULT NULL,
  `answer_obj_19` int(11) DEFAULT NULL,
  `answer_obj_20` int(11) DEFAULT NULL,
  `score_obj` float DEFAULT NULL,
  `score_trans` float DEFAULT NULL,
  `score_writing` float DEFAULT NULL,
  PRIMARY KEY (`answerSheet_id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `exam_id` (`exam_id`) USING BTREE,
  CONSTRAINT `cet6_answersheet_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `cet6_userinfo` (`user_id`),
  CONSTRAINT `cet6_answersheet_ibfk_2` FOREIGN KEY (`exam_id`) REFERENCES `cet6_examinfo` (`exam_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of cet6_answersheet
-- ----------------------------
INSERT INTO `cet6_answersheet` VALUES ('000001', '000001', '00001', '翻译答案', '写作答案', '1', '1', '1', '1', '2', '3', '4', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2', null, null, null);

-- ----------------------------
-- Table structure for `cet6_enum_examstate`
-- ----------------------------
DROP TABLE IF EXISTS `cet6_enum_examstate`;
CREATE TABLE `cet6_enum_examstate` (
  `type` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of cet6_enum_examstate
-- ----------------------------
INSERT INTO `cet6_enum_examstate` VALUES ('1', '开始报名');
INSERT INTO `cet6_enum_examstate` VALUES ('2', '开始考试');
INSERT INTO `cet6_enum_examstate` VALUES ('3', '开始阅卷');
INSERT INTO `cet6_enum_examstate` VALUES ('4', '发布成绩');

-- ----------------------------
-- Table structure for `cet6_enum_usertype`
-- ----------------------------
DROP TABLE IF EXISTS `cet6_enum_usertype`;
CREATE TABLE `cet6_enum_usertype` (
  `type` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of cet6_enum_usertype
-- ----------------------------
INSERT INTO `cet6_enum_usertype` VALUES ('1', '学生');
INSERT INTO `cet6_enum_usertype` VALUES ('2', '教师');
INSERT INTO `cet6_enum_usertype` VALUES ('3', '管理员');

-- ----------------------------
-- Table structure for `cet6_examinfo`
-- ----------------------------
DROP TABLE IF EXISTS `cet6_examinfo`;
CREATE TABLE `cet6_examinfo` (
  `exam_id` varchar(255) NOT NULL,
  `exam_name` varchar(255) DEFAULT NULL,
  `exam_paperID` int(11) DEFAULT NULL,
  `exam_time` datetime DEFAULT NULL,
  `exam_state` int(11) DEFAULT NULL,
  `exam_fee` float DEFAULT NULL,
  PRIMARY KEY (`exam_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of cet6_examinfo
-- ----------------------------
INSERT INTO `cet6_examinfo` VALUES ('00001', 'cet6', '1', '2023-06-04 08:00:00', '4', '500');
INSERT INTO `cet6_examinfo` VALUES ('2', 'cet6-2', null, '2023-06-16 06:00:00', '2', '50');
INSERT INTO `cet6_examinfo` VALUES ('3', 'cet6-3', null, '2023-06-21 00:00:00', '2', '100');
INSERT INTO `cet6_examinfo` VALUES ('4', 'cet6-4', null, '2023-06-05 16:00:00', '1', '40');
INSERT INTO `cet6_examinfo` VALUES ('5', 'cet6-5', null, '2023-06-20 16:00:00', '1', '100');

-- ----------------------------
-- Table structure for `cet6_obj_ques`
-- ----------------------------
DROP TABLE IF EXISTS `cet6_obj_ques`;
CREATE TABLE `cet6_obj_ques` (
  `question_id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `option_A` varchar(255) DEFAULT NULL,
  `option_B` varchar(255) DEFAULT NULL,
  `option_C` varchar(255) DEFAULT NULL,
  `option_D` varchar(255) DEFAULT NULL,
  `answer` int(11) DEFAULT NULL,
  PRIMARY KEY (`question_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of cet6_obj_ques
-- ----------------------------
INSERT INTO `cet6_obj_ques` VALUES ('1', '题目', '1', '2', '3', '4', '1');
INSERT INTO `cet6_obj_ques` VALUES ('2', '选B1', '1', '2', '3', '4', '2');
INSERT INTO `cet6_obj_ques` VALUES ('3', '1', '1', '1', '1', '1', '3');
INSERT INTO `cet6_obj_ques` VALUES ('4', '1', '1', '1', '1', '1', '1');

-- ----------------------------
-- Table structure for `cet6_paperinfo`
-- ----------------------------
DROP TABLE IF EXISTS `cet6_paperinfo`;
CREATE TABLE `cet6_paperinfo` (
  `paper_id` int(11) NOT NULL,
  `question_obj1` int(11) DEFAULT NULL,
  `question_obj2` int(11) DEFAULT NULL,
  `question_obj3` int(11) DEFAULT NULL,
  `question_obj4` int(11) DEFAULT NULL,
  `question_obj5` int(11) DEFAULT NULL,
  `question_obj6` int(11) DEFAULT NULL,
  `question_obj7` int(11) DEFAULT NULL,
  `question_obj8` int(11) DEFAULT NULL,
  `question_obj9` int(11) DEFAULT NULL,
  `question_obj10` int(11) DEFAULT NULL,
  `question_obj11` int(11) DEFAULT NULL,
  `question_obj12` int(11) DEFAULT NULL,
  `question_obj13` int(11) DEFAULT NULL,
  `question_obj14` int(11) DEFAULT NULL,
  `question_obj15` int(11) DEFAULT NULL,
  `question_obj16` int(11) DEFAULT NULL,
  `question_obj17` int(11) DEFAULT NULL,
  `question_obj18` int(11) DEFAULT NULL,
  `question_obj19` int(11) DEFAULT NULL,
  `question_obj20` int(11) DEFAULT NULL,
  `question_Translating` varchar(255) DEFAULT NULL,
  `question_Writing` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`paper_id`) USING BTREE,
  KEY `question_obj_1` (`question_obj1`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of cet6_paperinfo
-- ----------------------------
INSERT INTO `cet6_paperinfo` VALUES ('1', '1', '1', '1', '1', '1', '1', '1', '2', '1', '1', '2', '1', '2', '1', '2', '1', '2', '1', '2', '1', '翻译1', '写作1');
INSERT INTO `cet6_paperinfo` VALUES ('2', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '翻译', '写作');
INSERT INTO `cet6_paperinfo` VALUES ('3', '2', '2', '2', '2', '2', '1', '1', '1', '1', '1', '2', '2', '2', '2', '2', '1', '1', '1', '1', '1', '翻译', '写作');
INSERT INTO `cet6_paperinfo` VALUES ('4', '2', '2', '2', '2', '2', '1', '1', '1', '1', '1', '2', '2', '2', '2', '2', '1', '1', '1', '1', '1', '翻译', '写作');
INSERT INTO `cet6_paperinfo` VALUES ('5', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', 'fanyi', 'xiezuo');
INSERT INTO `cet6_paperinfo` VALUES ('6', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', 'fanyi', 'xiezuo');
INSERT INTO `cet6_paperinfo` VALUES ('7', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', 'fanyi', 'xiezuo');
INSERT INTO `cet6_paperinfo` VALUES ('8', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '翻译', '写作');

-- ----------------------------
-- Table structure for `cet6_registinfo`
-- ----------------------------
DROP TABLE IF EXISTS `cet6_registinfo`;
CREATE TABLE `cet6_registinfo` (
  `regist_id` int(255) NOT NULL AUTO_INCREMENT,
  `exam_id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `room_id` varchar(255) DEFAULT NULL,
  `seat_number` int(11) DEFAULT NULL,
  `paid` int(11) DEFAULT NULL,
  `answerSheet_id` varchar(255) DEFAULT NULL,
  `score` float DEFAULT NULL,
  PRIMARY KEY (`regist_id`),
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `room_id` (`room_id`) USING BTREE,
  KEY `answerSheet_id` (`answerSheet_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of cet6_registinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `cet6_roominfo`
-- ----------------------------
DROP TABLE IF EXISTS `cet6_roominfo`;
CREATE TABLE `cet6_roominfo` (
  `room_id` int(11) NOT NULL AUTO_INCREMENT,
  `room_name` varchar(255) DEFAULT NULL,
  `place` varchar(255) DEFAULT NULL,
  `capacity` int(11) DEFAULT NULL,
  PRIMARY KEY (`room_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of cet6_roominfo
-- ----------------------------
INSERT INTO `cet6_roominfo` VALUES ('1', '线上考试1', '线上考场21', '50');
INSERT INTO `cet6_roominfo` VALUES ('2', '线上考试2', '线上考场', '50');

-- ----------------------------
-- Table structure for `cet6_userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `cet6_userinfo`;
CREATE TABLE `cet6_userinfo` (
  `user_id` varchar(255) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_password` varchar(255) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `user_phonenumber` varchar(255) DEFAULT NULL,
  `user_IDnumber` varchar(255) DEFAULT NULL,
  `user_role` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  KEY `user_role` (`user_role`) USING BTREE,
  CONSTRAINT `cet6_userinfo_ibfk_1` FOREIGN KEY (`user_role`) REFERENCES `cet6_enum_usertype` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of cet6_userinfo
-- ----------------------------
INSERT INTO `cet6_userinfo` VALUES ('000001', '小朋友', '123456', '2@email.com', '123456', '290000199901014321', '1');
INSERT INTO `cet6_userinfo` VALUES ('100001', '教师', '123456', null, null, null, '2');
INSERT INTO `cet6_userinfo` VALUES ('900001', '管理', '123456', null, null, null, '3');
