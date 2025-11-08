from usuario_dao import UsuarioDAO
from logger_base import log
from usuario import Usuario
from getpass import getpass
import os

def limpiar_consola():
    os.system('cls' if os.name == 'nt' else 'clear')

opcion = None
while opcion != 5:
    print('===============================')
    print('       MENÚ DE USUARIOS        ')
    print('===============================')
    print('1. Listar usuarios')
    print('2. Agregar usuario')
    print('3. Modificar usuario')
    print('4. Eliminar usuario')
    print('5. Salir')
    print('===============================')
    
    try:
        opcion = int(input('Digite la opción (1-5): '))
    except ValueError:
        print('⚠️  Debe ingresar un número del 1 al 5.')
        continue

    limpiar_consola()

    if opcion == 1:
        usuarios = UsuarioDAO.seleccionar()
        if usuarios:
            print('📋 Lista de usuarios:')
            for usuario in usuarios:
                print(usuario)
        else:
            print('No hay usuarios registrados en la base de datos.')
    elif opcion == 2:
        username_var = input('Digite el nombre de usuario: ')
        password_var = getpass('Digite su contraseña: ')
        usuario = Usuario(username=username_var, password=password_var)
        usuario_insertado = UsuarioDAO.insertar(usuario)
        print(f'✅ Usuario insertado correctamente ({usuario_insertado} registro/s).')
    elif opcion == 3:
        id_usuario_var = int(input('Digite el ID de usuario a modificar: '))
        username_var = input('Digite el nuevo nombre de usuario: ')
        password_var = getpass('Digite la nueva contraseña: ')
        usuario = Usuario(id_usuario=id_usuario_var, username=username_var, password=password_var)
        usuario_actualizado = UsuarioDAO.actualizar(usuario)
        print(f'✅ Usuario actualizado correctamente ({usuario_actualizado} registro/s).')
    elif opcion == 4:
        id_usuario_var = int(input('Digite el ID de usuario a eliminar: '))
        usuario = Usuario(id_usuario=id_usuario_var)
        usuario_eliminado = UsuarioDAO.eliminar(usuario)
        print(f'🗑️  Usuario eliminado correctamente ({usuario_eliminado} registro/s).')
    elif opcion == 5:
        print('👋 Salimos de la aplicación, hasta pronto!')
    else:
        print('⚠️  Opción no válida. Intente nuevamente.')

    print()
    input('Presione ENTER para continuar...')
    limpiar_consola()
