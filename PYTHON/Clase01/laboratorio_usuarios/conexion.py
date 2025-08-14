"""conexion.py"""

from psycopg2 import pool  # Importamos el moulo de conexion a postgresSQL con soporte para el pool de conexiones
from logger_base import log  # Importamos el logger
import sys  # Importamos sys para poder terminar el programa si hay error crítico


class Conexion:
    # Datos de conexíon a la base de datos
    _DATABASE = "test_bd"  # Nombre de la base de datos
    _USERNAME = "postgres"  # Usuario de la base de datos
    _PASSWORD = "admin"  # Contraseña
    _DB_PORT = "5432"  # Puerto de conexcion
    _HOST = "127.0.0.1"  # Host de la base de datos
    _MIN_CON = 1  # Número mínimo de conexiones en el pool
    _MAX_CON = 5  # Número máximo de conexiones en el pool
    _pool = None  # Variable que almacena el pool de conexiones

    # Método para obtener la conexión del pool
    @classmethod
    def obtenerConexion(cls):
        conexion = cls.obtenerpool().getconn()
        return conexion

    # Método para inicializar o devolver el pool de conexiónes
    @classmethod
    def obtenerpool(cls):
        if cls._pool is None:
            try:
                # Crea un SimpleConnectionPool con los parámetros definidos
                cls._pool = pool.SimpleConnectionPool(cls._MIN_CON,
                                                      cls._MAX_CON,
                                                      host=cls._HOST,
                                                      user=cls._USERNAME,
                                                      password=cls._PASSWORD,
                                                      port=cls._DB_PORT,
                                                      database=cls._DATABASE)
                return cls._pool  # Devuelve el pool creado
            except Exception as e:
                # Si ocurre un error crítico al crear el pool, lo registramos y salimos
                log.error(f"Ocurrió un error al obtener el pool:  {e}")
                sys.exit()  # Termina el programa
        else:
            return cls._pool

    # Método para devolver una conexión al pool depués de usarla
    @classmethod
    def liberarConexion(cls, conexion):
        cls.obtenerpool().putconn(conexion)  # devuelve la conexión al pool

    # Método para cerra todas la s conexiones del pool
    @classmethod
    def cerrarConexion(cls):
        cls.obtenerpool().closeall()  # Cierra todas las conexiones activas en el pool
