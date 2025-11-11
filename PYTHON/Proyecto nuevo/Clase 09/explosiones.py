import pygame
import os
from constantes import ASSETS_PATH, SCREEN_WIDTH, SCREEN_HEIGHT

class Explosion:
    def __init__(self, x, y):
        # Cargar las imágenes de la explosión
        self.frames = []
        for i in range(1, 6):  # suponiendo 5 frames: 001–005
            ruta = os.path.join(ASSETS_PATH, 'images', f'regularExplosion0{i:02d}.png')
            if os.path.exists(ruta):
                imagen = pygame.image.load(ruta).convert_alpha()
                imagen = pygame.transform.scale(imagen, (SCREEN_WIDTH, SCREEN_HEIGHT))
                self.frames.append(imagen)
            else:
                print(f"⚠️ No se encontró: {ruta}")

        self.frame_index = 0
        self.image = self.frames[self.frame_index] if self.frames else None
        self.rect = self.image.get_rect(center=(x, y)) if self.image else None
        self.velocidad_animacion = 3  # 🔹 velocidad más rápida
        self.tiempo_ultimo_update = pygame.time.get_ticks()

    def actualizar(self):
        # Controlar la velocidad de la animación
        ahora = pygame.time.get_ticks()
        if ahora - self.tiempo_ultimo_update > self.velocidad_animacion * 20:
            self.frame_index += 1
            self.tiempo_ultimo_update = ahora

            if self.frame_index >= len(self.frames):
                return False  # explosión terminada
            else:
                self.image = self.frames[self.frame_index]
        return True

    def dibujar(self, screen):
        if self.image:
            screen.blit(self.image, (0, 0))  # cubre toda la pantalla
