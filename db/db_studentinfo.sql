/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : db_studentinfo

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 28/02/2019 17:00:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_course
-- ----------------------------
DROP TABLE IF EXISTS `t_course`;
CREATE TABLE `t_course`  (
  `couid` int(255) NOT NULL AUTO_INCREMENT COMMENT '课程ID',
  `couname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `counteacher` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `coudesc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`couid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_course
-- ----------------------------
INSERT INTO `t_course` VALUES (1, 'java', '刘老师', 'java天下第一');
INSERT INTO `t_course` VALUES (3, 'c++', '吴老师', '22');
INSERT INTO `t_course` VALUES (4, '11', '1333', '1');
INSERT INTO `t_course` VALUES (5, '22', '22', '222');

-- ----------------------------
-- Table structure for t_grade
-- ----------------------------
DROP TABLE IF EXISTS `t_grade`;
CREATE TABLE `t_grade`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gradeName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gradeDesc` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_grade
-- ----------------------------
INSERT INTO `t_grade` VALUES (1, '08计本', '08计算机本科');
INSERT INTO `t_grade` VALUES (2, '09计本', '09计算机本科');

-- ----------------------------
-- Table structure for t_score
-- ----------------------------
DROP TABLE IF EXISTS `t_score`;
CREATE TABLE `t_score`  (
  `scid` int(255) NOT NULL AUTO_INCREMENT,
  `scstuid` int(255) NULL DEFAULT NULL,
  `sccouid` int(255) NULL DEFAULT NULL,
  `scscore` int(3) NULL DEFAULT NULL,
  PRIMARY KEY (`scid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_score
-- ----------------------------
INSERT INTO `t_score` VALUES (1, 18, 1, 80);
INSERT INTO `t_score` VALUES (2, 3, 3, 85);
INSERT INTO `t_score` VALUES (5, 29, 3, 123);
INSERT INTO `t_score` VALUES (6, 18, 4, 23);
INSERT INTO `t_score` VALUES (11, 28, 4, 23);
INSERT INTO `t_score` VALUES (14, 28, 4, 23);
INSERT INTO `t_score` VALUES (15, 34, 4, 23);
INSERT INTO `t_score` VALUES (16, 36, 1, 3333);
INSERT INTO `t_score` VALUES (17, 34, 3, 444);

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student`  (
  `stuId` int(11) NOT NULL AUTO_INCREMENT,
  `stuNo` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stuName` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` date NULL DEFAULT NULL,
  `gradeId` int(11) NULL DEFAULT NULL,
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stuDesc` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`stuId`) USING BTREE,
  INDEX `FK_t_student`(`gradeId`) USING BTREE,
  CONSTRAINT `FK_t_student` FOREIGN KEY (`gradeId`) REFERENCES `t_grade` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_student
-- ----------------------------
INSERT INTO `t_student` VALUES (3, '080606110', '张三', '女', '1989-11-03', 1, '31321@qq.com', 'Good');
INSERT INTO `t_student` VALUES (18, '090606119', '王小美', '女', '1990-11-03', 1, '3112121@qq.com', 'Good Girls11');
INSERT INTO `t_student` VALUES (30, '1', '1', '男', '2013-04-30', 2, '321@11.com', '123');
INSERT INTO `t_student` VALUES (33, '321', '231', '男', '2013-05-01', 1, '321@11.com', '321');
INSERT INTO `t_student` VALUES (34, '0901101222222', '王靶档22', '女', '2013-05-01', 2, 'wanba@12222.com', '王八222');
INSERT INTO `t_student` VALUES (35, '123', '1555', '男', '2018-12-07', 1, '1145055743@qq.com', '123');
INSERT INTO `t_student` VALUES (36, '2222', '你是你是你是你是你是', '男', '2018-12-07', 1, '1145055743@qq.com', '');
INSERT INTO `t_student` VALUES (37, '1111112', '曹小小2', '男', '2018-12-08', NULL, '1145055743@qq.com', '544444444444');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin', 'admin');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '21', '2');

SET FOREIGN_KEY_CHECKS = 1;
