function SoyAsincrona(nombre, miCallback){
    setTimeout( function() {
        console.log("Hola" + nombre);
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

// funcion recursiva
function conversacion(nombre, vecesm callback){
    if (vaces > 0){
        Hablar(function(){
            conversacion(nombre, --vecesm, callback);
        });
    }else{
        callback(nombre, callback)
    }
}

//--Proceso principal
console.log("Iniciar el proceso...");
hola("Ariel", function(nombre){
    conversacion(nombre, 4, function(){
        console.log("Terminando el proceso...")
    });
});
//hola("Carlos", function(nombre){
//   hablar(function(){
//            hablar(function(){
//                hablar(function(){
//                    hablar(function(){
//                        adios(nombrem function(){
//                        console.log("Terminando el proceso...");
//                        });
//                   })
//                });
//            });
//        });
//    });
//});
