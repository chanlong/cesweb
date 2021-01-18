/*
 Navicat Premium Data Transfer

 Source Server         : 106.12.5.251_cesweb
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : 106.12.5.251:3306
 Source Schema         : cesweb_system

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 18/01/2021 12:40:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` varchar(64) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` char(1) DEFAULT '0',
  `parent_id` varchar(64) DEFAULT NULL,
  `tenant_id` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部门管理';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept` VALUES ('1', '上海信联信息发展股份有限公司', 1, '2018-01-22 19:00:23', '2020-10-21 17:13:36', '0', '0', '1');
INSERT INTO `sys_dept` VALUES ('1fa0d826e4d16345630893d1426c7d04', '智慧政务事业部', 0, '2020-10-21 17:14:39', NULL, '0', '1', '1');
INSERT INTO `sys_dept` VALUES ('2', '沙县国际', 2, '2018-01-22 19:00:38', '2020-10-21 17:13:08', '1', '0', '1');
INSERT INTO `sys_dept` VALUES ('3', '潍坊', 3, '2018-01-22 19:00:44', '2020-10-21 17:12:47', '1', '1', '1');
INSERT INTO `sys_dept` VALUES ('4', '高新', 4, '2018-01-22 19:00:52', '2020-10-21 17:12:43', '1', '3', '1');
INSERT INTO `sys_dept` VALUES ('5', '院校', 5, '2018-01-22 19:00:57', '2020-10-21 17:12:40', '1', '4', '1');
INSERT INTO `sys_dept` VALUES ('6', '潍院', 6, '2018-01-22 19:01:06', '2019-05-18 14:56:16', '1', '5', '1');
INSERT INTO `sys_dept` VALUES ('65b75d22b6419ad81d4683006829a675', '综合办', 0, '2020-10-23 09:38:36', NULL, '0', '1fa0d826e4d16345630893d1426c7d04', '1');
INSERT INTO `sys_dept` VALUES ('7', '山东沙县', 7, '2018-01-22 19:01:57', '2020-10-21 17:13:05', '1', '2', '1');
INSERT INTO `sys_dept` VALUES ('8', '潍坊沙县', 8, '2018-01-22 19:02:03', '2020-10-21 17:13:01', '1', '7', '1');
COMMIT;

-- ----------------------------
-- Table structure for sys_dept_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_relation`;
CREATE TABLE `sys_dept_relation` (
  `ancestor` varchar(64) NOT NULL COMMENT '祖先节点',
  `descendant` varchar(64) NOT NULL COMMENT '后代节点',
  PRIMARY KEY (`ancestor`,`descendant`) USING BTREE,
  KEY `idx1` (`ancestor`) USING BTREE,
  KEY `idx2` (`descendant`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部门关系表';

-- ----------------------------
-- Records of sys_dept_relation
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept_relation` VALUES ('1', '1');
INSERT INTO `sys_dept_relation` VALUES ('1', '1fa0d826e4d16345630893d1426c7d04');
INSERT INTO `sys_dept_relation` VALUES ('1', '65b75d22b6419ad81d4683006829a675');
INSERT INTO `sys_dept_relation` VALUES ('1fa0d826e4d16345630893d1426c7d04', '1fa0d826e4d16345630893d1426c7d04');
INSERT INTO `sys_dept_relation` VALUES ('1fa0d826e4d16345630893d1426c7d04', '65b75d22b6419ad81d4683006829a675');
INSERT INTO `sys_dept_relation` VALUES ('65b75d22b6419ad81d4683006829a675', '65b75d22b6419ad81d4683006829a675');
COMMIT;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `type` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL,
  `system` char(1) DEFAULT '0',
  `del_flag` char(1) DEFAULT '0',
  `tenant_id` varchar(64) NOT NULL DEFAULT '0' COMMENT '所属租户',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `sys_dict_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict` VALUES ('1', 'log_type', '日志类型', '2019-03-19 11:06:44', '2019-03-19 11:06:44', '异常、正常', '1', '0', '1');
INSERT INTO `sys_dict` VALUES ('10', 'response_type', '回复', '2019-03-28 21:29:21', '2019-03-28 21:29:21', '微信消息是否已回复', '1', '0', '1');
INSERT INTO `sys_dict` VALUES ('11', 'param_type', '参数配置', '2019-04-29 18:20:47', '2019-04-29 18:20:47', '检索、原文、报表、安全、文档、消息、其他', '1', '0', '1');
INSERT INTO `sys_dict` VALUES ('12', 'status_type', '租户状态', '2019-05-15 16:31:08', '2019-05-15 16:31:08', '租户状态', '1', '0', '1');
INSERT INTO `sys_dict` VALUES ('13', 'dict_type', '字典类型', '2019-05-16 14:16:20', '2019-05-16 14:20:16', '系统类不能修改', '1', '0', '1');
INSERT INTO `sys_dict` VALUES ('14', 'channel_status', '支付渠道状态', '2019-05-30 16:14:43', '2019-05-30 16:14:43', '支付渠道状态（0-正常，1-冻结）', '1', '0', '1');
INSERT INTO `sys_dict` VALUES ('15', 'channel_id', '渠道编码ID', '2019-05-30 18:59:12', '2019-05-30 18:59:12', '不同的支付方式', '1', '0', '1');
INSERT INTO `sys_dict` VALUES ('16', 'order_status', '订单状态', '2019-06-27 08:17:40', '2019-06-27 08:17:40', '支付订单状态', '1', '0', '1');
INSERT INTO `sys_dict` VALUES ('17', 'grant_types', '授权类型', '2019-08-13 07:34:10', '2019-08-13 07:34:10', NULL, '1', '0', '1');
INSERT INTO `sys_dict` VALUES ('18', 'style_type', '前端风格', '2020-02-07 03:49:28', '2020-02-07 03:50:40', '0-Avue 1-element', '1', '0', '1');
INSERT INTO `sys_dict` VALUES ('2', 'social_type', '社交登录', '2019-03-19 11:09:44', '2019-03-19 11:09:44', '微信、QQ', '1', '0', '1');
INSERT INTO `sys_dict` VALUES ('3', 'leave_status', '请假状态', '2019-03-19 11:09:44', '2019-03-19 11:09:44', '未提交、审批中、完成、驳回', '1', '0', '1');
INSERT INTO `sys_dict` VALUES ('4', 'job_type', '定时任务类型', '2019-03-19 11:22:21', '2019-03-19 11:22:21', 'quartz', '1', '0', '1');
INSERT INTO `sys_dict` VALUES ('5', 'job_status', '定时任务状态', '2019-03-19 11:24:57', '2019-03-19 11:24:57', '发布状态、运行状态', '1', '0', '1');
INSERT INTO `sys_dict` VALUES ('6', 'job_execute_status', '定时任务执行状态', '2019-03-19 11:26:15', '2019-03-19 11:26:15', '正常、异常', '1', '0', '1');
INSERT INTO `sys_dict` VALUES ('7', 'misfire_policy', '定时任务错失执行策略', '2019-03-19 11:27:19', '2019-03-19 11:27:19', '周期', '1', '0', '1');
INSERT INTO `sys_dict` VALUES ('8', 'gender', '性别', '2019-03-27 13:44:06', '2019-03-27 13:44:06', '微信用户性别', '1', '0', '1');
INSERT INTO `sys_dict` VALUES ('9', 'subscribe', '订阅状态', '2019-03-27 13:48:33', '2019-03-27 13:48:33', '公众号订阅状态', '1', '0', '1');
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `dict_id` varchar(64) NOT NULL,
  `value` varchar(100) DEFAULT NULL,
  `label` varchar(100) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `sort` int(10) NOT NULL DEFAULT '0' COMMENT '排序（升序）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` char(1) DEFAULT '0',
  `tenant_id` varchar(64) NOT NULL DEFAULT '0' COMMENT '所属租户',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `sys_dict_value` (`value`) USING BTREE,
  KEY `sys_dict_label` (`label`) USING BTREE,
  KEY `sys_dict_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典项';

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_item` VALUES ('1', '1', '9', '异常', 'log_type', '日志异常', 1, '2019-03-19 11:08:59', '2019-03-25 12:49:13', '', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('10', '4', '2', 'spring bean', 'job_type', 'spring bean容器实例', 2, '2019-03-19 11:23:05', '2019-03-25 12:49:36', '', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('11', '4', '9', '其他', 'job_type', '其他类型', 9, '2019-03-19 11:23:31', '2019-03-25 12:49:36', '', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('12', '4', '3', 'Rest 调用', 'job_type', 'Rest 调用', 3, '2019-03-19 11:23:57', '2019-03-25 12:49:36', '', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('13', '4', '4', 'jar', 'job_type', 'jar类型', 4, '2019-03-19 11:24:20', '2019-03-25 12:49:36', '', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('14', '5', '1', '未发布', 'job_status', '未发布', 1, '2019-03-19 11:25:18', '2019-03-25 12:49:36', '', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('15', '5', '2', '运行中', 'job_status', '运行中', 2, '2019-03-19 11:25:31', '2019-03-25 12:49:36', '', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('16', '5', '3', '暂停', 'job_status', '暂停', 3, '2019-03-19 11:25:42', '2019-03-25 12:49:36', '', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('17', '6', '0', '正常', 'job_execute_status', '正常', 0, '2019-03-19 11:26:27', '2019-03-25 12:49:36', '', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('18', '6', '1', '异常', 'job_execute_status', '异常', 1, '2019-03-19 11:26:41', '2019-03-25 12:49:36', '', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('19', '7', '1', '错失周期立即执行', 'misfire_policy', '错失周期立即执行', 1, '2019-03-19 11:27:45', '2019-03-25 12:49:36', '', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('2', '1', '0', '正常', 'log_type', '日志正常', 0, '2019-03-19 11:09:17', '2019-03-25 12:49:18', '', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('20', '7', '2', '错失周期执行一次', 'misfire_policy', '错失周期执行一次', 2, '2019-03-19 11:27:57', '2019-03-25 12:49:36', '', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('21', '7', '3', '下周期执行', 'misfire_policy', '下周期执行', 3, '2019-03-19 11:28:08', '2019-03-25 12:49:36', '', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('22', '8', '1', '男', 'gender', '微信-男', 0, '2019-03-27 13:45:13', '2019-03-27 13:45:13', '微信-男', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('23', '8', '2', '女', 'gender', '女-微信', 1, '2019-03-27 13:45:34', '2019-03-27 13:45:34', '女-微信', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('24', '8', '0', '未知', 'gender', 'x性别未知', 3, '2019-03-27 13:45:57', '2019-03-27 13:45:57', 'x性别未知', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('25', '9', '0', '未关注', 'subscribe', '公众号-未关注', 0, '2019-03-27 13:49:07', '2019-03-27 13:49:07', '公众号-未关注', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('26', '9', '1', '已关注', 'subscribe', '公众号-已关注', 1, '2019-03-27 13:49:26', '2019-03-27 13:49:26', '公众号-已关注', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('27', '10', '0', '未回复', 'response_type', '微信消息-未回复', 0, '2019-03-28 21:29:47', '2019-03-28 21:29:47', '微信消息-未回复', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('28', '10', '1', '已回复', 'response_type', '微信消息-已回复', 1, '2019-03-28 21:30:08', '2019-03-28 21:30:08', '微信消息-已回复', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('29', '11', '1', '检索', 'param_type', '检索', 0, '2019-04-29 18:22:17', '2019-04-29 18:22:17', '检索', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('3', '2', 'WX', '微信', 'social_type', '微信登录', 0, '2019-03-19 11:10:02', '2019-03-25 12:49:36', '', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('30', '11', '2', '原文', 'param_type', '原文', 0, '2019-04-29 18:22:27', '2019-04-29 18:22:27', '原文', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('31', '11', '3', '报表', 'param_type', '报表', 0, '2019-04-29 18:22:36', '2019-04-29 18:22:36', '报表', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('32', '11', '4', '安全', 'param_type', '安全', 0, '2019-04-29 18:22:46', '2019-04-29 18:22:46', '安全', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('33', '11', '5', '文档', 'param_type', '文档', 0, '2019-04-29 18:22:56', '2019-04-29 18:22:56', '文档', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('34', '11', '6', '消息', 'param_type', '消息', 0, '2019-04-29 18:23:05', '2019-04-29 18:23:05', '消息', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('35', '11', '9', '其他', 'param_type', '其他', 0, '2019-04-29 18:23:16', '2019-04-29 18:23:16', '其他', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('36', '11', '0', '默认', 'param_type', '默认', 0, '2019-04-29 18:23:30', '2019-04-29 18:23:30', '默认', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('37', '12', '0', '正常', 'status_type', '状态正常', 0, '2019-05-15 16:31:34', '2019-05-16 22:30:46', '状态正常', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('38', '12', '9', '冻结', 'status_type', '状态冻结', 1, '2019-05-15 16:31:56', '2019-05-16 22:30:50', '状态冻结', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('39', '13', '1', '系统类', 'dict_type', '系统类字典', 0, '2019-05-16 14:20:40', '2019-05-16 14:20:40', '不能修改删除', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('4', '2', 'QQ', 'QQ', 'social_type', 'QQ登录', 1, '2019-03-19 11:10:14', '2019-03-25 12:49:36', '', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('40', '13', '0', '业务类', 'dict_type', '业务类字典', 0, '2019-05-16 14:20:59', '2019-05-16 14:20:59', '可以修改', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('41', '14', '0', '正常', 'channel_status', '支付渠道状态正常', 0, '2019-05-30 16:16:51', '2019-05-30 16:16:51', NULL, '0', '1');
INSERT INTO `sys_dict_item` VALUES ('42', '14', '1', '冻结', 'channel_status', '支付渠道冻结', 0, '2019-05-30 16:17:08', '2019-05-30 16:17:08', NULL, '0', '1');
INSERT INTO `sys_dict_item` VALUES ('43', '15', 'ALIPAY_WAP', '支付宝wap支付', 'channel_id', '支付宝扫码支付', 0, '2019-05-30 19:03:16', '2019-06-18 13:51:42', '支付宝wap支付', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('44', '15', 'WEIXIN_MP', '微信公众号支付', 'channel_id', '微信公众号支付', 1, '2019-05-30 19:08:08', '2019-06-18 13:51:53', '微信公众号支付', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('45', '16', '1', '支付成功', 'order_status', '支付成功', 1, '2019-06-27 08:18:26', '2019-06-27 08:18:26', '订单支付成功', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('46', '16', '2', '支付完成', 'order_status', '订单支付完成', 2, '2019-06-27 08:18:44', '2019-06-27 08:18:44', '订单支付完成', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('47', '16', '0', '待支付', 'order_status', '订单待支付', 0, '2019-06-27 08:19:02', '2019-06-27 08:19:02', '订单待支付', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('48', '16', '-1', '支付失败', 'order_status', '订单支付失败', 3, '2019-06-27 08:19:37', '2019-06-27 08:19:37', '订单支付失败', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('49', '2', 'GITEE', '码云', 'social_type', '码云', 2, '2019-06-28 09:59:12', '2019-06-28 09:59:12', '码云', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('5', '3', '0', '未提交', 'leave_status', '未提交', 0, '2019-03-19 11:18:34', '2019-03-25 12:49:36', '', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('50', '2', 'OSC', '开源中国', 'social_type', '开源中国登录', 0, '2019-06-28 10:04:32', '2019-06-28 10:04:32', 'http://gitee.huaxiadaowei.com/#/authredirect', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('51', '17', 'password', '密码模式', 'grant_types', '支持oauth密码模式', 0, '2019-08-13 07:35:28', '2019-08-13 07:35:28', NULL, '0', '1');
INSERT INTO `sys_dict_item` VALUES ('52', '17', 'authorization_code', '授权码模式', 'grant_types', 'oauth2 授权码模式', 1, '2019-08-13 07:36:07', '2019-08-13 07:36:07', NULL, '0', '1');
INSERT INTO `sys_dict_item` VALUES ('53', '17', 'client_credentials', '客户端模式', 'grant_types', 'oauth2 客户端模式', 2, '2019-08-13 07:36:30', '2019-08-13 07:36:30', NULL, '0', '1');
INSERT INTO `sys_dict_item` VALUES ('54', '17', 'refresh_token', '刷新模式', 'grant_types', 'oauth2 刷新token', 3, '2019-08-13 07:36:54', '2019-08-13 07:36:54', NULL, '0', '1');
INSERT INTO `sys_dict_item` VALUES ('55', '17', 'implicit', '简化模式', 'grant_types', 'oauth2 简化模式', 4, '2019-08-13 07:39:32', '2019-08-13 07:39:32', NULL, '0', '1');
INSERT INTO `sys_dict_item` VALUES ('56', '18', '0', 'Avue', 'style_type', 'Avue风格', 0, '2020-02-07 03:52:52', '2020-02-07 03:52:52', '', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('57', '18', '1', 'element', 'style_type', 'element-ui', 1, '2020-02-07 03:53:12', '2020-02-07 03:53:12', '', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('6', '3', '1', '审批中', 'leave_status', '审批中', 1, '2019-03-19 11:18:45', '2019-03-25 12:49:36', '', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('7', '3', '2', '完成', 'leave_status', '完成', 2, '2019-03-19 11:19:02', '2019-03-25 12:49:36', '', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('8', '3', '9', '驳回', 'leave_status', '驳回', 9, '2019-03-19 11:19:20', '2019-03-25 12:49:36', '', '0', '1');
INSERT INTO `sys_dict_item` VALUES ('9', '4', '1', 'java类', 'job_type', 'java类', 1, '2019-03-19 11:22:37', '2019-03-25 12:49:36', '', '0', '1');
COMMIT;

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `file_name` varchar(100) DEFAULT NULL,
  `bucket_name` varchar(200) DEFAULT NULL,
  `original` varchar(100) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `file_size` bigint(50) DEFAULT NULL COMMENT '文件大小',
  `create_user` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
  `update_user` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` char(1) DEFAULT '0',
  `tenant_id` varchar(64) DEFAULT NULL COMMENT '所属租户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文件管理表';

-- ----------------------------
-- Records of sys_file
-- ----------------------------
BEGIN;
INSERT INTO `sys_file` VALUES ('7267fa5d6f0aef17bebd3105310e0460', '32994388d8b645679b7ea59144be475a.jpg', 'cesweb', '2020-09-09_104219.jpg', 'jpg', 6757454, 'admin', '2021-01-18 12:22:26', NULL, '2021-01-18 12:22:26', '0', '1');
INSERT INTO `sys_file` VALUES ('908b67db9d8315853bb5f5aa213057fc', 'e27b93d8cf1e4f63940322776fc660fd.gif', 'cesweb', 'avatar.gif', 'gif', 22736, 'admin', '2020-08-18 17:57:58', NULL, '2020-08-18 17:57:58', '0', '1');
COMMIT;

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job` (
  `job_id` varchar(32) NOT NULL COMMENT '任务id',
  `job_name` varchar(64) NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL COMMENT '任务组名',
  `job_order` char(1) DEFAULT '1' COMMENT '组内执行顺利，值越大执行优先级越高，最大值9，最小值1',
  `job_type` char(1) NOT NULL DEFAULT '1' COMMENT '1、java类;2、spring bean名称;3、rest调用;4、jar调用;9其他',
  `execute_path` varchar(500) DEFAULT NULL COMMENT 'job_type=3时，rest调用地址，仅支持rest get协议,需要增加String返回值，0成功，1失败;job_type=4时，jar路径;其它值为空',
  `class_name` varchar(500) DEFAULT NULL COMMENT 'job_type=1时，类完整路径;job_type=2时，spring bean名称;其它值为空',
  `method_name` varchar(500) DEFAULT NULL COMMENT '任务方法',
  `method_params_value` varchar(2000) DEFAULT NULL COMMENT '参数值',
  `cron_expression` varchar(255) DEFAULT NULL COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) DEFAULT '3' COMMENT '错失执行策略（1错失周期立即执行 2错失周期执行一次 3下周期执行）',
  `job_tenant_type` char(1) DEFAULT '1' COMMENT '1、多租户任务;2、非多租户任务',
  `job_status` char(1) DEFAULT '0' COMMENT '状态（1、未发布;2、运行中;3、暂停;4、删除;）',
  `job_execute_status` char(1) DEFAULT '0' COMMENT '状态（0正常 1异常）',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '初次执行时间',
  `previous_time` timestamp NULL DEFAULT NULL COMMENT '上次执行时间',
  `next_time` timestamp NULL DEFAULT NULL COMMENT '下次执行时间',
  `tenant_id` varchar(32) DEFAULT '1' COMMENT '租户',
  `remark` varchar(500) DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`,`job_name`,`job_group`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='定时任务调度表';

-- ----------------------------
-- Records of sys_job
-- ----------------------------
BEGIN;
INSERT INTO `sys_job` VALUES ('63516258bce7d7b734b256f26132fbec', '测试任务', '测试任务', '', '3', '', '', '', '', '3/5 * * * * ? *', '1', '1', '1', '', 'admin', '2020-08-20 10:59:08', '', '2020-08-20 10:59:08', NULL, NULL, NULL, '', '');
COMMIT;

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log` (
  `job_log_id` varchar(32) NOT NULL COMMENT '任务日志ID',
  `job_id` varchar(32) NOT NULL COMMENT '任务id',
  `job_name` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '任务组名',
  `job_order` char(1) CHARACTER SET utf8 DEFAULT NULL COMMENT '组内执行顺利，值越大执行优先级越高，最大值9，最小值1',
  `job_type` char(1) CHARACTER SET utf8 NOT NULL DEFAULT '1' COMMENT '1、java类;2、spring bean名称;3、rest调用;4、jar调用;9其他',
  `execute_path` varchar(500) CHARACTER SET utf8 DEFAULT NULL COMMENT 'job_type=3时，rest调用地址，仅支持post协议;job_type=4时，jar路径;其它值为空',
  `class_name` varchar(500) CHARACTER SET utf8 DEFAULT NULL COMMENT 'job_type=1时，类完整路径;job_type=2时，spring bean名称;其它值为空',
  `method_name` varchar(500) CHARACTER SET utf8 DEFAULT NULL COMMENT '任务方法',
  `method_params_value` varchar(2000) CHARACTER SET utf8 DEFAULT NULL COMMENT '参数值',
  `cron_expression` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT 'cron执行表达式',
  `job_message` varchar(500) CHARACTER SET utf8 DEFAULT NULL COMMENT '日志信息',
  `job_log_status` char(1) CHARACTER SET utf8 DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `execute_time` varchar(30) CHARACTER SET utf8 DEFAULT NULL COMMENT '执行时间',
  `exception_info` varchar(2000) CHARACTER SET utf8 DEFAULT '' COMMENT '异常信息',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `tenant_id` varchar(32) NOT NULL DEFAULT '1' COMMENT '租户id',
  PRIMARY KEY (`job_log_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='定时任务执行日志表';

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `type` char(1) DEFAULT '0',
  `title` varchar(255) DEFAULT NULL,
  `service_id` varchar(32) DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remote_addr` varchar(255) DEFAULT NULL,
  `user_agent` varchar(1000) DEFAULT NULL,
  `request_uri` varchar(255) DEFAULT NULL,
  `method` varchar(10) DEFAULT NULL,
  `params` text,
  `time` mediumtext CHARACTER SET utf8 COMMENT '执行时间',
  `del_flag` char(1) DEFAULT '0',
  `exception` text,
  `tenant_id` varchar(64) DEFAULT '0' COMMENT '所属租户',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `sys_log_create_by` (`create_by`) USING BTREE,
  KEY `sys_log_request_uri` (`request_uri`) USING BTREE,
  KEY `sys_log_type` (`type`) USING BTREE,
  KEY `sys_log_create_date` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='日志表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
BEGIN;
INSERT INTO `sys_log` VALUES ('00d608a4e746fe1601fdb9dc8a66c4e5', '0', 'admin用户登录', 'cesweb', 'admin', '2020-10-10 15:07:15', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('00db3526344cd41777d7def36f3c18fb', '0', '更新菜单', 'cesweb', 'admin', '2020-08-13 15:35:08', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '182', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('00f78702088f49ffba95e180fafc455d', '0', '更新菜单', 'cesweb', 'admin', '2020-12-15 14:36:21', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/menu', 'PUT', '', '278', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('01073979728e300a13142458e5957ab4', '0', 'admin用户登录', 'N/A', 'admin', '2020-07-16 07:25:16', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/token/form', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('01d5680ca855c13ae084cae01d35af09', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:08:26', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/6600', 'DELETE', '', '91', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('024191de719244f2a29b0dcf5b9cfd63', '0', 'admin用户登录', 'N/A', 'admin', '2020-07-16 08:09:33', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/token/form', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('0241fc611917604c3a3280c3f655024c', '0', '更新菜单', 'cesweb', 'admin', '2020-09-23 08:56:22', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '24', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('03b2692414baf57faed545313477945d', '9', 'chanlong用户登录', 'cesweb', 'chanlong', '2020-10-23 17:06:40', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'chanlong', NULL, '0', '用户名或密码错误', '1');
INSERT INTO `sys_log` VALUES ('03c5c5d0ba4e14c24835a38ca7ca4132', '9', 'null用户登录', 'null', 'null', '2020-08-21 15:18:20', NULL, '127.0.0.1', 'Java/1.8.0_251', '/oauth/check_token', 'POST', 'null', NULL, '0', 'Bad credentials', '1');
INSERT INTO `sys_log` VALUES ('046293fa784ab66e1074b8b63a245944', '0', '删除文件管理', 'cesweb', 'admin', '2020-08-18 16:00:39', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/sysfile/7fe98940d5c77f0a5fdb19cfc1fc4345', 'DELETE', '', '300', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('056342cf6be5e59993ea43fae5106dc0', '0', '更新菜单', 'cesweb', 'admin', '2020-11-18 10:58:58', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/menu', 'PUT', '', '69', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('058062dacb4951194bdc5e62d6e1a76b', '0', '更新菜单', 'cesweb', 'admin', '2020-12-15 14:46:29', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/menu', 'PUT', '', '561', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('05d663268fc8ba6bfcdf36a431e58fef', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:06:24', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/5520', 'DELETE', '', '73', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('061296d51b27fd6f8f21d49bb0bb2e6b', '0', 'admin用户登录', 'cesweb', 'admin', '2020-10-27 09:21:27', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('082378076a55a9ab20aee86dcd82c802', '9', 'admin用户登录', 'cesweb', 'admin', '2020-11-30 10:11:31', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', '1');
INSERT INTO `sys_log` VALUES ('0840b9cf493c5d5e92b982cc2c1087f8', '0', '更新菜单', 'cesweb', 'admin', '2020-12-15 14:36:54', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/menu', 'PUT', '', '195', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('08510652e606dfccdcfdf828efbfef4b', '0', 'admin用户登录', 'cesweb', 'admin', '2020-11-17 12:56:23', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('0876b7c96b873d68790d8b1e48b06332', '0', '更新菜单', 'cesweb', 'admin', '2020-12-15 14:36:41', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/menu', 'PUT', '', '212', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('08cb1d21d07a79c48d9b33b6c5932ca5', '9', 'cesweb用户登录', 'cesweb', 'cesweb', '2020-07-17 03:16:53', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'cesweb', NULL, '0', 'Bad credentials', '1');
INSERT INTO `sys_log` VALUES ('08df50e291c4b27ab7176006b07006e9', '0', '添加角色', 'cesweb', 'admin', '2020-10-23 09:42:14', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/role', 'POST', '', '18', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('09420ea6b9b3e3e1b414b7cba45d322c', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:07:40', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/6203', 'DELETE', '', '75', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('099c1f748daa91f3fb7e88e98c317e68', '0', '更新菜单', 'cesweb', 'admin', '2020-09-23 08:49:06', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '30', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('0a2f62b8404d6e31a9d01ee6e585cef6', '0', '更新菜单', 'cesweb', 'admin', '2020-11-18 10:59:57', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/menu', 'PUT', '', '12', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('0ae8158c269fff022acb7087c2a46da6', '0', 'admin用户登录', 'cesweb', 'admin', '2020-08-27 08:51:38', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('0aeddf0f0dbcd5079e51de09cca9ee3c', '9', 'admin用户登录', 'admin', 'admin', '2020-07-17 00:36:52', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/error', 'GET', 'admin', NULL, '0', 'Bad credentials', '1');
INSERT INTO `sys_log` VALUES ('0af8d6fda60a6eff0d86dc66372915bf', '0', '更新菜单', 'cesweb', 'admin', '2020-09-23 08:48:26', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '12', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('0b597e299f2e551c1e2f719af7b0ebac', '0', '新增定时任务', 'cesweb', 'admin', '2020-08-20 10:59:08', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/sys-job', 'POST', '', '92', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('0d2f1ab5958c0266c5f6fe33bf07e966', '0', '更新菜单', 'cesweb', 'admin', '2020-12-15 14:42:54', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/menu', 'PUT', '', '226', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('0e46e5d86edc7ec99b8b883891bfd2c0', '0', 'admin用户登录', 'cesweb', 'admin', '2020-09-27 09:14:17', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('0eb5bba6f1ec46a9dad3c3366937891f', '0', 'admin用户登录', 'cesweb', 'admin', '2020-08-21 15:16:50', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('0f36f5640ce615d60cba83791c8640cf', '0', 'admin用户登录', 'cesweb', 'admin', '2020-10-19 11:04:51', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('0f41576051847fd0b288cf0808ef6286', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:08:16', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/6400', 'DELETE', '', '38', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('10c7775782d1b093bb9d0008c4f7b4a8', '0', 'admin用户登录', 'cesweb', 'admin', '2020-08-13 10:10:29', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('113d6043576ae0502af795a2020217e5', '0', '更新菜单', 'cesweb', 'admin', '2020-08-13 15:33:53', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '140', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('1159f8d57b1fc465e09f2f6e80af60fe', '0', '更新菜单', 'cesweb', 'admin', '2020-12-15 14:38:04', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/menu', 'PUT', '', '210', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('1383ab3fafdd0521e8562bcfc1d91331', '0', '菜单排序', 'cesweb', 'admin', '2020-08-18 09:05:39', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/sort', 'PUT', '', '139', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('13ba10ea812b21eb884e19a0fb0fa366', '9', 'admin用户登录', 'admin', 'admin', '2020-09-04 09:31:42', NULL, '172.20.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/error', 'GET', 'admin', NULL, '0', 'Bad credentials', '1');
INSERT INTO `sys_log` VALUES ('142ed6e580d58789ecf5992dc03b9345', '0', '删除文件管理', 'cesweb', 'admin', '2020-08-18 17:41:34', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/sysfile/668ee1e74e5ad788cb05c52984b92c75', 'DELETE', '', '367', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('149135ba2b02175aaf484da41e18820f', '0', 'admin用户登录', 'N/A', 'admin', '2020-07-16 07:07:17', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/token/form', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('15965488dd4c6c89523797d514dbb1d8', '0', 'admin用户登录', 'cesweb', 'admin', '2020-12-18 15:23:11', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('16393a2d89c289066e07e72def84d450', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:08:12', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/6602', 'DELETE', '', '26', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('168e93ff394cab257baf4f270f973c71', '0', 'admin用户登录', 'cesweb', 'admin', '2020-08-20 09:59:42', NULL, '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('169d634261c0955f81c9340bdc6c3edd', '0', '更新菜单', 'cesweb', 'admin', '2020-09-23 08:51:10', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '23', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('16f0809adae5d521e8adf07f0ceb432a', '0', 'admin用户登录', 'cesweb', 'admin', '2020-08-18 08:46:11', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('17a5cfd99176b0d2648250c519e1b5e4', '0', 'admin用户登录', 'cesweb', 'admin', '2020-11-30 14:01:23', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('17d30460dd37231a17c7ee9cb0d83c6c', '0', 'admin用户登录', 'cesweb', 'admin', '2020-07-22 02:07:03', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('18a7071b10f17cc793678fd307792945', '0', 'admin用户登录', 'cesweb', 'admin', '2020-11-30 17:26:35', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('191c23838757cb1c8feb73432a3af526', '0', '删除部门', 'cesweb', 'admin', '2020-10-21 17:12:40', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/dept/5', 'DELETE', '', '126', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('1936d2f26af0abd0d735473ea4aaaf12', '0', '更新菜单', 'cesweb', 'admin', '2020-11-18 11:00:41', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/menu', 'PUT', '', '53', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('19429bfccdd05ed2bdcd3228a6bb9f03', '0', 'admin用户登录', 'cesweb', 'admin', '2020-09-22 07:58:06', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('1a0dcf723c1f6c420fe3d51881dd9cd2', '0', '更新菜单', 'cesweb', 'admin', '2020-09-23 08:40:54', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '41', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('1a9b707f25d7cbf051c2dd8d744d423b', '9', 'null用户登录', 'null', 'null', '2020-08-21 15:18:20', NULL, '127.0.0.1', 'Java/1.8.0_251', '/oauth/check_token', 'POST', 'null', NULL, '0', 'Bad credentials', '1');
INSERT INTO `sys_log` VALUES ('1ae3d89a63d1789349ff00610682ba33', '9', 'null用户登录', 'null', 'null', '2020-08-21 15:17:57', NULL, '127.0.0.1', 'Java/1.8.0_251', '/oauth/check_token', 'POST', 'null', NULL, '0', 'Bad credentials', '1');
INSERT INTO `sys_log` VALUES ('1c5d0bbf8a55c842bdb89c811fe0957b', '0', 'admin用户登录', 'cesweb', 'admin', '2020-09-28 07:49:09', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('1c619e8693cd15d69774f872f5db9c53', '0', 'admin用户登录', 'cesweb', 'admin', '2020-08-20 12:47:15', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('1cc00dc005c8813eb26ba754dafdfb35', '0', 'admin用户登录', 'cesweb', 'admin', '2020-08-19 09:40:04', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('1ccc867613b28c6aad6f05db8a2b8cc9', '0', 'admin用户登录', 'N/A', 'admin', '2020-07-16 07:02:38', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/token/form', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('1cd0e05d1c3638e9d32b81eff952a6ad', '0', 'admin用户登录', 'cesweb', 'admin', '2020-08-19 09:38:49', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('1d56a48b7f3b1fa69cea9d17407988d3', '9', 'admin用户登录', 'cesweb', 'admin', '2020-11-30 10:32:24', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', '1');
INSERT INTO `sys_log` VALUES ('1dfd51109f1346923e0f0cf058dfe66e', '0', '更新菜单', 'cesweb', 'admin', '2020-08-13 15:33:11', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '259', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('1fd4a6126d1dc3569e81a02ad97dbc53', '0', 'admin用户登录', 'cesweb', 'admin', '2020-12-15 11:56:12', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('1fef977064e62afec797bc00e0d429c2', '0', '更新菜单', 'cesweb', 'admin', '2020-12-15 14:37:32', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/menu', 'PUT', '', '199', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('201c030e7a7b71970736597a85a91282', '0', 'admin用户登录', 'cesweb', 'admin', '2020-07-21 01:56:02', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('220e755fa2b9e1d9ab8c4e109b16a011', '0', 'admin用户登录', 'cesweb', 'admin', '2020-10-15 09:02:56', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('22e748f05c5869ac0a6b5b44f50f92fb', '0', 'admin用户登录', 'cesweb', 'admin', '2020-10-26 13:16:47', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('22f6935b3e893e3ab394b835541d46ec', '0', '修改路由', 'cesweb', 'admin', '2020-12-15 12:18:43', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/route', 'PUT', '', '1796', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('232928db254f7c4da9fe78b9cfe71267', '9', 'cesweb用户登录', 'cesweb', 'cesweb', '2020-07-17 03:09:48', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'cesweb', NULL, '0', 'Bad credentials', '1');
INSERT INTO `sys_log` VALUES ('233af16865c9cee1e7f8f1a9f78362a9', '0', '更新菜单', 'cesweb', 'admin', '2020-08-13 15:34:10', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '186', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('23802a73a110fd7006f74dfb226ef597', '0', 'admin用户登录', 'cesweb', 'admin', '2020-10-29 10:04:42', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('243acce849cb1844def79867c7da159f', '0', 'admin用户登录', 'cesweb', 'admin', '2020-11-30 11:14:19', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('2478d18d16d95c5277dfe917ab5cc2e4', '0', '更新菜单', 'cesweb', 'admin', '2020-09-23 08:47:46', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '19', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('24ad718beeacd7ec246d1f34be84c4f9', '0', '菜单排序', 'cesweb', 'admin', '2020-08-18 09:05:41', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/sort', 'PUT', '', '107', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('25642effd0c018004f32c4a04afc05c6', '0', 'admin用户登录', 'cesweb', 'admin', '2020-09-03 10:55:27', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('26302ace5286a07cfbef54f28bc1c228', '0', '更新菜单', 'cesweb', 'admin', '2020-09-23 08:28:44', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '103', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('268588c5234f932a7d6b14bac75cc037', '9', 'admin用户登录', 'admin', 'admin', '2020-07-16 07:04:34', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'GET', 'admin', NULL, '0', 'Bad credentials', '1');
INSERT INTO `sys_log` VALUES ('269b41c73dd972a38b38d6a2ffa17ee0', '0', 'admin用户登录', 'cesweb', 'admin', '2020-09-21 15:04:05', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('26ad552d346169ff2e25e3c060d48c39', '0', 'admin用户登录', 'cesweb', 'admin', '2021-01-18 09:00:17', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('271cab142d4a2246f35ac7f2e70db2a3', '0', '菜单排序', 'cesweb', 'admin', '2020-08-18 09:05:25', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/sort', 'PUT', '', '95', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('2866e72d8910dd8ce1d8fde60e32304b', '9', 'admin用户登录', 'cesweb', 'admin', '2020-11-30 10:05:16', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', '1');
INSERT INTO `sys_log` VALUES ('287899fb7163b2089046e56f5b69f61d', '0', 'admin用户登录', 'cesweb', 'admin', '2020-09-09 09:04:39', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('29f05d05cf10a99b5608069333a537f0', '9', 'admin用户登录', 'pig', 'admin', '2020-07-22 01:42:58', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', '1');
INSERT INTO `sys_log` VALUES ('2a21693a7764a766d20ae4213b21b2a4', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:06:15', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/5430', 'DELETE', '', '43', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('2ab19d763a953005e5db2cf5f0654fb7', '0', '更新菜单', 'cesweb', 'admin', '2020-12-15 14:48:27', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/menu', 'PUT', '', '192', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('2b0f4c20eeb66034ce821ed5428adb75', '0', 'admin用户登录', 'cesweb', 'admin', '2020-11-30 09:21:04', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('2b8c1a596b28cd676986e9bced87bfcd', '0', 'admin用户登录', 'N/A', 'admin', '2020-07-16 08:12:19', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/token/form', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('2c72aec69d5b89574abbeddb0e2f57fd', '0', 'admin用户登录', 'cesweb', 'admin', '2020-12-01 10:07:00', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('2d7663bcad6a9b22fe29060f7e3e2cb0', '0', '立刻执行定时任务', 'cesweb', 'admin', '2021-01-18 12:12:50', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/sys-job/run-job/63516258bce7d7b734b256f26132fbec', 'POST', '', '56', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('2db67edfa0a9fcaf658e9fbc7b60d2dd', '0', 'admin用户登录', 'cesweb', 'admin', '2020-11-30 13:59:42', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('2debe351a62074d82bdbce4b8f8099cb', '0', 'admin用户登录', 'N/A', 'admin', '2020-07-16 01:03:08', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/token/form', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('2e2a4d9e582581b3d0a71bfa2c60627c', '0', 'admin用户登录', 'cesweb', 'admin', '2020-08-17 09:07:05', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('2ea9dceddf7808ae0e0a7d0736d6f487', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:07:50', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/6302', 'DELETE', '', '25', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('2fcd37eb91f924fc856ead2e88b6ab59', '0', 'admin用户登录', 'cesweb', 'admin', '2020-07-24 02:54:36', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('3012df563d39e349228ccd3e5192df27', '0', 'admin用户登录', 'cesweb', 'admin', '2020-09-10 14:59:12', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('315595d06186da0eaee48619f3cfe9e9', '0', 'admin用户登录', 'cesweb', 'admin', '2020-11-30 13:53:04', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('319f398f72f75bba0a7f3fbd2b1b8100', '9', 'admin用户登录', 'cesweb', 'admin', '2020-11-30 10:28:29', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', '1');
INSERT INTO `sys_log` VALUES ('330c6c451157b65c10d4cb480fdfff28', '0', 'admin用户登录', 'cesweb', 'admin', '2020-07-22 08:30:10', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('330f356c9c8ba77d31a71b3582970f5e', '9', 'admin用户登录', 'admin', 'admin', '2020-07-17 00:37:38', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'GET', 'admin', NULL, '0', 'Bad credentials', '1');
INSERT INTO `sys_log` VALUES ('332927bec2c3076b20c211567ee246e4', '0', 'admin用户登录', 'N/A', 'admin', '2020-07-16 08:02:35', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/token/form', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('342d0bbbef6c9c67932a5adb14e13636', '0', 'admin用户登录', 'cesweb', 'admin', '2020-09-03 17:35:34', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('3701ec78c4756fdd71948de999c22b8d', '0', '更新菜单', 'cesweb', 'admin', '2020-11-18 11:00:04', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/menu', 'PUT', '', '20', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('37111ec5e1861fb0ecb34f289e80859e', '0', '新增定时任务', 'cesweb', 'admin', '2020-08-20 10:10:01', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/sys-job', 'POST', '', '91', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('377127fa50064308dc0f9add1dbb386a', '0', '更新菜单', 'cesweb', 'admin', '2020-09-23 08:55:02', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '26', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('37927dc28f98f4a28b2184ceb3d0157b', '0', '更新菜单', 'cesweb', 'admin', '2020-08-13 15:15:39', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '160', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('386b28786d68ed099dcfa24b0061f9fb', '0', '更新菜单', 'cesweb', 'admin', '2020-12-15 14:43:13', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/menu', 'PUT', '', '237', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('38780963f3a9368857c8de9068b744f7', '0', 'admin用户登录', 'cesweb', 'admin', '2020-08-13 10:48:12', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('3967811762b73a224b2d6aa404469054', '9', 'null用户登录', 'null', 'null', '2020-08-21 15:23:48', NULL, '127.0.0.1', 'Java/1.8.0_251', '/oauth/check_token', 'POST', 'null', NULL, '0', 'Bad credentials', '1');
INSERT INTO `sys_log` VALUES ('3983d5e35b316accfc6af73874942996', '0', '更新菜单', 'cesweb', 'admin', '2020-08-13 15:35:30', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '197', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('39c1e032daff280719193af481c5aa5a', '0', '更新菜单', 'cesweb', 'admin', '2020-08-13 15:30:07', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '136', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('3a496b346df729e67d00ad1a8fd46491', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:05:59', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/5310', 'DELETE', '', '50', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('3bf170f80441a68aaf4eb1779b441862', '0', 'admin用户登录', 'cesweb', 'admin', '2020-08-19 09:30:13', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('3c7b3037304e8982d5331e82b0987285', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:07:48', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/6301', 'DELETE', '', '43', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('3c823e2c0a4ca81fae5832716b89db87', '0', 'admin用户登录', 'cesweb', 'admin', '2020-08-18 15:53:41', NULL, '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('3c87887aa97915101b59851167a5672c', '0', '添加用户', 'cesweb', 'admin', '2020-10-23 09:54:09', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/user', 'POST', '', '193', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('3d27bb42fdb8ff07681a03b6dd667d4f', '0', 'admin用户登录', 'cesweb', 'admin', '2020-07-20 02:51:53', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('40d4915abd45d5c585cd6c19d92441dd', '9', 'null用户登录', 'null', 'null', '2020-08-21 15:23:22', NULL, '127.0.0.1', 'Java/1.8.0_251', '/oauth/check_token', 'POST', 'null', NULL, '0', 'Bad credentials', '1');
INSERT INTO `sys_log` VALUES ('4116171427f832c6c3c424be74500d09', '0', 'admin用户登录', 'cesweb', 'admin', '2020-10-20 11:03:20', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('42a7bf591070a44aef9865ad63fd302c', '9', 'admin用户登录', 'cesweb', 'admin', '2020-11-27 17:38:49', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', '1');
INSERT INTO `sys_log` VALUES ('42cf09e1a77b37950cd1881361c81a61', '0', 'admin用户登录', 'cesweb', 'admin', '2020-12-02 10:20:50', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('4313d0a6835a2c5024c588c110ac4e1c', '0', 'admin用户登录', 'cesweb', 'admin', '2020-11-20 09:21:34', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('43a38c4568e2b6c6106b1a84c24a09b0', '0', 'admin用户登录', 'cesweb', 'admin', '2020-08-20 11:58:54', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('44ec36cf3a17fd4413a3e27786bcca0f', '0', '更新角色菜单', 'cesweb', 'admin', '2020-10-23 17:11:47', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/role/menu', 'PUT', '', '574', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('45393c68181a3695712b27ab31e76c53', '0', '更新菜单', 'cesweb', 'admin', '2020-08-13 15:15:01', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '132', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('45c580a3ba5acf8618cdf40a036ec54e', '0', '更新菜单', 'cesweb', 'admin', '2020-08-13 15:31:45', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '190', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('45d13119564e0465e71355efb2bd2f9c', '0', '菜单排序', 'cesweb', 'admin', '2020-08-18 09:05:21', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/sort', 'PUT', '', '179', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('465a1e028af99040f5b76ef93bb35267', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:07:02', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/6401', 'DELETE', '', '37', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('46c0f3a902eed42a051530594b9a732e', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:07:20', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/6102', 'DELETE', '', '146', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('46f433491d83208cf8bb693d7be8839e', '0', '更新菜单', 'cesweb', 'admin', '2020-08-13 15:35:23', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '133', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('471751b5802413e844c0f5b926cf3dc3', '9', 'admin用户登录', 'cesweb', 'admin', '2020-11-30 09:53:26', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', '1');
INSERT INTO `sys_log` VALUES ('479323cdc89633b0ef412e2aafa62921', '0', '更新菜单', 'cesweb', 'admin', '2020-08-13 15:14:54', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '143', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('47c3b00a8a9de99d0330c702d2ed88c8', '0', '更新菜单', 'cesweb', 'admin', '2020-09-23 08:58:34', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '11', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('486c84e85e0c2ccebea08d3fca991da8', '0', '更新菜单', 'cesweb', 'admin', '2020-09-23 08:49:20', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '14', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('48f3e3181b1052f6ce89ed7532e3f0b4', '0', 'admin用户登录', 'cesweb', 'admin', '2020-07-17 04:53:25', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('4911da2d62563da747dc4246e9c0a504', '0', '删除文件管理', 'cesweb', 'admin', '2020-08-18 16:00:45', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/sysfile/18213c9af35c2888fb0012c2787601df', 'DELETE', '', '153', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('4ac47f8327828226fcb9a22d5086cf08', '9', 'admin用户登录', 'admin', 'admin', '2020-07-17 00:37:30', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'GET', 'admin', NULL, '0', 'Bad credentials', '1');
INSERT INTO `sys_log` VALUES ('4afaebfcc23b326989eda3d944385e2b', '0', 'admin用户登录', 'cesweb', 'admin', '2020-11-19 13:37:48', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('4b4f54d0ca74c894ad6c7c0d2e25c353', '0', 'admin用户登录', 'cesweb', 'admin', '2020-12-01 11:20:20', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('4c8c29ca7fa3b51cb0fc493d4d86da64', '0', '删除文件管理', 'cesweb', 'admin', '2020-08-18 17:41:41', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/sysfile/d9a40316b46ad00369f492e0a07cffd0', 'DELETE', '', '370', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('4cb74387306a2303f72018b9fea5ac99', '9', 'admin用户登录', 'cesweb', 'admin', '2020-11-30 09:56:36', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', '1');
INSERT INTO `sys_log` VALUES ('4e2d541e334c1594e8080d8f354d966b', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:06:26', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/5530', 'DELETE', '', '44', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('4e420c18e84470be2ebad22424d10832', '0', 'admin用户登录', 'cesweb', 'admin', '2020-10-23 08:08:32', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('4f93d49dd6e56376666bdf6cafdb7363', '0', '更新菜单', 'cesweb', 'admin', '2020-09-23 08:54:30', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '51', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('51b0495f53c4adc872260a12d7aea58e', '0', 'admin用户登录', 'cesweb', 'admin', '2020-11-18 10:01:13', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('521f3074953a3af99320b6124ed59dc4', '0', 'admin用户登录', 'cesweb', 'admin', '2020-11-30 11:12:33', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('5247086281066e2ff8e54cf15d2e24a0', '0', 'admin用户登录', 'cesweb', 'admin', '2020-09-01 09:08:42', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('53107adb61e21e27f81c85cf2e8b3d39', '0', 'admin用户登录', 'cesweb', 'admin', '2021-01-15 16:43:30', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('537a3b591ace76032ae90a71c690298c', '0', 'admin用户登录', 'cesweb', 'admin', '2020-08-13 16:04:42', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('53d8cebf7d08cb496b9eb1f4471215da', '9', 'admin用户登录', 'cesweb', 'admin', '2020-11-30 10:03:30', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', '1');
INSERT INTO `sys_log` VALUES ('54918b606183926f86bdae68f6e68cd9', '9', 'admin用户登录', 'cesweb', 'admin', '2020-11-30 10:27:06', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', '1');
INSERT INTO `sys_log` VALUES ('5516f284ac3302ec36a595f23ee604dd', '9', 'cesweb用户登录', 'cesweb', 'cesweb', '2020-07-17 04:49:28', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'cesweb', NULL, '0', 'Bad credentials', '1');
INSERT INTO `sys_log` VALUES ('5522070fc2ee2640d415d3cd4986d838', '9', 'admin用户登录', 'admin', 'admin', '2020-07-17 00:37:22', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'GET', 'admin', NULL, '0', 'Bad credentials', '1');
INSERT INTO `sys_log` VALUES ('553701074360fa20506bdcfb467bf8e0', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:06:54', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/5000', 'DELETE', '', '116', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('554d2e7428b08ca19ddb4524bd629333', '0', '更新菜单', 'cesweb', 'admin', '2020-09-23 08:55:08', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '46', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('5563727c45fea4af334426669d7f794a', '0', 'admin用户登录', 'cesweb', 'admin', '2020-10-09 14:30:12', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('5570f6c3675083f9189d2368c5df4fe3', '0', 'admin用户登录', 'cesweb', 'admin', '2021-01-18 08:45:27', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('55bbde61ee398c031b2e9bebcbd1b391', '0', '更新菜单', 'cesweb', 'admin', '2020-09-23 08:51:55', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '42', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('55e74ac7536deca9080e3bdb9b7a4032', '0', '删除文件管理', 'cesweb', 'admin', '2020-08-18 17:45:26', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/sysfile/a7c327135cb49ec7e2e790cccad22bd4', 'DELETE', '', '368', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('563ebd9ece2dc3ab14e38005cc151996', '0', '更新菜单', 'cesweb', 'admin', '2020-12-15 14:48:40', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/menu', 'PUT', '', '242', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('56508882c26f414ca9b25f9b782d27c8', '0', 'admin用户登录', 'cesweb', 'admin', '2020-09-02 10:17:24', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('56707bfb1bf2f688105656ce8421831a', '0', '删除部门', 'cesweb', 'admin', '2020-10-21 17:12:43', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/dept/4', 'DELETE', '', '52', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('575af56e6e419813e8ed824908914a52', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:08:02', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/6702', 'DELETE', '', '32', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('580c919d3d2cce04fe740a986fccd853', '0', '更新菜单', 'cesweb', 'admin', '2020-11-18 10:59:15', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/menu', 'PUT', '', '14', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('580dde6a4eb5755ba86c41587444b48b', '0', 'admin用户登录', 'cesweb', 'admin', '2020-09-17 08:55:27', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('58b459beded77262db8904f14b7714b0', '0', 'admin用户登录', 'cesweb', 'admin', '2020-08-24 10:32:44', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('596bdcb9583e32f54f45502c63813775', '0', 'admin用户登录', 'cesweb', 'admin', '2020-08-20 10:00:46', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('5a6abb8b6bcfa19a726b6dc31ffa1c14', '0', '更新菜单', 'cesweb', 'admin', '2020-12-15 14:47:12', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/menu', 'PUT', '', '198', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('5b02df04292efd9d5ac8661a16a09036', '0', 'admin用户登录', 'cesweb', 'admin', '2020-08-31 13:24:35', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('5b0bc9d507cab381d5ebf324ccd66452', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:08:09', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/6601', 'DELETE', '', '38', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('5d9b01369902b86a95c8144ef0963548', '0', '更新菜单', 'cesweb', 'admin', '2020-09-23 08:57:44', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '20', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('5e9b3d528406c86c502fc8de220e480e', '0', '修改路由', 'cesweb', 'admin', '2020-08-19 14:58:44', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/route', 'PUT', '', '1526', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('5eabc9508decae4b517222508080096f', '0', 'admin用户登录', 'cesweb', 'admin', '2020-08-28 08:12:40', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('5eb56f7d3743d55478ea15177efcc96e', '0', 'admin用户登录', 'N/A', 'admin', '2020-07-17 00:37:06', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/token/form', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('5efbade7a96ae74e44bcf60f2f3fe198', '0', 'chanlong用户登录', 'cesweb', 'chanlong', '2020-10-23 17:12:26', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'chanlong', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('600b24a4c3c95a58542591dda779f8e7', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:05:49', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/5120', 'DELETE', '', '75', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('60442e8226e2f8ff2b12d4ff0419a02a', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:08:19', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/6100', 'DELETE', '', '27', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('60781d29ad16ddf6219fd129ca89ac4a', '0', 'admin用户登录', 'cesweb', 'admin', '2021-01-18 08:57:09', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('60a8b71ddef0daf5b7060feaa8195825', '0', '新增数据源表', 'cesweb', 'admin', '2021-01-18 09:11:30', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/dsconf', 'POST', '', '10', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('6123d1823525e63ba75d75235b1df523', '0', '更新菜单', 'cesweb', 'admin', '2020-09-23 08:50:02', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '39', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('61cdff8865331e6c8844c2c930103a32', '0', '启动定时任务', 'cesweb', 'admin', '2021-01-18 12:06:15', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/sys-job/start-job/63516258bce7d7b734b256f26132fbec', 'POST', '', '228', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('6323e70c6d94c3515b44c3ed0166aff7', '0', '更新菜单', 'cesweb', 'admin', '2020-08-13 15:14:44', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '139', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('6363255df621f9cfc1e50ede66b6c3e0', '0', 'admin用户登录', 'cesweb', 'admin', '2020-12-02 14:49:42', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('636fa5502ebbc93956e4478aa9e9125e', '0', '删除文件管理', 'cesweb', 'admin', '2020-08-18 17:45:38', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/sysfile/70f3bd9d8eb7f7b7cac64cbe6aafbf67', 'DELETE', '', '359', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('63762affec2910a968872333c54d3e38', '0', 'admin用户登录', 'N/A', 'admin', '2020-07-16 07:32:13', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/token/form', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('639f852b607ba2de235b1808a985e1e3', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:08:18', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/6500', 'DELETE', '', '25', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('64caf71017b5bfe105f303b94e5828ed', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:05:35', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/5000', 'DELETE', '', '138', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('65b1a99927973e7268fe0daa544e7f39', '0', '更新菜单', 'cesweb', 'admin', '2020-08-26 16:31:34', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '261', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('65fb7cc2b85bfd1c0eaf0a8fa27950c3', '0', 'admin用户登录', 'cesweb', 'admin', '2020-11-23 09:22:07', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('6629ee3f4a8ca26f476fac63a69a99dd', '0', 'admin用户登录', 'cesweb', 'admin', '2020-11-18 10:03:45', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('663fa82c162f6bcb933a3aa5f33fd438', '0', '删除文件管理', 'cesweb', 'admin', '2020-08-18 17:41:44', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/sysfile/772720e3cf04478deb6a23b043411500', 'DELETE', '', '613', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('66b35ca593a03045b0bf67ac9bcc49a6', '0', '更新菜单', 'cesweb', 'admin', '2020-09-23 08:54:49', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '25', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('67452f6ff13239864ead164f8295076c', '0', 'admin用户登录', 'cesweb', 'admin', '2020-09-14 15:17:28', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('680d8fba8ef1187ec9990a572349e90b', '0', '更新菜单', 'cesweb', 'admin', '2020-09-23 08:52:10', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '54', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('68e0c2bf2d77bfb063b2fce72bbc4362', '0', 'admin用户登录', 'cesweb', 'admin', '2020-08-17 17:41:06', NULL, '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('69f1152fbf5990613845b75ce9f3b133', '0', '新增菜单', 'cesweb', 'admin', '2020-12-15 14:42:38', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/menu', 'POST', '', '212', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('6aa770b149403a4bf9f932d6880c04c2', '0', '更新菜单', 'cesweb', 'admin', '2020-09-23 08:56:37', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '13', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('6aac0137f9bbdae0e2872254cf7e84a7', '9', 'admin用户登录', 'cesweb', 'admin', '2020-11-30 10:21:41', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', '1');
INSERT INTO `sys_log` VALUES ('6af31b80e21f577b1918b54e49c860ad', '9', 'admin用户登录', 'cesweb', 'admin', '2020-11-27 17:37:51', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', '1');
INSERT INTO `sys_log` VALUES ('6b0c581cc8c49fce5c0a4cd1a5aba15e', '0', 'admin用户登录', 'cesweb', 'admin', '2020-12-02 09:27:22', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('6b56dcad0c09eb158d6d8ce7668ee28c', '0', 'admin用户登录', 'cesweb', 'admin', '2020-12-08 09:24:34', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('6cd9fd694989837cad43d19e98938a49', '0', '更新菜单', 'cesweb', 'admin', '2020-09-23 08:58:47', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '26', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('6e4287263a211fddf3e115e4582cb42a', '9', 'admin用户登录', 'cesweb', 'admin', '2020-12-15 16:16:10', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', '1');
INSERT INTO `sys_log` VALUES ('6e8e18c28d7c8a7277b1f23c047850bc', '0', '更新菜单', 'cesweb', 'admin', '2020-09-23 08:58:08', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '13', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('6ecc8826744067e988b061248b2a0e28', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:06:02', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/5320', 'DELETE', '', '40', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('6edaf124afaab71c7a0b398a60efda59', '9', 'null用户登录', 'null', 'null', '2020-08-21 15:17:57', NULL, '127.0.0.1', 'Java/1.8.0_251', '/oauth/check_token', 'POST', 'null', NULL, '0', 'Bad credentials', '1');
INSERT INTO `sys_log` VALUES ('70433d551e8d559c27fcf7f16b3ba1f0', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:05:42', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/5100', 'DELETE', '', '26', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('70626a695c471c483890a2ba74db570a', '9', 'null用户登录', 'null', 'null', '2020-08-21 15:17:13', NULL, '127.0.0.1', 'Java/1.8.0_251', '/oauth/check_token', 'POST', 'null', NULL, '0', 'Bad credentials', '1');
INSERT INTO `sys_log` VALUES ('7187722ed41a229d5bc0a88ca2239e09', '0', '更新菜单', 'cesweb', 'admin', '2020-11-19 11:55:24', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/menu', 'PUT', '', '12', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('719a73fd990a07e939d61ae99c32d788', '0', 'admin用户登录', 'cesweb', 'admin', '2020-08-24 11:11:22', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:79.0) Gecko/20100101 Firefox/79.0', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('73616c31c949940a8dc4bd921cd265bd', '0', 'admin用户登录', 'cesweb', 'admin', '2020-12-02 09:27:05', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('73a1c2b3e1f7ab75121bef4b082776b7', '0', 'admin用户登录', 'cesweb', 'admin', '2020-09-11 09:22:23', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('73e751db3ef262d23965242905676ba5', '0', 'admin用户登录', 'cesweb', 'admin', '2020-10-21 09:01:22', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('73e919e4860e6f0e978ab8ebc4856d89', '0', '更新菜单', 'cesweb', 'admin', '2020-11-18 11:00:11', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/menu', 'PUT', '', '13', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('74df3e12e7164f86bf0bc1801ce63b30', '0', '删除文件管理', 'cesweb', 'admin', '2020-08-18 17:53:14', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/sysfile/507b1ce1be7226bf4dc837ba32cd57b5', 'DELETE', '', '356', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('7585d0ff2e5c98ec5cda2573cd336d80', '0', '更新用户信息', 'cesweb', 'admin', '2020-10-23 17:04:34', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/user', 'PUT', '', '221', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('75acf361cd28d8f897a4b61402073200', '0', 'admin用户登录', 'N/A', 'admin', '2020-07-16 02:29:10', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/token/form', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('75b01ac088be9ae4f41121c898cbe31f', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:07:53', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/6303', 'DELETE', '', '40', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('75cb0fcfdd16d4cc2fd0df3a419ee541', '0', '更新菜单', 'cesweb', 'admin', '2020-09-23 08:52:02', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '12', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('779ec86a54a3f830aac9e850cbd1b13d', '0', 'admin用户登录', 'cesweb', 'admin', '2021-01-18 10:32:13', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('78005da28beebfbacef60631cc5b1a05', '0', 'chanlong用户登录', 'cesweb', 'chanlong', '2020-10-23 17:07:03', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'chanlong', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('78d0dc85e8b85711df3447a5633402df', '0', '修改角色', 'cesweb', 'admin', '2020-10-23 17:11:23', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/role', 'PUT', '', '23', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('7989cf0375c65b474b63ec145161d03e', '0', '更新菜单', 'cesweb', 'admin', '2020-09-23 08:56:44', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '59', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('7aa6a3256eca9cd4bd1dca0398c2c086', '0', 'admin用户登录', 'cesweb', 'admin', '2020-12-15 16:21:28', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('7ac7ceaa694f482fb49fd3c42afb5aba', '0', 'admin用户登录', 'cesweb', 'admin', '2020-08-13 09:13:52', NULL, '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('7b996380767281cf1bcf69bb221c66f9', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:06:50', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/5200', 'DELETE', '', '76', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('7bc3859e002cd66c345359b413103998', '0', '更新菜单', 'cesweb', 'admin', '2020-11-18 10:59:42', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/menu', 'PUT', '', '21', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('7bf3ed62b4a845365b4defcd4957663f', '9', 'null用户登录', 'null', 'null', '2020-08-21 15:23:48', NULL, '127.0.0.1', 'Java/1.8.0_251', '/error', 'POST', 'null', NULL, '0', 'Bad credentials', '1');
INSERT INTO `sys_log` VALUES ('7c01de6d517489d95796912aea63e730', '0', 'admin用户登录', 'cesweb', 'admin', '2020-12-18 15:15:37', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('7c742c3a2b38c3f2e5ddac9ff9ec04f5', '9', 'null用户登录', 'null', 'null', '2020-08-21 15:18:03', NULL, '127.0.0.1', 'Java/1.8.0_251', '/oauth/check_token', 'POST', 'null', NULL, '0', 'Bad credentials', '1');
INSERT INTO `sys_log` VALUES ('7caff7d05ff71884f5b3b5d1e5782bad', '0', '更新菜单', 'cesweb', 'admin', '2020-09-23 08:56:29', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '12', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('7cb0490529e8058285b9cd9f463a41e1', '0', '更新角色菜单', 'cesweb', 'admin', '2020-12-15 14:49:30', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/role/menu', 'PUT', '', '6325', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('7cf74f666efd262f3e6b19f4c576ba43', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:07:22', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/6103', 'DELETE', '', '32', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('7d1cf5c53787860a397f39b4010807aa', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:06:47', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/5500', 'DELETE', '', '47', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('7da4e0a58b908d753c4efbe1ddbb33cc', '0', '更新菜单', 'cesweb', 'admin', '2020-11-18 11:00:30', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/menu', 'PUT', '', '20', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('7e427ce83856dfd3edeb5e81fcaea404', '0', 'admin用户登录', 'cesweb', 'admin', '2020-09-04 09:26:01', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('7eae46011195b89f4496dfc163e4170c', '0', '新增菜单', 'cesweb', 'admin', '2020-12-15 14:35:38', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/menu', 'POST', '', '223', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('7f0eabfa540e1284b4d82899e7cc9ab2', '0', 'admin用户登录', 'cesweb', 'admin', '2020-07-22 08:31:10', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('7f169c9beb8c724d4e99565b6d66f25f', '0', '更新用户信息', 'cesweb', 'admin', '2020-10-23 17:04:47', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/user', 'PUT', '', '105', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('800e474dc751fce01b9b904b58f8d73e', '0', '更新菜单', 'cesweb', 'admin', '2020-11-18 11:00:16', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/menu', 'PUT', '', '50', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('8028daabeca9fea8f4e9a123768041bd', '0', '新增数据源表', 'cesweb', 'admin', '2021-01-18 09:14:49', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/dsconf', 'POST', '', '16', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('80b39d3002bbb4c98aa178b43161e85b', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:07:32', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/6200', 'DELETE', '', '41', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('81c986421e7666679707bcc58e1495e9', '9', 'admin用户登录', 'cesweb', 'admin', '2020-07-22 01:46:00', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', '1');
INSERT INTO `sys_log` VALUES ('823d3acb6801db9744d11899ec8e97eb', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:07:29', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/6201', 'DELETE', '', '68', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('82619647c283f85fc8771365a0bd9940', '0', '菜单排序', 'cesweb', 'admin', '2020-07-28 09:17:36', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/sort', 'PUT', '', '13', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('826be0cd46ad19f1f51cef1078eb26fa', '0', 'admin用户登录', 'cesweb', 'admin', '2020-11-30 11:11:49', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('83449b58df7b7ede3e961715f7096f2a', '0', 'admin用户登录', 'cesweb', 'admin', '2020-10-28 15:36:19', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('840c89364c89bbf559c2bc6aa0285004', '0', '更新菜单', 'cesweb', 'admin', '2020-12-15 14:36:47', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/menu', 'PUT', '', '195', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('84a4f7c54f0a1db306ca708c8aacc398', '0', 'admin用户登录', 'cesweb', 'admin', '2020-11-17 15:31:15', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('84c33ec02f97abb9b1c4a4eef4f61a16', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:06:31', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/5000', 'DELETE', '', '34', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('8536f6eab959e906be82c9ba82082db7', '0', '更新菜单', 'cesweb', 'admin', '2020-12-15 14:37:51', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/menu', 'PUT', '', '264', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('85c0df7f686a379cc988d571856ce6c9', '0', '更新菜单', 'cesweb', 'admin', '2020-12-15 14:47:04', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/menu', 'PUT', '', '987', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('860a91e9291462f09c94b1c3896ec764', '0', '删除文件管理', 'cesweb', 'admin', '2020-08-18 17:41:36', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/sysfile/39fc0e670de86effef49bcb247516b43', 'DELETE', '', '395', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('86c3caf017558e40d306977586f2c77e', '0', '更新菜单', 'cesweb', 'admin', '2020-11-18 10:59:51', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/menu', 'PUT', '', '18', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('8726569628d7cdc18b8670c3f770912c', '0', 'admin用户登录', 'cesweb', 'admin', '2020-12-04 16:20:35', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('87b99289108588ed782032a96127baaa', '0', 'admin用户登录', 'cesweb', 'admin', '2020-08-13 12:57:10', NULL, '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('87f30d4f922d9afb6f8328a26d01897e', '0', '删除文件管理', 'cesweb', 'admin', '2020-08-18 16:00:21', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/sysfile/ab9bd7ccef51f64c59e485796608ce80', 'DELETE', '', '1484', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('889d55db670e0997c2e9990f6362d6dc', '0', 'admin用户登录', 'cesweb', 'admin', '2020-07-22 03:24:05', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('88ce49a66261bf0bea3be88760b4a730', '0', 'admin用户登录', 'cesweb', 'admin', '2021-01-18 09:04:05', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('89700e01e89d0996dbe54abcacb43657', '9', 'admin用户登录', 'cesweb', 'admin', '2020-11-27 17:41:39', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', '1');
INSERT INTO `sys_log` VALUES ('8a0837fe47da82882b36de6f94b227c1', '9', 'cesweb用户登录', 'cesweb', 'cesweb', '2020-07-17 03:17:11', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'cesweb', NULL, '0', 'Bad credentials', '1');
INSERT INTO `sys_log` VALUES ('8a81a4f1e92c697ce649176c5cea0201', '0', '更新菜单', 'cesweb', 'admin', '2021-01-18 10:28:59', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/menu', 'PUT', '', '31', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('8b63e1bc2a5f26e1fe5b0c87c8e7582d', '9', 'admin用户登录', 'cesweb', 'admin', '2020-12-15 16:17:34', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', '1');
INSERT INTO `sys_log` VALUES ('8b6d37351fc7043f59e39a687f0d9e07', '0', 'admin用户登录', 'cesweb', 'admin', '2020-12-15 10:40:37', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('8cfa50c4b1982b51ff059260b6bc5cc9', '0', '更新菜单', 'cesweb', 'admin', '2020-09-23 08:58:25', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '11', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('8cfef8a64fdd2a01898d67975502739a', '0', 'admin用户登录', 'cesweb', 'admin', '2020-09-16 14:49:09', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('8d4ea5301d6302b2ad58b21819b3ea06', '9', 'admin用户登录', 'cesweb', 'admin', '2020-11-30 10:33:15', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', '1');
INSERT INTO `sys_log` VALUES ('8de006ba2f965f78c3587b1b3893fda1', '0', '更新角色菜单', 'cesweb', 'admin', '2020-08-13 15:30:35', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/role/menu', 'PUT', '', '6656', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('8e505ecd02b455d4cbcd05a3229bab12', '0', 'admin用户登录', 'cesweb', 'admin', '2020-07-22 01:18:38', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('8ed5f1c23f03dbb87d91663fb01070a8', '0', '删除文件管理', 'cesweb', 'admin', '2020-08-18 17:41:29', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/sysfile/b83e53b61441b1cd68c698dcf0108396', 'DELETE', '', '522', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('8f5eb9d3c01f527dcd86a781c602f163', '0', 'admin用户登录', 'cesweb', 'admin', '2020-09-23 08:11:17', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('8f684746b292ca132928c23b4ef72eaf', '0', '新增数据源表', 'cesweb', 'admin', '2021-01-18 09:10:08', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/dsconf', 'POST', '', '34', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('8ff7e3f4c804502d96cda8106ebe8de8', '9', 'cesweb用户登录', 'cesweb', 'cesweb', '2020-07-17 03:08:09', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'cesweb', NULL, '0', 'Bad credentials', '1');
INSERT INTO `sys_log` VALUES ('910fd57eeb2979f7b615d7cc253b6bfb', '0', '立刻执行定时任务', 'cesweb', 'admin', '2021-01-18 12:07:06', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/sys-job/run-job/63516258bce7d7b734b256f26132fbec', 'POST', '', '67', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('919c9676e5d98cfd72925f426531a3ef', '9', 'null用户登录', 'null', 'null', '2020-08-21 15:17:12', NULL, '127.0.0.1', 'Java/1.8.0_251', '/oauth/check_token', 'POST', 'null', NULL, '0', 'Bad credentials', '1');
INSERT INTO `sys_log` VALUES ('9251f8e79f34eb7dd3b0c90de32e5f47', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:07:17', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/6101', 'DELETE', '', '56', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('92ad7962a0b9d46ad19918bbc72995df', '0', '新增菜单', 'cesweb', 'admin', '2020-08-13 13:36:44', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'POST', '', '135', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('93a628a19587a41e438fb9e03a9cbca5', '0', '更新菜单', 'cesweb', 'admin', '2020-09-23 08:57:51', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '14', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('9444b4e448bcf843e3ba176460cd1058', '0', '编辑部门', 'cesweb', 'admin', '2020-10-21 17:13:36', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/dept', 'PUT', '', '76', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('944763dd424d57342bddcc03f8e295e8', '0', 'admin用户登录', 'cesweb', 'admin', '2020-08-13 09:50:08', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('9453ca541aea66c4b5718eca28de331b', '0', 'admin用户登录', 'cesweb', 'admin', '2020-08-26 16:31:05', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('94d9e80355ec48205110240a2e7f1bf8', '0', '删除文件管理', 'cesweb', 'admin', '2020-08-18 17:45:33', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/sysfile/c357b0bbdefb734b99dc10e74edf4ee5', 'DELETE', '', '374', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('9687550b4b2d2c5d88811ed2e6efc79e', '9', 'admin用户登录', 'cesweb', 'admin', '2020-11-30 10:27:53', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', '1');
INSERT INTO `sys_log` VALUES ('97adbfc58bcf089c4818611399981f20', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:07:42', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/6204', 'DELETE', '', '38', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('97aed6e0007e30e696d8046bcc2e524b', '0', '删除文件管理', 'cesweb', 'admin', '2020-08-18 17:53:11', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/sysfile/ede2278d7f0f718faa812f3299fe34e7', 'DELETE', '', '381', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('989cc651e16eb1a606c44da572b3028f', '9', 'admin用户登录', 'cesweb', 'admin', '2020-11-30 09:54:30', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', '1');
INSERT INTO `sys_log` VALUES ('98a68b3c5231c4a93fd2328a6c4fbb20', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:08:23', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/6300', 'DELETE', '', '72', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('98fc4ede2d80a9f3185133f701a3bae2', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:06:35', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/5100', 'DELETE', '', '34', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('9b5e77d1ed6469c109cca007647ac983', '9', 'admin用户登录', 'cesweb', 'admin', '2020-11-30 10:23:11', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', '1');
INSERT INTO `sys_log` VALUES ('9b8604c0825b49a6a3b2ee093ec7d206', '0', 'admin用户登录', 'N/A', 'admin', '2020-07-16 01:15:32', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/token/form', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('9c20c9f990e639b1999317e317e48991', '0', 'admin用户登录', 'cesweb', 'admin', '2020-07-23 02:24:27', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('9c9f1ab373cd7b0d4ad2cae0b55bdd1a', '0', 'admin用户登录', 'cesweb', 'admin', '2020-07-22 08:19:52', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('9e021bba05fa53d2d1073c75d76788aa', '0', 'admin用户登录', 'cesweb', 'admin', '2020-07-22 08:19:17', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('9f5e2151f43769b9f073da008397b1e3', '9', 'null用户登录', 'null', 'null', '2020-08-21 15:17:59', NULL, '127.0.0.1', 'Java/1.8.0_251', '/oauth/check_token', 'POST', 'null', NULL, '0', 'Bad credentials', '1');
INSERT INTO `sys_log` VALUES ('9f93770a8c408b8f687c2932ded46730', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:05:46', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/5110', 'DELETE', '', '146', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('a004ac69e5031bc4ccb69f74591b19f8', '0', 'admin用户登录', 'N/A', 'admin', '2020-07-16 08:02:05', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/token/form', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('a07006065560fd01a8509899d9bef671', '0', '更新菜单', 'cesweb', 'admin', '2020-08-13 15:34:39', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '136', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('a0a66c70d29a746073a230091d1572d8', '0', '删除部门', 'cesweb', 'admin', '2020-10-21 17:12:47', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/dept/3', 'DELETE', '', '24', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('a6008d3fd39b310336b243bb41749ef2', '0', '立刻执行定时任务', 'cesweb', 'admin', '2021-01-18 12:13:54', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/sys-job/run-job/63516258bce7d7b734b256f26132fbec', 'POST', '', '43', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('a650848c4c2a70d8e7c31d00d4d5eb99', '0', 'admin用户登录', 'N/A', 'admin', '2020-07-16 08:10:58', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/token/form', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('a802736d47c71e12544468341277576c', '0', '新增菜单', 'cesweb', 'admin', '2020-12-18 16:58:57', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/menu', 'POST', '', '37', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('a853024c7551c906855b5fcab6d8faf3', '0', '新增菜单', 'cesweb', 'admin', '2020-08-13 15:14:06', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'POST', '', '586', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('a86123ee60b3d870a6dc82db770fa747', '0', 'admin用户登录', 'cesweb', 'admin', '2020-09-24 08:23:56', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('a87eeaffb31b8573e11556504ab9b7cf', '0', '修改路由', 'cesweb', 'admin', '2020-08-19 09:31:49', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/route', 'PUT', '', '2918', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('a964aef9623b892018e2df5c6644f729', '0', 'admin用户登录', 'N/A', 'admin', '2020-07-16 01:07:03', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/token/form', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('aa74f8985445ab2af23d472ca64f7f7e', '0', '更新菜单', 'cesweb', 'admin', '2020-09-23 08:51:48', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '21', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('aa9b281e55d8eb0a2f947f1fc6a5daa6', '0', 'admin用户登录', 'cesweb', 'admin', '2020-08-13 15:21:19', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('aad6277ded1d50774e0bbc50427a3c91', '0', 'admin用户登录', 'cesweb', 'admin', '2020-08-18 09:10:00', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('ab52037e9f0766f7ce03d6d168320d8f', '0', 'admin用户登录', 'cesweb', 'admin', '2020-08-21 15:44:51', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('ab69ea365ba458f2d1d13296b0a2dfd4', '9', 'cesweb用户登录', 'cesweb', 'cesweb', '2020-07-17 04:46:51', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'cesweb', NULL, '0', 'Bad credentials', '1');
INSERT INTO `sys_log` VALUES ('acacc1832cea1e8f2929dd236b746b94', '0', 'admin用户登录', 'cesweb', 'admin', '2020-10-14 10:21:48', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('acc479800e15ffea98b461bb0ff06a6f', '0', '更新菜单', 'cesweb', 'admin', '2020-09-23 08:48:55', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '35', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('ad26ca5fcf32844544a8c1aa47381b34', '0', 'admin用户登录', 'cesweb', 'admin', '2020-08-21 16:20:26', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('add352bb1393b0a4b80e5cf8d167a66b', '9', 'cesweb用户登录', 'cesweb', 'cesweb', '2020-07-17 04:46:38', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/error', 'POST', 'cesweb', NULL, '0', 'Bad credentials', '1');
INSERT INTO `sys_log` VALUES ('ae0e152627bb2792bb80e5b45bc26452', '0', '更新菜单', 'cesweb', 'admin', '2020-09-23 08:54:43', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '17', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('ae9f208b13294728aa0f5197f1d4c898', '0', 'admin用户登录', 'cesweb', 'admin', '2020-08-13 16:39:42', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('af36ea7f9c0902953641f939480f146a', '0', 'admin用户登录', 'cesweb', 'admin', '2020-12-04 13:33:30', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('af6038f719d515c0f6190720cb5d17ba', '0', 'admin用户登录', 'cesweb', 'admin', '2020-11-19 09:39:36', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('af9e0d34943aa336f1d0bd29445fee72', '0', 'admin用户登录', 'N/A', 'admin', '2020-07-16 01:39:54', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/token/form', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('b0eb0eeaa7a053a0ab57f8a79fcc67fd', '0', 'admin用户登录', 'cesweb', 'admin', '2020-08-13 09:53:00', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('b11d2cc6e85f2753f6ff9aa5bbecbaff', '0', '更新菜单', 'cesweb', 'admin', '2020-12-15 14:43:51', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/menu', 'PUT', '', '172', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('b14bb2a503b3d69d9b43f4c9bd421afc', '0', '删除部门', 'cesweb', 'admin', '2020-10-21 17:13:01', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/dept/8', 'DELETE', '', '23', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('b15098782ffadb91400ea2172a894e15', '0', 'admin用户登录', 'cesweb', 'admin', '2020-11-27 17:42:20', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('b18ccb5c61a33440531738b968bd46ab', '0', '更新菜单', 'cesweb', 'admin', '2020-08-13 15:14:33', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '296', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('b22f804a2ef55fdc147b3d16727b0d6d', '0', 'admin用户登录', 'cesweb', 'admin', '2020-07-17 05:48:42', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('b29230d1b9cb88fcf7ca9bcd48e83a0d', '0', '更新菜单', 'cesweb', 'admin', '2020-08-13 15:33:35', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '137', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('b293e4a650b944d5653dfbd26bd3e4ee', '0', '更新菜单', 'cesweb', 'admin', '2020-12-15 14:43:44', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/menu', 'PUT', '', '230', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('b2dd8dceaa23f9cf1b6c63a28c4caa2f', '0', 'admin用户登录', 'cesweb', 'admin', '2020-08-21 15:43:32', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('b2f39d2d149df851873bedaafe8699b8', '0', '更新菜单', 'cesweb', 'admin', '2020-08-13 15:29:52', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '141', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('b302efdd27c5e073cf31a108e9edefb4', '0', 'admin用户登录', 'cesweb', 'admin', '2020-07-22 08:27:01', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('b37a5da2d6d05d987eb3a73d91e76c2c', '0', '更新菜单', 'cesweb', 'admin', '2020-08-13 15:35:53', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '136', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('b3d2328961b54a96477948ec7f80d9a7', '0', '更新菜单', 'cesweb', 'admin', '2020-12-15 14:43:04', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/menu', 'PUT', '', '250', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('b3e47d5e7c22c22b9b5a460e941ea96f', '9', 'admin用户登录', 'cesweb', 'admin', '2020-11-30 10:16:22', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', '1');
INSERT INTO `sys_log` VALUES ('b5c9b674c5616b8607d67f90233e1a63', '9', 'admin用户登录', 'cesweb', 'admin', '2020-11-30 09:52:19', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', '1');
INSERT INTO `sys_log` VALUES ('b63f91aaf93885dbac13dc5201057c14', '0', '更新菜单', 'cesweb', 'admin', '2020-08-13 15:15:20', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '136', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('b651c33e38ca2ded22962835a72bb87b', '0', 'admin用户登录', 'cesweb', 'admin', '2020-07-22 01:53:38', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('b6c3aac4c88969b012b0e087e9ce0245', '0', 'admin用户登录', 'cesweb', 'admin', '2020-08-20 14:55:17', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('b6cf560fc03e7c15e2e9b75b1dcd8fd1', '0', 'admin用户登录', 'cesweb', 'admin', '2020-12-01 10:17:58', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('b6e470642e3b8028cab2e241abf6d19d', '0', 'admin用户登录', 'cesweb', 'admin', '2020-09-15 11:45:09', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('b70253f7270901a54c22b1d4e97644d2', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:06:42', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/5400', 'DELETE', '', '42', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('b73af27c528d0ee2879a7883e777bfa5', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:06:20', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/5510', 'DELETE', '', '98', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('b7b4420d84cadb02a7347b35e1535124', '0', '更新菜单', 'cesweb', 'admin', '2020-08-13 15:29:37', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '186', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('b817d028fd788ba84fae17c734f0090a', '0', 'admin用户登录', 'cesweb', 'admin', '2021-01-18 11:09:08', NULL, '114.88.111.236', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('b84d3292f27d77654767ccfae732558c', '0', 'admin用户登录', 'cesweb', 'admin', '2020-09-25 08:23:02', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('b953212f83d3a14156ecaa9f7deca353', '9', 'admin用户登录', 'cesweb', 'admin', '2020-11-30 10:33:59', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', '1');
INSERT INTO `sys_log` VALUES ('ba163d512a67dbdb7c0a88db92d5ec58', '0', 'admin用户登录', 'cesweb', 'admin', '2020-12-02 15:23:09', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('ba46d73f0855e5e6074af23ba6067c7f', '0', 'admin用户登录', 'cesweb', 'admin', '2020-10-28 13:50:05', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('bca9b8b69ddeaeb3861f8d3e74b41684', '0', '更新菜单', 'cesweb', 'admin', '2020-12-15 14:48:34', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/menu', 'PUT', '', '202', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('bcbf2ac72a796212c19de387fbc1330a', '0', '添加部门', 'cesweb', 'admin', '2020-10-21 17:14:39', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/dept', 'POST', '', '130', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('bdadaeb6e66d24d8826be52c3b2bb524', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:06:10', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/5410', 'DELETE', '', '47', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('bdffca0aec49032f2c14aef87664855d', '0', '更新菜单', 'cesweb', 'admin', '2020-08-13 15:32:02', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '153', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('be6e63159422858651b32ab689ccb115', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:06:12', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/5420', 'DELETE', '', '36', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('bfca20c9a24af66a85dc4ccdc1bfd768', '0', 'admin用户登录', 'cesweb', 'admin', '2020-11-30 14:09:33', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('c0953e4eed76482e75bffe28d0a5b259', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:08:24', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/6700', 'DELETE', '', '120', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('c0e493e2faf0d9ec6a0f3be1107e215f', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:06:05', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/5330', 'DELETE', '', '114', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('c16015358cc3f736b4d4aefa60c57cdc', '0', '更新菜单', 'cesweb', 'admin', '2020-12-15 14:46:46', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/menu', 'PUT', '', '206', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('c251de31cc1bdab91e2401ec382349fd', '0', '添加部门', 'cesweb', 'admin', '2020-10-23 09:38:36', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/dept', 'POST', '', '178', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('c2ba1d72db8e255e446f7df16207381a', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:07:59', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/6701', 'DELETE', '', '33', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('c2dba2cb83a57d336240dfd9280ad501', '0', 'admin用户登录', 'cesweb', 'admin', '2020-08-14 10:29:42', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('c2fa72348e754e68175abc339eaf0239', '0', 'admin用户登录', 'cesweb', 'admin', '2020-11-19 13:38:55', NULL, '116.228.54.50', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('c3e0c8fa8cef83c3368676ffaff23e16', '0', '删除文件管理', 'cesweb', 'admin', '2020-08-18 17:53:17', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/sysfile/e271f2406339bfce8a8da26a687623f9', 'DELETE', '', '359', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('c40f5e19f77249d78adaf70f2fe55a13', '0', '更新菜单', 'cesweb', 'admin', '2020-09-23 08:57:37', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '14', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('c4632af4c20a9509509bf2b8c22b8833', '0', '删除文件管理', 'cesweb', 'admin', '2020-08-18 17:45:31', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/sysfile/780dfbb3df32e56442f3e2140ec27ed1', 'DELETE', '', '500', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('c48bf7a6d5315e2a7530959fbdbb0e40', '9', 'cesweb用户登录', 'cesweb', 'cesweb', '2020-07-17 03:01:15', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/error', 'POST', 'cesweb', NULL, '0', 'Bad credentials', '1');
INSERT INTO `sys_log` VALUES ('c499ca23ed8d4a8dd988a6f2faf9a4b6', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:07:36', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/6202', 'DELETE', '', '35', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('c4e60d998fe7a7805557a909172bba31', '0', '更新菜单', 'cesweb', 'admin', '2020-09-23 08:56:54', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '33', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('c5b271ad9b1c5c468d488d9bd63e9aeb', '9', 'null用户登录', 'null', 'null', '2020-08-21 15:23:22', NULL, '127.0.0.1', 'Java/1.8.0_251', '/oauth/check_token', 'POST', 'null', NULL, '0', 'Bad credentials', '1');
INSERT INTO `sys_log` VALUES ('c62dbcb00918eb384d9e3c21f59da480', '0', 'admin用户登录', 'cesweb', 'admin', '2020-12-15 14:14:26', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('c65f7da65b4dbe76fa5483fad73dad52', '0', 'admin用户登录', 'cesweb', 'admin', '2020-11-30 09:20:47', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('c70b1f2489c6b25dad73ac7ff3577562', '0', 'admin用户登录', 'cesweb', 'admin', '2020-08-20 12:40:33', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('c83e7929e38dd721b9633a1d919767cd', '9', 'admin用户登录', 'cesweb', 'admin', '2020-11-30 10:09:57', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', '1');
INSERT INTO `sys_log` VALUES ('c9510cfa35c123b48b3f1177bf09a311', '0', '更新菜单', 'cesweb', 'admin', '2020-12-15 14:48:03', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/menu', 'PUT', '', '254', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('c996cedbc77c475078035d8e0be284a3', '0', '更新菜单', 'cesweb', 'admin', '2020-09-23 08:57:15', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '17', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('cae4a70dec6f83ff80da9d9761bb1a79', '0', 'admin用户登录', 'cesweb', 'admin', '2020-08-20 14:52:25', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('cd7306bc9650bb3eae3245c14c8cdc36', '9', 'admin用户登录', 'cesweb', 'admin', '2020-11-30 10:22:16', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', '1');
INSERT INTO `sys_log` VALUES ('ce72309b6fe9712528897f27efc2c1fc', '9', 'admin用户登录', 'admin', 'admin', '2020-07-17 02:38:02', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/error', 'GET', 'admin', NULL, '0', 'Bad credentials', '1');
INSERT INTO `sys_log` VALUES ('cf682c6a769aaacff850bc38a915a048', '0', 'admin用户登录', 'cesweb', 'admin', '2020-12-02 14:31:37', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('d05aa7df64566451195aadb814220674', '0', 'admin用户登录', 'cesweb', 'admin', '2020-09-29 11:24:27', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('d08283608e278a745e2f092920670005', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:07:07', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/6403', 'DELETE', '', '82', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('d0a68ea15cea6199d4708f6b6aadb2cc', '9', 'admin用户登录', 'cesweb', 'admin', '2020-11-30 09:50:09', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', '1');
INSERT INTO `sys_log` VALUES ('d0b9eaf0724ca7d68100f3e5cf2b4fd9', '0', 'admin用户登录', 'cesweb', 'admin', '2020-10-12 09:08:38', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('d16c6125938ab1c7998652b00b61ebf1', '0', '菜单排序', 'cesweb', 'admin', '2020-08-13 15:34:45', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/sort', 'PUT', '', '110', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('d1c974908088ef18bd1bc7fe06685a41', '9', 'null用户登录', 'null', 'null', '2020-08-21 15:18:03', NULL, '127.0.0.1', 'Java/1.8.0_251', '/error', 'POST', 'null', NULL, '0', 'Bad credentials', '1');
INSERT INTO `sys_log` VALUES ('d645bdfe4395dac597e9e79e40941326', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:08:21', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/6200', 'DELETE', '', '47', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('d80016772a746952a8dd0ecc72818d90', '9', 'admin用户登录', 'admin', 'admin', '2020-07-16 02:11:22', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/error', 'GET', 'admin', NULL, '0', 'Bad credentials', '1');
INSERT INTO `sys_log` VALUES ('d8896cedf5882d108e6097e7f99f8719', '0', '更新菜单', 'cesweb', 'admin', '2020-12-15 14:43:59', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/menu', 'PUT', '', '173', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('d8db4c70cbc970d840700954e6dc8d5d', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:08:05', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/6703', 'DELETE', '', '38', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('d95b958c042c90e6666270f73cf25806', '0', 'admin用户登录', 'N/A', 'admin', '2020-07-16 02:42:19', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/token/form', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('d96b2b66d77be4608b13895ca569c627', '0', '更新菜单', 'cesweb', 'admin', '2020-09-23 08:55:13', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '23', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('da60ab409a6eb39cd2ae8bbcc50507d2', '0', '删除部门', 'cesweb', 'admin', '2020-10-21 17:13:08', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/dept/2', 'DELETE', '', '44', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('dad2b471ef5991c66ffbd940cff9ad13', '0', 'admin用户登录', 'cesweb', 'admin', '2020-09-08 09:53:26', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('dae5cdb7d17dbed6c200aa50a8fcc10c', '0', 'admin用户登录', 'N/A', 'admin', '2020-07-16 02:11:50', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/token/form', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('db0c2311104c6118ade47cfca6b661d2', '0', '删除文件管理', 'cesweb', 'admin', '2020-08-18 17:41:39', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/sysfile/56f481e66241beb739bc6feb7b7b0a63', 'DELETE', '', '378', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('dbb822ca933f215ad1a9a2fcfdc80054', '0', '删除部门', 'cesweb', 'admin', '2020-10-21 17:13:05', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/dept/7', 'DELETE', '', '36', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('dc18c5e6ec6850fd17b94966ea3c43e3', '0', 'admin用户登录', 'cesweb', 'admin', '2020-11-30 14:07:55', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('dc1b3fd3e91b21d6facd391b8ee0abff', '0', 'admin用户登录', 'cesweb', 'admin', '2020-12-07 09:09:13', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('dcc24f0b672f1f4e96571968e823c929', '0', '菜单排序', 'cesweb', 'admin', '2020-07-29 00:19:01', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/sort', 'PUT', '', '64', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('de000ff1fe772103fd31071f4cd25d63', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:08:28', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/6000', 'DELETE', '', '53', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('dee854dc892b4023935c9ca54078f75f', '0', 'admin用户登录', 'cesweb', 'admin', '2020-11-19 11:10:21', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('e104303cb78834074794ef879b2a5590', '0', '更新菜单', 'cesweb', 'admin', '2020-11-18 11:00:37', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/menu', 'PUT', '', '21', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('e1e81da92bb0970c9094d67cd2452db9', '0', '更新菜单', 'cesweb', 'admin', '2020-09-23 08:58:41', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '14', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('e1e83d038a13bb71e8d0919961fbef1a', '0', 'admin用户登录', 'cesweb', 'admin', '2020-11-20 09:40:27', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('e24610248fd9b6c51c101a4834192864', '0', 'admin用户登录', 'cesweb', 'admin', '2020-09-07 08:07:20', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('e29e051b8bab3ad717fae1d78011e64f', '0', '删除文件管理', 'cesweb', 'admin', '2020-08-18 17:45:40', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/sysfile/263735820ebb2b8dac2a63262167642f', 'DELETE', '', '369', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('e2d083b7a84df0e6f7e7a498b3f24719', '0', 'admin用户登录', 'cesweb', 'admin', '2020-10-13 13:00:27', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('e54cb53c25b980300667fdbc37c81893', '0', 'admin用户登录', 'N/A', 'admin', '2020-07-16 08:08:51', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/token/form', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('e5a71e8f73a2bda4cd0e74ec4eaf0f6f', '0', '更新菜单', 'cesweb', 'admin', '2020-09-23 08:50:54', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '20', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('e6401a1f494e80eb81f99c10542ca3ba', '0', '菜单排序', 'cesweb', 'admin', '2020-07-28 09:19:29', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/sort', 'PUT', '', '10', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('e64a213e0050c2554dfbb13aed43c9c0', '0', 'admin用户登录', 'cesweb', 'admin', '2020-08-25 08:55:26', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('e6f6ce35e3c0207f790c1413ea579f05', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:07:05', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/6402', 'DELETE', '', '53', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('e6fd071077e749bb90ab4269c8496df7', '0', 'admin用户登录', 'cesweb', 'admin', '2020-12-21 09:12:08', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('e865bd242ae89807ef7658458ae6a370', '0', '更新菜单', 'cesweb', 'admin', '2020-12-15 14:46:55', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/menu', 'PUT', '', '201', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('e8a02a8f2f87ef06a4b15a4d96dc07b4', '0', 'admin用户登录', 'cesweb', 'admin', '2020-07-22 01:47:58', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('e9a315590dcd47d64a013ee95e4c07f5', '0', 'admin用户登录', 'cesweb', 'admin', '2020-07-22 23:27:59', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('e9c31d5808b9c4e70c50b9be6b303691', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:05:53', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/5130', 'DELETE', '', '63', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('ea484743ad5ac1224f3331325ebc7a53', '0', 'admin用户登录', 'cesweb', 'admin', '2020-12-02 09:26:19', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('eb3ae73c25ab2f8b73cae3591c6102c3', '0', 'admin用户登录', 'cesweb', 'admin', '2020-08-18 09:09:26', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('ec288d616c3d0ef4b3b66c8e6fb32027', '0', 'admin用户登录', 'cesweb', 'admin', '2020-07-29 00:07:57', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('ec4121f99728fa0ec5ae25288c6a0a0c', '0', '暂停定时任务', 'cesweb', 'admin', '2021-01-18 12:06:35', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/sys-job/shutdown-job/63516258bce7d7b734b256f26132fbec', 'POST', '', '54', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('ec6b6893c6b378bf2d7a37aa44c32cf5', '0', '删除菜单', 'cesweb', 'admin', '2020-09-22 17:06:39', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/5300', 'DELETE', '', '44', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('ecb68b299da178a8a58cdc3d124995bb', '0', '删除文件管理', 'cesweb', 'admin', '2020-08-18 16:00:42', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/sysfile/05d6368b6b579590b01fe83caa52989c', 'DELETE', '', '147', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('ee400c8ca9e1ca7aa15fde0bb1112b1d', '0', 'admin用户登录', 'cesweb', 'admin', '2020-12-21 08:57:23', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('eece28a46fc10035302956486fca575e', '9', 'admin用户登录', 'cesweb', 'admin', '2020-11-30 10:10:33', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', '1');
INSERT INTO `sys_log` VALUES ('eeeab94385d2a00a804f851419b44911', '0', 'admin用户登录', 'N/A', 'admin', '2020-07-16 02:33:53', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/token/form', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('eef5fab64d9378e6d86dbcfa002ea659', '0', 'admin用户登录', 'cesweb', 'admin', '2020-09-30 08:19:58', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('ef2948ae4cb69997bfc7690d3f21d603', '0', 'admin用户登录', 'N/A', 'admin', '2020-07-16 03:10:04', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/token/form', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('ef9abf3b79213ddcc193e5cf65071243', '0', '删除定时任务', 'cesweb', 'admin', '2020-08-20 10:58:38', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/sys-job/c54b917b6d6ecb58e916e8329966192a', 'DELETE', '', '164', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('f0045eb24fdf9874a587650d17672cb9', '0', 'admin用户登录', 'cesweb', 'admin', '2020-07-22 01:08:31', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('f00d13bcadb824c45aae299b57158416', '0', 'admin用户登录', 'cesweb', 'admin', '2020-12-03 08:46:20', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('f0de4ba5f00d117297766ebdce772f1b', '0', '更新菜单', 'cesweb', 'admin', '2020-11-19 11:55:11', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/menu', 'PUT', '', '17', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('f2190ee599ee0e2c2e02d0db56354e5c', '0', '更新菜单', 'cesweb', 'admin', '2020-09-23 08:54:37', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '16', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('f229ec2bdd837c4ab315eea5bdb8fac0', '0', '更新菜单', 'cesweb', 'admin', '2020-09-23 08:51:32', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '83', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('f2a9e9a8211b61872636cae083e2739b', '0', 'admin用户登录', 'cesweb', 'admin', '2020-12-02 08:34:39', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('f37caba797dd26190d001ab44ed52701', '0', '更新菜单', 'cesweb', 'admin', '2020-09-23 08:57:26', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '44', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('f43280cc0a887e8a6879db6ed06c5aed', '0', '删除文件管理', 'cesweb', 'admin', '2020-08-18 17:45:36', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/sysfile/bfa70d915cf786285a73f1413ab6e587', 'DELETE', '', '359', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('f59c14b52a5a5d69e8064f1c6db31e3f', '9', 'admin用户登录', 'cesweb', 'admin', '2020-11-30 10:07:08', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', '1');
INSERT INTO `sys_log` VALUES ('f5dc81e18a2d29375ff36cf4649468a8', '0', 'admin用户登录', 'cesweb', 'admin', '2020-10-23 17:07:47', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('f5e4cd5b791e1558ece40fbb7edafb42', '0', '更新菜单', 'cesweb', 'admin', '2020-09-23 08:55:20', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '25', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('f85a089c4aae38c7bdcc36f0c040c0ec', '0', 'admin用户登录', 'N/A', 'admin', '2020-07-16 01:45:06', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/token/form', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('f9046e19eff1ec7af9812af4977155b6', '9', 'null用户登录', 'null', 'null', '2020-08-21 15:17:59', NULL, '127.0.0.1', 'Java/1.8.0_251', '/oauth/check_token', 'POST', 'null', NULL, '0', 'Bad credentials', '1');
INSERT INTO `sys_log` VALUES ('f9ac65854cb04966bce8b9928305af13', '0', '修改路由', 'cesweb', 'admin', '2020-08-18 18:06:42', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/route', 'PUT', '', '776', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('f9b34d52f4dac70189bc9a83d8007d4a', '0', '删除文件管理', 'cesweb', 'admin', '2020-08-18 17:41:31', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/sysfile/6ef74a028cc1a953e3eb624a66931b66', 'DELETE', '', '359', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('f9c0dc2c6c6fd45729184b45c268bd5c', '0', '菜单排序', 'cesweb', 'admin', '2020-07-28 09:18:57', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/sort', 'PUT', '', '13', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('fa039b7445913f6329ac5a1d24cc0284', '9', 'admin用户登录', 'cesweb', 'admin', '2020-11-30 10:19:35', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', '1');
INSERT INTO `sys_log` VALUES ('fa7108cd78c9450b346832ea5bfad198', '0', 'admin用户登录', 'N/A', 'admin', '2020-07-16 07:21:49', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/token/form', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('fb62dc55f5e1c0540162edfcdf0a43c8', '9', 'admin用户登录', 'cesweb', 'admin', '2020-07-21 01:45:53', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', '1');
INSERT INTO `sys_log` VALUES ('fb8271f34bb3260b75155043d7941bfa', '0', '菜单排序', 'cesweb', 'admin', '2020-07-28 08:35:43', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu/sort', 'PUT', '', '22', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('fc12971eceaf63e5910c77230d1f59ec', '0', 'admin用户登录', 'cesweb', 'admin', '2020-07-17 05:21:16', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('fc13b1347dd91f816e629ed067ed85ff', '0', 'admin用户登录', 'cesweb', 'admin', '2020-10-16 09:22:05', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('fdde854836753eee8e98fde2f2ed4464', '0', 'admin用户登录', 'cesweb', 'admin', '2020-07-28 07:20:24', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('fe9aa82756ab9a63a99c34e836226bff', '0', 'admin用户登录', 'N/A', 'admin', '2020-07-16 02:19:36', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/token/form', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('fedb348ee1fab2f4bd8980e66cfca7d2', '0', '更新菜单', 'cesweb', 'admin', '2020-12-15 14:37:57', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '/menu', 'PUT', '', '175', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('ff2ea69f94ff60801340fc5618ef0797', '0', '更新菜单', 'cesweb', 'admin', '2020-09-23 08:54:55', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/menu', 'PUT', '', '11', '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('ffc88c272d5e092085d671b25e9f4a01', '0', 'admin用户登录', 'cesweb', 'admin', '2020-08-13 16:06:01', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, '1');
INSERT INTO `sys_log` VALUES ('ffe6ed74c5ae1c0f143e2aee0e3c1319', '9', 'admin用户登录', 'admin', 'admin', '2020-07-16 07:04:27', NULL, '10.10.202.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36', '/oauth/token', 'GET', 'admin', NULL, '0', 'Bad credentials', '1');
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` varchar(64) NOT NULL COMMENT '菜单ID',
  `name` varchar(32) DEFAULT NULL,
  `permission` varchar(32) DEFAULT NULL,
  `path` varchar(128) DEFAULT NULL,
  `parent_id` varchar(64) DEFAULT NULL COMMENT '父菜单ID',
  `icon` varchar(32) DEFAULT NULL,
  `sort` int(11) DEFAULT '1' COMMENT '排序值',
  `keep_alive` char(1) DEFAULT '0',
  `type` char(1) DEFAULT '0',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` char(1) DEFAULT '0',
  `tenant_id` int(64) unsigned DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单权限表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES ('01933f8edb53055ce49dcb32421267c6', '数据源管理', NULL, '/sys/tenant/datasource/index', '1500', 'iconfont icon-data-model', 2, '0', '0', '2020-12-18 16:58:57', NULL, '0', 1);
INSERT INTO `sys_menu` VALUES ('1000', '权限管理', NULL, '/sys/authority', 'a00635fcbe68cfebb00e44cf75f2eb9c', 'iconfont icon-audit', 2, '0', '0', '2018-09-28 08:29:53', '2020-08-13 15:18:03', '0', 1);
INSERT INTO `sys_menu` VALUES ('10000', '文件管理', NULL, '/sys/system/file/index', '2000', 'iconfont icon-file', 6, '0', '0', '2019-06-25 12:44:46', '2020-07-23 07:11:02', '0', 1);
INSERT INTO `sys_menu` VALUES ('10001', '删除文件', 'sys_file_del', NULL, '10000', NULL, 1, '0', '1', '2019-06-25 13:41:41', '2020-03-24 08:58:42', '0', 1);
INSERT INTO `sys_menu` VALUES ('10002', '表单管理', '', '/sys/developer/form/index', '10006', 'iconfont icon-form', 4, '0', '0', '2018-01-20 13:17:19', '2020-08-21 13:31:41', '0', 1);
INSERT INTO `sys_menu` VALUES ('10003', '表单新增', 'gen_form_add', NULL, '10002', '1', 0, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:58:45', '0', 1);
INSERT INTO `sys_menu` VALUES ('10004', '表单修改', 'gen_form_edit', NULL, '10002', '1', 1, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:58:46', '0', 1);
INSERT INTO `sys_menu` VALUES ('10005', '表单删除', 'gen_form_del', NULL, '10002', '1', 2, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:58:47', '0', 1);
INSERT INTO `sys_menu` VALUES ('10006', '开发平台', NULL, '/sys/developer', 'a00635fcbe68cfebb00e44cf75f2eb9c', 'iconfont icon-develop', 9, '0', '0', '2019-08-12 09:35:16', '2020-08-21 13:31:21', '0', 1);
INSERT INTO `sys_menu` VALUES ('10007', '数据源管理', NULL, '/sys/developer/dbsource/index', '10006', 'iconfont icon-datasource', 2, '0', '0', '2019-08-12 09:42:11', '2020-08-21 13:32:36', '0', 1);
INSERT INTO `sys_menu` VALUES ('10008', '表单设计', NULL, '/sys/developer/design/index', '10006', 'iconfont icon-design', 5, '0', '0', '2019-08-16 10:08:56', '2020-08-21 14:16:40', '0', 1);
INSERT INTO `sys_menu` VALUES ('1100', '用户管理', NULL, '/sys/authority/user/index', '1000', 'iconfont icon-user', 2, '0', '0', '2017-11-02 22:24:37', '2020-07-23 07:09:51', '0', 1);
INSERT INTO `sys_menu` VALUES ('1101', '用户新增', 'sys_user_add', NULL, '1100', NULL, NULL, '0', '1', '2017-11-08 09:52:09', '2020-03-24 08:56:34', '0', 1);
INSERT INTO `sys_menu` VALUES ('1102', '用户修改', 'sys_user_edit', NULL, '1100', NULL, NULL, '0', '1', '2017-11-08 09:52:48', '2020-03-24 08:56:35', '0', 1);
INSERT INTO `sys_menu` VALUES ('1103', '用户删除', 'sys_user_del', NULL, '1100', NULL, NULL, '0', '1', '2017-11-08 09:54:01', '2020-03-24 08:56:37', '0', 1);
INSERT INTO `sys_menu` VALUES ('1200', '菜单管理', NULL, '/sys/authority/menu/index', '1000', 'iconfont icon-menu', 0, '0', '0', '2017-11-08 09:57:27', '2020-07-23 07:10:00', '0', 1);
INSERT INTO `sys_menu` VALUES ('1201', '菜单新增', 'sys_menu_add', NULL, '1200', NULL, NULL, '0', '1', '2017-11-08 10:15:53', '2020-03-24 08:56:39', '0', 1);
INSERT INTO `sys_menu` VALUES ('1202', '菜单修改', 'sys_menu_edit', NULL, '1200', NULL, NULL, '0', '1', '2017-11-08 10:16:23', '2020-03-24 08:56:40', '0', 1);
INSERT INTO `sys_menu` VALUES ('1203', '菜单删除', 'sys_menu_del', NULL, '1200', NULL, NULL, '0', '1', '2017-11-08 10:16:43', '2020-03-24 08:56:41', '0', 1);
INSERT INTO `sys_menu` VALUES ('1300', '角色管理', NULL, '/sys/authority/role/index', '1000', 'iconfont icon-role', 3, '0', '0', '2017-11-08 10:13:37', '2020-07-23 07:10:07', '0', 1);
INSERT INTO `sys_menu` VALUES ('1301', '角色新增', 'sys_role_add', NULL, '1300', NULL, NULL, '0', '1', '2017-11-08 10:14:18', '2020-03-24 08:56:43', '0', 1);
INSERT INTO `sys_menu` VALUES ('1302', '角色修改', 'sys_role_edit', NULL, '1300', NULL, NULL, '0', '1', '2017-11-08 10:14:41', '2020-03-24 08:56:43', '0', 1);
INSERT INTO `sys_menu` VALUES ('1303', '角色删除', 'sys_role_del', NULL, '1300', NULL, NULL, '0', '1', '2017-11-08 10:14:59', '2020-03-24 08:56:45', '0', 1);
INSERT INTO `sys_menu` VALUES ('1304', '分配权限', 'sys_role_perm', NULL, '1300', NULL, NULL, '0', '1', '2018-04-20 07:22:55', '2020-03-24 08:56:46', '0', 1);
INSERT INTO `sys_menu` VALUES ('1400', '部门管理', NULL, '/sys/authority/dept/index', '1000', 'iconfont icon-dept', 1, '0', '0', '2018-01-20 13:17:19', '2020-07-23 07:10:16', '0', 1);
INSERT INTO `sys_menu` VALUES ('1401', '部门新增', 'sys_dept_add', NULL, '1400', NULL, NULL, '0', '1', '2018-01-20 14:56:16', '2020-03-24 08:56:48', '0', 1);
INSERT INTO `sys_menu` VALUES ('1402', '部门修改', 'sys_dept_edit', NULL, '1400', NULL, NULL, '0', '1', '2018-01-20 14:56:59', '2020-03-24 08:56:48', '0', 1);
INSERT INTO `sys_menu` VALUES ('1403', '部门删除', 'sys_dept_del', NULL, '1400', NULL, NULL, '0', '1', '2018-01-20 14:57:28', '2020-03-24 08:56:51', '0', 1);
INSERT INTO `sys_menu` VALUES ('1500', '租户管理', '', '/sys/tenant', 'a00635fcbe68cfebb00e44cf75f2eb9c', 'iconfont icon-tenant', 1, '0', '0', '2018-01-20 13:17:19', '2020-07-23 07:10:25', '0', 1);
INSERT INTO `sys_menu` VALUES ('1501', '租户新增', 'admin_tenant_add', NULL, 'a8de49afd940245925c8c00866bb6056', '1', 0, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:56:52', '0', 1);
INSERT INTO `sys_menu` VALUES ('1502', '租户修改', 'admin_tenant_edit', NULL, 'a8de49afd940245925c8c00866bb6056', '1', 1, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:56:53', '0', 1);
INSERT INTO `sys_menu` VALUES ('1503', '租户删除', 'admin_tenant_del', NULL, 'a8de49afd940245925c8c00866bb6056', '1', 2, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:56:54', '0', 1);
INSERT INTO `sys_menu` VALUES ('2000', '系统管理', NULL, '/sys/system', 'a00635fcbe68cfebb00e44cf75f2eb9c', 'iconfont icon-system', 3, '0', '0', '2017-11-07 20:56:00', '2020-08-13 15:19:47', '0', 1);
INSERT INTO `sys_menu` VALUES ('2100', '日志管理', NULL, '/sys/system/log/index', '2000', 'iconfont icon-logger', 5, '0', '0', '2017-11-20 14:06:22', '2020-07-23 07:10:33', '0', 1);
INSERT INTO `sys_menu` VALUES ('2101', '日志删除', 'sys_log_del', NULL, '2100', NULL, NULL, '0', '1', '2017-11-20 20:37:37', '2020-03-24 08:56:58', '0', 1);
INSERT INTO `sys_menu` VALUES ('2200', '字典管理', NULL, '/sys/system/dict/index', '2000', 'iconfont icon-dict', 6, '0', '0', '2017-11-29 11:30:52', '2020-07-23 07:10:37', '0', 1);
INSERT INTO `sys_menu` VALUES ('2201', '字典删除', 'sys_dict_del', NULL, '2200', NULL, NULL, '0', '1', '2017-11-29 11:30:11', '2020-03-24 08:56:59', '0', 1);
INSERT INTO `sys_menu` VALUES ('2202', '字典新增', 'sys_dict_add', NULL, '2200', NULL, NULL, '0', '1', '2018-05-11 22:34:55', '2020-03-24 08:57:01', '0', 1);
INSERT INTO `sys_menu` VALUES ('2203', '字典修改', 'sys_dict_edit', NULL, '2200', NULL, NULL, '0', '1', '2018-05-11 22:36:03', '2020-03-24 08:57:09', '0', 1);
INSERT INTO `sys_menu` VALUES ('2210', '参数管理', NULL, '/sys/system/param/index', '2000', 'iconfont icon-config', 7, '1', '0', '2019-04-29 22:16:50', '2020-07-23 07:10:42', '0', 1);
INSERT INTO `sys_menu` VALUES ('2211', '参数新增', 'admin_syspublicparam_add', NULL, '2210', NULL, 1, '0', '1', '2019-04-29 22:17:36', '2020-03-24 08:57:11', '0', 1);
INSERT INTO `sys_menu` VALUES ('2212', '参数删除', 'admin_syspublicparam_del', NULL, '2210', NULL, 1, '0', '1', '2019-04-29 22:17:55', '2020-03-24 08:57:12', '0', 1);
INSERT INTO `sys_menu` VALUES ('2213', '参数编辑', 'admin_syspublicparam_edit', NULL, '2210', NULL, 1, '0', '1', '2019-04-29 22:18:14', '2020-03-24 08:57:13', '0', 1);
INSERT INTO `sys_menu` VALUES ('2300', '代码生成', '', '/sys/developer/codegen/index', '10006', 'iconfont icon-codegen', 3, '0', '0', '2018-01-20 13:17:19', '2020-08-21 14:14:05', '0', 1);
INSERT INTO `sys_menu` VALUES ('2400', '终端管理', '', '/sys/system/client/index', '2000', 'iconfont icon-terminal', 9, '0', '0', '2018-01-20 13:17:19', '2020-07-23 07:10:47', '0', 1);
INSERT INTO `sys_menu` VALUES ('2401', '客户端新增', 'sys_client_add', NULL, '2400', '1', NULL, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:57:16', '0', 1);
INSERT INTO `sys_menu` VALUES ('2402', '客户端修改', 'sys_client_edit', NULL, '2400', NULL, NULL, '0', '1', '2018-05-15 21:37:06', '2020-03-24 08:57:16', '0', 1);
INSERT INTO `sys_menu` VALUES ('2403', '客户端删除', 'sys_client_del', NULL, '2400', NULL, NULL, '0', '1', '2018-05-15 21:39:16', '2020-03-24 08:57:17', '0', 1);
INSERT INTO `sys_menu` VALUES ('2500', '密钥管理', '', '/sys/system/social/index', '2000', 'iconfont icon-key', 10, '0', '0', '2018-01-20 13:17:19', '2020-07-23 07:10:50', '0', 1);
INSERT INTO `sys_menu` VALUES ('2501', '密钥新增', 'sys_social_details_add', NULL, '2500', '1', 0, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:57:19', '0', 1);
INSERT INTO `sys_menu` VALUES ('2502', '密钥修改', 'sys_social_details_edit', NULL, '2500', '1', 1, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:57:19', '0', 1);
INSERT INTO `sys_menu` VALUES ('2503', '密钥删除', 'sys_social_details_del', NULL, '2500', '1', 2, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:57:23', '0', 1);
INSERT INTO `sys_menu` VALUES ('2600', '令牌管理', NULL, '/sys/system/token/index', '2000', 'iconfont icon-token', 11, '0', '0', '2018-09-04 05:58:41', '2020-07-23 07:10:54', '0', 1);
INSERT INTO `sys_menu` VALUES ('2601', '令牌删除', 'sys_token_del', NULL, '2600', NULL, 1, '0', '1', '2018-09-04 05:59:50', '2020-03-24 08:57:24', '0', 1);
INSERT INTO `sys_menu` VALUES ('2700', '动态路由', NULL, '/sys/system/route/index', '2000', 'iconfont icon-route', 12, '0', '0', '2018-09-04 05:58:41', '2020-07-23 07:10:57', '0', 1);
INSERT INTO `sys_menu` VALUES ('2800', '计划任务', '', '/sys/quartz/manage/index', '2000', 'iconfont icon-quartz', 8, '0', '0', '2018-01-20 13:17:19', '2020-08-21 13:28:44', '0', 1);
INSERT INTO `sys_menu` VALUES ('2810', '任务新增', 'job_sys_job_add', NULL, '2800', '1', 0, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:57:26', '0', 1);
INSERT INTO `sys_menu` VALUES ('2820', '任务修改', 'job_sys_job_edit', NULL, '2800', '1', 0, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:57:27', '0', 1);
INSERT INTO `sys_menu` VALUES ('2830', '任务删除', 'job_sys_job_del', NULL, '2800', '1', 0, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:57:28', '0', 1);
INSERT INTO `sys_menu` VALUES ('2840', '任务暂停', 'job_sys_job_shutdown_job', NULL, '2800', '1', 0, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:57:28', '0', 1);
INSERT INTO `sys_menu` VALUES ('2850', '任务开始', 'job_sys_job_start_job', NULL, '2800', '1', 0, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:57:29', '0', 1);
INSERT INTO `sys_menu` VALUES ('2860', '任务刷新', 'job_sys_job_refresh_job', NULL, '2800', '1', 0, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:57:30', '0', 1);
INSERT INTO `sys_menu` VALUES ('2870', '执行任务', 'job_sys_job_run_job', NULL, '2800', '1', 0, '0', '1', '2019-08-08 15:35:18', '2020-03-24 08:57:31', '0', 1);
INSERT INTO `sys_menu` VALUES ('3000', '系统监控', NULL, '/sys/monitor', 'a00635fcbe68cfebb00e44cf75f2eb9c', 'iconfont icon-monitor', 0, '0', '0', '2018-07-27 01:13:21', '2020-08-21 13:24:31', '0', 1);
INSERT INTO `sys_menu` VALUES ('3064c8ad1f223aae292ba462e335ecab', '信息发布', NULL, '/cms', '-1', 'iconfont icon-cms-o', 2, '0', '2', '2020-08-13 15:14:06', '2020-08-13 15:32:02', '0', 1);
INSERT INTO `sys_menu` VALUES ('3100', '服务监控', NULL, 'http://localhost:9999/monitor', '3000', 'iconfont icon-service-monitor', 0, '0', '0', '2018-06-26 10:50:32', '2020-07-22 09:01:51', '0', 1);
INSERT INTO `sys_menu` VALUES ('3110', '缓存监控', NULL, '/sys/monitor/redis/index', '3000', 'iconfont icon-redis', 1, '1', '0', '2019-05-08 23:51:27', '2020-08-20 12:46:31', '0', 1);
INSERT INTO `sys_menu` VALUES ('3200', '接口文档', NULL, 'http://cesweb-cloud-gateway:9999/swagger-ui.html', '10006', 'iconfont icon-swagger', 0, '0', '0', '2018-06-26 10:50:32', '2020-09-07 12:48:55', '0', 1);
INSERT INTO `sys_menu` VALUES ('3300', '事务监控', NULL, '/sys/monitor/trans/index', '3000', 'iconfont icon-transcation-o', 5, '0', '0', '2018-08-19 11:02:39', '2020-08-21 13:24:57', '0', 1);
INSERT INTO `sys_menu` VALUES ('3400', '在线事务', NULL, '/sys/monitor/trans/online', '3000', 'iconfont icon-transcation', 6, '0', '0', '2018-08-19 11:32:04', '2020-08-21 13:25:46', '0', 1);
INSERT INTO `sys_menu` VALUES ('3500', '文档扩展', NULL, 'http://cesweb-cloud-gateway:9999/doc.html', '10006', 'iconfont icon-swagger', 1, '0', '0', '2018-06-26 10:50:32', '2020-07-22 09:04:00', '0', 1);
INSERT INTO `sys_menu` VALUES ('3600', 'Quartz日志', '', '/sys/quartz/manage/joblog/index', '3000', 'iconfont icon-quartz', 8, '0', '0', '2018-01-20 13:17:19', '2020-08-21 13:29:08', '0', 1);
INSERT INTO `sys_menu` VALUES ('3700', '注册配置', NULL, '', '3000', 'icon-line', 10, '0', '0', '2018-01-25 11:08:52', '2020-03-24 08:57:37', '1', 1);
INSERT INTO `sys_menu` VALUES ('4000', '流程管理', NULL, '/sys/workflow', 'a00635fcbe68cfebb00e44cf75f2eb9c', 'iconfont icon-workflow', 4, '0', '0', '2018-09-26 01:38:13', '2020-08-21 13:21:17', '0', 1);
INSERT INTO `sys_menu` VALUES ('4100', '模型管理', NULL, '/sys/workflow/model/index', '4000', 'iconfont icon-flow-model', 1, '0', '0', '2018-09-26 01:39:07', '2020-08-21 13:21:39', '0', 1);
INSERT INTO `sys_menu` VALUES ('4101', '模型管理', 'act_model_manage', NULL, '4100', '1', 0, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:57:41', '0', 1);
INSERT INTO `sys_menu` VALUES ('4200', '流程实例', '/sys/workflow/process/index', '/sys/workflow/process/index', '4000', 'iconfont icon-process', 2, '0', '0', '2018-09-26 06:41:05', '2020-08-21 13:23:02', '0', 1);
INSERT INTO `sys_menu` VALUES ('4201', '流程管理', 'act_process_manage', NULL, '4200', '1', 0, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:57:42', '0', 1);
INSERT INTO `sys_menu` VALUES ('4300', '请假管理', '/sys/workflow/leave/index', '/sys/workflow/leave/index', '4000', 'iconfont icon-role', 3, '0', '0', '2018-01-20 13:17:19', '2020-08-21 13:22:59', '0', 1);
INSERT INTO `sys_menu` VALUES ('4301', '请假新增', 'act_leavebill_add', NULL, '4300', '1', 0, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:57:44', '0', 1);
INSERT INTO `sys_menu` VALUES ('4302', '请假修改', 'act_leavebill_edit', NULL, '4300', '1', 1, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:57:45', '0', 1);
INSERT INTO `sys_menu` VALUES ('4303', '请假删除', 'act_leavebill_del', NULL, '4300', '1', 2, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:57:46', '0', 1);
INSERT INTO `sys_menu` VALUES ('4400', '待办任务', '/sys/workflow/todo/index', '/sys/workflow/todo/index', '4000', 'iconfont icon-todolist', 4, '0', '0', '2018-09-27 09:52:31', '2020-08-21 15:44:21', '0', 1);
INSERT INTO `sys_menu` VALUES ('4401', '流程管理', 'act_task_manage', NULL, '4400', '1', 0, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:57:50', '0', 1);
INSERT INTO `sys_menu` VALUES ('5000', '支付管理', NULL, '/sys/pay', 'a00635fcbe68cfebb00e44cf75f2eb9c', 'icon-pay6zhifu', 4, '0', '0', '2019-05-30 15:28:03', '2020-09-22 17:06:54', '1', 1);
INSERT INTO `sys_menu` VALUES ('5100', '渠道管理', NULL, '/pay/paychannel/index', '5000', 'icon-zhifuqudaoguanli', 1, '0', '0', '2019-05-30 15:32:17', '2020-09-22 17:06:35', '1', 1);
INSERT INTO `sys_menu` VALUES ('5110', '增加渠道', 'pay_paychannel_add', NULL, '5100', NULL, 1, '0', '1', '2019-05-30 15:46:14', '2020-09-22 17:05:46', '1', 1);
INSERT INTO `sys_menu` VALUES ('5120', '编辑渠道', 'pay_paychannel_edit', NULL, '5100', NULL, 1, '0', '1', '2019-05-30 15:46:35', '2020-09-22 17:05:49', '1', 1);
INSERT INTO `sys_menu` VALUES ('5130', '删除渠道', 'pay_paychannel_del', NULL, '5100', NULL, 1, '0', '1', '2019-05-30 15:47:08', '2020-09-22 17:05:53', '1', 1);
INSERT INTO `sys_menu` VALUES ('5200', '收银台', NULL, '/pay/cd/index', '5000', 'icon-shouyintai', 0, '0', '0', '2019-05-30 19:44:00', '2020-09-22 17:06:50', '1', 1);
INSERT INTO `sys_menu` VALUES ('5300', '商品订单', '', '/pay/goods/index', '5000', 'icon-dingdan', 2, '0', '0', '2018-01-20 13:17:19', '2020-09-22 17:06:39', '1', 1);
INSERT INTO `sys_menu` VALUES ('5310', '商品订单新增', 'generator_paygoodsorder_add', NULL, '5300', '1', 0, '0', '1', '2018-05-15 21:35:18', '2020-09-22 17:05:59', '1', 1);
INSERT INTO `sys_menu` VALUES ('5320', '商品订单修改', 'generator_paygoodsorder_edit', NULL, '5300', '1', 1, '0', '1', '2018-05-15 21:35:18', '2020-09-22 17:06:02', '1', 1);
INSERT INTO `sys_menu` VALUES ('5330', '商品订单删除', 'generator_paygoodsorder_del', NULL, '5300', '1', 2, '0', '1', '2018-05-15 21:35:18', '2020-09-22 17:06:05', '1', 1);
INSERT INTO `sys_menu` VALUES ('5400', '支付订单', '', '/pay/orders/index', '5000', 'icon-zhifu', 3, '0', '0', '2018-01-20 13:17:19', '2020-09-22 17:06:42', '1', 1);
INSERT INTO `sys_menu` VALUES ('5410', '支付订单新增', 'generator_paytradeorder_add', NULL, '5400', '1', 0, '0', '1', '2018-05-15 21:35:18', '2020-09-22 17:06:10', '1', 1);
INSERT INTO `sys_menu` VALUES ('5420', '支付订单修改', 'generator_paytradeorder_edit', NULL, '5400', '1', 1, '0', '1', '2018-05-15 21:35:18', '2020-09-22 17:06:12', '1', 1);
INSERT INTO `sys_menu` VALUES ('5430', '支付订单删除', 'generator_paytradeorder_del', NULL, '5400', '1', 2, '0', '1', '2018-05-15 21:35:18', '2020-09-22 17:06:15', '1', 1);
INSERT INTO `sys_menu` VALUES ('5500', '回调记录', '', '/pay/notify/index', '5000', 'icon-huitiao', 4, '0', '0', '2018-01-20 13:17:19', '2020-09-22 17:06:47', '1', 1);
INSERT INTO `sys_menu` VALUES ('5510', '记录新增', 'generator_paynotifyrecord_add', NULL, '5500', '1', 0, '0', '1', '2018-05-15 21:35:18', '2020-09-22 17:06:20', '1', 1);
INSERT INTO `sys_menu` VALUES ('5520', '记录修改', 'generator_paynotifyrecord_edit', NULL, '5500', '1', 1, '0', '1', '2018-05-15 21:35:18', '2020-09-22 17:06:24', '1', 1);
INSERT INTO `sys_menu` VALUES ('5530', '记录删除', 'generator_paynotifyrecord_del', NULL, '5500', '1', 2, '0', '1', '2018-05-15 21:35:18', '2020-09-22 17:06:26', '1', 1);
INSERT INTO `sys_menu` VALUES ('6000', '微信管理', NULL, '/sys/mp', 'a00635fcbe68cfebb00e44cf75f2eb9c', 'icon-gongzhonghao', 5, '0', '0', '2018-09-26 01:38:13', '2020-09-22 17:08:28', '1', 1);
INSERT INTO `sys_menu` VALUES ('6100', '账号管理', '', '/mp/wxaccount/index', '6000', 'icon-weixincaidan', 8, '0', '0', '2018-01-20 13:17:19', '2020-09-22 17:08:19', '1', 1);
INSERT INTO `sys_menu` VALUES ('6101', '公众号新增', 'mp_wxaccount_add', '', '6100', '1', 0, '0', '1', '2018-05-15 21:35:18', '2020-09-22 17:07:17', '1', 1);
INSERT INTO `sys_menu` VALUES ('6102', '公众号修改', 'mp_wxaccount_edit', NULL, '6100', '1', 1, '0', '1', '2018-05-15 21:35:18', '2020-09-22 17:07:20', '1', 1);
INSERT INTO `sys_menu` VALUES ('6103', '公众号删除', 'mp_wxaccount_del', NULL, '6100', '1', 2, '0', '1', '2018-05-15 21:35:18', '2020-09-22 17:07:22', '1', 1);
INSERT INTO `sys_menu` VALUES ('6200', '粉丝管理', '', '/mp/wxaccountfans/index', '6000', 'icon-fensiguanli', 8, '0', '0', '2018-01-20 13:17:19', '2020-09-22 17:08:21', '1', 1);
INSERT INTO `sys_menu` VALUES ('6201', '粉丝新增', 'mp_wxaccountfans_add', NULL, '6200', '1', 0, '0', '1', '2018-05-15 21:35:18', '2020-09-22 17:07:29', '1', 1);
INSERT INTO `sys_menu` VALUES ('6202', '粉丝修改', 'mp_wxaccountfans_edit', NULL, '6200', '1', 1, '0', '1', '2018-05-15 21:35:18', '2020-09-22 17:07:36', '1', 1);
INSERT INTO `sys_menu` VALUES ('6203', '粉丝删除', 'mp_wxaccountfans_del', NULL, '6200', '1', 2, '0', '1', '2018-05-15 21:35:18', '2020-09-22 17:07:39', '1', 1);
INSERT INTO `sys_menu` VALUES ('6204', '粉丝同步', 'mp_wxaccountfans_sync', NULL, '6200', '1', 3, '0', '1', '2018-05-15 21:35:18', '2020-09-22 17:07:42', '1', 1);
INSERT INTO `sys_menu` VALUES ('6300', '消息管理', '', '/mp/wxfansmsg/index', '6000', 'icon-xiaoxiguanli', 8, '0', '0', '2018-01-20 13:17:19', '2020-09-22 17:08:22', '1', 1);
INSERT INTO `sys_menu` VALUES ('6301', '消息新增', 'mp_wxmsg_add', NULL, '6300', '1', 0, '0', '1', '2018-05-15 21:35:18', '2020-09-22 17:07:48', '1', 1);
INSERT INTO `sys_menu` VALUES ('6302', '消息修改', 'mp_wxmsg_edit', NULL, '6300', '1', 1, '0', '1', '2018-05-15 21:35:18', '2020-09-22 17:07:50', '1', 1);
INSERT INTO `sys_menu` VALUES ('6303', '消息删除', 'mp_wxmsg_del', NULL, '6300', '1', 2, '0', '1', '2018-05-15 21:35:18', '2020-09-22 17:07:53', '1', 1);
INSERT INTO `sys_menu` VALUES ('6400', '菜单设置', NULL, '/mp/wxmenu/index', '6000', 'icon-anniu_weixincaidanlianjie', 6, '0', '0', '2019-03-29 15:20:12', '2020-09-22 17:08:16', '1', 1);
INSERT INTO `sys_menu` VALUES ('6401', '保存', 'mp_wxmenu_add', NULL, '6400', NULL, 1, '0', '1', '2019-03-29 15:43:22', '2020-09-22 17:07:02', '1', 1);
INSERT INTO `sys_menu` VALUES ('6402', '发布', 'mp_wxmenu_push', NULL, '6400', NULL, 1, '0', '1', '2019-03-29 15:43:54', '2020-09-22 17:07:05', '1', 1);
INSERT INTO `sys_menu` VALUES ('6403', '删除', 'mp_wxmenu_del', NULL, '6400', NULL, 1, '0', '1', '2019-03-29 15:43:54', '2020-09-22 17:07:07', '1', 1);
INSERT INTO `sys_menu` VALUES ('6500', '运营数据', NULL, '/mp/wxstatistics/index', '6000', 'icon-zhexiantu', 7, '0', '0', '2019-04-14 00:15:35', '2020-09-22 17:08:18', '1', 1);
INSERT INTO `sys_menu` VALUES ('6600', '素材管理', NULL, '/mp/wxmaterial/index', '6000', 'icon-sucaisads', 999, '0', '0', '2020-04-27 15:25:17', '2020-09-22 17:08:26', '1', 1);
INSERT INTO `sys_menu` VALUES ('6601', '素材维护', 'mp_wxmaterial_add', NULL, '6600', NULL, 1, '0', '1', '2019-03-29 15:43:54', '2020-09-22 17:08:09', '1', 1);
INSERT INTO `sys_menu` VALUES ('6602', '素材删除', 'mp_wxmaterial_del', NULL, '6600', NULL, 1, '0', '1', '2019-03-29 15:43:54', '2020-09-22 17:08:12', '1', 1);
INSERT INTO `sys_menu` VALUES ('6700', '自动回复', NULL, '/mp/wxautoreply/index', '6000', 'icon-huifu', 998, '0', '0', '2020-04-27 15:25:17', '2020-09-22 17:08:24', '1', 1);
INSERT INTO `sys_menu` VALUES ('6701', '新增回复', 'mp_wxautoreply_add', NULL, '6700', NULL, 1, '0', '1', '2019-03-29 15:43:54', '2020-09-22 17:07:59', '1', 1);
INSERT INTO `sys_menu` VALUES ('6702', '编辑回复', 'mp_wxautoreply_edit', NULL, '6700', NULL, 1, '0', '1', '2019-03-29 15:43:54', '2020-09-22 17:08:02', '1', 1);
INSERT INTO `sys_menu` VALUES ('6703', '删除回复', 'mp_wxautoreply_del', NULL, '6700', NULL, 1, '0', '1', '2019-03-29 15:43:54', '2020-09-22 17:08:04', '1', 1);
INSERT INTO `sys_menu` VALUES ('6b1c224d3e1456ef7af991479c293cce', '站点管理', NULL, '/cms/website', '3064c8ad1f223aae292ba462e335ecab', 'iconfont icon-dept', 1, '0', '0', '2020-12-15 14:35:37', NULL, '0', 1);
INSERT INTO `sys_menu` VALUES ('a00635fcbe68cfebb00e44cf75f2eb9c', '管理平台', NULL, '/sys', '-1', 'iconfont icon-sys', 1, '0', '2', '2020-08-13 13:36:44', '2020-08-13 15:31:45', '0', 1);
INSERT INTO `sys_menu` VALUES ('a8de49afd940245925c8c00866bb6056', '租户维护', '', '/sys/tenant/maintain/index', '1500', 'iconfont icon-dept', 0, '0', '0', '2018-01-20 13:17:19', '2020-07-23 07:10:25', '0', 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `sys_oauth_client_details`;
CREATE TABLE `sys_oauth_client_details` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `client_id` varchar(32) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  `del_flag` char(1) DEFAULT '0',
  `tenant_id` varchar(64) NOT NULL DEFAULT '0' COMMENT '所属租户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='终端信息表';

-- ----------------------------
-- Records of sys_oauth_client_details
-- ----------------------------
BEGIN;
INSERT INTO `sys_oauth_client_details` VALUES ('1', 'nacos', NULL, 'nacos', 'server', 'password,refresh_token,authorization_code,client_credentials,implicit', 'http://192.168.56.101:8848/nacos', NULL, 43200, 2592001, NULL, 'false', '0', '1');
INSERT INTO `sys_oauth_client_details` VALUES ('2', 'system', NULL, 'system', 'server', 'password,refresh_token', NULL, NULL, NULL, NULL, NULL, 'true', '0', '1');
INSERT INTO `sys_oauth_client_details` VALUES ('3', 'cesweb', NULL, 'cesweb', 'server', 'password,refresh_token', NULL, NULL, NULL, NULL, NULL, 'true', '0', '1');
COMMIT;

-- ----------------------------
-- Table structure for sys_public_param
-- ----------------------------
DROP TABLE IF EXISTS `sys_public_param`;
CREATE TABLE `sys_public_param` (
  `public_id` varchar(64) NOT NULL COMMENT '编号',
  `public_name` varchar(128) DEFAULT NULL,
  `public_key` varchar(128) DEFAULT NULL,
  `public_value` varchar(128) DEFAULT NULL,
  `status` char(1) DEFAULT '0',
  `validate_code` varchar(64) DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `public_type` char(1) DEFAULT '0',
  `system` char(1) DEFAULT '0',
  `del_flag` char(1) DEFAULT '0',
  `tenant_id` varchar(64) DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`public_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='公共参数配置表';

-- ----------------------------
-- Records of sys_public_param
-- ----------------------------
BEGIN;
INSERT INTO `sys_public_param` VALUES ('1', '租户默认来源', 'TENANT_DEFAULT_ID', '1', '0', '', '2020-05-12 04:03:46', '2020-06-20 08:56:30', '2', '0', '1', '1');
INSERT INTO `sys_public_param` VALUES ('2', '租户默认部门名称', 'TENANT_DEFAULT_DEPTNAME', '租户默认部门', '0', '', '2020-05-12 03:36:32', NULL, '2', '1', '0', '1');
INSERT INTO `sys_public_param` VALUES ('3', '租户默认账户', 'TENANT_DEFAULT_USERNAME', 'admin', '0', '', '2020-05-12 04:05:04', NULL, '2', '1', '0', '1');
INSERT INTO `sys_public_param` VALUES ('4', '租户默认密码', 'TENANT_DEFAULT_PASSWORD', '123456', '0', '', '2020-05-12 04:05:24', NULL, '2', '1', '0', '1');
INSERT INTO `sys_public_param` VALUES ('5', '租户默认角色编码', 'TENANT_DEFAULT_ROLECODE', 'ROLE_ADMIN', '0', '', '2020-05-12 04:05:57', NULL, '2', '1', '0', '1');
INSERT INTO `sys_public_param` VALUES ('6', '租户默认角色名称', 'TENANT_DEFAULT_ROLENAME', '租户默认角色', '0', '', '2020-05-12 04:06:19', NULL, '2', '1', '0', '1');
INSERT INTO `sys_public_param` VALUES ('7', '表前缀', 'GEN_TABLE_PREFIX', 'tb_', '0', '', '2020-05-12 04:23:04', NULL, '9', '1', '0', '1');
INSERT INTO `sys_public_param` VALUES ('8', '接口文档不显示的字段', 'GEN_HIDDEN_COLUMNS', 'tenant_id', '0', '', '2020-05-12 04:25:19', NULL, '9', '1', '0', '1');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` varchar(64) NOT NULL,
  `role_name` varchar(64) DEFAULT NULL,
  `role_code` varchar(64) DEFAULT NULL,
  `role_desc` varchar(255) DEFAULT NULL,
  `ds_type` char(1) DEFAULT '2',
  `ds_scope` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` char(1) DEFAULT '0',
  `tenant_id` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE,
  KEY `role_idx1_role_code` (`role_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES ('1', '超级管理员', 'ROLE_ADMIN', '超级管理员', '0', '2', '2017-10-29 15:45:51', '2018-12-26 14:09:11', '0', '1');
INSERT INTO `sys_role` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '系统管理员', 'ROLE_SYSADMIN', '系统管理员', '0', '', '2020-10-23 09:42:14', NULL, '0', '1');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` varchar(64) NOT NULL COMMENT '角色ID',
  `menu_id` varchar(64) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色菜单表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES ('1', '1000');
INSERT INTO `sys_role_menu` VALUES ('1', '10000');
INSERT INTO `sys_role_menu` VALUES ('1', '10001');
INSERT INTO `sys_role_menu` VALUES ('1', '10002');
INSERT INTO `sys_role_menu` VALUES ('1', '10003');
INSERT INTO `sys_role_menu` VALUES ('1', '10004');
INSERT INTO `sys_role_menu` VALUES ('1', '10005');
INSERT INTO `sys_role_menu` VALUES ('1', '10006');
INSERT INTO `sys_role_menu` VALUES ('1', '10007');
INSERT INTO `sys_role_menu` VALUES ('1', '10008');
INSERT INTO `sys_role_menu` VALUES ('1', '1100');
INSERT INTO `sys_role_menu` VALUES ('1', '1101');
INSERT INTO `sys_role_menu` VALUES ('1', '1102');
INSERT INTO `sys_role_menu` VALUES ('1', '1103');
INSERT INTO `sys_role_menu` VALUES ('1', '1200');
INSERT INTO `sys_role_menu` VALUES ('1', '1201');
INSERT INTO `sys_role_menu` VALUES ('1', '1202');
INSERT INTO `sys_role_menu` VALUES ('1', '1203');
INSERT INTO `sys_role_menu` VALUES ('1', '1300');
INSERT INTO `sys_role_menu` VALUES ('1', '1301');
INSERT INTO `sys_role_menu` VALUES ('1', '1302');
INSERT INTO `sys_role_menu` VALUES ('1', '1303');
INSERT INTO `sys_role_menu` VALUES ('1', '1304');
INSERT INTO `sys_role_menu` VALUES ('1', '1400');
INSERT INTO `sys_role_menu` VALUES ('1', '1401');
INSERT INTO `sys_role_menu` VALUES ('1', '1402');
INSERT INTO `sys_role_menu` VALUES ('1', '1403');
INSERT INTO `sys_role_menu` VALUES ('1', '1500');
INSERT INTO `sys_role_menu` VALUES ('1', '1501');
INSERT INTO `sys_role_menu` VALUES ('1', '1502');
INSERT INTO `sys_role_menu` VALUES ('1', '1503');
INSERT INTO `sys_role_menu` VALUES ('1', '2000');
INSERT INTO `sys_role_menu` VALUES ('1', '2100');
INSERT INTO `sys_role_menu` VALUES ('1', '2101');
INSERT INTO `sys_role_menu` VALUES ('1', '2200');
INSERT INTO `sys_role_menu` VALUES ('1', '2201');
INSERT INTO `sys_role_menu` VALUES ('1', '2202');
INSERT INTO `sys_role_menu` VALUES ('1', '2203');
INSERT INTO `sys_role_menu` VALUES ('1', '2210');
INSERT INTO `sys_role_menu` VALUES ('1', '2211');
INSERT INTO `sys_role_menu` VALUES ('1', '2212');
INSERT INTO `sys_role_menu` VALUES ('1', '2213');
INSERT INTO `sys_role_menu` VALUES ('1', '2300');
INSERT INTO `sys_role_menu` VALUES ('1', '2400');
INSERT INTO `sys_role_menu` VALUES ('1', '2401');
INSERT INTO `sys_role_menu` VALUES ('1', '2402');
INSERT INTO `sys_role_menu` VALUES ('1', '2403');
INSERT INTO `sys_role_menu` VALUES ('1', '2500');
INSERT INTO `sys_role_menu` VALUES ('1', '2501');
INSERT INTO `sys_role_menu` VALUES ('1', '2502');
INSERT INTO `sys_role_menu` VALUES ('1', '2503');
INSERT INTO `sys_role_menu` VALUES ('1', '2600');
INSERT INTO `sys_role_menu` VALUES ('1', '2601');
INSERT INTO `sys_role_menu` VALUES ('1', '2700');
INSERT INTO `sys_role_menu` VALUES ('1', '2800');
INSERT INTO `sys_role_menu` VALUES ('1', '2810');
INSERT INTO `sys_role_menu` VALUES ('1', '2820');
INSERT INTO `sys_role_menu` VALUES ('1', '2830');
INSERT INTO `sys_role_menu` VALUES ('1', '2840');
INSERT INTO `sys_role_menu` VALUES ('1', '2850');
INSERT INTO `sys_role_menu` VALUES ('1', '2860');
INSERT INTO `sys_role_menu` VALUES ('1', '2870');
INSERT INTO `sys_role_menu` VALUES ('1', '3000');
INSERT INTO `sys_role_menu` VALUES ('1', '3064c8ad1f223aae292ba462e335ecab');
INSERT INTO `sys_role_menu` VALUES ('1', '3100');
INSERT INTO `sys_role_menu` VALUES ('1', '3110');
INSERT INTO `sys_role_menu` VALUES ('1', '3200');
INSERT INTO `sys_role_menu` VALUES ('1', '3300');
INSERT INTO `sys_role_menu` VALUES ('1', '3400');
INSERT INTO `sys_role_menu` VALUES ('1', '3500');
INSERT INTO `sys_role_menu` VALUES ('1', '3600');
INSERT INTO `sys_role_menu` VALUES ('1', '4000');
INSERT INTO `sys_role_menu` VALUES ('1', '4100');
INSERT INTO `sys_role_menu` VALUES ('1', '4101');
INSERT INTO `sys_role_menu` VALUES ('1', '4200');
INSERT INTO `sys_role_menu` VALUES ('1', '4201');
INSERT INTO `sys_role_menu` VALUES ('1', '4300');
INSERT INTO `sys_role_menu` VALUES ('1', '4301');
INSERT INTO `sys_role_menu` VALUES ('1', '4302');
INSERT INTO `sys_role_menu` VALUES ('1', '4303');
INSERT INTO `sys_role_menu` VALUES ('1', '4400');
INSERT INTO `sys_role_menu` VALUES ('1', '4401');
INSERT INTO `sys_role_menu` VALUES ('1', '6b1c224d3e1456ef7af991479c293cce');
INSERT INTO `sys_role_menu` VALUES ('1', 'a00635fcbe68cfebb00e44cf75f2eb9c');
INSERT INTO `sys_role_menu` VALUES ('1', 'a8de49afd940245925c8c00866bb6056');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '1000');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '10000');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '10001');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '10002');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '10003');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '10004');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '10005');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '10006');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '10007');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '10008');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '1100');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '1101');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '1102');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '1103');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '1200');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '1201');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '1202');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '1203');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '1300');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '1301');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '1302');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '1303');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '1304');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '1400');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '1401');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '1402');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '1403');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '1500');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '1501');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '1502');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '1503');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '2000');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '2100');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '2101');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '2200');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '2201');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '2202');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '2203');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '2210');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '2211');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '2212');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '2213');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '2300');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '2400');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '2401');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '2402');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '2403');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '2500');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '2501');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '2502');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '2503');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '2600');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '2601');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '2700');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '2800');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '2810');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '2820');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '2830');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '2840');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '2850');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '2860');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '2870');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '3000');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '3100');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '3110');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '3200');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '3300');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '3400');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '3500');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '3600');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '4000');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '4100');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '4101');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '4200');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '4201');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '4300');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '4301');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '4302');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '4303');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '4400');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', '4401');
INSERT INTO `sys_role_menu` VALUES ('1bbc2e2de9fc431625d640d8acdaef2e', 'a00635fcbe68cfebb00e44cf75f2eb9c');
COMMIT;

-- ----------------------------
-- Table structure for sys_route_conf
-- ----------------------------
DROP TABLE IF EXISTS `sys_route_conf`;
CREATE TABLE `sys_route_conf` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `route_name` varchar(30) DEFAULT NULL,
  `route_id` varchar(30) DEFAULT NULL,
  `predicates` json DEFAULT NULL COMMENT '断言',
  `filters` json DEFAULT NULL COMMENT '过滤器',
  `uri` varchar(50) DEFAULT NULL,
  `order` int(2) DEFAULT '0' COMMENT '排序',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` char(1) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='路由配置表';

-- ----------------------------
-- Records of sys_route_conf
-- ----------------------------
BEGIN;
INSERT INTO `sys_route_conf` VALUES ('01', '微信公众号管理', 'pigx-mp-platform', '[{\"args\": {\"_genkey_0\": \"/mp/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-mp-platform', 0, '2019-10-16 16:44:41', '2020-12-15 14:09:48', '1');
INSERT INTO `sys_route_conf` VALUES ('02', '支付管理', 'pigx-pay-platform', '[{\"args\": {\"_genkey_0\": \"/pay/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-pay-platform', 0, '2019-10-16 16:44:41', '2020-12-15 14:09:51', '1');
INSERT INTO `sys_route_conf` VALUES ('03', 'elastic-job定时任务模块', 'pigx-daemon-elastic-job', '[{\"args\": {\"_genkey_0\": \"/daemon/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-daemon-elastic-job', 0, '2019-10-16 16:44:41', '2020-12-15 14:09:53', '1');
INSERT INTO `sys_route_conf` VALUES ('05', '工作流长链接支持1', 'cesweb-service-workflow-ws-1', '[{\"args\": {\"_genkey_0\": \"/wokflow/ws/info/**\"}, \"name\": \"Path\"}]', '[]', 'lb://cesweb-service-workflow', 0, '2020-12-15 12:18:42', '2020-12-21 09:08:45', '1');
INSERT INTO `sys_route_conf` VALUES ('06', '工作流长链接支持2', 'cesweb-service-workflow-ws-2', '[{\"args\": {\"_genkey_0\": \"/wokflow/ws/**\"}, \"name\": \"Path\"}]', '[]', 'lb:ws://cesweb-service-workflow', 100, '2020-12-15 12:18:42', '2020-12-21 09:08:53', '1');
INSERT INTO `sys_route_conf` VALUES ('07', '事务监控模块', 'cesweb-monitor-trans', '[{\"args\": {\"_genkey_0\": \"/tx/**\"}, \"name\": \"Path\"}]', '[]', 'lb://cesweb-monitor-trans', 0, '2020-12-15 12:18:42', '2020-12-15 14:10:01', '0');
INSERT INTO `sys_route_conf` VALUES ('08', '服务监控模块', 'cesweb-monitor-admin', '[{\"args\": {\"_genkey_0\": \"/monitor/**\"}, \"name\": \"Path\"}]', '[]', 'lb://cesweb-monitor-admin', 0, '2020-12-15 12:18:42', '2020-12-15 14:10:58', '0');
INSERT INTO `sys_route_conf` VALUES ('09', '认证中心服务', 'cesweb-service-auth', '[{\"args\": {\"_genkey_0\": \"/auth/**\"}, \"name\": \"Path\"}]', '[{\"args\": {}, \"name\": \"ValidateCodeGatewayFilter\"}, {\"args\": {}, \"name\": \"PasswordDecoderFilter\"}]', 'lb://cesweb-service-auth', 0, '2020-12-15 12:18:42', '2020-12-15 14:13:14', '0');
INSERT INTO `sys_route_conf` VALUES ('10', '定时任务服务', 'cesweb-service-quartz', '[{\"args\": {\"_genkey_0\": \"/job/**\"}, \"name\": \"Path\"}]', '[]', 'lb://cesweb-service-quartz', 0, '2020-12-15 12:18:42', '2020-12-15 14:13:11', '0');
INSERT INTO `sys_route_conf` VALUES ('11', '系统管理模块', 'cesweb-service-system', '[{\"args\": {\"_genkey_0\": \"/system/**\"}, \"name\": \"Path\"}]', '[{\"args\": {\"key-resolver\": \"#{@remoteAddrKeyResolver}\", \"redis-rate-limiter.burstCapacity\": \"1000\", \"redis-rate-limiter.replenishRate\": \"1000\"}, \"name\": \"RequestRateLimiter\"}]', 'lb://cesweb-service-system', 0, '2020-12-15 12:18:42', '2020-12-15 14:13:09', '0');
INSERT INTO `sys_route_conf` VALUES ('12', '开发管理模块', 'cesweb-service-develope', '[{\"args\": {\"_genkey_0\": \"/develope/**\"}, \"name\": \"Path\"}]', '[]', 'lb://cesweb-service-develope', 0, '2020-12-15 12:18:42', '2020-12-21 09:07:59', '0');
INSERT INTO `sys_route_conf` VALUES ('13', '流程管理模块', 'cesweb-service-workflow', '[{\"args\": {\"_genkey_0\": \"/workflow/**\"}, \"name\": \"Path\"}]', '[]', 'lb://cesweb-service-workflow', 0, '2020-12-15 12:18:42', '2020-12-21 09:08:26', '0');
INSERT INTO `sys_route_conf` VALUES ('14', '租户管理模块', 'cesweb-service-tenant', '[{\"args\": {\"_genkey_0\": \"/tenant/**\"}, \"name\": \"Path\"}]', '[]', 'lb://cesweb-service-tenant', 0, '2020-12-15 12:18:42', '2020-12-15 14:16:25', '0');
COMMIT;

-- ----------------------------
-- Table structure for sys_social_details
-- ----------------------------
DROP TABLE IF EXISTS `sys_social_details`;
CREATE TABLE `sys_social_details` (
  `id` varchar(64) NOT NULL COMMENT '主鍵',
  `type` varchar(16) DEFAULT NULL,
  `remark` varchar(64) DEFAULT NULL,
  `app_id` varchar(64) DEFAULT NULL,
  `app_secret` varchar(64) DEFAULT NULL,
  `redirect_url` varchar(128) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` char(1) DEFAULT '0',
  `tenant_id` varchar(64) NOT NULL DEFAULT '0' COMMENT '所属租户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统社交登录账号表';

-- ----------------------------
-- Records of sys_social_details
-- ----------------------------
BEGIN;
INSERT INTO `sys_social_details` VALUES ('1', 'WX', '微信互联参数', 'wxd1678d3f83b1d83a', '6ddb043f94da5d2172926abe8533504f', 'daoweicloud.com', '2018-08-16 14:24:25', '2019-03-02 09:43:13', '0', '1');
INSERT INTO `sys_social_details` VALUES ('2', 'GITEE', '码云登录', '8fc54e0e76e7842cf767c3ae3b9fdc48c03cefed27aa565ff7b2a39d142d9892', 'c544469ce78a67d9fcf9b28cd9f310b73f5cbc46a1b993e0802ad61517deb221', 'http://gitee.huaxiadaowei.com/#/authredirect', '2019-06-28 09:59:55', '2019-06-28 09:59:55', '0', '1');
INSERT INTO `sys_social_details` VALUES ('3', 'OSC', '开源中国', 'neIIqlwGsjsfsA6uxNqD', 'aOPhRuOOJNXV1x7JrTJ9qIyRCAPXoO0l', 'http://gitee.huaxiadaowei.com/#/authredirect', '2019-06-28 10:05:37', '2019-06-28 10:05:37', '0', '1');
INSERT INTO `sys_social_details` VALUES ('4', 'MINI', '小程序', 'wx6832be859d0e1cf5', '08036aef810dcb2f8ae31510910ba631', NULL, '2019-11-02 22:08:03', '2019-11-02 22:10:53', '0', '1');
COMMIT;

-- ----------------------------
-- Table structure for sys_tenant
-- ----------------------------
DROP TABLE IF EXISTS `sys_tenant`;
CREATE TABLE `sys_tenant` (
  `id` varchar(64) NOT NULL COMMENT '租户id',
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(64) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `status` char(1) DEFAULT '0',
  `del_flag` char(1) DEFAULT '0',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='租户表';

-- ----------------------------
-- Records of sys_tenant
-- ----------------------------
BEGIN;
INSERT INTO `sys_tenant` VALUES ('1', '北京分公司', '1', '2019-05-15 00:00:00', '2029-05-15 00:00:00', '0', '0', '2019-05-15 15:44:57', '2019-05-18 14:47:30');
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` varchar(64) NOT NULL COMMENT '主键ID',
  `name` varchar(64) DEFAULT NULL COMMENT '用户姓名',
  `username` varchar(64) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `salt` varchar(255) DEFAULT NULL COMMENT '随机盐',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `dept_id` varchar(64) DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `lock_flag` char(1) DEFAULT '0' COMMENT '锁定标识',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标识',
  `wx_openid` varchar(32) DEFAULT NULL COMMENT '微信登录openId',
  `mini_openid` varchar(32) DEFAULT NULL COMMENT '小程序openId',
  `qq_openid` varchar(32) DEFAULT NULL COMMENT 'QQ openId',
  `gitee_login` varchar(100) DEFAULT NULL COMMENT '码云 标识',
  `osc_id` varchar(100) DEFAULT NULL COMMENT '开源中国 标识',
  `tenant_id` varchar(64) NOT NULL DEFAULT '0' COMMENT '所属租户',
  PRIMARY KEY (`user_id`) USING BTREE,
  KEY `user_wx_openid` (`wx_openid`) USING BTREE,
  KEY `user_qq_openid` (`qq_openid`) USING BTREE,
  KEY `user_idx1_username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES ('1', '超级管理员', 'admin', '$2a$10$IVzj1Wd.ZQdOIWdb1htQjexU94uoNeuk1crlQ9ExVupPi0Iy1uv.C', '', '17034642888', '/system/sysfile/cesweb/e27b93d8cf1e4f63940322776fc660fd.gif', '1', '2018-04-20 07:15:18', '2020-10-23 17:04:34', '0', '0', 'o_0FT0uyg_H1vVy2H0JpSwlVGhWQ', 'oBxPy5E-v82xWGsfzZVzkD3wEX64', NULL, 'log4j', '2303656', '1');
INSERT INTO `sys_user` VALUES ('62f5e46970651bad9cd5ae5b44703855', '系统管理员', 'chanlong', '$2a$10$MDcLCNMWOeLPTD2aLrTi4OH3MKwIfA8rU931E1Vux1d2jXctINbh2', NULL, '', NULL, '65b75d22b6419ad81d4683006829a675', '2020-10-23 09:54:09', '2020-10-23 17:04:47', '0', '0', NULL, NULL, NULL, NULL, NULL, '1');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` varchar(64) NOT NULL COMMENT '用户ID',
  `role_id` varchar(64) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('62f5e46970651bad9cd5ae5b44703855', '1bbc2e2de9fc431625d640d8acdaef2e');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
