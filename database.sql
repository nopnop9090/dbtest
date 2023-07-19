SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

DROP TABLE IF EXISTS `personen`;
CREATE TABLE IF NOT EXISTS `personen` (
  `PersonID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(250) NOT NULL,
  `Vorname` varchar(250) DEFAULT NULL,
  `Groesse` double DEFAULT NULL,
  `Gewicht` double DEFAULT NULL,
  `Kategorie` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`PersonID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `personen` (`PersonID`, `Name`, `Vorname`, `Groesse`, `Gewicht`, `Kategorie`) VALUES
(1, 'Muster', 'Giesela', 1.63, 45.5, 'Untergewicht'),
(2, 'Beispiel', 'Max', 1.23, 99.9, 'Übergewicht'),
(3, 'Local', 'Horst', 1.95, 85.5, 'Normalgewicht'),
(4, 'Huber', NULL, NULL, NULL, NULL),
(5, 'Meier', 'Babsi', 1.89, 70.3, 'Normalgewicht'),
(6, 'Schmidt', NULL, NULL, NULL, NULL),
(7, 'Meier', 'Babsi', 1.89, 70.3, 'Normalgewicht'),
(8, 'Wetzer', 'Matthias', 1.95, 56.4, 'Untergewicht'),
(9, 'Wurst', 'Hans', NULL, NULL, NULL),
(10, 'Belsa', 'Elsa', NULL, NULL, NULL);
COMMIT;