package Test;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import static org.junit.Assert.*;

public class FluentWaits {
    WebDriver webDriver;


    @Before
    public void init() {
        System.setProperty("webdriver.gecko.driver", "/Users/lanre/eclipse-workspace/geckodriver");
        if (webDriver != null) {
            return;
        } else {
            webDriver = new FirefoxDriver();
            //implicit wait
            // webDriver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        }
        webDriver.get("http://the-internet.herokuapp.com/dynamic_loading/1");
    }

    @AfterEach
    public void tearDown() {
        webDriver.close();
        webDriver.quit();
    }

    @Test
    public void test_fluent_wait() {
        webDriver.findElement(By.cssSelector("div[id='start'] button")).click();
        WebElement h4Text = webDriver.findElement(By.cssSelector("[id='finish'] h4"));

        assertFalse(h4Text.isDisplayed());
        Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver webDriver) {


                //asserting the visibility of element.
                if (h4Text.isDisplayed()) {
                    return webDriver.findElement(By.cssSelector("[id='finish'] h4"));

                } else {
                    return null;
                }
            }
        });
        assertTrue(h4Text.isDisplayed());
        assertEquals("Hello World!", foo.getText());
     }

}


