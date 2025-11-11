//this === global = true

//mostrar algo en consola
//console.log();

//mostrar un mensaje en forma de error
//console.error();

//Ejecutar un codigo despues de un intervalo de tiempo
//setTimeout(() => {});

//Ejecutar un codigo ada intervalo de tiempo
//setInterval(() => {});

//Da prioridad de ejecucion a una funcion asincrona
//setImmdiate(() => {})

//console.log(setInterval);

let i = 0;
let intervalor = setInterval(() => {
    console.log("hola");
    if (i===3){
        clearInterval(intervalor); //Detetemos la funcion
    }
    i++
}, 1000);

setImmediate(()=>{
    console.log("Saludos inmediato");
});

//require();

console.log(__dirneme);

globalThis.miVariable = "mi variable gloal";
console.log(miVariable);