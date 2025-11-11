// Define el paquete donde se encuentra la interfaz del servicio
package utn.estudiantes.servicio;

import utn.estudiantes.modelo.Estudiante;
import java.util.List;

/**
 * Interfaz que define las operaciones de negocio
 * para la gestión de estudiantes.
 * 
 * Contiene los métodos principales que deberán
 * implementarse en la capa de servicio.
 */
public interface IEstudianteServicio {
    
    /**
     * Obtiene la lista completa de estudiantes registrados.
     * @return Lista de entidades Estudiante.
     */
    List<Estudiante> listarEstudinates();
    
    /**
     * Busca un estudiante por su identificador único.
     * @param idEstudiante ID del estudiante.
     * @return Entidad Estudiante correspondiente, o null si no existe.
     */
    Estudiante buscarEstudiantePorId(Integer idEstudiante);
    
    /**
     * Guarda o actualiza la información de un estudiante.
     * @param estudiante Entidad a persistir.
     */
    void guardarEstudiante(Estudiante estudiante);
    
    /**
     * Elimina un estudiante de la base de datos.
     * @param estudiante Entidad a eliminar.
     */
    void eliminarEstudiante(Estudiante estudiante);
}
