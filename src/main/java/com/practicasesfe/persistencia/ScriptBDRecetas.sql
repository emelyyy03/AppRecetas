-- Crear la base de datos
CREATE DATABASE AppRecetas2;
GO

USE AppRecetas2;
GO

-- Tabla de usuarios
CREATE TABLE Usuarios (
    UsuarioID INT PRIMARY KEY IDENTITY(1,1),
    NombreUsuario VARCHAR(100) NOT NULL,
    CorreoElectronico VARCHAR(100) UNIQUE NOT NULL,
    ContrasenaHash CHAR(32) NOT NULL,
    FechaRegistro DATETIME DEFAULT GETDATE(),
    EstadoCuenta TINYINT DEFAULT 1 CHECK (EstadoCuenta IN (0,1))
);

-- Tabla de categorías
CREATE TABLE Categorias (
    CategoriaID INT PRIMARY KEY IDENTITY(1,1),
    Nombre VARCHAR(50) NOT NULL UNIQUE,
    ImagenURL VARCHAR(255)
);

-- Tabla de recetas
CREATE TABLE Recetas (
    RecetaID INT PRIMARY KEY IDENTITY(1,1),
    UsuarioID INT FOREIGN KEY REFERENCES Usuarios(UsuarioID) ON DELETE CASCADE,
CategoriaID INT NULL FOREIGN KEY REFERENCES Categorias(CategoriaID) ON DELETE SET NULL,
    Titulo VARCHAR(100) NOT NULL,
    Descripcion VARCHAR(1000),
    TiempoCoccion INT NULL CHECK (TiempoCoccion > 0),
    ImagenURL VARCHAR(255),
    FechaCreacion DATETIME DEFAULT GETDATE()
);

-- Tabla global de ingredientes
CREATE TABLE Ingredientes (
    IngredienteID INT PRIMARY KEY IDENTITY(1,1),
    Nombre VARCHAR(100) UNIQUE NOT NULL
);

-- Relación entre recetas e ingredientes
CREATE TABLE RecetaIngredientes (
    RecetaID INT FOREIGN KEY REFERENCES Recetas(RecetaID) ON DELETE CASCADE,
    IngredienteID INT FOREIGN KEY REFERENCES Ingredientes(IngredienteID) ON DELETE CASCADE,
    Cantidad DECIMAL(6,2),
    Unidad VARCHAR(20),
    PRIMARY KEY (RecetaID, IngredienteID)
);

-- Tabla de reseñas
CREATE TABLE Reseñas (
    ReseñaID INT PRIMARY KEY IDENTITY(1,1),
    RecetaID INT FOREIGN KEY REFERENCES Recetas(RecetaID) ON DELETE CASCADE,
    UsuarioID INT FOREIGN KEY REFERENCES Usuarios(UsuarioID) ON DELETE NO ACTION,
    Calificacion DECIMAL(2,1) CHECK (Calificacion BETWEEN 0.0 AND 5.0),
    Comentario VARCHAR(500) NOT NULL,
    FechaReseña DATETIME DEFAULT GETDATE()
);


-- Tabla de favoritos (evita conflicto de cascadas)
CREATE TABLE Favoritos (
UsuarioID INT FOREIGN KEY REFERENCES Usuarios(UsuarioID) ON DELETE CASCADE,
RecetaID INT FOREIGN KEY REFERENCES Recetas(RecetaID) ON DELETE NO ACTION,
FechaAgregado DATETIME DEFAULT GETDATE(),
PRIMARY KEY (UsuarioID, RecetaID)
);

-- Tabla de tips para recetas
CREATE TABLE TipsReceta (
    TipID INT PRIMARY KEY IDENTITY(1,1),
    RecetaID INT FOREIGN KEY REFERENCES Recetas(RecetaID) ON DELETE CASCADE,
    Texto VARCHAR(500) NOT NULL
);

