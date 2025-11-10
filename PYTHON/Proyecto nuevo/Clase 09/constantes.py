import os

# Dimension de la pantalla, el alto y ancho de la pantalla
SCREEN_WIDTH = 800
SCREEN_HEIGHT = 600

# Especificamos los colores de la pantalla

COLOR_LASER =(0,0,255) #Azul

# Ruta absoluta de la carpeta 'assets' relativa a este archivo
BASE_PATH = os.path.dirname(os.path.abspath(__file__))
ASSETS_PATH = os.path.join(BASE_PATH, 'assets')
