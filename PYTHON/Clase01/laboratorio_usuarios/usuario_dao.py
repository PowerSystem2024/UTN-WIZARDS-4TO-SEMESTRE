"""usuario_dao.py"""

from laboratorio_usuarios.Usuario import Usuario  # Importa la clae Usuario
from laboratorio_usuarios.cursor_del_pool import CursorDelPool  # Importa el context manager de cursores
from logger_base import log  # Importa el logger


class UsuarioDAO:
    # Sentencias SQL
    _SELECCIONAR = "SELECT * FROM usuario ORDER BY id_usuario"
    _INSERTAR = "INSERT INTO usuario(username, password) VALUES (%s, %s)"
    _ACTUALIZAR = "UPDATE usuario SET username=%s, password=%s WHERE id_usuario= %s"
    _ELIMINAR = "DELETE FROM usuario WHERE id_usuario=%s"

    #Definimos métodos de clase
    @classmethod
    def seleccionar(cls):
        with CursorDelPool() as cursor:  # Abre un cursor usando el pool con "with"
            cursor.execute(cls._SELECCIONAR)  # Ejecuta la consulta SELECT
            registros = cursor.fetchall()  # Obtiene tod0 los registros
            usuarios = []
            for registro in registros:
                # Crea un objeto Usuario pro cada registro y los agrega a la lista
                usuario = Usuario(registro[0], registro[1], registro[2])
                usuarios.append(usuario)
            return usuarios  # Devuelve la lista usuarios

    @classmethod
    def insertar(cls, usuario):
        with CursorDelPool() as cursor:
            valores = (usuario.username, usuario.password)  # Tupla con los valores para la consulta
            cursor.execute(cls._INSERTAR, valores)  # Ejecuta la consulta INSERT
            log.debug(f"Usuario Insertado: {usuario}")  # Loguea el usuario insertado (debug)
            return cursor.rowcount  # Devuelve la cantidad de filas afectadas

    @classmethod
    def actualizar(cls, usuario):
        with CursorDelPool() as curosr:
            valores = (usuario.username, usuario.password, usuario.id_usuario)  # Tupla con los valores para UPDATE
            curosr.execute(cls._ACTUALIZAR, valores)  # Ejecuta la consulta UPDATE
            log.debug(f"Usuario Actualizado: {usuario}")  # Loguea el usuario actualizado
            return curosr.rowcount  # Devuelve la cantida de filas afectadas

    @classmethod
    def eliminar(cls, usuario):
        with CursorDelPool() as cursor:
            valores = (usuario.id_usuario,)  # Tupla con el ID del usuario a eliminar
            cursor.execute(cls._ELIMINAR, valores)  # Ejcuta la consulta DELETE
            log.debug(f"El usuario eliminado es: {usuario}")  # Loguea el usuario eliminado
            return cursor.rowcount  # Devuelve la cantidad de flas afectadas
