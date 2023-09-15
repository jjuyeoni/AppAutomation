package pageObjects.Android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import tests.Android.Benefit;
import utils.Helpers.ElementHandlers;

import java.time.Duration;

public class BenefitPage extends Common{

    public AndroidDriver androidDriver;
    public SoftAssert softAssert;
    public ElementHandlers handlers;

    public BenefitPage(AndroidDriver androidDriver){
        super(androidDriver);
        this.androidDriver = androidDriver;
        this.handlers = new ElementHandlers(androidDriver);
        this.softAssert = new SoftAssert();
        PageFactory.initElements(new AppiumFieldDecorator(androidDriver), this);
    }

    public boolean isToastDisplayed(int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(androidDriver, Duration.ofSeconds(timeoutInSeconds));
        try {
            WebElement toastElement =
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Toast[1]")));
            String toastMessage = toastElement.getText();
            System.out.println("Toast Message: " + toastMessage);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isTextDisplayed(int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(androidDriver, Duration.ofSeconds(timeoutInSeconds));
        try {
            WebElement element =
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='출석 체크 완료!']")));
            System.out.println("Toast Message: " + element.getText());
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }


    public void todayWeather(){
        androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.LinearLayout/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.TextView[2]")).click();
        compareText("날씨 체크");

        try {
            WebElement element = androidDriver.findElement(By.xpath("//*[@text='내일 또 만나요']"));
            System.out.println("이미 이벤트에 참여하였습니다.");
        }
        catch (NoSuchElementException e) {

            androidDriver.findElement(By.xpath("//android.widget.Button[contains(@text,'날씨 체크')]")).click();

            // 앱 사용 중에만 허용
            androidDriver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")).click();

            boolean isToastDisplayed = isToastDisplayed(10);
            softAssert.assertTrue(isToastDisplayed);
        }
    }

    public void attendanceCheck(){
        androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.LinearLayout/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.TextView[2]")).click();
        compareText("출석 체크");

        try {
            WebElement element = androidDriver.findElement(By.xpath("//*[@text='내일 또 만나요']"));
            System.out.println("이미 이벤트에 참여하였습니다.");
        }
        catch (NoSuchElementException e) {
            androidDriver.findElement(By.xpath("//android.widget.Button[contains(@text,'오늘 출석 체크하기')]")).click();

            boolean isTextDisplayed = isTextDisplayed(10);
            softAssert.assertTrue(isTextDisplayed);
        }
    }
}
