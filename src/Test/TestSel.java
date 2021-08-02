package Test;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertEquals;

public class TestSel {

    @Test
    public void testGoogle() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "/Users/lanre/eclipse-workspace/geckodriver");

        WebDriver webDriver = new FirefoxDriver();
        String user = "test@user.com";
        String password = "test";
         webDriver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        WebElement selectCountry = webDriver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));

        //Selecting options from dropddown
        Select select = new Select(selectCountry);
        select.selectByIndex(2);
        String text = select.getFirstSelectedOption().getText();
        assertEquals("AED", text);


    }

}
