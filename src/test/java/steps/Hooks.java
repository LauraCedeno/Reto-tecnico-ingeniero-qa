package steps;

import io.cucumber.java.AfterStep;

public class Hooks {

    @AfterStep
    public void pausarPaso() {
        try {
            Thread.sleep(500); // Espera de 1.5 segundos después de cada paso
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
