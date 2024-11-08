package tpe;

import java.util.ArrayList;

public class Procesador {

    private ArrayList<Tarea> tareas;
    private boolean refrigerado;
    private String idProcesador;
    private String codigo;
    private int anioFuncionamiento;
    private int tiempoEjecucion;//ultimos 2 atributos ver de actualizarlos en el add
    private int cantCriticas;

    public Procesador(boolean refrigerado, String idProcesador, String codigo, int anioFuncionamiento) {
        this.tareas = new ArrayList<Tarea>();
        this.refrigerado = refrigerado;
        this.idProcesador = idProcesador;
        this.codigo = codigo;
        this.anioFuncionamiento = anioFuncionamiento;
        this.tiempoEjecucion = 0;
        this.cantCriticas = 0;
    }

    public Procesador copy(){
        Procesador copia = new Procesador(this.refrigerado, this.idProcesador, this.codigo, this.anioFuncionamiento);
        for(Tarea t : this.tareas){
            copia.addTarea(t);//hacer add tarea
        }
        return copia;
    }

    public int size(){
        return this.tareas.size();
    }

    public int getTiempoEjecucion() {
        return tiempoEjecucion;
    }

    public void setTiempoEjecucion(int tiempoEjecucion) {
        this.tiempoEjecucion = tiempoEjecucion;
    }

    public void addTarea(Tarea t){
        this.tareas.add(t);
        this.tiempoEjecucion = this.tiempoEjecucion + t.getTiempo();
        if(t.getCritica()){
            this.cantCriticas++;
        }
    }

    public void deleteTarea(Tarea t){
        this.tareas.remove(t);
        this.tiempoEjecucion = this.tiempoEjecucion - t.getTiempo();
        if(t.getCritica()){
            this.cantCriticas--;
        }
    }

    public boolean containsTarea(Tarea t){
        return this.tareas.contains(t);
    }

    public boolean isRefrigerado() {
        return refrigerado;
    }

    public int getCantCriticas() {
        return this.cantCriticas;
    }

    public String toString(){
        String texto= "\nProcesador: " + this.idProcesador + "\n";
        texto = texto + " Tareas: ";
        for(Tarea t: this.tareas){
            texto = texto + t.toString();
        }
        return texto;
    }
}
