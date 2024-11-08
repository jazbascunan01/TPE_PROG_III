package tpe;

import java.util.ArrayList;
import java.util.LinkedList;

public class Backtracking {

    private Solucion sol;
    private ArrayList<Procesador> procesadores;
    private LinkedList<Tarea> tareasCriticas;
    private LinkedList<Tarea> tareas;
    private int tiempoEjecucion;//cambiar nombre patram ostrar que es el de la refrigeraciones

    public Backtracking(ArrayList<Procesador> procesadores, LinkedList<Tarea> tareasCriticas, LinkedList<Tarea> tareasNoCriticas) {
        this.sol = new Solucion();
        this.procesadores = procesadores;
        this.tareasCriticas = tareasCriticas; // se puede pasar todo por el main
        this.tareas = tareasNoCriticas;//es necesario preguntar si viene vacio?
        this.tareas.addAll(this.tareasCriticas);
        this.tiempoEjecucion = 0;
    }

    public Solucion asignarTareasBack(int tiempoEjecucion){
        if(procesadores.size()*2<this.tareasCriticas.size() || procesadores.isEmpty() || tareas.isEmpty()){
            return null;
        }else{
            this.tiempoEjecucion = tiempoEjecucion;
            Solucion solParcial = new Solucion(this.procesadores);
            this.sol = new Solucion();//reiniciamos la variable
            asignarTareasBack(solParcial);
            return this.sol;
        }
    }

    private void asignarTareasBack(Solucion solParcial){
        solParcial.incrementarContadorEstados();
        if(tareas.isEmpty()){
            if(this.sol.isEmpty() || solParcial.getTiempoEjecucion()<sol.getTiempoEjecucion()){
                sol = solParcial.copy();// borrar la solucion anterior y copiar la nueva
            }
        }else{
            IteratorP<Procesador> it = new IteratorP<>(solParcial.getProcesadores().iterator());
            Tarea t = tareas.getFirst();
            while(it.hasNext()){
                Procesador p = it.next();
                if((!t.getCritica() || (t.getCritica() && p.getCantCriticas()<2)) &&
                        (p.isRefrigerado() || (!p.isRefrigerado() && p.getTiempoEjecucion()+t.getTiempo()<=this.tiempoEjecucion))){
                    if(this.sol.isEmpty() || (p.getTiempoEjecucion()+t.getTiempo() < this.sol.getTiempoEjecucion())){
                        p.addTarea(t);
                        tareas.removeFirst();
                        solParcial.actualizarTiempoEjecucion(p);
                        asignarTareasBack(solParcial);
                        solParcial.actualizarTiempoEjecucion(p,t);
                        tareas.addFirst(t);
                        p.deleteTarea(t);

                    }
                }
            }
        }
    }
}
