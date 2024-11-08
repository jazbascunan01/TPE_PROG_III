package tpe;

import java.util.ArrayList;

public class Procesador {

    private String id;
    private String codigo;
    private boolean refrigerado;
    private int anioFuncionamiento;
    private ArrayList<Tarea> tareas;
    private int tiempoEjecucion;
    private int cantCriticas;

    public Procesador(String id, String codigo,boolean refrigerado, int anioFuncionamiento) {
        this.tareas = new ArrayList<>();
        this.refrigerado = refrigerado;
        this.id = id;
        this.codigo = codigo;
        this.anioFuncionamiento = anioFuncionamiento;
        this.tiempoEjecucion = 0;
        this.cantCriticas = 0;
    }

    public Procesador copiar() {
        Procesador copia = new Procesador(this.id, this.codigo, this.refrigerado, this.anioFuncionamiento);
        for (Tarea t : this.tareas) {
            copia.addTarea(t);//hacer add tarea
        }
        return copia;
    }

    public int size() {
        return this.tareas.size();
    }

    public int getTiempoEjecucion() {
        return tiempoEjecucion;
    }

    public void setTiempoEjecucion(int tiempoEjecucion) {
        this.tiempoEjecucion = tiempoEjecucion;
    }

    public void addTarea(Tarea t) {
        this.tareas.add(t);
        this.tiempoEjecucion = this.tiempoEjecucion + t.getTiempo();
        if (t.getCritica()) {
            this.cantCriticas++;
        }
    }

    public void deleteTarea(Tarea t) {
        this.tareas.remove(t);
        this.tiempoEjecucion = this.tiempoEjecucion - t.getTiempo();
        if (t.getCritica()) {
            this.cantCriticas--;
        }
    }

    public boolean containsTarea(Tarea t) {
        return this.tareas.contains(t);
    }

    public boolean isRefrigerado() {
        return refrigerado;
    }

    public int getCantCriticas() {
        return this.cantCriticas;
    }

    @Override
    public String toString() {
        StringBuilder texto = new StringBuilder();
    
        // Longitud total del marco
        int longitudLinea = 45;
        
        // Texto del procesador centrado
        String procesadorTexto = "Procesador: " + this.id;
        int espaciosLado = (longitudLinea - procesadorTexto.length() - 2) / 2; // -2 por los asteriscos laterales
    
        // Enmarcar con asteriscos y añadir el procesador
        texto.append("****************************************\n");
        texto.append("*").append(" ".repeat(longitudLinea - 2)).append("*\n"); // Línea vacía
        texto.append("*").append(" ".repeat(espaciosLado)).append(procesadorTexto)
             .append(" ".repeat(longitudLinea - espaciosLado - procesadorTexto.length() - 2)).append("*\n");
        texto.append("*").append(" ".repeat(longitudLinea - 2)).append("*\n"); // Línea vacía
        texto.append("****************************************\n");
    
        texto.append(" Tareas:\n");
    
        // Añadimos cada tarea relacionada
        for (Tarea t : this.tareas) {
            texto.append(t.toString());
        }
    
        return texto.toString();
    }
    

}
