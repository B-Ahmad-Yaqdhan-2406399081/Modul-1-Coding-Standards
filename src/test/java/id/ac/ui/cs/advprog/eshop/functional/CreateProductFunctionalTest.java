package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void pageInputsAvailable(FirefoxDriver driver) throws Exception {
        driver.get(baseUrl + "/product/create");

        WebElement productNameInput = driver.findElement(By.cssSelector("input[name=\"productName\"]"));
        assertNotNull(productNameInput);

        WebElement productQuantityInput = driver.findElement(By.cssSelector("input[name=\"productQuantity\"]"));
        assertNotNull(productQuantityInput);

        WebElement submitButton = driver.findElement(By.cssSelector("button[type=\"submit\"]"));
        assertNotNull(submitButton);
    }

    @Test
    void productCreateTest(FirefoxDriver driver) throws Exception {
        driver.get(baseUrl + "/product/create");

        WebElement productNameInput = driver.findElement(By.cssSelector("input[name=\"productName\"]"));
        productNameInput.clear();
        productNameInput.sendKeys("Sampo Cap Bambang");

        WebElement productQuantityInput = driver.findElement(By.cssSelector("input[name=\"productQuantity\"]"));
        productQuantityInput.clear();
        productQuantityInput.sendKeys("10");

        WebElement submitButton = driver.findElement(By.cssSelector("button[type=\"submit\"]"));
        submitButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(baseUrl + "/product/list"));

        List<WebElement> productRows = driver.findElements(By.cssSelector("tbody tr"));
        assertEquals(1, productRows.size());

        WebElement productRow = productRows.getFirst();
        List<WebElement> productDatas = productRow.findElements(By.cssSelector("td"));

        String productName = productDatas.get(0).getText();
        assertEquals("Sampo Cap Bambang", productName);

        String productQuantity = productDatas.get(1).getText();
        assertEquals("10", productQuantity);
    }
}
