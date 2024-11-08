package tpe;

import java.util.ArrayList;
import java.util.LinkedList;

public class Greedy {

    private ArrayList<Procesador> procesadores;
    private LinkedList<Tarea> criticalTareas;
    private LinkedList<Tarea> NonCriticalTareas;
    private int tiempoEjecucion;

    // Constructor
    public Greedy(ArrayList<Procesador> procesadores, LinkedList<Tarea> criticalTareas, LinkedList<Tarea> NonCriticalTareas) {
        this.procesadores = procesadores;
        this.criticalTareas = criticalTareas;
        this.NonCriticalTareas = NonCriticalTareas;
        this.tiempoEjecucion = 0;
    }

    public Solucion asignarTareasGreedy() {
        // Crear una nueva solución
        Solucion sol = new Solucion(this.procesadores);

        // Unir las listas de tareas críticas y no críticas
        LinkedList<Tarea> todasTareas = new LinkedList<>();
        todasTareas.addAll(criticalTareas);
        todasTareas.addAll(NonCriticalTareas);

        // Ordenar las tareas (primero por criticidad, luego por prioridad y tiempo)
        todasTareas.sort((t1, t2) -> {
            if (t1.getCritica() != t2.getCritica()) {
                return Boolean.compare(t2.getCritica(), t1.getCritica()); // Priorizar tareas críticas
            }
            if (t1.getPrioridad() != t2.getPrioridad()) {
                return Integer.compare(t2.getPrioridad(), t1.getPrioridad()); // Priorizar por prioridad
            }
            return Integer.compare(t1.getTiempo(), t2.getTiempo()); // Luego por tiempo
        });

        // Asignar las tareas
        for (Tarea t : todasTareas) {
            // Encontrar el procesador con el menor tiempo de ejecución
            Procesador mejorProcesador = null;
            for (Procesador p : procesadores) {
                if (mejorProcesador == null || p.getTiempoEjecucion() < mejorProcesador.getTiempoEjecucion()) {
                    mejorProcesador = p;
                }
            }

            // Asignar la tarea al mejor procesador encontrado
            if (mejorProcesador != null) {
                mejorProcesador.addTarea(t);
                sol.actualizarTiempoEjecucion(mejorProcesador);
            }
        }

        // Devolver la solución encontrada
        return sol;
    }
}
