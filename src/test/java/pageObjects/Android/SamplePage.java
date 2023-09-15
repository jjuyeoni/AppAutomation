package pageObjects.Android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;
import utils.Helpers.ElementHandlers;

public class SamplePage extends Common{

    public AndroidDriver androidDriver;
    public ElementHandlers handlers;
    public SoftAssert softAssert;

    public SamplePage(AndroidDriver androidDriver){
        super(androidDriver);
        this.androidDriver = androidDriver;
        this.handlers = new ElementHandlers(androidDriver);
        this.softAssert = new SoftAssert();
        PageFactory.initElements(new AppiumFieldDecorator(androidDriver), this);
    }

    public void shoppingContent(WebElement element){
        try{
            String content = element.getText();

            // 첫번째 제품 클릭
            element.click();
            String realName = androidDriver.findElement(By.id("kr.co.company.hwahae:id/tv_name")).getText();
            System.out.println(content + " : " +realName);

            // 제품명 동일한지 비교
            softAssert.assertTrue(realName.contains(content));
        } catch (AssertionError e) {
            System.out.println("제품명이 동일하지 않습니다.");
        }
    }

    public void scrollDown(double percent){
        WebElement element = androidDriver.findElement(By.id("android:id/content"));
        handlers.swipeByTouchActions(androidDriver,element,"up", percent);
    }

    public void sampleDetail(){
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/rv_sample_goods_request")).click();

        String sample_goods = androidDriver.findElement(By.id("kr.co.company.hwahae:id/tv_brand_sample_goods_experience")).getText();
        softAssert.assertTrue(sample_goods.contains("샘플 체험"));

        scrollDown(0.3);
        WebElement sample = androidDriver.findElement(By.id("kr.co.company.hwahae:id/tv_product_name"));
        shoppingContent(sample);

        pressBackButton();
    }

    public void clickCartBtn(){
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/btn_cart")).click();

        // 샘플체험 0원 노출 확인
        softAssert.assertEquals(androidDriver.findElement(By.id("kr.co.company.hwahae:id/tv_sample_goods_experience")).getText()
                ,"샘플 체험");
        softAssert.assertEquals(androidDriver.findElement(By.id("kr.co.company.hwahae:id/tv_price")).getText()
                ,"0");
        softAssert.assertEquals(androidDriver.findElement(By.id("kr.co.company.hwahae:id/tv_price_won")).getText()
                , "원");

        androidDriver.findElement(By.id("kr.co.company.hwahae:id/btn_plus")).click();
        isTextDisplayed("1개까지 구매 가능합니다");
        clickToText("확인");
    }

    public void checkInCart(){
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/btn_cart")).click();

        clickToText("아니요");

        androidDriver.findElement(By.id("kr.co.company.hwahae:id/fragment_goods_cta")).click(); // 바텀 레이어 닫기

        // 장바구니 클릭
        androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout[3]/android.widget.FrameLayout")).click();
        // 수량 [+] 클릭
        androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.widget.Button[3]")).click();

        isTextDisplayed("1개까지 구매 가능합니다.");
        clickToText("확인");

        androidDriver.findElement(By.xpath("//*[contains(@text,'주문하기')]")).click();
        isTextDisplayed("주문/결제");
    }

    public void checkPayment(){
        scrollDown(0.2);

        String text1 = androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View[8]"))
                .getText();
        softAssert.assertEquals(text1, "총 상품 금액이 10,000원 이상일 경우 사용 가능 (배송비 제외)");

        scrollDown(0.2);

        String text2 = androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View[11]"))
                .getText();
        softAssert.assertEquals(text2, "샘플 체험 상품은 포인트 적립 (기본적립, 리뷰적립) 불가");
    }
}
