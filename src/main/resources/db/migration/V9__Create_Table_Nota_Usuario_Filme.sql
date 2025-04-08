CREATE TABLE IF NOT EXISTS `nota_usuario_filme` (
  `id_usuario` bigint(20) NOT NULL,
  `id_filme` bigint(20) NOT NULL,
  `nota` int(20),
  PRIMARY KEY (`id_usuario`, `id_filme`),
  FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  FOREIGN KEY (`id_filme`) REFERENCES `filme` (`id`)
) ENGINE=InnoDB;