"""cursor_del_Pool.py"""

from .logger_base import log    # Importamos el logger para registrar eventos y error
from laboratorioUsuarios.conexion import Conexion  # Importamos la clase Conexion que maneja el pool de conexiones


class CursorDelPool:
    def __init__(self):
        # Inicializa los atributos de conexion y cursor en None
        self._conexion = None
        self._cursor = None

    def __enter__(self):
        self._conexion = Conexion.obtenerConexion()  # Toma una conexión disponible del pool
        self._cursor = self._conexion.cursor()  # Crea un cursor de la conexión
        return self._cursor  # Devuelve el cursor al bloque "with

    def __exit__(self, tipo_exception, valor_exception, detalle_exception):
        if valor_exception:  # Si ocurrio alguna excepción dentro del "with"
            self._conexion.rollback()  # Deshace los cambios realizados
            log.debug(f"Ocurrió una excepción: {valor_exception}")  # Loguea el error
        else:  # Si no hubo excepciones
            self._conexion.commit()  # confirma los cambios realizados en la base de datos
        self._cursor.close()  # Cierra el cursor siempre
        Conexion.liberarConexion(self._conexion)  # Devuelve la conexión al pool para que pueda retilizarse
