// Declaración del package - indica la ubicación de esta clase en la estructura del proyecto
package utn.estudiantes.servicio;

// Import de la interfaz IEstudianteServicio para el servicio de estudiantes
import utn.estudiantes.servicio.IEstudianteServicio;
// Import de la entidad Estudiante para el servicio
import utn.estudiantes.modelo.Estudiante;
// Import del repositorio para acceder a la base de datos
import utn.estudiantes.repositorio.EstudianteRepositorio;
// Import de List para manejar colecciones de estudiantes
import java.util.List;
// Import de Spring para inyección de dependencias
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Clase de servicio que implementa la interfaz IEstudianteServicio
// @Service marca esta clase como un componente de servicio de Spring
@Service
public class EstudianteServicio implements IEstudianteServicio {
    
    // Inyección de dependencias del repositorio
    @Autowired
    private EstudianteRepositorio estudianteRepositorio;
    
    // Implementación del método para listar todos los estudiantes
    @Override
    public List<Estudiante> listarEstudinates() {
        List<Estudiante> estudiantes = estudianteRepositorio.findAll();
        return estudiantes;
    }
    
    // Implementación del método para buscar un estudiante por ID
    @Override
    public Estudiante buscarEstudiantePorId(Integer idEstudiante) {
        return estudianteRepositorio.findById(idEstudiante).orElse(null);
    }
    
    // Implementación del método para guardar un estudiante
    @Override
    public void guardarEstudiante(Estudiante estudiante) {
        estudianteRepositorio.save(estudiante);
    }
    
    // Implementación del método para eliminar un estudiante
    @Override
    public void eliminarEstudiante(Estudiante estudiante) {
        estudianteRepositorio.delete(estudiante);
    }
}
