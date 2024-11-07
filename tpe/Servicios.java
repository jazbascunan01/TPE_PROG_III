package tpe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import tpe.utils.CSVReader;

/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "Tarea" según sus decisiones
 * de implementación.
 */
public class Servicios {
	private HashMap<String, Tarea> tareasMap;
	private LinkedList<Tarea> tareasCriticas;
	private LinkedList<Tarea> tareasNoCriticas;
	private Tree tareasPorPrioridad;
	private ArrayList<Procesador> procesadores;
	/*
	 * Expresar la complejidad temporal del constructor.
	 */
	public Servicios(String pathProcesadores, String pathTareas) {
		this.tareasMap = new HashMap<String, Tarea>();
		this.tareasCriticas = new LinkedList<>();
		this.tareasNoCriticas = new LinkedList<>();
		this.tareasPorPrioridad = new Tree();
		CSVReader reader = new CSVReader();
		reader.readProcessors(pathProcesadores);
		reader.readTasks(pathTareas, tareasMap, tareasCriticas, tareasNoCriticas);
	}

	/*
	 * Servicio 1: Dado un identificador de tarea obtener toda la información de la
	 * tarea asociada.
	 * Complejidad: O(1).
	 */
	public Tarea servicio1(String ID) {
		return this.tareasMap.get(ID);
	}

	/*
	 * Servicio 2: Filtrar tareas críticas o no críticas.
	 * Complejidad: O(1).
	 */
	public List<Tarea> servicio2(boolean esCritica) {
		return esCritica ? tareasCriticas : tareasNoCriticas;
	}

	/*
	 * Servicio 3: Obtener todas las tareas entre dos niveles de prioridad
	 * indicados.
	 * Complejidad: O(h + m), donde h es la altura del árbol y m es el número de
	 * tareas en el rango.
	 */
	public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
		return tareasPorPrioridad.getElemBetween(prioridadInferior, prioridadSuperior);
	}

	public Solucion asignarTareas(int tiempoEjecucion){
		Backtracking backtracking = new Backtracking(this.procesadores, this.tareasCriticas, this.tareasNoCriticas);
		return backtracking.asignarTareas(tiempoEjecucion);
	}
}
