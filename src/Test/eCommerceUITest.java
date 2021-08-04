package Test;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
    public void add_items_to_cart() throws InterruptedException {


        // You can get the Xpath of an HTML element by using the text. e.g //button[text()="text"]
        WebElement orderTotal = webDriver.findElement(By.xpath("/html/body/div/div/header/div/div[3]/div[1]/table/tbody/tr[2]/td[3]/strong"));

        List<WebElement> productsList = webDriver.findElements(By.cssSelector("H4.PRODUCT-NAME"));

        List<String> name = new ArrayList<>();
        name.add("Cucumber");
        name.add("Brocolli");

        int j = 0;
        for (int i = 0; i < productsList.size(); i++) {
            String pName = productsList.get(i).getText();
            String[] split = pName.split("-");

            if (name.contains(split[0].trim())) {
//
                System.out.println(productsList.get(i).getText());
                webDriver.findElements(By.xpath("//a[@class= 'increment']")).get(i).click();
                webDriver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
                j++;
                //breaking out of loop if all items are met
                if (j == 3) {
                    break;
                }
            }
        }

        assertEquals("336", orderTotal.getText());
        webDriver.get("https://rahulshettyacademy.com/seleniumPractise/#/cart");
        WebElement grandTotal = webDriver.findElement(By.xpath("/html/body/div/div/div/div/div/span[1]"));
        assertEquals("336", grandTotal.getText());
    }
}
