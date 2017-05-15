--�������ݿ�
CREATE DATABASE seckill;
--ʹ�����ݿ�
use seckill;
--������ɱ����
CREATE TABLE  seckill (
  `seckill_id`   bigint NOT NULL AUTO_INCREMENT COMMENT '���id',
  `name`  varchar(120) NOT NULL COMMENT '��Ʒ����',
`number` int NOT NULL COMMENT 'ʣ������',
`start_time` timestamp NOT NULL COMMENT '��ʼʱ��',
`end_time` timestamp NOT NULL COMMENT '����ʱ��',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
PRIMARY KEY (seckill_id),
key idx_start_time (start_time),
key idx_end_time (end_time),
key idx_create_time (create_time)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='��ɱ����';
--��ʼ������
insert into seckill(name,number,start_time,end_time)
values
('1000Ԫ��ɱС���ֻ�',100,'2017-5-4 00:00:00', '2017-6-4 00:00:00'),
('2000Ԫ��ɱipad',200,'2017-5-4 00:00:00', '2017-6-4 00:00:00'),
('300Ԫ��ɱ������',300,'2017-5-4 00:00:00', '2017-6-4 00:00:00'),
('2000Ԫ��ɱoppo�ֻ�',400,'2017-5-4 00:00:00', '2017-6-4 00:00:00'),
('4000Ԫ��ɱiPhone6',500,'2017-5-4 00:00:00' ,'2017-6-4 00:00:00');
--��ɱ��ϸ��
--�û���Ϣ
create table success_killed(
`seckill_id` bigint NOT NULL AUTO_INCREMENT COMMENT '���id',
`user_phone` bigint NOT NULL COMMENT '��ɱ���ֻ���',
`state` tinyint NOT NULL DEFAULT -1 COMMENT '-1 ��Ч  0����ɱ�ɹ�  1���Ѹ��� 2���ѷ���',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',

PRIMARY KEY (seckill_id,user_phone),/*��������*/
key idx_create_time (create_time)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='��ɱ�ɹ���Ϣ��';
