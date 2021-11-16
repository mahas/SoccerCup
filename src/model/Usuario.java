package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Usuario extends DbData{
    public int id_usuario;
    public String nombreUsuario;
    
    public boolean insert(){
        Connection connection;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);

            String query = "INSERT INTO usuarios VALUES(null,?)";
            PreparedStatement sentencia = connection.prepareStatement(query);
            sentencia.setString(1, nombreUsuario);
            int filasAfectadas = sentencia.executeUpdate();
            connection.close();
            if(filasAfectadas > 0){
                return true;
            }
            else{
                return false;
            }

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
            return false;
        }
    }

    public boolean update(){
        Connection connection;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);

            String query = "UPDATE usuarios SET nombre=? WHERE id_usuario=?";
            PreparedStatement sentencia = connection.prepareStatement(query);
            sentencia.setString(1, nombreUsuario);
            sentencia.setInt(2, id_usuario);
            int filasAfectadas = sentencia.executeUpdate();
            connection.close();
            if(filasAfectadas > 0){
                return true;
            }
            else{
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
            return false;
        }
    }
    
    public boolean delete(int id){
        Connection connection;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);

            String query = "DELETE FROM usuarios WHERE id_usuario=?";
            PreparedStatement sentencia = connection.prepareStatement(query);
            sentencia.setInt(1, id);
            int filasAfectadas = sentencia.executeUpdate();
            connection.close();
            if(filasAfectadas > 0){
                return true;
            }
            else{
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
            return false;
        }
    }

        public boolean deleteAll(){
        Connection connection;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);

            String query = "TRUNCATE TABLE usuarios ";
            PreparedStatement sentencia = connection.prepareStatement(query);
            sentencia.execute();
            int filasAfectadas = sentencia.executeUpdate();
            connection.close();
            if(filasAfectadas > 0){
                return true;
            }
            else{
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
            return false;
        }
    }
    
    public Usuario find(int id_usuario){
        Connection connection;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);

            String query = "SELECT * FROM usuarios WHERE id_usuario=?";
            PreparedStatement sentencia = connection.prepareStatement(query);
            sentencia.setInt(1, id_usuario);
            ResultSet rs = sentencia.executeQuery();
            Usuario usuario = new Usuario();
            if(rs.next()){
                usuario.id_usuario = rs.getInt(1);
                usuario.nombreUsuario = rs.getString(2);
            }
            else{
                usuario = null;
            }
            connection.close();
            return usuario;
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
            return null;
        }
    }

    public ArrayList<Usuario> select(){
        Connection connection;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);

            String query = "SELECT * FROM usuarios";
            PreparedStatement sentencia = connection.prepareStatement(query);
            ResultSet rs = sentencia.executeQuery();
            ArrayList<Usuario> listaUsuarios = new ArrayList<>();
            while(rs.next()){
                Usuario usuario = new Usuario();
                usuario.id_usuario = rs.getInt(1);
                usuario.nombreUsuario = rs.getString(2);
                listaUsuarios.add(usuario);
            }
            connection.close();
            return listaUsuarios;
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
            return null;
        }
    }
    
    
    @Override
    public String toString() {
        return nombreUsuario;
    }
    
    
    
}
