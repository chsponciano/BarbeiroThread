package Monitor;

/**
 *
 * @author Carlos Henrique Ponciano da Silva && Vinicius Luis da Silva
 */
import Util.IBarbearia;
import java.util.ArrayDeque;

public class Barbearia implements IBarbearia{

    private final int LIMITE; // Limite das cadeiras
    
    //Memoria compartilhada
    private ArrayDeque<Cliente> espera; 
    
    private boolean dormindo; //Atributo de controle do Barbeiro
    private Cliente atual; // Cliente atual sendo modificado

    //Saida de clientes que foram embora sem atendimento
    private String foiEmboraSemAtendimento = "";
    
    //Inicializado atributos
    {
        LIMITE = 6;
        espera = new ArrayDeque<>();
        dormindo = true;
        atual = null;
    }

    public String getFoiEmboraSemAtendimento() {
        return foiEmboraSemAtendimento;
    }  
    
    //Metodo elaboraborado com Monitor para controle das funcoes do barbeiro e controle da fila
    public synchronized void verificarBarbeiro() {
        try {
            while (espera.isEmpty()) {
                System.out.println("Barbeiro está dormindo");
                dormindo = true;
                wait();
            }

            if (!espera.isEmpty()) {
                atual = espera.poll(); //Memoria compartilhado
                System.out.println(atual + " está sendo atendido");
                Thread.sleep(100);
                System.out.println(atual + " foi embora");
            }

            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    
    //Metodo elaboraborado com Monitor para verificar e controle a entrada de clientes na fila
    public synchronized void verificarEspera(Cliente c) {
        try {
            if (espera.size() < LIMITE) {
                espera.add(c); //Memoria compartilhado
                System.out.println("Quantidade fila: " + getQtdEspera());

                System.out.println(c + " entrou na barbearia e sentou");
            } else {
                System.out.println(c + " entrou na barbearia e foi embora");
                this.foiEmboraSemAtendimento += c + "\n";
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
