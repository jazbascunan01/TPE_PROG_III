package tpe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import tpe.utils.CSVReader;

/**
 * Clase principal de servicios que maneja la carga y asignación de tareas. NO
 * modificar la interfaz de esta clase ni sus métodos públicos.
 */
public class Servicios {

    // Estructuras donde se almacenan las tareas y los procesadores
    private final HashMap<String, Tarea> tareasMap;
    private final LinkedList<Tarea> tareasCriticas;
    private final LinkedList<Tarea> tareasNoCriticas;
    private final Tree priorityTareaTree;
    private final ArrayList<Procesador> procesadores;

    /*
     * Complejidad temporal del constructor:
     * - O(1) para inicialización de HashMap, listas y ArrayList.
     * - O(h) para la inserción en el árbol, donde h es la altura del árbol (dependiente del balance).
     * - La lectura de CSV depende del número de procesadores y tareas, pero no afecta la estructura interna.
     */
    public Servicios(String pathProcesadores, String pathTareas) {
        this.tareasMap = new HashMap<>();
        this.tareasCriticas = new LinkedList<>();
        this.tareasNoCriticas = new LinkedList<>();
        this.priorityTareaTree = new Tree();
        this.procesadores = new ArrayList<>();

        // Leer los procesadores y tareas desde los archivos CSV
        CSVReader reader = new CSVReader();
        reader.readProcesadores(pathProcesadores, this.procesadores);
        reader.readTareas(pathTareas, this.tareasMap, this.tareasCriticas, this.tareasNoCriticas, this.priorityTareaTree);
    }

    /*
     * Servicio 1: Obtener una tarea por su ID.
     * Complejidad: O(1) debido al acceso directo en HashMap.
     */
    public Tarea servicio1(String ID) {
        return this.tareasMap.get(ID);
    }

    /*
     * Servicio 2: Obtener la lista de tareas críticas o no críticas.
     * Complejidad: O(1) ya que devuelve directamente una lista existente.
     */
    public List<Tarea> servicio2(boolean esCritica) {
        return esCritica ? tareasCriticas : tareasNoCriticas;
    }

    /*
 	* Servicio 3: Obtener tareas dentro de un rango de prioridad.
	* Complejidad: O(h + m), donde h es la altura del árbol y m es el número de tareas en el rango.
	* La búsqueda para encontrar el rango de prioridades toma O(h), y la recolección de nodos dentro del rango es O(m).
     */
    public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
        return priorityTareaTree.getElemBetween(prioridadInferior, prioridadSuperior);
    }

    public Solucion asignarTareasBack(int tiempoEjecucion) {
        Backtracking back = new Backtracking(this.procesadores, this.tareasCriticas, this.tareasNoCriticas);
        return back.ejecutarBacktracking(tiempoEjecucion);
    }

    public Solucion asignarTareasGreedy() {
        Greedy greedy = new Greedy(this.procesadores, this.tareasCriticas, this.tareasNoCriticas);
        return greedy.asignarTareasGreedy();
    }
}
