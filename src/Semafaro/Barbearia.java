package Semafaro;

import Util.IBarbearia;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos Henrique Ponciano da Silva && Vinicius Luis da Silva
 */
public class Barbearia implements IBarbearia {

    public Semafaro cliente = new Semafaro(0);
    public Semafaro barbeiro = new Semafaro(0);
    public Semafaro mutex = new Semafaro(1);
    public List<Cliente> espera = new ArrayList<>();
    public final int LIMITE = 6;
    public boolean isDormindo = true;

    //Saida de clientes que foram embora sem atendimento
    private String foiEmboraSemAtendimento = "";

    public void setFoiEmboraSemAtendimento(String foiEmboraSemAtendimento) {
        this.foiEmboraSemAtendimento += foiEmboraSemAtendimento;
    }

    public String getFoiEmboraSemAtendimento() {
        return foiEmboraSemAtendimento;
    }

    public int getQtdEspera() {
        return this.espera.size();
    }

    public boolean isDormindo() {
        return isDormindo;
    }
}
