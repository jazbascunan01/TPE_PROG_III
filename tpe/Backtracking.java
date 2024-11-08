package tpe;

import java.util.ArrayList;
import java.util.LinkedList;

public class Backtracking {

    private Solucion mejorSolucion;  // Solución óptima
    private ArrayList<Procesador> procesadores;  // Procesadores disponibles
    private LinkedList<Tarea> tareasCriticas;    // Tareas críticas
    private LinkedList<Tarea> tareas;     // Todas las tareas (críticas y no críticas)
    private int limiteTiempo;                    // Límite para procesadores sin refrigeración

    // Constructor que recibe los procesadores y las listas de tareas
    public Backtracking(ArrayList<Procesador> procesadores, LinkedList<Tarea> tareasCriticas, LinkedList<Tarea> tareasNoCriticas) {
        this.mejorSolucion = new Solucion();
        this.procesadores = procesadores;
        this.tareasCriticas = tareasCriticas;
        this.tareas = new LinkedList<>();
        this.tareas.addAll(tareasCriticas);  // Añade las tareas críticas
        this.tareas.addAll(tareasNoCriticas); // Luego las no críticas
        this.limiteTiempo = 0;  // Limite temporal por defecto
    }

    /**
     * Método que gestiona el inicio del proceso de backtracking.
     * @param tiempoLimite Tiempo máximo que un procesador puede estar sin refrigeración.
     * @return La mejor solución que puede encontrarse.
     */
    public Solucion ejecutarBacktracking(int tiempoLimite) {
        // Si hay problemas con los recursos, se termina de inmediato
        if (procesadores.isEmpty() || tareasCriticas.size() > procesadores.size() * 2 || tareas.isEmpty()) {
            return null; // No se puede ejecutar la asignación
        }

        this.limiteTiempo = tiempoLimite;  // Establece el límite de refrigeración
        Solucion solucionTemporal = new Solucion(procesadores);  // Se inicia con una solución parcial
        this.mejorSolucion = new Solucion(); // Reinicia la mejor solución
        explorarSoluciones(solucionTemporal); // Comienza el proceso de exploración
        return mejorSolucion;
    }

    /**
     * Método que explora las soluciones posibles mediante backtracking.
     * @param solucionActual La solución parcial actual.
     */
    private void explorarSoluciones(Solucion solucionActual) {
        // Incrementamos el contador de estados explorados
        solucionActual.incrementarContadorEstados();

        // Si no quedan tareas por asignar, se evalúa la solución actual
        if (tareas.isEmpty()) {
            if (mejorSolucion.isEmpty() || solucionActual.getTiempoEjecucion() < mejorSolucion.getTiempoEjecucion()) {
                mejorSolucion = solucionActual.copy();  // Copia la mejor solución encontrada hasta ahora
            }
            return;  // Regresa ya que no hay más tareas
        }

        // Explora cada procesador disponible para asignar la siguiente tarea
        for (Procesador procesador : procesadores) {
            // Toma la primera tarea y la evalúa
            Tarea tarea = tareas.peek(); 

            if (puedeAsignar(procesador, tarea)) {  // Verifica si el procesador puede manejar la tarea
                procesador.addTarea(tarea);
                tareas.poll();  // Elimina la tarea de la lista
                solucionActual.actualizarTiempoEjecucion(procesador);  // Actualiza la solución actual
                explorarSoluciones(solucionActual);  // Llamada recursiva
                revertirCambios(procesador, tarea, solucionActual);  // Revertir asignación
            }
        }
    }

    /**
     * Verifica si un procesador puede aceptar una tarea según las restricciones actuales.
     */
    private boolean puedeAsignar(Procesador procesador, Tarea tarea) {
        return (!tarea.getCritica() || procesador.getCantCriticas() < 2) &&
                (procesador.isRefrigerado() || procesador.getTiempoEjecucion() + tarea.getTiempo() <= limiteTiempo);
    }

    /**
     * Revertir la asignación de una tarea para continuar con la exploración de soluciones.
     */
    private void revertirCambios(Procesador procesador, Tarea tarea, Solucion solucionActual) {
        solucionActual.actualizarTiempoEjecucion(procesador, tarea);
        tareas.addFirst(tarea);  // Reinsertar la tarea al inicio de la lista
        procesador.deleteTarea(tarea);  // Eliminar la tarea del procesador
    }
}
