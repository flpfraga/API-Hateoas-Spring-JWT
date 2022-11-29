CREATE TABLE IF NOT EXISTS `user_movie` (
  `id_user` bigint(20) NOT NULL,
  `id_movie` bigint(20) NOT NULL,
  PRIMARY KEY (`id_user`,`id_movie`),
  KEY `fk_user_movie_movie` (`id_movie`),
  CONSTRAINT `fk_user_movie` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`),
  CONSTRAINT `fk_user_movie_movie` FOREIGN KEY (`id_movie`) REFERENCES `movie` (`id`)
) ENGINE=InnoDB;