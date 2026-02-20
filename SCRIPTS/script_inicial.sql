USE master;
GO

-- Se crea la base de datos
CREATE DATABASE InventarioBD;
GO

USE InventarioBD;
GO

-- Tabla de roles
CREATE TABLE roles (
    idRol INT PRIMARY KEY IDENTITY(1,1),
    nombreRol NVARCHAR(50) NOT NULL
);

-- Insertar roles iniciales
INSERT INTO roles (nombreRol) VALUES ('Administrador');
INSERT INTO roles (nombreRol) VALUES ('Almacenista');

-- Tabla de usuarios
CREATE TABLE usuarios (
    idUsuario INT PRIMARY KEY IDENTITY(1,1),
    nombre NVARCHAR(100) NOT NULL,
    correo NVARCHAR(50) NOT NULL,
    contrasena NVARCHAR(255) NOT NULL,
    idRol INT NOT NULL,
    estatus INT DEFAULT 1,
    CONSTRAINT FK_usuarios_roles FOREIGN KEY (idRol) REFERENCES roles(idRol),
    CONSTRAINT UQ_usuarios_correo UNIQUE (correo)
);

-- Tabla de productos
CREATE TABLE productos (
    idProducto INT PRIMARY KEY IDENTITY(1,1),
    nombre NVARCHAR(100) NOT NULL,
    cantidad INT DEFAULT 0,
    estatus NVARCHAR(20) DEFAULT 'ACTIVO',
    fechaCreacion DATETIME DEFAULT GETDATE()
);

CREATE TABLE movimientos (
    idMovimiento INT PRIMARY KEY IDENTITY(1,1),
    idProducto INT NOT NULL,
    idUsuario INT NOT NULL,
    tipoMovimiento VARCHAR(20) NOT NULL, -- Etrada | SalidaA
    cantidad INT NOT NULL,
    fechaHora DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (idProducto) REFERENCES productos(idProducto),
    FOREIGN KEY (idUsuario) REFERENCES usuarios(idUsuario)
);
   
  -- Insertar usuaris
INSERT INTO usuarios (nombre, correo, contrasena, idRol, estatus)
VALUES ('Admin', 'admin@admin.com', '1234', 1, 1);

INSERT INTO usuarios (nombre, correo, contrasena, idRol, estatus)
VALUES ('Almacenista', 'daniel@daniel.com', '1234', 2, 1);

-- Cambiar contraseña por una encriptada
UPDATE usuarios 
SET contrasena = '$2a$10$Q57b4E5S.lzixDImw8sFROnO4KtFSI5tioC/4unsKbDmlKiOs4qY6'
WHERE correo = 'admin@admin.com';

UPDATE usuarios 
SET contrasena = '$2a$10$Q57b4E5S.lzixDImw8sFROnO4KtFSI5tioC/4unsKbDmlKiOs4qY6'
WHERE correo = 'daniel@daniel.com';

-- Poblar tabla productos

INSERT INTO productos (nombre, cantidad, estatus)
VALUES
('Taladro Bosch', 15, 'ACTIVO'),
('Broca 1/2 Cobalto', 50, 'ACTIVO'),
('Sierra Circular', 8, 'ACTIVO'),
('Corta Pernos 18"', 20, 'ACTIVO'),
('Esmeril Angular', 12, 'ACTIVO'),
('Juego de Llaves Allen', 30, 'ACTIVO'),
('Martillo Industrial', 25, 'ACTIVO'),
('Desarmador Plano', 40, 'ACTIVO'),
('Pinzas de Presión', 18, 'ACTIVO'),
('Flexómetro 5m', 35, 'ACTIVO');


-- Poblar tabla movimientos,por fines practicos y mostrar historial poblado.

INSERT INTO movimientos (idProducto, idUsuario, tipoMovimiento, cantidad)
VALUES
-- ENTRADAS
(1, 1, 'ENTRADA', 10),
(2, 2, 'ENTRADA', 25),
(3, 1, 'ENTRADA', 5),
(4, 2, 'ENTRADA', 15),
-- SALIDAS
(1, 2, 'SALIDA', 3),
(2, 1, 'SALIDA', 10),
(4, 1, 'SALIDA', 5),
(5, 2, 'SALIDA', 2),
(6, 1, 'SALIDA', 8);
