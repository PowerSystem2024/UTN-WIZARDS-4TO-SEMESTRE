// Define el paquete principal de la aplicación
package utn.estudiantes;

/**
 * Aplicación de gestión de estudiantes desarrollada con Spring Boot.
 * 
 * Estructura general:
 * - Modelo: entidad Estudiante mapeada con JPA.
 * - Repositorio: interfaz EstudianteRepositorio basada en JpaRepository.
 * - Servicio: interfaz y clase de implementación (IEstudianteServicio y EstudianteServicio).
 * - Presentación: menú interactivo por consola.
 * 
 * Funcionalidades principales:
 * - Listado general de estudiantes.
 * - Búsqueda por ID.
 * - Creación, modificación y eliminación de registros.
 * - Validaciones de entrada y manejo de errores controlado.
 * 
 * Tecnologías:
 * - Spring Boot, Spring Data JPA, MySQL, Lombok, SLF4J y Maven.
 */

import utn.estudiantes.modelo.Estudiante;           
import org.springframework.boot.SpringApplication;  
import org.springframework.boot.autoconfigure.SpringBootApplication; 
import org.springframework.boot.CommandLineRunner;  
import org.springframework.beans.factory.annotation.Autowired; 
import org.slf4j.Logger;                            
import org.slf4j.LoggerFactory;                     
import utn.estudiantes.servicio.IEstudianteServicio; 
import java.util.Scanner;                           
import java.util.List;                              

// Clase principal de la aplicación. Marca el punto de inicio de Spring Boot.
@SpringBootApplication
public class EstudiantesApplication implements CommandLineRunner {
  
  // Inyección del servicio que gestiona las operaciones sobre estudiantes.
  @Autowired
  private IEstudianteServicio estudianteServicio;

  // Logger para el registro de información, advertencias y errores.
  private static final Logger logger = LoggerFactory.getLogger(EstudiantesApplication.class);

  // Constante para representar saltos de línea según el sistema operativo.
  String nl = System.lineSeparator();

	/**
	 * Método principal de ejecución del programa.
	 * Inicializa el contexto de Spring Boot y lanza la aplicación.
	 */
	public static void main(String[] args) {
    logger.info("Iniciando la aplicación...");
    SpringApplication.run(EstudiantesApplication.class, args);
    logger.info("Aplicación finalizada correctamente.");
  }

  /**
   * Método ejecutado al iniciar la aplicación (implementa CommandLineRunner).
   * Contiene la lógica principal del menú interactivo por consola.
   */
  @Override
  public void run(String... args) throws Exception {
    logger.info("Ejecutando método run...");
    
    var salir = false;
    var consola = new Scanner(System.in);
    
    while(!salir){
      try{
        mostrarMenu();
        salir = ejecutarOpciones(consola);
      }catch(Exception e){
        logger.error("Error inesperado: " + e.getMessage());
        logger.info("Intente nuevamente." + nl);
        logger.info("════════════════════════════════════════" + nl);
      }
    }
    
    consola.close();
    logger.info("Aplicación cerrada correctamente.");
  }
  
  /**
   * Muestra el menú principal de opciones en consola.
   */
private void mostrarMenu() {
    logger.info(nl);
    logger.info("==========================================");
    logger.info("        📚 SISTEMA DE GESTIÓN DE ESTUDIANTES");
    logger.info("==========================================");
    
    logger.info(" 1️⃣  Mostrar todos los estudiantes");
    logger.info(" 2️⃣  Buscar estudiante por ID");
    logger.info(" 3️⃣  Registrar nuevo estudiante");
    logger.info(" 4️⃣  Actualizar datos de un estudiante");
    logger.info(" 5️⃣  Eliminar estudiante");
    logger.info(" 6️⃣  Salir del sistema");
    
    logger.info("==========================================");
    logger.info("Seleccione una opción: ");
}

