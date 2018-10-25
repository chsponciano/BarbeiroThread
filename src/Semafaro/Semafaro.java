package Semafaro;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos Henrique Ponciano da Silva && Vinicius Luis da Silva
 */
public class Semafaro {

    private int count;

    public Semafaro(int count) {
        this.count = count;
    }

    /**
     * Decrementa o contador se ele for maior ou igual a 0, caso contrário para
     * a thread e espera o método up() ser chamado
     */
    public synchronized void down() {
        try {
            if (count <= 0) {
                wait();
            }
            count--;
        } catch (InterruptedException ex) {
            Logger.getLogger(Semafaro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Incremente o contador e notifica qualquer thread que possa ter sido parada
     * no descrementador
     */
    public synchronized void up() {
        count++;
        notify();
    }
}
