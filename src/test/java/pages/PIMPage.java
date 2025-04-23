package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;




import java.time.Duration;
import java.util.List;

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
    By menuContact = By.xpath("//a[normalize-space()='Contact Details']");
    By workEmail = By.xpath("//label[contains(text(),'Work Email')]/ancestor::div[@class='oxd-input-group oxd-input-field-bottom-space']//input");
    By menuJob = By.xpath("//a[normalize-space()='Job']");
    By subUnitDropdown = By.xpath("//label[text()='Sub Unit']/following::div[@class='oxd-select-wrapper'][1]");
    By opcionQA = By.xpath("//div[@role='option']/span[text()='Quality Assurance']");

    By employeeList = By.xpath("//a[normalize-space()='Employee List']");
    By searchName = By.xpath("//input[@placeholder='Type for hints...']");
    By searchBtn = By.xpath("//button[@type='submit']");
    By resultName = By.xpath("//div[@role='rowgroup']//div[2]");

    public void agregarEmpleado(String nombre, String apellido, String email) {
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

        WebElement infoContact = wait.until(ExpectedConditions.elementToBeClickable(menuContact));
        infoContact.click();

        // Hacer scroll 300 píxeles hacia abajo
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 300)");

        WebElement emailInput = wait.until(ExpectedConditions.elementToBeClickable(workEmail));
        emailInput.sendKeys(email);
    
        driver.findElement(saveBtn).click();
        WebElement job = wait.until(ExpectedConditions.elementToBeClickable(menuJob));
        job.click();
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(subUnitDropdown));
        dropdown.click();

        WebElement opcion = wait.until(ExpectedConditions.elementToBeClickable(opcionQA));
        opcion.click();

        driver.findElement(saveBtn).click();
        driver.findElement(employeeList).click();

    }

    public void buscarEmpleado(String nombre) {
        WebElement search = wait.until(ExpectedConditions.elementToBeClickable(searchName));
        search.sendKeys(nombre);
        try {
            Thread.sleep(1000); // Espera de 1 segundos después de buscar
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(searchBtn));
        btn.click();
        try {
            Thread.sleep(1000); // Espera de 1 segundos después de buscar
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // Hacer scroll 300 píxeles hacia abajo
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 300)");

        try {
            Thread.sleep(1000); // Espera de 1 segundos después de buscar
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
    }

    public boolean empleadoExisteEnResultados(String nombre, String apellido) {
        try {
            // Traer todas las filas de resultados
            List<WebElement> filas = driver.findElements(By.xpath("//div[@role='rowgroup']/div"));

            for (WebElement fila : filas) {
                String textoFila = fila.getText().toLowerCase();
                if (textoFila.contains(nombre.toLowerCase()) && textoFila.contains(apellido.toLowerCase())) {
                    return true; 
                }
            }
            return false; 
        } catch (Exception e) {
            System.out.println("Error buscando empleado en resultados: " + e.getMessage());
            return false;
        }
    }
 
    public void editarEmpleado(String nombre, String nuevoApellido) {
    
        // Esperar a que el botón de editar esté visible y hacer clic en el primero que aparezca
        By botonEditar = By.xpath("//div[@role='rowgroup']//div[contains(text(),'" + nombre + "')]/ancestor::div[@role='row']//i[@class='oxd-icon bi-pencil-fill']");
        WebElement editar = wait.until(ExpectedConditions.elementToBeClickable(botonEditar));
        editar.click();
        try {
            Thread.sleep(3000); // Espera de 2 segundos después de buscar
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    
        // Esperar a que el campo apellido esté disponible y cambiar el valor
        WebElement campoApellido = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("lastName")));
        campoApellido.click();
        campoApellido.sendKeys(Keys.chord(Keys.COMMAND, "a"));
        campoApellido.sendKeys(Keys.DELETE); // Borra
        campoApellido.sendKeys(nuevoApellido);

        try {
            Thread.sleep(4000); // Espera de 2 segundos después de buscar
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Hacer scroll 300 píxeles hacia abajo
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 300)");

        try {
            Thread.sleep(3000); // Espera de 2 segundos después de buscar
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    
        // Guardar cambios
        WebElement btnGuardar = driver.findElement(By.xpath("//button[@type='submit']"));
        btnGuardar.click();
    
        // Esperar a que redireccione y vuelva a la lista de empleados
        wait.until(ExpectedConditions.elementToBeClickable(employeeList)).click();
    }
    
    public void eliminarEmpleado(String nombreCompleto) {
        // Encuentra el botón de eliminar al lado del empleado
        By botonEliminar = By.xpath("//div[@role='table']//div[1]//div[1]//div[9]//div[1]//button[2]//i[1]");
        WebElement eliminar = wait.until(ExpectedConditions.elementToBeClickable(botonEliminar));
        eliminar.click();
        try {
            Thread.sleep(3000); // Espera de 2 segundos después de buscar
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // Confirmar eliminación
        By confirmarBtn = By.xpath("//button[normalize-space()='Yes, Delete']");
        WebElement btnConfirmar = wait.until(ExpectedConditions.elementToBeClickable(confirmarBtn));
        try {
            Thread.sleep(3000); // Espera de 2 segundos después de buscar
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        btnConfirmar.click();
    
        // Espera a que se borre y la lista se actualice
        wait.until(ExpectedConditions.invisibilityOfElementLocated(botonEliminar));
    }
    
    
    
    
    
}
