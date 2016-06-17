/*
 * Clase Resultados en package modelo
 */
package modelo;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author MPort y SGamarra
 */
public class Resultados {
    // Declaramos las variables de la clase Resultados
    private int idresultados;
    private Ruta ruta;    
    private User user;    
    private String tiempo;

    // Constructor
    public Resultados() {
        tiempo = "";
    }
    
    // Bounds
    public static final String PROP_TIEMPO = "tiempo";

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        String oldTiempo = this.tiempo;
        this.tiempo = tiempo;
        propertyChangeSupport.firePropertyChange(PROP_TIEMPO, oldTiempo, tiempo);
    }


    public static final String PROP_USER = "user";

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        User oldUser = this.user;
        this.user = user;
        propertyChangeSupport.firePropertyChange(PROP_USER, oldUser, user);
    }


    public static final String PROP_RUTA = "ruta";

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        Ruta oldRuta = this.ruta;
        this.ruta = ruta;
        propertyChangeSupport.firePropertyChange(PROP_RUTA, oldRuta, ruta);
    }

    
    
    public static final String PROP_IDRESULTADOS = "idresultados";

    public int getIdresultados() {
        return idresultados;
    }

    public void setIdresultados(int idresultados) {
        int oldIdresultados = this.idresultados;
        this.idresultados = idresultados;
        propertyChangeSupport.firePropertyChange(PROP_IDRESULTADOS, oldIdresultados, idresultados);
    }

    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

}
