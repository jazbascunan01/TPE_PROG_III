package tpe;

public class Main {

    public static void main(String args[]) {
        Servicios servicios = new Servicios("tpe\\datasets\\Procesadores.csv", "tpe\\datasets\\Tareas.csv");
        /* System.out.println("Tarea 1 DE PRUEBA");
        System.out.println(servicios.servicio1("T1").toString());
        System.out.println("CRITICAS");
        System.out.println(servicios.servicio2(true));
        System.out.println("PRIORIDAD ENTRE 35 Y 60");
        System.out.print(servicios.servicio3(35,60).toString());
        System.out.println("ASIGNAR TAREA 70"); */
        Solucion solucion = servicios.asignarTareasBack(80);
        System.out.println(solucion); 
    }
}
