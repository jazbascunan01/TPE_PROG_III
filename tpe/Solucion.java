package tpe;

import java.util.ArrayList;

public class Solucion {

    private ArrayList<Procesador> procesadores;
    private int tiempoEjecucion;
    private int contadorEstados;

    public Solucion(ArrayList<Procesador> procesadores) {
        this.procesadores = new ArrayList<>(procesadores);
        this.tiempoEjecucion = 0;
        this.contadorEstados = 0; // preguntar si esta bien tomarlo como 0 o -1
    }

    public Solucion() {
        this.procesadores = new ArrayList<>();
        this.tiempoEjecucion = 0;
        this.contadorEstados = 0; // preguntar si esta bien tomarlo como 0 o -1
    }

    public boolean isEmpty() {
        return procesadores.isEmpty();
    }

    public Solucion copy(){ // si agregamos atributos tenerlo en cuenta
        Solucion sol = new Solucion(new ArrayList<>());
        sol.setTiempoEjecucion(this.tiempoEjecucion);
        sol.setContadorEstados(this.contadorEstados);
        for (Procesador p : this.procesadores){
            sol.addProcesador(p.copy());
        }
        return sol;
    }

    public void actualizarTiempoEjecucion(Procesador p) {
        if (p.getTiempoEjecucion()>this.tiempoEjecucion){
            this.tiempoEjecucion = p.getTiempoEjecucion();
        }
    }

    public void actualizarTiempoEjecucion(Procesador p, Tarea t) {
        if (p.getTiempoEjecucion()==this.tiempoEjecucion){
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
        return new ArrayList<Procesador>(procesadores);
    }

    public void addProcesador(Procesador p) {
        this.procesadores.add(p);
        if(p.getTiempoEjecucion()>this.tiempoEjecucion){
            this.tiempoEjecucion = p.getTiempoEjecucion();
        }
    }

    public boolean containsTarea(Tarea t) {
        for (Procesador p : procesadores) {
            if(p.containsTarea(t)){
                return true;
            }
        }
        return false;
    }

    public void incrementarContadorEstados() {
        this.contadorEstados++;
    }
@Override
public String toString() {
    // Usamos StringBuilder para optimizar el manejo de cadenas
    StringBuilder texto = new StringBuilder();

    // Agrega un encabezado visualmente atractivo
    texto.append("========================================\n");
    texto.append("           Solución Obtenida           \n");
    texto.append("========================================\n");

    // Añade la lista de procesadores con viñetas
    texto.append("Procesadores involucrados: ");
    if (this.procesadores.isEmpty()) {
        texto.append("No se encontraron procesadores.\n");
    } else {
        // Agrega cada procesador en una misma línea, separados por guiones
        for (int i = 0; i < this.procesadores.size(); i++) {
            Procesador p = this.procesadores.get(i);
            texto.append("").append(p.toString());
            if (i < this.procesadores.size() - 1) {
                texto.append("");
            }
        }
        texto.append("\n");
    }

    // Añade el tiempo de ejecución y contador de estados
    texto.append("\nInformación adicional:\n");
    texto.append("  -- Tiempo de ejecución: ").append(this.tiempoEjecucion).append(" segundos\n");
    texto.append("  -- Contador de estados: ").append(this.contadorEstados).append("\n");

    // Cierra el formato con líneas divisorias
    texto.append("\n****************************************\n");

    // Retorna la cadena formateada
    return texto.toString();
}


    
}
