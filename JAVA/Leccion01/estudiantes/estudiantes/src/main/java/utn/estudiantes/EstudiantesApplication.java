// Declaración del package - indica la ubicación de esta clase en la estructura del proyecto  
// Este paquete contiene la clase principal de la aplicación Spring Boot
package utn.estudiantes;

/**
 * RESUMEN DE LA APLICACIÓN:
 * 
 * Esta aplicación Spring Boot implementa un sistema completo de gestión de estudiantes
 * con las siguientes características:
 * 
 * 1. ARQUITECTURA:
 *    - Capa de Modelo: Entidad Estudiante con JPA
 *    - Capa de Repositorio: EstudianteRepositorio con JpaRepository
 *    - Capa de Servicio: IEstudianteServicio y EstudianteServicio
 *    - Capa de Presentación: Menú interactivo en consola
 * 
 * 2. FUNCIONALIDADES:
 *    - Listar todos los estudiantes
 *    - Buscar estudiante por ID
 *    - Agregar nuevo estudiante con validaciones
 *    - Modificar estudiante existente
 *    - Eliminar estudiante con confirmación
 *    - Salir del sistema
 * 
 * 3. VALIDACIONES IMPLEMENTADAS:
 *    - Validación de opciones de menú (1-6)
 *    - Validación de entrada numérica para IDs
 *    - Validación de campos obligatorios (nombre, apellido)
 *    - Validación de formato de email
 *    - Validación de formato de teléfono
 *    - Confirmación para operaciones destructivas
 * 
 * 4. MANEJO DE ERRORES:
 *    - Captura de NumberFormatException
 *    - Manejo de errores de base de datos
 *    - Mensajes informativos para el usuario
 *    - Recuperación elegante de errores
 * 
 * 5. TECNOLOGÍAS UTILIZADAS:
 *    - Spring Boot 2.7.18
 *    - Spring Data JPA
 *    - MySQL Database
 *    - Lombok para reducir código boilerplate
 *    - SLF4J para logging
 *    - Maven para gestión de dependencias
 */

// Imports necesarios para la aplicación
import utn.estudiantes.modelo.Estudiante;           // Entidad Estudiante para mapear datos de BD
import org.springframework.boot.SpringApplication;     // Clase principal de Spring Boot
import org.springframework.boot.autoconfigure.SpringBootApplication; // Anotación para auto-configuración
import org.springframework.boot.CommandLineRunner;     // Interface para ejecutar código al iniciar
import org.springframework.beans.factory.annotation.Autowired; // Para inyección de dependencias
import org.slf4j.Logger;                            // Para logging (registro de eventos)
import org.slf4j.LoggerFactory;                     // Factory para crear loggers
import utn.estudiantes.servicio.IEstudianteServicio; // Interfaz del servicio de estudiantes
// Imports adicionales para el menú interactivo
import java.util.Scanner;                           // Para leer entrada del usuario
import java.util.List;                              // Para manejar listas de estudiantes
// Import para el manejo de excepciones

// Anotación que marca esta clase como aplicación Spring Boot
// Spring Boot se encarga de la configuración automática
@SpringBootApplication
public class EstudiantesApplication implements CommandLineRunner{
  
  // Inyección de dependencias del servicio de estudiantes
  // Spring automáticamente crea e inyecta una instancia de EstudianteServicio
  @Autowired
  private IEstudianteServicio estudianteServicio;

  // Logger para registrar eventos y mensajes de la aplicación
  // Se usa para mostrar información, errores y debug
  private static final Logger logger = LoggerFactory.getLogger(EstudiantesApplication.class);

  // Constante para saltos de línea (compatible con Windows, Linux, Mac)
  String nl = System.lineSeparator();

	/**
	 * Método principal de la aplicación
	 * Este es el punto de entrada cuando se ejecuta el programa
	 * @param args Argumentos de línea de comandos (no utilizados en este caso)
	 */
	public static void main(String[] args) {
    logger.info("Iniciando aplicación...");
    
    // Levantar el contenedor de Spring Boot
    // Esto inicializa toda la configuración, crea los beans y ejecuta la aplicación
    SpringApplication.run(EstudiantesApplication.class, args);
    
    logger.info("Aplicación finalizada!");
  }

