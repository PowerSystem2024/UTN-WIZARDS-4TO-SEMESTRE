// Declaración del package - indica la ubicación de esta clase en la estructura del proyecto
package utn.estudiantes.servicio;

// Import de la entidad Estudiante para el servicio
import utn.estudiantes.modelo.Estudiante;
// Import de List para manejar colecciones de estudiantes
import java.util.List;

// Interface para el servicio de estudiantes que define los métodos de negocio
public interface IEstudianteServicio {
    
    // Método para listar todos los estudiantes
    public List<Estudiante> listarEstudinates();
    
    // Método para buscar un estudiante por su ID
    public Estudiante buscarEstudiantePorId(Integer idEstudiante);
    
    // Método para guardar un estudiante (crear o actualizar)
    public void guardarEstudiante(Estudiante estudiante);
    
    // Método para eliminar un estudiante
    public void eliminarEstudiante(Estudiante estudiante);
}
