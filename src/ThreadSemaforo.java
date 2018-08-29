import java.util.concurrent.Semaphore;

public class ThreadSemaforo extends Thread {
    private String cor;
    private int tempo;
    private Semaphore semaforo;
    private Semaphore semaforoProximo;
    public int n;

    public ThreadSemaforo(String cor, int tempo, int nVezes, Semaphore semaforo, Semaphore semaforoProximo) {
        this.cor = cor;
        this.tempo = tempo;
        this.n = nVezes;
        this.semaforo = semaforo;
        this.semaforoProximo = semaforoProximo;
    }

    @Override
    public void run() {
        while (n > 0) {
            if (semaforo.tryAcquire()) {
                try {
                    semaforo.acquire();

                    System.out.println(this.cor + " - tempo: " + tempo + ", N = " + Integer.valueOf(n));
                    n -= 1;
                    Thread.sleep(this.tempo * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    semaforoProximo.release(2);
                }
            }
        }
    }
}