  /**
   * Método que se ejecuta automáticamente después de que Spring Boot se inicializa
   * Implementa CommandLineRunner para ejecutar lógica personalizada al arrancar
   * @param args Argumentos de línea de comandos
   * @throws Exception Si ocurre algún error durante la ejecución
   */
  @Override
  public void run(String... args) throws Exception {
    logger.info("Ejecutando método run de Spring...");
    
    // Variable para controlar el bucle principal del menú
    var salir = false;
    
    // Scanner para leer entrada del usuario desde la consola
    var consola = new Scanner(System.in);
    
    // Bucle principal del menú - se ejecuta hasta que el usuario elija salir
    while(!salir){
      try{
        // Mostrar el menú de opciones al usuario
        mostrarMenu();
        
        // Ejecutar la opción seleccionada y determinar si debe salir
        salir = ejecutarOpciones(consola);
        
      }catch(Exception e){
        // Capturar cualquier error inesperado y mostrar mensaje amigable
        logger.error("❌ Error inesperado: " + e.getMessage());
        logger.info("💡 Por favor, intente nuevamente." + nl);
        logger.info("════════════════════════════════════════" + nl);
      }
    } //Fin while
    
    // Cerrar el Scanner para liberar recursos
    consola.close();
    logger.info("Aplicación finalizada!");
  }
  
  /**
   * Método para mostrar el menú principal de opciones
   * Despliega una interfaz visual atractiva con todas las opciones disponibles
   * El usuario puede elegir entre 6 opciones diferentes
   */
  private void mostrarMenu(){
    logger.info(nl);
    // Separadores visuales para hacer el menú más atractivo
    logger.info("════════════════════════════════════════");
    logger.info("******* Sistema de Estudiantes *******");
    logger.info("════════════════════════════════════════");
    
    // Opciones del menú principal
    logger.info(" 1. Listar Estudiantes");        // Mostrar todos los estudiantes
    logger.info(" 2. Buscar Estudiante");         // Buscar por ID específico
    logger.info(" 3. Agregar Estudiante");        // Crear nuevo estudiante
    logger.info(" 4. Modificar Estudiante");      // Editar datos existentes
    logger.info(" 5. Eliminar Estudiante");       // Borrar estudiante
    logger.info(" 6. Salir");                     // Terminar la aplicación
    
    logger.info("════════════════════════════════════════");
    logger.info("Eliga una opción: ");              // Prompt para entrada del usuario
  }
  
