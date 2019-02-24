/*
SQLyog Community v13.0.1 (64 bit)
MySQL - 10.2.19-MariaDB-cll-lve : Database - kappsonl_demo_workday
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`kappsonl_demo_workday` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `kappsonl_demo_workday`;

/*Table structure for table `activity_tbl` */

DROP TABLE IF EXISTS `activity_tbl`;

CREATE TABLE `activity_tbl` (
  `activity_id` varchar(30) NOT NULL,
  `description` varchar(50) NOT NULL,
  `test_cycle_id` varchar(30) NOT NULL,
  `test_type` varchar(30) NOT NULL COMMENT 'automation,manual',
  PRIMARY KEY (`activity_id`,`test_cycle_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `activity_tbl` */

insert  into `activity_tbl`(`activity_id`,`description`,`test_cycle_id`,`test_type`) values 
('ACTIVITY001','ACTIVITY001','MANTESTCYCLE001','manual'),
('ACTIVITY001','ACTIVITY001','MANTESTCYCLE002','manual'),
('ACTIVITY001','ACTIVITY001','TESTCYCLE001','automation'),
('ACTIVITY001','ACTIVITY001','TESTCYCLE002','automation'),
('Medko_signup','signup as patient','A_medko_signup','automation'),
('Medko_signup','signup as patient','m_medko_signup','manual'),
('test1','test','A_medko_signup','automation'),
('test1','test','m_medko_signup','manual'),
('W_admin_user_login','login as admin','W_admin_login','automation'),
('W_admin_user_login','login as admin','W_admin_login_manual','manual');

/*Table structure for table `categories` */

DROP TABLE IF EXISTS `categories`;

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `category_name` varchar(255) NOT NULL,
  `row_status` tinyint(1) NOT NULL DEFAULT 0,
  `created_on` datetime NOT NULL,
  `updated_on` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `categories` */

insert  into `categories`(`id`,`category_name`,`row_status`,`created_on`,`updated_on`) values 
(0,'Personal Data',0,'2018-12-27 04:13:23','2018-12-27 08:00:48'),
(1,'Staffing',0,'2018-12-27 05:22:27','2018-12-27 07:50:42'),
(2,'Compensation',0,'2018-12-27 08:20:28','2018-12-27 07:50:47'),
(3,'Benefits',0,'2018-12-27 08:28:28','2018-12-27 07:50:52'),
(4,'Payroll',0,'2018-12-27 04:17:20','2018-12-27 07:50:56'),
(5,'Integration',0,'2018-12-27 07:22:23','2018-12-27 07:51:01');

/*Table structure for table `defects_status` */

DROP TABLE IF EXISTS `defects_status`;

CREATE TABLE `defects_status` (
  `id` int(11) NOT NULL,
  `defect_status_name` varchar(255) NOT NULL,
  `row_status` tinyint(1) NOT NULL,
  `created_on` datetime NOT NULL,
  `updated_on` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `defects_status` */

insert  into `defects_status`(`id`,`defect_status_name`,`row_status`,`created_on`,`updated_on`) values 
(0,'Open',0,'2019-01-09 03:22:45','2019-01-09 10:00:19'),
(1,'In-progress',0,'2019-01-09 03:26:44','2019-01-09 10:00:19'),
(2,'Closed',0,'2019-01-09 03:21:40','2019-01-09 10:01:03'),
(3,'Re-open',0,'2019-01-09 03:20:34','2019-01-09 10:01:03');

/*Table structure for table `issues` */

DROP TABLE IF EXISTS `issues`;

CREATE TABLE `issues` (
  `id` int(6) unsigned NOT NULL AUTO_INCREMENT,
  `issue_id` varchar(255) NOT NULL,
  `issue_title` varchar(50) NOT NULL,
  `issue_description` varchar(200) NOT NULL,
  `testcase_type` int(11) NOT NULL,
  `related_testcase` varchar(50) NOT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `modified_on` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `severity` int(11) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `issue_status` varchar(20) NOT NULL,
  `issue_assign` varchar(20) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `login_type` tinyint(4) NOT NULL COMMENT '0-wokday,1-salesforce',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `issues` */

insert  into `issues`(`id`,`issue_id`,`issue_title`,`issue_description`,`testcase_type`,`related_testcase`,`created_by`,`created_on`,`modified_on`,`severity`,`priority`,`issue_status`,`issue_assign`,`status`,`login_type`) values 
(1,'Medko signup','signup','signup is not successful with the valid data',0,'Konnect_Self_service','w_admin','2019-01-25 07:11:09','0000-00-00 00:00:00',1,2,'0','w_admin',0,0),
(2,'Defect001','defect001','description',0,'Hire_Employee_Existing_PreHire','sf_admin','2019-01-30 09:22:44','2019-01-30 01:22:44',0,0,'0','sf_001',0,1),
(3,'medko login','login as patient','medko login',0,'W_user_manual','w_admin','2019-02-04 09:45:37','2019-02-04 01:45:37',1,2,'3','w_admin',0,0),
(4,'medko_login','login','login',0,'Medko_login','sf_admin','2019-01-28 09:13:29','2019-01-28 01:13:29',1,2,'1','sf_001',0,1),
(5,'D_002','login as patient','login is not successful',1,'MANTESTCASE002','sf_admin','2019-01-30 01:14:00','0000-00-00 00:00:00',2,1,'0','sf_admin',0,1),
(6,'W_user','user login','User login failed there is no validation message',0,'W_user','w_user','2019-02-04 09:46:59','2019-02-04 01:46:44',1,1,'2','w_user',2,0);

/*Table structure for table `lightening_setup` */

DROP TABLE IF EXISTS `lightening_setup`;

CREATE TABLE `lightening_setup` (
  `lightening` varchar(255) NOT NULL DEFAULT '',
  `user_id` varchar(30) NOT NULL,
  `url` varchar(255) NOT NULL,
  `status` tinyint(4) NOT NULL COMMENT '0-active,1-inactive,2-deleted',
  `time_created` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `lightening_setup` */

insert  into `lightening_setup`(`lightening`,`user_id`,`url`,`status`,`time_created`) values 
('SALES','','https://ap4.lightning.force.com/lightning/page/home',0,'2018-09-23 04:37:42'),
('MRKTNG','','https://mc.exacttarget.com/',2,'2019-02-01 02:36:42'),
('MRKTNG','','https://mc.exacttarget.com/',2,'2019-02-01 02:36:50'),
('MARKETING','','https://mc.exacttarget.com/',2,'2019-02-01 02:39:42'),
('MRKTNG','','https://mc.exacttarget.com/',2,'2019-02-01 02:40:38'),
('MRKTNG','','https://mc.exacttarget.com/',0,'2019-02-01 02:40:55'),
('MRKTING','','https://mc.exacttarget.com/',2,'2019-02-01 02:53:12'),
('jhhjhj','','http://salesfprce.com',2,'2019-02-01 02:53:22'),
('jghjh','','www.qwer.com',2,'2019-02-01 02:53:31');

/*Table structure for table `login_types` */

DROP TABLE IF EXISTS `login_types`;

CREATE TABLE `login_types` (
  `login_type_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `row_status` tinyint(4) NOT NULL DEFAULT 0,
  `created_on` datetime NOT NULL,
  `updated_on` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`login_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `login_types` */

insert  into `login_types`(`login_type_id`,`name`,`row_status`,`created_on`,`updated_on`) values 
(0,'Workday',0,'2018-12-19 18:28:03','2018-12-19 13:11:08'),
(1,'Salesforce',0,'2018-12-19 18:28:03','2018-12-19 13:11:16');

/*Table structure for table `object_properties_tbl` */

DROP TABLE IF EXISTS `object_properties_tbl`;

CREATE TABLE `object_properties_tbl` (
  `test_case_id` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `object_id` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `method` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `value` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`test_case_id`,`object_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `object_properties_tbl` */

insert  into `object_properties_tbl`(`test_case_id`,`object_id`,`method`,`value`) values 
('Login','password','XPATH','.//input[@type=\'password\']'),
('Login','signIn','XPATH','.//*[@data-automation-id=\'goButton\']'),
('Login','userName','XPATH','.//input[@aria-label=\'user name\']');

/*Table structure for table `priority` */

DROP TABLE IF EXISTS `priority`;

CREATE TABLE `priority` (
  `id` int(11) NOT NULL,
  `priority_name` varchar(255) NOT NULL,
  `row_status` tinyint(1) NOT NULL,
  `created_on` datetime NOT NULL,
  `updated_on` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `priority` */

insert  into `priority`(`id`,`priority_name`,`row_status`,`created_on`,`updated_on`) values 
(0,'Normal',0,'2019-01-09 03:17:33','2019-01-09 09:42:52'),
(1,'Low',0,'2019-01-09 03:32:43','2019-01-09 09:43:19'),
(2,'High',0,'2019-01-09 03:26:50','2019-01-09 09:43:44');

/*Table structure for table `severity` */

DROP TABLE IF EXISTS `severity`;

CREATE TABLE `severity` (
  `id` int(11) NOT NULL,
  `severity_name` varchar(255) NOT NULL,
  `row_status` tinyint(1) NOT NULL,
  `created_on` datetime NOT NULL,
  `updated_on` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `severity` */

insert  into `severity`(`id`,`severity_name`,`row_status`,`created_on`,`updated_on`) values 
(0,'Minor',0,'2019-01-09 03:14:28','2019-01-09 09:40:35'),
(1,'Major',0,'2019-01-09 02:15:24','2019-01-09 09:41:01'),
(2,'Critical',0,'2019-01-09 02:30:47','2019-01-09 09:41:23');

/*Table structure for table `sf_activity_tbl` */

DROP TABLE IF EXISTS `sf_activity_tbl`;

CREATE TABLE `sf_activity_tbl` (
  `activity_id` varchar(30) NOT NULL,
  `description` varchar(30) NOT NULL,
  `test_cycle_id` varchar(30) NOT NULL,
  `test_type` varchar(30) NOT NULL COMMENT 'automation,manual'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `sf_activity_tbl` */

insert  into `sf_activity_tbl`(`activity_id`,`description`,`test_cycle_id`,`test_type`) values 
('ACTIVITY001','ACTIVITY001','TESTCYCLE001','automation'),
('ACTIVITY001','ACTIVITY001','TESTCYCLE002','automation'),
('ACTIVITY001','ACTIVITY001','MANTESTCYCLE001','manual'),
('ACTIVITY001','ACTIVITY001','MANTESTCYCLE002','manual'),
('ACTIVITY002','ACTIVITY002','TESTCYCLE002','automation'),
('ACTIVITY002','ACTIVITY002','MANTESTCYCLE002','manual'),
('Medko_A1','medko login','Medko_A','automation'),
('Medko_A1','medko login','medko_M_login','manual'),
('Medco_signup','Enter correct info','Medko_A','automation'),
('Medco_signup','Enter correct info','medko_M_login','manual'),
('Salesforce_Create_Opportunity','Enter correct values','Medko_A','automation'),
('Salesforce_Create_Opportunity','Enter correct values','MANTESTCYCLE001','manual'),
('Medko_Ph_01','Medko splash  screen should  a','Medko_A','automation'),
('Medko_Ph_01','Medko splash  screen should  a','Medko_Ph_01','manual'),
('ACTIVITY003','ACTIVITY003','TESTCYCLE003','automation'),
('ACTIVITY003','ACTIVITY003','MANTESTCASE003','manual'),
('test','test','Medko_A','automation'),
('test','test','medko_M_login','manual'),
('A_test','test','Medko_A','automation'),
('A_test','test','medko_M_login','manual'),
('Salesforce_Create_Account','Enter correct info','TESTCYCLE001','automation'),
('Salesforce_Create_Account','Enter correct info','Medko_Ph_01','manual'),
('ACTIVITY007','Enter correct info','Konnect_Self_service','automation'),
('ACTIVITY007','Enter correct info','Testcase001','manual'),
('ACTIVITY00001','sould show the following','Konnect_Add_Expenses','automation'),
('ACTIVITY00001','sould show the following','Testcase01','manual');

/*Table structure for table `sf_categories` */

DROP TABLE IF EXISTS `sf_categories`;

CREATE TABLE `sf_categories` (
  `id` int(11) NOT NULL,
  `category_name` varchar(255) NOT NULL,
  `row_status` tinyint(4) NOT NULL,
  `created_on` datetime NOT NULL,
  `updated_on` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `sf_categories` */

insert  into `sf_categories`(`id`,`category_name`,`row_status`,`created_on`,`updated_on`) values 
(0,'Sales',0,'2019-01-09 02:11:28','2019-01-09 09:39:24'),
(1,'Marketing',0,'2019-01-09 04:08:18','2019-01-09 09:39:24');

/*Table structure for table `tenant_setup` */

DROP TABLE IF EXISTS `tenant_setup`;

CREATE TABLE `tenant_setup` (
  `tenant` varchar(50) NOT NULL DEFAULT '',
  `user_id` varchar(30) NOT NULL,
  `password` varchar(150) NOT NULL,
  `url` varchar(255) NOT NULL,
  `status` tinyint(4) NOT NULL COMMENT '0-active,1-inactive,2-deleted',
  `time_created` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tenant_setup` */

insert  into `tenant_setup`(`tenant`,`user_id`,`password`,`url`,`status`,`time_created`) values 
('fdfd','','','fdf',2,'2019-01-30 08:50:28'),
('GMS','','','https://impl.workday.com/wday/authgwy/osv/login.htmld',0,'2018-10-31 06:28:58'),
('konnect','','','www.konnect.com',2,'2019-01-24 02:46:03'),
('SBX','','','https://impl.workday.com/wday/authgwy/dpt2/login.htmld',0,'2018-10-31 06:28:35'),
('tenant','','','http://www.google.com',2,'2019-02-01 02:42:21'),
('Test','','','www.konnect.comn',2,'2019-01-25 05:52:25'),
('tenant','','','http://www.google.com',2,'2019-02-01 02:44:30'),
('tenant','','','http://www.google.com',2,'2019-02-01 02:45:19');

/*Table structure for table `test_case_defn` */

DROP TABLE IF EXISTS `test_case_defn`;

CREATE TABLE `test_case_defn` (
  `test_case_id` varchar(30) NOT NULL,
  `description` varchar(50) NOT NULL,
  `category` varchar(30) DEFAULT NULL,
  `status` tinyint(4) NOT NULL COMMENT '0-active,1-inactive,2-deleted',
  `template` text NOT NULL,
  PRIMARY KEY (`test_case_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `test_case_defn` */

insert  into `test_case_defn`(`test_case_id`,`description`,`category`,`status`,`template`) values 
('Konnect_Add_Expenses','Konnect add expenses','1',0,'Konnect_Add_Expenses.xls'),
('Konnect_Self_service','Konnect self service','2',0,'Konnect_Self_service_template.xls'),
('Medko_signup','signup as patient','0',0,'Medko_signup.xls'),
('Personal_Information_Change','Personal_Information_Change','2',0,'Personal_Information_Change.xls'),
('W_user','Login as workday admin','0',0,'W_user.xlsx');

/*Table structure for table `test_case_details` */

DROP TABLE IF EXISTS `test_case_details`;

CREATE TABLE `test_case_details` (
  `test_case_id` varchar(30) NOT NULL,
  `keyword` varchar(30) NOT NULL,
  `object_id` varchar(30) NOT NULL,
  `keyin_data` varchar(30) NOT NULL,
  `screenshot` varchar(255) DEFAULT NULL,
  `pass_log_msg` varchar(255) DEFAULT NULL,
  `fail_log_msg` varchar(255) DEFAULT NULL,
  `created_at` date NOT NULL,
  `modified_at` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `test_case_details` */

insert  into `test_case_details`(`test_case_id`,`keyword`,`object_id`,`keyin_data`,`screenshot`,`pass_log_msg`,`fail_log_msg`,`created_at`,`modified_at`) values 
('IU_login','click','region','','','','','2019-01-28','2019-01-28'),
('IU_login','click','region_option','getData=region','','','','2019-01-28','2019-01-28'),
('IU_login','delay','','1000','','','','2019-01-28','2019-01-28'),
('IU_login','click','school','','','','','2019-01-28','2019-01-28'),
('IU_login','click','school_option','getData=school','','','','2019-01-28','2019-01-28'),
('IU_login','delay','','1000','','','','2019-01-28','2019-01-28'),
('IU_login','click','Month','','','','','2019-01-28','2019-01-28'),
('IU_login','click','month_option','getData=month','','','','2019-01-28','2019-01-28'),
('IU_login','click','submit','','','','','2019-01-28','2019-01-28'),
('Medko_signup','click','region','','','','','2019-01-28','2019-01-28'),
('Medko_signup','click','region_option','getData=region','','','','2019-01-28','2019-01-28'),
('Medko_signup','delay','','1000','','','','2019-01-28','2019-01-28'),
('Medko_signup','click','school','','','','','2019-01-28','2019-01-28'),
('Medko_signup','click','school_option','getData=school','','','','2019-01-28','2019-01-28'),
('Medko_signup','delay','','1000','','','','2019-01-28','2019-01-28'),
('Medko_signup','click','Month','','','','','2019-01-28','2019-01-28'),
('Medko_signup','click','month_option','getData=month','','','','2019-01-28','2019-01-28'),
('Medko_signup','click','submit','','','','','2019-01-28','2019-01-28'),
('Medko_login_A','','','','madrid','Madrid Elementary School','noviembre - 2018','2019-01-28','2019-01-28'),
('Salesforce_Create_Opportunity','waitForPageToLoad','','','','','','2019-01-29','2019-01-29'),
('Salesforce_Create_Opportunity','enter_text','S_Username','getData=Username','','','','2019-01-29','2019-01-29'),
('Salesforce_Create_Opportunity','enter_text','S_Password','getData=Password','','','','2019-01-29','2019-01-29'),
('Salesforce_Create_Opportunity','click','S_SignIn','','','','','2019-01-29','2019-01-29'),
('Salesforce_Create_Opportunity','waitForPageToLoad','','','','','','2019-01-29','2019-01-29'),
('Salesforce_Create_Opportunity','click','Opportunity_Dropdown','','','','','2019-01-29','2019-01-29'),
('Salesforce_Create_Opportunity','click','New_Opportunity','','','','','2019-01-29','2019-01-29'),
('Salesforce_Create_Opportunity','enter_text','Amount','getData=Amount','','','','2019-01-29','2019-01-29'),
('Salesforce_Create_Opportunity','click','Private','','','','','2019-01-29','2019-01-29'),
('Salesforce_Create_Opportunity','pickFromDatePicker','Close_date','getData=Close date','','','','2019-01-29','2019-01-29'),
('Salesforce_Create_Opportunity','enter_text','Opportunity_Name','getData=Opportunity Name','','','','2019-01-29','2019-01-29'),
('Salesforce_Create_Opportunity','enter_text','Next_step','getData=Next step','','','','2019-01-29','2019-01-29'),
('Salesforce_Create_Opportunity','click','Stage_Dropdown','','','','','2019-01-29','2019-01-29'),
('Salesforce_Create_Opportunity','click','Stage','getData=Stage','','','','2019-01-29','2019-01-29'),
('Salesforce_Create_Opportunity','click','Type_dropdown','','','','','2019-01-29','2019-01-29'),
('Salesforce_Create_Opportunity','click','Type','getData=Type','','','','2019-01-29','2019-01-29'),
('Salesforce_Create_Opportunity','enter_text','Probability','getData=Probability','','','','2019-01-29','2019-01-29'),
('Salesforce_Create_Opportunity','click','Lead_source_dropdown','','','','','2019-01-29','2019-01-29'),
('Salesforce_Create_Opportunity','click','Lead_source','getData=Leadsource','','','','2019-01-29','2019-01-29'),
('Salesforce_Create_Opportunity','enter_text','Other_Number','getData=Type','','','','2019-01-29','2019-01-29'),
('Salesforce_Create_Opportunity','enter_text','Main_Competitor','getData=Type','','','','2019-01-29','2019-01-29'),
('Salesforce_Create_Opportunity','enter_text','Current_Generator','getData=Type','','','','2019-01-29','2019-01-29'),
('Salesforce_Create_Opportunity','click','Delivery_dropdown','','','','','2019-01-29','2019-01-29'),
('Salesforce_Create_Opportunity','click','Delivery','getData=Type','','','','2019-01-29','2019-01-29'),
('Salesforce_Create_Opportunity','enter_text','Tracking_Number','getData=Type','','','','2019-01-29','2019-01-29'),
('Salesforce_Create_Opportunity','enter_text','Description','getData=Type','','','','2019-01-29','2019-01-29'),
('Salesforce_Create_Opportunity','click','Save','','','','','2019-01-29','2019-01-29'),
('Konnect_Self_service','1','EMPP28','Kastech@123','Personal Work','CASUAL LEAVE','Full Day','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','waitForPageToLoad','','','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','enter_text','S_Username','getData=Username','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','enter_text','S_Password','getData=Password','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','click','S_SignIn','','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','waitForPageToLoad','','','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','click','Account_Dropdown','','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','click','Account_Dropdown','','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','click','Account_Dropdown','','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','click','New_Account','','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','click','Rating_Dropdown','','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','click','Rating','getData=Rating','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','enter_text','Account_name','getData=Account Name','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','enter_text','Phone','getData=Phone','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','enter_text','Fax','getData=Fax','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','enter_text','Account_number','getData=Account Number','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','enter_text','Website','getData=Website','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','enter_text','Account_site','getData=Account site','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','enter_text','Ticker_Symbol','getData=Ticker Symbol','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','click','Type_Dropdown','','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','click','Type','getData=Type','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','click','Ownership_Dropdown','','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','click','Ownership','getData=Ownership','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','click','Industry_Dropdown','','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','click','Industry','getData=Industry','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','enter_text','Employees','getData=Employees','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','enter_text','Annual_Revenue','getData=Annual Revenue','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','enter_text','SIC_Code','getData=Shipping Country','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','enter_text','Billing_Street','getData=Billing Street','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','enter_text','Billing_City','getData=Billing City','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','enter_text','Billing_State','getData=Billing State','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','enter_text','Billing_Zip','getData=Billing Zip','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','enter_text','Billing_Country','getData=Billing Country','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','enter_text','Shipping_street','getData=Shipping Street','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','enter_text','Shipping_City','getData=Shipping City','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','enter_text','Shipping_State','getData=Shipping State','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','enter_text','Shipping_Zip','getData=Shipping Zip','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','enter_text','Shipping_Country','getData=Shipping Country','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','click','Customer_Priority_Dropdown','','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','click','Customer_Priority','getData=Custom Priority','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','click','SLA_Dropdown','','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','click','SLA','getData=SLA','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','pickFromDatePicker','SLA_Expiry','getData=SLA expiry date','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','enter_text','SLA_Serial_Number','getData=SLA s.no','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','enter_text','Number_Location','getData=No of locations','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','click','Upsell_Opportunity_Dropdown','','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','click','Upsell_Opportunity','getData=Upsell ','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','click','Active_Dropdown','','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','click','Active','getData=Active','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','enter_text','Description','getData=Description','','','','2019-01-31','2019-01-31'),
('Salesforce_Create_Account','enter_text','Save','','','','','2019-01-31','2019-01-31'),
('Konnect_Add_Expenses','waitForPageToLoad','','','','','','2019-02-01','2019-02-01'),
('Konnect_Add_Expenses','enter_text','K_Username','getData=Username','','','','2019-02-01','2019-02-01'),
('Konnect_Add_Expenses','enter_text','K_Password','getData=Password','','','','2019-02-01','2019-02-01'),
('Konnect_Add_Expenses','click','K_SignIn','','','','','2019-02-01','2019-02-01'),
('Konnect_Add_Expenses','waitForPageToLoad','','','','','','2019-02-01','2019-02-01'),
('Konnect_Add_Expenses','click','K_Expenses','','','','','2019-02-01','2019-02-01'),
('Konnect_Add_Expenses','click','K_add','','','','','2019-02-01','2019-02-01'),
('Konnect_Add_Expenses','enter_text','K_Expenses_name','getData=Expenses_name','','','','2019-02-01','2019-02-01'),
('Konnect_Add_Expenses','click','K_Reimbursable','','','','','2019-02-01','2019-02-01'),
('Konnect_Add_Expenses','waitForXPath','K_Category_dropdown','','','','','2019-02-01','2019-02-01'),
('Konnect_Add_Expenses','click','K_Category_dropdown','','','','','2019-02-01','2019-02-01'),
('Konnect_Add_Expenses','click','K_Category','getData=Category','','','','2019-02-01','2019-02-01'),
('Konnect_Add_Expenses','click','K_Project_dropdown','','','','','2019-02-01','2019-02-01'),
('Konnect_Add_Expenses','click','K_Project','getData=Project','','','','2019-02-01','2019-02-01'),
('Konnect_Add_Expenses','pickPreviousDayFromDatePicker','K_expenses_date','getData=date','','','','2019-02-01','2019-02-01'),
('Konnect_Add_Expenses','enter_text','K_Amount','getData=Amount','','','','2019-02-01','2019-02-01'),
('Konnect_Add_Expenses','click','K_Payment_mode_dropdown','','','','','2019-02-01','2019-02-01'),
('Konnect_Add_Expenses','click','K_Payment_mode','getData=Payment_Mode','','','','2019-02-01','2019-02-01'),
('Konnect_Add_Expenses','click','K_save','','','','','2019-02-01','2019-02-01'),
('Konnect_Add_Expenses','click','K_expense_view','','','','','2019-02-01','2019-02-01'),
('Konnect_Add_Expenses','click','K_submit','','','','','2019-02-01','2019-02-01'),
('Konnect_Add_Expenses','click','K_approve_yes','','','','','2019-02-01','2019-02-01'),
('Konnect_Add_Expenses','click','K_user_Logout','','','','','2019-02-01','2019-02-01'),
('Konnect_Add_Expenses','click','K_Logout','','','','','2019-02-01','2019-02-01'),
('Konnect_Add_Expenses','enter_text','K_Username','getData=M_name','','','','2019-02-01','2019-02-01'),
('Konnect_Add_Expenses','enter_text','K_Password','getData=M_password','','','','2019-02-01','2019-02-01'),
('Konnect_Add_Expenses','click','K_SignIn','','','','','2019-02-01','2019-02-01'),
('Konnect_Add_Expenses','waitForPageToLoad','','','','','','2019-02-01','2019-02-01'),
('Konnect_Add_Expenses','click','K_Expenses','','','','','2019-02-01','2019-02-01'),
('Konnect_Add_Expenses','click','K_My_employees','','','','','2019-02-01','2019-02-01'),
('Konnect_Add_Expenses','click','K_employee_expenses','','','','','2019-02-01','2019-02-01'),
('Konnect_Add_Expenses','click','K_Manager_approve','','','','','2019-02-01','2019-02-01'),
('Konnect_Add_Expenses','click','K_approve_yes','','','','','2019-02-01','2019-02-01'),
('Konnect_Add_Expenses','click','K_user_Logout','','','','','2019-02-01','2019-02-01'),
('Konnect_Add_Expenses','click','K_Logout','','','','','2019-02-01','2019-02-01'),
('W_user','enter_text','email','getData=Username','','','','2019-02-04','2019-02-04'),
('W_user','enter_text','Password','getData=Password','','','','2019-02-04','2019-02-04'),
('W_user','click','Login','','','','','2019-02-04','2019-02-04');

/*Table structure for table `test_case_lines` */

DROP TABLE IF EXISTS `test_case_lines`;

CREATE TABLE `test_case_lines` (
  `test_case_id` varchar(30) NOT NULL,
  `line_id` int(4) NOT NULL,
  `keyword` varchar(30) NOT NULL,
  `object_id` varchar(30) NOT NULL,
  `key_in_data` varchar(30) NOT NULL,
  PRIMARY KEY (`test_case_id`,`line_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `test_case_lines` */

/*Table structure for table `test_case_man_defn` */

DROP TABLE IF EXISTS `test_case_man_defn`;

CREATE TABLE `test_case_man_defn` (
  `test_case_id` varchar(30) NOT NULL,
  `description` text NOT NULL,
  `category` varchar(30) NOT NULL,
  `instructions` text NOT NULL,
  `status` tinyint(4) NOT NULL,
  PRIMARY KEY (`test_case_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `test_case_man_defn` */

insert  into `test_case_man_defn`(`test_case_id`,`description`,`category`,`instructions`,`status`) values 
('MANUAL001','MANUAL001','0','MANUAL001',0),
('MANUAL002','MANUAL002','3','MANUAL002',0),
('Medko_signup','should successfully signup as patient','0','1.enter mobile number\r\n2.enter password\r\n3.click on submit',0),
('W_user_manual','login as a workday admin','0','1.Enter username\r\n2.Enter password\r\n3.Click on signin',0);

/*Table structure for table `test_case_status` */

DROP TABLE IF EXISTS `test_case_status`;

CREATE TABLE `test_case_status` (
  `test_cycle_id` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `run_id` int(11) NOT NULL,
  `activity_id` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `test_case_id` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `status` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `test_data` longtext COLLATE utf8_unicode_ci DEFAULT NULL,
  `test_type` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '''manual'',''automation''',
  `comments` text COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`activity_id`,`test_cycle_id`,`test_case_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `test_case_status` */

insert  into `test_case_status`(`test_cycle_id`,`run_id`,`activity_id`,`test_case_id`,`status`,`test_data`,`test_type`,`comments`) values 
('MANTESTCYCLE001',0,'ACTIVITY001','MANUAL001','Not Started',NULL,'manual',''),
('MANTESTCYCLE001',0,'ACTIVITY001','MANUAL002','Not Started',NULL,'manual',''),
('MANTESTCYCLE002',0,'ACTIVITY001','MANUAL002','Not Started',NULL,'manual',''),
('TESTCYCLE001',1,'ACTIVITY001','Konnect_Add_Expenses','Not Started','Konnect_Add_Expenses.xls','automation',''),
('TESTCYCLE001',1,'ACTIVITY001','Konnect_Self_service','Not Started','Konnect_Add_Expenses.xls','automation',''),
('TESTCYCLE002',1,'ACTIVITY001','Konnect_Self_service','Not Started','Konnect_Add_Expenses.xls','automation',''),
('A_medko_signup',5,'Medko_signup','Medko_signup','Not Started','Medko_signup.xls','automation',''),
('m_medko_signup',0,'Medko_signup','Medko_signup','Success',NULL,'manual','GMS'),
('W_admin_login',8,'W_admin_user_login','W_user','Not Started','W_user.xlsx','automation',''),
('W_admin_login_manual',8,'W_admin_user_login','W_user_manual','Success',NULL,'manual','Logged in successfully');

/*Table structure for table `test_cycle_case_defn` */

DROP TABLE IF EXISTS `test_cycle_case_defn`;

CREATE TABLE `test_cycle_case_defn` (
  `test_cycle_id` varchar(30) NOT NULL,
  `test_case_id` varchar(30) NOT NULL,
  PRIMARY KEY (`test_cycle_id`,`test_case_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `test_cycle_case_defn` */

insert  into `test_cycle_case_defn`(`test_cycle_id`,`test_case_id`) values 
('A_medko_signup','Medko_signup'),
('Medko_signup','Medko_signup'),
('test','Konnect_Self_service'),
('TESTCYCLE001','Konnect_Add_Expenses'),
('TESTCYCLE001','Konnect_Self_service'),
('TESTCYCLE002','Konnect_Self_service'),
('W_admin_login','W_user');

/*Table structure for table `test_cycle_case_man_defn` */

DROP TABLE IF EXISTS `test_cycle_case_man_defn`;

CREATE TABLE `test_cycle_case_man_defn` (
  `test_cycle_id` varchar(30) NOT NULL,
  `test_case_id` varchar(30) NOT NULL,
  `assignee` varchar(30) NOT NULL,
  PRIMARY KEY (`test_cycle_id`,`test_case_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `test_cycle_case_man_defn` */

insert  into `test_cycle_case_man_defn`(`test_cycle_id`,`test_case_id`,`assignee`) values 
('MANTESTCYCLE001','MANUAL001','w_admin'),
('MANTESTCYCLE001','MANUAL002','w_admin'),
('MANTESTCYCLE002','MANUAL002','w_user'),
('m_medko_signup','Medko_signup','w_admin'),
('W_admin_login_manual','W_user_manual','w_user');

/*Table structure for table `test_cycle_defn` */

DROP TABLE IF EXISTS `test_cycle_defn`;

CREATE TABLE `test_cycle_defn` (
  `test_cycle_id` varchar(30) NOT NULL,
  `description` varchar(50) NOT NULL,
  `test_case_id` varchar(30) NOT NULL,
  `status` tinyint(4) NOT NULL COMMENT '0-active,1-inactive,2-deleted',
  `time_created` datetime NOT NULL,
  PRIMARY KEY (`test_cycle_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `test_cycle_defn` */

insert  into `test_cycle_defn`(`test_cycle_id`,`description`,`test_case_id`,`status`,`time_created`) values 
('A_medko_signup','signup as pateint','',0,'2019-01-25 06:02:42'),
('Medko_signup','signup as patient','',0,'2019-01-29 00:54:40'),
('test','Signed up successfully ','',0,'2019-01-25 07:35:53'),
('TESTCYCLE001','TESTCYCLE001','',0,'2019-01-25 05:18:30'),
('TESTCYCLE002','TESTCYCLE002','',0,'2019-01-25 05:18:47'),
('W_admin_login','login as workday admin','',0,'2019-02-04 01:17:27');

/*Table structure for table `test_cycle_man_defn` */

DROP TABLE IF EXISTS `test_cycle_man_defn`;

CREATE TABLE `test_cycle_man_defn` (
  `test_cycle_id` varchar(30) NOT NULL,
  `description` varchar(30) NOT NULL,
  `test_case_id` varchar(30) NOT NULL,
  `status` tinyint(4) NOT NULL COMMENT '0-active,1-inactive,2-deleted',
  `assignee` varchar(30) NOT NULL,
  PRIMARY KEY (`test_cycle_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `test_cycle_man_defn` */

insert  into `test_cycle_man_defn`(`test_cycle_id`,`description`,`test_case_id`,`status`,`assignee`) values 
('MANTESTCYCLE001','MANTESTCYCLE001','',0,''),
('MANTESTCYCLE002','MANTESTCYCLE002','',0,''),
('m_medko_signup','signup as patient','',0,''),
('W_admin_login_manual','login as admin ','',0,'');

/*Table structure for table `test_cycle_run` */

DROP TABLE IF EXISTS `test_cycle_run`;

CREATE TABLE `test_cycle_run` (
  `run_id` int(30) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(30) NOT NULL,
  `activity_id` text NOT NULL,
  `test_cycle_id` text NOT NULL,
  `proxy` varchar(30) NOT NULL,
  `tenant` varchar(10) NOT NULL,
  `mode` varchar(30) NOT NULL,
  `run_dttm` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`run_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `test_cycle_run` */

insert  into `test_cycle_run`(`run_id`,`user_id`,`activity_id`,`test_cycle_id`,`proxy`,`tenant`,`mode`,`run_dttm`) values 
(1,'w_admin','ACTIVITY001','','','GMS','','2019-01-25 05:23:23'),
(2,'w_admin','Medko_signup','','','GMS','','2019-01-25 06:04:42'),
(3,'w_admin','Medko_signup','','','GMS','','2019-01-25 06:09:47'),
(4,'w_admin','Medko_signup','','','GMS','','2019-01-25 06:17:48'),
(5,'w_admin','Medko_signup','','','SBX','','2019-01-27 22:36:49'),
(6,'w_admin','W_admin_user_login','','','GMS','','2019-02-04 01:24:51'),
(7,'w_admin','W_admin_user_login','','','SBX','','2019-02-04 01:36:53'),
(8,'w_admin','W_admin_user_login','','','SBX','','2019-02-04 01:37:49');

/*Table structure for table `test_sf_case_defn` */

DROP TABLE IF EXISTS `test_sf_case_defn`;

CREATE TABLE `test_sf_case_defn` (
  `test_case_id` varchar(30) NOT NULL,
  `description` varchar(50) NOT NULL,
  `category` varchar(30) NOT NULL,
  `status` tinyint(4) NOT NULL COMMENT '0-active,1-inactive,2-deleted',
  `template` text NOT NULL,
  PRIMARY KEY (`test_case_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `test_sf_case_defn` */

insert  into `test_sf_case_defn`(`test_case_id`,`description`,`category`,`status`,`template`) values 
('Hire_Employee_Existing_PreHire','Hire_Employee_Existing_PreHire','1',0,'HIRE_EMPL.xlsx'),
('IU_login','IU_login','0',0,'IU_login.xls'),
('Konnect_Add_Expenses','Konnect_Add_Expenses','1',0,'Konnect_Add_Expenses.xlsx'),
('Konnect_Self_service','Konnect_Self_service','1',0,'Konnect_Self_service.xlsx'),
('Medko_login','login as patient','1',0,'Medko_login.xlsx'),
('Medko_login_A','login as pharmacy','0',0,'Medko_login_A.xlsx'),
('Personal_Information_Change','Personal_Information_Change','0',0,'Personal_Information_Change.xls'),
('Salesforce_Create_Account','Enter User Name','0',0,'Salesforce_Create_Account.xlsx'),
('Salesforce_Create_Opportunity','Salesforce_Create_Opportunity','0',0,'Salesforce_Create_Opportunity.xlsx');

/*Table structure for table `test_sf_case_lines` */

DROP TABLE IF EXISTS `test_sf_case_lines`;

CREATE TABLE `test_sf_case_lines` (
  `test_case_id` varchar(30) NOT NULL,
  `line_id` int(4) NOT NULL,
  `keyword` varchar(30) NOT NULL,
  `object_id` varchar(30) NOT NULL,
  `key_in_data` varchar(30) NOT NULL,
  PRIMARY KEY (`line_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `test_sf_case_lines` */

/*Table structure for table `test_sf_case_man_defn` */

DROP TABLE IF EXISTS `test_sf_case_man_defn`;

CREATE TABLE `test_sf_case_man_defn` (
  `test_case_id` varchar(30) NOT NULL,
  `description` varchar(30) NOT NULL,
  `category` varchar(30) NOT NULL,
  `instructions` varchar(2000) NOT NULL,
  `status` tinyint(4) NOT NULL COMMENT '0-active,1-inactive,2-deleted'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `test_sf_case_man_defn` */

insert  into `test_sf_case_man_defn`(`test_case_id`,`description`,`category`,`instructions`,`status`) values 
('MANTESTCASE001','MANTESTCASE001','0','MANTESTCASE001',0),
('MANTESTCASE002','MANTESTCASE002','0','MANTESTCASE002',0),
('Medko_login','Login with valid credentials','1','1.enter mobile number\r\n2.enter password\r\n3. Click on submit',0),
('Medco_signup','Enter correct info','0','Enter region\r\nEnter school\r\nEnter month',0),
('Salesforce_Create_Opportunity','Enter correct values','0','Enter Amount\r\nEnter Stage\r\nEnter Closedate',0),
('Medko_Ph_01','Medko splash \r\nscreen should o','1','Click on Medko \r\n     app',0),
('MANTESTCASE003','MANTESTCASE003','0','MANTESTCASE003',0),
('Salesforce_Create_Account','Enter User Name','0','Enter User Name',0),
('Konnect_Self_service','details are correct then login','1','1)Valid Username\r\n2)Valid password',0),
('Konnect_Add_Expenses','valid credentials should be gi','1','clicking on login button should open login page',0);

/*Table structure for table `test_sf_case_status` */

DROP TABLE IF EXISTS `test_sf_case_status`;

CREATE TABLE `test_sf_case_status` (
  `activity_id` varchar(30) NOT NULL,
  `test_cycle_id` varchar(30) DEFAULT NULL,
  `run_id` int(11) NOT NULL,
  `test_case_id` varchar(90) DEFAULT NULL,
  `test_data` longtext NOT NULL,
  `test_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '''manual'',''automation''',
  `comments` text NOT NULL,
  `status` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `test_sf_case_status` */

insert  into `test_sf_case_status`(`activity_id`,`test_cycle_id`,`run_id`,`test_case_id`,`test_data`,`test_type`,`comments`,`status`) values 
('ACTIVITY001','TESTCYCLE001',1,'Hire_Employee_Existing_PreHire','HIRE_EMPL.xlsx','automation','','Not Started'),
('ACTIVITY001','TESTCYCLE002',1,'Personal_Information_Change','HIRE_EMPL.xlsx','automation','','Not Started'),
('ACTIVITY001','TESTCYCLE002',1,'Hire_Employee_Existing_PreHire','HIRE_EMPL.xlsx','automation','','Not Started'),
('Medko_A1','Medko_A',9,'Medko_login','Medko_login.xlsx','automation','','Not Started'),
('test','Medko_A',8,'Medko_login','Medko_login.xls','automation','','Not Started'),
('Medko_Ph_01','Medko_A',10,'Medko_login','Medko_login.xlsx','automation','','Not Started'),
('ACTIVITY002','TESTCYCLE002',0,'Hire_Employee_Existing_PreHire','HIRE_EMPL (1).xlsx','automation','','Not Started'),
('ACTIVITY002','TESTCYCLE002',0,'Personal_Information_Change','HIRE_EMPL (1).xlsx','automation','','Not Started'),
('ACTIVITY002','MANTESTCYCLE002',0,'MANTESTCASE001','','manual','','Not Started'),
('ACTIVITY002','MANTESTCYCLE002',0,'MANTESTCASE002','','manual','','Not Started'),
('ACTIVITY003','MANTESTCASE003',0,'MANTESTCASE003','','manual','','Not Started'),
('Salesforce_Create_Opportunity','MANTESTCYCLE001',0,'MANTESTCASE001','','manual','','Not Started'),
('Salesforce_Create_Account','Medko_Ph_01',0,'Medko_Ph_01','','manual','','Not Started'),
('ACTIVITY007','Konnect_Self_service',13,'Konnect_Self_service','Konnect_Self_service.xlsx','automation','','Not Started'),
('ACTIVITY007','Testcase001',13,'Konnect_Self_service','','manual','','Not Started'),
('test','medko_M_login',0,'Medko_login','','manual','','Not Started'),
('ACTIVITY00001','Konnect_Add_Expenses',14,'Konnect_Add_Expenses','Konnect_Add_Expenses.xlsx','automation','','Not Started'),
('ACTIVITY00001','Testcase01',0,'Konnect_Add_Expenses','','manual','','Not Started');

/*Table structure for table `test_sf_cycle_case_defn` */

DROP TABLE IF EXISTS `test_sf_cycle_case_defn`;

CREATE TABLE `test_sf_cycle_case_defn` (
  `test_cycle_id` varchar(30) NOT NULL,
  `test_case_id` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `test_sf_cycle_case_defn` */

insert  into `test_sf_cycle_case_defn`(`test_cycle_id`,`test_case_id`) values 
('TESTCYCLE002','Hire_Employee_Existing_PreHire'),
('TESTCYCLE002','Personal_Information_Change'),
('TESTCYCLE001','Hire_Employee_Existing_PreHire'),
('Medko_A','Medko_login'),
('TESTCYCLE003','Hire_Employee_Existing_PreHire'),
('Konnect_Self_service','Konnect_Self_service'),
('Konnect_Add_Expenses','Konnect_Add_Expenses');

/*Table structure for table `test_sf_cycle_case_man_defn` */

DROP TABLE IF EXISTS `test_sf_cycle_case_man_defn`;

CREATE TABLE `test_sf_cycle_case_man_defn` (
  `test_cycle_id` varchar(30) NOT NULL,
  `test_case_id` varchar(30) NOT NULL,
  `assignee` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `test_sf_cycle_case_man_defn` */

insert  into `test_sf_cycle_case_man_defn`(`test_cycle_id`,`test_case_id`,`assignee`) values 
('MANTESTCYCLE001','MANTESTCASE001','sf_admin'),
('MANTESTCYCLE002','MANTESTCASE001','sf_admin'),
('MANTESTCYCLE002','MANTESTCASE002','sf_user'),
('medko_M_login','Medko_login','sf_admin'),
('Medko_Ph_01','Medko_Ph_01','sf_admin'),
('MANTESTCASE003','MANTESTCASE003','sf_admin'),
('Testcase001','Konnect_Self_service','sf_user'),
('Testcase01','Konnect_Add_Expenses','sf_005');

/*Table structure for table `test_sf_cycle_defn` */

DROP TABLE IF EXISTS `test_sf_cycle_defn`;

CREATE TABLE `test_sf_cycle_defn` (
  `test_cycle_id` varchar(30) NOT NULL,
  `description` varchar(50) NOT NULL,
  `test_case_id` varchar(30) NOT NULL,
  `status` tinyint(4) NOT NULL COMMENT '0-active,1-inactive,2-deleted',
  `time_created` datetime NOT NULL,
  PRIMARY KEY (`test_cycle_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `test_sf_cycle_defn` */

insert  into `test_sf_cycle_defn`(`test_cycle_id`,`description`,`test_case_id`,`status`,`time_created`) values 
('IU_login','IU_login','',0,'2019-01-28 02:06:05'),
('Konnect_Add_Expenses','konnect_Add_Expenses','',0,'2019-02-01 03:15:53'),
('Konnect_Self_service','Konnect_Self_service','',0,'2019-01-31 02:57:22'),
('Medko_A','medko login ','',0,'2019-01-27 22:37:08'),
('TESTCYCLE001','TESTCYCLE001','',0,'2019-01-25 05:46:23'),
('TESTCYCLE002','TESTCYCLE002','',0,'2019-01-25 05:46:36'),
('TESTCYCLE003','TESTCYCLE003','',0,'2019-01-28 04:17:23');

/*Table structure for table `test_sf_cycle_man_defn` */

DROP TABLE IF EXISTS `test_sf_cycle_man_defn`;

CREATE TABLE `test_sf_cycle_man_defn` (
  `test_cycle_id` varchar(30) NOT NULL,
  `description` varchar(30) NOT NULL,
  `test_case_id` varchar(30) NOT NULL,
  `status` tinyint(4) NOT NULL COMMENT '0-active,1-inactive,2-deleted',
  `assignee` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `test_sf_cycle_man_defn` */

insert  into `test_sf_cycle_man_defn`(`test_cycle_id`,`description`,`test_case_id`,`status`,`assignee`) values 
('MANTESTCYCLE001','MANTESTCYCLE001','',0,''),
('MANTESTCYCLE002','MANTESTCYCLE002','',0,''),
('medko_M_login','medko patient login','',0,''),
('Medko_Ph_01','Medko splash \r\nscreen should\r\n','',0,''),
('MANTESTCASE003','MANTESTCASE003','',0,''),
('Testcase001','valid details should login','',0,''),
('Testcase01','valid credentials should be gi','',0,'');

/*Table structure for table `test_sf_cycle_run` */

DROP TABLE IF EXISTS `test_sf_cycle_run`;

CREATE TABLE `test_sf_cycle_run` (
  `run_id` int(30) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(30) NOT NULL,
  `activity_id` text NOT NULL,
  `test_cycle_id` text NOT NULL,
  `proxy` varchar(30) NOT NULL,
  `lightening` varchar(10) NOT NULL,
  `mode` varchar(30) NOT NULL,
  `run_dttm` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`run_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

/*Data for the table `test_sf_cycle_run` */

insert  into `test_sf_cycle_run`(`run_id`,`user_id`,`activity_id`,`test_cycle_id`,`proxy`,`lightening`,`mode`,`run_dttm`) values 
(1,'sf_admin','ACTIVITY001','','','MRKTNG','','2019-01-25 05:48:06'),
(2,'sf_admin','Medko_A1','','','MRKTNG','','2019-01-27 22:40:22'),
(3,'sf_admin','Medko_A1','','','MRKTNG','','2019-01-27 22:55:52'),
(4,'sf_admin','Medko_A1','','','SALES','','2019-01-27 22:56:23'),
(5,'sf_admin','ACTIVITY001','','','MRKTNG','','2019-01-28 05:35:14'),
(6,'sf_admin','ACTIVITY001','','','SALES','','2019-01-28 05:35:31'),
(7,'sf_admin','ACTIVITY001','','','MRKTNG','','2019-01-28 22:44:32'),
(8,'sf_admin','test','','','MRKTNG','','2019-01-29 00:03:48'),
(9,'sf_admin','Medko_A1','','','MRKTNG','','2019-01-29 00:18:42'),
(10,'sf_admin','Medko_Ph_01','','','MRKTNG','','2019-01-29 00:59:02'),
(11,'sf_admin','test','','','MRKTNG','','2019-01-29 02:16:51'),
(12,'sf_admin','ACTIVITY007','','','MRKTNG','','2019-01-31 03:00:17'),
(13,'sf_admin','ACTIVITY007','','','MRKTNG','','2019-01-31 03:03:06'),
(14,'sf_admin','ACTIVITY00001','','','MRKTNG','','2019-02-01 03:17:48');

/*Table structure for table `testcase_type` */

DROP TABLE IF EXISTS `testcase_type`;

CREATE TABLE `testcase_type` (
  `id` int(11) NOT NULL,
  `testcase_name` varchar(255) NOT NULL,
  `row_status` tinyint(1) NOT NULL,
  `created_on` datetime NOT NULL,
  `updated_on` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `testcase_type` */

insert  into `testcase_type`(`id`,`testcase_name`,`row_status`,`created_on`,`updated_on`) values 
(0,'Automation',0,'2019-01-09 03:15:30','2019-01-09 10:02:47'),
(1,'Manual',0,'2019-01-09 03:32:41','2019-01-09 10:02:47');

/*Table structure for table `user_roles` */

DROP TABLE IF EXISTS `user_roles`;

CREATE TABLE `user_roles` (
  `role_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `row_status` tinyint(4) NOT NULL DEFAULT 0,
  `created_on` datetime NOT NULL,
  `updated_on` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user_roles` */

insert  into `user_roles`(`role_id`,`name`,`row_status`,`created_on`,`updated_on`) values 
(0,'Admin',0,'2018-12-19 18:28:03','2019-01-09 07:31:12'),
(1,'User',0,'2018-12-19 18:28:03','2019-01-09 07:31:19');

/*Table structure for table `user_setup` */

DROP TABLE IF EXISTS `user_setup`;

CREATE TABLE `user_setup` (
  `user_id` varchar(30) NOT NULL DEFAULT '',
  `name` varchar(30) NOT NULL,
  `password` varchar(150) NOT NULL,
  `role` varchar(10) NOT NULL COMMENT '0-admin,1-user',
  `status` tinyint(4) NOT NULL COMMENT '0-active,1-inactive,2-deleted',
  `login_type` tinyint(4) NOT NULL COMMENT '0-workday,1-salesforce',
  `time_created` datetime NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`user_id`,`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=latin1;

/*Data for the table `user_setup` */

insert  into `user_setup`(`user_id`,`name`,`password`,`role`,`status`,`login_type`,`time_created`,`id`) values 
('kas011','sivak','e10adc3949ba59abbe56e057f20f883e','0',0,0,'2019-01-31 06:54:04',56),
('KAS097','yamika','81dc9bdb52d04dc20036dbd8313ed055','1',0,0,'2019-02-03 21:50:04',59),
('KAS099','yamika','81dc9bdb52d04dc20036dbd8313ed055','0',0,1,'2019-02-03 22:40:49',61),
('sf_001','Anantha','e10adc3949ba59abbe56e057f20f883e','1',0,1,'2019-01-27 20:58:59',35),
('sf_005','user','e10adc3949ba59abbe56e057f20f883e','1',0,1,'2019-01-31 05:02:02',55),
('sf_admin','sf_admin','e10adc3949ba59abbe56e057f20f883e','0',0,1,'2018-07-24 00:41:37',7),
('w_admin','w_admin','e10adc3949ba59abbe56e057f20f883e','0',0,0,'2018-07-14 16:59:02',13),
('w_user','w_user','e10adc3949ba59abbe56e057f20f883e','1',0,0,'2018-09-26 03:18:41',14);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
