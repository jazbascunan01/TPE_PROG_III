package tpe;

import java.util.ArrayList;

public class Solucion {

    private final ArrayList<Procesador> procesadores;
    private int tiempoEjecucion;
    private int contadorEstados;

    // Constructor principal que inicializa los procesadores a partir de una lista existente
    public Solucion(ArrayList<Procesador> procesadores) {
        this();
        this.procesadores.addAll(procesadores);
    }

    // Constructor vacío que inicializa variables por defecto
    public Solucion() {
        procesadores = new ArrayList<>();
        tiempoEjecucion = 0;
        contadorEstados = 0;
    }

    // Método que verifica si la solución está vacía
    public boolean isEmpty() {
        return procesadores.isEmpty();
    }

    // Copia la solución actual en una nueva instancia
    public Solucion copiar() {
        Solucion copia = new Solucion();
        copia.tiempoEjecucion = this.tiempoEjecucion;
        copia.contadorEstados = this.contadorEstados;

        // Clona los procesadores en la nueva solución
        for (Procesador p : procesadores) {
            copia.addProcesador(p.copiar());
        }
        return copia;
    }

    // Actualiza el tiempo de ejecución de la solución si el tiempo del procesador es mayor
    public void actualizarTiempoEjecucion(Procesador procesador) {
        if (procesador.getTiempoEjecucion() > tiempoEjecucion) {
            tiempoEjecucion = procesador.getTiempoEjecucion();
        }
    }

    // Restaura el tiempo de ejecución cuando se desasigna una tarea
    public void actualizarTiempoEjecucion(Procesador procesador, Tarea tarea) {
        if (procesador.getTiempoEjecucion() == tiempoEjecucion) {
            tiempoEjecucion -= tarea.getTiempo();
        }
    }

    // Retorna el contador de estados explorados
    public int getContadorEstados() {
        return contadorEstados;
    }

    // Permite modificar el valor del contador de estados
    public void setContadorEstados(int contadorEstados) {
        this.contadorEstados = contadorEstados;
    }

    // Retorna el tiempo de ejecución total
    public int getTiempoEjecucion() {
        return tiempoEjecucion;
    }

    // Asigna un nuevo tiempo de ejecución a la solución
    public void setTiempoEjecucion(int tiempoEjecucion) {
        this.tiempoEjecucion = tiempoEjecucion;
    }

    // Devuelve una copia de la lista de procesadores
    public ArrayList<Procesador> getProcesadores() {
        return new ArrayList<>(procesadores);
    }

    // Agrega un procesador a la solución, actualizando el tiempo de ejecución si es necesario
    public void addProcesador(Procesador procesador) {
        procesadores.add(procesador);
        actualizarTiempoEjecucion(procesador);
    }

    // Incrementa el contador de estados
    public void incrementarContadorEstados() {
        contadorEstados++;
    }

    // Genera una representación en texto de la solución
    @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder("""
                
                ========================================
                           Soluci\u00f3n Obtenida           
                ========================================
                Procesadores involucrados:
                """);

        if (procesadores.isEmpty()) {
            resultado.append("  No se encontraron procesadores.\n");
        } else {
            for (Procesador p : procesadores) {
                resultado.append(p).append("\n");
            }
        }

        resultado.append(String.format("""
                                   
                                   Informaci\u00f3n adicional:
                                     -- Tiempo de ejecuci\u00f3n: %d segundos
                                     -- Contador de estados: %d
                                   ****************************************
                                   """, tiempoEjecucion, contadorEstados));

        return resultado.toString();
    }
}