  /**
   * Método principal para procesar las opciones del menú
   * Lee la entrada del usuario, valida la opción y ejecuta la acción correspondiente
   * @param consola Scanner para leer entrada del usuario
   * @return true si debe salir de la aplicación, false para continuar
   */
  private boolean ejecutarOpciones(Scanner consola){
    var opcion = 0;      // Variable para almacenar la opción seleccionada
    var salir = false;   // Variable para controlar si debe salir de la aplicación
    
    try {
      // Leer la entrada del usuario y convertir a entero
      opcion = Integer.parseInt(consola.nextLine());
      
      // Validar que la opción esté en el rango válido (1-6)
      if(opcion < 1 || opcion > 6) {
        logger.info("❌ Error: Opción inválida. Debe elegir entre 1 y 6." + nl);
        logger.info("💡 Tip: Use números del 1 al 6 para elegir opciones" + nl);
        return salir; // No salir, volver al menú
      }
    } catch (NumberFormatException e) {
      // Capturar error si el usuario ingresa texto en lugar de número
      logger.info("❌ Error: Debe ingresar un número válido (1-6). Intente nuevamente." + nl);
      logger.info("💡 Tip: Use números del 1 al 6 para elegir opciones" + nl);
      return salir; // No salir, volver al menú
    }
    
    // Switch para manejar las diferentes opciones del menú
    switch(opcion){
      case 1 -> {
        // OPCIÓN 1: LISTAR TODOS LOS ESTUDIANTES
        // Obtiene todos los estudiantes de la base de datos y los muestra
        logger.info(nl + "=== LISTANDO ESTUDIANTES ===");
        
        // Llamar al servicio para obtener todos los estudiantes
        List<Estudiante> estudiantes = estudianteServicio.listarEstudinates();
        
        // Verificar si hay estudiantes registrados
        if(estudiantes.isEmpty()){
          logger.info("No hay estudiantes registrados." + nl);
        } else {
          // Mostrar el total de estudiantes y listarlos uno por uno
          logger.info("Total de estudiantes: " + estudiantes.size() + nl);
          estudiantes.forEach(estudiante -> logger.info("• " + estudiante.toString() + nl));
        }
        logger.info("================================" + nl);
      }//Fin caso 1
      
      case 2 -> {
        // OPCIÓN 2: BUSCAR ESTUDIANTE POR ID
        // Permite buscar un estudiante específico usando su ID
        logger.info(nl + "=== BUSCAR ESTUDIANTE ===");
        logger.info("Ingrese el ID del estudiante: ");
        
        try {
          // Leer el ID del estudiante desde la consola
          var idEstudiante = Integer.parseInt(consola.nextLine());
          
          // Validar que el ID sea un número positivo
          if(idEstudiante <= 0) {
            logger.info("❌ Error: El ID debe ser un número positivo." + nl);
          } else {
            // Buscar el estudiante en la base de datos
            var estudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);
            
            // Mostrar resultado de la búsqueda
            if(estudiante != null){
              logger.info("✓ Estudiante encontrado:");
              logger.info("  " + estudiante.toString() + nl);
            } else {
              logger.info("✗ No se encontró un estudiante con ID: " + idEstudiante + nl);
            }
          }
        } catch (NumberFormatException e) {
          // Capturar error si el usuario ingresa texto en lugar de número
          logger.info("❌ Error: Debe ingresar un número válido para el ID." + nl);
        }
        logger.info("===========================" + nl);
      }//Fin caso 2
      
      case 3 -> {
        // OPCIÓN 3: AGREGAR NUEVO ESTUDIANTE
        // Permite crear un nuevo estudiante con validaciones completas
        logger.info(nl + "=== AGREGAR ESTUDIANTE ===");
        
        // Solicitar datos del estudiante al usuario
        logger.info("Ingrese el nombre: ");
        var nombre = consola.nextLine().trim();        // trim() elimina espacios en blanco
        logger.info("Ingrese el apellido: ");
        var apellido = consola.nextLine().trim();
        logger.info("Ingrese el teléfono: ");
        var telefono = consola.nextLine().trim();
        logger.info("Ingrese el email: ");
        var email = consola.nextLine().trim();
        
        // VALIDACIONES DE DATOS
        // Verificar que nombre y apellido no estén vacíos
        if(nombre.isEmpty() || apellido.isEmpty()) {
          logger.info("❌ Error: Nombre y apellido son obligatorios." + nl);
          logger.info("=============================" + nl);
          return salir;
        }
        
        // Validar formato básico de email (debe contener @ y .)
        if(!email.contains("@") || !email.contains(".")) {
          logger.info("❌ Error: Formato de email inválido. Debe contener '@' y '.'" + nl);
          logger.info("=============================" + nl);
          return salir;
        }
        
        // Validar que el teléfono solo contenga números
        if(!telefono.matches("\\d+")) {
          logger.info("❌ Error: El teléfono solo debe contener números." + nl);
          logger.info("=============================" + nl);
          return salir;
        }
        
        try {
          // Crear nuevo objeto Estudiante
          var nuevoEstudiante = new Estudiante();
          nuevoEstudiante.setNombre(nombre);
          nuevoEstudiante.setApellido(apellido);
          nuevoEstudiante.setTelefono(telefono);
          nuevoEstudiante.setEmail(email);
          
          // Guardar en la base de datos (JPA asigna el ID automáticamente)
          estudianteServicio.guardarEstudiante(nuevoEstudiante);
          
          // Mostrar confirmación con el ID asignado
          logger.info("✓ Estudiante agregado exitosamente!");
          logger.info("  ID asignado: " + nuevoEstudiante.getIdEstudiante());
          logger.info("  Datos: " + nuevoEstudiante.toString() + nl);
        } catch (Exception e) {
          // Capturar errores de base de datos
          logger.info("❌ Error al guardar en la base de datos: " + e.getMessage() + nl);
        }
        logger.info("=============================" + nl);
      }//Fin caso 3
      
      case 4 -> {
        // OPCIÓN 4: MODIFICAR ESTUDIANTE EXISTENTE
        // Permite editar los datos de un estudiante existente
        logger.info(nl+"Modificando estudiante...");
        logger.info("Ingrese el ID del estudiante a modificar: ");
        
        try {
          // Leer el ID del estudiante a modificar
          var idEstudiante = Integer.parseInt(consola.nextLine());
          
          // Buscar el estudiante en la base de datos
          Estudiante estudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);
          
          if(estudiante != null){
            // Si el estudiante existe, solicitar nuevos datos
            logger.info("Nombre: ");
            var nombre = consola.nextLine();
            logger.info("Apellido: ");
            var apellido = consola.nextLine();
            logger.info("Telefono: ");
            var telefono = consola.nextLine();
            logger.info("Email: ");
            var email = consola.nextLine();
            
            // Actualizar los datos del estudiante
            estudiante.setNombre(nombre);
            estudiante.setApellido(apellido);
            estudiante.setTelefono(telefono);
            estudiante.setEmail(email);
            
            // Guardar los cambios en la base de datos
            estudianteServicio.guardarEstudiante(estudiante);
            
            // Mostrar confirmación
            logger.info("Estudiante modificado exitosamente!" + nl);
            logger.info("  " + estudiante.toString() + nl);
            logger.info("=============================" + nl);
          } else {
            // Si no se encuentra el estudiante
            logger.info("No se encontró un estudiante con ID: " + idEstudiante + nl);
          }
        } catch (NumberFormatException e) {
          // Capturar error si se ingresa texto en lugar de número
          logger.info("❌ Error: Debe ingresar un número válido para el ID." + nl);
        }
        logger.info("=============================" + nl);
      }//Fin caso 4
      
