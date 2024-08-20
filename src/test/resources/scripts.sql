
DROP TABLE IF EXISTS alumno;
CREATE TABLE alumno (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    apellidos VARCHAR(100),
    fecha_nacimiento INT,
    activo INT,
    usuario_registra VARCHAR(50),
    fecha_registro DATE,
    usuario_actualiza VARCHAR(50),
    fecha_actualiza DATE
);