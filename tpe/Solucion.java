package tpe;

import java.util.ArrayList;

public class Solucion {

    private final ArrayList<Procesador> procesadores;
    private int tiempoEjecucion;
    private int contadorEstados;

    public Solucion(ArrayList<Procesador> procesadores) {
        this.procesadores = new ArrayList<>(procesadores);
        this.tiempoEjecucion = 0;
        this.contadorEstados = 0;
    }

    public Solucion() {
        this.procesadores = new ArrayList<>();
        this.tiempoEjecucion = 0;
        this.contadorEstados = 0;
    }

    public boolean isEmpty() {
        return procesadores.isEmpty();
    }

    public Solucion copiar() {
        Solucion sol = new Solucion(new ArrayList<>());
        sol.setTiempoEjecucion(this.tiempoEjecucion);
        sol.setContadorEstados(this.contadorEstados);
        for (Procesador p : this.procesadores) {
            sol.addProcesador(p.copiar());
        }
        return sol;
    }

    public void actualizarTiempoEjecucion(Procesador p) {
        if (p.getTiempoEjecucion() > this.tiempoEjecucion) {
            this.tiempoEjecucion = p.getTiempoEjecucion();
        }
    }

    public void actualizarTiempoEjecucion(Procesador p, Tarea t) {
        if (p.getTiempoEjecucion() == this.tiempoEjecucion) {
            this.tiempoEjecucion = this.tiempoEjecucion - t.getTiempo();
        }
    }

    public int getContadorEstados() {
        return contadorEstados;
    }

    public void setContadorEstados(int contadorEstados) {
        this.contadorEstados = contadorEstados;
    }

    public int getTiempoEjecucion() {
        return tiempoEjecucion;
    }

    public void setTiempoEjecucion(int tiempoEjecucion) {
        this.tiempoEjecucion = tiempoEjecucion;
    }

    public ArrayList<Procesador> getProcesadores() {
        return new ArrayList<>(procesadores);
    }

    public void addProcesador(Procesador p) {
        this.procesadores.add(p);
        if (p.getTiempoEjecucion() > this.tiempoEjecucion) {
            this.tiempoEjecucion = p.getTiempoEjecucion();
        }
    }

    public void incrementarContadorEstados() {
        this.contadorEstados++;
    }

    @Override
    public String toString() {
        StringBuilder texto = new StringBuilder(
                """
                
                ========================================
                           Soluci\u00f3n Obtenida           
                ========================================
                Procesadores involucrados:
                """);

        if (this.procesadores.isEmpty()) {
            texto.append("  No se encontraron procesadores.\n");
        } else {
            for (Procesador p : this.procesadores) {
                texto.append(p.toString()).append("\n");
            }
        }

        texto.append(String.format("""
                                   
                                   Informaci\u00f3n adicional:
                                     -- Tiempo de ejecuci\u00f3n: %d segundos
                                     -- Contador de estados: %d
                                   ****************************************
                                   """,
                this.tiempoEjecucion, this.contadorEstados
        ));

        return texto.toString();
    }

}
