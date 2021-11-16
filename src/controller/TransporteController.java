/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.Usuario;
import model.Ruta;
import model.Trayecto;
import model.ReporteViajes;
import model.Precio;
import model.Exportar;

/**
 *
 * @author imac
 */
public class TransporteController {
    
    //==============USUARIO=======================

    public boolean insertUsuario(String nombre){
        Usuario usuario = new Usuario();
        usuario.nombreUsuario = nombre;
        return usuario.insert();
    }

    public boolean updateUsuario(int id, String nomUsuario){
        Usuario usuario = new Usuario();
        usuario.id_usuario = id;
        usuario.nombreUsuario = nomUsuario;
        return usuario.update();
    }
    public boolean deleteUsuario(int id){
        Usuario usuario = new Usuario();
        return usuario.delete(id);
    }
   
    
    public Usuario findUsuario(int id){
        Usuario usuario = new Usuario();
        return usuario.find(id);
    }
    
    public ArrayList<Usuario> selectUsuarios(){
        Usuario usuario = new Usuario();
        return usuario.select();
    }    
    
    //==============RUTA=======================
    
    public boolean insertRuta(int trayectoId, int usuarioId, String nomRuta, int precioActual, String nombreUsuario ){
        Ruta ruta = new Ruta();
        ruta.id_trayecto = trayectoId;
        ruta.id_usuario = usuarioId;
        ruta.nombre = nomRuta;
        ruta.precioVenta = precioActual;
      //ruta.id_ruta = rutaId;
        ruta.nombreUsuario = nombreUsuario;
        
        return ruta.insert();
    }

    public boolean updateRuta(int trayectoId, int usuarioId, String nomRuta, int precioActual, String nombreUsuario, int rutaId){
        Ruta ruta = new Ruta();
        ruta.id_ruta = rutaId;
        ruta.nombre = nomRuta;
        ruta.id_usuario = usuarioId;
        ruta.id_trayecto = trayectoId;
        ruta.precioVenta = precioActual;
        ruta.nombreUsuario = nombreUsuario;
        return ruta.update();
    }

    public boolean deleteRuta(int id){
        Ruta ruta = new Ruta();
        return ruta.delete(id);
    }
    
    public boolean deleteRutaAll(){
        Ruta ruta = new Ruta();
        return ruta.deleteAll();
    }    

    public Ruta findRuta(int id){
        Ruta ruta = new Ruta();
        return ruta.find(id);
    }

    public ArrayList<Ruta> selectRutas(){
        Ruta ruta = new Ruta();
        return ruta.select();
    }
 

//======================TRAYECTO===================================
        public ArrayList<Trayecto> selectTrayectos(){
        Trayecto trayecto = new Trayecto();
        return trayecto.select();
    } 
    
//=====================REPORTE VIAJES===============================
        
        public ArrayList<ReporteViajes> selectReporteViajes(){
        ReporteViajes re = new ReporteViajes();
        return re.select();
    }
    

//=====================PRECIOS===============================
        
        public ArrayList<Precio> selectPrecios(){
        Precio re = new Precio();
        return re.selectPrecios();
    }        
     public Precio find(int id){
        Precio precio = new Precio();
        return precio.find(id);       
     } 
//======================EXPORTAR============================        
        public ArrayList<Exportar> selectTotal(){
        Exportar exportar = new Exportar();
        return exportar.select();
    }
        
}
