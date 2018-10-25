package Semafaro;

/**
 *
 * @author Carlos Henrique Ponciano da Silva && Vinicius Luis da Silva
 */
public class Barbeiro extends Thread {
    private Barbearia barbearia;
    private Cliente atual;

    public Barbeiro(Barbearia barbearia) {
        this.barbearia = barbearia;
    }   

    public void cortarCabelo(Cliente c) {
        System.out.println(c + " está sendo atendido");
        try {
            Thread.sleep(500);
            barbearia.espera.remove(c);

            System.out.println(c + " foi embora");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void controle() {
        if (barbearia.espera.isEmpty()) {
            if (!barbearia.isDormindo) {
                System.out.println("Barbeiro dormiu");
                barbearia.isDormindo = true;
            }
            barbearia.cliente.down();
        } else {
            barbearia.mutex.down();

            atual = barbearia.espera.remove(0);
            barbearia.barbeiro.up();
            barbearia.mutex.up();

            if (barbearia.isDormindo) {
                System.out.println("Barbeiro acordou");
                barbearia.isDormindo = false;
            }

            cortarCabelo(atual);
        }
    }

    @Override
    public void run() {
        while (true) {
            controle();
        }
    }
}
