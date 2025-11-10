import pygame
import os
from constantes import ASSETS_PATH
game_paused = False
explosion = None

class Personaje:
    def __init__(self, x, y):
        #Construye la ruta completa a la imagen del personaje
        self.image = pygame.image.load(os.path.join(ASSETS_PATH, 'images', 'Speeder.png'))
        self.image = pygame.transform.scale(self.image, (95, 95))
        self.shape = self.image.get_rect(center=(x, y))
        self.lasers = []
        self.energia = 100  #Barra de energía inicial

    def mover(self, dx, dy):
        self.shape.x += dx
        self.shape.y += dy

    def lanzar_laser(self):
        laser = Laser(self.shape.centerx, self.shape.top)
        self.lasers.append(laser)

    def recibir_dano(self):
        self.energia -= 10
        if self.energia <= 0:
            self.energia = 0
            return False
        return True

    def dibujar(self, screen):
        screen.blit(self.image, self.shape.topleft)
        for laser in self.lasers:
            laser.dibujar(screen)
            laser.mover()

        # Dibujar la barra de energía
        pygame.draw.rect(screen, (255, 0, 0), (10, 10, 100, 10))  # Barra de fondo
        pygame.draw.rect(screen, (0, 255, 0), (10, 10, self.energia, 10))  # Barra de energía

class Enemigo:
    def __init__(self, x, y):
        #Construye la ruta completa a la imagen del enemigo
        self.image = pygame.image.load(os.path.join(ASSETS_PATH, 'images', 'enemigo1.png'))
        self.image = pygame.transform.scale(self.image, (80, 80))
        self.rect = self.image.get_rect(topleft=(x, y))

    def mover(self):
        self.rect.y += 5  # Velocidad de movimiento del enemigo

    def dibujar(self, screen):
        screen.blit(self.image, self.rect.topleft)

class Laser:
    def __init__(self, x, y):
        # Crear una superficie azul que representa el láser
        self.image = pygame.Surface((4, 15))
        self.image.fill((0, 0, 255))  # Azul
        self.rect = self.image.get_rect()
        self.rect.centerx = x
        self.rect.top = y
        self.velocidad = -10  # Velocidad hacia arriba

    def mover(self):
        self.rect.y += self.velocidad

    def dibujar(self, pantalla):
        pantalla.blit(self.image, self.rect)

class Explosion:
    def __init__(self, x, y):
        self.images = [pygame.image.load(os.path.join(ASSETS_PATH, 'images', f'regularExplosion0{i:02d}.png')) for i in range(9)]
        self.index = 0
        self.image = self.images[self.index]
        self.rect = self.image.get_rect(center=(x, y))
        self.frame_rate = 0
        self.max_frames = 5
        self.finished = False  # 🔹 NUEVO: indica si la animación terminó

    def update(self):
        self.frame_rate += 1
        if self.frame_rate >= self.max_frames:
            self.frame_rate = 0
            self.index += 1
            if self.index >= len(self.images):
                self.finished = True  # 🔹 NUEVO: termina animación
            else:
                self.image = self.images[self.index]

    def draw(self, screen):
        if not self.finished:
            screen.blit(self.image, self.rect)
