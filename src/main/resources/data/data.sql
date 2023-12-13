drop database if exists `zhishu-match`;
create database `zhishu-match`;
use `zhishu-match`;

create table `zhishu-match`.user
(
    id          int unsigned auto_increment comment '主键ID'
        primary key,
    account     varchar(64)  default ''                not null comment '账号',
    password    varchar(128) default ''                not null comment '密码',
    deleted     tinyint      default 0                 not null comment '是否删除',
    create_time timestamp    default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time timestamp    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '最后更新时间',
    constraint uk_account
        unique (account)
)
    comment '用户登录表' collate = utf8mb4_general_ci;

create table `zhishu-match`.user_info
(
    id          int unsigned auto_increment comment '主键ID'
        primary key,
    user_id     int unsigned  default '0'               not null comment '用户ID',
    user_name   varchar(50)   default ''                not null comment '用户名',
    real_name   varchar(50)   default ''                not null comment '真实姓名',
    student_id  varchar(50)   default ''                not null comment '学号',
    picture     varchar(128)  default ''                not null comment '用户图像',
    phone       varchar(100)  default ''                not null comment '电话号码',
    college     varchar(50)   default ''                not null comment '学院',
    profile     varchar(225)  default ''                not null comment '个人简介',
    user_role   int           default 0                 not null comment '0 普通用户 1 超管',
    extend      varchar(1024) default ''                not null comment '扩展字段',
    ip          varchar(100)  default ''                not null comment '用户的ip信息',
    major       varchar(100)  default ''                not null comment '专业',
    deleted     tinyint       default 0                 not null comment '是否删除',
    create_time timestamp     default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time timestamp     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '最后更新时间'
)
    comment '用户个人信息表' collate = utf8mb4_general_ci;

create index key_user_id
    on `zhishu-match`.user_info (user_id);

create table `zhishu-match`.announcement
(
    id          int unsigned auto_increment comment '主键ID'
        primary key,
    user_id     int unsigned  default '0'               not null comment '创建用户',
    title       varchar(300)  default ''                not null comment '标题',
    summary     varchar(1024) default ''                not null comment '摘要',
    content     longtext                                not null comment '内容',
    status      tinyint       default 0                 not null comment '0-未发布 1-已发布',
    deleted     tinyint       default 0                 not null comment '是否删除',
    create_time timestamp     default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time timestamp     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '最后更新时间'
)
    comment '公告表';

create table `zhishu-match`.competition
(
    id              int auto_increment comment '主键'
        primary key,
    name            varchar(255)                        null comment '比赛名称',
    description     varchar(255)                        null comment '介绍',
    type            int                                 null comment '比赛类型 1-个人赛 2-团体赛',
    start_time      timestamp default CURRENT_TIMESTAMP null comment '比赛开始时间',
    end_time        timestamp default CURRENT_TIMESTAMP null comment '比赛结束时间',
    signup_deadline timestamp default CURRENT_TIMESTAMP null comment '报名截止时间',
    max_member      int                                 null comment '最大人数',
    deleted         tinyint   default 0                 not null comment '是否删除',
    create_time     timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time     timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '最后更新时间'
)
    charset = utf8mb3
    row_format = DYNAMIC;

create table `zhishu-match`.`rank`
(
    id             int auto_increment comment '主键'
        primary key,
    user_id        int                                 null comment '用户id',
    score          double                              null comment '分数',
    status         varchar(255)                        null comment '状态',
    competition_id int                                 null comment '比赛id',
    create_time    timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time    timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '最后更新时间',
    deleted        tinyint   default 0                 not null comment '是否删除'
)
    charset = utf8mb3
    row_format = DYNAMIC;

create table `zhishu-match`.team
(
    id             int unsigned auto_increment comment '业务id'
        primary key,
    competition_id int                                   null comment '比赛id',
    name           varchar(60) default ''                not null comment '团队名称',
    captain        int         default 0                 not null comment '队长id',
    deleted        tinyint     default 0                 not null comment '是否删除',
    create_time    timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time    timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '最后更新时间',
    constraint uk_competition_name
        unique (competition_id, name)
)
    comment '团队表';

create table `zhishu-match`.team_member
(
    id          int unsigned auto_increment comment '业务id'
        primary key,
    team_id     int                                 null comment '团队id',
    user_id     int       default 0                 not null comment '用户id',
    status      int       default 0                 not null comment '组队状态 0-未加入 1-待通过 2-已加入',
    create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '最后更新时间',
    constraint uk_team_user
        unique (team_id, user_id)
)
    comment '组队表';


create table `zhishu-match`.register
(
    id             int unsigned auto_increment comment '业务id'
        primary key,
    competition_id int                                 null comment '比赛id',
    user_id        int       default 0                 not null comment '用户id',
    deleted        tinyint   default 0                 not null comment '是否删除',
    create_time    timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time    timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '最后更新时间',
    constraint uk_competition_user
        unique (competition_id, user_id)
)
    comment '报名表';