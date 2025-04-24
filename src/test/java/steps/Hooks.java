package steps;

import io.cucumber.java.AfterStep;
import io.cucumber.java.AfterAll;
import utils.DriverManager;

public class Hooks {

    @AfterStep
    public void pausarPaso() {
        try {
            Thread.sleep(500); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @AfterAll
    public static void cerrarDriver() {
        DriverManager.quitDriver(); // Solo se ejecuta una vez al final de todos los escenarios
    }
}
