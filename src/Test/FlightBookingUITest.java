package Test;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.*;


public class FlightBookingUITest {
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
        webDriver.quit();

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

    /**
     * When 2 nodes are found, tell selenium to select the second element found
     * You can do this by appending an index number at the end of the xpath or css selector
     */
    @Test
    public void test_dynamic_drop_down() {


        WebElement departureDropdown = webDriver.findElement(By.xpath("//input[@id='ctl00_mainContent_ddl_originStation1_CTXT']"));
        departureDropdown.click();

        WebElement departureCity = webDriver.findElement(By.xpath("//a[@value='IXB']"));
        departureCity.click();
        String cityName = departureDropdown.getAttribute("value");
        assertEquals("Bagdogra (IXB)", cityName);


        WebElement destinationDropDown = webDriver.findElement(By.xpath("//input[@id='ctl00_mainContent_ddl_destinationStation1_CTXT']"));
        destinationDropDown.click();

        WebElement selectedDst = webDriver.findElement(By.xpath("//a[@value='DEL']"));

        new WebDriverWait(webDriver, Duration.ofSeconds(10).toSeconds())
                .until(webDriver -> selectedDst).click();

        String selectedDestination = destinationDropDown.getAttribute("value");
        assertEquals("Delhi (DEL)", selectedDestination);

    }

    /**
     * Testing UI with suggestive dropdown.
     * The users input is grabbed from the input box and a loop goes through all
     * list of suggested values. getText() is called on each iteration and the value is compared to
     * the test entered by the user
     */

    @Test
    public void test_input_suggestion_dropdown() {

        WebElement userInput = webDriver.findElement(By.id("autosuggest"));

        userInput.sendKeys("Argentina");

        List<WebElement> options = webDriver.findElements(By.cssSelector("li[class='ui-menu-item'] a"));

        options.forEach(el -> {
            if (el.getText().equalsIgnoreCase("Argentina")) {
                el.click();
            }

        });

    }
    /*
    Handling checkbox
     */

    @Test
    public void test_check_box() {
        WebElement checker = webDriver.findElement(By.id("ctl00_mainContent_chk_friendsandfamily"));
        assertFalse(checker.isSelected());
        checker.click();

        assert (checker.isSelected());

        List<WebElement> elem = webDriver.findElements(By.cssSelector("input[type=checkbox]"));

        System.out.println(elem.size());

    }


    /**
     * Checking UI elements that depends on eachother
     */
    @Test
    public void check_disabled() {

        WebElement oneWay = webDriver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_0"));
        assertTrue(oneWay.isSelected());
        WebElement returnTrip = webDriver.findElement(By.id("Div1"));
        boolean val = returnTrip.getAttribute("style").contains("0.5");
        assertTrue(val);


        WebElement twoWay = webDriver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_1"));
        twoWay.click();
        assertTrue(twoWay.isSelected());
        boolean val2 = returnTrip.getAttribute("style").contains("1");
        assertTrue(val2);


    }

    @Test
    public void handle_pop_up() {

        webDriver.get("https://rahulshettyacademy.com/AutomationPractice/");
        WebElement name = webDriver.findElement(By.id("name"));
        WebElement btn = webDriver.findElement(By.id("confirmbtn"));
        name.sendKeys("Lanre");
        btn.click();


        //This wil switch the selenuim to handle Javascript alert
        Alert alert = webDriver.switchTo().alert();

        //alert.getTExt() will get the text of an alert

        assertEquals("Hello Lanre, Are you sure you want to confirm?", alert.getText());
    }

}
