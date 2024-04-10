CREATE TABLE IF NOT EXISTS `usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome_usuario` varchar(255) DEFAULT NULL,
  `nome_completo` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `account_non_expired` bit(1) DEFAULT NULL,
  `account_non_locked` bit(1) DEFAULT NULL,
  `credentials_non_expired` bit(1) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  `criado_em` date NOT NULL,
  `atualizado_em`date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_name` (`nome_usuario`)
) ENGINE=InnoDB;