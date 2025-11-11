const fs = require("fs")

//primero leemos el archivo.txt
function leer(ruta , cb){
    fs.readFileSync(ruta, (err, data) => {
        console.log(data);
    })
}

leer(`${__dirname}/archivo.txt`, console.log); //Sintaxis ES6


//Segundo escribimos el archivo1.txt creandolo
function escribir(ruta, contenido, cb){
    fs.writeFile(ruta, contenido, function (err){
        if (err){
            console.log("No se ha podido escribir ", err);
        }else{
            console.log("Se ha escrito correctamente");
        }
    })
}

//Tercero eliminamos el archivo1.txt
function borrar(ruta, cb){
    fs.unlink(ruta, cb); //elimina de manera asincrona
}

borrar(`${__dirname}/archivo1.txt`);
escribir(`${__dirname}/archivo1.txt`, "Soy un nuevo archivo",console.log);
//leer(`${__dirname}/archivo.txt`, console.log); //sintaxis ES&