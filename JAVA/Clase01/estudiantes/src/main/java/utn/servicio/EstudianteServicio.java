// Define el paquete donde se encuentra esta clase
package utn.estudiantes.servicio;

// Importa la interfaz del servicio de estudiantes
import utn.estudiantes.servicio.IEstudianteServicio;
// Importa la entidad Estudiante
import utn.estudiantes.modelo.Estudiante;
// Importa el repositorio para operaciones con la base de datos
import utn.estudiantes.repositorio.EstudianteRepositorio;
// Importa la clase List para manejar colecciones
import java.util.List;
// Importa anotaciones de Spring para inyección de dependencias y componentes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Clase de servicio que implementa la lógica del negocio relacionada con estudiantes
@Service
public class EstudianteServicio implements IEstudianteServicio {
    
    // Inyección del repositorio de estudiantes
    @Autowired
    private EstudianteRepositorio estudianteRepositorio;
    
    // Retorna la lista completa de estudiantes
    @Override
    public List<Estudiante> listarEstudinates() {
        List<Estudiante> estudiantes = estudianteRepositorio.findAll();
        return estudiantes;
    }
    
    // Busca un estudiante por su ID
    @Override
    public Estudiante buscarEstudiantePorId(Integer idEstudiante) {
        Estudiante estudiante = estudianteRepositorio.findById(idEstudiante).orElse(null);
        return estudiante;
    }
    
    // Guarda o actualiza un estudiante
    @Override
    public void guardarEstudiante(Estudiante estudiante) {
        estudianteRepositorio.save(estudiante);
    }
    
    // Elimina un estudiante
    @Override
    public void eliminarEstudiante(Estudiante estudiante) {
        estudianteRepositorio.delete(estudiante);
    }
}
