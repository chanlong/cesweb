# cesweb

#### 介绍
微服务框架，演示地址：http://cesweb.chanlong.online

#### 技术栈
 1. SpringBoot -------------------- 2.3.1.RELEASE
 2. SpringCloud ------------------- Hoxton.SR6
 3. Spring-cloud-alibaba ---------- 2.2.1.RELEASE
 4. spring-boot-admin ------------- 2.2.3
 5. Nacos ------------------------- 1.3.2
 6. minio(Open OSS) --------------- 2020-08-08T04:50:06Z
 7. Mysql ------------------------- 5.7.31
 8. Redis ------------------------- 6.0.6
 9. JDK --------------------------- 1.8.0_121
10. Alibaba sentinel-dashboard ---- 1.7.2


#### 软件架构
```
cesweb  
├── _doc ----------------------------- 前端工程聚合模块  
├    ├── db ----------------------------- 数据库初始化脚本  
├  
├── cesweb-web ----------------------- 前端工程聚合模块  
├    ├── cesweb-web-avue ---------------- 基于AVUE的前端工程  
├  
├── cesweb-api ----------------------- 微服务远程调用接口聚合模块  
├    ├── cesweb-api-system -------------- 系统管理微服务远程调用接口模块  
├    ├── cesweb-api-tenant -------------- 租户管理微服务远程调用接口模块（待完善，计划采用独立数据库+独立用户的模式管理租户）  
├  
├── cesweb-bom ----------------------- 统一版本控制模块  
├  
├── cesweb-cloud --------------------- 微服务组件聚合模块  
├    ├── cesweb-cloud-boot -------------- 微服务组件统一依赖模块，供其他模块引用  
├    ├── cesweb-cloud-feign ------------- 微服务远程调用组件  
├    ├── cesweb-cloud-gateway ----------- 网关组件服务，端口（9999）  
├    ├── cesweb-cloud-gray -------------- 微服务灰度路由组件  
├    ├── cesweb-cloud-router ------------ 微服务动态路由组件  
├    ├── cesweb-cloud-sentinel----------- 微服务哨兵服务组件  
├  
├── cesweb-common -------------------- 开发框架公共组件聚合模块  
├    ├── cesweb-common-boot ------------- 公共组件依赖模块，供其他模块引用  
├    ├── cesweb-common-core ------------- 开发框架公共组件核心模块  
├    ├── cesweb-common-data ------------- 数据操作相关模块  
├    ├── cesweb-common-datasource ------- 动态数据源相关模块  
├    ├── cesweb-common-job -------------- 定时任务组件  
├    ├── cesweb-common-log -------------- 日志服务组件  
├    ├── cesweb-common-oss -------------- 对象存储组件  
├    ├── cesweb-common-security --------- 安全服务组件  
├    ├── cesweb-common-sequence --------- 分布式发号器  
├    ├── cesweb-common-swagger ---------- Swagger组件  
├  
├── cesweb-monitor ------------------- 监控服务聚合模块  
├    └── cesweb-monitor-admin ----------- 微服务监控服务，端口（9997）  
├  
├── cesweb-service ------------------- 微服务聚合模块  
├    ├── cesweb-service-auth ------------ 鉴权中心微服务，端口（9998）  
├    ├── cesweb-service-develope -------- 开发平台微服务，端口（9995）  
├    ├── cesweb-service-system ---------- 系统管理微服务，端口（9996）  
├    ├── cesweb-service-quartz ---------- 计划任务微服务，端口（9994）  
├    ├── cesweb-service-tenant ---------- 租户管理微服务（待完善，计划采用独立数据库+独立用户的模式管理租户）  
└    └── cesweb-service-workflow -------- 工作流微服务（待完善，计划使用bpmn.js作为流程设计器，已完成30%，activiti7为工作流引擎）  
```


#### 部署说明
1. 安装 mysql
2. 安装 Redis
3. 安装 Nacos
4. 安装 minio

#### 使用说明
1. 启动 cesweb-cloud-gateway 项目（必须）  
2. 启动 cesweb-service-auth 项目（必须）  
3. 启动 cesweb-service-system 项目（必须）
4. 启动 cesweb-service-develope 项目（可选）  
5. 启动 cesweb-service-quartz 项目（可选）  
6. 启动 cesweb-service-tenant 项目（可选）  
7. 启动 cesweb-service-workflow 项目（可选）  
8. 启动 cesweb-web-avue 前端项目  

##### 补充说明
1、项目中默认配置的地址均可直接使用，如网关、nacos、minio、redis、mysql等  
2、上述服务如需管理和维护，请自行安装相关产品  
3、为了便于开发，开放的数据库权限较高，使用数据库时，请谨慎执行删除等操作（请勿私自泄漏数据库访问信息）  

#### 开发说明
##### 一、新增微服务
1、创建项目  
可使用如下maven命令创建项目，也可使用IDE工具创建项目

```
mvn archetype:generate ^
   -DgroupId=cn.cesgroup.cesweb ^
   -DartifactId=cesweb-service-sample ^
   -Dversion=1.0.0 ^
   -Dpackage=cn.cesgroup.cesweb.service.sample ^
   -DarchetypeGroupId=com.pig4cloud.archetype ^
   -DarchetypeArtifactId=pigx-gen ^
   -DarchetypeVersion=3.11.0 ^
   -DarchetypeCatalog=local
```

##### 二、加载配置文件

##### 三、Swagger授权

##### 四、配置文件加密

#### 参与贡献

1.  Fork 本仓库
2.  管理员注意：请不要直接提交代码到本仓库

