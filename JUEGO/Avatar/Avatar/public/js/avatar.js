// ----------------- VARIABLES GLOBALES -----------------
// Variables que guardan el ataque elegido y las vidas de cada jugador
let ataqueJugador;
let ataqueEnemigo;
let vidasJugador = 3;
let vidasEnemigo = 3;

// Referencias a secciones del HTML
const sectionSeleccionarAtaque = document.getElementById("seleccionar-ataque");
const sectionReiniciar = document.getElementById("reiniciar");
const sectionMensaje = document.getElementById("mensajes");
const sectionSeleccionarPersonaje = document.getElementById("seleccionar-personaje");
// Referencia a elementos HTML que muestran el estado del juego
const spanPersonajeJugador = document.getElementById("personaje-jugador");
const spanPersonajeEnemigo = document.getElementById("personaje-enemigo");
const spanVidasJugador = document.getElementById("vidas-jugador");
const spanVidasEnemigo = document.getElementById("vidas-enemigo");
// Referencias a botones
const botonPersonajeJugador = document.getElementById("boton-personaje");
const botonReiniciar = document.getElementById("boton-reiniciar");
const botonPunio = document.getElementById("boton-punio");
const botonPatada = document.getElementById("boton-patada");
const botonBarrida = document.getElementById("boton-barrida");

// ----------------- CLASE PERSONAJE -----------------
// Clase que representa un personaje seleccionable en el jeugo
class Personaje {
  constructor(nombre) {
    this.nombre = nombre;
    this.vidas = 3;
    this.ataques = ["Punio", "Patada", "Barrida"]; // Cada pj puede usar estas ataques
  }
}

// ----------------- INSTANCIAS DE PERSONAJES -----------------
// Lista con los personajes disponibles para elejir
const personajes = [
  new Personaje("Zuko"),
  new Personaje("Katara"),
  new Personaje("Aang"),
  new Personaje("Toph"),
  new Personaje("Sokka"),
  new Personaje("Azula")
];

// ----------------- FUNCIONES -----------------
function iniciarJuego() {
  //Oculta secciones al inicio
  sectionSeleccionarAtaque.style.display = "none";
  sectionReiniciar.style.display = "none";

  // Generar dinámicamente los inputs(radios) para elejir personajes
  generarInputsPersonajes();
  // Evento de botones
  botonPersonajeJugador.addEventListener("click", seleccionarPersonajeJugador);

  let botonReglasJuego = document.getElementById("boton-reglas");
  botonReglasJuego.addEventListener("click", reglasDelJuego);

  botonPunio.addEventListener("click", ataquePunio);
  botonPatada.addEventListener("click", ataquePatada);
  botonBarrida.addEventListener("click", ataqueBarrida);

  botonReiniciar.addEventListener("click", reiniciarJuego);
}

function generarInputsPersonajes() {
  // Limpia la sección antes de insertar los inputs
  sectionSeleccionarPersonaje.innerHTML = "<h2>Elige tu personaje</h2>";
  
  //Recorre la lista de personaejs y genera inputs + lebels con imagenes
  personajes.forEach((personaje, index) => {
    const container = document.createElement("div");
    container.classList.add("radio-container");

    const input = document.createElement("input");
    input.type = "radio";
    input.name = "personaje"; // tod0s comparten el mismo grupo
    input.id = personaje.nombre;
    input.value = personaje.nombre;

    const label = document.createElement("label");
    label.setAttribute("for", personaje.nombre);
    label.innerHTML = `
      <img src="assets/${personaje.nombre.toLowerCase()}.png" alt="${personaje.nombre}">
      <span>${personaje.nombre}</span>
    `;

    container.appendChild(input);
    container.appendChild(label);
    sectionSeleccionarPersonaje.appendChild(container);
  });

  // Insertar el bóton para confirmar selección
  sectionSeleccionarPersonaje.appendChild(botonPersonajeJugador);
}

