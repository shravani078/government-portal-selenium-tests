package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.URL;

public class FormSubmissionTest {

    private WebDriver driver;

    @BeforeMethod
public void setUp() {
    System.out.println("STEP 1: Starting Chrome setup");

    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless=new");
    options.addArguments("--window-size=1365,900");
    options.addArguments("--disable-gpu");

    driver = new ChromeDriver(options);
    System.out.println("STEP 2: Chrome started");

    URL formUrl = getClass().getResource("/form.html");
    Assert.assertNotNull(formUrl, "form.html was not found");
    System.out.println("STEP 3: Form URL found: " + formUrl);

    driver.get(formUrl.toExternalForm());
    System.out.println("STEP 4: Form loaded successfully");
}

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private boolean formIsValid() {
        return (Boolean) ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript(
                        "return document.getElementById('applicationForm').checkValidity();"
                );
    }

    private void fillValidFields() {
        driver.findElement(By.id("fullName")).sendKeys("Test Applicant");
        driver.findElement(By.id("email")).sendKeys("test@example.com");
        driver.findElement(By.id("mobile")).sendKeys("9876543210");

        Select service = new Select(driver.findElement(By.id("service")));
        service.selectByVisibleText("Certificate Request");

        driver.findElement(By.id("details"))
                .sendKeys("Request for a demo certificate");
    }

    @Test
    public void emptyFormShouldBeInvalid() {
        Assert.assertFalse(formIsValid(),
                "An empty form should not be valid");
    }

    @Test
    public void invalidEmailShouldBeRejected() {
        fillValidFields();

        WebElement email = driver.findElement(By.id("email"));
        email.clear();
        email.sendKeys("invalid-email");

        Assert.assertFalse(formIsValid(),
                "An invalid email should be rejected");
    }

    @Test
    public void invalidMobileShouldBeRejected() {
        fillValidFields();

        WebElement mobile = driver.findElement(By.id("mobile"));
        mobile.clear();
        mobile.sendKeys("12345");

        Assert.assertFalse(formIsValid(),
                "A mobile number with fewer than 10 digits should be rejected");
    }

    @Test
    public void validFormShouldSubmitSuccessfully() {
        fillValidFields();

        Assert.assertTrue(formIsValid(),
                "The completed form should be valid");

        driver.findElement(
                By.cssSelector("#applicationForm button[type='submit']")
        ).click();

        WebElement message = driver.findElement(By.id("successMessage"));

        Assert.assertTrue(message.isDisplayed(),
                "The success message should be visible");

        Assert.assertEquals(
                message.getText(),
                "Application submitted successfully."
        );
    }
}