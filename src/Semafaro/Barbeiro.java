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

    /**
     * Corta o cabelo do cliente no tempo de 500 ms
     * @param c Cliente que está sendo atendido
     */
    public void cortarCabelo(Cliente c) {
        System.out.println(c + " está sendo atendido");
        try {
            Thread.sleep(1000);
            System.out.println(c + " foi embora");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que será executado o tempo todo pelo barbeiro
     * 
     * Caso a  fila de espera esteja vazia, decrementa o semaforo do cliente
     * o que fará com que a thread durma, já que o count estará em 0 no momento 
     * decremento
     * 
     * Caso contrário, pegará o proxmixo cliente da fila e incrementa o
     * semaforo do babeiro e então irá cortar o cabelo dele
     */
    public void controle() {
        if (barbearia.espera.isEmpty()) {
            if (!barbearia.isDormindo) {
                System.out.println("Barbeiro dormiu");
                barbearia.isDormindo = true;
            }
            barbearia.cliente.down();
        } else {
            atual = barbearia.espera.remove(0);
            barbearia.barbeiro.up();

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
