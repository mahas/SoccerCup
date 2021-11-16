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

/**
 *
 * @author Ricardo
 */
public class ReporteViajes extends DbData{
    public String ruta;
    public int cantidad;
    public int usuarios;
    public int precio;
    public int total;

    public ArrayList<ReporteViajes> select(){
        Connection connection;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            String query = "SELECT t.nombre AS municipio, SUM(r.precioVenta) AS cantidad, COUNT(1) AS usuarios "
                    + "FROM trayectos AS t "
                    + "LEFT JOIN rutas AS r "
                    + "ON t.id_trayecto=r.id_trayecto "
                    + "GROUP BY t.nombre";
            PreparedStatement sentencia = connection.prepareStatement(query);
            ResultSet rs = sentencia.executeQuery();
            ArrayList<ReporteViajes> listaReporteViajes = new ArrayList<>();
            while(rs.next()){
                ReporteViajes re = new ReporteViajes();
                re.ruta = rs.getString(1);
                re.cantidad = rs.getInt(2);
                re.usuarios = rs.getInt(3);
                listaReporteViajes.add(re);
            }
            
            connection.close();
            return listaReporteViajes;
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
            return null;
        }
    }
   
    
    public ArrayList<ReporteViajes> selectTotal(){
        Connection connection;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            String query = "Select t.nombre as destino, t.precioActual as precio, count(1) as pasajeros, "
                    + "sum(r.precioVenta) as total "
                    + "from rutas as r "
                    + "left join trayectos as t "
                    + "on r.id_trayecto = t.id_trayecto "
                    + "group by t.nombre, t.precioActual";
            PreparedStatement sentencia = connection.prepareStatement(query);
            ResultSet rs = sentencia.executeQuery();
            ArrayList<ReporteViajes> listaReporteViajes = new ArrayList<>();
            while(rs.next()){
                ReporteViajes re = new ReporteViajes();
                    re.ruta = rs.getString(1);
                    re.precio = rs.getInt(2);
                    re.usuarios = rs.getInt(3);
                    re.total = rs.getInt(4);
                listaReporteViajes.add(re);
            }
            
            connection.close();
            return listaReporteViajes;
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
            return null;
        }
    }
            
}    
    



