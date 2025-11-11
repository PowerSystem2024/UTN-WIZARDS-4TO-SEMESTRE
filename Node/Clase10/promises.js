function hola(nombre) {
  return new Promise(function (resolver, reject) {
    setTimeout(function () {
      console.log("¡Hola " + nombre + "!");
      resolver(nombre); 
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

function adios(nombrem){
        return new Promise((resolver, reject) => {
            setTimeout(function(){
            console.log("Adion" + nombre);
            otroCallback();
        }, 1500);
    });
}

//LLamamos a la funcion
console.log("Iniciando el proceso...")
hola("Ariel")
    .then(hablar)
    .then(hablar)
    .then(hablar)
    .then(adios)
    .then((nombre) => {
        console.log("Terminado el proceso");
    })
    .catch(error => {
        console.log("Ha habido un error: ");
        console.log(error);
    })
