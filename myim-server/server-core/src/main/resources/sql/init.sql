use myim;
CREATE TABLE `im_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID编号_自增',
  `user_name` varchar(128) NOT NULL DEFAULT '' COMMENT '用户名称',
  `nick_name` varchar(32) NOT NULL DEFAULT '' COMMENT '别名',
  `phone` INT(11) NOT NULL DEFAULT 0 COMMENT '手机号码',
  `photo` varchar(256) NOT NULL DEFAULT '' COMMENT '头像',
  `login_name` varchar(128) NOT NULL DEFAULT '' COMMENT '注册名称(唯一)',
  `login_password` varchar(128) NOT NULL DEFAULT '' COMMENT '登录密码',
  `user_status` int(2) NOT NULL DEFAULT 0 COMMENT '用户状态',
  `service_alias_name` varchar(128) NOT NULL DEFAULT '' COMMENT '调用服务名称别名',
  `ext_char1` varchar(1024) NOT NULL DEFAULT '' COMMENT '扩展字段Char1',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据最后更新时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE `uni_login_name`(`login_name`),
  KEY `idx_user_name` (`user_name`),
  KEY `idx_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';

CREATE TABLE `im_user_single_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID编号_自增',
  `im_user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '关联用户id',
  `category_name` varchar(32) NOT NULL DEFAULT '' COMMENT '单聊分类名称',
  `count` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '分类下好友数量',
  `ext_char1` varchar(1024) NOT NULL DEFAULT '' COMMENT '扩展字段Char1',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据最后更新时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE  `uni_im_user_id_category_name`(`im_user_id`,`category_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户单聊分组';

CREATE TABLE `im_user_single_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID编号_自增',
  `im_user_single_category_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '关联单聊分类id',
  `im_user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '关联用户id，好友id',
  `ext_char1` varchar(1024) NOT NULL DEFAULT '' COMMENT '扩展字段Char1',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据最后更新时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_im_user_id` (`im_user_id`),
  KEY `idx_im_user_single_category_id` (`im_user_single_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='单聊分组用户关系';


CREATE TABLE `im_user_group_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID编号_自增',
  `im_user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '关联用户id',
  `group_name` varchar(512) NOT NULL DEFAULT '' COMMENT '群聊名称',
  `group_desc` varchar(2048) NOT NULL DEFAULT '' COMMENT '群聊公告',
  `count` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '群内数量',
  `ext_char1` varchar(1024) NOT NULL DEFAULT '' COMMENT '扩展字段Char1',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据最后更新时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE `uni_group_name`(`group_name`),
  KEY `idx_im_user_id` (`im_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户群聊群信息';

CREATE TABLE `im_user_group_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID编号_自增',
  `im_user_group_category_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '关联群聊id',
  `im_user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '关联用户id，一个群内用户',
  `ext_char1` varchar(1024) NOT NULL DEFAULT '' COMMENT '扩展字段Char1',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据最后更新时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_im_user_id` (`im_user_id`),
  KEY `idx_im_user_group_category_id` (`im_user_group_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='群聊关系群'