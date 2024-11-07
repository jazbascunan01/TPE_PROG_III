package tpe;

import java.util.ArrayList;
import java.util.List;

public class Solucion {

    private ArrayList<Procesador> asignacion;
    private int tiempoEjecucion, cont;

    // Constructor vacío
    public Solucion() {
        this.asignacion = new ArrayList<Procesador>();
        this.tiempoEjecucion = 0; // inicia en 0
        this.cont = 0;
    }

    // Constructor con lista de procesadores
    public Solucion(ArrayList<Procesador> procesadores) {
        this.asignacion = new ArrayList<>(procesadores);
        this.tiempoEjecucion = 0; // inicia en 0
        this.cont = 0;
    }


    
    public boolean isEmpty(){
        return asignacion.isEmpty();
    }
    public Solucion copiar(){
        Solucion solucion= new Solucion(new ArrayList<>());
        solucion.setTiempo(tiempoEjecucion);
        solucion.setCont(this.cont);
        for(Procesador p:this.asignacion){
            solucion.addAsignacion(p.copiar());
        }
        return solucion;

    }
    public int getCont(){
        return cont;
    }
    public void setCont(int contador){
        this.cont=contador;
    }
    public int getTiempo() {
        return tiempoEjecucion;
    }

    public void setTiempo(int tiempo) {
        this.tiempoEjecucion = tiempo;
    }


    public void addAsignacion(Procesador procesador) {
        if (!asignacion.contains(procesador)) {
            asignacion.add(procesador);
        }
        if(procesador.getTiempoEjecucion()>this.tiempoEjecucion){
            this.tiempoEjecucion = procesador.getTiempoEjecucion();
        }
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

    public List<Procesador> getAsignacion() {
        return new ArrayList<Procesador>(asignacion);
    }
    public boolean contieneTarea(Tarea tarea) {
        for (Procesador p : asignacion) {
            if(p.contieneTarea(tarea)){
                return true;
            }
        }
        return false;
    }
    public void incrCont() {
        this.cont++;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Asignación de tareas:\n");

        for (Procesador procesador : this.asignacion) {
            sb.append("Procesador ID: ").append(procesador.getId()).append("\n");
            for (Tarea tarea : procesador.getTareas()) {
                sb.append("  Tarea ID: ").append(tarea.getId())
                        .append(" Nombre: ").append(tarea.getNombre())
                        .append(" Tiempo: ").append(tarea.getTiempo())
                        .append(" Prioridad: ").append(tarea.getPrioridad()).append("\n");
            }
        }
        sb.append("Tiempo máximo entre todos los procesadores: ").append(this.tiempoEjecucion);
        return sb.toString();
    }

}
