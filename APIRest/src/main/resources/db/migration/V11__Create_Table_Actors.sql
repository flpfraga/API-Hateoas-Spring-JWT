CREATE TABLE IF NOT EXISTS `actors` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `id_movie` bigint(20) NOT NULL,	
  FOREIGN KEY (`id_movie`) REFERENCES `movie` (`id`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;