  /**
   * Ejecuta la acción correspondiente según la opción seleccionada por el usuario.
   * @param consola Scanner para leer la entrada desde consola.
   * @return true si el usuario decide salir, false en caso contrario.
   */
  private boolean ejecutarOpciones(Scanner consola){
    var opcion = 0;
    var salir = false;
    
    try {
      opcion = Integer.parseInt(consola.nextLine());
      if(opcion < 1 || opcion > 6) {
        logger.info("Opción fuera de rango. Elija entre 1 y 6." + nl);
        return salir;
      }
    } catch (NumberFormatException e) {
      logger.info("Debe ingresar un número entre 1 y 6." + nl);
      return salir;
    }
    
    switch(opcion){
      case 1 -> {
        logger.info(nl + "=== LISTA DE ESTUDIANTES ===");
        List<Estudiante> estudiantes = estudianteServicio.listarEstudinates();
        if(estudiantes.isEmpty()){
          logger.info("No se encontraron registros." + nl);
        } else {
          logger.info("Total registrados: " + estudiantes.size() + nl);
          estudiantes.forEach(est -> logger.info("• " + est.toString() + nl));
        }
        logger.info("================================" + nl);
      }
      
      case 2 -> {
        logger.info(nl + "=== BÚSQUEDA DE ESTUDIANTE ===");
        logger.info("Ingrese el ID a buscar: ");
        try {
          var id = Integer.parseInt(consola.nextLine());
          if(id <= 0) {
            logger.info("El ID debe ser positivo." + nl);
          } else {
            var estudiante = estudianteServicio.buscarEstudiantePorId(id);
            if(estudiante != null){
              logger.info("Estudiante encontrado: " + estudiante + nl);
            } else {
              logger.info("No existe registro con ID: " + id + nl);
            }
          }
        } catch (NumberFormatException e) {
          logger.info("Ingrese un número válido para el ID." + nl);
        }
      }
      
      case 3 -> {
        logger.info(nl + "=== NUEVO ESTUDIANTE ===");
        logger.info("Nombre: ");
        var nombre = consola.nextLine().trim();
        logger.info("Apellido: ");
        var apellido = consola.nextLine().trim();
        logger.info("Teléfono: ");
        var telefono = consola.nextLine().trim();
        logger.info("Email: ");
        var email = consola.nextLine().trim();
        
        if(nombre.isEmpty() || apellido.isEmpty()) {
          logger.info("Nombre y apellido son obligatorios." + nl);
          return salir;
        }
        if(!email.contains("@") || !email.contains(".")) {
          logger.info("Email con formato incorrecto." + nl);
          return salir;
        }
        if(!telefono.matches("\\d+")) {
          logger.info("El teléfono solo debe contener números." + nl);
          return salir;
        }
        try {
          var nuevo = new Estudiante();
          nuevo.setNombre(nombre);
          nuevo.setApellido(apellido);
          nuevo.setTelefono(telefono);
          nuevo.setEmail(email);
          estudianteServicio.guardarEstudiante(nuevo);
          logger.info("Registro exitoso. ID asignado: " + nuevo.getIdEstudiante() + nl);
        } catch (Exception e) {
          logger.info("Error al guardar: " + e.getMessage() + nl);
        }
      }
      
      case 4 -> {
        logger.info(nl + "=== MODIFICAR ESTUDIANTE ===");
        logger.info("Ingrese el ID a modificar: ");
        try {
          var id = Integer.parseInt(consola.nextLine());
          var estudiante = estudianteServicio.buscarEstudiantePorId(id);
          if(estudiante != null){
            logger.info("Nombre: ");
            estudiante.setNombre(consola.nextLine());
            logger.info("Apellido: ");
            estudiante.setApellido(consola.nextLine());
            logger.info("Teléfono: ");
            estudiante.setTelefono(consola.nextLine());
            logger.info("Email: ");
            estudiante.setEmail(consola.nextLine());
            estudianteServicio.guardarEstudiante(estudiante);
            logger.info("Datos actualizados correctamente." + nl);
          } else {
            logger.info("No existe estudiante con ID: " + id + nl);
          }
        } catch (NumberFormatException e) {
          logger.info("ID no válido. Debe ser un número." + nl);
        }
      }
      
      case 5 -> {
        logger.info(nl + "=== ELIMINAR ESTUDIANTE ===");
        logger.info("Ingrese el ID a eliminar: ");
        try {
          var id = Integer.parseInt(consola.nextLine());
          var estudiante = estudianteServicio.buscarEstudiantePorId(id);
          if(estudiante != null){
            logger.info("Estudiante: " + estudiante);
            logger.info("¿Confirmar eliminación? (s/n): ");
            var conf = consola.nextLine().toLowerCase().trim();
            if(conf.equals("s") || conf.equals("si")){
              estudianteServicio.eliminarEstudiante(estudiante);
              logger.info("Eliminación completada." + nl);
            } else if(conf.equals("n") || conf.equals("no")){
              logger.info("Operación cancelada." + nl);
            } else {
              logger.info("Respuesta inválida. Use 's' o 'n'." + nl);
            }
          } else {
            logger.info("No existe estudiante con ese ID." + nl);
          }
        } catch (NumberFormatException e) {
          logger.info("Ingrese un número válido para el ID." + nl);
        }
      }
      
      case 6 -> {
        logger.info(nl + "Saliendo del sistema...");
        salir = true;
      }
      
      default -> logger.info("Opción no válida." + nl);
    }
    
    return salir;
  }

}
