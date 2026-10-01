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

public class Busquedagoogletest {

    private WebDriver driver;
    private WebDriverWait wait;

    private final String url = "https://www.google.com";
    private final String textoBusqueda = "Montevideo";

    @BeforeEach
    void setUp() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(20)
        );
    }

    @AfterEach
    void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void buscarEnGoogle() {

        driver.get(url);

        assertThat(driver.getTitle())
                .containsIgnoringCase("Google");

        WebElement buscador = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.name("q")
                )
        );

        buscador.sendKeys(textoBusqueda);

        buscador.sendKeys(Keys.ENTER);

        wait.until(
                ExpectedConditions.urlContains("search")
        );

        WebElement buscadorResultados = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.name("q")
                )
        );

        assertThat(buscadorResultados.getDomProperty("value"))
                .isEqualTo(textoBusqueda);

        System.out.println(
                "Búsqueda realizada correctamente: "
                        + textoBusqueda
        );
    }
}