/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import exceptions.MyException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @authors MPort y SGamarra
 */
public class ProyectoJDBC {

    private Connection conexion;
    
    
    public boolean insertarUsuario (User usuario) throws MyException{
        conectar();
        if (conexion != null){
        try{
            String insert = "insert into user values (?,?,?,?,?)";
            PreparedStatement ps = conexion.prepareStatement(insert);
            ps.setString(1, usuario.getNif());
            ps.setString (2, usuario.getNombre());
            ps.setString(3, usuario.getApellidos());
            ps.setString(4, usuario.getPeso());
            ps.setString(5, usuario.getEdad());
            ps.executeUpdate();
            ps.close();
            return true;
        }catch (SQLException ex){
            System.out.println("Error al insertar " + ex.getMessage());
            return false;
        }finally{
            desconectar();
        }
        }else{
            return false;
        }
    }
    
    public void existeUsuario (User u) throws MyException{
        conectar();
        try{
            String query = "select * from user where nif='" + u.getNif()+ "';";
            Statement st
        }
    }
        
    

    private void conectar() throws MyException{
        
            String url = "jdbc:mysql://localhost:3306/proyecto";
            String user = "root";
            String pass = "jeveris";
            try{
            conexion = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            throw new MyException("Error al conectar " + ex.getLocalizedMessage());
        }
    }

    private void desconectar() throws MyException{
        try {
            conexion.close();
        } catch (SQLException ex) {
           throw new MyException("Error al desconectar " + ex.getLocalizedMessage());  
        }
    }

}
