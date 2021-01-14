-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  jeu. 14 jan. 2021 à 18:37
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `color`
--

INSERT INTO `color` (`id`, `label`, `rgbcode`) VALUES
(1, 'BLUE', '0, 161, 255'),
(2, 'ORANGE', '248, 156, 14');

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
-- Structure de la table `rights`
--

DROP TABLE IF EXISTS `rights`;
CREATE TABLE IF NOT EXISTS `rights` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `label` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `rights`
--

INSERT INTO `rights` (`id`, `label`) VALUES
(1, 'CREATE_TASK'),
(2, 'MOVE_TASK'),
(3, 'SHOW_TASK');

-- --------------------------------------------------------

--
-- Structure de la table `right_rank`
--

DROP TABLE IF EXISTS `right_rank`;
CREATE TABLE IF NOT EXISTS `right_rank` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rank_id` bigint(20) NOT NULL,
  `rights_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `rank_id` (`rank_id`,`rights_id`),
  KEY `rights_id_fk` (`rights_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `right_rank`
--

INSERT INTO `right_rank` (`id`, `rank_id`, `rights_id`) VALUES
(1, 5, 1),
(2, 5, 2),
(3, 5, 3),
(4, 6, 2),
(5, 6, 3);

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `task`
--

INSERT INTO `task` (`id`, `name`, `creation_date`, `account_id`, `tasktype_id`, `taskprogress_id`) VALUES
(1, 'tachetest', '2020-11-26', 1, 5, 11),
(3, 'lolll', '2020-12-21', 3, 5, 9),
(4, 'sdtgseggressger', '2020-12-21', 3, 5, 12),
(5, 'mdr', '2021-01-14', 3, 6, 10);

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
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `taskhistory`
--

INSERT INTO `taskhistory` (`id`, `moving_date`, `task_id`, `taskprogress_id`, `user_id`) VALUES
(8, '2020-12-02', 1, 11, 4),
(9, '2020-12-21', 1, 10, 3),
(10, '2020-12-21', 1, 11, 4),
(11, '2020-12-21', 1, 10, 4),
(12, '2020-12-21', 3, 11, 3),
(13, '2020-12-21', 3, 9, 3),
(14, '2020-12-21', 4, 11, 3),
(15, '2020-12-21', 4, 9, 3),
(25, '2020-12-21', 3, 10, 3),
(26, '2021-01-06', 3, 10, 3),
(27, '2021-01-06', 3, 9, 3),
(28, '2021-01-06', 3, 10, 3),
(29, '2021-01-06', 3, 9, 3),
(30, '2021-01-06', 3, 10, 3),
(31, '2021-01-06', 3, 9, 3),
(32, '2021-01-06', 3, 10, 3),
(33, '2021-01-06', 3, 9, 3),
(34, '2021-01-06', 3, 10, 3),
(35, '2021-01-06', 3, 9, 3),
(36, '2021-01-06', 3, 10, 3),
(37, '2021-01-06', 3, 9, 3),
(38, '2021-01-06', 3, 10, 3),
(39, '2021-01-06', 3, 9, 3),
(40, '2021-01-06', 3, 10, 3),
(41, '2021-01-06', 3, 9, 3),
(46, '2021-01-07', 4, 9, 4),
(47, '2021-01-07', 4, 12, 4),
(48, '2021-01-07', 4, 9, 4),
(49, '2021-01-07', 4, 10, 4),
(50, '2021-01-07', 4, 9, 4),
(51, '2021-01-07', 4, 12, 4),
(52, '2021-01-07', 4, 9, 4),
(53, '2021-01-07', 4, 11, 4),
(54, '2021-01-07', 4, 12, 4),
(55, '2021-01-07', 4, 9, 4),
(56, '2021-01-07', 4, 10, 4),
(57, '2021-01-07', 4, 9, 4),
(58, '2021-01-07', 4, 12, 4),
(59, '2021-01-14', 5, 10, 3),
(60, '2021-01-14', 5, 9, 4),
(61, '2021-01-14', 5, 10, 4);

-- --------------------------------------------------------

--
-- Structure de la table `taskprogress`
--

DROP TABLE IF EXISTS `taskprogress`;
CREATE TABLE IF NOT EXISTS `taskprogress` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `label` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `taskprogress`
--

INSERT INTO `taskprogress` (`id`, `label`) VALUES
(9, 'To Do'),
(10, 'In Progress'),
(11, 'To Verify'),
(12, 'Done');

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `tasktype`
--

INSERT INTO `tasktype` (`id`, `label`, `color_id`) VALUES
(5, 'FEATURE', 1),
(6, 'BUG', 2);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `rank_id_FK` FOREIGN KEY (`rank_id`) REFERENCES `rank` (`id`);

--
-- Contraintes pour la table `right_rank`
--
ALTER TABLE `right_rank`
  ADD CONSTRAINT `right_rank_id_fk` FOREIGN KEY (`rank_id`) REFERENCES `rank` (`id`),
  ADD CONSTRAINT `rights_id_fk` FOREIGN KEY (`rights_id`) REFERENCES `rights` (`id`);

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
  ADD CONSTRAINT `taskhistory_taskprogress_id_FK` FOREIGN KEY (`taskprogress_id`) REFERENCES `taskprogress` (`id`);

--
-- Contraintes pour la table `tasktype`
--
ALTER TABLE `tasktype`
  ADD CONSTRAINT `color_color_id_FK` FOREIGN KEY (`color_id`) REFERENCES `color` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
