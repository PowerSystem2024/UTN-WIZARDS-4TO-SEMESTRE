function hola(nombre){
        return new Promise( function (resolver, reject){
            setTimeout(function(){
            console.log("Adion" + nombre);
            miCallback(nombre);
        }, 1000);
    });
}

function hablar(CallbackHablar){
    return new Promise((resolver, reject) => {
        setTimeout(function(){
            console.log("bla bla bla bla");
            CallbackHablar(nombre);
        }, 1000);
    })    
}

async function adios(nombrem){
        return new Promise((resolver, reject) => {
            //validamos el error o aprobacion
            setTimeout(function(){
            console.log("Adion" + nombre);
            //if (err) reject("Hay un error");
            otroCallback();
        }, 1500);
    });
}

//awaithola("Ariel"); // esto es mala sintaxis

async function main(){
    let nombre = await hola("Ariel");
    await hablar();
    await hablar();
    await hablar();
    await hablar(nombre);
    console.log("Termina el proceso...");
}

console.log("Empezamos el proceso...");
main();
console.log("Esta va a ser la segunda instruccion");

//Codigo en ingles
//Es asincrono y retorno una promesa
function sayHello(name){
    return new Promise((resolver, reject) => {
        setTimeout(() => {
            console.log("Hello"+ name);
            resolver(name);
        }, 1000);
    })
}

async function conversacion(name){
    console.log("Code in ingelsh")
    console.log("Atarting async process...")
    await soyHello(name);
    await talk();
    await talk();
    await talk();
    await sayBye(name);
    console.log("process completed");
}