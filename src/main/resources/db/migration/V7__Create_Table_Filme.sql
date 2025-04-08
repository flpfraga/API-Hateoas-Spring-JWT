CREATE TABLE IF NOT EXISTS `filme` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(255) NOT NULL,
  `diretor` varchar(255) DEFAULT NULL,
  `genero` varchar(40) DEFAULT NULL,
  `media_votos` decimal(2,1)  DEFAULT NULL,
  `contagem_votos` bigint(20) DEFAULT NULL,
  `detalhes` longtext DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;