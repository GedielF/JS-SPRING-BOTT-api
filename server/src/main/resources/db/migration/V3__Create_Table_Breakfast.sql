CREATE TABLE `breakfast` (
  `id` INT(10) AUTO_INCREMENT PRIMARY KEY,
  `nome` longtext,
  `dataataual` datetime(6) NOT NULL,
  `cpf` decimal(65,2) NOT NULL,
  `comida` longtext,
	`bebida` longtext,
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
