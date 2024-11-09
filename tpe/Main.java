package tpe;

public class Main {

    public static void main(String args[]) {
        Servicios servicios = new Servicios("tpe\\datasets\\Procesadores.csv", "tpe\\datasets\\Tareas.csv");
        
        

        /*************************************/
        /*                                   */
        /*             PRUEBAS               */
        /*                                   */
        /*************************************/
        System.out.println("Tarea 1 DE PRUEBA");
        System.out.println(servicios.servicio1("T1").toString());
        System.out.println();
        System.out.println();
        System.out.println("CRITICAS");
        System.out.println(servicios.servicio2(true));
        System.out.println();
        System.out.println();
        System.out.println("PRIORIDAD ENTRE 30 Y 55");
        System.out.print(servicios.servicio3(30,55).toString());
        System.out.println();
        System.out.println();
        System.out.println("ASIGNAR TAREA 70");
        System.out.println("BACKTRACKING");
        Solucion solucion = servicios.asignarTareasBack(70);
        System.out.println(solucion); 
        System.out.println();
        System.out.println();
        System.out.println("GREEDY");
        Solucion solucionGreedy = servicios.asignarTareasGreedy(70);
        System.out.println(solucionGreedy);
    }
}
