package UTN.datos;


import UTN.conexion.Conexion;
import UTN.dominio.Estudiante;

import javax.swing.*;

import static UTN.conexion.Conexion.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {
    //Metodo listar
    public List<Estudiante> listarEstudiantes(){
        List<Estudiante> estudiantes = new ArrayList<>();
        //Creamos algunos objetos que son necesarios para comunicarnos con la base de datos
        PreparedStatement ps; // Envia la sentencia a la base de datos
        ResultSet rs; // Obtenemos el resultado de la base de datos
        //Creamos un objeto de tipo conexion
        Connection con = getConnection();
        String sql = "SELECT * FROM estudiantes ORDER BY estudiantes2022";
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                var estudiante = new Estudiante();
                estudiante.setIdEstudiante(rs.getInt("idestudiante2022"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                //Falta agregarlo a la lista
                estudiantes.add(estudiante);
            }

        }catch (Exception e){
            System.out.println("Ocurrio un error al seleccionar datos: "+e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Ocurrio un error al cerrar la conexion: "+e.getMessage());
            }
        }//Fin finally
        return estudiantes;
    }//Fin metodo listar

    //Metodo por id -> fin by id
    public boolean buscarEstudiantePorId(Estudiante estudiante){
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConnection();
        String sql = "SELEC * FROM estidiantes2022 WHERE idestudiantes=?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, estudiante.getIdEstudiante());
            rs = ps.executeQuery();
            if(rs.next()){
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                return true; // Se encontro un registro
            } // Fin if
        } catch (Exception e){
            System.out.println("Ocurrio un error al buscar estudiante: "+e.getMessage());
        }//Fin catch
        finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Ocurrio un error al cerrar la conexion: "+e.getMessage());
            }//Fin catch
        }//Fin finally
        return false;
    }
    // Metodo egregar un nuevo estudiante
    public boolean agregarEstudiante(Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConnection();
        String sql = "INSERT INTO estudiante2022 (nombre, apellido, telefono, email) VALUES(?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getEmail());
            ps.execute();
            return true;

        }catch (Exception e){
            System.out.println("Ocurrio un error al agregar estudiante: "+e.getMessage());
        }//Fin catch
        finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar la conexin: "+e.getMessage());
            }//Fin catch
        }//Fin finally
        return false;
    }//Fin metodo agregarEstudiante

    // Metodo para modificar estudiante
    public boolean modificarEstudiante(Estudiante estudiante) {
        PreparedStatement ps;
        Connection con = getConnection();
        String sql = "UPDATE estudiantes2022 SET nombre=?, apellido=?, telefono=?, email=? WHERE idestudiantes2022=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getEmail());
            ps.setInt(5, estudiante.getIdEstudiante());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al modificar estudiante: " + e.getMessage());
            return false;
        }//Fin catch
        finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar la conexion: "+e.getMessage());
            }//Fin catch
        }//Fin finally
        return false;
    }//Fin metodo modificarEstudiante

    public boolean eliminarEstudiante(Estudiante estudiante) {
        PreparedStatement ps;
        Connection con = getConnection();
        String sql = "DELETE FROM estudiantes2022 WHERE idestudiantes2022=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, estudiante.getIdEstudiante());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar estudiante: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar la conexion: "+e.getMessage());
            }
        }
        return false;
    }
    public class Main {
        public static void main(String[] args) {
            EstudianteDAO estudianteDao = new EstudianteDAO();

            // Modificar estudiante
            Estudiante estudianteModificado = new Estudiante(1, "Juan Carlos", "Juarez", "554466332");
            boolean modificado = estudianteDao.modificarEstudiante(estudianteModificado);

            if (modificado) {
                System.out.println("Estudiante modificado: " + estudianteModificado);
            } else {
                System.out.println("No se modificó el estudiante: " + estudianteModificado);
            }

            // Agregar estudiante
            Estudiante nuevoEstudiante = new Estudiante("Carlos", "Lara", "5495544223", "carlosl@mail.com");
            boolean agregado = estudianteDao.agregarEstudiante(nuevoEstudiante);

            if (agregado) {
                System.out.println("Estudiante agregado: " + nuevoEstudiante);
            } else {
                System.out.println("No se ha agregado estudiante: " + nuevoEstudiante);
            }

            // Eliminar estudiante con id 3
            Estudiante estudianteEliminar = new Estudiante(3);
            boolean eliminado = estudianteDao.eliminarEstudiante(estudianteEliminar);

            if (eliminado) {
                System.out.println("Estudiante eliminado: " + estudianteEliminar);
            } else {
                System.out.println("No se eliminó estudiante: " + estudianteEliminar);
            }

            // Listar estudiantes
            System.out.println("Listado de estudiantes: ");
            List<Estudiante> estudiantes = estudianteDao.listarEstudiantes();
            estudiantes.forEach(System.out::println); // Lambda
        }
    }



    // Agregar estudiante
    Estudiante nuevoEstudiante = new Estudiante("Carlos", "Lara","2121121","carwerwe@email.com");
    boolean agregado = estudianteDao.agregarEstudiante(nuevoEstudiante);
        if (agregado)
            System.out.println("Estudiante agregado: " + nuevoEstudiante);
        else
                System.out.println("No se ha agregado estudiante: " + nuevoEstudiante);

    // Buscar por id
    Estudiante estudiante1 = new Estudiante(1);
        System.out.println("Estudiante antes de la búsqueda: " + estudiante1);
    boolean encontrado = estudianteDao.buscarEstudiantePorId(estudiante1);

        if (encontrado)
            System.out.println("Estudiante encontrado: " + estudiante1);
        else
                System.out.println("No se encontró el estudiante con id: " + estudiante1.getIdEstudiante());
}
}