package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;



public class AttachmentsPage {

    WebDriver driver;
    WebDriverWait wait; 

    By myInfoTab = By.xpath("//span[text()='My Info']/parent::a");
    By contactDetails = By.xpath("//a[normalize-space()='Contact Details']");
    By uploadBtn = By.xpath("//button[normalize-space()='Add']");
    By fileInput = By.xpath("//input[@type='file']");
    By comment = By.xpath("//textarea[@placeholder='Type comment here']");
    By saveAttachment = By.xpath("//div[@class='orangehrm-attachment']//button[@type='submit'][normalize-space()='Save']");

    public AttachmentsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
    }


    public void navegarAContactDetails() {
        wait.until(ExpectedConditions.elementToBeClickable(myInfoTab)).click();
        wait.until(ExpectedConditions.elementToBeClickable(contactDetails)).click();
    
        // Esperar a que el contenedor esté presente
        WebElement contenedorScroll = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("oxd-layout-context")));
        
        // Hacer scroll dentro del contenedor
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", contenedorScroll);
    }
    
    
    public void subirArchivo(String nombreArchivo) {
        WebElement addBtn = wait.until(ExpectedConditions.presenceOfElementLocated(uploadBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addBtn);
    
        File file = new File("src/test/resources/files/" + nombreArchivo);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")))
            .sendKeys(file.getAbsolutePath());
        try {
            Thread.sleep(2000); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(comment)).sendKeys("Subiendo archivo de prueba");
    
        WebElement saveBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(saveAttachment));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveBtn);
        try {
            Thread.sleep(2000); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//*[contains(text(),'" + nombreArchivo + "')]")
        ));
        try {
            Thread.sleep(2000); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    
        // Hacer scroll hasta el área donde están los documentos
        WebElement contenedorArchivos = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='orangehrm-attachment']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", contenedorArchivos);
    }
    

    public boolean archivoExiste(String nombreArchivo) {
        return driver.findElement(By.xpath("//a[text()='" + nombreArchivo + "']")).isDisplayed();
    }    

    
}
