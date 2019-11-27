/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : shopping

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2019-04-08 16:25:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for gm_address
-- ----------------------------
DROP TABLE IF EXISTS `gm_address`;
CREATE TABLE `gm_address` (
  `id` varchar(36) NOT NULL,
  `userId` varchar(36) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `county` varchar(255) DEFAULT NULL,
  `particular` varchar(255) DEFAULT NULL,
  `iphone` varchar(255) DEFAULT NULL,
  `consignee` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gm_address
-- ----------------------------
INSERT INTO `gm_address` VALUES ('8dc32d53ae5e4211a39fbed1f12a15e7', '812150ca85ac49c0853611a5fd2f6c37', '浙江省', '南昌市', '青山湖区', 'xx街道', '18977445566', '肖大爷', '0');
INSERT INTO `gm_address` VALUES ('d94d6cc5813c4192b8ccac36ae0831ef', '812150ca85ac49c0853611a5fd2f6c37', '陕西省', '九江市', '青山湖区', 'aaaa', '18977445566', '18977445566', '1');

-- ----------------------------
-- Table structure for gm_cart
-- ----------------------------
DROP TABLE IF EXISTS `gm_cart`;
CREATE TABLE `gm_cart` (
  `id` varchar(36) NOT NULL,
  `productId` varchar(36) DEFAULT NULL,
  `userId` varchar(36) DEFAULT NULL,
  `productName` varchar(255) DEFAULT NULL,
  `price` float(10,0) DEFAULT NULL,
  `proPrice` float(10,0) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `imgUrl` varchar(255) DEFAULT NULL,
  `parameter` varchar(255) DEFAULT NULL,
  `specification` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gm_cart
-- ----------------------------

-- ----------------------------
-- Table structure for gm_category
-- ----------------------------
DROP TABLE IF EXISTS `gm_category`;
CREATE TABLE `gm_category` (
  `id` varchar(36) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `parentId` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gm_category
-- ----------------------------
INSERT INTO `gm_category` VALUES ('13bd6086e41c485f872146355e45e274', '葡萄酒', '2019-04-08 15:43:42', 'f754fb4d58c8488488a78123ce1b0d67');
INSERT INTO `gm_category` VALUES ('13dfe8624b8340d6af4170ff37838e4c', '方便食品', '2019-04-08 15:43:05', '27cd48b702c14c66a2ffc4e9b555cf30');
INSERT INTO `gm_category` VALUES ('1e5180fae4ec4129a6d0bc41a3b91967', '红酒', '2019-04-08 15:43:35', 'f754fb4d58c8488488a78123ce1b0d67');
INSERT INTO `gm_category` VALUES ('24ae793abb4242dcaf6ade4f2675c07c', '洗漱用品', '2019-03-05 16:53:42', '1738605e0423481192a68dc98373a8d4');
INSERT INTO `gm_category` VALUES ('2ac705df1a7a428797f1f20292c2b129', '卫生用品', '2019-03-05 16:54:09', '1738605e0423481192a68dc98373a8d4');
INSERT INTO `gm_category` VALUES ('3e81fe914131499784e247a9ae710c8a', '冲饮', '2019-04-08 15:43:12', '27cd48b702c14c66a2ffc4e9b555cf30');
INSERT INTO `gm_category` VALUES ('5252b79c530642579dbfbd17bfdcf26a', '白酒', '2019-04-08 15:43:52', 'f754fb4d58c8488488a78123ce1b0d67');
INSERT INTO `gm_category` VALUES ('738f7614739e49c7987ac1af65612c4f', '啤酒', '2019-04-08 15:43:25', 'f754fb4d58c8488488a78123ce1b0d67');
INSERT INTO `gm_category` VALUES ('956d6611939a453cb35ca1d4d70c37a4', '干果', '2019-04-08 15:42:48', '27cd48b702c14c66a2ffc4e9b555cf30');
INSERT INTO `gm_category` VALUES ('cab3aaea23a94fbf8ead49fbedd73daa', '药酒', '2019-04-08 15:43:47', 'f754fb4d58c8488488a78123ce1b0d67');
INSERT INTO `gm_category` VALUES ('d86c6712fb2b4b77b1ca21cb55628cde', '面包', '2019-04-08 15:43:18', '27cd48b702c14c66a2ffc4e9b555cf30');

-- ----------------------------
-- Table structure for gm_evaluate
-- ----------------------------
DROP TABLE IF EXISTS `gm_evaluate`;
CREATE TABLE `gm_evaluate` (
  `id` varchar(36) NOT NULL,
  `productId` varchar(36) DEFAULT NULL,
  `userId` varchar(36) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `content` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gm_evaluate
-- ----------------------------
INSERT INTO `gm_evaluate` VALUES ('3ad7666127044cf7816019855ecb5a73', 'd28c3127aa8a4c05b83a964031989419', '812150ca85ac49c0853611a5fd2f6c37', '2019-03-03 19:03:38', 'henghao ');
INSERT INTO `gm_evaluate` VALUES ('7ac31cd254fc4caa80de9fb78e3b350e', 'd28c3127aa8a4c05b83a964031989419', '812150ca85ac49c0853611a5fd2f6c37', '2019-03-03 18:54:41', '這個花henhao');
INSERT INTO `gm_evaluate` VALUES ('9e6df56476a149f6a085212b096892d6', '4fb8f23b27534ef19fbf578d9b1bf84a', '812150ca85ac49c0853611a5fd2f6c37', '2019-04-08 11:16:29', 'zhenhap ');

-- ----------------------------
-- Table structure for gm_log
-- ----------------------------
DROP TABLE IF EXISTS `gm_log`;
CREATE TABLE `gm_log` (
  `id` varchar(36) COLLATE utf8_bin NOT NULL,
  `userName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `operation` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of gm_log
-- ----------------------------
INSERT INTO `gm_log` VALUES ('18ff36fcafcf4a7697982ce6740abfd0', 'admin', '登录操作', '2019-03-04 09:03:25');
INSERT INTO `gm_log` VALUES ('197d8282adcc4fc39921fe26a7cc166f', 'admin', '登录操作', '2019-04-08 09:10:44');
INSERT INTO `gm_log` VALUES ('1e378efe11aa4378afe81c60a17b9859', 'admin', '登录操作', '2019-03-03 16:45:45');
INSERT INTO `gm_log` VALUES ('25971e1ec5f145e58b45cbd5577961a2', 'admin', '登录操作', '2019-03-03 18:24:09');
INSERT INTO `gm_log` VALUES ('2bbc46bc769343e49a919d77e207dca0', 'K001', '登录操作', '2019-03-04 15:58:00');
INSERT INTO `gm_log` VALUES ('2e88c16b220b456dbc486657c89a8915', 'admin', '登录操作', '2019-04-08 15:37:39');
INSERT INTO `gm_log` VALUES ('2ecc495e6fe94bfbbf07bbc88ec63112', 'admin', '修改个人信息', '2019-04-08 11:04:09');
INSERT INTO `gm_log` VALUES ('3ad2b0c422274cf4bf1a00a7fdd36548', 'admin', '登录操作', '2019-03-03 16:35:20');
INSERT INTO `gm_log` VALUES ('430202246dd9477b98f00940733fffab', 'admin', '修改个人信息', '2019-04-08 11:18:44');
INSERT INTO `gm_log` VALUES ('4aca8bc0dc6b40ca9e329609f9026b1d', 'admin', '登录操作', '2019-03-01 15:06:11');
INSERT INTO `gm_log` VALUES ('616ac831537f48beb80e04c4f7b454c4', 'admin', '登录操作', '2019-03-03 19:02:16');
INSERT INTO `gm_log` VALUES ('7cfdb76d93fc4fdba7a157d06d758b60', 'admin', '登录操作', '2019-03-03 18:52:51');
INSERT INTO `gm_log` VALUES ('8b844d424ec549208e915682d1f468d7', 'admin', '修改个人信息', '2019-04-08 16:18:03');
INSERT INTO `gm_log` VALUES ('9513d6d13c0841e6b0f741677db4be04', 'admin', '登录操作', '2019-04-08 16:02:06');
INSERT INTO `gm_log` VALUES ('9b8a39cf8d9441419e2426e48667234a', 'admin', '登录操作', '2019-04-08 11:13:13');
INSERT INTO `gm_log` VALUES ('9f10fe59d4b14cdeaa5e4c663487fb63', 'admin', '登录操作', '2019-03-04 09:21:33');
INSERT INTO `gm_log` VALUES ('a2d28850c3dc48a08e06076c0f50e617', 'admin', '登录操作', '2019-03-04 09:16:30');
INSERT INTO `gm_log` VALUES ('a50e00f8a6de47878da65126db7127ae', 'admin', '登录操作', '2019-03-04 08:23:42');
INSERT INTO `gm_log` VALUES ('a5f5c0cc13754991a15b928d941a617b', 'admin', '登录操作', '2019-03-01 14:33:15');
INSERT INTO `gm_log` VALUES ('a8296d11e6784cd7a4e824a2d1497e34', 'admin', '登录操作', '2019-04-08 08:55:16');
INSERT INTO `gm_log` VALUES ('af5f1574053b4f6cbe2e9ef4fdc21658', 'admin', '登录操作', '2019-03-03 14:16:20');
INSERT INTO `gm_log` VALUES ('b139f9179c5b49bfa60c78c91bf610cb', 'admin', '登录操作', '2019-03-03 14:37:48');
INSERT INTO `gm_log` VALUES ('c8bcd41f3a5a4dbcaff7867e1faaf0b4', 'admin', '登录操作', '2019-03-05 16:18:10');
INSERT INTO `gm_log` VALUES ('d28babd0e2304e858c4286ec60ea066e', 'admin', '登录操作', '2019-03-03 18:55:08');
INSERT INTO `gm_log` VALUES ('dfe71b341d814350baf1880aff369ec5', 'admin', '登录操作', '2019-03-04 15:57:42');
INSERT INTO `gm_log` VALUES ('ebc1800b4cd84d16a803b5dcc6537bc9', 'admin', '登录操作', '2019-03-03 09:18:38');
INSERT INTO `gm_log` VALUES ('ee753759a9c241f990b6b3bf8d6c4e0f', 'admin', '修改个人信息', '2019-02-27 18:39:44');
INSERT INTO `gm_log` VALUES ('ef41648ee934491aacbdcaf539cfd0ba', 'admin', '登录操作', '2019-03-04 16:02:55');
INSERT INTO `gm_log` VALUES ('fc2fa6dcf6ec41fd994f423eb63a4ac5', 'admin', '登录操作', '2019-03-03 18:08:47');

-- ----------------------------
-- Table structure for gm_order
-- ----------------------------
DROP TABLE IF EXISTS `gm_order`;
CREATE TABLE `gm_order` (
  `id` varchar(36) NOT NULL,
  `userId` varchar(36) DEFAULT NULL,
  `addressId` varchar(36) DEFAULT NULL,
  `productId` varchar(36) DEFAULT NULL,
  `orderUuid` varchar(255) DEFAULT NULL,
  `orderTime` datetime DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `zumPrice` float DEFAULT NULL,
  `imgUrl` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gm_order
-- ----------------------------
INSERT INTO `gm_order` VALUES ('44250101fc3846aa93085d043816b22a', '812150ca85ac49c0853611a5fd2f6c37', 'd94d6cc5813c4192b8ccac36ae0831ef', '5283e749b91a410ea44995ef87615319', '1000001355473708', '2019-04-08 16:18:10', '1', '432', '/image/58e736ddNc1181853.jpg', '待发货');
INSERT INTO `gm_order` VALUES ('c9a3cebf2dfb47a2a16688f763ea216e', '812150ca85ac49c0853611a5fd2f6c37', 'd94d6cc5813c4192b8ccac36ae0831ef', 'b59cf21d9d4348fda369a2e5c3214c42', '1000001655827761', '2019-04-08 16:18:33', '1', '23', '/image/5b3ee4c8Nb7f50b3f.jpg', '待发货');

-- ----------------------------
-- Table structure for gm_permission
-- ----------------------------
DROP TABLE IF EXISTS `gm_permission`;
CREATE TABLE `gm_permission` (
  `permissionId` varchar(36) NOT NULL,
  `permissionName` varchar(255) NOT NULL,
  `permissionMark` varchar(255) DEFAULT NULL,
  `permissionType` varchar(255) DEFAULT NULL,
  `parentId` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `available` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `lastTime` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gm_permission
-- ----------------------------
INSERT INTO `gm_permission` VALUES ('9', '系统设置', 'menu:sysSet', 'menu', '5', '/wbem/', '5', '1', '2017-10-03 15:22:50', '2017-10-03 15:22:50', '');
INSERT INTO `gm_permission` VALUES ('10', '资源管理', 'menu:sysSet:org:org', 'menu', '9', '/menu/menuManagerHouse.do', '0', '1', '2018-01-22 11:39:03', '2018-01-22 11:39:03', '');
INSERT INTO `gm_permission` VALUES ('12', '权限管理', 'menu:sysSet:module:module:module', 'menu', '9', '/module/moduleManagerHouse.do', '30', '1', '2018-01-22 11:38:43', '2018-01-22 11:38:43', '');
INSERT INTO `gm_permission` VALUES ('58', '用户管理', 'menu:sysSet:user:user', 'menu', '9', '/user/userManagerHouse.do', '5', '1', '2016-06-13 16:23:30', '2016-06-13 16:23:30', '');
INSERT INTO `gm_permission` VALUES ('483', '商品管理', 'menu:product', 'menu', '482', '', '5', '1', '2018-06-10 19:54:28', '2018-06-10 19:54:28', '');
INSERT INTO `gm_permission` VALUES ('5', '系统管理', 'subsystem:system', 'subsystem', '0', '', '1000', '1', '2018-01-22 11:37:36', '2018-01-22 11:37:36', 'nothing');
INSERT INTO `gm_permission` VALUES ('529', '分类信息', 'menu:product:classify', 'menu', '483', '/category/index.do', '5', '1', '2018-09-05 09:01:18', '2018-09-05 09:01:20', null);
INSERT INTO `gm_permission` VALUES ('482', '基础管理', 'subsystem:agent', 'subsystem', '0', null, '7', '1', '2018-10-19 08:37:38', '2018-09-28 08:37:40', null);
INSERT INTO `gm_permission` VALUES ('72aaac0231a74896997cb59c7455c411', '日志管理', 'menu:sysSet:log', 'menu', '9', '/log/index.do', null, '1', '2019-02-27 18:34:48', null, null);
INSERT INTO `gm_permission` VALUES ('884bd8e254b54613a37678a1d63bae6a', '商品信息', 'menu:product:info', 'menu', '483', '/product/index.do', null, '1', '2019-03-01 15:01:49', null, null);
INSERT INTO `gm_permission` VALUES ('55017c8ca02b4e408ec9896f65d98623', '订单管理', 'menu:product:order', 'menu', '483', '/order/index.do', null, '1', '2019-03-03 15:10:44', null, null);

-- ----------------------------
-- Table structure for gm_product
-- ----------------------------
DROP TABLE IF EXISTS `gm_product`;
CREATE TABLE `gm_product` (
  `id` varchar(36) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `proPrice` float DEFAULT NULL,
  `price` float DEFAULT NULL,
  `sales` int(11) DEFAULT NULL,
  `specification` varchar(255) DEFAULT NULL,
  `parameter` varchar(255) DEFAULT NULL,
  `imgUrl` varchar(255) DEFAULT NULL,
  `dasImg` text,
  `createdTime` datetime DEFAULT NULL,
  `categoryId` varchar(36) DEFAULT NULL,
  `userId` varchar(36) DEFAULT NULL,
  `season` varchar(255) DEFAULT NULL,
  `ptCategoryId` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gm_product
-- ----------------------------
INSERT INTO `gm_product` VALUES ('5283e749b91a410ea44995ef87615319', '法国进口红酒 拉菲（LAFITE）传奇波尔多干红葡萄酒', '432', '458', '1', '波尔多红;波亚克红', '容量：750mL', '/image/58e736ddNc1181853.jpg', ';/image/5b10fa3dN33215941.jpg;/image/5b1e3218N397be644.jpg;/image/5b1e3219N6e4f2a50.jpg', '2019-04-08 16:10:13', '13bd6086e41c485f872146355e45e274', '812150ca85ac49c0853611a5fd2f6c37', null, 'f754fb4d58c8488488a78123ce1b0d67');
INSERT INTO `gm_product` VALUES ('b59cf21d9d4348fda369a2e5c3214c42', '立白 茶籽洗洁精双瓶省心装 1.45kg*2瓶', '22.9', '23.9', '1', '3.04kg', '1.45kg;1.45kg*2', '/image/5b3ee4c8Nb7f50b3f.jpg', ';/image/c297061aaa749c6c.jpg;/image/QQ截图20190408161239.png;/image/QQ截图20190408161248.png', '2019-04-08 16:13:01', '2ac705df1a7a428797f1f20292c2b129', '812150ca85ac49c0853611a5fd2f6c37', null, '1738605e0423481192a68dc98373a8d4');
INSERT INTO `gm_product` VALUES ('c75d22d9f48d44b58c35f217a0ef35f8', '三只松鼠 坚果炒货 ', '34.9', '35.9', '0', '0.2kg', '0.2kg*2', '/image/54b0789e5f108b7a.jpg', ';/image/ad50d56ef2fa43f8.jpg;/image/95178243a0570f47.jpg;/image/5bb867d8Nb37d8c1c.jpg;/image/45c57d7d01e4fd32.jpg', '2019-04-08 16:15:18', '956d6611939a453cb35ca1d4d70c37a4', '812150ca85ac49c0853611a5fd2f6c37', null, '27cd48b702c14c66a2ffc4e9b555cf30');
INSERT INTO `gm_product` VALUES ('fbdabf28dbec4eab9a28643a92e1593d', '五粮液52度普五 500ML（新老包装随机发货）', '999', '1099', '0', '优惠套装1;优惠套装2', '普装500ml;礼鉴正品', '/image/01c78d613e673803.jpg', ';/image/01c78d613e673803.jpg;/image/QQ截图20190408160726.png;/image/QQ截图20190408160744.png;/image/QQ截图20190408160751.png', '2019-04-08 16:08:12', '5252b79c530642579dbfbd17bfdcf26a', '812150ca85ac49c0853611a5fd2f6c37', null, 'f754fb4d58c8488488a78123ce1b0d67');

-- ----------------------------
-- Table structure for gm_ptcategory
-- ----------------------------
DROP TABLE IF EXISTS `gm_ptcategory`;
CREATE TABLE `gm_ptcategory` (
  `id` varchar(36) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gm_ptcategory
-- ----------------------------
INSERT INTO `gm_ptcategory` VALUES ('1738605e0423481192a68dc98373a8d4', '日用品', '2019-03-04 09:33:12');
INSERT INTO `gm_ptcategory` VALUES ('27cd48b702c14c66a2ffc4e9b555cf30', '零食', '2019-03-04 09:32:42');
INSERT INTO `gm_ptcategory` VALUES ('7e520e3a92f64a7c887312b522a58e67', '五金', '2019-03-05 16:52:54');
INSERT INTO `gm_ptcategory` VALUES ('b27cc59d066049539dfb929aac5f32b9', '厨房用具', '2019-03-05 16:52:48');
INSERT INTO `gm_ptcategory` VALUES ('f754fb4d58c8488488a78123ce1b0d67', '烟酒', '2019-04-08 15:41:06');

-- ----------------------------
-- Table structure for gm_role
-- ----------------------------
DROP TABLE IF EXISTS `gm_role`;
CREATE TABLE `gm_role` (
  `id` varchar(36) COLLATE utf8_bin NOT NULL,
  `role` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `description` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of gm_role
-- ----------------------------
INSERT INTO `gm_role` VALUES ('2', '超级管理员', '拥有所有权限');
INSERT INTO `gm_role` VALUES ('3', '管理员', '所拥有权限由超级管理员决定');
INSERT INTO `gm_role` VALUES ('4', '卖家', null);
INSERT INTO `gm_role` VALUES ('5', '买家', null);

-- ----------------------------
-- Table structure for gm_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `gm_role_permission`;
CREATE TABLE `gm_role_permission` (
  `id` int(200) NOT NULL AUTO_INCREMENT,
  `roleId` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `permissionId` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=502 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of gm_role_permission
-- ----------------------------
INSERT INTO `gm_role_permission` VALUES ('170', '2', '109');
INSERT INTO `gm_role_permission` VALUES ('171', '2', '334');
INSERT INTO `gm_role_permission` VALUES ('172', '2', '336');
INSERT INTO `gm_role_permission` VALUES ('173', '2', '338');
INSERT INTO `gm_role_permission` VALUES ('174', '2', '343');
INSERT INTO `gm_role_permission` VALUES ('175', '2', '344');
INSERT INTO `gm_role_permission` VALUES ('176', '2', '486');
INSERT INTO `gm_role_permission` VALUES ('180', '2', '9');
INSERT INTO `gm_role_permission` VALUES ('181', '2', '10');
INSERT INTO `gm_role_permission` VALUES ('182', '2', '12');
INSERT INTO `gm_role_permission` VALUES ('185', '2', '58');
INSERT INTO `gm_role_permission` VALUES ('187', '2', '337');
INSERT INTO `gm_role_permission` VALUES ('188', '2', '339');
INSERT INTO `gm_role_permission` VALUES ('189', '2', '340');
INSERT INTO `gm_role_permission` VALUES ('190', '2', '347');
INSERT INTO `gm_role_permission` VALUES ('191', '2', '348');
INSERT INTO `gm_role_permission` VALUES ('192', '2', '349');
INSERT INTO `gm_role_permission` VALUES ('193', '2', '350');
INSERT INTO `gm_role_permission` VALUES ('194', '2', '483');
INSERT INTO `gm_role_permission` VALUES ('195', '2', '5');
INSERT INTO `gm_role_permission` VALUES ('196', '2', '351');
INSERT INTO `gm_role_permission` VALUES ('197', '2', '352');
INSERT INTO `gm_role_permission` VALUES ('198', '2', '353');
INSERT INTO `gm_role_permission` VALUES ('199', '2', '354');
INSERT INTO `gm_role_permission` VALUES ('200', '2', '345');
INSERT INTO `gm_role_permission` VALUES ('202', '2', '540');
INSERT INTO `gm_role_permission` VALUES ('203', '2', '544');
INSERT INTO `gm_role_permission` VALUES ('204', '2', '482');
INSERT INTO `gm_role_permission` VALUES ('205', '2', '529');
INSERT INTO `gm_role_permission` VALUES ('230', '3', '9');
INSERT INTO `gm_role_permission` VALUES ('231', '3', '12');
INSERT INTO `gm_role_permission` VALUES ('232', '3', '58');
INSERT INTO `gm_role_permission` VALUES ('233', '3', '483');
INSERT INTO `gm_role_permission` VALUES ('234', '3', '5');
INSERT INTO `gm_role_permission` VALUES ('235', '3', '529');
INSERT INTO `gm_role_permission` VALUES ('236', '3', '482');
INSERT INTO `gm_role_permission` VALUES ('276', '5', '482');
INSERT INTO `gm_role_permission` VALUES ('289', '6', '482');
INSERT INTO `gm_role_permission` VALUES ('290', '7', '483');
INSERT INTO `gm_role_permission` VALUES ('291', '7', '529');
INSERT INTO `gm_role_permission` VALUES ('292', '7', '482');
INSERT INTO `gm_role_permission` VALUES ('296', '3', '10');
INSERT INTO `gm_role_permission` VALUES ('312', '8', '483');
INSERT INTO `gm_role_permission` VALUES ('313', '8', '529');
INSERT INTO `gm_role_permission` VALUES ('314', '8', '482');
INSERT INTO `gm_role_permission` VALUES ('315', '6', '483');
INSERT INTO `gm_role_permission` VALUES ('324', '9', '483');
INSERT INTO `gm_role_permission` VALUES ('325', '9', '529');
INSERT INTO `gm_role_permission` VALUES ('326', '9', '482');
INSERT INTO `gm_role_permission` VALUES ('450', '6', '529');
INSERT INTO `gm_role_permission` VALUES ('470', '5', '483');
INSERT INTO `gm_role_permission` VALUES ('487', '4', '483');
INSERT INTO `gm_role_permission` VALUES ('488', '4', '482');
INSERT INTO `gm_role_permission` VALUES ('491', '4', '529');
INSERT INTO `gm_role_permission` VALUES ('493', '5', '96960dc23a86479396c8e701fa49861a');
INSERT INTO `gm_role_permission` VALUES ('494', '5', '529');
INSERT INTO `gm_role_permission` VALUES ('495', '3', '1b65feeca45945aea6cbd0fc223eec5e');
INSERT INTO `gm_role_permission` VALUES ('496', '3', '5b8b02e6f4b64553bd04b1a0a8ce0677');
INSERT INTO `gm_role_permission` VALUES ('497', '5', '31769a41feaf48e39bed1b697c37ad30');
INSERT INTO `gm_role_permission` VALUES ('498', '3', '72aaac0231a74896997cb59c7455c411');
INSERT INTO `gm_role_permission` VALUES ('499', '3', '884bd8e254b54613a37678a1d63bae6a');
INSERT INTO `gm_role_permission` VALUES ('500', '3', '55017c8ca02b4e408ec9896f65d98623');

-- ----------------------------
-- Table structure for gm_supplier
-- ----------------------------
DROP TABLE IF EXISTS `gm_supplier`;
CREATE TABLE `gm_supplier` (
  `id` varchar(36) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `iphone` varchar(255) DEFAULT NULL,
  `uuId` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `bank` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gm_supplier
-- ----------------------------
INSERT INTO `gm_supplier` VALUES ('7613bda5038641e182444ec302c6b7ff', '中标花卉有限公司', '丽尔地', 'ASF-1856AD6', '河北省邯郸市xx街道', '232302-1988-4566-2233-03', 'A-16589-D456');

-- ----------------------------
-- Table structure for gm_user
-- ----------------------------
DROP TABLE IF EXISTS `gm_user`;
CREATE TABLE `gm_user` (
  `id` varchar(36) COLLATE utf8_bin NOT NULL,
  `userName` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `salt` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `iphone` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `platform` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `updatedDate` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '0表示已删除',
  `realName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `sex` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `address` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `imgUrl` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `balance` float(255,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of gm_user
-- ----------------------------
INSERT INTO `gm_user` VALUES ('17c546c1e5f9467b81f47c3746f62a3a', 'C001', '1cc4760a2c25f9cb33df44ed9b5558ad', '498aaa36-89c7-4adc-a6be-89eb2250370c', '15035520122', '115@qq.com', null, '2019-02-27 17:37:33', null, '1', '李窗口', '男', 'xx省mm市ss街道', null, '0');
INSERT INTO `gm_user` VALUES ('58ae970b1e70443c9a93a659289c153d', 'K001', '72408dddd12ed80523e2cc44a834caa9', 'ff592426-d736-4e54-aa87-d81fdb857e07', '15035520122', '115@qq.com', null, '2019-02-27 17:38:01', '2019-03-04 15:58:00', '1', '张库管', '男', 'xx省mm市ss街道', null, '0');
INSERT INTO `gm_user` VALUES ('767fc6290a6e4ddab65650aebc88ca99', 'H001', '322a7481ad1c178a4587eddac417752d', '2acd18c9-6eda-4d78-bbbe-02ad658769d5', '15035520122', '115@qq.com', null, '2019-02-27 17:38:30', null, '1', '赵患者', '男', 'xx省mm市ss街道x', null, '0');
INSERT INTO `gm_user` VALUES ('812150ca85ac49c0853611a5fd2f6c37', 'admin', 'fa5e84ae1000b44d164def3ec4f0d76f', '3c025a99-ee24-42fa-851d-dd585abf91d4', '18966554411', '115@qq.com', null, '2018-10-24 17:07:35', '2019-04-08 16:02:06', '1', '王钰尧1', '男', 'xx省mm市ss街道', '/image/1552920002293.jpg@196w_126h_90q_1e_1c.jpg', '1545');

-- ----------------------------
-- Table structure for gm_user_role
-- ----------------------------
DROP TABLE IF EXISTS `gm_user_role`;
CREATE TABLE `gm_user_role` (
  `id` int(200) NOT NULL AUTO_INCREMENT,
  `userId` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `roleId` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of gm_user_role
-- ----------------------------
INSERT INTO `gm_user_role` VALUES ('14', 'b67bb7c71d694dd084869c24b0f61d70', '2');
INSERT INTO `gm_user_role` VALUES ('24', '812150ca85ac49c0853611a5fd2f6c37', '3');
INSERT INTO `gm_user_role` VALUES ('39', 'bc18f78236b544ba92531b2ac63a2808', '4');
INSERT INTO `gm_user_role` VALUES ('40', '852751327f244b7ca8aa1baf394fb594', '4');
INSERT INTO `gm_user_role` VALUES ('41', '17d183be6b3d4c7a9665d9a35394bd88', '4');
INSERT INTO `gm_user_role` VALUES ('42', '4228b7f0c012489c8ede5668eaf1e63e', '5');
INSERT INTO `gm_user_role` VALUES ('43', '862697a022ab48278bb13874b5440970', '5');
INSERT INTO `gm_user_role` VALUES ('44', '15f5964ea96b4823bb57efbe17e44e9c', '4');
INSERT INTO `gm_user_role` VALUES ('45', '32c01fc528fe475d9afd364a5a9fbd43', '4');
INSERT INTO `gm_user_role` VALUES ('46', 'de4683fa4684481e8ee2fb18f804019a', '4');
INSERT INTO `gm_user_role` VALUES ('48', '544d3e1b84a64f0698bc16978cc311d4', '4');
INSERT INTO `gm_user_role` VALUES ('49', '578b27bc3125465aaed73abdb47ff894', '4');
INSERT INTO `gm_user_role` VALUES ('57', '039c511c70834332a0492fc8ccd625d8', '5');
INSERT INTO `gm_user_role` VALUES ('58', '814e804a1df749699b8e2626f5fc33a5', '4');
INSERT INTO `gm_user_role` VALUES ('60', '11dec3d4761b4dc39e1809ed8526cf6b', '5');
INSERT INTO `gm_user_role` VALUES ('61', 'b5cb0da5b4ae4c1fa9d47e3a3a704644', '5');
INSERT INTO `gm_user_role` VALUES ('63', '767fc6290a6e4ddab65650aebc88ca99', '4');
INSERT INTO `gm_user_role` VALUES ('64', '17c546c1e5f9467b81f47c3746f62a3a', '5');
INSERT INTO `gm_user_role` VALUES ('65', '58ae970b1e70443c9a93a659289c153d', '6');
INSERT INTO `gm_user_role` VALUES ('66', '3dd1ba3987c0444a8c9c2bf14dbf58fe', '7');
INSERT INTO `gm_user_role` VALUES ('67', '897c7c3b882744aaadf74734b2ee2873', '7');
