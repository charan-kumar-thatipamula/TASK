-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Feb 24, 2019 at 06:04 AM
-- Server version: 10.2.21-MariaDB-cll-lve
-- PHP Version: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kappsonl_demo_workday`
--

-- --------------------------------------------------------

--
-- Table structure for table `activity_tbl`
--

CREATE TABLE `activity_tbl` (
  `activity_id` varchar(30) NOT NULL,
  `description` varchar(50) NOT NULL,
  `test_cycle_id` varchar(30) NOT NULL,
  `test_type` varchar(30) NOT NULL COMMENT 'automation,manual'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `activity_tbl`
--

INSERT INTO `activity_tbl` (`activity_id`, `description`, `test_cycle_id`, `test_type`) VALUES
('K_Mohid_activity', 'test', 'Personal_Information', 'automation'),
('K_Mohid_activity', 'test', 'Personal_Information_Change', 'automation'),
('K_Mohid_activity', 'test', 'Update_E_Details', 'manual'),
('Personal_Information_Change', 'Personal_Information_Change', 'Personal_Information_Change', 'automation'),
('test', '123', 'Update_Details', 'automation'),
('test', '123', 'Update_E_Details', 'manual'),
('test123', 'tesr23', 'Update_Details', 'automation'),
('test123', 'tesr23', 'Update_E_Details', 'manual'),
('Update_Employee_details', 'Update employee job details and add benefits', 'Update_personal_details', 'automation'),
('Update_Employee_details', 'Update employee job details and add benefits', 'Update_Personal_details_M', 'manual'),
('Update_Emp_details', 'Update details of employee', 'Update_Details', 'automation'),
('Update_Emp_details', 'Update details of employee', 'Update_E_Details', 'manual');

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `category_name` varchar(255) NOT NULL,
  `row_status` tinyint(1) NOT NULL DEFAULT 0,
  `created_on` datetime NOT NULL,
  `updated_on` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `category_name`, `row_status`, `created_on`, `updated_on`) VALUES
(0, 'Personal Data', 0, '2018-12-27 04:13:23', '2018-12-27 08:00:48'),
(1, 'Staffing', 0, '2018-12-27 05:22:27', '2018-12-27 07:50:42'),
(2, 'Compensation', 0, '2018-12-27 08:20:28', '2018-12-27 07:50:47'),
(3, 'Benefits', 0, '2018-12-27 08:28:28', '2018-12-27 07:50:52'),
(4, 'Payroll', 0, '2018-12-27 04:17:20', '2018-12-27 07:50:56'),
(5, 'Integration', 0, '2018-12-27 07:22:23', '2018-12-27 07:51:01');

-- --------------------------------------------------------

--
-- Table structure for table `defects_status`
--

CREATE TABLE `defects_status` (
  `id` int(11) NOT NULL,
  `defect_status_name` varchar(255) NOT NULL,
  `row_status` tinyint(1) NOT NULL,
  `created_on` datetime NOT NULL,
  `updated_on` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `defects_status`
--

INSERT INTO `defects_status` (`id`, `defect_status_name`, `row_status`, `created_on`, `updated_on`) VALUES
(0, 'Open', 0, '2019-01-09 03:22:45', '2019-01-09 10:00:19'),
(1, 'In-progress', 0, '2019-01-09 03:26:44', '2019-01-09 10:00:19'),
(2, 'Closed', 0, '2019-01-09 03:21:40', '2019-01-09 10:01:03'),
(3, 'Re-open', 0, '2019-01-09 03:20:34', '2019-01-09 10:01:03');

-- --------------------------------------------------------

--
-- Table structure for table `issues`
--

CREATE TABLE `issues` (
  `id` int(6) UNSIGNED NOT NULL,
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
  `login_type` tinyint(4) NOT NULL COMMENT '0-wokday,1-salesforce'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `issues`
--

INSERT INTO `issues` (`id`, `issue_id`, `issue_title`, `issue_description`, `testcase_type`, `related_testcase`, `created_by`, `created_on`, `modified_on`, `severity`, `priority`, `issue_status`, `issue_assign`, `status`, `login_type`) VALUES
(1, 'emp_details', 'employee details', 'Employee details should be updated \r\n\r\nIssue was resolved by fixing the code.', 0, 'M_Update_Employee_Details', 'w_admin', '2019-02-21 17:42:51', '2019-02-21 09:42:51', 0, 1, '2', 'w_admin', 0, 0),
(2, 'Issue_34', 'Task_w_34', '', 0, 'Task_W_34', 'sf_user', '2019-02-20 07:58:42', '2019-02-19 23:58:42', 0, 1, '0', 'sf_admin', 0, 1),
(3, 'issue_001', 'issue', 'issue description', 0, 'Task_W_31', 'sf_admin', '2019-02-18 12:03:02', '0000-00-00 00:00:00', 0, 1, '0', 'garima', 2, 1),
(4, '434', 'Not able to login to the system with user credenti', 'fasf', 1, 'M_Update_Employee_Details', 'w_admin', '2019-02-21 09:44:06', '0000-00-00 00:00:00', 2, 2, '0', 'KAS097', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `lightening_setup`
--

CREATE TABLE `lightening_setup` (
  `lightening` varchar(255) NOT NULL DEFAULT '',
  `user_id` varchar(30) NOT NULL,
  `url` varchar(255) NOT NULL,
  `status` tinyint(4) NOT NULL COMMENT '0-active,1-inactive,2-deleted',
  `time_created` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lightening_setup`
--

INSERT INTO `lightening_setup` (`lightening`, `user_id`, `url`, `status`, `time_created`) VALUES
('SALES', '', 'https://ap4.lightning.force.com/lightning/page/home', 0, '2018-09-23 04:37:42'),
('MRKTNG', '', 'https://mc.exacttarget.com/', 2, '2019-02-01 02:36:42'),
('MRKTNG', '', 'https://mc.exacttarget.com/', 2, '2019-02-01 02:36:50'),
('MARKETING', '', 'https://mc.exacttarget.com/', 2, '2019-02-01 02:39:42'),
('MRKTNG', '', 'https://mc.exacttarget.com/', 2, '2019-02-01 02:40:38'),
('MRKTNG', '', 'https://mc.exacttarget.com/', 0, '2019-02-01 02:40:55'),
('MRKTING', '', 'https://mc.exacttarget.com/', 2, '2019-02-01 02:53:12'),
('jhhjhj', '', 'http://salesfprce.com', 2, '2019-02-01 02:53:22'),
('jghjh', '', 'www.qwer.com', 2, '2019-02-01 02:53:31'),
('abc', '', 'a', 2, '2019-02-13 04:45:53'),
('abc', '', 'a', 2, '2019-02-13 04:48:54'),
('abc', '', 'a', 2, '2019-02-13 05:21:17'),
('abc', '', 'a', 2, '2019-02-14 22:30:06');

-- --------------------------------------------------------

--
-- Table structure for table `login_types`
--

