/**
 *
 * @author Carlso Henrique Ponciano da Silva && Vinicius Luis da Silva
 */

public class Cliente extends Thread {

    private Barbearia barbearia;
    private static int contador = 1;
    private String nome;

    public Cliente(Barbearia barbearia) {
        this.barbearia = barbearia;
        this.nome = "Cliente " + (contador++);
    }

    public void run() {
        try {
            barbearia.verificarEspera(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return this.nome;
    }

}
