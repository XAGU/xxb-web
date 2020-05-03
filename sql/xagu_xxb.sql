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

 Date: 03/05/2020 23:06:46
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
INSERT INTO `sys_power` VALUES ('451166594501054464', '我的账户', '1', 'xxt:account:select', '/xxt/account/main', '1', '451166304267800576', 'layui-icon-app', 1, '0');
INSERT INTO `sys_power` VALUES ('451168075706929152', '收件箱', '1', 'xxt:notice', 'http://notice.chaoxing.com/pc/notice/myNotice', '1', '451166304267800576', 'layui-icon-note', 4, '0');
INSERT INTO `sys_power` VALUES ('452553393244868608', '增', '2', 'xxt:account:insert', '', '', '451166594501054464', 'layui-icon-add-1', 1, '0');
INSERT INTO `sys_power` VALUES ('452652816314535936', '删', '2', 'xxt:account:delete', '', '', '451166594501054464', 'layui-icon-delete', 2, '0');
INSERT INTO `sys_power` VALUES ('453311594508718080', '课程列表', '1', 'xxt:course:select', '/xxt/course/main', '1', '451166304267800576', 'layui-icon-file', 2, '0');
INSERT INTO `sys_power` VALUES ('454408401611526144', '课程中心', '1', 'xxt:course', 'http://mooc1-1.chaoxing.com/visit/interaction', '1', '451166304267800576', 'layui-icon-app', 3, '0');
INSERT INTO `sys_power` VALUES ('454408947047206912', '我的云盘', '1', 'xxt:pan', 'http://pan-yz.chaoxing.com', '1', '451166304267800576', 'layui-icon-download-circle', 5, '0');
INSERT INTO `sys_power` VALUES ('454409444604907520', '笔记', '1', 'xxt:activity', 'https://groupyd2.chaoxing.com/pc/activity/activityList', '1', '451166304267800576', 'layui-icon-edit', 6, '0');
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
INSERT INTO `sys_role_power` VALUES ('452652964440576000', '3', '451166304267800576');
INSERT INTO `sys_role_power` VALUES ('452652964440576001', '3', '451166594501054464');
INSERT INTO `sys_role_power` VALUES ('452652964440576002', '3', '452553393244868608');
INSERT INTO `sys_role_power` VALUES ('452652964440576003', '3', '452652816314535936');
INSERT INTO `sys_role_power` VALUES ('452652964440576004', '3', '451168075706929152');
INSERT INTO `sys_role_power` VALUES ('454409601270550528', '1', '451166304267800576');
INSERT INTO `sys_role_power` VALUES ('454409601270550529', '1', '451166594501054464');
INSERT INTO `sys_role_power` VALUES ('454409601270550530', '1', '452553393244868608');
INSERT INTO `sys_role_power` VALUES ('454409601270550531', '1', '452652816314535936');
INSERT INTO `sys_role_power` VALUES ('454409601270550532', '1', '453311594508718080');
INSERT INTO `sys_role_power` VALUES ('454409601270550533', '1', '454408401611526144');
INSERT INTO `sys_role_power` VALUES ('454409601270550534', '1', '451168075706929152');
INSERT INTO `sys_role_power` VALUES ('454409601270550535', '1', '454408947047206912');
INSERT INTO `sys_role_power` VALUES ('454409601270550536', '1', '454409444604907520');
INSERT INTO `sys_role_power` VALUES ('454409601270550537', '1', '694203021537574912');
INSERT INTO `sys_role_power` VALUES ('454409601270550538', '1', '442520236248403968');
INSERT INTO `sys_role_power` VALUES ('454409601270550539', '1', '1');
INSERT INTO `sys_role_power` VALUES ('454409601270550540', '1', '2');
INSERT INTO `sys_role_power` VALUES ('454409601270550541', '1', '450117342081454080');
INSERT INTO `sys_role_power` VALUES ('454409601270550542', '1', '450117498277335040');
INSERT INTO `sys_role_power` VALUES ('454409601270550543', '1', '450117610852454400');
INSERT INTO `sys_role_power` VALUES ('454409601270550544', '1', '3');
INSERT INTO `sys_role_power` VALUES ('454409601270550545', '1', '442359447487123456');
INSERT INTO `sys_role_power` VALUES ('454409601270550546', '1', '450298003970985984');
INSERT INTO `sys_role_power` VALUES ('454409601270550547', '1', '450298165279723520');
INSERT INTO `sys_role_power` VALUES ('454409601270550548', '1', '4');
INSERT INTO `sys_role_power` VALUES ('454409601270550549', '1', '450110932639682560');
INSERT INTO `sys_role_power` VALUES ('454409601270550550', '1', '450111259514376192');
INSERT INTO `sys_role_power` VALUES ('454409601270550551', '1', '450111550494216192');

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
INSERT INTO `sys_user_xxt_account` VALUES ('454460544343216128', '444226209941950464', '454460543332388864');

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
INSERT INTO `xxt_account` VALUES ('454460543332388864', '13565325091', 'Wycsdlg.', NULL, '[\"JSESSIONID=158C0894F99B89DAE69AFEF3B791E4DC; Path=/; HttpOnly\",\"lv=1; Domain=.chaoxing.com; Path=/\",\"fid=436; Domain=.chaoxing.com; Path=/\",\"_uid=120625397; Domain=.chaoxing.com; Path=/\",\"uf=d9387224d3a6095b9436305635d2bba7036e1ede20d7393c7ff3c7e9fb7452ba3a9bcbd18ecb46e9320342e5156407b2c49d67c0c30ca5047c5a963e85f11099bf68506bc4132f28ce71fc6e59483dd3578142841bd53b8da0304b21219cbb8cccbca3515b932eba; Domain=.chaoxing.com; Path=/\",\"_d=1588518302169; Domain=.chaoxing.com; Path=/\",\"UID=120625397; Domain=.chaoxing.com; Path=/\",\"vc=5D9AC57D0D7D737B3A69656ED4B17ADE; Domain=.chaoxing.com; Path=/\",\"vc2=6E4605E69F9B902AA9B8E295662ADB43; Domain=.chaoxing.com; Path=/\",\"vc3=bNfETS8NZTExsNV0G3%2Bpws%2Brhab0UNVzjwtfvKLo3ozjI%2FIMhpSEgUlqPhiwv7Wxk71mm58hAvp9H3WRErqwCXd5gDT2fuz3YUZJxGnqcjI5gDZKhztKzWHLpO%2B7KfpaepGO1Z1vwf0%2BbUaQMFDKzfGGro4%2BVO%2FmpKbx8Pt34IQ%3D78bc47b315b7b44dde5ae40667977e54; Domain=.chaoxing.com; Path=/\",\"xxtenc=406867fea257058a5e588c5b636a80f9; Domain=.chaoxing.com; Path=/\",\"DSSTASH_LOG=C_38-UN_564-US_120625397-T_1588518302170; Domain=.chaoxing.com; Path=/\",\"route=d044092ba3e1792539d699b31c278e5b;Path=/\"]', '2020-05-03 23:05:03');

SET FOREIGN_KEY_CHECKS = 1;
