-- phpMyAdmin SQL Dump
-- version 4.4.13.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 16, 2016 at 12:15 AM
-- Server version: 5.6.30-0ubuntu0.15.10.1
-- PHP Version: 5.6.11-1ubuntu3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `deer`
--

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE IF NOT EXISTS `roles` (
  `id` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `serverid` int(10) NOT NULL,
  `rolename` varchar(20) NOT NULL,
  `level` int(8) NOT NULL,
  `exp` int(11) NOT NULL,
  `gold` int(8) NOT NULL,
  `createtime` int(15) NOT NULL,
  `logintime` int(15) NOT NULL,
  `consecutivedays` int(6) NOT NULL,
  `rolestate` int(4) NOT NULL,
  `headimage` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;