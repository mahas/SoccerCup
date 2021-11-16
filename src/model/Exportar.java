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

/**
 *
 * @author imac
 */
public class Exportar extends DbData {
    public String ruta;
    public int cantidad;
    public int usuarios;
    public int precio;
    public int total;    
    public int granTotal;
    
    
        public ArrayList<Exportar> select(){
        Connection connection;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            String query = "Select t.nombre as destino, t.precioActual as precio, count(r.id_ruta) as pasajeros, "
                    + "sum(r.precioVenta) as total "
                    + "from rutas as r "
                    + "left join trayectos as t "
                    + "on r.id_trayecto = t.id_trayecto "
                    + "group by t.nombre, t.precioActual";
            PreparedStatement sentencia = connection.prepareStatement(query);
            ResultSet rs = sentencia.executeQuery();
            ArrayList<Exportar> listaExportar = new ArrayList<>();
            while(rs.next()){
                Exportar ex = new Exportar();
                    ex.ruta = rs.getString(1);
                    ex.precio = rs.getInt(2);
                    ex.usuarios = rs.getInt(3);
                    ex.total = rs.getInt(4);
                    granTotal += rs.getInt(4);
                listaExportar.add(ex);
            }
            
            connection.close();
            return listaExportar;
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
            return null;
        }
    }
    
}
