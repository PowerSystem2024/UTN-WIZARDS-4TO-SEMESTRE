// Declaración del package - indica la ubicación de esta clase en la estructura del proyecto
package utn.estudiantes.modelo;

// Imports de Javax Persistence para manejo de entidades de base de datos (Spring Boot 2.x)
import javax.persistence.Entity;         // Para marcar la clase como entidad de base de datos
import javax.persistence.GeneratedValue;  // Para generar valores automáticamente
import javax.persistence.GenerationType; // Estrategias de generación de IDs
import javax.persistence.Id;             // Para marcar campos como clave primaria
// Imports de Lombok para reducir código repetitivo
import lombok.Data;         // Genera getters, setters, equals, hashCode, toString
import lombok.NoArgsConstructor;  // Genera constructor sin parámetros
import lombok.AllArgsConstructor; // Genera constructor con todos los parámetros
import lombok.ToString;     // Genera método toString personalizado

// Anotación de Jakarta Persistence para marcar esta clase como una entidad de base de datos
@Entity
// Anotación de Lombok que genera automáticamente:
// - Getters y setters para todos los campos
// - equals() y hashCode()
// - toString()
@Data
// Anotación de Lombok que genera un constructor sin parámetros (necesario para JPA)
@NoArgsConstructor
// Anotación de Lombok que genera un constructor con todos los parámetros
@AllArgsConstructor
// Anotación de Lombok que genera un método toString() personalizado
@ToString
public class Estudiante {
    // Campo ID con anotaciones de JPA para clave primaria auto-generada
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEstudiante;
    
    // Campos de la entidad Estudiante
    private String nombre;        // Nombre del estudiante
    private String apellido;      // Apellido del estudiante
    private String telefono;      // Teléfono del estudiante
    private String email;         // Email del estudiante
}
