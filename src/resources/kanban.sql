-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mar. 01 déc. 2020 à 17:00
-- Version du serveur :  5.7.26
-- Version de PHP :  7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `kanban`
--
CREATE DATABASE IF NOT EXISTS `kanban` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `kanban`;

-- --------------------------------------------------------

--
-- Structure de la table `account`
--

DROP TABLE IF EXISTS `account`;
CREATE TABLE IF NOT EXISTS `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `rank_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `rank_id_FK` (`rank_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `account`
--

INSERT INTO `account` (`id`, `firstname`, `lastname`, `username`, `password`, `rank_id`) VALUES
(3, 'Lucas', 'Besson', 'lbesson', '$2a$11$f49YgHPi10TYZVLOtjyMwOu1CVqreiNZS1Qw.wQsNDpiO7c2nUNle', 5),
(4, 'admintest', 'test', 'admin', '$2a$11$1RG.mQKSk7oGb55SfQc7l.47/4h6JL7A8fCbBwskOcjKLCV.lOH/q', 6);

-- --------------------------------------------------------

--
-- Structure de la table `color`
--

DROP TABLE IF EXISTS `color`;
CREATE TABLE IF NOT EXISTS `color` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `label` varchar(11) NOT NULL,
  `rgbcode` varchar(13) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `color`
--

INSERT INTO `color` (`id`, `label`, `rgbcode`) VALUES
(1, 'BLUE', '0, 0, 255');

-- --------------------------------------------------------

--
-- Structure de la table `rank`
--

DROP TABLE IF EXISTS `rank`;
CREATE TABLE IF NOT EXISTS `rank` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `label` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `rank`
--

INSERT INTO `rank` (`id`, `label`) VALUES
(5, 'ADMIN'),
(6, 'DEV');

-- --------------------------------------------------------

--
-- Structure de la table `task`
--

DROP TABLE IF EXISTS `task`;
CREATE TABLE IF NOT EXISTS `task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `creation_date` date NOT NULL,
  `account_id` bigint(20) NOT NULL,
  `tasktype_id` bigint(20) NOT NULL,
  `taskprogress_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `task_id_FK` (`account_id`),
  KEY `tasktype_id_FK` (`tasktype_id`),
  KEY `taskprogress_id_FK` (`taskprogress_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `task`
--

INSERT INTO `task` (`id`, `name`, `creation_date`, `account_id`, `tasktype_id`, `taskprogress_id`) VALUES
(1, 'tachetest', '2020-11-26', 1, 5, 9);

-- --------------------------------------------------------

--
-- Structure de la table `taskhistory`
--

DROP TABLE IF EXISTS `taskhistory`;
CREATE TABLE IF NOT EXISTS `taskhistory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `moving_date` date NOT NULL,
  `task_id` bigint(20) NOT NULL,
  `taskprogress_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `taskhistory_taskprogress_id_FK` (`taskprogress_id`),
  KEY `taskhistory_task_id_FK` (`task_id`),
  KEY `user_id_FK` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `taskprogress`
--

DROP TABLE IF EXISTS `taskprogress`;
CREATE TABLE IF NOT EXISTS `taskprogress` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `label` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `taskprogress`
--

INSERT INTO `taskprogress` (`id`, `label`) VALUES
(9, 'To-Do'),
(10, 'Work-In-Progress'),
(11, 'Validate');

-- --------------------------------------------------------

--
-- Structure de la table `tasktype`
--

DROP TABLE IF EXISTS `tasktype`;
CREATE TABLE IF NOT EXISTS `tasktype` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `label` varchar(255) NOT NULL,
  `color_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `color_color_id_FK` (`color_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `tasktype`
--

INSERT INTO `tasktype` (`id`, `label`, `color_id`) VALUES
(5, 'FEATURE', 1);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `rank_id_FK` FOREIGN KEY (`rank_id`) REFERENCES `rank` (`id`);

--
-- Contraintes pour la table `task`
--
ALTER TABLE `task`
  ADD CONSTRAINT `task_id_FK` FOREIGN KEY (`account_id`) REFERENCES `task` (`id`),
  ADD CONSTRAINT `taskprogress_id_FK` FOREIGN KEY (`taskprogress_id`) REFERENCES `taskprogress` (`id`),
  ADD CONSTRAINT `tasktype_id_FK` FOREIGN KEY (`tasktype_id`) REFERENCES `tasktype` (`id`);

--
-- Contraintes pour la table `taskhistory`
--
ALTER TABLE `taskhistory`
  ADD CONSTRAINT `account_user_id_FK` FOREIGN KEY (`user_id`) REFERENCES `account` (`id`),
  ADD CONSTRAINT `taskhistory_task_id_FK` FOREIGN KEY (`task_id`) REFERENCES `task` (`id`),
  ADD CONSTRAINT `taskhistory_taskprogress_id_FK` FOREIGN KEY (`taskprogress_id`) REFERENCES `taskhistory` (`id`);

--
-- Contraintes pour la table `tasktype`
--
ALTER TABLE `tasktype`
  ADD CONSTRAINT `color_color_id_FK` FOREIGN KEY (`color_id`) REFERENCES `color` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
