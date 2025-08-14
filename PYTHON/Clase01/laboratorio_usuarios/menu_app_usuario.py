"""menu_app_usuario.py"""

from laboratorio_usuarios.Usuario import Usuario  # Importa la clase Usuario
from laboratorio_usuarios.usuario_dao import UsuarioDAO  # Importa la c lase DAO para acceder a la base de datos
from logger_base import log  # Importa el logger para mostrar info y errores

opcion = None  # Inicializamos la variable de opcion del menu

# Bucle principal del menú
while opcion != 5:
    try:
        print("\n" + "-"*30)
        print("Opciones: ")
        print("1. Listar usuarios")
        print("2. Agregar usuarios")
        print("3. Modificar usuarios")
        print("4. Eliminar usuarios")
        print("5. Salir")
        print("-"*30)
        print()
        # Solicitamos al usuario que ingrese la opcion
        opcion = int(input("Digite la opción del menu (1-5): "))

        # Opcion 1: Lista usuarios
        if opcion == 1:
            try:
                usuarios = UsuarioDAO.seleccionar()  # Obtener todos los usuarios de la BD
                for usuario in usuarios:
                    log.info(usuario)  # Muestra cada usuario usando el logger
            except Exception as e:
                log.error(f"Error al listar usuarios: {e}")  # Captura cualquier error
            print()
        # Opcion 2: Agregar usuario
        elif opcion == 2:
            try:
                username_var = input("Digite el nombre de usuario: ")  # Nombre
                password_var = input("Digite su contraseña: ")  # Contraseña
                usuario = Usuario(username=username_var, password=password_var)  # Crea objeto Usuario
                usuario_insertado = UsuarioDAO.insertar(usuario)  # Inserta en la BD
                log.info(f"Usuario insertado: {usuario_insertado}")  # Muestra filas afectadas
            except Exception as e:
                log.error(f"Error al insertar usuario: {e}")  # Captura error
            print()
        # Opciones 3: Modificar usuario
        elif opcion == 3:
            try:
                id_usuario_var = int(input("Digite el id del usuario a modificar: "))  # ID
                username_var = input("Digite el nombre del usuario a modificar: ")  # Nuevo Nombre
                password_var = input("Digite la contraseña del usuario a modificar: ")  # Nueva contraseña
                usuario = Usuario(id_usuario=id_usuario_var, username=username_var, password=password_var)
                usuario_actualizado = UsuarioDAO.actualizar(usuario)  # Actualiza en la BD
                log.info(f"Usuario actualizado: {usuario_actualizado}")  # Muestra filas afectadas
            except Exception as e:
                log.error(f"Error al actualizar usuario: {e}")
            print()
        # Opcion 4: Eliminar usuario
        elif opcion == 4:
            try:
                id_usuario_var = int(input("Digite el id del usuario a eliminar: "))  # ID
                usuario = Usuario(id_usuario=id_usuario_var)
                usuario_eliminado = UsuarioDAO.eliminar(usuario)  # Eliminar en la Bd
                log.info(f"Usuario eliminado: {usuario_eliminado}")  # Muestra filas afectadas
            except Exception as e:
                log.error(f"Error al eliminar usuario: {e}")
            print()
        # Opcion 5: salir
        elif opcion == 5:
            print("Saliendo del programa...")

        else:
            print("Opcion invalida. Debe ser un número entre 1 y 5.")
            print()
    # Captura cuando el usuario no ingresa un número valido
    except ValueError:
        print("Debe ingresar un número válido para el menú")
        opcion = None
        print()
    # Captura cualquier otro error inesperado
    except Exception as e:
        print(f"Ocurrio un error de tipo: {e}")
        opcion = None
        print()