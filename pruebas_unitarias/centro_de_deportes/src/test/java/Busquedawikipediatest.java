import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class Busquedawikipediatest {

    private WebDriver driver;
    private WebDriverWait wait;

    private final String url = "https://es.wikipedia.org";
    private final String articulo = "Hola mundo";

    @BeforeEach
    void setUp() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );
    }

    @AfterEach
    void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void buscarArticuloHolaMundo() {

        driver.get(url);

        assertThat(driver.getTitle())
                .containsIgnoringCase("Wikipedia");

        WebElement buscador = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.name("search")
                )
        );

        buscador.sendKeys(articulo);

        buscador.sendKeys(Keys.ENTER);

        wait.until(
                ExpectedConditions.textToBe(
                        By.id("firstHeading"),
                        articulo
                )
        );

        WebElement titulo = driver.findElement(
                By.id("firstHeading")
        );

        assertThat(titulo.getText().trim())
                .isEqualTo(articulo);

        System.out.println(
                "Artículo encontrado correctamente: "
                        + titulo.getText()
        );
    }
}