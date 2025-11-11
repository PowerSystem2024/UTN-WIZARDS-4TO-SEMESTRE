-- ==============================================
-- CRUD: Create (Insertar), Read (Leer), Update (Actualizar), Delete (Eliminar)
-- ==============================================

-- Lista los estudiantes (READ)
SELECT * FROM estudiantes2022;

-- Insertar estudiante (CREATE)
INSERT INTO estudiantes2022 (nombre, apellido, telefono, email)
VALUES ('Lucía', 'Martínez', '3517894561', 'lucia.martinez@email.com');

-- Modificar estudiante (UPDATE)
UPDATE estudiantes2022
SET nombre = 'Lucía Belén', apellido = 'Martínez López'
WHERE idestudiantes2022 = 1;

-- Eliminar estudiante (DELETE)
DELETE FROM estudiantes2022
WHERE idestudiantes2022 = 1;

-- Reiniciar el contador AUTO_INCREMENT
ALTER TABLE estudiantes2022 AUTO_INCREMENT = 1;
