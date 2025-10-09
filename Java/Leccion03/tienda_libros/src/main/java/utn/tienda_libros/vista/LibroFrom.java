package utn.tienda_libros.vista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utn.tienda_libros.servicio.LibroServicio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


@Component
public class LibroFrom extends JFrame {

    LibroServicio libroServicio;
    private JPanel panel;
    // Corregido: La tabla es el componente visual
    private JTable TablaLibros;
    // Corregido: El modelo maneja los datos
    private DefaultTableModel tablaModeloLibros;

    @Autowired
    public LibroFrom(LibroServicio libroServicio) {
        this.libroServicio = libroServicio;
        iniciarForma();
    }

    private void iniciarForma() {
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Configuración de tamaño y centrado
        setSize(900, 700);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension tamanioPantalla = toolkit.getScreenSize();
        int x = (tamanioPantalla.width - getWidth()) / 2;
        int y = (tamanioPantalla.height - getHeight()) / 2;
        setLocation(x, y);

        setVisible(true);
    }

    private void createUIComponents() {
        // 1. Inicialización del modelo de datos
        this.tablaModeloLibros = new DefaultTableModel(0, 5);
        String[] cabecera = {"Id", "Libro", "Autor", "Precio", "Existencias"};
        this.tablaModeloLibros.setColumnIdentifiers(cabecera);

        // 2. Instanciación del JTable usando el modelo
        this.TablaLibros = new JTable(tablaModeloLibros);

        // 3. Llenar la tabla con los datos
        listarLibros();
    }

    private void listarLibros() {
        // Limpiar filas anteriores (fundamental para refrescar)
        tablaModeloLibros.setRowCount(0);

        // Obtener los libros de la BD
        var libros = libroServicio.listarLibros();

        // Iteramos cada libro y corregimos la sintaxis lambda
        libros.forEach(libro -> {

            // Creamos cada registro (renglón) para agregarlos a la tabla
            Object [] renglonLibro = {
                    libro.getIdLibro(),
                    libro.getNombreLibro(),
                    libro.getAutor(),
                    libro.getPrecio(),
                    libro.getExistencias()
            };
            this.tablaModeloLibros.addRow(renglonLibro);

        });
    }
}
