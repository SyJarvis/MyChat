聊天程序

- dao: 数据访问层
- bean 实体类-->数据表
- socket  服务端，网络通信
- ui 图形界面
- utils  工具类

```sql
create database mychat charset=utf8mb4;
create table user(
   id int primary key auto_increment not null comment '主键',
   nickname varchar(20) not null comment '昵称',
   avatar varchar(300) comment '头像',
   gender varchar(5) comment '性别 男 女 保密',
   mobile varchar(20) comment '手机号',
   email varchar(100) comment'邮箱',
   login_name varchar(20) unique comment '登录用户名',
   login_pwd varchar(32) comment '登录密码',
   login_salt varchar(32) comment '登录密码的随机加密密钥',
   status tinyint(1) comment '1:有效 0:无效',
   created_time datetime comment '创建时间',
   updated_time datetime comment '更新时间'
);

create table user(
     id int primary key auto_increment not null comment '主键',
     username varchar(20) not null comment '昵称',
     password varchar(32) comment '登录密码',
     status tinyint(1) comment '1:有效 0:无效',
     created_time datetime comment '创建时间',
     updated_time datetime comment '更新时间'
);
```