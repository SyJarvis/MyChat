聊天程序

- com.taihua.dao: 数据访问层
- com.taihua.entity 实体类-->数据表
- com.taihua.socket  服务端，网络通信
- com.taihua.ui 图形界面
- com.taihua.utils  工具类

```sql
create database mychat charset=utf8mb4;
create table user(
     id int primary key auto_increment not null comment '主键',
     username varchar(20) not null comment '昵称',
     password varchar(32) comment '登录密码',
     status tinyint(1) comment '1:有效 0:无效',
     created_time datetime comment '创建时间',
     updated_time datetime comment '更新时间'
);
```

不用去考虑群组的问题，只有两个模式，一个是群聊，一个是私聊，
现在先实现私聊，即一对一，不要考虑第三个客户端的事