-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Час створення: Квт 02 2018 р., 22:26
-- Версія сервера: 10.1.30-MariaDB
-- Версія PHP: 7.2.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База даних: `studentcard`
--

-- --------------------------------------------------------

--
-- Структура таблиці `journal`
--

CREATE TABLE `journal` (
  `journalID` int(11) NOT NULL,
  `journal_subjectID` int(11) NOT NULL,
  `journal_studentID` int(11) NOT NULL,
  `journal_Note` int(11) NOT NULL,
  `journal_Renote` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблиці `students`
--

CREATE TABLE `students` (
  `studentID` int(11) NOT NULL,
  `studentLastName` varchar(50) NOT NULL,
  `studentFirstName` varchar(50) NOT NULL,
  `studentForeName` varchar(50) DEFAULT NULL,
  `studentGroup` varchar(10) NOT NULL,
  `studentNumberOfRecordBook` varchar(10) NOT NULL,
  `studentFormOfStudy` varchar(10) NOT NULL,
  `studentTypeOfTraining` varchar(10) NOT NULL,
  `studentBirthdate` date DEFAULT NULL,
  `studentSex` varchar(10) NOT NULL,
  `studentPhoto` mediumblob,
  `studentAddress` varchar(150) DEFAULT NULL,
  `studentPhone` varchar(14) DEFAULT NULL,
  `studentPhotoUrl` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблиці `subjects`
--

CREATE TABLE `subjects` (
  `subjectID` int(11) NOT NULL,
  `subjectName` varchar(100) NOT NULL,
  `teacherTeacherID` int(11) NOT NULL,
  `subjectECTS` double DEFAULT NULL,
  `subjectTotalNumberOfHours` int(11) NOT NULL,
  `subjecNumberOfLecturesHours` int(11) NOT NULL,
  `subjectNumberOfPracticeHours` int(11) NOT NULL,
  `subjectNumberOfLaboratoryHours` int(11) NOT NULL,
  `subjectConsultationHours` int(11) NOT NULL,
  `subjectFormOfControl` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблиці `teachers`
--

CREATE TABLE `teachers` (
  `teacherID` int(11) NOT NULL,
  `teacherLastname` varchar(100) NOT NULL,
  `teacherNameAndForeName` varchar(75) NOT NULL,
  `teacherSpeciality` varchar(50) NOT NULL,
  `teacherDiplom` varchar(20) NOT NULL,
  `teacherKategory` varchar(45) NOT NULL,
  `teacherDegree` varchar(20) DEFAULT NULL,
  `teacheExperience` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблиці `user`
--

CREATE TABLE `user` (
  `userID` int(11) NOT NULL,
  `userName` varchar(100) NOT NULL,
  `userLogin` varchar(100) NOT NULL,
  `userPassword` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Індекси таблиці `journal`
--
ALTER TABLE `journal`
  ADD PRIMARY KEY (`journalID`),
  ADD KEY `fk_Subject_has_Student_Student1_idx` (`journal_studentID`),
  ADD KEY `fk_Subject_has_Student_Subject1_idx` (`journal_subjectID`);

--
-- Індекси таблиці `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`studentID`);

--
-- Індекси таблиці `subjects`
--
ALTER TABLE `subjects`
  ADD PRIMARY KEY (`subjectID`),
  ADD KEY `fk_Subject_Teacher1_idx` (`teacherTeacherID`);

--
-- Індекси таблиці `teachers`
--
ALTER TABLE `teachers`
  ADD PRIMARY KEY (`teacherID`);

--
-- Індекси таблиці `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userID`);

--
-- AUTO_INCREMENT для збережених таблиць
--

--
-- AUTO_INCREMENT для таблиці `journal`
--
ALTER TABLE `journal`
  MODIFY `journalID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблиці `students`
--
ALTER TABLE `students`
  MODIFY `studentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблиці `subjects`
--
ALTER TABLE `subjects`
  MODIFY `subjectID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблиці `teachers`
--
ALTER TABLE `teachers`
  MODIFY `teacherID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблиці `user`
--
ALTER TABLE `user`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Обмеження зовнішнього ключа збережених таблиць
--

--
-- Обмеження зовнішнього ключа таблиці `journal`
--
ALTER TABLE `journal`
  ADD CONSTRAINT `fk_Subject_has_Student_Student1` FOREIGN KEY (`journal_studentID`) REFERENCES `students` (`studentID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_Subject_has_Student_Subject1` FOREIGN KEY (`journal_subjectID`) REFERENCES `subjects` (`subjectID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Обмеження зовнішнього ключа таблиці `subjects`
--
ALTER TABLE `subjects`
  ADD CONSTRAINT `fk_Subject_Teacher1` FOREIGN KEY (`teacherTeacherID`) REFERENCES `teachers` (`teacherID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
