package tpe;

import java.util.Iterator;

public class IteratorP<Procesador> implements Iterator<Procesador>{
    private Iterator<Procesador> iterador;

    public IteratorP(Iterator<Procesador> iterador) {
        this.iterador = iterador;
    }

    @Override
    public Procesador next() {
        return iterador.next();
    }

    @Override
    public boolean hasNext() {
        return iterador.hasNext();
    }
}
