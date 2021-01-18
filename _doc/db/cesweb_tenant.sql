/*
 Navicat Premium Data Transfer

 Source Server         : 106.12.5.251_cesweb
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : 106.12.5.251:3306
 Source Schema         : cesweb_tenant

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 18/01/2021 12:40:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_mt_tenant
-- ----------------------------
DROP TABLE IF EXISTS `t_mt_tenant`;
CREATE TABLE `t_mt_tenant` (
  `id` varchar(64) NOT NULL,
  `name` varchar(256) NOT NULL COMMENT '租户名称',
  `code` varchar(256) NOT NULL COMMENT '租户编码',
  `validity_begin` datetime DEFAULT NULL COMMENT '有效期开始',
  `validity_finish` datetime DEFAULT NULL COMMENT '有效期结束',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `status` varchar(8) DEFAULT NULL COMMENT '状态标识',
  `del_flag` varchar(8) DEFAULT NULL COMMENT '删除标识',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(64) DEFAULT NULL COMMENT '修改者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
