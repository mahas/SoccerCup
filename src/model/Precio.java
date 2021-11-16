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
import model.DbData;


/**
 *
 * @author imac
 */
public class Precio extends DbData{
    public int precioVenta;
    public int id_trayecto;
    
    
        public ArrayList<Precio> selectPrecios(){
        Connection connection;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);

            String query = "SELECT * FROM trayectos";
            PreparedStatement sentencia = connection.prepareStatement(query);
            ResultSet rs = sentencia.executeQuery();
            ArrayList<Precio> listaPrecios = new ArrayList<>();
            while(rs.next()){
                Precio precio = new Precio();
                precio.id_trayecto = rs.getInt(1);
                precio.precioVenta = rs.getInt(3);
                listaPrecios.add(precio);
                
            }
            connection.close();
            return listaPrecios;
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
            return null;
        }
    }
        
        
            public Precio find(int id_trayecto){
        Connection connection;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);

            String query = "SELECT * FROM rutas WHERE id_trayecto=?";
            PreparedStatement sentencia = connection.prepareStatement(query);
            sentencia.setInt(1, id_trayecto);
            ResultSet rs = sentencia.executeQuery();
            Precio precio = new Precio();
            if(rs.next()){
                precio.id_trayecto = rs.getInt(1);
                //usuario.nombreUsuario = rs.getString(2);
            }
            else{
                precio = null;
            }
            connection.close();
            return precio;
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
            return null;
        }
    }
    
 @Override
    public String toString() {
        return precioVenta + "";   
        }
}
