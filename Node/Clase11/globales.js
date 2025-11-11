// En un entorno Node.js, "this === global" es verdadero
// (significa que 'this' hace referencia al objeto global)
 
// Mostrar algo en la consola
// console.log();

// Mostrar un mensaje de error en la consola
// console.error();

// Ejecutar un código después de un intervalo de tiempo
// setTimeout(() => {}, milisegundos);

// Ejecutar un código repetidamente cada cierto intervalo de tiempo
// setInterval(() => {}, milisegundos);

// Da prioridad de ejecución a una función asíncrona (se ejecuta enseguida tras el ciclo actual)
// setImmediate(() => {})

// console.log(setInterval); // Muestra información sobre la función setInterval

let i = 0;
let intervalo = setInterval(() => {
    console.log("hola");
    if (i === 3) {
        clearInterval(intervalo); // Detenemos la función repetitiva
    }
    i++;
}, 1000);

setImmediate(() => {
    console.log("Saludos inmediato");
});

// require(); // Se usa para importar módulos en Node.js

console.log(__dirname); // Muestra el directorio actual del archivo

globalThis.miVariable = "mi variable global";
console.log(miVariable);
