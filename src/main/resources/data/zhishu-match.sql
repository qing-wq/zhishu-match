/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : zhishu-match

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 09/12/2023 21:01:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for announcement
-- ----------------------------
DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建用户',
  `title` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '标题',
  `summary` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '摘要',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '0-未发布 1-已发布',
  `deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '公告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of announcement
-- ----------------------------
INSERT INTO `announcement` VALUES (1, 1, 'Announcement 1', 'Summary for Announcement 1', 'Content for Announcement 1', 1, 0, '2023-12-08 00:21:10', '2023-12-08 00:21:10');
INSERT INTO `announcement` VALUES (2, 2, 'Announcement 2', 'Summary for Announcement 2', 'Content for Announcement 2', 1, 0, '2023-12-08 00:21:10', '2023-12-08 00:21:10');
INSERT INTO `announcement` VALUES (3, 1, 'Announcement 1', 'Summary for Announcement 1', 'Content for Announcement 1', 1, 0, '2023-12-08 00:41:44', '2023-12-08 00:41:44');
INSERT INTO `announcement` VALUES (4, 2, 'Announcement 2', 'Summary for Announcement 2', 'Content for Announcement 2', 1, 0, '2023-12-08 00:41:44', '2023-12-08 00:41:44');

-- ----------------------------
-- Table structure for competition
-- ----------------------------
DROP TABLE IF EXISTS `competition`;
CREATE TABLE `competition`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '比赛名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '介绍',
  `type` int(11) NULL DEFAULT NULL COMMENT '比赛类型',
  `deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `start_time` date NULL DEFAULT NULL COMMENT '比赛开始时间',
  `end_time` date NULL DEFAULT NULL COMMENT '比赛结束时间',
  `signup_deadline` date NULL DEFAULT NULL COMMENT '报名截止时间',
  `max_member` int(255) NULL DEFAULT NULL COMMENT '最大人数',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of competition
-- ----------------------------
INSERT INTO `competition` VALUES (3, 'Competition 1', 'Description for Competition 1', NULL, 0, NULL, NULL, NULL, NULL, '2023-12-08 00:21:10', '2023-12-08 00:21:10');
INSERT INTO `competition` VALUES (4, 'Competition 2', 'Description for Competition 2', NULL, 0, NULL, NULL, NULL, NULL, '2023-12-08 00:21:10', '2023-12-08 00:21:10');
INSERT INTO `competition` VALUES (5, 'Competition 1', 'Description for Competition 1', NULL, 0, NULL, NULL, NULL, NULL, '2023-12-08 00:41:44', '2023-12-08 00:41:44');
INSERT INTO `competition` VALUES (6, 'Competition 2', 'Description for Competition 2', NULL, 0, NULL, NULL, NULL, NULL, '2023-12-08 00:41:44', '2023-12-08 00:41:44');
INSERT INTO `competition` VALUES (7, '某比赛', '这是一个比赛的介绍', 1, 0, '2023-01-01', NULL, NULL, 10, '2023-12-09 20:25:52', '2023-12-09 20:25:52');
INSERT INTO `competition` VALUES (8, '某比赛', '这是一个比赛的介绍', 1, 0, '2023-01-01', NULL, NULL, 10, '2023-12-09 20:31:40', '2023-12-09 20:31:40');
INSERT INTO `competition` VALUES (9, '某比赛', '这是一个比赛的介绍', 1, 0, '2023-01-01', NULL, NULL, 10, '2023-12-09 20:36:40', '2023-12-09 20:36:40');
INSERT INTO `competition` VALUES (10, '某比赛', '这是一个比赛的介绍', 1, 0, '2023-01-01', NULL, NULL, 10, '2023-12-09 20:37:24', '2023-12-09 20:37:24');
INSERT INTO `competition` VALUES (11, '某比赛', '这是一个比赛的介绍', 1, 0, '2023-01-01', '2023-01-10', NULL, 10, '2023-12-09 20:43:48', '2023-12-09 20:43:48');
INSERT INTO `competition` VALUES (12, '某比赛', '这是一个比赛的介绍', 2, 0, '2023-01-01', '2023-01-10', '2023-12-30', 9, '2023-12-09 20:45:50', '2023-12-09 20:54:17');

-- ----------------------------
-- Table structure for rank
-- ----------------------------
DROP TABLE IF EXISTS `rank`;
CREATE TABLE `rank`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `score` double NULL DEFAULT NULL COMMENT '分数',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `competition_id` int(11) NULL DEFAULT NULL COMMENT '比赛id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rank
-- ----------------------------
INSERT INTO `rank` VALUES (1, 1, 100, 'Succeed', 1, '2023-12-08 00:43:19', '2023-12-08 12:21:20', 0);
INSERT INTO `rank` VALUES (2, 2, 150, 'Succeed', 1, '2023-12-08 00:43:19', '2023-12-08 12:21:27', 0);
INSERT INTO `rank` VALUES (3, 1, 90, 'Succeed', 1, '2023-12-08 00:49:18', '2023-12-08 12:21:27', 0);
INSERT INTO `rank` VALUES (4, 2, 40, 'Succeed', 1, '2023-12-08 00:49:18', '2023-12-08 12:21:27', 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `account` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '账号',
  `password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_account`(`account`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户登录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '123', 0, '2023-12-09 20:14:14', '2023-12-09 20:14:25');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户ID',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `photo` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户图像',
  `student_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '学号',
  `college` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '学院',
  `profile` varchar(225) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '个人简介',
  `user_role` int(11) NOT NULL DEFAULT 0 COMMENT '0 普通用户 1 超管',
  `extend` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '扩展字段',
  `ip` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户的ip信息',
  `major` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '专业',
  `deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `key_user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户个人信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1, 1, 'User1', 'user1.jpg', 'S1234567', 'Computer Science', 'A computer science student.', 0, '', '192.168.1.1', 'Software Engineering', 0, '2023-12-08 00:21:10', '2023-12-08 00:21:10');
INSERT INTO `user_info` VALUES (2, 2, 'User2', 'user2.jpg', 'S2345678', 'Electrical Engineering', 'An electrical engineering student.', 0, '', '192.168.1.2', 'Electrical Engineering', 0, '2023-12-08 00:21:10', '2023-12-08 00:21:10');
INSERT INTO `user_info` VALUES (3, 3, 'User3', 'user1.jpg', 'S1234567', 'Computer Science', 'A computer science student.', 0, '', '192.168.1.1', 'Software Engineering', 0, '2023-12-08 00:41:44', '2023-12-08 00:42:18');
INSERT INTO `user_info` VALUES (4, 4, 'User4', 'user2.jpg', 'S2345678', 'Electrical Engineering', 'An electrical engineering student.', 0, '', '192.168.1.2', 'Electrical Engineering', 0, '2023-12-08 00:41:44', '2023-12-08 00:42:23');

SET FOREIGN_KEY_CHECKS = 1;
