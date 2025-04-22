package steps;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.PIMPage;
import utils.DriverManager;

public class AgregarEmpleadoSteps {
    
    private WebDriver driver = DriverManager.getDriver();
    private HomePage homePage = new HomePage(driver);
    private PIMPage empleado = new PIMPage(driver);

    @Given("el usuario accede al login de OrangeHRM")
    public void el_usuario_accede_al_login() {
        homePage.abrirPagina();
        
    }

    @When("ingresa credenciales válidas con usuario {string} y contraseña {string}")
    public void ingresa_credenciales_validas(String username, String password) {
        homePage.login(username, password);
    }

    @When("navega a la sección PIM y agrega un nuevo empleado con nombre {string}, apellido {string}, email {string} y departamento {string}")
    public void agrega_nuevo_empleado(String nombre, String apellido, String email, String departamento) {
        empleado.agregarEmpleado(nombre, apellido, email, departamento);
    }


}
