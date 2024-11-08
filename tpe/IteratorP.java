package tpe;

import java.util.Iterator;

public class IteratorP<Procesador> implements Iterator<Procesador>{
    private Iterator<Procesador> iteradorProcesador;

    public IteratorP(Iterator<Procesador> iteradorProcesador) {
        this.iteradorProcesador = iteradorProcesador;
    }

    @Override
    public Procesador next() {
        return iteradorProcesador.next();
    }

    @Override
    public boolean hasNext() {
        return iteradorProcesador.hasNext();
    }
}
