CREATE TABLE IF NOT EXISTS `usuario_filme` (
  `id_usuario` bigint(20) NOT NULL,
  `id_filme` bigint(20) NOT NULL,
  PRIMARY KEY (`id_usuario`,`id_filme`),
  KEY `fk_usuario_filme_filme` (`id_filme`),
  CONSTRAINT `fk_usuario_filme` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `fk_usuario_filme_filme` FOREIGN KEY (`id_filme`) REFERENCES `filme` (`id`)
) ENGINE=InnoDB;