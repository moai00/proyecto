/*
 * Llistat de Rutes
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
public class ListaRutas {
    
    private ObservableList<Ruta> lista;
    
    public ListaRutas() {
        lista = ObservableCollections.observableList(new ArrayList<Ruta>());
    }
    public void altaRuta(Ruta r){
        lista.add(r);
    }
    public void bajaRuta(Ruta r){
        lista.remove(r);
    }
    public static final String PROP_LISTA = "lista";

    public ObservableList<Ruta> getLista() {
        return lista;
    }

    public void setLista(ObservableList<Ruta> lista) {
        ObservableList<Ruta> oldLista = this.lista;
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
