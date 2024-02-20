-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 19, 2024 at 12:41 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kwizera_bonheur_jms`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `CreatePrisonView` ()   BEGIN
    -- Check if the view already exists and drop it if it does
    IF EXISTS (SELECT table_name FROM information_schema.views WHERE table_name = 'prison_view') THEN
        DROP VIEW prison_view;
    END IF;

    -- Create the view using a subquery
    CREATE VIEW prison_view AS
    SELECT
        p.prison_id,
        p.prison_name,
        p.prison_district,
        p.prison_sector,
        pr.prisoner_id,
        pr.fname AS prisoner_fname,
        pr.lname AS prisoner_lname,
        pr.id_number AS prisoner_id_number,
        pr.gender AS prisoner_gender,
        pr.DoB AS prisoner_DoB,
        pr.martial_status AS prisoner_martial_status,
        pr.admission_date,
        pr.release_date
    FROM
        prisons p
    LEFT JOIN
        prisoners pr ON p.prison_id = pr.prison_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `DeleteDepartedEmployees` ()   BEGIN
    -- Delete employees who have left the company (status = 0)
    DELETE FROM employee
    WHERE employee_id = 2;

    -- Delete corresponding department records for departed employees
    DELETE FROM employee
    WHERE employee_id=2;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetAllAdmins` ()   BEGIN
    SELECT *
    FROM admin;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetAllDataFromTables` ()   BEGIN
    SELECT * FROM  admin;
    SELECT * FROM  employee;
    SELECT * FROM visitors;
    SELECT * FROM visit_date;
    SELECT * FROM prisons;
    SELECT * FROM prisoners;
SELECT * FROM visit_date;
SELECT * FROM visitors;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetAllEmployee` ()   BEGIN
    SELECT *
    FROM employee;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetAllPrisoners` ()   BEGIN
    SELECT *
    FROM prisoners;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetAllprisoner_relation` ()   BEGIN
    SELECT *
    FROM prisoner_relation;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetAllschedule_visit_date` ()   BEGIN
    SELECT *
    FROM schedule_visit_date;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetAllVisit` ()   BEGIN
    SELECT *
    FROM visit;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetAllvisitors` ()   BEGIN
    SELECT *
    FROM visitors;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetFilteredEmployeeDetails` (IN `filter_prison_id` INT, IN `filter_education_level` VARCHAR(255))   BEGIN
    SELECT
        employee_id,
        fname,
        lname,
        id_number,
        phone,
        gender,
        martial_status,
        education_level,
        DoB,
        email,
        password,
        prison_id
    FROM
        employees
    WHERE
        prison_id IN (
            SELECT prison_id FROM prisons WHERE prison_id = filter_prison_id
        )
        AND
        education_level IN (
            SELECT education_level FROM employees WHERE education_level = filter_education_level
        );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertDataIntoMultipleTables` (IN `admin_fname` VARCHAR(255), IN `admin_lname` VARCHAR(255), IN `admin_id_number` VARCHAR(255), IN `admin_phone` VARCHAR(15), IN `admin_gender` VARCHAR(10), IN `admin_martial_status` VARCHAR(20), IN `admin_DoB` DATE, IN `admin_email` VARCHAR(255), IN `admin_password` VARCHAR(255), IN `employee_id` INT, IN `employee_fname` VARCHAR(255), IN `employee_lname` VARCHAR(255), IN `employee_id_number` VARCHAR(255), IN `employee_phone` VARCHAR(15), IN `employee_gender` VARCHAR(10), IN `employee_martial_status` VARCHAR(20), IN `employee_education_level` VARCHAR(50), IN `employee_DoB` DATE, IN `employee_email` VARCHAR(255), IN `employee_password` VARCHAR(255), IN `employee_prison_id` INT, IN `prisoners_fname` VARCHAR(255), IN `prisoners_lname` VARCHAR(255), IN `prisoners_id_number` VARCHAR(255), IN `prisoners_gender` VARCHAR(10), IN `prisoners_DoB` DATE, IN `prisoners_martial_status` VARCHAR(20), IN `prisoners_admission_date` DATE, IN `prisoners_release_date` DATE, IN `prisoners_prison_id` INT, IN `prisoners_prisoner_relation` VARCHAR(255), IN `prison_id` INT, IN `prison_name` VARCHAR(255), IN `prison_district` VARCHAR(255), IN `prison_sector` VARCHAR(255))   BEGIN
    -- Insert data into admin table
    INSERT INTO admin (fname, lname, id_number, phone, gender, martial_status, DoB, email, password)
    VALUES (admin_fname, admin_lname, admin_id_number, admin_phone, admin_gender, admin_martial_status, admin_DoB, admin_email, admin_password);

    -- Insert data into employee table
    INSERT INTO employee (employee_id, fname, lname, id_number, phone, gender, martial_status, education_level, DoB, email, password, prison_id)
    VALUES (employee_id, employee_fname, employee_lname, employee_id_number, employee_phone, employee_gender, employee_martial_status, employee_education_level, employee_DoB, employee_email, employee_password, employee_prison_id);

    -- Insert data into prisoners table
    INSERT INTO prisoners (fname, lname, id_number, gender, DoB, martial_status, admission_date, release_date, prison_id, prisoner_relation)
    VALUES (prisoners_fname, prisoners_lname, prisoners_id_number, prisoners_gender, prisoners_DoB, prisoners_martial_status, prisoners_admission_date, prisoners_release_date, prisoners_prison_id, prisoners_prisoner_relation);

    -- Insert data into other tables as needed...

    COMMIT;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertEmployee` (IN `p_employee_id` INT, IN `p_fname` VARCHAR(50), IN `p_lname` VARCHAR(50), IN `p_id_number` VARCHAR(20), IN `p_phone` VARCHAR(15), IN `p_gender` VARCHAR(10), IN `p_marital_status` VARCHAR(20), IN `p_education_level` VARCHAR(50), IN `p_DoB` DATE, IN `p_email` VARCHAR(100), IN `p_password` VARCHAR(100), IN `p_prison_id` INT)   BEGIN
    INSERT INTO Employee (employee_id, fname, lname, id_number, phone, gender, martial_status, education_level, DoB, email, password, prison_id)
    VALUES (p_employee_id, p_fname, p_lname, p_id_number, p_phone, p_gender, p_marital_status, p_education_level, p_DoB, p_email, p_password, p_prison_id);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertPrisoner` (IN `prisoner_id` INT, IN `fname` VARCHAR(50), IN `lname` VARCHAR(50), IN `id_number` VARCHAR(20), IN `gender` VARCHAR(10), IN `DoB` DATE, IN `martial_status` VARCHAR(20), IN `admission_date` DATE, IN `release_date` DATE, IN `prison_id` INT)   BEGIN
    INSERT INTO Prisoners (prisoner_id, fname, lname, id_number, gender, DoB, martial_status, admission_date, release_date, prison_id)
    VALUES (prisoner_id, fname, lname, id_number, gender, DoB, martial_status, admission_date, release_date, prison_id);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertPrisonRelation` (IN `fname` VARCHAR(50), IN `lname` VARCHAR(50), IN `id_number` VARCHAR(20), IN `prisoner_id` INT, IN `gender` VARCHAR(10), IN `martial_status` VARCHAR(20), IN `email` VARCHAR(100), IN `district` VARCHAR(50), IN `sector` VARCHAR(50), IN `cell` VARCHAR(50), IN `prison_id` INT)   BEGIN
    INSERT INTO InsertPrison_relation (fname, lname, id_number, prisoner_id, gender, martial_status, email, district, sector, cell, prison_id)
    VALUES (fname, lname, id_number, prisoner_id, gender, martial_status, email, district, sector, cell, prison_id);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertVisitDate` (IN `visitor_id` INT, IN `prisoner_id` INT, IN `visit_id` INT, IN `reason` VARCHAR(255), IN `prison_id` INT)   BEGIN
    INSERT INTO schedule_visit_date (visitor_id, prisoner_id, visit_id, reason, prison_id)
    VALUES (visitor_id, prisoner_id, visit_id, reason, prison_id);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertVisitTime` (IN `visitor_id` INT, IN `prisoner_id` INT, IN `visit_id` INT, IN `reason` VARCHAR(255), IN `prison_id` INT)   BEGIN
    INSERT INTO schedule_visit_date (visitor_id, prisoner_id, visit_id, reason, prison_id)
    VALUES (visitor_id, prisoner_id, visit_id, reason, prison_id);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `UpdateEmployeePhoneNumber` (IN `p_employee_id` INT, IN `p_new_phone_number` VARCHAR(15))   BEGIN
    UPDATE Employee
    SET phone = p_new_phone_number
    WHERE employee_id = p_employee_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `UpdatePrisonerMaritalStatus` (IN `p_prisoner_id` INT, IN `p_new_marital_status` VARCHAR(20))   BEGIN
    UPDATE Prisoners
    SET martial_status = p_new_marital_status
    WHERE prisoner_id = p_prisoner_id;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `admin_id` int(10) NOT NULL,
  `fname` varchar(50) NOT NULL,
  `lname` varchar(50) NOT NULL,
  `id_number` varchar(16) NOT NULL,
  `phone` varchar(13) NOT NULL,
  `gender` varchar(40) NOT NULL,
  `martial_status` varchar(50) NOT NULL,
  `DoB` date NOT NULL,
  `email` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `role` varchar(255) NOT NULL DEFAULT 'visitor'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`admin_id`, `fname`, `lname`, `id_number`, `phone`, `gender`, `martial_status`, `DoB`, `email`, `password`, `role`) VALUES
