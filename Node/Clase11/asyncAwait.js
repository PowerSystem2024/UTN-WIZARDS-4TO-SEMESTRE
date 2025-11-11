function hola(nombre){
    return new Promise(function (resolver, reject){
        setTimeout(function(){
            console.log("¡Adion " + nombre + "!");
            miCallback(nombre);
        }, 1000);
    });
}

function hablar(CallbackHablar){
    return new Promise((resolver, reject) => {
        setTimeout(function(){
            console.log("¡Bla bla bla bla!");
            CallbackHablar(nombre);
        }, 1000);
    })    
}

async function adios(nombre){
    return new Promise((resolver, reject) => {
        setTimeout(function(){
            console.log("¡Adion " + nombre + "!");
            otroCallback();
        }, 1500);
    });
}

//awaithola("Rocio"); // esto es mala sintaxis

async function main(){
    let nombre = await hola("Rocío");
    await hablar();
    await hablar();
    await hablar();
    await hablar(nombre);
    console.log("¡Termina el proceso!");
}

console.log("¡Empezamos el proceso!");
main();
console.log("¡Esta va a ser la segunda instrucción!");

// Código en inglés
// Es asincrónico y retorna una promesa
function sayHello(name){
    return new Promise((resolver, reject) => {
        setTimeout(() => {
            console.log("¡Hello " + name + "!");
            resolver(name);
        }, 1000);
    })
}

async function conversacion(name){
    console.log("Code in English");
    console.log("Starting async process...");
    await soyHello(name);
    await talk();
    await talk();
    await talk();
    await sayBye(name);
    console.log("Process completed!");
}
