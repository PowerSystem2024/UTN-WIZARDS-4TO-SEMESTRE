import pygame
import sys
import random
import os
from personaje import Personaje, Enemigo
from explosiones import Explosion
from constantes import SCREEN_WIDTH, SCREEN_HEIGHT, ASSETS_PATH


def mostrar_imagen_inicial(screen, imagen_path, duracion):
    imagen = pygame.image.load(imagen_path).convert()
    imagen = pygame.transform.scale(imagen, (SCREEN_WIDTH, SCREEN_HEIGHT))
    alpha = 255
    clock = pygame.time.Clock()
    tiempo_inicial = pygame.time.get_ticks()

    while pygame.time.get_ticks() - tiempo_inicial < duracion:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                sys.exit()
        tiempo_transcurrido = pygame.time.get_ticks() - tiempo_inicial
        alpha = 255 - (255 * (tiempo_transcurrido / duracion))
        alpha = max(0, alpha)
        imagen.set_alpha(int(alpha))
        screen.fill((0, 0, 0))
        screen.blit(imagen, (0, 0))
        pygame.display.flip()
        clock.tick(60)


def main():
    pygame.init()
    screen = pygame.display.set_mode((SCREEN_WIDTH, SCREEN_HEIGHT))
    pygame.display.set_caption('Amenaza Fantasma')

    imagen_inicial_path = os.path.join(ASSETS_PATH, 'images', 'inicio', 'star.png')
    if os.path.exists(imagen_inicial_path):
        mostrar_imagen_inicial(screen, imagen_inicial_path, 3000)

    icon_path = os.path.join(ASSETS_PATH, 'images', '001.png')
    if os.path.exists(icon_path):
        icon = pygame.image.load(icon_path)
        pygame.display.set_icon(icon)

    fondo = pygame.image.load(icon_path)
    fondo = pygame.transform.scale(fondo, (SCREEN_WIDTH, SCREEN_HEIGHT))

    # --- SONIDOS ---
    try:
        sonido_laser = pygame.mixer.Sound(os.path.join(ASSETS_PATH, 'sounds', 'laserdis.mp3'))
        sonido_explosion = pygame.mixer.Sound(os.path.join(ASSETS_PATH, 'sounds', 'explosion.mp3'))
        pygame.mixer.music.load(os.path.join(ASSETS_PATH, 'sounds', 'efectos.mp3'))
        pygame.mixer.music.play(-1)
    except Exception as e:
        print("⚠️ No se pudieron cargar los sonidos:", e)

    personaje = Personaje(SCREEN_WIDTH // 2, SCREEN_HEIGHT // 2)
    enemigos = []
    explosiones = []
    puntos = 0
    nivel = 1
    clock = pygame.time.Clock()
    running = True
    pausa_por_explosion = False

    while running:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                sys.exit()

        keys = pygame.key.get_pressed()
        dx, dy = 0, 0
        if keys[pygame.K_LEFT]:
            dx = -5
        if keys[pygame.K_RIGHT]:
            dx = 5
        if keys[pygame.K_UP]:
            dy = -5
        if keys[pygame.K_DOWN]:
            dy = 5

        personaje.mover(dx, dy)

        if keys[pygame.K_SPACE] and not pausa_por_explosion:
            personaje.lanzar_laser()
            if 'sonido_laser' in locals():
                sonido_laser.play()

        if not pausa_por_explosion:
            # --- ACTUALIZAR ENEMIGOS ---
            for enemigo in enemigos[:]:
                enemigo.mover()
                if enemigo.rect.top > SCREEN_HEIGHT:
                    enemigos.remove(enemigo)

                # Colisiones con láseres
                for laser in personaje.lasers[:]:
                    if enemigo.rect.colliderect(laser.rect):
                        explosiones.append(Explosion(enemigo.rect.centerx, enemigo.rect.centery))
                        enemigos.remove(enemigo)
                        personaje.lasers.remove(laser)
                        if 'sonido_explosion' in locals():
                            sonido_explosion.play()
                        puntos += 10
                        break

                # Colisión con el jugador
                if enemigo.rect.colliderect(personaje.shape):
                    if not personaje.recibir_dano():
                        explosiones.append(Explosion(personaje.shape.centerx, personaje.shape.centery))
                        if 'sonido_explosion' in locals():
                            sonido_explosion.play()
                        pausa_por_explosion = True  # pausa breve por explosión del jugador

        # --- PAUSA CORTA POR EXPLOSIÓN ---
        if pausa_por_explosion:
            for _ in range(10):  # 🔹 explosión más rápida
                screen.blit(fondo, (0, 0))
                for explosion in explosiones:
                    explosion.actualizar()
                    explosion.dibujar(screen)
                pygame.display.flip()
                clock.tick(60)
            running = False

        # Generar enemigos
        if random.random() < 0.02:
            x = random.randint(0, SCREEN_WIDTH - 50)
            enemigos.append(Enemigo(x, 0))

        # Actualizar explosiones
        nuevas_explosiones = []
        for explosion in explosiones:
            if explosion.actualizar():
                nuevas_explosiones.append(explosion)
        explosiones = nuevas_explosiones

        # --- DIBUJAR ---
        screen.blit(fondo, (0, 0))
        personaje.dibujar(screen)
        for enemigo in enemigos:
            enemigo.dibujar(screen)
        for explosion in explosiones:
            explosion.dibujar(screen)

        font = pygame.font.Font(None, 36)
        texto_puntos = font.render(f"Puntos: {puntos}", True, (255, 255, 255))
        texto_nivel = font.render(f"Nivel: {nivel}", True, (255, 255, 255))
        screen.blit(texto_puntos, (10, 50))
        screen.blit(texto_nivel, (10, 90))

        pygame.display.flip()
        clock.tick(60)

    # --- GAME OVER ---
    screen.fill((0, 0, 0))
    font_large = pygame.font.Font(None, 74)
    font_small = pygame.font.Font(None, 36)
    texto_game_over = font_large.render("GAME OVER", True, (255, 0, 0))
    texto_mensaje = font_small.render("Que la Fuerza te acompañe", True, (255, 255, 255))

    pos_x_game_over = SCREEN_WIDTH // 2 - texto_game_over.get_width() // 2
    pos_y_game_over = SCREEN_HEIGHT // 2 - texto_game_over.get_height() // 2 - 20
    pos_x_mensaje = SCREEN_WIDTH // 2 - texto_mensaje.get_width() // 2
    pos_y_mensaje = SCREEN_HEIGHT // 2 + texto_game_over.get_height() // 2 + 20

    screen.blit(texto_game_over, (pos_x_game_over, pos_y_game_over))
    screen.blit(texto_mensaje, (pos_x_mensaje, pos_y_mensaje))
    pygame.display.flip()
    pygame.time.wait(2000)
    pygame.quit()
    sys.exit()


if __name__ == '__main__':
    main()
