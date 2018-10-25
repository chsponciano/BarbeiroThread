package Monitor;

/**
 *
 * @author Carlos Henrique Ponciano da Silva && Vinicius Luis da Silva
 */
public class Barbeiro extends Thread {

    private Barbearia barbearia;
    private boolean cortar;

    public Barbeiro(Barbearia barbearia) {
        this.barbearia = barbearia;
        this.cortar = true;
    }

    public void run() {
        try {
            while (cortar) {
                sleep(500);
                barbearia.verificarBarbeiro();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
