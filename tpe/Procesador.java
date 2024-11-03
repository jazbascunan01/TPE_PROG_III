
package tpe;

import java.util.ArrayList;

public class Procesador {
    private String id;
    private boolean refrigerado;
    private int tiempoMaximo;
    private ArrayList<Tarea> tareas;
    private int tareasCriticasCount;
    private int tiempoActual;

    public Procesador(String id, boolean refrigerado, int tiempoMaximo) {
        this.id = id;
        this.refrigerado = refrigerado;
        this.tiempoMaximo = tiempoMaximo;
        this.tareas = new ArrayList<>();
        this.tareasCriticasCount = 0;
        this.tiempoActual = 0;
    }

    public boolean asignarTarea(Tarea tarea) {
        if (tarea.getCritica()) {
            if (tareasCriticasCount >= 2) return false;
            tareasCriticasCount++;
        }
        if (!refrigerado && tiempoActual + tarea.getTiempo() > tiempoMaximo) return false;

        tareas.add(tarea);
        tiempoActual += tarea.getTiempo();
        return true;
    }

    public void removerTarea(Tarea tarea) {
        if (tareas.remove(tarea)) {
            tiempoActual -= tarea.getTiempo();
            if (tarea.getCritica()) tareasCriticasCount--;
        }
    }

    public int getTiempoTotal() {
        return tiempoActual;
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
        return "Procesador{" + "id='" + id + '\'' + ", tareas=" + tareas + ", tiempoActual=" + tiempoActual + '}';
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
    public int getTiempoMaximo() {
        return tiempoMaximo;
    }

    /**
     * @param tiempoMaximo the tiempoMaximo to set
     */
    public void setTiempoMaximo(int tiempoMaximo) {
        this.tiempoMaximo = tiempoMaximo;
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

    /**
     * @return int return the tiempoActual
     */
    public int getTiempoActual() {
        return tiempoActual;
    }

    /**
     * @param tiempoActual the tiempoActual to set
     */
    public void setTiempoActual(int tiempoActual) {
        this.tiempoActual = tiempoActual;
    }

}