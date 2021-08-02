package Test;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;


public class TestSel {
    WebDriver webDriver;

    @Before
    public void init() {
        System.setProperty("webdriver.gecko.driver", "/Users/lanre/eclipse-workspace/geckodriver");
        if (webDriver != null) {
            return;
        } else {
            webDriver = new FirefoxDriver();
        }

        webDriver.get("https://rahulshettyacademy.com/dropdownsPractise/");

    }

    @AfterEach
    public void tearDown() {
        webDriver.close();

    }

    @Test
    public void test_html_select_tag() {

        WebElement selectCountry = webDriver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));

        //Selecting options from dropddown
        Select select = new Select(selectCountry);

//        1. selecting by index
        select.selectByIndex(2);
        String index = select.getFirstSelectedOption().getText();

        assertEquals("AED", index);
//        2. Select by visible text
        select.selectByVisibleText("USD");
        String visibleText = select.getFirstSelectedOption().getText();
        assertEquals("USD", visibleText);

        /*
         *    3. Select by value attribute
         *     values can ge gotten by inspecting the html element
         */

        select.selectByValue("INR");
        String value = select.getFirstSelectedOption().getText();
        assertEquals("INR", value);

    }

    @Test
    public void test_dropdown_with_click() {
        WebElement clickDropDown = webDriver.findElement(By.xpath("//div[@id='divpaxinfo']"));
        assertEquals("1 Adult", clickDropDown.getText());
        clickDropDown.click();

        WebElement addAdults = webDriver.findElement(By.xpath("//span[@id='hrefIncAdt']"));

        for (int i = 0; i < 4; i++) {
            addAdults.click();
        }
        assertEquals("5 Adult", clickDropDown.getText());


    }

    @Test
    public void test_dynamic_drop_down()  {

        //whe 2 nodes are found, tell selenium to select the second element found
//        You can do this by appending an index number at the end of the xpath or css selector


        WebElement departureDropdown = webDriver.findElement(By.xpath("//input[@id='ctl00_mainContent_ddl_originStation1_CTXT']"));
        departureDropdown.click();

        WebElement departureCity= webDriver.findElement(By.xpath("//a[@value='IXB']"));
        departureCity.click();
        String cityName = departureDropdown.getAttribute("value");
        assertEquals("Bagdogra (IXB)", cityName);


        WebElement destinationDropDown = webDriver.findElement(By.xpath("//input[@id='ctl00_mainContent_ddl_destinationStation1_CTXT']"));
        destinationDropDown.click();

        WebElement selectedDst = webDriver.findElement(By.xpath("//a[@value='DEL']"));

        new WebDriverWait(webDriver, Duration.ofSeconds(10).toSeconds())
                .until(webDriver ->selectedDst).click();

        String selectedDestination = destinationDropDown.getAttribute("value");
        assertEquals("Delhi (DEL)", selectedDestination);







    }

}
