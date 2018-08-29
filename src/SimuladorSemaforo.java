import javax.swing.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class SimuladorSemaforo {
    public static void main(String[] args) {
        int n = Integer.parseInt(JOptionPane.showInputDialog("Insira o número de vezes a repetir: "));

        Semaphore semaforoVermelho = new Semaphore(1);
        Semaphore semaforoAmarelo = new Semaphore(1);
        Semaphore semaforoVerde = new Semaphore(1);

        try {
            semaforoVermelho.release();
            semaforoVerde.acquire();
            semaforoAmarelo.acquire();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        ThreadSemaforo vermelho = new ThreadSemaforo("Vermelho", getTempoEspera(), n, semaforoVermelho, semaforoAmarelo);
        ThreadSemaforo amarelo = new ThreadSemaforo("Yellow", getTempoEspera(), n, semaforoAmarelo, semaforoVerde);
        ThreadSemaforo verde = new ThreadSemaforo("Verde", getTempoEspera(), n, semaforoVerde, semaforoVermelho);

        vermelho.start();
        amarelo.start();
        verde.start();
    }

    public static int getTempoEspera() {
        return ThreadLocalRandom.current().nextInt(1, 5 + 1);
    }
}