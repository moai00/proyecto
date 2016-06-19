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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Resultados;
import modelo.Ruta;
import modelo.User;

/**
 *
 * @authors MPort y SGamarra
 */
public class ProyectoJDBC {

    private Connection conexion;
    
    //metode per a que apareguin les rutes quan donem d'alta un resultat
    public ArrayList<Ruta> selectRuta() throws MyException{
        ArrayList<Ruta> ruta = new ArrayList<>();
        conectar();
        try{
            String query = "select * from ruta;";
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                Ruta r = new Ruta();
                r.setIdruta(rs.getInt("idruta"));
                r.setNomruta(rs.getString("nomruta"));
                r.setDistancia(rs.getDouble("distancia"));
                r.setDesnivell(rs.getInt("desnivell"));
                r.setDificultat(rs.getInt("dificultat"));
                ruta.add(r);
            }
        }catch (SQLException ex){
            throw new MyException("Error al consultar " + ex.getLocalizedMessage());
        }finally{
            desconectar();
        }
        return ruta;
        
    }

    
    //quan donem d'alta un resultat amb aquesta consulta ens apareixen al combobox
    //els usuaris donats d'alta a la base de dades
    //amb un override a la clase User decidirem quins camps volem mostrar al combobox
    public ArrayList<User> selectUser() throws MyException {
        ArrayList<User> user = new ArrayList<>();
        conectar();
        try {
            String query = "select * from user;";
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                User u = new User();
                u.setNif(rs.getString("nif"));
                u.setNom(rs.getString("nom"));
                u.setCognom(rs.getString("cognoms"));
                u.setPes(rs.getInt("pes"));
                u.setEdad(rs.getInt("edad"));
                user.add(u);
            }

        } catch (SQLException ex) {

            throw new MyException("Error al consultar " + ex.getLocalizedMessage());
        } finally {
            desconectar();
        }
        return user;
    }

    public void insertResultat(Resultados res) throws MyException {
        conectar();

        try {
            String insert = "insert into resultados values (null, ?, ?,?,?,?);";
            PreparedStatement ps = conexion.prepareStatement(insert);
            ps.setString(1, res.getUser().getNif());
            ps.setInt(2, res.getRuta().getIdruta());
            ps.setInt(3, res.getHoras());
            ps.setInt(4, res.getMinutos());
            ps.setDouble(5, res.getVelmedia());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            throw new MyException("Error amb la inserció " + ex.getLocalizedMessage());
        } finally {
            desconectar();
        }
    }

    public boolean insertarUsuario(User usuario) throws MyException {
        conectar();
        if (conexion != null) {
            try {
                String insert = "insert into user values (?,?,?,?,?)";
                PreparedStatement ps = conexion.prepareStatement(insert);
                ps.setString(1, usuario.getNif());
                ps.setString(2, usuario.getNom());
                ps.setString(3, usuario.getCognom());
                ps.setInt(4, usuario.getPes());
                ps.setInt(5, usuario.getEdad());
                ps.executeUpdate();
                ps.close();
                return true;
            } catch (SQLException ex) {
                System.out.println("Error a l'insertar " + ex.getMessage());
                return false;
            } finally {
                desconectar();
            }
        } else {
            return false;
        }
    }

    public boolean insertarRuta(Ruta r) throws MyException {
        conectar();
        if (conexion != null) {
            try {
                String insert = "insert into ruta values(?,?,?,?,?)";
                PreparedStatement ps = conexion.prepareStatement(insert);
                ps.setInt(1, r.getIdruta());
                ps.setString(2, r.getNomruta());
                ps.setDouble(3, r.getDistancia());
                ps.setInt(4, r.getDesnivell());
                ps.setInt(5, r.getDificultat());
                ps.executeUpdate();
                ps.close();
                return true;
            } catch (SQLException ex) {
                System.out.println("Error a l'insertar " + ex.getMessage());
                return false;
            } finally {
                desconectar();
            }
        } else {
            return false;
        }
    }

    public void existeUsuario(User u) throws MyException {
        conectar();
        try {
            String query = "select * from user where nif='" + u.getNif() + "';";
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                throw new MyException("Ja existeix un usuari amb aquest NIF");
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            throw new MyException("Error a la consulta " + ex.getLocalizedMessage());
        } finally {
            desconectar();
        }
    }

    public void existeRuta(Ruta r) throws MyException {
        conectar();
        try {
            String query = "select *from ruta where idruta='" + r.getIdruta() + "';";
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                throw new MyException("Ja existeix una ruta amb aquest ID");
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            throw new MyException("Error a la consulta " + ex.getLocalizedMessage());
        } finally {
            desconectar();
        }
    }

    private void conectar() throws MyException {

        String url = "jdbc:mysql://localhost:3306/proyecto";
        String user = "root";
        String pass = "jeveris";
        try {
            conexion = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            throw new MyException("Error amb la connexió " + ex.getLocalizedMessage());
        }
    }

    private void desconectar() throws MyException {
        try {
            conexion.close();
        } catch (SQLException ex) {
            throw new MyException("Error amb la desconnexió " + ex.getLocalizedMessage());
        }
    }

}
