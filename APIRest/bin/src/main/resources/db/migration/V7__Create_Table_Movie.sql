CREATE TABLE IF NOT EXISTS `movie` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `director` varchar(255) DEFAULT NULL,
  `genre` varchar(40) DEFAULT NULL,
  `vote_average` decimal(2,1)  DEFAULT NULL,
  `vote_count` bigint(20) DEFAULT NULL,
  `details` longtext DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;