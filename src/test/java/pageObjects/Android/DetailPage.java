package pageObjects.Android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import utils.Helpers.ElementHandlers;

public class DetailPage extends Common{

    public AndroidDriver androidDriver;
    public ElementHandlers handlers;
    public SoftAssert softAssert;

    public DetailPage(AndroidDriver androidDriver){
        super(androidDriver);
        this.androidDriver = androidDriver;
        this.handlers = new ElementHandlers(androidDriver);
        this.softAssert = new SoftAssert();
        PageFactory.initElements(new AppiumFieldDecorator(androidDriver), this);
    }

    public void scrollDown(double percent){
        WebElement element = androidDriver.findElement(By.id("android:id/content"));
        handlers.swipeByTouchActions(androidDriver,element,"up", percent);
    }

    public void PrePutComparison(){
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/layout_item_container")).click();
        scrollDown(0.2);

        androidDriver.findElement(By.id("kr.co.company.hwahae:id/btn_compare")).click();

        boolean isToastDisplayed = isToastMessageDisplayed("비교함에 담겼습니다.", 10);
        softAssert.assertTrue(isToastDisplayed, "토스트 메시지 '비교함에 담겼습니다.'가 표시되지 않았습니다.");

        pressBackButton();
    }


    public void searchProduct(){
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/main_actionbar_search_container")).click(); // 검색 홈 진입

        PrePutComparison();

        androidDriver.findElement(By.id("kr.co.company.hwahae:id/actionbar_search_product_edittext"))
                .sendKeys("어드밴스드 제니피끄");
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/actionbar_search_product_execute_btn")).click();

        androidDriver.findElement(By.id("kr.co.company.hwahae:id/ll_product_item")).click(); // 첫번째 제품 클릭
    }

    public void requireModify(){
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/tv_correct_request")).click();
        compareText("정보 수정 요청");

        pressBackButton();
    }

    public void putComparison(){
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/btn_compare")).click();

        boolean isToastDisplayed = isToastMessageDisplayed("비교함에 담겼습니다.", 10);
        softAssert.assertTrue(isToastDisplayed, "토스트 메시지 '비교함에 담겼습니다.'가 표시되지 않았습니다.");
    }

    public void ComparisonBox(){
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/do_compare")).click();
        compareText("제품 비교하기");

        // 첫번째 제품 평점 영역
        androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.TableLayout/android.widget.TableRow[3]/android.widget.LinearLayout[1]")).click();
        compareText("제품 리뷰");
        pressBackButton();

        // 두번째 제품 랭킹 영역
        androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.TableLayout/android.widget.TableRow[6]/android.widget.LinearLayout[2]")).click();
        compareText("카테고리별 랭킹");
        pressBackButton();

        // 첫번째 제품 지성피부 영역
        androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.TableLayout/android.widget.TableRow[9]/android.widget.LinearLayout[1]")).click();
        compareText("피부 타입별");
        pressBackButton();

        // 두번째 제품 구매하기 클릭
        clickToText("구매하기");
        compareText("판매 중인 상품");
        pressBackButton();

        pressBackButton();
    }

    public void switchFilter(){
        handlers.scrollToId("kr.co.company.hwahae:id/review_title");
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/review_title")).click();

        WebElement element = androidDriver.findElement(By.id("kr.co.company.hwahae:id/filter_chip_fitting"));
        element.click();

        softAssert.assertEquals(element.getAttribute("checked"),"true");
    }

    public void clickFilter(){
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/filter_chip_setting")).click();

        clickToText("복합성");
        clickToText("민감성");
        clickToText("10대");
        androidDriver.findElement(By.id("kr.co.company.hwahae:id/filter_complete_button")).click();

        androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.view.ViewGroup[2]/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]")).click();

        boolean isToastDisplayed = isToastMessageDisplayed("‘필터’와 ‘리뷰토픽’을 같이 선택할 수 없어요", 10);
        softAssert.assertTrue(isToastDisplayed, "'‘필터’와 ‘리뷰토픽’을 같이 선택할 수 없어요'가 표시되지 않았습니다.");

        pressBackButton();
        pressBackButton();
        pressBackButton();
        pressBackButton();
    }


}
