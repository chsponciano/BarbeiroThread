
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlso Henrique Ponciano da Silva && Vinicius Luis da Slva
 */
public class executor {

    public static void main(String[] args) {
        Barbearia barbearia = new Barbearia();

        new Barbeiro(barbearia).start();

        while (true) {
            new Cliente(barbearia).start();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(executor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
