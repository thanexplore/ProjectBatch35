package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;

import java.time.Duration;


public class ObjectUtilities {

    WebDriver driver;

    public ObjectUtilities (WebDriver driver) throws InterruptedException {
        this.driver=driver;

    }

    public void waitforElement(int timeOut, WebElement locator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public boolean isElementvisible(int timeOut, WebElement locator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        WebElement elementtoWait = wait.until(ExpectedConditions.elementToBeClickable(locator));
        return elementtoWait.isDisplayed();
    }

    public void waitFor(int timeOut){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
    }

}
