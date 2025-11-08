"""logger_base.py"""

# importamos el logging y lo renombramos log
import logging as log

# Configuracion básica del logging
log.basicConfig(
                # Nivel minimo de log que se registrará, DEBUG captura todos lso niveles
                level=log.DEBUG,
                # Formato de los mensajes de log
                # %(asctime)s -> hora en que se genera el log
                # %(levelname)s -> nivel de log (DEBUG, INFO, EROR)
                # %(filename)s -> archivo desde donde se genera el log
                # %(lineno)s -> línea del archivo donde se genera el log
                # %(message)s -> mensaje que pasamos al log
                format="%(asctime)s:%(levelname)s [%(filename)s:%(lineno)s] %(message)s",
                # Formato de la fechra/hora que aparece en %(asctime)s
                # %I -> hora en formato 12h
                # %M -> minutos
                # %S -> segundos
                # %p -> AM/PM
                datefmt="%I:%M:%S %p",
                # Handlers donde se registran los logs
                handlers=[
                    # Guardamos los log en un archivo llamado "usuario_datos.log"
                    log.FileHandler("usuarios_datos.log", encoding="utf-8"),
                    # Mostramos los logs en la consola
                    log.StreamHandler()
                ])


