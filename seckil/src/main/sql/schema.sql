--创建数据库
CREATE DATABASE seckill;
--使用数据库
use seckill;
--创建秒杀库存表
CREATE TABLE  seckill (
  `seckill_id`   bigint NOT NULL AUTO_INCREMENT COMMENT '库存id',
  `name`  varchar(120) NOT NULL COMMENT '商品名称',
`number` int NOT NULL COMMENT '剩余数量',
`start_time` timestamp NOT NULL COMMENT '开始时间',
`end_time` timestamp NOT NULL COMMENT '结束时间',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (seckill_id),
key idx_start_time (start_time),
key idx_end_time (end_time),
key idx_create_time (create_time)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';
--初始化数据
insert into seckill(name,number,start_time,end_time)
values
('1000元秒杀小米手机',100,'2017-5-4 00:00:00', '2017-6-4 00:00:00'),
('2000元秒杀ipad',200,'2017-5-4 00:00:00', '2017-6-4 00:00:00'),
('300元秒杀拍立得',300,'2017-5-4 00:00:00', '2017-6-4 00:00:00'),
('2000元秒杀oppo手机',400,'2017-5-4 00:00:00', '2017-6-4 00:00:00'),
('4000元秒杀iPhone6',500,'2017-5-4 00:00:00' ,'2017-6-4 00:00:00');
--秒杀明细表
--用户信息
create table success_killed(
`seckill_id` bigint NOT NULL AUTO_INCREMENT COMMENT '库存id',
`user_phone` bigint NOT NULL COMMENT '秒杀者手机号',
`state` tinyint NOT NULL DEFAULT -1 COMMENT '-1 无效  0：秒杀成功  1：已付款 2：已发货',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',

PRIMARY KEY (seckill_id,user_phone),/*联合主键*/
key idx_create_time (create_time)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='秒杀成功信息表';
