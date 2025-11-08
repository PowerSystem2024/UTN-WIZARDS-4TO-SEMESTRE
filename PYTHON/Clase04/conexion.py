# -*- coding: utf-8 -*-
from psycopg2 import pool
from logger_base import log
import sys
import os

# Forzamos UTF-8 para la salida en Windows
os.environ["PYTHONIOENCODING"] = "utf-8"

class Conexion:
    _DATABASE = "test_db_utf8"
    _USERNAME = "postgres"
    _PASSWORD = "admin"
    _DB_PORT = "5432"
    _HOST = "127.0.0.1"
    _MIN_CON = 1
    _MAX_CON = 5
    _pool = None

    @classmethod
    def obtenerConexion(cls):
        conexion = cls.obtenerPool().getconn()
        return conexion

    @classmethod
    def obtenerPool(cls):
        if cls._pool is None:
            try:
                cls._pool = pool.SimpleConnectionPool(
                    cls._MIN_CON,
                    cls._MAX_CON,
                    host=cls._HOST,
                    user=cls._USERNAME,
                    password=cls._PASSWORD,
                    port=cls._DB_PORT,
                    database=cls._DATABASE,
                    client_encoding='UTF8'
                )
                return cls._pool
            except Exception as e:
                # Logueamos el error sin tocar encoding
                log.error(f"Ha ocurrido un error al obtener el pool: {repr(e)}")
                sys.exit()
        else:
            return cls._pool

    @classmethod
    def liberarConexion(cls, conexion):
        cls.obtenerPool().putconn(conexion)

    @classmethod
    def cerrarConexiones(cls):
        cls.obtenerPool().closeall()
