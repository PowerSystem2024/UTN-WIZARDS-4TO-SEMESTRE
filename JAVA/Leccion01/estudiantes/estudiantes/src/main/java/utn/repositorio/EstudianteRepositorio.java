// Declaración del package - indica la ubicación de esta clase en la estructura del proyecto
package utn.estudiantes.repositorio;

// Import de la entidad Estudiante para el repositorio
import utn.estudiantes.modelo.Estudiante;
// Import de JpaRepository para operaciones CRUD automáticas
import org.springframework.data.jpa.repository.JpaRepository;

// Interface para el repositorio de estudiantes que extiende JpaRepository
// JpaRepository<Estudiante, Integer> significa:
// - Estudiante: la entidad que maneja este repositorio
// - Integer: el tipo de dato de la clave primaria (idEstudiante)
public interface EstudianteRepositorio extends JpaRepository<Estudiante, Integer> {

}
