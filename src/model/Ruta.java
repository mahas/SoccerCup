/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Usuario;
import model.DbData;
/**
 *
 * @author imac
 */
public class Ruta extends DbData {
    
    public int id_ruta;
    public String nombre;
    public String nombreUsuario;
    public int id_usuario;
    public int id_trayecto;
    //public int posicion_id;
    private Usuario usuario;
    public int tipoRuta;
    public int precioVenta;
    

    
 




    
    

    
    public boolean insert(){
        

        
        Connection connection;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);

            String query = "INSERT INTO rutas VALUES(?,?,?,null,?,?)";
            PreparedStatement sentencia = connection.prepareStatement(query);
            sentencia.setInt(1, id_trayecto);
            sentencia.setInt(2, id_usuario);
            sentencia.setString(3, nombre);
            sentencia.setInt(4, precioVenta);
            sentencia.setString(5, nombreUsuario);
          //sentencia.setInt(3, id_ruta);
            
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

            String query = "UPDATE rutas SET id_trayecto=?, id_usuario=?, nombre=?, precioVenta=?, nombreUsuario=? WHERE id_ruta=?";
            PreparedStatement sentencia = connection.prepareStatement(query);
            sentencia.setInt(1, id_trayecto);
            sentencia.setInt(2, id_usuario);
            sentencia.setString(3, nombre);
            sentencia.setInt(4, precioVenta);
            sentencia.setString(5, nombreUsuario);
            sentencia.setInt(6, id_ruta);
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

            String query = "DELETE FROM rutas WHERE id_ruta=?";
            PreparedStatement sentencia = connection.prepareStatement(query);
            sentencia.setInt(1, id);///ATENCIÓN
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

            String query = "TRUNCATE TABLE rutas ";
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
    
    public Ruta find(int id){
        Connection connection;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);

            String query = "SELECT * FROM rutas WHERE id_ruta=?";
            PreparedStatement sentencia = connection.prepareStatement(query);
            sentencia.setInt(1, id);//ATENCIÓN
            ResultSet rs = sentencia.executeQuery();
            Ruta ruta = new Ruta();
            if(rs.next()){
                ruta.id_ruta = rs.getInt(4);//ATENCIÓN
                ruta.nombre = rs.getString(3);
                ruta.id_usuario = rs.getInt(2);
                ruta.nombreUsuario = rs.getString(6);
                ruta.precioVenta = rs.getInt(5);
                ruta.id_trayecto = rs.getInt(1);
                //JOptionPane.showMessageDialog(null, ruta.nombreUsuario + ", "+ ruta.precioVenta);
            }
            else{
                ruta = null;
            }
            connection.close();
            return ruta;
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
            return null;
        }
    } 
    
    public ArrayList<Ruta> select(){
        Connection connection;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);

            String query = "SELECT * FROM rutas";
            PreparedStatement sentencia = connection.prepareStatement(query);
            ResultSet rs = sentencia.executeQuery();
            ArrayList<Ruta> listaRutas = new ArrayList<>();
            while(rs.next()){
                Ruta ruta = new Ruta();
                ruta.id_ruta = rs.getInt(4);
                ruta.nombre = rs.getString(3);
                ruta.nombreUsuario = rs.getString(6);
                //ruta.nombreUsuario = rs.getString(3);
              //  ruta.id_usuario = rs.getInt(4);
                listaRutas.add(ruta);
            }
            connection.close();
            return listaRutas;
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
            return null;
        }
    }
    
    @Override
    public String toString() {
        return id_ruta + " - " + nombre + " - "+ nombreUsuario;
    }    
}
