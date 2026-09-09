import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class LocalizadoresTest {

    private WebDriver driver;
    private String url;

    @BeforeEach
    void setUp() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        url = Paths.get(
                "C:\\Users\\drdie\\IdeaProjects\\tatf\\interfaz_web\\StartBootstrap\\index.html"
        ).toUri().toString();

        driver.get(url);
    }

    @AfterEach
    void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }

    // OBJETIVO 1

    @Test
    void objetivo1CSS() {

        WebElement elemento = driver.findElement(
                By.cssSelector(".masthead-heading")
        );

        assertThat(elemento.isDisplayed()).isTrue();
        assertThat(elemento.getText()).isEqualToIgnoringCase("Start Bootstrap");

        System.out.println("Objetivo 1 CSS: " + elemento.getText());
    }

    @Test
    void objetivo1XPath() {

        WebElement elemento = driver.findElement(
                By.xpath("//h1[normalize-space()='Start Bootstrap']")
        );

        assertThat(elemento.isDisplayed()).isTrue();
        assertThat(elemento.getText()).isEqualToIgnoringCase("Start Bootstrap");

        System.out.println("Objetivo 1 XPath: " + elemento.getText());
    }

    // OBJETIVO 2

    @Test
    void objetivo2CSS() {

        WebElement elemento = driver.findElement(
                By.cssSelector(".masthead-subheading")
        );

        assertThat(elemento.isDisplayed()).isTrue();

        System.out.println("Objetivo 2 CSS: " + elemento.getText());
    }

    @Test
    void objetivo2XPath() {

        WebElement elemento = driver.findElement(
                By.xpath(
                        "//p[normalize-space()='Graphic Artist - Web Designer - Illustrator']"
                )
        );

        assertThat(elemento.isDisplayed()).isTrue();

        System.out.println("Objetivo 2 XPath: " + elemento.getText());
    }

    // OBJETIVO 3

    @Test
    void objetivo3CSS() {

        WebElement elementoPadre = driver.findElement(
                By.cssSelector("#about .col-lg-4.ms-auto")
        );

        assertThat(elementoPadre.isDisplayed()).isTrue();

        System.out.println("Objetivo 3 CSS: " + elementoPadre.getText());
    }

    @Test
    void objetivo3XPath() {

        WebElement elementoPadre = driver.findElement(
                By.xpath(
                        "//p[contains(normalize-space(.)," +
                                "'Freelancer is a free bootstrap theme')]/parent::div"
                )
        );

        assertThat(elementoPadre.isDisplayed()).isTrue();

        System.out.println("Objetivo 3 XPath: " + elementoPadre.getText());
    }

    // OBJETIVO 4

    @Test
    void objetivo4CSS() {

        WebElement elementoPadre = driver.findElement(
                By.cssSelector("footer .col-lg-4.mb-5.mb-lg-0")
        );

        assertThat(elementoPadre.isDisplayed()).isTrue();

        System.out.println("Objetivo 4 CSS: " + elementoPadre.getText());
    }

    @Test
    void objetivo4XPath() {

        WebElement elementoPadre = driver.findElement(
                By.xpath(
                        "//p[contains(normalize-space(.)," +
                                "'2215 John Daniel Drive')]/parent::div"
                )
        );

        assertThat(elementoPadre.isDisplayed()).isTrue();

        System.out.println("Objetivo 4 XPath: " + elementoPadre.getText());
    }

    // OBJETIVO 5

    @Test
    void objetivo5CSS() {

        WebElement linkedin = driver.findElement(
                By.cssSelector("a:has(.fa-linkedin-in)")
        );

        assertThat(linkedin.isDisplayed()).isTrue();

        System.out.println("Objetivo 5 CSS: LinkedIn encontrado");
    }

    @Test
    void objetivo5XPath() {

        WebElement linkedin = driver.findElement(
                By.xpath(
                        "//a[.//*[contains(@class,'fa-linkedin-in')]]"
                )
        );

        assertThat(linkedin.isDisplayed()).isTrue();

        System.out.println("Objetivo 5 XPath: LinkedIn encontrado");
    }

    // OBJETIVO 6

    @Test
    void objetivo6CSS() {

        WebElement boton = driver.findElement(
                By.cssSelector("#about a.btn-outline-light")
        );

        assertThat(boton.isDisplayed()).isTrue();

        System.out.println("Objetivo 6 CSS: " + boton.getText());
    }

    @Test
    void objetivo6XPath() {

        WebElement boton = driver.findElement(
                By.xpath(
                        "//a[contains(normalize-space(.),'Free Download')]"
                )
        );

        assertThat(boton.isDisplayed()).isTrue();

        System.out.println("Objetivo 6 XPath: " + boton.getText());
    }

    // OBJETIVO 7

    @Test
    void objetivo7CSS() {

        WebElement imagen = driver.findElement(
                By.cssSelector(
                        "#itemsPortafolio img[src='assets/img/portfolio/cabin.png']"
                )
        );

        assertThat(imagen.isDisplayed()).isTrue();

        System.out.println("Objetivo 7 CSS: imagen encontrada");
    }

    @Test
    void objetivo7XPath() {

        WebElement imagen = driver.findElement(
                By.xpath(
                        "//*[@id='itemsPortafolio']//img[@src='assets/img/portfolio/cabin.png']"
                )
        );

        assertThat(imagen.isDisplayed()).isTrue();

        System.out.println("Objetivo 7 XPath: imagen encontrada");
    }
}