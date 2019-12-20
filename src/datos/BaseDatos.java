package datos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Pahblo
 */
public class BaseDatos {

    /**
     * Clase que lleva el control de Archivos de texto.
     *
     * @param <T> Tipo de elementos a guardar, ya sea Inventario, Ventas,
     * Cliente o pedidos.
     */
    public class Archivo<T> {

        private FileOutputStream fos;
        private ObjectOutputStream oos;
        private FileInputStream fis;
        private ObjectInputStream ois;

        /**
         * Inicializa la clase Archivo.
         */
        public Archivo() {
            fos = null;
            oos = null;
            fis = null;
            ois = null;
        }

        /**
         * Guarda una lista de productos a un archivo.
         *
         * @exception e's
         * @param lista Lista a guardar.
         * @param nombre Nombre del archivo.
         */
        public void guardar(ObservableList<T> lista, String nombre) {
            try {
                fos = new FileOutputStream(nombre);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(new ArrayList<>(lista));
                oos.close();
                fos.close();
            } catch (FileNotFoundException e) {
                System.err.println("ERROR: " + e.getLocalizedMessage());
            } catch (IOException e) {
                System.err.println("ERROR: " + e.getLocalizedMessage());
            }
        }

        /**
         * Carga una lista desde un archivo.
         *
         * @exception e's
         * @param archivo Nombre del archivo.
         * @return Lista del archivo.
         */
        public ObservableList<T> cargar(String archivo) {
            ObservableList<T> productos = FXCollections.observableArrayList();
            try {
                fis = new FileInputStream(archivo);
                ois = new ObjectInputStream(fis);
                List<T> lista = (List<T>) ois.readObject();
                productos = FXCollections.observableList(lista);
                ois.close();
                fis.close();
            } catch (FileNotFoundException e) {
                System.err.println("ERROR: " + e.getLocalizedMessage());
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("ERROR: " + e.getLocalizedMessage());
            }
            return productos;
        }
    }
}
