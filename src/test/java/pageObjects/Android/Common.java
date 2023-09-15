package pageObjects.Android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import utils.Helpers.ElementHandlers;

import java.time.Duration;

public class Common {

    AndroidDriver androidDriver;
    SoftAssert softAssert;

    public Common(AndroidDriver androidDriver){
        this.androidDriver = androidDriver;
        this.softAssert = new SoftAssert();
        PageFactory.initElements(new AppiumFieldDecorator(androidDriver), this);
    }

    public void pressBackButton() {
        androidDriver.navigate().back();
    }

    public void clickToText(String name){
        try {
            WebElement element = androidDriver.findElement(By.xpath("//*[@text='" + name + "']"));
            element.click();
        }catch (Exception e) {
            System.out.println("Not Found : "+ name);
        }
    }
    public void compareText(String name){
        try{
            WebElement element = androidDriver.findElement(By.xpath("//*[contains(@text,'" + name + "')]"));
            softAssert.assertTrue(element.isDisplayed());
        }catch (Exception e) {
            System.out.println("Not Found : " + name);
        }
    }

    public boolean isToastMessageDisplayed(String expectedMessage, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(androidDriver, Duration.ofSeconds(timeoutInSeconds));
        try {
            return wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//android.widget.Toast[1]"), expectedMessage));
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isTextDisplayed(String name) {
        int timeoutInSeconds = 10;
        WebDriverWait wait = new WebDriverWait(androidDriver, Duration.ofSeconds(timeoutInSeconds));
        try {
            WebElement element =
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'"+name+"')]")));
            softAssert.assertTrue(element.isDisplayed());
            System.out.println("Message: " + element.getText());
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
