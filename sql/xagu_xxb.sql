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

 Date: 25/04/2020 20:58:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
INSERT INTO `sys_power` VALUES ('1', '系统管理', '0', '', '', '', '0', 'layui-icon-set-fill', 2, '0');
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
INSERT INTO `sys_power` VALUES ('451166594501054464', '我的空间', '1', 'xxt:home', 'http://mooc1-1.chaoxing.com/visit/interaction', '1', '451166304267800576', 'layui-icon-app', 1, '0');
INSERT INTO `sys_power` VALUES ('451168075706929152', '收件箱', '1', 'xxb:notice', 'http://notice.chaoxing.com/pc/notice/myNotice', '1', '451166304267800576', 'layui-icon-note', 2, '0');
INSERT INTO `sys_power` VALUES ('694203021537574912', '系统监控', '0', '', '', '', '0', 'layui-icon-console', 2, '0');

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
INSERT INTO `sys_role` VALUES ('3', '普通用户', 'pearson', '0', '普通用户a');

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
INSERT INTO `sys_role_power` VALUES ('450406353840771072', '2', '694203021537574912');
INSERT INTO `sys_role_power` VALUES ('450406353840771073', '2', '442520236248403968');
INSERT INTO `sys_role_power` VALUES ('450406353840771074', '2', '1');
INSERT INTO `sys_role_power` VALUES ('450406353840771075', '2', '2');
INSERT INTO `sys_role_power` VALUES ('450406353840771076', '2', '450117342081454080');
INSERT INTO `sys_role_power` VALUES ('450406353840771077', '2', '450117498277335040');
INSERT INTO `sys_role_power` VALUES ('450406353840771078', '2', '450117610852454400');
INSERT INTO `sys_role_power` VALUES ('450406353840771079', '2', '3');
INSERT INTO `sys_role_power` VALUES ('450406353840771080', '2', '442359447487123456');
INSERT INTO `sys_role_power` VALUES ('450406353840771081', '2', '450298003970985984');
INSERT INTO `sys_role_power` VALUES ('450406353840771082', '2', '450298165279723520');
INSERT INTO `sys_role_power` VALUES ('450406353840771083', '2', '4');
INSERT INTO `sys_role_power` VALUES ('450406353840771084', '2', '450110932639682560');
INSERT INTO `sys_role_power` VALUES ('450406353840771085', '2', '450111259514376192');
INSERT INTO `sys_role_power` VALUES ('450406353840771086', '2', '450111550494216192');
INSERT INTO `sys_role_power` VALUES ('451168159630757888', '1', '451166304267800576');
INSERT INTO `sys_role_power` VALUES ('451168159630757889', '1', '451166594501054464');
INSERT INTO `sys_role_power` VALUES ('451168159630757890', '1', '451168075706929152');
INSERT INTO `sys_role_power` VALUES ('451168159630757891', '1', '1');
INSERT INTO `sys_role_power` VALUES ('451168159630757892', '1', '2');
INSERT INTO `sys_role_power` VALUES ('451168159630757893', '1', '450117342081454080');
INSERT INTO `sys_role_power` VALUES ('451168159630757894', '1', '450117498277335040');
INSERT INTO `sys_role_power` VALUES ('451168159630757895', '1', '450117610852454400');
INSERT INTO `sys_role_power` VALUES ('451168159630757896', '1', '3');
INSERT INTO `sys_role_power` VALUES ('451168159630757897', '1', '442359447487123456');
INSERT INTO `sys_role_power` VALUES ('451168159630757898', '1', '450298003970985984');
INSERT INTO `sys_role_power` VALUES ('451168159630757899', '1', '450298165279723520');
INSERT INTO `sys_role_power` VALUES ('451168159630757900', '1', '4');
INSERT INTO `sys_role_power` VALUES ('451168159630757901', '1', '450110932639682560');
INSERT INTO `sys_role_power` VALUES ('451168159630757902', '1', '450111259514376192');
INSERT INTO `sys_role_power` VALUES ('451168159630757903', '1', '450111550494216192');
INSERT INTO `sys_role_power` VALUES ('451168159630757904', '1', '694203021537574912');
INSERT INTO `sys_role_power` VALUES ('451168159630757905', '1', '442520236248403968');

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

SET FOREIGN_KEY_CHECKS = 1;
