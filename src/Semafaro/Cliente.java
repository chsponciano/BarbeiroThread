package Semafaro;

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

    private void controle() {
        barbearia.mutex.down();

        if (barbearia.espera.size() < barbearia.LIMITE) {
            barbearia.espera.add(this);

            barbearia.cliente.up();
            barbearia.mutex.up();

            System.out.println(this + " entrou na barbearia e sentou");
        } else {
            barbearia.mutex.up();
            barbearia.setFoiEmboraSemAtendimento(this + "\n");
            System.out.println(this + " entrou na barbearia e foi embora");
        }
    }

    @Override
    public void run() {
        controle();
    }

}
