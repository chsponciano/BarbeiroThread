/**
 *
 * @author Carlso Henrique Ponciano da Silva && Vinicius Luis da Silva
 */

import java.util.concurrent.LinkedBlockingQueue;

public class Barbearia {

    private final int LIMITE; // Limite das cadeiras
    
    //Fila sincronizada, sendo um memoria compartilhada
    //Usada também para aplicar a regra de monitoramento
    private LinkedBlockingQueue<Cliente> espera; 
    
    private boolean dormindo; //Atributo de controle do Barbeiro
    private Cliente atual; // Cliente atual sendo modificado

    //Inicializado atributos
    {
        LIMITE = 6;
        espera = new LinkedBlockingQueue<>();
        dormindo = true;
        atual = null;
    }

    //Mecanismo de controle de notificação do barbeiro usando Semafaro;
    public synchronized void verificarBarbeiro() {
        try {
            while (espera.isEmpty()) {
                System.out.println("Barbeiro está dormindo");
                wait();
            }

            if (!espera.isEmpty()) {
                atual = espera.poll(); //Monitor compartilhado
                System.out.println(atual + " está sendo atendido");
                Thread.sleep(500);
                System.out.println(atual + " foi embora");
            }

            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    
    //Mecanismo de controle de inserção de clientes usando Semafaro;
    public synchronized void verificarEspera(Cliente c) {
        try {
            if (espera.size() < LIMITE) {
                espera.add(c); //Monitor compartilhado
                System.out.println("Quantidade fila: " + getQtdEspera());

                System.out.println(c + " entrou na barbearia e sentou");
            } else {
                System.out.println(c + " entrou na barbearia e foi embora");
            }

            while (espera.size() < LIMITE) {
                if (dormindo) {
                    System.out.println("Barbeiro acordou");
                    notify();
                    dormindo = false;
                }
                wait();
            }
            notifyAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isDormindo() {
        return dormindo;
    }

    public int getQtdEspera() {
        return this.espera.size();
    }
}
