-- MySQL dump 10.13  Distrib 8.1.0, for macos14.0 (arm64)
--
-- Host: 127.0.0.1    Database: zhishu-match
-- ------------------------------------------------------
-- Server version	8.1.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `announcement`
--

DROP TABLE IF EXISTS `announcement`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `announcement`
(
    `id`          int unsigned  NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id`     int unsigned  NOT NULL DEFAULT '0' COMMENT '创建用户',
    `title`       varchar(300)  NOT NULL DEFAULT '' COMMENT '标题',
    `summary`     varchar(1024) NOT NULL DEFAULT '' COMMENT '摘要',
    `content`     longtext      NOT NULL COMMENT '内容',
    `status`      tinyint       NOT NULL DEFAULT '0' COMMENT '0-未发布 1-已发布',
    `deleted`     tinyint       NOT NULL DEFAULT '0' COMMENT '是否删除',
    `create_time` timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='公告表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `announcement`
--

LOCK TABLES `announcement` WRITE;
/*!40000 ALTER TABLE `announcement`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `announcement`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `competition`
--

DROP TABLE IF EXISTS `competition`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `competition`
(
    `id`              int       NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`            varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '比赛名称',
    `description`     varchar(1024)                                                 DEFAULT '' COMMENT '介绍，不超过300字',
    `type`            int                                                           DEFAULT NULL COMMENT '比赛类型 1-个人赛 2-团体赛',
    `start_time`      timestamp NULL                                                DEFAULT CURRENT_TIMESTAMP COMMENT '比赛开始时间',
    `end_time`        timestamp NULL                                                DEFAULT CURRENT_TIMESTAMP COMMENT '比赛结束时间',
    `signup_deadline` timestamp NULL                                                DEFAULT CURRENT_TIMESTAMP COMMENT '报名截止时间',
    `max_member`      int                                                           DEFAULT NULL COMMENT '最大人数',
    `deleted`         tinyint   NOT NULL                                            DEFAULT '0' COMMENT '是否删除',
    `create_time`     timestamp NOT NULL                                            DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     timestamp NOT NULL                                            DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 12
  DEFAULT CHARSET = utf8mb3
  ROW_FORMAT = DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competition`
--

LOCK TABLES `competition` WRITE;
/*!40000 ALTER TABLE `competition`
    DISABLE KEYS */;
INSERT INTO `competition` (`id`, `name`, `description`, `type`, `start_time`, `end_time`, `signup_deadline`,
                           `max_member`, `deleted`, `create_time`, `update_time`)
VALUES (1, '程序设计赛道',
        '程序设计赛道是一个激动人心的比赛，旨在考察参赛者的编程技巧和创造力。参赛者将面对一系列程序设计问题，包括算法、数据结构、逻辑推理和代码实现等方面的挑战。这是一个展示你的编程能力和解决问题能力的绝佳机会。无论你是有经验的程序员还是新手入门者，这个比赛都将为你提供一个锻炼和展示自己的舞台。通过参加程序设计赛道，你将能够与其他优秀的编程爱好者交流、学习和竞争，不断提升自己的技能和知识',
        1, '2023-12-14 04:00:00', '2023-12-30 15:59:59', '2023-12-23 04:00:00', 1, 0, '2023-12-13 09:19:47',
        '2023-12-14 05:01:34'),
       (2, 'AI视觉算法打榜赛道',
        'AI视觉算法打榜赛道是一个专注于图像处理和人工智能的比赛。这个赛道旨在挑战参赛者在计算机视觉领域的技术能力和创新思维。参赛者将面对各种图像处理问题，包括图像识别、目标检测、图像分割等。你将有机会利用机器学习和深度学习算法，构建智能视觉系统，解决实际问题。通过参与AI视觉算法打榜赛道，你将能够与其他热爱人工智能和图像处理的专业人士交流，分享经验，不断探索和推动这一领域的发展\n详情请查看 附件: 比赛题目https://dasai.sai.cxjd.zone/3bad382a-8b4c-444e-8987-43387c3b7c29.pdf',
        2, '2023-12-14 04:00:00', '2023-12-30 15:59:59', '2023-12-23 04:00:00', 3, 0, '2023-12-13 10:44:37',
        '2023-12-14 05:01:34'),
       (3, '智能小车赛道',
        '智能小车赛道是一个刺激和有趣的比赛，旨在挑战参赛者在自动驾驶领域的技术能力。参赛者将面对设计、构建和编程智能小车的任务。你将使用传感器、控制器和算法，使小车能够感知环境、做出决策并进行自主导航。这个赛道将考验你的工程能力、机械设计和算法优化技巧。通过参与智能小车赛道，你将能够与其他对自动驾驶技术充满热情的人交流、竞争和合作，共同探索这个快速发展的领域',
        2, '2023-12-14 04:00:00', '2023-12-30 15:59:59', '2023-12-23 04:00:00', 4, 0, '2023-12-13 10:44:37',
        '2023-12-14 05:01:34'),
       (4, '机械臂赛道',
        '机械臂赛道是一个专注于机械臂控制和自动化技术的比赛。参赛者将面对设计、编程和控制机械臂执行各种任务的挑战。你将使用传感器、运动控制和算法，使机械臂能够精确定位、抓取和操纵物体。这个赛道将考验你的机械设计能力、动力学分析和控制算法的优化。',
        2, '2023-12-14 04:00:00', '2023-12-30 15:59:59', '2023-12-23 04:00:00', 4, 0, '2023-12-13 10:44:37',
        '2023-12-14 05:01:34');
/*!40000 ALTER TABLE `competition`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rank`
--

DROP TABLE IF EXISTS `rank`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rank`
(
    `id`             int       NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id`        int                                                           DEFAULT NULL COMMENT '用户id',
    `team_id`        int       NOT NULL                                            DEFAULT '0' COMMENT '队伍id',
    `score`          double                                                        DEFAULT NULL COMMENT '分数',
    `status`         varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '状态',
    `competition_id` int                                                           DEFAULT NULL COMMENT '比赛id',
    `create_time`    timestamp NOT NULL                                            DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    timestamp NOT NULL                                            DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    `deleted`        tinyint   NOT NULL                                            DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 16
  DEFAULT CHARSET = utf8mb3
  ROW_FORMAT = DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rank`
--

LOCK TABLES `rank` WRITE;
/*!40000 ALTER TABLE `rank`
    DISABLE KEYS */;
INSERT INTO `rank` (`id`, `user_id`, `team_id`, `score`, `status`, `competition_id`, `create_time`, `update_time`,
                    `deleted`)
VALUES (5, 2, 0, 0.7192059720380463, 'succeed', 2, '2023-12-15 18:29:03', '2023-12-15 18:29:03', 0),
       (6, 7, 0, 0.7192059720380463, 'succeed', 2, '2023-12-15 18:37:23', '2023-12-15 18:37:23', 0),
       (7, 14, 0, 0.013586065573770493, 'succeed', 2, '2023-12-15 18:42:05', '2023-12-15 18:42:05', 0),
       (8, 14, 0, 1, 'succeed', 2, '2023-12-15 18:42:20', '2023-12-15 18:42:20', 0),
       (9, 14, 0, 1, 'succeed', 2, '2023-12-15 18:46:08', '2023-12-15 18:46:08', 0),
       (10, 14, 0, 1, 'succeed', 2, '2023-12-15 18:46:19', '2023-12-15 18:46:19', 0),
       (11, 14, 0, 1, 'succeed', 2, '2023-12-15 18:46:24', '2023-12-15 18:46:24', 0),
       (12, 11, 0, 1, 'succeed', 2, '2023-12-15 18:53:48', '2023-12-15 18:53:48', 0),
       (13, 7, 0, 0.7192059720380463, 'succeed', 2, '2023-12-15 18:55:44', '2023-12-15 18:55:44', 0),
       (14, 7, 0, 0.7192059720380463, 'succeed', 2, '2023-12-15 18:57:07', '2023-12-15 18:57:07', 0),
       (15, 12, 0, 1, 'succeed', 2, '2023-12-15 18:57:49', '2023-12-15 18:57:49', 0);
/*!40000 ALTER TABLE `rank`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `register`
--

DROP TABLE IF EXISTS `register`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `register`
(
    `id`             int unsigned NOT NULL AUTO_INCREMENT COMMENT '业务id',
    `competition_id` int                   DEFAULT NULL COMMENT '比赛id',
    `user_id`        int          NOT NULL DEFAULT '0' COMMENT '用户id',
    `group_status`   int          NOT NULL DEFAULT '0' COMMENT '组队状态 0-未组队 1-已组队',
    `deleted`        tinyint      NOT NULL DEFAULT '0' COMMENT '是否删除',
    `create_time`    timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user` (`user_id`),
    UNIQUE KEY `uk_competition_user` (`competition_id`, `user_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 31
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='报名表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `register`
--

LOCK TABLES `register` WRITE;
/*!40000 ALTER TABLE `register`
    DISABLE KEYS */;
INSERT INTO `register` (`id`, `competition_id`, `user_id`, `group_status`, `deleted`, `create_time`, `update_time`)
VALUES (30, 2, 2, 0, 0, '2023-12-16 13:58:48', '2023-12-16 13:58:48');
/*!40000 ALTER TABLE `register`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `team`
(
    `id`             int unsigned NOT NULL AUTO_INCREMENT COMMENT '业务id',
    `competition_id` int                   DEFAULT NULL COMMENT '比赛id',
    `name`           varchar(60)  NOT NULL COMMENT '团队名称',
    `captain`        int          NOT NULL DEFAULT '0' COMMENT '队长id',
    `deleted`        tinyint      NOT NULL DEFAULT '0' COMMENT '是否删除',
    `create_time`    timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_competition_name` (`competition_id`, `name`, `captain`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 11
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='团队表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `team`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team_member`
--

DROP TABLE IF EXISTS `team_member`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `team_member`
(
    `id`          int unsigned NOT NULL AUTO_INCREMENT COMMENT '业务id',
    `team_id`     int          NOT NULL DEFAULT '0' COMMENT '团队id',
    `user_id`     int          NOT NULL DEFAULT '0' COMMENT '用户id',
    `status`      int          NOT NULL DEFAULT '0' COMMENT '组队状态 0-未加入 1-待通过 2-已加入',
    `create_time` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_team_user` (`team_id`, `user_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 19
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='组队表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team_member`
--

LOCK TABLES `team_member` WRITE;
/*!40000 ALTER TABLE `team_member`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `team_member`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user`
(
    `id`          int unsigned                            NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `account`     varchar(64) COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '账号',
    `password`    varchar(128) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码',
    `deleted`     tinyint                                 NOT NULL DEFAULT '0' COMMENT '是否删除',
    `create_time` timestamp                               NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp                               NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_account` (`account`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 15
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='用户登录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user`
    DISABLE KEYS */;
INSERT INTO `user` (`id`, `account`, `password`, `deleted`, `create_time`, `update_time`)
VALUES (1, 'sai@163.com', '123456', 0, '2023-12-09 10:36:16',
        '2023-12-10 14:20:21'),
       (2, '123@qq.com', 'password', 0,
        '2023-12-07 17:11:22', '2023-12-09 10:37:20'),
       (4, '3079791498@qq.com', '$2a$10$iWfRN14xjmvbg5AI7HKIE.hzB1kxjvcJm0oW0A8mhSorhqVR5ECAu', 0,
        '2023-12-13 10:56:05', '2023-12-13 10:56:05'),
       (7, 'test@qq.com', '$2a$10$ZVmglwcpkdky0oEYBrKaPetzOGkAsj8CkRQhNgfzsKSfvrAONH/SK', 0, '2023-12-13 12:39:07',
        '2023-12-13 12:59:58'),
       (8, 'test1@qq.com', '$2a$10$ZVmglwcpkdky0oEYBrKaPetzOGkAsj8CkRQhNgfzsKSfvrAONH/SK', 0, '2023-12-13 12:39:07',
        '2023-12-13 12:59:58'),
       (9, 'test2@qq.com', '$2a$10$ZVmglwcpkdky0oEYBrKaPetzOGkAsj8CkRQhNgfzsKSfvrAONH/SK', 0, '2023-12-13 12:39:07',
        '2023-12-13 12:59:58'),
       (10, 'test3@qq.com', '$2a$10$ZVmglwcpkdky0oEYBrKaPetzOGkAsj8CkRQhNgfzsKSfvrAONH/SK', 0, '2023-12-13 12:39:07',
        '2023-12-13 12:59:58'),
       (11, 'test4@qq.com', '$2a$10$ZVmglwcpkdky0oEYBrKaPetzOGkAsj8CkRQhNgfzsKSfvrAONH/SK', 0, '2023-12-13 12:39:07',
        '2023-12-13 12:59:58'),
       (12, 'test5@qq.com', '$2a$10$ZVmglwcpkdky0oEYBrKaPetzOGkAsj8CkRQhNgfzsKSfvrAONH/SK', 0, '2023-12-13 12:39:07',
        '2023-12-13 12:59:58'),
       (13, 'test6@qq.com', '$2a$10$ZVmglwcpkdky0oEYBrKaPetzOGkAsj8CkRQhNgfzsKSfvrAONH/SK', 0, '2023-12-13 12:39:07',
        '2023-12-13 12:59:58'),
       (14, '2368442877@qq.com', '$2a$10$FrfhL091rmdE5.tcHpgV3utgvzMrJL2E7g0xBU1W/iwa3RHLVoK6W', 0,
        '2023-12-13 18:16:20', '2023-12-13 18:16:20');
/*!40000 ALTER TABLE `user`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_info`
(
    `id`          int unsigned                             NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id`     int unsigned                             NOT NULL DEFAULT '0' COMMENT '用户ID',
    `user_name`   varchar(50) COLLATE utf8mb4_general_ci   NOT NULL DEFAULT '' COMMENT '用户名',
    `real_name`   varchar(50) COLLATE utf8mb4_general_ci   NOT NULL DEFAULT '' COMMENT '真实姓名',
    `student_id`  varchar(50) COLLATE utf8mb4_general_ci   NOT NULL DEFAULT '' COMMENT '学号',
    `picture`     varchar(128) COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '用户图像',
    `phone`       varchar(100) COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '电话号码',
    `college`     varchar(50) COLLATE utf8mb4_general_ci   NOT NULL DEFAULT '' COMMENT '学院',
    `profile`     varchar(225) COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '个人简介',
    `user_role`   int                                      NOT NULL DEFAULT '0' COMMENT '0 普通用户 1 超管',
    `extend`      varchar(1024) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '扩展字段',
    `ip`          varchar(100) COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '用户的ip信息',
    `major`       varchar(100) COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '专业',
    `deleted`     tinyint                                  NOT NULL DEFAULT '0' COMMENT '是否删除',
    `create_time` timestamp                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`),
    KEY `key_user_id` (`user_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 14
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='用户个人信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info`
    DISABLE KEYS */;
INSERT INTO `user_info` (`id`, `user_id`, `user_name`, `real_name`, `student_id`, `picture`, `phone`, `college`,
                         `profile`, `user_role`, `extend`, `ip`, `major`, `deleted`, `create_time`, `update_time`)
VALUES (1, 1, '管理员', '', '', '', '', '', '', 1, '', '10.33.64.109', '', 0, '2023-12-09 10:36:57',
        '2023-12-17 09:22:19'),
       (2, 2, 'zhishu_783fc96c', '', '', '', '', 'guet', '', 0, '', '10.33.104.78', '', 0,
        '2023-12-07 17:11:22', '2023-12-15 18:27:13'),
       (4, 4, 'zhishu_cfa1425d', '', '', '', '', '', '', 0, '', '10.60.100.13', '', 0,
        '2023-12-13 10:56:05', '2023-12-13 10:56:05'),
       (5, 7, 'zhishu_783fc96c', 'test_user', '', '', '1233333', 'guet', '', 0, '', '10.33.48.189', '', 0,
        '2023-12-13 12:50:09', '2023-12-15 18:35:16'),
       (6, 8, 'zhishu_783fc96c', 'test_user', '', '', '1233333', 'guet', '', 0, '', '', '', 0, '2023-12-13 12:50:09',
        '2023-12-13 12:50:09'),
       (7, 9, 'zhishu_783fc96c', 'test_user', '', '', '1233333', 'guet', '', 0, '', '10.33.48.189', '', 0,
        '2023-12-13 12:50:09', '2023-12-15 18:47:38'),
       (8, 10, 'zhishu_783fc96c', 'test_user', '', '', '1233333', 'guet', '', 0, '', '', '', 0, '2023-12-13 12:50:09',
        '2023-12-13 12:50:09'),
       (9, 11, 'zhishu_783fc96c', 'test_user', '', '', '1233333', 'guet', '', 0, '', '10.33.48.189', '', 0,
        '2023-12-13 12:50:09', '2023-12-15 18:53:30'),
       (10, 12, 'zhishu_783fc96c', 'test_user', '', '', '1233333', 'guet', '', 0, '', '10.33.48.189', '', 0,
        '2023-12-13 12:50:09', '2023-12-15 18:56:34'),
       (11, 13, 'zhishu_783fc96c', 'test_user', '', '', '1233333', 'guet', '', 0, '', '', '', 0, '2023-12-13 12:50:09',
        '2023-12-13 12:50:09'),
       (13, 14, 'zhishu_7da65e85', '王春实', '2101630316', '', '17864718790', '', '', 0, '', '10.33.48.189', '', 0,
        '2023-12-13 18:16:20', '2023-12-15 18:32:23');
/*!40000 ALTER TABLE `user_info`
    ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2023-12-17 17:46:06
