/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : 127.0.0.1:3306
Source Database       : bootdo-demo

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2020-03-26 18:33:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for blog_content
-- ----------------------------
DROP TABLE IF EXISTS `blog_content`;
CREATE TABLE `blog_content` (
  `cid` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `slug` varchar(255) DEFAULT NULL,
  `created` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `modified` bigint(20) DEFAULT NULL COMMENT '最近修改人id',
  `content` text COMMENT '内容',
  `type` varchar(16) DEFAULT NULL COMMENT '类型',
  `tags` varchar(200) DEFAULT NULL COMMENT '标签',
  `categories` varchar(200) DEFAULT NULL COMMENT '分类',
  `hits` int(5) DEFAULT NULL,
  `comments_num` int(5) DEFAULT '0' COMMENT '评论数量',
  `allow_comment` int(1) DEFAULT '0' COMMENT '开启评论',
  `allow_ping` int(1) DEFAULT '0' COMMENT '允许ping',
  `allow_feed` int(1) DEFAULT '0' COMMENT '允许反馈',
  `status` int(1) DEFAULT NULL COMMENT '状态',
  `author` varchar(100) DEFAULT NULL COMMENT '作者',
  `gtm_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gtm_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8 COMMENT='文章内容';

-- ----------------------------
-- Records of blog_content
-- ----------------------------
INSERT INTO `blog_content` VALUES ('123', '微信公众平台接口调试工具', null, null, null, '<ol id=\"manual\" class=\"manual\" style=\"color: rgb(51, 51, 51); font-family: &quot;Microsoft Yahei&quot;, Tahoma, Arial;\"><li style=\"margin-top: 10px; list-style: none;\"><b>使用说明：</b></li><li style=\"margin-top: 10px; list-style: none;\">（1）选择合适的接口。</li><li style=\"margin-top: 10px; list-style: none;\">（2）系统会生成该接口的参数表，您可以直接在文本框内填入对应的参数值。（红色星号表示该字段必填）</li><li style=\"margin-top: 10px; list-style: none;\">（3）点击检查问题按钮，即可得到相应的调试信息。</li></ol><div id=\"content\" class=\"content\" style=\"padding: 10px 15px; border: 1px solid rgb(204, 204, 204); color: rgb(51, 51, 51); background-color: rgb(248, 248, 248); border-radius: 3px; margin-bottom: 20px; font-family: &quot;Microsoft Yahei&quot;, Tahoma, Arial;\"><div id=\"typeSelectorDiv\" class=\"frm_control_group\" style=\"margin-bottom: 20px;\"><label class=\"frm_label\" style=\"float: left; margin-right: 38px; vertical-align: top; zoom: 1;\">一、接口类型：</label><div class=\"frm_controls\" style=\"overflow: hidden;\"><select id=\"typeSelector\" class=\"frm_input_box\" style=\"zoom: 1; line-height: 30px; vertical-align: middle; border-color: rgb(179, 179, 179); box-shadow: rgba(0, 0, 0, 0.15) 0px 1px 1px inset; border-radius: 3px; background-color: rgb(255, 255, 255);\">&nbsp;			 				 					<option value=\"0\">基础支持</option>&nbsp;						 			 				 					<option value=\"1\">向用户发送消息</option>&nbsp;						 			 				 					<option value=\"2\">用户管理</option>&nbsp;						 			 				 					<option value=\"3\">自定义菜单</option>&nbsp;						 			 				 					<option value=\"4\">推广支持</option>&nbsp;						 			 				 					<option value=\"5\">消息接口调试</option>&nbsp;						 			 				 					<option value=\"6\">硬件接入api接口调试</option>&nbsp;						 			 				 					<option value=\"7\">硬件接入消息接口调试</option>&nbsp;						 			 				 					<option value=\"8\">卡劵接口</option>&nbsp;						 			 				 					<option value=\"9\">评论接口</option>&nbsp;						 			 		</select></div></div><div id=\"formSelectorDiv\" class=\"frm_control_group\" style=\"margin-bottom: 20px;\"><label class=\"frm_label\" style=\"float: left; margin-right: 38px; vertical-align: top; zoom: 1;\">二、接口列表：</label><div class=\"frm_controls\" style=\"overflow: hidden;\"><select id=\"formSelector\" class=\"frm_input_box\" style=\"zoom: 1; line-height: 30px; vertical-align: middle; border-color: rgb(179, 179, 179); box-shadow: rgba(0, 0, 0, 0.15) 0px 1px 1px inset; border-radius: 3px; background-color: rgb(255, 255, 255); min-width: 330px;\">&nbsp;			 				<option value=\"0\">获取access_token接口 /token</option>&nbsp;			 				<option value=\"1\">多媒体文件上传接口 /media/upload</option>&nbsp;			 				<option value=\"2\">下载多媒体文件接口 /media/get</option>&nbsp;			 				<option value=\"3\">上传logo接口 /media/uploadimg</option>&nbsp;			 		</select>&nbsp;<span id=\"methodType\" class=\"frm_tips\" style=\"color: rgb(163, 163, 163); display: inline-block; zoom: 1; margin-left: 5px;\">方法：GET</span></div></div><div id=\"formContent\" class=\"frm_control_group\" style=\"margin-bottom: 20px;\"><label style=\"margin-right: 38px; vertical-align: top; zoom: 1;\">三、参数列表：</label><br><br><div id=\"formContainer\"><div class=\"inputDiv\"><span class=\"red\" style=\"color: red; vertical-align: top;\">*</span>&nbsp;<span class=\"name\" style=\"display: inline-block; zoom: 1; width: 125px; vertical-align: top;\">grant_type :</span>&nbsp;<input type=\"text\" reserved-name=\"grant_type\" method=\"GET\" data-type=\"string\" required=\"true\" value=\"client_credential\" disabled=\"true\" readonly=\"true\" style=\"width: 200px;\" aria-required=\"true\"><span class=\"tips\" style=\"vertical-align: top; display: block; margin-left: 139px; color: rgb(163, 163, 163);\">获取access_token填写client_credential</span><span class=\"errMsg\" style=\"vertical-align: top; display: block; margin-left: 139px;\"></span><br></div><div class=\"inputDiv\"><span class=\"red\" style=\"color: red; vertical-align: top;\">*</span>&nbsp;<span class=\"name\" style=\"display: inline-block; zoom: 1; width: 125px; vertical-align: top;\">appid :</span>&nbsp;<input type=\"text\" reserved-name=\"appid\" method=\"GET\" data-type=\"string\" required=\"true\" style=\"width: 200px;\" aria-required=\"true\"><span class=\"tips\" style=\"vertical-align: top; display: block; margin-left: 139px; color: rgb(163, 163, 163);\">填写appid</span><span class=\"errMsg\" style=\"vertical-align: top; display: block; margin-left: 139px;\"></span><br></div><div class=\"inputDiv\"><span class=\"red\" style=\"color: red; vertical-align: top;\">*</span>&nbsp;<span class=\"name\" style=\"display: inline-block; zoom: 1; width: 125px; vertical-align: top;\">secret :</span>&nbsp;<input type=\"text\" reserved-name=\"secret\" method=\"GET\" data-type=\"string\" required=\"true\" style=\"width: 200px;\" aria-required=\"true\"><span class=\"tips\" style=\"vertical-align: top; display: block; margin-left: 139px; color: rgb(163, 163, 163);\">填写appsecret</span><div><br></div><span class=\"errMsg\" style=\"vertical-align: top; display: block; margin-left: 139px;\"></span></div></div></div></div>', 'article', null, '', null, '0', '1', '0', '0', '1', 'zhch', '2020-03-26 10:21:29', '2020-03-26 10:21:29');
INSERT INTO `blog_content` VALUES ('124', '微信公众平台开发者功能调整公告', null, null, null, '<p><img src=\"/files/7e9a5664-2538-4365-a4f9-340c1e474bde.png\" style=\"width: 287px;\"></p><h3 class=\"announcement_title\" style=\"margin-top: 0px; margin-bottom: 0px; padding-bottom: 42px; font-weight: 400; font-size: 18px; text-align: center; color: rgb(53, 53, 53); font-family: &quot;Helvetica Neue&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, 黑体, Arial, sans-serif;\">微信公众平台开发者功能调整公告</h3><div id=\"content\" class=\"announcement_content\" style=\"color: rgb(53, 53, 53); font-family: &quot;Helvetica Neue&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, 黑体, Arial, sans-serif;\"><p style=\"margin-bottom: 0px; max-width: 100%;\">为保障微信公众平台用户帐号安全，降低微信公众平台用户开发者密码泄漏引发的盗用风险，平台将于2020年4月上旬起灰度调整部分开发者功能的使用方法，被灰度到的开发者也可以在站内信中查看相关通知，本功能调整也将在5月全面上线。具体调整说明如下：</p><h4 id=\"-access_token-\" style=\"margin-top: 0px; margin-bottom: 0px; max-width: 100%; font-weight: 400;\">一、 access_token接口调整</h4><p style=\"margin-bottom: 0px; max-width: 100%;\">在开发者进行获取 access_token调用时，如平台判断本次调用IP可能存在风险则进入风险调用确认流程，需要用户管理员确认后才可以成功获取。具体流程为：</p><p style=\"margin-bottom: 0px; max-width: 100%;\">开发者通过某IP发起调用-&gt;平台返回错误码[89503]并同时下发模板消息给公众号管理员-&gt;公众号管理员确认该IP可以调用-&gt;开发者使用该IP再次发起调用-&gt;调用成功。</p><p style=\"margin-bottom: 0px; max-width: 100%;\">如公众号管理员第一次拒绝该IP调用，该IP 1个小时内将无法发起调用，如公众号管理员多次拒绝该IP调用，该IP将可能长期无法发起调用。平台建议开发者在发起调用前主动与管理员沟通确认调用需求，或请求管理员开启IP白名单功能并将该IP加入IP白名单列表。详情请参考<a href=\"https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Get_access_token.html\" style=\"color: rgb(87, 107, 149); outline: 0px; max-width: 100%;\">开发文档</a>。</p><h4 id=\"-api-\" style=\"margin-top: 0px; margin-bottom: 0px; max-width: 100%; font-weight: 400;\">二、API群发接口调整</h4><p style=\"margin-bottom: 0px; max-width: 100%;\">平台将对已开启公众号群发保护的用户灰度开启API群发保护。该功能开启后，通过API群发接口对全部用户群发需要公众号管理员确认后才会群发成功，如管理员拒绝该次群发则群发失败，平台将会推送错误码到开发者服务器：</p><p style=\"margin-bottom: 0px; max-width: 100%;\">err(40001)：管理员拒绝</p><p style=\"margin-bottom: 0px; max-width: 100%;\">err(40002)：管理员30分钟内无响应，超时</p><p style=\"margin-bottom: 0px; max-width: 100%;\">用户可通过设置-安全中心-风险操作保护中关闭API群发保护功能。详情请参考<a href=\"https://developers.weixin.qq.com/doc/offiaccount/Message_Management/Batch_Sends_and_Originality_Checks.html\" style=\"color: rgb(87, 107, 149); outline: 0px; max-width: 100%;\">开发文档</a>。</p><h4 id=\"-\" style=\"margin-top: 0px; margin-bottom: 0px; max-width: 100%; font-weight: 400;\">三、新增关闭公众号开发者功能</h4><p style=\"margin-bottom: 0px; max-width: 100%;\">如无需再使用API功能，公众号管理员可以通过开发-基本配置-关闭开发者功能来主动关停API功能。该功能可以在需要使用时再次开启。对于长期不使用API功能且存在开发者密码泄漏风险的帐号，平台可能会主动为用户关闭该功能。</p><p style=\"margin-bottom: 0px; max-width: 100%;\">开发者密码具有微信公众平台帐号最高权限，与微信公众平台登录密码同样重要，我们再次提醒公众平台运营者不要将开发者密码透露给任何团队，并利用IP白名单功能主动限制恶意调用，规避因开发者密码泄漏造成的帐号被盗用风险。对于未主动开启IP白名单功能的帐号，平台建议用户做好风险调用确认工作，避免正常业务受到影响。</p><p style=\"margin-bottom: 0px; max-width: 100%;\">对于使用第三方平台的用户，平台建议用户通过授权机制使用正规的第三方平台来管理公众平台帐号，只授权业务所需的最小权限并及时取消不必要的第三方授权。</p><br style=\"max-width: 100%;\"></div><p class=\"sign\" style=\"margin-bottom: 0px; padding-top: 28px; text-align: right; color: rgb(53, 53, 53); font-family: &quot;Helvetica Neue&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, 黑体, Arial, sans-serif;\">微信团队<br><span id=\"online_time\">2020年03月17日</span></p>', 'article', null, '', null, '0', '1', '0', '0', '1', 'zhch', '2020-03-26 10:22:25', '2020-03-26 10:22:25');

-- ----------------------------
-- Table structure for function_switch
-- ----------------------------
DROP TABLE IF EXISTS `function_switch`;
CREATE TABLE `function_switch` (
  `hospital_id` varchar(64) NOT NULL COMMENT '医院id',
  `order_is_open` char(2) NOT NULL DEFAULT '1' COMMENT '预约流程是否开启1开启 0关闭',
  `clinic_is_open` char(2) NOT NULL DEFAULT '1' COMMENT '门诊流程是否开启1开启 0关闭',
  `report_is_open` char(2) NOT NULL DEFAULT '1' COMMENT '报告单流程是否开启1开启 0关闭',
  `beinhospital_is_opem` char(2) NOT NULL DEFAULT '1' COMMENT '住院流程是否开启1开启 0关闭',
  `tip` varchar(255) NOT NULL DEFAULT '该医院暂未开通此功能' COMMENT '提示语',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `extend` text COMMENT '扩展字段',
  PRIMARY KEY (`hospital_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='功能开关';

-- ----------------------------
-- Records of function_switch
-- ----------------------------
INSERT INTO `function_switch` VALUES ('b943887a3d464912b85df65019219218', '1', '1', '1', '1', '该医院暂未开通此功能', '2020-03-24 07:04:15', '2020-03-24 07:04:15', null);

-- ----------------------------
-- Table structure for news_msg_flow
-- ----------------------------
DROP TABLE IF EXISTS `news_msg_flow`;
CREATE TABLE `news_msg_flow` (
  `msg_flow_id` varchar(64) NOT NULL,
  `user_id` varchar(64) DEFAULT NULL COMMENT '用户id',
  `project_id` varchar(64) DEFAULT '' COMMENT '项目编号',
  `hospital_id` varchar(64) DEFAULT NULL COMMENT '医院院区id',
  `sender` varchar(128) DEFAULT NULL COMMENT '发送者',
  `recipient` varchar(128) DEFAULT NULL COMMENT '接受者',
  `msg_type` char(2) DEFAULT '0' COMMENT '推送方式 0 短信，1 公众号模板消息推送， 2 微信小程序模板消息',
  `msg_tag` char(2) DEFAULT '0' COMMENT '标签 01 注册，02 忘记密码，03 预约成功，04 取消预约，05 预约支付成功，06 预约支付取消，07 报告单推送',
  `content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
  `result_desc` varchar(2048) DEFAULT NULL COMMENT '发送结果',
  `msg_status` char(2) NOT NULL DEFAULT '0' COMMENT '发送状态 0成功 1失败',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `type` char(1) DEFAULT '2' COMMENT '1医生，2患者',
  `deletion_state` char(1) NOT NULL DEFAULT '0' COMMENT '删除状态,0未删除，1已删除',
  `temp1` varchar(128) DEFAULT NULL COMMENT '预留字段1',
  `temp2` varchar(128) DEFAULT NULL COMMENT '预留字段2',
  `temp3` varchar(128) DEFAULT NULL COMMENT '预留字段3',
  PRIMARY KEY (`msg_flow_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news_msg_flow
-- ----------------------------

-- ----------------------------
-- Table structure for news_notice
-- ----------------------------
DROP TABLE IF EXISTS `news_notice`;
CREATE TABLE `news_notice` (
  `channel_id` varchar(40) NOT NULL COMMENT '栏目ID',
  `parent_channel_id` varchar(40) DEFAULT NULL COMMENT '上级栏目ID',
  `channel_type` varchar(40) DEFAULT NULL COMMENT '发布类型',
  `hospital_id` varchar(40) DEFAULT NULL COMMENT '医院ID',
  `branch_id` varchar(40) DEFAULT NULL COMMENT '院区ID',
  `title` varchar(40) DEFAULT NULL COMMENT '内容标题',
  `external_links` varchar(255) DEFAULT NULL COMMENT '外部链接',
  `auther` varchar(40) DEFAULT NULL COMMENT '作者',
  `release_date` datetime DEFAULT NULL COMMENT '发布时间',
  `type` char(2) DEFAULT NULL COMMENT '类型(1.普通 2.图文 3.焦点  4.头条  5.其他)',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `attachments_path` varchar(255) DEFAULT NULL COMMENT '附件路径',
  `txt` longtext COMMENT '内容',
  `picture_path` varchar(255) DEFAULT NULL COMMENT '标题图片路径',
  `is_open` char(2) DEFAULT NULL COMMENT '是否开放(0：开放  1：不开放)',
  `state` char(2) DEFAULT NULL COMMENT '状态( 0:草稿  1：已发布  2：已删除 )',
  `browse_times` int(11) DEFAULT NULL COMMENT '浏览次数',
  `user_Id` varchar(40) DEFAULT NULL COMMENT '管理员ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `temp1` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `temp2` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `temp3` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  PRIMARY KEY (`channel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news_notice
-- ----------------------------

-- ----------------------------
-- Table structure for pv_user
-- ----------------------------
DROP TABLE IF EXISTS `pv_user`;
CREATE TABLE `pv_user` (
  `user_id` varchar(32) NOT NULL COMMENT 'UUID',
  `hospital_id` varchar(32) DEFAULT NULL COMMENT '医院ID',
  `branch_id` varchar(8) DEFAULT NULL COMMENT '院区ID',
  `third` varchar(255) DEFAULT NULL,
  `source` varchar(32) DEFAULT NULL COMMENT '注册来源，1微信，2支付宝，3rubikU，4第三方',
  `account` varchar(64) DEFAULT NULL COMMENT '账号，根据各种情况，可能是openid，手机号码',
  `third_open_id` varchar(128) DEFAULT NULL COMMENT '第三方唯一id',
  `third_type` varchar(8) DEFAULT NULL COMMENT '第三方类型',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `security_key` varchar(64) DEFAULT NULL COMMENT '密钥，一般用来加密密码',
  `union_id` varchar(64) DEFAULT NULL COMMENT '微信unionid',
  `user_name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `phone` varchar(64) DEFAULT NULL COMMENT '手机号码',
  `id_card` varchar(64) DEFAULT NULL COMMENT '证件号',
  `id_card_type` varchar(1) DEFAULT NULL COMMENT '证件号类型',
  `card` varchar(32) DEFAULT NULL COMMENT '卡号',
  `card_type` varchar(1) DEFAULT NULL COMMENT '卡类型',
  `wechat` varchar(64) DEFAULT NULL,
  `ali_user_id` varchar(64) DEFAULT NULL,
  `address` varchar(64) DEFAULT NULL COMMENT '住址',
  `state` char(1) DEFAULT '0' COMMENT '账户状态，0正常，1黑名单',
  `state_descr` varchar(32) DEFAULT NULL COMMENT '对state的说明，如进入预约黑名单，进入支付黑名单',
  `delete_state` char(1) DEFAULT '0' COMMENT '是否删除：0有效，1注销',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(64) DEFAULT NULL,
  `tmp1` varchar(32) DEFAULT NULL COMMENT '是否实名认证（0否，1是）',
  `tmp2` varchar(32) DEFAULT NULL COMMENT '备用字段2',
  `tmp3` varchar(32) DEFAULT NULL COMMENT '备用字段1',
  `relation_third_source` varchar(32) DEFAULT NULL COMMENT '注册来源，10微信公众，12微信小程序，   20支付宝生活号，3rubikU，4第三方',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pv_user
-- ----------------------------
INSERT INTO `pv_user` VALUES ('UCMED17022313492439130238', null, null, null, null, 'oaFPz0xZaWc0lid-VHURHC6uridY', null, null, null, null, 'oQxW7tzFNhvjHHrTb7ysS3xa3mO0', null, '44265e01a15e1b981927bb1bab30e990', null, null, null, null, null, null, null, '0', null, '0', '2020-03-22 07:05:44', '2020-03-22 07:05:44', null, null, null, null, null);

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级部门ID，一级部门为0',
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：禁用  1: 正常',
  `hospital_id` varchar(64) NOT NULL COMMENT '医院id',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COMMENT='部门管理';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('36', '0', '信息科', '1', '1', 'b943887a3d464912b85df65019219218');

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL COMMENT '文件类型',
  `url` varchar(200) DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=149 DEFAULT CHARSET=utf8 COMMENT='文件上传';

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES ('148', '0', '/files/7e9a5664-2538-4365-a4f9-340c1e474bde.png', '2020-03-26 09:39:03');

-- ----------------------------
-- Table structure for sys_hospital
-- ----------------------------
DROP TABLE IF EXISTS `sys_hospital`;
CREATE TABLE `sys_hospital` (
  `hospital_id` varchar(40) NOT NULL COMMENT '医院id',
  `parent_id` varchar(40) NOT NULL DEFAULT '0' COMMENT '父级医院id，第一级0',
  `project_id` varchar(40) DEFAULT NULL COMMENT '所属项目id',
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `his_hospital_id` varchar(50) DEFAULT NULL COMMENT 'his医院id 不是区域医院值为1',
  `hospital_name` varchar(100) NOT NULL COMMENT '医院名称',
  `is_maintenance` char(2) DEFAULT '0' COMMENT '是否开启维护 0不开启1开启',
  `maintenance_notice` varchar(255) DEFAULT NULL COMMENT '维护提示语',
  `seq` int(11) DEFAULT NULL COMMENT '排序',
  `delete_state` char(2) DEFAULT '0' COMMENT '0 有效，1 无效',
  `extend` text COMMENT '扩展字段（电子健康卡appid appkey termId）',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`hospital_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='医院管理表';

-- ----------------------------
-- Records of sys_hospital
-- ----------------------------
INSERT INTO `sys_hospital` VALUES ('4f3b437ef9f24b5e87afd4979c0d77d8', '50a1bac65525474cb9c05a87e05c58da', '3b069fc06c0211eab6780050569b0a11', '伊宁区域掌医项目', '1', '测试医院3', '0', '功能维护中，请稍后访问。', '0', '0', '{\"commonUrl\":\"1\",\"wswReportUrl\":\"\",\"jyReportUrl\":\"\",\"jcReportUrl\":\"\",\"qyHIS\":\"0\"}', '2020-03-23 12:06:16', null);
INSERT INTO `sys_hospital` VALUES ('50a1bac65525474cb9c05a87e05c58da', '0', '3b069fc06c0211eab6780050569b0a11', '伊宁区域掌医项目', '1', '测试区', '0', '功能维护中，请稍后访问。', '99', '0', '{\"commonUrl\":\"\",\"wswReportUrl\":\"\",\"jyReportUrl\":\"\",\"jcReportUrl\":\"\",\"qyHIS\":\"0\"}', '2020-03-23 12:05:30', '2020-03-23 12:06:45');
INSERT INTO `sys_hospital` VALUES ('b943887a3d464912b85df65019219218', '50a1bac65525474cb9c05a87e05c58da', '3b069fc06c0211eab6780050569b0a11', '伊宁区域掌医项目', '1', '测试医院1', '0', '功能维护中，请稍后访问。', '0', '0', '{\"commonUrl\":\"1\",\"wswReportUrl\":\"\",\"jyReportUrl\":\"\",\"jcReportUrl\":\"\",\"qyHIS\":\"0\"}', '2020-03-23 12:05:53', null);
INSERT INTO `sys_hospital` VALUES ('f2e6332f02c14b7897abc78cc2fdabb1', '50a1bac65525474cb9c05a87e05c58da', '3b069fc06c0211eab6780050569b0a11', '伊宁区域掌医项目', '1', '测试医院2', '0', '功能维护中，请稍后访问。', '0', '0', '{\"commonUrl\":\"1\",\"wswReportUrl\":\"\",\"jyReportUrl\":\"\",\"jcReportUrl\":\"\",\"qyHIS\":\"0\"}', '2020-03-23 12:06:02', null);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=297 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('2', '3', '系统菜单', '/admin/sys-menu', 'sys:menu:menu', '1', 'fa fa-th-list', '2', '2017-08-09 22:55:15', null);
INSERT INTO `sys_menu` VALUES ('3', '0', '系统管理', null, null, '0', 'fa fa-cubes', '0', '2017-08-09 23:06:55', '2017-08-14 14:13:43');
INSERT INTO `sys_menu` VALUES ('6', '251', '用户管理', '/admin/sys-user', 'sys:user:user', '1', 'fa fa-user', '0', '2017-08-10 14:12:11', null);
INSERT INTO `sys_menu` VALUES ('7', '3', '角色管理', '/admin/sys-role', 'sys:role:role', '1', 'fa fa-paw', '1', '2017-08-10 14:13:19', null);
INSERT INTO `sys_menu` VALUES ('12', '6', '新增', '', 'sys:user:add', '2', '', '0', '2017-08-14 10:51:35', null);
INSERT INTO `sys_menu` VALUES ('13', '6', '编辑', '', 'sys:user:edit', '2', '', '0', '2017-08-14 10:52:06', null);
INSERT INTO `sys_menu` VALUES ('14', '6', '删除', null, 'sys:user:remove', '2', null, '0', '2017-08-14 10:52:24', null);
INSERT INTO `sys_menu` VALUES ('15', '7', '新增', '', 'sys:role:add', '2', '', '0', '2017-08-14 10:56:37', null);
INSERT INTO `sys_menu` VALUES ('20', '2', '新增', '', 'sys:menu:add', '2', '', '0', '2017-08-14 10:59:32', null);
INSERT INTO `sys_menu` VALUES ('21', '2', '编辑', '', 'sys:menu:edit', '2', '', '0', '2017-08-14 10:59:56', null);
INSERT INTO `sys_menu` VALUES ('22', '2', '删除', '', 'sys:menu:remove', '2', '', '0', '2017-08-14 11:00:26', null);
INSERT INTO `sys_menu` VALUES ('24', '6', '批量删除', '', 'sys:user:batchRemove', '2', '', '0', '2017-08-14 17:27:18', null);
INSERT INTO `sys_menu` VALUES ('25', '6', '停用', null, 'sys:user:disable', '2', null, '0', '2017-08-14 17:27:43', null);
INSERT INTO `sys_menu` VALUES ('26', '6', '重置密码', '', 'sys:user:resetPwd', '2', '', '0', '2017-08-14 17:28:34', null);
INSERT INTO `sys_menu` VALUES ('55', '7', '编辑', '', 'sys:role:edit', '2', '', null, null, null);
INSERT INTO `sys_menu` VALUES ('56', '7', '删除', '', 'sys:role:remove', '2', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('61', '2', '批量删除', '', 'sys:menu:batchRemove', '2', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('62', '7', '批量删除', '', 'sys:role:batchRemove', '2', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('73', '251', '部门管理', '/admin/dept', 'admin:dept:dept', '1', 'fa fa-users', '3', null, null);
INSERT INTO `sys_menu` VALUES ('74', '73', '增加', '/admin/dept/add', 'admin:dept:add', '2', null, '1', null, null);
INSERT INTO `sys_menu` VALUES ('75', '73', '刪除', '/admin/dept/remove', 'admin:dept:remove', '2', null, '2', null, null);
INSERT INTO `sys_menu` VALUES ('76', '73', '编辑', '/admin/dept/edit', 'admin:dept:edit', '2', null, '3', null, null);
INSERT INTO `sys_menu` VALUES ('121', '0', '消息中心', '', '', '0', 'fa fa-bell-o', '3', null, null);
INSERT INTO `sys_menu` VALUES ('125', '121', '推送记录', '/admin/news/msgFlow', 'admin:news:msgFlow', '1', '', '0', null, null);
INSERT INTO `sys_menu` VALUES ('143', '232', '文本内容', '/admin/news-notice', 'admin:news:notice', '1', 'fa fa-book', '9', null, null);
INSERT INTO `sys_menu` VALUES ('174', '3', '医院管理', '/admin/hospital/hospital', 'admin:hospital:hospital', '1', 'fa fa-file-excel-o', '5', null, null);
INSERT INTO `sys_menu` VALUES ('180', '3', '项目管理', '/admin/hospital/project', 'admin:hospital:project', '1', 'fa fa-file-excel-o', '6', null, null);
INSERT INTO `sys_menu` VALUES ('186', '180', '增加项目', '/admin/hospital/project/add', 'admin:hospital:project:add', '2', '', '1', null, null);
INSERT INTO `sys_menu` VALUES ('187', '180', '项目修改', '/admin/hospital/project/edit', 'admin:hospital:project:edit', '2', '', '2', null, null);
INSERT INTO `sys_menu` VALUES ('188', '180', '项目删除', '/admin/hospital/project/remove', 'admin:hospital:project:remove', '2', 'fa fa-remove', '5', null, null);
INSERT INTO `sys_menu` VALUES ('202', '174', '增加医院', '/admin/hospital/hospital/add', 'admin:hospital:hospital:add', '2', 'fa fa-plus', '1', null, null);
INSERT INTO `sys_menu` VALUES ('203', '174', '医院修改', '/admin/hospital/hospital/edit', 'admin:hospital:hospital:edit', '2', 'fa fa-arrows-h', '2', null, null);
INSERT INTO `sys_menu` VALUES ('204', '174', '医院删除', '/admin/hospital/hospital/remove', 'admin:hospital:hospital:remove', '2', 'fa fa-close', '3', null, null);
INSERT INTO `sys_menu` VALUES ('232', '0', '静态功能管理', '', '', '0', 'fa fa-bank', '2', null, null);
INSERT INTO `sys_menu` VALUES ('251', '0', '医院信息管理', '', '', '0', 'fa fa-arrows', '0', null, null);
INSERT INTO `sys_menu` VALUES ('269', '143', '新增', null, 'admin:news:add', '2', '', '0', null, null);
INSERT INTO `sys_menu` VALUES ('270', '143', '修改', null, 'admin:news:edit', '2', null, '1', null, null);
INSERT INTO `sys_menu` VALUES ('271', '143', '删除', null, 'admin:news:delete', '2', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('272', '143', '批量删除', null, 'admin:news:batchRemove', '2', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('282', '0', '功能管理', '', '', '0', 'fa fa-bar-chart', '66', null, null);
INSERT INTO `sys_menu` VALUES ('283', '282', '功能开关', '/admin/functionSwitch', 'admin:functionSwitch:functionSwitch', '1', 'fa fa-folder', '1', null, null);
INSERT INTO `sys_menu` VALUES ('284', '283', '新增', '', 'admin:functionSwitch:add', '2', '', '1', null, null);
INSERT INTO `sys_menu` VALUES ('285', '283', '修改', '', 'admin:functionSwitch:edit', '2', '', '2', null, null);
INSERT INTO `sys_menu` VALUES ('286', '283', '删除', '', 'admin:functionSwitch:remove', '2', '', '3', null, null);
INSERT INTO `sys_menu` VALUES ('287', '0', '博客管理', '', '', '0', 'fa fa-photo', '66', null, null);
INSERT INTO `sys_menu` VALUES ('288', '287', '发布文章', '/admin/blog/bContent/add', 'blog:bContent:add', '1', 'fa fa-arrows', null, null, null);
INSERT INTO `sys_menu` VALUES ('289', '287', '文章列表', '/admin/blog/bContent', 'blog:bContent:bContent', '1', 'fa fa-bars', null, null, null);
INSERT INTO `sys_menu` VALUES ('290', '289', '新增', '', 'blog:bContent:add', '2', 'fa fa-plus', null, null, null);
INSERT INTO `sys_menu` VALUES ('291', '289', '编辑', '', 'blog:bContent:edit', '2', 'fa fa-window-minimize', null, null, null);
INSERT INTO `sys_menu` VALUES ('292', '289', '删除', '', 'blog:bContent:remove', '2', 'fa fa-remove', null, null, null);
INSERT INTO `sys_menu` VALUES ('293', '289', '批量删除', '', 'blog:bContent:batchRemove', '2', 'fa fa-remove', null, null, null);
INSERT INTO `sys_menu` VALUES ('294', '0', '其他模块', '', '', '0', 'fa fa-folder-open', '99', null, null);
INSERT INTO `sys_menu` VALUES ('295', '294', '百度图表', '/admin/graphEcharts', '', '1', '', null, null, null);
INSERT INTO `sys_menu` VALUES ('296', '294', '系统监控', '/druid/datasource.html', '', '1', '', null, null, null);

-- ----------------------------
-- Table structure for sys_project
-- ----------------------------
DROP TABLE IF EXISTS `sys_project`;
CREATE TABLE `sys_project` (
  `project_id` varchar(40) NOT NULL COMMENT '主键',
  `project_name` varchar(40) NOT NULL COMMENT '项目名称',
  `project_type` char(1) DEFAULT NULL COMMENT '项目类型（1.平台，2.单医院）',
  `img_url` varchar(1024) DEFAULT NULL COMMENT '项目图片',
  `createdby` varchar(40) DEFAULT NULL COMMENT '新建者',
  `createdon` datetime DEFAULT NULL COMMENT '新建日期',
  `modifiedby` varchar(40) DEFAULT NULL COMMENT '修改者',
  `modifiedon` datetime DEFAULT NULL COMMENT '修改日期',
  `deletion_state` char(1) DEFAULT '0' COMMENT '删除状态,0未删除，1已删除',
  `description` varchar(500) DEFAULT NULL COMMENT '备注',
  `seq` int(10) DEFAULT '0' COMMENT '排序，越大越排前',
  `project_level` varchar(40) DEFAULT '0' COMMENT '项目等级字段',
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_project
-- ----------------------------
INSERT INTO `sys_project` VALUES ('3b069fc06c0211eab6780050569b0a11', 'bootdo测试学习', '1', null, 'admin', '2019-03-25 13:52:44', 'admin', '2019-11-12 12:51:48', '0', null, '0', '04');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `hospital_id` varchar(40) DEFAULT NULL COMMENT '医院编号',
  `hospital_name` varchar(255) DEFAULT NULL COMMENT '所属医院名称',
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `role_sign` varchar(100) DEFAULT NULL COMMENT '角色标识',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `user_id_create` bigint(255) DEFAULT NULL COMMENT '创建用户id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('13', 'b943887a3d464912b85df65019219218', '测试医院1', '消息查看', null, '', '1', '2020-03-24 12:26:21', '2020-03-24 12:26:21');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9174 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('8330', '12', '76');
INSERT INTO `sys_role_menu` VALUES ('8331', '12', '75');
INSERT INTO `sys_role_menu` VALUES ('8332', '12', '74');
INSERT INTO `sys_role_menu` VALUES ('8333', '12', '26');
INSERT INTO `sys_role_menu` VALUES ('8334', '12', '25');
INSERT INTO `sys_role_menu` VALUES ('8335', '12', '24');
INSERT INTO `sys_role_menu` VALUES ('8336', '12', '14');
INSERT INTO `sys_role_menu` VALUES ('8337', '12', '13');
INSERT INTO `sys_role_menu` VALUES ('8338', '12', '12');
INSERT INTO `sys_role_menu` VALUES ('8339', '12', '246');
INSERT INTO `sys_role_menu` VALUES ('8340', '12', '245');
INSERT INTO `sys_role_menu` VALUES ('8341', '12', '243');
INSERT INTO `sys_role_menu` VALUES ('8342', '12', '242');
INSERT INTO `sys_role_menu` VALUES ('8343', '12', '241');
INSERT INTO `sys_role_menu` VALUES ('8344', '12', '236');
INSERT INTO `sys_role_menu` VALUES ('8345', '12', '250');
INSERT INTO `sys_role_menu` VALUES ('8346', '12', '249');
INSERT INTO `sys_role_menu` VALUES ('8347', '12', '135');
INSERT INTO `sys_role_menu` VALUES ('8348', '12', '134');
INSERT INTO `sys_role_menu` VALUES ('8349', '12', '133');
INSERT INTO `sys_role_menu` VALUES ('8350', '12', '169');
INSERT INTO `sys_role_menu` VALUES ('8351', '12', '168');
INSERT INTO `sys_role_menu` VALUES ('8352', '12', '167');
INSERT INTO `sys_role_menu` VALUES ('8353', '12', '177');
INSERT INTO `sys_role_menu` VALUES ('8354', '12', '176');
INSERT INTO `sys_role_menu` VALUES ('8355', '12', '175');
INSERT INTO `sys_role_menu` VALUES ('8356', '12', '161');
INSERT INTO `sys_role_menu` VALUES ('8357', '12', '208');
INSERT INTO `sys_role_menu` VALUES ('8358', '12', '207');
INSERT INTO `sys_role_menu` VALUES ('8359', '12', '206');
INSERT INTO `sys_role_menu` VALUES ('8360', '12', '179');
INSERT INTO `sys_role_menu` VALUES ('8361', '12', '215');
INSERT INTO `sys_role_menu` VALUES ('8362', '12', '212');
INSERT INTO `sys_role_menu` VALUES ('8363', '12', '201');
INSERT INTO `sys_role_menu` VALUES ('8364', '12', '200');
INSERT INTO `sys_role_menu` VALUES ('8365', '12', '199');
INSERT INTO `sys_role_menu` VALUES ('8366', '12', '170');
INSERT INTO `sys_role_menu` VALUES ('8367', '12', '158');
INSERT INTO `sys_role_menu` VALUES ('8368', '12', '157');
INSERT INTO `sys_role_menu` VALUES ('8369', '12', '155');
INSERT INTO `sys_role_menu` VALUES ('8370', '12', '152');
INSERT INTO `sys_role_menu` VALUES ('8371', '12', '146');
INSERT INTO `sys_role_menu` VALUES ('8372', '12', '145');
INSERT INTO `sys_role_menu` VALUES ('8373', '12', '144');
INSERT INTO `sys_role_menu` VALUES ('8374', '12', '238');
INSERT INTO `sys_role_menu` VALUES ('8375', '12', '240');
INSERT INTO `sys_role_menu` VALUES ('8376', '12', '239');
INSERT INTO `sys_role_menu` VALUES ('8377', '12', '237');
INSERT INTO `sys_role_menu` VALUES ('8378', '12', '228');
INSERT INTO `sys_role_menu` VALUES ('8379', '12', '226');
INSERT INTO `sys_role_menu` VALUES ('8380', '12', '225');
INSERT INTO `sys_role_menu` VALUES ('8381', '12', '231');
INSERT INTO `sys_role_menu` VALUES ('8382', '12', '165');
INSERT INTO `sys_role_menu` VALUES ('8383', '12', '164');
INSERT INTO `sys_role_menu` VALUES ('8384', '12', '163');
INSERT INTO `sys_role_menu` VALUES ('8385', '12', '150');
INSERT INTO `sys_role_menu` VALUES ('8386', '12', '149');
INSERT INTO `sys_role_menu` VALUES ('8387', '12', '148');
INSERT INTO `sys_role_menu` VALUES ('8388', '12', '139');
INSERT INTO `sys_role_menu` VALUES ('8389', '12', '138');
INSERT INTO `sys_role_menu` VALUES ('8390', '12', '137');
INSERT INTO `sys_role_menu` VALUES ('8391', '12', '218');
INSERT INTO `sys_role_menu` VALUES ('8392', '12', '217');
INSERT INTO `sys_role_menu` VALUES ('8393', '12', '216');
INSERT INTO `sys_role_menu` VALUES ('8394', '12', '214');
INSERT INTO `sys_role_menu` VALUES ('8395', '12', '213');
INSERT INTO `sys_role_menu` VALUES ('8396', '12', '196');
INSERT INTO `sys_role_menu` VALUES ('8397', '12', '195');
INSERT INTO `sys_role_menu` VALUES ('8398', '12', '194');
INSERT INTO `sys_role_menu` VALUES ('8399', '12', '192');
INSERT INTO `sys_role_menu` VALUES ('8400', '12', '191');
INSERT INTO `sys_role_menu` VALUES ('8401', '12', '190');
INSERT INTO `sys_role_menu` VALUES ('8402', '12', '125');
INSERT INTO `sys_role_menu` VALUES ('8403', '12', '142');
INSERT INTO `sys_role_menu` VALUES ('8404', '12', '141');
INSERT INTO `sys_role_menu` VALUES ('8405', '12', '140');
INSERT INTO `sys_role_menu` VALUES ('8406', '12', '123');
INSERT INTO `sys_role_menu` VALUES ('8407', '12', '130');
INSERT INTO `sys_role_menu` VALUES ('8408', '12', '129');
INSERT INTO `sys_role_menu` VALUES ('8409', '12', '128');
INSERT INTO `sys_role_menu` VALUES ('8410', '12', '247');
INSERT INTO `sys_role_menu` VALUES ('8411', '12', '229');
INSERT INTO `sys_role_menu` VALUES ('8412', '12', '104');
INSERT INTO `sys_role_menu` VALUES ('8413', '12', '223');
INSERT INTO `sys_role_menu` VALUES ('8414', '12', '222');
INSERT INTO `sys_role_menu` VALUES ('8415', '12', '221');
INSERT INTO `sys_role_menu` VALUES ('8416', '12', '188');
INSERT INTO `sys_role_menu` VALUES ('8417', '12', '187');
INSERT INTO `sys_role_menu` VALUES ('8418', '12', '186');
INSERT INTO `sys_role_menu` VALUES ('8419', '12', '204');
INSERT INTO `sys_role_menu` VALUES ('8420', '12', '203');
INSERT INTO `sys_role_menu` VALUES ('8421', '12', '202');
INSERT INTO `sys_role_menu` VALUES ('8422', '12', '62');
INSERT INTO `sys_role_menu` VALUES ('8423', '12', '56');
INSERT INTO `sys_role_menu` VALUES ('8424', '12', '55');
INSERT INTO `sys_role_menu` VALUES ('8425', '12', '15');
INSERT INTO `sys_role_menu` VALUES ('8426', '12', '61');
INSERT INTO `sys_role_menu` VALUES ('8427', '12', '22');
INSERT INTO `sys_role_menu` VALUES ('8428', '12', '21');
INSERT INTO `sys_role_menu` VALUES ('8429', '12', '20');
INSERT INTO `sys_role_menu` VALUES ('8430', '12', '120');
INSERT INTO `sys_role_menu` VALUES ('8431', '12', '119');
INSERT INTO `sys_role_menu` VALUES ('8432', '12', '118');
INSERT INTO `sys_role_menu` VALUES ('8433', '12', '117');
INSERT INTO `sys_role_menu` VALUES ('8434', '12', '116');
INSERT INTO `sys_role_menu` VALUES ('8435', '12', '115');
INSERT INTO `sys_role_menu` VALUES ('8436', '12', '114');
INSERT INTO `sys_role_menu` VALUES ('8437', '12', '113');
INSERT INTO `sys_role_menu` VALUES ('8438', '12', '112');
INSERT INTO `sys_role_menu` VALUES ('8439', '12', '111');
INSERT INTO `sys_role_menu` VALUES ('8440', '12', '110');
INSERT INTO `sys_role_menu` VALUES ('8441', '12', '109');
INSERT INTO `sys_role_menu` VALUES ('8442', '12', '81');
INSERT INTO `sys_role_menu` VALUES ('8443', '12', '80');
INSERT INTO `sys_role_menu` VALUES ('8444', '12', '79');
INSERT INTO `sys_role_menu` VALUES ('8445', '12', '73');
INSERT INTO `sys_role_menu` VALUES ('8446', '12', '6');
INSERT INTO `sys_role_menu` VALUES ('8447', '12', '251');
INSERT INTO `sys_role_menu` VALUES ('8448', '12', '235');
INSERT INTO `sys_role_menu` VALUES ('8449', '12', '248');
INSERT INTO `sys_role_menu` VALUES ('8450', '12', '132');
INSERT INTO `sys_role_menu` VALUES ('8451', '12', '234');
INSERT INTO `sys_role_menu` VALUES ('8452', '12', '166');
INSERT INTO `sys_role_menu` VALUES ('8453', '12', '233');
INSERT INTO `sys_role_menu` VALUES ('8454', '12', '256');
INSERT INTO `sys_role_menu` VALUES ('8455', '12', '254');
INSERT INTO `sys_role_menu` VALUES ('8456', '12', '198');
INSERT INTO `sys_role_menu` VALUES ('8457', '12', '154');
INSERT INTO `sys_role_menu` VALUES ('8458', '12', '143');
INSERT INTO `sys_role_menu` VALUES ('8459', '12', '232');
INSERT INTO `sys_role_menu` VALUES ('8460', '12', '230');
INSERT INTO `sys_role_menu` VALUES ('8461', '12', '227');
INSERT INTO `sys_role_menu` VALUES ('8462', '12', '224');
INSERT INTO `sys_role_menu` VALUES ('8463', '12', '162');
INSERT INTO `sys_role_menu` VALUES ('8464', '12', '147');
INSERT INTO `sys_role_menu` VALUES ('8465', '12', '136');
INSERT INTO `sys_role_menu` VALUES ('8466', '12', '220');
INSERT INTO `sys_role_menu` VALUES ('8467', '12', '193');
INSERT INTO `sys_role_menu` VALUES ('8468', '12', '189');
INSERT INTO `sys_role_menu` VALUES ('8469', '12', '197');
INSERT INTO `sys_role_menu` VALUES ('8470', '12', '124');
INSERT INTO `sys_role_menu` VALUES ('8471', '12', '122');
INSERT INTO `sys_role_menu` VALUES ('8472', '12', '121');
INSERT INTO `sys_role_menu` VALUES ('8473', '12', '72');
INSERT INTO `sys_role_menu` VALUES ('8474', '12', '77');
INSERT INTO `sys_role_menu` VALUES ('8475', '12', '180');
INSERT INTO `sys_role_menu` VALUES ('8476', '12', '174');
INSERT INTO `sys_role_menu` VALUES ('8477', '12', '7');
INSERT INTO `sys_role_menu` VALUES ('8478', '12', '2');
INSERT INTO `sys_role_menu` VALUES ('8479', '12', '3');
INSERT INTO `sys_role_menu` VALUES ('8480', '12', '108');
INSERT INTO `sys_role_menu` VALUES ('8481', '12', '107');
INSERT INTO `sys_role_menu` VALUES ('8482', '12', '106');
INSERT INTO `sys_role_menu` VALUES ('8483', '12', '105');
INSERT INTO `sys_role_menu` VALUES ('8484', '12', '78');
INSERT INTO `sys_role_menu` VALUES ('8485', '12', '1');
INSERT INTO `sys_role_menu` VALUES ('8486', '12', '-1');
INSERT INTO `sys_role_menu` VALUES ('9129', '11', '76');
INSERT INTO `sys_role_menu` VALUES ('9130', '11', '75');
INSERT INTO `sys_role_menu` VALUES ('9131', '11', '74');
INSERT INTO `sys_role_menu` VALUES ('9132', '11', '26');
INSERT INTO `sys_role_menu` VALUES ('9133', '11', '25');
INSERT INTO `sys_role_menu` VALUES ('9134', '11', '24');
INSERT INTO `sys_role_menu` VALUES ('9135', '11', '14');
INSERT INTO `sys_role_menu` VALUES ('9136', '11', '13');
INSERT INTO `sys_role_menu` VALUES ('9137', '11', '12');
INSERT INTO `sys_role_menu` VALUES ('9138', '11', '165');
INSERT INTO `sys_role_menu` VALUES ('9139', '11', '164');
INSERT INTO `sys_role_menu` VALUES ('9140', '11', '163');
INSERT INTO `sys_role_menu` VALUES ('9141', '11', '150');
INSERT INTO `sys_role_menu` VALUES ('9142', '11', '149');
INSERT INTO `sys_role_menu` VALUES ('9143', '11', '148');
INSERT INTO `sys_role_menu` VALUES ('9144', '11', '142');
INSERT INTO `sys_role_menu` VALUES ('9145', '11', '141');
INSERT INTO `sys_role_menu` VALUES ('9146', '11', '140');
INSERT INTO `sys_role_menu` VALUES ('9147', '11', '229');
INSERT INTO `sys_role_menu` VALUES ('9148', '11', '73');
INSERT INTO `sys_role_menu` VALUES ('9149', '11', '6');
INSERT INTO `sys_role_menu` VALUES ('9150', '11', '251');
INSERT INTO `sys_role_menu` VALUES ('9151', '11', '162');
INSERT INTO `sys_role_menu` VALUES ('9152', '11', '124');
INSERT INTO `sys_role_menu` VALUES ('9153', '11', '232');
INSERT INTO `sys_role_menu` VALUES ('9154', '11', '272');
INSERT INTO `sys_role_menu` VALUES ('9155', '11', '271');
INSERT INTO `sys_role_menu` VALUES ('9156', '11', '270');
INSERT INTO `sys_role_menu` VALUES ('9157', '11', '269');
INSERT INTO `sys_role_menu` VALUES ('9158', '11', '268');
INSERT INTO `sys_role_menu` VALUES ('9159', '11', '143');
INSERT INTO `sys_role_menu` VALUES ('9160', '11', '123');
INSERT INTO `sys_role_menu` VALUES ('9161', '11', '174');
INSERT INTO `sys_role_menu` VALUES ('9162', '11', '204');
INSERT INTO `sys_role_menu` VALUES ('9163', '11', '203');
INSERT INTO `sys_role_menu` VALUES ('9164', '11', '202');
INSERT INTO `sys_role_menu` VALUES ('9165', '11', '-1');
INSERT INTO `sys_role_menu` VALUES ('9166', '11', '220');
INSERT INTO `sys_role_menu` VALUES ('9167', '11', '147');
INSERT INTO `sys_role_menu` VALUES ('9168', '11', '121');
INSERT INTO `sys_role_menu` VALUES ('9169', '11', '77');
INSERT INTO `sys_role_menu` VALUES ('9170', '11', '3');
INSERT INTO `sys_role_menu` VALUES ('9171', '13', '121');
INSERT INTO `sys_role_menu` VALUES ('9172', '13', '125');
INSERT INTO `sys_role_menu` VALUES ('9173', '13', '-1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `is_superman` char(2) DEFAULT '0' COMMENT '是否为超级管理员 0 不是，1 是',
  `is_project` char(2) DEFAULT '0' COMMENT '是否为项目管理员 0 不是，1 是',
  `name` varchar(100) NOT NULL COMMENT '真实姓名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `hospital_id` varchar(40) DEFAULT NULL COMMENT '医院id',
  `project_id` varchar(40) DEFAULT NULL COMMENT '所属项目id',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门id',
  `status` tinyint(255) NOT NULL COMMENT '状态 0:禁用，1:正常',
  `user_id_create` bigint(255) NOT NULL COMMENT '创建用户id',
  `last_login_hospital` varchar(40) DEFAULT NULL COMMENT '最后一次登录的医院id',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '1', '0', '超管', '27bd386e70f280e24c2f4f2a549b82cf', 'b943887a3d464912b85df65019219218', '3b069fc06c0211eab6780050569b0a11', '0', '1', '0', 'b943887a3d464912b85df65019219218', '2019-05-09 20:13:01', '2019-05-09 20:13:03');
INSERT INTO `sys_user` VALUES ('4', 'ceshi', '0', '0', '测试', 'a31c1e2c1b18f3d1392ba79584b9445a', 'b943887a3d464912b85df65019219218', '3b069fc06c0211eab6780050569b0a11', '36', '1', '1', 'b943887a3d464912b85df65019219218', '2020-03-24 12:27:20', '2020-03-24 12:27:20');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=164 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('162', '13', '11');
INSERT INTO `sys_user_role` VALUES ('163', '4', '13');
