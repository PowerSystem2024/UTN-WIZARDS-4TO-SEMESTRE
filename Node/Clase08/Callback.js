function SoyAsincrona(nombre, miCallback){
    setTimeout( function() {
        console.log("¡Hola " + nombre + "!");
        miCallback;
    }, 1000)
    
}

function adios (nombre, otroCallback){
    setTimeout(function(){
        console.log("Adion" + nombre);
        otroCallback();
    }, 1000);
}

console.log("Iniciando el Proceso...");
hola("Carlos", function(nombre){
    adios(nombre, function(){
        console.log("Terminando el proceso...")
    });
});

//hola("carlos", function(){});
//hola("carlos", function(){});