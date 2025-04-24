package steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.AttachmentsPage;
import utils.DriverManager;

public class AttachmentsSteps {
    

    private WebDriver driver;
    private AttachmentsPage addAttachments;

    public AttachmentsSteps() {
        this.driver = DriverManager.getDriver();
        this.addAttachments = new AttachmentsPage(driver);
    }

    @Given("el usuario sube un archivo {string}")
    public void subir_archivo(String nombreArchivo) {
        addAttachments.navegarAContactDetails(); 
        addAttachments.subirArchivo(nombreArchivo);
    }

}
