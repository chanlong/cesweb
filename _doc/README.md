# 项目结构
cesweb 主工程
cesweb-api 微服务调用接口聚合模块
cesweb-api-system system微服务调用接口，供其他微服务调用
cesweb-bom 统一版本控制模块
cesweb-cloud 微服务组件聚合模块
cesweb-cloud-boot 微服务组件依赖模块，供其他模块引用
cesweb-cloud-feign 服务调用组件
cesweb-cloud-gateway 网关组件服务，端口（9999）
cesweb-cloud-gray 微服务灰度路由组件
cesweb-cloud-router 微服务动态路由组件
cesweb-cloud-sentinel 微服务哨兵服务组件

cesweb-common 公共组件聚合模块
cesweb-common-boot 公共组件依赖模块，供其他模块引用
cesweb-common-core 公共工具类核心组件
cesweb-common-data 数据操作模块
cesweb-common-datasource 动态数据源(动态切换)
cesweb-common-job 定时任务组件
cesweb-common-log 日志工具组件
cesweb-common-oss 对象存储组件
cesweb-common-security 安全服务组件
cesweb-common-sequence 分布式发号器
cesweb-common-swagger swagger组件

cesweb-monitor 监控服务聚合模块
cesweb-monitor-admin “spring boot admin”监控及redis监控，端口（9997）
cesweb-monitor-trans 分布式事务监控（待完善），端口（5002）

cesweb-service 微服务聚合模块
cesweb-service-auth 鉴权中心微服务，端口（9998）
cesweb-service-develope 开发平台微服务，端口（9995）
cesweb-service-system 系统管理微服务，端口（9996）
cesweb-service-quartz 计划任务微服务，端口（9994）
cesweb-service-tenant 租户管理微服务（待完善）
cesweb-service-workflow 工作流微服务（待完善）