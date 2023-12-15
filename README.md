# 新华三杯比赛官网后端


## 项目结构

```
..
└── ink
    └── whi
        └── project
            ├── ApplicationRun.java
            ├── common  # 公共模块
            │   ├── annotition
            │   ├── aspect
            │   ├── config
            │   ├── context
            │   ├── domain
            │   ├── enums
            │   ├── exception
            │   ├── rest_template
            │   └── utils
            ├── controller  # controller层
            │   ├── AdminRestController.java
            │   ├── AnnouncementRestController.java
            │   ├── CompetitionController.java
            │   ├── LoginRestController.java
            │   ├── RankController.java
            │   ├── RegisterRestController.java
            │   ├── TeamRestController.java
            │   ├── base
            │   └── helper
            ├── hook      
            │   ├── filter
            │   └── interceptor
            └── modules         # dao层
                ├── announcement
                ├── competition
                ├── rank
                ├── team
                └── user
```

## 项目启动

### 1. 打包项目

```shell
mvn clean package
```

### 2. 启动项目

```shell
mvn spring-boot:run
```