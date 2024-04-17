CREATE TABLE IF NOT EXISTS `usuario_permission` (
  `id_usuario` bigint(20) NOT NULL,
  `id_permission` bigint(20) NOT NULL,
  PRIMARY KEY (`id_usuario`,`id_permission`),
  KEY `fk_usuario_permission_permission` (`id_permission`),
  CONSTRAINT `fk_usuario_permission` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `fk_usuario_permission_permission` FOREIGN KEY (`id_permission`) REFERENCES `permission` (`id`)
) ENGINE=InnoDB;