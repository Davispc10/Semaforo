import java.util.concurrent.ThreadLocalRandom;

public enum CorSemaforo {
    VERDE, AMARELO, VERMELHO;

    CorSemaforo() { }

    public int getTempoEspera() {
        return ThreadLocalRandom.current().nextInt(1, 10 + 1);
    }
}