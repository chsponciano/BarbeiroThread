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

    public synchronized void up() {
        notify();
        count++;
    }
}
