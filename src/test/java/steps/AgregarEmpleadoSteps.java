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
    private PIMPage pimPage = new PIMPage(driver);


    @Given("el usuario accede al login de OrangeHRM")
    public void el_usuario_accede_al_login() {
        homePage.abrirPagina();
        
    }

    @When("ingresa credenciales válidas con usuario {string} y contraseña {string}")
    public void ingresa_credenciales_validas(String username, String password) {
        homePage.login(username, password);
    }

    @When("navega a la sección PIM y agrega un nuevo empleado con nombre {string}, apellido {string}, email {string}")
    public void agrega_nuevo_empleado(String nombre, String apellido, String email) {
        pimPage.agregarEmpleado(nombre, apellido, email);
    }

    @When("busca al empleado por nombre {string}")
    public void buscar_empleado(String nombre) {
        pimPage.buscarEmpleado(nombre);
    }
    
    @Then("los resultados deben mostrar al empleado con nombre {string} y apellido {string}")
    public void verificar_resultado_de_busqueda(String nombre, String apellido) {
    Assert.assertTrue("El empleado no fue encontrado en los resultados.",
        pimPage.empleadoExisteEnResultados(nombre, apellido));
    }

    @Given("edita el empleado con nombre {string} cambiando el apellido a {string}")
    public void editar_empleado(String nombre, String nuevoApellido) {
        pimPage.editarEmpleado(nombre, nuevoApellido);

    }

    @When("elimina al empleado con nombre {string}")
    public void eliminar_empleado(String nombreCompleto) {
        pimPage.eliminarEmpleado(nombreCompleto);
    }



}


