package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NopCommerceTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testNopCommerce() throws InterruptedException {
        // 1. E-ticaret sitesini ziyaret etme
        driver.get("https://demo.nopcommerce.com/");
        System.out.println("Site ziyaret edildi.");

        // 2. Kullanıcı giriş işlemi
        wait.until(ExpectedConditions.elementToBeClickable(By.className("ico-login"))).click();
        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Email")));
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Password")));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Log in')]")));

        email.sendKeys("kadriye.sir@gmail.com");
        password.sendKeys("123456");
        loginButton.click();
        System.out.println("Kullanıcı girişi yapıldı.");

        // 3. "Cep telefonu" araması yapma
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("small-searchterms")));
        searchBox.sendKeys("phone");
        searchBox.submit();
        System.out.println("Ürün araması yapıldı.");

        // 4. Filtrelenen sonuçlarda en alt satırdan rastgele bir ürün seçme
        List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("product-item")));
        Random random = new Random();
        WebElement randomProduct = products.get(random.nextInt(products.size()));
        randomProduct.findElement(By.className("product-title")).click();
        System.out.println("Rastgele ürün seçildi.");

        // 5. Ürünü sepete ekleme
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'add-to-cart-button')]")));
        addToCartButton.click();
        System.out.println("Ürün sepete eklendi.");

        // Pop-up kapanana kadar bekle
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("bar-notification")));

        // 6. Ürünün sepete doğru eklendiğini kontrol etme
        driver.findElement(By.className("ico-cart")).click(); // Sepete git
        WebElement cart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cart")));

        // Sepet içeriğini kontrol et
        List<WebElement> cartItems = cart.findElements(By.className("product-name"));
        boolean itemAdded = cartItems.stream().anyMatch(item -> item.getText().toLowerCase().contains("htc one m8 android l 5.0 lollipop"));
        if (!itemAdded) {
            System.out.println("Sepetteki ürünler:");
            for (WebElement item : cartItems) {
                System.out.println(item.getText());
            }
        }
        assertTrue(itemAdded, "Ürün sepete başarıyla eklenmedi.");
        System.out.println("Ürün sepete başarıyla eklendi.");
    }

}
