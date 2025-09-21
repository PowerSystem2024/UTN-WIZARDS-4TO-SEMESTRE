package utn.estudiantes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utn.estudiantes.servicio.IEstudianteServicio;

@SpringBootApplication
public class EstudiantesApplication implements CommandLineRunner{
  @Autowired
  private IEstudianteServicio estudianteServicio;
  private static final Logger logger = LoggerFactory.getLogger(EstudiantesApplication.class);

  String nl = System.lineSeparator();
	public static void main(String[] args) {
    logger.info("Iniciando aplicación...");
    //Levantar la fabrica de spring 
    SpringApplication.run(EstudiantesApplication.class, args);
    logger.info("Aplicación finalizada!");
  }

  @Override
  public void run(String... args) throws Exception {
    logger.info("Ejecutando método run de Spring...");
  }

}
