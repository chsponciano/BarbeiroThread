
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
                sleep(5000);
                barbearia.verificarBarbeiro();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
