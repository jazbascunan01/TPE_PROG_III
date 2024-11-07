
package tpe;

import java.util.ArrayList;

public class Procesador {
    private String id;
    private boolean refrigerado;
    private int tiempoEjecucion;
    private ArrayList<Tarea> tareas;
    private int tareasCriticasCount;


    public Procesador(String id, boolean refrigerado, int tiempoEjecucion) {
        this.id = id;
        this.refrigerado = refrigerado;
        this.tiempoEjecucion = tiempoEjecucion;
        this.tareas = new ArrayList<Tarea>();
        this.tareasCriticasCount = 0;
    }

    public boolean asignarTarea(Tarea tarea) {
        if (tarea.getCritica()) {
            if (tareasCriticasCount >= 2) return false;
            tareasCriticasCount++;
        }

        tareas.add(tarea);
        this.tiempoEjecucion = this.tiempoEjecucion + tarea.getTiempo();
        return true;
    }
    public boolean contieneTarea(Tarea tarea){
        return this.tareas.contains(tarea);
    }
    public void removerTarea(Tarea tarea) {
        if (tareas.remove(tarea)) {
            this.tiempoEjecucion = this.tiempoEjecucion - tarea.getTiempo();
            if (tarea.getCritica()) tareasCriticasCount--;
        }
    }
    public Procesador copiar(){
        Procesador copia = new Procesador(this.id, this.refrigerado, this.tiempoEjecucion);
        for(Tarea tarea : this.tareas){
            copia.asignarTarea(tarea);//hacer add tarea
        }
        return copia;
    }

    public int size(){
        return this.tareas.size();
    }

    public int cuentaTareasCriticas() {
        return tareasCriticasCount;
    }

    public boolean isRefrigerado() {
        return refrigerado;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Procesador{" + "id='" + id + '\'' + ", tareas=" + tareas + ", tiempoActual=" + '}';
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param refrigerado the refrigerado to set
     */
    public void setRefrigerado(boolean refrigerado) {
        this.refrigerado = refrigerado;
    }

    /**
     * @return int return the tiempoMaximo
     */
    public int getTiempoEjecucion() {
        return tiempoEjecucion;
    }

    /**
     * @param tiempoMaximo the tiempoMaximo to set
     */
    public void setTiempoEjecucion(int tiempoEjecucion) {
        this.tiempoEjecucion = tiempoEjecucion;
    }

    /**
     * @return ArrayList<Tarea> return the tareas
     */
    public ArrayList<Tarea> getTareas() {
        return tareas;
    }

    /**
     * @param tareas the tareas to set
     */
    public void setTareas(ArrayList<Tarea> tareas) {
        this.tareas = tareas;
    }

    /**
     * @return int return the tareasCriticasCount
     */
    public int getTareasCriticasCount() {
        return tareasCriticasCount;
    }

    /**
     * @param tareasCriticasCount the tareasCriticasCount to set
     */
    public void setTareasCriticasCount(int tareasCriticasCount) {
        this.tareasCriticasCount = tareasCriticasCount;
    }


}