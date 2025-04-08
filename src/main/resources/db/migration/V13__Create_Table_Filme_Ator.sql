CREATE TABLE IF NOT EXISTS `filme_ator` (
    `id_filme` bigint(20) NOT NULL,
    `id_ator` bigint(20) NOT NULL,
    PRIMARY KEY (`id_filme`,`id_ator`),
    KEY `fk_filme_ator_ator` (`id_ator`),
    CONSTRAINT `fk_filme_ator` FOREIGN KEY (`id_filme`) REFERENCES `filme` (`id`),
    CONSTRAINT `fk_filme_ator_ator` FOREIGN KEY (`id_ator`) REFERENCES `ator` (`id`)
    ) ENGINE=InnoDB;