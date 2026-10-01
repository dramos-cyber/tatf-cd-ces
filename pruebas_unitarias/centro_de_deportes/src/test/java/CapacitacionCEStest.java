import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class CapacitacionCEStest {

    private WebDriver driver;
    private WebDriverWait wait;

    private final String url = "https://capacitacion.ces.com.uy";

    private final String usuario = System.getenv("CES_USUARIO");
    private final String password = System.getenv("CES_PASSWORD");

    private final String nombreCurso =
            "Taller de Automatización del Testing Funcional";

    private final String textoForo = "Bienvenida";

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
    void buscarForoBienvenida() {

        driver.get("https://capacitacion.ces.com.uy/login/index.php");

        WebElement campoUsuario = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("username")
                )
        );

        WebElement campoPassword = driver.findElement(
                By.id("password")
        );

        WebElement botonAcceder = driver.findElement(
                By.id("loginbtn")
        );

        campoUsuario.clear();
        campoUsuario.sendKeys(usuario);

        campoPassword.clear();
        campoPassword.sendKeys(password);

        botonAcceder.click();

        wait.until(
                ExpectedConditions.not(
                        ExpectedConditions.urlContains("/login/index.php")
                )
        );

        assertThat(driver.getCurrentUrl())
                .doesNotContain("/login/index.php");

        System.out.println("Login realizado correctamente");
        System.out.println("URL actual: " + driver.getCurrentUrl());

        WebElement curso = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath(
                                "//a[contains(normalize-space(.)," +
                                        "'TALLER DE AUTOMATIZACIÓN DEL TESTING FUNCIONAL')]"
                        )
                )
        );

        assertThat(curso.isDisplayed()).isTrue();

        System.out.println("Curso encontrado: " + curso.getText());

        curso.click();

        wait.until(
                ExpectedConditions.urlContains("/course/view.php")
        );

        assertThat(driver.getCurrentUrl())
                .contains("/course/view.php");

        assertThat(driver.getPageSource())
                .containsIgnoringCase(nombreCurso);

        System.out.println("Ingreso al curso realizado correctamente");
        System.out.println("URL del curso: " + driver.getCurrentUrl());

        WebElement enlaceForos = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.linkText("Foros")
                )
        );

        assertThat(enlaceForos.isDisplayed()).isTrue();

        System.out.println(
                "Enlace Foros encontrado correctamente"
        );

        enlaceForos.click();

        wait.until(
                ExpectedConditions.urlContains("/mod/forum/")
        );

        assertThat(driver.getCurrentUrl())
                .contains("/mod/forum/");

        System.out.println(
                "Ingreso a Foros realizado correctamente"
        );

        System.out.println(
                "URL actual: " + driver.getCurrentUrl()
        );

        WebElement buscadorForos = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(
                                "input[placeholder='Buscar en los foros']"
                        )
                )
        );

        assertThat(buscadorForos.isDisplayed()).isTrue();

        System.out.println(
                "Buscador de foros encontrado correctamente"
        );

        buscadorForos.clear();
        buscadorForos.sendKeys(textoForo);

        buscadorForos.sendKeys(Keys.ENTER);

        System.out.println(
                "Búsqueda realizada: " + textoForo
        );

        WebElement resultadoBusqueda = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("span.highlight")
                )
        );

        assertThat(resultadoBusqueda.getText())
                .containsIgnoringCase(textoForo);

        System.out.println(
                "Resultado encontrado correctamente: "
                        + resultadoBusqueda.getText()
        );
    }
}
