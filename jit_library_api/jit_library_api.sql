-- phpMyAdmin SQL Dump
-- version 4.8.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Oct 17, 2018 at 07:24 AM
-- Server version: 10.1.33-MariaDB
-- PHP Version: 7.2.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `jit_library_api`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_books`
--

CREATE TABLE `tbl_books` (
  `book_id` int(11) NOT NULL,
  `book_name` varchar(200) NOT NULL,
  `author` varchar(200) NOT NULL,
  `publication` varchar(200) NOT NULL,
  `branch` varchar(200) NOT NULL,
  `year` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_books`
--

INSERT INTO `tbl_books` (`book_id`, `book_name`, `author`, `publication`, `branch`, `year`) VALUES
(1, 'Computer Organisation', 'abc ', 'Techmax', 'Computer', '2nd'),
(2, 'Microprocessor', 'xyz', 'Nirali', 'Computer', 'Last'),
(3, 'Theory of machin', 'fdsfds', 'Techmax', 'Mechanical', '1st'),
(4, 'Fluid Mechnics', 'fdsfsfg', 'Nirali', 'Mechanical', '3rd'),
(5, 'Concrete Technology', 'fdsfds', 'Techmax', 'Civil', '2nd'),
(6, 'Structural analysis', 'fdsfsfg', 'Nirali', 'Civil', 'Last'),
(7, 'Objective Electrical', 'bhjdfgs', 'Nirali', 'Electrical', '2nd'),
(8, 'Fundamental of Electrical', 'fgdsg', 'Techmax', 'Electrical', '3rd');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_users`
--

CREATE TABLE `tbl_users` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `branch` varchar(200) NOT NULL,
  `year` varchar(255) NOT NULL,
  `gender` varchar(200) NOT NULL,
  `mobile` varchar(13) NOT NULL,
  `added_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_users`
--

INSERT INTO `tbl_users` (`id`, `username`, `password`, `email`, `branch`, `year`, `gender`, `mobile`, `added_date`) VALUES
(1, 'shradha', '2ea3c6010c84a750f4fc93483c0c6abc', 'shradha@gmail.com', 'Computer', 'Last', 'Female', '9000000000', '2018-10-17 05:20:15');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_books`
--
ALTER TABLE `tbl_books`
  ADD PRIMARY KEY (`book_id`);

--
-- Indexes for table `tbl_users`
--
ALTER TABLE `tbl_users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_books`
--
ALTER TABLE `tbl_books`
  MODIFY `book_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `tbl_users`
--
ALTER TABLE `tbl_users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
