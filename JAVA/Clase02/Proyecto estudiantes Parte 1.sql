
-- ==============================================
-- CRUD (Create, Read, Update, Delete)
-- Operaciones básicas sobre la tabla de estudiantes
-- ==============================================

-- 🔹 READ: listar todos los estudiantes
SELECT * FROM estudiantes2022;

-- 🔹 CREATE: insertar un nuevo estudiante
INSERT INTO estudiantes2022 (nombre, apellido, telefono, email)
VALUES ('Lucía', 'Martínez', '3517894561', 'lucia.martinez@email.com');

-- 🔹 UPDATE: modificar los datos de un estudiante existente
UPDATE estudiantes2022
SET nombre = 'Lucía Belén', apellido = 'Martínez López'
WHERE idestudiantes2022 = 1;

-- 🔹 DELETE: eliminar un estudiante por su ID
DELETE FROM estudiantes2022
WHERE idestudiantes2022 = 1;

-- 🔹 Reiniciar el contador AUTO_INCREMENT de la tabla
ALTER TABLE estudiantes2022 AUTO_INCREMENT = 1;
