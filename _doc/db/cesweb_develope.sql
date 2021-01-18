/*
 Navicat Premium Data Transfer

 Source Server         : 106.12.5.251_cesweb
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : 106.12.5.251:3306
 Source Schema         : cesweb_develope

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 18/01/2021 12:40:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gen_datasource_conf
-- ----------------------------
DROP TABLE IF EXISTS `gen_datasource_conf`;
CREATE TABLE `gen_datasource_conf` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `name` varchar(64) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `username` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新',
  `del_flag` char(1) DEFAULT '0',
  `tenant_id` int(11) DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='数据源表';

-- ----------------------------
-- Records of gen_datasource_conf
-- ----------------------------
BEGIN;
INSERT INTO `gen_datasource_conf` VALUES ('f6c4e31cd8e21d0b6a9ee27fa53a8b2e', 'cesweb_develope', 'jdbc:mysql://106.12.5.251:3306/cesweb_develope?characterEncoding=utf8', 'cesweb', 'd+4MVbpYxa2jYDPxEFDRrTQWVRRNB+/G', '2021-01-18 09:42:44', '2021-01-18 09:42:44', '0', NULL);
COMMIT;

-- ----------------------------
-- Table structure for gen_form_conf
-- ----------------------------
DROP TABLE IF EXISTS `gen_form_conf`;
CREATE TABLE `gen_form_conf` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `table_name` varchar(64) DEFAULT NULL,
  `form_info` json NOT NULL COMMENT '表单信息',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` char(1) DEFAULT '0',
  `tenant_id` int(11) DEFAULT NULL COMMENT '所属租户',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `table_name` (`table_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='表单配置';

-- ----------------------------
-- Records of gen_form_conf
-- ----------------------------
BEGIN;
INSERT INTO `gen_form_conf` VALUES ('e07929f9bee917ee2e06f9318d720154', 'gen_form_conf', '{\"align\": \"center\", \"index\": true, \"border\": true, \"column\": [{\"prop\": \"id\", \"type\": \"input\", \"label\": \"ID\"}, {\"prop\": \"tableName\", \"type\": \"input\", \"label\": \"\"}, {\"prop\": \"formInfo\", \"type\": \"input\", \"label\": \"表单信息\"}, {\"prop\": \"createTime\", \"type\": \"input\", \"label\": \"创建时间\"}, {\"prop\": \"updateTime\", \"type\": \"input\", \"label\": \"修改时间\"}, {\"prop\": \"delFlag\", \"type\": \"input\", \"label\": \"\"}, {\"prop\": \"tenantId\", \"type\": \"input\", \"label\": \"所属租户\"}], \"gutter\": 0, \"stripe\": true, \"menuBtn\": true, \"emptyBtn\": true, \"emptyText\": \"清空\", \"menuAlign\": \"center\", \"submitBtn\": true, \"indexLabel\": \"序号\", \"labelWidth\": 120, \"submitText\": \"提交\", \"labelSuffix\": \"：\", \"menuPosition\": \"center\", \"labelPosition\": \"left\", \"searchMenuSpan\": 6}', '2021-01-18 11:08:00', '2021-01-18 11:08:00', '0', NULL);
COMMIT;

-- ----------------------------
-- Table structure for tb_dev_datasource
-- ----------------------------
DROP TABLE IF EXISTS `tb_dev_datasource`;
CREATE TABLE `tb_dev_datasource` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `name` varchar(64) DEFAULT NULL,
  `jdbc_url` varchar(1024) DEFAULT NULL,
  `username` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `driver_class` varchar(64) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `remark` varchar(512) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `deleted` int(11) DEFAULT '0',
  `create_by` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='数据源表';

-- ----------------------------
-- Records of tb_dev_datasource
-- ----------------------------
BEGIN;
INSERT INTO `tb_dev_datasource` VALUES ('045273aed7a14eec284154834ab226c8', 'name', 'jdbcUrl', 'username', 'password', 'driverClass', NULL, NULL, NULL, 0, NULL, '2021-01-06 17:42:47', NULL, '2021-01-06 17:42:49');
INSERT INTO `tb_dev_datasource` VALUES ('233500f7b880b081fa6f0e4688b8bc63', 'cesweb_develope', 'jdbc:mysql://${Mjkl;\'YSQL_HOST:106.12.5.251}:${MYSQL_PORT:3306}/${MYSQL_DB:cesweb_develope}?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&allowMultiQueries=true&allowPublicKeyRetrieval=true', 'oajD8nC2NY+PQwcIXcfv3EoHkjGH+MhG', 'iQqVemi5yvMy5o9PGmtv4KTn/c6JNBEF', 'com.mysql.cj.jdbc.Driver', NULL, NULL, NULL, 0, NULL, '2021-01-06 16:53:22', NULL, '2021-01-07 09:33:16');
INSERT INTO `tb_dev_datasource` VALUES ('2544cb1868120dd616c8813f9b872fe5', 'name', 'jdbcUrl', 'username', 'password', 'driverClass', NULL, NULL, NULL, 0, NULL, '2021-01-06 16:52:21', NULL, '2021-01-06 16:52:21');
INSERT INTO `tb_dev_datasource` VALUES ('bb262691939699bdfdd333a288100585', '123', '123', NULL, '789', '456', NULL, NULL, NULL, 1, NULL, NULL, NULL, '2021-01-07 09:43:26');
INSERT INTO `tb_dev_datasource` VALUES ('c5e460fd21958d2562e417d61ac5ae32', '123', '123', NULL, '789', '456', NULL, NULL, NULL, 0, NULL, NULL, NULL, '2021-01-06 13:25:56');
INSERT INTO `tb_dev_datasource` VALUES ('e378eba5f441029245a219a9abf458f0', 'cesweb_develope', 'jdbc:mysql://${MYSQL_HOST:106.12.5.251}:${MYSQL_PORT:3306}/${MYSQL_DB:cesweb_develope}?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&allowMultiQueries=true&allowPublicKeyRetrieval=true', 'oajD8nC2NY+PQwcIXcfv3EoHkjGH+MhG', 'iQqVemi5yvMy5o9PGmtv4KTn/c6JNBEF', 'com.mysql.cj.jdbc.Driver', NULL, NULL, NULL, 0, NULL, '2021-01-07 09:30:49', NULL, '2021-01-07 09:30:50');
COMMIT;

-- ----------------------------
-- Table structure for tb_dev_form
-- ----------------------------
DROP TABLE IF EXISTS `tb_dev_form`;
CREATE TABLE `tb_dev_form` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `table` varchar(64) DEFAULT NULL,
  `code` json NOT NULL COMMENT '表单信息',
  `status` int(11) DEFAULT NULL,
  `remark` varchar(512) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `deleted` int(11) DEFAULT '0',
  `create_by` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `table_name` (`table`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='表单配置';

SET FOREIGN_KEY_CHECKS = 1;
