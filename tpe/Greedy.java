package tpe;

import java.util.ArrayList;
import java.util.LinkedList;

public class Greedy {

    private final ArrayList<Procesador> procesadores;
    private LinkedList<Tarea> tareasCriticas;
    private final LinkedList<Tarea> tareasNoCriticas;
    private int tiempoTotal;

    // Constructor que recibe los procesadores y las listas de tareas
    public Greedy(ArrayList<Procesador> procesadores, LinkedList<Tarea> tareasCriticas, LinkedList<Tarea> tareasNoCriticas) {
        this.procesadores = procesadores;
        this.tareasCriticas = tareasCriticas;
        this.tareasNoCriticas = tareasNoCriticas;
        this.tiempoTotal = 0;
    }

  /**
     * Método principal que gestiona la asignación de tareas utilizando la estrategia Greedy.
     * @param tiempoEjecucion Tiempo límite para los procesadores no refrigerados.
     * @return La mejor solución encontrada.
     */
    public Solucion asignarTareasGreedy(int tiempoEjecucion) {
        this.tiempoTotal = tiempoEjecucion;  // Establece el límite de tiempo para procesadores sin refrigeración
        Solucion solucionFinal = new Solucion(procesadores);  // Inicializa la solución con los procesadores disponibles
        LinkedList<Tarea> todasLasTareas = combinarYOrdenarTareas();  // Combina las tareas y las ordena

        // Asignar cada tarea a un procesador de la manera más eficiente
        for (Tarea tarea : todasLasTareas) {
            Procesador mejorProcesador = obtenerMejorProcesador(tarea);  // Encuentra el procesador con el menor tiempo de ejecución que pueda aceptar la tarea
            if (mejorProcesador != null) {
                asignarTareaAlProcesador(mejorProcesador, tarea, solucionFinal);  // Asigna la tarea al procesador seleccionado
            }
        }

        return solucionFinal;  // Retorna la solución optimizada
    }

    /**
     * Combina las tareas críticas y no críticas, luego las ordena según un criterio específico.
     * @return Una lista de todas las tareas ordenadas
     */
    private LinkedList<Tarea> combinarYOrdenarTareas() {
        LinkedList<Tarea> todasTareas = new LinkedList<>();
        todasTareas.addAll(tareasCriticas);
        todasTareas.addAll(tareasNoCriticas);

        // Ordenamos las tareas por criticidad, prioridad y tiempo
        todasTareas.sort((t1, t2) -> {
            if (t1.getCritica() != t2.getCritica()) {
                return Boolean.compare(t2.getCritica(), t1.getCritica()); // Tareas críticas primero
            }
            if (t1.getPrioridad() != t2.getPrioridad()) {
                return Integer.compare(t2.getPrioridad(), t1.getPrioridad()); // Luego por prioridad
            }
            return Integer.compare(t1.getTiempo(), t2.getTiempo()); // Finalmente, por tiempo
        });
        
        return todasTareas;
    }

    /**
     * Encuentra el procesador con el menor tiempo de ejecución que pueda aceptar la tarea.
     * @param tarea La tarea que se va a asignar.
     * @return El procesador con el menor tiempo de ejecución que pueda aceptar la tarea.
     */
    private Procesador obtenerMejorProcesador(Tarea tarea) {
        Procesador mejorProcesador = null;
        for (Procesador procesador : procesadores) {
            // Verifica si el procesador puede aceptar la tarea respetando el límite de tiempo
            if (puedeAsignar(procesador, tarea)) {
                if (mejorProcesador == null || procesador.getTiempoEjecucion() < mejorProcesador.getTiempoEjecucion()) {
                    mejorProcesador = procesador;
                }
            }
        }
        return mejorProcesador;
    }
    /**
     * Verifica si un procesador puede aceptar una tarea según las restricciones actuales.
     */
    private boolean puedeAsignar(Procesador procesador, Tarea tarea) {
        return (!tarea.getCritica() || procesador.getCantCriticas() < 2) &&
               (procesador.isRefrigerado() || procesador.getTiempoEjecucion() + tarea.getTiempo() <= tiempoTotal);
    }
    /**
     * Asigna una tarea a un procesador y actualiza el tiempo de ejecución de la solución.
     * @param procesador El procesador al que se le asigna la tarea.
     * @param tarea La tarea a asignar.
     * @param solucion La solución que se va a actualizar.
     */
    private void asignarTareaAlProcesador(Procesador procesador, Tarea tarea, Solucion solucion) {
        procesador.addTarea(tarea);
        solucion.actualizarTiempoEjecucion(procesador);
        solucion.incrementarContadorEstados();
    }
}
