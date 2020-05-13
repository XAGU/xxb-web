/*
 Navicat Premium Data Transfer

 Source Server         : centos
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : 192.168.189.140:3306
 Source Schema         : xagu_xxb

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 06/05/2020 16:44:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `log_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `username` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `operaEvent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `ua` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `clientIp` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `operaTime` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `isSuccess` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`log_id`) USING BTREE,
  INDEX `username`(`username`) USING BTREE,
  CONSTRAINT `username` FOREIGN KEY (`username`) REFERENCES `sys_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('454792030346940416', 'admin', '登录成功', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '0:0:0:0:0:0:0:1', '2020-05-04 21:02:15', 1);
INSERT INTO `sys_log` VALUES ('454805734673223680', 'admin', '作业打回', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '0:0:0:0:0:0:0:1', '2020-05-04 21:56:43', 0);
INSERT INTO `sys_log` VALUES ('454805802134409216', 'admin', '作业加时', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '0:0:0:0:0:0:0:1', '2020-05-04 21:56:59', 1);
INSERT INTO `sys_log` VALUES ('454805832681525248', 'admin', '作业打回', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '0:0:0:0:0:0:0:1', '2020-05-04 21:57:06', 0);
INSERT INTO `sys_log` VALUES ('454805897219280896', 'admin', '考试打回', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '0:0:0:0:0:0:0:1', '2020-05-04 21:57:21', 0);
INSERT INTO `sys_log` VALUES ('454805944518447104', 'admin', '考试打回', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '0:0:0:0:0:0:0:1', '2020-05-04 21:57:33', 0);
INSERT INTO `sys_log` VALUES ('454805980912422912', 'admin', '考试打回', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '0:0:0:0:0:0:0:1', '2020-05-04 21:57:41', 0);
INSERT INTO `sys_log` VALUES ('454805993226899456', 'admin', '考试打回', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '0:0:0:0:0:0:0:1', '2020-05-04 21:57:44', 0);
INSERT INTO `sys_log` VALUES ('454806002483728384', 'admin', '考试打回', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '0:0:0:0:0:0:0:1', '2020-05-04 21:57:47', 0);
INSERT INTO `sys_log` VALUES ('454813201771663360', 'admin', '登录成功', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '0:0:0:0:0:0:0:1', '2020-05-04 22:26:23', 1);
INSERT INTO `sys_log` VALUES ('455145235597627392', 'admin', '登录成功', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '127.0.0.1', '2020-05-05 20:25:46', 1);
INSERT INTO `sys_log` VALUES ('455151490848395264', 'admin', '登录成功', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '127.0.0.1', '2020-05-05 20:50:37', 1);
INSERT INTO `sys_log` VALUES ('455152783688077312', 'admin', '作业打回', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '127.0.0.1', '2020-05-05 20:55:46', 1);
INSERT INTO `sys_log` VALUES ('455161243943178240', 'admin', '登录成功', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '127.0.0.1', '2020-05-05 21:29:23', 1);
INSERT INTO `sys_log` VALUES ('455161465515675648', 'admin', '登录成功', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '127.0.0.1', '2020-05-05 21:30:16', 1);
INSERT INTO `sys_log` VALUES ('455162344608239616', 'admin', '登录成功', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '127.0.0.1', '2020-05-05 21:33:45', 1);
INSERT INTO `sys_log` VALUES ('455163001167810560', 'admin', '登录成功', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '127.0.0.1', '2020-05-05 21:36:22', 1);
INSERT INTO `sys_log` VALUES ('455163285436764160', 'admin', '登录成功', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '127.0.0.1', '2020-05-05 21:37:29', 1);
INSERT INTO `sys_log` VALUES ('455163532342857728', 'xagu', '登录成功', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '127.0.0.1', '2020-05-05 21:38:28', 1);
INSERT INTO `sys_log` VALUES ('455163832826990592', 'admin', '登录成功', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '127.0.0.1', '2020-05-05 21:39:40', 1);
INSERT INTO `sys_log` VALUES ('455174195303813120', 'admin', '登录成功', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '127.0.0.1', '2020-05-05 22:20:51', 1);
INSERT INTO `sys_log` VALUES ('455174752278024192', 'admin', '登录成功', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '127.0.0.1', '2020-05-05 22:23:03', 1);
INSERT INTO `sys_log` VALUES ('455175717286711296', 'admin', '登录成功', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '127.0.0.1', '2020-05-05 22:26:53', 1);
INSERT INTO `sys_log` VALUES ('455178100599296000', 'admin', '登录成功', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '127.0.0.1', '2020-05-05 22:36:22', 1);
INSERT INTO `sys_log` VALUES ('455195500501667840', 'admin', '登录成功', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '127.0.0.1', '2020-05-05 23:45:30', 1);
INSERT INTO `sys_log` VALUES ('455195916496932864', 'admin', '登录成功', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '127.0.0.1', '2020-05-05 23:47:09', 1);
INSERT INTO `sys_log` VALUES ('455197135747551232', 'admin', '登录成功', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '127.0.0.1', '2020-05-05 23:52:00', 1);
INSERT INTO `sys_log` VALUES ('455394652066025472', 'admin', '登录成功', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '127.0.0.1', '2020-05-06 12:56:52', 1);
INSERT INTO `sys_log` VALUES ('455394979695693824', 'admin', '作业加时', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '127.0.0.1', '2020-05-06 12:58:10', 1);
INSERT INTO `sys_log` VALUES ('455395023949795328', 'admin', '作业加时', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '127.0.0.1', '2020-05-06 12:58:20', 1);
INSERT INTO `sys_log` VALUES ('455395068501692416', 'admin', '作业加时', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '127.0.0.1', '2020-05-06 12:58:31', 1);
INSERT INTO `sys_log` VALUES ('455402282834595840', 'admin', '作业打回', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '127.0.0.1', '2020-05-06 13:27:11', 1);
INSERT INTO `sys_log` VALUES ('455402359246426112', 'admin', '作业加时', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '127.0.0.1', '2020-05-06 13:27:29', 1);
INSERT INTO `sys_log` VALUES ('455412578332577792', 'admin', '登录成功', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '127.0.0.1', '2020-05-06 14:08:05', 1);
INSERT INTO `sys_log` VALUES ('455414790500782080', 'admin', '登录成功', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '127.0.0.1', '2020-05-06 14:16:53', 1);
INSERT INTO `sys_log` VALUES ('455426302393061376', 'admin', '作业加时', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '127.0.0.1', '2020-05-06 15:02:38', 1);
INSERT INTO `sys_log` VALUES ('455426826379071488', 'admin', '作业加时', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '127.0.0.1', '2020-05-06 15:04:42', 1);
INSERT INTO `sys_log` VALUES ('455427024031453184', 'admin', '作业打回', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '127.0.0.1', '2020-05-06 15:05:30', 1);

-- ----------------------------
-- Table structure for sys_power
-- ----------------------------
DROP TABLE IF EXISTS `sys_power`;
CREATE TABLE `sys_power`  (
  `power_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `power_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `power_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `power_code` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `power_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `open_type` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `parent_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `icon` char(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sort` int(11) NULL DEFAULT NULL,
  `enable` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`power_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_power
-- ----------------------------
INSERT INTO `sys_power` VALUES ('1', '系统管理', '0', '', '', '', '0', 'layui-icon-set-fill', 4, '0');
INSERT INTO `sys_power` VALUES ('2', '用户管理', '1', 'sys:user:select', '/system/user/main', '1', '1', 'layui-icon-username', 1, '0');
INSERT INTO `sys_power` VALUES ('3', '角色管理', '1', 'sys:role:select', '/system/role/main', '1', '1', 'layui-icon-user', 2, '0');
INSERT INTO `sys_power` VALUES ('4', '权限管理', '1', 'sys:power:select', '/system/power/main', '1', '1', 'layui-icon-vercode', 3, '0');
INSERT INTO `sys_power` VALUES ('442359447487123456', '增', '2', 'sys:role:insert', '', '', '3', 'layui-icon-add-1', 1, '0');
INSERT INTO `sys_power` VALUES ('442520236248403968', '数据监控', '1', 'sys:druid', '/druid/index.html', '0', '694203021537574912', 'layui-icon-chart', 1, '0');
INSERT INTO `sys_power` VALUES ('450110932639682560', '增', '2', 'sys:power:insert', '', '', '4', 'layui-icon-add-1', 1, '0');
INSERT INTO `sys_power` VALUES ('450111259514376192', '删', '2', 'sys:power:delete', '', '', '4', 'layui-icon-delete', 2, '0');
INSERT INTO `sys_power` VALUES ('450111550494216192', '改', '2', 'sys:power:update', '', '', '4', 'layui-icon-edit', 3, '0');
INSERT INTO `sys_power` VALUES ('450117342081454080', '增', '2', 'sys:user:insert', '', '', '2', 'layui-icon-add-1', 1, '0');
INSERT INTO `sys_power` VALUES ('450117498277335040', '删', '2', 'sys:user:delete', '', '', '2', 'layui-icon-delete', 2, '0');
INSERT INTO `sys_power` VALUES ('450117610852454400', '改', '2', 'sys:user:update', '', '', '2', 'layui-icon-edit', 3, '0');
INSERT INTO `sys_power` VALUES ('450298003970985984', '删', '2', 'sys:role:delete', '', '', '3', 'layui-icon-delete', 2, '0');
INSERT INTO `sys_power` VALUES ('450298165279723520', '改', '2', 'sys:role:update', '', '', '3', 'layui-icon-edit', 3, '0');
INSERT INTO `sys_power` VALUES ('451166304267800576', '学习通', '0', '', '', '', '0', 'layui-icon-home', 1, '0');
INSERT INTO `sys_power` VALUES ('451166594501054464', '我的账户', '1', 'xxt:account:select', '/xxt/account/main', '1', '451166304267800576', 'layui-icon-app', 1, '0');
INSERT INTO `sys_power` VALUES ('451168075706929152', '收件箱', '1', 'xxt:notice', 'http://notice.chaoxing.com/pc/notice/myNotice', '1', '451166304267800576', 'layui-icon-note', 4, '0');
INSERT INTO `sys_power` VALUES ('452553393244868608', '增', '2', 'xxt:account:insert', '', '', '451166594501054464', 'layui-icon-add-1', 1, '0');
INSERT INTO `sys_power` VALUES ('452652816314535936', '删', '2', 'xxt:account:delete', '', '', '451166594501054464', 'layui-icon-delete', 2, '0');
INSERT INTO `sys_power` VALUES ('453311594508718080', '课程列表', '1', 'xxt:course:select', '/xxt/course/main', '1', '451166304267800576', 'layui-icon-file', 2, '0');
INSERT INTO `sys_power` VALUES ('454408401611526144', '课程中心', '1', 'xxt:course', 'http://mooc1-1.chaoxing.com/visit/interaction', '1', '451166304267800576', 'layui-icon-app', 3, '0');
INSERT INTO `sys_power` VALUES ('454408947047206912', '我的云盘', '1', 'xxt:pan', 'http://pan-yz.chaoxing.com', '1', '451166304267800576', 'layui-icon-download-circle', 5, '0');
INSERT INTO `sys_power` VALUES ('454409444604907520', '笔记', '1', 'xxt:activity', 'https://groupyd2.chaoxing.com/pc/activity/activityList', '1', '451166304267800576', 'layui-icon-edit', 6, '0');
INSERT INTO `sys_power` VALUES ('455160442860474368', '个人中心', '0', '', '', '', '0', 'layui-icon-face-smile-b', 2, '0');
INSERT INTO `sys_power` VALUES ('455161101626249216', '操作日志', '1', 'sys:log:select', 'system/log/main', '1', '455160442860474368', 'layui-icon-log', 1, '0');
INSERT INTO `sys_power` VALUES ('455412354679705600', '操作日志', '1', 'sys:allLog:select', 'system/allLog/main', '1', '694203021537574912', 'layui-icon-log', 2, '0');
INSERT INTO `sys_power` VALUES ('694203021537574912', '系统监控', '0', '', '', '', '0', 'layui-icon-console', 3, '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `role_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `enable` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `details` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE,
  UNIQUE INDEX `rolename_unique`(`role_name`) USING BTREE,
  UNIQUE INDEX `rolecode_unique`(`role_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', 'admin', '0', '超级管理员');
INSERT INTO `sys_role` VALUES ('2', '普通管理员', 'manager', '0', '普通管理员');
INSERT INTO `sys_role` VALUES ('3', '普通用户', 'person', '0', '普通用户a');

-- ----------------------------
-- Table structure for sys_role_power
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_power`;
CREATE TABLE `sys_role_power`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `power_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `role_power_roleId`(`role_id`) USING BTREE,
  INDEX `role_power_powerId`(`power_id`) USING BTREE,
  CONSTRAINT `role_power_powerId` FOREIGN KEY (`power_id`) REFERENCES `sys_power` (`power_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `role_power_roleId` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_power
-- ----------------------------
INSERT INTO `sys_role_power` VALUES ('455161423018987520', '2', '451166304267800576');
INSERT INTO `sys_role_power` VALUES ('455161423018987521', '2', '451166594501054464');
INSERT INTO `sys_role_power` VALUES ('455161423018987522', '2', '452553393244868608');
INSERT INTO `sys_role_power` VALUES ('455161423018987523', '2', '452652816314535936');
INSERT INTO `sys_role_power` VALUES ('455161423018987524', '2', '453311594508718080');
INSERT INTO `sys_role_power` VALUES ('455161423018987525', '2', '454408401611526144');
INSERT INTO `sys_role_power` VALUES ('455161423018987526', '2', '451168075706929152');
INSERT INTO `sys_role_power` VALUES ('455161423018987527', '2', '454408947047206912');
INSERT INTO `sys_role_power` VALUES ('455161423018987528', '2', '454409444604907520');
INSERT INTO `sys_role_power` VALUES ('455161423018987529', '2', '455160442860474368');
INSERT INTO `sys_role_power` VALUES ('455161423018987530', '2', '455161101626249216');
INSERT INTO `sys_role_power` VALUES ('455161441570394112', '3', '451166304267800576');
INSERT INTO `sys_role_power` VALUES ('455161441570394113', '3', '451166594501054464');
INSERT INTO `sys_role_power` VALUES ('455161441570394114', '3', '452553393244868608');
INSERT INTO `sys_role_power` VALUES ('455161441570394115', '3', '452652816314535936');
INSERT INTO `sys_role_power` VALUES ('455161441570394116', '3', '451168075706929152');
INSERT INTO `sys_role_power` VALUES ('455161441570394117', '3', '455160442860474368');
INSERT INTO `sys_role_power` VALUES ('455161441570394118', '3', '455161101626249216');
INSERT INTO `sys_role_power` VALUES ('455412399944634368', '1', '451166304267800576');
INSERT INTO `sys_role_power` VALUES ('455412399944634369', '1', '451166594501054464');
INSERT INTO `sys_role_power` VALUES ('455412399944634370', '1', '452553393244868608');
INSERT INTO `sys_role_power` VALUES ('455412399944634371', '1', '452652816314535936');
INSERT INTO `sys_role_power` VALUES ('455412399944634372', '1', '453311594508718080');
INSERT INTO `sys_role_power` VALUES ('455412399944634373', '1', '454408401611526144');
INSERT INTO `sys_role_power` VALUES ('455412399944634374', '1', '451168075706929152');
INSERT INTO `sys_role_power` VALUES ('455412399944634375', '1', '454408947047206912');
INSERT INTO `sys_role_power` VALUES ('455412399944634376', '1', '454409444604907520');
INSERT INTO `sys_role_power` VALUES ('455412399944634377', '1', '455160442860474368');
INSERT INTO `sys_role_power` VALUES ('455412399944634378', '1', '455161101626249216');
INSERT INTO `sys_role_power` VALUES ('455412399944634379', '1', '694203021537574912');
INSERT INTO `sys_role_power` VALUES ('455412399944634380', '1', '442520236248403968');
INSERT INTO `sys_role_power` VALUES ('455412399944634381', '1', '455412354679705600');
INSERT INTO `sys_role_power` VALUES ('455412399944634382', '1', '1');
INSERT INTO `sys_role_power` VALUES ('455412399944634383', '1', '2');
INSERT INTO `sys_role_power` VALUES ('455412399944634384', '1', '450117342081454080');
INSERT INTO `sys_role_power` VALUES ('455412399944634385', '1', '450117498277335040');
INSERT INTO `sys_role_power` VALUES ('455412399944634386', '1', '450117610852454400');
INSERT INTO `sys_role_power` VALUES ('455412399944634387', '1', '3');
INSERT INTO `sys_role_power` VALUES ('455412399944634388', '1', '442359447487123456');
INSERT INTO `sys_role_power` VALUES ('455412399944634389', '1', '450298003970985984');
INSERT INTO `sys_role_power` VALUES ('455412399944634390', '1', '450298165279723520');
INSERT INTO `sys_role_power` VALUES ('455412399944634391', '1', '4');
INSERT INTO `sys_role_power` VALUES ('455412399944634392', '1', '450110932639682560');
INSERT INTO `sys_role_power` VALUES ('455412399944634393', '1', '450111259514376192');
INSERT INTO `sys_role_power` VALUES ('455412399944634394', '1', '450111550494216192');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '编号',
  `username` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账户',
  `password` char(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `salt` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态',
  `real_name` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `email` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话',
  `enable` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否启用',
  `login` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `uniqe_username`(`username`) USING BTREE,
  UNIQUE INDEX `unique_email`(`email`) USING BTREE,
  UNIQUE INDEX `unique`(`phone`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('444226209941950464', 'admin', '$2a$10$1K7E1.IYCrsoZVCb6utOo.5jENtfOzhdKWhc49t2lk.UQd7Oam4FG', NULL, NULL, 'xagu', 'xagu', NULL, '0', 'xagu', '0', NULL);
INSERT INTO `sys_user` VALUES ('452185052684619776', 'xagu', '$2a$10$GnPeqTzddsmA6ukA4xQJtOI6uimnvKUZAvw1VuM9m9w30cIfUx3/e', NULL, NULL, 'xagu', 'xagu@qq.com', NULL, '0', '321', '0', '0');
INSERT INTO `sys_user` VALUES ('452554278192680960', '23121', '$2a$10$KvonCBx7ZHwjiFo2Y7evtO6JBo48b0wCj6kQt3UDMMcAo0YeAcs7m', NULL, NULL, '2121', '21212@qq.com', NULL, '0', '1212', '0', '0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `role_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userid`(`user_id`) USING BTREE,
  INDEX `roleid`(`role_id`) USING BTREE,
  CONSTRAINT `roleid` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `userid` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('450338508213719040', '444226209941950464', '1');
INSERT INTO `sys_user_role` VALUES ('452185053179547648', '452185052684619776', '3');
INSERT INTO `sys_user_role` VALUES ('452554278742134784', '452554278192680960', '3');

-- ----------------------------
-- Table structure for sys_user_xxt_account
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_xxt_account`;
CREATE TABLE `sys_user_xxt_account`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sys_user_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `xxt_account_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_user`(`sys_user_id`) USING BTREE,
  INDEX `xxt_account`(`xxt_account_id`) USING BTREE,
  CONSTRAINT `sys_user` FOREIGN KEY (`sys_user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `xxt_account` FOREIGN KEY (`xxt_account_id`) REFERENCES `xxt_account` (`account_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_xxt_account
-- ----------------------------
INSERT INTO `sys_user_xxt_account` VALUES ('452653194493956096', '452185052684619776', '452653192879149056');
INSERT INTO `sys_user_xxt_account` VALUES ('455426935321923584', '444226209941950464', '455426934860550144');

-- ----------------------------
-- Table structure for xxt_account
-- ----------------------------
DROP TABLE IF EXISTS `xxt_account`;
CREATE TABLE `xxt_account`  (
  `account_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `account_username` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `account_password` char(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `account_school` int(8) NULL DEFAULT NULL,
  `cookie` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`account_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxt_account
-- ----------------------------
INSERT INTO `xxt_account` VALUES ('452653192879149056', '201710159138', '990301cq', 4110, '[\"JSESSIONID=96FABD738D036AD44BA64EC9C9D620A2; Path=/; HttpOnly\",\"lv=3; Domain=.chaoxing.com; Expires=Thu, 28-May-2020 15:23:16 GMT; Path=/\",\"fid=4110; Domain=.chaoxing.com; Expires=Thu, 28-May-2020 15:23:16 GMT; Path=/\",\"_uid=58960432; Domain=.chaoxing.com; Expires=Thu, 28-May-2020 15:23:16 GMT; Path=/\",\"uf=d9387224d3a6095b54d5893ca86d1e0326864e3405193817196ebd963cf063022de5db18aab15ff5fd319048ad889ab0c49d67c0c30ca5047c5a963e85f110999fdd6303a0ce17ddfd68be96b6183b1a32402bc66d851182568f0b57dce0a8d04e166242e0700ecb; Domain=.chaoxing.com; Expires=Thu, 28-May-2020 15:23:16 GMT; Path=/\",\"_d=1588087396367; Domain=.chaoxing.com; Expires=Thu, 28-May-2020 15:23:16 GMT; Path=/\",\"UID=58960432; Domain=.chaoxing.com; Expires=Thu, 28-May-2020 15:23:16 GMT; Path=/\",\"vc=E9F067137D7B069777C7E9A33792F75F; Domain=.chaoxing.com; Expires=Thu, 28-May-2020 15:23:16 GMT; Path=/; HttpOnly\",\"vc2=1359ECC39B2274B28A149C55D50F5C8E; Domain=.chaoxing.com; Expires=Thu, 28-May-2020 15:23:16 GMT; Path=/; HttpOnly\",\"vc3=U%2BNS5MXd2g54EwXZLHtWRCytS1NzA3%2BsRFAxOTbkROxuRl7e8pqlqFwFBtJPxXAMCFShFCgLW%2BVpdsvf7vq9USRXk%2B45DU4P66xaaB%2BUP%2F%2Fn4u3pwl33a1rrkbbySKk%2FsIy%2FhLSIQPLdC%2FAbs8eEe4ds6563wUz%2BUIYZAiq10vI%3D008ce8db2f7fe49f082f15f380c59205; Domain=.chaoxing.com; Expires=Thu, 28-May-2020 15:23:16 GMT; Path=/\",\"xxtenc=789786756760ca397b656d19f5f5d8d8; Domain=.chaoxing.com; Expires=Thu, 28-May-2020 15:23:16 GMT; Path=/\",\"DSSTASH_LOG=C_38-UN_3182-US_58960432-T_1588087396368; Domain=.chaoxing.com; Expires=Thu, 28-May-2020 15:23:16 GMT; Path=/\",\"route=79ce23ddb2f292eb1888bf5874fe3f18;Path=/\"]', '2020-04-28 23:23:17');
INSERT INTO `xxt_account` VALUES ('455426934860550144', '18345164950', '05113618l', NULL, '[\"JSESSIONID=8A29718169D53165AB0883927814B35E; Path=/; HttpOnly\",\"lv=2; Domain=.chaoxing.com; Path=/\",\"fid=1619; Domain=.chaoxing.com; Path=/\",\"_uid=81049269; Domain=.chaoxing.com; Path=/\",\"uf=b2d2c93beefa90dceea3c95dda94cdc02b09f5c93b052999fb165ff70ec8f3b0100bed44b43a4fd9d1e2de524ebad29ec49d67c0c30ca5047c5a963e85f11099edac199ff7004295fd68be96b6183b1a2f9d414f24b87d0aaee76a41c6339dc7e38e3b9ec54cd2e1; Domain=.chaoxing.com; Path=/\",\"_d=1588748708784; Domain=.chaoxing.com; Path=/\",\"UID=81049269; Domain=.chaoxing.com; Path=/\",\"vc=85A734BCF54360D42C47E12F91255189; Domain=.chaoxing.com; Path=/\",\"vc2=DDFF494A7479B8FF97AD971963E29CC0; Domain=.chaoxing.com; Path=/\",\"vc3=WMNcHXiuI5xIEIMtfCLZqY0KzPPbp4VbN135a3zwf3nIxqAzV6oXhxnyxQIVu5J47Q2%2FU2cjipfGAyEPRzR2uPjiOg%2B6zWtutVKYi4cHdLazxpKd6WW5ZAsM%2BC8asGxpS9KK2uZ6KBjNm3KfbMKk6YDZv4iUFH1ANpcQHOc79Yw%3Dcebac4561af97185f1609e4a21262ec5; Domain=.chaoxing.com; Path=/\",\"xxtenc=92a1b1179c9ffc7b4c6508ba1b6a2fe1; Domain=.chaoxing.com; Path=/\",\"DSSTASH_LOG=C_38-UN_1652-US_81049269-T_1588748708786; Domain=.chaoxing.com; Path=/\",\"route=82c7e0db729eaea6696b1dfa5b4c6270;Path=/\"]', '2020-05-06 15:05:08');

SET FOREIGN_KEY_CHECKS = 1;
