package tpe;

import java.util.ArrayList;

public class Procesador {

    private final String id;
    private final String codigo;
    private final boolean refrigerado;
    private final int anioFuncionamiento;
    private final ArrayList<Tarea> tareas;
    private int tiempoEjecucion;
    private int cantCriticas;

    // Constructor principal que inicializa el procesador con sus propiedades
    public Procesador(String id, String codigo, boolean refrigerado, int anioFuncionamiento) {
        this.id = id;
        this.codigo = codigo;
        this.refrigerado = refrigerado;
        this.anioFuncionamiento = anioFuncionamiento;
        this.tareas = new ArrayList<>();
        this.tiempoEjecucion = 0;
        this.cantCriticas = 0;
    }

    // Método para copiar el estado actual del procesador
    public Procesador copiar() {
        Procesador copiaProcesador = new Procesador(this.id, this.codigo, this.refrigerado, this.anioFuncionamiento);
        copiaProcesador.tareas.addAll(this.tareas);  // Copiar tareas asociadas
        copiaProcesador.tiempoEjecucion = this.tiempoEjecucion;
        copiaProcesador.cantCriticas = this.cantCriticas;
        return copiaProcesador;
    }

    // Devuelve el tiempo total de ejecución acumulado por el procesador
    public int getTiempoEjecucion() {
        return tiempoEjecucion;
    }

    // Método para agregar una tarea al procesador
    public void addTarea(Tarea tarea) {
        tareas.add(tarea);
        tiempoEjecucion += tarea.getTiempo();  // Actualiza el tiempo de ejecución
        if (tarea.getCritica()) {
            cantCriticas++;  // Incrementa la cantidad de tareas críticas
        }
    }

    // Método para eliminar una tarea asignada al procesador
    public void deleteTarea(Tarea tarea) {
        tareas.remove(tarea);
        tiempoEjecucion -= tarea.getTiempo();  // Resta el tiempo de la tarea eliminada
        if (tarea.getCritica()) {
            cantCriticas--;  // Decrementa la cantidad de tareas críticas si aplica
        }
    }

    // Verifica si una tarea ya está asignada al procesador
    public boolean contieneTarea(Tarea tarea) {
        return tareas.contains(tarea);
    }

    // Verifica si el procesador cuenta con refrigeración
    public boolean isRefrigerado() {
        return refrigerado;
    }

    // Retorna la cantidad de tareas críticas asignadas al procesador
    public int getCantCriticas() {
        return cantCriticas;
    }

    // Sobrescribe el método toString para generar una representación en texto del procesador
    @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder();

        // Longitud del borde
        int longitudLinea = 45;
        String encabezado = "Procesador: " + id;

        // Cálculo para centrar el texto dentro del marco
        int padding = (longitudLinea - encabezado.length() - 2) / 2;

        // Construcción del marco
        resultado.append("*".repeat(longitudLinea)).append("\n");
        resultado.append("*").append(" ".repeat(longitudLinea - 2)).append("*\n");  // Línea vacía
        resultado.append("*").append(" ".repeat(padding)).append(encabezado)
                 .append(" ".repeat(longitudLinea - padding - encabezado.length() - 2)).append("*\n");
        resultado.append("*").append(" ".repeat(longitudLinea - 2)).append("*\n");  // Línea vacía
        resultado.append("*".repeat(longitudLinea)).append("\n");

        // Listado de tareas asignadas al procesador
        resultado.append(" Tareas:\n");
        for (Tarea tarea : tareas) {
            resultado.append(tarea).append("\n");
        }

        return resultado.toString();
    }
}
