package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void abrirPagina() {
        driver.get(url);
    }

    public void login(String username, String password) {
        By userLocator = By.name("username");
        By passwordLocator = By.name("password");
        By loginBtn = By.xpath("//button[@type='submit']");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(userLocator));
        driver.findElement(userLocator).sendKeys(username);

        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordLocator));
        driver.findElement(passwordLocator).sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
        driver.findElement(loginBtn).click();
    }
}
