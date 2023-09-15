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

public class MyPage extends Common{
    public AndroidDriver androidDriver;
    public ElementHandlers handlers;
    public SoftAssert softAssert;

    public MyPage(AndroidDriver androidDriver){
        super(androidDriver);
        this.androidDriver = androidDriver;
        this.handlers = new ElementHandlers(androidDriver);
        this.softAssert = new SoftAssert();
        PageFactory.initElements(new AppiumFieldDecorator(androidDriver), this);
    }


    public void scrollToBottom(){
        WebElement element = androidDriver.findElement(By.id("kr.co.company.hwahae:id/container"));
        handlers.swipeByTouchActions(androidDriver,element,"up", 0.05);
    }

    public void scrollEventView(){
        WebElement element = androidDriver.findElement(By.id("android:id/content"));
        handlers.swipeByTouchActions(androidDriver,element,"up", 0.15);
    }

    public void modifyPhoto(){
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/iv_profile_image")).click();
        clickToText("사진 앨범에서 선택");

        // 사진 미디어 권한 허용
        androidDriver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_button")).click();
        clickToText("전체보기");

        // 첫번째 사진 클릭
        androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.GridView/android.widget.FrameLayout[1]")).click();
        clickToText("자르기");

        compareText("마이페이지");

    }

    public void modifyProfile(){

        clickToText("내 정보 수정");
        clickToText("건성");
        clickToText("봄웜톤");
        clickToText("저장");

        compareText("수정된 회원정보를 저장할까요?");
        clickToText("예");

        boolean isToastDisplayed = isToastMessageDisplayed("수정 완료!", 10);
        softAssert.assertTrue(isToastDisplayed, "토스트 메시지 '수정 완료!'가 표시되지 않았습니다.");

        pressBackButton();

    }

    public void modifyAddress(String s){
        clickToText("이벤트 배송지 수정");
        compareText("이벤트 배송지 수정");

        WebElement textField = androidDriver.findElement(By.id("kr.co.company.hwahae:id/address_detail_text"));
       // int textLength = textField.getText().length();

        textField.click();
        boolean keyboardShown = androidDriver.isKeyboardShown();
        softAssert.assertTrue(keyboardShown);

        textField.clear();
        textField.sendKeys(s);

        clickToText("저장");
        compareText("저장이 완료 됐습니다.");
        clickToText("확인");

        compareText("마이페이지");
    }

    public void checkAddress(){
        androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.view.ViewGroup[1]")).click();

        clickToText("신청하기");
        String add1 = androidDriver.findElement(By.id("kr.co.company.hwahae:id/address_detail_text")).getText();

        softAssert.assertEquals("test",add1);
    }

    public void clickMagnifier(){
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/ibtn_search_brand")).click();
        compareText("브랜드");

        pressBackButton();
    }

    public void clickFolder(String s){
        androidDriver
                .findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.ListView/android.widget.FrameLayout[1]"))
                .click();

        if(s.equals("product")){
            compareText("즐겨찾는 제품");
        }
        else if(s.equals("ingredient")){
            compareText("즐겨찾는 성분");
        }
        else
            System.out.println("변수명을 확인해주세요.");

        pressBackButton();
    }

    public void clickUser(){
        androidDriver
                .findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.ListView/android.widget.FrameLayout[1]"))
                .click();

        compareText("사용자 리뷰 보기");
        pressBackButton();
    }

    public void clickEditer(){
        androidDriver
                .findElement(By.id("kr.co.company.hwahae:id/subscription_recycler_view"))
                .click();

        compareText("에디터 콘텐츠 보기");
        pressBackButton();
    }

    public void checkColorReview(){
        scrollEventView();

        androidDriver.findElement(By.xpath("//android.view.View[@content-desc=\"event_button\"]")).click();
        compareText("발색 리뷰 쓰기");
        pressBackButton();

        androidDriver.findElement(By.xpath("//android.view.View[@content-desc=\"history_button\"]")).click();
        compareText("발색 사진 이벤트 참여 내역");
        pressBackButton();
    }

    public void findTitleForInviteFriend(){
        WebElement title = androidDriver
                .findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout[2]/android.widget.TextView"));
        String text = title.getText();

        softAssert.assertEquals(text,"친구 초대");
    }

}
