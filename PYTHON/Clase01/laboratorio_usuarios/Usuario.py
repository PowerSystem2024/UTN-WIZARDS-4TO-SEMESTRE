"""Usuario.py"""

from logger_base import log  # Importa el logger para poder registrar mensajes de debug/info/error
class Usuario:
    def __init__(self, id_usuario=None, username=None, password=None):
        self._id_usuario = id_usuario  # ID del usuario
        self._username = username  # Nombre de usuario
        self._password = password  # Contraseña

    def __str__(self):
        return f"""
            Id Usuario: {self._id_usuario},
            Username: {self._username},
            Password: {self._password}          
        """

    # Métodos Getters and Setters

    @property
    def id_usuario(self):
        return self._id_usuario

    @id_usuario.setter
    def id_usuario(self, id_usuario):
        self._id_usuario = id_usuario

    @property
    def username(self):
        return self._username

    @username.setter
    def username(self, username):
        self._username = username

    @property
    def password(self):
        return self._password

    @password.setter
    def password(self, password):
        self._password = password


if __name__ == "__main__":
    usuario1 = Usuario(1, "ale95", "1234")
    log.debug(usuario1)
    usuario2 = Usuario(id_usuario=2, username="victor23", password=5432)
    log.debug(usuario2)