(3, 'k dot', 'Munyawera', '1200430403', '0781242335', 'MALE', 'Single', '2002-02-03', 'kdot@gmail.com', '1234567821', 'Employee'),
(4, 'KWIZERA', 'k dot', '123567654', '07812345423', 'Female', 'Single', '2002-03-09', 'kbonheur123@gmail.com', '123456y', 'visitor'),
(5, 'KAKAJAMA', 'ORGINAL', '120023984', '0781353223', 'Female', 'Single', '2002-09-03', 'kbonheur123@gmail.com', '7882eh3wefbrd fj', 'visitor'),
(7, 'IRADUKUNDA', 'Maurice', '1200970389', '0785676243', 'Male', 'Single', '2002-12-12', 'ira@gmail.com', 'fuwyqkj', 'visitor'),
(8, 'KWIZERA', 'Bob', '124322232', '089762676', 'MALE', 'Single', '2006-06-05', 'bob@gmail.com', '12345678', 'visitor'),
(9, 'SEMAHORO', 'Jack', '12001344', '078453524', 'MALE', 'Single', '2001-02-03', 'sema@gmail.com', '13456', 'visitor');

-- --------------------------------------------------------

--
-- Table structure for table `choosen_visitors`
--

CREATE TABLE `choosen_visitors` (
  `prisoner_id` int(10) DEFAULT NULL,
  `fname` varchar(40) DEFAULT NULL,
  `lname` varchar(40) DEFAULT NULL,
  `id_number` varchar(16) DEFAULT NULL,
  `gender` varchar(40) DEFAULT NULL,
  `DoB` date DEFAULT NULL,
  `martial_status` varchar(40) DEFAULT NULL,
  `admission_date` date DEFAULT NULL,
  `release_date` date DEFAULT NULL,
  `prison_id` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `emoployee_view`
--

CREATE TABLE `emoployee_view` (
  `employee_id` int(10) DEFAULT NULL,
  `fname` varchar(50) DEFAULT NULL,
  `lname` varchar(16) DEFAULT NULL,
  `id_number` varchar(16) DEFAULT NULL,
  `phone` varchar(13) DEFAULT NULL,
  `gender` varchar(40) DEFAULT NULL,
  `martial_status` varchar(50) DEFAULT NULL,
  `education_level` varchar(40) DEFAULT NULL,
  `DoB` date DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `employee_id` int(10) NOT NULL,
  `fname` varchar(50) NOT NULL,
  `lname` varchar(16) NOT NULL,
  `id_number` varchar(16) NOT NULL,
  `phone` varchar(13) NOT NULL,
  `gender` varchar(40) NOT NULL,
  `martial_status` varchar(50) NOT NULL,
  `education_level` varchar(40) NOT NULL,
  `DoB` date NOT NULL,
  `email` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `prison_id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`employee_id`, `fname`, `lname`, `id_number`, `phone`, `gender`, `martial_status`, `education_level`, `DoB`, `email`, `password`, `prison_id`) VALUES
(1, 'RUKUNDO', 'egide', '1200256765324536', '+250781452545', 'male', 'married', 'Banchelor', '2000-09-04', 'rukundo@gmaol.com', '12345678', 2),
(4, 'KANGABE', 'Ritha', '1200256765678617', '+250781423775', 'female', 'single', 'Banchelor', '2001-09-04', 'rukundo@gmaol.com', '12345678', 2);

--
-- Triggers `employee`
--
DELIMITER $$
CREATE TRIGGER `AfterDeleteEmployee` AFTER DELETE ON `employee` FOR EACH ROW BEGIN
    INSERT INTO employee_audit (employee_id, action, audit_timestamp)
    VALUES (employee_id, 'DELETE', NOW());
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `AfterInsertEmployee` AFTER INSERT ON `employee` FOR EACH ROW BEGIN
    -- You can perform actions here after a new employee is inserted.
    -- For example, you might log the insertion or perform additional data operations.
    -- This is a placeholder for your custom logic.
    
    -- For demonstration purposes, we'll insert a record into an audit table.
    INSERT INTO employee_audit (employee_id, action, audit_timestamp)
    VALUES (NEW.employee_id, 'INSERT', NOW());
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `AfterUpdateEmployee` AFTER UPDATE ON `employee` FOR EACH ROW BEGIN
    INSERT INTO employee_audit (employee_id, action, audit_timestamp)
    VALUES (NEW.employee_id, 'UPDATE', NOW());
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `employee_audit`
--

CREATE TABLE `employee_audit` (
  `employee_id` int(10) NOT NULL,
  `action` varchar(50) NOT NULL,
  `audit_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employee_audit`
--

INSERT INTO `employee_audit` (`employee_id`, `action`, `audit_timestamp`) VALUES
(4, 'INSERT', '2023-09-12 21:17:42.000000');

-- --------------------------------------------------------

--
-- Table structure for table `prisoners`
--

CREATE TABLE `prisoners` (
  `prisoner_id` int(10) NOT NULL,
  `fname` varchar(40) NOT NULL,
  `lname` varchar(40) NOT NULL,
  `id_number` varchar(16) NOT NULL,
  `gender` varchar(40) NOT NULL,
  `DoB` date NOT NULL,
  `martial_status` varchar(40) NOT NULL,
  `admission_date` date NOT NULL,
  `release_date` date NOT NULL,
  `prison_id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `prisoners`
--

INSERT INTO `prisoners` (`prisoner_id`, `fname`, `lname`, `id_number`, `gender`, `DoB`, `martial_status`, `admission_date`, `release_date`, `prison_id`) VALUES
(2, 'RURANGWA ', 'Leo', '123342232', 'MALE', '2014-12-12', 'Single', '2024-12-12', '2030-12-12', 7),
(3, 'RUGAJU', 'Albelt', '1200156583498765', 'male', '2001-09-15', 'single', '2023-09-06', '2023-09-03', 2),
(4, 'MUGABE', 'Ruth', '1200574567892309', 'female', '2002-09-02', 'devorse', '2014-09-16', '2022-09-20', 4),
(101, 'John', 'kwihangana', '123457654314273', 'Male', '1990-01-15', 'Married', '2023-09-01', '2024-09-01', 1);

-- --------------------------------------------------------

--
-- Table structure for table `prisoners_view`
--

CREATE TABLE `prisoners_view` (
  `prisoner_id` int(10) DEFAULT NULL,
  `fname` varchar(40) DEFAULT NULL,
  `lname` varchar(40) DEFAULT NULL,
  `id_number` varchar(16) DEFAULT NULL,
  `gender` varchar(40) DEFAULT NULL,
  `DoB` date DEFAULT NULL,
  `martial_status` varchar(40) DEFAULT NULL,
  `admission_date` date DEFAULT NULL,
  `release_date` date DEFAULT NULL,
  `prison_id` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `prisoner_relation`
--

CREATE TABLE `prisoner_relation` (
  `relation_id` int(10) NOT NULL,
  `fname` varchar(40) DEFAULT NULL,
  `lname` varchar(40) DEFAULT NULL,
  `id_number` varchar(16) DEFAULT NULL,
  `prisoner_id` int(10) DEFAULT NULL,
  `gender` varchar(30) DEFAULT NULL,
  `martial_status` varchar(30) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `district` varchar(40) DEFAULT NULL,
  `sector` varchar(40) DEFAULT NULL,
  `cell` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `prisoner_relation`
--

INSERT INTO `prisoner_relation` (`relation_id`, `fname`, `lname`, `id_number`, `prisoner_id`, `gender`, `martial_status`, `email`, `district`, `sector`, `cell`) VALUES
(1, 'ISHIMWE', 'Kabebe', '1200232765324536', 3, 'male', 'single', 'kabe@gmail.com', 'RUTSIRO', 'GIHANGO', 'CONGO-NIL'),
(2, 'KABANO', 'Emmy', '1200156213493465', 4, 'female', 'married', 'emmy@gmail.com', 'Karongi', 'kibuyr', 'gisayo'),
(3, 'KANGABE', 'Ritha', '1200574985292309', 2, 'female', 'devorse', 'kagi@gmail.com', 'Huye', 'Ngoma', 'Ngoma');

-- --------------------------------------------------------

--
-- Table structure for table `prisoner_relation_view`
--

CREATE TABLE `prisoner_relation_view` (
  `relation_id` int(10) DEFAULT NULL,
  `fname` varchar(40) DEFAULT NULL,
  `lname` varchar(40) DEFAULT NULL,
  `id_number` varchar(16) DEFAULT NULL,
  `prisoner_id` int(10) DEFAULT NULL,
  `gender` varchar(30) DEFAULT NULL,
  `martial_status` varchar(30) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `district` varchar(40) DEFAULT NULL,
  `sector` varchar(40) DEFAULT NULL,
  `cell` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `prisons`
--

CREATE TABLE `prisons` (
  `prison_id` int(10) NOT NULL,
  `prison_name` varchar(50) NOT NULL,
  `prison_district` varchar(50) NOT NULL,
  `prison_sector` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `prisons`
--

INSERT INTO `prisons` (`prison_id`, `prison_name`, `prison_district`, `prison_sector`) VALUES
(5, 'Nyarugenge prison', 'Nyarugenge', 'mushubi'),
(7, 'Kigali', 'Nyanza', 'kigali'),
(8, 'Kigali prison', 'Nyanza', 'kigali'),
(9, 'Rubavu prison', 'Rubavu', 'Rubavu'),
(18, 'Kigali', 'Kicukiro', 'kigali'),
(19, 'Rubavu Prison', 'Rubavu', 'Rubavu'),
(20, 'RUBAVU JAIL', 'RUBAVU', 'RUBAVU'),
(21, 'Rubavu', 'Rubavu', 'Rubavu'),
(22, 'KABUTARE Jail', 'Huye', 'Taba');

--
-- Triggers `prisons`
--
DELIMITER $$
CREATE TRIGGER `AfterInsertPrison` AFTER INSERT ON `prisons` FOR EACH ROW BEGIN
    -- You can perform actions here after a new prison is inserted.
    -- For example, you might log the insertion or perform additional data operations.
    -- This is a placeholder for your custom logic.

    -- For demonstration purposes, we'll insert a record into an audit table.
    INSERT INTO prison_audit (prison_id, prison_name, prison_district, prison_sector, action, audit_timestamp)
    VALUES (NEW.prison_id, NEW.prison_name, NEW.prison_district, NEW.prison_sector, 'INSERT', NOW());
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `AfterUpdatePrison` AFTER INSERT ON `prisons` FOR EACH ROW BEGIN
    INSERT INTO prison_audit (prison_id, prison_name, prison_district, prison_sector, action, audit_timestamp)
    VALUES (NEW.prison_id, NEW.prison_name, NEW.prison_district, NEW.prison_sector, 'UPDATE', NOW());
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `prisons_view`
--

CREATE TABLE `prisons_view` (
  `prison_id` int(10) DEFAULT NULL,
  `prison_name` varchar(50) DEFAULT NULL,
  `prison_district` varchar(50) DEFAULT NULL,
  `prison_sector` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `prison_audit`
--

CREATE TABLE `prison_audit` (
  `prison_id` int(10) NOT NULL,
  `prison_name` varchar(50) NOT NULL,
  `prison_district` varchar(50) NOT NULL,
  `prison_sector` varchar(50) NOT NULL,
  `action` varchar(50) NOT NULL,
  `audit_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `prison_audit`
--

INSERT INTO `prison_audit` (`prison_id`, `prison_name`, `prison_district`, `prison_sector`, `action`, `audit_timestamp`) VALUES
(7, 'Kigali', 'Nyanza', 'kigali', 'INSERT', '2023-09-12 22:09:14.000000'),
(7, 'Kigali', 'Nyanza', 'kigali', 'UPDATE', '2023-09-12 22:09:14.000000'),
(8, 'Kigali prison', 'Nyanza', 'kigali', 'INSERT', '2023-09-12 22:09:43.000000'),
(8, 'Kigali prison', 'Nyanza', 'kigali', 'UPDATE', '2023-09-12 22:09:43.000000'),
(0, 'Rubavu prison', 'Rubavu', 'Rubavu', 'INSERT', '2024-01-16 11:20:32.000000'),
(0, 'Rubavu prison', 'Rubavu', 'Rubavu', 'UPDATE', '2024-01-16 11:20:32.000000'),
(18, 'Kigali', 'Kicukiro', 'kigali', 'INSERT', '2024-01-16 11:23:05.000000'),
(18, 'Kigali', 'Kicukiro', 'kigali', 'UPDATE', '2024-01-16 11:23:05.000000'),
(19, 'Rubavu Prison', 'Rubavu', 'Rubavu', 'INSERT', '2024-01-16 11:24:20.000000'),
(19, 'Rubavu Prison', 'Rubavu', 'Rubavu', 'UPDATE', '2024-01-16 11:24:20.000000'),
(20, 'RUBAVU JAIL', 'RUBAVU', 'RUBAVU', 'INSERT', '2024-01-16 14:08:20.000000'),
(20, 'RUBAVU JAIL', 'RUBAVU', 'RUBAVU', 'UPDATE', '2024-01-16 14:08:20.000000'),
(21, '', '', '', 'INSERT', '2024-01-16 17:15:39.000000'),
(21, '', '', '', 'UPDATE', '2024-01-16 17:15:39.000000'),
(22, 'KABUTARE Jail', 'Huye', 'Taba', 'INSERT', '2024-01-16 20:37:13.000000'),
(22, 'KABUTARE Jail', 'Huye', 'Taba', 'UPDATE', '2024-01-16 20:37:13.000000'),
(23, 'Kagugu', 'Kigali', 'Kigali', 'INSERT', '2024-01-19 12:55:57.000000'),
(23, 'Kagugu', 'Kigali', 'Kigali', 'UPDATE', '2024-01-19 12:55:57.000000');

-- --------------------------------------------------------

--
-- Table structure for table `prison_view`
--

CREATE TABLE `prison_view` (
  `prison_id` int(10) DEFAULT NULL,
  `prison_name` varchar(50) DEFAULT NULL,
  `prison_district` varchar(50) DEFAULT NULL,
  `prison_sector` varchar(50) DEFAULT NULL,
  `prisoner_id` int(10) DEFAULT NULL,
  `prisoner_fname` varchar(40) DEFAULT NULL,
  `prisoner_lname` varchar(40) DEFAULT NULL,
  `prisoner_id_number` varchar(16) DEFAULT NULL,
  `prisoner_gender` varchar(40) DEFAULT NULL,
  `prisoner_DoB` date DEFAULT NULL,
  `prisoner_martial_status` varchar(40) DEFAULT NULL,
  `admission_date` date DEFAULT NULL,
  `release_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `schedule_visit_date`
--

CREATE TABLE `schedule_visit_date` (
  `schedule_id` int(10) NOT NULL,
  `visitor_id` int(10) NOT NULL,
  `prisoner_id` int(10) NOT NULL,
  `visit_id` int(10) NOT NULL,
  `reason` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `schedule_visit_date`
--

INSERT INTO `schedule_visit_date` (`schedule_id`, `visitor_id`, `prisoner_id`, `visit_id`, `reason`) VALUES
(1, 1, 3, 1, 'give him food'),
(2, 3, 2, 3, 'check the health'),
(3, 2, 2, 4, 'I miss him');

-- --------------------------------------------------------

--
-- Table structure for table `schedule_visit_date_view`
--

CREATE TABLE `schedule_visit_date_view` (
  `schedule_id` int(10) DEFAULT NULL,
  `visitor_id` int(10) DEFAULT NULL,
  `prisoner_id` int(10) DEFAULT NULL,
  `visit_id` int(10) DEFAULT NULL,
  `reason` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `visitors`
--

CREATE TABLE `visitors` (
  `visitor_id` int(10) NOT NULL,
  `fname` varchar(50) NOT NULL,
  `lname` varchar(50) NOT NULL,
  `id_number` varchar(16) NOT NULL,
  `phone` varchar(13) NOT NULL,
  `gender` varchar(30) NOT NULL,
  `martial_status` varchar(50) NOT NULL,
  `DoB` date NOT NULL,
  `email` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `visitors`
--

INSERT INTO `visitors` (`visitor_id`, `fname`, `lname`, `id_number`, `phone`, `gender`, `martial_status`, `DoB`, `email`, `password`) VALUES
(1, 'KWIZERA', 'Omah lay', '1200256765332536', '+250781454375', 'male', 'single', '2001-09-04', 'omah@gmail.com', '12345678'),
(2, 'RUKUNDO', 'Vivens', '1200156583432765', '+250781232215', 'male', 'single', '2001-09-04', 'vie@gmail.com', '12345vive'),
(3, 'ISHIME', 'Audile', '1200574987892309', '+250781322445', 'female', 'devorse', '2000-09-13', 'ishia@gmail.com', '123ogog');

-- --------------------------------------------------------

--
-- Table structure for table `visitor_view`
--

CREATE TABLE `visitor_view` (
  `visitor_id` int(10) DEFAULT NULL,
  `fname` varchar(50) DEFAULT NULL,
  `lname` varchar(50) DEFAULT NULL,
  `id_number` varchar(16) DEFAULT NULL,
  `phone` varchar(13) DEFAULT NULL,
  `gender` varchar(30) DEFAULT NULL,
  `martial_status` varchar(50) DEFAULT NULL,
  `DoB` date DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `visit_data_view`
--

CREATE TABLE `visit_data_view` (
  `visit_id` int(10) DEFAULT NULL,
  `visit_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `visit_date`
--

CREATE TABLE `visit_date` (
  `visit_id` int(10) NOT NULL,
  `visit_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `visit_date`
--

INSERT INTO `visit_date` (`visit_id`, `visit_date`) VALUES
(1, '2023-09-05'),
(2, '2023-09-14'),
(3, '2023-09-11'),
(4, '2023-09-16');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`admin_id`);

--
-- Indexes for table `prisoners`
--
ALTER TABLE `prisoners`
  ADD PRIMARY KEY (`prisoner_id`);

--
-- Indexes for table `prisons`
--
ALTER TABLE `prisons`
  ADD PRIMARY KEY (`prison_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `admin_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `prisoners`
--
ALTER TABLE `prisoners`
  MODIFY `prisoner_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=104;

--
-- AUTO_INCREMENT for table `prisons`
--
ALTER TABLE `prisons`
  MODIFY `prison_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
