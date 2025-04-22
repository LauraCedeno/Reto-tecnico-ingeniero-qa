package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class PIMPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public PIMPage(WebDriver driver) {
        this.driver = driver;
        // Asegúrate de que el WebDriverWait esté bien inicializado
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    By menuPIM = By.linkText("PIM");
    By addBtn = By.xpath("//button[text()=' Add ']");
    By firstName = By.name("firstName");
    By lastName = By.name("lastName");
    By saveBtn = By.xpath("//button[@type='submit']");
    By employeeList = By.linkText("Employee List");
    By searchName = By.xpath("//input[@placeholder='Type for hints...']");
    By searchBtn = By.xpath("//button[@type='submit']");
    By resultName = By.xpath("//div[@role='rowgroup']//div[2]");

    public void agregarEmpleado(String nombre, String apellido, String email, String departamento) {
        // Esperar a que el menú PIM esté visible y hacer clic
        WebElement menu = wait.until(ExpectedConditions.elementToBeClickable(menuPIM));
        menu.click();

        // Esperar a que el botón de "Add" esté disponible y hacer clic
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(addBtn));
        addButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
        driver.findElement(firstName).sendKeys(nombre);
        driver.findElement(lastName).sendKeys(apellido);
        driver.findElement(saveBtn).click();
        wait.until(ExpectedConditions.urlContains("viewPersonalDetails"));
    }
}
