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
    photo       varchar(128)  default ''                not null comment '用户图像',
    student_id  varchar(50)   default ''                not null comment '学号',
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
    comment '用户个人信息表' engine = InnoDB
                             collate = utf8mb4_general_ci;

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
    id          int auto_increment
        primary key,
    name        varchar(255)                        null,
    description varchar(255)                        null,
    deleted     tinyint   default 0                 not null comment '是否删除',
    create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '最后更新时间'
)
    charset = utf8mb3
    row_format = DYNAMIC;

create table `zhishu-match`.`rank`
(
    id             int                                 not null comment '主键'
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
    id          int unsigned auto_increment comment '业务id'
        primary key,
    competition_id int                                 null comment '比赛id',
    name        varchar(60) default ''                not null comment '团队名称',
    captain     int         default 0                 not null comment '队长id',
    deleted     tinyint     default 0                 not null comment '是否删除',
    create_time timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '最后更新时间'
)
    comment '团队表';

create table `zhishu-match`.team_member
(
    id          int unsigned auto_increment comment '业务id'
        primary key,
    team_id int                                 null comment '团队id',
    user_id     int         default 0                 not null comment '用户id',
    create_time timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '最后更新时间'
)
    comment '组队表';

create table `zhishu-match`.register
(
    id          int unsigned auto_increment comment '业务id'
        primary key,
    competition_id int                                 null comment '比赛id',
    user_id     int         default 0                 not null comment '用户id',
    deleted     tinyint     default 0                 not null comment '是否删除',
    create_time timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '最后更新时间'
)
    comment '报名表';