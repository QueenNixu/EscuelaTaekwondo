# Escuela de Taekwondo App

Esta es una aplicación de gestión para una escuela de taekwondo local, desarrollada en Java con interfaz de usuario utilizando Swing y conectándose a una base de datos MySQL.

## Funcionalidades

- Anotar un nuevo estudiante
- Ver la lista de estudiantes
- Ver detalles de un estudiante
- Editar información de un estudiante
- Eliminar un estudiante
- Agregar un nuevo torneo
- Ver la lista de torneos
- Ver detalles de un torneo
- Editar información de un torneo
- Eliminar un torneo
- Inscribir un estudiante en un torneo
- Dar de baja a un estudiante de un torneo
- Otorgar medallas a los estudiantes (oro, plata, bronce - 3er lugar y 4to lugar)
- Retirar medallas de los estudiantes

## Tecnologías Utilizadas

- Java (con interfaz de usuario Swing)
- MySQL
- JCalendar de toedter.com

## Base de Datos MySQL

Para probar el programa, primero necesitas configurar la base de datos MySQL. A continuación se muestra el script SQL para crear la base de datos y las tablas necesarias:

```sql
-- Verificar si la base de datos existe antes de crearla
CREATE DATABASE IF NOT EXISTS escuela_taekwondo;

-- Usar la base de datos
USE escuela_taekwondo;

-- Crear la tabla taekwondoka si no existe
CREATE TABLE IF NOT EXISTS taekwondoka (
    id INT auto_increment PRIMARY KEY,
    nombre VARCHAR(50) CHARACTER SET utf8mb4,
    apellido VARCHAR(50) CHARACTER SET utf8mb4,
    edad VARCHAR(10) CHARACTER SET utf8mb4,
    direccion VARCHAR(100) CHARACTER SET utf8mb4,
    email VARCHAR(100) CHARACTER SET utf8mb4,
    celular VARCHAR(15) CHARACTER SET utf8mb4,
    cinturon VARCHAR(50) CHARACTER SET utf8mb4,
    punta VARCHAR(50) CHARACTER SET utf8mb4
);

-- Crear la tabla torneo si no existe
CREATE TABLE IF NOT EXISTS torneo (
    id INT auto_increment PRIMARY KEY,
    nombre VARCHAR(255) CHARACTER SET utf8mb4,
    fecha DATE,
    participantes INT,
    idGanadorOro INT NULL,
    idGanadorPlata INT NULL,
    idGanadorBronce3 INT NULL,
    idGanadorBronce4 INT NULL,
    FOREIGN KEY (idGanadorOro) REFERENCES taekwondoka(id),
    FOREIGN KEY (idGanadorPlata) REFERENCES taekwondoka(id),
    FOREIGN KEY (idGanadorBronce3) REFERENCES taekwondoka(id),
    FOREIGN KEY (idGanadorBronce4) REFERENCES taekwondoka(id)
);

-- Crear la tabla torneo_taekwondoka si no existe
CREATE TABLE IF NOT EXISTS torneo_taekwondoka (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idTorneo INT,
    idTaekwondoka INT,
    FOREIGN KEY (idTorneo) REFERENCES torneo(id),
    FOREIGN KEY (idTaekwondoka) REFERENCES taekwondoka(id)
);
```
## Uso

Este proyecto es una aplicación de gestión de una escuela de taekwondo local, desarrollada con Java y utilizando Swing para la interfaz de usuario. Se conecta a una base de datos MySQL.

## Autor

- [Reategui Agustin](https://github.com/QueenNixu)

## Licencia

Este proyecto está bajo la Licencia MIT.
