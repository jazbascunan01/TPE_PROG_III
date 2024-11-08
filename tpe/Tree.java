package tpe;
import java.util.LinkedList;

public class Tree {

    private TreeNode raiz;

    public Tree() {
        this.raiz = null;
    }

    public void add(int valor, Tarea tarea) {
        if(valor >=0 && valor <=100) {
            if (this.raiz == null) {
                this.raiz = new TreeNode(valor, null, null);
                this.raiz.addElement(tarea);
            } else
                add(valor, this.raiz, tarea);
        }
    }

    private void add(int valor, TreeNode actual, Tarea task) {
        if (valor < actual.getKey()) {

            if (actual.getLeft() == null){
                TreeNode node = new TreeNode(valor, null, null);
                node.addElement(task);
                actual.setLeft(node);
            }
            else
                add(valor, actual.getLeft(), task);
        }

        else if (valor > actual.getKey()) {

            if (actual.getRight() == null) {
                TreeNode node = new TreeNode(valor, null, null);
                node.addElement(task);
                actual.setRight(node);
            } else
                add(valor, actual.getRight(), task);
        }
        else{
            actual.addElement(task);
        }
    }

    public LinkedList<Tarea> getElemBetween (int minor, int mayor){
        LinkedList<Tarea> list = new LinkedList<>();

        if (!this.isEmpty())
            getElemBetween(minor, mayor, list, this.raiz);

        return list;
    }

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
        return this.raiz == null;
    }
}
