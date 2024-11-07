package tpe;

import java.util.ArrayList;
import java.util.LinkedList;

public class Backtracking {
 private Solucion sol;
    private ArrayList<Procesador> procesadores;
    private LinkedList<Tarea> tareasCriticas;
    private LinkedList<Tarea> tareas;
    private int tiempoEjecucion;

    public Backtracking(ArrayList<Procesador> procesadores, LinkedList<Tarea> tareasCriticas, LinkedList<Tarea> tareasNoCriticas) {
        this.sol = new Solucion();
        this.procesadores = procesadores;
        this.tareasCriticas = tareasCriticas; // se puede pasar todo por el main
        this.tareas = tareasNoCriticas;//es necesario preguntar si viene vacio?
        this.tareas.addAll(this.tareasCriticas);
        this.tiempoEjecucion = 0;
    }

    public Solucion asignarTareas(int tiempoEjecucion){
        if(procesadores.size()*2<this.tareasCriticas.size() || procesadores.isEmpty() || tareas.isEmpty()){
            return null;
        }else{
            this.tiempoEjecucion = tiempoEjecucion;
            Solucion parcial = new Solucion(this.procesadores);
            this.sol = new Solucion();//reiniciamos la variable
            asignarTareas(parcial);
            return this.sol;
        }
    }

    private void asignarTareas(Solucion parcial){
        parcial.incrCont();
        if(tareas.isEmpty()){
            if(this.sol.isEmpty() || parcial.getTiempo()<sol.getTiempo()){
                sol = parcial.copiar();// borrar la solucion anterior y copiar la nueva
            }
        }else{
            IteratorP<Procesador> it = new IteratorP<>(parcial.getAsignacion()().iterator());
            Tarea t = tareas.getFirst();
            while(it.hasNext()){
                Procesador p = it.next();
                if((!t.getCritica() || (t.getCritica() && p.cuentaTareasCriticas()<2)) &&
                        (p.isRefrigerado() || (!p.isRefrigerado() && p.getTiempoEjecucion()+t.getTiempo()<=this.tiempoEjecucion))){
                    if(this.sol.isEmpty() || (p.getTiempoEjecucion()+t.getTiempo() < this.sol.getTiempo())){
                        p.asignarTarea(t);
                        tareas.removeFirst();
                        parcial.actualizarTiempoEjecucion(p);
                        asignarTareas(parcial);
                        parcial.actualizarTiempoEjecucion(p,t);
                        tareas.addFirst(t);
                        p.removerTarea(t);

                    }
                }
            }
        }
    }
}
