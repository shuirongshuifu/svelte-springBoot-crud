-- person_manage.t_people definition

CREATE TABLE `t_people` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '一条数据的唯一标识',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名字字段',
  `age` int DEFAULT NULL COMMENT '年龄字段',
  `home` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '家乡字段',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注字段',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '逻辑删除字段，0代表为未删除，1代表已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=297 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT;