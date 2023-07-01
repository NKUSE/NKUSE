/*
Navicat MySQL Data Transfer

Source Server         : NKUSE
Source Server Version : 50738
Source Host           : localhost:3306
Source Database       : nkuse-cet6

Target Server Type    : MYSQL
Target Server Version : 50738
File Encoding         : 65001

Date: 2023-07-01 20:58:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `cet6_answersheet`
-- ----------------------------
DROP TABLE IF EXISTS `cet6_answersheet`;
CREATE TABLE `cet6_answersheet` (
  `answerSheet_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL,
  `exam_id` varchar(255) DEFAULT NULL,
  `answer_trans` varchar(550) DEFAULT NULL,
  `answer_writing` varchar(255) DEFAULT NULL,
  `answer_obj1` int(11) DEFAULT NULL,
  `answer_obj2` int(11) DEFAULT NULL,
  `answer_obj3` int(11) DEFAULT NULL,
  `answer_obj4` int(11) DEFAULT NULL,
  `answer_obj5` int(11) DEFAULT NULL,
  `answer_obj6` int(11) DEFAULT NULL,
  `answer_obj7` int(11) DEFAULT NULL,
  `answer_obj8` int(11) DEFAULT NULL,
  `answer_obj9` int(11) DEFAULT NULL,
  `answer_obj10` int(11) DEFAULT NULL,
  `answer_obj11` int(11) DEFAULT NULL,
  `answer_obj12` int(11) DEFAULT NULL,
  `answer_obj13` int(11) DEFAULT NULL,
  `answer_obj14` int(11) DEFAULT NULL,
  `answer_obj15` int(11) DEFAULT NULL,
  `answer_obj16` int(11) DEFAULT NULL,
  `answer_obj17` int(11) DEFAULT NULL,
  `answer_obj18` int(11) DEFAULT NULL,
  `answer_obj19` int(11) DEFAULT NULL,
  `answer_obj20` int(11) DEFAULT NULL,
  `score_obj` float DEFAULT NULL,
  `score_trans` float DEFAULT NULL,
  `score_writing` float DEFAULT NULL,
  PRIMARY KEY (`answerSheet_id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `exam_id` (`exam_id`) USING BTREE,
  CONSTRAINT `cet6_answersheet_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `cet6_userinfo` (`user_id`),
  CONSTRAINT `cet6_answersheet_ibfk_2` FOREIGN KEY (`exam_id`) REFERENCES `cet6_examinfo` (`exam_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of cet6_answersheet
-- ----------------------------
INSERT INTO `cet6_answersheet` VALUES ('8', '000001', '2', '12341111213231213213231321213wqe王企鹅111111231243122341134321434134234', '14334223434', '1', '0', '2', '3', '3', '2', '1', '0', '2', '2', '0', '1', '1', '2', '3', '0', '1', '1', '1', '2', '6', null, null);
INSERT INTO `cet6_answersheet` VALUES ('9', '000001', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '-1', null, null);
INSERT INTO `cet6_answersheet` VALUES ('11', '000001', '3', null, null, '2', '1', '2', '3', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `cet6_answersheet` VALUES ('12', '000001', '4', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `cet6_answersheet` VALUES ('13', '000001', '1', 'Theme Of Exodus, also known as Exodus, is a magnificent and magnificent piece of music set against the historical story of Exodus in the Bible. The music begins with a solemn and solemn melody, and a shocking religious atmosphere is about to emerge. The rhythm of the music varies from high to slow, from strong to lyrical, like layers of flowing water, showcasing the high spirit of the Israeli nation and the praise of God. The echoes of history, the power of faith, and the cheers of the Israeli nation interweave and blend through time and space.', 'my name is lihua', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '12', '12');
INSERT INTO `cet6_answersheet` VALUES ('14', '000002', '1', 'answertran', 'wrt', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '12', '2');

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
  PRIMARY KEY (`exam_id`) USING BTREE,
  KEY `exam_state` (`exam_state`),
  CONSTRAINT `cet6_examinfo_ibfk_1` FOREIGN KEY (`exam_state`) REFERENCES `cet6_enum_examstate` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of cet6_examinfo
-- ----------------------------
INSERT INTO `cet6_examinfo` VALUES ('0', 'cet6-1', '9', '2023-05-31 16:00:00', '4', '10');
INSERT INTO `cet6_examinfo` VALUES ('1', 'cet6-2', '10', '2023-06-19 16:00:00', '3', '10');
INSERT INTO `cet6_examinfo` VALUES ('2', 'cet6-3', '11', '2023-06-29 16:00:00', '2', '2');
INSERT INTO `cet6_examinfo` VALUES ('3', 'cet6-4', '12', '2023-06-30 16:00:00', '2', '12');
INSERT INTO `cet6_examinfo` VALUES ('4', 'cet6-5', null, '2023-06-29 16:00:00', '1', '20');

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
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of cet6_obj_ques
-- ----------------------------
INSERT INTO `cet6_obj_ques` VALUES ('7', 'What is the capital of Australia?', 'Sydney', 'Melbourne', 'Canberra', 'Perth', '3');
INSERT INTO `cet6_obj_ques` VALUES ('8', 'Which of the following is a programming language?', 'HTML', 'JPEG', 'XML', 'MP3', '1');
INSERT INTO `cet6_obj_ques` VALUES ('9', 'Who wrote the novel \"Pride and Prejudice\"?', 'Jane Austen', 'William Shakespeare', 'Charles Dickens', 'Mark Twain', '1');
INSERT INTO `cet6_obj_ques` VALUES ('10', 'Which planet is known as the \"Red Planet\"?', 'Venus', 'Mars', 'Jupiter', 'Saturn', '2');
INSERT INTO `cet6_obj_ques` VALUES ('11', 'Who painted the Mona Lisa?', 'Pablo Picasso', 'Leonardo da Vinci', 'Vincent van Gogh', 'Claude Monet', '2');
INSERT INTO `cet6_obj_ques` VALUES ('12', 'What is the chemical symbol for gold?', 'Au', 'Ag', 'Fe', 'Cu', '1');
INSERT INTO `cet6_obj_ques` VALUES ('13', 'Who is known as the father of modern computer science?', 'Alan Turing', 'Bill Gates', 'Steve Jobs', 'Tim Berners-Lee', '1');
INSERT INTO `cet6_obj_ques` VALUES ('14', 'Which country is home to the Taj Mahal?', 'India', 'China', 'Japan', 'Egypt', '1');
INSERT INTO `cet6_obj_ques` VALUES ('15', 'Who wrote the play \"Romeo and Juliet\"?', 'William Shakespeare', 'Oscar Wilde', 'George Bernard Shaw', 'Samuel Beckett', '1');
INSERT INTO `cet6_obj_ques` VALUES ('16', 'Which is the largest ocean in the world?', 'Atlantic Ocean', 'Indian Ocean', 'Arctic Ocean', 'Pacific Ocean', '4');
INSERT INTO `cet6_obj_ques` VALUES ('17', 'Who invented the telephone?', 'Alexander Graham Bell', 'Thomas Edison', 'Nikola Tesla', 'Guglielmo Marconi', '1');
INSERT INTO `cet6_obj_ques` VALUES ('18', 'Which of the following is a primary color?', 'Green', 'Orange', 'Purple', 'Red', '4');
INSERT INTO `cet6_obj_ques` VALUES ('19', 'Which famous scientist developed the theory of relativity?', 'Isaac Newton', 'Albert Einstein', 'Galileo Galilei', 'Stephen Hawking', '2');
INSERT INTO `cet6_obj_ques` VALUES ('20', 'Who is the author of the novel \"To Kill a Mockingbird\"?', 'F. Scott Fitzgerald', 'Harper Lee', 'J.D. Salinger', 'Ernest Hemingway', '2');
INSERT INTO `cet6_obj_ques` VALUES ('21', 'Which city hosted the 2016 Summer Olympics?', 'Beijing', 'London', 'Rio de Janeiro', 'Tokyo', '3');
INSERT INTO `cet6_obj_ques` VALUES ('22', 'Who painted the famous artwork \"The Starry Night\"?', 'Pablo Picasso', 'Vincent van Gogh', 'Salvador Dalí', 'Leonardo da Vinci', '2');
INSERT INTO `cet6_obj_ques` VALUES ('23', 'Which is the longest river in the world?', 'Amazon River', 'Nile River', 'Mississippi River', 'Yangtze River', '2');
INSERT INTO `cet6_obj_ques` VALUES ('24', 'Who is the current President of the United States?', 'Donald Trump', 'Joe Biden', 'Barack Obama', 'George W. Bush', '2');
INSERT INTO `cet6_obj_ques` VALUES ('25', 'What is the chemical formula for water?', 'H2O', 'CO2', 'NaCl', 'CH4', '1');
INSERT INTO `cet6_obj_ques` VALUES ('26', 'Who is the author of the play \"Hamlet\"?', 'William Shakespeare', 'Oscar Wilde', 'Henrik Ibsen', 'Anton Chekhov', '1');
INSERT INTO `cet6_obj_ques` VALUES ('27', 'What is the largest country in the world by land area?', 'China', 'Russia', 'United States', 'Canada', '2');
INSERT INTO `cet6_obj_ques` VALUES ('28', 'Who is the author of the novel \"1984\"?', 'George Orwell', 'Aldous Huxley', 'Ray Bradbury', 'J.R.R. Tolkien', '1');
INSERT INTO `cet6_obj_ques` VALUES ('29', 'Which is the highest mountain in the world?', 'Mount Kilimanjaro', 'Mount Everest', 'Mount Fuji', 'Mount McKinley', '2');
INSERT INTO `cet6_obj_ques` VALUES ('30', 'Who painted the famous artwork \"The Last Supper\"?', 'Michelangelo', 'Leonardo da Vinci', 'Pablo Picasso', 'Vincent van Gogh', '2');
INSERT INTO `cet6_obj_ques` VALUES ('31', 'Which country is known as the \"Land of the Rising Sun\"?', 'China', 'Japan', 'South Korea', 'Thailand', '2');
INSERT INTO `cet6_obj_ques` VALUES ('32', 'What is the chemical symbol for iron?', 'Fe', 'Ag', 'Cu', 'Au', '1');
INSERT INTO `cet6_obj_ques` VALUES ('33', 'Who is the author of the novel \"The Great Gatsby\"?', 'F. Scott Fitzgerald', 'Ernest Hemingway', 'Harper Lee', 'Charles Dickens', '1');
INSERT INTO `cet6_obj_ques` VALUES ('34', 'Which city is the capital of France?', 'Paris', 'Rome', 'London', 'Berlin', '1');
INSERT INTO `cet6_obj_ques` VALUES ('35', 'Who wrote the play \"Macbeth\"?', 'William Shakespeare', 'Arthur Miller', 'Tennessee Williams', 'Henrik Ibsen', '1');
INSERT INTO `cet6_obj_ques` VALUES ('36', 'Which is the largest desert in the world?', 'Gobi Desert', 'Sahara Desert', 'Atacama Desert', 'Antarctic Desert', '2');
INSERT INTO `cet6_obj_ques` VALUES ('37', 'Who is the inventor of the light bulb?', 'Thomas Edison', 'Benjamin Franklin', 'Nikola Tesla', 'Alexander Graham Bell', '1');
INSERT INTO `cet6_obj_ques` VALUES ('38', 'What is the chemical symbol for sodium?', 'Na', 'Ca', 'K', 'Mg', '1');
INSERT INTO `cet6_obj_ques` VALUES ('39', 'Who is the author of the novel \"Moby-Dick\"?', 'Herman Melville', 'Mark Twain', 'Emily Brontë', 'Jane Austen', '1');
INSERT INTO `cet6_obj_ques` VALUES ('40', 'Which city is known as the \"Eternal City\"?', 'Rome', 'Athens', 'Venice', 'Florence', '1');
INSERT INTO `cet6_obj_ques` VALUES ('41', 'Who wrote the play \"Hamlet\"?', 'William Shakespeare', 'Oscar Wilde', 'Henrik Ibsen', 'Anton Chekhov', '1');
INSERT INTO `cet6_obj_ques` VALUES ('42', 'Which is the largest continent in the world?', 'Asia', 'Africa', 'North America', 'Europe', '1');
INSERT INTO `cet6_obj_ques` VALUES ('43', 'Who is the author of the novel \"The Catcher in the Rye\"?', 'J.D. Salinger', 'Harper Lee', 'F. Scott Fitzgerald', 'George Orwell', '1');
INSERT INTO `cet6_obj_ques` VALUES ('44', 'Which city is the capital of Russia?', 'Moscow', 'St. Petersburg', 'Kiev', 'Berlin', '1');
INSERT INTO `cet6_obj_ques` VALUES ('45', 'Who wrote the play \"Macbeth\"?', 'William Shakespeare', 'Arthur Miller', 'Tennessee Williams', 'Henrik Ibsen', '1');
INSERT INTO `cet6_obj_ques` VALUES ('46', 'Which is the largest desert in the world?', 'Gobi Desert', 'Sahara Desert', 'Atacama Desert', 'Antarctic Desert', '2');
INSERT INTO `cet6_obj_ques` VALUES ('47', 'Who is the inventor of the light bulb?', 'Thomas Edison', 'Benjamin Franklin', 'Nikola Tesla', 'Alexander Graham Bell', '1');
INSERT INTO `cet6_obj_ques` VALUES ('48', 'What is the chemical symbol for sodium?', 'Na', 'Ca', 'K', 'Mg', '1');
INSERT INTO `cet6_obj_ques` VALUES ('49', 'Who is the author of the novel \"Moby-Dick\"?', 'Herman Melville', 'Mark Twain', 'Emily Brontë', 'Jane Austen', '1');
INSERT INTO `cet6_obj_ques` VALUES ('50', 'Which city is known as the \"Eternal City\"?', 'Rome', 'Athens', 'Venice', 'Florence', '1');
INSERT INTO `cet6_obj_ques` VALUES ('51', 'Who wrote the play \"Hamlet\"?', 'William Shakespeare', 'Oscar Wilde', 'Henrik Ibsen', 'Anton Chekhov', '1');
INSERT INTO `cet6_obj_ques` VALUES ('52', 'Which is the largest continent in the world?', 'Asia', 'Africa', 'North America', 'Europe', '1');
INSERT INTO `cet6_obj_ques` VALUES ('53', 'Who is the author of the novel \"The Catcher in the Rye\"?', 'J.D. Salinger', 'Harper Lee', 'F. Scott Fitzgerald', 'George Orwell', '1');
INSERT INTO `cet6_obj_ques` VALUES ('54', 'Which city is the capital of Russia?', 'Moscow', 'St. Petersburg', 'Kiev', 'Berlin', '1');
INSERT INTO `cet6_obj_ques` VALUES ('55', 'Who wrote the play \"Romeo and Juliet\"?', 'William Shakespeare', 'Oscar Wilde', 'George Bernard Shaw', 'Samuel Beckett', '1');
INSERT INTO `cet6_obj_ques` VALUES ('56', 'What is the chemical symbol for silver?', 'Ag', 'Au', 'Cu', 'Fe', '1');
INSERT INTO `cet6_obj_ques` VALUES ('99', 'test', 'a', 'b', 'c', 'd', '1');
INSERT INTO `cet6_obj_ques` VALUES ('100', 'test2', 'a', 'b', 'c', 'd', '1');

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
  KEY `question_obj_1` (`question_obj1`) USING BTREE,
  KEY `cet6_paperinfo_ibfk_2` (`question_obj2`),
  KEY `cet6_paperinfo_ibfk_3` (`question_obj3`),
  KEY `cet6_paperinfo_ibfk_4` (`question_obj4`),
  KEY `cet6_paperinfo_ibfk_5` (`question_obj5`),
  KEY `cet6_paperinfo_ibfk_6` (`question_obj6`),
  KEY `cet6_paperinfo_ibfk_7` (`question_obj7`),
  KEY `cet6_paperinfo_ibfk_8` (`question_obj8`),
  KEY `cet6_paperinfo_ibfk_9` (`question_obj9`),
  KEY `cet6_paperinfo_ibfk_10` (`question_obj10`),
  KEY `cet6_paperinfo_ibfk_11` (`question_obj11`),
  KEY `cet6_paperinfo_ibfk_12` (`question_obj12`),
  KEY `cet6_paperinfo_ibfk_13` (`question_obj13`),
  KEY `cet6_paperinfo_ibfk_14` (`question_obj14`),
  KEY `cet6_paperinfo_ibfk_15` (`question_obj15`),
  KEY `cet6_paperinfo_ibfk_16` (`question_obj16`),
  KEY `cet6_paperinfo_ibfk_17` (`question_obj17`),
  KEY `cet6_paperinfo_ibfk_18` (`question_obj18`),
  KEY `cet6_paperinfo_ibfk_19` (`question_obj19`),
  KEY `cet6_paperinfo_ibfk_20` (`question_obj20`),
  CONSTRAINT `cet6_paperinfo_ibfk_1` FOREIGN KEY (`question_obj1`) REFERENCES `cet6_obj_ques` (`question_id`),
  CONSTRAINT `cet6_paperinfo_ibfk_10` FOREIGN KEY (`question_obj10`) REFERENCES `cet6_obj_ques` (`question_id`),
  CONSTRAINT `cet6_paperinfo_ibfk_11` FOREIGN KEY (`question_obj11`) REFERENCES `cet6_obj_ques` (`question_id`),
  CONSTRAINT `cet6_paperinfo_ibfk_12` FOREIGN KEY (`question_obj12`) REFERENCES `cet6_obj_ques` (`question_id`),
  CONSTRAINT `cet6_paperinfo_ibfk_13` FOREIGN KEY (`question_obj13`) REFERENCES `cet6_obj_ques` (`question_id`),
  CONSTRAINT `cet6_paperinfo_ibfk_14` FOREIGN KEY (`question_obj14`) REFERENCES `cet6_obj_ques` (`question_id`),
  CONSTRAINT `cet6_paperinfo_ibfk_15` FOREIGN KEY (`question_obj15`) REFERENCES `cet6_obj_ques` (`question_id`),
  CONSTRAINT `cet6_paperinfo_ibfk_16` FOREIGN KEY (`question_obj16`) REFERENCES `cet6_obj_ques` (`question_id`),
  CONSTRAINT `cet6_paperinfo_ibfk_17` FOREIGN KEY (`question_obj17`) REFERENCES `cet6_obj_ques` (`question_id`),
  CONSTRAINT `cet6_paperinfo_ibfk_18` FOREIGN KEY (`question_obj18`) REFERENCES `cet6_obj_ques` (`question_id`),
  CONSTRAINT `cet6_paperinfo_ibfk_19` FOREIGN KEY (`question_obj19`) REFERENCES `cet6_obj_ques` (`question_id`),
  CONSTRAINT `cet6_paperinfo_ibfk_2` FOREIGN KEY (`question_obj2`) REFERENCES `cet6_obj_ques` (`question_id`),
  CONSTRAINT `cet6_paperinfo_ibfk_20` FOREIGN KEY (`question_obj20`) REFERENCES `cet6_obj_ques` (`question_id`),
  CONSTRAINT `cet6_paperinfo_ibfk_3` FOREIGN KEY (`question_obj3`) REFERENCES `cet6_obj_ques` (`question_id`),
  CONSTRAINT `cet6_paperinfo_ibfk_4` FOREIGN KEY (`question_obj4`) REFERENCES `cet6_obj_ques` (`question_id`),
  CONSTRAINT `cet6_paperinfo_ibfk_5` FOREIGN KEY (`question_obj5`) REFERENCES `cet6_obj_ques` (`question_id`),
  CONSTRAINT `cet6_paperinfo_ibfk_6` FOREIGN KEY (`question_obj6`) REFERENCES `cet6_obj_ques` (`question_id`),
  CONSTRAINT `cet6_paperinfo_ibfk_7` FOREIGN KEY (`question_obj7`) REFERENCES `cet6_obj_ques` (`question_id`),
  CONSTRAINT `cet6_paperinfo_ibfk_8` FOREIGN KEY (`question_obj8`) REFERENCES `cet6_obj_ques` (`question_id`),
  CONSTRAINT `cet6_paperinfo_ibfk_9` FOREIGN KEY (`question_obj9`) REFERENCES `cet6_obj_ques` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of cet6_paperinfo
-- ----------------------------
INSERT INTO `cet6_paperinfo` VALUES ('9', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '翻译以下内容：\\n端午节，又称端阳节、龙舟节、重午节、重五节、天中节等，日期在每年农历五月初五，是集拜神祭祖、祈福辟邪、欢庆娱乐和饮食为一体的民俗大节。端午节源于自然天象崇拜，由上古时代祭龙演变而来。仲夏端午，苍龙七宿飞升于正南中央，处在全年最“中正”之位，正如《易经·乾卦》第五爻：“飞龙在天”。端午是“飞龙在天”吉祥日，龙及龙舟文化始终贯穿在端午节的传承历史中。', '按照以下要求完成写作：\\n要求：自由选题，自由发挥');
INSERT INTO `cet6_paperinfo` VALUES ('10', '27', '28', '29', '30', '31', '32', '33', '34', '35', '36', '37', '38', '39', '40', '41', '42', '43', '44', '45', '46', '翻译以下句子：\\n2018年9月27日，联合国将最高级别的环保荣誉“地球卫士奖”授予了中国浙江省“千村示范、万村整治”工程。“千万工程”为全球环境治理提供了中国方案。', '以“端午节”为话题写一篇20000字的作文');
INSERT INTO `cet6_paperinfo` VALUES ('11', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '翻译：\\n“千万工程”的生动实践有力说明，这是一条高质量发展之路，是坚持“人民至上”理念的实践之路，是不断实现人民对美好生活向往的奋进之路。“美丽乡村”万千气象，铺展现实版...', '以“如果精神有颜色它一定在其中”为题写一篇作文');
INSERT INTO `cet6_paperinfo` VALUES ('12', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '31', '翻译以下段落：\\n《Theme Of Exodus》又名《出埃及记》，是一首气势恢宏、波澜壮阔的乐曲，它的创作背景是《圣经》中《出埃及记》的历史故事。音乐以庄严肃穆的曲调拉开序幕，震撼的宗教氛围呼之欲出。乐曲的节奏时而高亢时而舒缓，时而强烈时而抒情，犹如一层层涌动的流水，将以色列民族高昂的气势和对上帝的赞美极力渲染。历史的回响，信仰的力量，以色列民族的欢呼，交织融汇穿透时空。', '以NKUSE为主题完成一篇作文');

-- ----------------------------
-- Table structure for `cet6_registinfo`
-- ----------------------------
DROP TABLE IF EXISTS `cet6_registinfo`;
CREATE TABLE `cet6_registinfo` (
  `regist_id` int(255) NOT NULL AUTO_INCREMENT,
  `exam_id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `room_id` int(255) DEFAULT NULL,
  `seat_number` int(11) DEFAULT NULL,
  `paid` int(11) DEFAULT NULL,
  `answerSheet_id` varchar(255) DEFAULT NULL,
  `score` float DEFAULT NULL,
  PRIMARY KEY (`regist_id`),
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `room_id` (`room_id`) USING BTREE,
  KEY `answerSheet_id` (`answerSheet_id`) USING BTREE,
  KEY `exam_id` (`exam_id`),
  CONSTRAINT `cet6_registinfo_ibfk_1` FOREIGN KEY (`exam_id`) REFERENCES `cet6_examinfo` (`exam_id`),
  CONSTRAINT `cet6_registinfo_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `cet6_userinfo` (`user_id`),
  CONSTRAINT `cet6_registinfo_ibfk_3` FOREIGN KEY (`room_id`) REFERENCES `cet6_roominfo` (`room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of cet6_registinfo
-- ----------------------------
INSERT INTO `cet6_registinfo` VALUES ('1', '0', '000001', '1', '1', '1', '9', null);
INSERT INTO `cet6_registinfo` VALUES ('2', '4', '000001', '1', '1', '0', '12', null);
INSERT INTO `cet6_registinfo` VALUES ('3', '3', '000001', '1', '1', '1', '11', null);
INSERT INTO `cet6_registinfo` VALUES ('4', '1', '000001', '1', '1', '0', '13', null);
INSERT INTO `cet6_registinfo` VALUES ('5', '1', '000002', '1', '1', '0', '14', null);
INSERT INTO `cet6_registinfo` VALUES ('8', '4', '000002', '1', '1', '0', null, null);
INSERT INTO `cet6_registinfo` VALUES ('9', '4', '000012', '1', '1', '0', null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of cet6_roominfo
-- ----------------------------
INSERT INTO `cet6_roominfo` VALUES ('1', '线上考试1', '线上考场', '50');
INSERT INTO `cet6_roominfo` VALUES ('2', '线上考试2', '线上考场', '50');
INSERT INTO `cet6_roominfo` VALUES ('3', '线上考试5', '线上考试', '20');

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
INSERT INTO `cet6_userinfo` VALUES ('000001', '学生用户1', '123456', '200000000@email.com', '123456', '290000199901014321', '1');
INSERT INTO `cet6_userinfo` VALUES ('000002', 'lyh', '123456', '2012516@mail.nankai.edu.cn', '12345678901', null, '1');
INSERT INTO `cet6_userinfo` VALUES ('000012', '月儿弯弯照九州', '123456', '', '13222314212', null, '1');
INSERT INTO `cet6_userinfo` VALUES ('000013', '黄河远上白云间', '123456', '', '13222314213', null, '1');
INSERT INTO `cet6_userinfo` VALUES ('000015', '春风不度玉门关', '123456', '', '13222214213', null, '1');
INSERT INTO `cet6_userinfo` VALUES ('000021', '山有木兮木有枝', '123456', '2900034525@qq.com', '13777771111', null, '1');
INSERT INTO `cet6_userinfo` VALUES ('000051', '明月何曾是两乡', '123456', '', '12323232221', null, '1');
INSERT INTO `cet6_userinfo` VALUES ('100001', '教师用户1', '123456', null, null, null, '2');
INSERT INTO `cet6_userinfo` VALUES ('123243', '看花回', '123456', '2900034525@qq.com', null, null, '3');
INSERT INTO `cet6_userinfo` VALUES ('123333', '悠悠我心', '123456', '', '12342132341', null, '1');
INSERT INTO `cet6_userinfo` VALUES ('156764', '海上明月共潮生', '123456', '', '31212323211', null, '1');
INSERT INTO `cet6_userinfo` VALUES ('234422', '烟花三月下扬州', '123456', '', '22345523533', null, '1');
INSERT INTO `cet6_userinfo` VALUES ('423132', '青青子衿', '123456', '', '31232311111', null, '1');
INSERT INTO `cet6_userinfo` VALUES ('423412', '春眠不觉晓', '123456', '', '43242311241', null, '1');
INSERT INTO `cet6_userinfo` VALUES ('445555', '蝶恋花', '123455', '2900034525@qq.com', null, null, '3');
INSERT INTO `cet6_userinfo` VALUES ('544313', '雄关漫道真如铁', '123456', '', '54543234522', null, '1');
INSERT INTO `cet6_userinfo` VALUES ('673211', '秋水共长天一色', '123456', '', '12363125345', null, '1');
INSERT INTO `cet6_userinfo` VALUES ('900001', '管理员用户1', '111111', '2900034525@qq.com', '13000', '53000', '3');
