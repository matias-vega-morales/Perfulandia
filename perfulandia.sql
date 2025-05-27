-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-05-2025 a las 18:14:53
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

-- Base de datos: perfulandia

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

-- --------------------------------------------------------
-- Tabla: almacen
CREATE TABLE `almacen` (
  `almacen_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `direccion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`almacen_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------
-- Tabla: clientes
CREATE TABLE `clientes` (
  `id_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) DEFAULT NULL,
  `nombre_completo` varchar(255) DEFAULT NULL,
  `rut` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_cliente`),
  KEY `id_usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

INSERT INTO `clientes` (`id_cliente`, `id_usuario`, `nombre_completo`, `rut`, `direccion`, `telefono`) VALUES
(3, 8, 'Jp Gonzales', '11.111.111-1', 'Av. Colo Colo es chile', '+56912345678');

-- --------------------------------------------------------
-- Tabla: cupones
CREATE TABLE `cupones` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(30) DEFAULT NULL,
  `descuento` decimal(5,2) DEFAULT NULL,
  `valido_hasta` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------
-- Tabla: detalle_venta
CREATE TABLE `detalle_venta` (
  `id_detalle` int(11) NOT NULL AUTO_INCREMENT,
  `id_venta` int(11) DEFAULT NULL,
  `id_producto` int(11) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `precio_unitario` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id_detalle`),
  KEY `id_venta` (`id_venta`),
  KEY `id_producto` (`id_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------
-- Tabla: envios
CREATE TABLE `envios` (
  `id_envio` int(11) NOT NULL AUTO_INCREMENT,
  `id_venta` int(11) DEFAULT NULL,
  `direccion_envio` varchar(150) DEFAULT NULL,
  `estado_envio` varchar(30) DEFAULT NULL,
  `fecha_envio` date DEFAULT NULL,
  `fecha_entrega` date DEFAULT NULL,
  PRIMARY KEY (`id_envio`),
  KEY `id_venta` (`id_venta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------
-- Tabla: inventario
CREATE TABLE `inventario` (
  `id_inventario` int(11) NOT NULL AUTO_INCREMENT,
  `id_producto` int(11) DEFAULT NULL,
  `stock_disponible` int(11) DEFAULT NULL,
  `ubicacion_bodega` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_inventario`),
  KEY `id_producto` (`id_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------
-- Tabla: movimiento_inventario
CREATE TABLE `movimiento_inventario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cantidad` int(11) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `fecha` datetime(6) DEFAULT NULL,
  `inventario_id` int(11) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `inventario_id` (`inventario_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------
-- Tabla: productos
CREATE TABLE `productos` (
  `id_producto` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `precio` decimal(38,2) DEFAULT NULL,
  `categoria` varchar(255) DEFAULT NULL,
  `marca` varchar(255) DEFAULT NULL,
  `mml` varchar(255) DEFAULT NULL,
  `activo` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

INSERT INTO `productos` (`id_producto`, `nombre`, `descripcion`, `precio`, `categoria`, `marca`, `mml`) VALUES
(3, 'Dolce & Gabbana Light Blue Pour Homme EDT', 'Fragancia masculina fresca y vibrante, inspirada en el espíritu del Mediterráneo. Combina notas cítricas y amaderadas para una sensación limpia, energética y sofisticada. Ideal para el uso diario en climas cálidos o actividades informales.', 48990.00, 'Fragancia masculina', 'Dolce & Gabbana', '100'),
(4, 'Dior Sauvage Eau de Toilette', 'Una fragancia masculina fresca y vibrante, inspirada en espacios abiertos y naturaleza salvaje. Combina notas cítricas y amaderadas para una sensación limpia, energética y sofisticada. Ideal para el uso diario en climas cálidos o actividades informales.', 110990.00, 'Fragancia masculina', 'Dior', '200');

-- --------------------------------------------------------
-- Tabla: reportes
CREATE TABLE `reportes` (
  `id_reporte` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_reporte` varchar(100) DEFAULT NULL,
  `fecha_generacion` date DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `json_datos` text DEFAULT NULL,
  PRIMARY KEY (`id_reporte`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------
-- Tabla: soporte
CREATE TABLE `soporte` (
  `id_ticket` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) DEFAULT NULL,
  `tipo_ticket` varchar(100) DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `estado` varchar(30) DEFAULT NULL,
  `fecha_creacion` date DEFAULT NULL,
  `fecha_resolucion` date DEFAULT NULL,
  PRIMARY KEY (`id_ticket`),
  KEY `id_usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------
-- Tabla: usuarios
CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_usuario` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `contrasena` varchar(255) DEFAULT NULL,
  `rol` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `nombre_usuario` (`nombre_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `usuarios` (`id_usuario`, `nombre_usuario`, `email`, `contrasena`, `rol`, `estado`) VALUES
(8, 'juanbustos', 'nuevo@email.com', '$2a$10$lt154kkMDd1fR7Ak6ql5v.YsGzGRVrZcURoUnsUZE1bkGMrIgZSwi', 'cliente', 'activo'),
(10, 'admin', 'admin01@ejemplo.com', '$2a$10$8j2oZXxk8hEfNtjzs7tx7./hodZx0BC27cP4WbZIpMDEYfij4CRHi', 'admin', 'activo');

-- --------------------------------------------------------
-- Tabla: vendedores
CREATE TABLE `vendedores` (
  `id_vendedor` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) DEFAULT NULL,
  `nombre_completo` varchar(255) DEFAULT NULL,
  `rut` varchar(255) DEFAULT NULL,
  `area_ventas` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_vendedor`),
  KEY `id_usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------
-- Tabla: ventas
CREATE TABLE `ventas` (
  `id_venta` int(11) NOT NULL AUTO_INCREMENT,
  `id_cliente` int(11) DEFAULT NULL,
  `id_vendedor` int(11) DEFAULT NULL,
  `fecha_venta` date DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id_venta`),
  KEY `id_cliente` (`id_cliente`),
  KEY `id_vendedor` (`id_vendedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------
-- Restricciones de claves foráneas
ALTER TABLE `clientes`
  ADD CONSTRAINT `clientes_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `detalle_venta`
  ADD CONSTRAINT `detalle_venta_ibfk_1` FOREIGN KEY (`id_venta`) REFERENCES `ventas` (`id_venta`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detalle_venta_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `envios`
  ADD CONSTRAINT `envios_ibfk_1` FOREIGN KEY (`id_venta`) REFERENCES `ventas` (`id_venta`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `inventario`
  ADD CONSTRAINT `inventario_ibfk_1` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `soporte`
  ADD CONSTRAINT `soporte_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `vendedores`
  ADD CONSTRAINT `vendedores_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `ventas`
  ADD CONSTRAINT `ventas_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ventas_ibfk_2` FOREIGN KEY (`id_vendedor`) REFERENCES `vendedores` (`id_vendedor`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `movimiento_inventario`
  ADD CONSTRAINT `movimiento_inventario_ibfk_1` FOREIGN KEY (`inventario_id`) REFERENCES `inventario` (`id_inventario`) ON DELETE CASCADE ON UPDATE CASCADE;

COMMIT;
