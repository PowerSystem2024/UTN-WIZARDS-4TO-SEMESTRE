// Define el paquete donde se encuentra la entidad
package utn.estudiantes.modelo;

// Importaciones de JPA para el mapeo de entidades a tablas en la base de datos
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// Importaciones de Lombok para generar automáticamente código repetitivo
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * Representa la entidad Estudiante dentro del sistema.
 * 
 * Esta clase se encuentra mapeada a la tabla 'estudiantes2022' en la base de datos.
 * Utiliza anotaciones de JPA para definir su estructura y comportamiento,
 * y Lombok para generar automáticamente métodos y constructores.
 */
@Entity
@Table(name = "estudiantes2022")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Estudiante {

    // Identificador único del estudiante (clave primaria, autogenerada)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idestudiantes2022")
    private Integer idEstudiante;
    
    // Campos principales de la entidad
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
}
