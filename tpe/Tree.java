package tpe;
import java.util.LinkedList;

/* -- Árbol de busqueda binaria por prioridad (0-100) con lista de factoreo (tareas que cumplen con dicha prioridad) --*/

public class Tree {

    private TreeNode root;

    public Tree() {
        this.root = null;
    }


    /* -- AGREGAR UNA NUEVA TAREA EN LA LISTA DE FACTOREO DEL NODO CON KEY = PRIORIDAD --*/

    public void add(int value, Tarea task) {
        if(value >=0 && value <=100) {
            if (this.root == null) {
                this.root = new TreeNode(value, null, null);
                this.root.addElement(task);
            } else
                add(value, this.root, task);
        }
        else{
            //No se pueden agregar tareas con prioridades fuera del rango 0-100
        }
    }

    /*
     * h es la altura del árbol
     * O(h) porque en el peor de los casos el nuevo valor pasa a ser mi hoja más lejana.
     */

    private void add(int value, TreeNode actual, Tarea task) {
        if (value < actual.getKey()) {

            if (actual.getLeft() == null){
                TreeNode node = new TreeNode(value, null, null);
                node.addElement(task);
                actual.setLeft(node);
            }
            else
                add(value, actual.getLeft(), task);
        }

        else if (value > actual.getKey()) {

            if (actual.getRight() == null) {
                TreeNode node = new TreeNode(value, null, null);
                node.addElement(task);
                actual.setRight(node);
            } else
                add(value, actual.getRight(), task);
        }
        else{
            actual.addElement(task);
        }
    }



    /* -- BUSCA TODAS LAS TAREAS QUE SE ENCUENTRAN DENTRO DE UN RANGO DE PRIORIDADES --*/

    public LinkedList<Tarea> getElemBetween (int minor, int mayor){
        LinkedList<Tarea> list = new LinkedList<>();

        if (!this.isEmpty())
            getElemBetween(minor, mayor, list, this.root);

        return list;
    }

    /*
     * n es la cantidad de nodos del árbol.
     * O(n) porque en el peor de los casos quiero obtener los elementos del árbol completo
     */


    private void getElemBetween (int minor, int mayor, LinkedList<Tarea> list, TreeNode actual){
        if(actual != null){

            if(actual.getKey() == minor){
                list.addAll(actual.getList());
                getElemBetween(minor, mayor, list, actual.getRight());
            }

            else if(actual.getKey() == mayor){
                list.addAll(actual.getList());
                getElemBetween(minor, mayor, list, actual.getLeft());
            }

            else if(actual.getKey() < minor){
                getElemBetween(minor, mayor, list, actual.getRight());
            }

            else if(actual.getKey() > mayor){
                getElemBetween(minor, mayor, list, actual.getLeft());
            }

            else{
                list.addAll(actual.getList());
                getElemBetween(minor, mayor, list, actual.getLeft());
                getElemBetween(minor, mayor, list, actual.getRight());
            }
        }
    }

    public boolean isEmpty(){
        return this.root == null;
    }

    public void printInOrder() {
        printInOrder(this.root);
    }

    private void printInOrder(TreeNode node) {
        if (node == null)
            return;
        
        printInOrder(node.getLeft());
        System.out.print(node.getKey() + " ");
        printInOrder(node.getRight());
    }

}
