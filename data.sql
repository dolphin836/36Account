CREATE TABLE `member` (
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '用户名',
    `password` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '密码',
    `client` TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户注册客户端：0 - 系统创建、1 - PC web、2 - 移动 web、3 - iOS、4 - Android、5 - 微信小程序',
    `application` TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户注册应用：0 - 系统创建、1 - Photo、2 - Password、3 - Awesome、4 - College',
    `insert_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录的创建时间',
    `last_update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录的更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_username` (`username`),
    KEY `idx_client` (`client`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户身份信息记录表';

CREATE TABLE `member_content` (
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `member_id` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'MemberId',
    `nickname` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '昵称',
    `mail` VARCHAR(255) NOT NULL DEFAULT '' COMMENT 'Mail',
    `avatar` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '头像',
    `sign` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '签名',
    `insert_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录的创建时间',
    `last_update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录的更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_member` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户其他信息记录表';

CREATE TABLE `member_sign_in` (
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `member_id` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'MemberId',
    `client` TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户登录客户端：0 - 系统创建、1 - PC web、2 - 移动 web、3 - iOS、4 - Android、5 - 微信小程序',
    `application` TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户登录应用：0 - 系统创建、1 - Photo、2 - Password、3 - Awesome、4 - College',
    `insert_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录的创建时间',
    `last_update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录的更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_member` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录记录表';