function seleccionarPersonajeJugador() {
  //Busca el personajes elegido
  const selectedInput = document.querySelector('input[name="personaje"]:checked');

  //Si no selleciono nada. muestra error temporal
  if (!selectedInput) {
    const mensajeError = document.createElement("p");
    mensajeError.innerHTML = "Seleccioná un personaje";
    mensajeError.style.color = "red";
    sectionSeleccionarPersonaje.appendChild(mensajeError);

    setTimeout(() => {
      sectionSeleccionarPersonaje.removeChild(mensajeError);
    }, 2000);
    return;
  }

  // Muestra nombre del jugador
  spanPersonajeJugador.innerHTML = selectedInput.id;
  // Cambia la imagen del jugador por la del personaejs elegido
  const imagenJugador = document.getElementById("imagen-jugador");
  imagenJugador.src = `./assets/${selectedInput.id.toLowerCase()}.png`;
  // Pasa a la sección de ataque
  sectionSeleccionarAtaque.style.display = "block";
  sectionSeleccionarPersonaje.style.display = "none";
  // Selecciona automáticamente al enemigo
  seleccionarPersonajeEnemigo();
}

function seleccionarPersonajeEnemigo() {
  // Selecciona enemigo al azar de la lista
  let enemigo = personajes[aleatorio(0, personajes.length - 1)];
  spanPersonajeEnemigo.innerHTML = enemigo.nombre;
  // Muestra su imagen
  const imagenEnemigo = document.getElementById("imagen-enemigo");
  imagenEnemigo.src = `./assets/${enemigo.nombre.toLowerCase()}.png`;
}

// Funciones de ataque del jugador
function ataquePunio() {
  ataqueJugador = "Punio";
  ataqueAleatorioEnemigo();
}

function ataquePatada() {
  ataqueJugador = "Patada";
  ataqueAleatorioEnemigo();
}

function ataqueBarrida() {
  ataqueJugador = "Barrida";
  ataqueAleatorioEnemigo();
}

function ataqueAleatorioEnemigo() {
  // Ele enemigo elige ataque al azar
  const ataques = ["Punio", "Patada", "Barrida"];
  ataqueEnemigo = ataques[aleatorio(0, ataques.length - 1)];
  combate();
}

function combate() {
  // Comaparación de ataques
  if (ataqueEnemigo == ataqueJugador) crearMensaje("EMPATE");
  else if (
    (ataqueJugador == "Punio" && ataqueEnemigo == "Barrida") ||
    (ataqueJugador == "Patada" && ataqueEnemigo == "Punio") ||
    (ataqueJugador == "Barrida" && ataqueEnemigo == "Patada")
  ) {
    crearMensaje("GANASTE");
    vidasEnemigo--;
    spanVidasEnemigo.innerHTML = vidasEnemigo;
  } else {
    crearMensaje("PERDISTE");
    vidasJugador--;
    spanVidasJugador.innerHTML = vidasJugador;
  }
  // Revisa si alguien llegó a 0 vidas
  revisarVidas();
}

function revisarVidas() {
  if (vidasEnemigo == 0) crearMensajeFinal("Felicitaciones!!! Has GANADO");
  else if (vidasJugador == 0) crearMensajeFinal("Que Pena, Has PERDIDO");
}

function crearMensajeFinal(resultado) {
  sectionReiniciar.style.display = "block"; // Muestra el botón de reiniciar
  const parrafo = document.createElement("p");
  parrafo.innerHTML = resultado;
  sectionMensaje.appendChild(parrafo);
  // Dehabilita botones de ataque
  botonPunio.disabled = true;
  botonPatada.disabled = true;
  botonBarrida.disabled = true;
}

function crearMensaje(resultado) {
  // Mensaje de cada ronda, que ataque hizo cada uno y el resultado
  const parrafo = document.createElement("p");
  parrafo.innerHTML =
    `Tu personaje atacó con ${ataqueJugador}, el personaje del enemigo atacó con ${ataqueEnemigo} ${resultado}`;
  sectionMensaje.appendChild(parrafo);
}

function reglasDelJuego() {
  // Mostrar/ocultar la sección de reglas con un botón
  const seccionReglas = document.getElementById("reglas-juego");
  const boton = document.getElementById("boton-toggle-reglas");

  if (seccionReglas.style.display === "none") {
    seccionReglas.style.display = "block";
    boton.textContent = "Ocultar reglas del juego";
  } else {
    seccionReglas.style.display = "none";
    boton.textContent = "Mostrar reglas del juego";
  }
}

function reiniciarJuego() {
  // Recarga la pagina para reiniciar todo
  location.reload();
}

// Funcion auxiliar que devuelve número aleatorio entre min y max
function aleatorio(min, max) {
  return Math.floor(Math.random() * (max - min + 1) + min);
}

// Cuando la pagina carga, arraca el jeugo
window.addEventListener("load", iniciarJuego);