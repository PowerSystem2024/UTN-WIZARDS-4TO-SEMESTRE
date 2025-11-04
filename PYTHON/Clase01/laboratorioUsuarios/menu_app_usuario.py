"""menu_app_usuario.py"""

from laboratorioUsuarios.Usuario import Usuario
from laboratorioUsuarios.usuario_dao import UsuarioDAO
from laboratorioUsuarios.logger_base import log
opcion = None

while opcion != 5:
    try:
        print("\n" + "-" * 30)
        print("Menu de Usuarios")
        print("1. Listar usuarios")
        print("2. Agregar usuario")
        print("3. Modificar usuario")
        print("4. Eliminar usuario")
        print("5. Salir")
        opcion = int(input("Seleccione una opción (1-5): "))

        if opcion == 1:
            try:
                usuarios = UsuarioDAO.seleccionar()
                for usuario in usuarios:
                    log.info(usuario)
            except Exception as e:
                log.error(f"Error al listar usuarios: {e}")

        elif opcion == 2:
            try:
                username_var = input("Nombre de usuario: ") or "rocio"
                password_var = input("Contraseña: ") or "5813"
                usuario = Usuario(username=username_var, password=password_var)
                filas_insertadas = UsuarioDAO.insertar(usuario)
                log.info(f"Usuarios insertados: {filas_insertadas}")
            except Exception as e:
                log.error(f"Error al insertar usuario: {e}")

        elif opcion == 3:
            try:
                id_usuario_var = int(input("ID del usuario a modificar: "))
                username_var = input("Nuevo nombre de usuario: ")
                password_var = input("Nueva contraseña: ")
                usuario = Usuario(id_usuario=id_usuario_var, username=username_var, password=password_var)
                filas_actualizadas = UsuarioDAO.actualizar(usuario)
                log.info(f"Usuarios actualizados: {filas_actualizadas}")
            except Exception as e:
                log.error(f"Error al actualizar usuario: {e}")

        elif opcion == 4:
            try:
                id_usuario_var = int(input("ID del usuario a eliminar: "))
                usuario = Usuario(id_usuario=id_usuario_var)
                filas_eliminadas = UsuarioDAO.eliminar(usuario)
                log.info(f"Usuarios eliminados: {filas_eliminadas}")
            except Exception as e:
                log.error(f"Error al eliminar usuario: {e}")

        elif opcion == 5:
            print("Saliendo del programa...")

        else:
            print("Opción inválida. El número debe ser entre 1 y 5.")

    except ValueError:
        print("Debe ingresar un número válido.")
        opcion = None
    except Exception as e:
        print(f"Ocurrió un error: {e}")
        opcion = None
