/*
 Navicat Premium Data Transfer

 Source Server         : 106.12.5.251_cesweb
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : 106.12.5.251:3306
 Source Schema         : cesweb_quartz

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 18/01/2021 12:40:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `sched_name` varchar(120) CHARACTER SET utf8 NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 NOT NULL,
  `blob_data` blob,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `sched_name` varchar(120) CHARACTER SET utf8 NOT NULL,
  `calendar_name` varchar(200) CHARACTER SET utf8 NOT NULL,
  `calendar` blob NOT NULL,
  PRIMARY KEY (`sched_name`,`calendar_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `sched_name` varchar(120) CHARACTER SET utf8 NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 NOT NULL,
  `cron_expression` varchar(200) CHARACTER SET utf8 NOT NULL,
  `time_zone_id` varchar(80) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
BEGIN;
INSERT INTO `qrtz_cron_triggers` VALUES ('clusteredScheduler', '测试任务', 'demo', '0 * * * * ? *', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('clusteredScheduler', '测试任务', '测试任务', '3/5 * * * * ? *', 'Asia/Shanghai');
COMMIT;

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `sched_name` varchar(120) CHARACTER SET utf8 NOT NULL,
  `entry_id` varchar(95) CHARACTER SET utf8 NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 NOT NULL,
  `instance_name` varchar(200) CHARACTER SET utf8 NOT NULL,
  `fired_time` bigint(13) NOT NULL,
  `sched_time` bigint(13) NOT NULL,
  `priority` int(11) NOT NULL,
  `state` varchar(16) CHARACTER SET utf8 NOT NULL,
  `job_name` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `job_group` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `is_nonconcurrent` varchar(1) CHARACTER SET utf8 DEFAULT NULL,
  `requests_recovery` varchar(1) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`entry_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `sched_name` varchar(120) CHARACTER SET utf8 NOT NULL,
  `job_name` varchar(200) CHARACTER SET utf8 NOT NULL,
  `job_group` varchar(200) CHARACTER SET utf8 NOT NULL,
  `description` varchar(250) CHARACTER SET utf8 DEFAULT NULL,
  `job_class_name` varchar(250) CHARACTER SET utf8 NOT NULL,
  `is_durable` varchar(1) CHARACTER SET utf8 NOT NULL,
  `is_nonconcurrent` varchar(1) CHARACTER SET utf8 NOT NULL,
  `is_update_data` varchar(1) CHARACTER SET utf8 NOT NULL,
  `requests_recovery` varchar(1) CHARACTER SET utf8 NOT NULL,
  `job_data` blob,
  PRIMARY KEY (`sched_name`,`job_name`,`job_group`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
BEGIN;
INSERT INTO `qrtz_job_details` VALUES ('clusteredScheduler', '测试任务', 'demo', NULL, 'com.pig4cloud.pigx.daemon.quartz.config.PigxQuartzFactory', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000B7363686564756C654A6F627372002E636F6D2E70696734636C6F75642E706967782E6461656D6F6E2E71756172747A2E656E746974792E5379734A6F6200000000000000010200174C0009636C6173734E616D657400124C6A6176612F6C616E672F537472696E673B4C0008637265617465427971007E00094C000A63726561746554696D657400194C6A6176612F74696D652F4C6F63616C4461746554696D653B4C000E63726F6E45787072657373696F6E71007E00094C000B657865637574655061746871007E00094C00106A6F624578656375746553746174757371007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400134C6A6176612F6C616E672F496E74656765723B4C00076A6F624E616D6571007E00094C00086A6F624F7264657271007E00094C00096A6F6253746174757371007E00094C000D6A6F6254656E616E745479706571007E00094C00076A6F625479706571007E00094C000A6D6574686F644E616D6571007E00094C00116D6574686F64506172616D7356616C756571007E00094C000D6D697366697265506F6C69637971007E00094C00086E65787454696D6571007E000A4C000C70726576696F757354696D6571007E000A4C000672656D61726B71007E00094C0009737461727454696D6571007E000A4C000874656E616E74496471007E000B4C0008757064617465427971007E00094C000A75706461746554696D6571007E000A78720035636F6D2E62616F6D69646F752E6D796261746973706C75732E657874656E73696F6E2E6163746976657265636F72642E4D6F64656C0000000000000001020000787074000464656D6F74000561646D696E7372000D6A6176612E74696D652E536572955D84BA1B2248B20C00007870770A05000007E303190E00F17874000D30202A202A202A202A203F202A7400007400013074000464656D6F737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000174000CE6B58BE8AF95E4BBBBE58AA17400013574000133740001317400013274000A64656D6F4D6574686F6474000970696734636C6F7564740001337371007E0010770905000007E3031916E2787371007E0010770A05000007E30319161CCD78740020E6BC94E7A4BA537072696E67204265616EE79A84E5AE9AE697B6E4BBBBE58AA17371007E0010770A05000007E303191600DE7871007E001874000561646D696E7371007E0010770A05000007E303190E00F1787800);
INSERT INTO `qrtz_job_details` VALUES ('clusteredScheduler', '测试任务', '测试任务', NULL, 'cn.cesgroup.cesweb.service.quartz.factory.QuartzFactory', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000B7363686564756C654A6F627372002F636E2E63657367726F75702E6365737765622E736572766963652E71756172747A2E656E746974792E5379734A6F6200000000000000010200114C0009636C6173734E616D657400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000B657865637574655061746871007E00094C00106A6F624578656375746553746174757371007E00094C00086A6F6247726F757071007E00094C00056A6F62496471007E00094C00076A6F624E616D6571007E00094C00086A6F624F7264657271007E00094C00096A6F6253746174757371007E00094C000D6A6F6254656E616E745479706571007E00094C00076A6F625479706571007E00094C000A6D6574686F644E616D6571007E00094C00116D6574686F64506172616D7356616C756571007E00094C000D6D697366697265506F6C69637971007E00094C00086E65787454696D657400194C6A6176612F74696D652F4C6F63616C4461746554696D653B4C000C70726576696F757354696D6571007E000A4C0009737461727454696D6571007E000A78720030636E2E63657367726F75702E6365737765622E636F6D6D6F6E2E646174612E656E746974792E42617365456E7469747900000000000000010200064C0008637265617465427971007E00094C000A63726561746554696D6571007E000A4C000672656D61726B71007E00094C000874656E616E74496471007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000A78720035636F6D2E62616F6D69646F752E6D796261746973706C75732E657874656E73696F6E2E6163746976657265636F72642E4D6F64656C0000000000000001020000787074000561646D696E7372000D6A6176612E74696D652E536572955D84BA1B2248B20C00007870770A05000007E408140A3BF7787400007400007400007371007E000F770A05000007E408140A3BF77874000074000F332F35202A202A202A202A203F202A74000074000074000CE6B58BE8AF95E4BBBBE58AA1740020363335313632353862636537643762373334623235366632363133326662656374000CE6B58BE8AF95E4BBBBE58AA1740000740001317400013174000133740000740000740001317070707800);
COMMIT;

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `sched_name` varchar(120) CHARACTER SET utf8 NOT NULL,
  `lock_name` varchar(40) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`sched_name`,`lock_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
BEGIN;
INSERT INTO `qrtz_locks` VALUES ('clusteredScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('clusteredScheduler', 'TRIGGER_ACCESS');
COMMIT;

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `sched_name` varchar(120) CHARACTER SET utf8 NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`sched_name`,`trigger_group`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `sched_name` varchar(120) CHARACTER SET utf8 NOT NULL,
  `instance_name` varchar(200) CHARACTER SET utf8 NOT NULL,
  `last_checkin_time` bigint(13) NOT NULL,
  `checkin_interval` bigint(13) NOT NULL,
  PRIMARY KEY (`sched_name`,`instance_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
BEGIN;
INSERT INTO `qrtz_scheduler_state` VALUES ('clusteredScheduler', 'd234da6f1aec1610942524355', 1610944818967, 10000);
COMMIT;

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `sched_name` varchar(120) CHARACTER SET utf8 NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 NOT NULL,
  `repeat_count` bigint(7) NOT NULL,
  `repeat_interval` bigint(12) NOT NULL,
  `times_triggered` bigint(10) NOT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`) USING BTREE,
  CONSTRAINT `QRTZ_SIMPLE_TRIGGERS_IBFK_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `sched_name` varchar(120) CHARACTER SET utf8 NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 NOT NULL,
  `str_prop_1` varchar(512) CHARACTER SET utf8 DEFAULT NULL,
  `str_prop_2` varchar(512) CHARACTER SET utf8 DEFAULT NULL,
  `str_prop_3` varchar(512) CHARACTER SET utf8 DEFAULT NULL,
  `int_prop_1` int(11) DEFAULT NULL,
  `int_prop_2` int(11) DEFAULT NULL,
  `long_prop_1` bigint(20) DEFAULT NULL,
  `long_prop_2` bigint(20) DEFAULT NULL,
  `dec_prop_1` decimal(13,4) DEFAULT NULL,
  `dec_prop_2` decimal(13,4) DEFAULT NULL,
  `bool_prop_1` varchar(1) CHARACTER SET utf8 DEFAULT NULL,
  `bool_prop_2` varchar(1) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`) USING BTREE,
  CONSTRAINT `QRTZ_SIMPROP_TRIGGERS_IBFK_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `sched_name` varchar(120) CHARACTER SET utf8 NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 NOT NULL,
  `job_name` varchar(200) CHARACTER SET utf8 NOT NULL,
  `job_group` varchar(200) CHARACTER SET utf8 NOT NULL,
  `description` varchar(250) CHARACTER SET utf8 DEFAULT NULL,
  `next_fire_time` bigint(13) DEFAULT NULL,
  `prev_fire_time` bigint(13) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `trigger_state` varchar(16) CHARACTER SET utf8 NOT NULL,
  `trigger_type` varchar(8) CHARACTER SET utf8 NOT NULL,
  `start_time` bigint(13) NOT NULL,
  `end_time` bigint(13) DEFAULT NULL,
  `calendar_name` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `misfire_instr` smallint(2) DEFAULT NULL,
  `job_data` blob,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`) USING BTREE,
  KEY `sched_name` (`sched_name`,`job_name`,`job_group`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
BEGIN;
INSERT INTO `qrtz_triggers` VALUES ('clusteredScheduler', '测试任务', 'demo', '测试任务', 'demo', NULL, 1566205080000, 1566205020000, 5, 'PAUSED', 'CRON', 1553564598000, 0, NULL, 2, '');
INSERT INTO `qrtz_triggers` VALUES ('clusteredScheduler', '测试任务', '测试任务', '测试任务', '测试任务', NULL, 1610942798000, 1610942793000, 5, 'PAUSED', 'CRON', 1610942774000, 0, NULL, -1, '');
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
INSERT INTO `sys_job` VALUES ('63516258bce7d7b734b256f26132fbec', '测试任务', '测试任务', '', '3', 'http://106.12.5.251:9999/actuator', '', '', '', '3/5 * * * * ? *', '1', '1', '3', '0', 'admin', '2020-08-20 10:59:08', '', '2020-08-20 10:59:08', '2021-01-18 12:06:14', '2021-01-18 12:06:33', '2021-01-18 12:06:38', '', '');
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
  `remark` varchar(1024) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `tenant_id` varchar(32) NOT NULL DEFAULT '1' COMMENT '租户id',
  PRIMARY KEY (`job_log_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='定时任务执行日志表';

-- ----------------------------
-- Records of sys_job_log
-- ----------------------------
BEGIN;
INSERT INTO `sys_job_log` VALUES ('27ff9db693057f9f4293205cde8a23f8', '63516258bce7d7b734b256f26132fbec', '测试任务', '测试任务', '', '3', '', '', '', '', '3/5 * * * * ? *', '执行失败', '1', '3', '定时任务restTaskInvok业务执行失败,任务：', NULL, NULL, '2021-01-18 12:06:28', NULL, NULL, '');
INSERT INTO `sys_job_log` VALUES ('49cd6b0a0c2d9e696af98ddaecde2cdf', '63516258bce7d7b734b256f26132fbec', '测试任务', '测试任务', '', '3', '', '', '', '', '3/5 * * * * ? *', '执行失败', '1', '1', '定时任务restTaskInvok业务执行失败,任务：', NULL, NULL, '2021-01-18 12:06:23', NULL, NULL, '');
INSERT INTO `sys_job_log` VALUES ('4b1fe2684e93683d51b4928c149e34d0', '63516258bce7d7b734b256f26132fbec', '测试任务', '测试任务', '', '3', '', '', '', '', '3/5 * * * * ? *', '执行失败', '1', '42', '定时任务restTaskInvok业务执行失败,任务：', NULL, NULL, '2021-01-18 12:06:18', NULL, NULL, '');
INSERT INTO `sys_job_log` VALUES ('944155934363f46ddf4b159ac2b7349a', '63516258bce7d7b734b256f26132fbec', '测试任务', '测试任务', '', '3', '', '', '', '', '3/5 * * * * ? *', '执行失败', '1', '1', '定时任务restTaskInvok业务执行失败,任务：', NULL, NULL, '2021-01-18 12:06:33', NULL, NULL, '');
INSERT INTO `sys_job_log` VALUES ('cd1eafaabacc8dceafddab20ef6e7aca', '63516258bce7d7b734b256f26132fbec', '测试任务', '测试任务', '', '3', 'http://106.12.5.251:9999/actuator', '', '', '', '3/5 * * * * ? *', '执行成功', '0', '147', '', NULL, NULL, '2021-01-18 12:13:55', NULL, NULL, '');
INSERT INTO `sys_job_log` VALUES ('df738720ba39a1f08ff665bbf0385b22', '63516258bce7d7b734b256f26132fbec', '测试任务', '测试任务', '', '3', '', '', '', '', '3/5 * * * * ? *', '执行失败', '1', '1', '定时任务restTaskInvok业务执行失败,任务：', NULL, NULL, '2021-01-18 12:07:06', NULL, NULL, '');
INSERT INTO `sys_job_log` VALUES ('e77f649ec9ef3662f55f201eb4b6f95f', '63516258bce7d7b734b256f26132fbec', '测试任务', '测试任务', '', '3', '', '', '', '', '3/5 * * * * ? *', '执行失败', '1', '1', '定时任务restTaskInvok业务执行失败,任务：', NULL, NULL, '2021-01-18 12:12:50', NULL, NULL, '');
COMMIT;

-- ----------------------------
-- Table structure for xxl_job_group
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_group`;
CREATE TABLE `xxl_job_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(64) NOT NULL COMMENT '执行器AppName',
  `title` varchar(12) NOT NULL COMMENT '执行器名称',
  `order` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `address_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '执行器地址类型：0=自动注册、1=手动录入',
  `address_list` varchar(512) DEFAULT NULL COMMENT '执行器地址列表，多地址逗号分隔',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of xxl_job_group
-- ----------------------------
BEGIN;
INSERT INTO `xxl_job_group` VALUES (1, 'xxl-job-executor', '示例执行器', 1, 0, NULL);
COMMIT;

-- ----------------------------
-- Table structure for xxl_job_info
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_info`;
CREATE TABLE `xxl_job_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_group` int(11) NOT NULL COMMENT '执行器主键ID',
  `job_cron` varchar(128) NOT NULL COMMENT '任务执行CRON',
  `job_desc` varchar(255) NOT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `author` varchar(64) DEFAULT NULL COMMENT '作者',
  `alarm_email` varchar(255) DEFAULT NULL COMMENT '报警邮件',
  `executor_route_strategy` varchar(50) DEFAULT NULL COMMENT '执行器路由策略',
  `executor_handler` varchar(255) DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) DEFAULT NULL COMMENT '执行器任务参数',
  `executor_block_strategy` varchar(50) DEFAULT NULL COMMENT '阻塞处理策略',
  `executor_timeout` int(11) NOT NULL DEFAULT '0' COMMENT '任务执行超时时间，单位秒',
  `executor_fail_retry_count` int(11) NOT NULL DEFAULT '0' COMMENT '失败重试次数',
  `glue_type` varchar(50) NOT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) DEFAULT NULL COMMENT 'GLUE备注',
  `glue_updatetime` datetime DEFAULT NULL COMMENT 'GLUE更新时间',
  `child_jobid` varchar(255) DEFAULT NULL COMMENT '子任务ID，多个逗号分隔',
  `trigger_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '调度状态：0-停止，1-运行',
  `trigger_last_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '上次调度时间',
  `trigger_next_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '下次调度时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of xxl_job_info
-- ----------------------------
BEGIN;
INSERT INTO `xxl_job_info` VALUES (1, 1, '0 0 0 * * ? *', '测试任务1', '2018-11-03 22:21:31', '2020-04-15 13:58:35', 'lengleng', '', 'ROUND', 'demoJobHandler', '', 'DISCARD_LATER', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2018-11-03 22:21:31', '', 0, 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for xxl_job_lock
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_lock`;
CREATE TABLE `xxl_job_lock` (
  `lock_name` varchar(50) NOT NULL COMMENT '锁名称',
  PRIMARY KEY (`lock_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of xxl_job_lock
-- ----------------------------
BEGIN;
INSERT INTO `xxl_job_lock` VALUES ('schedule_lock');
COMMIT;

-- ----------------------------
-- Table structure for xxl_job_log
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_log`;
CREATE TABLE `xxl_job_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_group` int(11) NOT NULL COMMENT '执行器主键ID',
  `job_id` int(11) NOT NULL COMMENT '任务，主键ID',
  `executor_address` varchar(255) DEFAULT NULL COMMENT '执行器地址，本次执行的地址',
  `executor_handler` varchar(255) DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) DEFAULT NULL COMMENT '执行器任务参数',
  `executor_sharding_param` varchar(20) DEFAULT NULL COMMENT '执行器任务分片参数，格式如 1/2',
  `executor_fail_retry_count` int(11) NOT NULL DEFAULT '0' COMMENT '失败重试次数',
  `trigger_time` datetime DEFAULT NULL COMMENT '调度-时间',
  `trigger_code` int(11) NOT NULL COMMENT '调度-结果',
  `trigger_msg` text COMMENT '调度-日志',
  `handle_time` datetime DEFAULT NULL COMMENT '执行-时间',
  `handle_code` int(11) NOT NULL COMMENT '执行-状态',
  `handle_msg` text COMMENT '执行-日志',
  `alarm_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败',
  PRIMARY KEY (`id`),
  KEY `I_trigger_time` (`trigger_time`),
  KEY `I_handle_code` (`handle_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for xxl_job_log_report
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_log_report`;
CREATE TABLE `xxl_job_log_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `trigger_day` datetime DEFAULT NULL COMMENT '调度-时间',
  `running_count` int(11) NOT NULL DEFAULT '0' COMMENT '运行中-日志数量',
  `suc_count` int(11) NOT NULL DEFAULT '0' COMMENT '执行成功-日志数量',
  `fail_count` int(11) NOT NULL DEFAULT '0' COMMENT '执行失败-日志数量',
  PRIMARY KEY (`id`),
  UNIQUE KEY `i_trigger_day` (`trigger_day`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for xxl_job_logglue
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_logglue`;
CREATE TABLE `xxl_job_logglue` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_id` int(11) NOT NULL COMMENT '任务，主键ID',
  `glue_type` varchar(50) DEFAULT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) NOT NULL COMMENT 'GLUE备注',
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for xxl_job_registry
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_registry`;
CREATE TABLE `xxl_job_registry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `registry_group` varchar(50) NOT NULL,
  `registry_key` varchar(255) NOT NULL,
  `registry_value` varchar(255) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `i_g_k_v` (`registry_group`,`registry_key`,`registry_value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for xxl_job_user
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_user`;
CREATE TABLE `xxl_job_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '账号',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `role` tinyint(4) NOT NULL COMMENT '角色：0-普通用户、1-管理员',
  `permission` varchar(255) DEFAULT NULL COMMENT '权限：执行器ID列表，多个逗号分割',
  PRIMARY KEY (`id`),
  UNIQUE KEY `i_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of xxl_job_user
-- ----------------------------
BEGIN;
INSERT INTO `xxl_job_user` VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 1, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
