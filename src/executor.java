
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Carlos
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
