/*
 * Llistat d'Usuaris
 */
package modelo;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;

/**
 *
 * @author MPort y SGamarra
 */
public class ListaUsers {
    private ObservableList<User> lista;
    
    public ListaUsers() {
        lista = ObservableCollections.observableList(new ArrayList<User>());
    }
    
    public void altaUser(User u){
        lista.add(u);
    }
    public void bajaUser(User u){
        lista.remove(u);
    }
    public static final String PROP_LISTA = "lista";

    public ObservableList<User> getLista() {
        return lista;
    }

    public void setLista(ObservableList<User> lista) {
        ObservableList<User> oldLista = this.lista;
        this.lista = lista;
        propertyChangeSupport.firePropertyChange(PROP_LISTA, oldLista, lista);
    }

    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}
