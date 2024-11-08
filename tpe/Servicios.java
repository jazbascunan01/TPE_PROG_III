package tpe;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.LinkedList;
import tpe.utils.CSVReader;


/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "Tarea" según sus decisiones
 * de implementación.
 */
public class Servicios {

	//Estructuras donde se van a guardar las tareas y los servicios

	private final HashMap<String, Tarea> tasksHash;
	private final LinkedList<Tarea> criticalTareas;
	private final LinkedList<Tarea> NonCriticalTareas;
	private final Tree priorityTareaTree;
	private final ArrayList<Procesador> procesadores;


	/*
     * Expresar la complejidad temporal del constructor.
     * O(1) agregar tareas al hash
     * O(1) agregar tareas a las listas
     * O(h) agregar tareas al árbol, donde "h" es la altura del mismo.
     */
	public Servicios(String pathProcesadores, String pathTareas){
		this.tasksHash = new HashMap<String, Tarea>();
		this.criticalTareas = new LinkedList<Tarea>();
		this.NonCriticalTareas = new LinkedList<Tarea>();
		this.priorityTareaTree = new Tree();
		this.procesadores=new ArrayList<>();


		CSVReader reader = new CSVReader();
		reader.readProcesadores(pathProcesadores, this.procesadores);
		reader.readTareas(pathTareas, this.tasksHash, this.criticalTareas, this.NonCriticalTareas, this.priorityTareaTree);
	}
	
	/*
     * Expresar la complejidad temporal del servicio 1.
     * O(1) Constante ????
     */
	public Tarea servicio1(String ID) {
		if(this.tasksHash.containsKey(ID))
			return this.tasksHash.get(ID);
		return null;
	}

    /*
     * Expresar la complejidad temporal del servicio 2.
     * O(1) Constante ????
     */
	public LinkedList<Tarea> servicio2(boolean esCritica) {
		if(esCritica)
			return this.criticalTareas;
		return this.NonCriticalTareas;
	}

    /*
     * Expresar la complejidad temporal del servicio 3.
     * n es la cantidad de nodos del árbol
     * O(n) en el peor de los casos que todas las prioridades
     * del arbol esten entre el intervalo dado.
     * Segun la consigna el peor de los casos seria O(100)
     */
	public LinkedList<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
		return this.priorityTareaTree.getElemBetween(prioridadInferior, prioridadSuperior);
	}

	public Solucion asignarTareasBack(int tiempoEjecucion){
		Backtracking back = new Backtracking(this.procesadores, this.criticalTareas, this.NonCriticalTareas);
		return back.asignarTareasBack(tiempoEjecucion);
	}

}