      case 5 -> {
        // OPCIÓN 5: ELIMINAR ESTUDIANTE
        // Permite eliminar un estudiante con confirmación de seguridad
        logger.info(nl+"Eliminar estudiante...");
        logger.info("Ingrese el ID del estudiante a eliminar: ");
        
        try {
          // Leer el ID del estudiante a eliminar
          var idEstudiante = Integer.parseInt(consola.nextLine());
          
          // Buscar el estudiante en la base de datos
          var estudianteEliminar = estudianteServicio.buscarEstudiantePorId(idEstudiante);
          
          if(estudianteEliminar != null){
            // Si el estudiante existe, mostrar sus datos y pedir confirmación
            logger.info("Estudiante a eliminar: " + estudianteEliminar.toString());
            logger.info("¿Está seguro? (s/n): ");
            var confirmacion = consola.nextLine().toLowerCase().trim();
            
            // Procesar la confirmación del usuario
            if(confirmacion.equals("s") || confirmacion.equals("si")){
              try {
                // Eliminar el estudiante de la base de datos
                estudianteServicio.eliminarEstudiante(estudianteEliminar);
                logger.info("✓ Estudiante eliminado exitosamente!" + nl);
              } catch (Exception e) {
                // Capturar errores durante la eliminación
                logger.info("❌ Error al eliminar: " + e.getMessage() + nl);
              }
            } else if(confirmacion.equals("n") || confirmacion.equals("no")){
              // Usuario canceló la operación
              logger.info("Operación cancelada." + nl);
            } else {
              // Respuesta inválida
              logger.info("❌ Error: Responda 's' para sí o 'n' para no." + nl);
            }
          } else {
            // No se encontró el estudiante
            logger.info("✗ Estudiante no encontrado con ID: " + idEstudiante + nl);
          }
        } catch (NumberFormatException e) {
          // Capturar error si se ingresa texto en lugar de número
          logger.info("❌ Error: Debe ingresar un número válido para el ID." + nl);
        }
        logger.info("=============================" + nl);
      }//Fin caso 5
      
      case 6 -> {
        // OPCIÓN 6: SALIR DEL SISTEMA
        // Termina la aplicación y cierra el bucle principal
        logger.info(nl+"Saliendo del sistema...");
        salir = true;  // Cambiar flag para salir del bucle principal
      }//Fin caso 6
      
      default -> {
        // CASO DEFAULT: OPCIÓN INVÁLIDA
        // Este caso no debería ejecutarse debido a las validaciones previas
        // pero se mantiene como medida de seguridad
        logger.info(nl+"Opción inválida. Por favor, elija una opción del 1 al 6." + nl);
      }//Fin default
    }//Fin switch
    
    // Retornar el estado de salida para controlar el bucle principal
    return salir;
  }//Fin método ejecutarOpciones

}//Fin clase EstudiantesApplication
