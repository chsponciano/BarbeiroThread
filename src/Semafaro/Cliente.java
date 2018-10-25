package Semafaro;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos Henrique Ponciano da Silva && Vinicius Luis da Silva
 */
public class Cliente extends Thread {

    private Barbearia barbearia;
    private String nome;
    private static int contador = 1;

    public Cliente(Barbearia barbearia) {
        this.barbearia = barbearia;
        this.nome = "Cliente " + (contador++);
    }

    @Override
    public String toString() {
        return this.nome;
    }

    /**
     * Adiciona esse cliente na fila do barbeiro se houver lugar e da um up no
     * semaforo de cliente
     *
     * caso nao houver lugar, simplemente marca como mais um cliente que foi
     * embora
     */
    private void controle() {
        if (barbearia.espera.size() < barbearia.LIMITE) {
            try {
                barbearia.espera.add(this);

                barbearia.cliente.up();

                System.out.println(this + " entrou na barbearia e sentou");
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            barbearia.setFoiEmboraSemAtendimento(this + "\n");
            System.out.println(this + " entrou na barbearia e foi embora");
        }
    }

    @Override
    public void run() {
        controle();
    }

}
