package tpe;

public class Tarea {

    private String id;
    private String nombre;
    private Integer tiempo;
    private Boolean critica;
    private Integer prioridad;

    public Tarea(String id, String nombre, Integer tiempo, Boolean critica, Integer prioridad){
        this.id = id;
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.critica = critica;
        this.prioridad = prioridad;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTiempo() {
        return tiempo;
    }

    public void setTiempo(Integer tiempo) {
        this.tiempo = tiempo;
    }

    public Boolean getCritica() {
        return critica;
    }

    public void setCritica(Boolean critica) {
        this.critica = critica;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

@Override
public String toString() {
    // Usamos StringBuilder para mejorar la legibilidad y el rendimiento
    StringBuilder texto = new StringBuilder();

    // Agregamos un salto de línea antes del encabezado para separarlo del resto
    texto.append("\n");

    // Añadimos el encabezado de la tarea con formato
    texto.append("========================================\n");
    texto.append("            Tarea Detalles             \n");
    texto.append("========================================\n");

    // Agregamos la información de la tarea de forma clara
    texto.append("ID: ").append(this.id).append("\n");
    texto.append("Nombre: ").append(this.nombre).append("\n");
    texto.append("Tiempo: ").append(this.tiempo).append(" unidades de tiempo\n");
    texto.append("Crítica: ").append(this.critica ? "Sí" : "No").append("\n");
    texto.append("Prioridad: ").append(this.prioridad).append("\n");

    // Cierra el formato con una línea divisoria
    texto.append("----------------------------------------\n");

    // Retorna el texto formateado
    return texto.toString();
}

    
}