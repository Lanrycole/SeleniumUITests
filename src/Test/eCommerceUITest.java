package Test;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;

public class eCommerceUITest {
    WebDriver webDriver;

    @Before
    public void init() {
        System.setProperty("webdriver.gecko.driver", "/Users/lanre/eclipse-workspace/geckodriver");
        if (webDriver != null) {
            return;
        } else {
            webDriver = new FirefoxDriver();
        }

        webDriver.get("https://rahulshettyacademy.com/seleniumPractise/#/");

    }

    @AfterEach
    public void tearDown() {
        webDriver.close();
        webDriver.quit();

    }

    @Test
    public void add_items_to_cart() {

         WebElement addBtn = webDriver.findElement(By.xpath("/html/body/div/div/div[1]/div/div[1]/div[2]/a[2]"));
        WebElement inputValue = webDriver.findElement(By.xpath("/html/body/div/div/div[1]/div/div[1]/div[2]/input"));
        WebElement orderPrice = webDriver.findElement(By.cssSelector("div.product:nth-child(1) > p:nth-child(3)"));
        WebElement orderTotal = webDriver.findElement(By.xpath("/html/body/div/div/header/div/div[3]/div[1]/table/tbody/tr[2]/td[3]/strong"));
        assertEquals("120", orderPrice.getText());
        addBtn.click();
        addBtn.click();
        assertEquals("3", inputValue.getAttribute("value"));


        WebElement addToCart = webDriver.findElement(By.cssSelector("div.product:nth-child(1) > div:nth-child(5) > button:nth-child(1)"));
        addToCart.click();

        assertEquals("360", orderTotal.getText());

        webDriver.get("https://rahulshettyacademy.com/seleniumPractise/#/cart");

        WebElement grandTotal = webDriver.findElement(By.xpath("/html/body/div/div/div/div/div/span[1]"));

        assertEquals("360", grandTotal.getText());

    }


}
