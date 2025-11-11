package UTN.presentacion;

import UTN.datos.EstudianteDAO;
import UTN.dominio.Estudiante;
import java.util.Scanner;

public class SistemaEstudiantesApp {

    public static void main(String[] args) {
        var salir = false;
        var consola = new Scanner(System.in);
        var estudianteDao = new EstudianteDAO(); // Instancia única del DAO para gestionar los estudiantes

        while (!salir) {
            try {
                mostrarMenu();
                salir = ejecutarOpciones(consola, estudianteDao);
            } catch (Exception e) {
                System.out.println("⚠️  Ocurrió un error al ejecutar la operación: " + e.getMessage());
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n==========================================");
        System.out.println("        📚 SISTEMA DE GESTIÓN DE ESTUDIANTES");
        System.out.println("==========================================");
        System.out.println(" 1️⃣  Mostrar todos los estudiantes");
        System.out.println(" 2️⃣  Buscar estudiante por ID");
        System.out.println(" 3️⃣  Registrar nuevo estudiante");
        System.out.println(" 4️⃣  Actualizar datos de un estudiante");
        System.out.println(" 5️⃣  Eliminar estudiante");
        System.out.println(" 6️⃣  Salir del sistema");
        System.out.println("==========================================");
        System.out.print("Seleccione una opción: ");
    }

    private static boolean ejecutarOpciones(Scanner consola, EstudianteDAO estudianteDao) {
        var opcion = Integer.parseInt(consola.nextLine());
        var salir = false;

        switch (opcion) {
            case 1 -> { // Listar estudiantes
                System.out.println("\n📋 LISTADO DE ESTUDIANTES:");
                var estudiantes = estudianteDao.listarEstudiantes();
                estudiantes.forEach(System.out::println);
            }
            case 2 -> { // Buscar estudiante por ID
                System.out.print("\n🔍 Ingrese el ID del estudiante a buscar: ");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                var estudiante = new Estudiante(idEstudiante);
                var encontrado = estudianteDao.buscarEstudiantePorId(estudiante);
                if (encontrado)
                    System.out.println("✅ Estudiante encontrado: " + estudiante);
                else
                    System.out.println("❌ No se encontró ningún estudiante con ese ID.");
            }
            case 3 -> { // Agregar estudiante
                System.out.println("\n🆕 REGISTRAR NUEVO ESTUDIANTE:");
                System.out.print("Nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Teléfono: ");
                var telefono = consola.nextLine();
                System.out.print("Correo electrónico: ");
                var email = consola.nextLine();

                var estudiante = new Estudiante(nombre, apellido, telefono, email);
                var agregado = estudianteDao.agregarEstudiante(estudiante);

                if (agregado)
                    System.out.println("✅ Estudiante registrado correctamente: " + estudiante);
                else
                    System.out.println("❌ No se pudo registrar el estudiante.");
            }
            case 4 -> { // Modificar estudiante
                System.out.println("\n✏️  ACTUALIZAR DATOS DEL ESTUDIANTE:");
                System.out.print("ID del estudiante: ");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                System.out.print("Nuevo nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Nuevo apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Nuevo teléfono: ");
                var telefono = consola.nextLine();
                System.out.print("Nuevo correo electrónico: ");
                var email = consola.nextLine();

                var estudiante = new Estudiante(idEstudiante, nombre, apellido, telefono, email);
                var modificado = estudianteDao.modificarEstudiante(estudiante);

                if (modificado)
                    System.out.println("✅ Estudiante actualizado: " + estudiante);
                else
                    System.out.println("❌ No se pudo actualizar el estudiante.");
            }
            case 5 -> { // Eliminar estudiante
                System.out.println("\n🗑️  ELIMINAR ESTUDIANTE:");
                System.out.print("Ingrese el ID del estudiante a eliminar: ");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                var estudiante = new Estudiante(idEstudiante);
                var eliminado = estudianteDao.eliminarEstudiante(estudiante);

                if (eliminado)
                    System.out.println("✅ Estudiante eliminado correctamente.");
                else
                    System.out.println("❌ No se pudo eliminar el estudiante.");
            }
            case 6 -> { // Salir
                System.out.println("\n👋 ¡Gracias por usar el sistema! Hasta pronto.");
                salir = true;
            }
            default -> System.out.println("⚠️  Opción no válida. Intente nuevamente.");
        }

        if (!salir) {
            System.out.println("\nPresione ENTER para continuar...");
            consola.nextLine();
        }

        return salir;
    }
}
