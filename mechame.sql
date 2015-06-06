-- phpMyAdmin SQL Dump
-- version 4.3.12
-- http://www.phpmyadmin.net
--
-- Host: mysql02-farm58.kinghost.net
-- Tempo de geração: 02/06/2015 às 01:04
-- Versão do servidor: 5.5.43-log
-- Versão do PHP: 5.3.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Banco de dados: `mechame`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `lembrete`
--

CREATE TABLE IF NOT EXISTS `lembrete` (
  `id` bigint(20) NOT NULL,
  `idTelefone` bigint(20) NOT NULL,
  `titulo` varchar(150) NOT NULL,
  `raio` double NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `ativo` tinyint(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura para tabela `log_acesso`
--

CREATE TABLE IF NOT EXISTS `log_acesso` (
  `id` bigint(20) NOT NULL,
  `data_log` datetime NOT NULL,
  `telefone` bigint(20) NOT NULL,
  `descricao` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `telefone` bigint(20) NOT NULL,
  `nome` varchar(150) NOT NULL,
  `email` varchar(150) NOT NULL,
  `senha` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `usuario`
--

INSERT INTO `usuario` (`telefone`, `nome`, `email`, `senha`) VALUES
(5511912342345, 'Pablo Henrique', 'teste@gmail.com', 'senha2'),
(5511956781234, 'Rafael', 'rafa@gmail.com', 'senha3'),
(5511987412157, 'Pablo Vianini', 'pablovianini@gmail.com', 'senha1'),
(5511987654321, 'Guilherme', 'guilherme@gmail.com', 'senha4');

--
-- Índices de tabelas apagadas
--

--
-- Índices de tabela `lembrete`
--
ALTER TABLE `lembrete`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `log_acesso`
--
ALTER TABLE `log_acesso`
  ADD PRIMARY KEY (`id`), ADD KEY `IX_DATALOG` (`data_log`);

--
-- Índices de tabela `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`telefone`);

--
-- AUTO_INCREMENT de tabelas apagadas
--

--
-- AUTO_INCREMENT de tabela `lembrete`
--
ALTER TABLE `lembrete`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de tabela `log_acesso`
--
ALTER TABLE `log_acesso`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