CREATE TABLE `login_types` (
  `login_type_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `row_status` tinyint(4) NOT NULL DEFAULT 0,
  `created_on` datetime NOT NULL,
  `updated_on` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login_types`
--

INSERT INTO `login_types` (`login_type_id`, `name`, `row_status`, `created_on`, `updated_on`) VALUES
(0, 'Workday', 0, '2018-12-19 18:28:03', '2018-12-19 13:11:08'),
(1, 'Salesforce', 0, '2018-12-19 18:28:03', '2018-12-19 13:11:16');

-- --------------------------------------------------------

--
-- Table structure for table `object_properties_tbl`
--

CREATE TABLE `object_properties_tbl` (
  `test_case_id` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `object_id` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `method` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `value` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `object_properties_tbl`
--

INSERT INTO `object_properties_tbl` (`test_case_id`, `object_id`, `method`, `value`) VALUES
('Login', 'password', 'XPATH', './/input[@type=\'password\']'),
('Login', 'signIn', 'XPATH', './/*[@data-automation-id=\'goButton\']'),
('Login', 'userName', 'XPATH', './/input[@aria-label=\'user name\']');

-- --------------------------------------------------------

--
-- Table structure for table `priority`
--

CREATE TABLE `priority` (
  `id` int(11) NOT NULL,
  `priority_name` varchar(255) NOT NULL,
  `row_status` tinyint(1) NOT NULL,
  `created_on` datetime NOT NULL,
  `updated_on` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `priority`
--

INSERT INTO `priority` (`id`, `priority_name`, `row_status`, `created_on`, `updated_on`) VALUES
(0, 'Normal', 0, '2019-01-09 03:17:33', '2019-01-09 09:42:52'),
(1, 'Low', 0, '2019-01-09 03:32:43', '2019-01-09 09:43:19'),
(2, 'High', 0, '2019-01-09 03:26:50', '2019-01-09 09:43:44');

-- --------------------------------------------------------

--
-- Table structure for table `severity`
--

CREATE TABLE `severity` (
  `id` int(11) NOT NULL,
  `severity_name` varchar(255) NOT NULL,
  `row_status` tinyint(1) NOT NULL,
  `created_on` datetime NOT NULL,
  `updated_on` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `severity`
--

INSERT INTO `severity` (`id`, `severity_name`, `row_status`, `created_on`, `updated_on`) VALUES
(0, 'Minor', 0, '2019-01-09 03:14:28', '2019-01-09 09:40:35'),
(1, 'Major', 0, '2019-01-09 02:15:24', '2019-01-09 09:41:01'),
(2, 'Critical', 0, '2019-01-09 02:30:47', '2019-01-09 09:41:23');

-- --------------------------------------------------------

--
-- Table structure for table `sf_activity_tbl`
--

CREATE TABLE `sf_activity_tbl` (
  `activity_id` varchar(30) NOT NULL,
  `description` varchar(30) NOT NULL,
  `test_cycle_id` varchar(30) NOT NULL,
  `test_type` varchar(30) NOT NULL COMMENT 'automation,manual'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sf_activity_tbl`
--

INSERT INTO `sf_activity_tbl` (`activity_id`, `description`, `test_cycle_id`, `test_type`) VALUES
('ACTIVITY003', 'Automation and Manual test cas', 'Test cycle id 1', 'automation'),
('ACTIVITY003', 'Automation and Manual test cas', 'Test cycle id 2', 'manual'),
('ACTIVITY004', 'Automation and Manual test cas', 'Test cycle id 1', 'automation'),
('ACTIVITY004', 'Automation and Manual test cas', 'Test cycle id 4', 'automation'),
('ACTIVITY004', 'Automation and Manual test cas', 'Test cycle id 4', 'manual'),
('ACTIVITY00001', 'Automation and Manual test cas', 'Test cycle id 1', 'automation'),
('ACTIVITY00001', 'Automation and Manual test cas', 'Test cycle id 2', 'manual');

-- --------------------------------------------------------

--
-- Table structure for table `sf_categories`
--

CREATE TABLE `sf_categories` (
  `id` int(11) NOT NULL,
  `category_name` varchar(255) NOT NULL,
  `row_status` tinyint(4) NOT NULL,
  `created_on` datetime NOT NULL,
  `updated_on` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sf_categories`
--

INSERT INTO `sf_categories` (`id`, `category_name`, `row_status`, `created_on`, `updated_on`) VALUES
(0, 'Sales', 0, '2019-01-09 02:11:28', '2019-01-09 09:39:24'),
(1, 'Marketing', 0, '2019-01-09 04:08:18', '2019-01-09 09:39:24');

-- --------------------------------------------------------

--
-- Table structure for table `tenant_setup`
--

CREATE TABLE `tenant_setup` (
  `tenant` varchar(50) NOT NULL DEFAULT '',
  `user_id` varchar(30) NOT NULL,
  `password` varchar(150) NOT NULL,
  `url` varchar(255) NOT NULL,
  `status` tinyint(4) NOT NULL COMMENT '0-active,1-inactive,2-deleted',
  `time_created` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tenant_setup`
--

INSERT INTO `tenant_setup` (`tenant`, `user_id`, `password`, `url`, `status`, `time_created`) VALUES
('fdfd', '', '', 'fdf', 2, '2019-01-30 08:50:28'),
('GMS', '', '', 'https://impl.workday.com/wday/authgwy/osv/login.htmld', 0, '2018-10-31 06:28:58'),
('konnect', '', '', 'www.konnect.com', 2, '2019-01-24 02:46:03'),
('SBX', '', '', 'https://impl.workday.com/wday/authgwy/dpt2/login.htmld', 0, '2018-10-31 06:28:35'),
('tenant', '', '', 'http://www.google.com', 2, '2019-02-01 02:42:21'),
('Test', '', '', 'www.konnect.comn', 2, '2019-01-25 05:52:25'),
('tenant', '', '', 'http://www.google.com', 2, '2019-02-01 02:44:30'),
('tenant', '', '', 'http://www.google.com', 2, '2019-02-01 02:45:19'),
('Vision', '', '', 'https://ucf6-zafa-fa-ext.oracledemos.com/', 0, '2019-02-12 19:56:00');

-- --------------------------------------------------------

--
-- Table structure for table `testcase_type`
--

CREATE TABLE `testcase_type` (
  `id` int(11) NOT NULL,
  `testcase_name` varchar(255) NOT NULL,
  `row_status` tinyint(1) NOT NULL,
  `created_on` datetime NOT NULL,
  `updated_on` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `testcase_type`
--

INSERT INTO `testcase_type` (`id`, `testcase_name`, `row_status`, `created_on`, `updated_on`) VALUES
(0, 'Automation', 0, '2019-01-09 03:15:30', '2019-01-09 10:02:47'),
(1, 'Manual', 0, '2019-01-09 03:32:41', '2019-01-09 10:02:47');

-- --------------------------------------------------------

--
-- Table structure for table `test_case_defn`
--

CREATE TABLE `test_case_defn` (
  `test_case_id` varchar(30) NOT NULL,
  `description` varchar(50) NOT NULL,
  `category` varchar(30) DEFAULT NULL,
  `status` tinyint(4) NOT NULL COMMENT '0-active,1-inactive,2-deleted',
  `template` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `test_case_defn`
--

INSERT INTO `test_case_defn` (`test_case_id`, `description`, `category`, `status`, `template`) VALUES
('Benefits_Enrollment', 'Benefits Enrollment', '0', 0, 'Benefits_Enrollment.xlsx'),
('Konnect_Add_Expenses', 'Update_Employee_Details', '0', 0, 'EDIT_PERS_INFO.xlsx'),
('Personal_Information_Change', 'Personal Info', '0', 0, 'Personal_Information_Change.xls'),
('Update_Employee_Details', 'Update employee job details', '0', 0, 'Update_Employee_Details.xlsx');

-- --------------------------------------------------------

--
-- Table structure for table `test_case_details`
--

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

--
-- Dumping data for table `test_case_details`
--

INSERT INTO `test_case_details` (`test_case_id`, `keyword`, `object_id`, `keyin_data`, `screenshot`, `pass_log_msg`, `fail_log_msg`, `created_at`, `modified_at`) VALUES
('Task_W_32', 'enter_text', 'email', 'getData=Username', '', '', '', '2019-02-12', '2019-02-12'),
('Task_W_32', 'enter_text', 'Password', 'getData=Password', '', '', '', '2019-02-12', '2019-02-12'),
('Task_W_32', 'click', 'Login', '', '', '', '', '2019-02-12', '2019-02-12'),
('Task_W_30', 'enter_text', 'email', 'getData=Username', '', '', '', '2019-02-12', '2019-02-12'),
('Task_W_30', 'enter_text', 'Password', 'getData=Password', '', '', '', '2019-02-12', '2019-02-12'),
('Task_W_30', 'click', 'Login', '', '', '', '', '2019-02-12', '2019-02-12'),
('Task_W_33', 'enter_text', 'email', 'getData=Username', '', '', '', '2019-02-12', '2019-02-12'),
('Task_W_33', 'enter_text', 'Password', 'getData=Password', '', '', '', '2019-02-12', '2019-02-12'),
('Task_W_33', 'click', 'Login', '', '', '', '', '2019-02-12', '2019-02-12'),
('Task_W_35', 'enter_text', 'email', 'getData=Username', '', '', '', '2019-02-12', '2019-02-12'),
('Task_W_35', 'enter_text', 'Password', 'getData=Password', '', '', '', '2019-02-12', '2019-02-12'),
('Task_W_35', 'click', 'Login', '', '', '', '', '2019-02-12', '2019-02-12'),
('Task_W_37', 'enter_text', 'email', 'getData=Username', '', '', '', '2019-02-12', '2019-02-12'),
('Task_W_37', 'enter_text', 'Password', 'getData=Password', '', '', '', '2019-02-12', '2019-02-12'),
('Task_W_37', 'click', 'Login', '', '', '', '', '2019-02-12', '2019-02-12'),
('Task_W_38', 'enter_text', 'email', 'getData=Username', '', '', '', '2019-02-12', '2019-02-12'),
('Task_W_38', 'enter_text', 'Password', 'getData=Password', '', '', '', '2019-02-12', '2019-02-12'),
('Task_W_38', 'click', 'Login', '', '', '', '', '2019-02-12', '2019-02-12'),
('Task_W_39', 'enter_text', 'email', 'getData=Username', '', '', '', '2019-02-12', '2019-02-12'),
('Task_W_39', 'enter_text', 'Password', 'getData=Password', '', '', '', '2019-02-12', '2019-02-12'),
('Task_W_39', 'click', 'Login', '', '', '', '', '2019-02-12', '2019-02-12'),
('Task_W_40', 'enter_text', 'email', 'getData=Username', '', '', '', '2019-02-12', '2019-02-12'),
('Task_W_40', 'enter_text', 'Password', 'getData=Password', '', '', '', '2019-02-12', '2019-02-12'),
('Task_W_40', 'click', 'Login', '', '', '', '', '2019-02-12', '2019-02-12'),
('Task_W_41', 'enter_text', 'email', 'getData=Username', '', '', '', '2019-02-12', '2019-02-12'),
('Task_W_41', 'enter_text', 'Password', 'getData=Password', '', '', '', '2019-02-12', '2019-02-12'),
('Task_W_41', 'click', 'Login', '', '', '', '', '2019-02-12', '2019-02-12'),
('Update_Employee_Details', 'enter_text', 'Username', 'getData=Username', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'enter_text', 'Password', 'getData=Password', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'click', 'Signin', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'delay', '', 'getData=sleepingtime', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'click', 'Home', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'delay', '', 'getData=sleepingtime', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'click', 'Menu', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'delay', '', 'getData=sleepingtime', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'delay', '', 'getData=sleepingtime', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'waitForXPath', 'My_Client_group', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'click', 'My_Client_group', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'click', 'Person_Management', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'delay', '', 'getData=sleepingtime', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'delay', '', 'getData=sleepingtime', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'delay', '', 'getData=sleepingtime', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'delay', '', 'getData=sleepingtime', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'waitForXPath', 'Name', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'enter_text', 'Name', 'getData=Name', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'waitForPageToLoad', '', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'click', 'Search', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'delay', '', 'getData=sleepingtime', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'click', 'Person_name', 'getData=Person_name', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'waitForPageToLoad', '', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'delay', '', 'getData=sleepingtime', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'click', 'Edit_dropdown', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'delay', '', 'getData=sleepingtime', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'waitForXPath', 'Update', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'click', 'Update', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'delay', '', 'getData=sleepingtime', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'delay', '', 'getData=sleepingtime', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'waitForXPath', 'Action', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'click', 'Action', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'waitForXPath', 'Action_dd', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'click', 'Action_dd', 'getData=Action', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'delay', '', 'getData=sleepingtime', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'waitForXPath', 'Action_reason', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'click', 'Action_reason', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'waitForXPath', 'Action_reson_dd', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'click', 'Action_reson_dd', 'getData=Action_reason', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'click', 'Ok_rep', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'delay', '', 'getData=sleepingtime', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'delay', '', 'getData=sleepingtime', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'delay', '', 'getData=sleepingtime', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'enter_text', 'Test', 'getData=Test', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'scrollDown', 'Job', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'waitForXPath', 'Job', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'enter_text', 'Job', 'getData=Job', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'click', 'Job_dd', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'scrollDown', 'Assignment_category', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'waitForXPath', 'Assignment_category', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'click', 'Assignment_category', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'waitForXPath', 'Assignment_category_dd', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'click', 'Assignment_category_dd', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'waitForXPath', 'Regular', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'click', 'Regular', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'waitForXPath', 'Regular_dd', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'click', 'Regular_dd', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'waitForXPath', 'Full_time', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'click', 'Full_time', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'waitForXPath', 'Full_time_dd', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'click', 'Full_time_dd', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'delay', '', 'getData=sleepingtime', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'waitForXPath', 'Review', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'click', 'Review', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'waitForXPath', 'Submit', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'click', 'Submit', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'delay', '', 'getData=sleepingtime', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'waitForXPath', 'Request_submit_Y', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'click', 'Request_submit_Y', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'waitForPageToLoad', '', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'delay', '', 'getData=sleepingtime', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'waitForXPath', 'confirmation', '', '', '', '', '2019-02-14', '2019-02-14'),
('Update_Employee_Details', 'confirm_Message', 'confirmation', '', '', '', '', '2019-02-14', '2019-02-14'),
('Task_W_51', 'enter_text', 'email', 'getData=Username', '', '', '', '2019-02-15', '2019-02-15'),
('Task_W_51', 'enter_text', 'Password', 'getData=Password', '', '', '', '2019-02-15', '2019-02-15'),
('Task_W_51', 'click', 'Login', '', '', '', '', '2019-02-15', '2019-02-15'),
('IU_login', 'click', 'region', '', '', '', '', '2019-02-18', '2019-02-18'),
('IU_login', 'click', 'region_option', 'getData=region', '', '', '', '2019-02-18', '2019-02-18'),
('IU_login', 'delay', '', '1000', '', '', '', '2019-02-18', '2019-02-18'),
('IU_login', 'click', 'school', '', '', '', '', '2019-02-18', '2019-02-18'),
('IU_login', 'click', 'school_option', 'getData=school', '', '', '', '2019-02-18', '2019-02-18'),
('IU_login', 'delay', '', '1000', '', '', '', '2019-02-18', '2019-02-18'),
('IU_login', 'click', 'Month', '', '', '', '', '2019-02-18', '2019-02-18'),
('IU_login', 'click', 'month_option', 'getData=month', '', '', '', '2019-02-18', '2019-02-18'),
('IU_login', 'click', 'submit', '', '', '', '', '2019-02-18', '2019-02-18'),
('Task_W_34', 'enter_text', 'email', 'getData=Username', '', '', '', '2019-02-18', '2019-02-18'),
('Task_W_34', 'enter_text', 'Password', 'getData=Password', '', '', '', '2019-02-18', '2019-02-18'),
('Task_W_34', 'click', 'Login', '', '', '', '', '2019-02-18', '2019-02-18'),
('Konnect_Add_Expenses', 'waitForPageToLoad', '', '', '', '', '', '2019-02-20', '2019-02-20'),
('Konnect_Add_Expenses', 'enter_text', 'K_Username', 'getData=Username', '', '', '', '2019-02-20', '2019-02-20'),
('Konnect_Add_Expenses', 'enter_text', 'K_Password', 'getData=Password', '', '', '', '2019-02-20', '2019-02-20'),
('Konnect_Add_Expenses', 'click', 'K_SignIn', '', '', '', '', '2019-02-20', '2019-02-20'),
('Konnect_Add_Expenses', 'waitForPageToLoad', '', '', '', '', '', '2019-02-20', '2019-02-20'),
('Konnect_Add_Expenses', 'click', 'K_Expenses', '', '', '', '', '2019-02-20', '2019-02-20'),
('Konnect_Add_Expenses', 'click', 'K_add', '', '', '', '', '2019-02-20', '2019-02-20'),
('Konnect_Add_Expenses', 'enter_text', 'K_Expenses_name', 'getData=Expenses_name', '', '', '', '2019-02-20', '2019-02-20'),
('Konnect_Add_Expenses', 'click', 'K_Reimbursable', '', '', '', '', '2019-02-20', '2019-02-20'),
('Konnect_Add_Expenses', 'waitForXPath', 'K_Category_dropdown', '', '', '', '', '2019-02-20', '2019-02-20'),
('Konnect_Add_Expenses', 'click', 'K_Category_dropdown', '', '', '', '', '2019-02-20', '2019-02-20'),
('Konnect_Add_Expenses', 'click', 'K_Category', 'getData=Category', '', '', '', '2019-02-20', '2019-02-20'),
('Konnect_Add_Expenses', 'click', 'K_Project_dropdown', '', '', '', '', '2019-02-20', '2019-02-20'),
('Konnect_Add_Expenses', 'click', 'K_Project', 'getData=Project', '', '', '', '2019-02-20', '2019-02-20'),
('Konnect_Add_Expenses', 'pickPreviousDayFromDatePicker', 'K_expenses_date', 'getData=date', '', '', '', '2019-02-20', '2019-02-20'),
('Konnect_Add_Expenses', 'enter_text', 'K_Amount', 'getData=Amount', '', '', '', '2019-02-20', '2019-02-20'),
('Konnect_Add_Expenses', 'click', 'K_Payment_mode_dropdown', '', '', '', '', '2019-02-20', '2019-02-20'),
('Konnect_Add_Expenses', 'click', 'K_Payment_mode', 'getData=Payment_Mode', '', '', '', '2019-02-20', '2019-02-20'),
('Konnect_Add_Expenses', 'click', 'K_save', '', '', '', '', '2019-02-20', '2019-02-20'),
('Konnect_Add_Expenses', 'click', 'K_expense_view', '', '', '', '', '2019-02-20', '2019-02-20'),
('Konnect_Add_Expenses', 'click', 'K_submit', '', '', '', '', '2019-02-20', '2019-02-20'),
('Konnect_Add_Expenses', 'click', 'K_approve_yes', '', '', '', '', '2019-02-20', '2019-02-20'),
('Konnect_Add_Expenses', 'click', 'K_user_Logout', '', '', '', '', '2019-02-20', '2019-02-20'),
('Konnect_Add_Expenses', 'click', 'K_Logout', '', '', '', '', '2019-02-20', '2019-02-20'),
('Konnect_Add_Expenses', 'enter_text', 'K_Username', 'getData=M_name', '', '', '', '2019-02-20', '2019-02-20'),
('Konnect_Add_Expenses', 'enter_text', 'K_Password', 'getData=M_password', '', '', '', '2019-02-20', '2019-02-20'),
('Konnect_Add_Expenses', 'click', 'K_SignIn', '', '', '', '', '2019-02-20', '2019-02-20'),
('Konnect_Add_Expenses', 'waitForPageToLoad', '', '', '', '', '', '2019-02-20', '2019-02-20'),
('Konnect_Add_Expenses', 'click', 'K_Expenses', '', '', '', '', '2019-02-20', '2019-02-20'),
('Konnect_Add_Expenses', 'click', 'K_My_employees', '', '', '', '', '2019-02-20', '2019-02-20'),
('Konnect_Add_Expenses', 'click', 'K_employee_expenses', '', '', '', '', '2019-02-20', '2019-02-20'),
('Konnect_Add_Expenses', 'click', 'K_Manager_approve', '', '', '', '', '2019-02-20', '2019-02-20'),
('Konnect_Add_Expenses', 'click', 'K_approve_yes', '', '', '', '', '2019-02-20', '2019-02-20'),
('Konnect_Add_Expenses', 'click', 'K_user_Logout', '', '', '', '', '2019-02-20', '2019-02-20'),
('Konnect_Add_Expenses', 'click', 'K_Logout', '', '', '', '', '2019-02-20', '2019-02-20'),
('Benefits_Enrollment', '1', 'implementation.consultant', 'Fusion123', '1000', 'Benefits Administration', 'Enrollment', '2019-02-21', '2019-02-21');

-- --------------------------------------------------------

--
-- Table structure for table `test_case_lines`
--

CREATE TABLE `test_case_lines` (
  `test_case_id` varchar(30) NOT NULL,
  `line_id` int(4) NOT NULL,
  `keyword` varchar(30) NOT NULL,
  `object_id` varchar(30) NOT NULL,
  `key_in_data` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `test_case_man_defn`
--

CREATE TABLE `test_case_man_defn` (
  `test_case_id` varchar(30) NOT NULL,
  `description` text NOT NULL,
  `category` varchar(30) NOT NULL,
  `instructions` text NOT NULL,
  `status` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `test_case_man_defn`
--

INSERT INTO `test_case_man_defn` (`test_case_id`, `description`, `category`, `instructions`, `status`) VALUES
('Benefits_Enrollment', 'Add benefits', '3', '1. Click on benefits\r\n2. Add benefits', 0),
('M_Update_Employee_Details', 'Update the employee job details', '0', '1.Login with valid credentials.\r\n2.Search with the employee name\r\n3.Update Employee details', 0),
('Personal_Information_Change', 'Edit personal information', '0', '1.Open url\r\n2.Edit personal information', 0);

-- --------------------------------------------------------

--
-- Table structure for table `test_case_status`
--

CREATE TABLE `test_case_status` (
  `test_cycle_id` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `run_id` int(11) NOT NULL,
  `activity_id` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `test_case_id` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `status` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `test_data` longtext COLLATE utf8_unicode_ci DEFAULT NULL,
  `test_type` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '''manual'',''automation''',
  `comments` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `test_case_status`
--

INSERT INTO `test_case_status` (`test_cycle_id`, `run_id`, `activity_id`, `test_case_id`, `status`, `test_data`, `test_type`, `comments`) VALUES
('Personal_Information_Change', 15, 'K_Mohid_activity', 'Personal_Information_Change', 'Not Started', 'Test Data.xlsx', 'automation', ''),
('Personal_Information_Change', 15, 'K_Mohid_activity', 'Update_Employee_Details', 'Not Started', 'Personal_Information_Change.xls', 'automation', ''),
('Update_E_Details', 15, 'K_Mohid_activity', 'M_Update_Employee_Details', 'Not Started', NULL, 'manual', ''),
('Update_Details', 0, 'test', 'Update_Employee_Details', 'Not Started', 'Personal_Information_Change.xls', 'automation', ''),
('Update_Details', 5, 'Update_Emp_details', 'Update_Employee_Details', 'Not Started', 'Personal_Information_Change.xls', 'automation', ''),
('Update_personal_details', 13, 'Update_Employee_details', 'Benefits_Enrollment', 'Not Started', 'Benefits_Enrollment.xlsx', 'automation', ''),
('Update_personal_details', 13, 'Update_Employee_details', 'Update_Employee_Details', 'Not Started', 'Update_Employee_Details.xlsx', 'automation', ''),
('Update_Personal_details_M', 13, 'Update_Employee_details', 'Benefits_Enrollment', 'Not Started', NULL, 'manual', ''),
('Update_Personal_details_M', 13, 'Update_Employee_details', 'M_Update_Employee_Details', 'Not Started', NULL, 'manual', '');

-- --------------------------------------------------------

--
-- Table structure for table `test_cycle_case_defn`
--

CREATE TABLE `test_cycle_case_defn` (
  `test_cycle_id` varchar(30) NOT NULL,
  `test_case_id` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `test_cycle_case_defn`
--

INSERT INTO `test_cycle_case_defn` (`test_cycle_id`, `test_case_id`) VALUES
('Personal_Information_Change', 'Personal_Information_Change'),
('Personal_Information_Change', 'Update_Employee_Details'),
('Update_Details', 'Update_Employee_Details'),
('Update_personal_details', 'Benefits_Enrollment'),
('Update_personal_details', 'Update_Employee_Details');

-- --------------------------------------------------------

--
-- Table structure for table `test_cycle_case_man_defn`
--

CREATE TABLE `test_cycle_case_man_defn` (
  `test_cycle_id` varchar(30) NOT NULL,
  `test_case_id` varchar(30) NOT NULL,
  `assignee` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `test_cycle_case_man_defn`
--

INSERT INTO `test_cycle_case_man_defn` (`test_cycle_id`, `test_case_id`, `assignee`) VALUES
('Personal_Information_Change', 'Personal_Information_Change', 'w_user'),
('Update_E_Details', 'M_Update_Employee_Details', 'w_admin'),
('Update_Personal_details_M', 'Benefits_Enrollment', 'w_admin'),
('Update_Personal_details_M', 'M_Update_Employee_Details', 'w_admin');

-- --------------------------------------------------------

--
-- Table structure for table `test_cycle_defn`
--

CREATE TABLE `test_cycle_defn` (
  `test_cycle_id` varchar(30) NOT NULL,
  `description` varchar(50) NOT NULL,
  `test_case_id` varchar(30) NOT NULL,
  `status` tinyint(4) NOT NULL COMMENT '0-active,1-inactive,2-deleted',
  `time_created` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `test_cycle_defn`
--

INSERT INTO `test_cycle_defn` (`test_cycle_id`, `description`, `test_case_id`, `status`, `time_created`) VALUES
('Personal_Information', 'Edit Personal Information', '', 0, '2019-02-18 02:31:19'),
('Personal_Information_Change', 'Personal_Information_Change', '', 0, '2019-02-20 00:25:31'),
('Update_Details', 'Update employee job details', '', 0, '2019-02-14 03:09:11'),
('Update_personal_details', 'Add benefits and update job changes', '', 0, '2019-02-21 03:43:17');

-- --------------------------------------------------------

--
-- Table structure for table `test_cycle_man_defn`
--

CREATE TABLE `test_cycle_man_defn` (
  `test_cycle_id` varchar(30) NOT NULL,
  `description` varchar(30) NOT NULL,
  `test_case_id` varchar(30) NOT NULL,
  `status` tinyint(4) NOT NULL COMMENT '0-active,1-inactive,2-deleted',
  `assignee` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `test_cycle_man_defn`
--

INSERT INTO `test_cycle_man_defn` (`test_cycle_id`, `description`, `test_case_id`, `status`, `assignee`) VALUES
('Personal_Information_Change', 'Personal_Information_Change', '', 0, ''),
('Update_E_Details', 'Update Employee details', '', 0, ''),
('Update_Personal_details_M', 'Update details', '', 0, '');

-- --------------------------------------------------------

--
-- Table structure for table `test_cycle_run`
--

CREATE TABLE `test_cycle_run` (
  `run_id` int(30) NOT NULL,
  `user_id` varchar(30) NOT NULL,
  `activity_id` text NOT NULL,
  `test_cycle_id` text NOT NULL,
  `proxy` varchar(30) NOT NULL,
  `tenant` varchar(10) NOT NULL,
  `mode` varchar(30) NOT NULL,
  `run_dttm` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `test_cycle_run`
--

INSERT INTO `test_cycle_run` (`run_id`, `user_id`, `activity_id`, `test_cycle_id`, `proxy`, `tenant`, `mode`, `run_dttm`) VALUES
(1, 'w_admin', 'Update_Emp_details', '', '', 'Vision', '', '2019-02-14 03:15:50'),
(2, 'w_admin', 'Update_Emp_details', '', '', 'GMS', '', '2019-02-18 08:01:22'),
(3, 'w_admin', 'test', '', '', 'Vision', '', '2019-02-18 09:00:58'),
(4, 'w_admin', 'Update_Emp_details', '', '', 'Vision', '', '2019-02-20 00:30:18'),
(5, 'w_admin', 'Update_Emp_details', '', '142', 'Vision', '', '2019-02-20 00:53:11'),
(6, 'w_admin', 'Update_Employee_details', '', '', 'Vision', '', '2019-02-21 03:45:30'),
(7, 'w_admin', 'Update_Employee_details', '', '', 'Vision', '', '2019-02-21 04:02:01'),
(8, 'w_admin', 'Update_Employee_details', '', '', 'Vision', '', '2019-02-21 04:04:31'),
(9, 'w_admin', 'Update_Employee_details', '', '', 'Vision', '', '2019-02-21 04:07:33'),
(10, 'w_admin', 'Update_Employee_details', '', '', 'Vision', '', '2019-02-21 04:10:02'),
(11, 'w_admin', 'Update_Employee_details', '', '', 'Vision', '', '2019-02-21 04:10:33'),
(12, 'w_admin', 'Update_Employee_details', '', '', 'Vision', '', '2019-02-21 04:11:44'),
(13, 'w_admin', 'Update_Employee_details', '', '', 'Vision', '', '2019-02-21 04:13:39'),
(14, 'w_admin', 'K_Mohid_activity', '', '', 'GMS', '', '2019-02-21 09:38:46'),
(15, 'w_admin', 'K_Mohid_activity', '', '', 'SBX', '', '2019-02-21 09:47:17');

-- --------------------------------------------------------

--
-- Table structure for table `test_sf_case_defn`
--

CREATE TABLE `test_sf_case_defn` (
  `test_case_id` varchar(30) NOT NULL,
  `description` varchar(50) NOT NULL,
  `category` varchar(30) NOT NULL,
  `status` tinyint(4) NOT NULL COMMENT '0-active,1-inactive,2-deleted',
  `template` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `test_sf_case_defn`
--

INSERT INTO `test_sf_case_defn` (`test_case_id`, `description`, `category`, `status`, `template`) VALUES
('Task_W_30', 'Add Automation Test Case', '0', 0, 'Task_W_30.xlsx'),
('Task_W_31', 'Create Automation test case', '0', 0, 'Task _W_31.xlsx'),
('Task_W_32', 'Empty Automation test case', '0', 0, 'Task_W_32.xlsx'),
('Task_W_33', 'Test case id validation with test script id', '0', 0, 'Task_W_33.xlsx'),
('Task_W_34', 'Edit Automation Test cases', '0', 0, 'Task _W_34.xlsx'),
('Task_W_35', 'Edit empty automation test case', '0', 0, 'Task _W_35.xlsx'),
('Task_W_36', 'delete-yes Automation test case', '0', 0, 'Task _W_36.xlsx'),
('Task_W_37', 'delete-No Automation test case', '0', 0, 'Task _W_37.xlsx'),
('Task_W_38', 'List of test cases', '0', 0, 'Task _W_38.xlsx'),
('Task_W_39', 'No delete option', '0', 0, 'Task _W_39.xlsx'),
('Task_W_40', 'Pagination', '0', 0, 'Task _W_40.xlsx'),
('Task_W_41', 'Search', '0', 0, 'Task _W_41.xlsx');

-- --------------------------------------------------------

--
-- Table structure for table `test_sf_case_lines`
--

CREATE TABLE `test_sf_case_lines` (
  `test_case_id` varchar(30) NOT NULL,
  `line_id` int(4) NOT NULL,
  `keyword` varchar(30) NOT NULL,
  `object_id` varchar(30) NOT NULL,
  `key_in_data` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `test_sf_case_man_defn`
--

CREATE TABLE `test_sf_case_man_defn` (
  `test_case_id` varchar(30) NOT NULL,
  `description` varchar(30) NOT NULL,
  `category` varchar(30) NOT NULL,
  `instructions` varchar(2000) NOT NULL,
  `status` tinyint(4) NOT NULL COMMENT '0-active,1-inactive,2-deleted'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `test_sf_case_man_defn`
--

INSERT INTO `test_sf_case_man_defn` (`test_case_id`, `description`, `category`, `instructions`, `status`) VALUES
('Task_W_42', 'Add Manual Test Case', '0', '1.Click on add New Manual Test Case', 0),
('Task_W_43', 'Create Manual test case', '0', '\"1.Enter Testcase id:- Test case id is unique\r\n2.Select the Category\r\n3.Enter Description\r\n4.Enter instructions\r\n5.Click on save\"', 0),
('Task_W_44', 'Empty Manual test case', '0', '\"1.Enter Testcase id:- Test case id is unique\r\n2.Select the Category\r\n3.Enter Description\r\n4.Enter instructions\r\n5.Click on save\"', 0),
('Task_W_45', 'Edit Automation Test Case', '0', '\"1.Except test case id all are in editable format\r\n2.Select the Category\r\n3.Enter Description\r\n4.Upload the test data template\r\n5.Upload test scripts for the test case\r\n6.Click on save\"', 0),
('Task_W_46', 'delete-yes Manual test case', '0', '\"1.Click on the delete button to delete the Automation test case.\r\n2.Click on \"\"yes\"\"\"', 0),
('Task_W_47', 'delete-No Manual test case', '0', '\"1.Click on the delete button to delete the Automation test case.\r\n2.Click on \"\"NO\"\"\"', 0),
('Task_W_48', 'List of test cases', '0', '1.Display all the automation test cases.', 0),
('Task_W_49', 'No delete option', '0', '1.No delete option for test cases that are added In test cycle.', 0),
('Task_W_50', 'Pagination', '0', '1.The list is restricted to certain count selected in the drop down.', 0),
('Task_W_51', 'Search', '0', '1.Search with any of the field text', 0),
('Task_W_52', 'Execute Manual Test Case', '0', '1.Click on \"Execute Manual Test Case\".', 0),
('Task_W_53', 'List of Execute Manual Test Ca', '0', '1.List of manual test cases that are executed in the activity', 0),
('Task_W_54', 'Edit', '0', '1.Click on the edit', 0),
('Task_W_55', 'Edit-save', '0', '\"1.Activity id-read only\r\n2.Test Cycle Id-read only\r\n3.Test Case Id-read only\r\n4.Select status\r\n5.Enter comment\r\n6.Click on save\r\n\"', 0),
('Task_W_56', 'Edit-Cancel', '0', '\"1.Activity id-read only\r\n2.Test Cycle Id-read only\r\n3.Test Case Id-read only\r\n4.Select Status\r\n5.Enter Comment\r\n6.Click on \"\"Cancel\"\"\r\n\"', 0),
('Task_W_34', 'Edit Manual test case', '0', '1. Click on Manual test cases\r\n2. Click on Edit button of any particular test case\r\n3. After Editing the fields \r\n4. Click on save', 0),
('Task_W_31', 'Delete manual test cases', '0', '1. Click on Delete button\r\n2. Click on Yes From the pop-up screen', 0);

-- --------------------------------------------------------

--
-- Table structure for table `test_sf_case_status`
--

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

--
-- Dumping data for table `test_sf_case_status`
--

INSERT INTO `test_sf_case_status` (`activity_id`, `test_cycle_id`, `run_id`, `test_case_id`, `test_data`, `test_type`, `comments`, `status`) VALUES
('ACTIVITY003', 'Test cycle id 1', 6, 'Task_W_30', 'Task _W_36.xlsx', 'automation', '', 'Not Started'),
('ACTIVITY004', 'Test cycle id 4', 4, 'Task_W_42', '', 'manual', '', 'Not Started'),
('ACTIVITY004', 'Test cycle id 1', 4, 'Task_W_30', 'Task _W_51.xlsx', 'automation', '', 'Not Started'),
('ACTIVITY004', 'Test cycle id 4', 4, 'Task_W_32', 'Task _W_51.xlsx', 'automation', '', 'Not Started'),
('ACTIVITY004', 'Test cycle id 4', 4, 'Task_W_31', 'Task _W_51.xlsx', 'automation', '', 'Not Started'),
('ACTIVITY00001', 'Test cycle id 1', 9, 'Task_W_30', 'Task _W_36.xlsx', 'automation', '', 'Not Started'),
('ACTIVITY00001', 'Test cycle id 2', 9, 'Task_W_42', '', 'manual', '', 'Not Started');

-- --------------------------------------------------------

--
-- Table structure for table `test_sf_cycle_case_defn`
--

CREATE TABLE `test_sf_cycle_case_defn` (
  `test_cycle_id` varchar(30) NOT NULL,
  `test_case_id` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `test_sf_cycle_case_defn`
--

INSERT INTO `test_sf_cycle_case_defn` (`test_cycle_id`, `test_case_id`) VALUES
('Test cycle id 1', 'Task_W_30'),
('Test cycle id 4', 'Task_W_31'),
('Test cycle id 4', 'Task_W_32');

-- --------------------------------------------------------

--
-- Table structure for table `test_sf_cycle_case_man_defn`
--

CREATE TABLE `test_sf_cycle_case_man_defn` (
  `test_cycle_id` varchar(30) NOT NULL,
  `test_case_id` varchar(30) NOT NULL,
  `assignee` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `test_sf_cycle_case_man_defn`
--

INSERT INTO `test_sf_cycle_case_man_defn` (`test_cycle_id`, `test_case_id`, `assignee`) VALUES
('Test cycle id 2', 'Task_W_42', 'sf_admin'),
('Test cycle id 4', 'Task_W_42', 'garima'),
('Task_S_52', 'Task_W_42', ''),
('Task_W_31', 'Task_W_42', '');

-- --------------------------------------------------------

--
-- Table structure for table `test_sf_cycle_defn`
--

CREATE TABLE `test_sf_cycle_defn` (
  `test_cycle_id` varchar(30) NOT NULL,
  `description` varchar(50) NOT NULL,
  `test_case_id` varchar(30) NOT NULL,
  `status` tinyint(4) NOT NULL COMMENT '0-active,1-inactive,2-deleted',
  `time_created` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `test_sf_cycle_defn`
--

INSERT INTO `test_sf_cycle_defn` (`test_cycle_id`, `description`, `test_case_id`, `status`, `time_created`) VALUES
('Test cycle id 1', 'Automation test cycle', '', 0, '2019-02-12 23:54:47'),
('Test cycle id 4', 'Automation test case', '', 0, '2019-02-15 03:54:24');

-- --------------------------------------------------------

--
-- Table structure for table `test_sf_cycle_man_defn`
--

CREATE TABLE `test_sf_cycle_man_defn` (
  `test_cycle_id` varchar(30) NOT NULL,
  `description` varchar(30) NOT NULL,
  `test_case_id` varchar(30) NOT NULL,
  `status` tinyint(4) NOT NULL COMMENT '0-active,1-inactive,2-deleted',
  `assignee` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `test_sf_cycle_man_defn`
--

INSERT INTO `test_sf_cycle_man_defn` (`test_cycle_id`, `description`, `test_case_id`, `status`, `assignee`) VALUES
('Test cycle id 2', 'Manual  test cycle', '', 0, ''),
('Test cycle id 4', 'Manual test case', '', 0, ''),
('Task_S_52', 'Manual test cases', '', 0, ''),
('Task_W_31', 'manual test cases', '', 0, '');

-- --------------------------------------------------------

--
-- Table structure for table `test_sf_cycle_run`
--

CREATE TABLE `test_sf_cycle_run` (
  `run_id` int(30) NOT NULL,
  `user_id` varchar(30) NOT NULL,
  `activity_id` text NOT NULL,
  `test_cycle_id` text NOT NULL,
  `proxy` varchar(30) NOT NULL,
  `lightening` varchar(10) NOT NULL,
  `mode` varchar(30) NOT NULL,
  `run_dttm` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `test_sf_cycle_run`
--

INSERT INTO `test_sf_cycle_run` (`run_id`, `user_id`, `activity_id`, `test_cycle_id`, `proxy`, `lightening`, `mode`, `run_dttm`) VALUES
(1, 'sf_admin', 'ACTIVITY003', '', '', 'SALES', '', '2019-02-13 01:52:13'),
(2, 'sf_admin', 'ACTIVITY003', '', '', 'SALES', '', '2019-02-14 22:47:58'),
(3, 'sf_admin', 'ACTIVITY003', '', '', 'SALES', '', '2019-02-14 22:49:19'),
(4, 'sf_admin', 'ACTIVITY004', '', '', 'SALES', '', '2019-02-15 03:56:42'),
(5, 'sf_admin', 'ACTIVITY00001', '', '', 'SALES', '', '2019-02-17 22:16:12'),
(6, 'sf_user', 'ACTIVITY003', '', '', 'SALES', '', '2019-02-19 01:19:55'),
(7, 'sf_user', 'ACTIVITY00001', '', '', 'SALES', '', '2019-02-19 01:20:46'),
(8, 'sf_user', 'ACTIVITY00001', '', '', 'SALES', '', '2019-02-19 01:22:38'),
(9, 'sf_user', 'ACTIVITY00001', '', '', 'SALES', '', '2019-02-19 22:54:52');

-- --------------------------------------------------------

--
-- Table structure for table `user_roles`
--

CREATE TABLE `user_roles` (
  `role_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `row_status` tinyint(4) NOT NULL DEFAULT 0,
  `created_on` datetime NOT NULL,
  `updated_on` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_roles`
--

INSERT INTO `user_roles` (`role_id`, `name`, `row_status`, `created_on`, `updated_on`) VALUES
(0, 'Admin', 0, '2018-12-19 18:28:03', '2019-01-09 07:31:12'),
(1, 'User', 0, '2018-12-19 18:28:03', '2019-01-09 07:31:19');

-- --------------------------------------------------------

--
-- Table structure for table `user_setup`
--

CREATE TABLE `user_setup` (
  `user_id` varchar(30) NOT NULL DEFAULT '',
  `name` varchar(30) NOT NULL,
  `password` varchar(150) NOT NULL,
  `role` varchar(10) NOT NULL COMMENT '0-admin,1-user',
  `status` tinyint(4) NOT NULL COMMENT '0-active,1-inactive,2-deleted',
  `login_type` tinyint(4) NOT NULL COMMENT '0-workday,1-salesforce',
  `time_created` datetime NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_setup`
--

INSERT INTO `user_setup` (`user_id`, `name`, `password`, `role`, `status`, `login_type`, `time_created`, `id`) VALUES
('garima', 'garima', 'e10adc3949ba59abbe56e057f20f883e', '1', 0, 1, '2019-02-18 21:55:31', 72),
('garimam', 'garimam', 'e10adc3949ba59abbe56e057f20f883e', '0', 0, 1, '2019-02-20 00:15:03', 74),
('KAS097', 'yamika', '81dc9bdb52d04dc20036dbd8313ed055', '0', 0, 0, '2019-02-03 21:50:04', 59),
('KAS099', 'yamika', '81dc9bdb52d04dc20036dbd8313ed055', '0', 0, 1, '2019-02-03 22:40:49', 61),
('KAS119', 'Kirti', 'e10adc3949ba59abbe56e057f20f883e', '1', 0, 0, '2019-02-19 20:24:16', 73),
('sf_001', 'Anantha', 'e10adc3949ba59abbe56e057f20f883e', '1', 0, 1, '2019-01-27 20:58:59', 35),
('sf_005', 'user', 'e10adc3949ba59abbe56e057f20f883e', '1', 0, 1, '2019-01-31 05:02:02', 55),
('sf_admin', 'sf_admin', 'e10adc3949ba59abbe56e057f20f883e', '0', 0, 1, '2018-07-24 00:41:37', 7),
('sf_user', 'sf_user', 'e10adc3949ba59abbe56e057f20f883e', '1', 0, 1, '2019-02-13 02:25:26', 63),
('sivag', 'sivag', '25d55ad283aa400af464c76d713c07ad', '1', 0, 0, '2019-02-18 00:59:48', 71),
('w_admin', 'w_admin', 'e10adc3949ba59abbe56e057f20f883e', '0', 0, 0, '2018-07-14 16:59:02', 13),
('w_user', 'w_user', 'e10adc3949ba59abbe56e057f20f883e', '1', 0, 0, '2018-09-26 03:18:41', 14);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `activity_tbl`
--
ALTER TABLE `activity_tbl`
  ADD PRIMARY KEY (`activity_id`,`test_cycle_id`);

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `issues`
--
ALTER TABLE `issues`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `login_types`
--
ALTER TABLE `login_types`
  ADD PRIMARY KEY (`login_type_id`);

--
-- Indexes for table `object_properties_tbl`
--
ALTER TABLE `object_properties_tbl`
  ADD PRIMARY KEY (`test_case_id`,`object_id`);

--
-- Indexes for table `test_case_defn`
--
ALTER TABLE `test_case_defn`
  ADD PRIMARY KEY (`test_case_id`);

--
-- Indexes for table `test_case_lines`
--
ALTER TABLE `test_case_lines`
  ADD PRIMARY KEY (`test_case_id`,`line_id`);

--
-- Indexes for table `test_case_man_defn`
--
ALTER TABLE `test_case_man_defn`
  ADD PRIMARY KEY (`test_case_id`);

--
-- Indexes for table `test_case_status`
--
ALTER TABLE `test_case_status`
  ADD PRIMARY KEY (`activity_id`,`test_cycle_id`,`test_case_id`);

--
-- Indexes for table `test_cycle_case_defn`
--
ALTER TABLE `test_cycle_case_defn`
  ADD PRIMARY KEY (`test_cycle_id`,`test_case_id`);

--
-- Indexes for table `test_cycle_case_man_defn`
--
ALTER TABLE `test_cycle_case_man_defn`
  ADD PRIMARY KEY (`test_cycle_id`,`test_case_id`);

--
-- Indexes for table `test_cycle_defn`
--
ALTER TABLE `test_cycle_defn`
  ADD PRIMARY KEY (`test_cycle_id`);

--
-- Indexes for table `test_cycle_man_defn`
--
ALTER TABLE `test_cycle_man_defn`
  ADD PRIMARY KEY (`test_cycle_id`);

--
-- Indexes for table `test_cycle_run`
--
ALTER TABLE `test_cycle_run`
  ADD PRIMARY KEY (`run_id`);

--
-- Indexes for table `test_sf_case_defn`
--
ALTER TABLE `test_sf_case_defn`
  ADD PRIMARY KEY (`test_case_id`);

--
-- Indexes for table `test_sf_case_lines`
--
ALTER TABLE `test_sf_case_lines`
  ADD PRIMARY KEY (`line_id`);

--
-- Indexes for table `test_sf_cycle_defn`
--
ALTER TABLE `test_sf_cycle_defn`
  ADD PRIMARY KEY (`test_cycle_id`);

--
-- Indexes for table `test_sf_cycle_run`
--
ALTER TABLE `test_sf_cycle_run`
  ADD PRIMARY KEY (`run_id`);

--
-- Indexes for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `user_setup`
--
ALTER TABLE `user_setup`
  ADD PRIMARY KEY (`user_id`,`id`),
  ADD KEY `id` (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `issues`
--
ALTER TABLE `issues`
  MODIFY `id` int(6) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `test_cycle_run`
--
ALTER TABLE `test_cycle_run`
  MODIFY `run_id` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `test_sf_cycle_run`
--
ALTER TABLE `test_sf_cycle_run`
  MODIFY `run_id` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `user_setup`
--
ALTER TABLE `user_setup`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=75;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
