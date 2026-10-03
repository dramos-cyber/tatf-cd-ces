import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class TestForm {

    //WebDriver para configurar y manipular pagina
    private static WebDriver driver;
    private static int sleep;

    //Valores para utilizar en test
    private static String sitioWeb;
    private static String nombre;
    private static String contrasenia;
    private static String comentario;

    //Valores para utilzar en asserts
    private static String formInputResult;
    private static String formTitleResult;
    private static String formMessageResult;

    @BeforeAll
    static void beforeAll() {
        //Configuracion de web driver
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("--ignore-certificate-errors");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        sleep = 3;

        //Valores para test
        sitioWeb = "https://www.selenium.dev/selenium/web/web-form.html";
        nombre = "Leonardo";
        contrasenia = "alskdjf1234!!$";
        comentario = "Esto es un comentario.";

        //Valores para asserts
        formInputResult = "Readonly input";
        formTitleResult = "Form submitted";
        formMessageResult = "Received!";
    }

    @AfterAll
    static void afterAll() {
        sleep(sleep);
        driver.quit();
    }

    @Test
    void testWebForm() {
        driver.get(sitioWeb);

        driver.findElement(By.id("my-text-id")).sendKeys(nombre);
        driver.findElement(By.name("my-password")).sendKeys(contrasenia);
        driver.findElement(By.name("my-textarea")).sendKeys(comentario);

        String myReadonly =  driver.findElement(By.name("my-readonly")).getAttribute("value");
        Assertions.assertThat(myReadonly).as("Se obtiene un valor inesperado.").isEqualTo(formInputResult);

        WebElement mySelect = driver.findElement(By.name("my-select"));
        Select dropdown = new Select(mySelect);
        dropdown.selectByVisibleText("Two");

        driver.findElement(By.name("my-datalist")).sendKeys("Los Angeles");

        driver.findElement(By.id("my-check-2")).click();
        driver.findElement(By.id("my-radio-2")).click();

        driver.findElement(By.name("my-date")).click();
        driver.findElement(By.xpath("//td[@class='day' and text()='26']")).click();
        driver.findElement(By.name("my-date")).sendKeys(Keys.TAB);

        WebElement range = driver.findElement(By.name("my-range"));
        Actions actions = new Actions(driver);
        actions.clickAndHold(range).moveByOffset(150,0).release().perform();

        driver.findElement(By.cssSelector(".btn")).click();

        String formSubmit = driver.findElement(By.cssSelector(".display-6")).getText();
        String received = driver.findElement(By.id("message")).getText();
        Assertions.assertThat(formSubmit).as("Título no es el esperado").isEqualTo(formTitleResult);
        Assertions.assertThat(received).as("Texto no es el esperado").isEqualTo(formMessageResult);

    }

    private static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
