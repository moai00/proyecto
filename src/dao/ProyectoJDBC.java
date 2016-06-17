/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @authors MPort y SGamarra
 */
public class ProyectoJDBC {

    private Connection connection;

    private void conectar(){
        try {
            String url = "jdbc:mysql://localhost:3306/proyecto";
            String user = "root";
            String pass = "jeveris";

            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            System.out.println("Error al conectar " + ex.getMessage());
            connection = null;
        }
    }

    private void desconectar(){
        try {
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Error al desconectar " + ex.getMessage());
        }
    }

}
