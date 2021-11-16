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
import model.*;

/**
 *
 * @author imac
 */
public class Trayecto extends DbData {
    
        public int id_trayecto;
        public String nombre;
        public int precioActual;
    
    public ArrayList<Trayecto> select(){
        Connection connection;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);

            String query = "SELECT * FROM trayectos";
            PreparedStatement sentencia = connection.prepareStatement(query);
            ResultSet rs = sentencia.executeQuery();
            ArrayList<Trayecto> listaTrayectos = new ArrayList<>();
            while(rs.next()){
                Trayecto trayecto = new Trayecto();
                trayecto.id_trayecto = rs.getInt(1);
                trayecto.nombre = rs.getString(2);
                
                listaTrayectos.add(trayecto);
//trayecto.precioActual = rs.getInt(3);
            }
            connection.close();
            return listaTrayectos;
           
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
            return null;
        }
    }
    
   /*     public boolean update(){
        Connection connection;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);

            String query = "UPDATE trayectos SET nombre=? WHERE id_usuario=?";
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
    }*/
    
    
    @Override
    public String toString() {
      //  return id_trayecto + " - " + nombre;
      return id_trayecto + " - " + nombre;
    }  
    
    
}
