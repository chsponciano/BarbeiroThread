package Monitor;

/**
 *
 * @author Carlos Henrique Ponciano da Silva && Vinicius Luis da Silva
 */
import Util.IBarbearia;
import java.util.ArrayDeque;

public class Barbearia implements IBarbearia {

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

    /**
     * Metodo usado pelo barbeira para verificar se tem alguem na fila,
     * caso nao tenha ele faz a thread do barbeiro dormir, caso contrario
     * tira um cliente da fila, e notifica todas as threads de clientes na
     * fila de espera
     */
    public synchronized void verificarBarbeiro() {
        try {
            if (espera.isEmpty()) {
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

    /**
     * Se a fila nao estiver cheia, coloca o cliente na fila
     * Caso o barbeiro estiver dormmindo, acorda ele usando o notify()
     * já que a thread do barbeiro é a unica que possivelmente estara
     * dormindo. E finalmente para a thread com o wait() para esperar 
     * ser chamando pelo barbeiro
     * @param c Cliente a ser adicionado na fila
     */
    public synchronized void verificarEspera(Cliente c) {
        try {
            if (espera.size() < LIMITE) {
                espera.add(c); //Memoria compartilhado
                System.out.println(c + " entrou na barbearia e sentou");

                if (dormindo) {
                    System.out.println("Barbeiro acordou");
                    notify();   //Acordar o barbeiro
                    dormindo = false;
                }
                
                wait();
            } else {
                System.out.println(c + " entrou na barbearia e foi embora");
                this.foiEmboraSemAtendimento += c + "\n";
            }
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