# cesweb

#### 介绍
微服务框架，演示地址：http://cesweb.chanlong.online

#### 技术栈
 1. SpringBoot -------------------- 2.3.1.RELEASE
 2. SpringCloud ------------------- Hoxton.SR6
 3. Spring-cloud-alibaba ---------- 2.2.1.RELEASE
 4. spring-boot-admin ------------- 2.2.3
 5. Nacos ------------------------- 1.3.2
 6. minio(Open OSS) --------------- 2020-08-08T04:50:06Z
 7. Mysql ------------------------- 5.7.31
 8. Redis ------------------------- 6.0.6
 9. JDK --------------------------- 1.8.0_121
10. Alibaba sentinel-dashboard ---- 1.7.2


#### 软件架构
```
cesweb  
├── _doc ----------------------------- 前端工程聚合模块  
├    ├── db ----------------------------- 数据库初始化脚本  
├  
├── cesweb-web ----------------------- 前端工程聚合模块  
├    ├── cesweb-web-avue ---------------- 基于AVUE的前端工程  
├  
├── cesweb-api ----------------------- 微服务远程调用接口聚合模块  
├    ├── cesweb-api-system -------------- 系统管理微服务远程调用接口模块  
├    ├── cesweb-api-tenant -------------- 租户管理微服务远程调用接口模块（待完善，计划采用独立数据库+独立用户的模式管理租户）  
├  
├── cesweb-bom ----------------------- 统一版本控制模块  
├  
├── cesweb-cloud --------------------- 微服务组件聚合模块  
├    ├── cesweb-cloud-boot -------------- 微服务组件统一依赖模块，供其他模块引用  
├    ├── cesweb-cloud-feign ------------- 微服务远程调用组件  
├    ├── cesweb-cloud-gateway ----------- 网关组件服务，端口（9999）  
├    ├── cesweb-cloud-gray -------------- 微服务灰度路由组件  
├    ├── cesweb-cloud-router ------------ 微服务动态路由组件  
├    ├── cesweb-cloud-sentinel----------- 微服务哨兵服务组件  
├  
├── cesweb-common -------------------- 开发框架公共组件聚合模块  
├    ├── cesweb-common-boot ------------- 公共组件依赖模块，供其他模块引用  
├    ├── cesweb-common-core ------------- 开发框架公共组件核心模块  
├    ├── cesweb-common-data ------------- 数据操作相关模块  
├    ├── cesweb-common-datasource ------- 动态数据源相关模块  
├    ├── cesweb-common-job -------------- 定时任务组件  
├    ├── cesweb-common-log -------------- 日志服务组件  
├    ├── cesweb-common-oss -------------- 对象存储组件  
├    ├── cesweb-common-security --------- 安全服务组件  
├    ├── cesweb-common-sequence --------- 分布式发号器  
├    ├── cesweb-common-swagger ---------- Swagger组件  
├  
├── cesweb-monitor ------------------- 监控服务聚合模块  
├    └── cesweb-monitor-admin ----------- 微服务监控服务，端口（9997）  
├  
├── cesweb-service ------------------- 微服务聚合模块  
├    ├── cesweb-service-auth ------------ 鉴权中心微服务，端口（9998）  
├    ├── cesweb-service-develope -------- 开发平台微服务，端口（9995）  
├    ├── cesweb-service-system ---------- 系统管理微服务，端口（9996）  
├    ├── cesweb-service-quartz ---------- 计划任务微服务，端口（9994）  
├    ├── cesweb-service-tenant ---------- 租户管理微服务（待完善，计划采用独立数据库+独立用户的模式管理租户）  
└    └── cesweb-service-workflow -------- 工作流微服务（待完善，计划使用bpmn.js作为流程设计器，已完成30%，activiti7为工作流引擎）  
```


#### 部署说明
1. 安装 mysql
2. 安装 Redis
3. 安装 Nacos
4. 安装 minio

#### 使用说明
1. 启动 cesweb-cloud-gateway 项目（必须）  
2. 启动 cesweb-service-auth 项目（必须）  
3. 启动 cesweb-service-system 项目（必须）
4. 启动 cesweb-service-develope 项目（可选）  
5. 启动 cesweb-service-quartz 项目（可选）  
6. 启动 cesweb-service-tenant 项目（可选）  
7. 启动 cesweb-service-workflow 项目（可选）  
8. 启动 cesweb-web-avue 前端项目  

##### 补充说明
1、项目中默认配置的地址均可直接使用，如网关、nacos、minio、redis、mysql等  
2、上述服务如需管理和维护，请自行安装相关产品  
3、为了便于开发，开放的数据库权限较高，使用数据库时，请谨慎执行删除等操作（请勿私自泄漏数据库访问信息）  

#### 开发说明
##### 一、新增微服务
1、创建项目  
可使用如下maven命令创建项目，也可使用IDE工具创建项目

```
mvn archetype:generate ^
   -DgroupId=cn.cesgroup.cesweb ^
   -DartifactId=cesweb-service-sample ^
   -Dversion=1.0.0 ^
   -Dpackage=cn.cesgroup.cesweb.service.sample ^
   -DarchetypeGroupId=com.pig4cloud.archetype ^
   -DarchetypeArtifactId=pigx-gen ^
   -DarchetypeVersion=3.11.0 ^
   -DarchetypeCatalog=local
```

##### 二、加载配置文件

##### 三、Swagger授权

##### 四、配置文件加密

#### 参与贡献

1.  Fork 本仓库
2.  管理员注意：请不要直接提交代码到本仓库

