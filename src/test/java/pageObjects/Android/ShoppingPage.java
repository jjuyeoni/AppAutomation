package pageObjects.Android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utils.Helpers.ElementHandlers;

import java.time.Duration;

public class ShoppingPage extends Common{

    public AndroidDriver androidDriver;
    public SoftAssert softAssert;
    public ElementHandlers handlers;

    public ShoppingPage(AndroidDriver androidDriver){
        super(androidDriver);
        this.androidDriver = androidDriver;
        this.handlers = new ElementHandlers(androidDriver);
        this.softAssert = new SoftAssert();
        PageFactory.initElements(new AppiumFieldDecorator(androidDriver), this);
    }

    public void checkSelectedTab(String tab){
        softAssert.assertTrue(androidDriver.findElement(By.xpath("//android.widget.TextView[@text='" + tab + "']")).isSelected());
    }

    public void cmpProductByPrice(String tab){
        WebElement product = null;
        try {

            if(tab.equals("shop")) {
                product = androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.GridView/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.TextView[1]"));
            }
            else if(tab.equals("offer")) {
                product = androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.GridView/android.view.ViewGroup[4]/android.widget.TextView[1]"));
            }

            String productName = product.getText();
            // 스토어 이름 제외하고 제품명만 남기기
            String expectedName = productName.substring(productName.indexOf(" ") + 1);

            // 첫번째 제품 클릭
            product.click();
            String realName = androidDriver.findElement(By.id("kr.co.company.hwahae:id/tv_name")).getText();

            // 제품명 동일한지 비교
            softAssert.assertTrue(realName.contains(expectedName));
            pressBackButton();

        } catch (AssertionError e) {
            System.out.println("제품명이 동일하지 않습니다.");
        }
    }

    public void ongoingOffer(){
        WebElement element = androidDriver.findElement(By.id("kr.co.company.hwahae:id/viewpager_main"));
        handlers.swipeByTouchActions(androidDriver,element,"up",0.3);
    }

    public void upcomingOffer(){
        clickToText("오픈 예정인 특가");
        clickToText("알림 받기");

        boolean isToastDisplayed = isToastMessageDisplayed("알림 설정 완료! 특가 시작 시 푸시로 알려드릴게요.", 10);
        softAssert.assertTrue(isToastDisplayed, "토스트 메시지 '알림 설정 완료!'가 표시되지 않았습니다.");

        clickToText("알림 받는 중");
        isToastDisplayed = isToastMessageDisplayed("알림 설정 취소!", 10);
        softAssert.assertTrue(isToastDisplayed, "토스트 메시지 '알림 설정 취소!'가 표시되지 않았습니다.");
    }

}
