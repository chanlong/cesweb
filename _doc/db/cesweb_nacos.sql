/*
 Navicat Premium Data Transfer

 Source Server         : 106.12.5.251_cesweb
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : 106.12.5.251:3306
 Source Schema         : cesweb_nacos

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 18/01/2021 12:40:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `c_use` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `effect` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `c_schema` text COLLATE utf8_bin,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfo_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info';

-- ----------------------------
-- Records of config_info
-- ----------------------------
BEGIN;
INSERT INTO `config_info` VALUES (1, 'application-dev.yml', 'DEFAULT_GROUP', '# 配置文件加密规则配置\njasypt:\n  encryptor:\n    password: cesweb\n    algorithm: PBEWithMD5AndDES\n\nspring:\n  redis:\n    host: ${REDIS_HOST:106.12.5.251}\n    password: ENC(5Xm2Gp3G0VaaUTAA6ngFNIuzYHiyX2PaiEOb/ZbD924=)\n  servlet:\n    multipart:\n      max-file-size: 100MB\n      max-request-size: 100MB\n  cloud:\n    sentinel:\n      eager: true\n      filter:\n        url-patterns: /** \n      transport:\n        dashboard: ${SENTINEL_HOST:106.12.5.251}:5020\n                \nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: ALWAYS\n\nfeign:\n  sentinel:\n    enabled: true\n  okhttp:\n    enabled: true\n  httpclient:\n    enabled: false\n  client:\n    config:\n      default:\n        connectTimeout: 10000\n        readTimeout: 10000\n  compression:\n    request:\n      enabled: true\n    response:\n      enabled: true\n\nribbon:\n  ReadTimeout: 10000\n  ConnectTimeout: 10000\n\n# 开启灰度\ngray:\n  rule:\n    enabled: true\n\n# mybatis-plus配置\nmybatis-plus:\n  tenant-enable: true\n  mapper-locations: mapper/*/*.xml, classpath*:mapper/*.xml, classpath:/META-INF/modeler-mybatis-mappings/*.xml\n  configuration-properties:\n    prefix: \'\'\n    blobType: BLOB\n    boolValue: TRUE\n  global-config:\n    banner: false\n    db-config:\n      id-type: assign-uuid\n      field-strategy: NOT_EMPTY\n  type-handlers-package: cn.cesgroup.cesweb.common.data.handler\n\n# swagger配置\nswagger:\n  title: cesweb swagger api\n  license: Powered By cesgroup\n  licenseUrl: https://www.cesgroup.com.cn/\n  terms-of-service-url: https://www.cesgroup.com.cn/\n  contact:\n    email: shanghaiwc@gmail.com\n    url: https://www.cesgroup.com.cn/about.html\n  authorization:\n    name: Authorization\n    auth-regex: ^.*$\n    authorization-scope-list:\n      - scope: server\n        description: server all\n    token-url-list:\n      - http://${GATEWAY_HOST:cesweb-cloud-gateway}:${GATEWAY_PORT:9999}/auth/oauth/token\n\nsecurity:\n  oauth2:\n    client:\n      ignore-urls:\n        - /actuator/**\n        - /v2/api-docs\n    resource:\n      loadBalanced: true\n      token-info-uri: http://${GATEWAY_HOST:cesweb-cloud-gateway}:${GATEWAY_PORT:9999}/auth/oauth/check_token', 'bca0150b23a912d2626bd74fadcff0be', '2020-07-07 11:17:12', '2020-11-18 22:51:13', NULL, '116.228.54.50', '', '', '通用配置文件', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (18, 'cesweb-cloud-gateway-dev.yml', 'DEFAULT_GROUP', 'spring:\n  cloud:\n    gateway:\n      globalcors:\n        corsConfigurations:\n          \'[/**]\':\n            allowCredentials: true\n            exposedHeaders: \"Content-Disposition,Content-Type,Cache-Control\"\n            allowedHeaders: \"*\"\n            allowedOrigins: \"*\"\n            allowedMethods: \"*\"\n            \nsecurity:\n  encode:\n    key: \'chanlongcesgroup\' # 6ucSqGXE3MpRYoWltrmkQQ==\n\nignore:\n  clients:\n    - cesweb\n  swagger-providers:\n    - cesweb-service-auth\n    - cesweb-monitor-admin\n    - cesweb-monitor-trans\n\nribbon:\n  rule:\n    gray-enabled: true\n\ncaptcha:\n  water:\n    mark: cesweb', '70f12166e56444335eac6f697068024d', '2020-07-08 10:10:44', '2020-09-04 03:50:45', NULL, '58.33.77.18', '', '', '网关服务配置文件', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (34, 'cesweb-monitor-admin-dev.yml', 'DEFAULT_GROUP', 'spring:\n  # 安全配置\n  security:\n    user:\n      name: ENC(iUsB4Zo4TiOkxBIUiPqz3FTjKWVrPNFo) # cesweb\n      password: ENC(aXLl2LrQ/wtMwJjD83/CS6RCr8xWVF95) # cesweb\n  boot:\n    admin:\n      ui:\n        title: \'CESWEB 服务状态监控\'\n        brand: \'CESWEB 服务状态监控\'\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      #显示详细信息\n      show-details: ALWAYS', '2dfcfdd59b372f1ff24814c4850ca1f9', '2020-08-20 00:59:41', '2020-08-20 00:59:41', NULL, '101.231.218.250', '', '', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (35, 'cesweb-monitor-trans-dev.yml', 'DEFAULT_GROUP', '# 页面配置\nspring:\n  mvc:\n    static-path-pattern: /**\n  resources:\n    static-locations: classpath:/static/\n\n\n# LCN 配置\ntm:\n  transaction:\n    netty:\n      delaytime: 5   # 客户端链接最大通讯时间 （秒）\n      hearttime: 15  # 客户端心跳时间   （秒）\n  redis:\n    savemaxtime: 30  # redis 保存时间  （秒）\n  socket:\n    port: 9998       # 通讯端口\n    maxconnection: 1000  #最大链接数\n  compensate:\n    auto: false   #是否自动补偿\n    notifyUrl: http://ip:port/path #补偿结果通知（配消息总线里面）\n    tryTime: 30     # z再次重试时间间隔\n    maxWaitTime: 5000   # 请求超时的最大时间 (毫秒)', '885f5c125f2df7a08692929ccce154be', '2019-04-18 02:54:03', '2019-04-18 02:54:03', NULL, '127.0.0.1', '', '', '分布式事务协调模块', NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (45, 'cesweb-service-auth-dev.yml', 'DEFAULT_GROUP', '# spring security 配置\nsecurity:\n  oauth2:\n    client:\n      client-id: ENC(iUsB4Zo4TiOkxBIUiPqz3FTjKWVrPNFo)\n      client-secret: ENC(iUsB4Zo4TiOkxBIUiPqz3FTjKWVrPNFo)\n      scope: server\n      \n# 数据源\nspring:\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      driver-class-name: com.mysql.cj.jdbc.Driver\n      username: ${MYSQL_USER:cesweb}\n      password: ${MYSQL_PWD:cesweb}\n      url: jdbc:mysql://${MYSQL_HOST:192.168.0.8}:${MYSQL_PORT:3306}/${MYSQL_DB:cesweb_system}?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&allowMultiQueries=true&allowPublicKeyRetrieval=true\n  freemarker:\n    allow-request-override: false\n    allow-session-override: false\n    cache: true\n    charset: UTF-8\n    check-template-location: true\n    content-type: text/html\n    enabled: true\n    expose-request-attributes: false\n    expose-session-attributes: false\n    expose-spring-macro-helpers: true\n    prefer-file-system-access: true\n    suffix: .ftl\n    template-loader-path: classpath:/templates/', '196005a8b01f0a642df9ffc2c56117e6', '2020-08-26 00:08:02', '2020-09-04 03:49:03', NULL, '58.33.77.18', '', '', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (46, 'cesweb-service-workflow-dev.yml', 'DEFAULT_GROUP', '## spring security 配置\nsecurity:\n  oauth2:\n    client:\n      client-id: ENC(iUsB4Zo4TiOkxBIUiPqz3FTjKWVrPNFo)\n      client-secret: ENC(iUsB4Zo4TiOkxBIUiPqz3FTjKWVrPNFo)\n      scope: server\n      ignore-urls:\n        - \'/actuator/**\'\n        - \'/designer/**\'\n        - \'/v2/api-docs\'\nspring:\n  autoconfigure:\n    exclude: org.activiti.spring.boot.SecurityAutoConfiguration\n  activiti:\n    check-process-definitions: false\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      driver-class-name: com.mysql.cj.jdbc.Driver\n      username: ${MYSQL_USER:cesweb}\n      password: ${MYSQL_PWD:cesweb}\n      url: jdbc:mysql://${MYSQL_HOST:106.12.5.251}:${MYSQL_PORT:3306}/${MYSQL_DB:cesweb_workflow}?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&allowMultiQueries=true&allowPublicKeyRetrieval=true\n      \n# 租户表维护\ncesweb:\n  tenant:\n    column: tenant_id\n    tables:\n      - oa_leave_bill', 'bf426edc377c5dbc727ff72f9d140947', '2020-08-26 00:08:32', '2020-09-03 04:33:01', NULL, '101.231.218.252', '', '', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (47, 'cesweb-service-quartz-dev.yml', 'DEFAULT_GROUP', '## spring security 配置\nsecurity:\n  oauth2:\n    client:\n      client-id: ENC(iUsB4Zo4TiOkxBIUiPqz3FTjKWVrPNFo)\n      client-secret: ENC(iUsB4Zo4TiOkxBIUiPqz3FTjKWVrPNFo)\n      scope: server\n\n# 数据源\nspring:\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      driver-class-name: com.mysql.cj.jdbc.Driver\n      username: ${MYSQL_USER:cesweb}\n      password: ${MYSQL_PWD:cesweb}\n      url: jdbc:mysql://${MYSQL_HOST:106.12.5.251}:${MYSQL_PORT:3306}/${MYSQL_DB:cesweb_quartz}?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&allowMultiQueries=true&allowPublicKeyRetrieval=true\n  resources:\n    static-locations: classpath:/static/,classpath:/views/\n  quartz:\n    #相关属性配置\n    properties:\n      org:\n        quartz:\n          scheduler:\n            instanceName: clusteredScheduler\n            instanceId: AUTO\n          jobStore:\n            class: org.quartz.impl.jdbcjobstore.JobStoreTX\n            driverDelegateClass: org.quartz.impl.jdbcjobstore.StdJDBCDelegate\n            tablePrefix: QRTZ_\n            isClustered: true\n            clusterCheckinInterval: 10000\n            useProperties: false\n          threadPool:\n            class: org.quartz.simpl.SimpleThreadPool\n            threadCount: 50\n            threadPriority: 5\n            threadsInheritContextClassLoaderOfInitializingThread: true\n    #数据库方式\n    job-store-type: jdbc\n    #初始化表结构\n    #jdbc:\n    #initialize-schema: never\n\n', 'b2de09417170e119ec79a884eae7913a', '2020-08-26 00:58:24', '2020-09-04 03:51:28', NULL, '58.33.77.18', '', '', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (51, 'cesweb-service-system-dev.yml', 'DEFAULT_GROUP', '## spring security 配置\nsecurity:\n  oauth2:\n    client:\n      client-id: ENC(iUsB4Zo4TiOkxBIUiPqz3FTjKWVrPNFo)\n      client-secret: ENC(iUsB4Zo4TiOkxBIUiPqz3FTjKWVrPNFo)\n      scope: server\n      ignore-urls:\n        - /druid/**\n        - /actuator/**\n        - /v2/api-docs\n\n# 数据源\nspring:\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      driver-class-name: com.mysql.cj.jdbc.Driver\n      username: ${MYSQL_USER:cesweb}\n      password: ${MYSQL_PWD:cesweb}\n      url: jdbc:mysql://${MYSQL_HOST:106.12.5.251}:${MYSQL_PORT:3306}/${MYSQL_DB:cesweb_system}?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&allowMultiQueries=true&allowPublicKeyRetrieval=true\n      stat-view-servlet:\n        enabled: true\n        url-pattern: /druid/*\n        #login-username: admin\n        #login-password: admin\n      filter:\n        stat:\n          enabled: true\n          log-slow-sql: true\n          slow-sql-millis: 10000\n          merge-sql: false\n        wall:\n          config:\n            multi-statement-allow: true\n\n# 文件系统 (提供测试环境，不要乱传)\noss:\n  endpoint: http://minio.chanlong.online\n  access-key: minio\n  secret-key: J978jjkk\n  bucket-name: cesweb\n\n# Logger Config\nlogging:\n  level:\n    cn.cesgroup.cesweb.kernal.system.mapper: debug\n\n# 租户表维护\ncesweb:\n  tenant:\n    column: tenant_id\n    tables:\n      - sys_user\n      - sys_role\n      - sys_menu\n      - sys_dept\n      - sys_log\n      - sys_social_details\n      - sys_dict\n      - sys_dict_item\n      - sys_public_param\n      - sys_log\n      - sys_file\n      - sys_oauth_client_details', '0dda4ed80b4e38548366f1beaa850b4b', '2020-09-04 01:17:28', '2020-09-04 01:24:57', NULL, '101.231.218.251', '', '', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (59, 'cesweb-service-tenant-dev.yml', 'DEFAULT_GROUP', '## spring security 配置\nsecurity:\n  oauth2:\n    client:\n      client-id: ENC(iUsB4Zo4TiOkxBIUiPqz3FTjKWVrPNFo)\n      client-secret: ENC(iUsB4Zo4TiOkxBIUiPqz3FTjKWVrPNFo)\n      scope: server\n      ignore-urls:\n        - \'/actuator/**\'\n        - \'/designer/**\'\n        - \'/v2/api-docs\'\nspring:\n  autoconfigure:\n    exclude: org.activiti.spring.boot.SecurityAutoConfiguration\n  activiti:\n    check-process-definitions: false\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      driver-class-name: com.mysql.cj.jdbc.Driver\n      username: ${MYSQL_USER:cesweb}\n      password: ${MYSQL_PWD:cesweb}\n      url: jdbc:mysql://${MYSQL_HOST:106.12.5.251}:${MYSQL_PORT:3306}/${MYSQL_DB:cesweb_tenant}?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&allowMultiQueries=true&allowPublicKeyRetrieval=true\n      ', '78016d246e23cadb9d9316a376d97ad5', '2020-12-14 19:30:58', '2020-12-14 19:32:06', NULL, '122.97.222.74', '', '', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (61, 'cesweb-service-develope-dev.yml', 'DEFAULT_GROUP', '## spring security 配置\nsecurity:\n  oauth2:\n    client:\n      client-id: ENC(iUsB4Zo4TiOkxBIUiPqz3FTjKWVrPNFo)\n      client-secret: ENC(iUsB4Zo4TiOkxBIUiPqz3FTjKWVrPNFo)\n      scope: server\n      \n# 数据源\nspring:\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      driver-class-name: com.mysql.cj.jdbc.Driver\n      username: ${MYSQL_USER:cesweb}\n      password: ${MYSQL_PWD:cesweb}\n      url: jdbc:mysql://${MYSQL_HOST:106.12.5.251}:${MYSQL_PORT:3306}/${MYSQL_DB:cesweb_develope}?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&allowMultiQueries=true&allowPublicKeyRetrieval=true\n  resources:\n    static-locations: classpath:/static/,classpath:/views/', 'bb96eef7a3faadad63454b13a8d34d1f', '2020-12-20 19:28:52', '2020-12-20 19:34:09', NULL, '122.97.175.133', '', '', '', '', '', 'yaml', '');
COMMIT;

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfoaggr_datagrouptenantdatum` (`data_id`,`group_id`,`tenant_id`,`datum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='增加租户字段';

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfobeta_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_beta';

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfotag_datagrouptenanttag` (`data_id`,`group_id`,`tenant_id`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_tag';

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `tag_name` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`),
  UNIQUE KEY `uk_configtagrelation_configidtag` (`id`,`tag_name`,`tag_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_tag_relation';

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='集群、各Group容量信息表';

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info` (
  `id` bigint(20) unsigned NOT NULL,
  `nid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `src_user` text COLLATE utf8_bin,
  `src_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `op_type` char(10) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`),
  KEY `idx_gmt_create` (`gmt_create`),
  KEY `idx_gmt_modified` (`gmt_modified`),
  KEY `idx_did` (`data_id`)
) ENGINE=InnoDB AUTO_INCREMENT=159 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='多租户改造';

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
BEGIN;
INSERT INTO `his_config_info` VALUES (0, 151, 'cesweb-service-develope-dev.yml', 'DEFAULT_GROUP', '', '## spring security 配置\nsecurity:\n  oauth2:\n    client:\n      client-id: ENC(iUsB4Zo4TiOkxBIUiPqz3FTjKWVrPNFo)\n      client-secret: ENC(iUsB4Zo4TiOkxBIUiPqz3FTjKWVrPNFo)\n      scope: server\n      ignore-urls:\n        - \'/actuator/**\'\n        - \'/designer/**\'\n        - \'/v2/api-docs\'\nspring:\n  autoconfigure:\n    exclude: org.activiti.spring.boot.SecurityAutoConfiguration\n  activiti:\n    check-process-definitions: false\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      driver-class-name: com.mysql.cj.jdbc.Driver\n      username: ${MYSQL_USER:cesweb}\n      password: ${MYSQL_PWD:cesweb}\n      url: jdbc:mysql://${MYSQL_HOST:106.12.5.251}:${MYSQL_PORT:3306}/${MYSQL_DB:cesweb_tenant}?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&allowMultiQueries=true&allowPublicKeyRetrieval=true\n      ', '78016d246e23cadb9d9316a376d97ad5', '2020-12-21 09:28:51', '2020-12-20 19:28:52', NULL, '122.97.175.133', 'I', '');
INSERT INTO `his_config_info` VALUES (61, 152, 'cesweb-service-develope-dev.yml', 'DEFAULT_GROUP', '', '## spring security 配置\\nsecurity:\\n  oauth2:\\n    client:\\n      client-id: ENC(gPFcUOmJm8WqM3k3eSqS0Q==)\\n      client-secret: ENC(gPFcUOmJm8WqM3k3eSqS0Q==)\\n      scope: server\\n# 数据源\\nspring:\\n  datasource:\\n    type: com.alibaba.druid.pool.DruidDataSource\\n    druid:\\n      driver-class-name: com.mysql.cj.jdbc.Driver\\n      username: ${MYSQL_USER:root}\\n      password: ${MYSQL_PWD:root}\\n      url: jdbc:mysql://${MYSQL_HOST:pigx-mysql}:${MYSQL_PORT:3306}/${MYSQL_DB:pigxx_codegen}?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&allowMultiQueries=true&allowPublicKeyRetrieval=true\\n  resources:\\n    static-locations: classpath:/static/,classpath:/views/\\n\\npigx:\\n  tenant:\\n    column: tenant_id\\n    tables:\\n      - gen_datasource_conf\nsecurity:\n  oauth2:\n    client:\n      client-id: ENC(iUsB4Zo4TiOkxBIUiPqz3FTjKWVrPNFo)\n      client-secret: ENC(iUsB4Zo4TiOkxBIUiPqz3FTjKWVrPNFo)\n      scope: server\n      ignore-urls:\n        - \'/actuator/**\'\n        - \'/designer/**\'\n        - \'/v2/api-docs\'\nspring:\n  autoconfigure:\n    exclude: org.activiti.spring.boot.SecurityAutoConfiguration\n  activiti:\n    check-process-definitions: false\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      driver-class-name: com.mysql.cj.jdbc.Driver\n      username: ${MYSQL_USER:cesweb}\n      password: ${MYSQL_PWD:cesweb}\n      url: jdbc:mysql://${MYSQL_HOST:106.12.5.251}:${MYSQL_PORT:3306}/${MYSQL_DB:cesweb_tenant}?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&allowMultiQueries=true&allowPublicKeyRetrieval=true\n      ', '2399dd99d71ae899461768a41d2ebaa3', '2020-12-21 09:31:57', '2020-12-20 19:31:56', NULL, '122.97.175.133', 'U', '');
INSERT INTO `his_config_info` VALUES (3, 153, 'pigx-codegen-dev.yml', 'DEFAULT_GROUP', '', '## spring security 配置\nsecurity:\n  oauth2:\n    client:\n      client-id: ENC(gPFcUOmJm8WqM3k3eSqS0Q==)\n      client-secret: ENC(gPFcUOmJm8WqM3k3eSqS0Q==)\n      scope: server\n# 数据源\nspring:\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      driver-class-name: com.mysql.cj.jdbc.Driver\n      username: ${MYSQL_USER:root}\n      password: ${MYSQL_PWD:root}\n      url: jdbc:mysql://${MYSQL_HOST:pigx-mysql}:${MYSQL_PORT:3306}/${MYSQL_DB:pigxx_codegen}?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&allowMultiQueries=true&allowPublicKeyRetrieval=true\n  resources:\n    static-locations: classpath:/static/,classpath:/views/\n\npigx:\n  tenant:\n    column: tenant_id\n    tables:\n      - gen_datasource_conf', '760cf56fc1d78d50c1170b29e2f96493', '2020-12-21 09:32:06', '2020-12-20 19:32:07', NULL, '122.97.175.133', 'D', '');
INSERT INTO `his_config_info` VALUES (61, 154, 'cesweb-service-develope-dev.yml', 'DEFAULT_GROUP', '', '## spring security 配置\nsecurity:\n  oauth2:\n    client:\n      client-id: ENC(gPFcUOmJm8WqM3k3eSqS0Q==)\n      client-secret: ENC(gPFcUOmJm8WqM3k3eSqS0Q==)\n      scope: server\n# 数据源\nspring:\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      driver-class-name: com.mysql.cj.jdbc.Driver\n      username: ${MYSQL_USER:root}\n      password: ${MYSQL_PWD:root}\n      url: jdbc:mysql://${MYSQL_HOST:pigx-mysql}:${MYSQL_PORT:3306}/${MYSQL_DB:pigxx_codegen}?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&allowMultiQueries=true&allowPublicKeyRetrieval=true\n  resources:\n    static-locations: classpath:/static/,classpath:/views/\n\npigx:\n  tenant:\n    column: tenant_id\n    tables:\n      - gen_datasource_conf', '760cf56fc1d78d50c1170b29e2f96493', '2020-12-21 09:32:47', '2020-12-20 19:32:48', NULL, '122.97.175.133', 'U', '');
INSERT INTO `his_config_info` VALUES (61, 155, 'cesweb-service-develope-dev.yml', 'DEFAULT_GROUP', '', '## spring security 配置\nsecurity:\n  oauth2:\n    client:\n      client-id: ENC(iUsB4Zo4TiOkxBIUiPqz3FTjKWVrPNFo)\n      client-secret: ENC(iUsB4Zo4TiOkxBIUiPqz3FTjKWVrPNFo)\n      scope: server\n# 数据源\nspring:\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      driver-class-name: com.mysql.cj.jdbc.Driver\n      username: ${MYSQL_USER:cesweb}\n      password: ${MYSQL_PWD:cesweb}\n      url: jdbc:mysql://${MYSQL_HOST:pigx-mysql}:${MYSQL_PORT:3306}/${MYSQL_DB:pigxx_codegen}?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&allowMultiQueries=true&allowPublicKeyRetrieval=true\n  resources:\n    static-locations: classpath:/static/,classpath:/views/\n\npigx:\n  tenant:\n    column: tenant_id\n    tables:\n      - gen_datasource_conf', '60c465b530aaa12f8f21b307312f0e03', '2020-12-21 09:34:08', '2020-12-20 19:34:09', NULL, '122.97.175.133', 'U', '');
INSERT INTO `his_config_info` VALUES (27, 156, 'cesweb-kernal-develop-generate-dev.yml', 'DEFAULT_GROUP', '', '## spring security 配置\nsecurity:\n  oauth2:\n    client:\n      client-id: gen # ENC(gPFcUOmJm8WqM3k3eSqS0Q==) gen\n      client-secret: gen # ENC(gPFcUOmJm8WqM3k3eSqS0Q==) gen\n      scope: server\n# 数据源\nspring:\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      driver-class-name: com.mysql.cj.jdbc.Driver\n      username: ${MYSQL_USER:cesweb}\n      password: ${MYSQL_PWD:cesweb}\n      url: jdbc:mysql://${MYSQL_HOST:106.12.5.251}:${MYSQL_PORT:3306}/${MYSQL_DB:cesweb}?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&allowMultiQueries=true&allowPublicKeyRetrieval=true\n  resources:\n    static-locations: classpath:/static/,classpath:/views/\n\ncesweb:\n  tenant:\n    column: tenant_id\n    tables:\n      - gen_datasource_conf', '50a986ddf99b7f687da5af731ca775d8', '2021-01-18 08:50:45', '2021-01-17 18:50:46', NULL, '114.88.111.236', 'D', '');
INSERT INTO `his_config_info` VALUES (0, 157, 'cesweb-service-develope-dev.yml11', 'DEFAULT_GROUP', '', '## spring security 配置\nsecurity:\n  oauth2:\n    client:\n      client-id: ENC(iUsB4Zo4TiOkxBIUiPqz3FTjKWVrPNFo)\n      client-secret: ENC(iUsB4Zo4TiOkxBIUiPqz3FTjKWVrPNFo)\n      scope: server\n      \n# 数据源\nspring:\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      driver-class-name: com.mysql.cj.jdbc.Driver\n      username: ${MYSQL_USER:cesweb}\n      password: ${MYSQL_PWD:cesweb}\n      url: jdbc:mysql://${MYSQL_HOST:106.12.5.251}:${MYSQL_PORT:3306}/${MYSQL_DB:cesweb_develope}?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&allowMultiQueries=true&allowPublicKeyRetrieval=true\n  resources:\n    static-locations: classpath:/static/,classpath:/views/', 'bb96eef7a3faadad63454b13a8d34d1f', '2021-01-18 11:15:39', '2021-01-17 21:15:40', NULL, '114.88.111.236', 'I', '');
INSERT INTO `his_config_info` VALUES (65, 158, 'cesweb-service-develope-dev.yml11', 'DEFAULT_GROUP', '', '## spring security 配置\nsecurity:\n  oauth2:\n    client:\n      client-id: ENC(iUsB4Zo4TiOkxBIUiPqz3FTjKWVrPNFo)\n      client-secret: ENC(iUsB4Zo4TiOkxBIUiPqz3FTjKWVrPNFo)\n      scope: server\n      \n# 数据源\nspring:\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      driver-class-name: com.mysql.cj.jdbc.Driver\n      username: ${MYSQL_USER:cesweb}\n      password: ${MYSQL_PWD:cesweb}\n      url: jdbc:mysql://${MYSQL_HOST:106.12.5.251}:${MYSQL_PORT:3306}/${MYSQL_DB:cesweb_develope}?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&allowMultiQueries=true&allowPublicKeyRetrieval=true\n  resources:\n    static-locations: classpath:/static/,classpath:/views/', 'bb96eef7a3faadad63454b13a8d34d1f', '2021-01-18 11:15:45', '2021-01-17 21:15:46', NULL, '114.88.111.236', 'D', '');
COMMIT;

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions` (
  `role` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `resource` varchar(512) COLLATE utf8_unicode_ci NOT NULL,
  `action` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  UNIQUE KEY `uk_role_permission` (`role`,`resource`,`action`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of permissions
-- ----------------------------
BEGIN;
INSERT INTO `permissions` VALUES ('ROLE_NORMAL', ':*:*', 'r');
COMMIT;

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `username` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `role` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  UNIQUE KEY `idx_user_role` (`username`,`role`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of roles
-- ----------------------------
BEGIN;
INSERT INTO `roles` VALUES ('nacos', 'ROLE_ADMIN');
INSERT INTO `roles` VALUES ('zhouxi', 'ROLE_NORMAL');
COMMIT;

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数',
  `max_aggr_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='租户容量信息表';

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_info_kptenantid` (`kp`,`tenant_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='tenant_info';

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);
INSERT INTO `users` VALUES ('zhouxi', '$2a$10$Ac3/0xPstiN9fM5mUwwtlea883N07Tjti8HJ66mb9XDUNrgyeptVG', 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
