package org.example;
import com.codeborne.selenide.impl.Photographer;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StepDefinitions {
    WebDriver driver;

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://example.com/login"); // Replace with the actual login page URL
    }

    @When("the user enters username {string} and password {string}")
    public void theUserEntersUsernameAndPassword(String username, String password) {
        driver.findElement(By.id("username")).sendKeys(username); // Replace with actual element id
        driver.findElement(By.id("password")).sendKeys(password); // Replace with actual element id
    }

    @And("the user clicks on the login button")
    public void theUserClicksOnTheLoginButton() {
        driver.findElement(By.id("loginButton")).click(); // Replace with actual element id
    }

    @Then("the user should be logged in successfully")
    public void theUserShouldBeLoggedInSuccessfully() {
        // Verify successful login
        assertTrue(driver.findElement(By.id("logoutButton")).isDisplayed()); // Replace with actual verification
    }

    @When("the user searches for {string}")
    public void theUserSearchesFor(String query) {
        WebElement searchBox = driver.findElement(By.id("searchBox")); // Replace with actual element id
        searchBox.sendKeys(query);
        searchBox.submit();
    }

    @And("the user filters the results by price range {int} to {int} TL")
    public void theUserFiltersTheResultsByPriceRangeToTL(int minPrice, int maxPrice) {
        // Implement price range filter
    }

    @And("the user selects a random product from the filtered results")
    public void theUserSelectsARandomProductFromTheFilteredResults() {
        // Implement product selection
    }

    @And("the user adds the product from the lowest-rated seller to the cart")
    public void theUserAddsTheProductFromTheLowestRatedSellerToTheCart() {
        // Implement adding product from the lowest-rated seller to the cart
    }

    @Then("the product should be added to the cart successfully")
    public void theProductShouldBeAddedToTheCartSuccessfully() {
        // Verify product added to cart
    }
}
