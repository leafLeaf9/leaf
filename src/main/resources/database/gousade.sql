DROP
DATABASE IF EXISTS gousade;
/* 数据库采用UTF-8 */
CREATE
DATABASE IF NOT EXISTS gousade
CHARACTER SET utf8mb4
COLLATE utf8mb4_general_ci;

/*
 Navicat Premium Data Transfer

 Source Server         : gousade
 Source Server Type    : MySQL
 Source Server Version : 50556
 Source Host           : 101.132.118.130:9733
 Source Schema         : gousade

 Target Server Type    : MySQL
 Target Server Version : 50556
 File Encoding         : 65001

 Date: 15/01/2021 16:49:24
*/

USE
`gousade`;
/*SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;*/

-- ----------------------------
-- Table structure for aliyun_video
-- ----------------------------
DROP TABLE IF EXISTS `aliyun_video`;
CREATE TABLE `aliyun_video`
(
    `id`                  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `video_id`            varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `video_original_name` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频名称',
    `duration`            varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频时长(秒)',
    `status`              varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Empty未上传 Transcoding转码中  Normal正常',
    `size`                bigint(20) NULL DEFAULT NULL COMMENT '视频源文件大小(字节)',
    `create_time`         datetime NULL DEFAULT NULL,
    `update_time`         datetime NULL DEFAULT NULL,
    `delflag`             tinyint(1) NULL DEFAULT NULL,
    `version`             bigint(20) NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `index_id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for attachment_general
-- ----------------------------
DROP TABLE IF EXISTS `attachment_general`;
CREATE TABLE `attachment_general`
(
    `id`          varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `attach_id`   varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `attach_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `attach_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `attach_size` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `attach_path` varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `create_time` datetime NULL DEFAULT NULL,
    `delflag`     tinyint(1) NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of attachment_general
-- ----------------------------
INSERT INTO `attachment_general`
VALUES ('0009f964fafd40999e4c5bc9cc7a3612', NULL, 'u=2599492247,533981307&fm=26&gp=0', 'jpg', '30KB',
        'D:\\gousadeFiles\\generalfile\\20200901\\ee25356f9d664bddbb030d1efadad6ae.jpg', '2020-09-01 10:12:31', 0);
INSERT INTO `attachment_general`
VALUES ('02f44c773cb945f1b862b2628f72d936', NULL, '新建文本文档 (4)', 'txt', '0B',
        'D:\\gousadeFiles\\generalfile\\20200907\\06ec141dc27543fb82032bef6d3befd7.txt', '2020-09-07 17:17:43', 0);
INSERT INTO `attachment_general`
VALUES ('053fad2e8dab46d58d0527be31bf774a', NULL, 'jdk-8u261-windows-x64', 'exe', '166MB',
        'D:\\gousadeFiles\\generalfile\\20200921\\eb01800d8a424c6884d1b8823520c7f7.exe', '2020-09-21 19:14:14', 0);
INSERT INTO `attachment_general`
VALUES ('0ab97f5e3e7742aea7d5bf920bbdf835', '911a8e15eebf4d569f8a8299e5ad99d5', 'u=2599492247,533981307&fm=26&gp=0',
        'jpg', '30KB', 'D:\\gousadeFiles\\generalfile\\20200911\\a4ddc948513b4bed905d85d6d48e83a8.jpg',
        '2020-09-11 14:52:52', 0);
INSERT INTO `attachment_general`
VALUES ('1b29705e795341bd9466114018ae4acb', NULL, 'jdk-8u261-windows-x64', 'exe', '166MB',
        'D:\\gousadeFiles\\generalfile\\20200921\\124baab292be42fb9a3b3fa997f48dc4.exe', '2020-09-21 19:20:00', 0);
INSERT INTO `attachment_general`
VALUES ('2d7f3fd9871640b38835317c2528fa42', NULL, 'logback-spring', 'xml', '12KB',
        'D:\\gousadeFiles\\generalfile\\20200902\\e47edcfc5b6c461bb6a06a0ca314b743.xml', '2020-09-02 22:19:41', 0);
INSERT INTO `attachment_general`
VALUES ('309f5f71521649589c6cd00d9f247d81', NULL, '新建文本文档', 'txt', '0B',
        'D:\\gousadeFiles\\generalfile\\20200901\\b56a4235fa144504bfb07468d0633d02.txt', '2020-09-01 10:12:31', 0);
INSERT INTO `attachment_general`
VALUES ('3d4660312fa74da1b209ef5173fc59ab', NULL, 'jdk-11.0.8_windows-x64_bin', 'exe', '151MB',
        'D:\\gousadeFiles\\generalfile\\20200921\\07d294fd8e6e4406ae4d5bc43b4bc32e.exe', '2020-09-21 19:18:51', 0);
INSERT INTO `attachment_general`
VALUES ('3e4da1a7f25d4791992c78fb4312125b', NULL, '我的青春恋爱物语果然有问题。短篇集', '7z', '19MB',
        'D:\\gousadeFiles\\generalfile\\20201021\\987d6fb426a340c3ba947720fbc26d4e.7z', '2020-10-21 19:03:37', 0);
INSERT INTO `attachment_general`
VALUES ('41445771cf4e41dd8ae8992118e41d09', NULL, 'Tohsaka Rin', 'jpg', '110KB',
        'D:\\gousadeFiles\\generalfile\\20201016\\e6a254d265044e2eaf90d80450032c76.jpg', '2020-10-16 10:14:10', 0);
INSERT INTO `attachment_general`
VALUES ('56db8d82dd3f43ffbf80c5fffe46a675', NULL, '我的青春恋爱物语果然有问题。短篇集', '7z', '19MB',
        'D:\\gousadeFiles\\generalfile\\20201021\\3e24145766d0450ab1c240ee125d4349.7z', '2020-10-21 19:04:46', 0);
INSERT INTO `attachment_general`
VALUES ('59b2fa3e874548dc85851ef6ab3676b9', NULL, 'u=2599492247,533981307&fm=26&gp=0', 'jpg', '30KB',
        'D:\\gousadeFiles\\generalfile\\20200902\\ce028d8ff0f842a18cfaad6f65c63445.jpg', '2020-09-02 14:38:33', 0);
INSERT INTO `attachment_general`
VALUES ('629cbadb8c654a149914d34e52f2140a', '911a8e15eebf4d569f8a8299e5ad99d5', 'u=2599492247,533981307&fm=26&gp=0',
        'jpg', '30KB', 'D:\\gousadeFiles\\generalfile\\20200901\\17b32ac0aa574d40a225a04eb0d58402.jpg',
        '2020-09-01 22:08:34', 0);
INSERT INTO `attachment_general`
VALUES ('64a403952135472992db550737eac6cd', NULL, 'jdk-8u261-windows-x64', 'exe', '166MB',
        'D:\\gousadeFiles\\generalfile\\20200921\\562f6afae8624d069ccc885272b98796.exe', '2020-09-21 19:15:02', 0);
INSERT INTO `attachment_general`
VALUES ('69732331690646ad8fe7e6680247f241', NULL, '新建文本文档 (2)', 'txt', '2KB',
        'D:\\gousadeFiles\\generalfile\\20200901\\e08c0437b95a40f581e1de3ad150a111.txt', '2020-09-01 10:12:31', 0);
INSERT INTO `attachment_general`
VALUES ('6ce200ce17524d3e8f641e39a639c2c1', NULL, '我的青春恋爱物语果然有问题。短篇集', '7z', '19MB',
        'D:\\gousadeFiles\\generalfile\\20201021\\4d2af6ef193545fc911c8683ad8f8146.7z', '2020-10-21 19:04:11', 0);
INSERT INTO `attachment_general`
VALUES ('7414aa149bd44da5997becb42a4ee67c', NULL, '新建文本文档 (3)', 'txt', '0B',
        'D:\\gousadeFiles\\generalfile\\20200902\\f6ec8a9b1b104f6ebf7b04ef5b3fd246.txt', '2020-09-02 14:37:25', 0);
INSERT INTO `attachment_general`
VALUES ('7955cd148fcf48d288f04a10fd1441c4', '911a8e15eebf4d569f8a8299e5ad99d5', 'u=894585891,2109799313&fm=26&gp=0',
        'jpg', '33KB', 'D:\\gousadeFiles\\generalfile\\20200907\\d84de889445b454b99b15ccee35c735a.jpg',
        '2020-09-07 17:17:25', 0);
INSERT INTO `attachment_general`
VALUES ('7b44437d0c00417dbb0c200b9f48ba05', '911a8e15eebf4d569f8a8299e5ad99d5', 'u=894585891,2109799313&fm=26&gp=0',
        'jpg', '33KB', 'D:\\gousadeFiles\\generalfile\\20200923\\036929c2fe38427f984d41ca69c1b4eb.jpg',
        '2020-09-23 17:12:38', 0);
INSERT INTO `attachment_general`
VALUES ('7f4991f7c3a348b79247b8e11ed3d86d', '911a8e15eebf4d569f8a8299e5ad99d5', 'u=2599492247,533981307&fm=26&gp=0',
        'jpg', '30KB', 'D:\\gousadeFiles\\generalfile\\20200907\\5d957ff75d02460faf3f5f51652a2b00.jpg',
        '2020-09-07 17:17:33', 0);
INSERT INTO `attachment_general`
VALUES ('8ec754c44c0e48828a8c957907a8a045', '911a8e15eebf4d569f8a8299e5ad99d5', 'u=894585891,2109799313&fm=26&gp=0',
        'jpg', '33KB', 'D:\\gousadeFiles\\generalfile\\20200910\\3127c14a30404bdbb450cf4c6f98b59c.jpg',
        '2020-09-10 16:04:42', 0);
INSERT INTO `attachment_general`
VALUES ('99d8daf744f24fa99a016d795dd8134a', NULL, 'u=894585891,2109799313&fm=26&gp=0', 'jpg', '33KB',
        'D:\\gousadeFiles\\generalfile\\20200901\\72744c3366854f4aade2d1e2b0756e1a.jpg', '2020-09-01 10:12:31', 0);
INSERT INTO `attachment_general`
VALUES ('9f78ee9a39094c5ea2fdd7492207f4d7', NULL, '6d1ed1848611db806d7d40660d84fd1af0e7dfda', 'jpg', '110KB',
        'D:\\gousadeFiles\\generalfile\\20210110\\0d76a2fd3e8e4f0781ae114c6ed92603.jpg', '2021-01-10 18:10:20', 0);
INSERT INTO `attachment_general`
VALUES ('a0d9d98fdfb24792a2c517ea14e484c6', NULL, 'jdk-11.0.8_windows-x64_bin', 'exe', '151MB',
        'D:\\gousadeFiles\\generalfile\\20200921\\69d5bf31260d47efb0c54171a07fe6a3.exe', '2020-09-21 19:12:32', 0);
INSERT INTO `attachment_general`
VALUES ('b405b94ed47b414eb408c102a90d29a1', NULL, '我的青春恋爱物语果然有问题 第3季 完 第12集 1080P nvl', 'mp4', '170MB',
        'D:\\gousadeFiles\\generalfile\\20201021\\2c4742329e1f4c98a9df9a06bf2d2726.mp4', '2020-10-21 19:01:51', 0);
INSERT INTO `attachment_general`
VALUES ('b4989bddd8144de5a9eaebbd5f83d309', NULL, '我的青春恋爱物语果然有问题。短篇集', '7z', '19MB',
        'D:\\gousadeFiles\\generalfile\\20201021\\869f414d580148f38475b82cccbaf0dc.7z', '2020-10-21 19:03:07', 0);
INSERT INTO `attachment_general`
VALUES ('b73d81af09bd4bfd9fe178f9bcc84de0', NULL, '我的青春恋爱物语果然有问题。短篇集', '7z', '19MB',
        'D:\\gousadeFiles\\generalfile\\20201021\\f15d5d15b02d4b278f3fbec37cb405ab.7z', '2020-10-21 19:04:57', 0);
INSERT INTO `attachment_general`
VALUES ('b93bdfcf0b97496db5a03c4cf52a7750', NULL, 'git概念', 'png', '205KB',
        'D:\\gousadeFiles\\generalfile\\20200902\\1f50d9928612403da0fdbb233faa90c0.png', '2020-09-02 22:20:09', 0);
INSERT INTO `attachment_general`
VALUES ('c816d66a33804ff99650d32fd819b47b', NULL, 'u=2599492247,533981307&fm=26&gp=0', 'jpg', '30KB',
        'D:\\gousadeFiles\\generalfile\\20200901\\8f24182d-b1ec-405e-be96-e5a27eb5f205.jpg', '2020-09-01 10:06:37', 0);
INSERT INTO `attachment_general`
VALUES ('c83fc59685a74d1798f0212551fae8cb', NULL, 'lombok', 'jar', '1MB',
        'D:\\gousadeFiles\\generalfile\\20200921\\e083a0c2382e492797b5564458afa972.jar', '2020-09-21 19:19:46', 0);
INSERT INTO `attachment_general`
VALUES ('cb2ced50d1c04c9fabe543943a277246', NULL, 'jdk-11.0.8_windows-x64_bin', 'exe', '151MB',
        'D:\\gousadeFiles\\generalfile\\20200921\\196cd84ebecf4983a27cd43f7ae1e06b.exe', '2020-09-21 19:15:27', 0);
INSERT INTO `attachment_general`
VALUES ('dcf506b91f03463a97b1e3ba42f3b883', '911a8e15eebf4d569f8a8299e5ad99d5', 'u=2599492247,533981307&fm=26&gp=0',
        'jpg', '30KB', 'D:\\gousadeFiles\\generalfile\\20200901\\43f501f7ec0f45019110add7dddac661.jpg',
        '2020-09-01 10:11:52', 0);
INSERT INTO `attachment_general`
VALUES ('f856467cdb874faa84aa02f22daa00eb', NULL, 'Tohsaka Rin', 'jpg', '110KB',
        'D:\\gousadeFiles\\generalfile\\20200901\\5c20fa4467be4b6bb260cd12e8e12a58.jpg', '2020-09-01 10:12:31', 0);
INSERT INTO `attachment_general`
VALUES ('fe3a9bcd71924a63bca18fade91a301a', '911a8e15eebf4d569f8a8299e5ad99d5', 'u=2599492247,533981307&fm=26&gp=0',
        'jpg', '30KB', 'D:\\gousadeFiles\\generalfile\\20200904\\a13d83b75d504f1f9f184cff32cd9b85.jpg',
        '2020-09-04 10:29:55', 0);

-- ----------------------------
-- Table structure for easy_excel_data
-- ----------------------------
DROP TABLE IF EXISTS `easy_excel_data`;
CREATE TABLE `easy_excel_data`
(
    `id`          varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `string`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字符串标题',
    `double_data` decimal(65, 4) NULL DEFAULT NULL COMMENT '数字标题',
    `date`        datetime NULL DEFAULT NULL COMMENT '日期标题',
    `delflag`     tinyint(1) NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `index_id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of easy_excel_data
-- ----------------------------
INSERT INTO `easy_excel_data`
VALUES ('02d845533c63284efa7383936df278c1', '字符串3', 0.5600, '2020-09-28 11:03:36', 0);
INSERT INTO `easy_excel_data`
VALUES ('02f011aeeca93380914cc13c5fdfc2ce', '字符串6', 0.5600, '2020-09-27 10:51:33', 0);
INSERT INTO `easy_excel_data`
VALUES ('03ee1c2a10e909138fa5d0ec6b2a5c30', '字符串0', 0.5600, '2020-09-27 14:25:38', 0);
INSERT INTO `easy_excel_data`
VALUES ('054e76a2075020881698bdc100cf17a3', '字符串8', 0.5600, '2020-09-27 09:06:03', 0);
INSERT INTO `easy_excel_data`
VALUES ('05afe19bbe383c56ea73ddfaf77256f7', '字符串9', 0.5600, '2020-09-30 16:36:36', 0);
INSERT INTO `easy_excel_data`
VALUES ('06508d1380beb06995a1375618f3f075', '字符串1', 0.5600, '2020-09-28 11:03:36', 0);
INSERT INTO `easy_excel_data`
VALUES ('0802dc30ba98ff626db7968dbe01fdc1', '字符串7', 0.5600, '2020-09-27 09:06:03', 0);
INSERT INTO `easy_excel_data`
VALUES ('0928c20af4b5aeef747199d4ccde09c2', '字符串8', 0.5600, '2020-09-27 14:28:00', 0);
INSERT INTO `easy_excel_data`
VALUES ('096fc675ccc8205b9c8f04cd9b36d921', '字符串3', 0.5600, '2020-09-27 10:51:33', 0);
INSERT INTO `easy_excel_data`
VALUES ('12624361ba683ab3b292e4b249d53200', '字符串4', 0.5600, '2020-09-28 10:50:29', 0);
INSERT INTO `easy_excel_data`
VALUES ('162834fd9badf12c8ebcd60c72c5fcdf', '字符串2', 0.5600, '2020-09-28 10:50:29', 0);
INSERT INTO `easy_excel_data`
VALUES ('17b2b21e5cb52cba01d5236d167ca65e', '字符串9', 0.5600, '2020-09-28 11:03:36', 0);
INSERT INTO `easy_excel_data`
VALUES ('1819c2940853998e9b9433fbbc5dcec3', '字符串1', 0.5600, '2020-09-27 14:24:56', 0);
INSERT INTO `easy_excel_data`
VALUES ('1db19785a9d2e602acc96306ad137f1d', '字符串6', 0.5600, '2020-09-27 09:06:03', 0);
INSERT INTO `easy_excel_data`
VALUES ('207bac261e7385f2a301e6df5f536ba9', '字符串1', 0.5600, '2020-09-27 14:28:37', 0);
INSERT INTO `easy_excel_data`
VALUES ('20dc755141e4709b9a0cbb9f0ed51196', '字符串0', 0.5600, '2020-09-27 10:51:33', 0);
INSERT INTO `easy_excel_data`
VALUES ('27fda840ed38f74fc58574c97b813ec0', '字符串1', 0.5600, '2020-09-27 14:28:00', 0);
INSERT INTO `easy_excel_data`
VALUES ('291f48724abdb53788b1e37999059c41', '字符串8', 0.5600, '2020-09-27 14:28:37', 0);
INSERT INTO `easy_excel_data`
VALUES ('2f8578afc432890b6f7573bb9dd30879', '字符串9', 0.5600, '2020-09-28 10:50:29', 0);
INSERT INTO `easy_excel_data`
VALUES ('3148a17f5c3da7ae83071ac4cce55f8d', '字符串5', 0.5600, '2020-09-27 10:51:33', 0);
INSERT INTO `easy_excel_data`
VALUES ('339135c29259aa9572e5544f6176743c', '字符串9', 0.5600, '2020-09-27 14:28:37', 0);
INSERT INTO `easy_excel_data`
VALUES ('3620871b3edca95d933d04ac4356ec6a', '字符串7', 0.5600, '2020-09-27 10:51:33', 0);
INSERT INTO `easy_excel_data`
VALUES ('37844cc5da7f7d26789d564862f8458f', '字符串4', 0.5600, '2020-09-27 10:51:33', 0);
INSERT INTO `easy_excel_data`
VALUES ('37d28876876ccdb0eb8cc4edf324bcb0', '字符串4', 0.5600, '2020-09-28 11:03:36', 0);
INSERT INTO `easy_excel_data`
VALUES ('418524c8ba3065eeff5fc5045ddb2ab2', '字符串3', 0.5600, '2020-09-27 09:06:03', 0);
INSERT INTO `easy_excel_data`
VALUES ('44cb1edca4cf2c4a4a28eba9949a6565', '字符串7', 0.5600, '2020-09-27 14:28:37', 0);
INSERT INTO `easy_excel_data`
VALUES ('4d3071048256d892491b1d02fe0d7e16', '字符串9', 0.5600, '2020-09-27 09:06:03', 0);
INSERT INTO `easy_excel_data`
VALUES ('4e43c45f2f277462f84d61c501f7c1d9', '字符串3', 0.5600, '2020-09-30 16:36:36', 0);
INSERT INTO `easy_excel_data`
VALUES ('54ee0f4a23358a79fc693eec9e3223d9', '字符串6', 0.5600, '2020-09-27 09:06:52', 0);
INSERT INTO `easy_excel_data`
VALUES ('5b2f6eed55d50e40a1d9dd36b3dba448', '字符串1', 0.5600, '2020-09-27 09:06:52', 0);
INSERT INTO `easy_excel_data`
VALUES ('5c96157ad47369716069a827e326652c', '字符串2', 0.5600, '2020-09-27 14:24:56', 0);
INSERT INTO `easy_excel_data`
VALUES ('5cc026080450a7c852e4127ceff55c16', '字符串2', 0.5600, '2020-09-28 11:03:36', 0);
INSERT INTO `easy_excel_data`
VALUES ('5cc63a9365167cf3861a4e465b4e3a5f', '字符串8', 0.5600, '2020-09-28 10:50:29', 0);
INSERT INTO `easy_excel_data`
VALUES ('5d43d4920f62069bf4ae6de7cd722b5c', '字符串2', 0.5600, '2020-09-27 14:28:37', 0);
INSERT INTO `easy_excel_data`
VALUES ('672bf4e40b45d83699859d67586f1f3e', '字符串1', 0.5600, '2020-09-27 09:06:03', 0);
INSERT INTO `easy_excel_data`
VALUES ('6d67ce365d7fadfdfc9a4910cc7133cd', '字符串7', 0.5600, '2020-09-27 14:24:56', 0);
INSERT INTO `easy_excel_data`
VALUES ('75b7490fb1edcb6284a9e8b8459356bd', '字符串4', 0.5600, '2020-09-27 09:06:52', 0);
INSERT INTO `easy_excel_data`
VALUES ('7623e1f207e6256e9047b7a10b3de021', '字符串7', 0.5600, '2020-09-27 09:06:52', 0);
INSERT INTO `easy_excel_data`
VALUES ('7c846c6ad469dad69a394c638dc2d090', '字符串4', 0.5600, '2020-09-27 14:28:00', 0);
INSERT INTO `easy_excel_data`
VALUES ('81e609573a97ef01327d98089d8c370e', '字符串0', 0.5600, '2020-09-27 09:06:03', 0);
INSERT INTO `easy_excel_data`
VALUES ('8670a8d4882b8bcff7b0a0387663d5ca', '字符串2', 0.5600, '2020-09-30 16:36:36', 0);
INSERT INTO `easy_excel_data`
VALUES ('8efbe1bc8b78cd8b5b33535611937714', '字符串9', 0.5600, '2020-09-27 14:28:00', 0);
INSERT INTO `easy_excel_data`
VALUES ('93cac30955ae57d0dd885a1f748df976', '字符串5', 0.5600, '2020-09-27 09:06:03', 0);
INSERT INTO `easy_excel_data`
VALUES ('99eed19b7a1edb4f930f0ef3caa7ac6f', '字符串2', 0.5600, '2020-09-27 09:06:52', 0);
INSERT INTO `easy_excel_data`
VALUES ('a2929a466779e49e3d8d62cb7d8d29e2', '字符串5', 0.5600, '2020-09-27 09:06:52', 0);
INSERT INTO `easy_excel_data`
VALUES ('a2a0600553dd139d379d36ddd0da7a01', '字符串8', 0.5600, '2020-09-27 14:25:38', 0);
INSERT INTO `easy_excel_data`
VALUES ('a82b9fb0a487d8c7b8b4f6876007d79a', '字符串7', 0.5600, '2020-09-30 16:36:36', 0);
INSERT INTO `easy_excel_data`
VALUES ('a8f2989623882c33370e3646c5e10fa7', '字符串9', 0.5600, '2020-09-27 14:24:56', 0);
INSERT INTO `easy_excel_data`
VALUES ('af6151976c8f279d71487cae621c6f1c', '字符串0', 0.5600, '2020-09-27 14:28:00', 0);
INSERT INTO `easy_excel_data`
VALUES ('b3b11786d4045cdd1f9c3138e3bbfd47', '字符串7', 0.5600, '2020-09-27 14:25:38', 0);
INSERT INTO `easy_excel_data`
VALUES ('b57e59d1da82ea312c47efe83a25cc20', '字符串3', 0.5600, '2020-09-27 14:28:37', 0);
INSERT INTO `easy_excel_data`
VALUES ('b84f17375fb93725360c14b4c8af9f99', '字符串4', 0.5600, '2020-09-27 09:06:03', 0);
INSERT INTO `easy_excel_data`
VALUES ('b8d649cab4521e78631911203d940028', '字符串4', 0.5600, '2020-09-30 16:36:36', 0);
INSERT INTO `easy_excel_data`
VALUES ('ba704844b729c79aa84a83096f5fb384', '字符串8', 0.5600, '2020-09-27 09:06:52', 0);
INSERT INTO `easy_excel_data`
VALUES ('be5f8c3a52e10a34afca036e2ec4ba8c', '字符串7', 0.5600, '2020-09-27 14:28:00', 0);
INSERT INTO `easy_excel_data`
VALUES ('bf929f4fc5544578797c1aa05a44fd80', '字符串2', 0.5600, '2020-09-27 14:28:00', 0);
INSERT INTO `easy_excel_data`
VALUES ('c57462876e90c3ab3ddf0758e19b2214', '字符串3', 0.5600, '2020-09-27 14:28:00', 0);
INSERT INTO `easy_excel_data`
VALUES ('c5a6725b1c3fc5a029491b9de6d55e83', '字符串6', 0.5600, '2020-09-27 14:24:56', 0);
INSERT INTO `easy_excel_data`
VALUES ('c8afa94d5f1307a8b361dd348a3fe1e0', '字符串4', 0.5600, '2020-09-27 14:28:37', 0);
INSERT INTO `easy_excel_data`
VALUES ('cbfa818b0088b92a0a1036870ee16f3a', '字符串9', 0.5600, '2020-09-27 09:06:52', 0);
INSERT INTO `easy_excel_data`
VALUES ('cc5f95cdee9e17e817f86ca471cd0e3a', '字符串9', 0.5600, '2020-09-27 14:25:38', 0);
INSERT INTO `easy_excel_data`
VALUES ('cd1d1c9fd8c8f2318b5ed7cf2f8caec1', '字符串4', 0.5600, '2020-09-27 14:25:38', 0);
INSERT INTO `easy_excel_data`
VALUES ('ce00c989aa734364ee1b963337e1d870', '字符串1', 0.5600, '2020-09-30 16:36:36', 0);
INSERT INTO `easy_excel_data`
VALUES ('ce03f8ca9eee2ad0e524b0734ff22a4e', '字符串2', 0.5600, '2020-09-27 10:51:33', 0);
INSERT INTO `easy_excel_data`
VALUES ('d218d6381f8eb8bc0025a24a44babc7f', '字符串8', 0.5600, '2020-09-30 16:36:36', 0);
INSERT INTO `easy_excel_data`
VALUES ('d2f9007ff8b0dbbe9294e1a4a7f672bb', '字符串3', 0.5600, '2020-09-27 14:24:56', 0);
INSERT INTO `easy_excel_data`
VALUES ('d43baac47135c86edf13d039fee62404', '字符串5', 0.5600, '2020-09-27 14:24:56', 0);
INSERT INTO `easy_excel_data`
VALUES ('d4d25ea217d6ca850df38ad4c362c7c2', '字符串1', 0.5600, '2020-09-27 10:51:33', 0);
INSERT INTO `easy_excel_data`
VALUES ('d6133c7e2267f1375762c2f1cfaad28a', '字符串7', 0.5600, '2020-09-28 11:03:36', 0);
INSERT INTO `easy_excel_data`
VALUES ('d94fe558772cd0008bb2d05eee49e64e', '字符串8', 0.5600, '2020-09-27 10:51:33', 0);
INSERT INTO `easy_excel_data`
VALUES ('dc096c672c6728dc7c1e867a554c1851', '字符串3', 0.5600, '2020-09-27 09:06:52', 0);
INSERT INTO `easy_excel_data`
VALUES ('dde11b4458b7d42574ccab23c32be44d', '字符串1', 0.5600, '2020-09-27 14:25:38', 0);
INSERT INTO `easy_excel_data`
VALUES ('e0b53e5d2a20003536da0a9e9d873cc2', '字符串2', 0.5600, '2020-09-27 09:06:03', 0);
INSERT INTO `easy_excel_data`
VALUES ('e32483f681e49f1f61542b440a6b3e92', '字符串8', 0.5600, '2020-09-28 11:03:36', 0);
INSERT INTO `easy_excel_data`
VALUES ('e601ae1c64ff65da7b3bfd746af079f8', '字符串3', 0.5600, '2020-09-27 14:25:38', 0);
INSERT INTO `easy_excel_data`
VALUES ('e7737a87ea50d116cd4cde8d24b52a9f', '字符串3', 0.5600, '2020-09-28 10:50:29', 0);
INSERT INTO `easy_excel_data`
VALUES ('e8e0ed94bb5b5bef23517b9acd0a382c', '字符串7', 0.5600, '2020-09-28 10:50:29', 0);
INSERT INTO `easy_excel_data`
VALUES ('ec4e20eb68adae6e28b5642d900d116e', '字符串8', 0.5600, '2020-09-27 14:24:56', 0);
INSERT INTO `easy_excel_data`
VALUES ('ef2c3737e9dc98298b43ba48d6afcfdf', '字符串9', 0.5600, '2020-09-27 10:51:33', 0);
INSERT INTO `easy_excel_data`
VALUES ('f0953120c088a4155f799b405a6cb9af', '字符串0', 0.5600, '2020-09-27 09:06:52', 0);
INSERT INTO `easy_excel_data`
VALUES ('f1a71048e00e862033a5239ed6da6a7d', '字符串1', 0.5600, '2020-09-28 10:50:29', 0);
INSERT INTO `easy_excel_data`
VALUES ('f3e1844c36e2bb832ca5cd57e2871e5a', '字符串0', 0.5600, '2020-09-27 14:24:56', 0);
INSERT INTO `easy_excel_data`
VALUES ('f777f3a6dff71d9cc989df9d92231fe8', '字符串2', 0.5600, '2020-09-27 14:25:38', 0);
INSERT INTO `easy_excel_data`
VALUES ('f7b1541c2dd11c6c183133cb15154444', '字符串4', 0.5600, '2020-09-27 14:24:56', 0);

-- ----------------------------
-- Table structure for operation_record_log
-- ----------------------------
DROP TABLE IF EXISTS `operation_record_log`;
CREATE TABLE `operation_record_log`
(
    `id`                    varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `operation_person`      varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `operation_num`         int(11) NULL DEFAULT NULL,
    `operation_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `operation_interface`   varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `operation_param`       varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `create_time`           datetime NULL DEFAULT NULL,
    `delflag`               tinyint(1) NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `index_id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of operation_record_log
-- ----------------------------
INSERT INTO `operation_record_log`
VALUES ('1300321830129348609', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@44fb685b', '2020-08-31 14:37:37', 0);
INSERT INTO `operation_record_log`
VALUES ('1300325152680816642', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@663eb3c3', '2020-08-31 14:50:49', 0);
INSERT INTO `operation_record_log`
VALUES ('1300325404192256001', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-08-31 14:51:49', 0);
INSERT INTO `operation_record_log`
VALUES ('1300325604893896706', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-08-31 14:52:37', 0);
INSERT INTO `operation_record_log`
VALUES ('1300325793591439361', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-08-31 14:53:22', 0);
INSERT INTO `operation_record_log`
VALUES ('1300331901102043138', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@6ae29a92,org.apache.shiro.web.servlet.ShiroHttpServletRequest@eaf6f9f',
        '2020-08-31 15:17:38', 0);
INSERT INTO `operation_record_log`
VALUES ('1300331934077661186', 'Tohsaka Rin', 1, '上传头像', 'com.gousade.controller.UserController.userAvatorUpload',
        'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@1d5f6154',
        '2020-08-31 15:17:46', 0);
INSERT INTO `operation_record_log`
VALUES ('1300331935050739714', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@52196070,org.apache.shiro.web.servlet.ShiroHttpServletRequest@68c2b537',
        '2020-08-31 15:17:46', 0);
INSERT INTO `operation_record_log`
VALUES ('1300342332705153026', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@3f980cf2,org.apache.shiro.web.servlet.ShiroHttpServletRequest@4793d365',
        '2020-08-31 15:59:05', 0);
INSERT INTO `operation_record_log`
VALUES ('1300342370307088385', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@3e17e4a7,org.apache.shiro.web.servlet.ShiroHttpServletRequest@6640d4f6',
        '2020-08-31 15:59:14', 0);
INSERT INTO `operation_record_log`
VALUES ('1300342824101421057', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@8e88e16', '2020-08-31 16:01:03', 0);
INSERT INTO `operation_record_log`
VALUES ('1300344149467983873', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@3f41467c,org.apache.shiro.web.servlet.ShiroHttpServletRequest@1dccdb25',
        '2020-08-31 16:06:19', 0);
INSERT INTO `operation_record_log`
VALUES ('1300344223002521602', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@5e44c352', '2020-08-31 16:06:36', 0);
INSERT INTO `operation_record_log`
VALUES ('1300344411188359169', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@65c53946', '2020-08-31 16:07:21', 0);
INSERT INTO `operation_record_log`
VALUES ('1300357799876378626', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@4fdf8c1a,org.apache.shiro.web.servlet.ShiroHttpServletRequest@775e2b4c',
        '2020-08-31 17:00:33', 0);
INSERT INTO `operation_record_log`
VALUES ('1300357842746359809', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@220ba534', '2020-08-31 17:00:43', 0);
INSERT INTO `operation_record_log`
VALUES ('1300358144895639554', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@33010c5c,org.apache.shiro.web.servlet.ShiroHttpServletRequest@47c64fa6',
        '2020-08-31 17:01:55', 0);
INSERT INTO `operation_record_log`
VALUES ('1300358176029958146', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@4d11271e', '2020-08-31 17:02:03', 0);
INSERT INTO `operation_record_log`
VALUES ('1300358742626004993', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@37d69196', '2020-08-31 17:04:18', 0);
INSERT INTO `operation_record_log`
VALUES ('1300359928246579201', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@163c4b39', '2020-08-31 17:09:01', 0);
INSERT INTO `operation_record_log`
VALUES ('1300360319008944129', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@171afc10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@2dc31db1',
        '2020-08-31 17:10:34', 0);
INSERT INTO `operation_record_log`
VALUES ('1300360336427888642', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@82f03d4', '2020-08-31 17:10:38', 0);
INSERT INTO `operation_record_log`
VALUES ('1300360360876486658', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@365f143b', '2020-08-31 17:10:44', 0);
INSERT INTO `operation_record_log`
VALUES ('1300360958329888770', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@54e69c34,org.apache.shiro.web.servlet.ShiroHttpServletRequest@64215b26',
        '2020-08-31 17:13:06', 0);
INSERT INTO `operation_record_log`
VALUES ('1300393391379517442', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@1b5b11be,org.apache.shiro.web.servlet.ShiroHttpServletRequest@558c1a90',
        '2020-08-31 19:21:59', 0);
INSERT INTO `operation_record_log`
VALUES ('1300393454394740737', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@31b85bde', '2020-08-31 19:22:14', 0);
INSERT INTO `operation_record_log`
VALUES ('1300394312654229505', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@1b3706ae,org.apache.shiro.web.servlet.ShiroHttpServletRequest@4f731e2a',
        '2020-08-31 19:25:38', 0);
INSERT INTO `operation_record_log`
VALUES ('1300394333357314050', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@713cd880', '2020-08-31 19:25:43', 0);
INSERT INTO `operation_record_log`
VALUES ('1300394387216371713', 'Tohsaka Rin', 1, '上传头像', 'com.gousade.controller.UserController.userAvatorUpload',
        'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@2c6c3a0a',
        '2020-08-31 19:25:56', 0);
INSERT INTO `operation_record_log`
VALUES ('1300394388155895809', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@3e766e0e,org.apache.shiro.web.servlet.ShiroHttpServletRequest@1f5daf67',
        '2020-08-31 19:25:56', 0);
INSERT INTO `operation_record_log`
VALUES ('1300394840729686017', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-08-31 19:27:44', 0);
INSERT INTO `operation_record_log`
VALUES ('1300394892231544834', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-08-31 19:27:57', 0);
INSERT INTO `operation_record_log`
VALUES ('1300395391223697409', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-08-31 19:29:56', 0);
INSERT INTO `operation_record_log`
VALUES ('1300414099241406466', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@aeb41a6,org.apache.shiro.web.servlet.ShiroHttpServletRequest@354b5f72',
        '2020-08-31 20:44:16', 0);
INSERT INTO `operation_record_log`
VALUES ('1300414187518922754', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@5e084157,org.apache.shiro.web.servlet.ShiroHttpServletRequest@4dfe9c9f',
        '2020-08-31 20:44:37', 0);
INSERT INTO `operation_record_log`
VALUES ('1300414216895827969', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@36480313,org.apache.shiro.web.servlet.ShiroHttpServletRequest@31f2edca',
        '2020-08-31 20:44:44', 0);
INSERT INTO `operation_record_log`
VALUES ('1300414413172477953', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@77d46ba7,org.apache.shiro.web.servlet.ShiroHttpServletRequest@4f439191',
        '2020-08-31 20:45:31', 0);
INSERT INTO `operation_record_log`
VALUES ('1300414460115128321', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@10d2b988,org.apache.shiro.web.servlet.ShiroHttpServletRequest@2768c492',
        '2020-08-31 20:45:42', 0);
INSERT INTO `operation_record_log`
VALUES ('1300414571792588802', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@5a62fdd,org.apache.shiro.web.servlet.ShiroHttpServletRequest@67a07d40',
        '2020-08-31 20:46:09', 0);
INSERT INTO `operation_record_log`
VALUES ('1300414662657990657', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@1681606f,org.apache.shiro.web.servlet.ShiroHttpServletRequest@74736d5a',
        '2020-08-31 20:46:30', 0);
INSERT INTO `operation_record_log`
VALUES ('1300430013093507074', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@33b1c1a3,org.apache.shiro.web.servlet.ShiroHttpServletRequest@1c9979ca',
        '2020-08-31 21:47:30', 0);
INSERT INTO `operation_record_log`
VALUES ('1300430057095950338', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@8d37d50', '2020-08-31 21:47:41', 0);
INSERT INTO `operation_record_log`
VALUES ('1300431842432880642', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@4e0a4d58,org.apache.shiro.web.servlet.ShiroHttpServletRequest@734296b1',
        '2020-08-31 21:54:46', 0);
INSERT INTO `operation_record_log`
VALUES ('1300432162969980930', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@1174abf5', '2020-08-31 21:56:03', 0);
INSERT INTO `operation_record_log`
VALUES ('1300433638098268162', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@648e096a,org.apache.shiro.web.servlet.ShiroHttpServletRequest@605a9c1',
        '2020-08-31 22:01:54', 0);
INSERT INTO `operation_record_log`
VALUES ('1300434417907462146', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@716e1706', '2020-08-31 22:05:00', 0);
INSERT INTO `operation_record_log`
VALUES ('1300615950747983874', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@15d7b9e1,org.apache.shiro.web.servlet.ShiroHttpServletRequest@1670b2f7',
        '2020-09-01 10:06:21', 0);
INSERT INTO `operation_record_log`
VALUES ('1300616019178053633', 'Tohsaka Rin', 1, '上传头像', 'com.gousade.controller.UserController.userAvatorUpload',
        'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@11ca88d8',
        '2020-09-01 10:06:37', 0);
INSERT INTO `operation_record_log`
VALUES ('1300616020490870785', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@7f1e1e87,org.apache.shiro.web.servlet.ShiroHttpServletRequest@18a49ecd',
        '2020-09-01 10:06:38', 0);
INSERT INTO `operation_record_log`
VALUES ('1300617337850454018', 'Tohsaka Rin', 1, '上传头像', 'com.gousade.controller.UserController.userAvatorUpload',
        'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@d103fda',
        '2020-09-01 10:11:52', 0);
INSERT INTO `operation_record_log`
VALUES ('1300617338869669890', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@bea6f31,org.apache.shiro.web.servlet.ShiroHttpServletRequest@efeab0a',
        '2020-09-01 10:11:52', 0);
INSERT INTO `operation_record_log`
VALUES ('1300620404637458434', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@a820e82,org.apache.shiro.web.servlet.ShiroHttpServletRequest@564feca6',
        '2020-09-01 10:24:03', 0);
INSERT INTO `operation_record_log`
VALUES ('1300620460484616194', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@30403bdf,org.apache.shiro.web.servlet.ShiroHttpServletRequest@637d1dc',
        '2020-09-01 10:24:16', 0);
INSERT INTO `operation_record_log`
VALUES ('1300620687568429058', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@7d50bb5b,org.apache.shiro.web.servlet.ShiroHttpServletRequest@269c3d3e',
        '2020-09-01 10:25:10', 0);
INSERT INTO `operation_record_log`
VALUES ('1300620903721885698', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@3ee60457,org.apache.shiro.web.servlet.ShiroHttpServletRequest@6adc8eab',
        '2020-09-01 10:26:02', 0);
INSERT INTO `operation_record_log`
VALUES ('1300620928640245762', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@1b2b638e,org.apache.shiro.web.servlet.ShiroHttpServletRequest@59c4d9d1',
        '2020-09-01 10:26:08', 0);
INSERT INTO `operation_record_log`
VALUES ('1300629616859963393', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@22e2096f,org.apache.shiro.web.servlet.ShiroHttpServletRequest@2d819735',
        '2020-09-01 11:00:39', 0);
INSERT INTO `operation_record_log`
VALUES ('1300681054936076290', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@71a1eca7,org.apache.shiro.web.servlet.ShiroHttpServletRequest@7ea7ecdc',
        '2020-09-01 14:25:03', 0);
INSERT INTO `operation_record_log`
VALUES ('1300694312539611138', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@62e4d1d9,org.apache.shiro.web.servlet.ShiroHttpServletRequest@5d334207',
        '2020-09-01 15:17:44', 0);
INSERT INTO `operation_record_log`
VALUES ('1300705475629383682', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@6b151f51,org.apache.shiro.web.servlet.ShiroHttpServletRequest@3fe068bc',
        '2020-09-01 16:02:06', 0);
INSERT INTO `operation_record_log`
VALUES ('1300712866987081729', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@7de12ab3,org.apache.shiro.web.servlet.ShiroHttpServletRequest@78dbe888',
        '2020-09-01 16:31:28', 0);
INSERT INTO `operation_record_log`
VALUES ('1300743399582789634', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@7e350285,org.apache.shiro.web.servlet.ShiroHttpServletRequest@1044fa83',
        '2020-09-01 18:32:47', 0);
INSERT INTO `operation_record_log`
VALUES ('1300767100141428737', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@3299a1fd,org.apache.shiro.web.servlet.ShiroHttpServletRequest@f8bb11c',
        '2020-09-01 20:06:58', 0);
INSERT INTO `operation_record_log`
VALUES ('1300770653215924225', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@72d28565,org.apache.shiro.web.servlet.ShiroHttpServletRequest@363d8432',
        '2020-09-01 20:21:05', 0);
INSERT INTO `operation_record_log`
VALUES ('1300772317482196993', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@d50f5d0,org.apache.shiro.web.servlet.ShiroHttpServletRequest@166d3b00',
        '2020-09-01 20:27:42', 0);
INSERT INTO `operation_record_log`
VALUES ('1300772474059759617', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@696995f4,org.apache.shiro.web.servlet.ShiroHttpServletRequest@1c529c3c',
        '2020-09-01 20:28:19', 0);
INSERT INTO `operation_record_log`
VALUES ('1300779815941439489', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@488aa36e,org.apache.shiro.web.servlet.ShiroHttpServletRequest@24d9e31b',
        '2020-09-01 20:57:30', 0);
INSERT INTO `operation_record_log`
VALUES ('1300780105923035138', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@6e211715,org.apache.shiro.web.servlet.ShiroHttpServletRequest@358aed91',
        '2020-09-01 20:58:39', 0);
INSERT INTO `operation_record_log`
VALUES ('1300780435628883969', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-09-01 20:59:57', 0);
INSERT INTO `operation_record_log`
VALUES ('1300783742355243010', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@4ee76071,org.apache.shiro.web.servlet.ShiroHttpServletRequest@6ca2f319',
        '2020-09-01 21:13:06', 0);
INSERT INTO `operation_record_log`
VALUES ('1300783760642408450', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-09-01 21:13:10', 0);
INSERT INTO `operation_record_log`
VALUES ('1300783854947139585', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@426053df,org.apache.shiro.web.servlet.ShiroHttpServletRequest@efb206e',
        '2020-09-01 21:13:33', 0);
INSERT INTO `operation_record_log`
VALUES ('1300786290738855937', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@56cbbd3a,org.apache.shiro.web.servlet.ShiroHttpServletRequest@71435f28',
        '2020-09-01 21:23:13', 0);
INSERT INTO `operation_record_log`
VALUES ('1300786675973095426', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@6f41541f,org.apache.shiro.web.servlet.ShiroHttpServletRequest@4a930db9',
        '2020-09-01 21:24:45', 0);
INSERT INTO `operation_record_log`
VALUES ('1300789202353389570', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@854aa6f,org.apache.shiro.web.servlet.ShiroHttpServletRequest@635aa824',
        '2020-09-01 21:34:47', 0);
INSERT INTO `operation_record_log`
VALUES ('1300789742051262465', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@7edba0a2,org.apache.shiro.web.servlet.ShiroHttpServletRequest@568a38f9',
        '2020-09-01 21:36:56', 0);
INSERT INTO `operation_record_log`
VALUES ('1300789745163436034', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@3ae3dcd0,org.apache.shiro.web.servlet.ShiroHttpServletRequest@55f0abfa',
        '2020-09-01 21:36:57', 0);
INSERT INTO `operation_record_log`
VALUES ('1300789799311900674', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@ca7d1ec,org.apache.shiro.web.servlet.ShiroHttpServletRequest@318bbf23',
        '2020-09-01 21:37:10', 0);
INSERT INTO `operation_record_log`
VALUES ('1300789823051661314', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@7d0130ff,org.apache.shiro.web.servlet.ShiroHttpServletRequest@5ad54a20',
        '2020-09-01 21:37:15', 0);
INSERT INTO `operation_record_log`
VALUES ('1300791115199283201', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@2d33ff19,org.apache.shiro.web.servlet.ShiroHttpServletRequest@1a6959b2',
        '2020-09-01 21:42:24', 0);
INSERT INTO `operation_record_log`
VALUES ('1300792043696553985', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@768fcb6c,org.apache.shiro.web.servlet.ShiroHttpServletRequest@6073a648',
        '2020-09-01 21:46:05', 0);
INSERT INTO `operation_record_log`
VALUES ('1300792598242263041', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@47044916,org.apache.shiro.web.servlet.ShiroHttpServletRequest@6c31782b',
        '2020-09-01 21:48:17', 0);
INSERT INTO `operation_record_log`
VALUES ('1300797585227776001', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@6484c45d,org.apache.shiro.web.servlet.ShiroHttpServletRequest@342584db',
        '2020-09-01 22:08:06', 0);
INSERT INTO `operation_record_log`
VALUES ('1300797702525681666', 'Tohsaka Rin', 1, '上传头像', 'com.gousade.controller.UserController.userAvatorUpload',
        'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@22064c43',
        '2020-09-01 22:08:34', 0);
INSERT INTO `operation_record_log`
VALUES ('1300797703498760194', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@26620b39,org.apache.shiro.web.servlet.ShiroHttpServletRequest@20c96eb',
        '2020-09-01 22:08:34', 0);
INSERT INTO `operation_record_log`
VALUES ('1300979468911497217', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@17134c13,org.apache.shiro.web.servlet.ShiroHttpServletRequest@3655e666',
        '2020-09-02 10:10:51', 0);
INSERT INTO `operation_record_log`
VALUES ('1301046498129862657', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@5cd47b7e,org.apache.shiro.web.servlet.ShiroHttpServletRequest@4ce253a8',
        '2020-09-02 14:37:12', 0);
INSERT INTO `operation_record_log`
VALUES ('1301048373302517762', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-09-02 14:44:39', 0);
INSERT INTO `operation_record_log`
VALUES ('1301056127698198529', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-09-02 15:15:27', 0);
INSERT INTO `operation_record_log`
VALUES ('1301057008313294850', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-09-02 15:18:57', 0);
INSERT INTO `operation_record_log`
VALUES ('1301060591326846978', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-09-02 15:33:12', 0);
INSERT INTO `operation_record_log`
VALUES ('1301060689209319426', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@2a576fcf,org.apache.shiro.web.servlet.ShiroHttpServletRequest@14ea7700',
        '2020-09-02 15:33:35', 0);
INSERT INTO `operation_record_log`
VALUES ('1301060984622538754', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@4d66fa12,org.apache.shiro.web.servlet.ShiroHttpServletRequest@74be814d',
        '2020-09-02 15:34:45', 0);
INSERT INTO `operation_record_log`
VALUES ('1301061000061771778', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-09-02 15:34:49', 0);
INSERT INTO `operation_record_log`
VALUES ('1301062257157595138', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-09-02 15:39:49', 0);
INSERT INTO `operation_record_log`
VALUES ('1301063997529513986', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@421207d0,org.apache.shiro.web.servlet.ShiroHttpServletRequest@49a18dff',
        '2020-09-02 15:46:44', 0);
INSERT INTO `operation_record_log`
VALUES ('1301064009340674050', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-09-02 15:46:47', 0);
INSERT INTO `operation_record_log`
VALUES ('1301064114739339266', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@60276e3c,org.apache.shiro.web.servlet.ShiroHttpServletRequest@176675f8',
        '2020-09-02 15:47:12', 0);
INSERT INTO `operation_record_log`
VALUES ('1301064141763239938', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-09-02 15:47:18', 0);
INSERT INTO `operation_record_log`
VALUES ('1301064221954138114', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-09-02 15:47:37', 0);
INSERT INTO `operation_record_log`
VALUES ('1301122758246244353', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@15b197e3,org.apache.shiro.web.servlet.ShiroHttpServletRequest@75473d68',
        '2020-09-02 19:40:13', 0);
INSERT INTO `operation_record_log`
VALUES ('1301122950240509953', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@697e1e23,org.apache.shiro.web.servlet.ShiroHttpServletRequest@7c25f0b0',
        '2020-09-02 19:40:59', 0);
INSERT INTO `operation_record_log`
VALUES ('1301124087093927937', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@5bd72746,org.apache.shiro.web.servlet.ShiroHttpServletRequest@74fba05b',
        '2020-09-02 19:45:30', 0);
INSERT INTO `operation_record_log`
VALUES ('1301124400576208897', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@2f746be6,org.apache.shiro.web.servlet.ShiroHttpServletRequest@672db2df',
        '2020-09-02 19:46:45', 0);
INSERT INTO `operation_record_log`
VALUES ('1301125760294100994', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@65e0157d,org.apache.shiro.web.servlet.ShiroHttpServletRequest@3228b620',
        '2020-09-02 19:52:09', 0);
INSERT INTO `operation_record_log`
VALUES ('1301128177689567233', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@667ef79c,org.apache.shiro.web.servlet.ShiroHttpServletRequest@77e892c1',
        '2020-09-02 20:01:46', 0);
INSERT INTO `operation_record_log`
VALUES ('1301161009338241025', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@42a46477,org.apache.shiro.web.servlet.ShiroHttpServletRequest@cdda543',
        '2020-09-02 22:12:13', 0);
INSERT INTO `operation_record_log`
VALUES ('1301161452961386497', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@1e49530a,org.apache.shiro.web.servlet.ShiroHttpServletRequest@35900368',
        '2020-09-02 22:13:59', 0);
INSERT INTO `operation_record_log`
VALUES ('1301431234035683330', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@3870115f,org.apache.shiro.web.servlet.ShiroHttpServletRequest@27bc8b7f',
        '2020-09-03 16:06:00', 0);
INSERT INTO `operation_record_log`
VALUES ('1301431577746313218', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@2f6b3722,org.apache.shiro.web.servlet.ShiroHttpServletRequest@eac5d07',
        '2020-09-03 16:07:22', 0);
INSERT INTO `operation_record_log`
VALUES ('1301431608519921665', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@3e9bff8b', '2020-09-03 16:07:29', 0);
INSERT INTO `operation_record_log`
VALUES ('1301431832348954626', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@3dc4142a,org.apache.shiro.web.servlet.ShiroHttpServletRequest@6810b36a',
        '2020-09-03 16:08:22', 0);
INSERT INTO `operation_record_log`
VALUES ('1301432694714634241', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@54cd8ebd,org.apache.shiro.web.servlet.ShiroHttpServletRequest@631cb40d',
        '2020-09-03 16:11:48', 0);
INSERT INTO `operation_record_log`
VALUES ('1301433120784617473', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@3067d32f,org.apache.shiro.web.servlet.ShiroHttpServletRequest@1608126',
        '2020-09-03 16:13:30', 0);
INSERT INTO `operation_record_log`
VALUES ('1301433311226990594', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@24ff851b,org.apache.shiro.web.servlet.ShiroHttpServletRequest@3df4fefc',
        '2020-09-03 16:14:15', 0);
INSERT INTO `operation_record_log`
VALUES ('1301434161487908866', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@724ba390,org.apache.shiro.web.servlet.ShiroHttpServletRequest@2e51aae8',
        '2020-09-03 16:17:38', 0);
INSERT INTO `operation_record_log`
VALUES ('1301434302521380866', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@217ea6d8,org.apache.shiro.web.servlet.ShiroHttpServletRequest@4201df91',
        '2020-09-03 16:18:11', 0);
INSERT INTO `operation_record_log`
VALUES ('1301434677026590722', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@46182396,org.apache.shiro.web.servlet.ShiroHttpServletRequest@38b4888e',
        '2020-09-03 16:19:41', 0);
INSERT INTO `operation_record_log`
VALUES ('1301435997359935490', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@69a1ca66,org.apache.shiro.web.servlet.ShiroHttpServletRequest@5aca7854',
        '2020-09-03 16:24:55', 0);
INSERT INTO `operation_record_log`
VALUES ('1301436897247858690', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@3b10fd81,org.apache.shiro.web.servlet.ShiroHttpServletRequest@63cccf4',
        '2020-09-03 16:28:30', 0);
INSERT INTO `operation_record_log`
VALUES ('1301437775468007426', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@ddf6282,org.apache.shiro.web.servlet.ShiroHttpServletRequest@28fe641b',
        '2020-09-03 16:31:59', 0);
INSERT INTO `operation_record_log`
VALUES ('1301437895525765121', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@994582a,org.apache.shiro.web.servlet.ShiroHttpServletRequest@58e56bc5',
        '2020-09-03 16:32:28', 0);
INSERT INTO `operation_record_log`
VALUES ('1301438122404057090', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@27acbbfa,org.apache.shiro.web.servlet.ShiroHttpServletRequest@471863f1',
        '2020-09-03 16:33:22', 0);
INSERT INTO `operation_record_log`
VALUES ('1301439710413688833', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@768ee016,org.apache.shiro.web.servlet.ShiroHttpServletRequest@4d71d5b2',
        '2020-09-03 16:39:41', 0);
INSERT INTO `operation_record_log`
VALUES ('1301440193417154561', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@3f35f435,org.apache.shiro.web.servlet.ShiroHttpServletRequest@7d81811c',
        '2020-09-03 16:41:36', 0);
INSERT INTO `operation_record_log`
VALUES ('1301440757634928641', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@1c0dc24c,org.apache.shiro.web.servlet.ShiroHttpServletRequest@28e66fef',
        '2020-09-03 16:43:50', 0);
INSERT INTO `operation_record_log`
VALUES ('1301441309185904642', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@1990ec72,org.apache.shiro.web.servlet.ShiroHttpServletRequest@1ec58fe8',
        '2020-09-03 16:46:02', 0);
INSERT INTO `operation_record_log`
VALUES ('1301442376380420098', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@4f9bb5ea,org.apache.shiro.web.servlet.ShiroHttpServletRequest@73a2ea06',
        '2020-09-03 16:50:16', 0);
INSERT INTO `operation_record_log`
VALUES ('1301442547067621377', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@e35056,org.apache.shiro.web.servlet.ShiroHttpServletRequest@589f3a03',
        '2020-09-03 16:50:57', 0);
INSERT INTO `operation_record_log`
VALUES ('1301442736755019777', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@21b9e8f9,org.apache.shiro.web.servlet.ShiroHttpServletRequest@43fa539c',
        '2020-09-03 16:51:42', 0);
INSERT INTO `operation_record_log`
VALUES ('1301443410465099777', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@36c07216,org.apache.shiro.web.servlet.ShiroHttpServletRequest@27bffe14',
        '2020-09-03 16:54:23', 0);
INSERT INTO `operation_record_log`
VALUES ('1301443484221935618', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@515e658f,org.apache.shiro.web.servlet.ShiroHttpServletRequest@12c7e29e',
        '2020-09-03 16:54:40', 0);
INSERT INTO `operation_record_log`
VALUES ('1301445180415250434', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@5902d63b,org.apache.shiro.web.servlet.ShiroHttpServletRequest@3b2b4284',
        '2020-09-03 17:01:25', 0);
INSERT INTO `operation_record_log`
VALUES ('1301445340247592962', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@344e8062', '2020-09-03 17:02:03', 0);
INSERT INTO `operation_record_log`
VALUES ('1301445717978222593', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@1e00874e,org.apache.shiro.web.servlet.ShiroHttpServletRequest@3c5470af',
        '2020-09-03 17:03:33', 0);
INSERT INTO `operation_record_log`
VALUES ('1301445858432880642', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@61ada7f3,org.apache.shiro.web.servlet.ShiroHttpServletRequest@713d1af6',
        '2020-09-03 17:04:07', 0);
INSERT INTO `operation_record_log`
VALUES ('1301446171583811585', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@3eafff4b,org.apache.shiro.web.servlet.ShiroHttpServletRequest@45d2c82a',
        '2020-09-03 17:05:21', 0);
INSERT INTO `operation_record_log`
VALUES ('1301447352083255298', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@65ae072f,org.apache.shiro.web.servlet.ShiroHttpServletRequest@1a48f521',
        '2020-09-03 17:10:03', 0);
INSERT INTO `operation_record_log`
VALUES ('1301447809174310913', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@4a1a8c04,org.apache.shiro.web.servlet.ShiroHttpServletRequest@989203e',
        '2020-09-03 17:11:52', 0);
INSERT INTO `operation_record_log`
VALUES ('1301449152379195394', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@305c2311,org.apache.shiro.web.servlet.ShiroHttpServletRequest@4c61beb2',
        '2020-09-03 17:17:12', 0);
INSERT INTO `operation_record_log`
VALUES ('1301449349255630849', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@4dcdd302,org.apache.shiro.web.servlet.ShiroHttpServletRequest@7e8817fe',
        '2020-09-03 17:17:59', 0);
INSERT INTO `operation_record_log`
VALUES ('1301449421305384961', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@5de5ad05,org.apache.shiro.web.servlet.ShiroHttpServletRequest@5fc04d5e',
        '2020-09-03 17:18:16', 0);
INSERT INTO `operation_record_log`
VALUES ('1301449472618500098', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@dbc4e1e,org.apache.shiro.web.servlet.ShiroHttpServletRequest@32efea74',
        '2020-09-03 17:18:28', 0);
INSERT INTO `operation_record_log`
VALUES ('1301449529019305986', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@2a1a677f,org.apache.shiro.web.servlet.ShiroHttpServletRequest@3637d28f',
        '2020-09-03 17:18:42', 0);
INSERT INTO `operation_record_log`
VALUES ('1301449582924500994', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@71b4e5f7,org.apache.shiro.web.servlet.ShiroHttpServletRequest@4ea18cbe',
        '2020-09-03 17:18:54', 0);
INSERT INTO `operation_record_log`
VALUES ('1301482622969565185', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@3f5685c3,org.apache.shiro.web.servlet.ShiroHttpServletRequest@4e07bd64',
        '2020-09-03 19:30:12', 0);
INSERT INTO `operation_record_log`
VALUES ('1301482701545656321', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@6ffa6702,org.apache.shiro.web.servlet.ShiroHttpServletRequest@4d6f5268',
        '2020-09-03 19:30:31', 0);
INSERT INTO `operation_record_log`
VALUES ('1301482982438195202', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@1c0c11c4,org.apache.shiro.web.servlet.ShiroHttpServletRequest@1987bc53',
        '2020-09-03 19:31:38', 0);
INSERT INTO `operation_record_log`
VALUES ('1301483555480784898', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@57efe54c,org.apache.shiro.web.servlet.ShiroHttpServletRequest@39b6ef8a',
        '2020-09-03 19:33:54', 0);
INSERT INTO `operation_record_log`
VALUES ('1301483659302391809', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@2cc4a023,org.apache.shiro.web.servlet.ShiroHttpServletRequest@62e43c82',
        '2020-09-03 19:34:19', 0);
INSERT INTO `operation_record_log`
VALUES ('1301483865783783425', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@d2aeac0,org.apache.shiro.web.servlet.ShiroHttpServletRequest@65aef581',
        '2020-09-03 19:35:08', 0);
INSERT INTO `operation_record_log`
VALUES ('1301484084428656641', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@1197c230,org.apache.shiro.web.servlet.ShiroHttpServletRequest@dfdfce1',
        '2020-09-03 19:36:00', 0);
INSERT INTO `operation_record_log`
VALUES ('1301487725873057794', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@117856b,org.apache.shiro.web.servlet.ShiroHttpServletRequest@7fc52538',
        '2020-09-03 19:50:28', 0);
INSERT INTO `operation_record_log`
VALUES ('1301487753035370497', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@e4ebda6,org.apache.shiro.web.servlet.ShiroHttpServletRequest@47858b96',
        '2020-09-03 19:50:35', 0);
INSERT INTO `operation_record_log`
VALUES ('1301487937765101570', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@56729360,org.apache.shiro.web.servlet.ShiroHttpServletRequest@65044f9d',
        '2020-09-03 19:51:19', 0);
INSERT INTO `operation_record_log`
VALUES ('1301488026789203969', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@476882f7,org.apache.shiro.web.servlet.ShiroHttpServletRequest@4b55d8f9',
        '2020-09-03 19:51:40', 0);
INSERT INTO `operation_record_log`
VALUES ('1301488349440233474', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@199c07b3,org.apache.shiro.web.servlet.ShiroHttpServletRequest@6ef2a518',
        '2020-09-03 19:52:57', 0);
INSERT INTO `operation_record_log`
VALUES ('1301489115315949570', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@4a9b3ce1,org.apache.shiro.web.servlet.ShiroHttpServletRequest@7d0a70c0',
        '2020-09-03 19:56:00', 0);
INSERT INTO `operation_record_log`
VALUES ('1301489695803428865', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@3701ef04,org.apache.shiro.web.servlet.ShiroHttpServletRequest@1cf7089d',
        '2020-09-03 19:58:18', 0);
INSERT INTO `operation_record_log`
VALUES ('1301489816234479618', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@5cfd8b51,org.apache.shiro.web.servlet.ShiroHttpServletRequest@74561bff',
        '2020-09-03 19:58:47', 0);
INSERT INTO `operation_record_log`
VALUES ('1301489915949862914', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@7f8e45b7,org.apache.shiro.web.servlet.ShiroHttpServletRequest@2b0fa186',
        '2020-09-03 19:59:11', 0);
INSERT INTO `operation_record_log`
VALUES ('1301489961193820162', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@53753230,org.apache.shiro.web.servlet.ShiroHttpServletRequest@63cd7a39',
        '2020-09-03 19:59:21', 0);
INSERT INTO `operation_record_log`
VALUES ('1301490589605298178', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@5a3ac3fd,org.apache.shiro.web.servlet.ShiroHttpServletRequest@4bd42d75',
        '2020-09-03 20:01:51', 0);
INSERT INTO `operation_record_log`
VALUES ('1301490620144025601', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@123e7c38,org.apache.shiro.web.servlet.ShiroHttpServletRequest@5fa39283',
        '2020-09-03 20:01:59', 0);
INSERT INTO `operation_record_log`
VALUES ('1301501700794744833', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@27de58af,org.apache.shiro.web.servlet.ShiroHttpServletRequest@56c86fd',
        '2020-09-03 20:46:00', 0);
INSERT INTO `operation_record_log`
VALUES ('1301708050486980609', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@84f8b7b,org.apache.shiro.web.servlet.ShiroHttpServletRequest@53b3f69f',
        '2020-09-04 10:25:58', 0);
INSERT INTO `operation_record_log`
VALUES ('1301709017857060865', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@32cf4276,org.apache.shiro.web.servlet.ShiroHttpServletRequest@3b56cec2',
        '2020-09-04 10:29:49', 0);
INSERT INTO `operation_record_log`
VALUES ('1301709044629303297', 'Tohsaka Rin', 1, '上传头像', 'com.gousade.controller.UserController.userAvatorUpload',
        'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@726d4a5f',
        '2020-09-04 10:29:55', 0);
INSERT INTO `operation_record_log`
VALUES ('1301709046202167298', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@1d337771,org.apache.shiro.web.servlet.ShiroHttpServletRequest@6cf5094e',
        '2020-09-04 10:29:55', 0);
INSERT INTO `operation_record_log`
VALUES ('1301720905261182977', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@678dc2ac,org.apache.shiro.web.servlet.ShiroHttpServletRequest@54194552',
        '2020-09-04 11:17:03', 0);
INSERT INTO `operation_record_log`
VALUES ('1301737193886924802', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@336e16b7,org.apache.shiro.web.servlet.ShiroHttpServletRequest@1ff743e7',
        '2020-09-04 12:21:46', 0);
INSERT INTO `operation_record_log`
VALUES ('1301769832853393409', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@1817d87b,org.apache.shiro.web.servlet.ShiroHttpServletRequest@c72990d',
        '2020-09-04 14:31:28', 0);
INSERT INTO `operation_record_log`
VALUES ('1301772606135476225', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@7422cdae,org.apache.shiro.web.servlet.ShiroHttpServletRequest@662eff0e',
        '2020-09-04 14:42:29', 0);
INSERT INTO `operation_record_log`
VALUES ('1301772628113629186', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@4e9d85cb', '2020-09-04 14:42:34', 0);
INSERT INTO `operation_record_log`
VALUES ('1301800261497004033', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@63705539,org.apache.shiro.web.servlet.ShiroHttpServletRequest@559238c8',
        '2020-09-04 16:32:23', 0);
INSERT INTO `operation_record_log`
VALUES ('1302770278946525185', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@78913a1b,org.apache.shiro.web.servlet.ShiroHttpServletRequest@68412e20',
        '2020-09-07 08:46:53', 0);
INSERT INTO `operation_record_log`
VALUES ('1302873338007920642', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@5e0746ec,org.apache.shiro.web.servlet.ShiroHttpServletRequest@2c15e5fe',
        '2020-09-07 15:36:24', 0);
INSERT INTO `operation_record_log`
VALUES ('1302873430861422593', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@6e66d13c,org.apache.shiro.web.servlet.ShiroHttpServletRequest@406cf614',
        '2020-09-07 15:36:46', 0);
INSERT INTO `operation_record_log`
VALUES ('1302873533307297794', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@bf8900a,org.apache.shiro.web.servlet.ShiroHttpServletRequest@4f852973',
        '2020-09-07 15:37:11', 0);
INSERT INTO `operation_record_log`
VALUES ('1302873625493905410', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@4ed8ad02,org.apache.shiro.web.servlet.ShiroHttpServletRequest@46d1a05f',
        '2020-09-07 15:37:33', 0);
INSERT INTO `operation_record_log`
VALUES ('1302873753546006530', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@2b85fa,org.apache.shiro.web.servlet.ShiroHttpServletRequest@363d9794',
        '2020-09-07 15:38:03', 0);
INSERT INTO `operation_record_log`
VALUES ('1302874375141859329', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@77f6c72c,org.apache.shiro.web.servlet.ShiroHttpServletRequest@a36fbce',
        '2020-09-07 15:40:31', 0);
INSERT INTO `operation_record_log`
VALUES ('1302874432943562753', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@83016dc,org.apache.shiro.web.servlet.ShiroHttpServletRequest@3461a618',
        '2020-09-07 15:40:45', 0);
INSERT INTO `operation_record_log`
VALUES ('1302877807164661761', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@380877a7,org.apache.shiro.web.servlet.ShiroHttpServletRequest@240c3e5d',
        '2020-09-07 15:54:10', 0);
INSERT INTO `operation_record_log`
VALUES ('1302877824025763841', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@7ab59538', '2020-09-07 15:54:14', 0);
INSERT INTO `operation_record_log`
VALUES ('1302877919395848194', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@1baaa72c', '2020-09-07 15:54:36', 0);
INSERT INTO `operation_record_log`
VALUES ('1302878059397521409', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '2,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@486dabaa', '2020-09-07 15:55:10', 0);
INSERT INTO `operation_record_log`
VALUES ('1302878068985700354', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@7aa8057c', '2020-09-07 15:55:12', 0);
INSERT INTO `operation_record_log`
VALUES ('1302878362717003777', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@59878334,org.apache.shiro.web.servlet.ShiroHttpServletRequest@768a2b1e',
        '2020-09-07 15:56:22', 0);
INSERT INTO `operation_record_log`
VALUES ('1302878637301309441', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@f3871fb,org.apache.shiro.web.servlet.ShiroHttpServletRequest@7e9d04a',
        '2020-09-07 15:57:28', 0);
INSERT INTO `operation_record_log`
VALUES ('1302878731866087425', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@644523c4,org.apache.shiro.web.servlet.ShiroHttpServletRequest@2dadc396',
        '2020-09-07 15:57:50', 0);
INSERT INTO `operation_record_log`
VALUES ('1302880467506831362', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@141d965c,org.apache.shiro.web.servlet.ShiroHttpServletRequest@3513dce1',
        '2020-09-07 16:04:44', 0);
INSERT INTO `operation_record_log`
VALUES ('1302881266249113602', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@40cf6a30,org.apache.shiro.web.servlet.ShiroHttpServletRequest@3e9622e0',
        '2020-09-07 16:07:54', 0);
INSERT INTO `operation_record_log`
VALUES ('1302887121539440642', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@6adc6a80,org.apache.shiro.web.servlet.ShiroHttpServletRequest@1ef083e0',
        '2020-09-07 16:31:10', 0);
INSERT INTO `operation_record_log`
VALUES ('1302888241015955457', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@34735046,org.apache.shiro.web.servlet.ShiroHttpServletRequest@7401eff9',
        '2020-09-07 16:35:37', 0);
INSERT INTO `operation_record_log`
VALUES ('1302888680516100097', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@14777f63,org.apache.shiro.web.servlet.ShiroHttpServletRequest@346f4b71',
        '2020-09-07 16:37:22', 0);
INSERT INTO `operation_record_log`
VALUES ('1302890199185498114', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@54b0bc32,org.apache.shiro.web.servlet.ShiroHttpServletRequest@4fef92ea',
        '2020-09-07 16:43:24', 0);
INSERT INTO `operation_record_log`
VALUES ('1302891746015453185', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@25a50403,org.apache.shiro.web.servlet.ShiroHttpServletRequest@3165f788',
        '2020-09-07 16:49:33', 0);
INSERT INTO `operation_record_log`
VALUES ('1302892581457895425', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@1b84c6ba,org.apache.shiro.web.servlet.ShiroHttpServletRequest@6a5fbf66',
        '2020-09-07 16:52:52', 0);
INSERT INTO `operation_record_log`
VALUES ('1302892818347991042', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@3e3e9aa1,org.apache.shiro.web.servlet.ShiroHttpServletRequest@7d326f6a',
        '2020-09-07 16:53:49', 0);
INSERT INTO `operation_record_log`
VALUES ('1302893041350746113', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@44c197ee,org.apache.shiro.web.servlet.ShiroHttpServletRequest@34b65727',
        '2020-09-07 16:54:42', 0);
INSERT INTO `operation_record_log`
VALUES ('1302893452996517890', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@2529a109,org.apache.shiro.web.servlet.ShiroHttpServletRequest@74cfde0d',
        '2020-09-07 16:56:20', 0);
INSERT INTO `operation_record_log`
VALUES ('1302894569897730049', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@3facff88,org.apache.shiro.web.servlet.ShiroHttpServletRequest@27f5ae83',
        '2020-09-07 17:00:46', 0);
INSERT INTO `operation_record_log`
VALUES ('1302894759371218946', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@59acdce8,org.apache.shiro.web.servlet.ShiroHttpServletRequest@3cee27e4',
        '2020-09-07 17:01:31', 0);
INSERT INTO `operation_record_log`
VALUES ('1302896925217529857', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@52272126,org.apache.shiro.web.servlet.ShiroHttpServletRequest@7a7eeb70',
        '2020-09-07 17:10:08', 0);
INSERT INTO `operation_record_log`
VALUES ('1302897126242131970', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@494fd40,org.apache.shiro.web.servlet.ShiroHttpServletRequest@429c046d',
        '2020-09-07 17:10:56', 0);
INSERT INTO `operation_record_log`
VALUES ('1302898760691212290', 'Tohsaka Rin', 1, '上传头像', 'com.gousade.controller.UserController.userAvatorUpload',
        'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@86f596f',
        '2020-09-07 17:17:25', 0);
INSERT INTO `operation_record_log`
VALUES ('1302898762079526913', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@34a006cd,org.apache.shiro.web.servlet.ShiroHttpServletRequest@77e8fe4',
        '2020-09-07 17:17:26', 0);
INSERT INTO `operation_record_log`
VALUES ('1302898793729744898', 'Tohsaka Rin', 1, '上传头像', 'com.gousade.controller.UserController.userAvatorUpload',
        'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@6a63a929',
        '2020-09-07 17:17:33', 0);
INSERT INTO `operation_record_log`
VALUES ('1302898794811875330', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@64086a99,org.apache.shiro.web.servlet.ShiroHttpServletRequest@4e522338',
        '2020-09-07 17:17:34', 0);
INSERT INTO `operation_record_log`
VALUES ('1303213729009860609', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@607f5f27,org.apache.shiro.web.servlet.ShiroHttpServletRequest@7e26a975',
        '2020-09-08 14:09:00', 0);
INSERT INTO `operation_record_log`
VALUES ('1303215559169261569', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@100be4e4,org.apache.shiro.web.servlet.ShiroHttpServletRequest@7f0c7f53',
        '2020-09-08 14:16:16', 0);
INSERT INTO `operation_record_log`
VALUES ('1303232364168331266', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@4345edbe,org.apache.shiro.web.servlet.ShiroHttpServletRequest@78f130a7',
        '2020-09-08 15:23:03', 0);
INSERT INTO `operation_record_log`
VALUES ('1303233184314146817', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@61e9c092,org.apache.shiro.web.servlet.ShiroHttpServletRequest@1018c055',
        '2020-09-08 15:26:18', 0);
INSERT INTO `operation_record_log`
VALUES ('1303233208645304322', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@7612a278', '2020-09-08 15:26:24', 0);
INSERT INTO `operation_record_log`
VALUES ('1303233350114983938', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@4bf7da6d,org.apache.shiro.web.servlet.ShiroHttpServletRequest@2d2b82c4',
        '2020-09-08 15:26:58', 0);
INSERT INTO `operation_record_log`
VALUES ('1303233943755800578', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@532d66e,org.apache.shiro.web.servlet.ShiroHttpServletRequest@5f6e0397',
        '2020-09-08 15:29:19', 0);
INSERT INTO `operation_record_log`
VALUES ('1303234099741966338', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@69c4980e,org.apache.shiro.web.servlet.ShiroHttpServletRequest@55a6087b',
        '2020-09-08 15:29:56', 0);
INSERT INTO `operation_record_log`
VALUES ('1303235031326244866', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@27e716c6', '2020-09-08 15:33:39', 0);
INSERT INTO `operation_record_log`
VALUES ('1303604863284477954', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@64b4c135,org.apache.shiro.web.servlet.ShiroHttpServletRequest@1ddd8edd',
        '2020-09-09 16:03:13', 0);
INSERT INTO `operation_record_log`
VALUES ('1303613029246238722', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-09-09 16:35:40', 0);
INSERT INTO `operation_record_log`
VALUES ('1303615487506841602', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-09-09 16:45:26', 0);
INSERT INTO `operation_record_log`
VALUES ('1303897638727012353', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@4db43372,org.apache.shiro.web.servlet.ShiroHttpServletRequest@352054ae',
        '2020-09-10 11:26:36', 0);
INSERT INTO `operation_record_log`
VALUES ('1303965767683493890', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@77adcfa8,org.apache.shiro.web.servlet.ShiroHttpServletRequest@323910be',
        '2020-09-10 15:57:20', 0);
INSERT INTO `operation_record_log`
VALUES ('1303965768560103426', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@ddd48d2,org.apache.shiro.web.servlet.ShiroHttpServletRequest@5703100d',
        '2020-09-10 15:57:20', 0);
INSERT INTO `operation_record_log`
VALUES ('1303966095443185666', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@723e50fc,org.apache.shiro.web.servlet.ShiroHttpServletRequest@4814bb3a',
        '2020-09-10 15:58:38', 0);
INSERT INTO `operation_record_log`
VALUES ('1303967446092959746', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@47190d5e,org.apache.shiro.web.servlet.ShiroHttpServletRequest@5a2ae953',
        '2020-09-10 16:04:00', 0);
INSERT INTO `operation_record_log`
VALUES ('1303967551193829378', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@75f36e0f,org.apache.shiro.web.servlet.ShiroHttpServletRequest@7795775a',
        '2020-09-10 16:04:25', 0);
INSERT INTO `operation_record_log`
VALUES ('1303967622320836610', 'Tohsaka Rin', 1, '上传头像', 'com.gousade.controller.UserController.userAvatorUpload',
        'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@4fe47e19',
        '2020-09-10 16:04:42', 0);
INSERT INTO `operation_record_log`
VALUES ('1303967623809814530', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@21843e1e,org.apache.shiro.web.servlet.ShiroHttpServletRequest@592359c7',
        '2020-09-10 16:04:42', 0);
INSERT INTO `operation_record_log`
VALUES ('1303968086433157121', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@25f8f197,org.apache.shiro.web.servlet.ShiroHttpServletRequest@964a6e5',
        '2020-09-10 16:06:33', 0);
INSERT INTO `operation_record_log`
VALUES ('1303968207610793985', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@353d1b5f,org.apache.shiro.web.servlet.ShiroHttpServletRequest@1333e894',
        '2020-09-10 16:07:01', 0);
INSERT INTO `operation_record_log`
VALUES ('1303968882327506945', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@58c0abd0,org.apache.shiro.web.servlet.ShiroHttpServletRequest@3bd19471',
        '2020-09-10 16:09:42', 0);
INSERT INTO `operation_record_log`
VALUES ('1303971010362167298', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@35091d58,org.apache.shiro.web.servlet.ShiroHttpServletRequest@7b4cdce9',
        '2020-09-10 16:18:10', 0);
INSERT INTO `operation_record_log`
VALUES ('1303971218030546945', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@330eff97,org.apache.shiro.web.servlet.ShiroHttpServletRequest@58f128d',
        '2020-09-10 16:18:59', 0);
INSERT INTO `operation_record_log`
VALUES ('1303971492874899458', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@2f6f90de,org.apache.shiro.web.servlet.ShiroHttpServletRequest@69a388b2',
        '2020-09-10 16:20:05', 0);
INSERT INTO `operation_record_log`
VALUES ('1303971754528165889', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@2fac21bc,org.apache.shiro.web.servlet.ShiroHttpServletRequest@5b6cbb7a',
        '2020-09-10 16:21:07', 0);
INSERT INTO `operation_record_log`
VALUES ('1303972070476697601', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@7eac5743,org.apache.shiro.web.servlet.ShiroHttpServletRequest@4361ae01',
        '2020-09-10 16:22:22', 0);
INSERT INTO `operation_record_log`
VALUES ('1303972457002782722', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@17a5134,org.apache.shiro.web.servlet.ShiroHttpServletRequest@9738730',
        '2020-09-10 16:23:55', 0);
INSERT INTO `operation_record_log`
VALUES ('1303972511805558785', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@6a8f333e,org.apache.shiro.web.servlet.ShiroHttpServletRequest@2b5294d',
        '2020-09-10 16:24:08', 0);
INSERT INTO `operation_record_log`
VALUES ('1303973064249921537', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@1a71119a,org.apache.shiro.web.servlet.ShiroHttpServletRequest@5f2548f6',
        '2020-09-10 16:26:19', 0);
INSERT INTO `operation_record_log`
VALUES ('1303973158403657729', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@1306d2bf,org.apache.shiro.web.servlet.ShiroHttpServletRequest@45efad79',
        '2020-09-10 16:26:42', 0);
INSERT INTO `operation_record_log`
VALUES ('1303973492421251073', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@1a20af22,org.apache.shiro.web.servlet.ShiroHttpServletRequest@39b9c83a',
        '2020-09-10 16:28:01', 0);
INSERT INTO `operation_record_log`
VALUES ('1304311878784905217', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@66752a4,org.apache.shiro.web.servlet.ShiroHttpServletRequest@2864d9f0',
        '2020-09-11 14:52:39', 0);
INSERT INTO `operation_record_log`
VALUES ('1304311934736920578', 'Tohsaka Rin', 1, '上传头像', 'com.gousade.controller.UserController.userAvatorUpload',
        'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@1a6cf854',
        '2020-09-11 14:52:52', 0);
INSERT INTO `operation_record_log`
VALUES ('1304311936192344065', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@740dc7e7,org.apache.shiro.web.servlet.ShiroHttpServletRequest@50346a7c',
        '2020-09-11 14:52:53', 0);
INSERT INTO `operation_record_log`
VALUES ('1304344760886648833', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@6695b5fc,org.apache.shiro.web.servlet.ShiroHttpServletRequest@52c3f1c7',
        '2020-09-11 17:03:19', 0);
INSERT INTO `operation_record_log`
VALUES ('1304419062760140801', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@710f41a,org.apache.shiro.web.servlet.ShiroHttpServletRequest@677fa3d6',
        '2020-09-11 21:58:34', 0);
INSERT INTO `operation_record_log`
VALUES ('1304730811319296002', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@2c919112,org.apache.shiro.web.servlet.ShiroHttpServletRequest@73770f42',
        '2020-09-12 18:37:20', 0);
INSERT INTO `operation_record_log`
VALUES ('1304733323883450369', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@4f8db936,org.apache.shiro.web.servlet.ShiroHttpServletRequest@1610a865',
        '2020-09-12 18:47:19', 0);
INSERT INTO `operation_record_log`
VALUES ('1304733535205064705', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@5750aaa,org.apache.shiro.web.servlet.ShiroHttpServletRequest@4d5405f6',
        '2020-09-12 18:48:10', 0);
INSERT INTO `operation_record_log`
VALUES ('1304736140312457217', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@150d1996,org.apache.shiro.web.servlet.ShiroHttpServletRequest@3f8f1623',
        '2020-09-12 18:58:31', 0);
INSERT INTO `operation_record_log`
VALUES ('1304741751007748097', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@61231209,org.apache.shiro.web.servlet.ShiroHttpServletRequest@4e386cb',
        '2020-09-12 19:20:49', 0);
INSERT INTO `operation_record_log`
VALUES ('1304741924953923585', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@4140212c,org.apache.shiro.web.servlet.ShiroHttpServletRequest@3eac52d3',
        '2020-09-12 19:21:30', 0);
INSERT INTO `operation_record_log`
VALUES ('1304990090135711745', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@55532e63,org.apache.shiro.web.servlet.ShiroHttpServletRequest@6bf2b4cf',
        '2020-09-13 11:47:37', 0);
INSERT INTO `operation_record_log`
VALUES ('1305000260005810177', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@5b1f0169,org.apache.shiro.web.servlet.ShiroHttpServletRequest@55707c10',
        '2020-09-13 12:28:02', 0);
INSERT INTO `operation_record_log`
VALUES ('1305003538085163010', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@631ee452,org.apache.shiro.web.servlet.ShiroHttpServletRequest@24b42cc0',
        '2020-09-13 12:41:03', 0);
INSERT INTO `operation_record_log`
VALUES ('1305009682807640066', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@19766e54,org.apache.shiro.web.servlet.ShiroHttpServletRequest@7e8ac70c',
        '2020-09-13 13:05:28', 0);
INSERT INTO `operation_record_log`
VALUES ('1305010881795465217', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@6aa81b91,org.apache.shiro.web.servlet.ShiroHttpServletRequest@6c3d03d8',
        '2020-09-13 13:10:14', 0);
INSERT INTO `operation_record_log`
VALUES ('1305013922628173825', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@5546201d,org.apache.shiro.web.servlet.ShiroHttpServletRequest@73df1cce',
        '2020-09-13 13:22:19', 0);
INSERT INTO `operation_record_log`
VALUES ('1305013998972895233', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@47218a5', '2020-09-13 13:22:38', 0);
INSERT INTO `operation_record_log`
VALUES ('1305015225265709058', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@48d81195,org.apache.shiro.web.servlet.ShiroHttpServletRequest@48da733a',
        '2020-09-13 13:27:30', 0);
INSERT INTO `operation_record_log`
VALUES ('1305017395939749889', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@300c24a2,org.apache.shiro.web.servlet.ShiroHttpServletRequest@7899512',
        '2020-09-13 13:36:07', 0);
INSERT INTO `operation_record_log`
VALUES ('1305019452453146625', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@bd9ff76,org.apache.shiro.web.servlet.ShiroHttpServletRequest@4a42c4e8',
        '2020-09-13 13:44:18', 0);
INSERT INTO `operation_record_log`
VALUES ('1305316749394755585', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@4dc3e9ff,org.apache.shiro.web.servlet.ShiroHttpServletRequest@5603346',
        '2020-09-14 09:25:39', 0);
INSERT INTO `operation_record_log`
VALUES ('1305319641166036993', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@47056699,org.apache.shiro.web.servlet.ShiroHttpServletRequest@2f0193d0',
        '2020-09-14 09:37:08', 0);
INSERT INTO `operation_record_log`
VALUES ('1305322666324652033', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@720a0a39,org.apache.shiro.web.servlet.ShiroHttpServletRequest@647c118e',
        '2020-09-14 09:49:10', 0);
INSERT INTO `operation_record_log`
VALUES ('1305328689420693505', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@29917c0b,org.apache.shiro.web.servlet.ShiroHttpServletRequest@200d5d0a',
        '2020-09-14 10:13:06', 0);
INSERT INTO `operation_record_log`
VALUES ('1305349729161035777', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@33dc3c0d,org.apache.shiro.web.servlet.ShiroHttpServletRequest@e3cb360',
        '2020-09-14 11:36:42', 0);
INSERT INTO `operation_record_log`
VALUES ('1305410433788792833', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@77df12d3,org.apache.shiro.web.servlet.ShiroHttpServletRequest@3bca7b9',
        '2020-09-14 15:37:55', 0);
INSERT INTO `operation_record_log`
VALUES ('1305410924807573505', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@77385697', '2020-09-14 15:39:52', 0);
INSERT INTO `operation_record_log`
VALUES ('1305476737671393281', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@36b0147a,org.apache.shiro.web.servlet.ShiroHttpServletRequest@43049f25',
        '2020-09-14 20:01:23', 0);
INSERT INTO `operation_record_log`
VALUES ('1305477242829144066', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@5b5eb19a,org.apache.shiro.web.servlet.ShiroHttpServletRequest@6a7f9ea6',
        '2020-09-14 20:03:23', 0);
INSERT INTO `operation_record_log`
VALUES ('1305477269358116866', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@5e19335,org.apache.shiro.web.servlet.ShiroHttpServletRequest@1a745642',
        '2020-09-14 20:03:30', 0);
INSERT INTO `operation_record_log`
VALUES ('1305477287511064578', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-09-14 20:03:34', 0);
INSERT INTO `operation_record_log`
VALUES ('1305478045845434369', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@33222ba6,org.apache.shiro.web.servlet.ShiroHttpServletRequest@39d23577',
        '2020-09-14 20:06:35', 0);
INSERT INTO `operation_record_log`
VALUES ('1305478057140695042', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-09-14 20:06:38', 0);
INSERT INTO `operation_record_log`
VALUES ('1305478302331318274', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@7100dd52,org.apache.shiro.web.servlet.ShiroHttpServletRequest@38ab4ec3',
        '2020-09-14 20:07:36', 0);
INSERT INTO `operation_record_log`
VALUES ('1305478314033426434', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-09-14 20:07:39', 0);
INSERT INTO `operation_record_log`
VALUES ('1305478486847139841', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-09-14 20:08:20', 0);
INSERT INTO `operation_record_log`
VALUES ('1305478578727563265', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-09-14 20:08:42', 0);
INSERT INTO `operation_record_log`
VALUES ('1305478816934768642', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@597d75a5,org.apache.shiro.web.servlet.ShiroHttpServletRequest@64336860',
        '2020-09-14 20:09:39', 0);
INSERT INTO `operation_record_log`
VALUES ('1305478828532019201', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-09-14 20:09:42', 0);
INSERT INTO `operation_record_log`
VALUES ('1305481944438419458', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@625f3cdf,org.apache.shiro.web.servlet.ShiroHttpServletRequest@4facf084',
        '2020-09-14 20:22:04', 0);
INSERT INTO `operation_record_log`
VALUES ('1305481975887310850', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-09-14 20:22:12', 0);
INSERT INTO `operation_record_log`
VALUES ('1305482287427629057', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@6e89365,org.apache.shiro.web.servlet.ShiroHttpServletRequest@2f7d8ada',
        '2020-09-14 20:23:26', 0);
INSERT INTO `operation_record_log`
VALUES ('1305482307241521153', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-09-14 20:23:31', 0);
INSERT INTO `operation_record_log`
VALUES ('1305753702804606977', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@5db73165,org.apache.shiro.web.servlet.ShiroHttpServletRequest@39df9dac',
        '2020-09-15 14:21:57', 0);
INSERT INTO `operation_record_log`
VALUES ('1306044793025675266', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@139fa2df,org.apache.shiro.web.servlet.ShiroHttpServletRequest@abb8272',
        '2020-09-16 09:38:38', 0);
INSERT INTO `operation_record_log`
VALUES ('1306120356344066049', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@391026e2,org.apache.shiro.web.servlet.ShiroHttpServletRequest@fdae470',
        '2020-09-16 14:38:54', 0);
INSERT INTO `operation_record_log`
VALUES ('1306846512177442818', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@66f6d8b0,org.apache.shiro.web.servlet.ShiroHttpServletRequest@6f3d0905',
        '2020-09-18 14:44:23', 0);
INSERT INTO `operation_record_log`
VALUES ('1306868616948064257', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@27b3f3d3,org.apache.shiro.web.servlet.ShiroHttpServletRequest@668c77da',
        '2020-09-18 16:12:13', 0);
INSERT INTO `operation_record_log`
VALUES ('1306869108860231681', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@3263b0c7', '2020-09-18 16:14:10', 0);
INSERT INTO `operation_record_log`
VALUES ('1307938056875683841', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@63ccb95f,org.apache.shiro.web.servlet.ShiroHttpServletRequest@bff95ae',
        '2020-09-21 15:01:47', 0);
INSERT INTO `operation_record_log`
VALUES ('1307938158117793793', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@5f7fe29c', '2020-09-21 15:02:11', 0);
INSERT INTO `operation_record_log`
VALUES ('1307940968062398466', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@31c187a2,org.apache.shiro.web.servlet.ShiroHttpServletRequest@19848c3a',
        '2020-09-21 15:13:21', 0);
INSERT INTO `operation_record_log`
VALUES ('1307961700171599874', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@4de1a428,org.apache.shiro.web.servlet.ShiroHttpServletRequest@71238726',
        '2020-09-21 16:35:44', 0);
INSERT INTO `operation_record_log`
VALUES ('1307961720505585666', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@257509ae', '2020-09-21 16:35:49', 0);
INSERT INTO `operation_record_log`
VALUES ('1307962961272983553', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@6b0017b', '2020-09-21 16:40:45', 0);
INSERT INTO `operation_record_log`
VALUES ('1307971947170852865', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@2a7fac82,org.apache.shiro.web.servlet.ShiroHttpServletRequest@30126d1f',
        '2020-09-21 17:16:27', 0);
INSERT INTO `operation_record_log`
VALUES ('1307972293628751874', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@13cbd7b5', '2020-09-21 17:17:50', 0);
INSERT INTO `operation_record_log`
VALUES ('1307972429566177281', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@df741da', '2020-09-21 17:18:22', 0);
INSERT INTO `operation_record_log`
VALUES ('1307991578388840450', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@66e5db99,org.apache.shiro.web.servlet.ShiroHttpServletRequest@4f4ef4d1',
        '2020-09-21 18:34:28', 0);
INSERT INTO `operation_record_log`
VALUES ('1307991837552693250', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@2af1e0a8,org.apache.shiro.web.servlet.ShiroHttpServletRequest@27466d8f',
        '2020-09-21 18:35:30', 0);
INSERT INTO `operation_record_log`
VALUES ('1307995763362512898', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@70ce7b27,org.apache.shiro.web.servlet.ShiroHttpServletRequest@37604b78',
        '2020-09-21 18:51:06', 0);
INSERT INTO `operation_record_log`
VALUES ('1307996551396737025', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@31b7841b,org.apache.shiro.web.servlet.ShiroHttpServletRequest@28225ab9',
        '2020-09-21 18:54:13', 0);
INSERT INTO `operation_record_log`
VALUES ('1307996781143932930', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@44cae325,org.apache.shiro.web.servlet.ShiroHttpServletRequest@266238d2',
        '2020-09-21 18:55:08', 0);
INSERT INTO `operation_record_log`
VALUES ('1307997255221919745', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@38757b7e,org.apache.shiro.web.servlet.ShiroHttpServletRequest@37c316bd',
        '2020-09-21 18:57:01', 0);
INSERT INTO `operation_record_log`
VALUES ('1307997412470571010', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@7cd6687f,org.apache.shiro.web.servlet.ShiroHttpServletRequest@5808bb29',
        '2020-09-21 18:57:39', 0);
INSERT INTO `operation_record_log`
VALUES ('1307997676560728066', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@708f4910,org.apache.shiro.web.servlet.ShiroHttpServletRequest@2cc10376',
        '2020-09-21 18:58:42', 0);
INSERT INTO `operation_record_log`
VALUES ('1307997767803617281', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@21511004,org.apache.shiro.web.servlet.ShiroHttpServletRequest@14ba0a8b',
        '2020-09-21 18:59:03', 0);
INSERT INTO `operation_record_log`
VALUES ('1307999204497539074', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@52003e35,org.apache.shiro.web.servlet.ShiroHttpServletRequest@6ddbf26d',
        '2020-09-21 19:04:46', 0);
INSERT INTO `operation_record_log`
VALUES ('1308002635373096962', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@375d7b0f,org.apache.shiro.web.servlet.ShiroHttpServletRequest@187dcc96',
        '2020-09-21 19:18:24', 0);
INSERT INTO `operation_record_log`
VALUES ('1308003349293969410', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@fbd8b1e,org.apache.shiro.web.servlet.ShiroHttpServletRequest@1a4aa363',
        '2020-09-21 19:21:14', 0);
INSERT INTO `operation_record_log`
VALUES ('1308003924685369346', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@61a88d6a,org.apache.shiro.web.servlet.ShiroHttpServletRequest@2e32989e',
        '2020-09-21 19:23:31', 0);
INSERT INTO `operation_record_log`
VALUES ('1308003948710342658', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@7b5bd737', '2020-09-21 19:23:37', 0);
INSERT INTO `operation_record_log`
VALUES ('1308004944291311618', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@3318ccba,org.apache.shiro.web.servlet.ShiroHttpServletRequest@342d2867',
        '2020-09-21 19:27:34', 0);
INSERT INTO `operation_record_log`
VALUES ('1308205705262190593', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@6fa3359e,org.apache.shiro.web.servlet.ShiroHttpServletRequest@68e7ac74',
        '2020-09-22 08:45:20', 0);
INSERT INTO `operation_record_log`
VALUES ('1308207399219978242', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@710146aa,org.apache.shiro.web.servlet.ShiroHttpServletRequest@38d6fe3',
        '2020-09-22 08:52:03', 0);
INSERT INTO `operation_record_log`
VALUES ('1308310099274207233', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@1f017bba,org.apache.shiro.web.servlet.ShiroHttpServletRequest@74cc1ddc',
        '2020-09-22 15:40:09', 0);
INSERT INTO `operation_record_log`
VALUES ('1308310186918383618', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@1b07c730', '2020-09-22 15:40:30', 0);
INSERT INTO `operation_record_log`
VALUES ('1308310248448823297', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@6091ad92', '2020-09-22 15:40:45', 0);
INSERT INTO `operation_record_log`
VALUES ('1308311698163863553', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@529ecc50,org.apache.shiro.web.servlet.ShiroHttpServletRequest@3e6648cb',
        '2020-09-22 15:46:30', 0);
INSERT INTO `operation_record_log`
VALUES ('1308311723551985666', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@2088d402', '2020-09-22 15:46:36', 0);
INSERT INTO `operation_record_log`
VALUES ('1308312283931971585', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@43984f7b,org.apache.shiro.web.servlet.ShiroHttpServletRequest@45a79bcd',
        '2020-09-22 15:48:50', 0);
INSERT INTO `operation_record_log`
VALUES ('1308318589371781122', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@2d65251c,org.apache.shiro.web.servlet.ShiroHttpServletRequest@3a526611',
        '2020-09-22 16:13:53', 0);
INSERT INTO `operation_record_log`
VALUES ('1308318889058996225', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@2176554d,org.apache.shiro.web.servlet.ShiroHttpServletRequest@42695e33',
        '2020-09-22 16:15:05', 0);
INSERT INTO `operation_record_log`
VALUES ('1308319115614326785', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@60eba211,org.apache.shiro.web.servlet.ShiroHttpServletRequest@1045b216',
        '2020-09-22 16:15:59', 0);
INSERT INTO `operation_record_log`
VALUES ('1308321016833609730', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-09-22 16:23:32', 0);
INSERT INTO `operation_record_log`
VALUES ('1308321148601864193', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@7604a210,org.apache.shiro.web.servlet.ShiroHttpServletRequest@4119afc',
        '2020-09-22 16:24:03', 0);
INSERT INTO `operation_record_log`
VALUES ('1308321255372066818', 'rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@45693877,org.apache.shiro.web.servlet.ShiroHttpServletRequest@7bef3c77',
        '2020-09-22 16:24:29', 0);
INSERT INTO `operation_record_log`
VALUES ('1308321448008060930', 'rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode', '1',
        '2020-09-22 16:25:15', 0);
INSERT INTO `operation_record_log`
VALUES ('1308321757845491713', 'rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode', '1',
        '2020-09-22 16:26:29', 0);
INSERT INTO `operation_record_log`
VALUES ('1308322041808261121', 'rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@c71f0f6,org.apache.shiro.web.servlet.ShiroHttpServletRequest@1b6c39b8',
        '2020-09-22 16:27:36', 0);
INSERT INTO `operation_record_log`
VALUES ('1308322518155366401', 'rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@3434b302,org.apache.shiro.web.servlet.ShiroHttpServletRequest@291b4213',
        '2020-09-22 16:29:30', 0);
INSERT INTO `operation_record_log`
VALUES ('1308322628239069185', 'rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@7252c792,org.apache.shiro.web.servlet.ShiroHttpServletRequest@71fe8c40',
        '2020-09-22 16:29:56', 0);
INSERT INTO `operation_record_log`
VALUES ('1308322693766680578', 'rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode', '151********',
        '2020-09-22 16:30:12', 0);
INSERT INTO `operation_record_log`
VALUES ('1308323790187106305', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@431f053f,org.apache.shiro.web.servlet.ShiroHttpServletRequest@96614c7',
        '2020-09-22 16:34:33', 0);
INSERT INTO `operation_record_log`
VALUES ('1308324392036175874', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@7bd065d4', '2020-09-22 16:36:57', 0);
INSERT INTO `operation_record_log`
VALUES ('1308576146287976449', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@4b97ce99,org.apache.shiro.web.servlet.ShiroHttpServletRequest@2453b193',
        '2020-09-23 09:17:20', 0);
INSERT INTO `operation_record_log`
VALUES ('1308576186112892929', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-09-23 09:17:29', 0);
INSERT INTO `operation_record_log`
VALUES ('1308578391872208898', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@18f84463,org.apache.shiro.web.servlet.ShiroHttpServletRequest@79af09b8',
        '2020-09-23 09:26:15', 0);
INSERT INTO `operation_record_log`
VALUES ('1308578735159214081', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-09-23 09:27:37', 0);
INSERT INTO `operation_record_log`
VALUES ('1308580476470640641', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@35bfd7f9,org.apache.shiro.web.servlet.ShiroHttpServletRequest@544ba339',
        '2020-09-23 09:34:32', 0);
INSERT INTO `operation_record_log`
VALUES ('1308580701465690113', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-09-23 09:35:26', 0);
INSERT INTO `operation_record_log`
VALUES ('1308581242333777922', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@483b74be,org.apache.shiro.web.servlet.ShiroHttpServletRequest@3697ded4',
        '2020-09-23 09:37:35', 0);
INSERT INTO `operation_record_log`
VALUES ('1308581329202008065', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-09-23 09:37:55', 0);
INSERT INTO `operation_record_log`
VALUES ('1308581481916616706', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@717c817a,org.apache.shiro.web.servlet.ShiroHttpServletRequest@691f5eaf',
        '2020-09-23 09:38:32', 0);
INSERT INTO `operation_record_log`
VALUES ('1308581530759286785', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-09-23 09:38:43', 0);
INSERT INTO `operation_record_log`
VALUES ('1308581645548998657', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@650cec9e,org.apache.shiro.web.servlet.ShiroHttpServletRequest@7ae5957c',
        '2020-09-23 09:39:11', 0);
INSERT INTO `operation_record_log`
VALUES ('1308582024726663169', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-09-23 09:40:41', 0);
INSERT INTO `operation_record_log`
VALUES ('1308582743710060545', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@4a916142,org.apache.shiro.web.servlet.ShiroHttpServletRequest@664543e6',
        '2020-09-23 09:43:33', 0);
INSERT INTO `operation_record_log`
VALUES ('1308582891882237954', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-09-23 09:44:08', 0);
INSERT INTO `operation_record_log`
VALUES ('1308582969975984130', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@6e01a32c,org.apache.shiro.web.servlet.ShiroHttpServletRequest@7841acab',
        '2020-09-23 09:44:26', 0);
INSERT INTO `operation_record_log`
VALUES ('1308583099236044802', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-09-23 09:44:57', 0);
INSERT INTO `operation_record_log`
VALUES ('1308585297802104833', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@7d79c946,org.apache.shiro.web.servlet.ShiroHttpServletRequest@47ce1000',
        '2020-09-23 09:53:41', 0);
INSERT INTO `operation_record_log`
VALUES ('1308585387136585730', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-09-23 09:54:03', 0);
INSERT INTO `operation_record_log`
VALUES ('1308586682245410818', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@7eb10a2e,org.apache.shiro.web.servlet.ShiroHttpServletRequest@5608acaf',
        '2020-09-23 09:59:12', 0);
INSERT INTO `operation_record_log`
VALUES ('1308586701929279490', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-09-23 09:59:16', 0);
INSERT INTO `operation_record_log`
VALUES ('1308587443557732353', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@8710ca8,org.apache.shiro.web.servlet.ShiroHttpServletRequest@7fde8b91',
        '2020-09-23 10:02:13', 0);
INSERT INTO `operation_record_log`
VALUES ('1308589271955505154', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@24ad7ff2,org.apache.shiro.web.servlet.ShiroHttpServletRequest@4e4cff25',
        '2020-09-23 10:09:29', 0);
INSERT INTO `operation_record_log`
VALUES ('1308589316553539585', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-09-23 10:09:40', 0);
INSERT INTO `operation_record_log`
VALUES ('1308655729146310658', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@4976fec7,org.apache.shiro.web.servlet.ShiroHttpServletRequest@51fa1b0a',
        '2020-09-23 14:33:34', 0);
INSERT INTO `operation_record_log`
VALUES ('1308671249790767106', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@61378321,org.apache.shiro.web.servlet.ShiroHttpServletRequest@5bd0ad35',
        '2020-09-23 15:35:14', 0);
INSERT INTO `operation_record_log`
VALUES ('1308671770341613570', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@6210aaa1,org.apache.shiro.web.servlet.ShiroHttpServletRequest@1d4d4b0a',
        '2020-09-23 07:37:18', 0);
INSERT INTO `operation_record_log`
VALUES ('1308695710556360706', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@7a314237,org.apache.shiro.web.servlet.ShiroHttpServletRequest@6aa9bccb',
        '2020-09-23 17:12:26', 0);
INSERT INTO `operation_record_log`
VALUES ('1308695762653810690', 'Tohsaka Rin', 1, '上传头像', 'com.gousade.controller.UserController.userAvatarUpload',
        'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@68ff573f',
        '2020-09-23 17:12:38', 0);
INSERT INTO `operation_record_log`
VALUES ('1308695764000182274', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@7d0df504,org.apache.shiro.web.servlet.ShiroHttpServletRequest@7744f211',
        '2020-09-23 17:12:39', 0);
INSERT INTO `operation_record_log`
VALUES ('1308696018258890753', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@5f3b69ad,org.apache.shiro.web.servlet.ShiroHttpServletRequest@297b1914',
        '2020-09-23 17:13:39', 0);
INSERT INTO `operation_record_log`
VALUES ('1308696090690326530', 'Tohsaka Rin', 1, '上传oss头像', 'com.gousade.controller.UserController.uploadOssAvatar',
        'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@675258ef',
        '2020-09-23 17:13:57', 0);
INSERT INTO `operation_record_log`
VALUES ('1308696091675987969', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@553d300d,org.apache.shiro.web.servlet.ShiroHttpServletRequest@a8c52f4',
        '2020-09-23 17:13:57', 0);
INSERT INTO `operation_record_log`
VALUES ('1308698748188794882', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@4cc3bff1,org.apache.shiro.web.servlet.ShiroHttpServletRequest@7e86d1c5',
        '2020-09-23 17:24:30', 0);
INSERT INTO `operation_record_log`
VALUES ('1308698873606873090', 'Tohsaka Rin', 1, '上传oss头像', 'com.gousade.controller.UserController.uploadOssAvatar',
        'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@7d1091c2',
        '2020-09-23 17:25:00', 0);
INSERT INTO `operation_record_log`
VALUES ('1308941811851108353', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@4ef64b60,org.apache.shiro.web.servlet.ShiroHttpServletRequest@1b77a591',
        '2020-09-24 09:30:21', 0);
INSERT INTO `operation_record_log`
VALUES ('1308942497573675010', 'Tohsaka Rin', 1, '上传oss头像', 'com.gousade.controller.UserController.uploadOssAvatar',
        'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@203d173a',
        '2020-09-24 09:33:05', 0);
INSERT INTO `operation_record_log`
VALUES ('1308944284338790402', 'Tohsaka Rin', 2, '获取oss头像', 'com.gousade.controller.UserController.getOssAvatar', NULL,
        '2020-09-24 09:40:11', 0);
INSERT INTO `operation_record_log`
VALUES ('1308944683737194497', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@589b52fa,org.apache.shiro.web.servlet.ShiroHttpServletRequest@1008348c',
        '2020-09-24 09:41:46', 0);
INSERT INTO `operation_record_log`
VALUES ('1308944732466618370', 'Tohsaka Rin', 1, '上传oss头像', 'com.gousade.controller.UserController.uploadOssAvatar',
        'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@78d94366',
        '2020-09-24 09:41:57', 0);
INSERT INTO `operation_record_log`
VALUES ('1308944733095763969', 'Tohsaka Rin', 2, '获取oss头像', 'com.gousade.controller.UserController.getOssAvatar', NULL,
        '2020-09-24 09:41:58', 0);
INSERT INTO `operation_record_log`
VALUES ('1308945813292621826', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@4f586122,org.apache.shiro.web.servlet.ShiroHttpServletRequest@723c29cb',
        '2020-09-24 09:46:15', 0);
INSERT INTO `operation_record_log`
VALUES ('1308946321130561537', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@61c49c70,org.apache.shiro.web.servlet.ShiroHttpServletRequest@673cb6ef',
        '2020-09-24 09:48:16', 0);
INSERT INTO `operation_record_log`
VALUES ('1308946589276610561', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@608596c9,org.apache.shiro.web.servlet.ShiroHttpServletRequest@516318a6',
        '2020-09-24 09:49:20', 0);
INSERT INTO `operation_record_log`
VALUES ('1309045879143534593', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@6d87cf49', '2020-09-24 16:23:53', 0);
INSERT INTO `operation_record_log`
VALUES ('1309407692104355842', 'Tohsaka Rin', 1, '上传excel测试', 'com.gousade.controller.EasyExcelDataController.upload',
        'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@29c8e5bb',
        '2020-09-25 16:21:36', 0);
INSERT INTO `operation_record_log`
VALUES ('1309408103536218113', 'Tohsaka Rin', 1, '上传excel测试', 'com.gousade.controller.EasyExcelDataController.upload',
        'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@2d20fa68',
        '2020-09-25 16:23:14', 0);
INSERT INTO `operation_record_log`
VALUES ('1309408474186862594', 'Tohsaka Rin', 1, '上传excel测试', 'com.gousade.controller.EasyExcelDataController.upload',
        'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@25f2d2ff',
        '2020-09-25 16:24:42', 0);
INSERT INTO `operation_record_log`
VALUES ('1309408856401203202', 'Tohsaka Rin', 1, '上传excel测试', 'com.gousade.controller.EasyExcelDataController.upload',
        'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@582a7dd',
        '2020-09-25 16:26:13', 0);
INSERT INTO `operation_record_log`
VALUES ('1309409695882760193', 'Tohsaka Rin', 1, '上传excel测试', 'com.gousade.controller.EasyExcelDataController.upload',
        'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@3182a6b5',
        '2020-09-25 16:29:33', 0);
INSERT INTO `operation_record_log`
VALUES ('1309410276496068610', 'Tohsaka Rin', 1, '上传excel测试', 'com.gousade.controller.EasyExcelDataController.upload',
        'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@6d378b62',
        '2020-09-25 16:31:52', 0);
INSERT INTO `operation_record_log`
VALUES ('1309417857377587201', 'Tohsaka Rin', 1, '上传excel测试', 'com.gousade.controller.EasyExcelDataController.upload',
        'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@26bd2d48',
        '2020-09-25 17:01:59', 0);
INSERT INTO `operation_record_log`
VALUES ('1310022860161105921', 'Tohsaka Rin', 1, '上传excel测试', 'com.gousade.controller.EasyExcelDataController.upload',
        'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@4aa2f42a',
        '2020-09-27 09:06:03', 0);
INSERT INTO `operation_record_log`
VALUES ('1310023065686196225', 'Tohsaka Rin', 1, '上传excel测试', 'com.gousade.controller.EasyExcelDataController.upload',
        'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@5d0637bb',
        '2020-09-27 09:06:52', 0);
INSERT INTO `operation_record_log`
VALUES ('1310027430467682305', 'Tohsaka Rin', 2, '下载excel测试', 'com.gousade.controller.EasyExcelDataController.download',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@45ee3239', '2020-09-27 09:24:13',
        0);
INSERT INTO `operation_record_log`
VALUES ('1310028014272856065', 'Tohsaka Rin', 2, '下载excel测试', 'com.gousade.controller.EasyExcelDataController.download',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@6a5002d8', '2020-09-27 09:26:32',
        0);
INSERT INTO `operation_record_log`
VALUES ('1310031638583525378', 'Tohsaka Rin', 2, '下载excel测试', 'com.gousade.controller.EasyExcelDataController.download',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@48008492', '2020-09-27 09:40:56',
        0);
INSERT INTO `operation_record_log`
VALUES ('1310032003320201217', 'Tohsaka Rin', 2, '下载excel测试', 'com.gousade.controller.EasyExcelDataController.download',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@39003223', '2020-09-27 09:42:23',
        0);
INSERT INTO `operation_record_log`
VALUES ('1310049411275567106', 'Tohsaka Rin', 1, '上传excel测试', 'com.gousade.controller.EasyExcelDataController.upload',
        'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@e829ba5',
        '2020-09-27 10:51:33', 0);
INSERT INTO `operation_record_log`
VALUES ('1310103109850882049', 'Tohsaka Rin', 1, '上传excel测试', 'com.gousade.controller.EasyExcelDataController.upload',
        'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@475afd8c',
        '2020-09-27 14:24:56', 0);
INSERT INTO `operation_record_log`
VALUES ('1310103287899086849', 'Tohsaka Rin', 1, '上传excel测试', 'com.gousade.controller.EasyExcelDataController.upload',
        'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@5b9c37cd',
        '2020-09-27 14:25:39', 0);
INSERT INTO `operation_record_log`
VALUES ('1310103881778978818', 'Tohsaka Rin', 1, '上传excel测试', 'com.gousade.controller.EasyExcelDataController.upload',
        'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@511e8a77',
        '2020-09-27 14:28:00', 0);
INSERT INTO `operation_record_log`
VALUES ('1310104038247489537', 'Tohsaka Rin', 1, '上传excel测试', 'com.gousade.controller.EasyExcelDataController.upload',
        'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@5fe9a703',
        '2020-09-27 14:28:37', 0);
INSERT INTO `operation_record_log`
VALUES ('1310107532241133570', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@5407d5a8', '2020-09-27 14:42:30', 0);
INSERT INTO `operation_record_log`
VALUES ('1310411530005327873', 'Tohsaka Rin', 1, '上传excel测试', 'com.gousade.controller.EasyExcelDataController.upload',
        'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@5007b7b',
        '2020-09-28 10:50:29', 0);
INSERT INTO `operation_record_log`
VALUES ('1310412731304349697', 'Tohsaka Rin', 2, '下载excel测试', 'com.gousade.controller.EasyExcelDataController.download',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@bb6c2d8', '2020-09-28 10:55:16',
        0);
INSERT INTO `operation_record_log`
VALUES ('1310414830931001345', 'Tohsaka Rin', 1, '上传excel测试', 'com.gousade.controller.EasyExcelDataController.upload',
        'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@74dd912f',
        '2020-09-28 11:03:36', 0);
INSERT INTO `operation_record_log`
VALUES ('1310414927576154114', 'Tohsaka Rin', 1, '上传excel测试', 'com.gousade.controller.EasyExcelDataController.upload',
        'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@7d988b22',
        '2020-09-28 11:03:59', 0);
INSERT INTO `operation_record_log`
VALUES ('1311223409980116994', 'Tohsaka Rin', 1, '上传excel测试', 'com.gousade.controller.EasyExcelDataController.upload',
        'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@28dd2140',
        '2020-09-30 16:36:36', 0);
INSERT INTO `operation_record_log`
VALUES ('1316210920217755650', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@26fb507,org.apache.shiro.web.servlet.ShiroHttpServletRequest@9aa285d',
        '2020-10-14 10:55:12', 0);
INSERT INTO `operation_record_log`
VALUES ('1316217281022377986', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@6f0185ef,org.apache.shiro.web.servlet.ShiroHttpServletRequest@3fa566d8',
        '2020-10-14 11:20:28', 0);
INSERT INTO `operation_record_log`
VALUES ('1316222141407019010', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@2ef3a777', '2020-10-14 11:39:47', 0);
INSERT INTO `operation_record_log`
VALUES ('1316658526559518721', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@514cbbf8,org.apache.shiro.web.servlet.ShiroHttpServletRequest@27518a79',
        '2020-10-15 16:33:49', 0);
INSERT INTO `operation_record_log`
VALUES ('1318002444039036930', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@4bc3f51', '2020-10-19 09:34:04', 0);
INSERT INTO `operation_record_log`
VALUES ('1318445973227941889', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@3b4bd761', '2020-10-20 14:56:30', 0);
INSERT INTO `operation_record_log`
VALUES ('1318446919412592642', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@464f0da8', '2020-10-20 15:00:15', 0);
INSERT INTO `operation_record_log`
VALUES ('1318870853243392001', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@694e40e4', '2020-10-21 19:04:49', 0);
INSERT INTO `operation_record_log`
VALUES ('1328879762874155009', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@277af94d,org.apache.shiro.web.servlet.ShiroHttpServletRequest@11ebebd4',
        '2020-11-18 09:56:39', 0);
INSERT INTO `operation_record_log`
VALUES ('1328879813864308737', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-11-18 09:56:51', 0);
INSERT INTO `operation_record_log`
VALUES ('1328880081968414721', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@396ddee8,org.apache.shiro.web.servlet.ShiroHttpServletRequest@1a9a9505',
        '2020-11-18 09:57:55', 0);
INSERT INTO `operation_record_log`
VALUES ('1328880083423838209', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-11-18 09:57:55', 0);
INSERT INTO `operation_record_log`
VALUES ('1328880092106047490', 'Tohsaka Rin', 9999, '短信验证码发送', 'com.gousade.redis.RedisSmsCodeUtil.sendSmsCode',
        '151********', '2020-11-18 09:57:58', 0);
INSERT INTO `operation_record_log`
VALUES ('1329315095596089345', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@41656040,org.apache.shiro.web.servlet.ShiroHttpServletRequest@3261a18d',
        '2020-11-19 14:46:30', 0);
INSERT INTO `operation_record_log`
VALUES ('1331152533301870593', 'Tohsaka Rin', 2, '获取头像', 'com.gousade.controller.UserController.getUserAvatar',
        'com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@7c215b23,org.apache.shiro.web.servlet.ShiroHttpServletRequest@611ee34a',
        '2020-11-24 16:27:50', 0);
INSERT INTO `operation_record_log`
VALUES ('1331152567804215297', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@635295d3', '2020-11-24 16:27:58', 0);
INSERT INTO `operation_record_log`
VALUES ('1331152652558516226', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@2b4e9eac', '2020-11-24 16:28:18', 0);
INSERT INTO `operation_record_log`
VALUES ('1332551867689967618', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@23c78354', '2020-11-28 13:08:17', 0);
INSERT INTO `operation_record_log`
VALUES ('1348210622641250306', 'Tohsaka Rin', 0, '查询角色列表', 'com.gousade.controller.RoleController.selectRoleList',
        '1,10,org.apache.shiro.web.servlet.ShiroHttpServletRequest@5b587d2', '2021-01-10 18:10:35', 0);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`
(
    `id` double(255, 0
) NULL DEFAULT NULL,
  `amount` decimal(65, 0) NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_date` date NULL DEFAULT NULL,
  `create_datetime` datetime NULL DEFAULT NULL,
  `time` time NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product`
VALUES (1, 100, '2020-10-13 11:38:08', '2020-10-13', '2020-10-13 11:38:05', '11:37:57');
INSERT INTO `product`
VALUES (2, 200, NULL, NULL, NULL, NULL);
INSERT INTO `product`
VALUES (3, 300, NULL, NULL, NULL, NULL);
INSERT INTO `product`
VALUES (4, 400, NULL, NULL, NULL, NULL);
INSERT INTO `product`
VALUES (1, 200, NULL, NULL, NULL, NULL);
INSERT INTO `product`
VALUES (10, 1000, NULL, NULL, NULL, NULL);
INSERT INTO `product`
VALUES (99, 9990, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for product_details
-- ----------------------------
DROP TABLE IF EXISTS `product_details`;
CREATE TABLE `product_details`
(
    `id`     int(255) NULL DEFAULT NULL,
    `weight` int(255) NULL DEFAULT NULL,
    `exist`  int(255) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of product_details
-- ----------------------------
INSERT INTO `product_details`
VALUES (2, 200, 0);
INSERT INTO `product_details`
VALUES (3, 400, 1);
INSERT INTO `product_details`
VALUES (4, 55, 0);
INSERT INTO `product_details`
VALUES (5, 66, 1);
INSERT INTO `product_details`
VALUES (1, 33, 0);
INSERT INTO `product_details`
VALUES (7, 77, 777);
INSERT INTO `product_details`
VALUES (6, 66, 666);

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource`
(
    `id`            varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `pid`           varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `name`          varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `url`           varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `icon`          varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源图标',
    `seq`           int(32) NULL DEFAULT NULL COMMENT '资源排序序号(用于同级资源展示时先后顺序控制)',
    `remarks`       varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `status`        int(1) NULL DEFAULT NULL COMMENT '打开状态',
    `resource_type` int(5) NULL DEFAULT NULL COMMENT '菜单类型 1:目录-非外链 2:目录-外链 3:目录-外链-登录(加密) 4:目录-外链-登录(明码) 5:目录-外链-嵌入 6:目录-外链-嵌入-登录 7:按钮或权限',
    `tip`           int(5) NULL DEFAULT NULL,
    `create_time`   datetime NULL DEFAULT NULL,
    `update_time`   datetime NULL DEFAULT NULL,
    `delflag`       int(1) NULL DEFAULT 0,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资源表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource`
VALUES ('1521d63c0d4e421fb20fd593d2827351', '1521d63c0d4e421fb20fd593d2827351', '品牌链接', '', '', 3, '', 0, 1, 0, NULL,
        '2020-08-04 09:08:30', 0);
INSERT INTO `resource`
VALUES ('1780f2abcd3241eabe62677d8d7c90d4', 'cb63cdbde80e48b085a03d4fdd226301', '资源管理',
        '/admin/resource/resourceManage', NULL, 2, '资源的列表', 0, 1, 0, '2019-12-15 17:09:39', '2020-03-16 11:05:17', 0);
INSERT INTO `resource`
VALUES ('230888defa67469cabf6d160b4099567', '', 'echarts', '', '', 2, 'e', 0, 1, NULL, NULL, NULL, 0);
INSERT INTO `resource`
VALUES ('26adeafc02f54b5fa1766784bc501033', 'cb63cdbde80e48b085a03d4fdd226301', 'tab', '/admin/user/tabs', '', 5, '1',
        0, 1, 0, '2020-08-18 09:37:03', NULL, 0);
INSERT INTO `resource`
VALUES ('42b9aa7bd4df417989dcb148a6402a62', '230888defa67469cabf6d160b4099567', 'echarts-gl',
        '/admin/echarts/echarts-gl', 'fa-globe', 0, 'echarts3D', 0, 1, 0, '2020-08-11 20:32:03', NULL, 0);
INSERT INTO `resource`
VALUES ('622039c6820a49fd98db50c050c7413b', '9c81edaf15b749a0bf8f61b4926daa0f', 'adminlte.io-2.4.18',
        'https://adminlte.io/themes/AdminLTE/index.html', '', 1, '', 0, 2, 0, '2020-08-11 20:56:25', NULL, 0);
INSERT INTO `resource`
VALUES ('6af9c09c689a41cb9cddc17e2c2644d0', '1521d63c0d4e421fb20fd593d2827351', 'github/gousade',
        'https://github.com/woxigousade/gousade', 'fa-github', 1, '', 0, 2, 0, '2020-09-08 15:26:06', NULL, 0);
INSERT INTO `resource`
VALUES ('7b5e58dc822e4262a6a919c316c9f023', 'cb63cdbde80e48b085a03d4fdd226301', '用户管理', '/admin/user/userManage', NULL,
        0, '用户管理', 0, 1, 9, NULL, '2020-03-16 11:05:17', 0);
INSERT INTO `resource`
VALUES ('8b54d67ed1b745b7a89c3cb7d662f9b0', 'bc97fbda9e644b3aa3efe55bb8c56d7c', 'player01', '/admin/aliyun/player01',
        '', 0, '', 0, 1, 0, '2020-10-20 14:55:50', NULL, 0);
INSERT INTO `resource`
VALUES ('91a17ec6bc2b4f7d9dfdb05f8e7bbda4', '9c81edaf15b749a0bf8f61b4926daa0f', 'adminlte.io-docs3.0',
        'https://adminlte.io/docs/3.0/', '', 0, '', 0, 2, 0, '2020-08-11 20:55:49', NULL, 0);
INSERT INTO `resource`
VALUES ('94fdfa071b1d4025b31a843fc432d0aa', NULL, '后台管理', '', '', -1, NULL, 0, 1, 0, '2019-12-12 16:36:26',
        '2019-12-13 20:14:55', 0);
INSERT INTO `resource`
VALUES ('9c81edaf15b749a0bf8f61b4926daa0f', '', 'adminlte.io 3.0', '', 'fa-link', 1, '', 0, 1, 0, '2020-08-11 20:52:09',
        NULL, 0);
INSERT INTO `resource`
VALUES ('aa8bbc7e05594e9ab4714df3ecc0714c', 'bc97fbda9e644b3aa3efe55bb8c56d7c', 'player02', '/admin/aliyun/player02',
        '', 1, '', 0, 1, 0, '2020-10-20 15:22:43', NULL, 0);
INSERT INTO `resource`
VALUES ('ba1d900f91dd46b990c04dd51a7d65a7', '1521d63c0d4e421fb20fd593d2827351', 'pilipili', 'https://www.bilibili.com',
        'fa-behance', -1, 'bilibili', 0, 2, 0, '2020-09-24 10:13:28', NULL, 0);
INSERT INTO `resource`
VALUES ('bc97fbda9e644b3aa3efe55bb8c56d7c', '', 'aliyunPlayer', '', '', 4, '', 0, 1, 0, '2020-10-20 14:55:01', NULL, 0);
INSERT INTO `resource`
VALUES ('c7d66d969354494da013de70a6765bb3', '1521d63c0d4e421fb20fd593d2827351', '百度', 'https://www.baidu.com', '', 0,
        '', 0, 5, 0, '2020-09-03 16:06:53', NULL, 0);
INSERT INTO `resource`
VALUES ('ca171f466a62472996a2591d0a3d4966', 'cb63cdbde80e48b085a03d4fdd226301', '角色管理', '/admin/role/roleManage', NULL,
        1, '角色的列表', 0, 1, 0, '2019-12-12 16:38:19', '2020-03-16 11:05:17', 0);
INSERT INTO `resource`
VALUES ('cb63cdbde80e48b085a03d4fdd226301', '94fdfa071b1d4025b31a843fc432d0aa', '权限管理', NULL, NULL, 0, NULL, 0, 1, 0,
        NULL, '2020-03-16 11:05:17', 0);
INSERT INTO `resource`
VALUES ('dce73d7e99a14d09a8f410532a689da9', '', '测试', '1', '', NULL, '', 0, 1, 0, NULL, '2020-08-11 21:29:38', 1);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
    `id`          varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `name`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `remarks`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `seq`         int(32) NULL DEFAULT NULL COMMENT '排序序号',
    `create_time` datetime NULL DEFAULT NULL,
    `update_time` datetime NULL DEFAULT NULL,
    `delflag`     tinyint(1) NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role`
VALUES ('0e073250b876489a8eefd6b801374b60', '超级管理员', '拥有本系统最高权限', 0, '2018-12-25 17:16:45', '2019-12-15 17:16:52', 0);
INSERT INTO `role`
VALUES ('19b9914b6c9a342f92377783422d8430', '测试mybatisplus角色3', NULL, 10, '2020-08-29 12:09:01', '2020-08-29 12:09:01',
        1);
INSERT INTO `role`
VALUES ('27f83817878649cb941b2c4fe71de12e', '测试setz', '', 99, '2020-03-19 09:25:36', '2020-11-20 14:40:46', 0);
INSERT INTO `role`
VALUES ('2821427e1b9743c1afc57f0ba476e3f4', '综合功能', '综合功能维护', 2, '2019-12-15 17:18:27', '2020-03-18 14:32:08', 0);
INSERT INTO `role`
VALUES ('28438d95153646b1ae8a7ee1338dce22', '测试角色', '4', 4, '2020-03-18 14:22:25', '2020-08-31 20:15:13', 1);
INSERT INTO `role`
VALUES ('30f11a4ac30023ee5119fae02005adcc', '测试mybatisplus角色3', NULL, 10, '2020-08-31 17:06:06', '2020-08-31 17:06:06',
        0);
INSERT INTO `role`
VALUES ('30f52a03f28399a3147a0dfa5103a750', 'jasypt', '拥有jasypt权限', 11, '2020-09-07 15:54:36', '2020-09-07 15:54:36',
        0);
INSERT INTO `role`
VALUES ('40089b6738770c9b21dbae278351ed4d', '测试mybatisplus角色3', NULL, 10, '2020-08-31 19:51:56', '2020-08-31 19:51:56',
        0);
INSERT INTO `role`
VALUES ('5a7608c676f762d9198fbe5076f3c7ee', '测试mybatisplus角色3', NULL, 10, '2020-08-29 12:07:51', '2020-08-29 12:07:51',
        0);
INSERT INTO `role`
VALUES ('7314257f98de48588012b2f30ede585a', '测试资源2', '2', 2, '2020-03-18 14:18:10', '2020-03-18 14:55:11', 0);
INSERT INTO `role`
VALUES ('7c1d5e63821b131998acbe0125da7917', '测试mybatisplus角色3', NULL, 10, '2020-09-01 14:50:57', '2020-09-01 14:50:57',
        0);
INSERT INTO `role`
VALUES ('82773b3e46924b049b0269334cc4ea0e', '后台管理员', '维护系统后台数据', 1, '2019-12-15 17:18:04', '2020-03-18 14:32:03', 0);
INSERT INTO `role`
VALUES ('8a024a8d53934950ad56c98846ca864b', 'echarts测试角色', '', 0, '2020-08-11 20:14:24', NULL, 0);
INSERT INTO `role`
VALUES ('9cb8a0e38a4b4c46bad26010c8486386', '831测试', '啊', 1, '2020-08-31 09:46:44', '2020-08-31 09:46:44', 0);
INSERT INTO `role`
VALUES ('a542106af9074d8589fc646ce827be73', '资源橘色的', '1', 3, '2020-04-30 10:57:11', '2020-04-30 10:57:20', 1);
INSERT INTO `role`
VALUES ('c1d4fbfcd68580147437291a4896e11d', '测试mybatisplus角色3', NULL, 10, '2020-08-28 22:59:46', '2020-08-28 22:59:46',
        0);
INSERT INTO `role`
VALUES ('c32460983cc870145f4a8da17d2e63ec', '测试mybatisplus角色3', NULL, 10, '2020-09-01 14:28:21', '2020-09-01 14:28:21',
        0);
INSERT INTO `role`
VALUES ('c628cfe4030f355dedf1d11977d6e145', '测试mybatisplus角色3', NULL, 10, '2020-08-26 11:08:16', '2020-08-26 11:08:16',
        0);
INSERT INTO `role`
VALUES ('f2ad2fc3c7384bf687c2fba8278fe42f', '测试角色2', '2', 2, '2020-03-18 14:23:01', '2020-03-18 14:57:54', 0);
INSERT INTO `role`
VALUES ('ff383e5d436b08017ba97342d9cdafda', '测试mybatisplus角色3', NULL, 10, '2020-08-31 21:44:39', '2020-08-31 21:44:39',
        0);

-- ----------------------------
-- Table structure for role_resource
-- ----------------------------
DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE `role_resource`
(
    `id`          varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `roleid`      varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `resourceid`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `create_time` datetime NULL DEFAULT NULL,
    `update_time` datetime NULL DEFAULT NULL,
    `delflag`     varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色资源关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role_resource
-- ----------------------------
INSERT INTO `role_resource`
VALUES ('063c1a2df2dc45cc9024718a4d6b36a0', '0e073250b876489a8eefd6b801374b60', '7b5e58dc822e4262a6a919c316c9f023',
        '2020-08-11 20:56:54', NULL, '0');
INSERT INTO `role_resource`
VALUES ('079398f025b24fc3a26d76b7edca16fc', '82773b3e46924b049b0269334cc4ea0e', '7b5e58dc822e4262a6a919c316c9f023',
        '2020-09-22 15:40:46', NULL, '0');
INSERT INTO `role_resource`
VALUES ('1bc087ffc14e47b580a21fcff4c51f5c', '0e073250b876489a8eefd6b801374b60', '42b9aa7bd4df417989dcb148a6402a62',
        '2020-08-11 20:56:54', NULL, '0');
INSERT INTO `role_resource`
VALUES ('1ea5a756ed964b6b888462a56c5ba99e', '0e073250b876489a8eefd6b801374b60', '1521d63c0d4e421fb20fd593d2827351',
        '2020-08-11 20:56:54', NULL, '0');
INSERT INTO `role_resource`
VALUES ('2025a3d3076b48f79c71129b93519b18', '0e073250b876489a8eefd6b801374b60', 'dce73d7e99a14d09a8f410532a689da9',
        '2020-08-11 20:56:54', NULL, '0');
INSERT INTO `role_resource`
VALUES ('2400ef83166f475a960d78b0a37f813f', '0e073250b876489a8eefd6b801374b60', '26adeafc02f54b5fa1766784bc501033',
        '2020-08-18 09:37:03', NULL, '0');
INSERT INTO `role_resource`
VALUES ('3396b17ef3614409bedba357d87e0a2f', '0e073250b876489a8eefd6b801374b60', 'aa8bbc7e05594e9ab4714df3ecc0714c',
        '2020-10-20 15:22:44', NULL, '0');
INSERT INTO `role_resource`
VALUES ('3a563b1995554ac3a6f3fc1aaef7acb5', '82773b3e46924b049b0269334cc4ea0e', '94fdfa071b1d4025b31a843fc432d0aa',
        '2020-09-22 15:40:46', NULL, '0');
INSERT INTO `role_resource`
VALUES ('3d312a87727341a99e478d51518eef26', '27f83817878649cb941b2c4fe71de12e', '1521d63c0d4e421fb20fd593d2827351',
        '2020-07-02 15:29:11', NULL, '0');
INSERT INTO `role_resource`
VALUES ('3d44165a016b407c86fcd5ae87410c36', '82773b3e46924b049b0269334cc4ea0e', '94fdfa071b1d4025b31a843fc432d0aa',
        '2020-08-05 17:16:08', '2020-09-22 15:40:46', '1');
INSERT INTO `role_resource`
VALUES ('424f1184760d4c4f80fbe5a883e4c66c', '27f83817878649cb941b2c4fe71de12e', '94fdfa071b1d4025b31a843fc432d0aa',
        '2020-07-02 15:29:11', NULL, '0');
INSERT INTO `role_resource`
VALUES ('4437689fa2a44d8da052009b695a6eb5', '0e073250b876489a8eefd6b801374b60', '6af9c09c689a41cb9cddc17e2c2644d0',
        '2020-09-08 15:26:06', NULL, '0');
INSERT INTO `role_resource`
VALUES ('444065a32155438bac4ca0c5da66c467', '0e073250b876489a8eefd6b801374b60', '1780f2abcd3241eabe62677d8d7c90d4',
        '2020-08-11 20:56:54', NULL, '0');
INSERT INTO `role_resource`
VALUES ('458a6eb59654407fbadb01009e909cda', '0e073250b876489a8eefd6b801374b60', 'ba1d900f91dd46b990c04dd51a7d65a7',
        '2020-09-24 10:13:28', NULL, '0');
INSERT INTO `role_resource`
VALUES ('5dcdebd7493f4ddba7d56ee695dc3c13', '27f83817878649cb941b2c4fe71de12e', 'cde08aca539349259c9fc4c024ecade5',
        '2020-07-02 15:29:11', NULL, '0');
INSERT INTO `role_resource`
VALUES ('5f057e8f2f6940008d84fe6f42e2aeba', '27f83817878649cb941b2c4fe71de12e', '1cc6969b704b4695a9d55bde5986ca0f',
        '2020-07-02 15:29:11', NULL, '0');
INSERT INTO `role_resource`
VALUES ('5f111b95910e458b9346a968dc44b134', '27f83817878649cb941b2c4fe71de12e', 'e33cba1cb6f748baa07f14ed859d6996',
        '2020-07-02 15:29:11', NULL, '0');
INSERT INTO `role_resource`
VALUES ('5fb65862c63747108afa6a4266565b7a', '82773b3e46924b049b0269334cc4ea0e', '1521d63c0d4e421fb20fd593d2827351',
        '2020-09-22 15:40:46', NULL, '0');
INSERT INTO `role_resource`
VALUES ('62abe39e2c6740469ac259a10b8c1d1d', '0e073250b876489a8eefd6b801374b60', '066a7a097a00456f84b1f99fd64f8865',
        '2020-08-11 20:56:54', NULL, '0');
INSERT INTO `role_resource`
VALUES ('6f1c3be4d5cd4cc7833212b678d2f030', '0e073250b876489a8eefd6b801374b60', '94fdfa071b1d4025b31a843fc432d0aa',
        '2020-08-11 20:56:54', NULL, '0');
INSERT INTO `role_resource`
VALUES ('6fd8d7fb85bd4619a8b503be6dc6d972', '27f83817878649cb941b2c4fe71de12e', '14cba37c49dd4797884675e8f2ef287e',
        '2020-07-02 15:29:11', NULL, '0');
INSERT INTO `role_resource`
VALUES ('71dfe9fbeca343b7b5c799a6d84f84d7', '0e073250b876489a8eefd6b801374b60', 'cb63cdbde80e48b085a03d4fdd226301',
        '2020-08-11 20:56:54', NULL, '0');
INSERT INTO `role_resource`
VALUES ('891a70c877504850b5c766fbe2cc367e', '0e073250b876489a8eefd6b801374b60', '622039c6820a49fd98db50c050c7413b',
        '2020-08-11 20:56:54', NULL, '0');
INSERT INTO `role_resource`
VALUES ('8fc37fc6494244e69fd644ab0f70296e', '0e073250b876489a8eefd6b801374b60', '230888defa67469cabf6d160b4099567',
        '2020-08-11 20:56:54', NULL, '0');
INSERT INTO `role_resource`
VALUES ('a2e4182ba16a48b3935af65f9fac6073', '7314257f98de48588012b2f30ede585a', 'cb63cdbde80e48b085a03d4fdd226301',
        '2020-11-24 16:28:18', NULL, '0');
INSERT INTO `role_resource`
VALUES ('b0ea89727c474b09bc2a8a902e4e4950', '27f83817878649cb941b2c4fe71de12e', '9dae1d494f414264a4ffa69fb995c3da',
        '2020-07-02 15:29:11', NULL, '0');
INSERT INTO `role_resource`
VALUES ('b5378d782033499aa1ac7386c55e0d49', '8a024a8d53934950ad56c98846ca864b', '230888defa67469cabf6d160b4099567',
        '2020-08-11 20:14:38', NULL, '0');
INSERT INTO `role_resource`
VALUES ('bcc7f3e57ad84a7db4fa57e100952b92', '7314257f98de48588012b2f30ede585a', '1780f2abcd3241eabe62677d8d7c90d4',
        '2020-11-24 16:28:18', NULL, '0');
INSERT INTO `role_resource`
VALUES ('bf03128ec9544cb2bcc82f5017370030', '0e073250b876489a8eefd6b801374b60', '8b54d67ed1b745b7a89c3cb7d662f9b0',
        '2020-10-20 14:55:50', NULL, '0');
INSERT INTO `role_resource`
VALUES ('c427d0c1f0284ef4a97c01cc936edc6b', '0e073250b876489a8eefd6b801374b60', 'ca171f466a62472996a2591d0a3d4966',
        '2020-08-11 20:56:54', NULL, '0');
INSERT INTO `role_resource`
VALUES ('c4f4ee7a72e949839733248982dda420', '0e073250b876489a8eefd6b801374b60', '9c81edaf15b749a0bf8f61b4926daa0f',
        '2020-08-11 20:56:54', NULL, '0');
INSERT INTO `role_resource`
VALUES ('d6adb78d980e4aaba1ed9ef1b1e632ee', '82773b3e46924b049b0269334cc4ea0e', 'ca171f466a62472996a2591d0a3d4966',
        '2020-09-22 15:40:46', NULL, '0');
INSERT INTO `role_resource`
VALUES ('d99c73c46b4c43baa12f0d06414429d7', '0e073250b876489a8eefd6b801374b60', 'c7d66d969354494da013de70a6765bb3',
        '2020-09-03 16:06:53', NULL, '0');
INSERT INTO `role_resource`
VALUES ('da33af9d089848238d4550b6c4ce3414', '0e073250b876489a8eefd6b801374b60', 'bc97fbda9e644b3aa3efe55bb8c56d7c',
        '2020-10-20 14:55:01', NULL, '0');
INSERT INTO `role_resource`
VALUES ('e58f07893b4d4e2c9d1e42b55f3a60e4', '0e073250b876489a8eefd6b801374b60', '91a17ec6bc2b4f7d9dfdb05f8e7bbda4',
        '2020-08-11 20:56:54', NULL, '0');
INSERT INTO `role_resource`
VALUES ('ebfe03fdadc144f0b7b4a18d3500d578', '27f83817878649cb941b2c4fe71de12e', 'cb63cdbde80e48b085a03d4fdd226301',
        '2020-07-02 15:29:11', NULL, '0');
INSERT INTO `role_resource`
VALUES ('f021634f29da4120a0d864906947f245', '7314257f98de48588012b2f30ede585a', '94fdfa071b1d4025b31a843fc432d0aa',
        '2020-11-24 16:28:18', NULL, '0');

-- ----------------------------
-- Table structure for secret_jasypt
-- ----------------------------
DROP TABLE IF EXISTS `secret_jasypt`;
CREATE TABLE `secret_jasypt`
(
    `id`          varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
    `jasypt`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '混淆盐值',
    `vm_options`  varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '启动参数',
    `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `delflag`     tinyint(1) NULL DEFAULT NULL COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of secret_jasypt
-- ----------------------------
INSERT INTO `secret_jasypt`
VALUES ('52ad73833a754219b9770e712f17e639', '52ad73833a754219b9770e712f17e639',
        '-Djasypt.encryptor.password=52ad73833a754219b9770e712f17e639 -Dspring.profiles.active=dev',
        '2020-08-31 16:31:33', 0);
INSERT INTO `secret_jasypt`
VALUES ('eclipse', 'eclipse中运行所需配置',
        '默认直接以dev模式运行。在maven build和run configuration的Profile中都需要填入prod(主要原因是pom的<resource>标签的写法会过滤配置文件，只有build包含了prod配置文件才能运行，在run configuration中有两处可以设置prod，springboot页设置Porfile栏为prod，以及arguments页设置Program arguments栏为--spring.profiles.active=prod)，执行build后再run。(还需要在vm参数中填入-Djasypt.encryptor.password=52ad73833a754219b9770e712f17e639)',
        NULL, NULL);
INSERT INTO `secret_jasypt`
VALUES ('idea.run', 'idea中运行所需配置',
        'edit configuration中填写环境变量(environment variables) jasypt.encryptor.password=52ad73833a754219b9770e712f17e639 或者在vm参数中填写-Djasypt.encryptor.password=52ad73833a754219b9770e712f17e639，再将右侧maven的Profiles勾选 dev test prod其中一个即可正常运行',
        '2020-09-13 13:37:29', 0);
INSERT INTO `secret_jasypt`
VALUES ('maven-D-P', '-D和-P的解释', 'http://mvnbook.com/maven-properties.html', NULL, NULL);
INSERT INTO `secret_jasypt`
VALUES ('maven.package', 'maven打包命令',
        'mvn clean package -Pdev 或 mvn clean package -Pprod 或  mvn clean package -Dprofiles.active=dev 其中-D之后的profiles.active为pom中<properties>包裹的自定义的名字。可以参考http://mvnbook.com/maven-properties.html 或者直接使用idea中的maven，Profiles选中一种环境，再点击clean和package即可。',
        '2020-09-13 13:31:14', 0);
INSERT INTO `secret_jasypt`
VALUES ('spring.datasource.url', 'spring.datasource.url',
        'spring.datasource.url=jdbc:mysql://101.132.118.130:9733/gousade?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&allowMultiQueries=true&serverTimezone=GMT%2B8&useSSL=false',
        NULL, NULL);
INSERT INTO `secret_jasypt`
VALUES ('spring.profiles.active', '运行时环境变量', 'spring.profiles.active=dev 或 spring.profiles.active=prod',
        '2020-09-13 13:24:26', 0);
INSERT INTO `secret_jasypt`
VALUES ('war.run', '打包后运行命令',
        'java -Djasypt.encryptor.password=52ad73833a754219b9770e712f17e639 -jar gousade.war 不需要指定dev和prod 因为打包时候已经将配置填充到文件中，当然也可以写全：java -Djasypt.encryptor.password=52ad73833a754219b9770e712f17e639 -Dspring.profiles.active=dev -jar gousade.war',
        '2020-09-13 13:32:14', 0);

-- ----------------------------
-- Table structure for sms_response_log
-- ----------------------------
DROP TABLE IF EXISTS `sms_response_log`;
CREATE TABLE `sms_response_log`
(
    `id`          varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `response`    varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `create_time` datetime NULL DEFAULT NULL,
    `delflag`     tinyint(1) NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sms_response_log
-- ----------------------------
INSERT INTO `sms_response_log`
VALUES ('1300243375404560385',
        '{\"Message\":\"模板不合法(不存在或被拉黑)\",\"RequestId\":\"E26D2A98-3AA9-44AF-A8C3-F0390840AD98\",\"Code\":\"isv.SMS_TEMPLATE_ILLEGAL\"}',
        '2020-08-31 09:25:52', 0);
INSERT INTO `sms_response_log`
VALUES ('1300243769602027522',
        '{\"Message\":\"模板不合法(不存在或被拉黑)\",\"RequestId\":\"54A06B8A-BA8F-4853-A347-5615B559D023\",\"Code\":\"isv.SMS_TEMPLATE_ILLEGAL\"}',
        '2020-08-31 09:27:26', 0);
INSERT INTO `sms_response_log`
VALUES ('1300244037295091714',
        '{\"Message\":\"模板不合法(不存在或被拉黑)\",\"RequestId\":\"5BEE04A8-2BA8-45CF-B60B-96ADC10CDB5A\",\"Code\":\"isv.SMS_TEMPLATE_ILLEGAL\"}',
        '2020-08-31 09:28:30', 0);
INSERT INTO `sms_response_log`
VALUES ('1300245539661152257',
        '{\"Message\":\"模板不合法(不存在或被拉黑)\",\"RequestId\":\"10CBA6CB-7BC3-4884-B936-9652F907BC7C\",\"Code\":\"isv.SMS_TEMPLATE_ILLEGAL\"}',
        '2020-08-31 09:34:28', 0);
INSERT INTO `sms_response_log`
VALUES ('1300325404116758530',
        '{\"Message\":\"模板不合法(不存在或被拉黑)\",\"RequestId\":\"8CB2C3A3-50A8-4022-B98A-996B1160C7DC\",\"Code\":\"isv.SMS_TEMPLATE_ILLEGAL\"}',
        '2020-08-31 14:51:49', 0);
INSERT INTO `sms_response_log`
VALUES ('1300325793453027330',
        '{\"Message\":\"模板不合法(不存在或被拉黑)\",\"RequestId\":\"B3F47F6F-CA83-482D-B58E-1F10F0536E17\",\"Code\":\"isv.SMS_TEMPLATE_ILLEGAL\"}',
        '2020-08-31 14:53:22', 0);
INSERT INTO `sms_response_log`
VALUES ('1300395391127228417',
        '{\"Message\":\"模板不合法(不存在或被拉黑)\",\"RequestId\":\"D4A29375-FB0C-46B7-9DC2-EE20D173D573\",\"Code\":\"isv.SMS_TEMPLATE_ILLEGAL\"}',
        '2020-08-31 19:29:56', 0);
INSERT INTO `sms_response_log`
VALUES ('1300780435444334594',
        '{\"Message\":\"模板不合法(不存在或被拉黑)\",\"RequestId\":\"09ACA75B-1255-4DDD-96A5-BEE0B5C09321\",\"Code\":\"isv.SMS_TEMPLATE_ILLEGAL\"}',
        '2020-09-01 20:59:57', 0);
INSERT INTO `sms_response_log`
VALUES ('1300783760533356546',
        '{\"Message\":\"模板不合法(不存在或被拉黑)\",\"RequestId\":\"7CE7877C-D47A-4694-9AF4-1CFB8B1DE3B2\",\"Code\":\"isv.SMS_TEMPLATE_ILLEGAL\"}',
        '2020-09-01 21:13:10', 0);
INSERT INTO `sms_response_log`
VALUES ('1301048373222825986',
        '{\"Message\":\"模板不合法(不存在或被拉黑)\",\"RequestId\":\"B7ABB0A3-1B32-4128-9E5B-58BD4152541D\",\"Code\":\"isv.SMS_TEMPLATE_ILLEGAL\"}',
        '2020-09-02 14:44:39', 0);
INSERT INTO `sms_response_log`
VALUES ('1301056127572369410',
        '{\"Message\":\"模板不合法(不存在或被拉黑)\",\"RequestId\":\"FD252775-DFC6-4F30-8394-27B0DDF29C4E\",\"Code\":\"isv.SMS_TEMPLATE_ILLEGAL\"}',
        '2020-09-02 15:15:27', 0);
INSERT INTO `sms_response_log`
VALUES ('1301057008120356865',
        '{\"Message\":\"模板不合法(不存在或被拉黑)\",\"RequestId\":\"BAF2C375-FC6B-4647-86CA-B05087B4A797\",\"Code\":\"isv.SMS_TEMPLATE_ILLEGAL\"}',
        '2020-09-02 15:18:57', 0);
INSERT INTO `sms_response_log`
VALUES ('1301060590886445057',
        '{\"Message\":\"模板不合法(不存在或被拉黑)\",\"RequestId\":\"99DC57C6-21F1-41D4-8C9A-72EDDA02C57D\",\"Code\":\"isv.SMS_TEMPLATE_ILLEGAL\"}',
        '2020-09-02 15:33:12', 0);
INSERT INTO `sms_response_log`
VALUES ('1301062256088047617',
        '{\"Message\":\"模板不合法(不存在或被拉黑)\",\"RequestId\":\"CFFEFE45-80E8-4E12-88DD-B40E5422F377\",\"Code\":\"isv.SMS_TEMPLATE_ILLEGAL\"}',
        '2020-09-02 15:39:49', 0);
INSERT INTO `sms_response_log`
VALUES ('1301064009240010754',
        '{\"Message\":\"模板不合法(不存在或被拉黑)\",\"RequestId\":\"8C4980B0-C5E9-4954-BA20-58DF7F59BF80\",\"Code\":\"isv.SMS_TEMPLATE_ILLEGAL\"}',
        '2020-09-02 15:46:47', 0);
INSERT INTO `sms_response_log`
VALUES ('1301064221857669121',
        '{\"Message\":\"模板不合法(不存在或被拉黑)\",\"RequestId\":\"ABA6562B-5D9E-4276-9D34-1B8B720949AB\",\"Code\":\"isv.SMS_TEMPLATE_ILLEGAL\"}',
        '2020-09-02 15:47:37', 0);
INSERT INTO `sms_response_log`
VALUES ('1303615487280349185',
        '{\"Message\":\"OK\",\"RequestId\":\"B48297FB-A999-4D77-A91A-5D09CECCBB9B\",\"BizId\":\"776723599641127392^0\",\"Code\":\"OK\"}',
        '2020-09-09 16:45:26', 0);
INSERT INTO `sms_response_log`
VALUES ('1305477287368458241',
        '{\"Message\":\"OK\",\"RequestId\":\"7FD0E82C-DF5C-442F-A50E-D31EBDCC85D1\",\"BizId\":\"618423000085013457^0\",\"Code\":\"OK\"}',
        '2020-09-14 20:03:34', 0);
INSERT INTO `sms_response_log`
VALUES ('1305478057073586178',
        '{\"Message\":\"OK\",\"RequestId\":\"3820604D-2367-4FDF-ABBF-0467CF04D6FA\",\"BizId\":\"467901800085197095^0\",\"Code\":\"OK\"}',
        '2020-09-14 20:06:38', 0);
INSERT INTO `sms_response_log`
VALUES ('1305478578664648706',
        '{\"Message\":\"OK\",\"RequestId\":\"1932ACFB-6775-45AF-9051-283F4A19BC64\",\"BizId\":\"596224600085321473^0\",\"Code\":\"OK\"}',
        '2020-09-14 20:08:42', 0);
INSERT INTO `sms_response_log`
VALUES ('1305478828485881858',
        '{\"Message\":\"OK\",\"RequestId\":\"522214A4-77B1-4BC2-B9A5-E54B662FD9E0\",\"BizId\":\"648921400085381013^0\",\"Code\":\"OK\"}',
        '2020-09-14 20:09:41', 0);
INSERT INTO `sms_response_log`
VALUES ('1305481975820201985',
        '{\"Message\":\"模板不合法(不存在或被拉黑)\",\"RequestId\":\"C0150C10-41B0-4322-A44A-F8F0668544DF\",\"Code\":\"isv.SMS_TEMPLATE_ILLEGAL\"}',
        '2020-09-14 20:22:12', 0);
INSERT INTO `sms_response_log`
VALUES ('1305482307157635073',
        '{\"Message\":\"OK\",\"RequestId\":\"1EEC5A1D-1F2F-4CBE-93F8-664F04570F07\",\"BizId\":\"184407500086210407^0\",\"Code\":\"OK\"}',
        '2020-09-14 20:23:31', 0);
INSERT INTO `sms_response_log`
VALUES ('1308321016699392002',
        '{\"Message\":\"OK\",\"RequestId\":\"1B83F553-AE61-40D8-BDBE-08E6CF0809DB\",\"BizId\":\"864701800763013276^0\",\"Code\":\"OK\"}',
        '2020-09-22 16:23:32', 0);
INSERT INTO `sms_response_log`
VALUES ('1308576186049978370',
        '{\"Message\":\"模板不合法(不存在或被拉黑)\",\"RequestId\":\"7A1F4169-1C0C-4EFD-9637-D5A6DD16A024\",\"Code\":\"isv.SMS_TEMPLATE_ILLEGAL\"}',
        '2020-09-23 09:17:29', 0);
INSERT INTO `sms_response_log`
VALUES ('1308578735087910913',
        '{\"Message\":\"模板不合法(不存在或被拉黑)\",\"RequestId\":\"2406F9EA-EF3D-4C47-802C-4FBA97BD5299\",\"Code\":\"isv.SMS_TEMPLATE_ILLEGAL\"}',
        '2020-09-23 09:27:37', 0);
INSERT INTO `sms_response_log`
VALUES ('1308582891810934785',
        '{\"Message\":\"模板不合法(不存在或被拉黑)\",\"RequestId\":\"8D4C0B3E-DA5A-45D1-B085-350F1EA45FCC\",\"Code\":\"isv.SMS_TEMPLATE_ILLEGAL\"}',
        '2020-09-23 09:44:08', 0);
INSERT INTO `sms_response_log`
VALUES ('1308583099072466946',
        '{\"Message\":\"模板不合法(不存在或被拉黑)\",\"RequestId\":\"06FA9461-0003-4CF3-8847-1B80A63CC0A0\",\"Code\":\"isv.SMS_TEMPLATE_ILLEGAL\"}',
        '2020-09-23 09:44:57', 0);
INSERT INTO `sms_response_log`
VALUES ('1308585387052699649',
        '{\"Message\":\"模板不合法(不存在或被拉黑)\",\"RequestId\":\"EB079F53-2E30-4A26-A838-9CF87BCFCD3E\",\"Code\":\"isv.SMS_TEMPLATE_ILLEGAL\"}',
        '2020-09-23 09:54:03', 0);
INSERT INTO `sms_response_log`
VALUES ('1308586701866364929',
        '{\"Message\":\"模板不合法(不存在或被拉黑)\",\"RequestId\":\"87233C95-E30B-4980-968F-B12CF5C1E3D2\",\"Code\":\"isv.SMS_TEMPLATE_ILLEGAL\"}',
        '2020-09-23 09:59:16', 0);
INSERT INTO `sms_response_log`
VALUES ('1308589316473847809',
        '{\"Message\":\"模板不合法(不存在或被拉黑)\",\"RequestId\":\"45876F89-470C-4566-A7BB-6F0505D50F50\",\"Code\":\"isv.SMS_TEMPLATE_ILLEGAL\"}',
        '2020-09-23 10:09:40', 0);

-- ----------------------------
-- Table structure for ssr
-- ----------------------------
DROP TABLE IF EXISTS `ssr`;
CREATE TABLE `ssr`
(
    `id`   varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `link` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of ssr
-- ----------------------------
INSERT INTO `ssr`
VALUES ('3d217633e6c34e3bb278d1bdd93eefb4',
        'ssr://MjA3LjI0Ni44NC4yMTM6OTk5OTphdXRoX3NoYTFfdjQ6YWVzLTI1Ni1jZmI6cGxhaW46ZDNoc016WTJNakF4Lz9vYmZzcGFyYW09');

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`
(
    `id` double(5, 2
) NULL DEFAULT NULL,
  `amount` decimal(65, 0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test`
VALUES (1.13, 1);
INSERT INTO `test`
VALUES (1.23, 1);
INSERT INTO `test`
VALUES (123.12, 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`            varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `user_id`       varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `user_name`     varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `password`      varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `salt`          varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `create_time`   datetime NULL DEFAULT NULL,
    `update_time`   datetime NULL DEFAULT NULL,
    `remark`        varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `phone_number`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `lastlogintime` datetime NULL DEFAULT NULL,
    `delflag`       tinyint(1) NULL DEFAULT 0,
    `avatar_path`   varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像路径',
    `version`       int(11) NULL DEFAULT NULL COMMENT '版本号 乐观锁',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `gousade_user_index_id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user`
VALUES ('04ab11ce56c34a9297ab3f0cd4901a9d', 'z', NULL, NULL, NULL, '2020-09-22 18:36:04', '2020-09-22 16:44:27', NULL,
        NULL, NULL, 0, NULL, 2);
INSERT INTO `user`
VALUES ('0cc558e6d87c4379a414a2a482054cf7', 'ceshi1', 'ceshi1', '67bfbdf8871081969a37f64e371890c9',
        '29f47476a4c7475c8a5fc809a703a5f3', '2020-08-05 22:34:31', '2020-09-22 16:44:27', NULL, '1', NULL, 0, NULL, 2);
INSERT INTO `user`
VALUES ('1b0422c6f0644d84b947c6d893b11749', 'bb', NULL, NULL, NULL, NULL, '2020-09-22 16:44:27', NULL, NULL, NULL, 0,
        NULL, 2);
INSERT INTO `user`
VALUES ('2959c92012ba4db08714369f52eca424', 'b', NULL, NULL, NULL, NULL, '2020-09-22 16:46:35', NULL, NULL, NULL, 0,
        NULL, 4);
INSERT INTO `user`
VALUES ('2e63015ae9093b00ea7d6f54d1e9cf5d', '831测试1', '831测试1', '0fba3a2c7c1a816d383c6266492341aa',
        'fb8c35469f624997ad1e400d0f9b35ee', '2020-08-31 10:48:31', '2020-09-22 16:46:35', NULL, '1',
        '2020-08-31 10:49:32', 0, NULL, 4);
INSERT INTO `user`
VALUES ('326f44ea600f516708966fa65a6b91f0', '831测试2', '831测试1', 'aa', '23cf6d80de3d4ba8a0316466b50372fe',
        '2020-08-31 10:49:00', '2020-09-22 16:46:35', NULL, '12', NULL, 0, NULL, 4);
INSERT INTO `user`
VALUES ('70a6c4d7a0034123a506c1570fd85ec2', 's', NULL, NULL, NULL, NULL, '2020-09-22 16:46:35', NULL, NULL, NULL, 0,
        NULL, 4);
INSERT INTO `user`
VALUES ('728fdccf23dc48b8a53014cbc475042d', 'sss', NULL, NULL, NULL, NULL, '2020-09-22 16:46:35', NULL, NULL, NULL, 0,
        NULL, 4);
INSERT INTO `user`
VALUES ('78b66ae44ad34c23afd9afac02cd83e0', 'textadmin', '文本分析管理员', 'c08ce72bf25386cc3814f7c5131f97d9',
        'b222ee185b7c48c7bce686e715fe931f', '2019-02-28 11:05:52', '2020-09-22 16:46:35', '文本分析管理员', '151********',
        NULL, 0, NULL, 4);
INSERT INTO `user`
VALUES ('8252099ea409477489b90f4cf0f084a3', 'gousade', '狗萨德', 'c08ce72bf25386cc3814f7c5131f97d9',
        'b222ee185b7c48c7bce686e715fe931f', '2019-11-27 20:32:27', '2020-09-22 16:46:35', NULL, NULL, NULL, 0, NULL, 4);
INSERT INTO `user`
VALUES ('86a50c1474ef4a61b00fef8c8e5db8a1', 'ccc', NULL, NULL, NULL, NULL, '2020-09-22 16:46:35', NULL, NULL, NULL, 0,
        NULL, 4);
INSERT INTO `user`
VALUES ('8961e4f21fac43b796c1e0d0a422414', 'abbt', '023', NULL, NULL, NULL, '2020-09-22 16:46:35', NULL, '222', NULL, 0,
        NULL, 4);
INSERT INTO `user`
VALUES ('911a8e15eebf4d569f8a8299e5ad99d5', 'Tohsaka Rin', 'Tohsaka Rin', '9bb54586cc1d39ea40fd0efab1b845c5',
        '9117b66d4b3c40d5a53d7a199b0d437b', '2018-11-07 22:56:54', '2020-09-22 16:46:35', '超级管理员，拥有全部权限', '151********',
        '2021-01-10 18:10:01', 0,
        'https://gousade.oss-cn-beijing.aliyuncs.com/2020-09-24/19e1b6fd7fc74588b573864aec3ca838avarar3.jpg', 4);
INSERT INTO `user`
VALUES ('9f8a863225404d908e9a028cc8adb9fd', 'aaa', NULL, NULL, NULL, NULL, '2020-09-22 16:46:35', NULL, NULL, NULL, 0,
        NULL, 4);
INSERT INTO `user`
VALUES ('a34d6da6c33b49af9f532c3dd7cde20c', 'z新增', 'a', '74b91c865bdcb34751b88f5410b70573',
        '53b8ce1db4ed4b529a10ea219feb6a66', '2020-08-26 09:59:58', '2020-09-22 16:46:35', NULL, '1', NULL, 0, NULL, 4);
INSERT INTO `user`
VALUES ('a8a31f50f3634c7eb42f35d03e8afacf', 'zzz', NULL, NULL, NULL, NULL, '2020-09-22 16:46:35', NULL, NULL, NULL, 0,
        NULL, 4);
INSERT INTO `user`
VALUES ('aa6ec84407295d184d5464401b94d131', 'a1', 'a1', '7da78b5ee40c61ed751eead003d49690',
        '4561aefdaff64eb09baa609cf5325b1a', '2020-09-23 15:35:40', '2020-09-23 15:35:40', NULL, '151', NULL, 0, NULL,
        1);
INSERT INTO `user`
VALUES ('abbt', 'abbt', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, 1);
INSERT INTO `user`
VALUES ('b0121414558a4804a86eacff5ac33584', 'ss', NULL, NULL, NULL, NULL, '2020-09-22 16:46:35', NULL, NULL, NULL, 0,
        NULL, 4);
INSERT INTO `user`
VALUES ('b1a7e088446b46b89a3d994589c3d29b', 'aaaa', NULL, NULL, NULL, NULL, '2020-09-22 16:46:35', NULL, NULL, NULL, 0,
        NULL, 4);
INSERT INTO `user`
VALUES ('c0f597ae8bbd4550b1caa6ac130c02e6', '831测试', 'rin', 'cc7c12d1f150821ae99d18af18febef9',
        'b8fb13f2e57c4c60b305a2618bc82be2', '2020-08-31 09:51:16', '2020-09-22 16:46:35', NULL, '151********',
        '2020-09-22 16:29:56', 0, NULL, 4);
INSERT INTO `user`
VALUES ('c4d94480bf2e4a6a894c669897a6dc21', 'ztesttime', '1', 'ed0b2ece34f1f79b7d9ed4109d7f9e92',
        'e4c71e9343494b84b5655a53b907a363', '2020-08-24 15:28:21', '2020-09-22 16:46:35', NULL, '1', NULL, 0, NULL, 4);
INSERT INTO `user`
VALUES ('cc1fb84381be4f03aab1160cc990dc22', 'salt', '盐', '5b96272f7074510dcc9e3acafd1ad8a4',
        '8b035f42a17b4bfdbd4d41af71af1ee0', '2019-09-05 18:51:22', '2020-09-22 16:46:35', NULL, '13357145239',
        '2020-08-12 14:05:35', 0, NULL, 4);
INSERT INTO `user`
VALUES ('ccd25094437d6a67970890a6e5c51674', 'a3', 'a3', '70b7da115c37f78af8bfc828a1057f73',
        'e7201771355f401ea5f492fbb8b21a98', '2020-09-23 15:38:27', '2020-09-23 15:38:27', NULL, '3', NULL, 0, NULL, 1);
INSERT INTO `user`
VALUES ('d1411e5c58a70c44d72ca016d1acc183', '0901测试', '0901测试才啊', '414274c52def5d67d3e169cf7a3aebec',
        'eab63256145c414eae2a5c57fe763c06', '2020-09-01 11:03:59', '2020-09-01 15:17:54', '备注', '0901', NULL, 0, NULL,
        7);
INSERT INTO `user`
VALUES ('d426016a5b814aa4bd50276ede4fe6c3', 'aaaaaaa', NULL, NULL, NULL, NULL, '2020-09-22 16:46:35', NULL, NULL, NULL,
        0, NULL, 4);
INSERT INTO `user`
VALUES ('eac90d4d92fb45de91ad33df85ebc0ae', 'shiro002', '2', '796b0c410981e75ef5dcf1a34c81101c',
        '5c3e7ffe8155450e9e6deaab37431801', '2019-11-27 18:53:41', '2020-09-22 16:46:35', NULL, NULL, NULL, 0, NULL, 4);
INSERT INTO `user`
VALUES ('f9a35773d8ed4edfbb2e81d878f9dd59', 'c', NULL, NULL, NULL, NULL, '2020-09-22 16:46:35', NULL, NULL, NULL, 0,
        NULL, 4);
INSERT INTO `user`
VALUES ('fc6b0b6aa2dadb653f8de78266d60f88', 'a2', 'a2', '4b3f9223b88a8324380990c8bc0677f1',
        '8bfdc190cdbe4d7db98ee423775b1d6e', '2020-09-23 07:37:35', '2020-09-23 07:37:35', NULL, '11', NULL, 0, NULL, 1);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`
(
    `id`          varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `userid`      varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `roleid`      varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `create_time` datetime NULL DEFAULT NULL,
    `update_time` datetime NULL DEFAULT NULL,
    `delflag`     varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX         `FK_Reference_3`(`userid`) USING BTREE,
    INDEX         `FK_Reference_4`(`roleid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role`
VALUES ('06f2245fa6754f93a62015bb3a6c4489', 'fc354e3dd71711ea9fce00163e002b3a', '82773b3e46924b049b0269334cc4ea0e',
        '2020-08-11 21:09:56', '2020-08-11 21:14:59', '1');
INSERT INTO `user_role`
VALUES ('09e6a970cc6a43398d15ed5f149a7592', 'c0f597ae8bbd4550b1caa6ac130c02e6', '82773b3e46924b049b0269334cc4ea0e',
        '2020-09-22 16:21:02', '2020-09-22 16:24:21', '1');
INSERT INTO `user_role`
VALUES ('0bf66696412d4f828d2515e37a49cf5b', '911a8e15eebf4d569f8a8299e5ad99d5', '0e073250b876489a8eefd6b801374b60',
        '2020-09-07 15:57:36', '2020-09-07 15:57:36', '0');
INSERT INTO `user_role`
VALUES ('132bcff1ab1045918fcb65f30e45f3d6', '326f44ea600f516708966fa65a6b91f0', '82773b3e46924b049b0269334cc4ea0e',
        '2020-08-31 14:35:54', '2020-08-31 14:35:54', '0');
INSERT INTO `user_role`
VALUES ('13bb0c1de7d144e9b94daff2f822daba', '911a8e15eebf4d569f8a8299e5ad99d5', '0e073250b876489a8eefd6b801374b60',
        '2020-08-14 16:02:36', '2020-08-28 10:44:59', '1');
INSERT INTO `user_role`
VALUES ('1f76727aa008423ba9fb105124b0a0f8', 'd1411e5c58a70c44d72ca016d1acc183', '8a024a8d53934950ad56c98846ca864b',
        '2020-09-01 14:25:16', '2020-09-01 15:17:54', '1');
INSERT INTO `user_role`
VALUES ('1f7b749c218e4e128e9854405f8bcf5e', '911a8e15eebf4d569f8a8299e5ad99d5', '30f52a03f28399a3147a0dfa5103a750',
        '2020-09-07 15:57:36', '2020-09-07 15:57:36', '0');
INSERT INTO `user_role`
VALUES ('274ef0b7aca445d890117b35761134f8', '911a8e15eebf4d569f8a8299e5ad99d5', '0e073250b876489a8eefd6b801374b60',
        '2020-08-13 17:04:05', '2020-08-14 15:55:45', '1');
INSERT INTO `user_role`
VALUES ('3225cfdc08c3440d960386343a467daa', 'fc354e3dd71711ea9fce00163e002b3a', '82773b3e46924b049b0269334cc4ea0e',
        '2020-08-11 21:14:59', '2020-08-11 21:14:59', '0');
INSERT INTO `user_role`
VALUES ('344020c0d88f462da54f639e57f51fea', 'd1411e5c58a70c44d72ca016d1acc183', '8a024a8d53934950ad56c98846ca864b',
        '2020-09-01 15:17:54', '2020-09-01 15:18:39', '1');
INSERT INTO `user_role`
VALUES ('381fef22f7ff4f4195f22c71d7dfdcb5', '911a8e15eebf4d569f8a8299e5ad99d5', '0e073250b876489a8eefd6b801374b60',
        '2020-08-28 10:45:32', '2020-09-07 15:57:36', '1');
INSERT INTO `user_role`
VALUES ('39bba4a3191545939040bf71a4ce7607', 'fc354e3dd71711ea9fce00163e002b3a', '7314257f98de48588012b2f30ede585a',
        '2020-08-11 21:14:59', '2020-08-11 21:14:59', '0');
INSERT INTO `user_role`
VALUES ('431baa378bba485d81e267863d80e860', '8961e4f21fac43b796c1e0d0a422414', '8a024a8d53934950ad56c98846ca864b',
        '2020-08-31 21:03:15', '2020-08-31 21:04:47', '1');
INSERT INTO `user_role`
VALUES ('5a36c4a99fdf458eaaa42760a5966c34', 'fc354e3dd71711ea9fce00163e002b3a', '27f83817878649cb941b2c4fe71de12e',
        '2020-08-11 21:14:59', '2020-08-11 21:14:59', '0');
INSERT INTO `user_role`
VALUES ('62bcc727c9f547f4aadce8ae46d6f49b', 'fc354e3dd71711ea9fce00163e002b3a', '0e073250b876489a8eefd6b801374b60',
        '2020-08-11 21:09:56', '2020-08-11 21:14:59', '1');
INSERT INTO `user_role`
VALUES ('6ec3c562938341f2a5d505c09bfad9fd', 'c0f597ae8bbd4550b1caa6ac130c02e6', '82773b3e46924b049b0269334cc4ea0e',
        '2020-09-22 15:40:26', '2020-09-22 16:21:02', '1');
INSERT INTO `user_role`
VALUES ('79c67612ab234b348cbcd9b8821a5c9d', '911a8e15eebf4d569f8a8299e5ad99d5', '0e073250b876489a8eefd6b801374b60',
        '2020-08-28 10:44:59', '2020-08-28 10:45:32', '1');
INSERT INTO `user_role`
VALUES ('8a606b2536c5467383e7df04d21760a1', 'd1411e5c58a70c44d72ca016d1acc183', '8a024a8d53934950ad56c98846ca864b',
        '2020-09-01 15:18:39', '2020-09-01 15:18:39', '0');
INSERT INTO `user_role`
VALUES ('8b7d467e58b443438635f54bdb32da57', '326f44ea600f516708966fa65a6b91f0', '82773b3e46924b049b0269334cc4ea0e',
        '2020-08-31 11:01:12', '2020-08-31 14:35:54', '1');
INSERT INTO `user_role`
VALUES ('8c24b8675bcb4d8b8dc2c2f99fefec1b', 'd1411e5c58a70c44d72ca016d1acc183', '8a024a8d53934950ad56c98846ca864b',
        '2020-09-01 11:07:58', '2020-09-01 11:40:18', '1');
INSERT INTO `user_role`
VALUES ('8d5ae4c84c5749068fb39cf700bb90d7', '326f44ea600f516708966fa65a6b91f0', '82773b3e46924b049b0269334cc4ea0e',
        '2020-08-31 10:55:50', '2020-08-31 11:01:12', '1');
INSERT INTO `user_role`
VALUES ('8f97064d81db44bbb5e9ea9bef0ac983', 'fc354e3dd71711ea9fce00163e002b3a', '0e073250b876489a8eefd6b801374b60',
        '2020-08-11 21:14:59', '2020-08-11 21:14:59', '0');
INSERT INTO `user_role`
VALUES ('925865d661c740f386b2bca5d77aaca1', 'fc35495fd71711ea9fce00163e002b3a', '0e073250b876489a8eefd6b801374b60',
        '2020-08-12 11:40:16', '2020-08-12 11:40:16', '0');
INSERT INTO `user_role`
VALUES ('9f443b36f0e24b1abb5fd2dca9b8a345', '911a8e15eebf4d569f8a8299e5ad99d5', '0e073250b876489a8eefd6b801374b60',
        '2020-08-14 16:57:40', '2020-08-28 10:44:59', '1');
INSERT INTO `user_role`
VALUES ('a0c6c162e7cf4da0988231270a4e9ae9', 'd1411e5c58a70c44d72ca016d1acc183', '8a024a8d53934950ad56c98846ca864b',
        '2020-09-01 11:40:18', '2020-09-01 14:25:16', '1');
INSERT INTO `user_role`
VALUES ('a38a79a8104d44ec91621b9ee0fd6cf9', '8961e4f21fac43b796c1e0d0a422414', '8a024a8d53934950ad56c98846ca864b',
        '2020-08-31 21:04:51', '2020-08-31 21:04:51', '0');
INSERT INTO `user_role`
VALUES ('b37794076c284a0c96ecf960c9aba05d', 'c0f597ae8bbd4550b1caa6ac130c02e6', '82773b3e46924b049b0269334cc4ea0e',
        '2020-09-22 16:24:21', '2020-09-22 16:29:03', '1');
INSERT INTO `user_role`
VALUES ('d4f90eb1ecfa480c8d50f6f08f35bfe9', 'fc354e3dd71711ea9fce00163e002b3a', '27f83817878649cb941b2c4fe71de12e',
        '2020-08-11 21:09:56', '2020-08-11 21:14:59', '1');
INSERT INTO `user_role`
VALUES ('dd16dc2f7f0f4a18a331be1c9026328d', '326f44ea600f516708966fa65a6b91f0', '82773b3e46924b049b0269334cc4ea0e',
        '2020-08-31 10:52:33', '2020-08-31 10:55:50', '1');
INSERT INTO `user_role`
VALUES ('e539a761e1a04b57ab2918b07b5e4975', 'fc354e3dd71711ea9fce00163e002b3a', '7314257f98de48588012b2f30ede585a',
        '2020-08-11 21:09:56', '2020-08-11 21:14:59', '1');
INSERT INTO `user_role`
VALUES ('e6e9f64298f644658cb1db72dc20ed5d', '911a8e15eebf4d569f8a8299e5ad99d5', '0e073250b876489a8eefd6b801374b60',
        '2020-08-14 15:55:45', '2020-08-14 16:02:36', '1');
INSERT INTO `user_role`
VALUES ('ffd0fcf7605940b9b24d30134a58c3af', 'c0f597ae8bbd4550b1caa6ac130c02e6', '82773b3e46924b049b0269334cc4ea0e',
        '2020-09-22 16:29:03', '2020-09-22 16:29:03', '0');


-- ----------------------------
-- Table structure for rd_hist_summary
-- ----------------------------
DROP TABLE IF EXISTS `rd_hist_summary`;
CREATE TABLE `rd_hist_summary`
(
    `id`                  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '编号',
    `device_id`           varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '设备编号',
    `device_type`         varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备类型 rdDevice ETCRdDevice',
    `line_no`             int NULL DEFAULT NULL COMMENT '车道号 单车道：上行 01，下行03。2车道以上：上行从内至外按 11、12、13…编号；下行按 31、32、33…编号',
    `type`                tinyint NULL DEFAULT NULL COMMENT '统计类型 5min(5),30min(30)',
    `time`                varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '时间,格式为HH:mm',
    `total_occupancy`     decimal(38, 3) NULL DEFAULT NULL COMMENT '总占有率',
    `total_flux`          bigint NULL DEFAULT NULL COMMENT '总交通量',
    `total_mileage`       decimal(38, 3) NULL DEFAULT NULL COMMENT '总里程',
    `count`               bigint NULL DEFAULT NULL COMMENT '数据总条数',
    `occupancy`           decimal(10, 3) NULL DEFAULT NULL COMMENT '占有率',
    `flux`                bigint NULL DEFAULT NULL COMMENT '交通量(总交通量除以总条数)',
    `speed`               decimal(10, 3) NULL DEFAULT NULL COMMENT '平均速度(总里程除以总交通量)',
    `car_total_flux`      bigint NULL DEFAULT NULL COMMENT '客车总交通量',
    `car_total_mileage`   decimal(38, 3) NULL DEFAULT NULL COMMENT '客车总里程',
    `car_flux`            bigint NULL DEFAULT NULL COMMENT '客车平均交通量(总交通量除以总条数)',
    `car_speed`           decimal(10, 3) NULL DEFAULT NULL COMMENT '客车平均速度(总里程除以总交通量)',
    `truck_total_flux`    bigint NULL DEFAULT NULL COMMENT '货车总交通量',
    `truck_total_mileage` decimal(38, 3) NULL DEFAULT NULL COMMENT '货车总里程',
    `truck_flux`          bigint NULL DEFAULT NULL COMMENT '货车平均交通量(总交通量除以总条数)',
    `truck_speed`         decimal(10, 3) NULL DEFAULT NULL COMMENT '货车平均速度(总里程除以总交通量)',
    `create_time`         datetime NULL DEFAULT NULL,
    `update_time`         datetime NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX                 `idx_device_id`(`device_id`) USING BTREE,
    INDEX                 `idx_device_type`(`device_type`) USING BTREE,
    INDEX                 `idx_type`(`type`) USING BTREE,
    INDEX                 `idx_time`(`time`) USING BTREE,
    INDEX                 `idx_line_no`(`line_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '交调(包括etc)历史数据统计表' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Records of rd_hist_summary
-- ----------------------------
INSERT INTO `rd_hist_summary`
VALUES ('000131553eb44633873ee3899a5aa4b4', '0071143313030004', 'rdDevice', 31, 5, '07:55', 366.000, 5, 639377.000, 250,
        1.464, 26, 96.495, 4936, 506805.000, 19, 102.675, 1690, 132572.000, 6, 78.445, '2022-02-23 14:28:00',
        '2022-02-24 00:30:15');
INSERT INTO `rd_hist_summary`
VALUES ('0001be00ec724618b05f483526318816', '0071143313030001', 'rdDevice', 32, 5, '05:30', 219.000, 1687, 145600.000,
        226, 0.969, 7, 86.307, 1327, 117155.000, 5, 88.286, 360, 28445.000, 1, 79.014, '2022-02-23 14:28:00',
        '2022-02-24 00:30:15');
INSERT INTO `rd_hist_summary`
VALUES ('00023523ab914b49b348ff5bb5b7b53b', 'G300161001001920010', 'ETCRdDevice', 31, 5, '06:55', 0.000, 11984,
        1133626.770, 161, 0.000, 74, 94.595, 10027, 980400.910, 62, 97.776, 1957, 153225.860, 12, 78.296,
        '2022-02-23 14:30:10', '2022-02-24 00:30:38');
INSERT INTO `rd_hist_summary`
VALUES ('0002f414bcbb42f0ac767f4b1f3a7903', 'G300161001000920010', 'ETCRdDevice', 33, 5, '06:40', 0.000, 3722,
        300758.260, 160, 0.000, 23, 80.806, 2241, 213635.960, 14, 95.331, 1481, 87122.300, 9, 58.827,
        '2022-02-23 14:30:10', '2022-02-24 00:30:38');
INSERT INTO `rd_hist_summary`
VALUES ('0005b917f669417a96d6792308d05782', 'G300161001000710010', 'ETCRdDevice', 13, 5, '17:15', 0.000, 7040,
        484380.060, 161, 0.000, 43, 68.804, 5115, 369638.330, 31, 72.266, 1925, 114741.730, 11, 59.606,
        '2022-02-23 14:30:10', '2022-02-24 00:30:38');
INSERT INTO `rd_hist_summary`
VALUES ('00065824ab27462e9e24c1fa34a15f46', 'G300161001001510010', 'ETCRdDevice', 12, 5, '20:45', 0.000, 5570,
        513209.190, 161, 0.000, 34, 92.138, 4726, 443886.200, 29, 93.924, 844, 69322.990, 5, 82.136,
        '2022-02-23 14:30:10', '2022-02-24 00:30:38');
INSERT INTO `rd_hist_summary`
VALUES ('0008fc35b9304833b0bc1e254ebba07e', '0151145313010067', 'rdDevice', 13, 30, '20:30', 10740.000, 91587,
        6276133.000, 1756, 6.116, 52, 68.526, 57376, 3915099.000, 32, 68.236, 34211, 2361034.000, 19, 69.014,
        '2022-02-23 14:29:05', '2022-02-24 00:30:25');
INSERT INTO `rd_hist_summary`
VALUES ('0009edc9fbae4d639983ea76256fed58', 'G300161001000420010', 'ETCRdDevice', 31, 5, '06:55', 0.000, 7982,
        581709.040, 161, 0.000, 49, 72.878, 6379, 482432.220, 39, 75.628, 1603, 99276.820, 9, 61.932,
        '2022-02-23 14:30:10', '2022-02-24 00:30:37');
INSERT INTO `rd_hist_summary`
VALUES ('000ae5881f104c15b9cb37f94750df9e', '0071143313030003', 'rdDevice', 32, 5, '14:10', 3489.000, 20486,
        1892133.000, 273, 12.780, 75, 92.362, 15677, 1470582.000, 57, 93.805, 4809, 421551.000, 17, 87.659,
        '2022-02-23 14:28:00', '2022-02-24 00:30:15');
INSERT INTO `rd_hist_summary`
VALUES ('000b971aaf2642ad9db55c429ecedd3b', '0071154312120040', 'rdDevice', 31, 5, '23:20', 221.000, 3586, 356650.000,
        290, 0.762, 12, 99.456, 3522, 350825.000, 12, 99.610, 64, 5825.000, 0, 91.016, '2022-02-23 14:28:00',
        '2022-02-24 00:30:15');
INSERT INTO `rd_hist_summary`
VALUES ('000ba428e72a47999daa449c2794a8ac', '0151145313010065', 'rdDevice', 32, 5, '16:35', 798.000, 17689, 1278266.000,
        271, 2.945, 65, 72.263, 16989, 1228507.000, 62, 72.312, 700, 49759.000, 2, 71.084, '2022-02-23 14:28:00',
        '2022-02-24 00:30:15');
INSERT INTO `rd_hist_summary`
VALUES ('000e1a1c42ed4bcebc5cd236710d2947', '0151145315060050', 'rdDevice', 33, 5, '18:10', 39.000, 1801, 188506.000,
        94, 0.415, 19, 104.667, 1801, 188506.000, 19, 104.667, 0, 0.000, 0, 0.000, '2022-02-23 14:28:00',
        '2022-02-24 00:30:15');

-- ----------------------------
-- Table structure for standard_table
-- ----------------------------
DROP TABLE IF EXISTS `standard_table`;
CREATE TABLE `standard_table`
(
    `id`          bigint UNSIGNED NOT NULL AUTO_INCREMENT,
    `create_time` datetime NULL DEFAULT NULL,
    `update_time` datetime NULL DEFAULT NULL,
    `is_deleted`  tinyint(1) NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of standard_table
-- ----------------------------
INSERT INTO `standard_table`
VALUES (1, '2021-07-02 08:50:36', '2021-07-02 08:51:36', 0);

DROP TABLE IF EXISTS `resource_route`;
CREATE TABLE `resource_route`
(
    `id`             bigint(20) NOT NULL COMMENT '主键',
    `begin_point_id` varchar(50)    NOT NULL COMMENT '起点id(可以是任何构造物、资源)',
    `end_point_id`   varchar(50)    NOT NULL COMMENT '终点id(可以是任何构造物、资源)',
    `distance`       decimal(65, 3) NOT NULL COMMENT '起点到终点的距离，单位为km',
    `in_use`         tinyint(1) unsigned NOT NULL COMMENT '是否启用',
    `create_time`    datetime       NOT NULL COMMENT '创建时间',
    `update_time`    datetime       NOT NULL COMMENT '更新时间',
    `is_deleted`     tinyint(1) unsigned NOT NULL COMMENT '逻辑删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_begin_point_id_end_point_id` (`begin_point_id`,`end_point_id`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT='资源邻接矩阵路径表' ROW_FORMAT = Dynamic;

INSERT INTO resource_route (id, begin_point_id, end_point_id, distance, in_use, create_time, update_time, is_deleted)
VALUES (1508694415952994305, 'v1', 'v3', 10.000, 1, '2022-03-29 14:35:54', '2022-03-29 14:35:54', 0);
INSERT INTO resource_route (id, begin_point_id, end_point_id, distance, in_use, create_time, update_time, is_deleted)
VALUES (1508694415982354433, 'v1', 'v5', 30.000, 1, '2022-03-29 14:35:54', '2022-03-29 14:35:54', 0);
INSERT INTO resource_route (id, begin_point_id, end_point_id, distance, in_use, create_time, update_time, is_deleted)
VALUES (1508694415990743042, 'v1', 'v6', 100.000, 1, '2022-03-29 14:35:54', '2022-03-29 14:35:54', 0);
INSERT INTO resource_route (id, begin_point_id, end_point_id, distance, in_use, create_time, update_time, is_deleted)
VALUES (1508694415990743043, 'v2', 'v3', 5.000, 1, '2022-03-29 14:35:54', '2022-03-29 14:35:54', 0);
INSERT INTO resource_route (id, begin_point_id, end_point_id, distance, in_use, create_time, update_time, is_deleted)
VALUES (1508694415990743044, 'v3', 'v4', 50.000, 1, '2022-03-29 14:35:54', '2022-03-29 14:35:54', 0);
INSERT INTO resource_route (id, begin_point_id, end_point_id, distance, in_use, create_time, update_time, is_deleted)
VALUES (1508694415990743045, 'v4', 'v6', 10.000, 1, '2022-03-29 14:35:54', '2022-03-29 14:35:54', 0);
INSERT INTO resource_route (id, begin_point_id, end_point_id, distance, in_use, create_time, update_time, is_deleted)
VALUES (1508694415999131650, 'v5', 'v4', 20.000, 1, '2022-03-29 14:35:54', '2022-03-29 14:35:54', 0);
INSERT INTO resource_route (id, begin_point_id, end_point_id, distance, in_use, create_time, update_time, is_deleted)
VALUES (1508694415999131651, 'v5', 'v6', 60.000, 1, '2022-03-29 14:35:54', '2022-03-29 14:35:54', 0);

-- ----------------------------
-- View structure for selectattachments
-- ----------------------------
DROP VIEW IF EXISTS `selectattachments`;
CREATE
ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `selectattachments` AS
select `attachment_general`.`id`          AS `id`,
       `attachment_general`.`attach_id`   AS `attach_id`,
       `attachment_general`.`attach_name` AS `attach_name`,
       `attachment_general`.`attach_type` AS `attach_type`,
       `attachment_general`.`attach_size` AS `attach_size`,
       `attachment_general`.`attach_path` AS `attach_path`,
       `attachment_general`.`create_time` AS `create_time`,
       `attachment_general`.`delflag`     AS `delflag`
from `attachment_general`;

SET
FOREIGN_KEY_CHECKS = 1;
